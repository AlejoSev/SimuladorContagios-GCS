package com.ingSoft.simulador;


import static org.junit.Assert.*;
import javax.swing.*;
import java.util.ArrayList;
import org.junit.Test;

public class HistorialMenuTest {

	@Test
	public void testMain() {
		JFrame frame = new JFrame();
		HistorialMenu hm = new HistorialMenu();
		
		hm.main(frame);
		System.out.println("historial Menu");
		
	}
	
	
	
}
