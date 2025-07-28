package GUI.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Controller.ControllerClientes;
import Controller.ControllerPedidos;
import Controller.ControllerProductos;
import Controller.ControllerProveedores;
//Clase que sirve para acceder a las funciones de los subsistemas de la tienda. 
//Se crearán los botones de estas funciones correspondientes, el campo de búsqueda principal, el borde de la tabla principal y 
//se llamarán a los controllers asociados a la tienda,
//además de instanciar las demás imágenes y métodos que conforman la ventana de información de los subsistemas.
public class TableWindow  extends MyWindow{
	private static final long serialVersionUID = 1L;
	protected JLayeredPane layeredPanel;
	protected JPanel tablePanel; 
	protected JButton botonAlta;
	protected JButton botonBaja;
	protected JButton botonModificacion;
	protected JButton botonConsulta;
	protected JButton botonBusqueda;
	protected JLabel borderTable;
	protected int tableWidth;
	protected int tableHeight;
	protected JPanel buttonsPanel; 
	protected JTextField busquedaField;
	protected ControllerClientes ctrlCl;
	protected ControllerPedidos ctrlPd;
	protected ControllerProductos ctrlProd;
	protected ControllerProveedores ctrlProv;
	protected int consultaValue;
	private JPanel buttonsPanel2;
	private ImageIcon imageIcon3;
	private ImageIcon imageIcon4;
	private ImageIcon imageIcon5;
	private ImageIcon imageIcon6;
	private JLabel imageLabel;
	
	public TableWindow(String name, ControllerClientes ctrlCl, ControllerPedidos ctrlPd, ControllerProductos ctrlProd,
			ControllerProveedores ctrlProv) {
		super(name);
		this.setSize(this.actualWidth, this.actualHeight);
		this.tableWidth = 930;
		this.tableHeight = 475;
		this.ctrlCl = ctrlCl;
		this.ctrlPd = ctrlPd;
		this.ctrlProd = ctrlProd;
		this.ctrlProv = ctrlProv;
		this.consultaValue = 0;
		initTableGUI();
    }
	private void initTableGUI() {
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		layeredPanel = new JLayeredPane();
		data.setLayout(new GridLayout(1, 1, 0, 0));
		data.setSize(230, 30);		
        Image image = codaIcon.getImage().getScaledInstance(200, 155, java.awt.Image.SCALE_SMOOTH);
		codaIcon = new ImageIcon(image);
	    imageLabel = new JLabel(codaIcon);
	    imageLabel.setOpaque(true);
	    imageLabel.setBackground(new Color(0,0,0,0));
	    imageLabel.setSize(200, 155);
		tablePanel = new JPanel();
		tablePanel.setSize(tableWidth, tableHeight);
		borderTable = new JLabel();
		borderTable.setSize(1110, 545);
		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(1, 2, 30, 0));
		buttonsPanel.setBackground(new Color(0,0,0,0));
		buttonsPanel.setSize(50, 67);

		buttonsPanel2 = new JPanel();
		buttonsPanel2.setLayout(new GridLayout(1, 3, 10, 0));
		buttonsPanel2.setBackground(new Color(1,3,14,0));
		buttonsPanel2.setOpaque(true);
		buttonsPanel2.setSize(110, 28);
		
		busquedaField = new JTextField();
		setMaxCharacters(busquedaField, 15);
		setJTextFieldProperties(busquedaField);
		selectField(busquedaField);
		data.add(busquedaField);
		
