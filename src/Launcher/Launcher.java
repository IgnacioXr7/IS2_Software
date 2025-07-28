package Launcher;

import Clases_y_Subsistemas.Tienda;
import Controller.*;
import DAO.AplicacionAccesoDatos.*;
import DAO.FachadasAccesoDatos.*;
import GUI.view.MainWindow;
import Negociacion_y_Servicio.FachadasImplementacion.*;
import Negociacion_y_Servicio.LogicaServicio.*;

public class Launcher {
	
	public static void main(String[] args) {
		Tienda store = new Tienda();
		IDAOClientes DAOClientes = new AplicacionDAOCliente(store);
		IDAOPedidos DAOPedidos = new AplicacionDAOPedidos(store);
		IDAOProductos DAOProductos = new AplicacionDAOProducto(store);
		IDAOProveedores DAOProveedores = new AplicacionDAOProveedores(store);
		
		IFachadaDAOClientes impDAOFachadaClientes = new ImpDAOClientes(DAOClientes);
		IFachadaDAOPedidos impDAOFachadaPedidos = new ImpDAOPedidos(DAOPedidos);
		IFachadaDAOProductos impDAOFachadaProductos = new ImpDAOProducto(DAOProductos);
		IFachadaDAOProveedores impDAOFachadaProveedores = new ImpDAOProvedor(DAOProveedores);
			
		IServicioClientes servicioClientes = new ServicioCliente(impDAOFachadaClientes);
		IServicioPedidos servicioPedidos = new ServicioPedido(impDAOFachadaPedidos,impDAOFachadaClientes,impDAOFachadaProductos);
		IServicioProductos servicioProductos = new ServicioProducto(impDAOFachadaProductos);
		IServicioProveedores servicioProveedores = new ServicioProveedor(impDAOFachadaProveedores);
			
		IFachadaClientes impClientes = new ImplementacionCliente(servicioClientes);
		IFachadaPedidos impPedidos = new ImplementacionPedido(servicioPedidos);
		IFachadaProductos impProductos = new ImplementacionProducto(servicioProductos);
		IFachadaProveedores impProveedores = new ImplementacionProveedor(servicioProveedores);
			
		ControllerClientes ctrlCl = new ControllerClientes(impClientes);
		ControllerPedidos ctrlPd = new ControllerPedidos(impPedidos);
		ControllerProductos ctrlProd = new ControllerProductos(impProductos);
		ControllerProveedores ctrlProv = new ControllerProveedores(impProveedores);
		
		System.out.println("Todo operativo y listo para su uso");
		new MainWindow(ctrlCl, ctrlPd, ctrlProd, ctrlProv);;
	}

}
