package com.ingSoft.simulador;



public class MyThread extends Thread {
	
    private  Simulador s;
    public MyThread (Simulador s) {
		     this.s = s;	     
    }
	public void run() {
		 s.simular();
	}
}
