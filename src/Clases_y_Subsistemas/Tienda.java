package Clases_y_Subsistemas;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;



public class Tienda{
	
	final private static String _archC = "resources/examples/input/clientes.json";
	final private static String _archPrv ="resources/examples/input/proveedor.json";
	final private static String _archProd ="resources/examples/input/producto.json";
	final private static String _archPd = "resources/examples/input/pedido.json";
	
	private List<Cliente> _listaCliente;
	private List<Proveedor> _listaProveedor;
	private List<Producto> _listaProducto;
	private List<Pedido> _listaPedido;
	
	public Tienda() {
		_listaCliente = new ArrayList<Cliente>();
		_listaProveedor = new ArrayList<Proveedor>();
		_listaProducto = new ArrayList<Producto>();
		_listaPedido = new ArrayList<Pedido>();
		start();
	}
	
	public void dataToCliente(JSONObject data) throws IllegalArgumentException {
		String DNI;
		String nombre;
		String apellidos;
		String correo;
		Integer telefono;
		String domicilio;
		if(data.has("DNI")&& data.has("nombre") && data.has("apellidos")&&data.has("correo")
				&& data.has("telefono") && data.has("domicilio")) {
			DNI = data.getString("DNI");
			nombre = data.getString("nombre");
			apellidos = data.getString("apellidos");
			correo = data.getString("correo");
			telefono = data.getInt("telefono");
			domicilio = data.getString("domicilio");
			Cliente c = new Cliente(DNI,nombre,apellidos,correo,telefono,domicilio);
			_listaCliente.add(c);
			
		}else throw new IllegalArgumentException();
			
	}
	
	public void dataToProducto(JSONObject data) throws IllegalArgumentException {
		String nombre;
		String id;
		String marca;
		String modelo;
		double precio;
		int unidades;
		if(data.has("codPr")&& data.has("nombre") && data.has("marca")&&data.has("modelo")
				&& data.has("precio") && data.has("unidades")) {
			id = data.getString("codPr");
			nombre = data.getString("nombre");
			marca = data.getString("marca");
			modelo = data.getString("modelo");
			precio = data.getDouble("precio");
			unidades = data.getInt("unidades");
			Producto p = new Producto(id,nombre,marca,modelo,precio,unidades);
			_listaProducto.add(p);
			
			
			
		}else throw new IllegalArgumentException();
			
	}
	
	public void dataToProveedor(JSONObject data) throws IllegalArgumentException {
		
		String nombre;
		String CIF;
		String dom;
		int tlf;
		if(data.has("CIF")&& data.has("nombre") && data.has("domicilio")&&data.has("telefono")) {
			CIF = data.getString("CIF");
			nombre = data.getString("nombre");
			dom = data.getString("domicilio");
			tlf = data.getInt("telefono");
			
			Proveedor p = new Proveedor(CIF, nombre, dom, tlf);
			_listaProveedor.add(p);
			
			
			
		}else throw new IllegalArgumentException();
		
	}
	public void dataToPedido(JSONObject data) throws IllegalArgumentException {
		String codPed;
		String DNICliente;
		String fecha;
		double importe;
		boolean estado;
		String codPr;
		
		if(data.has("codPed")&& data.has("DNICliente") && data.has("fecha")&&data.has("importe")
				&& data.has("estado") && data.has("codPr")) {
			codPed = data.getString("codPed");
			DNICliente = data.getString("DNICliente");
			fecha = data.getString("fecha");
			importe = data.getInt("importe");
			estado = data.getBoolean("estado");
			codPr = data.getString("codPr");
			Pedido p = new Pedido(codPed, DNICliente, fecha, importe, estado, codPr);
			_listaPedido.add(p);
			
			
		}else throw new IllegalArgumentException();
	}

