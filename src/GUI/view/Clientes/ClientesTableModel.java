package GUI.view.Clientes;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import Clases_y_Subsistemas.Cliente;


public class ClientesTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    protected String[] _header = {"DNI", "Nombre", "Apellidos", "Correo", "Dirección", "Teléfono"};
    protected List<Cliente> _clientes;
    //Se crea el tableModel del subsistema asociado recibiéndose la lista proveniente de la tienda.
    ClientesTableModel() {
        this._clientes = new ArrayList<Cliente>();
    }
    //Se guardará la información proveniente de la lista para ser mostrada más adelante
    public void mostrarCoincidenciasClientes(List<Cliente> clientes) {
        _clientes.clear();
        _clientes.addAll(clientes);
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
            return _clientes.size();
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
                    return _clientes.get(rowIndex).getDNI();
                case 1:
                    return _clientes.get(rowIndex).getNombre();
                case 2:
                	 return _clientes.get(rowIndex).getApellidos();
                case 3:
                   return _clientes.get(rowIndex).getCorreo();
                case 4:
                	return _clientes.get(rowIndex).getDomicilio();
                case 5:
                   return _clientes.get(rowIndex).getTelefono();
            }
        return null;
    }

    public void setClientes(List<Cliente> clientes) {
        _clientes = clientes;
        fireTableDataChanged();
    }
}

