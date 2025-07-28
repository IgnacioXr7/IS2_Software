package Negociacion_y_Servicio.FachadasImplementacion;

import java.util.List;

import Clases_y_Subsistemas.Cliente;
import Excepciones.ClientException;
import Negociacion_y_Servicio.LogicaServicio.IServicioClientes;

public class ImplementacionCliente implements IFachadaClientes{
	
	private IServicioClientes isc;

	public ImplementacionCliente(IServicioClientes servicio) {
		isc = servicio;
	}

	@Override
	public boolean altaCliente(Cliente cl) throws ClientException{
		//FALTA IMP
		return isc.altaCliente(cl);
	}

	@Override
	public boolean bajaCliente(String dni) {
		//FALTA IMP
		return isc.bajaCliente(dni);
	}

	@Override
	public boolean modificarCliente(String dni, Cliente mod) {
		//FALTA IMP
		return isc.modificarCliente(dni,mod);
	}

	@Override
	public Cliente consultaCliente(String dni) {
		//FALTA IMP
		return isc.consultaCliente(dni);
	}

	@Override
	public List<Cliente> busquedaClientes(String nombre) {
		//FALTA IMP
		return isc.busquedaClientes(nombre);
	}


}