		ImageIcon borderIcon = new ImageIcon("resources/icons/Marco_borde3.png");
		Image auxBorder = borderIcon.getImage().getScaledInstance(1110, 545, java.awt.Image.SCALE_SMOOTH);
		borderIcon = new ImageIcon(auxBorder);
		borderTable.setIcon(borderIcon);
		imageIcon3 = new ImageIcon("resources/icons/Fondo_campos.png");
        Image image2 = imageIcon3.getImage().getScaledInstance(237, 68, java.awt.Image.SCALE_SMOOTH);
        imageIcon3 = new ImageIcon(image2);
        imageIcon4 = new ImageIcon("resources/icons/Lupa.png");
        Image image3 = imageIcon4.getImage().getScaledInstance(30, 28, java.awt.Image.SCALE_SMOOTH);
        imageIcon4 = new ImageIcon(image3);
        imageIcon5 = new ImageIcon("resources/icons/Modificacion_nuevo.png");
        Image image4 = imageIcon5.getImage().getScaledInstance(30, 28, java.awt.Image.SCALE_SMOOTH);
        imageIcon5 = new ImageIcon(image4);
        imageIcon6 = new ImageIcon("resources/icons/Baja_nuevo.png");
        Image image5 = imageIcon6.getImage().getScaledInstance(30, 28, java.awt.Image.SCALE_SMOOTH);
        imageIcon6 = new ImageIcon(image5);
		layeredPanel.add(imageLabel, Integer.valueOf(4));
		layeredPanel.add(borderTable, Integer.valueOf(4));
		layeredPanel.add(backButton, Integer.valueOf(2));
		layeredPanel.add(data, Integer.valueOf(3));  
		botonBusqueda = new JButton();
		botonBusqueda.setIcon(imageIcon4);
		botonBusqueda.setSize(20, 30);
		
		botonModificacion = new JButton();
		botonModificacion.setIcon(imageIcon5);
		botonModificacion.setSize(20, 30);
		
		botonBaja = new JButton();
		botonBaja.setIcon(imageIcon6);
		botonBaja.setSize(20, 30);
		
		buttonsPanel2.add(botonBusqueda);
		buttonsPanel2.add(botonModificacion);
		buttonsPanel2.add(botonBaja);
		layeredPanel.add(buttonsPanel2, Integer.valueOf(4)); 
	}
	//Se lleva a cabo el redimensionamiento de componentes de la ventana y también de su posicion en la ventana, en lugar
		//de usar Layouts para mayor precisión.
	public void resizer() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int ejeX = (int) (0.1 * getWidth() - backButton.getWidth() / 2);
				int ejeY = (int) (0.08 * getHeight() - backButton.getHeight() / 2);
				backButton.setLocation(ejeX, ejeY);
				ejeX = (int) (0.493 * getWidth() - tablePanel.getWidth() / 2);
				ejeY = (int) (0.51 * getHeight() - tablePanel.getHeight() / 2);
				tablePanel.setLocation(ejeX, ejeY);
				ejeX = (int) (0.4965 * getWidth() - borderTable.getWidth() / 2);
				ejeY = (int) (0.512 * getHeight() - borderTable.getHeight() / 2);
				borderTable.setLocation(ejeX, ejeY);	
				ejeX = (int) (0.07 * getWidth() - imageLabel.getWidth() / 2);
				ejeY = (int) (0.81 * getHeight() - imageLabel.getHeight() / 2);
				imageLabel.setLocation(ejeX, ejeY);
				ejeX = (int) (0.635 * getWidth() - buttonsPanel.getWidth() / 2);
				ejeY = (int) (0.18 * getHeight() - buttonsPanel.getHeight() / 2);
				buttonsPanel.setLocation(ejeX, ejeY);		
				ejeX = (int) (0.275 * getWidth() - data.getWidth() / 2);
				ejeY = (int) (0.20 * getHeight() - data.getHeight() / 2);
				data.setLocation(ejeX, ejeY);	
				ejeX = (int) (0.405 * getWidth() - buttonsPanel2.getWidth() / 2);
				ejeY = (int) (0.20 * getHeight() - buttonsPanel2.getHeight() / 2);
				buttonsPanel2.setLocation(ejeX, ejeY);
			}
		});
	}
	//Se establecen las propiedades de los botones de las funciones principales
	public void setButtonProperties(JButton button) {
		button.setFont(new Font("Comic Sans", Font.BOLD, 15));
		button.setForeground(Color.white);
		button.setVerticalTextPosition(SwingConstants.CENTER);
		button.setHorizontalTextPosition(SwingConstants.CENTER);
		button.setIcon(imageIcon3);
		button.setSize(200, 100);
	}
}
