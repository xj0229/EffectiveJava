package com.SE.Manscdp;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.SE.SIPServer.IMessageProcessor;

public class MessageProcessor implements IMessageProcessor{

	@Override
	public String changePassword(String nonce, String nonce1, String user) {
		// TODO Auto-generated method stub
		String req;
		req = "<?xml version=\"1.0\" ?>\n"
				+ "<Control>\n"
				+ "<CmdType>DeviceControl</CmdType>\n"
				+ "<SN>1</SN>\n"
				+ "<DeviceID>" + user + "</DeviceID>\n"
				+ "<Info>\n"
				+ "<ControlCmd>ModifyPassword</ControlCmd>\n"
				+ "<nonce>" + nonce + "</nonce>\n"
				+ "<nonce1>" + nonce1 + "</nonce1>\n"
				+ "<algorithm>MD5</algorithm>\n"
				+ "</Info>\n";
		req = req + "</Control>";
		return req;
	}

	@Override
	public String acquireList(String user) {
		// TODO Auto-generated method stub
		String req;
		req = "<?xml version=\"1.0\" ?>\n"
				+ "<Query>\n"
				+ "<CmdType>Catalog</CmdType>\n"
				+ "<SN>1</SN>\n"
				+ "<DeviceID>" + user + "</DeviceID>\n"
				+ "</Query>";
		return req;
	}

	@Override
	public String keepAlive(String user) {
		// TODO Auto-generated method stub
		String req;
		req = "<?xml version=\"1.0\" ?>\n"
				+ "<Notify>\n"
				+ "<CmdType>Keepalive</CmdType>\n"
				+ "<SN>1</SN>\n"
				+ "<DeviceID>" + user + "</DeviceID>\n"
				+ "<Status>OK</Status>\n"
				+ "</Notify>";
		return req;
	}

	@Override
	public int checkMsgReq(String content) {
		// TODO Auto-generated method stub
				byte[] transMessage = content.getBytes();
			   SAXReader reader = new SAXReader();
			   // 定义一个文档
			   Document document = null;
			   //将字符串转换为
			   try {
				document = reader.read(new ByteArrayInputStream(transMessage));
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   // 得到xml的根节点(message)
			   Element root = document.getRootElement();
			   String cmdtype = root.elementText("CmdType");
			   switch(cmdtype)
			   {
			   case "Catalog":
			   		{
			   			return 2;
			   		}
			   case "DeviceControl":
		   		{
		   			if(root.elementText("PTZcmd") != null)
		   				return 99;
		   			else
			   			return 1;
		   		}
			   case "RealPlayUrl":
		   		{
		   			return 3;
		   		}
			   case "DecoderDivision":
		   		{
		   			return 3;
		   		}
			   case "Alarm":
		   		{
		   			return 4;
		   		}
			   }
		// test1
		return 0;
	}

	@Override
	public boolean changePWResult(String content) {
		// TODO Auto-generated method stub
		byte[] transMessage = content.getBytes();
		 SAXReader reader = new SAXReader();
		   // 定义一个文档
		   Document document = null;
		   //将字符串转换为
		   try {
				document = reader.read(new ByteArrayInputStream(transMessage));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   // 得到xml的根节点(message)
		   Element root = document.getRootElement();
		   String result = root.elementText("Result");
		   if( result.equals("OK"))
			   return true;
		   else
			   return false;
	}

	@Override
	public String decodePlay(String deviceID, String channel) {
		// TODO Auto-generated method stub
		String req;
		req = "<?xml version=\"1.0\" ?>\n"
				+ "<Control>\n"
				+ "<CmdType>RealPlayUrl</CmdType>\n"
				+ "<SN>1</SN>\n"
				+ "<DeviceID>" + deviceID + "</DeviceID>\n"
				+ "<DecoderChannelID>" + channel + "</DecoderChannelID>\n"
				+ "</Control>";
		return req;
	}

	@Override
	public String setDecoder(String decoderID, String pannels) {
		String req;
		req = "<?xml version=\"1.0\" ?>\n"
				+ "<Control>\n"
				+ "<CmdType>DecoderDivision</CmdType>\n"
				+ "<SN>1</SN>\n"
				+ "<DeviceID>" + decoderID + "</DeviceID>\n"
				+ "<DecoderChannelID>" + pannels + "</DecoderChannelID>\n"
				+ "</Control>";
		return req;
	}

	@Override
	public List<String> alarmNotifyProcessor(String content) {
		// TODO Auto-generated method stub
		 SAXReader reader = new SAXReader();
		   // 定义一个文档
		   Document document = null;
		   //将字符串转换为
		   try {
			document = reader.read(content);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   // 得到xml的根节点(message)
		   Element root = document.getRootElement();
		   String DeviceID = root.elementText("DeviceID");
		   String AlarmPriority = root.elementText("AlarmPriority");
		   String AlarmTime = root.elementText("AlarmTime");
		   String AlarmMethod = root.elementText("AlarmMethod");
		List<String> list = new ArrayList();
		list.add(DeviceID);
		list.add(AlarmPriority);
		list.add(AlarmTime);
		list.add(AlarmMethod);
		return list;
	}

}
