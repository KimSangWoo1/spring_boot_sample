package com.plantynet.common.exception;

import java.util.Locale;

import org.springframework.context.MessageSource;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
public class ProcessException extends RuntimeException
{
    protected String message;
    protected String messageKey;
    protected Object[] messageParameters;

    public ProcessException(String message)
    {
        super(null, null);
        this.message = message;
    } 
    
    public ProcessException(String message, Throwable cause)
    {
        super(message, cause);
        this.message = message;
    }
    
    public ProcessException(MessageSource messageSource, String messageKey)
    {
        this(messageSource, messageKey, null, null, Locale.getDefault(), null);
    }
    
    public ProcessException(MessageSource messageSource, String messageKey, Throwable wrappedException)
    {
        this(messageSource, messageKey, null, null, Locale.getDefault(), wrappedException);
    }
    
    public ProcessException(MessageSource messageSource, String messageKey, Locale locale, Throwable wrappedException)
    {
        this(messageSource, messageKey, null, null, locale, wrappedException);
    }
    
    public ProcessException(MessageSource messageSource, String messageKey, Object[] messageParameters, Throwable wrappedException)
    {
        this(messageSource, messageKey, messageParameters, null, Locale.getDefault(), wrappedException);
    }

    public ProcessException(MessageSource messageSource, String messageKey, Object[] messageParameters,
            String defaultMessage, Locale locale, Throwable wrappedException)
    {
        super(wrappedException);

        this.messageKey = messageKey;
        this.messageParameters = messageParameters;
        this.message = messageSource.getMessage(messageKey, messageParameters, defaultMessage, locale);
    }
}