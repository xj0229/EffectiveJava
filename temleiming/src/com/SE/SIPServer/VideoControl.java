/**
 * 
 */
package com.SE.SIPServer;

import java.text.ParseException;
import java.util.List;

import javax.sip.InvalidArgumentException;
import javax.sip.SipException;

/**
 * @author 03010213
 *
 */
public class VideoControl implements ISipVideoControl{
	
	private static SipServer m_sip;
	
	public VideoControl(){
		m_sip = SipServer.getSipInstance();
	}
	@Override
	public boolean playVideo(String deviceID, String uri)
			throws ParseException, InvalidArgumentException, SipException, InterruptedException {
		// TODO Auto-generated method stub
		boolean result = m_sip.playVideo(deviceID, uri);
		return result;
	}
	@Override
	public void decoderPlay(String deviceID, String channel) {
		// TODO Auto-generated method stub
		m_sip.decoderPlay(deviceID, channel);
	}
	@Override
	public boolean configureDecoder(String deviceID, String pannels) 
			throws ParseException, InvalidArgumentException, SipException,
			InterruptedException {
		// TODO Auto-generated method stub
		boolean result = m_sip.setDecoder(deviceID, pannels);
		return result;
	}
	@Override
	public void PTZControl(String content) {
		// TODO Auto-generated method stub
		m_sip.PTZcontrol(content);
	}
	
}
