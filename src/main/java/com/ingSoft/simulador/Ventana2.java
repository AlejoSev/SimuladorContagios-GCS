package com.ingSoft.simulador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class Ventana2 implements ActionListener, DocumentListener {

	
private JFrame ventana;

	//Variables de GUI
    private ArrayList<JTextField> whiteSpaces;
    private ArrayList<String>     nombre;
    private FrameGrafico frameGrafico;
    private Simulador simulador;
	
	private ButtonGroup group;
	private JRadioButton rb1;	
	private JRadioButton rb2;
	private JRadioButton rb3;
	private JButton cancelar;
	private JButton aceptar;
	private Container cp; 
	
	private JComboBox  graph;
	private JButton    applyParam,applyGraph;
	private JTextField tm;     //tasa mortalidad
	private JTextField rc;     //radio de contagio 
	private JTextField mov;    //movilidad
	
	
	//Variables de parametros de simulacion
	public static int poblacionTotal;
	public static int pobTotInfectados;
	public static int tasaMortalidad;
	public static int tiempoIncubacion;
	public static int radioContagio;
	public static int areaParam;
	public static int movilidad;
	public static int tiempoSimulacion;
	public String grafico;
	
	public Ventana2() {
        frameGrafico = new FrameGrafico();

	}
	
	//Metodos que permiten determinar si hay cambios en los espacios en blanco
	@Override
	public void changedUpdate(DocumentEvent arg0) {validarDatos();}

	@Override
	public void insertUpdate(DocumentEvent arg0) {validarDatos();}

	@Override
	public void removeUpdate(DocumentEvent arg0) {validarDatos();}
	
    //Metodo que permite deterimar si hay cambio en los botones
	@Override
	public void actionPerformed(ActionEvent e) {
		 if (e.getSource()==aceptar) {
			
	        try {     
                 //Se recogen variables de la GUI
	             poblacionTotal   = Integer.valueOf(whiteSpaces.get(0).getText());
				 pobTotInfectados = Integer.valueOf(whiteSpaces.get(1).getText());
				 tasaMortalidad   = Integer.valueOf(whiteSpaces.get(2).getText());
				 tiempoIncubacion = Integer.valueOf(whiteSpaces.get(3).getText());
				 radioContagio    = Integer.valueOf(whiteSpaces.get(4).getText());
				 areaParam        = Integer.valueOf(whiteSpaces.get(5).getText());
				 movilidad        = Integer.valueOf(whiteSpaces.get(6).getText());
				 tiempoSimulacion = Integer.valueOf(whiteSpaces.get(7).getText());
				 //Variables actualiadas al simulador
				 startSim(poblacionTotal,pobTotInfectados,tasaMortalidad,tiempoIncubacion,radioContagio,areaParam,movilidad,tiempoSimulacion);
				 //Controla que botones se presionaron y actualiza el modelo
				 if(rb1.isSelected()) {
					 frameGrafico.setJChart(new  Histogram(simulador));
				 }
				 else if(rb2.isSelected()) {
					 frameGrafico.setJChart(new  PieChart(simulador));
				 }
				 else if (rb3.isSelected()){
					 frameGrafico.setJChart(new  LineChart(simulador));
				 }
				 
				 ventana.setVisible(false);
				 parameterConfig();
	        }
	        catch(NumberFormatException error) {
	        	JOptionPane.showMessageDialog(null, "Las casillas no pueden estar vacias", "Error", JOptionPane.ERROR_MESSAGE);
	        }  
	     }
		 else if(e.getSource() == cancelar) {
			 ventana.dispatchEvent(new WindowEvent(ventana, WindowEvent.WINDOW_CLOSING));
			 
		 }   //Cambio del modelo en tiempo real
		 else if (e.getSource() == applyGraph){
			 if(graph.getSelectedItem()=="Histogram") {
				 frameGrafico.setJChart(new Histogram(simulador));
			 }
			 else if(graph.getSelectedItem()=="Pie") {
				 frameGrafico.setJChart(new PieChart(simulador));
			 }
			 else if (graph.getSelectedItem()=="Line"){
				 frameGrafico.setJChart(new LineChart(simulador));
			 }
		 }
		 else if(e.getSource() == applyParam) {
			//Setea los nuevos valores
			 simulador.setMortalidad((float)(Integer.valueOf(tm.getText()))*(float)0.01);
			 simulador.setRadioContagio(Integer.valueOf(rc.getText()));
			 simulador.setMovilidad(Integer.valueOf(mov.getText()));
		 }
		 else {
			 System.out.println("Elemento: "+e.getActionCommand());
			 PanelEditor pe = new PanelEditor();
		 }
	}
	    //validar datos
		public void validarDatos() {
			boolean isNull=false;
			try {
			for(int i=0;i<whiteSpaces.size();i++) {
				if(whiteSpaces.get(i).getText().equals("")) {
					isNull = true;
				}
			  }
			}
			catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Las casillas no pueden estar vacias", "Error", JOptionPane.ERROR_MESSAGE);
				isNull=true;
			}
			if(isNull) {
				aceptar.setEnabled(false);
			
			}else {
				aceptar.setEnabled(true);
			}
		}
		//Agregado de listener botones y listener espacios en blanco
		public void addListeners() {
			
			aceptar.addActionListener(this);
			cancelar.addActionListener(this);
	
			for(int i=0; i<whiteSpaces.size();i++) {
				whiteSpaces.get(i).getDocument().addDocumentListener(this);
			}
		}
		//Armado de ventana
		public void armadoVentana() {
			ventana = new JFrame("Nueva Simulacion");
			ventana.setSize(350,400); 
			ventana.setLocationRelativeTo(null);
			ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			JPanel panel = new JPanel();
			GridLayout gl = new GridLayout(8,2,0,10);
			panel.setLayout(gl);
			
			
			JMenuBar barraDeMenu = new JMenuBar();
		    ventana.setJMenuBar(barraDeMenu);
		    
		    JMenu ayuda = new JMenu("Open");
		    barraDeMenu.add(ayuda);
		    
		    JMenuItem elementoAbrir = new JMenuItem("Help");
		    elementoAbrir.addActionListener(this);
		    ayuda.add(elementoAbrir);
		
		
			//Inicializacion de frames, botones y botones redondos
		    for(int i=0;i<8;i++) {
		    	panel.add(new JLabel(nombre.get(i)));
		    	panel.add(whiteSpaces.get(i));
		    }
			
			JPanel panelBoton = new JPanel();
			panelBoton.setLayout(new FlowLayout());
			panelBoton.add(cancelar);
			panelBoton.add(aceptar);
			
			JPanel panelRadio = new JPanel();
			panelRadio.setLayout(new GridLayout(4,1));
			panelRadio.add(new JLabel("Seleccionar:"));
			panelRadio.add(rb1);
			panelRadio.add(rb2);
			panelRadio.add(rb3);
		
			cp = ventana.getContentPane();
			cp.add(panel,BorderLayout.NORTH);
			cp.add(panelRadio,BorderLayout.CENTER);
			cp.add(panelBoton,BorderLayout.SOUTH);
			ventana.setVisible(true);
		}
		
		public static ArrayList<Integer> getParametrosSimulacion(){
			ArrayList<Integer> param = new ArrayList<Integer> ();
			param.add(poblacionTotal);
			param.add(pobTotInfectados);
			param.add(tasaMortalidad);
			param.add(tiempoIncubacion);
			param.add(radioContagio);
			param.add(areaParam);
			param.add(movilidad);
			param.add(tiempoSimulacion);
			
			return param;
		}
		
		public void showVentana() {
			//Inicializacion de espacios en blancos para escritura y nombres de parametros
			whiteSpaces = new ArrayList<JTextField>();
			nombre      = new ArrayList<String>();
		
			nombre.add("Poblacion Total");
			nombre.add("Poblacion Inicial Infectados");
			nombre.add("Tasa de Mortalidad");
			nombre.add("Tiempo de Incubacion");
			nombre.add("Radio de Contagio");
			nombre.add("Area");
			nombre.add("Movilidad");
			nombre.add("Pasos de Simulacion");
			
			for(int i=0; i<8;i++) {
				whiteSpaces.add(new JTextField(10));
			}
			rb1      = new JRadioButton("Histogram");
			rb2      = new JRadioButton("Pie");
			rb3      = new JRadioButton("Line");
			aceptar  = new JButton("Aceptar");
			cancelar = new JButton("Cancelar");
			
			//Listeners de botones y whiteSpaces
			addListeners();
			//Se inicializa con "aceptar" deshabilitado
			aceptar.setEnabled(false);
			//Se aniade a ButtonGroup a los radioButton
			
		    group = new ButtonGroup();
			group.add(rb1);
			group.add(rb2);
			group.add(rb3);
			
			//Se fabrica toda la ventana
			armadoVentana();
		}
		
		//Aca se ejecuta el simulador de contagios
		public  void startSim(int poblacionTotal, int pobTotInfectados, int tasaMortalidad, int tiempoIncubacion, int radioContagio, int areaParam, int movilidad, int tiempoSimulacion) {
			
			//Instancia parametros de simulacion
			Area area   = new Area(areaParam,areaParam);
			Poblacion p = new Poblacion(area,poblacionTotal ,pobTotInfectados);
		    simulador   = new Simulador(area,p);
			Log log     = new Log(simulador);
			LogWriter logwriter = new LogWriter(simulador);
			
			//seteo de parametros del simulador
			simulador.setVisor(VisorSimulador.getVisor());
			simulador.setMortalidad((float)(0.01*tasaMortalidad));
			simulador.setMovilidad(movilidad);
			simulador.setDuracionEnfermedad(tiempoIncubacion);
			simulador.setTiempoSimulacion(tiempoSimulacion);
			simulador.setRadioContagio(radioContagio);
			log.displayPoblacion();
			
		    //visor simulador
			JFrame j1 = new JFrame("Simulacion de Contagios");
		    j1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    j1.setLocation(80,320);
	        j1.setVisible(true);
		    j1.add(simulador.getVisor().getPanel());
		    j1.pack();
     
		    MyThread t = new MyThread(simulador);
	        t.start();  
		}
		
		public void parameterConfig() {
			 String s[] = {"Histogram","Pie","Line"};
			 JFrame ventana = new JFrame();
			 
			 
			 DisplayInfo display = new DisplayInfo(simulador);
			 
		     ventana.setSize(700,100);
		     ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 ventana.setLocation(80,200);
			
			 JPanel panel1 = new JPanel();
			 JPanel panel3 = new JPanel();

			 tm  = new JTextField("",6);
			 rc  = new JTextField("",6);
			 mov = new JTextField("",6);
			 graph = new JComboBox(s);
			 
			 applyParam = new JButton("Params");
			 applyParam.addActionListener(this);
			 applyGraph = new JButton("Graph");
			 applyGraph.addActionListener(this);

			 //creacion de panel 1 y 2
			 panel1.add(tm);
			 panel1.add(rc);
			 panel1.add(mov);
			 panel1.add(graph);
			 panel1.add(applyParam);
			 panel1.add(applyGraph);

			 panel3.add(display.getLabelMortalidad());
			 panel3.add(display.getLabelRadio());
			 panel3.add(display.getLabelMovilidad());
			 panel3.add(display.getLabelSanos());
			 panel3.add(display.getLabelEnfermos());
			 panel3.add(display.getLabelRecuperados());
			 panel3.add(display.getLabelMuertos());

			 ventana.add(panel1,BorderLayout.SOUTH);
			 ventana.add(panel3,BorderLayout.WEST);

			 //ventana.add(histograma,BorderLayout.SOUTH);
			 ventana.setVisible(true);
		}
}
