package com.ingSoft.simulador;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChart implements ObserverPoblacion {
	DefaultCategoryDataset dataset;
	Simulador simulador;
	Poblacion p;
	ChartPanel panel;

	public LineChart(Simulador s) {
		simulador = s;
		p = s.getPoblacion();
		p.atachObserverPoblacion(this);

		dataset = new DefaultCategoryDataset();
		dataset.addValue(p.getCantSanos(), "Sanos", "0");
		dataset.addValue(p.getCantEnfermos(), "Enfermos", "0");
		dataset.addValue(p.getCantRecuperados(), "Recuperados", "0");
		dataset.addValue(p.getCantMuertos(), "Muertos", "0");

		JFreeChart chart = ChartFactory.createLineChart("Repeticion de randoms", "", "", dataset,
				PlotOrientation.VERTICAL, true, true, true);
		panel = new ChartPanel(chart);

		JFrame frame = new JFrame("CrearGraficos - LineaDeCodigo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);

	}

	public ChartPanel getPanel() {
		return panel;
	}

	@Override
	public void updatePoblacion() {
		int i = simulador.getPasoActual();
		dataset.addValue(p.getCantSanos(), "Sanos", "" + i);
		dataset.addValue(p.getCantEnfermos(), "Enfermos", "" + i);
		dataset.addValue(p.getCantRecuperados(), "Recuperados", "" + i);
		dataset.addValue(p.getCantMuertos(), "Muertos", "" + i);
	}

}