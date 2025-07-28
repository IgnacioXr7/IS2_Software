package GUI.view.Proveedores;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Clases_y_Subsistemas.Proveedor;
import Controller.ControllerClientes;
import Controller.ControllerPedidos;
import Controller.ControllerProductos;
import Controller.ControllerProveedores;
import Excepciones.ProvException;
import GUI.view.MainWindow;
import GUI.view.MessageWindow;
import GUI.view.ParseWindow;

public class ParseProveedores extends ParseWindow{
	private static final long serialVersionUID = 1L;
	private JLabel nombre;
	private JLabel cif;
	private JLabel domicilioSocial;
	private JLabel telefono;
	
	private JTextField cifField;
	private JTextField nombreField;
	private JTextField domicilioSocialField;
	private JTextField telefonoField;
	//Se crean varias constructores con el fin de reutilizar la ventana para las funciones de alta, modificación y consulta.
		//Este es el constructor de alta
	public ParseProveedores(ControllerClientes ctrlCl, ControllerPedidos ctrlPd, ControllerProductos ctrlProd,
			ControllerProveedores ctrlProv) {
		super("Datos proveedores", ctrlCl, ctrlPd, ctrlProd, ctrlProv);
		this.setSize(this.actualWidth, this.actualHeight);
		this.numDatos = 4; 
		this.cifField = new JTextField();
		this.nombreField = new JTextField();
		this.domicilioSocialField = new JTextField();
		this.telefonoField = new JTextField();
		this.action = 1;
		initGUI();
	}
	  //Este es el constructor de modificación
	public ParseProveedores(ControllerClientes ctrlCl, ControllerPedidos ctrlPd, ControllerProductos ctrlProd, ControllerProveedores ctrlProv,
			JTextField cifField, JTextField nombreField, JTextField domicilioSocialField , JTextField telefonoField) {
		super("Modificación datos clientes", ctrlCl, ctrlPd, ctrlProd, ctrlProv);
		this.cifField = cifField;
		this.cifField.setEditable(false);
		this.nombreField = nombreField;
		this.domicilioSocialField = domicilioSocialField;
		this.telefonoField = telefonoField;
		this.setSize(this.actualWidth, this.actualHeight);
		this.numDatos = 4; 
		this.action = 2;
		initGUI();
	}
	//Este es el constructor de consulta estableciéndose el resto de campos a no modificables
	public ParseProveedores(ControllerClientes ctrlCl, ControllerPedidos ctrlPd, ControllerProductos ctrlProd, ControllerProveedores ctrlProv,
			JTextField cifField, JTextField nombreField, JTextField domicilioSocialField , JTextField telefonoField, int consulta) {
		super("Modificación datos clientes", ctrlCl, ctrlPd, ctrlProd, ctrlProv);
		this.cifField = cifField;
		this.cifField.setEditable(false);
		this.nombreField = nombreField;
		this.nombreField.setEditable(false);
		this.domicilioSocialField = domicilioSocialField;
		this.domicilioSocialField.setEditable(false);
		this.telefonoField = telefonoField;
		this.telefonoField.setEditable(false);
		this.setSize(this.actualWidth, this.actualHeight);
		this.numDatos = 4; 
		this.action = consulta;
		initGUI();
	}
	 //Se inicializan los campos y los labels de la descripción de estos campos
	private void initGUI() {
		description.setLayout(new GridLayout(4, 1, 1, 45));
		data.setLayout(new GridLayout(4, 1, 1, 57));
		
		setMaxCharacters(nombreField, 20);
		setJTextFieldProperties(nombreField);
		selectField(nombreField);
		nombre = new JLabel("Nombre:");
		setLabelProperties(nombre);
		
		setMaxCharacters(cifField, 9);
		setJTextFieldProperties(cifField);
		selectField(cifField);
		cif = new JLabel("CIF:");
		setLabelProperties(cif);
		
		setMaxCharacters(domicilioSocialField, 28);
		setJTextFieldProperties(domicilioSocialField);
		selectField(domicilioSocialField);
		domicilioSocial = new JLabel("Domicilio social:");
		setLabelProperties(domicilioSocial);
		
		setMaxCharacters(telefonoField, 9);
		setJTextFieldProperties(telefonoField);
		selectField(telefonoField);
		telefono = new JLabel("Teléfono:");
		setLabelProperties(telefono);
		//Se añaden al panel 
		description.add(cif);
		data.add(cifField);
		description.add(nombre);
		data.add(nombreField);
		description.add(domicilioSocial);
		data.add(domicilioSocialField);
		description.add(telefono);
		data.add(telefonoField);
		description.setSize(305, 330);
		data.setSize(380, 320);
        layeredPanel.add(description, Integer.valueOf(3));
        layeredPanel.add(data, Integer.valueOf(3));
        //En caso de que se trate de alta o modificación se accederá al listener del addButton
        if(action != 0) {
        addButton.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
        		//Se añade este atributo para contemplar un posible caso de máximos proveedores insertados
        		boolean maxProductos = false;
        		//Se examina la validez de los campos, en caso de que el mensaje este vacío indica que no hay fallos
        		errorMessage +=  
        			 isOkField(cifField, cif, "cif") +
        			 isOkField(nombreField, nombre, "s") +
        		     isOkField(domicilioSocialField, domicilioSocial, "s") +
        		     isOkField(telefonoField, telefono, "n");
        		if (errorMessage == "") {
        			//Se contempla la excepción de la existencia de un proveedor con el CIF asociado al que queremos insertar
        			try {
                Proveedor proveedor = new Proveedor(cifField.getText(), nombreField.getText(),
                		domicilioSocialField.getText(), Integer.parseInt(telefonoField.getText()));
                if (action == 1) 
                	if (!ctrlProv.altaProveedor(proveedor)) {
                		ErrorMaxProv();
                		maxProductos = true;   		
                	}
                		
                else if (action == 2) 
                	ctrlProv.modificarProveedor(cifField.getText(), proveedor);   
              //En caso de que no surjan problemas al insertar o modificar, se pasara a la ventana con la tabla de proveedores
                //En caso contrario, se mostrarán los mensajes de error.
                if (!maxProductos) {
        		ParseProveedores.this.setVisible(false);
    			MainWindow main = new MainWindow(ctrlCl, ctrlPd, ctrlProd, ctrlProv);
                TableProveedores tableProveedores = new TableProveedores(ctrlCl, ctrlPd, ctrlProd, ctrlProv);
                tableProveedores.getBackButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	tableProveedores.setVisible(false);
                    	main.setVisible(true);
                    }
                });
                if (action == 1) 
                	newProvMessage();
                else if (action == 2) 
                	editedProvMessage();
                tableProveedores.setVisible(true);
                }
        			} catch (ProvException ce) {
        				ErrorProvAlreadyExists();
        			}
        		}
        		else {
        			new MessageWindow(ParseProveedores.this,  "<html>Error en los siguientes campos:<br>" +errorMessage+"</html>", "Error de datos", new Dimension(500, 160));
        			errorMessage = "";
        		}
            }   
        });  
        } else
        	layeredPanel.remove(addButton); //En caso de que se trate de una consulta, se borrará el botón de confirmación de la función de alta o modificación del subsistema
        
		mainPanel.add(layeredPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true);
	    resizer();
        setLocationRelativeTo(null);
	}
	//Se crean las ventanas indicando los errores o mensajes informativos de las acciones que se han realizado o vayan a hacerse
	private void ErrorMaxProv() {
		new MessageWindow(this, "Error, se ha alcanzado la máxima cantidad de proveedores posible", "Error alta proveedor", DimensionMessageWindow);
	}
	   private void ErrorProvAlreadyExists() {
	    	 new MessageWindow(this, "Error, existe ya un proveedor con el CIF introducido", "Error alta proveedor", DimensionMessageWindow);
	    }
	    private void newProvMessage() {
	    	new MessageWindow(this, "Proveedor añadido con éxito", "Alta proveedor", DimensionMessageWindow);
	    }
	    private void editedProvMessage() {
	    	new MessageWindow(this, "Proveedor modificado con éxito", "Modificar proveedor", DimensionMessageWindow);
	    }
}
