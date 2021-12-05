package com.musala.devops.exceptions;

import java.io.Serializable;

public class LoadWeightRestrictionException extends RuntimeException implements Serializable {
	
	private static final long serialVersionUID = -6224551998200528653L;
	private static final String MESSAGE="The approved maximum load was exceeded";
	
	public LoadWeightRestrictionException() {
		super(MESSAGE);
	}
	
	public LoadWeightRestrictionException(Throwable cause) {
		super(cause);
	}
	
	public LoadWeightRestrictionException(String message) {
		super(message);
	}
	
	public LoadWeightRestrictionException(String message, Throwable cause) {
		super(message, cause);
	}
}