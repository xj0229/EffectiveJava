package com.SE.Manscdp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.SE.SQLite.SQLiteConnector;
import com.SE.STClient.Common.PollingTeamInfoDAO;
import com.SE.STClient.Common.Tb_DecInfo;
import com.SE.STClient.Common.Tb_IpcInfo;
import com.SE.STClient.Service.EquipmentDataService;

public class Message implements IMessage{
	Tb_IpcInfo equipmentsInfoEntity;
	Tb_DecInfo equipmentsInfoEntity2;
	private List<String>m_List=new ArrayList<String>();
	
	public String parser(String msg,String devid){
		String req;
		req = "<?xml version=\"1.0\" ?>\n"
				+ "<Control>\n"
				+ "<CmdType>DeviceControl</CmdType>\n"
				+ "<SN>1</SN>\n"
				+ "<DeviceID>" + devid + "</DeviceID>\n"
				+ "<PTZCmd>" + msg + "</PTZCmd>\n"
				+ "</Control>";
		return req;
	}	
	public String keyboardinfo(String mon,String cam){
		String camum = null;
	//	String monitor = null;
		equipmentsInfoEntity=new Tb_IpcInfo();
		equipmentsInfoEntity2=new Tb_DecInfo();
	EquipmentDataService equipmentDataService=new EquipmentDataService();
		
	try {
		equipmentsInfoEntity=equipmentDataService.GetAllIPCAndDomeCameraInfo();
		equipmentsInfoEntity2=equipmentDataService.GetAllDecoderAndChannelInfo();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
		
		for (int i = 0; i < equipmentsInfoEntity.getDev_ID().size(); i++) {
			
			if (equipmentsInfoEntity.getDev_ID().get(i)==Integer.parseInt(cam)) {
					camum=equipmentsInfoEntity.getSipID().get(i).toString();
					break;
			}
	}
		
/*		for (int i = 0; i < equipmentsInfoEntity2.getDev_ID().size(); i++) {
			
			if (equipmentsInfoEntity2.getChannel().get(i)==Integer.parseInt(mon)) {
					monitor=equipmentsInfoEntity2.getSipID().get(i).toString();
					break;
			}
	}*/
		String req;
		req = "<?xml version=\"1.0\" ?>\n"
				+ "<Control>\n"
				+ "<CmdType>RealPlayUrl</CmdType>\n"
				+ "<SN>1</SN>\n"
				+ "<DeviceID>" + camum + "</DeviceID>\n"
				+ "<DecoderChannelID>" + mon + "</DecoderChannelID>\n"
				+ "</Control>";
		return req;

}
	
	
	
	@Override
	public String splitescrren(String mon, String SpliteScreen) {
		// TODO Auto-generated method stub
		equipmentsInfoEntity2=new Tb_DecInfo();
		EquipmentDataService equipmentDataService=new EquipmentDataService();
		try {
			equipmentsInfoEntity2=equipmentDataService.GetAllDecoderAndChannelInfo();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String monitor = null;
		for (int i = 0; i < equipmentsInfoEntity2.getDev_ID().size(); i++) {	
			if (equipmentsInfoEntity2.getChannel().get(i)==Integer.parseInt(mon)) {
					monitor=equipmentsInfoEntity2.getParentID().get(i).toString();
					break;
			}	
	}
		for (int i1 = 0; i1 < equipmentsInfoEntity2.getDev_ID().size(); i1++) {
			if(equipmentsInfoEntity2.getSipID().get(i1).equals(monitor)){
				monitor=equipmentsInfoEntity2.getParentID().get(i1);
				break;
			}
		}
		String req;
		req = "<?xml version=\"1.0\" ?>\n"
				+ "<Control>\n"
				+ "<CmdType>DecoderDivision</CmdType>\n"
				+ "<SN>1</SN>\n"
				+ "<DeviceID>" + monitor + "</DeviceID>\n"
				+ "<DecoderChannelID>" + SpliteScreen + "</DecoderChannelID>\n"
				+ "</Control>";
		return req;
	}
	public String cancelalarm() {

		String req;
		req = "<?xml version=\"1.0\" ?>\n"
				+ "<Control>\n"
				+ "<CmdType>DeviceControl</CmdType>\n"
				+ "<SN>1</SN>\n"
				+ "<DeviceID>Alldevice</DeviceID>\n"
				+ "<AlarmCmd>ResetAlarm</AlarmCmd>\n"
				+ "</Control>";
		return req;
	}

}
