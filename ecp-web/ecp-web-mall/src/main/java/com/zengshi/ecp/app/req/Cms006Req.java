package com.zengshi.ecp.app.req;

import com.zengshi.butterfly.app.model.IBody;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 获取APP首页分类服务-入参<br>
 * Date:2016-2-22下午6:52:57  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author zhanbh
 * @version  
 * @since JDK 1.6 
 */
public class Cms006Req extends IBody {
    
    /**
     * 内容位置id
     */
    private Long placeId;
    
    /** 
     * 首页显示分类个数. 
     */ 
    private int catgSize;

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public int getCatgSize() {
        return catgSize;
    }

    public void setCatgSize(int catgSize) {
        this.catgSize = catgSize;
    }
    
    
}

