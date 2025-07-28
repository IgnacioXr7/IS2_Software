package Negociacion_y_Servicio.FachadasImplementacion;

import java.util.List;

import Clases_y_Subsistemas.Pedido;
import Excepciones.PedidoException;
import Negociacion_y_Servicio.LogicaServicio.IServicioPedidos;

public class ImplementacionPedido implements IFachadaPedidos{
	
	private IServicioPedidos ispd;
	
	public ImplementacionPedido(IServicioPedidos servicio)  {
		ispd = servicio;
	}

	@Override
	public boolean altaPedido(Pedido pd) throws PedidoException{
		//FALTA IMP
		return ispd.altaPedido(pd);
	}

	@Override
	public boolean bajaPedido(String idPedido) {
		//FALTA IMP
		return ispd.bajaPedido(idPedido);
	}

	@Override
	public boolean modificarPedido(String idPedido, Pedido mod) {
		//FALTA IMP
		return ispd.modificarPedido(idPedido,mod);
	}

	@Override
	public Pedido consultaPedido(String id) {
		//FALTA IMP
		return ispd.consultaPedido(id);
	}

	@Override
	public List<Pedido> busquedaPedidos(String fecha) {
		//FALTA IMP
		return ispd.busquedaPedidos(fecha);
	}


}
