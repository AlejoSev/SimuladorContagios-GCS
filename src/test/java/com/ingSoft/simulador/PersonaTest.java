package com.ingSoft.simulador;

import static org.junit.Assert.*;

import org.junit.Test;
 
public class PersonaTest {

	@Test
	public void testVelocidadCalculaaDebeSerCero() {
		Area a = new Area(100,100);
		Persona p = new Persona(a);
		p.calcularVelocidad();
		assertEquals(0, p.getVel().getVelx());
		assertEquals(0, p.getVel().getVely());
	}
	
	public void testVelocidadCalculadaDebeCumplirLimites() {
		Area a = new Area(100,100);
		Persona p = new Persona(a);
		int m =10;
		p.setMovilidad(m);
		
		for(int i=0;i<100;i++) {
			p.calcularVelocidad();
			assertTrue(p.getVel().getVelx()<=m);
			assertTrue(p.getVel().getVelx()>=-m);
			assertTrue(p.getVel().getVely()<=m);
			assertTrue(p.getVel().getVely()>=-m);
		}
	}

	@Test
	public void testMover() {
		Area a = new Area(100,100);
		Persona p = new Persona(a);
		p.getPos().setPosx(0);
		p.getPos().setPosy(0);
		p.getVel().setVelx(15);
		p.getVel().setVely(10);
		p.mover();
		
		assertEquals(15, p.getPos().getPosx());
		assertEquals(10, p.getPos().getPosy());

	}
	
	@Test
	public void testNoExcederLimitesArea() {
		Area a = new Area(100,100);
		Persona p = new Persona(a);
		p.getPos().setPosx(10);
		p.getPos().setPosy(0);
		p.getVel().setVelx(-15);
		p.getVel().setVely(-10);
		p.mover();
		
		assertEquals(10, p.getPos().getPosx());
		assertEquals(0, p.getPos().getPosy());
		
		p.getPos().setPosx(95);
		p.getPos().setPosy(97);
		p.getVel().setVelx(15);
		p.getVel().setVely(10);
		p.mover();
		
		assertEquals(95, p.getPos().getPosx());
		assertEquals(97, p.getPos().getPosy());

	}

	@Test
	public void testEnfermar() {
		Persona p = new Persona();
		p.enfermar();
		assertEquals(Estados.Enfermo, p.getEstado());
		
	}

	@Test
	public void testEvolucionarEnfermedad() {
		Persona p = new Persona();
		p.setDuracionEnfermedad(100);
		p.evolucionarEnfermedad();
		assertTrue(99 == p.getDuracionEnfermedad());
		
		p.setDuracionEnfermedad(16);
		p.evolucionarEnfermedad();
		assertTrue(15 == p.getDuracionEnfermedad());
	}
	
	@Test
	public void testEvolucionarEnfermedadNoDeberiaSerNegativo() {
		Persona p = new Persona();
		p.setDuracionEnfermedad(0);
		p.evolucionarEnfermedad();
		assertTrue(0 == p.getDuracionEnfermedad());
	}

	@Test
	public void testFinEnfermedad() {
		Persona p = new Persona();
		p.setDuracionEnfermedad(10);
		assertFalse(p.finEnfermedad());
		p.setDuracionEnfermedad(0);
		assertTrue(p.finEnfermedad());
	}

	@Test
	public void testRecuperar() {
		Persona p = new Persona();
		p.recuperar();
		assertEquals(Estados.Recuperado, p.getEstado());
	}

	@Test
	public void testMorir() {
		Persona p = new Persona();
		p.morir();
		assertEquals(Estados.Muerto, p.getEstado());
	}

	@Test
	public void testDebeMorir() {
		Persona p = new Persona();
		p.setMortalidad(1);
		for(int i=0;i<100;i++) {
			assertTrue(p.debeMorir());
		}
		p.setMortalidad(0);
		for(int i=0;i<100;i++) {
			assertFalse(p.debeMorir());
		}
	}
}
