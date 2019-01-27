package com.pcfutbolmania.pcf2001.exception;

public class EpcfException extends RuntimeException {

	private static final long serialVersionUID = -8238935025911995277L;
	private String message;
	private Exception exception;

	public EpcfException() {
	}

	public EpcfException(String message, Exception exception) {
		this.message = message;
		this.exception = exception;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

}
