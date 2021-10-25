package com.ingSoft.simulador;

import java.io.File;
import java.io.FileWriter;

public class LogWriter implements ObserverPoblacion, ObserverParametros {
	private Poblacion poblacion;
	private Simulador simulador;

	public LogWriter(Simulador s) {
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
		return "Parametros seteados: Movilidad: " + simulador.getMovilidad() + ". Mortalidad: "
				+ simulador.getMortalidad();

	}

	public void displayPoblacion() {
		try {
			File file = new File("log.log");
			FileWriter myWriter = new FileWriter(file,true);
			myWriter.write(logPoblacion());
			myWriter.write("\r\n");
			myWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void displayParametros() {
		try {
			File file = new File("log.log");
			FileWriter myWriter = new FileWriter(file,true);
			myWriter.write(logParametros());
			myWriter.write("\r\n");
			myWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updatePoblacion() {
		displayPoblacion();

	}

	@Override
	public void updateParametros() {
		displayParametros();

	}

}
