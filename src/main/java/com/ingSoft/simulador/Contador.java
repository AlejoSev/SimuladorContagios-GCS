package com.ingSoft.simulador;

public class Contador implements ObserverPoblacion{
	private int cantSanos;
	private int cantEnfermos;
	private int cantMuertos;
	private int cantRecuperados;
	private Poblacion poblacion;
	public Contador(Poblacion p) {
		poblacion = p;
	}
	@Override
	public void updatePoblacion() {
		cantSanos = poblacion.getCantSanos();
		cantEnfermos = poblacion.getCantEnfermos();
		cantMuertos = poblacion.getCantMuertos();
		cantRecuperados = poblacion.getCantRecuperados();
		display();
		
	}
	
	public void display()
	{
		
	}

}
