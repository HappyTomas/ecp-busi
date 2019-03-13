package com.zengshi.ecp.goods.service.busi.interfaces;

import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: 商品快照服务（旧  <font color='red'>不再使用</font>） <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年9月25日下午4:58:18 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version
 * @since JDK 1.6
 */
@Deprecated
public interface IGdsSnapSV {

    /**
     * 保存全部商品关联表快照
     * 
     * @param gdsInfo
     * @return
     */
    @Deprecated
    Long saveAllGdsRelatedSnap(GdsSkuInfoReqDTO gdsSkuInfoReqDTO) throws BusinessException;

}
