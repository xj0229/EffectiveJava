package com.SE.STClient.Functions;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.SE.SIPServer.ICustomerManagement;
import com.SE.SQLite.SQLiteConnector;

import it.sauronsoftware.base64.Base64;

public class func implements ICustomerManagement{

	 private MessageDigest messageDigest;
	 public static final String DEFAULT_ALGORITHM = "MD5";
	 
	 public SQLiteConnector m_Connect =new SQLiteConnector();
	 
	 private static final char[] toHex = { '0', '1', '2', '3', '4', '5', '6',
				'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	 
		public static String toHexString(byte b[]) {
			int pos = 0;
			char[] c = new char[b.length*2];
			for (int i=0; i< b.length; i++) {
				c[pos++] = toHex[(b[i] >> 4) & 0x0F];
				c[pos++] = toHex[b[i] & 0x0f];
			}
			return new String(c);
		}
	 
	public String getResponse (String user,String password,String ip, int port,String nonce){
		
		try {
			messageDigest = MessageDigest.getInstance(DEFAULT_ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			System.out.println("Algorithm not found " + e);
			e.printStackTrace();
		}

		 String m_realm=user.substring(0, 10);
		
		 String m_uri="sip:"+user+"@"+ip+":"+port;
//		 String m_uri="sip:"+ip+":"+port;
		 String A1=user+":"+ m_realm +":"+ password;
		  byte[]  mdbytes = messageDigest.digest(A1.getBytes());
		  String HA1 = toHexString(mdbytes);
		  String A2="REGISTER"+":"+m_uri;
		  mdbytes = messageDigest.digest(A2.getBytes());
		  String HA2 = toHexString(mdbytes);
		  String KD=HA1 + ":" + nonce+":" +HA2;
		  mdbytes = messageDigest.digest(KD.getBytes());
		  String m_response= toHexString(mdbytes);
		  return m_response;
		
		
	}
	@Override
	public List<String> getNonce (String user, String oldpswd,String password,String ip, int port){
	
		String selectsql="SELECT ServerID,ServerIP,ServerPort FROM ClientInfo Where rowid=1";
		 ResultSet m_result=m_Connect.executeSelect(selectsql);
		 long serverId=0;
		 try {
		 serverId=m_result.getInt("ServerID");
		 } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		 String serverIp = "";
			try {
				serverIp =m_result.getString("ServerIP");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 int severPort=0;
			try {
				severPort =m_result.getInt("ServerPort");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				
		String oldpsw = oldpswd;
		String strServerId=Long.toString(serverId);
		String strUserport=Integer.toString(port);
		String strServerport=Integer.toString(severPort);
		String From=strServerId+"@"+serverIp+":"+strServerport;
		String To=user+"@"+serverIp+":"+strUserport;
		String nonceStr=From+":"+To+":"+password;
		String nonce=new String(Base64.encode(nonceStr));
		String nonce1Str=nonce+":"+oldpsw;
		
		 byte m_bytes[]=messageDigest.digest(nonce1Str.getBytes());
		 String m_Nonce1String=toHexString(m_bytes);
		 String nonce1=new String(Base64.encode(m_Nonce1String));
		
		 List<String> nonceList=new ArrayList<String>();
		 nonceList.add(nonce);
		 nonceList.add(nonce1);
		
		return nonceList;	
	}

}
