package com.ingSoft.simulador;

import javax.swing.JLabel;

public class DisplayInfo implements ObserverPoblacion, ObserverParametros {
	private Simulador simulador;
	private Poblacion poblacion;
	JLabel labelSanos, labelEnfermos, labelRecuperados, labelMuertos, labelMortalidad, labelMovilidad, labelRadio;
	
	

	public DisplayInfo(Simulador s) {
		simulador = s;
		poblacion = s.getPoblacion();
		poblacion.atachObserverPoblacion(this);
		simulador.atachObserverParametros(this);
		
		labelSanos = new JLabel();
		labelEnfermos = new JLabel();
		labelRecuperados = new JLabel();
		labelMuertos = new JLabel();
		labelMortalidad = new JLabel();
		labelMovilidad = new JLabel();
		labelRadio = new JLabel();
		
		labelSanos.setText("Sanos: "+poblacion.getCantSanos());
		labelEnfermos.setText("Enfermos: "+poblacion.getCantEnfermos());
		labelRecuperados.setText("Recuperados: "+poblacion.getCantRecuperados());
		labelMuertos.setText("Muertos: "+poblacion.getCantMuertos());
		
		labelMortalidad.setText("Tasa de Mortalidad: "+(int)(simulador.getMortalidad()*100)+"%");
		labelMovilidad.setText("Movilidad: "+simulador.getMovilidad());
		labelRadio.setText("R. Contagio: "+simulador.getRadioContagio());
	}
	
	public JLabel getLabelRadio() {
		return labelRadio;
	}

	public void setLabelRadio(JLabel labelRadio) {
		this.labelRadio = labelRadio;
	}

	public JLabel getLabelSanos() {
		return labelSanos;
	}

	public void setLabelSanos(JLabel labelSanos) {
		this.labelSanos = labelSanos;
	}

	public JLabel getLabelEnfermos() {
		return labelEnfermos;
	}

	public void setLabelEnfermos(JLabel labelEnfermos) {
		this.labelEnfermos = labelEnfermos;
	}

	public JLabel getLabelRecuperados() {
		return labelRecuperados;
	}

	public void setLabelRecuperados(JLabel labelRecuperados) {
		this.labelRecuperados = labelRecuperados;
	}

	public JLabel getLabelMuertos() {
		return labelMuertos;
	}

	public void setLabelMuertos(JLabel labelMuertos) {
		this.labelMuertos = labelMuertos;
	}

	public JLabel getLabelMortalidad() {
		return labelMortalidad;
	}

	public void setLabelMortalidad(JLabel labelMortalidad) {
		this.labelMortalidad = labelMortalidad;
	}

	public JLabel getLabelMovilidad() {
		return labelMovilidad;
	}

	public void setLabelMovilidad(JLabel labelMovilidad) {
		this.labelMovilidad = labelMovilidad;
	}
	
	@Override
	public void updatePoblacion() {
		
		labelSanos.setText("Sanos: "+poblacion.getCantSanos());
		labelEnfermos.setText("Enfermos: "+poblacion.getCantEnfermos());
		labelRecuperados.setText("Recuperados: "+poblacion.getCantRecuperados());
		labelMuertos.setText("Muertos: "+poblacion.getCantMuertos());
		
		
	}

	@Override
	public void updateParametros() {
		labelMortalidad.setText("Tasa de Mortalidad: "+(int)(simulador.getMortalidad()*100)+"%");
		labelMovilidad.setText("Movilidad: "+simulador.getMovilidad());
		labelRadio.setText("R. Contagio: "+simulador.getRadioContagio());
		
	}

}
