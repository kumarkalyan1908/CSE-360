package com.asu.UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.asu.Process.Dependency;
import com.asu.Process.Processor;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.awt.event.ActionEvent;

public class ExportFile extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExportFile frame = new ExportFile();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	 */

	/**
	 * Create the frame.
	 */
	public ExportFile(Processor processor, Dependency dependency) {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Export File");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(168, 11, 78, 20);
		contentPane.add(formattedTextField);
		
		JLabel lblEnterFileName = new JLabel("Enter File Name");
		lblEnterFileName.setBounds(71, 14, 110, 14);
		contentPane.add(lblEnterFileName);
		
		JButton btnExportNow = new JButton("Export Now");
		btnExportNow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String fileName = formattedTextField.getText();
				
				if( null==fileName || fileName.equals("") )
				{
					ErrorScreen error = new ErrorScreen("Enter valid FileName");
					error.setVisible(true);
				}
				else {
					
					String path = "C:\\Users\\kumar\\Documents\\CSE360 files\\" ;
					
					PrintWriter writer;
					try {
						writer = new PrintWriter(path+fileName+".txt", "UTF-8");
						writer.println("Network Diagram");
						writer.println("\n");
						writer.println("\n");
						DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
						Date date = new Date();
						writer.println(dateFormat.format(date));
						Map<String,Integer> mapDur = dependency.getDurations();
						Set<String> activities = mapDur.keySet();
						writer.println("\n");
						writer.println("\n");
						List<String>  act = new ArrayList<>(activities);
						Collections.sort(act);
						
						writer.println("Activities");
						writer.print("\n");
						for(int i=0;i<act.size();i++)
						{
							writer.println(act.get(i)+"-->"+mapDur.get(act.get(i)));
						}
						writer.println("\n");
						writer.println("\n");
						writer.println("\n");
						writer.println("\n");
						
						List<String> finalpaths = processor.getPathlistFinal();
						
						writer.println("Paths");
						writer.println("\n");
						writer.println("\n");
						
						for(int i=0;i<finalpaths.size();i++)
						{
							writer.println(finalpaths.get(i));
							
						}
						
						
					//	writer.println(processor.getPathsFinal());
						
						writer.close();
					} catch (FileNotFoundException | UnsupportedEncodingException e1) {
						
						e1.printStackTrace();
					}
					
				}
				
			}
		});
		btnExportNow.setBounds(157, 52, 123, 23);
		contentPane.add(btnExportNow);
	}
}
