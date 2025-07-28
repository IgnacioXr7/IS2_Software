package Clases_y_Subsistemas;
public class Proveedor {
	private String _CIF;
	private String _nombre;
	private String _domicilioSocial;
	private Integer _telefono;
	
	public Proveedor(String id, String nombre, String ds, int tel) {
		_CIF = id;
		_nombre = nombre;
		_domicilioSocial = ds;
		_telefono = tel;
	}
	//get
	public String getCIF() {
		return this._CIF;
	}
	public String getNombre() {
		return this._nombre;
	}
	public String getDomicilioSocial() {
		return this._domicilioSocial;
	}
	public Integer getTelefono() {
		return this._telefono;
	}
	//set
	public void setCIF(String CIF) {
		 this._CIF = CIF;
	}
	public void setNombre(String nombre) {
		 this._nombre = nombre;
	}
	public void setDomicilioSocial(String domicilioSocial) {
		 this._domicilioSocial = domicilioSocial;
	}
	public void setTelefono(Integer telefono) {
		 this._telefono = telefono;
	}
}

