package DAO.AplicacionAccesoDatos;

import java.util.List;

import Clases_y_Subsistemas.Producto;
import Clases_y_Subsistemas.Tienda;

public class AplicacionDAOProducto implements IDAOProductos{
	
	private Tienda _banco_datos;

	public AplicacionDAOProducto(Tienda store) {
		_banco_datos = store;
	}
	
	@Override
	public boolean bajaProducto(String CodPr) {
		// TODO Auto-generated method stub
		
		return _banco_datos.quitarProd(CodPr);
		//bds.eliminarProducto(CodPr);
	}

	@Override
	public Producto consultaProducto(String CodPr) {
		// TODO Auto-generated method stub
		//bds.leerDatos(CodPr);
		
		return _banco_datos.consultaProd(CodPr);
	}

	@Override
	public List<Producto> busquedaProducto(String nombre) {
		//bds.buscarSemejanzas(TProducto);
		return _banco_datos.coincidenciasProd(nombre);
	}

	@Override
	public boolean modificacionProducto(String CodPr, Producto TProducto) {
		//bds.modifica(CodPr, TProducto);
		return _banco_datos.modProd(CodPr, TProducto);
	}

	@Override
	public boolean altaProducto(Producto id) {
		// TODO Auto-generated method stub
		//bds.introduceProducto(id);
		return _banco_datos.insertarProd(id);
	}

	public boolean existeProducto(String codPr) {
		//if(bds.leerDatos(codPr))
			//return true;
		//else
			return _banco_datos.exProd(codPr);
	}

	public int lim() {
		// TODO Auto-generated method stub
		return _banco_datos.getLimProd();
	}
	

}
