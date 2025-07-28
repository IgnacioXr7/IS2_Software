package Controller;

import java.util.List;

import Clases_y_Subsistemas.Proveedor;
import Excepciones.ProvException;
import Negociacion_y_Servicio.FachadasImplementacion.IFachadaProveedores;

public class ControllerProveedores {

	
	IFachadaProveedores _IFProv;
	
	public ControllerProveedores(IFachadaProveedores f) {
		this._IFProv = f;
	}
	
	public boolean altaProveedor(Proveedor nuevoProveedor) throws ProvException {
		return this._IFProv.altaProveedor(nuevoProveedor);
	}
	public boolean bajaProveedor(String CIF){
		return this._IFProv.bajaProveedor(CIF);
	}
	public boolean modificarProveedor(String CIF, Proveedor nuevoProveedor){
		return this._IFProv.modificarProveedor(CIF, nuevoProveedor);
	}
	public List<Proveedor> busquedaProveedor(String nombre){
		return this._IFProv.busquedaProveedor(nombre);
	}
	public Proveedor consultaProveedor(String CIF) {
		return this._IFProv.consultaProveedor(CIF);
	}
}