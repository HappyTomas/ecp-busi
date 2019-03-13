package com.zengshi.ecp.staff.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.general.order.dto.ROrdCartsChkResponse;
import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.general.order.interfaces.IOrderChkRSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.service.busi.acct.interfaces.IAcctToOrderSV;
import com.zengshi.ecp.staff.service.busi.score.interfaces.IScoreToOrderSV;



/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description:订单提交验证方法 <br>
 * Date:2015年9月28日上午10:18:38  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author wangbh
 * @version  
 * @since JDK 1.7
 */
public class StaffOrdSubChkRSVImpl implements IOrderChkRSV{

    
    @Resource
    private IScoreToOrderSV scoreToOrderSV;
    
    @Resource
    private IAcctToOrderSV acctToOrderSV;
    /**
     * 
     * TODO 订单提交验证方法 
     * @see com.zengshi.ecp.general.order.interfaces.IOrderChkRSV#checkOrder(com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest)
     */
    @Override
    public ROrdCartsChkResponse checkOrder(ROrdCartsCommRequest req) throws BusinessException {
        
        ROrdCartsChkResponse res = new ROrdCartsChkResponse();
        //调用积分校验服务
        boolean result = scoreToOrderSV.checkOrdCart(req);
        if (!result) {
            res.setStatus(result);
            res.setMsg("您的积分不足！");
            return res;
        }
        //调用资金账户校验服务
        boolean resultAcct = acctToOrderSV.checkOrdCart(req);
        res.setStatus(resultAcct);
        if (resultAcct) {
            res.setMsg("校验成功！");
        } else {
            res.setMsg("资金余额不足！");
        }
        return res;
        
    }



    
}

