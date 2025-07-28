package GUI.view.Pedidos;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import Clases_y_Subsistemas.Pedido;
import Controller.ControllerClientes;
import Controller.ControllerPedidos;
import Controller.ControllerProductos;
import Controller.ControllerProveedores;
import GUI.view.TableWindow;
import GUI.view.Pedidos.TablePedidos;
import GUI.view.AcceptWindow;
import GUI.view.InfoTable;
import GUI.view.MessageWindow;

public class TablePedidos extends TableWindow{
    private static final long serialVersionUID = 1L;

    public TablePedidos(ControllerClientes ctrlCl, ControllerPedidos ctrlPd, ControllerProductos ctrlProd,
			ControllerProveedores ctrlProv) {
        super("Tabla pedidos", ctrlCl, ctrlPd, ctrlProd, ctrlProv);
        this.setSize(this.actualWidth, this.actualHeight);
        initGUI();
    }
    
    public void initGUI() {
        PedidosTableModel pedidosTableModel = new PedidosTableModel();
        InfoTable contentPanelGroups = new InfoTable(pedidosTableModel);
        contentPanelGroups.setPreferredSize(new Dimension(tableWidth, tableHeight));  
        tablePanel.add(contentPanelGroups);
        layeredPanel.add(tablePanel, Integer.valueOf(2));    
        botonAlta = new JButton("Nuevo pedido");
        setButtonProperties(botonAlta);
        botonConsulta = new JButton("Consulta pedido");
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
           		Pedido PedidoAux = ctrlPd.consultaPedido(busquedaField.getText());
           		 if(busquedaField.getText().isEmpty())
           			 ErrorEmptyField();
           		 else if (PedidoAux == null)
           			 ErrorDniNotFound();
           		 else {
           			JTextField codPedField = new JTextField();
           			codPedField.setText(PedidoAux.getCodPed()); 
           			JTextField dniClienteField = new JTextField();
           			dniClienteField.setText(PedidoAux.getDNICliente()); 
           			JTextField fechaField = new JTextField();
           			fechaField.setText(PedidoAux.getFecha()); 
           			JTextField importeField = new JTextField();
           			importeField.setText(Double.toString(PedidoAux.getImporte())); 
           			JTextField estadoField = new JTextField();
           			estadoField.setText(Boolean.toString(PedidoAux.getEstado())); 
           			JTextField cdArticuloField = new JTextField();
           			cdArticuloField.setText(PedidoAux.getCodArticulo()); 
           			TablePedidos.this.setVisible(false);
                    ParsePedidos parsePedidos = new ParsePedidos(ctrlCl, ctrlPd, ctrlProd, ctrlProv,
                    		codPedField,  dniClienteField,  fechaField ,  importeField,
                    		estadoField,  cdArticuloField, consultaValue);
                    parsePedidos.getBackButton().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                     	   parsePedidos.setVisible(false);
                     	  TablePedidos.this.setVisible(true);	
                        }
                    });
                    parsePedidos.setVisible(true);
           		  
           		 }     			
               }
        });
        botonAlta.addActionListener(new ActionListener() {
          	 @Override
               public void actionPerformed(ActionEvent e) {
          		TablePedidos.this.setVisible(false);
                   ParsePedidos parsePedidos = new ParsePedidos(ctrlCl, ctrlPd, ctrlProd, ctrlProv);
                   parsePedidos.getBackButton().addActionListener(new ActionListener() {
                       @Override
                       public void actionPerformed(ActionEvent e) {
                    	   AcceptWindow aW = new AcceptWindow(parsePedidos, "¿Estás seguro? Si has insertado datos no se guardarán"
                 					 , "Alta Pedido"); 
                 			if (aW.getOk()) {
                 				parsePedidos.setVisible(false);
                 				TablePedidos.this.setVisible(true);
                 			}
                       }
                   });
                   parsePedidos.setVisible(true);
               }
          });
        botonBusqueda.addActionListener(new ActionListener() {
         	 @Override
             public void actionPerformed(ActionEvent e) {
         		List<Pedido> pedidoList = ctrlPd.busquedaPedidos(busquedaField.getText());
         		if (pedidoList.isEmpty())
         			ErrorClientNameNotFound();
         		else
         			pedidosTableModel.mostrarCoincidenciasPedidos(pedidoList);  			 
             }
        });
        botonBaja.addActionListener(new ActionListener() {
        	 @Override
            public void actionPerformed(ActionEvent e) {
        		Pedido PedidoAux = ctrlPd.consultaPedido(busquedaField.getText());
        		 if(busquedaField.getText().isEmpty())
        			 ErrorEmptyField();
        		 else if (PedidoAux == null)
        			 ErrorDniNotFound();
        		 else {
        			 AcceptWindow aW = new AcceptWindow(TablePedidos.this, "¿Estás seguro de eliminar al pedido con DNI " + busquedaField.getText() + "?"
        					 , "Eliminar Pedido"); 
        			if (aW.getOk()) {
        				ctrlPd.bajaPedido(busquedaField.getText());   	
        				List<Pedido> pedList = ctrlPd.busquedaPedidos("");
        				pedidosTableModel.mostrarCoincidenciasPedidos(pedList);  	
        				deleteClientMessage();
        			}
        		 }
            }
       });
        botonModificacion.addActionListener(new ActionListener() {
       	 @Override
           public void actionPerformed(ActionEvent e) {
       		Pedido PedidoAux = ctrlPd.consultaPedido(busquedaField.getText());
       		 if(busquedaField.getText().isEmpty())
       			 ErrorEmptyField();
       		 else if (PedidoAux == null)
       			 ErrorDniNotFound();
       		 else {
       			 AcceptWindow aW = new AcceptWindow(TablePedidos.this, "¿Estás seguro de modificar al pedido con DNI " + busquedaField.getText() + "?"
       					 , "Modificar Pedido"); 
       			if (aW.getOk()) {
       			JTextField codPedField = new JTextField();
       			codPedField.setText(PedidoAux.getCodPed()); 
       			JTextField dniClienteField = new JTextField();
       			dniClienteField.setText(PedidoAux.getDNICliente()); 
       			JTextField fechaField = new JTextField();
       			fechaField.setText(PedidoAux.getFecha()); 
       			JTextField importeField = new JTextField();
       			importeField.setText(Double.toString(PedidoAux.getImporte())); 
       			JTextField estadoField = new JTextField();
       			estadoField.setText(Boolean.toString(PedidoAux.getEstado())); 
       			JTextField cdArticuloField = new JTextField();
       			cdArticuloField.setText(PedidoAux.getCodArticulo()); 
       			TablePedidos.this.setVisible(false);
                ParsePedidos parsePedidos = new ParsePedidos(ctrlCl, ctrlPd, ctrlProd, ctrlProv,
                		codPedField,  dniClienteField,  fechaField ,  importeField,
                		estadoField,  cdArticuloField);
                parsePedidos.getBackButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	  AcceptWindow aW = new AcceptWindow(parsePedidos, "¿Estás seguro? Si has insertado datos no se guardarán"
              					 , "Modificar Pedido"); 
              			if (aW.getOk()) {
                 	   parsePedidos.setVisible(false);
                 	  TablePedidos.this.setVisible(true);
              			}
                    }
                });
                parsePedidos.setVisible(true);
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
    	new MessageWindow(this, "No se encuentran coincidencias con el código de pedido insertado", "Error de parámetros", DimensionMessageWindow);
    }
    private void ErrorClientNameNotFound() {
   	 new MessageWindow(this, "No se encuentran coincidencias con la fecha de pedido insertada", "Error de parámetros", DimensionMessageWindow);
   }
    private void deleteClientMessage() {
      	 new MessageWindow(this, "Pedido eliminado con éxito", "Eliminar pedido", DimensionMessageWindow);
    }
}