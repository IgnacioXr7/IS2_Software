package Negociacion_y_Servicio.FachadasImplementacion;
import java.util.List;

import Clases_y_Subsistemas.Cliente;
import Excepciones.ClientException;

public interface IFachadaClientes {
	public boolean altaCliente(Cliente cl) throws ClientException;
	public boolean bajaCliente(String dni);
	public boolean modificarCliente(String dni, Cliente mod);
	public Cliente consultaCliente(String dni);
	public List<Cliente> busquedaClientes(String nombre);
}
