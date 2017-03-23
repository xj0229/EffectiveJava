/**
 * 
 */
package com.SE.SIPServer;

import java.text.ParseException;

import javax.sip.InvalidArgumentException;
import javax.sip.SipException;
import javax.sip.message.Request;

/**
 * @author 03010213
 *
 */
public class KeepAlive implements Runnable{
	
	SipServer m_sipServer;
	
	IMessageProcessor m_messageProcessor;
	
	int m_period;
	
	public KeepAlive(int period){
		m_sipServer = SipServer.getSipInstance();
		m_period  = period;
	}
	
	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(m_period);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		// TODO Auto-generated method stub
			m_sipServer.keepAlive();
		}
	}
}
