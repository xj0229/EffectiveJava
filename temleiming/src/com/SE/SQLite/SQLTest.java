package com.SE.SQLite;

import java.sql.*;

public class SQLTest {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		String content="<?xmlversion=\"1.0\"?><Notify><CmdType>Alarm</CmdType><SN>1</SN><DeviceID>64010000001340000001</DeviceID><AlarmPriority>4</AlarmPriority><AlarmMethod>2</AlarmMethod><AlarmTime>2009-12-04 16:23:32</AlarmTime><AlarmDescription>æØ«È√Ë ˆ</AlarmDescription>";
		String m_SN= content.substring(content.indexOf("<SN>")+4,content.indexOf("</SN>"));
		String m_DeviceID = content.substring(content.indexOf("<DeviceID>")+10,content.indexOf("</DeviceID>"));
		String m_AlarmPriority= content.substring(content.indexOf("<AlarmPriority>")+15,content.indexOf("</AlarmPriority>"));
		String m_AlarmTime= content.substring(content.indexOf("<AlarmTime>")+11,content.indexOf("</AlarmTime>"));
		m_AlarmTime=m_AlarmTime.substring(0, 10)+m_AlarmTime.substring(11);
		m_AlarmTime=m_AlarmTime.replaceAll(":", "-");
		System.out.println();
		String m_AlarmMethod= content.substring(content.indexOf("<AlarmMethod>")+13,content.indexOf("</AlarmMethod>"));
	//	String m_section=content.substring(content.indexOf("<Section>")+9,content.indexOf("</Section>"));
	//	String m_alarmtype=content.substring(content.indexOf("<AlarmType>")+11,content.indexOf("</AlarmType>"));
		System.out.println(m_SN);
		System.out.println(m_DeviceID);
		System.out.println(m_AlarmPriority);
		System.out.println(m_AlarmMethod);
		System.out.println(m_AlarmTime);
	//	System.out.println(m_section);
	//	System.out.println(m_alarmtype);
		 SQLiteConnector m_con = new SQLiteConnector();
			
			String m_alarmtype="2";
			String m_section="2";
			ResultSet rs = m_con.executeInsert("delete from Alarm where DeviceID='64010000001340000001'");
			
	       }
	}


