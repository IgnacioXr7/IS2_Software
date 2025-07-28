package Negociacion_y_Servicio.LogicaServicio;

import java.util.List;

import Clases_y_Subsistemas.Proveedor;
import DAO.FachadasAccesoDatos.IFachadaDAOProveedores;
import Excepciones.ProvException;


public class ServicioProveedor implements IServicioProveedores{

	IFachadaDAOProveedores FDAOProv;
	
	public ServicioProveedor(IFachadaDAOProveedores DAO) {
		FDAOProv = DAO;
	}

	public boolean altaProveedor(Proveedor nuevoProveedor)  throws ProvException{ //deberia lanzar excepcion
		
		if(!FDAOProv.existeProveedor(nuevoProveedor.getCIF())) {
			
			if(FDAOProv.lim() < 1000) {
				
				return FDAOProv.altaProveedor(nuevoProveedor);
				
			}
			else {
				
			return false;
				
			}
			
		}
		else {
			
			throw new ProvException("Ya existe Proveedor");
			
		}
	
	}
	
	public boolean bajaProveedor(String CIF) {
		
		if(FDAOProv.existeProveedor(CIF)) {
			
			return FDAOProv.bajaProveedor(CIF);
			
		}
		else {
			
			return false;
		}
		
	}
	
	public boolean modificarProveedor(String CIF, Proveedor nuevoProveedor) {
		if(FDAOProv.existeProveedor(CIF)) {
			
			return FDAOProv.modificarProveedor(CIF, nuevoProveedor);
			
		}
		else {
			
			return false;
		}
		
	}
	
	public List<Proveedor> busquedaProveedor(String nombre) {
		
		return FDAOProv.busquedaProveedor(nombre);
	}
	
	public Proveedor consultaProveedor(String CIF) {
		
		return FDAOProv.consultaProveedor(CIF);
	}
	
}
