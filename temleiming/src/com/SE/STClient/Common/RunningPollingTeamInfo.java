package com.SE.STClient.Common;

import java.util.ArrayList;

public class RunningPollingTeamInfo {

	private ArrayList<String> RunningPollingTeam=new ArrayList<String>();

	public ArrayList<String> getRunningPollingTeam() {
		return RunningPollingTeam;
	}

	public void setRunningPollingTeam(String runningPollingTeam) {
		this.RunningPollingTeam.add(runningPollingTeam);
	}
}
