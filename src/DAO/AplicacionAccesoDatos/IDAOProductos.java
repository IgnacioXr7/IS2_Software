package DAO.AplicacionAccesoDatos;

import java.util.List;

import Clases_y_Subsistemas.Producto;

public interface IDAOProductos {

	public boolean altaProducto(Producto id);
	public boolean bajaProducto(String CodPr);
	public Producto consultaProducto(String CodPr);
	public List<Producto> busquedaProducto(String nombre);
	public boolean modificacionProducto(String CodPr, Producto TProducto);
	public boolean existeProducto(String codPr);
	
	public int lim();
}
