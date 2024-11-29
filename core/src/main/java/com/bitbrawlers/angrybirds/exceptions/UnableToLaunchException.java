package com.bitbrawlers.angrybirds.exceptions;

public class UnableToLaunchException extends RuntimeException
{
    public UnableToLaunchException(String message)
    {
        super(message);
    }

    public UnableToLaunchException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