	//DATA CLIENTE
	public boolean insertarCl(Cliente cliente) {
		_listaCliente.add(cliente);
		guardar_cambiosCl();
		return true;
	}
	public boolean quitarCl(String dNICliente) {
		boolean encontrado = false;
		int i = 0;
		while(i<_listaCliente.size() && !encontrado) {
			if(_listaCliente.get(i).getDNI().equals(dNICliente)) {
				_listaCliente.remove(i);
				Iterator<Pedido> iterator = _listaPedido.iterator();
				while (iterator.hasNext()) {
				    Pedido p = iterator.next();
				    if (p.getDNICliente().equals(dNICliente)) 
				        iterator.remove();				    
				}
				encontrado = true;
			}
			i++;
		}
		guardar_cambiosCl();
		guardar_cambiosPd();
		return encontrado;
	}
	public Cliente consultaCl(String dNICliente) {
		boolean encontrado = false;
		int i = 0;
		while(i<_listaCliente.size() && !encontrado) {
			if(_listaCliente.get(i).getDNI().equals(dNICliente))
				encontrado = true;
			else {
				i++;
			}
		}
		if(encontrado)
			return _listaCliente.get(i);
		else
			return null;
	}
	public List<Cliente> coincidenciasCl(String nombre) {
		if(nombre == null || nombre.equals("")) {
			return _listaCliente;
		}
		List<Cliente> aux = new ArrayList<Cliente>();
		int i = 0;
		boolean ok;
		while(i<_listaCliente.size()) {
			ok = true;
			for(int j = 0; j<nombre.length() && ok ;j++) {
				if(nombre.charAt(j) != _listaCliente.get(i).getNombre().charAt(j))
					ok = false;
			}
			if(ok)
				aux.add(_listaCliente.get(i));
			i++;
		}
		return aux;
	}
	public int tamCl() {
		return _listaCliente.size();
	}
	public boolean modCl(String dNICliente, Cliente cliente) {
		boolean encontrado = false;
		int i = 0;
		while(i<_listaCliente.size() && !encontrado) {
			if(_listaCliente.get(i).getDNI().equals(dNICliente))
				encontrado = true;
			else
				i++;
		}
		if(encontrado) {
			_listaCliente.get(i).setNombre(cliente.getNombre());
			_listaCliente.get(i).setApellidos(cliente.getApellidos());
			_listaCliente.get(i).setCorreo(cliente.getCorreo());
			_listaCliente.get(i).setDomicilio(cliente.getDomicilio());
			_listaCliente.get(i).setTelefono(cliente.getTelefono());
		}
		guardar_cambiosCl();
		return encontrado;
	}
	public boolean exCliente(String DNI) {
		boolean encontrado = false;
		int i = 0;
		while(i<_listaCliente.size() && !encontrado) {
			if(_listaCliente.get(i).getDNI().equals(DNI))
				encontrado = true;
			i++;
		}
		return encontrado;
	}
	//DATA PEDIDOS
	public boolean insertarPd(Pedido ped) {
		_listaPedido.add(ped);
		int i = 0;
		boolean encontrado = false;
		while(!encontrado) {
			if(_listaProducto.get(i).getCodPr().equals(ped.getCodArticulo())) {
				encontrado = true;
				_listaProducto.get(i).setStock(_listaProducto.get(i).getStock()-1);
			}
			i++;
		}
		guardar_cambiosPd();
		return true;
	}
	public boolean quitarPd(String codPed) {
		boolean encontrado = false;
		int i = 0;
		while(i<_listaPedido.size() && !encontrado) {
			if(_listaPedido.get(i).getCodPed().equals(codPed)) {
				encontrado = true;
				_listaPedido.remove(i);
			}
			i++;
		}
		guardar_cambiosPd();
		return encontrado;
		
	}
	public Pedido consultaPd(String id) {
		Pedido aux = null;
		int i = 0;
		while(i<_listaPedido.size() && aux == null) {
			if(_listaPedido.get(i).getCodPed().equals(id)) {
				aux = _listaPedido.get(i);
			}
			i++;
		}
		return aux;
	}
	public List<Pedido> coincidenciaPd(String fecha) {
		if(fecha == null || fecha.equals("")) {
			return _listaPedido;
		}
		else if(fecha.length() != 8) {
			return new ArrayList<Pedido>();
		}
		String d = ""+fecha.charAt(0) + ""+fecha.charAt(1);
		String m = ""+fecha.charAt(3) + ""+fecha.charAt(4);
		String a = ""+fecha.charAt(6) + ""+fecha.charAt(7);
		List<Pedido> aux = new ArrayList<Pedido>();
		for(Pedido p: _listaPedido) {
			String i = p.getFecha();
			String dp = ""+i.charAt(0) + ""+i.charAt(1);
			String mp = ""+i.charAt(3) + ""+i.charAt(4);
			String ap = ""+i.charAt(6) + ""+i.charAt(7);
			if(d.equals(dp) || m.equals(mp) || a.equals(ap)) {
				aux.add(p);
			}
		}
		return aux;
	}
	public boolean modPd(Pedido p, String CodPed) {
		boolean encontrado = false;
		int i = 0;
		while(i<_listaPedido.size() && !encontrado) {
			if(_listaPedido.get(i).getCodPed().equals(CodPed))
				encontrado = true;
			else
				i++;
		}
		if(encontrado) {
			_listaPedido.get(i).setFecha(p.getFecha());
			_listaPedido.get(i).setEstado(p.getEstado());
		}
		guardar_cambiosPd();
		return encontrado;
	}
	
