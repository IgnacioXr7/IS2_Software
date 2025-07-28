package Negociacion_y_Servicio.FachadasImplementacion;

import java.util.List;

import Clases_y_Subsistemas.Proveedor;
import Excepciones.ProvException;
import Negociacion_y_Servicio.LogicaServicio.IServicioProveedores;

public class ImplementacionProveedor implements IFachadaProveedores{
	
    IServicioProveedores serProv;
    
    public ImplementacionProveedor(IServicioProveedores servicio) {
		serProv = servicio;
	}
	
	@Override
	public boolean altaProveedor(Proveedor nuevoProveedor) throws ProvException {
		// TODO Auto-generated method stub
		return serProv.altaProveedor(nuevoProveedor);
	}

	@Override
	public boolean bajaProveedor(String CIF){
		// TODO Auto-generated method stub
		return serProv.bajaProveedor(CIF);
	}

	@Override
	public boolean modificarProveedor(String CIF, Proveedor nuevoProveedor) {
		// TODO Auto-generated method stub
		return serProv.modificarProveedor(CIF, nuevoProveedor);
	}

	@Override
	public List<Proveedor> busquedaProveedor(String nombre) {
		// TODO Auto-generated method stub
		return serProv.busquedaProveedor(nombre);
	}

	@Override
	public Proveedor consultaProveedor(String CIF) {
		// TODO Auto-generated method stub
		return serProv.consultaProveedor(CIF);
	}

	
	
}

