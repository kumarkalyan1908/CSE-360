/*
 *@Author  
 */


package com.asu.UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JFormattedTextField;

public class AboutScreen extends JFrame {

	private JPanel contentPane;

	

	/**
	 * Create the frame.
	 */
	public AboutScreen() {
		setTitle("About");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setToolTipText("About");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JFormattedTextField frmtdtxtfldNetworkChartTool = new JFormattedTextField();
		frmtdtxtfldNetworkChartTool.setText("Network Chart Tool CSE-360 \n Fall 2018");
		frmtdtxtfldNetworkChartTool.setBounds(30, 92, 342, 20);
		contentPane.add(frmtdtxtfldNetworkChartTool);
	}
}
