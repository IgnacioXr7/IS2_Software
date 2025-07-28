package DAO.FachadasAccesoDatos;

import java.util.List;

import Clases_y_Subsistemas.Proveedor;
import DAO.AplicacionAccesoDatos.IDAOProveedores;

public class ImpDAOProvedor implements IFachadaDAOProveedores{

	IDAOProveedores apliDAOProv;
	
	public ImpDAOProvedor(IDAOProveedores dAO) {
		apliDAOProv = dAO;
	}
	
	public boolean existeProveedor(String cif) {
		// TODO Auto-generated method stub
		return apliDAOProv.existeProveedor(cif);
	}

	public int lim() {
		// TODO Auto-generated method stub
		return apliDAOProv.getLim();
	}

	public boolean altaProveedor(Proveedor nuevoProveedor) {
		// TODO Auto-generated method stub
		return apliDAOProv.altaProveedor(nuevoProveedor);
	}

	public boolean bajaProveedor(String cIF) {
		// TODO Auto-generated method stub
		return apliDAOProv.bajaProveedores(cIF);
	}

	public List<Proveedor> busquedaProveedor(String nombre) {
		// TODO Auto-generated method stub
		return apliDAOProv.busquedaProveedor(nombre);
	}

	public Proveedor consultaProveedor(String CIF) {
		// TODO Auto-generated method stub
		return apliDAOProv.consultaProveedor(CIF);
	}

	public boolean modificarProveedor(String CIF, Proveedor nuevoProveedor) {
		// TODO Auto-generated method stub
		return apliDAOProv.modificarProveedores(CIF, nuevoProveedor);
	}
	
}
