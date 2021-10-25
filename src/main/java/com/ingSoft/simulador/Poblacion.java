package com.ingSoft.simulador;


import java.util.ArrayList;


public class Poblacion implements SubjectPoblacion {
	private ArrayList<Persona> sanas;
	private ArrayList<Persona> enfermas;
	private ArrayList<Persona> recuperadas;
	private ArrayList<Persona> muertas;
	private Area area;
	
	private ArrayList<ObserverPoblacion> observers;
	
	public Poblacion(Area area, int cantPersonas, int cantEnfermas) {
		this.area = area;
		
		sanas = new ArrayList<Persona>();
		enfermas = new ArrayList<Persona>();
		recuperadas = new ArrayList<Persona>();
		muertas = new ArrayList<Persona>();
		observers = new ArrayList<ObserverPoblacion>();
		
		iniciarSanos(cantPersonas-cantEnfermas);
		iniciarEnfermos(cantEnfermas);
	}
	
	public ArrayList<Persona> getSanas() {
		return sanas;
	}
	public ArrayList<Persona> getEnfermas() {
		return enfermas;
	}
	public ArrayList<Persona> getRecuperadas() {
		return recuperadas;
	}
	public ArrayList<Persona> getMuertas() {
		return muertas;
	}
	public Area getArea() {
		return area;
	}
	public int getCantSanos() {
		return sanas.size();
	}
	public int getCantEnfermos() {
		return enfermas.size();
	}
	public int getCantMuertos() {
		return muertas.size();
	}
	public int getCantRecuperados() {
		return recuperadas.size();
	}
	public int getCantPersonas() {
		return getCantSanos()+getCantEnfermos()+getCantMuertos()+getCantRecuperados();
	}
	
	public void iniciarSanos(int n)
	{
		sanas.clear();
		for(int i=0;i<n;i++)
		{
			sanas.add(new Persona(area));
		}
	}
	
	public void iniciarEnfermos(int n)
	{
		enfermas.clear();
		for(int i=0;i<n;i++)
		{
			enfermas.add(new Persona(area));
		}
	}
	
	
	public void enfermarPersona(Persona p)
	{
		sanas.remove(p);
		enfermas.add(p);
		p.enfermar();
		notifyObserverPoblacion();
	}
	public void morirPersona(Persona p)
	{
		enfermas.remove(p);
		muertas.add(p);
		p.morir();
		notifyObserverPoblacion();
	}
	public void recuperarPersona(Persona p)
	{
		enfermas.remove(p);
		recuperadas.add(p);
		p.recuperar();
		notifyObserverPoblacion();
	}
	
	public void animar()
	{ 
		for(Persona p:sanas) {
			p.calcularVelocidad();
			p.mover();
		}
		for(Persona p:enfermas) {
			p.calcularVelocidad();
			p.mover();
		}
		for(Persona p:recuperadas) {
			p.calcularVelocidad();
			p.mover();
		}
	}

	public void setMortalidad(float mortalidad){
		for (Persona p: enfermas) {
			p.setMortalidad(mortalidad);
		}
		for (Persona p: sanas) {
			p.setMortalidad(mortalidad);
		}
	}
	
	public void setMovilidad(int movilidad) {
		for (Persona p: enfermas) {
			p.setMovilidad(movilidad);
		}
		for (Persona p: sanas) {
			p.setMovilidad(movilidad);
		}
		for (Persona p: recuperadas) {
			p.setMovilidad(movilidad);
		}
	}
	
	public void setDuracionEnfermedad(int duracion) {
		for (Persona p: enfermas) {
			p.setDuracionEnfermedad(duracion);
		}
		for (Persona p: sanas) {
			p.setDuracionEnfermedad(duracion);
		}
		for (Persona p: recuperadas) {
			p.setDuracionEnfermedad(duracion);
		}
	}
	@Override
	public void atachObserverPoblacion(ObserverPoblacion o) {
		observers.add(o);
		
	}
	@Override
	public void detachObserverPoblacion(ObserverPoblacion o) {
		int i = observers.lastIndexOf(o);
		
		if (i>=0) {

			observers.remove(i);
		}
		
	}
	@Override
	public void notifyObserverPoblacion() {
		for(int i =0;i<observers.size();i++)
		{
			ObserverPoblacion observer = (ObserverPoblacion)observers.get(i);
			observer.updatePoblacion();
		}
	}
	
	public ArrayList<ObserverPoblacion> getObserverList() {
		return observers;
	}





}
