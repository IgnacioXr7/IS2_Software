package Negociacion_y_Servicio.LogicaServicio;

import java.util.List;

import Clases_y_Subsistemas.Pedido;
import DAO.FachadasAccesoDatos.IFachadaDAOClientes;
import DAO.FachadasAccesoDatos.IFachadaDAOPedidos;
import DAO.FachadasAccesoDatos.IFachadaDAOProductos;
import Excepciones.PedidoException;

public class ServicioPedido implements IServicioPedidos{

	private IFachadaDAOPedidos accesoPd;
	private IFachadaDAOProductos accesoPr;
	private IFachadaDAOClientes accesoCl;
	
	
	public ServicioPedido(IFachadaDAOPedidos DAOPedidos, IFachadaDAOClientes DAOClientes,
			IFachadaDAOProductos DAOProductos) {
		accesoPd = DAOPedidos;
		accesoPr = DAOProductos;
		accesoCl = DAOClientes;
	}

	@Override
	public boolean altaPedido(Pedido pd) throws PedidoException{
	 if(!accesoPd.existePedido(pd.getCodPed())) {
		if(accesoCl.existeCliente(pd.getDNICliente())) { 
			if(accesoPr.existeProducto(pd.getCodArticulo())) {
				if(accesoPr.consultaProducto(pd.getCodArticulo()).getStock() > 0) {
					return accesoPd.altaPedido(pd);
				}
				else
					return false;
			}
			else {
				throw new PedidoException("No existe producto");
			}
		}
		else {
			throw new PedidoException("No existe cliente");
		}		
	 }	else
		 throw new PedidoException("Existe pedido");
	 
	}

	@Override
	public boolean bajaPedido(String CpodPed) {
		if(accesoPd.existePedido(CpodPed)) 
			return accesoPd.bajaPedido(CpodPed);
		
		return false;
	}

	@Override
	public boolean modificarPedido(String CodPedido, Pedido mod) {
		if(accesoPd.existePedido(CodPedido))
			return accesoPd.modificaPedido(mod, CodPedido);
		
		return false;
	}

	@Override
	public Pedido consultaPedido(String CodPed) {
	
		return accesoPd.consultaPedido(CodPed);
	}

	@Override
	public List<Pedido> busquedaPedidos(String fecha) {
		
		return accesoPd.buscaPedido(fecha);
	}

}

