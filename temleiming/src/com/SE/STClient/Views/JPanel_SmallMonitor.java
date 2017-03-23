package com.SE.STClient.Views;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.tree.DefaultMutableTreeNode;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.SE.STClient.Main.STClientMain;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class JPanel_SmallMonitor extends JPanel {

	/**
	 * Create the panel.
	 */
	public JPanel_SmallMonitor(int id,Color color) {		
		setLayout(new BorderLayout(0, 0));
		JPanel panel = new JPanel();
		panel.setBackground(color);
		
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignOnBaseline(true);
		flowLayout.setVgap(20);
		flowLayout.setHgap(20);
		
		
		add(panel, BorderLayout.CENTER);
		
		//∞¥≈•1£¨4
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton = new JButton("1");
		panel_1.add(btnNewButton, BorderLayout.WEST);
		
		JButton btnNewButton_1 = new JButton("4");
		panel_1.add(btnNewButton_1, BorderLayout.EAST);
		//±Í«©
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel, BorderLayout.SOUTH);
		lblNewLabel.setText("œ‘ æ∆˜"+id);
		lblNewLabel.setForeground(Color.white);
		setBackground(color);
				
	}

}







