/**
 * 
 */
package com.SE.SIPServer;

import java.util.List;

import javax.sip.header.AuthorizationHeader;

/**
 * @author Bryson Han
 *
 */
public interface ICustomerManagement {
	public String getResponse (String user,String password,String ip, int port,String nonce);
	public List<String> getNonce (String user, String oldpswd,String newpswd,String ip, int port);
}
