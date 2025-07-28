package GUI.view.Proveedores;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import Clases_y_Subsistemas.Proveedor;


public class ProveedoresTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    protected String[] _header = {"CIF", "Nombre", "Domicilio social", "teléfono"};
    protected List<Proveedor> _proveedores;
    //Se cogen los atributos correspondientes de cada subsistema para ser mostrados
    ProveedoresTableModel() {
    	_proveedores = new ArrayList<Proveedor>();
    }
  //Se guardará la información proveniente de la lista para ser mostrada más adelante
    public void mostrarCoincidenciasProveedores(List<Proveedor> proveedores) {
    	_proveedores.clear();
    	_proveedores.addAll(proveedores);
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
          return _proveedores.size();
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
                    return _proveedores.get(rowIndex).getCIF();
                case 1:
                    return _proveedores.get(rowIndex).getNombre();
                case 2:
                    return _proveedores.get(rowIndex).getDomicilioSocial();
                case 3:
                    return _proveedores.get(rowIndex).getTelefono();
            }
            return null;
    }

    public void setClientes(List<Proveedor> proveedores) {
    	_proveedores = proveedores;
        fireTableDataChanged();
    }
}