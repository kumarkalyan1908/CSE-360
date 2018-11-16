package com.asu.UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.asu.Process.Processor;

import java.awt.GridLayout;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class CriticalPaths extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public CriticalPaths(Processor processor) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		JTextPane textPane = new JTextPane();
		StringBuffer sb = new StringBuffer();
		List<String> mapCritical = processor.getCriticalPaths();
		String list = null;
		for(int i=0;i<mapCritical.size();i++)
		{
			list = mapCritical.get(i);
			
				sb.append(list).append("\n");
		
			
		}
		textPane.setText(sb.toString());
		contentPane.add(textPane);
	}

}
