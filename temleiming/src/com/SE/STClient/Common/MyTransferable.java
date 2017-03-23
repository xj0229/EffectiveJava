package com.SE.STClient.Common;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.tree.DefaultMutableTreeNode;

class MyTransferable implements Transferable
{
	
	private DefaultMutableTreeNode treeNode;
	
	public MyTransferable(DefaultMutableTreeNode treeNode) {
		// TODO Auto-generated constructor stub
		this.treeNode=treeNode;
	}
	
	static DataFlavor flavors[]={DataFlavor.stringFlavor};

	@Override
	public DataFlavor[] getTransferDataFlavors() {
		// TODO Auto-generated method stub
		return flavors;
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
		// TODO Auto-generated method stub
		return treeNode;
	}
	
	
}