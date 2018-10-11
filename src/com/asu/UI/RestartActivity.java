package com.asu.UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.asu.Process.Dependency;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RestartActivity extends JFrame {

	private JPanel contentPane;

	private Dependency dependency;
	public RestartActivity(Dependency dependency) {
		
		this.dependency =dependency;
		setTitle("RestartActivity");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane txtpnAreYouSure = new JTextPane();
		txtpnAreYouSure.setText("Are you sure you want to delete the existing network chart ?");
		txtpnAreYouSure.setBounds(38, 63, 361, 82);
		contentPane.add(txtpnAreYouSure);
		
		JButton confirmButton = new JButton("Confirm");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(null!=dependency) {
				dependency.clearAll();
				
				}
				RestartActivity.this.dispose();
			}
		});
		confirmButton.setBounds(137, 175, 89, 23);
		contentPane.add(confirmButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			RestartActivity.this.dispose();
			}
		});
		cancelButton.setBounds(269, 175, 89, 23);
		contentPane.add(cancelButton);
	}
}
