package com.ingSoft.simulador;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class PoblacionTest {

	@Test
	public void testIniciarSanas() {
		Area a = new Area(100,100);
		Poblacion p = new Poblacion(a,0,0);
		p.iniciarSanos(0);
		assertEquals(0, p.getSanas().size());
		p.iniciarSanos(100);
		assertEquals(100, p.getSanas().size());
	}

	@Test
	public void testIniciarEnfermos() {
		Area a = new Area(100,100);
		Poblacion p = new Poblacion(a,0,0);
		p.iniciarEnfermos(0);
		assertEquals(0, p.getEnfermas().size());
		p.iniciarEnfermos(100);
		assertEquals(100, p.getEnfermas().size());
	}

	@Test
	public void testEnfermarPersona() {
		Area a = new Area(100,100);
		Poblacion p = new Poblacion(a,100,0);
		Persona per = p.getSanas().get(1);
		p.enfermarPersona(per);
		assertEquals(Estados.Enfermo, per.getEstado());
		assertEquals(1, p.getEnfermas().size());
		assertEquals(99, p.getSanas().size());
		
		per = p.getSanas().get(1);
		p.enfermarPersona(per);
		assertEquals(Estados.Enfermo, per.getEstado());
		assertEquals(2, p.getEnfermas().size());
		assertEquals(98, p.getSanas().size());
	}

	@Test
	public void testMorirPersona() {
		Area a = new Area(100,100);
		Poblacion p = new Poblacion(a,100,10);
		Persona per = p.getEnfermas().get(1);
		p.morirPersona(per);
		assertEquals(Estados.Muerto, per.getEstado());
		assertEquals(1, p.getMuertas().size());
		assertEquals(9, p.getEnfermas().size());
		assertEquals(90, p.getSanas().size());
		
		per = p.getEnfermas().get(1);
		p.morirPersona(per);
		assertEquals(Estados.Muerto, per.getEstado());
		assertEquals(2, p.getMuertas().size());
		assertEquals(8, p.getEnfermas().size());
		assertEquals(90, p.getSanas().size());
	}

	@Test
	public void testRecuperarPersona() {
		Area a = new Area(100,100);
		Poblacion p = new Poblacion(a,100,10);
		Persona per = p.getEnfermas().get(1);
		p.recuperarPersona(per);
		assertEquals(Estados.Recuperado, per.getEstado());
		assertEquals(1, p.getRecuperadas().size());
		assertEquals(9, p.getEnfermas().size());
		assertEquals(90, p.getSanas().size());
		
		per = p.getEnfermas().get(1);
		p.recuperarPersona(per);
		assertEquals(Estados.Recuperado, per.getEstado());
		assertEquals(2, p.getRecuperadas().size());
		assertEquals(8, p.getEnfermas().size());
		assertEquals(90, p.getSanas().size());
	}

	@Test
	public void testSetMovilidad() {
		Area a = new Area(100,100);
		Poblacion p = new Poblacion(a,100,10);
		int m = 10;
		p.setMovilidad(m);
		for (Persona per: p.getSanas()) {
			assertEquals(m, per.getMovilidad());
		}
		for (Persona per: p.getEnfermas()) {
			assertEquals(m, per.getMovilidad());
		}
		for (Persona per: p.getRecuperadas()) {
			assertEquals(m, per.getMovilidad());
		}
		
		m = 0;
		p.setMovilidad(m);
		for (Persona per: p.getSanas()) {
			assertEquals(m, per.getMovilidad());
		}
		for (Persona per: p.getEnfermas()) {
			assertEquals(m, per.getMovilidad());
		}
		for (Persona per: p.getRecuperadas()) {
			assertEquals(m, per.getMovilidad());
		}
	}
	
	@Test
	public void testSetMortalidad() {
		Area a = new Area(100,100);
		Poblacion p = new Poblacion(a,100,10);
		float m = (float) 0.1;
		p.setMortalidad(m);
		for (Persona per: p.getSanas()) {
			assertTrue(m== per.getMortalidad());
		}
		for (Persona per: p.getEnfermas()) {
			assertTrue(m== per.getMortalidad());
		}
		for (Persona per: p.getRecuperadas()) {
			assertTrue(m== per.getMortalidad());
		}
		
		m = (float) 0.3;
		p.setMortalidad(m);
		for (Persona per: p.getSanas()) {
			assertTrue(m== per.getMortalidad());
		}
		for (Persona per: p.getEnfermas()) {
			assertTrue(m== per.getMortalidad());
		}
		for (Persona per: p.getRecuperadas()) {
			assertTrue(m== per.getMortalidad());
		}
		
	}
	
	@Test
	public void testArea() {
		Area a = new Area(10,10);
		Poblacion p = new Poblacion(a,100,10);
		
		assertEquals(a, p.getArea());
		System.out.println("test Area");
	}
	
	@Test
	public void testCant() {
		int cantPersonas = 100;
		int cantEnfermos = 80;
		Area a = new Area(100, 100);
		Poblacion p = new Poblacion(a, cantPersonas, cantEnfermos);
		
		assertEquals((cantPersonas - cantEnfermos), p.getCantSanos());
		assertEquals(cantEnfermos, p.getCantEnfermos());
		assertEquals(0, p.getCantMuertos());
		assertEquals(0, p.getCantRecuperados());
		assertEquals(cantPersonas, p.getCantPersonas());
		
		System.out.println("test Cant");
				
	}
	
	@Test
	public void testAnimar() {
		
		int cantPersonas = 100;
		int cantEnfermos = 80;
	
		Area a = new Area(100, 100);
		Poblacion p = new Poblacion(a, cantPersonas, cantEnfermos);
		
		Persona per = p.getEnfermas().get(1);
		p.recuperarPersona(per);
		
		ArrayList<Persona> sanas = p.getSanas();
		ArrayList<Persona> enfermas = p.getEnfermas();
		ArrayList<Persona> recuperadas = p.getRecuperadas();

		Long testVelXSanas = Long.valueOf(sanas.get(0).getVel().getVelx());
		Long testPosXSanas = Long.valueOf(sanas.get(0).getPos().getPosx());

		Long testVelXEnf = Long.valueOf(enfermas.get(0).getVel().getVelx());
		Long testPosXEnf = Long.valueOf(enfermas.get(0).getPos().getPosx());

		Long testVelXRec = Long.valueOf(recuperadas.get(0).getVel().getVelx());
		Long testPosXRec = Long.valueOf(recuperadas.get(0).getPos().getPosx());
		
		System.out.println("testvelXsanas = "+ testVelXSanas + " testPosXSanas = "+testPosXSanas );
		
		p.animar();
		
		Long newVelXSanas = Long.valueOf(p.getSanas().get(0).getVel().getVelx());
		Long newPosXSanas = Long.valueOf(p.getSanas().get(0).getPos().getPosx());

		System.out.println("newVelXSanas = "+ newVelXSanas + " newPosXSanas = "+newPosXSanas );
		Long newVelXEnf = Long.valueOf(p.getEnfermas().get(0).getVel().getVelx());
		Long newPosXEnf = Long.valueOf(p.getEnfermas().get(0).getPos().getPosx());

		Long newVelXRec = Long.valueOf(p.getRecuperadas().get(0).getVel().getVelx());
		Long newPosXRec = Long.valueOf(p.getRecuperadas().get(0).getPos().getPosx());

		assertTrue(newVelXSanas >= testVelXSanas);
		assertTrue(newPosXSanas >= testPosXSanas);

		assertTrue(newVelXEnf >= testVelXEnf);
		assertTrue(newPosXEnf >= testPosXEnf);
		
		assertTrue(newVelXRec >= testVelXRec);
		assertTrue(newPosXRec >= testPosXRec);
		System.out.println("animar");
		
	}
	
	// @Test
	// public void testDuracionEnfermedad() {
		
	// 	//El valor de duracioEnfermedad deberia disminuir para personas enfermas con la simulacion
	// 	int cantPersonas = 100;
	// 	int cantEnfermos = 80;
		
	// 	Area a = new Area(100, 100);
	// 	Poblacion p = new Poblacion(a, cantPersonas, cantEnfermos);
	// 	Simulador s = new Simulador(a, p);
	// 	ArrayList<Persona> enfermas = p.getEnfermas();
		
		
	// 	p.setDuracionEnfermedad(100);
		
	// 	s.setVisor(VisorSimulador.getVisor());
		
	// 	s.simularUnPaso();
		
	// 	Long enfermasDuracion = Long.valueOf(enfermas.get(0).getDuracionEnfermedad());
		
	// 	System.out.println("enfermasDuracion = "+ enfermasDuracion);

	// 	assertTrue(100 > enfermasDuracion);
	
	// }
	
	@Test
	public void testObserver() {
		
		int cantPersonas = 100;
		int cantEnfermos = 80;
		
		Area a = new Area(100, 100);
		Poblacion p = new Poblacion(a, cantPersonas, cantEnfermos);
		Simulador s = new Simulador(a, p);
		
		//Create and attach observer
		ObserverPoblacion obHist= new Histogram(s);
		ObserverPoblacion obLineChart = new LineChart(s);
		
		ArrayList<ObserverPoblacion> obseverList = p.getObserverList();
	
		assertEquals(obHist, obseverList.get(0));
		
		//detach every observer from list
		p.detachObserverPoblacion(obHist);
		p.detachObserverPoblacion(obLineChart);
	
		ArrayList<ObserverPoblacion> obseverListPost = p.getObserverList();
		System.out.println("observer size = "+ obseverListPost.size());
		assertEquals(obseverListPost.size(), 0);
		
		//Create observer without attachment
		
		ObserverPoblacion obCont = new Contador(p);
		p.detachObserverPoblacion(obCont);
		System.out.println("observer size = "+ obseverListPost.size());
		assertEquals(obseverListPost.size(), 0);
		
		
	}

}
