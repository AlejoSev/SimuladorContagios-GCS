package com.ingSoft.simulador;

import org.jfree.chart.*;
import org.jfree.data.category.*;
import org.jfree.chart.plot.*;

public class Histogram implements ObserverPoblacion,GraphicBehaviour{
	
	public  ChartPanel histograma;
	private Poblacion poblacion;
	private DefaultCategoryDataset data;
	private int pobSana,pobEnfermo,pobMuertos,pobRecuperados;
	
	public Histogram(Simulador s){
		
		poblacion      = s.getPoblacion();
		pobSana        = poblacion.getCantSanos();
		pobEnfermo     = poblacion.getCantEnfermos();
		pobMuertos     = poblacion.getCantMuertos();
		pobRecuperados = poblacion.getCantRecuperados();
		
		poblacion.atachObserverPoblacion(this);
		
		data = new DefaultCategoryDataset();
		
		data.addValue(pobSana,"1","Sanas");
		data.addValue(pobEnfermo,"2","Enfermas");
		data.addValue(pobMuertos,"3","Recuperadas");
		data.addValue(pobRecuperados,"4","Muertas");
		
		JFreeChart graph = ChartFactory.createBarChart("", "", "", data, PlotOrientation.VERTICAL, false, true, false);
		histograma = new ChartPanel(graph);
		histograma.setBounds(100,100,10,10);
	}
	
	@Override
	public ChartPanel getPanel() {
		return histograma;
	}
	
	@Override
	public void updatePoblacion() {
		data.setValue(poblacion.getCantSanos(),"1","Sanas");
		data.setValue(poblacion.getCantEnfermos(),"2","Enfermas");
		data.setValue(poblacion.getCantRecuperados(),"3","Recuperadas");
		data.setValue(poblacion.getCantMuertos(),"4","Muertas");
	}
}
