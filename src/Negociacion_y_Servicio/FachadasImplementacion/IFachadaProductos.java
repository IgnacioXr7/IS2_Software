package Negociacion_y_Servicio.FachadasImplementacion;
import java.util.List;

import Clases_y_Subsistemas.Producto;
import Excepciones.ProductException;

public interface IFachadaProductos {
	public boolean altaProducto(Producto pr) throws ProductException;
	public boolean bajaProducto(String codPr);
	public boolean modificarProducto(String codPr, Producto mod);
	public Producto consultaProducto(String codPr);
	public List<Producto> busquedaProductos(String nombre);
	
}
