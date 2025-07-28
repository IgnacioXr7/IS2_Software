package GUI.view.Pedidos;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import Clases_y_Subsistemas.Pedido;


public class PedidosTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    protected String[] _header = {"Código de Pedido", "DNI cliente", "Fecha (dd//mm//aa)", "Importe", "Estado", "Código de artículo" };
    protected List<Pedido> _pedidos;
  //Se crea el tableModel del subsistema asociado recibiéndose la lista proveniente de la tienda.
    PedidosTableModel() {
    	this._pedidos = new ArrayList<Pedido>();
    }
  //Se guardará la información proveniente de la lista para ser mostrada más adelante
    public void mostrarCoincidenciasPedidos(List<Pedido> pedidos) {
    	_pedidos.clear();
    	_pedidos.addAll(pedidos);
        fireTableDataChanged();
    }
    @Override
    public int getRowCount() {
    	return _pedidos.size();
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
                    return _pedidos.get(rowIndex).getCodPed();
                case 1:
                    return _pedidos.get(rowIndex).getDNICliente();
                case 2:
                    return _pedidos.get(rowIndex).getFecha();
                case 3:
                    return _pedidos.get(rowIndex).getImporte();
                case 4:
                 	return _pedidos.get(rowIndex).getEstado();
                case 5:
                    return _pedidos.get(rowIndex).getCodArticulo();
            }        
        return null;
    }

    public void setClientes(List<Pedido> pedidos) {
    	_pedidos = pedidos;
        fireTableDataChanged();
    }
}

