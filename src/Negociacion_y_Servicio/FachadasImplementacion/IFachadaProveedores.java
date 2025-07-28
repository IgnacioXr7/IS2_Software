package Negociacion_y_Servicio.FachadasImplementacion;

import java.util.List;

import Clases_y_Subsistemas.Proveedor;
import Excepciones.ProvException;

public interface IFachadaProveedores {
	public boolean altaProveedor(Proveedor nuevoProveedor) throws ProvException;
	public boolean bajaProveedor(String CIF);
	public boolean modificarProveedor(String CIF, Proveedor nuevoProveedor);
	public List<Proveedor> busquedaProveedor(String nombre);
	public Proveedor consultaProveedor(String CIF);
	
}
