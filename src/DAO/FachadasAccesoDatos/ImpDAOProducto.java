package DAO.FachadasAccesoDatos;

import java.util.List;

import Clases_y_Subsistemas.Producto;
import DAO.AplicacionAccesoDatos.IDAOProductos;

public class ImpDAOProducto implements IFachadaDAOProductos {

	IDAOProductos acceso;
	
	public ImpDAOProducto(IDAOProductos dAO) {
		 acceso = dAO;
	}
	
	@Override
	public boolean altaProducto(Producto producto) {
		// TODO Auto-generated method stub
		 return acceso.altaProducto(producto);
	}

	@Override
	public boolean bajaProducto(String cod) {
		// TODO Auto-generated method stub
		 return acceso.bajaProducto(cod);
	}

	@Override
	public boolean modificarProducto(String CodPr, Producto aMod) {
		// TODO Auto-generated method stub
		return acceso.modificacionProducto(CodPr, aMod);
	}

	@Override
	public Producto consultaProducto(String codPr) {
		// TODO Auto-generated method stub
		return acceso.consultaProducto(codPr);
	}

	@Override
	public List<Producto> busquedaProducto(String nombre) {
		// TODO Auto-generated method stub
		return acceso.busquedaProducto(nombre);
	}

	@Override
	public boolean existeProducto(String codProd) {
		// TODO Auto-generated method stub
		return acceso.existeProducto(codProd);
	}

	@Override
	public int cantidadProductos() {
		// TODO Auto-generated method stub
		return acceso.lim();
	}

}