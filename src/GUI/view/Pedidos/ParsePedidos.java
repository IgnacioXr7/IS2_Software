package GUI.view.Pedidos;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Clases_y_Subsistemas.Pedido;
import Controller.ControllerClientes;
import Controller.ControllerPedidos;
import Controller.ControllerProductos;
import Controller.ControllerProveedores;
import Excepciones.PedidoException;
import GUI.view.MessageWindow;
import GUI.view.MainWindow;
import GUI.view.ParseWindow;

public class ParsePedidos extends ParseWindow{
	private static final long serialVersionUID = 1L;
	private JLabel codPed;
	private JLabel dniCliente;
	private JLabel fecha;
	private JLabel importe;
	private JLabel estado;
	private JLabel codArticulo;
	private JTextField codPedField;
	private JTextField dniClienteField;
	private JTextField fechaField;
	private JTextField importeField;
	private JTextField estadoField;
	private JTextField cdArticuloField;
	//Se crean varias constructores con el fin de reutilizar la ventana para las funciones de alta, modificación y consulta.
		//Este es el constructor de alta
	public ParsePedidos(ControllerClientes ctrlCl, ControllerPedidos ctrlPd, ControllerProductos ctrlProd,
			ControllerProveedores ctrlProv) {
		super("Datos pedidos", ctrlCl, ctrlPd, ctrlProd, ctrlProv);
		this.setSize(this.actualWidth, this.actualHeight);
		this.numDatos = 6; 
		this.codPedField = new JTextField();
		this.dniClienteField = new JTextField();
		this.fechaField = new JTextField();
		this.importeField = new JTextField();
		this.estadoField = new JTextField();
		setMaxCharacters(estadoField, 4);
		setJTextFieldProperties(estadoField);
		this.estadoField.setText("true");
		selectField(estadoField);
		this.estadoField.setEditable(false);
		this.cdArticuloField = new JTextField();		
		this.action = 1;
		initGUI();
	}
	 //Este es el constructor de modificación
	public ParsePedidos(ControllerClientes ctrlCl, ControllerPedidos ctrlPd, ControllerProductos ctrlProd, ControllerProveedores ctrlProv,
			JTextField codPedField, JTextField dniClienteField, JTextField fechaField, JTextField importeField,
			JTextField estadoField, JTextField cdArticuloField) {
		super("Modificación datos pedidos", ctrlCl, ctrlPd, ctrlProd, ctrlProv);
		this.codPedField = codPedField;
		this.codPedField.setEditable(false);
		this.dniClienteField = dniClienteField;
		this.dniClienteField.setEditable(false);
		this.fechaField = fechaField;
		this.fechaField.setEditable(false);
		this.importeField = importeField;
		this.estadoField = estadoField;
		this.cdArticuloField = cdArticuloField;
		this.cdArticuloField.setEditable(false);
		this.setSize(this.actualWidth, this.actualHeight);
		this.numDatos = 6; 
		this.action = 2;
		initGUI();
	}
	 //Este es el constructor de consulta estableciéndose el resto de campos a no modificables
	public ParsePedidos(ControllerClientes ctrlCl, ControllerPedidos ctrlPd, ControllerProductos ctrlProd, ControllerProveedores ctrlProv,
			JTextField codPedField, JTextField dniClienteField, JTextField fechaField, JTextField importeField,
			JTextField estadoField, JTextField cdArticuloField, int consulta) {
		super("Modificación datos pedidos", ctrlCl, ctrlPd, ctrlProd, ctrlProv);
		this.codPedField = codPedField;
		this.codPedField.setEditable(false);
		this.dniClienteField = dniClienteField;
		this.dniClienteField.setEditable(false);
		this.fechaField = fechaField;
		this.fechaField.setEditable(false);
		this.importeField = importeField;
		this.importeField.setEditable(false);
		this.estadoField = estadoField;
		this.estadoField.setEditable(false);
		this.cdArticuloField = cdArticuloField;
		this.cdArticuloField.setEditable(false);
		this.setSize(this.actualWidth, this.actualHeight);
		this.numDatos = 6; 
		this.action = consulta;
		initGUI();
	}
	
