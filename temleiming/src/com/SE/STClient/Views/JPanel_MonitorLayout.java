package com.SE.STClient.Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.SE.STClient.Main.STClientMain;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;

public class JPanel_MonitorLayout extends JPanel {

	/**
	 * Create the panel.
	 */
	public JPanel titlePanel;
	private JPanel btn_Panel;
	private JPanel panel_GridContain;
	public JPanel_MonitorLayout() {
		btn_Panel = new JPanel();
		setLayout(new BorderLayout(0, 0));
		btn_Panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_GridContain = new JPanel();
		
		
		panel_GridContain.setAutoscrolls(true);
		JPanel btn_gridStyle = new JPanel();
		btn_Panel.add(btn_gridStyle);
		
		JButton fourLayout = new JButton();
		btn_gridStyle.add(fourLayout);
		fourLayout.setIcon(new ImageIcon(JFrame_MainWindow.class.getResource("/com/SE/res/4layout_32.png")));

		JButton nineLayout = new JButton();
		btn_gridStyle.add(nineLayout);
		nineLayout.setIcon(new ImageIcon(JFrame_MainWindow.class.getResource("/com/SE/res/9layout_32.png")));

		JButton sixteenLayout = new JButton();
		btn_gridStyle.add(sixteenLayout);
		sixteenLayout.setIcon(new ImageIcon(JFrame_MainWindow.class.getResource("/com/SE/res/16layout.jpg")));
		sixteenLayout.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				GenerateLayoutGrid(STClientMain.getInstance().monitorLayout, panel_GridContain, btn_Panel, 4);

			}
		});
		nineLayout.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				GenerateLayoutGrid(STClientMain.getInstance().monitorLayout, panel_GridContain, btn_Panel, 3);

			}
		});
		fourLayout.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				GenerateLayoutGrid(STClientMain.getInstance().monitorLayout, panel_GridContain, btn_Panel, 2);
			}
		});
		JButton btn_confirm = new JButton("确定");
		JButton btn_play = new JButton("播放");
		JButton btn_stop = new JButton("停止");
		JPanel btn_conmandJPanel = new JPanel();
		btn_conmandJPanel.add(btn_confirm);
		btn_conmandJPanel.add(btn_play);
		btn_conmandJPanel.add(btn_stop);
		btn_Panel.add(btn_conmandJPanel);
		add(btn_Panel, BorderLayout.SOUTH);

		Btn_CloseButton closeButton_screenPanel = new Btn_CloseButton("/com/SE/res/close_24.png",
				"/com/SE/res/close_red_24.png", STClientMain.getInstance().MainWindowframe.tabbedPane_top, this);
		
		
		add(panel_GridContain, BorderLayout.CENTER);
		panel_GridContain.setLayout(new GridLayout(1, 0, 0, 0));
		titlePanel= new JPanel();
		JLabel screenLabel = new JLabel("布局");
		screenLabel.setIcon(new ImageIcon(JFrame_MainWindow.class.getResource("/com/SE/res/layout_32.png")));
		titlePanel.setBorder(null);
		titlePanel.setOpaque(false);
		// titlePanel.setContentAreaFilled(false);
		titlePanel.add(screenLabel);
		titlePanel.add(closeButton_screenPanel);

	}
	
	
	
	private void GenerateLayoutGrid(JPanel_MonitorLayout jPanel_MonitorLayoutPara,JPanel jPanel_GridMonitorPara,JPanel btnPanel,int gridCount)
	{
		jPanel_GridMonitorPara.removeAll();
		JPanel smallMultiPanel=new JPanel();				
		smallMultiPanel.setLayout(new GridLayout(gridCount, gridCount,4,4));
		JPanel jPanel=new JPanel();
		jPanel.setLayout(null);
			
		for (int i = 0; i < gridCount*gridCount; i++) 
		{					
			JPanel_MonitorLayoutSmallPanel smallPanel=new JPanel_MonitorLayoutSmallPanel();
			smallPanel.setBorder(new LineBorder(Color.BLACK));
			smallPanel.Label_monitorTitle.setText("监视器"+(i+1));
			smallPanel.setName("监视器"+(i+1));
			
			//添加监视器到轮巡组中
			smallPanel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e)
				{
					if (e.getClickCount()==2) {
						STClientMain.getInstance().AddMonitorToPollingTeam(STClientMain.getInstance().pollingEditorPanel, e.getComponent().getName());
						System.out.println(e.getComponent().getName());
					}
				}
			});
			smallMultiPanel.add(smallPanel);					
			//创建拖拽目标，并添加监听器
			STClientMain.getInstance().DragAndDropMethod(smallPanel);																						
		}
		
		jPanel.add(smallMultiPanel);
		smallMultiPanel.setSize(new Dimension( (Integer)(jPanel_MonitorLayoutPara.getHeight()-btnPanel.getHeight()-20),(Integer)(jPanel_MonitorLayoutPara.getHeight()-btnPanel.getHeight()-20)) );	
	
		smallMultiPanel.setLocation((Integer)(jPanel_MonitorLayoutPara.getWidth()/2-smallMultiPanel.getWidth()/2  ), (Integer)(  (jPanel_MonitorLayoutPara.getHeight()-btnPanel.getHeight())/2-smallMultiPanel.getHeight()/2  ) );
		jPanel_GridMonitorPara.add(jPanel);
		jPanel_GridMonitorPara.updateUI();
		jPanel_MonitorLayoutPara.updateUI();
	}

}
