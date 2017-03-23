package com.SE.STClient.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTextField;

import com.SE.SQLite.SQLiteConnector;
import com.SE.STClient.Common.UserConfigInfo;

public class UserConfigDataService {

	SQLiteConnector sqLiteConnector;

	public UserConfigDataService() {
		sqLiteConnector = new SQLiteConnector();
	}

	
	
	public void updateAllUserConfigDataToSql(String textField_userIP, String textField_userPort,
			String textField_serverID, String textField_serverIP, String textField_serverPort)
			throws SQLException {
		String sqlStr = "update ClientInfo set UserIP = ?,UserPort =?,ServerID =?,ServerIP =?,ServerPort =? where rowid = 1";
		ArrayList<String> sqlPara = new ArrayList<String>();

		sqlPara.add(textField_userIP);
		sqlPara.add(textField_userPort);
		sqlPara.add(textField_serverID);
		sqlPara.add(textField_serverIP);
		sqlPara.add(textField_serverPort);

		sqLiteConnector.executeUpdate(sqlStr, sqlPara);
		sqLiteConnector.disconnect();
	}

	
	
	public UserConfigInfo GetAllUserConfigData() throws SQLException
	{
		System.out.println("开始查询用户配置表");
		UserConfigInfo userConfigInfo=new UserConfigInfo();
		String sqlStr="select * from ClientInfo where rowid = 1";
		ResultSet userConfigRS=sqLiteConnector.executeSelect(sqlStr);
		while (userConfigRS.next()) {
			System.out.println(userConfigRS.getString("UserIP"));
			userConfigInfo.setUserIP(userConfigRS.getString("UserIP"));
			userConfigInfo.setUserPort(userConfigRS.getInt("UserPort"));
			userConfigInfo.setServerID(userConfigRS.getInt("ServerID"));
			userConfigInfo.setServerIP(userConfigRS.getString("ServerIP"));
			userConfigInfo.setServerPort(userConfigRS.getInt("ServerPort"));
		}
		userConfigRS.close();
		sqLiteConnector.disconnect();
		return userConfigInfo;
	}
	
}
