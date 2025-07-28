package DAO.FachadasAccesoDatos;

import java.util.List;

import Clases_y_Subsistemas.Cliente;

public interface IFachadaDAOClientes {
	
	public boolean altaClientes(Cliente cliente);
	public boolean bajaClientes(String DNI);
	public boolean modificarCliente(String DNI, Cliente aMod);
	public Cliente consultaClientes (String DNI);
	public List<Cliente> busquedaClientes(String nombre);
	public boolean existeCliente(String DNICliente);
	 
	public int cantidadClientes();
}
