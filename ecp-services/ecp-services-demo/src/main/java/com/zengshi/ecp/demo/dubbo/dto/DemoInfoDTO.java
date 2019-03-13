/** 
 * Project Name:ecp-services-demo 
 * File Name:DemoInfoDTO.java 
 * Package Name:com.zengshi.ecp.demo.dubbo.dto 
 * Date:2015-8-3下午3:56:03 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.demo.dubbo.dto;

import java.sql.Timestamp;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-demo <br>
 * Description: <br>
 * Date:2015-8-3下午3:56:03  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public class DemoInfoDTO extends BaseInfo {

    /** 
     * serialVersionUID:
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 7271382803469024030L;

    @Min(value=10000,message="{demo.C10011}")
    private long id;
    
    @NotNull(message="名称不能为空")
    private String name;
    
    private Timestamp createTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
    
    

}

