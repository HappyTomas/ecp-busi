package com.zengshi.ecp.staff.dubbo.impl;


import javax.annotation.Resource;

import com.zengshi.ecp.general.order.dto.ROrdCartsChkResponse;
import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.general.order.interfaces.IOrdCartsChkRSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.service.busi.score.interfaces.IScoreToOrderSV;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: <br>
 * Date:2015年9月28日上午10:18:38  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author wangbh
 * @version  
 * @since JDK 1.7
 */
public class ScoreOrdCartsChkRSVImpl implements IOrdCartsChkRSV{

    @Resource
    private IScoreToOrderSV scoreToOrderSV;
    
    @Override
    public ROrdCartsChkResponse checkOrdCart(ROrdCartsCommRequest ordCarts) throws BusinessException {
        
        ROrdCartsChkResponse ordCartsChkResponse = new ROrdCartsChkResponse();
        //调用积分校验服务
        boolean resultScore = scoreToOrderSV.checkOrdCart(ordCarts);
        ordCartsChkResponse.setStatus(resultScore);
        if (resultScore) {
            ordCartsChkResponse.setMsg("校验成功！");
        } else {
            ordCartsChkResponse.setMsg("可用积分不足！");
        }
        
        return ordCartsChkResponse;
    }

    
}

