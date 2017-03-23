/**
 * 
 */
package com.SE.SIPServer;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.TooManyListenersException;

import javax.sip.InvalidArgumentException;
import javax.sip.ObjectInUseException;
import javax.sip.PeerUnavailableException;
import javax.sip.SipException;
import javax.sip.TransportNotSupportedException;

/**
 * @author Bryson Han
 *
 */
public class SIPTest {

	/**
	 * @param args
	 * @throws TooManyListenersException 
	 * @throws InvalidArgumentException 
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws SipException 
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws InvalidArgumentException, TooManyListenersException, ParseException, SipException, SQLException, InterruptedException {
		// TODO Auto-generated method stub
		SipServer.initilizeSipServer("00151000000800000001","192.168.10.26",5060);
		ISipCustomer m_cm = new CustomerManage();
		m_cm.clientRegister("12345");
	}

}
