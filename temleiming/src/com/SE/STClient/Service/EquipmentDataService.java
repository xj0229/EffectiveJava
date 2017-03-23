package com.SE.STClient.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.SE.STClient.Common.Tb_IpcInfo;
import com.SE.STClient.Common.PollingTeamInfoDAO;
import com.SE.STClient.Common.Tb_DecInfo;

public class EquipmentDataService {

	
	
	
	private PollingTeamInfoDAO dBDao;
	public EquipmentDataService()
	{
		dBDao = PollingTeamInfoDAO.getInstance();		
	}
	
	
	public Tb_IpcInfo GetAllIPCAndDomeCameraInfo() throws SQLException
	{
		Tb_IpcInfo equipmentsInfoEntity=new Tb_IpcInfo();
		String sqlStr="select * from tb_ipc";
		ResultSet equipRS= dBDao.executeSelect(sqlStr, "");
		while (equipRS.next()) {
			equipmentsInfoEntity.setSipID((equipRS.getString("sipId")));
			equipmentsInfoEntity.setActive(equipRS.getString("active"));
			equipmentsInfoEntity.setDev_Name(equipRS.getString("name"));
			equipmentsInfoEntity.setDev_Position(equipRS.getString("position"));
			equipmentsInfoEntity.setDev_Type(equipRS.getString("deviceType"));
			equipmentsInfoEntity.setDev_ID(equipRS.getInt("dev_id"));
		}
		equipRS.close();
		return equipmentsInfoEntity;
	
	}
	
	
	
	public Tb_DecInfo GetAllDecoderAndChannelInfo() throws SQLException
	{
		Tb_DecInfo tb_decInfoEntity=new Tb_DecInfo();
		String sqlStr="select * from tb_dec";
		ResultSet equipRS= dBDao.executeSelect(sqlStr, "");
		while (equipRS.next()) {
			tb_decInfoEntity.setDev_ID((equipRS.getInt("dev_id")));
			tb_decInfoEntity.setActive(equipRS.getString("active"));
			tb_decInfoEntity.setName(equipRS.getString("name"));
			tb_decInfoEntity.setReceiverToken(equipRS.getString("ReceiverToken"));
			tb_decInfoEntity.setSipID(equipRS.getString("sipId"));
			tb_decInfoEntity.setDev_Type(equipRS.getString("deviceType"));
			tb_decInfoEntity.setIPNoPort(equipRS.getString("IPNoPort"));
			tb_decInfoEntity.setChannel(equipRS.getInt("channel"));
			tb_decInfoEntity.setParentID(equipRS.getString("ParentID"));
		}
		equipRS.close();

		return tb_decInfoEntity;
	}
	
}
