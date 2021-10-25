package com.ingSoft.simulador;

import static org.junit.Assert.*;

import org.junit.Test;
public class PosicionTest {
 
	@Test
	public void testPosicionArea() {
		int ancho =10;
		int alto =20;
		Area a = new Area(ancho, alto);
		Posicion p;
		for (int i = 0; i < 100; i++) {
			p = new Posicion(a);
			assertTrue(p.getPosx()>=0 && p.getPosx()<ancho);
			assertTrue(p.getPosy()>=0 && p.getPosy()<alto);
		}
	}

	@Test
	public void testMover() {
		Area a = new Area(100,80);
		Posicion p= new Posicion(a);
		Velocidad v = new Velocidad();
		p.setPosx(0);
		p.setPosy(0);
		v.setVelx(15);
		v.setVely(10);
		p.mover(v);
		
		assertEquals(15, p.getPosx());
		assertEquals(10, p.getPosy());
	}
	
	@Test
	public void testMoverNoDebeExcederLimites() {
		Area a = new Area(100,80);
		Posicion p= new Posicion(a);
		Velocidad v = new Velocidad();
		p.setPosx(0);
		p.setPosy(0);
		v.setVelx(-15);
		v.setVely(-10);
		p.mover(v);
		
		assertEquals(0, p.getPosx());
		assertEquals(0, p.getPosy());
		
		p.setPosx(90);
		p.setPosy(90);
		v.setVelx(20);
		v.setVely(30);
		p.mover(v);
		
		assertEquals(90, p.getPosx());
		assertEquals(90, p.getPosy());
	}

}
