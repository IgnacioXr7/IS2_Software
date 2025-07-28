package Clases_y_Subsistemas;

public class Pedido {
	
	private String _codPed;
	private String _DNICliente;
	private String _fecha;
	private double _importe;
	private boolean _estado;
	private String _cdArticulo;

	public Pedido(String codPed, String DNICliente, String fecha, Double importe, boolean estado, String art){
		
		this._codPed = codPed;
		this._DNICliente = DNICliente;
		this._fecha = fecha;
		this._importe = importe;
		this._estado = estado;
		this._cdArticulo = art;
	}
	
// ---------------------------- GETS ---------------------------- //
	public String getCodPed() { return this._codPed; }
	
	public String getDNICliente() {	return this._DNICliente;	}
	
	public String getFecha() { return this._fecha; }
	
	public double getImporte() { return this._importe; }
	
	public boolean getEstado() { return this._estado; }
	
	public String getCodArticulo() {return _cdArticulo;}
// ---------------------------- GETS ---------------------------- //
	
// ---------------------------- SETS ---------------------------- //
	public void setCodPed(String codPed) { this._codPed = codPed; }
	
	public void setDNICliente(String DNICliente) { this._DNICliente = DNICliente; }
	
	public void setFecha(String fecha) { this._fecha = fecha; }
	
	public void setImporte(double importe) { this._importe = importe; }
	
	public void setEstado(boolean estado) { this._estado = estado; }
	
	public void setCodArticulo(String _nombreArticulo) {this._cdArticulo = _nombreArticulo;}
// ---------------------------- SETS ---------------------------- //

}

