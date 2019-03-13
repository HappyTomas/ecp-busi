package com.zengshi.ecp.goods.dubbo.impl.validation;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.zengshi.ecp.general.order.dto.ROrdCartsChkResponse;
import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.general.order.interfaces.IOrderChkRSV;
import com.zengshi.ecp.goods.service.busi.impl.validation.GdsOrdSubChkSVImpl;
import com.zengshi.ecp.goods.service.busi.interfaces.price.IGdsPriceSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: 订单下单商品校验<br>
 * Date:2015年9月28日上午10:33:33  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public class GdsOrdSubChkRSVImpl implements IOrderChkRSV {
    
    @Resource(name="gdsOrdSubChkSV")
    private GdsOrdSubChkSVImpl gdsOrdSubChkSV;
    
    @Autowired(required = false)
    private IGdsPriceSV gdsPriceSV;

    @Override
    public ROrdCartsChkResponse checkOrder(ROrdCartsCommRequest rOrdCartsCommRequest)
            throws BusinessException {
    	//先校验库存与上下架
    	ROrdCartsChkResponse response=gdsOrdSubChkSV.checkOrdSub(rOrdCartsCommRequest);
    	if(response.isStatus()){
    		//校验价格
    		response=gdsPriceSV.validatePrice(rOrdCartsCommRequest);
    	}
        return response;
    }
    
}

