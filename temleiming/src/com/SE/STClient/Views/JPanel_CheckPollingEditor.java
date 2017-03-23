package com.SE.STClient.Views;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Event;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JList;
import javax.sound.sampled.AudioFormat.Encoding;
import javax.swing.AbstractListModel;
import javax.swing.Box;
import javax.swing.ListSelectionModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.JTable;
import java.awt.ScrollPane;
import javax.swing.table.DefaultTableModel;

import com.SE.STClient.Common.PollingTeamInfoDAO;
import com.SE.STClient.Main.STClientMain;
import com.SE.STClient.Service.PollingTeamDataService;
import com.mysql.jdbc.MysqlIO;
import com.mysql.jdbc.MysqlParameterMetadata;

import gov.nist.javax.sip.header.WWWAuthenticate;

import java.awt.Choice;
import java.awt.Color;

import javax.swing.ScrollPaneConstants;
import javax.swing.JSplitPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;

public class JPanel_CheckPollingEditor extends JPanel {
	public JPanel titlePanel;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JPanel panel_1;
	private JTable table_camera;
	private JTable table_monitor;
	private Component verticalStrut_8;
	private JPanel panel_6;
	private JTextField textField_NewPollingName;
	private JLabel label;
	private JTextField textField_GapTime;
	private JLabel label_1;
	private JPanel panel_3;
	private JPanel panel_9;
	private JLabel lblNewLabel;
	private JComboBox comboBox_MultiMonitorChoice;
	private PollingTeamDataService pollingTeamDataService;
	private Component verticalStrut;
	private Component horizontalStrut;
	private Component horizontalStrut_1;
	private JPanel panel_4;
	private Component horizontalStrut_2;
	private Component horizontalStrut_3;
	private Component horizontalStrut_4;

	// Create the

	//JPanel_PollingEditor固定组件的初始化函数
	public void CommonInitJPanel_PollingEditor(String pollingTeamNamePara,JPanel current)
	{
		table_monitor = new JTable();
		table_monitor.setCellSelectionEnabled(true);
		table_monitor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_camera = new JTable();
		table_camera.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		Btn_CloseButton closeButton_screenPanel = new Btn_CloseButton("/com/SE/res/close_24.png",
				"/com/SE/res/close_red_24.png", STClientMain.MainWindowframe.tabbedPane_bottom, this);
		titlePanel = new JPanel();
		JLabel screenLabel = new JLabel("轮巡查看");
		screenLabel.setIcon(new ImageIcon(JFrame_MainWindow.class.getResource("/com/SE/res/polling24.png")));
		titlePanel.setBorder(null);
		titlePanel.setOpaque(false);
		titlePanel.add(screenLabel);
		titlePanel.add(closeButton_screenPanel);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		panel = new JPanel();
		add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(160, 220));
		panel.add(scrollPane);
	
		scrollPane.setViewportView(table_camera);
		
		horizontalStrut_3 = Box.createHorizontalStrut(20);
		add(horizontalStrut_3);

		JPanel panel_2 = new JPanel();
		add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setPreferredSize(new Dimension(160, 220));
		panel_2.add(scrollPane_1);

		scrollPane_1.setViewportView(table_monitor);
		
		horizontalStrut_4 = Box.createHorizontalStrut(20);
		panel_2.add(horizontalStrut_4);

		panel_9 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_9.getLayout();
		flowLayout.setAlignment(FlowLayout.LEADING);
		panel_2.add(panel_9);

		panel_6 = new JPanel();
		panel_9.add(panel_6);
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.Y_AXIS));
				
						panel_1 = new JPanel();
						panel_6.add(panel_1);
								panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
						
								lblNewLabel = new JLabel("\u8F6E\u5DE1\u7EC4\u540D\u79F0");
								panel_1.add(lblNewLabel);
								
										textField_NewPollingName = new JTextField(pollingTeamNamePara);
										panel_1.add(textField_NewPollingName);
										textField_NewPollingName.setColumns(15);
				
				horizontalStrut_1 = Box.createHorizontalStrut(12);
				panel_1.add(horizontalStrut_1);
				
				verticalStrut = Box.createVerticalStrut(10);
				panel_6.add(verticalStrut);
		
				panel_3 = new JPanel();
				panel_6.add(panel_3);
						panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));
				
						label = new JLabel("\u95F4\u9694\u65F6\u95F4");
						panel_3.add(label);
								
								horizontalStrut = Box.createHorizontalStrut(12);
								panel_3.add(horizontalStrut);
						
								textField_GapTime = new JTextField();
								panel_3.add(textField_GapTime);
								textField_GapTime.setColumns(15);
								
										label_1 = new JLabel("\u79D2");
										panel_3.add(label_1);

		verticalStrut_8 = Box.createVerticalStrut(10);
		panel_6.add(verticalStrut_8);
		
		panel_4 = new JPanel();
		panel_6.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));
