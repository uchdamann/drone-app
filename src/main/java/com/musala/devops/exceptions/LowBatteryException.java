package com.musala.devops.exceptions;

import java.io.Serializable;

public class LowBatteryException extends RuntimeException implements Serializable {
	private static final long serialVersionUID = 7238562067841068126L;
	private static final String MESSAGE="Drone battery is too low for service";
	
	public LowBatteryException()
    {
        super(MESSAGE);
    }

    public LowBatteryException(Throwable cause)
    {
        super(MESSAGE, cause);
    }

    public LowBatteryException(String message)
    {
        super(message);
    }

    public LowBatteryException(String message, Throwable cause)
    {
        super(message, cause);
    }	
}
