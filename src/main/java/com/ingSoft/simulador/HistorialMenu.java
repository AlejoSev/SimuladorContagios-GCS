package com.ingSoft.simulador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class HistorialMenu implements ActionListener {
	private JFrame ventana,historial;
	private JButton closeButton,enterButton;
	private JPanel closePanel,enterPanel,historialPanel;
	private JTextField enterField;
	private File file;
	private FileReader fr;
	private BufferedReader br;
	private StringBuffer sb;
	private String line;
	private JTextPane editor;
	private int counter = 0;
	
	public void main(JFrame v) {
		this.ventana = v;
		editor = new JTextPane();
		
		file = new File("historial.log");
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			sb = new StringBuffer();
		while((line=br.readLine()) != null) {
			sb.append(line);
			sb.append("\n");
			editor.setText("Todavia no funciona :(");
		}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		historial = new JFrame("Historial de Simulaciones");
		historial.setVisible(true);
		historial.setSize(600,600);
		historial.setLocationRelativeTo(null);
		historial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		historialPanel = new JPanel();
		closePanel = new JPanel();
		enterPanel = new JPanel();
		
		historialPanel.setBackground(Color.gray);
		enterPanel.setBackground(Color.darkGray);
		closePanel.setBackground(Color.darkGray);
		
		closeButton = new JButton("Close");
		enterButton = new JButton("Enter");
		
		enterField = new JTextField(3);
		
		closeButton.addActionListener(this);
		
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10,10,10,10);
		c.gridx = 0; c.gridy = 1;
		
		
		historialPanel.add(editor);
		enterPanel.add(enterField);
		enterPanel.add(enterButton);
		closePanel.add(closeButton);	
		
		historial.add(historialPanel,BorderLayout.CENTER);
		historial.add(enterPanel,BorderLayout.NORTH);
		historial.add(closePanel,BorderLayout.SOUTH);	
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==closeButton) {
			ventana.setVisible(true);
			historial.setVisible(false);
		}
//		else if(e.getSource()==enterButton) {
//			
//		}
	}
	
}
