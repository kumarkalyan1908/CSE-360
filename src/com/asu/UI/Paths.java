package com.asu.UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextPane;

public class Paths extends JFrame {

	private JPanel contentPane;

	
	/**
	 * Create the frame.
	 */
	public Paths(String paths) {
		setTitle("Paths");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 954, 378);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setText(paths);
		textPane.setBounds(10, 11, 918, 317);
		contentPane.add(textPane);
	}

}
