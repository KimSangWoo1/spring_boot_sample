package com.plantynet.common.exception;

/*@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)*/
//이렇게 하면 tomcat 기본 에러 페이지가 나온다-_- 
public class JsonException extends RuntimeException 
{
    private static final long serialVersionUID = -8753310102188930924L;
    
    public JsonException()
    {
        super();
    }
    
    public JsonException(String message)
    {
        super(message.length() > 100 ? message.substring(0, 100) + "..." : message);
    }
}