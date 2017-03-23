package com.SE.STClient.Common;

import java.awt.Color;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTree;

import com.SE.STClient.Main.STClientMain;
import com.SE.STClient.Views.JPanel_SmallMonitor;

public class MyTargetListener implements DropTargetListener {

	@Override
	public void dragEnter(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub
		

	}

	@Override
	public void dragOver(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dropActionChanged(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub
	

	}

	@Override
	public void dragExit(DropTargetEvent dte) {
		// TODO Auto-generated method stub

	}

	@Override
	public void drop(DropTargetDropEvent dtde) {
		// TODO Auto-generated method stub
		Transferable tr = dtde.getTransferable();


		String str = ""; 
		try {
			if (tr.isDataFlavorSupported(DataFlavor.stringFlavor)) {
				str = tr.getTransferData(DataFlavor.stringFlavor).toString();
			}
		}
		 catch(IOException ex)
		 {}
		 catch(UnsupportedFlavorException ex)
		 {}	
		 DropTarget target = (DropTarget) dtde.getSource();
		 
		if (target.getComponent().equals(STClientMain.getInstance().pollingEditorPanel.table_camera)|target.getComponent().equals( STClientMain.getInstance().pollingEditorPanel.scrollPane_camera)) {
			try {
				STClientMain.getInstance().AddCameraToPollingTeam(STClientMain.getInstance().pollingEditorPanel,tr.getTransferData(DataFlavor.stringFlavor).toString());
			} catch (UnsupportedFlavorException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("添加摄像机成功");
		} 
		else 
		{
			JPanel myTargetPanel = (JPanel) target.getComponent();
			if (str != null && str != "") {

				myTargetPanel.setBackground(Color.BLUE);
			}
		}
		
	}
	

}
