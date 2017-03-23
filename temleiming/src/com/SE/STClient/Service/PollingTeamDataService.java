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

	
	//Mysql���ݿ���ʶ���
	private PollingTeamInfoDAO dBDao;
	public PollingTeamInfo pollingTeamInfoEntity;
	
	
	//�����ݿ��еı�ӳ�䵽ʵ�����е�����
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
		
	
	//�޸���Ѳ�����ݲ����浽���ݿ�
	public void AlterPollingTeamData(String transferPollingTeamName,JTable cameraTable,JTable monitorTable,JTextField gapTime,JTextField newName,JComboBox multiMonitor) throws SQLException
	{
		/*
		 * 
		 * ��ȡ�����е�����
		 * 
		 * */		
		// 1.��pollingTeamInfoEntity���
		pollingTeamInfoEntity.DeleteAllData();
		// 2.��ȡ�޸ĺ�����е�����

		// ��ȡ��������е�����
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

		// ��ȡ����ļ��ʱ��
		if ((gapTime.getText() != null) & (gapTime.getText() != "")) {
			pollingTeamInfoEntity.setGapTime(gapTime.getText());
		}

		// ��ȡ���������е�����
		for (int i = 0; i < monitorTable.getRowCount(); i++) {
			if (monitorTable.getModel().getValueAt(i, 0) != null) {
				pollingTeamInfoEntity.setMonitor(monitorTable.getModel().getValueAt(i, 0).toString());
				pollingTeamInfoEntity.setMonitor(":");
			}
		}

		// ��ȡѡ��Ĵ���
		if (multiMonitor.getModel().getSelectedItem() != null) {
			pollingTeamInfoEntity.setMultiMonitor(multiMonitor.getModel().getSelectedItem().toString());
		}

		// ��ȡ�û����������Ѳ������
		if ((newName.getText() != null) & (newName.getText() != "")) {
			pollingTeamInfoEntity.setPollingTeamName(newName.getText());
		}

		// ÿ���½��������޸���Ѳ�飬Ĭ�ϳ�ʼ����״̬Ϊfalse
		pollingTeamInfoEntity.setRunningState("false");

		
		
		
		//������޸ĵ�ǰ���ڵ���Ѳ��
		if (transferPollingTeamName.equals(newName.getText())&(ValidateExist("pollingteaminfo","PollingTeamName",newName.getText())!=0)) 
		{
			System.out.println("�޸���Ѳ��");
			//ִ�и��²��������¸���Ѳ�����ݿ��е�����
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
			System.out.println("�޸ĳɹ�");

		}
		//����Ǵ����µ���Ѳ��
		else 
		{
			System.out.println("�½���Ѳ��");
			//ִ�в�����������½�����Ѳ�����ݲ������ݿ���
			String sqlStr_insertToDatabase="insert into pollingteaminfo(PollingTeamName,Camera,Monitor,CameraSpecifyPosition,GapTime,MultiMonitor,RunningState) values(?,?,?,?,?,?,?)";
			dBDao.insertAllDataToSql(sqlStr_insertToDatabase,pollingTeamInfoEntity);
		}	
			
	}	
	
	
	
	//�鿴��Ѳ��
	public void CheckPollingTeamData(String transferPollingTeamName)
	{
		
	}
	
	
	//�½���Ѳ�����ݵ����ݿ�
	public void AddPollingTeamData(String transferPollingTeamName,JTextField newName)
	{
		
	}
	
	
	
	//ɾ����Ӧ���Ƶ���Ѳ��
	public void DeletePollingTeamData(String transferPollingTeamName) throws SQLException
	{
		String sqlStr="delete from pollingteaminfo where PollingTeamName like ?";
		dBDao.deleteAllDataToSql(sqlStr,transferPollingTeamName);
		//dBDao.closeConnection();
	}
	
	
	
	//��֤���ݿ��Ƿ���ڸ���Ѳ��
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