	public boolean exPedido(String codPed) {
		boolean encontrado = false;
		int i = 0;
		while(i<_listaPedido.size() && !encontrado) {
			if(_listaPedido.get(i).getCodPed().equals(codPed))
				encontrado = true;
			i++;
		}
		return encontrado;
	}
	//DATA PRODUCTOS
	public boolean quitarProd(String id) {
		boolean encontrado = false;
		int i = 0;
		while(i<_listaProducto.size() && !encontrado) {
			if(_listaProducto.get(i).getCodPr().equals(id)) {
				encontrado = true;
				Iterator<Pedido> iterator = _listaPedido.iterator();
				while (iterator.hasNext()) {
				    Pedido p = iterator.next();
				    if (p.getCodArticulo().equals(id)) 
				        iterator.remove();				    
				}
			}
			else
				i++;
		}
		_listaProducto.remove(i);
		guardar_cambiosProd();
		guardar_cambiosPd();
		return encontrado;
	}
	
	public Producto consultaProd(String id) {
		boolean encontrado = false;
		int i = 0;
		while(i<_listaProducto.size() && !encontrado) {
			if(_listaProducto.get(i).getCodPr().equals(id))
				encontrado = true;
			else
				i++;
		}
		if(encontrado)
			return _listaProducto.get(i);
		else
			return null;
	}
	
	public List<Producto> coincidenciasProd(String nombre) {
		List<Producto> aux = new ArrayList<Producto>();
		if(nombre == null || nombre.equals("")) {
			return _listaProducto;
		}
		int i = 0;
		boolean ok;
		while(i<_listaProducto.size()) {
			ok = true;
			for(int j = 0; j<nombre.length() && ok ;j++) {
				if(nombre.charAt(j) != _listaProducto.get(i).getNombre().charAt(j))
					ok = false;
			}
			if(ok)
				aux.add(_listaProducto.get(i));
			i++;
		}
		return aux;
	}
	
	public boolean modProd(String id, Producto Producto) {
		boolean encontrado = false;
		int i = 0;
		while(i<_listaProducto.size() && !encontrado) {
			if(_listaProducto.get(i).getCodPr().equals(id))
				encontrado = true;
			else
				i++;
		}
		if(encontrado) {
			_listaProducto.get(i).setNombre(Producto.getNombre());
			_listaProducto.get(i).setMarca(Producto.getMarca());
			_listaProducto.get(i).setModelo(Producto.getModelo());
			_listaProducto.get(i).setPrecio(Producto.getPrecio());
			_listaProducto.get(i).setStock(Producto.getStock());
		}
		guardar_cambiosProd();
		return encontrado;
	}
	
	public boolean insertarProd(Producto Producto) {
		_listaProducto.add(Producto);
		guardar_cambiosProd();
		return true;
	}
	
	public boolean exProd(String id) {
		boolean encontrado = false;
		int i = 0;
		while(i<_listaProducto.size() && !encontrado) {
			if(_listaProducto.get(i).getCodPr().equals(id))
				encontrado = true;
			i++;
		}
		return encontrado;
	}
	
	public int getLimProd() {
		
		return _listaProducto.size();
	}
	
	//DATA PROVEEDORES
	
	public boolean quitarProv(String CIF) {
		boolean encontrado = false;
		int i = 0;
		while(i<_listaProveedor.size() && !encontrado) {
			if(_listaProveedor.get(i).getCIF().equals(CIF))
				encontrado = true;
			else
				i++;
		}
		_listaProveedor.remove(i);
		guardar_cambiosPrv();
		return encontrado;
	}
	
