package Negociacion_y_Servicio.LogicaServicio;

import java.util.List;

import Clases_y_Subsistemas.Producto;
import DAO.FachadasAccesoDatos.IFachadaDAOProductos;
import Excepciones.ProductException;

public class ServicioProducto implements IServicioProductos {
	
	private  IFachadaDAOProductos idaoProductos;
	
	
	public ServicioProducto(IFachadaDAOProductos DAO) {
		idaoProductos = DAO;
	}

	@Override
	public boolean altaProductos(Producto pr) throws ProductException{
		
		if(!idaoProductos.existeProducto(pr.getCodPr())) 	
			if(idaoProductos.cantidadProductos() <1000)
				 return idaoProductos.altaProducto(pr);
			else
				return false;
		else
			throw new ProductException("Ya existe producto");
	}

	@Override
	public boolean bajaProductos(String codPr) {
		if(idaoProductos.existeProducto(codPr)) 
			 idaoProductos.bajaProducto(codPr);
	
		return false; 		
	}

	@Override
	public boolean modificarProductos(String codPr, Producto mod) {
		if(idaoProductos.existeProducto(codPr)) 
			return idaoProductos.modificarProducto(codPr, mod);
			
		return false;
	}

	@Override
	public Producto consultaProducto(String codPr) {
		// TODO Auto-generated method stub
		return idaoProductos.consultaProducto(codPr);
	}

	@Override
	public List<Producto> busquedaProductos(String nombre) {
		// TODO Auto-generated method stub
		return idaoProductos.busquedaProducto(nombre);
	}
	
}