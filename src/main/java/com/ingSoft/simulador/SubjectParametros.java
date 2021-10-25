package com.ingSoft.simulador;

public interface SubjectParametros {
	void atachObserverParametros(ObserverParametros o);
	void detachObserverParametros(ObserverParametros o);
	void notifyObserverParametros();
}
