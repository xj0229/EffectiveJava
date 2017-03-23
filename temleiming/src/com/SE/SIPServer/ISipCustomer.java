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
	//获取设备列表，如果为true请去服务器数据库取你要的东西，谢君的设备表
	public boolean acquireList () 
			throws ParseException, InvalidArgumentException, 
			SipException, InterruptedException;
}
