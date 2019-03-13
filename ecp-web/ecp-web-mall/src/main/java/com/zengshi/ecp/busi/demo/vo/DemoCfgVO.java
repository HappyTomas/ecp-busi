/** 
 * Project Name:ecp-web-demo 
 * File Name:DemoInfoVO.java 
 * Package Name:com.zengshi.ecp.busi.demo.vo 
 * Date:2015-8-5下午3:02:29 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.busi.demo.vo;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-demo <br>
 * Description: <br>
 * Date:2015-8-5下午3:02:29  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public class DemoCfgVO implements Serializable{
    
    /** 
     * serialVersionUID:
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -1808106181284676714L;
    
    @NotNull(message="{demo.demoCfg.info.length.error}")
    @Length(min=6, max=32, message="{demo.demoCfg.info.length.error}")
    private String info;
    
    @NotNull(message="{demo.demoCfg.code.length.error}")
    @Length(min=4, max=10, message="{demo.demoCfg.code.length.error}")
    private String code;
    
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createTime;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}

