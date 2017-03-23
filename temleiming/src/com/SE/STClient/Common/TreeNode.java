package com.SE.STClient.Common;

import java.awt.Component;
import java.awt.Font;
import java.security.interfaces.RSAKey;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import org.apache.log4j.rewrite.MapRewritePolicy;

import com.SE.STClient.Common.Tb_IpcInfo;
import com.SE.STClient.Service.EquipmentDataService;  

public class TreeNode extends DefaultMutableTreeNode {

	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public List<String> runningDev=new ArrayList<String>();
		
	public List<String> sipID=new ArrayList<String>();

	public List<String> devType=new ArrayList<String>();
	
	
	//public Dictionary<String , String> devNameAndSipID=new Dictionary<String, String>();
	
	public Map<String, String> Dev_NameMapSipID=new HashMap<String, String>();
	
	

	Tb_IpcInfo ipcInfoEntity;
	
	Tb_DecInfo tb_decInfoEntity;
	
	public void CreatTreeNode(DefaultMutableTreeNode parent) throws SQLException 
	{
		ipcInfoEntity=new Tb_IpcInfo();
		EquipmentDataService equipmentDataService=new EquipmentDataService();
		
		ipcInfoEntity=equipmentDataService.GetAllIPCAndDomeCameraInfo();
		tb_decInfoEntity=equipmentDataService.GetAllDecoderAndChannelInfo();

		GetRunningDevInfo();
		DefaultMutableTreeNode node_zhantai = new DefaultMutableTreeNode("վ̨");
		DefaultMutableTreeNode node_zhanting = new DefaultMutableTreeNode("վ��");
		DefaultMutableTreeNode node_dev = new DefaultMutableTreeNode("�豸");

		
		//��վ��Ŀ¼
		DefaultMutableTreeNode node_rootdomecamera = new DefaultMutableTreeNode("���");
		DefaultMutableTreeNode node_rootIPC = new DefaultMutableTreeNode("IPC");
		DefaultMutableTreeNode node_rootdecoder = new DefaultMutableTreeNode("������");
		DefaultMutableTreeNode node1_3_1 =new DefaultMutableTreeNode("ͨ��");
		
		//node_rootdecoder.add(node1_3_1);
		node_zhantai.add(node_rootdomecamera);
		node_zhantai.add(node_rootIPC);
		node_zhantai.add(node_rootdecoder);
		
		
		
		//վ����Ŀ¼,վ��û�н�����Ŀ¼
		DefaultMutableTreeNode node2_1 = new DefaultMutableTreeNode("���");
		DefaultMutableTreeNode node2_2 = new DefaultMutableTreeNode("IPC");
		//DefaultMutableTreeNode node2_3 = new DefaultMutableTreeNode("������");
		node_zhanting.add(node2_1);
		node_zhanting.add(node2_2);
		//node2.add(node2_3);
		

		parent.add(node_zhantai);
		parent.add(node_zhanting);
		parent.add(node_dev);
		

		//��ipc���е��豸�嵽�豸�б�ڵ���
		for (int i = 0; i <ipcInfoEntity.getDev_Name().size(); i++) {
			//1.�ж��ǳ�վ���豸
			if (ipcInfoEntity.getDev_Position().get(i).equals("վ̨")) {
				//1.1�ж��������IPC�����е���һ��
				switch (ipcInfoEntity.getDev_Type().get(i)) {
				case "domecamera":
					node_rootdomecamera.add(new DefaultMutableTreeNode(ipcInfoEntity.getDev_Name().get(i)));

					Dev_NameMapSipID.put(ipcInfoEntity.getDev_Name().get(i), ipcInfoEntity.getSipID().get(i));
				
					break;
				case "ipc":
					node_rootIPC.add(new DefaultMutableTreeNode(ipcInfoEntity.getDev_Name().get(i)));
					Dev_NameMapSipID.put(ipcInfoEntity.getDev_Name().get(i), ipcInfoEntity.getSipID().get(i));
					break;
				default:
					break;
				}
			}
			//2.�����վ���豸
			else if (ipcInfoEntity.getDev_Position().get(i).equals("վ��")) {
				//2.1�ж��������IPC�����е���һ��
				switch (ipcInfoEntity.getDev_Type().get(i)) {
				case "domecamera":
					node2_1.add(new DefaultMutableTreeNode(ipcInfoEntity.getDev_Name().get(i)));
					Dev_NameMapSipID.put(ipcInfoEntity.getDev_Name().get(i), ipcInfoEntity.getSipID().get(i));
					break;
				case "ipc":
					node2_2.add(new DefaultMutableTreeNode(ipcInfoEntity.getDev_Name().get(i)));
					Dev_NameMapSipID.put(ipcInfoEntity.getDev_Name().get(i), ipcInfoEntity.getSipID().get(i));
					break;
				default:
					break;
				}
			}
			//3.������豸��Ķ���
			else if (ipcInfoEntity.getDev_Position().get(i).equals("�豸")) {
			
				node_dev.add(new DefaultMutableTreeNode(ipcInfoEntity.getDev_Name().get(i)));
			}
			
		}
		
		
		
		
		
		
		
		//��tb_dec�еĽ������豸�ͽ�����ͨ���嵽�豸�б�ڵ���
		
		for (int i = 0; i < tb_decInfoEntity.getDev_ID().size(); i++) {
			//����ǽ�����������뵽������Ŀ¼��
			 if (tb_decInfoEntity.getDev_Type().get(i).equals("decoder")) {
				    
				    DefaultMutableTreeNode node_decoder=new DefaultMutableTreeNode(tb_decInfoEntity.getName().get(i));	
				    
				    DefaultMutableTreeNode channel1=new DefaultMutableTreeNode("ͨ��1");
					DefaultMutableTreeNode channel2=new DefaultMutableTreeNode("ͨ��2");
					DefaultMutableTreeNode channel3=new DefaultMutableTreeNode("ͨ��3");
					DefaultMutableTreeNode channel4=new DefaultMutableTreeNode("ͨ��4");
					DefaultMutableTreeNode channel5=new DefaultMutableTreeNode("ͨ��5");
					DefaultMutableTreeNode channel6=new DefaultMutableTreeNode("ͨ��6");
					DefaultMutableTreeNode channel7=new DefaultMutableTreeNode("ͨ��7");
					DefaultMutableTreeNode channel8=new DefaultMutableTreeNode("ͨ��8");
					DefaultMutableTreeNode channel9=new DefaultMutableTreeNode("ͨ��9");
					DefaultMutableTreeNode channel10=new DefaultMutableTreeNode("ͨ��10");
					DefaultMutableTreeNode channel11=new DefaultMutableTreeNode("ͨ��11");
					DefaultMutableTreeNode channel12=new DefaultMutableTreeNode("ͨ��12");
					DefaultMutableTreeNode channel13=new DefaultMutableTreeNode("ͨ��13");
					DefaultMutableTreeNode channel14=new DefaultMutableTreeNode("ͨ��14");
					DefaultMutableTreeNode channel15=new DefaultMutableTreeNode("ͨ��15");
					DefaultMutableTreeNode channel16=new DefaultMutableTreeNode("ͨ��16");
				    
				    node_decoder.add(channel1);
				    node_decoder.add(channel2);
				    node_decoder.add(channel3);
				    node_decoder.add(channel4);
				    node_decoder.add(channel5);
				    node_decoder.add(channel6);
				    node_decoder.add(channel7);
				    node_decoder.add(channel8);
				    node_decoder.add(channel9);
				    node_decoder.add(channel10);
				    node_decoder.add(channel11);
				    node_decoder.add(channel12);
				    node_decoder.add(channel13);
				    node_decoder.add(channel14);
				    node_decoder.add(channel15);
				    node_decoder.add(channel16);
					node_rootdecoder.add(node_decoder);
					Dev_NameMapSipID.put(tb_decInfoEntity.getName().get(i), tb_decInfoEntity.getSipID().get(i));
				}
				
		}
		
		
		//tb_alarm���е��豸����ʱ����
	
		
		
		
		
		if (node_zhantai.isLeaf()) {
			node_zhantai.add(new DefaultMutableTreeNode("���豸"));
		}
		
		if (node_zhanting.isLeaf()) {
			node_zhanting.add(new DefaultMutableTreeNode("���豸"));
		}
		
		if (node_dev.isLeaf()) {
			node_dev.add(new DefaultMutableTreeNode("���豸"));
		}

	}
	
	
	
	
	
	public void GetRunningDevInfo()
	{
		for (int i = 0; i < ipcInfoEntity.getSipID().size(); i++) {
			//��ȡIPC���е����е��豸
			if (ipcInfoEntity.getActive().get(i).equals("T")) {
				
				runningDev.add(ipcInfoEntity.getDev_Name().get(i));
				
				sipID.add(ipcInfoEntity.getSipID().get(i));
				
				devType.add(ipcInfoEntity.getDev_Type().get(i));
				
			}		
		}
		
		
		for (int i = 0; i < tb_decInfoEntity.getDev_ID().size(); i++) {
			
			//��ȡtb_dec�����е��豸
			if (tb_decInfoEntity.getActive().get(i).equals("T")) {
				if (tb_decInfoEntity.getDev_Type().get(i).equals("decoder")) {
					runningDev.add(tb_decInfoEntity.getName().get(i));
					sipID.add(tb_decInfoEntity.getSipID().get(i));
				}
				else if (tb_decInfoEntity.getDev_Type().get(i).equals("channel"))
				{
					runningDev.add("ͨ��"+(i+1));	
					sipID.add(tb_decInfoEntity.getSipID().get(i));
				}
				
				
			}
		}
		
	
	}

}





