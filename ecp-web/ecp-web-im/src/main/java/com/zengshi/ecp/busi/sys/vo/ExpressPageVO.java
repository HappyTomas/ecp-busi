/** 
 * Project Name:ecp-web-manage 
 * File Name:ExpressPageVO.java 
 * Package Name:com.zengshi.ecp.busi.sys.vo 
 * Date:2015-9-3下午4:52:10 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.busi.sys.vo;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2015-9-3下午4:52:10  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public class ExpressPageVO extends EcpBasePageReqVO{

    /** 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -2320595774425276548L;
    
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}

