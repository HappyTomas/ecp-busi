package com.zengshi.ecp.search.dubbo.search.util;

public enum ERequestType {
    
    CLUSTERING("/clustering"),
    SPELLCHECK("/spell"),
    SUGGEST("/suggest"),
    SELECT("/select"),
    QUERY("/query");
    
    private String handler;
    
    public String getHandler(){
        return this.handler;
    }
    
    ERequestType(String handler){
        this.handler=handler;
    }
    
}

