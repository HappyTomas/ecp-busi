package com.zengshi.ecp.order.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsResponse;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdDetailsRSV;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdMainSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

public class OrdDetailsRSVImpl implements IOrdDetailsRSV {
    
    @Resource
    private IOrdMainSV ordMainSV;

    private static final String MODULE = OrdDetailsRSVImpl.class.getName();
    @Override
    public ROrderDetailsResponse queryOrderDetails(ROrderDetailsRequest rOrderDetailsRequest)  throws BusinessException{
        if(rOrderDetailsRequest == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if(StringUtil.isBlank(rOrderDetailsRequest.getOrderId())){
            LogUtil.info(MODULE, "订单号不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311000); 
        }
        ROrderDetailsResponse rodr = null;
        try {
            rodr = this.ordMainSV.queryOrderDetails(rOrderDetailsRequest);
        } catch (BusinessException be) {
            
            LogUtil.error(MODULE, "===业务异常==="+be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常==="+e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310005);
        }
        return rodr;
    }

}

