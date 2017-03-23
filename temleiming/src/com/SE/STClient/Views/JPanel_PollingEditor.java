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
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.ListSelectionModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.JTable;
import java.awt.ScrollPane;
import javax.swing.table.DefaultTableModel;

import com.SE.STClient.Common.PollingTeamInfo;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JPanel_PollingEditor extends JPanel {
	public JPanel titlePanel;
	private JPanel panel;
	public JScrollPane scrollPane_camera;
	private JScrollPane scrollPane_1;
	private JPanel panel_1;
	public JTable table_camera;
	public JTable table_monitor;
	private JButton btn_camera_delete;
	private JButton btn_camera_select;
	private JButton btn_camera_up;
	private JButton btn_camera_down;
	private JComboBox comboBox_specifyCameraPosition;
	private JPanel panel_5;
	private JButton btn_cameraSpecifyPosition_add;
	private JButton btn_monitor_select;
	private Component verticalStrut_5;
	private JButton btn_monitor_up;
	private Component verticalStrut_6;
	private JButton btn_monitor_delete;
	private Component verticalStrut_7;
	private JButton btn_monitor_down;
	private Component verticalStrut_8;
	private JPanel panel_6;
	private JTextField textField_NewPollingName;
	private JButton btn_AddNewName;
	private JLabel label;
	private JTextField textField_GapTime;
	private JLabel label_1;
	private JButton btn_AlterGapTime;
	private JButton btn_pollingEditor_confirm;
	private JButton btn_pollingEditorCancel;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_8;
	private JPanel panel_9;
	private JPanel panel_10;
	private JPanel panel_11;
	private JLabel lblNewLabel;
	private JComboBox comboBox_MultiMonitorChoice;
	private PollingTeamDataService pollingTeamDataService;
	public Btn_CloseButton closeButton_screenPanel;

	// Create the

	//JPanel_PollingEditor固定组件的初始化函数
	public void CommonInitJPanel_PollingEditor(String pollingTeamNamePara,JPanel current)
	{
		table_monitor = new JTable();
		table_monitor.setCellSelectionEnabled(true);
		table_monitor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_camera = new JTable();
		STClientMain.getInstance().DragAndDropMethod(table_camera);
		table_camera.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		closeButton_screenPanel= new Btn_CloseButton("/com/SE/res/close_24.png",
				"/com/SE/res/close_red_24.png", STClientMain.MainWindowframe.tabbedPane_bottom, this);
		titlePanel = new JPanel();
		JLabel screenLabel = new JLabel("轮巡编辑");
		screenLabel.setIcon(new ImageIcon(JFrame_MainWindow.class.getResource("/com/SE/res/polling24.png")));
		titlePanel.setBorder(null);
		titlePanel.setOpaque(false);
		titlePanel.add(screenLabel);
		titlePanel.add(closeButton_screenPanel);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		scrollPane_camera = new JScrollPane();
		STClientMain.getInstance().DragAndDropMethod(scrollPane_camera);
		scrollPane_camera.setPreferredSize(new Dimension(160, 220));
		panel.add(scrollPane_camera);
	
		scrollPane_camera.setViewportView(table_camera);

		panel_5 = new JPanel();
		panel.add(panel_5);
		FlowLayout fl_panel_5 = new FlowLayout(FlowLayout.LEADING, 5, 5);
		panel_5.setLayout(fl_panel_5);

		panel_8 = new JPanel();
		panel_5.add(panel_8);
		panel_8.setLayout(new BoxLayout(panel_8, BoxLayout.Y_AXIS));

		panel_11 = new JPanel();
		panel_11.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel_8.add(panel_11);
		panel_11.setLayout(new BoxLayout(panel_11, BoxLayout.Y_AXIS));

		btn_camera_select = new JButton("\u9009\u62E9");
		btn_camera_select.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
			}
		});
		panel_11.add(btn_camera_select);
		btn_camera_select.setHorizontalAlignment(SwingConstants.LEFT);
		Component verticalStrut = Box.createVerticalStrut(10);
		panel_11.add(verticalStrut);

		btn_camera_up = new JButton("\u4E0A\u79FB");
		btn_camera_up.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
			}
		});
		panel_11.add(btn_camera_up);
		btn_camera_up.setHorizontalAlignment(SwingConstants.LEFT);
		Component verticalStrut_1 = Box.createVerticalStrut(10);
		panel_11.add(verticalStrut_1);

		btn_camera_delete = new JButton("\u5220\u9664");
		btn_camera_delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
			}
		});
		panel_11.add(btn_camera_delete);
		btn_camera_delete.setHorizontalAlignment(SwingConstants.LEFT);
		Component verticalStrut_2 = Box.createVerticalStrut(10);
		panel_11.add(verticalStrut_2);

		btn_camera_down = new JButton("\u4E0B\u79FB");
		btn_camera_down.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
			}
		});
		panel_11.add(btn_camera_down);
		btn_camera_down.setHorizontalAlignment(SwingConstants.LEFT);
		Component verticalStrut_3 = Box.createVerticalStrut(10);
		panel_8.add(verticalStrut_3);

		panel_10 = new JPanel();
		panel_8.add(panel_10);
		panel_10.setLayout(new BoxLayout(panel_10, BoxLayout.X_AXIS));

		comboBox_specifyCameraPosition = new JComboBox();
		comboBox_specifyCameraPosition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		comboBox_specifyCameraPosition.setEnabled(false);
		panel_10.add(comboBox_specifyCameraPosition);
		panel_10.add(Box.createHorizontalStrut(10));
		comboBox_specifyCameraPosition.setModel(new DefaultComboBoxModel(new String[] { "\u9884\u7F6E\u4F4D        " }));
		// panel_5.add(Box.createHorizontalStrut(10));
		comboBox_specifyCameraPosition.setAutoscrolls(true);

		btn_cameraSpecifyPosition_add = new JButton("\u6DFB\u52A0");
		btn_cameraSpecifyPosition_add.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
			}
		});
		panel_10.add(btn_cameraSpecifyPosition_add);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setPreferredSize(new Dimension(160, 220));
		panel_2.add(scrollPane_1);

		scrollPane_1.setViewportView(table_monitor);

		panel_9 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_9.getLayout();
		flowLayout.setAlignment(FlowLayout.LEADING);
		panel_2.add(panel_9);

		panel_6 = new JPanel();
		panel_9.add(panel_6);
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.Y_AXIS));

		btn_monitor_select = new JButton("\u9009\u62E9");
		btn_monitor_select.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				STClientMain.getInstance().AddMonitorLayoutPanelToTabbedPane();
			}
		});
		btn_monitor_select.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel_6.add(btn_monitor_select);
		btn_monitor_select.setHorizontalAlignment(SwingConstants.LEFT);

		verticalStrut_5 = Box.createVerticalStrut(10);
		panel_6.add(verticalStrut_5);

		btn_monitor_up = new JButton("\u4E0A\u79FB");
		btn_monitor_up.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
			}
		});
		btn_monitor_up.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel_6.add(btn_monitor_up);
		btn_monitor_up.setHorizontalAlignment(SwingConstants.LEFT);

		verticalStrut_6 = Box.createVerticalStrut(10);
		panel_6.add(verticalStrut_6);

		btn_monitor_delete = new JButton("\u5220\u9664");
		btn_monitor_delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
			}
		});
		btn_monitor_delete.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel_6.add(btn_monitor_delete);
		btn_monitor_delete.setHorizontalAlignment(SwingConstants.LEFT);

		verticalStrut_7 = Box.createVerticalStrut(10);
		panel_6.add(verticalStrut_7);

		btn_monitor_down = new JButton("\u4E0B\u79FB");
		btn_monitor_down.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
			}
		});
		btn_monitor_down.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel_6.add(btn_monitor_down);
		btn_monitor_down.setHorizontalAlignment(SwingConstants.LEFT);

		verticalStrut_8 = Box.createVerticalStrut(10);
		panel_6.add(verticalStrut_8);
