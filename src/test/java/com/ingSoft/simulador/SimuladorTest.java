package com.ingSoft.simulador;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
public class SimuladorTest {

	@Test
	public void testTodoseberianMorir() {
		Area a = new Area(10,10);
		Poblacion p= new Poblacion(a,10,10);
		Simulador s = new Simulador(a,p);
		for(Persona enferma: p.getEnfermas()) {
			enferma.setDuracionEnfermedad(0);
			enferma.setMortalidad(1);
		}
		s.morirRecuperar();
		assertEquals(0,p.getEnfermas().size());
		assertEquals(10,p.getMuertas().size());
		assertEquals(0,p.getRecuperadas().size());
		//System.out.println("Todos deberian morir\n");
	}
	
	@Test
	public void testTodoseberianRecuperarse() {
		Area a = new Area(10,10);
		Poblacion p= new Poblacion(a,10,10);
		Simulador s = new Simulador(a,p);
		for(Persona enferma: p.getEnfermas()) {
			enferma.setDuracionEnfermedad(0);
			enferma.setMortalidad(0);
		}
		s.morirRecuperar();
		assertEquals(0,p.getEnfermas().size());
		assertEquals(0,p.getMuertas().size());
		assertEquals(10,p.getRecuperadas().size());
	}

	@Test
	public void testTransmitirATodos() {
		// fail("Not yet implemented");
		Area a = new Area(10,10);
		Poblacion p= new Poblacion(a,10,1);
		Simulador s = new Simulador(a,p);
		
		s.setRadioContagio(100);
		s.transmitir();
		
		assertTrue(p.getSanas().size()==0);
		assertTrue(p.getEnfermas().size()==10);
	}
	
	@Test
	public void testTransmitirANadie() {
		// fail("Not yet implemented");
		Area a = new Area(10,10);
		Poblacion p= new Poblacion(a,10,1);
		Simulador s = new Simulador(a,p);
		
		s.setRadioContagio(5);
		p.getEnfermas().get(0).getPos().setPosx(0);
		p.getEnfermas().get(0).getPos().setPosy(0);
		for(Persona sana: p.getSanas()) {
			sana.getPos().setPosx(10);
			sana.getPos().setPosy(10);
		}
		s.transmitir();
		
		assertTrue(p.getSanas().size()==9);
		assertTrue(p.getEnfermas().size()==1);
	}

	@Test
	public void testEstanCerca() {
		Persona p1 = new Persona();
		Persona p2 = new Persona();
		p1.getPos().setPosx(0);
		p1.getPos().setPosy(0);

		p2.getPos().setPosx(5);
		p2.getPos().setPosy(0);

		Simulador s = new Simulador();

		s.setRadioContagio(5);
		assertTrue(s.estanCerca(p1, p2));
		s.setRadioContagio(100);
		assertTrue(s.estanCerca(p1, p2));
		s.setRadioContagio(0);
		assertFalse(s.estanCerca(p1, p2));

	}

	@Test
	public void testDibujar() {
		// fail("Not yet implemented");
	}
   @Test
    public void testGetPasoActual(){
        Simulador s = new Simulador();

        assertTrue(s.getPasoActual() == 0);
        
        System.out.println("testGetPasoActual");
    }
   
   @Test
   public void testVisor(){
       Simulador s = new Simulador();
       s.setVisor(VisorSimulador.getVisor());

       assertEquals(VisorSimulador.getVisor(), s.getVisor());
   }
   
   @Test
   public void testPoblacion(){
       Area a = new Area(10, 10);
       Poblacion p = new Poblacion(a, 100, 20);
       Simulador s = new Simulador();

       s.setPoblacion(p);

       assertEquals(p, s.getPoblacion());
   }
@Test
   public void testDuracionEnfermedad(){
	   int cantPersonas = 100;
	   int cantEnfermos = 80;
	   Area a = new Area(100, 100);
	   Poblacion p = new Poblacion(a, cantPersonas, cantEnfermos);
	   Simulador s = new Simulador(a, p);

       s.setDuracionEnfermedad(120);
       System.out.println("duracionEnfermedad = " + s.getDuracionEnfermedad());

      // assertTrue(s.getDuracionEnfermedad() == 120);
   }

   @Test
   public void testMovilidad(){
	   
	   int cantPersonas = 100;
	   int cantEnfermos = 80;
	   Area a = new Area(100, 100);
	   Poblacion p = new Poblacion(a, cantPersonas, cantEnfermos);
       Simulador s = new Simulador(a, p);

       s.setMovilidad(30);
       
       System.out.println("s.getMovilidad() "+ s.getMovilidad());
      
   }

   @Test
   public void testMortalidad(){
       
	   int cantPersonas = 100;
	   int cantEnfermos = 80;
	   float tasa = 0.5f;
	   Area a = new Area(100, 100);
	   Poblacion p = new Poblacion(a, cantPersonas, cantEnfermos);
       Simulador s = new Simulador(a, p);
       

       s.setMortalidad(tasa);

       assertTrue(s.getMortalidad() == tasa);
   }

   @Test
   public void testTiempoSimulacion(){
       Simulador s = new Simulador();

       s.setTiempoSimulacion(200);

       assertTrue(s.getTiempoSimulacion() == 200);
   }

   @Test
   public void testRadioContagio(){
       Simulador s = new Simulador();

       s.setRadioContagio(25);

       assertTrue(s.getRadioContagio() == 25);
   }
   
   @Test
	public void testObserver() {
		
		int cantPersonas = 100;
		int cantEnfermos = 80;
		
		Area a = new Area(100, 100);
		Poblacion p = new Poblacion(a, cantPersonas, cantEnfermos);
		Simulador s = new Simulador(a, p);
		
		//Create and attach observer
		ObserverParametros log = new Log(s);
		//s.notifyObserverParametros();
		ArrayList<ObserverParametros> obseverList = s.getObserverList();
	
		assertEquals(log, obseverList.get(0));
		
		//detach every observer from list
		s.detachObserverParametros(log);
		
	
		ArrayList<ObserverParametros> obseverListPost = s.getObserverList();
		System.out.println("observer size = "+ obseverListPost.size());
		assertEquals(obseverListPost.size(), 0);
		
		//detach twice 
		s.detachObserverParametros(log);
		s.detachObserverParametros(log);
		assertEquals(obseverListPost.size(), 0);
		
	}
   
   @Test
   public void simularTest() {
		//El valor de duracioEnfermedad deberia disminuir para personas enfermas con la simulacion
		int cantPersonas = 100;
		int cantEnfermos = 80;
		int tasaMortalidad = 10, movilidad = 10, tiempoIncubacion = 1, tiempoSim = 10, radioContagio = 1; 
		
		int pasoInit;
		
		Area a = new Area(100, 100);
		Poblacion p = new Poblacion(a, cantPersonas, cantEnfermos);
		Simulador s = new Simulador(a, p);
		s.setVisor(VisorSimulador.getVisor());
		s.setMortalidad((float)(0.01*tasaMortalidad));
		s.setMovilidad(movilidad);
		s.setDuracionEnfermedad(tiempoIncubacion);
		s.setTiempoSimulacion(tiempoSim);
		s.setRadioContagio(radioContagio);
		
	
		pasoInit = s.getPasoActual();
		
		s.simular();

		assertTrue(s.getPasoActual() > pasoInit);
   }

}
