package com.ingSoft.simulador;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class Simulador implements SubjectParametros {
	private Poblacion poblacion;
	private Area area;
	private int cantEnfermos;
	private float mortalidad;
	private int duracionEnfermedad;
	private int tiempoIncubacion;
	private int radioContagio;
	private boolean inmunidad;
	private int movilidad;
	private int tiempoSimulacion;
	private VisorSimulador visor;
	private int pasoActual;
	private ArrayList<ObserverParametros> observers;

	public Simulador() {
		radioContagio = 0;
		mortalidad = 0;
		pasoActual = 0;
		observers = new ArrayList<ObserverParametros>();
	}

	public Simulador(Area a, Poblacion p) {
		area = a;
		poblacion = p;
		radioContagio = 0;
		mortalidad = 0;
		pasoActual = 0;
		observers = new ArrayList<ObserverParametros>();
	}

	public int getPasoActual() {
		return pasoActual;
	}

	public VisorSimulador getVisor() {
		return visor;
	}

	public void setVisor(VisorSimulador visor) {
		this.visor = visor;
		visor.setArea(area);
	}

	public Poblacion getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(Poblacion poblacion) {
		this.poblacion = poblacion;
	}

	public int getDuracionEnfermedad() {
		return duracionEnfermedad;
	}

	public void setDuracionEnfermedad(int duracionEnfermedad) {
		this.duracionEnfermedad = duracionEnfermedad;
		poblacion.setDuracionEnfermedad(duracionEnfermedad);
	}

	public int getMovilidad() {
		return movilidad;
	}

	public void setMovilidad(int movilidad) {
		if (movilidad >= 0) {

			this.movilidad = movilidad;
			poblacion.setMovilidad(movilidad);
			notifyObserverParametros();
		}
	}

	public float getMortalidad() {
		return mortalidad;
	}

	public void setMortalidad(float d) {
		this.mortalidad = d;
		poblacion.setMortalidad(d);
		notifyObserverParametros();
	}
	
	public int getTiempoSimulacion() {
		return tiempoSimulacion;
	}

	public void setTiempoSimulacion(int tiempoSimulacion) {
		this.tiempoSimulacion = tiempoSimulacion;
	}

	public int getRadioContagio() {
		return radioContagio;
	}

	public void setRadioContagio(int radioContagio) {
		this.radioContagio = radioContagio;
		notifyObserverParametros();
	}

	public void simular() {
		for (int i = 0; i < tiempoSimulacion; i++) {
			simularUnPaso();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		simulationEnd();
	}

	public void simularUnPaso() {
		pasoActual++;
		poblacion.animar();
		evolucionarEnfermedad();
		morirRecuperar();
		transmitir();
		dibujar();
	}
  
	public void morirRecuperar() {
		ArrayList<Persona> tmpRecuperadas = new ArrayList<Persona>();
		ArrayList<Persona> tmpMuertas = new ArrayList<Persona>();

		for (Persona enferma : poblacion.getEnfermas()) {
			if (enferma.finEnfermedad()) {
				if (enferma.debeMorir())
					tmpMuertas.add(enferma);
				else
					tmpRecuperadas.add(enferma);
			}
		}
		for (Persona enferma : tmpMuertas) {
			poblacion.morirPersona(enferma);
		}
		for (Persona enferma : tmpRecuperadas) {
			poblacion.recuperarPersona(enferma);
		}
	}

	public void transmitir() {
		ArrayList<Persona> tmp = new ArrayList<Persona>();
		for (Persona enferma : poblacion.getEnfermas()) {
			for (Persona sana : poblacion.getSanas()) {
				if (estanCerca(enferma, sana) && !tmp.contains(sana))
					tmp.add(sana);
			}
		}
		for (Persona sana : tmp) {
			poblacion.enfermarPersona(sana);
		}
	}

	public void evolucionarEnfermedad() {
		for (Persona enferma : poblacion.getEnfermas()) {
			enferma.evolucionarEnfermedad();
		}
	}

	public boolean estanCerca(Persona enferma, Persona sana) {
		int dx = enferma.getPos().getPosx() - sana.getPos().getPosx();
		int dy = enferma.getPos().getPosy() - sana.getPos().getPosy();

		return ((dx * dx + dy * dy) <= radioContagio * radioContagio);
	}

	public void dibujar() {
		for (Persona sano : poblacion.getSanas()) {
			visor.agregarCirculo(sano, ColoresEstados.colorSano, sano.getPos().getPosx(), sano.getPos().getPosy(), 10);
		}
		for (Persona enfermo : poblacion.getEnfermas()) {
			visor.agregarCirculo(enfermo, ColoresEstados.colorEnfermo, enfermo.getPos().getPosx(),
					enfermo.getPos().getPosy(), 10);
		}
		for (Persona recuperado : poblacion.getRecuperadas()) {
			visor.agregarCirculo(recuperado, ColoresEstados.colorRecuperado, recuperado.getPos().getPosx(),
					recuperado.getPos().getPosy(), 10);
		}
		for (Persona muerto : poblacion.getMuertas()) {
			visor.agregarCirculo(muerto, ColoresEstados.colorMuerto, muerto.getPos().getPosx(),
					muerto.getPos().getPosy(), 10);
		}
		visor.redibujar();
		try {
			Thread.sleep(10);
		} 
		catch (Exception e) {
		}
	}
	
	@Override
	public void atachObserverParametros(ObserverParametros o) {
		observers.add(o);
	}

	@Override
	public void detachObserverParametros(ObserverParametros o) {
		int i = observers.lastIndexOf(o);
		if (i >= 0) {
			observers.remove(i);
		}
	}
	
	public ArrayList<ObserverParametros> getObserverList() {
		return observers;
	}
	
	@Override
	public void notifyObserverParametros() {
		for (int i = 0; i < observers.size(); i++) {
			ObserverParametros observer = (ObserverParametros) observers.get(i);
			observer.updateParametros();
		}
	}
	
	public void simulationEnd() {
		try {
		File historial = new File("historial.log");
		FileWriter fw = new FileWriter(historial,true);
		
		fw.write("Cantidad de sanos: "+poblacion.getCantSanos());
		fw.write("\r\n");
		fw.write("Cantidad de enfermos: "+poblacion.getCantEnfermos());
		fw.write("\r\n");
		fw.write("Cantidad de muertos: "+poblacion.getCantMuertos());
		fw.write("\r\n");
		fw.write("Cantidad de recuperados: "+poblacion.getCantRecuperados());
		fw.write("\r\n");
		fw.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
