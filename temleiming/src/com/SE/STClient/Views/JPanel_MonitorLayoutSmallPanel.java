package com.SE.STClient.Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class JPanel_MonitorLayoutSmallPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public JLabel Label_monitorTitle;
	public JPanel_MonitorLayoutSmallPanel() {
		setLayout(new BorderLayout(0, 0));				
		Label_monitorTitle= new JLabel("New label");
		Label_monitorTitle.setHorizontalAlignment(SwingConstants.CENTER);
		add(Label_monitorTitle, BorderLayout.SOUTH);
		
		Label_monitorTitle.setForeground(new Color(12));

	}

}
