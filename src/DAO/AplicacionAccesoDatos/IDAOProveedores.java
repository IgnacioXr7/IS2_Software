package DAO.AplicacionAccesoDatos;

import java.util.List;

import Clases_y_Subsistemas.Proveedor;


public interface IDAOProveedores {

	public boolean bajaProveedores(String CIF);

	public boolean modificarProveedores(String CIF, Proveedor modificacion);

	public boolean altaProveedor(Proveedor proveedor);

	public Proveedor consultaProveedor(String CIF);

	public List<Proveedor> busquedaProveedor(String nombre);
	
	public boolean existeProveedor (String CIF);
	
	public int getLim ();

}
