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
public class CustomerManage implements ISipCustomer{
	SipServer m_sip = SipServer.getSipInstance();

	@Override
	public List<String> clientRegister(String pswd)
			throws ParseException, InvalidArgumentException, SipException, SQLException, InterruptedException {
		// TODO Auto-generated method stub
		List<String> result = m_sip.clientRegister(pswd);
		return result;
	}

	@Override
	public List<String> clientLogoff(String pswd)
			throws ParseException, InvalidArgumentException, SipException, InterruptedException {
		// TODO Auto-generated method stub
		List<String> result = m_sip.clientLogoff(pswd);
		return result;
	}

	@Override
	public List<String> changePassword(String oldpswd,String newpswd)
			throws ParseException, InvalidArgumentException, SipException, InterruptedException {
		// TODO Auto-generated method stub
		List<String> result = m_sip.changePassword(oldpswd,newpswd);
		return result;
	}

	@Override
	public boolean acquireList() throws ParseException, InvalidArgumentException, SipException, InterruptedException {
		// TODO Auto-generated method stub
		boolean result = m_sip.acquireList();
		return result;
	}
}
