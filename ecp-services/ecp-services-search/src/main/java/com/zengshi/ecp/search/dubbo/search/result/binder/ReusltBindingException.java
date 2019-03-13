package com.zengshi.ecp.search.dubbo.search.result.binder;

public class ReusltBindingException extends Exception{

    private static final long serialVersionUID = 8226088403886996564L;
    
    public ReusltBindingException(String message) {
        super(message);
    }

    public ReusltBindingException(String message, Throwable cause) {
        super(message, cause);
    }

}
