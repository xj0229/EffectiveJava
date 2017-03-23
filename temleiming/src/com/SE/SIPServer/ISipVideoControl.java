package com.SE.SIPServer;

import java.text.ParseException;
import java.util.List;

import javax.sip.InvalidArgumentException;
import javax.sip.SipException;

public interface ISipVideoControl {
	public boolean playVideo (String deviceID,String uri) 
			throws ParseException, InvalidArgumentException, 
			SipException, InterruptedException;
	public void decoderPlay (String deviceID,String channel );
	public boolean configureDecoder (String deviceID, String pannels) 
			throws ParseException, InvalidArgumentException, 
			SipException, InterruptedException;
	public void PTZControl (String content);
}
