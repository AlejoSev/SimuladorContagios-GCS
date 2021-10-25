package com.ingSoft.simulador;

import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

public class FrameGrafico extends JFrame{

	public FrameGrafico() {
		this.setLocation(600,200);
		setTitle("Grafico Dinamico");
	}
	
	public void setJChart(GraphicBehaviour chart) {
		this.setVisible(true);
		getContentPane().removeAll();
		add(chart.getPanel());
		pack();
		repaint();;
	}
}
