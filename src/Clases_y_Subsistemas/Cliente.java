package Clases_y_Subsistemas;

public class Cliente {
	private String _DNI;
	private String _nombre;
	private String _apellidos;
	private String _correo;
	private Integer _telefono;
	private String _domicilio;
	
	public Cliente(String DNI, String nombre, String apellidos, String correo, Integer telefono, String domicilio) {
		_DNI = DNI;
		_nombre = nombre;
		_apellidos = apellidos;
		_correo = correo;
		_telefono = telefono;
		_domicilio = domicilio;
	}
	//get
	public String getDNI() {
		return this._DNI;
	}
	public String getNombre() {
		return this._nombre;
	}
	public String getApellidos() {
		return this._apellidos;
	}
	public String getCorreo() {
		return this._correo;
	}
	public Integer getTelefono() {
		return this._telefono;
	}
	public String getDomicilio() {
		return _domicilio;
	}
	
	//set
	public void setDNI(String DNI) {
		 this._DNI = DNI;
	}
	public void setNombre(String nombre) {
		 this._nombre = nombre;
	}
	public void setApellidos(String apellidos) {
		this._apellidos = apellidos;
	}
	public void setCorreo(String correo) {
		 this._correo = correo;
	}
	public void setTelefono(Integer telefono) {
		 this._telefono = telefono;
	}
	public void setDomicilio(String _domicilio) {
		this._domicilio = _domicilio;
	}
	
}

