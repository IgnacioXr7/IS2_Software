package GUI.view.Productos;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Clases_y_Subsistemas.Producto;
import Controller.ControllerClientes;
import Controller.ControllerPedidos;
import Controller.ControllerProductos;
import Controller.ControllerProveedores;
import Excepciones.ProductException;
import GUI.view.MainWindow;
import GUI.view.MessageWindow;
import GUI.view.ParseWindow;

public class ParseProductos extends ParseWindow{
	private static final long serialVersionUID = 1L;
	private JLabel nombre;
	private JLabel codPr;
	private JLabel marca;
	private JLabel modelo;
	private JLabel precio;
	private JLabel unidades;
	private JTextField nombreField;
	private JTextField codPrField;
	private JTextField marcaField;
	private JTextField modeloField;
	private JTextField precioField;
	private JTextField unidadesField;
	
	
	public ParseProductos(ControllerClientes ctrlCl, ControllerPedidos ctrlPd, ControllerProductos ctrlProd,
			ControllerProveedores ctrlProv) {
		super("Datos productos", ctrlCl, ctrlPd, ctrlProd, ctrlProv);
		this.setSize(this.actualWidth, this.actualHeight);
		this.numDatos = 6; 
		this.nombreField = new JTextField();
		this.codPrField = new JTextField();
		this.marcaField = new JTextField();
		this.modeloField = new JTextField();
		this.precioField = new JTextField();
		this.unidadesField = new JTextField();
		this.action = 1;
		initGUI();
	}
	  //Este es el constructor de modificación
	public ParseProductos(ControllerClientes ctrlCl, ControllerPedidos ctrlPd, ControllerProductos ctrlProd, ControllerProveedores ctrlProv,
			JTextField nombreField, JTextField codPrField, JTextField marcaField , JTextField modeloField ,
			 JTextField precioField, JTextField unidadesField) {
		super("Modificación datos productos", ctrlCl, ctrlPd, ctrlProd, ctrlProv);
		this.nombreField = nombreField;
		this.codPrField = codPrField;
		this.codPrField.setEditable(false);
		this.marcaField = marcaField;
		this.modeloField = modeloField;
		this.precioField = precioField;
		this.unidadesField = unidadesField;
		this.setSize(this.actualWidth, this.actualHeight);
		this.numDatos = 6; 
		this.action = 2;	
		initGUI();
	}
	 //Este es el constructor de consulta estableciéndose el resto de campos a no modificables
	public ParseProductos(ControllerClientes ctrlCl, ControllerPedidos ctrlPd, ControllerProductos ctrlProd, ControllerProveedores ctrlProv,
			JTextField nombreField, JTextField codPrField, JTextField marcaField , JTextField modeloField ,
			 JTextField precioField, JTextField unidadesField, int consulta) {
		super("Modificación datos productos", ctrlCl, ctrlPd, ctrlProd, ctrlProv);
		this.nombreField = nombreField;
		this.nombreField.setEditable(false);
		this.codPrField = codPrField;
		this.codPrField.setEditable(false);
		this.marcaField = marcaField;
		this.marcaField.setEditable(false);
		this.modeloField = modeloField;
		this.modeloField.setEditable(false);
		this.precioField = precioField;
		this.precioField.setEditable(false);
		this.unidadesField = unidadesField;
		this.unidadesField.setEditable(false);
		this.setSize(this.actualWidth, this.actualHeight);
		this.numDatos = 6; 
		this.action = consulta;	
		initGUI();
	}
	 //Se inicializan los campos y los labels de la descripción de estos campos
	private void initGUI() {
		description.setLayout(new GridLayout(6, 1, 1, 45));
		data.setLayout(new GridLayout(6, 1, 1, 57));
		
		setMaxCharacters(nombreField, 20);
		setJTextFieldProperties(nombreField);
		selectField(nombreField);
		nombre = new JLabel("Nombre:");
		setLabelProperties(nombre);
		
		setMaxCharacters(codPrField, 4);
		setJTextFieldProperties(codPrField);
		selectField(codPrField);
		codPr = new JLabel("Código de producto:");
		setLabelProperties(codPr);
		
		setMaxCharacters(marcaField, 15);
		setJTextFieldProperties(marcaField);
		selectField(marcaField);
		marca = new JLabel("Marca:");
		setLabelProperties(marca);
		
		setMaxCharacters(modeloField, 15);
		setJTextFieldProperties(modeloField);
		selectField(modeloField);
		modelo = new JLabel("Modelo:");
		setLabelProperties(modelo);
		
		setMaxCharacters(precioField, 10);
		setJTextFieldProperties(precioField);
		selectField(precioField);
		precio = new JLabel("Precio:");
		setLabelProperties(precio);
		
		setMaxCharacters(unidadesField, 7);
		setJTextFieldProperties(unidadesField);
		selectField(unidadesField);
		unidades = new JLabel("Unidades:");
		setLabelProperties(unidades);
		//Se añaden al panel 
		description.add(nombre);
		data.add(nombreField);
		description.add(codPr);
		data.add(codPrField);
		description.add(marca);
		data.add(marcaField);
		description.add(modelo);
		data.add(modeloField);
		description.add(precio);
		data.add(precioField);
		description.add(unidades);
		data.add(unidadesField);
		description.setSize(305, 517);
		data.setSize(380, 507);
        layeredPanel.add(description, Integer.valueOf(3));
        layeredPanel.add(data, Integer.valueOf(3));
      //En caso de que se trate de alta o modificación se accederá al listener del addButton
        if(action != 0) {
        addButton.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
        		//Se añade este atributo para contemplar un posible caso de máximos productos insertados
        		boolean maxProductos = false;
        		//Se examina la validez de los campos, en caso de que el mensaje este vacío indica que no hay fallos
        		    errorMessage +=  
        		    isOkField(nombreField, nombre,"s") +
        			isOkField(codPrField, codPr,"cod") +
         			isOkField(marcaField, marca,"s") +
        			isOkField(modeloField, modelo,"s") + 
        			isOkField(precioField, precio,"d") +
        			isOkField(unidadesField, unidades,"i");
        		if (errorMessage == "") {
        			//Se contempla la excepción de la existencia de un producto con el dni asociado al que queremos insertar
        			try {
                Producto producto = new Producto(codPrField.getText(),nombreField.getText(),
                		marcaField.getText(),modeloField.getText(), 
                		Double.parseDouble(precioField.getText()), Integer.parseInt(unidadesField.getText()));
                if (action == 1) {
                	if (!ctrlProd.altaProducto(producto)) {
                		 ErrorMaxProd();
                		 maxProductos = true;
                	}
                }
                else if (action == 2) 
                	ctrlProd.modificarProducto(codPrField.getText(), producto);    
              //En caso de que no surjan problemas al insertar o modificar, se pasara a la ventana con la tabla de productos
                //En caso contrario, se mostrarán los mensajes de error.
                if (!maxProductos) {
        		ParseProductos.this.setVisible(false);
    			MainWindow main = new MainWindow(ctrlCl, ctrlPd, ctrlProd, ctrlProv);
                TableProductos tableProductos = new TableProductos(ctrlCl, ctrlPd, ctrlProd, ctrlProv);
                tableProductos.getBackButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	tableProductos.setVisible(false);
                    	main.setVisible(true);
                    }
                });
                if (action == 1) 
                	newProductMessage();
                else if (action == 2) 
                	editedProductMessage();
                tableProductos.setVisible(true);
                }
        			} catch (ProductException ce) {
        				ErrorProductAlreadyExists();
        			}
        		}
        		else {
        			new MessageWindow(ParseProductos.this,  "<html>Error en los siguientes campos:<br>" +errorMessage+"</html>", "Error de datos", new Dimension(500, 160));
        			errorMessage = "";
        		}
            }   
        });  
        } else
        	layeredPanel.remove(addButton);//En caso de que se trate de una consulta, se borrará el boton de confirmación de la función de alta o modificación del subsistema
		mainPanel.add(layeredPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true);
	    resizer();
        setLocationRelativeTo(null);
	}
	//Se crean las ventanas indicando los errores o mensajes informativos de las acciones que se han realizado o vayan a hacerse
		private void ErrorMaxProd() {
			new MessageWindow(this, "Error, se ha alcanzado la máxima cantidad de productos posible", "Error alta producto", DimensionMessageWindow);
		}
	   private void ErrorProductAlreadyExists() {
	    	 new MessageWindow(this, "Error, existe ya un producto con el código introducido", "Error alta producto", DimensionMessageWindow);
	    }
	    private void newProductMessage() {
	    	new MessageWindow(this, "Producto añadido con éxito", "Alta producto", DimensionMessageWindow);
	    }
	    private void editedProductMessage() {
	    	new MessageWindow(this, "Producto modificado con éxito", "Modificar producto", DimensionMessageWindow);
	    }
}
