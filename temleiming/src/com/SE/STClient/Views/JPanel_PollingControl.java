package com.SE.STClient.Views;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.Box;
import javax.swing.ListSelectionModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.JTable;
import java.awt.ScrollPane;
import javax.swing.table.DefaultTableModel;

import com.SE.STClient.Common.AllPollingTeamInfo;
import com.SE.STClient.Main.STClientMain;
import com.SE.STClient.Service.PollingTeamDataService;

import java.awt.Choice;
import java.awt.Color;

import javax.swing.ScrollPaneConstants;
import javax.swing.JSplitPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class JPanel_PollingControl extends JPanel {
	public JTable table_screenOfPolling;
	public JTable table_screenOfExitPolling;
	public JTable table_pollingName;
	public JTable table_pollingNow;
	public JPanel titlePanel;
	public JButton btn_start;
	public JButton btn_check;
	public JButton btn_edtior;
	public JButton btn_delete;
	public JButton btn_pause;
	public JButton btn_continue;
	public JButton btn_stop;
	public JButton btn_exit;
	public JButton btn_exitPollingTeam;
	public JButton btn_returnPollingTeam;
	private Object[][] pollingTable;
	public DefaultTableModel tableModel;
    public Btn_CloseButton closeButton_screenPanel;
    private Object[][] runningPollingTable;
    public DefaultTableModel tableModel_RunningPollingTeam;

	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	
	
	
	
	public void InitPollingControlPanel() throws SQLException
	{
		AllPollingTeamInfo allPollingTeamInfo;
		PollingTeamDataService pollingTeamDataService=new PollingTeamDataService("");
		allPollingTeamInfo=pollingTeamDataService.GetAllPollingTeam();
		pollingTable=new Object[allPollingTeamInfo.getPollingTeamName().size()][1];
		for (int i = 0; i <allPollingTeamInfo.getPollingTeamName().size(); i++) {
			pollingTable[i][0]=allPollingTeamInfo.getPollingTeamName().get(i);
		}
		
		table_pollingName.setModel(new DefaultTableModel(
				pollingTable,
			new String[] {
				"\u8F6E\u5DE1\u7EC4\u540D\u79F0"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_pollingName.updateUI();
		
		
	}
	
	
	
	public void InitRunningPollingTeamTable() throws SQLException
	{
		PollingTeamDataService pollingTeamDataService=new PollingTeamDataService("");
		runningPollingTable=new Object[pollingTeamDataService.GetRunningPollingTeamName().getRunningPollingTeam().size()][1];
		for (int i = 0; i <runningPollingTable.length; i++) 
		{
			runningPollingTable[i][0]=pollingTeamDataService.GetRunningPollingTeamName().getRunningPollingTeam().get(i);
		}
		table_pollingNow.setModel(new DefaultTableModel(
				runningPollingTable,
			new String[] {
				"\u8F6E\u5DE1\u7EC4\u540D\u79F0"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_pollingNow.updateUI();
	}
	
	
	
	public JPanel_PollingControl() throws SQLException {

		closeButton_screenPanel=new Btn_CloseButton("/com/SE/res/close_24.png", "/com/SE/res/close_red_24.png", STClientMain.MainWindowframe.tabbedPane_bottom, this);
		titlePanel=new JPanel();
		JLabel screenLabel=new JLabel("轮巡控制");
		screenLabel.setIcon(new ImageIcon(JFrame_MainWindow.class.getResource("/com/SE/res/polling24.png")));
		titlePanel.setBorder(null);
		titlePanel.setOpaque(false);
		titlePanel.add(screenLabel);
		titlePanel.add(closeButton_screenPanel);
		setLayout(new BorderLayout(0, 0));
		JSplitPane splitPane = new JSplitPane();
		splitPane.setContinuousLayout(true);
		splitPane.setResizeWeight(0.5);
		add(splitPane, BorderLayout.CENTER);
		
		JPanel panel_7 = new JPanel();

		splitPane.setLeftComponent(panel_7);
		panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		panel_7.add(scrollPane_2);
		panel_7.add(Box.createHorizontalStrut(10));
		scrollPane_2.setPreferredSize(new Dimension(160, 220));
		
		table_pollingName = new JTable();
		table_pollingName.setUpdateSelectionOnSort(true);
		table_pollingName.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		InitPollingControlPanel();
		tableModel=(DefaultTableModel)table_pollingName.getModel();	
		scrollPane_2.setViewportView(table_pollingName);
		
		JPanel panel_2 = new JPanel();
		panel_7.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		btn_start = new JButton("\u542F\u52A8");
		//**************************************************************************************启动按钮*****************************************************************************************
		btn_start.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				PollingTeamDataService pollingTeamDataService=new PollingTeamDataService("");				
				try {
					if (STClientMain.getInstance().pollingControlPanel.table_pollingName.getSelectedRow() != -1) {

						if (!pollingTeamDataService.GetRunningPollingTeamName().getRunningPollingTeam()
								.contains(STClientMain.getInstance().pollingControlPanel.table_pollingName
										.getValueAt(STClientMain.getInstance().pollingControlPanel.table_pollingName
												.getSelectedRow(), 0)
										.toString())) {

							STClientMain.getInstance().StartPolling(STClientMain.getInstance().pollingControlPanel);
							try {
								InitRunningPollingTeamTable();
								tableModel_RunningPollingTeam.fireTableDataChanged();

							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel_2.add(btn_start);
		panel_2.add(Box.createVerticalStrut(10));
		btn_check = new JButton("\u67E5\u770B");
		//**************************************************************************************查看按钮*****************************************************************************************
		btn_check.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (table_pollingName.getSelectedRow()!=-1) {
					try {
						STClientMain.getInstance().OpenCheckPollingEditor(table_pollingName.getModel().getValueAt(table_pollingName.getSelectedRow(), 0).toString());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}				
			}
		});
		panel_2.add(btn_check);
		panel_2.add(Box.createVerticalStrut(10));
		btn_edtior = new JButton("\u7F16\u8F91");
		//**************************************************************************************编辑按钮*****************************************************************************************
		btn_edtior.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (table_pollingName.getSelectedRow()!=-1) {
					try {
						STClientMain.getInstance().OpenPollingEditor(table_pollingName.getModel().getValueAt(table_pollingName.getSelectedRow(), 0).toString());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}				
			}
		});
		panel_2.add(btn_edtior);
		panel_2.add(Box.createVerticalStrut(10));
		btn_delete = new JButton("\u5220\u9664");
		//**************************************************************************************删除按钮*****************************************************************************************
		btn_delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (table_pollingName.getSelectedRow()!=-1) {
								
					try {
						STClientMain.getInstance().DeletePolling(table_pollingName.getModel().getValueAt(table_pollingName.getSelectedRow(), 0).toString());
						//删除选定的轮巡组			
						tableModel.fireTableDataChanged();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}	
				
			}
		});
		panel_2.add(btn_delete);
		panel_2.add(Box.createVerticalStrut(10));
		JScrollPane scrollPane_3 = new JScrollPane();
		panel_7.add(scrollPane_3);
		panel_7.add(Box.createHorizontalStrut(10));
		scrollPane_3.setPreferredSize(new Dimension(160, 220));
		
		
		
		table_pollingNow = new JTable();
		table_pollingNow.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		InitRunningPollingTeamTable();
		table_pollingNow.setModel(new DefaultTableModel(
				runningPollingTable,
			new String[] {
				"\u6B63\u5728\u8F6E\u5DE1"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableModel_RunningPollingTeam=(DefaultTableModel)table_pollingNow.getModel();
		scrollPane_3.setViewportView(table_pollingNow);
		
		JPanel panel_3 = new JPanel();
		panel_7.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		
		btn_pause = new JButton("\u6682\u505C");
		//**************************************************************************************暂停按钮*****************************************************************************************
		btn_pause.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
			}
		});
		panel_3.add(btn_pause);
		panel_3.add(Box.createVerticalStrut(10));
		btn_continue = new JButton("\u7EE7\u7EED");
		//**************************************************************************************继续按钮*****************************************************************************************
		btn_continue.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
			}
		});
		panel_3.add(btn_continue);
		panel_3.add(Box.createVerticalStrut(10));
		btn_stop = new JButton("\u505C\u6B62");
		//**************************************************************************************停止按钮*****************************************************************************************
		btn_stop.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
			
				if (table_pollingNow.getSelectedRow()!=-1) {
					STClientMain.getInstance().StopPolling(table_pollingNow.getModel().getValueAt(table_pollingNow.getSelectedRow(), 0).toString());
					try {
						STClientMain.getInstance().pollingControlPanel.InitRunningPollingTeamTable();
						tableModel_RunningPollingTeam.fireTableDataChanged();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		panel_3.add(btn_stop);
		panel_3.add(Box.createVerticalStrut(10));
		btn_exit = new JButton("\u9000\u51FA");
		//**************************************************************************************退出按钮*****************************************************************************************
		btn_exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
			}
		});
		panel_3.add(btn_exit);
		panel_3.add(Box.createVerticalStrut(10));
		
		JPanel panel_8 = new JPanel();
		splitPane.setRightComponent(panel_8);
		panel_8.setLayout(new BoxLayout(panel_8, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_8.add(scrollPane);
		panel_8.add(Box.createHorizontalStrut(10));
		scrollPane.setPreferredSize(new Dimension(100, 220));
		
		table_screenOfPolling = new JTable();
		table_screenOfPolling.setSurrendersFocusOnKeystroke(true);
		table_screenOfPolling.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_screenOfPolling.setFillsViewportHeight(true);
		table_screenOfPolling.setModel(new DefaultTableModel(
			new Object[][] {
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
			},
			new String[] {
				"\u8F6E\u5DE1\u7EC4 \u7684\u663E\u793A\u5668"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table_screenOfPolling);
		
		
		btn_exitPollingTeam = new JButton("\u6682\u65F6\u9000\u51FA\u8F6E\u5DE1\u7EC4");
		//**************************************************************************************暂时退出轮巡组按钮*****************************************************************************************
		btn_exitPollingTeam.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
			}
		});
		panel_8.add(btn_exitPollingTeam);
		panel_8.add(Box.createHorizontalStrut(30));
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_8.add(scrollPane_1);
		panel_8.add(Box.createHorizontalStrut(10));
		scrollPane_1.setPreferredSize(new Dimension(100, 220));
		
		table_screenOfExitPolling = new JTable();
		table_screenOfExitPolling.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_screenOfExitPolling.setModel(new DefaultTableModel(
			new Object[][] {
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
			},
			new String[] {
				"\u9000\u51FA\u8F6E\u5DE1\u7EC4 \u7684\u663E\u793A\u5668"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_1.setViewportView(table_screenOfExitPolling);
		
		btn_returnPollingTeam = new JButton("\u56DE\u5F52\u8F6E\u5DE1\u7EC4");
		//**************************************************************************************回归轮巡组按钮*****************************************************************************************
		btn_returnPollingTeam.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
			}
		});
		panel_8.add(btn_returnPollingTeam);
		panel_8.add(Box.createHorizontalStrut(10));

	}
}
