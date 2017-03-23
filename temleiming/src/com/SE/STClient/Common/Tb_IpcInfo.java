package com.SE.STClient.Common;

import java.util.ArrayList;

public class Tb_IpcInfo {
	
	
	
	
	private ArrayList<String> SipID =new ArrayList<String>();
	
	private ArrayList<String> Dev_Name =new ArrayList<String>();
	
	private ArrayList<String> Dev_Position =new ArrayList<String>();
	
	private ArrayList<String> Active =new ArrayList<String>();

	private ArrayList<String> Dev_Type =new ArrayList<String>();
	
	private ArrayList<Integer> Dev_ID =new ArrayList<Integer>();
	
	
	
	
	
	
	
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
	
	public ArrayList<String> getDev_Name() {
		return Dev_Name;
	}

	public void setDev_Name(String dev_Name) {
		this.Dev_Name.add(dev_Name);
	}

	public ArrayList<String> getDev_Position() {
		return Dev_Position;
	}

	public void setDev_Position(String dev_Position) {
		this.Dev_Position.add(dev_Position);
	}

	public ArrayList<String> getActive() {
		return Active;
	}

	public void setActive(String active) {
		this.Active.add(active);
	}
	
	public ArrayList<Integer> getDev_ID() {
		return Dev_ID;
	}

	public void setDev_ID(Integer dev_ID) {
		this.Dev_ID.add(dev_ID);
	}

}
