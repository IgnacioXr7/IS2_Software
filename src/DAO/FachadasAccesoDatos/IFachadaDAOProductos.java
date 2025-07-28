package DAO.FachadasAccesoDatos;

import java.util.List;

import Clases_y_Subsistemas.Producto;

public interface IFachadaDAOProductos {
	
	public boolean altaProducto(Producto producto);
	public boolean bajaProducto(String cod);
	public boolean modificarProducto(String CodPr, Producto aMod);
	public Producto consultaProducto(String codPr);
	public List <Producto> busquedaProducto(String nombre);
	public boolean existeProducto(String codProd);
	
	public int cantidadProductos();
	
	
}