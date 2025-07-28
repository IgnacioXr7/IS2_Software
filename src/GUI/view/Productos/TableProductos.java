package GUI.view.Productos;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import Clases_y_Subsistemas.Producto;
import Controller.ControllerClientes;
import Controller.ControllerPedidos;
import Controller.ControllerProductos;
import Controller.ControllerProveedores;
import GUI.view.TableWindow;
import GUI.view.AcceptWindow;
import GUI.view.InfoTable;
import GUI.view.MessageWindow;

public class TableProductos extends TableWindow{
    private static final long serialVersionUID = 1L;

    public TableProductos(ControllerClientes ctrlCl, ControllerPedidos ctrlPd, ControllerProductos ctrlProd,
			ControllerProveedores ctrlProv) {
        super("Tabla productos", ctrlCl, ctrlPd, ctrlProd, ctrlProv);
        this.setSize(this.actualWidth, this.actualHeight);
        initGUI();
    }
    
    public void initGUI() {
        ProductosTableModel productosTableModel = new ProductosTableModel();
        InfoTable contentPanelGroups = new InfoTable(productosTableModel);
        contentPanelGroups.setPreferredSize(new Dimension(tableWidth, tableHeight));  
        tablePanel.add(contentPanelGroups);
        layeredPanel.add(tablePanel, Integer.valueOf(2));    
        botonAlta = new JButton("Nuevo producto");
        setButtonProperties(botonAlta);
        botonConsulta = new JButton("Consulta producto");
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
           		Producto productoAux = ctrlProd.consultaProducto(busquedaField.getText());
           		 if(busquedaField.getText().isEmpty())
           			 ErrorEmptyField();
           		 else if (productoAux == null)
           			ErrorCodprNotFound();
           		 else {
           			JTextField nombreField = new JTextField();
           			nombreField.setText(productoAux.getNombre()); 
           			JTextField codPrField = new JTextField();
           			codPrField.setText(productoAux.getCodPr()); 
           			JTextField marcaField = new JTextField();
           			marcaField.setText(productoAux.getMarca()); 
           			JTextField modeloField = new JTextField();
           			modeloField.setText(productoAux.getModelo()); 
           			JTextField precioField = new JTextField();
           			precioField.setText(Double.toString(productoAux.getPrecio())); 
           			JTextField unidadesField = new JTextField();
           			unidadesField.setText(Integer.toString(productoAux.getStock())); 
           			TableProductos.this.setVisible(false);
                    ParseProductos parseproductos = new ParseProductos(ctrlCl, ctrlPd, ctrlProd, ctrlProv,
                    		nombreField,  codPrField,  marcaField ,  modeloField,
                    		precioField,  unidadesField, consultaValue);
                    parseproductos.getBackButton().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                        	parseproductos.setVisible(false);
                     	  TableProductos.this.setVisible(true);
                        }
                    });
                    parseproductos.setVisible(true);
           		  }   		   			
               }
        });

        botonAlta.addActionListener(new ActionListener() {
          	 @Override
               public void actionPerformed(ActionEvent e) {
          		TableProductos.this.setVisible(false);
                   ParseProductos parseProductos = new ParseProductos(ctrlCl, ctrlPd, ctrlProd, ctrlProv);
                   parseProductos.getBackButton().addActionListener(new ActionListener() {
                       @Override
                       public void actionPerformed(ActionEvent e) {
                    	   AcceptWindow aW = new AcceptWindow(TableProductos.this, "¿Estás seguro? Si has insertado datos no se guardarán"
                 					 , "Alta producto"); 
                 			if (aW.getOk()) {
                 				parseProductos.setVisible(false);
                 				TableProductos.this.setVisible(true);
                 			}
                       }
                   });
                   parseProductos.setVisible(true);
               }
          });
        botonBusqueda.addActionListener(new ActionListener() {
         	 @Override
             public void actionPerformed(ActionEvent e) {
         		List<Producto> productList = ctrlProd.busquedaProductos(busquedaField.getText());
         		if (productList.isEmpty())
         			ErrorProductNameNotFound();
         		else
         			productosTableModel.mostrarCoincidenciasProductos(productList);  			 
             }
        });
        botonBaja.addActionListener(new ActionListener() {
        	 @Override
            public void actionPerformed(ActionEvent e) {
        		 Producto productoAux = ctrlProd.consultaProducto(busquedaField.getText());
           		 if(busquedaField.getText().isEmpty())
           			 ErrorEmptyField();
           		 else if (productoAux == null)
           			ErrorCodprNotFound();
        		 else {
        			 AcceptWindow aW = new AcceptWindow(TableProductos.this, "¿Estás seguro de eliminar el producto con código " + busquedaField.getText() + "?"
        					 , "Eliminar producto"); 
        			if (aW.getOk()) {
        				ctrlProd.bajaProducto(busquedaField.getText());   	
        				List<Producto> productList = ctrlProd.busquedaProductos("");
        				productosTableModel.mostrarCoincidenciasProductos(productList);  	
        				deleteProductMessage();
        			}
        		 }
            }
       });
        botonModificacion.addActionListener(new ActionListener() {
       	 @Override
           public void actionPerformed(ActionEvent e) {
       		Producto productoAux = ctrlProd.consultaProducto(busquedaField.getText());
       		 if(busquedaField.getText().isEmpty())
       			 ErrorEmptyField();
       		 else if (productoAux == null)
       			ErrorCodprNotFound();
       		 else {
       			 AcceptWindow aW = new AcceptWindow(TableProductos.this, "¿Estás seguro de modificar el producto con código " + busquedaField.getText() + "?"
       					 , "Modificar producto"); 
       			if (aW.getOk()) {
       			JTextField nombreField = new JTextField();
       			nombreField.setText(productoAux.getNombre()); 
       			JTextField codPrField = new JTextField();
       			codPrField.setText(productoAux.getCodPr()); 
       			JTextField marcaField = new JTextField();
       			marcaField.setText(productoAux.getMarca()); 
       			JTextField modeloField = new JTextField();
       			modeloField.setText(productoAux.getModelo()); 
       			JTextField precioField = new JTextField();
       			precioField.setText(Double.toString(productoAux.getPrecio())); 
       			JTextField unidadesField = new JTextField();
       			unidadesField.setText(Integer.toString(productoAux.getStock())); 
       			TableProductos.this.setVisible(false);
                ParseProductos parseproductos = new ParseProductos(ctrlCl, ctrlPd, ctrlProd, ctrlProv,
                		nombreField,  codPrField,  marcaField ,  modeloField,
                		precioField,  unidadesField);
                parseproductos.getBackButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	  AcceptWindow aW = new AcceptWindow(parseproductos,  "¿Estás seguro? Si has insertado datos no se guardarán"
              					 , "Modificar producto"); 
              			if (aW.getOk()) {
                    	parseproductos.setVisible(false);
                 	  TableProductos.this.setVisible(true);
              			}
                    }
                });
                parseproductos.setVisible(true);
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
    	new MessageWindow(this, "No se encuentran coincidencias con el código de producto insertado", "Error de parámetros", DimensionMessageWindow);
    }
    private void ErrorProductNameNotFound() {
   	 new MessageWindow(this, "No se encuentran coincidencias con el nombre de producto insertado", "Error de parámetros", DimensionMessageWindow);
   }
    private void deleteProductMessage() {
      	 new MessageWindow(this, "Producto eliminado con éxito", "Eliminar producto", DimensionMessageWindow);
    }
}