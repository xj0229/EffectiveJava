package com.SE.STClient.Views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.SE.SQLite.SQLiteConnector;
import com.SE.STClient.Main.STClientMain;

import java.awt.Window.Type;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.Box;
import java.awt.Frame;
import java.awt.Cursor;
import java.awt.ComponentOrientation;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class JFrame_UserInfoConfigure extends JFrame {

	private JPanel contentPane;
	public JTextField textField_userIP;
	public JTextField textField_userPort;
	public JTextField textField_serverIP;
	public JTextField textField_serverPort;
	public JTextField textField_serverID;


	/**
	 * Create the frame.
	 */
	public JFrame_UserInfoConfigure() {
		setUndecorated(true);
		setType(Type.POPUP);
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblNewLabel_4 = new JLabel("\u914D\u7F6E\u8BBE\u5907\u4FE1\u606F");
		lblNewLabel_4.setForeground(new Color(51, 153, 204));
		lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 14));
		contentPane.add(lblNewLabel_4, BorderLayout.NORTH);
		
		JPanel panel_7 = new JPanel();
		contentPane.add(panel_7, BorderLayout.SOUTH);
		
		JButton btn_cancel = new JButton("\u53D6\u6D88");
		btn_cancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				STClientMain.getInstance().cancelSetUserInfo();
			}
		});
		btn_cancel.setFont(new Font("宋体", Font.PLAIN, 20));
		panel_7.add(btn_cancel);
		
		Component horizontalStrut = Box.createHorizontalStrut(40);
		panel_7.add(horizontalStrut);
		
		JButton btn_setUserInfo = new JButton("\u786E\u5B9A");
		btn_setUserInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					if ((textField_userIP.getDocument().getLength() != 0)
							& (textField_userPort.getDocument().getLength() != 0)
							& (textField_serverID.getDocument().getLength() != 0)
							& (textField_serverIP.getDocument().getLength() != 0)
							& (textField_serverPort.getDocument().getLength() != 0)) {
						STClientMain.getInstance().setUserInfo(textField_userIP.getText(), textField_userPort.getText(),
								textField_serverID.getText(), textField_serverIP.getText(),
								textField_serverPort.getText());
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_setUserInfo.setFont(new Font("宋体", Font.PLAIN, 20));
		panel_7.add(btn_setUserInfo);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		Component verticalStrut = Box.createVerticalStrut(80);
		panel.add(verticalStrut);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(12);
		panel_1.add(horizontalStrut_2);
		
		JLabel lblNewLabel = new JLabel("UserIP");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		panel_1.add(lblNewLabel);
		
		textField_userIP = new JTextField();
		textField_userIP.setFont(new Font("宋体", Font.PLAIN, 20));
		panel_1.add(textField_userIP);
		textField_userIP.setColumns(24);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(12);
		panel_1.add(horizontalStrut_1);
		
		JLabel lblNewLabel_1 = new JLabel("UserPort");
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));
		
		textField_userPort = new JTextField();
		panel_1.add(textField_userPort);
		textField_userPort.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_userPort.setColumns(6);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		
		JPanel panel_2 = new JPanel();
		panel_3.add(panel_2);
		
		JLabel lblNewLabel_2 = new JLabel("ServerIP");
		panel_2.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 20));
		
		textField_serverIP = new JTextField();
		panel_2.add(textField_serverIP);
		textField_serverIP.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_serverIP.setColumns(24);
		
		JLabel lblNewLabel_3 = new JLabel("ServerPort");
		panel_2.add(lblNewLabel_3);
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 20));
		
		textField_serverPort = new JTextField();
		panel_2.add(textField_serverPort);
		textField_serverPort.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_serverPort.setColumns(6);
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_4.getLayout();
		flowLayout.setAlignment(FlowLayout.LEADING);
		panel_3.add(panel_4);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(31);
		panel_4.add(horizontalStrut_3);
		
		JLabel lblNewLabel_5 = new JLabel("ServerID");
		lblNewLabel_5.setFont(new Font("宋体", Font.PLAIN, 20));
		panel_4.add(lblNewLabel_5);
		
		textField_serverID = new JTextField();
		textField_serverID.setFont(new Font("宋体", Font.PLAIN, 20));
		panel_4.add(textField_serverID);
		textField_serverID.setColumns(24);
	}
	
	

}