///////		
		comboBox_MultiMonitorChoice = new JComboBox();
		panel_4.add(comboBox_MultiMonitorChoice);
		comboBox_MultiMonitorChoice.setEnabled(false);
		comboBox_MultiMonitorChoice.setModel(new DefaultComboBoxModel(new String[] {"\u9009\u62E9\u5927\u5C4F\u5E55", "\u5927\u5C4F\u5E551", "\u5927\u5C4F\u5E552", "\u5927\u5C4F\u5E553", "\u5927\u5C4F\u5E554", "\u5927\u5C4F\u5E555"}));
		comboBox_MultiMonitorChoice.setAutoscrolls(true);
		
		horizontalStrut_2 = Box.createHorizontalStrut(12);
		panel_4.add(horizontalStrut_2);
		
	}
	
	
	//#############################################################################################################################################此处要根据数据库进行修改##############################################
	//JPanel_PollingEditor可变组件的初始化函数
	public void InitJPanel_PollingEditor(int PollingTeamCountPara,String pollingTeamNamePara,int pollingTeamNameItemCount,JPanel current) throws SQLException
	{

		CommonInitJPanel_PollingEditor(pollingTeamNamePara,current);

		Object[][] cameraTable;
		Object[][] monitorTable;
		// 主要是根据是否已经存在名称为pollingTeamName的轮巡组对其中的表进行初始化
		if (PollingTeamCountPara!=0)// 存在，则根据数据库中的内容进行初始化
		{
			
			
			int cameraIndex = 0;
			int monitorIndex = 0;

			String pollingValue=pollingTeamDataService.pollingTeamInfoEntity.getPollingTeamName();
			String cameraValue[]=pollingTeamDataService.pollingTeamInfoEntity.getCamera().split(":");
			String cameraPositionValue[]=pollingTeamDataService.pollingTeamInfoEntity.getCameraSpecifyPosition().split(":");
			String monitorValue[]=pollingTeamDataService.pollingTeamInfoEntity.getMonitor().split(":");
			String multiMonitorValue=pollingTeamDataService.pollingTeamInfoEntity.getMultiMonitor();
			String gapTimeValue=pollingTeamDataService.pollingTeamInfoEntity.getGapTime();
			String runningStateValue=pollingTeamDataService.pollingTeamInfoEntity.getRunningState();

			cameraTable = new Object[cameraValue.length][2];
			monitorTable = new Object[monitorValue.length][1];

			for (int i = 0; i < cameraValue.length; i++) {
				cameraTable[i][0]=cameraValue[i];
				
				cameraTable[i][1]=cameraPositionValue[i];
			}
			for (int i = 0; i < monitorValue.length; i++) {
				monitorTable[i][0]=monitorValue[i];
			}

			
			comboBox_MultiMonitorChoice.getModel().setSelectedItem(multiMonitorValue);
			textField_GapTime.setText(gapTimeValue);			
			
			
			
			
			/*
			cameraTable = new Object[pollingTeamNameItemCount][2];
			monitorTable = new Object[pollingTeamNameItemCount][1];

			int cameraIndex = 0;
			int monitorIndex = 0;

			String sqlStr = "select * from pollingteaminfo where PollingTeamName like binary ?";

			ResultSet pollingTeamNameResult = PollingTeamInfoDAO.getInstance().executeSelect(sqlStr,
					pollingTeamNamePara);
			while (pollingTeamNameResult.next()) {
				if (pollingTeamNameResult.getString("Camera") != null) {
					cameraTable[cameraIndex][0] = pollingTeamNameResult.getString("Camera");

				}
				if (pollingTeamNameResult.getString("CameraSpecifyPosition") != null) {
					cameraTable[cameraIndex][1] = pollingTeamNameResult.getString("CameraSpecifyPosition");
				}

				if (pollingTeamNameResult.getString("Monitor") != null) {
					monitorTable[monitorIndex][0] = pollingTeamNameResult.getString("Monitor");

				}
				
				if (pollingTeamNameResult.getString("MultiMonitor") != null) {
					comboBox_MultiMonitorChoice.getModel().setSelectedItem(pollingTeamNameResult.getString("MultiMonitor"));
				}
				
				if (pollingTeamNameResult.getString("GapTime") != null) {
					textField_GapTime.setText(pollingTeamNameResult.getString("GapTime"));			
				}
				
				cameraIndex++;
				monitorIndex++;

			}
			pollingTeamNameResult.close();
		*/} 
		else 
		{
			cameraTable = new Object[40][2];
			monitorTable = new Object[40][1];
		}

		table_camera.setModel(new DefaultTableModel(cameraTable, new String[] { "\u6444\u50CF\u673A", "\u9884\u7F6E\u4F4D" }) {
			boolean[] columnEditables = new boolean[] { false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		table_monitor.setModel(new DefaultTableModel(monitorTable, new String[] { "\u663E\u793A\u5668" }) {
			boolean[] columnEditables = new boolean[] { false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

	} 

		
	
	public JPanel_CheckPollingEditor(String pollingTeamName) throws SQLException {
		/*
		 * 1.判断名称为pollingTeamName的轮巡组在数据库中是否存在 1.存在
		 * 对数据库中该轮巡组的信息进行提取，并初始化JPanel_PollingEditor中对应的表
		 * 
		 * 2.不存在 直接初始化空白的表 2.判断用户有没有输入新轮巡组的名称 1.输入了
		 * 并点击确定按钮后，将JPanel_PollingEditor中对应的表信息插入数据库中。并且名称一列填用户输入的名称
		 * 
		 * 2.没输入 对数据库中pollingTeamName名称为此的表进行更新，更新内容为JPanel_PollingEditor中对应的表
		 */

		pollingTeamDataService=new PollingTeamDataService(pollingTeamName);
		int pollingTeamNameItemCount=0;
		pollingTeamNameItemCount=pollingTeamDataService.ValidateExist("pollingteaminfo","PollingTeamName",pollingTeamName);
		InitJPanel_PollingEditor(pollingTeamNameItemCount,pollingTeamName,pollingTeamNameItemCount,this);

	}
}
