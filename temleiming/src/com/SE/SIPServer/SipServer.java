/**
 * 
 */
package com.SE.SIPServer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.TooManyListenersException;
import java.util.concurrent.CountDownLatch;

import javax.sip.ClientTransaction;
import javax.sip.DialogTerminatedEvent;
import javax.sip.IOExceptionEvent;
import javax.sip.InvalidArgumentException;
import javax.sip.ListeningPoint;
import javax.sip.ObjectInUseException;
import javax.sip.PeerUnavailableException;
import javax.sip.RequestEvent;
import javax.sip.ResponseEvent;
import javax.sip.ServerTransaction;
import javax.sip.SipException;
import javax.sip.SipFactory;
import javax.sip.SipListener;
import javax.sip.SipProvider;
import javax.sip.SipStack;
import javax.sip.TimeoutEvent;
import javax.sip.TransactionAlreadyExistsException;
import javax.sip.TransactionTerminatedEvent;
import javax.sip.TransactionUnavailableException;
import javax.sip.TransportNotSupportedException;
import javax.sip.address.Address;
import javax.sip.address.AddressFactory;
import javax.sip.address.SipURI;
import javax.sip.header.AllowHeader;
import javax.sip.header.AuthorizationHeader;
import javax.sip.header.CSeqHeader;
import javax.sip.header.CallIdHeader;
import javax.sip.header.ContactHeader;
import javax.sip.header.ContentTypeHeader;
import javax.sip.header.FromHeader;
import javax.sip.header.Header;
import javax.sip.header.HeaderFactory;
import javax.sip.header.MaxForwardsHeader;
import javax.sip.header.RouteHeader;
import javax.sip.header.SubjectHeader;
import javax.sip.header.ToHeader;
import javax.sip.header.ViaHeader;
import javax.sip.header.WWWAuthenticateHeader;
import javax.sip.message.MessageFactory;
import javax.sip.message.Request;
import javax.sip.message.Response;
import org.apache.log4j.Logger;

import com.SE.Manscdp.MessageProcessor;
import com.SE.Notify.WatchedObject;
import com.SE.SQLite.SQLiteConnector;
import com.SE.STClient.Functions.func;
import com.SE.UdpNetty.NettyServer;

import gov.nist.javax.sip.header.AuthenticationHeader;
import gov.nist.javax.sip.header.Authorization;
import gov.nist.javax.sip.header.Expires;
import gov.nist.javax.sip.header.UserAgent;

/**
 * @author Bryson Han
 *
 */
public class SipServer implements SipListener{
	private String m_userName;
	//Object of sip factory to implement sip stack
	private SipFactory m_sipFactory;
	//Object of message factory to implement objects related to message
	private MessageFactory m_messageFactory;
	//Object of address factory to implement objects related to address
	private AddressFactory m_addressFactory;
	//First interface to create ListeningPoint and SipProvider
	private SipStack m_sipStack;
	//Object of header factory to implement objects related to header
	private HeaderFactory m_headerFactory;
	//SipProvide is used for providing message sending service
	private SipProvider m_sipProvider;
	//IP address
	private String m_host;
	//Local port		
	private int m_port;
	//Main Server ID
	private int m_serverID;
	//Main Server IP
	private String m_serverIP;
	//Main Server Port
	private int m_serverPort;
	//Result of registration (log on, log off, change password, aquire catalogue)
	private List<String> m_registerResult = new ArrayList<String>();
	//Thread lock used in waiting for communication back(registration)
	private CountDownLatch m_waitLock;
	//Thread lock for decoder function
	private CountDownLatch m_decoderLock;
	
	private IMessageProcessor m_messageProcessor = new MessageProcessor();
	
	private ICustomerManagement m_customerManage = new func();
	
	private IInviteProcessor m_inviteProcessor;
	
	private INotifyProcessor m_notifyProcessor;
	//Current password
	private String m_currentPW;
	//If this is true, set the authentication when sending message
	private boolean registerFlag = false;
	
