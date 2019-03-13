package com.zengshi.ecp.staff.facade.interfaces.eventual;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;


/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 店铺相关的分式事务接口<br>
 * Date:2015-9-4下午4:04:53  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl
 * @version  
 * @since JDK 1.7
 */
public interface IShopMainSV {

    
    
    /**
     * 
     * buildStorck:(新建店铺时，新建仓库的主事务). <br/> 
     * 
     * @author wangbh 
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public ShopInfoResDTO buildStorck(ShopInfoReqDTO req) throws BusinessException;
    
    /**
     * 
     * invalidShop:(失效店铺时，同时下架店铺的所有商品主事务). <br/> 
     * 
     * @author huangxl 
     * @param shopId
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public void invalidShop(ShopInfoReqDTO req) throws BusinessException;
    
}

