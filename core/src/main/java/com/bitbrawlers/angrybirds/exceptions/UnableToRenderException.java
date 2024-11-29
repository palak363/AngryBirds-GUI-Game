package com.bitbrawlers.angrybirds.exceptions;

import com.bitbrawlers.angrybirds.Screens.Level1;

public class UnableToRenderException extends RuntimeException
{
    public UnableToRenderException(String message)
    {
        super(message);
    }

    public UnableToRenderException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
