package com.SE.STClient.Views;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.SE.STClient.Main.STClientMain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class JPanel_MultiMonitorScreen extends JPanel {

	/**
	 * Create the panel.
	 */	
	public JPanel titlePanel;
	public JPanel_MultiMonitorScreen()
	{
		
		setLayout(new GridLayout(2, 4,4,50));

		for (int i = 0; i < 16; i++) {
			
				JPanel_SmallMonitor smallPanel=new JPanel_SmallMonitor(i+1,Color.black);
				
				smallPanel.setName(((Integer)(i+1)).toString());
				//放大窗口鼠标双击事件
				smallPanel.addMouseListener(new MouseAdapter(){
					@Override
					public void mouseClicked(MouseEvent e)
					{
						if (e.getClickCount()==2) {
							JPanel_SmallMonitor jPanel_SmallMonitor=new  JPanel_SmallMonitor(1,Color.black);
							jPanel_SmallMonitor=(JPanel_SmallMonitor)e.getSource();
							String amplifySmallMonitorIndex=((JPanel_SmallMonitor)e.getSource()).getName();
							STClientMain.getInstance().AmplifySmallMonitor((JPanel_SmallMonitor)e.getSource(),amplifySmallMonitorIndex);
							STClientMain.getInstance().screenPanel.updateUI();
						}
					}
				});	
				add(smallPanel);
				
				//创建拖拽目标，并添加监听器
				STClientMain.getInstance().DragAndDropMethod(smallPanel);			
		}
					
		Btn_CloseButton closeButton_screenPanel=new Btn_CloseButton("/com/SE/res/close_24.png", "/com/SE/res/close_red_24.png", STClientMain.getInstance().MainWindowframe.tabbedPane_top, this);
		titlePanel=new JPanel();
		JLabel screenLabel=new JLabel("显示器");
		screenLabel.setIcon(new ImageIcon(JFrame_MainWindow.class.getResource("/com/SE/res/screen_35.png")));
		titlePanel.setBorder(null);
		titlePanel.setOpaque(false);
		titlePanel.add(screenLabel);
		titlePanel.add(closeButton_screenPanel);
		
		
	}
	
}
