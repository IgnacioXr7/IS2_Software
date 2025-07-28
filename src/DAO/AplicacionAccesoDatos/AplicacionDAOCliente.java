package DAO.AplicacionAccesoDatos;

import java.util.List;

import Clases_y_Subsistemas.Cliente;
import Clases_y_Subsistemas.Tienda;

public class AplicacionDAOCliente implements IDAOClientes{
	
	private Tienda _banco_datos;

	public AplicacionDAOCliente(Tienda store) {
		_banco_datos = store;
	}

	@Override
	public boolean altaCliente(Cliente cliente) {
		return _banco_datos.insertarCl(cliente);
	}

	@Override
	public boolean eliminaCliente(String DNICliente) {
		return _banco_datos.quitarCl(DNICliente);
	}

	@Override
	public Cliente consultaCliente(String DNICliente) {
		return _banco_datos.consultaCl(DNICliente);
	}

	@Override
	public List<Cliente> buscaCliente(String nombre) {
		return _banco_datos.coincidenciasCl(nombre);
	}

	@Override
	public boolean modificaCliente(String DNICliente, Cliente cliente) {
		return _banco_datos.modCl(DNICliente,cliente);
	}

	@Override
	public boolean existeCliente(String DNICliente) {
		return _banco_datos.exCliente(DNICliente);
	}

	@Override
	public int dameEspacio() {
		return _banco_datos.tamCl();
	}

}
