package com.SE.STClient.Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;
import java.awt.dnd.DropTarget;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.TooManyListenersException;
import java.util.Vector;

import javax.sip.InvalidArgumentException;
import javax.sip.SipException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DebugGraphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.apache.log4j.Layout;

import com.SE.Manscdp.IMessage;
import com.SE.Manscdp.Message;
import com.SE.SIPServer.CustomerManage;
import com.SE.SIPServer.ISipCustomer;
import com.SE.SIPServer.ISipVideoControl;
import com.SE.SIPServer.SipServer;
import com.SE.SIPServer.VideoControl;
import com.SE.STClient.Common.MyDragGestureListener;
import com.SE.STClient.Common.MyTargetListener;
import com.SE.STClient.Common.TreeNode;
import com.SE.STClient.Service.PollingTeamDataService;
import com.SE.STClient.Service.UserConfigDataService;
import com.SE.STClient.Views.Btn_CloseButton;
import com.SE.STClient.Views.JPanel_PTZControl;
import com.SE.STClient.Views.JFrame_LoginWindow;
import com.SE.STClient.Views.JFrame_MainWindow;
import com.SE.STClient.Views.JFrame_UserInfoConfigure;
import com.SE.STClient.Views.JPanel_CheckPollingEditor;
import com.SE.STClient.Views.JPanel_MonitorLayout;
import com.SE.STClient.Views.JPanel_MonitorLayoutSmallPanel;
import com.SE.STClient.Views.JPanel_MultiMonitorScreen;
import com.SE.STClient.Views.JPanel_PollingControl;
import com.SE.STClient.Views.JPanel_PollingEditor;
import com.SE.STClient.Views.JPanel_SmallMonitor;

public class STClientMain {
	
	private SipServer m_sip;
	
