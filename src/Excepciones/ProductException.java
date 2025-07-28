package Excepciones;

public class ProductException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String _msg;
	
	public ProductException(String message) {
		_msg = message;
	}
	
	public String getMessage() {
		return _msg;
	}

}
