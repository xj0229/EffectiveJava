package com.SE.STClient.Common;

import java.util.ArrayList;

public class Tb_DecInfo {
	
	private ArrayList<Integer> Dev_ID =new ArrayList<Integer>();
	
	private ArrayList<String> Active =new ArrayList<String>();

	private ArrayList<String> Name =new ArrayList<String>();
	
	private ArrayList<String> ReceiverToken =new ArrayList<String>();

	private ArrayList<String> SipID =new ArrayList<String>();
	
	private ArrayList<String> Dev_Type =new ArrayList<String>();
		
	private ArrayList<String> IPNoPort =new ArrayList<String>();
	
	private ArrayList<Integer> chanel =new ArrayList<Integer>();
	
	private ArrayList<String> ParentID =new ArrayList<String>();
	

	public ArrayList<Integer> getDev_ID() {
		return Dev_ID;
	}

	public void setDev_ID(Integer dev_ID) {
		this.Dev_ID.add(dev_ID);
	}

	public ArrayList<String> getActive() {
		return Active;
	}

	public void setActive(String active) {
		this.Active.add(active);
	}

	
	
	public ArrayList<String> getName() {
		return Name;
	}

	public void setName(String name) {
		this.Name.add(name);
	}
	
	
	public ArrayList<String> getReceiverToken() {
		return ReceiverToken;
	}

	public void setReceiverToken(String receiverToken) {
		this.ReceiverToken.add(receiverToken);
	}

	public ArrayList<String> getSipID() {
		return SipID;
	}

	public void setSipID(String sipID) {
		this.SipID.add(sipID);
	}

	public ArrayList<String> getDev_Type() {
		return Dev_Type;
	}

	public void setDev_Type(String dev_Type) {
		this.Dev_Type.add(dev_Type);
	}

	public ArrayList<String> getIPNoPort() {
		return IPNoPort;
	}

	public void setIPNoPort(String iPNoPort) {
		this.IPNoPort.add(iPNoPort);
	}
	
	
	public ArrayList<Integer> getChannel() {
		return chanel;
	}

	public void setChannel(Integer channel) {
		this.chanel.add(channel);
	}
	
	public ArrayList<String> getParentID() {
		return ParentID;
	}

	public void setParentID(String ParentID) {
		this.ParentID.add(ParentID);
	}
}
