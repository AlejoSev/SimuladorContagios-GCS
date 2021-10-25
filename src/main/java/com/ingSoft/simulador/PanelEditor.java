package com.ingSoft.simulador;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

public class PanelEditor {

	public PanelEditor() {
		// TODO Auto-generated constructor stub
		try
        {
          // Preparamos la ventana de ejemplo
            JFrame       v     = new JFrame("Help");
            JEditorPane editor = new JEditorPane();
            JScrollPane scroll = new JScrollPane(editor);
            
            v.getContentPane().add(scroll);
            
            
            // Marcamos el editor para que use HTML 
            editor.setContentType("text/html");
            
            // Insertamos un texto
            editor.setText(
            		"<h1>Ventana de ayuda</h1>"+
            		"<h2>Parametros:</h2>"+
            		"<b> - PoblacionTotal: </b>"+
            		"<font face=\"courier\">cantidad de personas total a simular.</font><br>"+
            		"<b> - Poblacion Inicial Infectados: </b>"+
            		"<font face=\"courier\">Porcion de personas infectadas de la total.</font><br>"+
            		"<b> - Tasa de Mortalidad: </b>"+
            		"<font face=\"courier\">Va del 0% al 100%.</font><br>"+
            		"<b> - Tiempo de Incubacion: </b>"+
            		"<font face=\"courier\">Lo que dura la enfermedad en pasos de simulacion.</font><br>"+
            		"<b> - Radio de Contagio: </b>"+
            		"<font face=\"courier\">Alcance de contagio que tiene una persona.</font><br>"+
            		"<b> - Area: </b>"+
            		"<font face=\"courier\">En pixeles al cuadrado.</font><br>"+
            		"<b> - Movilidad </b>"+
            		"<font face=\"courier\">Velocidad de movimiento.</font><br>"+
            		"<b> - Pasos de Simulacion: </b>"+
            		"<font face=\"courier\">Tiempo en la simulacion.</font><br>"+
            		"<b> </b><br>"+
            		"<font color=\"red\">ADVERTENCIA</font><br>"+
            		"<font face=\"courier\">La ventana solo permitira ingresar datos si se rellenan todos los espacios.</font><br>"
            );
            
            // Se visualiza la ventana
            v.pack();
            v.setVisible(true);
      
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
	}

}
