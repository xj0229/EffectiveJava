package com.SE.STClient.Views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;

import com.SE.STClient.Common.UserConfigInfo;
import com.SE.STClient.Main.STClientMain;
import com.SE.STClient.Service.UserConfigDataService;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JPasswordField;
import javax.swing.JList;
import javax.sip.InvalidArgumentException;
import javax.sip.SipException;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.TooManyListenersException;
import java.awt.FlowLayout;
import java.awt.Window.Type;
import java.awt.GridLayout;
import java.awt.Component;
import javax.swing.BoxLayout;
import java.awt.Dimension;

public class JFrame_LoginWindow extends JFrame {

	private JPanel contentPane_Start;
	private JPasswordField passwordField;
	private JCheckBox chckbxNewCheckBox;
	private JCheckBox chckbxNewCheckBox_1;
	private JCheckBox chckbxNewCheckBox_2;
	private JButton btn_Login;
	private STClientMain stClientMainInstance;
	private JPanel panel;
	private JPanel panel_1;
	private JComboBox UserNameInput;



	/**
	 * Create the frame.
	 */
	public JFrame_LoginWindow() {
		setType(Type.POPUP);
		//AffineTransform affineTransform=new AffineTransform();
		

		setResizable(false);
		setFont(new Font("微软雅黑", Font.PLAIN, 12));
		setTitle("\u7528\u6237\u767B\u9646");
		setIconImage(Toolkit.getDefaultToolkit().getImage(JFrame_LoginWindow.class.getResource("/com/SE/res/m.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 603, 538);
		contentPane_Start = new JPanel();
		contentPane_Start.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane_Start);
		
		JPanel panel_LoginInfo = new JPanel();
		panel_LoginInfo.setBorder(new TitledBorder(null, "\u767B\u5F55\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel SHMetroIcon = new JLabel("");
		SHMetroIcon.setHorizontalAlignment(SwingConstants.RIGHT);
		SHMetroIcon.setIcon(new ImageIcon(JFrame_LoginWindow.class.getResource("/com/SE/res/SH_Metro_Icon.jpg")));
		panel_LoginInfo.setLayout(null);
		
		JLabel Icon = new JLabel("");
		Icon.setBounds(95, 38, 80, 86);
		panel_LoginInfo.add(Icon);
		Icon.setAlignmentX(Component.CENTER_ALIGNMENT);
		Icon.setFont(new Font("宋体", Font.PLAIN, 9));
		Icon.setHorizontalAlignment(SwingConstants.CENTER);
		Icon.setIcon(new ImageIcon(JFrame_LoginWindow.class.getResource("/com/SE/res/user_96.png")));
		
		UserNameInput = new JComboBox();
		UserNameInput.setBounds(185, 38, 261, 43);
		panel_LoginInfo.add(UserNameInput);
		UserNameInput.setFont(new Font("宋体", Font.PLAIN, 20));
		UserNameInput.setEditable(true);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(185, 81, 261, 43);
		panel_LoginInfo.add(passwordField);
		passwordField.setFont(new Font("宋体", Font.PLAIN, 20));
		
		panel = new JPanel();
		panel.setBounds(186, 147, 261, 46);
		panel_LoginInfo.add(panel);
		FlowLayout fl_panel = new FlowLayout(FlowLayout.CENTER, 0, 10);
		panel.setLayout(fl_panel);
		
		chckbxNewCheckBox = new JCheckBox("\u4FDD\u5B58\u4FE1\u606F");
		chckbxNewCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(chckbxNewCheckBox);
		chckbxNewCheckBox.setFont(new Font("宋体", Font.PLAIN, 15));
		
		chckbxNewCheckBox_1 = new JCheckBox("\u8BB0\u4F4F\u5BC6\u7801");
		chckbxNewCheckBox_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(chckbxNewCheckBox_1);
		chckbxNewCheckBox_1.setFont(new Font("宋体", Font.PLAIN, 15));
		
		chckbxNewCheckBox_2 = new JCheckBox("\u81EA\u52A8\u767B\u5F55");
		chckbxNewCheckBox_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(chckbxNewCheckBox_2);
		chckbxNewCheckBox_2.setFont(new Font("宋体", Font.PLAIN, 15));
		
		panel_1 = new JPanel();
		panel_1.setBounds(186, 220, 261, 40);
		panel_LoginInfo.add(panel_1);
		panel_1.setLayout(null);
		
		btn_Login = new JButton("\u767B\u5F55");
		btn_Login.setBounds(161, 0, 100, 40);
		panel_1.add(btn_Login);
		
		
		
		
		
		//登录按钮点击事件监听函数
		btn_Login.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				List<String> validateResult = new ArrayList<String>();
				stClientMainInstance = STClientMain.getInstance();
				if (passwordField.getDocument().getLength()!= 0) {
					// 从数据库查找userip和userport
					UserConfigInfo myUserConfigInfo=new UserConfigInfo();;
					UserConfigDataService userConfigDataService = new UserConfigDataService();
					try {
						myUserConfigInfo = userConfigDataService.GetAllUserConfigData();
						System.out.println(myUserConfigInfo.getUserIP().get(0));
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

					try {
						validateResult = stClientMainInstance.validateUser(UserNameInput.getSelectedItem().toString(),
								myUserConfigInfo.getUserIP().get(0), myUserConfigInfo.getUserPort().get(0), String.valueOf(passwordField.getPassword()));
						if (validateResult.get(0).equals("True")) {
							stClientMainInstance.enterClient();
						} else {
							System.out.println(validateResult.toArray());
						}

					} catch (ParseException | InvalidArgumentException | SipException | SQLException
							| InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (TooManyListenersException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});
		btn_Login.setBackground(new Color(0, 153, 204));
		btn_Login.setFont(new Font("宋体", Font.PLAIN, 20));
		btn_Login.setForeground(new Color(240, 248, 255));
		
		JButton btn_config = new JButton("\u914D\u7F6E");
		btn_config.setBounds(0, 0, 100, 40);
		panel_1.add(btn_config);
		
		//配置用户信息
		btn_config.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				STClientMain.getInstance().configUserInfo();
			}
		});
		btn_config.setForeground(new Color(240,248, 255));
		btn_config.setBackground(new Color(0,153, 204));
		btn_config.setFont(new Font("宋体", Font.PLAIN, 20));
		contentPane_Start.setLayout(new BorderLayout(0, 0));
		contentPane_Start.add(SHMetroIcon, BorderLayout.NORTH);
		contentPane_Start.add(panel_LoginInfo);
	}
}