	public boolean modProv(String CIF, Proveedor Proveedor) {
		boolean encontrado = false;
		int i = 0;
		while(i<_listaProveedor.size() && !encontrado) {
			if(_listaProveedor.get(i).getCIF().equals(CIF))
				encontrado = true;
			else
				i++;
		}
		if(encontrado) {
			_listaProveedor.get(i).setNombre(Proveedor.getNombre());
			_listaProveedor.get(i).setDomicilioSocial(Proveedor.getDomicilioSocial());
			_listaProveedor.get(i).setTelefono(Proveedor.getTelefono());
		}
		return encontrado;
	}
	
	public boolean insertarProv(Proveedor Proveedor) {
		_listaProveedor.add(Proveedor);
		guardar_cambiosPrv();
		return true;
	}
	
	public Proveedor consultaProv(String CIF) {
		boolean encontrado = false;
		int i = 0;
		while(i<_listaProveedor.size() && !encontrado) {
			if(_listaProveedor.get(i).getCIF().equals(CIF))
				encontrado = true;
			else
				i++;
		}
		if(encontrado)
			return _listaProveedor.get(i);
		else
			return null;
	}
	
	public List<Proveedor> coincidenciasProv(String nombre) {
		if(nombre == null || nombre.equals("")) {
			return _listaProveedor;
		}
		List<Proveedor> aux = new ArrayList<Proveedor>();
		int i = 0;
		boolean ok;
		while(i<_listaProveedor.size()) {
			ok = true;
			for(int j = 0; j<nombre.length() && ok ;j++) {
				if(nombre.charAt(j) != _listaProveedor.get(i).getNombre().charAt(j))
					ok = false;
			}
			if(ok)
				aux.add(_listaProveedor.get(i));
			i++;
		}
		return aux;
	}
	
	public boolean exProv(String CIF) {
		boolean encontrado = false;
		int i = 0;
		while(i<_listaProveedor.size() && !encontrado) {
			if(_listaProveedor.get(i).getCIF().equals(CIF))
				encontrado = true;
			i++;
		}
		return encontrado;
	}
	
	public int getLimProv() {
		
		return _listaProveedor.size();
	}
	
	private void guardar_cambiosCl() {
		FileOutputStream outputStream;
		try {
			outputStream = new FileOutputStream(_archC);
			PrintStream p = new PrintStream(outputStream);
			p.println("{");
			p.println("\"Clientes\": [");
			int i = 0;
			for(Cliente c: _listaCliente) {
				p.println("{");
				p.println("\"DNI\": " + '"' + c.getDNI() + '"' +',');
				p.println("\"nombre\": " + '"' + c.getNombre() + '"' +',');
				p.println("\"apellidos\": " + '"' + c.getApellidos() + '"' +',');
				p.println("\"correo\": " + '"' + c.getCorreo() + '"' +',');
				p.println("\"telefono\": "  + c.getTelefono()+',');
				p.println("\"domicilio\": " + '"' + c.getDomicilio() + '"');
				p.println("}");
				if(i < _listaCliente.size()-1) {
					p.println(",");
				}
				i++;
			}
			p.println("]");
			p.println("}");
		} catch (FileNotFoundException e) {
			System.out.println("Los cambios realizados no se han podido guardar. Revisar dirreccion"
					+ " del archivo.");
		}
		
	}
	
	private void guardar_cambiosPrv() {
		FileOutputStream outputStream;
		try {
			outputStream = new FileOutputStream(_archPrv);
			PrintStream p = new PrintStream(outputStream);
			p.println("{");
			p.println("\"Proveedores\": [");
			int i = 0;
			for(Proveedor c: _listaProveedor) {
				p.println("{");
				p.println("\"CIF\": " + '"' + c.getCIF() + '"' +',');
				p.println("\"nombre\": " + '"' + c.getNombre() + '"' +',');
				p.println("\"domicilio\": " + '"' + c.getDomicilioSocial() + '"'+',');
				p.println("\"telefono\": " + c.getTelefono()+',');
				p.println("}");
				if(i < _listaProveedor.size()-1) {
					p.println(",");
				}
				i++;
			}
			p.println("]");
			p.println("}");
		} catch (FileNotFoundException e) {
			System.out.println("Los cambios realizados no se han podido guardar. Revisar dirreccion"
					+ " del archivo.");
		}
	}
	
