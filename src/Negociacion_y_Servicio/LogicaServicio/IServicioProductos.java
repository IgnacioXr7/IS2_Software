package Negociacion_y_Servicio.LogicaServicio;
import java.util.List;

import Clases_y_Subsistemas.Producto;
import Excepciones.ProductException;

public interface IServicioProductos {
	public boolean altaProductos(Producto pr) throws ProductException;
	public boolean bajaProductos(String codPr);
	public boolean modificarProductos(String codPr, Producto mod);
	public Producto consultaProducto(String codPr);
	public List<Producto> busquedaProductos(String nombre);
}
