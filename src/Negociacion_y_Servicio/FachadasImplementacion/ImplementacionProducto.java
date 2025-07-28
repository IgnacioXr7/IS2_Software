package Negociacion_y_Servicio.FachadasImplementacion;

import java.util.List;

import Clases_y_Subsistemas.Producto;
import Excepciones.ProductException;
import Negociacion_y_Servicio.LogicaServicio.IServicioProductos;

public class ImplementacionProducto implements IFachadaProductos{
	
	private IServicioProductos ispr;
	
	public ImplementacionProducto(IServicioProductos servicio) {
		ispr = servicio;
	}

	@Override
	public boolean altaProducto(Producto pr) throws ProductException {
		//FALTA IMP
		return ispr.altaProductos(pr);
	}

	@Override
	public boolean bajaProducto(String codPr) {
		//FALTA IMP
		return ispr.bajaProductos(codPr);
	}

	@Override
	public boolean modificarProducto(String codPr, Producto mod) {
		//FALTA IMP
		return ispr.modificarProductos(codPr,mod);
	}

	@Override
	public Producto consultaProducto(String codPr) {
		//FALTA IMP
		return ispr.consultaProducto(codPr);
	}

	@Override
	public List<Producto> busquedaProductos(String nombre){
		//FALTA IMP
		return ispr.busquedaProductos(nombre);
	}


}
