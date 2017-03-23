package com.SE.STClient.Views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JSplitPane;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.SplitPaneUI;
import javax.swing.plaf.TabbedPaneUI;
import javax.swing.plaf.basic.BasicBorders.SplitPaneBorder;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIDefaults;
import javax.swing.JComboBox;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;

import com.SE.STClient.Common.MyDefaultTreeCellRenderer;
import com.SE.STClient.Common.TreeNode;

import com.SE.STClient.Main.STClientMain;

import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.Component;
import java.awt.Container;

import javax.swing.DropMode;
import java.awt.Dimension;
import java.awt.Choice;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import java.awt.Toolkit;
import javax.swing.JInternalFrame;
import java.awt.Rectangle;
import javax.swing.JEditorPane;
import javax.swing.JList;
import javax.sip.InvalidArgumentException;
import javax.sip.SipException;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Window.Type;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;
import java.awt.dnd.DropTarget;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.text.ParseException;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JFrame_MainWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPanel panel_screen;
	private JPanel panel_map;
	public JTabbedPane tabbedPane_top;
	private STClientMain stClientMainInstance;
	public static boolean isTreeDraged=false;
	public JTabbedPane tabbedPane_bottom;
	JDialog addPollingTeam;
	public JTextField setNewPollingTeamName;
	public JButton btn_addPollingTeam;
	public JPanel panel_EquipTree;
	public JComboBox combox_selectStation;
	public JTree tree;
	private JMenuItem menuItem_modifypwd;
    public JFrame_ModifyUserPassWord jFrame_ModifyUserPassWord;

    
    public JPopupMenu popMenu;
    public JMenuItem menu_CloudControl;
    public	JMenuItem menu_Replay;
    public	JMenuItem menu_Play;
    public	JMenuItem menu_Decode;
    public	JMenuItem menu_VideoDownload;
    public	JMenuItem menu_SetLayout;
    public TreeNode treeNodeCreater;

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public JFrame_MainWindow() throws SQLException {

		setIconImage(Toolkit.getDefaultToolkit().getImage(JFrame_MainWindow.class.getResource("/com/SE/res/243916591662263665.png")));
		setBackground(new Color(102, 204, 255));
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1202, 824);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setToolTipText("\u83DC\u5355\u680F");
		menuBar.setBackground(new Color(176, 196, 222));
		setJMenuBar(menuBar);
		
		JMenu menu_systemAdmin = new JMenu("\u7CFB\u7EDF\u7BA1\u7406");
		menu_systemAdmin.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
		menuBar.add(menu_systemAdmin);
		
		JMenuItem menuItem_systemLogout = new JMenuItem("\u6CE8\u9500\u7CFB\u7EDF");
		//系统注销按钮响应事件
		menuItem_systemLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {				
				JButton btn_logOutConfig=new  JButton("确定");				
				JTextField jTextField_pwd=new JTextField("");
				JDialog dialog_LogOut=new JDialog(STClientMain.MainWindowframe,"注销系统");
				dialog_LogOut.getContentPane().setLayout(new BorderLayout());
				dialog_LogOut.getContentPane().add(jTextField_pwd,BorderLayout.NORTH);
				dialog_LogOut.getContentPane().add(btn_logOutConfig,BorderLayout.EAST);
				dialog_LogOut.setBounds(0, 0, 400, 100);
				dialog_LogOut.setLocationRelativeTo(null); 
				dialog_LogOut.setVisible(true);
				btn_logOutConfig.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e)
					{
						try {
							STClientMain.getInstance().logOutSystem(jTextField_pwd.getText());
						} catch (ParseException | InvalidArgumentException | SipException | InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
				});
				
				
			}
		});
		
		menuItem_modifypwd = new JMenuItem("\u4FEE\u6539\u5BC6\u7801");
		menuItem_modifypwd.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				jFrame_ModifyUserPassWord= new JFrame_ModifyUserPassWord();
				jFrame_ModifyUserPassWord.setLocationRelativeTo(null); 
				jFrame_ModifyUserPassWord.setVisible(true);
			}
		});
		menu_systemAdmin.add(menuItem_modifypwd);
		menu_systemAdmin.add(menuItem_systemLogout);
		
		JMenu menu_configureData = new JMenu("  \u914D\u7F6E\u6570\u636E  ");
		menu_configureData.setHorizontalAlignment(SwingConstants.CENTER);
		menu_configureData.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
		menuBar.add(menu_configureData);
		
		JMenu menu_alarm = new JMenu("  \u62A5\u8B66  ");
		menu_alarm.setHorizontalAlignment(SwingConstants.CENTER);
		menu_alarm.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
		menuBar.add(menu_alarm);
		
		JMenu menu_view = new JMenu("  \u7A97\u53E3  ");
		menu_view.setHorizontalAlignment(SwingConstants.CENTER);
		menu_view.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
		menuBar.add(menu_view);
		
		JMenu menu_userAdmin = new JMenu("  \u7528\u6237\u7BA1\u7406  ");
		menu_userAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		menu_userAdmin.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
		menuBar.add(menu_userAdmin);
		
		JMenuItem menuItem_pollingControl = new JMenuItem("\u8F6E\u5DE1\u63A7\u5236");
		
		
		
		//快捷键单击事件监听处理
		menuItem_pollingControl.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
					try {
						STClientMain.getInstance().OpenPollingControl();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}		
			}
		});
		
		
		menu_userAdmin.add(menuItem_pollingControl);
		
		JMenuItem menuItem_pollingEditor = new JMenuItem("\u8F6E\u5DE1\u7F16\u8F91");
		
		//快捷键单击事件监听处理
		menuItem_pollingEditor.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				btn_addPollingTeam=new  JButton("确定");
								
				setNewPollingTeamName=new JTextField("");
				addPollingTeam=new JDialog(STClientMain.MainWindowframe,"添加轮巡组");
				addPollingTeam.getContentPane().setLayout(new BorderLayout());
				addPollingTeam.getContentPane().add(setNewPollingTeamName,BorderLayout.NORTH);
				addPollingTeam.getContentPane().add(btn_addPollingTeam,BorderLayout.EAST);
				addPollingTeam.setBounds(0, 0, 400, 100);
				addPollingTeam.setLocationRelativeTo(null); 
				addPollingTeam.setVisible(true);
				
				btn_addPollingTeam.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e)
					{
						System.out.println(setNewPollingTeamName.getText());
						if ( !setNewPollingTeamName.getText().equals(""))
						{
							addPollingTeam.dispose();
							try {
								STClientMain.getInstance().OpenPollingEditor(setNewPollingTeamName.getText());
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}						
					}
				});								
			}
		});
		menu_userAdmin.add(menuItem_pollingEditor);
		
		JMenu menu_help = new JMenu("  \u5E2E\u52A9  ");
		menu_help.setHorizontalAlignment(SwingConstants.CENTER);
		menu_help.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
		menuBar.add(menu_help);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar = new JToolBar();
		toolBar.setForeground(new Color(0, 191, 255));
		toolBar.setBackground(new Color(211, 211, 211));
		toolBar.setRollover(true);
		toolBar.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(JFrame_MainWindow.class.getResource("/com/SE/res/user_add.png")));
		toolBar.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon(JFrame_MainWindow.class.getResource("/com/SE/res/user_delete.png")));
		toolBar.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setIcon(new ImageIcon(JFrame_MainWindow.class.getResource("/com/SE/res/in.net.png")));
		toolBar.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setToolTipText("");
		btnNewButton_3.setVerticalAlignment(SwingConstants.BOTTOM);
		btnNewButton_3.setIcon(new ImageIcon(JFrame_MainWindow.class.getResource("/com/SE/res/out.png")));
		toolBar.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.setHorizontalAlignment(SwingConstants.RIGHT);
		btnNewButton_4.setIcon(new ImageIcon(JFrame_MainWindow.class.getResource("/com/SE/res/map_pointer.png")));
		toolBar.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("");
		btnNewButton_5.setVerticalAlignment(SwingConstants.BOTTOM);
		btnNewButton_5.setIcon(new ImageIcon(JFrame_MainWindow.class.getResource("/com/SE/res/alert.png")));
		toolBar.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("");
		btnNewButton_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				stClientMainInstance=STClientMain.getInstance();
				stClientMainInstance.AddScreenPanelToTabbedPane();
			}
		});
		btnNewButton_6.setIcon(new ImageIcon(JFrame_MainWindow.class.getResource("/com/SE/res/screen.png")));
		toolBar.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("");
		btnNewButton_7.setIcon(new ImageIcon(JFrame_MainWindow.class.getResource("/com/SE/res/video_camera.png")));
		toolBar.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("");
		btnNewButton_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					STClientMain.getInstance().OpenPollingControl();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_8.setIcon(new ImageIcon(JFrame_MainWindow.class.getResource("/com/SE/res/polling48.png")));
		//btnNewButton_8.setSize(48, 50);
		toolBar.add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton("");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				STClientMain.getInstance().AddMonitorLayoutPanelToTabbedPane();
			}
		});
		btnNewButton_9.setIcon(new ImageIcon(JFrame_MainWindow.class.getResource("/com/SE/res/layout_48.png")));
		toolBar.add(btnNewButton_9);
		
		JPanel panel_console = new JPanel();
		
		panel_console.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel_console, BorderLayout.SOUTH);
		panel_console.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 20));
		
		JPanel panel_equTree = new JPanel();
		contentPane.add(panel_equTree, BorderLayout.CENTER);
		panel_equTree.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setOneTouchExpandable(true);
		splitPane_1.setContinuousLayout(true);
		panel_equTree.add(splitPane_1);
		
		JPanel panel_EquipAndSearchTree = new JPanel();
		splitPane_1.setLeftComponent(panel_EquipAndSearchTree);
		panel_EquipAndSearchTree.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_EquipSearch = new JPanel();
		panel_EquipAndSearchTree.add(panel_EquipSearch, BorderLayout.NORTH);
		panel_EquipSearch.setLayout(new BoxLayout(panel_EquipSearch, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel = new JLabel("\u641C\u7D22");
		lblNewLabel.setIcon(new ImageIcon(JFrame_MainWindow.class.getResource("/com/SE/res/u=943123714,2250594442&fm=21&gp=0.jpg")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_EquipSearch.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setColumns(10);
		panel_EquipSearch.add(textField);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(JFrame_MainWindow.class.getResource("/com/SE/res/x.png")));
		panel_EquipSearch.add(lblNewLabel_1);
		
		panel_EquipTree = new JPanel();
		panel_EquipAndSearchTree.add(panel_EquipTree);
		panel_EquipTree.setLayout(new BorderLayout(0, 0));
		
		
		//设备列表树
	    DefaultMutableTreeNode node = new DefaultMutableTreeNode("地铁");//根node	  
	    treeNodeCreater=new TreeNode();
	    treeNodeCreater.CreatTreeNode(node);
	    tree = new JTree(node);     
        tree.setCellRenderer(new MyDefaultTreeCellRenderer());	
		panel_EquipTree.add(tree, BorderLayout.CENTER);
		
		
		
		
		
		popMenu = new JPopupMenu();
		menu_CloudControl = new JMenuItem("云台控制");
		menu_Replay = new JMenuItem("录像回放");
		menu_Decode = new JMenuItem("解码上墙");
		menu_Play = new JMenuItem("实时点播");
		menu_VideoDownload = new JMenuItem("录像下载");
		menu_SetLayout = new JMenuItem("设置分屏");
		
		
		menu_CloudControl.setEnabled(false);
		menu_Replay.setEnabled(false);
		menu_Decode.setEnabled(false);
		menu_Play.setEnabled(false);	
		menu_VideoDownload.setEnabled(false);
		menu_SetLayout.setEnabled(false);
		

		menu_Decode.setForeground(Color.black);
		menu_Play.setForeground(Color.black);
		menu_Replay.setForeground(Color.black);
		menu_CloudControl.setForeground(Color.black);
		menu_VideoDownload.setForeground(Color.black);
		menu_SetLayout.setForeground(Color.black);
		
		popMenu.add(menu_CloudControl);
		popMenu.add(menu_Replay);
		popMenu.add(menu_Decode);
		popMenu.add(menu_Play);
		popMenu.add(menu_VideoDownload);
		popMenu.add(menu_SetLayout);
		menu_CloudControl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				STClientMain.getInstance().PTZControl(e,treeNodeCreater.Dev_NameMapSipID.get(STClientMain.name));
			}
		});

		tree.addTreeSelectionListener(new TreeSelectionListener() {		
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				// TODO Auto-generated method stub
				STClientMain.getInstance().treeselect(e);
			}
		});

		
		
		//DefaultMutableTreeNode node111=tree.
		
		
		
		tree.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				STClientMain.getInstance().mouseclick(e);
			}
		});
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		
		
		combox_selectStation = new JComboBox();
		//线路改变选择时调用
		combox_selectStation.addItemListener(new ItemListener() {
			@Override
///////////////////////////////////
			public void itemStateChanged(ItemEvent e) {
				
			}
		});
		panel_EquipTree.add(combox_selectStation, BorderLayout.NORTH);
		combox_selectStation.setMaximumRowCount(30);
		combox_selectStation.setModel(new DefaultComboBoxModel(new String[] {"\u5730\u94C11\u53F7\u7EBF", "\u5730\u94C12\u53F7\u7EBF", "\u5730\u94C13\u53F7\u7EBF"}));
		combox_selectStation.setToolTipText("<全部>");
		
		
		
		
		
		JPanel panel_MainContainer = new JPanel();
		splitPane_1.setRightComponent(panel_MainContainer);
		panel_MainContainer.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
	
		splitPane.setOneTouchExpandable(true);
		panel_MainContainer.add(splitPane);
		splitPane.setContinuousLayout(true);
		splitPane.setResizeWeight(0.6);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		
		tabbedPane_top = new JTabbedPane(JTabbedPane.TOP);		
		splitPane.setLeftComponent(tabbedPane_top);
	
		
		tabbedPane_bottom = new JTabbedPane(JTabbedPane.BOTTOM);
		splitPane.setRightComponent(tabbedPane_bottom);
		
		
		
		
	}
		
}