	private AuthorizationHeader m_authHeader;
	//Used for those whose result is boolean type
	private boolean m_result;
	
	private boolean m_setDecoderResult;
	
	private static SipServer m_sipServer;
	
	private static RunNetty m_netty;
	//Obsercable object used in sip
	private WatchedObject m_sipNotify = new WatchedObject();
	
	private Logger mLogger = Logger.getLogger(SipServer.class);
	
	private SipServer (String user,String ip, int port) throws 
	PeerUnavailableException, TransportNotSupportedException,
    InvalidArgumentException, ObjectInUseException,
    TooManyListenersException{
		setUserName(user);
		setHost(ip);
		setPort(port);
		m_sipFactory = SipFactory.getInstance();
		//SEFirstSip represents the method name implementing a sip stack instance
		m_sipFactory.setPathName("gov.nist");
		Properties sipProperties = new Properties();
		//set the sip stack name as StationSipServer
		sipProperties.setProperty("javax.sip.STACK_NAME", user);
		//set the sip stack ip address from the value ip 
		sipProperties.setProperty("javax.sip.IP_ADDRESS", ip);
		
		m_sipStack = m_sipFactory.createSipStack(sipProperties);
		m_headerFactory = m_sipFactory.createHeaderFactory();
		m_addressFactory = m_sipFactory.createAddressFactory();
		m_messageFactory = m_sipFactory.createMessageFactory();
		
		//Create two listening points separately for tcp and udp
		@SuppressWarnings("deprecation")
		ListeningPoint tcp_listening = m_sipStack.createListeningPoint(port, "tcp");
		@SuppressWarnings("deprecation")
		ListeningPoint udp_listening = m_sipStack.createListeningPoint(port, "udp");
		//SipLayer implements SipListener that can be the listener
		m_sipProvider = m_sipStack.createSipProvider(tcp_listening);
		m_sipProvider.addSipListener(this);
		
		m_sipProvider = m_sipStack.createSipProvider(udp_listening);
		m_sipProvider.addSipListener(this);
		
		mLogger.debug("SIP Server Started");
		
		m_netty = new RunNetty(ip);
		
		Thread rt = new Thread (m_netty);
		
		rt.start();
		
		mLogger.debug("Netty Server Started");
	}
	
	private class RunNetty implements Runnable{
		
		String m_ip;
		
		public RunNetty(String ip){
			this.m_ip = ip;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			NettyServer m_keyBoardServer = new NettyServer(m_ip,1521);
		}
		
	}
	
	public int getServerName (){
		return m_serverID;
	}
	
	public String getServerIP(){
		return m_serverIP;
	}
	
	public int getServerPort(){
		return m_serverPort;
	}
	
	public void setUserName (String u){
		m_userName = u;
	}
	
	public String getUserName (){
		return m_userName;
	}
	
	public void setHost (String h){
		m_host = h;
	}
	
	public String getHost (){
		return m_host;
	}
	
	public void setPort (int p){
		m_port = p;
	}
	
	public int getPort (){
		return m_port;
	}

