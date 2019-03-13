package com.zengshi.ecp.search.index;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-search <br>
 * Description: <br>
 * Date:2015年8月14日下午3:06:50 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version
 * @since JDK 1.6
 */
public class IndexException extends Exception{

    /** 
     * serialVersionUID:
     */ 
    private static final long serialVersionUID = 8226088403886996564L;
    
    public IndexException(String message) {
        super(message);
    }

    public IndexException(String message, Throwable cause) {
        super(message, cause);
    }

}
