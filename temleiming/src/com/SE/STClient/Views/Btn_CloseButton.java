package com.SE.STClient.Views;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.SE.STClient.Main.STClientMain;

public class Btn_CloseButton extends JButton {

	private ImageIcon icon;

	public Btn_CloseButton(String iconPath, String rolloverIconPath, JTabbedPane currentTabbedPane,
			JPanel currentPanel) {
		icon = new ImageIcon(getClass().getResource(iconPath));
		setSize(icon.getImage().getWidth(null), icon.getImage().getHeight(null));
		setIcon(icon);
		setBorderPainted(false);
		setFocusPainted(false);
		setOpaque(false);
		setContentAreaFilled(false);
		setBorder(null);
		setRolloverIcon(new ImageIcon(getClass().getResource(rolloverIconPath)));
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				currentTabbedPane.remove(currentTabbedPane.indexOfComponent(currentPanel));

			}
		});

		this.addActionListener(new Action() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				currentTabbedPane.remove(currentTabbedPane.indexOfComponent(currentPanel));
			}

			@Override
			public void setEnabled(boolean b) {
				// TODO Auto-generated method stub

			}

			@Override
			public void removePropertyChangeListener(PropertyChangeListener listener) {
				// TODO Auto-generated method stub

			}

			@Override
			public void putValue(String key, Object value) {
				// TODO Auto-generated method stub

			}

			@Override
			public boolean isEnabled() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public Object getValue(String key) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void addPropertyChangeListener(PropertyChangeListener listener) {
				// TODO Auto-generated method stub

			}
		}

		);
	}
	
	
	
	
	
	public Btn_CloseButton(String iconPath, String rolloverIconPath, JFrame currentFrame) 
	{
		icon = new ImageIcon(getClass().getResource(iconPath));
		setSize(icon.getImage().getWidth(null), icon.getImage().getHeight(null));
		setIcon(icon);
		setBorderPainted(false);
		setFocusPainted(false);
		setOpaque(false);
		setContentAreaFilled(false);
		setBorder(null);
		setRolloverIcon(new ImageIcon(getClass().getResource(rolloverIconPath)));
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

			}
		});

		this.addActionListener(new Action() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void setEnabled(boolean b) {
				// TODO Auto-generated method stub

			}

			@Override
			public void removePropertyChangeListener(PropertyChangeListener listener) {
				// TODO Auto-generated method stub

			}

			@Override
			public void putValue(String key, Object value) {
				// TODO Auto-generated method stub

			}

			@Override
			public boolean isEnabled() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public Object getValue(String key) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void addPropertyChangeListener(PropertyChangeListener listener) {
				// TODO Auto-generated method stub

			}
		}

		);
	}
	
	
	
	
	
}
