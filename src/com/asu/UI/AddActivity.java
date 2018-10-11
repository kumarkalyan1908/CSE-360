/*
 * @Author 
 */

package com.asu.UI;



import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.asu.Process.Dependency;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class AddActivity extends JFrame {

	private JPanel contentPane;
	private JTextField activityName;
	private JTextField duration;
	private JTextField dependencies;
    private Dependency dependency ;
    private JLabel lblActivityname;
    private JLabel lblDuration;
    private JLabel lblDependencies;
	/**
	 * Create the frame.
	 */
	public AddActivity(Dependency dependency) {
	
		this.dependency =dependency;
		setTitle("Add Activity");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		activityName = new JTextField();
		
		activityName.setBounds(24, 31, 109, 25);
		contentPane.add(activityName);
		activityName.setColumns(10);
		
		duration = new JTextField();
		
		duration.setBounds(152, 33, 86, 25);
		contentPane.add(duration);
		duration.setColumns(10);
		
		dependencies = new JTextField();
		
		dependencies.setBounds(271, 31, 86, 25);
		contentPane.add(dependencies);
		dependencies.setColumns(10);
		
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String strActivityName = activityName.getText();
				String strDuration = duration.getText();
				String strDependencies = dependencies.getText();
				if(null!=strActivityName  && !strActivityName.trim().isEmpty() && null!=strDuration && !strDuration.trim().isEmpty()  ) {
					try {
					int iDuration = Integer.parseInt(strDuration);
					dependency.putInDurations(strActivityName, iDuration);
					
					if(null==strDependencies || strDependencies.trim().isEmpty())
					{
						dependency.putInStartNodes(strActivityName);
					}
					else {
						String dep[] = strDependencies.split(",");
						int length =dep.length;
						int i=0;
					for(i=0;i<length;i++)
					{
						dependency.putInNextJobs(strActivityName, dep[i].trim());
					}
					}
					}catch(Exception e)
					{
						ErrorScreen error  = new ErrorScreen("Please enter integer/whole number for duration");
						error.setVisible(true);
						
						
					}
						
				}
				
				activityName.setText(null);
				duration.setText(null);
				dependencies.setText(null);
			}
		});
		addButton.setBounds(44, 80, 89, 23);
		contentPane.add(addButton);
		
		JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activityName.setText(null);
				duration.setText(null);
				dependencies.setText(null);
				
			}
		});
		clearButton.setBounds(160, 80, 89, 23);
		contentPane.add(clearButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AddActivity.this.dispose();
			}
		});
		cancelButton.setBounds(271, 80, 89, 23);
		contentPane.add(cancelButton);
		
		lblActivityname = new JLabel("ActivityName");
		lblActivityname.setBounds(44, 11, 76, 14);
		contentPane.add(lblActivityname);
		
		lblDuration = new JLabel("Duration");
		lblDuration.setBounds(164, 11, 46, 14);
		contentPane.add(lblDuration);
		
		lblDependencies = new JLabel("Dependencies");
		lblDependencies.setBounds(286, 11, 71, 14);
		contentPane.add(lblDependencies);
	}
}
