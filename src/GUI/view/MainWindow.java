package GUI.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Controller.ControllerClientes;
import Controller.ControllerPedidos;
import Controller.ControllerProductos;
import Controller.ControllerProveedores;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import GUI.view.Clientes.*;
import GUI.view.Pedidos.*;
import GUI.view.Productos.*;
import GUI.view.Proveedores.*;

public class MainWindow extends MyWindow {
	private static final long serialVersionUID = 1L;
	private JButton provButton;
    private JButton prodButton;
    private JButton cliButton;
    private JButton pedButton;
    private ControllerClientes ctrlCl;
    private ControllerPedidos ctrlPd;
    private ControllerProductos ctrlProd;
    private ControllerProveedores ctrlProv;
    //Clase que servirá como menú principal para acceder al resto de subsistemas, además recibirá los controllers correspondientes para
    //acceder a la tienda, y se los pasará al resto de ventanas de los diversos subsistemas para que operen instanciando a estos.
    public MainWindow(ControllerClientes ctrlCl, ControllerPedidos ctrlPd, ControllerProductos ctrlProd,
			ControllerProveedores ctrlProv) {
    	  super("CODA");
          this.setSize(this.actualWidth, this.actualHeight);
          this.ctrlCl = ctrlCl;
          this.ctrlPd = ctrlPd;
          this.ctrlProd = ctrlProd;
          this.ctrlProv = ctrlProv;
          initGUI(); 	
	}
	private void initGUI() {
		//Inicialmente se agrega el logo
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        Image image = codaIcon.getImage().getScaledInstance(475, 395, java.awt.Image.SCALE_SMOOTH);
        codaIcon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(codaIcon);
        imageLabel.setOpaque(true);
        imageLabel.setBackground(new Color(0,0,0,0));
        imageLabel.setSize(475, 395);
        JLayeredPane layeredPanel = new JLayeredPane();
        layeredPanel.add(imageLabel, Integer.valueOf(3));
        //Se crea el panel para almacenar los botones de los subsistemas
        JPanel ButtonsPanel = new JPanel();
        ButtonsPanel.setLayout(new GridLayout(2, 2, 40, 40));
        ButtonsPanel.setBackground(new Color(0,0,0,0));
        
        ImageIcon imageIcon3 = new ImageIcon("resources/icons/Boton_subsistemas.png");
        Image image2 = imageIcon3.getImage().getScaledInstance(255, 77, java.awt.Image.SCALE_SMOOTH);
        imageIcon3 = new ImageIcon(image2);
        //Se crea el botón del subsistema correspondiente, a su vez, se crea una nueva ventana en caso de 
        cliButton = new JButton("Clientes");		//que sea pulsado, la cual se corresponderá con la ventana 
        setButtonProperties(cliButton, imageIcon3);            //principal del subsistema, que contendrá 
        cliButton.addActionListener(new ActionListener() {		//las funciones de alta, baja, modificación
        	 @Override											//búsqueda y consulta. 
             public void actionPerformed(ActionEvent e) { 		// A su vez, se generarán instancias de esta ventana en caso 
                 MainWindow.this.setVisible(false);				//de que se pulse al botón de volver de la ventana del subsistema creado
                 TableClientes tableClientes = new TableClientes(ctrlCl, ctrlPd, ctrlProd, ctrlProv); //para así generarse la transición
                 tableClientes.getBackButton().addActionListener(new ActionListener() { //creando y volviendo visibles o no las ventanas 
                     @Override															//según convenga.
                     public void actionPerformed(ActionEvent e) {	//Este patrón se repetirá para todos los subsistemas y sus respectivas
                    	 tableClientes.setVisible(false);			//ventanas a las que se pretenda acceder.
                         MainWindow.this.setVisible(true);
                     }
                 });
                 tableClientes.setVisible(true);
             }
        });
        pedButton = new JButton("Pedidos");
        setButtonProperties(pedButton, imageIcon3);
        pedButton.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
                MainWindow.this.setVisible(false);
                TablePedidos tablePedidos = new TablePedidos(ctrlCl, ctrlPd, ctrlProd, ctrlProv);
                tablePedidos.getBackButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	tablePedidos.setVisible(false);
                        MainWindow.this.setVisible(true);
                    }
                });
                tablePedidos.setVisible(true);
            }
        });
        ButtonsPanel.add(cliButton);
        ButtonsPanel.add(pedButton);
        provButton = new JButton("Proveedores");
        setButtonProperties(provButton, imageIcon3);
        provButton.addActionListener(new ActionListener() {
        	 @Override
             public void actionPerformed(ActionEvent e) {
                 MainWindow.this.setVisible(false);
                 TableProveedores tableProveedores = new TableProveedores(ctrlCl, ctrlPd, ctrlProd, ctrlProv);
                 tableProveedores.getBackButton().addActionListener(new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent e) {
                    	 tableProveedores.setVisible(false);
                         MainWindow.this.setVisible(true);
                     }
                 });
                 tableProveedores.setVisible(true);
             }
        });
        
        prodButton = new JButton("Productos");
        setButtonProperties(prodButton, imageIcon3);
        prodButton.addActionListener(new ActionListener() {
        	 @Override
             public void actionPerformed(ActionEvent e) {
                 MainWindow.this.setVisible(false);
                 TableProductos tableProductos = new TableProductos(ctrlCl, ctrlPd, ctrlProd, ctrlProv);
                 tableProductos.getBackButton().addActionListener(new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent e) {
                    	 tableProductos.setVisible(false);
                         MainWindow.this.setVisible(true);
                     }
                 });
                 tableProductos.setVisible(true);
             }
        });

        ButtonsPanel.add(provButton);
        ButtonsPanel.add(prodButton);
        ButtonsPanel.setOpaque(true);
        ButtonsPanel.setSize(545, 190);
        layeredPanel.add(ButtonsPanel, Integer.valueOf(4));
        mainPanel.add(layeredPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int ejeX = (int) ((0.5) * getWidth() - imageLabel.getWidth() / 2);
                int ejeY = (int) ((0.22) * getHeight() - imageLabel.getHeight() / 2);            
                imageLabel.setLocation(ejeX, ejeY);
                ejeX = (int) (0.5 * getWidth() - ButtonsPanel.getWidth() / 2);
                ejeY = (int) (0.65 * getHeight() - ButtonsPanel.getHeight() / 2);
                ButtonsPanel.setLocation(ejeX, ejeY);
            }
        });
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
       // setResizable(false);
    }
	//Se establecen las propiedades que se quieren tener de los botones de los subsistemas
    private void setButtonProperties(JButton button, ImageIcon imageIcon3) {
    	button.setIcon(imageIcon3);
    	button.setVerticalTextPosition(SwingConstants.CENTER);
    	button.setHorizontalTextPosition(SwingConstants.CENTER);
    	button.setFont(new Font("Comic Sans", Font.BOLD, 16));
    	button.setForeground(Color.white);
	}
}
