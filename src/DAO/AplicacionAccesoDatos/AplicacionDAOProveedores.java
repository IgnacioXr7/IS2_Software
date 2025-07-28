package DAO.AplicacionAccesoDatos;

import java.util.List;

import Clases_y_Subsistemas.Proveedor;
import Clases_y_Subsistemas.Tienda;


public class AplicacionDAOProveedores implements IDAOProveedores {

	private Tienda _banco_datos;

	public AplicacionDAOProveedores(Tienda store) {
		_banco_datos = store;
	}
	
	@Override
	public boolean bajaProveedores(String CIF) {
		// TODO Auto-generated method stub
		
		return _banco_datos.quitarProv(CIF);
		//return bsd.eliminarProveedor(CIF);
	}

	@Override
	public boolean modificarProveedores(String CIF, Proveedor modificacion) {
		// TODO Auto-generated method stub
		
		return _banco_datos.modProv(CIF, modificacion);
		//return bsd.modificarProveedor(CIF, modificacion);
	}

	@Override
	public boolean altaProveedor(Proveedor proveedor) {
		// TODO Auto-generated method stub
		
		
		return _banco_datos.insertarProv(proveedor);
		//return bsd.alta(proveedor);
	}

	@Override
	public Proveedor consultaProveedor(String CIF) { // falta
		// TODO Auto-generated method stub
		return _banco_datos.consultaProv(CIF);
		//return bsd.consultaProveedor(CIF);
	}

	@Override
	public List<Proveedor> busquedaProveedor(String nombre) {
		// TODO Auto-generated method stub
		return _banco_datos.coincidenciasProv(nombre);
		//return bsd.busquedaProveedor(CIF);
	}

	@Override
	public boolean existeProveedor(String CIF) {
		// TODO Auto-generated method stub
		return _banco_datos.exProv(CIF);
		//return bsd.existe(CIF);
	}

	@Override
	public int getLim() {
		// TODO Auto-generated method stub
		return _banco_datos.getLimProv();
		//return bsd.getLim();
	}
	
	
}