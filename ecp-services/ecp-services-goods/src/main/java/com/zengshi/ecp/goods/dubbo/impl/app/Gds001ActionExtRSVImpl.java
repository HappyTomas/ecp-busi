/** 
 * Project Name:ecp-services-goods-server 
 * File Name:Gds001AcitonExtRSVImpl.java 
 * Package Name:com.zengshi.ecp.goods.dubbo.impl.app 
 * Date:2016年11月4日下午2:25:00 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.dubbo.impl.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.zengshi.ecp.goods.dubbo.dto.app.AppExpandReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.app.AppExpandRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.app.IGds001ActionExtRSV;
import com.zengshi.ecp.goods.service.busi.interfaces.app.IGds001ActionExtSV;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: <br>
 * Date:2016年11月4日下午2:25:00  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public class Gds001ActionExtRSVImpl implements IGds001ActionExtRSV {
    @Autowired(required=false)
    private IGds001ActionExtSV gds001ActionExtSV;

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.goods.dubbo.interfaces.app.IGds001ActionExtRSV#setExpandInfo(com.zengshi.ecp.goods.dubbo.dto.app.AppExpandReqDTO) 
     */
    @Override
    public AppExpandRespDTO setExpandInfo(AppExpandReqDTO appExpandReqDTO) {
        return gds001ActionExtSV.setExpandInfo(appExpandReqDTO);
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.goods.dubbo.interfaces.app.IGds001ActionExtRSV#getPropIdsCondition() 
     */
    @Override
    public List<Long> getPropIdsCondition() {
        return gds001ActionExtSV.getPropIdsCondition();
    }

}

