package Excepciones;

public class PedidoException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String _msg;
	
	public PedidoException(String message) {
		_msg = message;
	}
	
	public String getMessage() {
		return _msg;
	}

}
