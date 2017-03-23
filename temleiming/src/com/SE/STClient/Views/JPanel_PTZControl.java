package com.SE.STClient.Views;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TreeSelectionListener;

import com.SE.STClient.Main.STClientMain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.border.EtchedBorder;
import java.awt.Component;



public class JPanel_PTZControl extends JPanel {
	
	
	public JLabel lblNewLabel_3;
	public JSlider slider_move;
	public JSlider slider_focuse;
	public JTextField textField;
	public JPanel titlePanel;
	private JPanel btn_Panel;

	
	
	public JPanel_PTZControl(String sipID) {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.NORTH);

		lblNewLabel_3 = new JLabel("");
		panel_3.add(lblNewLabel_3);

		JLabel lblNewLabel = new JLabel("目前速度：128");
		panel_3.add(lblNewLabel);

		JPanel panel_4 = new JPanel();
		panel.add(panel_4, BorderLayout.SOUTH);

		slider_move = new JSlider();
		panel_4.add(slider_move);
		slider_move.setMinimum(0);
		slider_move.setMaximum(255);
		slider_move.setValue(128);
		slider_move.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if ((JSlider) e.getSource() == slider_move)
					lblNewLabel.setText("目前速度：" + slider_move.getValue());
			}
		});

		JPanel panel_9 = new JPanel();
		panel.add(panel_9, BorderLayout.CENTER);
		panel_9.setLayout(new BorderLayout(0, 0));

		JPanel panel_10 = new JPanel();
		panel_9.add(panel_10, BorderLayout.NORTH);

		JButton btnNewButton = new JButton("      上      ");
		panel_10.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				STClientMain.getInstance().PTZUP(sipID);
			}
		});

		JPanel panel_12 = new JPanel();
		panel_9.add(panel_12, BorderLayout.SOUTH);

		JButton btnNewButton_3 = new JButton("      下     ");
		panel_12.add(btnNewButton_3);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				STClientMain.getInstance().PTZDown(sipID);
			}
		});

		JPanel panel_11 = new JPanel();
		panel_9.add(panel_11, BorderLayout.CENTER);
		panel_11.setLayout(new BoxLayout(panel_11, BoxLayout.X_AXIS));

		JPanel panel_14 = new JPanel();
		panel_11.add(panel_14);

		JButton btnNewButton_2 = new JButton("      左      ");
		panel_11.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				STClientMain.getInstance().PTZLeft(sipID);
			}
		});

		JPanel panel_13 = new JPanel();
		panel_11.add(panel_13);

		JButton btnNewButton_1 = new JButton("      右     ");
		panel_11.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				STClientMain.getInstance().PTZRight(sipID);
			}
		});

		JPanel panel_15 = new JPanel();
		panel_11.add(panel_15);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5, BorderLayout.NORTH);

		JLabel lblNewLabel_1 = new JLabel("目前速度：128");
		panel_5.add(lblNewLabel_1);

		JPanel panel_6 = new JPanel();
		panel_1.add(panel_6, BorderLayout.SOUTH);

		slider_focuse = new JSlider();
		slider_focuse.setMinimum(0);
		slider_focuse.setMaximum(16);
		slider_focuse.setValue(8);
		panel_6.add(slider_focuse);
		slider_focuse.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if ((JSlider) e.getSource() == slider_focuse)
					lblNewLabel_1.setText("目前速度：" + slider_focuse.getValue());
			}
		});

		JPanel panel_16 = new JPanel();
		panel_1.add(panel_16, BorderLayout.CENTER);
		panel_16.setLayout(new BorderLayout(0, 0));

		JPanel panel_17 = new JPanel();
		panel_16.add(panel_17, BorderLayout.NORTH);

		JPanel panel_18 = new JPanel();
		panel_16.add(panel_18, BorderLayout.SOUTH);

		JPanel panel_19 = new JPanel();
		panel_16.add(panel_19, BorderLayout.CENTER);
		panel_19.setLayout(new BoxLayout(panel_19, BoxLayout.X_AXIS));

		JPanel panel_20 = new JPanel();
		panel_19.add(panel_20);

		JButton btnNewButton_4 = new JButton("    放大    ");
		panel_19.add(btnNewButton_4);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				STClientMain.getInstance().PTZFocuseNear(sipID);
			}
		});

		JPanel panel_21 = new JPanel();
		panel_19.add(panel_21);

		JButton btnNewButton_5 = new JButton("    缩小    ");
		panel_19.add(btnNewButton_5);
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				STClientMain.getInstance().PTZFocuseFar(sipID);
			}
		});

		JPanel panel_22 = new JPanel();
		panel_19.add(panel_22);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JPanel panel_7 = new JPanel();
		panel_2.add(panel_7, BorderLayout.NORTH);

		JLabel lblNewLabel_2 = new JLabel("");
		panel_7.add(lblNewLabel_2);

		JPanel panel_8 = new JPanel();
		panel_2.add(panel_8, BorderLayout.SOUTH);

		JPanel panel_111 = new JPanel();
		panel_2.add(panel_111, BorderLayout.CENTER);
		panel_111.setLayout(new BoxLayout(panel_111, BoxLayout.LINE_AXIS));

		JPanel panel_51 = new JPanel();
		panel_111.add(panel_51);
		panel_51.setLayout(new BoxLayout(panel_51, BoxLayout.X_AXIS));

		JPanel panel_23 = new JPanel();
		panel_51.add(panel_23);

		JLabel lblNewLabel_11 = new JLabel("\u9884\u8BBE\u4F4D");
		panel_51.add(lblNewLabel_11);

		JPanel panel_61 = new JPanel();
		panel_111.add(panel_61);
		panel_61.setLayout(new BoxLayout(panel_61, BoxLayout.Y_AXIS));

		JPanel panel_161 = new JPanel();
		panel_61.add(panel_161);
		panel_161.setLayout(new BoxLayout(panel_161, BoxLayout.Y_AXIS));

		JPanel panel_201 = new JPanel();
		panel_161.add(panel_201);

		JPanel panel_171 = new JPanel();
		panel_161.add(panel_171);
		panel_171.setLayout(new BoxLayout(panel_171, BoxLayout.X_AXIS));

		JPanel panel_191 = new JPanel();
		panel_171.add(panel_191);
		panel_191.setLayout(new BoxLayout(panel_191, BoxLayout.Y_AXIS));

		JPanel panel_24 = new JPanel();
		panel_191.add(panel_24);

		JPanel panel_25 = new JPanel();
		panel_191.add(panel_25);

		textField = new JTextField();
		panel_25.add(textField);
		textField.setColumns(10);

		JPanel panel_181 = new JPanel();
		panel_61.add(panel_181);
		panel_181.setLayout(new BoxLayout(panel_181, BoxLayout.Y_AXIS));

		JPanel panel_211 = new JPanel();
		panel_181.add(panel_211);

		JButton btnNewButton_41 = new JButton("  调用    ");
		panel_211.add(btnNewButton_41);
		btnNewButton_41.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				STClientMain.getInstance().PTZSetSpecifyPosition(sipID);
			}
		});

		JPanel panel_221 = new JPanel();
		panel_181.add(panel_221);

		Btn_CloseButton closeButton_screenPanel = new Btn_CloseButton("/com/SE/res/close_24.png",
				"/com/SE/res/close_red_24.png", STClientMain.getInstance().MainWindowframe.tabbedPane_top, this);

		titlePanel = new JPanel();
		JLabel screenLabel = new JLabel("布局");
		screenLabel.setIcon(new ImageIcon(JFrame_MainWindow.class.getResource("/com/SE/res/layout_32.png")));
		titlePanel.setBorder(null);
		titlePanel.setOpaque(false);
		// titlePanel.setContentAreaFilled(false);
		titlePanel.add(screenLabel);
		titlePanel.add(closeButton_screenPanel);

	}

}
