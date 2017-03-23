package com.SE.STClient.Common;

import java.awt.Component;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import com.SE.STClient.Service.EquipmentDataService;

public class MyDefaultTreeCellRenderer extends DefaultTreeCellRenderer  
{
	private static final long serialVersionUID = 1L;

	public MyDefaultTreeCellRenderer() {

	}

	/**
	 * ��д����DefaultTreeCellRenderer�ķ���
	 */
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded,
			boolean leaf, int row, boolean hasFocus) {
		
		Tb_IpcInfo tb_IpcInfoEntity=new Tb_IpcInfo();
		Tb_DecInfo tb_DecInfoEntity=new Tb_DecInfo();
		EquipmentDataService equipmentDataService=new EquipmentDataService();
		try {
			tb_IpcInfoEntity=equipmentDataService.GetAllIPCAndDomeCameraInfo();
			tb_DecInfoEntity=equipmentDataService.GetAllDecoderAndChannelInfo();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 10));
		setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
		// ִ�и���ԭ�Ͳ���
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

		setText(value.toString());

		if (sel) {
			setForeground(getTextSelectionColor());
		} else {
			setForeground(getTextNonSelectionColor());
		}
		Icon LeafIcon = new ImageIcon("src/com/SE/res/green.png");// Ҷ
		setLeafIcon(LeafIcon);
		Icon ClosedIcon = new ImageIcon("src/com/SE/res/685895493857700850small.png");
		setClosedIcon(ClosedIcon);
		Icon OpenedIcon = new ImageIcon("src/com/SE/res/685895493857700850small2.png");
		setOpenIcon(OpenedIcon);
		// �õ�ÿ���ڵ��TreeNode
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;

		// �õ�ÿ���ڵ��text
		String nodeName = node.toString();
		ArrayList<String> noRunningEquip=new ArrayList<String>();
		
		for (int i = 0; i < tb_IpcInfoEntity.getDev_Name().size(); i++) {
			
			if (tb_IpcInfoEntity.getActive().get(i).equals("F")) {
				noRunningEquip.add(tb_IpcInfoEntity.getDev_Name().get(i));
			}
		}
		
		
		for (int i = 0; i < tb_DecInfoEntity.getDev_ID().size(); i++) {
				
				if (tb_DecInfoEntity.getActive().get(i).equals("F")) {
					noRunningEquip.add(tb_DecInfoEntity.getName().get(i));
				}
			}
		
		
		
		for (int i = 0; i < noRunningEquip.size(); i++) {
			if (nodeName.equals(noRunningEquip.get(i))|nodeName.equals("���豸")) {
				this.setIcon(new ImageIcon("src/com/SE/res/red.png"));
				//System.out.println(nodeName);
			}
			
			if (nodeName.equals("����")) {
				this.setIcon(new ImageIcon("src/com/SE/res/m2.jpg"));
			}
			
		}
		return this;

	}

}
