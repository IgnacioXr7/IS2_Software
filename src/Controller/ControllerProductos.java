package Controller;

import java.util.List;

import Clases_y_Subsistemas.Producto;
import Excepciones.ProductException;
import Negociacion_y_Servicio.FachadasImplementacion.IFachadaProductos;

public class ControllerProductos {
	
	IFachadaProductos _IFProductos;
	
	public ControllerProductos(IFachadaProductos f) {
		this._IFProductos = f;
	}

	public boolean altaProducto(Producto pr) throws ProductException {
		return this._IFProductos.altaProducto(pr);
	}
	public boolean bajaProducto(String codPr) {
		return this._IFProductos.bajaProducto(codPr);
	}
	public boolean modificarProducto(String codPr, Producto mod) {
		return this._IFProductos.modificarProducto(codPr, mod);
	}
	public Producto consultaProducto(String codPr) {
		return this._IFProductos.consultaProducto(codPr);
	}
	public List<Producto> busquedaProductos(String nombre){
		return this._IFProductos.busquedaProductos(nombre);
	}

}