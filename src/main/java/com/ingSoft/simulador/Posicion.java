package com.ingSoft.simulador;

import java.util.Random;

public class Posicion {
	private int posx;
	private int posy;
	private Area area;

	public Posicion() {
		area = new Area(0,0);
		posx = 0;
		posy = 0;
	}

	public Posicion(Area area) {
		this.area = area;
		Random r = new Random();
		posx = r.nextInt(area.getAncho());
		posy = r.nextInt(area.getAlto());
	}

	public int getPosx() {
		return posx;
	}

	public void setPosx(int posx) {
		this.posx = posx;
	}

	public int getPosy() {
		return posy;
	}

	public void setPosy(int posy) {
		this.posy = posy;
	}

	public void mover(Velocidad vel) {
		if (posx + vel.getVelx() <= area.getAncho() && posx + vel.getVelx() >= 0)
			posx += vel.getVelx();

		if (posy + vel.getVely() <= area.getAlto() && posy + vel.getVely() >= 0)
			posy += vel.getVely();

	}

}