	@Override
	public void processDialogTerminated(DialogTerminatedEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processIOException(IOExceptionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processRequest(RequestEvent evt) {
		// TODO Auto-generated method stub
		Request req = evt.getRequest();
		String method  = req.getMethod();
		String content = new String(req.getRawContent());
		if (method.equals(Request.MESSAGE)){
			int function = m_messageProcessor.checkMsgReq(content);
			switch (function){
			/*
			 * 0:message error
			 * 1:change password
			 * 2：get list
			 * 3:set decoder
			 * 4:alarm notify
			 */
			case 0:
			{
				
			}
			break;
			case 1:
			{
				confirm200OK(req);
				boolean result = m_messageProcessor.changePWResult(content);
				if (result){
					m_registerResult.clear();
					m_registerResult.add("True");
					m_waitLock.countDown();
				}
				else
				{
					m_registerResult.clear();
					m_registerResult.add("False");
					m_registerResult.add("Original password is not right!");
					m_waitLock.countDown();
				}
			}
			break;
			case 2:
			{
				confirm200OK(req);
				m_result = true;
				m_waitLock.countDown();
			}
			break;
			case 3:
			{
				confirm200OK(req);
				m_setDecoderResult = true;
				m_decoderLock.countDown();
			}
			break;
			case 4:
			{
				List<String> alarmNotify = 
						m_messageProcessor.alarmNotifyProcessor(content);
				m_sipNotify.setContent(alarmNotify);
				m_sipNotify.setChanged("alarm");
			}
			}
		}
		else if(method.equals(Request.NOTIFY)){
			String flag = m_notifyProcessor.checkNotify(content);
			if (flag.equals("catalogue")){
				List<String> catalogue = new LinkedList<String>(); 
				catalogue.add(m_notifyProcessor.processCatalogue(content));
				m_sipNotify.setContent(catalogue);
				m_sipNotify.setChanged("catalogue");
			}
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void processResponse(ResponseEvent evt) {
		// TODO Auto-generated method stub
		Response rp = evt.getResponse();
		int state = rp.getStatusCode();
		CSeqHeader cseq = (CSeqHeader)rp.getHeader("CSeq");
		switch(state)
		{
			case 401:
			{
				long num = cseq.getSeqNumber();
				if (num>1)
				{
					m_registerResult.clear();
					m_registerResult.add("False");
					m_registerResult.add("Register failed, wrong username or password!");
					m_waitLock.countDown();
				}
				else
				{
					WWWAuthenticateHeader auth = (WWWAuthenticateHeader)
							rp.getHeader("WWW-Authenticate");
					String nonce = auth.getNonce();
					//!!!!!!send second request with Authentication
					try {
						m_authHeader = m_headerFactory.createAuthorizationHeader("Authorization");
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						m_authHeader.setAlgorithm("MD5");
						m_authHeader.setNonce(nonce);
						m_authHeader.setUsername(m_userName);
						m_authHeader.setRealm(auth.getRealm());
						m_authHeader.setResponse(m_customerManage.getResponse(getUserName(), 
								m_currentPW, getHost(), getPort(), nonce));
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					registerFlag = true;
					try {
						sendMessage (Integer.toString(m_serverID), m_serverIP+":"+m_serverPort, null,2, 
								70, "0", Request.REGISTER, "0"); 
					} catch (ParseException | InvalidArgumentException | SipException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			break;
			case 200:
			{
				if (cseq.getMethod() == Request.REGISTER  && cseq.getSeqNumber() == 2)
				{
					m_registerResult.clear();
					m_registerResult.add("True");
					m_waitLock.countDown();
				}
			}
		}
	}

	@Override
	public void processTimeout(TimeoutEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processTransactionTerminated(TransactionTerminatedEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	private void sendMessage (String toName, String toAddr, String message, int Csq, 
			int maxForwards, String contentType, String method, String subject ) throws
    ParseException, InvalidArgumentException, SipException {
        //URI
//        SipURI requestURI = m_addressFactory.createSipURI(toName, toAddr);
		SipURI requestURI = m_addressFactory.
								createSipURI("00151000000401000001", "192.168.10.16:5060");
        requestURI.setTransportParam("udp");
        
		
		//From
		SipURI sendFrom = m_addressFactory.
				createSipURI(getUserName(),getHost() + ":" + getPort());
		Address sendFromAddress = m_addressFactory.createAddress(sendFrom);
		sendFromAddress.setDisplayName(getUserName());
		FromHeader sendFromHeader = m_headerFactory.
				createFromHeader(sendFromAddress, getUserName());
		
		//To
		SipURI sendTo = requestURI;
		Address sendToAddress = m_addressFactory.createAddress(sendTo);
		sendToAddress.setDisplayName(toName);
		ToHeader sendToHeader = m_headerFactory.createToHeader(sendToAddress, toName);
		
        //CSeq
        @SuppressWarnings("deprecation")
		CSeqHeader sendCSeqHeader = m_headerFactory.createCSeqHeader(Csq, method);
        
        //MaxForward
        MaxForwardsHeader sendMaxForwards = m_headerFactory.
        		createMaxForwardsHeader(maxForwards);
        
        //Via
        ArrayList<ViaHeader> viaHeaders = new ArrayList<ViaHeader>();
        ViaHeader viaHeader = m_headerFactory.
        		createViaHeader(getHost(),getPort(),"udp",null);
        viaHeaders.add(viaHeader);
        
        
        //CallID
        CallIdHeader sendCallIdHeader = m_sipProvider.getNewCallId();
        
        //Contact
        SipURI sendContactURI = m_addressFactory.createSipURI(getUserName(), getHost());
        sendContactURI.setPort(getPort());
        Address sendContactAddress = m_addressFactory.createAddress(sendContactURI);
        sendContactAddress.setDisplayName(getUserName());
        ContactHeader contactHeader = m_headerFactory.createContactHeader(sendContactAddress);
        
        
        //Build Request
        Request request;
    	request =  m_messageFactory.createRequest(
                requestURI, method, sendCallIdHeader, sendCSeqHeader,
                sendFromHeader, sendToHeader, viaHeaders, sendMaxForwards);
    	
        //ContentType
        if (!contentType.equals("0")){
        ContentTypeHeader contentTypeHeader = 
        		m_headerFactory.createContentTypeHeader("APPLICATION", contentType);
        
	    	//Content
	    	if (!message.isEmpty()){
	        	request.setContent(message, contentTypeHeader);
	    	}
	    	
	        //Subject
	        if (!subject.equals("0"))
	        {
	            SubjectHeader subjectHeader = m_headerFactory.createSubjectHeader(subject);
	            request.setHeader(subjectHeader);
	         }
        }
        
        //Authorization
        if (registerFlag == true)
        {
        	m_authHeader.setURI(sendFrom);
        	request.setHeader(m_authHeader);
        }
    	
        //Expires
        Expires expireHeader = (Expires) m_headerFactory.createExpiresHeader(3600);
        request.setExpires(expireHeader);
        
       //Send the message
        m_sipProvider.sendRequest(request);
	}
	
	private void confirm200OK (Request req){
		Response response = null;
		try{//Reply with 200OK
			response = m_messageFactory.createResponse(200, req);
            ServerTransaction st = m_sipProvider.getNewServerTransaction(req);
            st.sendResponse(response);
            st.terminate();
		}catch (Throwable e) {
            e.printStackTrace();
            mLogger.debug("Can't send OK reply.");
		}
	}
	
	public List<String> clientRegister(String pswd) throws ParseException, InvalidArgumentException, SipException, SQLException, InterruptedException {
		// TODO Auto-generated method stub
		 SQLiteConnector m_con = new SQLiteConnector();
		 ResultSet rs = m_con.executeSelect("select * from ClientInfo");
	      while ( rs.next() ) {
	          m_serverID = rs.getInt("ServerID");
	          m_serverIP = rs.getString("ServerIP");
	          m_serverPort = rs.getInt("ServerPort");
	      }
	    m_con.disconnect();
	    m_currentPW = null;
		m_currentPW = pswd;
		sendMessage (Integer.toString(m_serverID), m_serverIP+":"+m_serverPort, null, 1, 
				70, "0", Request.REGISTER, "0");
		m_waitLock = new CountDownLatch(1);
		m_waitLock.await();
		System.out.println(m_registerResult);
		registerFlag = false;
		return m_registerResult;
	}

	public boolean acquireList() throws ParseException, InvalidArgumentException, SipException, InterruptedException {
		// TODO Auto-generated method stub
		
		String content = m_messageProcessor.acquireList(getUserName());
		sendMessage (Integer.toString(m_serverID), m_serverIP+":"+m_serverPort, content, 1, 
				70, "MANSCDP+xml", Request.MESSAGE, "0");
		m_waitLock = new CountDownLatch(1);
		m_waitLock.await();
		mLogger.debug("获得列表"+m_result);
		return m_result;
	}

	public List<String> clientLogoff(String pswd) 
			throws ParseException, InvalidArgumentException, 
			SipException, InterruptedException {
		// TODO Auto-generated method stub
		m_currentPW = null;
		m_currentPW = pswd;
		sendMessage (Integer.toString(m_serverID), m_serverIP+":"+m_serverPort, null, 1, 
				70, "0", Request.REGISTER, "0");
		m_waitLock = new CountDownLatch(1);
		m_waitLock.await();
		mLogger.debug(m_registerResult);
		registerFlag = false;
		return m_registerResult;
	}

	public List<String> changePassword(String oldpswd, String newpswd) throws ParseException, InvalidArgumentException, SipException, InterruptedException {
		// TODO Auto-generated method stub
		m_currentPW = null;
		m_currentPW = newpswd;
		List<String> auth = m_customerManage.getNonce(getUserName(),oldpswd, m_currentPW, getHost(), getPort());
		String content = m_messageProcessor.changePassword(auth.get(0), auth.get(1), getUserName());
		sendMessage (Integer.toString(m_serverID), m_serverIP+":"+m_serverPort, content, 1, 
				70, "MANSCDP+xml", Request.MESSAGE, "0");
		m_waitLock = new CountDownLatch(1);
		m_waitLock.await();
		mLogger.debug(m_registerResult);
		registerFlag = false;
		return m_registerResult;
	}

	public boolean playVideo(String deviceID, String uri) 
			throws ParseException, InvalidArgumentException, SipException, 
			InterruptedException {
//		String content = m_inviteProcessor.playVideoContent(getUserName(), deviceID);
//		sendMessage (deviceID, uri, content, 1, 70, "SDP", Request.INVITE, null);
//		m_waitLock = new CountDownLatch(1);
//		m_waitLock.await();
		return false;
	}
	
	public void decoderPlay (String decoderID, String channel){
		m_messageProcessor.decodePlay(decoderID, channel);
	}
	
	public boolean setDecoder (String deviceID, String pannels) 
			throws ParseException, InvalidArgumentException,
			SipException, InterruptedException{
		String content = m_messageProcessor.setDecoder(deviceID, pannels);
		m_sipServer.sendMessage(Integer.toString(m_sipServer.getServerName()), 
				m_sipServer.getHost()+":"+m_sipServer.getServerPort(), 
				content, 1, 70, "MANSCDP+xml", Request.MESSAGE, "0");
		m_decoderLock = new CountDownLatch(1);
		m_decoderLock.await();
		return m_setDecoderResult;
	}
	
	public void keepAlive (){
		String content = m_messageProcessor.keepAlive(m_sipServer.getUserName());
		try {
			m_sipServer.sendMessage(Integer.toString(m_sipServer.getServerName()), 
							m_sipServer.getHost()+":"+m_sipServer.getServerPort(), 
							content, 1, 70, "MANSCDP+xml", Request.MESSAGE, "0");
		} catch (ParseException | InvalidArgumentException | SipException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void PTZcontrol (String content){
		try {
			m_sipServer.sendMessage(Integer.toString(m_sipServer.getServerName()), 
							m_sipServer.getHost()+":"+m_sipServer.getServerPort(), 
							content, 1, 70, "MANSCDP+xml", Request.MESSAGE, "0");
		} catch (ParseException | InvalidArgumentException | SipException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public WatchedObject getSipObservable (){
		return m_sipNotify;
	}
	
	public static SipServer getSipInstance (){
		return m_sipServer;
	}

	public static void initilizeSipServer(String user, String ip, int port) 
			throws PeerUnavailableException, TransportNotSupportedException, 
			ObjectInUseException, InvalidArgumentException, TooManyListenersException {
		m_sipServer = new SipServer(user,ip,port);
	}
	
}


