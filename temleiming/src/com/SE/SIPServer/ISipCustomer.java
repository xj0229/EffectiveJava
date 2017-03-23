/**
 * 
 */
package com.SE.SIPServer;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.sip.InvalidArgumentException;
import javax.sip.SipException;

/**
 * @author 03010213
 *
 */
public interface ISipCustomer {
	public List<String> clientRegister (String pswd) 
			throws ParseException, InvalidArgumentException, 
			SipException, SQLException, InterruptedException;
	public List<String> clientLogoff (String pswd) 
			throws ParseException, InvalidArgumentException, 
			SipException, InterruptedException;
	public List<String> changePassword (String oldpswd,String newpswd) 
			throws ParseException, InvalidArgumentException, SipException,
			InterruptedException;
	//��ȡ�豸�б����Ϊtrue��ȥ���������ݿ�ȡ��Ҫ�Ķ�����л�����豸��
	public boolean acquireList () 
			throws ParseException, InvalidArgumentException, 
			SipException, InterruptedException;
}
