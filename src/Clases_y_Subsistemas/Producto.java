package Clases_y_Subsistemas;

public class Producto {
	private String _codPr;
	private String _nombre;
	private String _marca;
	private String _modelo;
	private double _precio;
	private Integer _unidades;
	
	public Producto(String id, String nombre, String marca, String modelo, double precio, int unidades) {
		_codPr = id;
		_nombre = nombre;
		_marca = marca;
		_modelo = modelo;
		_precio = precio;
		_unidades = unidades;
	}
	//get
	public String getCodPr() {
		return this._codPr;
	}
	public String getNombre() {
		return this._nombre;
	}
	public String getMarca() {
		return this._marca;
	}
	public String getModelo() {
		return this._modelo;
	}
	public double getPrecio() {
		return this._precio;
	}
	public Integer getStock() {
		return this._unidades;
	}
	//set
	public void setCodPr(String codPr) {
		 this._codPr = codPr;
	}
	public void setNombre(String nombre) {
		 this._nombre = nombre;
	}
	public void setMarca(String marca) {
		this._marca = marca;
	}
	public void setModelo(String modelo) {
		 this._modelo = modelo;
	}
	public void setPrecio(double precio) {
		 this._precio = precio;
	}
	public void setStock(Integer unidades) {
		 this._unidades = unidades;
	}
}

