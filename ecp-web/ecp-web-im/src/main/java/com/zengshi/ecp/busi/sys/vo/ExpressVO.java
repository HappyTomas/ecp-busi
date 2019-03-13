/** 
 * Project Name:ecp-web-manage 
 * File Name:ExpressVO.java 
 * Package Name:com.zengshi.ecp.busi.sys.vo 
 * Date:2015-9-3下午9:13:59 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.busi.sys.vo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2015-9-3下午9:13:59  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public class ExpressVO implements Serializable {
    
    /** 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -2273821288879446062L;
    
    ///操作方式；
    @NotNull(message="{sys.expressvo.operate.null.error}")
    private String operate;

    private long id ;
    
    @NotNull(message="{sys.expressvo.fullname.length.error}")
    @Size(min=1, max=64, message="{sys.expressvo.fullname.length.error}")
    private String expressFullName;
    
    @NotNull(message="{sys.expressvo.name.length.error}")
    @Size(min=1, max=64, message="{sys.expressvo.name.length.error}")
    private String expressName;
    
    @Size(min=0, max=120, message="{sys.expressvo.website.length.error}")
    private String expressWebsite;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getExpressFullName() {
        return expressFullName;
    }

    public void setExpressFullName(String expressFullName) {
        this.expressFullName = expressFullName;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public String getExpressWebsite() {
        return expressWebsite;
    }

    public void setExpressWebsite(String expressWebsite) {
        this.expressWebsite = expressWebsite;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

}

