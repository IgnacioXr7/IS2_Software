package Negociacion_y_Servicio.FachadasImplementacion;
import java.util.List;

import Clases_y_Subsistemas.Pedido;
import Excepciones.PedidoException;


public interface IFachadaPedidos {
	public boolean altaPedido(Pedido pd) throws PedidoException;
	public boolean bajaPedido(String idPedido);
	public boolean modificarPedido(String idPedido, Pedido mod);
	public Pedido consultaPedido(String id);
	public List<Pedido> busquedaPedidos(String fecha);
}
