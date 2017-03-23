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
import javax.sip.InvalidArgumentException;
import javax.sip.SipException;
import javax.swing.Box;
import java.awt.Frame;
import java.awt.Cursor;
import java.awt.ComponentOrientation;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import javax.swing.JPasswordField;

public class JFrame_ModifyUserPassWord extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField_Oldpwd;
	private JPasswordField passwordField_Newpwd;
	private JPasswordField passwordField_Newpwd2;


	/**
	 * Create the frame.
	 */
	public JFrame_ModifyUserPassWord() {
		setUndecorated(true);
		setType(Type.POPUP);
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 334);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u4FEE\u6539\u7528\u6237\u5BC6\u7801", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		Component verticalStrut = Box.createVerticalStrut(50);
		panel.add(verticalStrut);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("\u539F\u59CB\u5BC6\u7801");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		panel_1.add(lblNewLabel);
		
		passwordField_Oldpwd = new JPasswordField();
		passwordField_Oldpwd.setFont(new Font("宋体", Font.PLAIN, 20));
		passwordField_Oldpwd.setColumns(24);
		panel_1.add(passwordField_Oldpwd);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		
		JLabel lblNewLabel_2 = new JLabel("\u65B0\u5BC6\u7801");
		panel_2.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 20));
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(12);
		panel_2.add(horizontalStrut_1);
		
		passwordField_Newpwd = new JPasswordField();
		passwordField_Newpwd.setFont(new Font("宋体", Font.PLAIN, 20));
		passwordField_Newpwd.setColumns(24);
		panel_2.add(passwordField_Newpwd);
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
		FlowLayout flowLayout = (FlowLayout) panel_4.getLayout();
		
		JLabel lblNewLabel_5 = new JLabel("\u65B0\u5BC6\u7801");
		lblNewLabel_5.setFont(new Font("宋体", Font.PLAIN, 20));
		panel_4.add(lblNewLabel_5);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(12);
		panel_4.add(horizontalStrut_2);
		
		passwordField_Newpwd2 = new JPasswordField();
		passwordField_Newpwd2.setFont(new Font("宋体", Font.PLAIN, 20));
		passwordField_Newpwd2.setColumns(24);
		panel_4.add(passwordField_Newpwd2);
		
		JPanel panel_btn = new JPanel();
		panel.add(panel_btn);
		
		JButton btn_cancel = new JButton("\u53D6\u6D88");
		btn_cancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {				
				STClientMain.getInstance().MainWindowframe.jFrame_ModifyUserPassWord.dispose();
			}
		});
		btn_cancel.setFont(new Font("宋体", Font.PLAIN, 20));
		panel_btn.add(btn_cancel);
		
		Component horizontalStrut = Box.createHorizontalStrut(40);
		panel_btn.add(horizontalStrut);
		
		JButton btn_modifypwd = new JButton("\u786E\u5B9A");
		btn_modifypwd.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				if ((passwordField_Newpwd.getDocument().getLength() != 0)
						& (passwordField_Newpwd2.getDocument().getLength() != 0)
						& (passwordField_Oldpwd.getDocument().getLength() != 0)) 
				{
					if (String.valueOf(passwordField_Newpwd2.getPassword())
							.equals(String.valueOf(passwordField_Newpwd.getPassword()))) {
						try {
							STClientMain.getInstance().modifyPWD(String.valueOf(passwordField_Oldpwd.getPassword()),
									String.valueOf(passwordField_Newpwd2.getPassword()));
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (InvalidArgumentException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SipException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
					}
					else
					{
						System.out.println("密码输入错误");
					}
				}

			}
		});
		btn_modifypwd.setFont(new Font("宋体", Font.PLAIN, 20));
		panel_btn.add(btn_modifypwd);
	}
	
	

}
