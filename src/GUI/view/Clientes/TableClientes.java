package GUI.view.Clientes;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import Clases_y_Subsistemas.Cliente;
import Controller.ControllerClientes;
import Controller.ControllerPedidos;
import Controller.ControllerProductos;
import Controller.ControllerProveedores;
import GUI.view.TableWindow;
import GUI.view.InfoTable;
import GUI.view.*;

public class TableClientes extends TableWindow{
    private static final long serialVersionUID = 1L;
    
    public TableClientes(ControllerClientes ctrlCl, ControllerPedidos ctrlPd, ControllerProductos ctrlProd,
			ControllerProveedores ctrlProv) {
        super("Tabla clientes", ctrlCl, ctrlPd, ctrlProd, ctrlProv);
        this.setSize(this.actualWidth, this.actualHeight);
        initGUI();
    }
    public void initGUI() {
        ClientesTableModel clientesTableModel = new ClientesTableModel();
        InfoTable contentPanelGroups = new InfoTable(clientesTableModel);
        contentPanelGroups.setPreferredSize(new Dimension(tableWidth, tableHeight));  
        tablePanel.add(contentPanelGroups);
        layeredPanel.add(tablePanel, Integer.valueOf(2));    
        botonAlta = new JButton("Nuevo cliente");
        setButtonProperties(botonAlta);
        botonConsulta = new JButton("Consulta cliente");
        setButtonProperties(botonConsulta);
		buttonsPanel.add(botonConsulta);
		buttonsPanel.add(botonAlta);
		buttonsPanel.setOpaque(true);
		buttonsPanel.setSize(500, 70);
		layeredPanel.add(buttonsPanel, Integer.valueOf(4));   
        mainPanel.add(layeredPanel);
        botonConsulta.addActionListener(new ActionListener() {
        	@Override 
        	public void actionPerformed(ActionEvent e) {
           		Cliente clienteAux = ctrlCl.consultaCliente(busquedaField.getText());
           		 if(busquedaField.getText().isEmpty())
           			 ErrorEmptyField();
           		 else if (clienteAux == null)
           			 ErrorDniNotFound();
           		 else { 
           			JTextField direccionField = new JTextField();
           			direccionField.setText(clienteAux.getDomicilio()); 
           			JTextField dniField = new JTextField();
           			dniField.setText(clienteAux.getDNI()); 
           			JTextField nombreField = new JTextField();
           			nombreField.setText(clienteAux.getNombre()); 
           			JTextField apellidosField = new JTextField();
           			apellidosField.setText(clienteAux.getApellidos()); 
           			JTextField correoField = new JTextField();
           			correoField.setText(clienteAux.getCorreo()); 
           			JTextField telefonoField = new JTextField();
           			telefonoField.setText(Integer.toString(clienteAux.getTelefono())); 
           			TableClientes.this.setVisible(false);
                    ParseClientes parseClientes = new ParseClientes(ctrlCl, ctrlPd, ctrlProd, ctrlProv,
                    		dniField,  nombreField,  apellidosField ,  correoField,
             				  direccionField,  telefonoField,consultaValue);
                    parseClientes.getBackButton().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                     	   parseClientes.setVisible(false);
                     	   TableClientes.this.setVisible(true);
                        }
                    });
                    parseClientes.setVisible(true);      		
           		 }     			
               }
        });

        botonAlta.addActionListener(new ActionListener() {
          	 @Override
               public void actionPerformed(ActionEvent e) {
          		TableClientes.this.setVisible(false);
                   ParseClientes parseClientes = new ParseClientes(ctrlCl, ctrlPd, ctrlProd, ctrlProv);
                   parseClientes.getBackButton().addActionListener(new ActionListener() {
                       @Override
                       public void actionPerformed(ActionEvent e) {
                    	   AcceptWindow aW = new AcceptWindow(parseClientes, "¿Estás seguro? Si has insertado datos no se guardarán"
                 					 , "Alta cliente"); 
                 			if (aW.getOk()) {
                 				parseClientes.setVisible(false);
                 				TableClientes.this.setVisible(true);
                 			}
                       }
                   });
                   parseClientes.setVisible(true);
               }
          });
        botonBusqueda.addActionListener(new ActionListener() {
         	 @Override
             public void actionPerformed(ActionEvent e) {
         		List<Cliente> clientList = ctrlCl.busquedaClientes(busquedaField.getText());
         		if (clientList.isEmpty())
         			ErrorClientNameNotFound();
         		else
         			clientesTableModel.mostrarCoincidenciasClientes(clientList);  			 
             }
        });
        botonBaja.addActionListener(new ActionListener() {
        	 @Override
            public void actionPerformed(ActionEvent e) {
        		Cliente clienteAux = ctrlCl.consultaCliente(busquedaField.getText());
        		 if(busquedaField.getText().isEmpty())
        			 ErrorEmptyField();
        		 else if (clienteAux == null)
        			 ErrorDniNotFound();
        		 else {
        			 AcceptWindow aW = new AcceptWindow(TableClientes.this, "¿Estás seguro de eliminar al cliente con DNI " + busquedaField.getText() + "?"
        					 , "Eliminar cliente"); 
        			if (aW.getOk()) {
        				ctrlCl.bajaCliente(busquedaField.getText());   	
        				List<Cliente> clientList = ctrlCl.busquedaClientes("");
        				clientesTableModel.mostrarCoincidenciasClientes(clientList);  	
        				deleteClientMessage();
        			}
        		 }
            }
       });
        botonModificacion.addActionListener(new ActionListener() {
       	 @Override
           public void actionPerformed(ActionEvent e) {
       		Cliente clienteAux = ctrlCl.consultaCliente(busquedaField.getText());
       		 if(busquedaField.getText().isEmpty())
       			 ErrorEmptyField();
       		 else if (clienteAux == null)
       			 ErrorDniNotFound();
       		 else {
       			 AcceptWindow aW = new AcceptWindow(TableClientes.this, "¿Estás seguro de modificar al cliente con DNI " + busquedaField.getText() + "?"
       					 , "Modificar cliente"); 
       			if (aW.getOk()) {
       			JTextField direccionField = new JTextField();
       			direccionField.setText(clienteAux.getDomicilio()); 
       			JTextField dniField = new JTextField();
       			dniField.setText(clienteAux.getDNI()); 
       			JTextField nombreField = new JTextField();
       			nombreField.setText(clienteAux.getNombre()); 
       			JTextField apellidosField = new JTextField();
       			apellidosField.setText(clienteAux.getApellidos()); 
       			JTextField correoField = new JTextField();
       			correoField.setText(clienteAux.getCorreo()); 
       			JTextField telefonoField = new JTextField();
       			telefonoField.setText(Integer.toString(clienteAux.getTelefono())); 
       			TableClientes.this.setVisible(false);
                ParseClientes parseClientes = new ParseClientes(ctrlCl, ctrlPd, ctrlProd, ctrlProv,
                		dniField,  nombreField,  apellidosField ,  correoField,
         				  direccionField,  telefonoField);
                parseClientes.getBackButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	  AcceptWindow aW = new AcceptWindow(parseClientes, "¿Estás seguro? Si has insertado datos no se guardarán"
              					 , "Modificar cliente"); 
              			if (aW.getOk()) {
                 	   parseClientes.setVisible(false);
                 	   TableClientes.this.setVisible(true);
              			}
                    }
                });
                parseClientes.setVisible(true);
       		  }
       		 }     			
           }
      });   
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        resizer();
        setLocationRelativeTo(null);
    }
    private void ErrorEmptyField() {
    	 new MessageWindow(this, "El campo de búsqueda está vacío", "Error de parámetros", DimensionMessageWindow);
    }
    private void ErrorDniNotFound() {
    	new MessageWindow(this, "No se encuentran coincidencias con el DNI de cliente insertado", "Error de parámetros", DimensionMessageWindow);
    }
    private void ErrorClientNameNotFound() {
   	 new MessageWindow(this, "No se encuentran coincidencias con el nombre de cliente insertado", "Error de parámetros", DimensionMessageWindow);
   }
    private void deleteClientMessage() {
      	 new MessageWindow(this, "Cliente eliminado con éxito", "Eliminar cliente", DimensionMessageWindow);
    }
}
