package DAO.FachadasAccesoDatos;

import java.util.List;

import Clases_y_Subsistemas.Proveedor;


public interface IFachadaDAOProveedores {
	
	public boolean existeProveedor(String cif);
	public boolean altaProveedor(Proveedor nuevoProveedor);
	public boolean bajaProveedor(String cIF);
	public List<Proveedor> busquedaProveedor(String nombre);
	public Proveedor consultaProveedor(String CIF);
	public boolean modificarProveedor(String CIF, Proveedor nuevoProveedor);
	
	public int lim();
}
