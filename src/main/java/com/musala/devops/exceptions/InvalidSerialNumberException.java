package com.musala.devops.exceptions;

import java.io.Serializable;

public class InvalidSerialNumberException extends RuntimeException implements Serializable {
	
	private static final long serialVersionUID = 1461610587893157590L;
	private static final String MESSAGE = "Serial number must be a maximum of 100 characters";
	
	public InvalidSerialNumberException() {
		super(MESSAGE);
	}
	
	public InvalidSerialNumberException(Throwable cause) {
		super(cause);
	}
	
	public InvalidSerialNumberException(String message) {
		super(message);
	}
	
	public InvalidSerialNumberException(Throwable cause, String message) {
		super(message, cause);
	}

}
