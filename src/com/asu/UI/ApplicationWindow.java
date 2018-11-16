/*
 * @Author Kumar Prabhu Kalyan
 */


package com.asu.UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.asu.Process.Dependency;
import com.asu.Process.Processor;

public class ApplicationWindow {

	private JFrame frmNetworkdiagram;
	private Dependency dependency;
	private Processor processor ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApplicationWindow window = new ApplicationWindow();
					window.frmNetworkdiagram.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ApplicationWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		dependency =null;
		frmNetworkdiagram = new JFrame();
		frmNetworkdiagram.setTitle("NetworkDiagram");
		frmNetworkdiagram.setBounds(100, 100, 495, 538);
		frmNetworkdiagram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNetworkdiagram.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		frmNetworkdiagram.getContentPane().add(panel);
		
		JButton inputButton = new JButton("Input");
		inputButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(null==dependency) {
					dependency = new Dependency();
				}
				AddActivity activity = new AddActivity(dependency);
				activity.setVisible(true);
				
			}
		});
		panel.add(inputButton);
		
		JButton processButton = new JButton("Process");
		processButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(null==dependency) {
				ErrorScreen error =new ErrorScreen("No Input to process");
				error.setVisible(true);
			}else {
				 processor = new Processor(dependency);
				try {
					processor.process();
				} catch (Exception e1) {
					ErrorScreen error = new ErrorScreen(e1.getMessage());
					error.setVisible(true);
				}
			}
				
			}
		});
		panel.add(processButton);
		
		JButton restartButton = new JButton("Restart");
		restartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RestartActivity restart =new RestartActivity(dependency);
				restart.setVisible(true);
			}
		});
		panel.add(restartButton);
		
		JButton aboutButton = new JButton("About");
		aboutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AboutScreen about = new AboutScreen();
				about.setVisible(true);
			}
		});
		panel.add(aboutButton);
		
		JButton helpButton = new JButton("Help");
		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Help help =new Help();
				help.setVisible(true);
			}
		});
		
		JButton btnCriticalpathnodes = new JButton("Show Critical Path Nodes");
		btnCriticalpathnodes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				CriticalPaths crits = new CriticalPaths(processor);
				crits.setVisible(true);
			}
		});
		panel.add(btnCriticalpathnodes);
		
		JButton btnExport = new JButton("Export");
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExportFile export = new ExportFile(processor,dependency);
				export.setVisible(true);
			}
		});
		panel.add(btnExport);
		panel.add(helpButton);
		
		JButton quitButton = new JButton("Quit");
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
				
			}
		});
		quitButton.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(quitButton);
	}

}
