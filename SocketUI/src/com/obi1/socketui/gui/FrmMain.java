package com.obi1.socketui.gui;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import java.awt.SystemColor;
import javax.swing.JInternalFrame;
import javax.swing.JToolBar;

public class FrmMain extends JFrame {
	public FrmMain() {
		
		JSplitPane splitPane = new JSplitPane();
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);
		
		JTree tree = new JTree();
		scrollPane.setViewportView(tree);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		scrollPane.setColumnHeaderView(toolBar);
		
		JButton btnX = new JButton("X");
		toolBar.add(btnX);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		splitPane.setRightComponent(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("New tab", null, panel, null);
	}
}
