package com.bitbrawlers.angrybirds.exceptions;

public class UnableToStartGameException extends RuntimeException
{
    public UnableToStartGameException(String message)
    {
        super(message);
    }

    public UnableToStartGameException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
