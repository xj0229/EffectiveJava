package com.SE.STClient.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.SE.STClient.Common.AllPollingTeamInfo;
import com.SE.STClient.Common.PollingTeamInfo;
import com.SE.STClient.Common.PollingTeamInfoDAO;
import com.SE.STClient.Common.RunningPollingTeamInfo;

public class PollingTeamDataService {

	
	//Mysql数据库访问对象
	private PollingTeamInfoDAO dBDao;
	public PollingTeamInfo pollingTeamInfoEntity;
	
	
	//将数据库中的表映射到实体类中的属性
	public PollingTeamDataService(String transferPollingTeamName)
	{
		pollingTeamInfoEntity = PollingTeamInfo.getInstance();
		pollingTeamInfoEntity.DeleteAllData();
		// pollingTeamInfoEntity=new PollingTeamInfo();
		dBDao = PollingTeamInfoDAO.getInstance();
		if (transferPollingTeamName != "") {
		
			ResultSet pollingTeamRS = null;
			try {
				pollingTeamRS = dBDao.executeSelect("select * from pollingteaminfo where PollingTeamName like binary ?",
						transferPollingTeamName);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				while (pollingTeamRS.next()) {
					// pollingTeamInfoEntity.setID(pollingTeamRS.getInt("ID"));
					pollingTeamInfoEntity.setPollingTeamName(pollingTeamRS.getString("PollingTeamName"));
					pollingTeamInfoEntity.setCamera(pollingTeamRS.getString("Camera"));
					pollingTeamInfoEntity.setCameraSpecifyPosition(pollingTeamRS.getString("CameraSpecifyPosition"));
					pollingTeamInfoEntity.setGapTime(pollingTeamRS.getString("GapTime"));
					pollingTeamInfoEntity.setMonitor(pollingTeamRS.getString("Monitor"));
					pollingTeamInfoEntity.setMultiMonitor(pollingTeamRS.getString("MultiMonitor"));
					pollingTeamInfoEntity.setRunningState(pollingTeamRS.getString("RunningState"));
				}
				pollingTeamRS.close();
				// dBDao.closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}		
	
		
	
	public RunningPollingTeamInfo GetRunningPollingTeamName() throws SQLException
	{
		RunningPollingTeamInfo allRunningPollingTeamName=new RunningPollingTeamInfo();
		ResultSet RunningPollingTeamInfo=dBDao.executeSelect("select RunningPollingTeamName from runningpollingteam","");
		while (RunningPollingTeamInfo.next()) {
			
			allRunningPollingTeamName.setRunningPollingTeam(RunningPollingTeamInfo.getString("RunningPollingTeamName"));
		}
		RunningPollingTeamInfo.close();
		return allRunningPollingTeamName;
	}
		
	
	//修改轮巡组数据并保存到数据库
	public void AlterPollingTeamData(String transferPollingTeamName,JTable cameraTable,JTable monitorTable,JTextField gapTime,JTextField newName,JComboBox multiMonitor) throws SQLException
	{
		/*
		 * 
		 * 获取界面中的数据
		 * 
		 * */		
		// 1.将pollingTeamInfoEntity清空
		pollingTeamInfoEntity.DeleteAllData();
		// 2.获取修改后界面中的数据

		// 获取摄像机表中的数据
		for (int i = 0; i < cameraTable.getRowCount(); i++) {
			if (cameraTable.getModel().getValueAt(i, 0) != null) {
				pollingTeamInfoEntity.setCamera(cameraTable.getModel().getValueAt(i, 0).toString());
				pollingTeamInfoEntity.setCamera(":");
			}

			if (cameraTable.getModel().getValueAt(i, 1) != null) {
				pollingTeamInfoEntity.setCameraSpecifyPosition(cameraTable.getModel().getValueAt(i, 1).toString());
				pollingTeamInfoEntity.setCameraSpecifyPosition(":");
			}
		}

		// 获取界面的间隔时间
		if ((gapTime.getText() != null) & (gapTime.getText() != "")) {
			pollingTeamInfoEntity.setGapTime(gapTime.getText());
		}

		// 获取监视器表中的数据
		for (int i = 0; i < monitorTable.getRowCount(); i++) {
			if (monitorTable.getModel().getValueAt(i, 0) != null) {
				pollingTeamInfoEntity.setMonitor(monitorTable.getModel().getValueAt(i, 0).toString());
				pollingTeamInfoEntity.setMonitor(":");
			}
		}

		// 获取选择的大屏
		if (multiMonitor.getModel().getSelectedItem() != null) {
			pollingTeamInfoEntity.setMultiMonitor(multiMonitor.getModel().getSelectedItem().toString());
		}

		// 获取用户输入的新轮巡组名称
		if ((newName.getText() != null) & (newName.getText() != "")) {
			pollingTeamInfoEntity.setPollingTeamName(newName.getText());
		}

		// 每次新建，或者修改轮巡组，默认初始运行状态为false
		pollingTeamInfoEntity.setRunningState("false");

		
		
		
		//如果是修改当前存在的轮巡组
		if (transferPollingTeamName.equals(newName.getText())&(ValidateExist("pollingteaminfo","PollingTeamName",newName.getText())!=0)) 
		{
			System.out.println("修改轮巡组");
			//执行更新操作，更新该轮巡组数据库中的数据
			String sqlStr_insertToDatabase="update pollingteaminfo set pollingteaminfo.PollingTeamName = ?,Camera = ?,Monitor = ?,CameraSpecifyPosition = ?,GapTime = ?,MultiMonitor = ?,RunningState = ? where PollingTeamName = ?";
			ArrayList<String> sqlPara=new ArrayList<String>();
			sqlPara.add(pollingTeamInfoEntity.getPollingTeamName());
			sqlPara.add(pollingTeamInfoEntity.getCamera());
			sqlPara.add(pollingTeamInfoEntity.getMonitor());
			sqlPara.add(pollingTeamInfoEntity.getCameraSpecifyPosition());
			sqlPara.add(pollingTeamInfoEntity.getGapTime());
			sqlPara.add(pollingTeamInfoEntity.getMultiMonitor());
			sqlPara.add(pollingTeamInfoEntity.getRunningState());
			sqlPara.add(pollingTeamInfoEntity.getPollingTeamName());
			dBDao.executeUpdate(sqlStr_insertToDatabase,sqlPara);
			System.out.println("修改成功");

		}
		//如果是创建新的轮巡组
		else 
		{
			System.out.println("新建轮巡组");
			//执行插入操作，将新建的轮巡组数据插入数据库中
			String sqlStr_insertToDatabase="insert into pollingteaminfo(PollingTeamName,Camera,Monitor,CameraSpecifyPosition,GapTime,MultiMonitor,RunningState) values(?,?,?,?,?,?,?)";
			dBDao.insertAllDataToSql(sqlStr_insertToDatabase,pollingTeamInfoEntity);
		}	
			
	}	
	
	
	
	//查看轮巡组
	public void CheckPollingTeamData(String transferPollingTeamName)
	{
		
	}
	
	
	//新建轮巡组数据到数据库
	public void AddPollingTeamData(String transferPollingTeamName,JTextField newName)
	{
		
	}
	
	
	
	//删除对应名称的轮巡组
	public void DeletePollingTeamData(String transferPollingTeamName) throws SQLException
	{
		String sqlStr="delete from pollingteaminfo where PollingTeamName like ?";
		dBDao.deleteAllDataToSql(sqlStr,transferPollingTeamName);
		//dBDao.closeConnection();
	}
	
	
	
	//验证数据库是否存在该轮巡组
	public int ValidateExist(String whichDataBaseTable,String whichColumn,String transferPollingTeamName) throws SQLException
	{
		String sqlStr="select count(*) AS PollingTeamNameCount from "+ whichDataBaseTable+" where " + whichColumn + " like ?";
		int pollingTeamDataRowCount=0;
		pollingTeamDataRowCount=dBDao.calculateRowCount(sqlStr,transferPollingTeamName);	
		//dBDao.closeConnection();
		return pollingTeamDataRowCount;
	}
	
	
	
	
	public AllPollingTeamInfo GetAllPollingTeam() throws SQLException
	{
		AllPollingTeamInfo allpollingteamlist=new AllPollingTeamInfo();
		String sqlStr="select distinct PollingTeamName AS AllPollingTeam from pollingteaminfo ";
		ResultSet pollingNameRS=dBDao.executeSelect(sqlStr, "");
		while (pollingNameRS.next()) {
			allpollingteamlist.setPollingTeamName(pollingNameRS.getString("AllPollingTeam"));			
		}	
		pollingNameRS.close();
		return allpollingteamlist;
	}
	
	
	
	public void AlterPollingTeamRunningState(String pollingTeamName)
	{
		String sqlStr="insert into runningpollingteam (RunningPollingTeamName) values (?)";
		ArrayList<String> sqlPara=new ArrayList<String>();
		sqlPara.add(pollingTeamName);
		dBDao.executeUpdate(sqlStr,sqlPara);
	}
	
	
	public void DeleteRunningPollingTeam(String pollingTeamName)
	{
		String sqlStr="delete from runningpollingteam where RunningPollingTeamName like ?";
		ArrayList<String> sqlPara=new ArrayList<String>();
		sqlPara.add(pollingTeamName);
		dBDao.executeUpdate(sqlStr,sqlPara);
	}
	
	
}
