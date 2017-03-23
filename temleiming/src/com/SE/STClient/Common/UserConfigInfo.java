package com.SE.STClient.Common;


import java.util.ArrayList;
import java.util.List;

public class UserConfigInfo {

	private List<String> userIP = new ArrayList<String>();

	private List<Integer> userPort = new ArrayList<Integer>();

	private List<String> serverIP = new ArrayList<String>();

	private List<Integer> serverPort = new ArrayList<Integer>();

	private List<Integer> serverID = new ArrayList<Integer>();

	public List<String> getUserIP() {
		return userIP;
	}

	public void setUserIP(String userIP) {
		this.userIP.add(userIP);
	}

	public List<Integer> getUserPort() {
		return userPort;
	}

	public void setUserPort(Integer userPort) {
		this.userPort.add(userPort);
	}

	public List<String> getServerIP() {
		return serverIP;
	}

	public void setServerIP(String serverIP) {
		this.serverIP.add(serverIP);
	}

	public List<Integer> getServerPort() {
		return serverPort;
	}

	public void setServerPort(Integer serverPort) {
		this.serverPort.add(serverPort);
	}

	public List<Integer> getServerID() {
		return serverID;
	}

	public void setServerID(Integer serverID) {
		this.serverID.add(serverID);
	}

}
