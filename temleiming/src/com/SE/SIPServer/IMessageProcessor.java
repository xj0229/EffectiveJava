/**
 * 
 */
package com.SE.SIPServer;

import java.util.List;

/**
 * @author Bryson Han
 *
 */
public interface IMessageProcessor {
	
	public String changePassword (String nonce,String nonce1,String user);
	public String acquireList (String user);
	public String keepAlive(String user);
	public int checkMsgReq (String content);
	public boolean changePWResult (String content);
	public String decodePlay (String deviceID, String channel);
	public String setDecoder (String decoderID, String pannels);
	public List<String> alarmNotifyProcessor (String content);
}