	 //Se inicializan los campos y los labels de la descripción de estos campos
	private void initGUI() {
		description.setLayout(new GridLayout(6, 1, 1, 45));
		data.setLayout(new GridLayout(6, 1, 1, 57));
		
		setMaxCharacters(codPedField, 4);
		setJTextFieldProperties(codPedField);
		selectField(codPedField);
		codPed = new JLabel("Código de pedido:");
		setLabelProperties(codPed);
		
		setMaxCharacters(dniClienteField, 9);
		setJTextFieldProperties(dniClienteField);
		selectField(dniClienteField);
		dniCliente = new JLabel("DNI cliente:");
		setLabelProperties(dniCliente);
		
		setMaxCharacters(fechaField, 8);
		setJTextFieldProperties(fechaField);
		selectField(fechaField);
		fecha = new JLabel("Fecha(dd/mm/aa):");
		setLabelProperties(fecha);
		
		setMaxCharacters(importeField, 8);
		setJTextFieldProperties(importeField);
		selectField(importeField);
		importe = new JLabel("Importe:");
		setLabelProperties(importe);
		
		setMaxCharacters(estadoField, 8);
		setJTextFieldProperties(estadoField);
		selectField(estadoField);
		estado = new JLabel("Estado:");
		setLabelProperties(estado);
		
		setMaxCharacters(cdArticuloField, 4);
		setJTextFieldProperties(cdArticuloField);
		selectField(cdArticuloField);
		codArticulo = new JLabel("Código de artículo:");
		setLabelProperties(codArticulo);
		//Se añaden al panel 
		description.add(codPed);
		data.add(codPedField);
		description.add(dniCliente);
		data.add(dniClienteField);
		description.add(fecha);
		data.add(fechaField);
		description.add(importe);
		data.add(importeField);
		description.add(estado);
		data.add(estadoField);
		description.add(codArticulo);
		data.add(cdArticuloField);
		description.setSize(305, 517);
		data.setSize(380, 507);
        layeredPanel.add(description, Integer.valueOf(3));
        layeredPanel.add(data, Integer.valueOf(3));
        //En caso de que se trate de alta o modificación se accederá al listener del addButton
        if(action != 0) {
        addButton.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
        		//Se añade este atributo para contemplar un posible caso de máximos pedidos insertados
        		boolean maxPedidos = false;
        		//Se examina la validez de los campos, en caso de que el mensaje este vacío indica que no hay fallos
        		errorMessage +=  
        			 isOkField(codPedField, codPed,"cod") +
        			 isOkField(dniClienteField, dniCliente, "dni") +
        			 isOkField(fechaField, fecha,"f") +
        			 isOkField(importeField, importe, "d") +
        			 isOkField(estadoField, estado, "e") +
        			 isOkField(cdArticuloField, codArticulo, "cod");
        		if (errorMessage == "") {
        			//Se contempla la excepción de la existencia de un pedido con el código asociado al que queremos insertar
        			try {	
                Pedido pedido = new Pedido(codPedField.getText(), dniClienteField.getText(), fechaField.getText(), 
                		Double.parseDouble(importeField.getText()), Boolean.parseBoolean(estadoField.getText()), 
                		cdArticuloField.getText());
                
                if (action == 1) {
                	 if (!ctrlPd.altaPedido(pedido)) {
                		 ErrorMaxPed();
                		 maxPedidos = true;
                	 }
                }
                else if (action == 2) 
                	ctrlPd.modificarPedido(codPedField.getText(), pedido);
                //En caso de que no surjan problemas al insertar o modificar, se pasara a la ventana con la tabla de pedidos
                //En caso contrario, se mostrarán los mensajes de error.
                if (!maxPedidos) {
        		ParsePedidos.this.setVisible(false);
    			MainWindow main = new MainWindow(ctrlCl, ctrlPd, ctrlProd, ctrlProv);
    			TablePedidos tablePedidos = new TablePedidos(ctrlCl, ctrlPd, ctrlProd, ctrlProv);
    			tablePedidos.getBackButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	tablePedidos.setVisible(false);
                    	main.setVisible(true);
                    }
                });
    			if (action == 1) 
                	newClientMessage();
    			else if (action == 2) 
                	editedClientMessage();
                tablePedidos.setVisible(true);
                }
        			} catch (PedidoException PE) {
        			    if (PE.getMessage().equals("No existe cliente")) {
        			    	ErrorCliNoExist();
        			    } else if (PE.getMessage().equals("No existe producto")) {
        			    	ErrorProdNoExist();
        			    } else if (PE.getMessage().equals("Existe pedido")) {
        			    	ErrorPedAlreadyExists();
        			    }
        			}
        		}
        		else {
        			new MessageWindow(ParsePedidos.this,  "<html>Error en los siguientes campos:<br>" +errorMessage+"</html>", "Error de datos", new Dimension(500, 160));
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
	 private void ErrorMaxPed() {
    	 new MessageWindow(this, "Error, no hay suficiente stock del producto introducido", "Error alta pedido", DimensionMessageWindow);
    }
	 private void ErrorProdNoExist() {
    	 new MessageWindow(this, "Error, el producto introducido no está registrado", "Error alta pedido", DimensionMessageWindow);
    }
	 private void ErrorCliNoExist() {
    	 new MessageWindow(this, "Error, el cliente introducido no existe", "Error alta pedido", DimensionMessageWindow);
    }
	private void ErrorPedAlreadyExists() {
   	 new MessageWindow(this, "Error, existe ya un pedido con el código de pedido introducido", "Error alta pedido", DimensionMessageWindow);
   }
   private void newClientMessage() {
   	new MessageWindow(this, "Pedido añadido con éxito", "Alta pedido", DimensionMessageWindow);
   }
   private void editedClientMessage() {
   	new MessageWindow(this, "Pedido modificado con éxito", "Modificar pedido", DimensionMessageWindow);
   }
}
