package Negociacion_y_Servicio.LogicaServicio;

import java.util.List;

import Clases_y_Subsistemas.Cliente;
import DAO.FachadasAccesoDatos.IFachadaDAOClientes;
import Excepciones.ClientException;

public class ServicioCliente implements IServicioClientes{
	
	private IFachadaDAOClientes acceso;

	public ServicioCliente(IFachadaDAOClientes DAO) {
		acceso = DAO;
	}

	@Override
	public boolean altaCliente(Cliente cl)throws ClientException {
		if(!acceso.existeCliente(cl.getDNI())) {
			if(1000>acceso.cantidadClientes()) {
				return acceso.altaClientes(cl);
			}
			else {
				return false;
			}
		}
		else
			throw new ClientException("Ya existe Cliente.");
	}

	@Override
	public boolean bajaCliente(String dni) {
		if(acceso.existeCliente(dni))
			return acceso.bajaClientes(dni);
		else return false;
	}

	@Override
	public boolean modificarCliente(String dni, Cliente mod) {
		if(acceso.existeCliente(dni)) {
			return acceso.modificarCliente(dni, mod);
		}
		else return false;
	}

	@Override
	public Cliente consultaCliente(String dni) {
		return acceso.consultaClientes(dni);
	}

	@Override
	public List<Cliente> busquedaClientes(String nombre) {
		// TODO Auto-generated method stub
		return acceso.busquedaClientes(nombre);
	}

	
}