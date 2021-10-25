package com.ingSoft.simulador;

public interface SubjectPoblacion {
	void atachObserverPoblacion(ObserverPoblacion o);
	void detachObserverPoblacion(ObserverPoblacion o);
	void notifyObserverPoblacion();
}
