package DAO.AplicacionAccesoDatos;

import java.util.List;

import Clases_y_Subsistemas.Cliente;

public interface IDAOClientes {

	public  boolean altaCliente(Cliente cliente);
	public boolean eliminaCliente(String DNICliente);
	public Cliente consultaCliente(String DNICliente);
	public List<Cliente> buscaCliente(String nombre);
	public boolean modificaCliente(String DNICliente, Cliente cliente);
	public boolean existeCliente(String DNICliente);
	
	public int dameEspacio();
	

}
