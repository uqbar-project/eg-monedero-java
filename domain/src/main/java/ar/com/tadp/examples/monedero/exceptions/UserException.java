package ar.com.tadp.examples.monedero.exceptions;

@SuppressWarnings("serial")
public class UserException extends RuntimeException {

	public UserException(String msg) {
		super(msg);
	}
	
	public UserException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
