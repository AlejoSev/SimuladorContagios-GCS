package com.ingSoft.simulador;

public class Log implements ObserverPoblacion, ObserverParametros{
	private Poblacion poblacion;
	private Simulador simulador;

	public Log(Simulador s) {
		simulador = s;
		poblacion = simulador.getPoblacion();
		poblacion.atachObserverPoblacion(this);
		s.atachObserverParametros(this);
	}

	public void setPoblacion(Poblacion p) {
		poblacion = p;
		poblacion.atachObserverPoblacion(this);
	}
	public String logPoblacion() {
		return "Paso: " + simulador.getPasoActual() + ". Sanos: " + poblacion.getCantSanos() + ". Enfermos: "
				+ poblacion.getCantEnfermos() + ". Muertos: " + poblacion.getCantMuertos() + ". Recuperados: "
				+ poblacion.getCantRecuperados();
		
	}
	
	public String logParametros() {
		return "Parametros seteados: Movilidad: " + simulador.getMovilidad() + ". Mortalidad: " + simulador.getMortalidad();
		
	}
	
	public void displayPoblacion() {
		System.out.println(logPoblacion());
	}
	public void displayParametros() {
		System.out.println(logParametros());
	}

	@Override
	public void updatePoblacion() {
		displayPoblacion();

	}@Override
	public void updateParametros() {
		displayParametros();

	}

}
