package com.musala.devops.exceptions;

import java.io.Serializable;

public class DroneDetailsException extends RuntimeException implements Serializable {
	
	private static final long serialVersionUID = -3365324435927165910L;
	private static final String MESSAGE="Drone Details Not Found";
	
	public DroneDetailsException()
    {
        super(MESSAGE);
    }

    public DroneDetailsException(Throwable cause)
    {
        super(MESSAGE, cause);
    }

    public DroneDetailsException(String message)
    {
        super(message);
    }

    public DroneDetailsException(String message, Throwable cause)
    {
        super(message, cause);
    }

}
