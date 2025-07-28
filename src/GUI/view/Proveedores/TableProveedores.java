package GUI.view.Proveedores;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import Clases_y_Subsistemas.Proveedor;
import Controller.ControllerClientes;
import Controller.ControllerPedidos;
import Controller.ControllerProductos;
import Controller.ControllerProveedores;
import GUI.view.TableWindow;
import GUI.view.AcceptWindow;
import GUI.view.InfoTable;
import GUI.view.MessageWindow;

public class TableProveedores extends TableWindow{
    private static final long serialVersionUID = 1L;

    public TableProveedores(ControllerClientes ctrlCl, ControllerPedidos ctrlPd, ControllerProductos ctrlProd,
			ControllerProveedores ctrlProv) {
        super("Tabla proveedores", ctrlCl, ctrlPd, ctrlProd, ctrlProv);
        this.setSize(this.actualWidth, this.actualHeight);
        initGUI();
    }
    
    public void initGUI() {
        ProveedoresTableModel proveedoresTableModel = new ProveedoresTableModel();
        InfoTable contentPanelGroups = new InfoTable(proveedoresTableModel);
        contentPanelGroups.setPreferredSize(new Dimension(tableWidth, tableHeight));  
        tablePanel.add(contentPanelGroups);
        layeredPanel.add(tablePanel, Integer.valueOf(2));    
        botonAlta = new JButton("Nuevo proveedor");
        setButtonProperties(botonAlta);
        botonConsulta = new JButton("Consulta proveedor");
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
           		Proveedor proveedorAux = ctrlProv.consultaProveedor(busquedaField.getText());
           		 if(busquedaField.getText().isEmpty())
           			 ErrorEmptyField();
           		 else if (proveedorAux == null)
           			ErrorCodprNotFound();
           		 else {
           			JTextField cifField = new JTextField();
           			cifField.setText(proveedorAux.getCIF()); 
           			JTextField nombreField = new JTextField();
           			nombreField.setText(proveedorAux.getNombre()); 
           			JTextField domicilioSocialField = new JTextField();
           			domicilioSocialField.setText(proveedorAux.getDomicilioSocial()); 
           			JTextField telefonoField = new JTextField();
           			telefonoField.setText(Integer.toString(proveedorAux.getTelefono())); 
           			TableProveedores.this.setVisible(false);
                    ParseProveedores parseproveedores = new ParseProveedores(ctrlCl, ctrlPd, ctrlProd, ctrlProv,
                    		cifField, nombreField, domicilioSocialField, telefonoField, consultaValue);
                    parseproveedores.getBackButton().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                        	parseproveedores.setVisible(false);
                        	TableProveedores.this.setVisible(true);
                        }
                    });
                    parseproveedores.setVisible(true);
           		  }		
               }
        });

        botonAlta.addActionListener(new ActionListener() {
          	 @Override
               public void actionPerformed(ActionEvent e) {
          		TableProveedores.this.setVisible(false);
                   ParseProveedores parseproveedores = new ParseProveedores(ctrlCl, ctrlPd, ctrlProd, ctrlProv);
                   parseproveedores.getBackButton().addActionListener(new ActionListener() {
                       @Override
                       public void actionPerformed(ActionEvent e) {
                    	   AcceptWindow aW = new AcceptWindow(TableProveedores.this, "¿Estás seguro? Si has insertado datos no se guardarán"
                 					 , "Alta proveedor"); 
                 			if (aW.getOk()) {
                 				parseproveedores.setVisible(false);
                 				TableProveedores.this.setVisible(true);
                 			}
                       }
                   });
                   parseproveedores.setVisible(true);
               }
          });
        botonBusqueda.addActionListener(new ActionListener() {
         	 @Override
             public void actionPerformed(ActionEvent e) {
         		List<Proveedor> provList = ctrlProv.busquedaProveedor(busquedaField.getText());
         		if (provList.isEmpty())
         			ErrorProductNameNotFound();
         		else
         			proveedoresTableModel.mostrarCoincidenciasProveedores(provList);  			 
             }
        });
        botonBaja.addActionListener(new ActionListener() {
        	 @Override
            public void actionPerformed(ActionEvent e) {
        		 Proveedor proveedorAux = ctrlProv.consultaProveedor(busquedaField.getText());
           		 if(busquedaField.getText().isEmpty())
           			 ErrorEmptyField();
           		 else if (proveedorAux == null)
           			ErrorCodprNotFound();
        		 else {
        			 AcceptWindow aW = new AcceptWindow(TableProveedores.this, "¿Estás seguro de eliminar el proveedor con código " + busquedaField.getText() + "?"
        					 , "Eliminar proveedor"); 
        			if (aW.getOk()) {
        				ctrlProv.bajaProveedor(busquedaField.getText());   	
        				List<Proveedor> provList = ctrlProv.busquedaProveedor("");
        				proveedoresTableModel.mostrarCoincidenciasProveedores(provList);  	
        				deleteProductMessage();
        			}
        		 }
            }
       });
        botonModificacion.addActionListener(new ActionListener() {
       	 @Override
           public void actionPerformed(ActionEvent e) {
       		Proveedor proveedorAux = ctrlProv.consultaProveedor(busquedaField.getText());
       		 if(busquedaField.getText().isEmpty())
       			 ErrorEmptyField();
       		 else if (proveedorAux == null)
       			ErrorCodprNotFound();
       		 else {
       			 AcceptWindow aW = new AcceptWindow(TableProveedores.this, "¿Estás seguro de modificar el proveedor con código " + busquedaField.getText() + "?"
       					 , "Modificar proveedor"); 
       			if (aW.getOk()) {
       			JTextField cifField = new JTextField();
       			cifField.setText(proveedorAux.getCIF()); 
       			JTextField nombreField = new JTextField();
       			nombreField.setText(proveedorAux.getNombre()); 
       			JTextField domicilioSocialField = new JTextField();
       			domicilioSocialField.setText(proveedorAux.getDomicilioSocial()); 
       			JTextField telefonoField = new JTextField();
       			telefonoField.setText(Integer.toString(proveedorAux.getTelefono())); 
       			TableProveedores.this.setVisible(false);
                ParseProveedores parseproveedores = new ParseProveedores(ctrlCl, ctrlPd, ctrlProd, ctrlProv,
                		cifField, nombreField, domicilioSocialField, telefonoField);
                parseproveedores.getBackButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	  AcceptWindow aW = new AcceptWindow(parseproveedores,  "¿Estás seguro? Si has insertado datos no se guardarán"
              					 , "Modificar proveedor"); 
              			if (aW.getOk()) {
                    	parseproveedores.setVisible(false);
                    	TableProveedores.this.setVisible(true);
              			}
                    }
                });
                parseproveedores.setVisible(true);
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
    private void ErrorCodprNotFound() {
    	new MessageWindow(this, "No se encuentran coincidencias con el CIF de Proveedor insertado", "Error de parámetros", DimensionMessageWindow);
    }
    private void ErrorProductNameNotFound() {
   	 new MessageWindow(this, "No se encuentran coincidencias con el nombre de Proveedor insertado", "Error de parámetros", DimensionMessageWindow);
   }
    private void deleteProductMessage() {
      	 new MessageWindow(this, "Proveedor eliminado con éxito", "Eliminar Proveedor", DimensionMessageWindow);
    }
}
