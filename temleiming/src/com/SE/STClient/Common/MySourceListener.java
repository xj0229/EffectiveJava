package com.SE.STClient.Common;

import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceContext;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;

public class MySourceListener implements DragSourceListener {

	@Override
	public void dragEnter(DragSourceDragEvent dsde) {
		// TODO Auto-generated method stub
		DragSourceContext context = dsde.getDragSourceContext();  
        int dropAction = dsde.getDropAction();  
        if( ( dropAction & DnDConstants.ACTION_COPY ) != 0 ){  
            context.setCursor( DragSource.DefaultCopyDrop );  
        } else if( ( dropAction & DnDConstants.ACTION_MOVE ) != 0 ){  
            context.setCursor( DragSource.DefaultMoveDrop );  
        } else {  
            context.setCursor( DragSource.DefaultCopyNoDrop );  
        }  

	}

	@Override
	public void dragOver(DragSourceDragEvent dsde) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dropActionChanged(DragSourceDragEvent dsde) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dragExit(DragSourceEvent dse) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dragDropEnd(DragSourceDropEvent dsde) {
		// TODO Auto-generated method stub
		if( dsde.getDropSuccess() ) {  
            int dropAction = dsde.getDropAction();  
            if( dropAction == DnDConstants.ACTION_MOVE ){  
                //System.out.println( "MOVE: remove node" );  
                  
            	}  

			}
		}

}
