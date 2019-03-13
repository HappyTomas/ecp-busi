/** 
 * Project Name:ecp-services-goods-server 
 * File Name:IGds001ActionExtRSV.java 
 * Package Name:com.zengshi.ecp.goods.dubbo.interfaces 
 * Date:2016年11月4日下午2:23:07 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.dubbo.interfaces.app;

import java.util.List;

import com.zengshi.ecp.goods.dubbo.dto.app.AppExpandReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.app.AppExpandRespDTO;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: Gds001Action扩展Dubbo服务<br>
 * Date:2016年11月4日下午2:23:07  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public interface IGds001ActionExtRSV {
    
    /**
     * 
     * setExpandInfo:为APP设置额外返回属性. <br/> 
     * 
     * @author liyong7
     * @param appExpandReqDTO
     * @return 
     * @since JDK 1.6
     */
    public AppExpandRespDTO setExpandInfo(AppExpandReqDTO appExpandReqDTO);
    /**
     * 
     * getPropIdsCondition:获取额外查询属性ID. <br/> 
     * 
     * @author liyong7
     * @return 
     * @since JDK 1.6
     */
    public List<Long> getPropIdsCondition();

}

