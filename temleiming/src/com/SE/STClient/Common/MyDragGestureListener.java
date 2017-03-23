package com.SE.STClient.Common;

import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class MyDragGestureListener implements DragGestureListener {

	public MySourceListener mySourceListener;
	@Override
	public void dragGestureRecognized(DragGestureEvent dge) {
		// TODO Auto-generated method stub
		mySourceListener=new MySourceListener();
		JTree tree=(JTree)dge.getComponent();
		TreePath path= tree.getSelectionPath();
		
		if (path!=null) {
			DefaultMutableTreeNode selection= (DefaultMutableTreeNode)path.getLastPathComponent();
			if (selection.isLeaf()) {
				MyTransferable dragAndDropTransferable=new  MyTransferable(selection);
				dge.startDrag(DragSource.DefaultCopyDrop, dragAndDropTransferable,mySourceListener );
			}			
		}
	}

}
