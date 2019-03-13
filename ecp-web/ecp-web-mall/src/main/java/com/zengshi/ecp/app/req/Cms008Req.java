package com.zengshi.ecp.app.req;

import com.zengshi.butterfly.app.model.IBody;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 获取APP首页数据服务-入参<br>
 * Date:2016-2-22下午6:52:57  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author jiangzh
 * @version  
 * @since JDK 1.6 
 */
public class Cms008Req extends IBody {
    
    private Long siteId ;
    
    private Long adPlaceId;
    
    private Integer channelSize;

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public Long getAdPlaceId() {
        return adPlaceId;
    }

    public void setAdPlaceId(Long adPlaceId) {
        this.adPlaceId = adPlaceId;
    }

    public Integer getChannelSize() {
        return channelSize;
    }

    public void setChannelSize(Integer channelSize) {
        this.channelSize = channelSize;
    }

}

