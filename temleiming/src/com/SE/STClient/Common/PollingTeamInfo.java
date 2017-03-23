package com.SE.STClient.Common;

import java.awt.List;
import java.util.ArrayList;

public class PollingTeamInfo {/*
	
	
	
	private ArrayList<String> PollingTeamName;
	private ArrayList<String> Camera;
	private ArrayList<String> Monitor;
	private ArrayList<String> CameraSpecifyPosition;
	private ArrayList<String> GapTime;
	private ArrayList<String> MultiMonitor;
	private ArrayList<String> RunningState;



	//单例模式
	private static volatile PollingTeamInfo instance;
	

	
	private PollingTeamInfo()
	{
		 PollingTeamName=new ArrayList<String>();
		 Camera=new ArrayList<String>();
		 Monitor=new ArrayList<String>();
		CameraSpecifyPosition=new ArrayList<String>();
		GapTime=new ArrayList<String>();
		 MultiMonitor=new ArrayList<String>();
		 RunningState=new ArrayList<String>();
	}		
	
	public static PollingTeamInfo getInstance()
	{
		if (instance==null) {
			synchronized (PollingTeamInfo.class) {
				if (instance==null) {
					instance=new PollingTeamInfo();
				}
				
			}
		}
		return instance;
	}
	
	public void DeleteAllData()
	{
		//this.ID.clear();
		this.PollingTeamName.clear();
		this.Camera.clear();
		this.Monitor.clear();
		this.CameraSpecifyPosition.clear();
		this.GapTime.clear();
		this.MultiMonitor.clear();	
		this.RunningState.clear();	
	}
	
	
	
	
	
	
	//Get And Set
	public ArrayList<String> getRunningState() {
		return RunningState;
	}

	public void setRunningState(String runningState) {
		RunningState.add(runningState);
	}
	
	
	public ArrayList<String> getPollingTeamName() {
		return PollingTeamName;
	}

	public void setPollingTeamName(String pollingTeamName) {
		PollingTeamName.add(pollingTeamName);
	}

	public ArrayList<String> getCamera() {
		return Camera;
	}

	public void setCamera(String camera) {
		Camera.add(camera);
	}

	public ArrayList<String> getMonitor() {
		return Monitor;
	}

	public void setMonitor(String monitor) {
		Monitor.add(monitor);
	}

	public ArrayList<String> getCameraSpecifyPosition() {
		return CameraSpecifyPosition;
	}

	public void setCameraSpecifyPosition(String cameraSpecifyPosition) {
		CameraSpecifyPosition.add(cameraSpecifyPosition);
	}

	public ArrayList<String> getGapTime() {
		return GapTime;
	}

	public void setGapTime(String gapTime) {
		GapTime.add(gapTime);
	}

	public ArrayList<String> getMultiMonitor() {
		return MultiMonitor;
	}

	public void setMultiMonitor(String multiMonitor) {
		MultiMonitor.add(multiMonitor);
	}

*/
	



	
	
	
	private String PollingTeamName;
	private String Camera;
	private String Monitor;
	private String CameraSpecifyPosition;
	private String GapTime;
	private String MultiMonitor;
	private String RunningState;



	//单例模式
	private static volatile PollingTeamInfo instance;
	

	
	private PollingTeamInfo()
	{
		 PollingTeamName="";
		 Camera="";
		 Monitor="";
		CameraSpecifyPosition="";
		GapTime="";
		 MultiMonitor="";
		 RunningState="";
	}		
	
	public static PollingTeamInfo getInstance()
	{
		if (instance==null) {
			synchronized (PollingTeamInfo.class) {
				if (instance==null) {
					instance=new PollingTeamInfo();
				}
				
			}
		}
		return instance;
	}
	
	public void DeleteAllData()
	{
		//this.ID.clear();
		this.PollingTeamName="";
		this.Camera="";
		this.Monitor="";
		this.CameraSpecifyPosition="";
		this.GapTime="";
		this.MultiMonitor="";	
		this.RunningState="";	
	}
	
	
	
	
	
	
	//Get And Set
	public String getRunningState() {
		return RunningState;
	}

	public void setRunningState(String runningState) {
		RunningState+=runningState;
	}
	
	
	public String getPollingTeamName() {
		return PollingTeamName;
	}

	public void setPollingTeamName(String pollingTeamName) {
		PollingTeamName+=pollingTeamName;
	}

	public String getCamera() {
		return Camera;
	}

	public void setCamera(String camera) {
		Camera+=camera;
	}

	public String getMonitor() {
		return Monitor;
	}

	public void setMonitor(String monitor) {
		Monitor+=monitor;
	}

	public String getCameraSpecifyPosition() {
		return CameraSpecifyPosition;
	}

	public void setCameraSpecifyPosition(String cameraSpecifyPosition) {
		CameraSpecifyPosition+=cameraSpecifyPosition;
	}

	public String getGapTime() {
		return GapTime;
	}

	public void setGapTime(String gapTime) {
		GapTime+=gapTime;
	}

	public String getMultiMonitor() {
		return MultiMonitor;
	}

	public void setMultiMonitor(String multiMonitor) {
		MultiMonitor+=multiMonitor;
	}
	
	
	
	
	
	
	






















}