	//所有界面的声明
	public static JFrame_MainWindow MainWindowframe;
	static JFrame_LoginWindow myLoginWindow;
	static List<JLabel> mylabel=new ArrayList<JLabel>();
	public JPanel_PollingEditor pollingEditorPanel;
	public JPanel_CheckPollingEditor checkPollingEditorPanel;
	public JFrame_UserInfoConfigure configUserInfoFrame;
	private ISipVideoControl m_VideoControl;

	
	//私有构造方法
	private STClientMain()
	{
		
	}
	

	
	//单例模式
	private static volatile STClientMain instance;	
	public static STClientMain getInstance()
	{
		if (instance==null) {
			synchronized (STClientMain.class) {
				
				if (instance==null) {
					instance=new STClientMain();
				}
			}			
		}
		return instance;
	}
	

	
	//主函数
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
 			try {	
 					myLoginWindow= new JFrame_LoginWindow();
 					myLoginWindow.setLocationRelativeTo(null); 
 					myLoginWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	public List<String> validateUser(String userName, String userIP, int userPort, String pwd) throws ParseException,
			InvalidArgumentException, SipException, SQLException, InterruptedException, TooManyListenersException {
		if (m_sip == null) {
			SipServer.initilizeSipServer(userName, userIP, userPort);
			m_sip = SipServer.getSipInstance();
		}
		List<String> validateResult = new ArrayList<String>();
		ISipCustomer iSipCustomer = new CustomerManage();
		validateResult = iSipCustomer.clientRegister(pwd);
		m_VideoControl = new VideoControl();
		return validateResult;
	}	

	
	//主界面开始按钮点击事件回调函数，进入矩阵控制界面
	public void enterClient()
	{
		
		//释放开始界面资源
		myLoginWindow.dispose();
		
		//加载矩阵控制界面
		EventQueue.invokeLater(new Runnable() {
			public void run() {
 			try {	
 					MainWindowframe= new JFrame_MainWindow(); 
 					MainWindowframe.setUndecorated(false); 
 			        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
 			        Rectangle bounds = new Rectangle(screenSize); 
 			        MainWindowframe.setBounds(bounds); 
 			        MainWindowframe.setExtendedState(MainWindowframe.MAXIMIZED_BOTH);
 					MainWindowframe.setVisible(true); 					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});		
	}
	
	
	
	
	//注销系统
	public void logOutSystem(String pwd) throws ParseException, InvalidArgumentException, SipException, InterruptedException
	{		
		ISipCustomer iSipCustomer = new CustomerManage();
		List<String> logOutResponse=iSipCustomer.clientLogoff(pwd);
		if (logOutResponse.get(0).equals("True")) {
			MainWindowframe.dispose();	
			System.out.println("注销成功");
		}
		else {
			for (String logoutResponse : logOutResponse) {
				System.out.println(logoutResponse);
			}			
		}		
	}
	
	
	//修改用户密码
	public void modifyPWD(String oldPWD,String newPWD) throws ParseException, InvalidArgumentException, SipException, InterruptedException
	{
		//1.先验证
		ISipCustomer iSipCustomer_modifyPWD=new CustomerManage();
		List<String> modifyPWDResult=iSipCustomer_modifyPWD.changePassword(oldPWD, newPWD);
		//2.验证成功往数据库更新值
		if (modifyPWDResult.get(0).equals("True")) {
			MainWindowframe.jFrame_ModifyUserPassWord.dispose();
			System.out.println("密码修改成功");
		} 
		else 
		{
			System.out.println("密码修改失败");

		}
		
	}
	
	
	
	
	//配置用户信息按钮事件
	public void configUserInfo()
	{
		myLoginWindow.dispose();		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
 			try {	
 					configUserInfoFrame= new JFrame_UserInfoConfigure();
 					configUserInfoFrame.setLocationRelativeTo(null); 
 					configUserInfoFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	//设置用户信息确认
	public void setUserInfo(String textField_userIP, String textField_userPort,
			String textField_serverID, String textField_serverIP, String textField_serverPort) throws SQLException
	{
		
		UserConfigDataService userConfigDataService=new UserConfigDataService();

		userConfigDataService.updateAllUserConfigDataToSql(textField_userIP,
				textField_userPort, textField_serverID,
				textField_serverIP, textField_userPort);
		
		configUserInfoFrame.dispose();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					myLoginWindow = new JFrame_LoginWindow();
					myLoginWindow.setLocationRelativeTo(null);
					myLoginWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public void cancelSetUserInfo()
	{
		configUserInfoFrame.dispose();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					myLoginWindow = new JFrame_LoginWindow();
					myLoginWindow.setLocationRelativeTo(null);
					myLoginWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//拖拽功能实现方法
	DropTarget dropTarget;
	MyTargetListener targetListener;
	DragSource dragSource;
	MyDragGestureListener dragGestureListener;
	private static boolean isExcuted=false;
	public void DragAndDropMethod(Component smallPanelPara)
	{
		if (!isExcuted) {
			//创建拖拽源
			dragSource=DragSource.getDefaultDragSource();
			//创建拖拽行为监听器
			dragGestureListener=new MyDragGestureListener();
			//设置拖拽源是MainWindowframe.tree
			dragSource.createDefaultDragGestureRecognizer(MainWindowframe.tree, DnDConstants.ACTION_COPY_OR_MOVE, 
					new MyDragGestureListener());
			//创建拖拽目标的监听器
			targetListener=new MyTargetListener();		
		}	
		isExcuted=true;
		dropTarget =new DropTarget(smallPanelPara, targetListener);
	}
	
	
	
	
	public JPanel_MultiMonitorScreen screenPanel;
	//添加显示器窗口到主窗口
	public void AddScreenPanelToTabbedPane()
	{
		
		if (MainWindowframe.tabbedPane_top.indexOfTab("显示器")==-1) {
			screenPanel=new JPanel_MultiMonitorScreen();
			MainWindowframe.tabbedPane_top.addTab("显示器", null, screenPanel, "");
			MainWindowframe.tabbedPane_top.setTabComponentAt(MainWindowframe.tabbedPane_top.indexOfComponent(screenPanel), screenPanel.titlePanel);
			//MainWindowframe.tabbedPane_top.setEnabledAt(0, true);
			MainWindowframe.tabbedPane_top.setSelectedIndex(MainWindowframe.tabbedPane_top.indexOfComponent(screenPanel));
		}
		else {
			MainWindowframe.tabbedPane_top.setSelectedIndex(MainWindowframe.tabbedPane_top.indexOfComponent(screenPanel));
		}
		
	}
	
	
		
	

	
	
	public JPanel_MonitorLayout monitorLayout;
	//设置解码器布局的按钮响应方法
	public void AddMonitorLayoutPanelToTabbedPane()	
	{
		
		if (MainWindowframe.tabbedPane_top.indexOfTab("布局")==-1) 
		{
			monitorLayout=new JPanel_MonitorLayout();
			MainWindowframe.tabbedPane_top.addTab("布局", null, monitorLayout, "");
			MainWindowframe.tabbedPane_top.setTabComponentAt(MainWindowframe.tabbedPane_top.indexOfComponent(monitorLayout), monitorLayout.titlePanel);
			MainWindowframe.tabbedPane_top.setSelectedIndex(MainWindowframe.tabbedPane_top.indexOfComponent(monitorLayout));
		}
		else
		{
			MainWindowframe.tabbedPane_top.setSelectedIndex(MainWindowframe.tabbedPane_top.indexOfComponent(monitorLayout));
		}
		
	}
	
	
	
	
		
	//放大单个显示窗口
	public void AmplifySmallMonitor(JPanel_SmallMonitor jPanel_SmallMonitor,String jPanel_SmallMonitorIndex)
	{
		JFrame maxMonitor=new  JFrame();
		maxMonitor.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	
		maxMonitor.setIconImage(Toolkit.getDefaultToolkit().getImage(JFrame_MainWindow.class.getResource("/com/SE/res/camera.png")));
	
		maxMonitor.add(jPanel_SmallMonitor);
		maxMonitor.setUndecorated(false); 
        Rectangle bounds = new Rectangle(new Dimension(MainWindowframe.tabbedPane_top.getHeight(), MainWindowframe.tabbedPane_top.getHeight())); 
        maxMonitor.setBounds(bounds); 
        maxMonitor.setTitle("摄像头"+jPanel_SmallMonitorIndex);
        maxMonitor.setVisible(true);
    	maxMonitor.setLocationRelativeTo(null); 
		maxMonitor.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e)
			{			
				screenPanel.add(jPanel_SmallMonitor, Integer.parseInt(jPanel_SmallMonitorIndex)-1);
				screenPanel.updateUI();
			}
		});

	}
	
	
	
	
	public JPanel_PollingControl pollingControlPanel;
	//添加轮巡控制界面到主窗口
	public void OpenPollingControl() throws SQLException
	{
		pollingControlPanel=new  JPanel_PollingControl();
	
		if (MainWindowframe.tabbedPane_bottom.indexOfTab("轮巡控制")==-1) {
			MainWindowframe.tabbedPane_bottom.addTab("轮巡控制", null, pollingControlPanel, "");
			MainWindowframe.tabbedPane_bottom.setTabComponentAt(MainWindowframe.tabbedPane_bottom.indexOfComponent(pollingControlPanel), pollingControlPanel.titlePanel);
			//MainWindowframe.tabbedPane_top.setEnabledAt(0, true);
			MainWindowframe.tabbedPane_bottom.setSelectedIndex(MainWindowframe.tabbedPane_bottom.indexOfComponent(pollingControlPanel));
		}	
	
	}
	
	
	
	//添加轮巡编辑界面到主窗口
	public void OpenPollingEditor(String pollingTeamName) throws SQLException
	{
		pollingEditorPanel=new JPanel_PollingEditor(pollingTeamName);
		
		if (MainWindowframe.tabbedPane_bottom.indexOfTab("轮巡编辑")==-1) {
			MainWindowframe.tabbedPane_bottom.addTab("轮巡编辑", null, pollingEditorPanel, "");
			MainWindowframe.tabbedPane_bottom.setTabComponentAt(MainWindowframe.tabbedPane_bottom.indexOfComponent(pollingEditorPanel), pollingEditorPanel.titlePanel);
			//MainWindowframe.tabbedPane_top.setEnabledAt(0, true);
			MainWindowframe.tabbedPane_bottom.setSelectedIndex(MainWindowframe.tabbedPane_bottom.indexOfComponent(pollingEditorPanel));
		}	
	}
	
	
	public void OpenCheckPollingEditor(String pollingTeamName) throws SQLException
	{
		checkPollingEditorPanel=new JPanel_CheckPollingEditor(pollingTeamName);
		
		if (MainWindowframe.tabbedPane_bottom.indexOfTab("轮巡查看")==-1) {
			MainWindowframe.tabbedPane_bottom.addTab("轮巡查看", null, checkPollingEditorPanel, "");
			MainWindowframe.tabbedPane_bottom.setTabComponentAt(MainWindowframe.tabbedPane_bottom.indexOfComponent(checkPollingEditorPanel), checkPollingEditorPanel.titlePanel);
			MainWindowframe.tabbedPane_bottom.setSelectedIndex(MainWindowframe.tabbedPane_bottom.indexOfComponent(checkPollingEditorPanel));
		}	
	}
	
	
	
	
	public void AlterAndAddNewPollingTeamConfirm(String transferPollingTeamName,JTable cameraTable,JTable monitorTable,JTextField gapTime,JTextField newName,JComboBox multiMonitor) throws SQLException
	{

		PollingTeamDataService pollingTeamDataService=new PollingTeamDataService(transferPollingTeamName);
		//如果新建的  或者编辑后保存的轮巡组正在运行   则停止该轮巡组
		if (pollingTeamDataService.GetRunningPollingTeamName().getRunningPollingTeam().contains(newName.getText())) {
			StopPolling(newName.getText());
			this.getInstance().pollingControlPanel.InitRunningPollingTeamTable();
			//pollingTeamDataService.DeletePollingTeamData(newName.getText());
			this.getInstance().pollingControlPanel.InitPollingControlPanel();
		}
		//将新建的或者修改后的轮巡组配置信息存入或者修改数据库
		pollingTeamDataService.AlterPollingTeamData(transferPollingTeamName, cameraTable, monitorTable, gapTime, newName, multiMonitor);

	}
	
	
	//启动轮巡组按钮响应函数
	public void StartPolling(JPanel_PollingControl pollingControlPanel)
	{
		PollingTeamDataService pollingTeamDataService=new PollingTeamDataService("");
		//System.out.println(pollingControlPanel.table_pollingNow.getModel().getValueAt(0, 0));
		if (pollingControlPanel.table_pollingName.getSelectedRow()!=-1) 
		{			
			pollingTeamDataService.AlterPollingTeamRunningState(pollingControlPanel.table_pollingName.getModel().getValueAt(pollingControlPanel.table_pollingName.getSelectedRow(), 0).toString());
		}
		
	}
	
	
	
	//查看轮巡组按钮响应函数
	public void CheckPolling()
	{
		
	}
	
	
	
	//编辑轮巡组按钮响应函数
	public void EditorPolling()
	{
		
	}
	
	
	
	//删除轮巡组按钮响应函数
	public void DeletePolling(String selectedPollingTeamName) throws SQLException
	{
		PollingTeamDataService pollingTeamDataService=new PollingTeamDataService(selectedPollingTeamName);
		
		//判断一下当前删除的轮巡组是否正在运行，如果正在运行则从正在运行的pollingteam表中删除
		if (pollingTeamDataService.GetRunningPollingTeamName().getRunningPollingTeam().contains(selectedPollingTeamName)) {
			StopPolling(selectedPollingTeamName);
			this.getInstance().pollingControlPanel.InitRunningPollingTeamTable();
			pollingTeamDataService.DeletePollingTeamData(selectedPollingTeamName);
			this.getInstance().pollingControlPanel.InitPollingControlPanel();
		}
		else
		{
			pollingTeamDataService.DeletePollingTeamData(selectedPollingTeamName);
			this.getInstance().pollingControlPanel.InitPollingControlPanel();
		}		
	}
	
	
	
	
	
	
	
	
	//暂停轮巡组按钮响应函数
	public void PausePolling()
	{
		
	}
	
	
	
	//继续轮巡组按钮响应函数
	public void ContinuePolling()
	{
		
	}
	
	
	
	//停止轮巡组按钮响应函数
	public void StopPolling(String runningPollingTeam)
	{
		PollingTeamDataService pollingTeamDataService=new PollingTeamDataService("");
		pollingTeamDataService.DeleteRunningPollingTeam(runningPollingTeam);
		System.out.println("删除成功");
	}
	
	
	
	//退出轮巡组按钮响应函数
	public void ExitPolling()
	{
		
	}
	
	
	
	//暂时退出轮巡组按钮响应函数
	public void ExitPollingTeam()
	{
		
	}
	
	
	
	//返回轮巡组按钮响应函数
	public void ReturnPollingTeam()
	{
		
	}
	
	//轮巡组编辑时，添加监视器到轮巡组
	public void AddMonitorToPollingTeam(JPanel_PollingEditor pollingEditor,String selectedMonitorName)
	{
		if (pollingEditor!=null) {	
		DefaultTableModel defaultTableModel_table_monitor=(DefaultTableModel)pollingEditor.table_monitor.getModel();
		Vector<String> newMonitor=new Vector<String>();
		newMonitor.addElement(selectedMonitorName);
		defaultTableModel_table_monitor.addRow(newMonitor);
		}
	}
	
	
	
	//轮巡组编辑时，添加摄像机到轮巡组
	public void AddCameraToPollingTeam(JPanel_PollingEditor pollingEditor,String cameraSource)
	{
		if (pollingEditor != null) {
			DefaultTableModel defaultTableModel_table_camera = (DefaultTableModel) pollingEditor.table_camera
					.getModel();
			Vector<String> newCamera = new Vector<String>();
			newCamera.addElement(cameraSource);
			newCamera.addElement("0");
			defaultTableModel_table_camera.addRow(newCamera);
		}
	}
	
	
	
	//根据用户选择的线路获取设备信息
	public void GetEquipmentsInfo(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			String s = (String) MainWindowframe.combox_selectStation.getSelectedItem();
			{
				if (MainWindowframe.combox_selectStation.getSelectedItem() == "地铁1号线") 
				{
					MainWindowframe.panel_EquipTree.remove(MainWindowframe.tree);
					MainWindowframe.panel_EquipTree.repaint();
			
					DefaultMutableTreeNode node = new DefaultMutableTreeNode("地铁");
			
				}
			}
		}
	}
	
	
	
	
	
	
	
	
	
	

	
	
	public static String name;
		
	public void treeselect(TreeSelectionEvent e) {
		DefaultMutableTreeNode note = (DefaultMutableTreeNode) MainWindowframe.tree.getLastSelectedPathComponent();
		name = note.toString();// 获得这个结点的名称

	}

	// 鼠标右键菜单按钮事件
	public void mouseclick(MouseEvent e) {
		TreePath path = STClientMain.MainWindowframe.tree.getPathForLocation(e.getX(), e.getY()); // 关键是这个方法的使用
		if (path == null) {
			return;
		}
		STClientMain.MainWindowframe.tree.setSelectionPath(path);
		if (e.getButton() == 3) {
			if ((!name.equals("车站")) & (!name.equals("站厅")) & (!name.equals("设备"))) {
				for (int i = 0; i < MainWindowframe.treeNodeCreater.runningDev.size(); i++) {

					if (name.equals(MainWindowframe.treeNodeCreater.runningDev.get(i))) {
						switch (MainWindowframe.treeNodeCreater.devType.get(i)) {

						case "domecamera":
							STClientMain.MainWindowframe.menu_CloudControl.setEnabled(true);
							STClientMain.MainWindowframe.menu_Play.setEnabled(true);
							STClientMain.MainWindowframe.menu_Replay.setEnabled(true);
							STClientMain.MainWindowframe.menu_Decode.setEnabled(true);
							STClientMain.MainWindowframe.menu_VideoDownload.setEnabled(true);
							break;
						case "ipc":
							STClientMain.MainWindowframe.menu_Play.setEnabled(true);
							STClientMain.MainWindowframe.menu_Replay.setEnabled(true);
							STClientMain.MainWindowframe.menu_Decode.setEnabled(true);
							STClientMain.MainWindowframe.menu_VideoDownload.setEnabled(true);
							STClientMain.MainWindowframe.menu_CloudControl.setEnabled(false);

							break;
						case "decoder":
							STClientMain.MainWindowframe.menu_Play.setEnabled(false);
							STClientMain.MainWindowframe.menu_Replay.setEnabled(false);
							STClientMain.MainWindowframe.menu_Decode.setEnabled(true);
							STClientMain.MainWindowframe.menu_VideoDownload.setEnabled(false);
							STClientMain.MainWindowframe.menu_CloudControl.setEnabled(false);							
							break;
						default:
							STClientMain.MainWindowframe.menu_Play.setEnabled(false);
							STClientMain.MainWindowframe.menu_Replay.setEnabled(false);
							STClientMain.MainWindowframe.menu_Decode.setEnabled(false);
							STClientMain.MainWindowframe.menu_VideoDownload.setEnabled(false);
							STClientMain.MainWindowframe.menu_CloudControl.setEnabled(false);	
							break;
						}
					}

				}
				STClientMain.MainWindowframe.popMenu.show(STClientMain.MainWindowframe.tree, e.getX(), e.getY());
			}

		}
	}
	

	
	
	public JPanel_PTZControl cloudterrace;
	// 右键菜单选择确定按钮
	public void PTZControl(ActionEvent e,String sipID) {

		if (MainWindowframe.tabbedPane_bottom.indexOfTab("云台控制") == -1) {
			cloudterrace = new JPanel_PTZControl(sipID);
			cloudterrace.lblNewLabel_3.setText(name);
			MainWindowframe.tabbedPane_bottom.addTab("云台控制", null, cloudterrace, "");
			MainWindowframe.tabbedPane_bottom.setTabComponentAt(
					MainWindowframe.tabbedPane_bottom.indexOfComponent(cloudterrace), cloudterrace.titlePanel);
			MainWindowframe.tabbedPane_bottom
					.setSelectedIndex(MainWindowframe.tabbedPane_bottom.indexOfComponent(cloudterrace));
		} else {
			MainWindowframe.tabbedPane_bottom
					.setSelectedIndex(MainWindowframe.tabbedPane_bottom.indexOfComponent(cloudterrace));
		}

	}

	
	public void PTZControlStartAndEnd(String command,String sipId )
	{
		IMessage ptzMessage=new Message();
		if (!command.equals(shot)) {
			String content = ptzMessage.parser(command, sipId);
			m_VideoControl.PTZControl(content);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			String stop = ptzMessage.parser("A50F0100000000B5", sipId);
			m_VideoControl.PTZControl(stop);
		}
		else
		{
			String preset = ptzMessage.parser(command, sipId);
			m_VideoControl.PTZControl(preset);
		}
		
	}
	
	
	
	
	
	public void PTZUP(String sipID) {
		String up = null;
		int speed3;
		String speed4;
		int speed = STClientMain.getInstance().cloudterrace.slider_move.getValue();
		String speed1 = Integer.toHexString(speed);
		if (speed < 16) {
			speed3 = (181 + 8 + speed) % 256;
			speed4 = Integer.toHexString(speed3);
			if (speed3 < 16) {
				up = "A50F01080" + speed1 + "00000" + speed4;
			} else if (speed3 >= 16) {
				up = "A50F01080" + speed1 + "0000" + speed4;
			}
		} else if (speed >= 16) {
			speed3 = (181 + 8 + speed) % 256;
			speed4 = Integer.toHexString(speed3);
			if (speed3 < 16) {
				up = "A50F0108" + speed1 + "00000" + speed4;
			} else if (speed3 >= 16) {
				up = "A50F0108" + speed1 + "0000" + speed4;
			}
		}
		System.out.println(up);
		PTZControlStartAndEnd(up,sipID);
	/*	list.clear();
		list.add(up);
		list.add("A50F010000000B5");*/
	}

	public void PTZDown(String sipID) {
		String up = null;
		int speed3;
		String speed4;
		int speed = STClientMain.getInstance().cloudterrace.slider_move.getValue();
		String speed1 = Integer.toHexString(speed);
		if (speed < 16) {
			speed3 = (181 + 4 + speed) % 256;
			speed4 = Integer.toHexString(speed3);
			if (speed3 < 16) {
				up = "A50F01040" + speed1 + "00000" + speed4;
			} else if (speed3 >= 16) {
				up = "A50F01040" + speed1 + "0000" + speed4;
			}
		} else if (speed >= 16) {
			speed3 = (181 + 4 + speed) % 256;
			speed4 = Integer.toHexString(speed3);
			if (speed3 < 16) {
				up = "A50F0104" + speed1 + "00000" + speed4;
			} else if (speed3 >= 16) {
				up = "A50F0104" + speed1 + "0000" + speed4;
			}
		}
		PTZControlStartAndEnd(up,sipID);
		System.out.println(up);
/*		list.clear();
		list.add(up);
		list.add("A50F010000000B5");*/
	}

	public void PTZLeft(String sipID) {
		String up = null;
		int speed3;
		String speed4;
		int speed = STClientMain.getInstance().cloudterrace.slider_move.getValue();
		String speed1 = Integer.toHexString(speed);
		if (speed < 16) {
			speed3 = (181 + 2 + speed) % 256;
			speed4 = Integer.toHexString(speed3);
			if (speed3 < 16) {
				up = "A50F0102000" + speed1 + "000" + speed4;
			} else if (speed3 >= 16) {
				up = "A50F0102000" + speed1 + "00" + speed4;
			}
		} else if (speed >= 16) {
			speed3 = (181 + 2 + speed) % 256;
			speed4 = Integer.toHexString(speed3);
			if (speed3 < 16) {
				up = "A50F010200" + speed1 + "000" + speed4;
			} else if (speed3 >= 16) {
				up = "A50F010200" + speed1 + "00" + speed4;
			}
		}
		PTZControlStartAndEnd(up,sipID);
		System.out.println(up);
/*		list.clear();
		list.add(up);
		list.add("A50F010000000B5");*/
	}

	public void PTZRight(String sipID) {
		String up = null;
		int speed3;
		String speed4;
		int speed = STClientMain.getInstance().cloudterrace.slider_move.getValue();
		String speed1 = Integer.toHexString(speed);
		if (speed < 16) {
			speed3 = (181 + 1 + speed) % 256;
			speed4 = Integer.toHexString(speed3);
			if (speed3 < 16) {
				up = "A50F0101000" + speed1 + "000" + speed4;
			} else if (speed3 >= 16) {
				up = "A50F0101000" + speed1 + "00" + speed4;
			}
		} else if (speed >= 16) {
			speed3 = (181 + 1 + speed) % 256;
			speed4 = Integer.toHexString(speed3);
			if (speed3 < 16) {
				up = "A50F010100" + speed1 + "000" + speed4;
			} else if (speed3 >= 16) {
				up = "A50F010100" + speed1 + "00" + speed4;
			}
		}
		PTZControlStartAndEnd(up,sipID);
		System.out.println(up);
	/*	list.clear();
		list.add(up);
		list.add("A50F010000000B5");*/
	}

	// 聚焦
	public void PTZFocuseNear(String sipID) {

		String near = null;
		int speed3;
		String speed4;
		int speed = STClientMain.getInstance().cloudterrace.slider_focuse.getValue();
		String speed1 = Integer.toHexString(speed);
		speed3 = (181 + 68 + speed) % 256;
		speed4 = Integer.toHexString(speed3);
		if (speed3 < 16) {
			near = "A50F011000000" + speed1 + "0" + speed4;
		} else if (speed3 >= 16) {
			near = "A50F011000000" + speed1 + speed4;
		}

		PTZControlStartAndEnd(near, sipID);
		System.out.println(near);
		/*
		 * list.clear(); list.add(near); list.add("A50F014000000F5");
		 */
	}

	// 聚焦远
	public void PTZFocuseFar(String sipID) {

		String far = null;
		int speed3;
		String speed4;
		int speed = STClientMain.getInstance().cloudterrace.slider_focuse.getValue();
		String speed1 = Integer.toHexString(speed);
		speed3 = (181 + 72 + speed) % 256;
		speed4 = Integer.toHexString(speed3);
		if (speed3 < 16) {
			far = "A50F012000000" + speed1 + "0" + speed4;
		} else if (speed3 >= 16) {
			far = "A50F012000000" + speed1 + speed4;
		}
		PTZControlStartAndEnd(far, sipID);
		System.out.println(far);
		/*
		 * list.clear(); list.add(near); list.add("A50F014000000F5");
		 */
	}

	
	String shot = null;
	// 预设位
	public void PTZSetSpecifyPosition(String sipID) {
		
		String shot1 = STClientMain.getInstance().cloudterrace.textField.getText();
		String shot2 = Integer.toHexString(Integer.parseInt(shot1));
		int shot3;
		String shot4;
		if (Integer.parseInt(shot1) < 16) {
			shot3 = (181 + 130 + Integer.parseInt(shot1)) % 256;
			shot4 = Integer.toHexString(shot3);
			if (shot3 < 16) {
				shot = "A50F0182000" + shot2 + "000" + shot4;
			} else if (shot3 >= 16) {
				shot = "A50F0182000" + shot2 + "00" + shot4;
			}
		} else if (Integer.parseInt(shot1) >= 16 && Integer.parseInt(shot1) <= 255) {
			shot3 = (181 + 130 + Integer.parseInt(shot1)) % 256;
			shot4 = Integer.toHexString(shot3);
			if (shot3 < 16) {
				shot = "A50F018200" + shot2 + "000" + shot3;
			} else if (shot3 >= 16) {
				shot = "A50F018200" + shot2 + "00" + shot3;
			}
		}
		PTZControlStartAndEnd(shot,sipID);
		System.out.println(shot);
	}
	
	
	
	
	
	
	
	
	
	

}
