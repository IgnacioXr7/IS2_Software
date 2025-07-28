package DAO.AplicacionAccesoDatos;

import java.util.List;

import Clases_y_Subsistemas.Pedido;
import Clases_y_Subsistemas.Tienda;

public class AplicacionDAOPedidos implements IDAOPedidos{
	
	private Tienda _banco_datos;

	public AplicacionDAOPedidos(Tienda store) {
		_banco_datos = store;
	}

	@Override
	public boolean altaPedido(Pedido pedido) {
		return _banco_datos.insertarPd(pedido);
	}

	@Override
	public boolean bajaPedido(String CodPed) {
		return _banco_datos.quitarPd(CodPed);
	}

	@Override
	public Pedido consultaPedido(String id) {
		return _banco_datos.consultaPd(id);
	}

	@Override
	public List<Pedido> buscaPedido(String fecha) {
		return _banco_datos.coincidenciaPd(fecha);
	}

	@Override
	public boolean modificaPedido(Pedido pedido, String CodPed) {
		return _banco_datos.modPd(pedido, CodPed);
	}

	@Override
	public boolean existePedido(String CodPed) {
		return _banco_datos.exPedido(CodPed);
	}
	

}
