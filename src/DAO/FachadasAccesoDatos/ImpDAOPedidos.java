package DAO.FachadasAccesoDatos;

import java.util.List;

import Clases_y_Subsistemas.Pedido;
import DAO.AplicacionAccesoDatos.IDAOPedidos;

public class ImpDAOPedidos implements IFachadaDAOPedidos{

	private IDAOPedidos IDAOpd;
	
	public ImpDAOPedidos(IDAOPedidos dAO) {
		 IDAOpd = dAO;
	}
	
	@Override
	public boolean altaPedido(Pedido pedido) { return IDAOpd.altaPedido(pedido); }

	@Override
	public boolean bajaPedido(String CodPed) { return IDAOpd.bajaPedido(CodPed); }

	@Override
	public boolean modificaPedido(Pedido pedido, String CodPed) { return IDAOpd.modificaPedido(pedido, CodPed); }

	@Override
	public Pedido consultaPedido(String id ) { return IDAOpd.consultaPedido(id); }

	@Override
	public List<Pedido> buscaPedido(String fecha) { return IDAOpd.buscaPedido(fecha); }

	@Override
	public boolean existePedido(String CodPed) { return IDAOpd.existePedido(CodPed);}
	
}
