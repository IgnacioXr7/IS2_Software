package Controller;

import java.util.List;

import Clases_y_Subsistemas.Pedido;
import Excepciones.PedidoException;
import Negociacion_y_Servicio.FachadasImplementacion.IFachadaPedidos;

public class ControllerPedidos {

	
	IFachadaPedidos _IFPedidos;
	
	public ControllerPedidos(IFachadaPedidos f) {
		this._IFPedidos = f;
	}
	
	
	public boolean altaPedido(Pedido pd) throws PedidoException{
		return this._IFPedidos.altaPedido(pd);	
	}
	public boolean bajaPedido(String idPedido) {
		return this._IFPedidos.bajaPedido(idPedido);
	}
	public boolean modificarPedido(String idPedido, Pedido mod) {
		return this._IFPedidos.modificarPedido(idPedido, mod);
	}
	public Pedido consultaPedido(String id) {
		return this._IFPedidos.consultaPedido(id);
	}
	public List<Pedido> busquedaPedidos(String fecha){
		return this._IFPedidos.busquedaPedidos(fecha);
	}

}