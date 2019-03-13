/** 
 * Project Name:ecp-services-goods-server 
 * File Name:Gds001ActionSV.java 
 * Package Name:com.zengshi.ecp.goods.service.busi.interfaces.app 
 * Date:2016年10月22日上午9:15:46 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.service.busi.interfaces.app;

import java.util.List;

import com.zengshi.ecp.goods.dubbo.dto.app.AppExpandReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.app.AppExpandRespDTO;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: APP端商品详情扩展服务.<br>
 * Date:2016年10月22日上午9:15:46  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public interface IGds001ActionExtSV {
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

