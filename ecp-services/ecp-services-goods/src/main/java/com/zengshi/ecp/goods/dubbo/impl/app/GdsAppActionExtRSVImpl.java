/** 
 * Project Name:ecp-services-goods-server 
 * File Name:GdsAppActionExtRSVImpl.java 
 * Package Name:com.zengshi.ecp.goods.dubbo.impl.app 
 * Date:2016年11月14日上午9:28:04 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.dubbo.impl.app;

import org.springframework.beans.factory.annotation.Autowired;

import com.zengshi.ecp.goods.dubbo.interfaces.app.IGdsAppActionExtRSV;
import com.zengshi.ecp.goods.service.busi.interfaces.app.IGdsAppActionExtSV;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: 商品域APP服务接口实现类<br>
 * Date:2016年11月14日上午9:28:04  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public class GdsAppActionExtRSVImpl implements IGdsAppActionExtRSV {
    @Autowired(required = false)
    private IGdsAppActionExtSV gdsAppActionExtSV;
    
    

}