///////		
		comboBox_MultiMonitorChoice = new JComboBox();
		comboBox_MultiMonitorChoice.setModel(new DefaultComboBoxModel(new String[] {"\u9009\u62E9\u5927\u5C4F\u5E55", "\u5927\u5C4F\u5E551", "\u5927\u5C4F\u5E552", "\u5927\u5C4F\u5E553", "\u5927\u5C4F\u5E554", "\u5927\u5C4F\u5E555"}));
		comboBox_MultiMonitorChoice.setAutoscrolls(true);
		panel_6.add(comboBox_MultiMonitorChoice);

		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(panel_7);
		panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.Y_AXIS));

		panel_1 = new JPanel();
		panel_7.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.LEADING, 30, 5));

		lblNewLabel = new JLabel("\u65B0\u8F6E\u5DE1\u7EC4\u540D\u79F0");
		panel_1.add(lblNewLabel);

		textField_NewPollingName = new JTextField(pollingTeamNamePara);
		panel_1.add(textField_NewPollingName);
		textField_NewPollingName.setColumns(15);

		btn_AddNewName = new JButton("\u6DFB\u52A0");
		btn_AddNewName.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println(textField_NewPollingName.getText());
			}
		});
		panel_1.add(btn_AddNewName);

		panel_3 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_3.getLayout();
		flowLayout_2.setHgap(30);
		flowLayout_2.setAlignment(FlowLayout.LEADING);
		panel_7.add(panel_3);

		label = new JLabel("\u95F4\u9694\u65F6\u95F4");
		panel_3.add(label);

		textField_GapTime = new JTextField();
		panel_3.add(textField_GapTime);
		textField_GapTime.setColumns(15);

		label_1 = new JLabel("\u79D2");
		panel_3.add(label_1);

		btn_AlterGapTime = new JButton("\u4FEE\u6539");
		panel_3.add(btn_AlterGapTime);

		panel_4 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_4.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEADING);
		flowLayout_1.setHgap(30);
		panel_7.add(panel_4);

		btn_pollingEditor_confirm = new JButton("\u786E\u5B9A");
		btn_pollingEditor_confirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					STClientMain.getInstance().AlterAndAddNewPollingTeamConfirm(pollingTeamNamePara, table_camera, table_monitor, textField_GapTime, textField_NewPollingName, comboBox_MultiMonitorChoice);
					
					//删除正在运行的
					STClientMain.getInstance().pollingEditorPanel.closeButton_screenPanel.doClick();
					if (STClientMain.MainWindowframe.tabbedPane_bottom.indexOfComponent(STClientMain.getInstance().pollingControlPanel)==-1) 
					{
						STClientMain.getInstance().OpenPollingControl();
						
					}
					else 
					{
						
						STClientMain.getInstance().pollingControlPanel.InitPollingControlPanel();
						STClientMain.getInstance().pollingControlPanel.tableModel.fireTableDataChanged();
					}
					

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel_4.add(btn_pollingEditor_confirm);

		btn_pollingEditorCancel = new JButton("\u53D6\u6D88");
		btn_pollingEditorCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (STClientMain.MainWindowframe.tabbedPane_bottom.indexOfComponent(STClientMain.getInstance().pollingControlPanel)==-1) {
					try {
						STClientMain.getInstance().OpenPollingControl();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				STClientMain.getInstance().pollingEditorPanel.closeButton_screenPanel.doClick();
				//STClientMain.MainWindowframe.tabbedPane_bottom.remove(STClientMain.MainWindowframe.tabbedPane_bottom.indexOfComponent(current));
			}
		});
		panel_4.add(btn_pollingEditorCancel);
		
	}
	
	
	
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
		} 
		else 
		{
			cameraTable = new Object[0][2];
			monitorTable = new Object[0][1];
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

		
	
	public JPanel_PollingEditor(String pollingTeamName) throws SQLException {
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
