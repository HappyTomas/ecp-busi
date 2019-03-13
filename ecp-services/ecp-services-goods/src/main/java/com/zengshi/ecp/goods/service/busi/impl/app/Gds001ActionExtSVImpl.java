/** 
 * Project Name:ecp-services-goods-server 
 * File Name:Gds001ActionExtSVImpl.java 
 * Package Name:com.zengshi.ecp.goods.service.busi.impl.app 
 * Date:2016年11月4日下午2:13:11 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.service.busi.impl.app;

import java.util.List;

import com.zengshi.ecp.goods.dubbo.dto.app.AppExpandReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.app.AppExpandRespDTO;
import com.zengshi.ecp.goods.service.busi.interfaces.app.IGds001ActionExtSV;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: <br>
 * Date:2016年11月4日下午2:13:11  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public class Gds001ActionExtSVImpl implements IGds001ActionExtSV {

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.goods.service.busi.interfaces.app.IGds001ActionExtSV#setExpandInfo(com.zengshi.ecp.goods.dubbo.dto.app.AppExpandReqDTO) 
     */
    @Override
    public AppExpandRespDTO setExpandInfo(AppExpandReqDTO appExpandReqDTO) {
        return null;
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.goods.service.busi.interfaces.app.IGds001ActionExtSV#getPropIdsCondition() 
     */
    @Override
    public List<Long> getPropIdsCondition() {
        return null;
    }

}

