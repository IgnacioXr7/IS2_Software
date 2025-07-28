package GUI.view.Productos;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import Clases_y_Subsistemas.Producto;


public class ProductosTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    protected String[] _header = {"Código de producto","Nombre", "Marca", "Modelo", "Precio", "Unidades"};
    protected List<Producto> _productos;
    //Se crea el tableModel del subsistema asociado recibiéndose la lista proveniente de la tienda.
    ProductosTableModel() {
    	this._productos = new ArrayList<Producto>();
    }
    //Se guardará la información proveniente de la lista para ser mostrada más adelante
    public void mostrarCoincidenciasProductos(List<Producto> productos) {
    	_productos.clear();
    	_productos.addAll(productos);
        fireTableDataChanged();
    }
   
    @Override
    public int getRowCount() {
           return _productos.size();
    }

    @Override
    public int getColumnCount() {
        return _header.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return _header[columnIndex];
    }
    //Se cogen los atributos correspondientes de cada subsistema para ser mostrados
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
            switch (columnIndex) {
                case 0:
                	return _productos.get(rowIndex).getCodPr();
                case 1:
                	return _productos.get(rowIndex).getNombre();
                case 2:
                	return _productos.get(rowIndex).getMarca();
                case 3:
                    return _productos.get(rowIndex).getModelo();
                case 4:
                	return _productos.get(rowIndex).getPrecio();
                case 5:
                   return _productos.get(rowIndex).getStock();
            }      
        return null;
    }

    public void setProductos(List<Producto> productos) {
    	_productos = productos;
        fireTableDataChanged();
    }
}


