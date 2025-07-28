package DAO.FachadasAccesoDatos;

import java.util.List;

import Clases_y_Subsistemas.Cliente;
import DAO.AplicacionAccesoDatos.IDAOClientes;

public class ImpDAOClientes implements IFachadaDAOClientes{
	
	private IDAOClientes acceso;
	
	public ImpDAOClientes(IDAOClientes dAOClientes) {
		acceso = dAOClientes;
	}

	@Override
	public boolean altaClientes(Cliente cliente) {
		return acceso.altaCliente(cliente);
	}

	@Override
	public boolean bajaClientes(String DNI) {
		return acceso.eliminaCliente(DNI);
	}

	@Override
	public boolean modificarCliente(String DNI, Cliente aMod) {
		return acceso.modificaCliente(DNI, aMod);
	}

	@Override
	public Cliente consultaClientes(String DNI) {
		return acceso.consultaCliente(DNI);
	}

	@Override
	public List<Cliente> busquedaClientes(String nombre) {
		return acceso.buscaCliente(nombre);
	}

	@Override
	public boolean existeCliente(String DNICliente) {
		return acceso.existeCliente(DNICliente);
	}

	@Override
	public int cantidadClientes() {
		return acceso.dameEspacio();
	}

}
