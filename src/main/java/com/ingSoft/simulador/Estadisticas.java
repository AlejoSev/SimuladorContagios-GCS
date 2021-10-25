package com.ingSoft.simulador;

public class Estadisticas implements ObserverPoblacion {
	private SubjectPoblacion poblacion; 
	public Estadisticas(SubjectPoblacion poblacion) {
		this.poblacion = poblacion;
	}
	void display(int cantSanos, int cantEnfermos, int cantMuertos, int cantRecuperados) {
		System.out.printf("%d %d %d %d",cantSanos,cantEnfermos, cantMuertos, cantRecuperados);
	}

	@Override
	public void updatePoblacion() {
		Poblacion p = (Poblacion)poblacion;
		display(p.getCantSanos(), p.getCantEnfermos(), p.getCantMuertos(), p.getCantRecuperados());
	}

}
