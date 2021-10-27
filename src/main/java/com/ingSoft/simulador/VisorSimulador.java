package com.ingSoft.simulador;

import javax.swing.*;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.List;
import java.util.*;

/**
 * Lienzo es una clase para permitir el dibujo de gr�ficos simples sobre un canvas.
 * Esta es una modificacin de la clase Canvas de prop�sitos generales, desarrollada
 * especialmente para el ejemplo de "formas" en BlueJ.
 *
 * @author: Bruce Quig
 * @author: Michael Kolling (mik)
 *
 * @version 2006.03.30
 * 
 */
public class VisorSimulador
{
    // Nota: La implementaci�n de esta clase (espec�ficamente el manejo de la
    // identidad y los colores de la forma) es levemente m�s compleja que lo 
    // necesario. Esto se ha hecho con el prop�sito de mantener limpias y simples
    // la interface y los campos de las instancias de los objetos forma en este
    // proyecto educativo.

    private static VisorSimulador canvasSingleton;

    /**
     * M�todo Factory para obtener el objeto singleton canvas.
     */
    public static VisorSimulador getVisor()
    {
        if(canvasSingleton == null) {
            canvasSingleton = new VisorSimulador("Visor de simulador", 500, 300);
        }
        canvasSingleton.setVisible(true);
        return canvasSingleton;
    }

    //  ----- instance part -----

    private JFrame frame;
    private CristalPanel panel;
    private Graphics2D grafico;
    private Color colorFondo;
    private Image imagenCanvas;
    private List<Object> objetos;
    private HashMap<Object, DescripcionForma> formas;
    private int ancho, alto;
	private Area area;
    
    /**
     * Crear un Canvas.
     * @param titulo   el titulo a aparecer en el marco del canvas
     * @param ancho    el ancho deseado para el canvas
     * @param altura   la altura deseada para el canvas
     * @param colorFdo el color deseado del fondo del canvas
     */
    private VisorSimulador(String titulo, int ancho, int altura)
    {
    	this.ancho = ancho;
    	this.alto = altura;
        frame  = new JFrame();
        panel = new CristalPanel();
        frame.setContentPane(panel);
        frame.setTitle(titulo);
        panel.setPreferredSize(new Dimension(ancho, altura));
        colorFondo = Color.white;
        frame.pack();
        objetos = new ArrayList<Object>();
        formas  = new HashMap<Object, DescripcionForma>();
    }
	
	public void setArea(Area area) {
		this.area = area;
	}


	public CristalPanel getPanel() {
		return panel;
	}

	/**
     * Establecer la visibilidad del canvas y traer el canvas al frente de
     * la pantalla cuando se haga visible. Este m�todo tambi�n puede ser usado
     * para traer un canvas preexistente al frente de otras ventanas.
     * El valor del @param visible boolean representa la visibilidad deseada del
     * canvas (true or false) 
     */
    public void setVisible(boolean visible)
    {
        if(grafico == null) {
            // primera vez: instancia la imagen de alcance de pantalla y la
            // rellena con el color de fondo.
            Dimension tamano = panel.getSize();
            imagenCanvas = panel.createImage(tamano.width, tamano.height);
            grafico = (Graphics2D)imagenCanvas.getGraphics();
            grafico.setColor(colorFondo);
            grafico.fillRect(0, 0, tamano.width, tamano.height);
            grafico.setColor(Color.black);
        }
        //frame.setVisible(visible);
    }
    
    public void agregarCirculo(Object referenciaObjeto, Color color, int xReal, int yReal, int diametro)
    {
    	int anchoReal = area.getAncho();
    	int altoReal = area.getAlto();
    	float escX = (float)panel.getWidth()/(float)anchoReal;
    	float escY = (float)panel.getHeight()/(float)altoReal;
    	int x = (int)((float)xReal*escX);
    	int y = (int)((float)yReal*escY);
    	objetos.remove(referenciaObjeto);   // solo en caso que ya estuviera all�
        objetos.add(referenciaObjeto);      // agregar al final
        formas.put(referenciaObjeto, new DescripcionForma(new Ellipse2D.Double(x, y, diametro, diametro), color));
    }

 
    /**
     * Borrar de la pantalla una forma dada.
     * @param  referenciaObjeto  el objeto forma object a ser borrado
     */
    public void borrar(Object referenciaObjeto)
    {
        objetos.remove(referenciaObjeto);   // solo en caso de que ya estuviera all�
        formas.remove(referenciaObjeto);
        redibujar();
    }

    /**
     * Establecer el color de fondo del canvas.
     * @param  nuevoColor  el color nuevo para el fondo del canvas. 
     */
    public void setForegroundColor(Color color)
    {
    	grafico.setColor(color);
    }

    /**
     * Redibujar todas las formas actualmente sobre el canvas.
     */
    public void redibujar()
    {
        borrar();
        for(Object forma : objetos) {
            formas.get(forma).dibujar(grafico);
        }
        panel.repaint();
    }
       
    /**
     * Borrar el canvas completo (No repinta).
     */
    private void borrar()
    {
        Color original = grafico.getColor();
        grafico.setColor(colorFondo);
        Dimension tamano = panel.getSize();
        grafico.fill(new Rectangle(0, 0, tamano.width, tamano.height));
        grafico.setColor(original);
    }


    /************************************************************************
     * Clase interior CristalCanvas - el componente actual contenido en el
     * marco del canvas. Este es esencialmente un JPanel con la capacidad 
     * a�adida de refrescar la imagen dibujada sobre el.
     */
    private class CristalPanel extends JPanel
    {
        public void paint(Graphics g)
        {
            g.drawImage(imagenCanvas, 0, 0, null);
        }
    }
    
  
     /************************************************************************
     * Clase interior DescripcionForma 
     */
    private class DescripcionForma
    {
        private Shape  forma;
        private Color color;

        public DescripcionForma(Shape forma, Color color)
        {
            this.forma  = forma;
            this.color = color;
        }

        public void dibujar(Graphics2D grafico)
        {
            setForegroundColor(color);
            grafico.fill(forma);
        }
    }

}
