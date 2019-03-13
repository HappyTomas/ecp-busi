package com.zengshi.ecp.app.req;

import com.zengshi.butterfly.app.model.IBody;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 获取APP栏目服务-入参<br>
 * Date:2016-2-22下午6:52:57  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author jiangzh
 * @version  
 * @since JDK 1.6 
 */
public class Cms002Req extends IBody {
    
    /** 
     * 站点ID. 
     */ 
    private Long siteId;
    /** 
     * 状态. 0，无效 1，有效 
     */
    private String status;
    
    /**
     * 平台分类（01：WEB,02:APP）
     */
    private String platformType;
    
    public Long getSiteId() {
        return siteId;
    }
    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getPlatformType() {
        return platformType;
    }
    public void setPlatformType(String platformType) {
        this.platformType = platformType;
    }
    
}

