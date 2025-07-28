package Excepciones;

public class ProvException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String _msg;
	
	public ProvException(String message) {
		_msg = message;
	}
	
	public String getMessage() {
		return _msg;
	}

}
