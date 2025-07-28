package GUI.view.Clientes;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Clases_y_Subsistemas.Cliente;
import Controller.ControllerClientes;
import Controller.ControllerPedidos;
import Controller.ControllerProductos;
import Controller.ControllerProveedores;
import Excepciones.ClientException;
import GUI.view.MessageWindow;
import GUI.view.MainWindow;
import GUI.view.ParseWindow;

public class ParseClientes extends ParseWindow{
	private static final long serialVersionUID = 1L;
	private JLabel dni;
	private JLabel nombre;
	private JLabel apellidos;
	private JLabel correo;
	private JLabel telefono;
	private JLabel direccion;
	private JTextField dniField;
	private JTextField nombreField;
	private JTextField apellidosField;
	private JTextField correoField;
	private JTextField direccionField;
	private JTextField telefonoField;
	//Se crean varias constructores con el fin de reutilizar la ventana para las funciones de alta, modificación y consulta.
	//Este es el constructor de alta
	    public ParseClientes(ControllerClientes ctrlCl, ControllerPedidos ctrlPd, ControllerProductos ctrlProd,
				ControllerProveedores ctrlProv) {
		super("Datos clientes", ctrlCl, ctrlPd, ctrlProd, ctrlProv);
		this.setSize(this.actualWidth, this.actualHeight);
		this.numDatos = 6; 
		this.dniField = new JTextField();
		this.nombreField = new JTextField();
		this.apellidosField = new JTextField();
		this.correoField = new JTextField();
		this.direccionField = new JTextField();
		this.telefonoField = new JTextField();
		this.action = 1;
		initGUI();
	}
	  //Este es el constructor de modificación
	public ParseClientes(ControllerClientes ctrlCl, ControllerPedidos ctrlPd, ControllerProductos ctrlProd, ControllerProveedores ctrlProv,
			JTextField dniField, JTextField nombreField, JTextField apellidosField , JTextField correoField ,
			 JTextField direccionField, JTextField telefonoField) {
		super("Modificación datos clientes", ctrlCl, ctrlPd, ctrlProd, ctrlProv);
		this.dniField = dniField;
		this.dniField.setEditable(false);
		this.nombreField = nombreField;
		this.apellidosField = apellidosField;
		this.correoField = correoField;
		this.direccionField = direccionField;
		this.telefonoField = telefonoField;
		this.setSize(this.actualWidth, this.actualHeight);
		this.numDatos = 6; 
		this.action = 2;
		
		initGUI();
	}
	 //Este es el constructor de consulta estableciéndose el resto de campos a no modificables
	public ParseClientes(ControllerClientes ctrlCl, ControllerPedidos ctrlPd, ControllerProductos ctrlProd, ControllerProveedores ctrlProv,
			JTextField dniField, JTextField nombreField, JTextField apellidosField , JTextField correoField ,
			 JTextField direccionField, JTextField telefonoField, int consulta) {
		super("Modificación datos clientes", ctrlCl, ctrlPd, ctrlProd, ctrlProv);
		this.dniField = dniField;
		this.dniField.setEditable(false);
		this.nombreField = nombreField;
		this.nombreField.setEditable(false);
		this.apellidosField = apellidosField;
		this.apellidosField.setEditable(false);
		this.correoField = correoField;
		this.correoField.setEditable(false);
		this.direccionField = direccionField;
		this.direccionField.setEditable(false);
		this.telefonoField = telefonoField;
		this.telefonoField.setEditable(false);
		this.setSize(this.actualWidth, this.actualHeight);
		this.numDatos = 6; 
		this.action = consulta;
		initGUI();
	}
	 //Se inicializan los campos y los labels de la descripción de estos campos
	private void initGUI() {
		description.setLayout(new GridLayout(6, 1, 1, 45));
		data.setLayout(new GridLayout(6, 1, 1, 57));
		setMaxCharacters(dniField, 9);
		setJTextFieldProperties(dniField);
		selectField(dniField);
		dni = new JLabel("DNI:");
		setLabelProperties(dni);
		
		setMaxCharacters(nombreField, 20);
		setJTextFieldProperties(nombreField);
		selectField(nombreField);
		nombre = new JLabel("Nombre:");
		setLabelProperties(nombre);
		
		setMaxCharacters(apellidosField, 28);
		setJTextFieldProperties(apellidosField);
		selectField(apellidosField);
		apellidos = new JLabel("Apellidos:");
		setLabelProperties(apellidos);
		
		setMaxCharacters(correoField, 28);
		setJTextFieldProperties(correoField);
		selectField(correoField);
		correo = new JLabel("Correo:");
		setLabelProperties(correo);
		
		setMaxCharacters(direccionField, 28);
		setJTextFieldProperties(direccionField);
		selectField(direccionField);
		direccion = new JLabel("Dirección:");
		setLabelProperties(direccion);
		
		setMaxCharacters(telefonoField, 9);
		setJTextFieldProperties(telefonoField);
		selectField(telefonoField);
		telefono = new JLabel("Teléfono:");
		setLabelProperties(telefono);
		//Se añaden al panel 
		description.add(dni);
		data.add(dniField);
		description.add(nombre);
		data.add(nombreField);
		description.add(apellidos);
		data.add(apellidosField);
		description.add(correo);
		data.add(correoField);
		description.add(direccion);
		data.add(direccionField);		
		description.add(telefono);
		data.add(telefonoField);
		description.setSize(305, 517);
		data.setSize(380, 507);
        layeredPanel.add(description, Integer.valueOf(3));
        layeredPanel.add(data, Integer.valueOf(3));
        //En caso de que se trate de alta o modificación se accederá al listener del addButton
        if(action != 0) {
        addButton.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
        		//Se añade este atributo para contemplar un posible caso de máximos clientes insertados
        		boolean maxClientes = false;
        		//Se examina la validez de los campos, en caso de que el mensaje este vacío indica que no hay fallos
        		errorMessage +=  
        			 isOkField(dniField, dni,"dni") +
        			 isOkField(nombreField, nombre,"s") +
        			 isOkField(apellidosField, apellidos,"s")+
        			 isOkField(correoField, correo ,"s") +
        			 isOkField(direccionField, direccion,"s") +
        			 isOkField(telefonoField, telefono,"n");
        		if (errorMessage == "") {
        			//Se contempla la excepción de la existencia de un cliente con el dni asociado al que queremos insertar
        			try {
                Cliente cliente = new Cliente(dniField.getText(), nombreField.getText(),
                		apellidosField.getText(),correoField.getText(), 
                		Integer.parseInt(telefonoField.getText()), direccionField.getText());
                if (action == 1) {
                	if (!ctrlCl.altaCliente(cliente)) {
                		ErrorMaxClient();
                		maxClientes = true;
                	}
                }
                else if (action == 2)
                	ctrlCl.modificarCliente(dniField.getText(), cliente);
                //En caso de que no surjan problemas al insertar o modificar, se pasara a la ventana con la tabla de clientes
                //En caso contrario, se mostrarán los mensajes de error.
                if (!maxClientes) {
        		ParseClientes.this.setVisible(false);
    			MainWindow main = new MainWindow(ctrlCl, ctrlPd, ctrlProd, ctrlProv);
                TableClientes tableClientes = new TableClientes(ctrlCl, ctrlPd, ctrlProd, ctrlProv);
                tableClientes.getBackButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	tableClientes.setVisible(false);
                    	main.setVisible(true);
                    }
                });    
                if (action == 1) 
                	newClientMessage();
                else if (action == 2)
                	editedClientMessage();
                tableClientes.setVisible(true);
                }
        			} catch (ClientException ce) {
        				ErrorClientAlreadyExists();
        			}
        		}
        		else {
        			new MessageWindow(ParseClientes.this,  "<html>Error en los siguientes campos:<br>" +errorMessage+"</html>", "Error de datos", new Dimension(500, 160));
        			errorMessage = "";
        		}
            }   
        });  
        } else
        	layeredPanel.remove(addButton); //En caso de que se trate de una consulta, se borrará el boton de confirmación de la función de alta o modificación del subsistema
		mainPanel.add(layeredPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true);
	    resizer();
        setLocationRelativeTo(null);
	}
	//Se crean las ventanas indicando los errores o mensajes informativos de las acciones que se han realizado o vayan a hacerse
	 private void ErrorMaxClient() {
    	 new MessageWindow(this, "Error, se ha alcanzado la máxima cantidad de clientes posible", "Error alta cliente", DimensionMessageWindow);
    }
	   private void ErrorClientAlreadyExists() {
	    	 new MessageWindow(this, "Error, existe ya un cliente con el DNI introducido", "Error alta cliente", DimensionMessageWindow);
	    }
	    private void newClientMessage() {
	    	new MessageWindow(this, "Cliente añadido con éxito", "Alta cliente", DimensionMessageWindow);
	    }
	    private void editedClientMessage() {
	    	new MessageWindow(this, "Cliente modificado con éxito", "Modificar cliente", DimensionMessageWindow);
	    }
}