	private void guardar_cambiosProd() {
		FileOutputStream outputStream;
		try {
			outputStream = new FileOutputStream(_archProd);
			PrintStream p = new PrintStream(outputStream);
			p.println("{");
			p.println("\"Productos\": [");
			int i = 0;
			for(Producto c: _listaProducto) {
				p.println("{");
				p.println("\"codPr\": " + '"' + c.getCodPr() + '"' +',');
				p.println("\"nombre\": " + '"' + c.getNombre() + '"' +',');
				p.println("\"marca\": " + '"' + c.getMarca() + '"'+',');
				p.println("\"modelo\": " + '"'+ c.getModelo()+ '"'+',');
				p.println("\"precio\": " + c.getPrecio()+',');
				p.println("\"unidades\": " + c.getStock());
				p.println("}");
				if(i < _listaProducto.size()-1) {
					p.println(",");
				}
				i++;
			}
			p.println("]");
			p.println("}");
		} catch (FileNotFoundException e) {
			System.out.println("Los cambios realizados no se han podido guardar. Revisar dirreccion"
					+ " del archivo.");
		}
	}
	
	private void guardar_cambiosPd() {
		FileOutputStream outputStream;
		try {
			outputStream = new FileOutputStream(_archPd);
			PrintStream p = new PrintStream(outputStream);
			p.println("{");
			p.println("\"Pedidos\": [");
			int i = 0;
			for(Pedido c: _listaPedido) {
				p.println("{");
				p.println("\"codPed\": " + '"' + c.getCodPed() + '"' +',');
				p.println("\"DNICliente\": " + '"' + c.getDNICliente() + '"' +',');
				p.println("\"fecha\": " + '"' + c.getFecha() + '"' +',');
				p.println("\"importe\": " + c.getImporte()+',');
				p.println("\"estado\": " + c.getEstado()+',');
				p.println("\"codPr\": " +'"'+ c.getCodArticulo()+'"');
				p.println("}");
				if(i < _listaPedido.size()-1) {
					p.println(",");
				}
				i++;
			}
			p.println("]");
			p.println("}");
		} catch (FileNotFoundException e) {
			System.out.println("Los cambios realizados no se han podido guardar. Revisar dirreccion"
					+ " del archivo.");
		}
	}
	
	private void createCl(JSONObject j) {
		try {
			this.dataToCliente(j);
		}catch(IllegalArgumentException e) {
			System.out.println("No se ha podido cargar un cliente. Se ha suprimido al insertarlo");
		}	
	}
	
	private void createProv(JSONObject j) {
		try {
			this.dataToProveedor(j);
		}catch(IllegalArgumentException e) {
			System.out.println("No se ha podido cargar un proveedor. Se ha suprimido al insertarlo");
		}	
	}
	
	private void createProd(JSONObject j) {
		try {
			this.dataToProducto(j);
		}catch(IllegalArgumentException e) {
			System.out.println("No se ha podido cargar un producto. Se ha suprimido al insertarlo");
		}	
	}
	
	private void createPd(JSONObject j) {
		try {
			this.dataToPedido(j);
		}catch(IllegalArgumentException e) {
			System.out.println("No se ha podido cargar un predido. Se ha suprimido al insertarlo");
		}	
	}
	
	private void start() {
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream(_archC);
			JSONArray clientesData = loadData(inputStream, "Clientes");
			for(int i = 0; i< clientesData.length(); i++) {
				this.createCl(clientesData.getJSONObject(i));
			}
			inputStream = new FileInputStream(_archPrv);
			JSONArray proveedoresData = loadData(inputStream, "Proveedores");
			for(int i = 0; i< proveedoresData.length(); i++) {
				this.createProv(proveedoresData.getJSONObject(i));
			}
			inputStream = new FileInputStream(_archProd);
			JSONArray productosData = loadData(inputStream, "Productos");
			for(int i = 0; i< productosData.length(); i++) {
				this.createProd(productosData.getJSONObject(i));
			}
			inputStream = new FileInputStream(_archPd);
			JSONArray pedidosData = loadData(inputStream, "Pedidos");
			for(int i = 0; i< pedidosData.length(); i++) {
				this.createPd(pedidosData.getJSONObject(i));
			}
		}catch (FileNotFoundException e) {
			System.out.println("Revisar codigo");
		}
		
	}
	
	public static JSONArray loadData(InputStream c, String clave) {
		JSONObject jason = new JSONObject(new JSONTokener(c));
		return jason.getJSONArray(clave);
	}

}
