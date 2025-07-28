package Controller;

import java.util.List;

import Clases_y_Subsistemas.Cliente;
import Excepciones.ClientException;
import Negociacion_y_Servicio.FachadasImplementacion.IFachadaClientes;

public class ControllerClientes {

	IFachadaClientes _IFClientes;
		
	public ControllerClientes(IFachadaClientes f) {
			this._IFClientes = f;
	}
	public boolean altaCliente(Cliente cl) throws ClientException{
		return this._IFClientes.altaCliente(cl);
	}
	public boolean bajaCliente(String dni) {
		return this._IFClientes.bajaCliente(dni);
	}
	public boolean modificarCliente(String dni, Cliente mod) {
		return this._IFClientes.modificarCliente(dni, mod);
	}
	public Cliente consultaCliente(String dni) {
		return this._IFClientes.consultaCliente(dni);
	}
	public List<Cliente> busquedaClientes(String nombre) {
		return this._IFClientes.busquedaClientes(nombre);
	}
}
