package ar.edu.design.examples.monedero.exceptions;

@SuppressWarnings("serial")
public class BusinessException extends RuntimeException {

	public BusinessException(String msg) {
		super(msg);
	}
	
	public BusinessException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
