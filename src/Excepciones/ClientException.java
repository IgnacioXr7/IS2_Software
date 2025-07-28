package Excepciones;

public class ClientException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String _msg;
	
	public ClientException(String message) {
		_msg = message;
	}
	
	public String getMessage() {
		return _msg;
	}

}
