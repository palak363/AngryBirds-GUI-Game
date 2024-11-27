package com.bitbrawlers.angrybirds.exceptions;

public class ButtonInteractionException extends RuntimeException
{
    public ButtonInteractionException(String message)
    {
        super(message);
    }

    public ButtonInteractionException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
