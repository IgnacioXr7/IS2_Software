package DAO.AplicacionAccesoDatos;

import java.util.List;

import Clases_y_Subsistemas.Pedido;

public interface IDAOPedidos {

	public  boolean altaPedido(Pedido pedido);
	public boolean bajaPedido(String CodPed);
	public Pedido consultaPedido(String id);
	public List<Pedido> buscaPedido(String fecha);
	public boolean modificaPedido(Pedido pedido, String CodPed);	
	public boolean existePedido (String CodPed);
	
}
