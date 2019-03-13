package com.zengshi.ecp.goods.dubbo.impl.validation;

import javax.annotation.Resource;

import com.zengshi.ecp.general.order.dto.ROrdCartsChkResponse;
import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.general.order.interfaces.IOrdCartsChkRSV;
import com.zengshi.ecp.goods.service.busi.impl.validation.GdsOrdCartsChkSVImpl;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年9月25日下午4:03:27  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public class GdsOrdCartsChkRSVImpl implements IOrdCartsChkRSV{
    
    @Resource(name="gdsOrdCartsChkSV")
    private GdsOrdCartsChkSVImpl gdsOrdCartsChkSV;

    @Override
    public ROrdCartsChkResponse checkOrdCart(ROrdCartsCommRequest info) throws BusinessException{
        return gdsOrdCartsChkSV.checkOrdCart(info);
    }

}

