package com.ingSoft.simulador;

import java.util.Random;

public class Persona {
	private Posicion pos;
	private Velocidad vel;
	private Estados estado;
	private int movilidad;
	private int duracionEnfermedad;
	private float mortalidad;
	
	public Persona() {
		pos = new Posicion();
		vel = new Velocidad();
		movilidad = 0;
		duracionEnfermedad = 0;
		mortalidad = 0;
		estado = Estados.Sano;
	}
	public Persona(Area area) {
		pos = new Posicion(area);
		vel = new Velocidad();
		movilidad = 0;
		duracionEnfermedad = 0;
		mortalidad = 0;
		estado = Estados.Sano;
		
	}

	public int getDuracionEnfermedad() {
		return duracionEnfermedad;
	}

	public void setDuracionEnfermedad(int duracionEnfermedad) {
		this.duracionEnfermedad = duracionEnfermedad;
	}

	public float getMortalidad() {
		return mortalidad;
	}

	public Posicion getPos() {
		return pos;
	}

	public void setPos(Posicion pos) {
		this.pos = pos;
	}

	public Velocidad getVel() {
		return vel;
	}

	public void setVel(Velocidad vel) {
		this.vel = vel;
	}

	public Estados getEstado() {
		return estado;
	}

	public void setEstado(Estados estado) {
		this.estado = estado;
	}

	public int getMovilidad() {
		return movilidad;
	}

	public void setMovilidad(int movilidad) {
		this.movilidad = movilidad;
	}

	public void calcularVelocidad() {
		Random r = new Random();
		vel.setVelx(r.nextInt(2 * movilidad + 1) - movilidad);
		vel.setVely(r.nextInt(2 * movilidad + 1) - movilidad);
	}

	public void mover() {
		pos.mover(vel);
	}

	public void enfermar() {
		this.estado = Estados.Enfermo;
	}

	public void evolucionarEnfermedad() {
		if (duracionEnfermedad > 0)
			duracionEnfermedad--;
	}

	public boolean finEnfermedad() {
		return duracionEnfermedad == 0;
	}

	public void recuperar() {
		this.estado = Estados.Recuperado;
	}

	public void morir() {
		this.estado = Estados.Muerto;
	}

	public boolean debeMorir() {
		Random r = new Random();
		return r.nextFloat() < mortalidad;
	}

	public void setMortalidad(float mortalidad) {
		this.mortalidad = mortalidad;
	}

}
