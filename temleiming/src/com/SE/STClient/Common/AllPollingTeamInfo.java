package com.SE.STClient.Common;

import java.awt.List;
import java.util.ArrayList;

public class AllPollingTeamInfo {
	
	private ArrayList<String> pollingTeamName=new ArrayList<String>();

	public ArrayList<String> getPollingTeamName() {
		return pollingTeamName;
	}

	public void setPollingTeamName(String pollingTeamName) {
		this.pollingTeamName.add(pollingTeamName);
	}

}
