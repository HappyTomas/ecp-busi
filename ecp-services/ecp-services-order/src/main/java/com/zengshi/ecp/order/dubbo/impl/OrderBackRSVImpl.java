package com.zengshi.ecp.order.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dubbo.dto.RBackApplyOrdReq;
import com.zengshi.ecp.order.dubbo.dto.ROrdReturnRefundReq;
import com.zengshi.ecp.order.dubbo.dto.ROrdReturnRefundResp;
import com.zengshi.ecp.order.dubbo.dto.ROrderBackReq;
import com.zengshi.ecp.order.dubbo.interfaces.IOrderBackRSV;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.service.busi.interfaces.IOrderBackSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

public class OrderBackRSVImpl implements IOrderBackRSV {
	
	private static final String MODULE = OrderBackRSVImpl.class.getName();
	
    @Resource  
    private IOrderBackSV orderBackSV;
	 
	/**
     * 
     * TODO 构造订单优惠信息. 
     * @see com.zengshi.ecp.pmph.dubbo.interfaces.IReturnBackRSV#calCulateBackInfo(com.zengshi.ecp.pmph.dubbo.dto.RBackApplyPmphOrdReq)
     */
    @Override
    public ROrdReturnRefundResp calCulateBackInfo(RBackApplyOrdReq resp)
            throws BusinessException {       
        if(StringUtil.isBlank(resp.getApplyType())){
            LogUtil.info(MODULE, "申请类型不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311010); 
        }
        try {
           return this.orderBackSV.calCulateBackInfo(resp);       
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;           
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310037);
        }
        
    }
    
    @Override
    public ROrdReturnRefundResp calCulateBackInfo(ROrderBackReq req) throws BusinessException {
        if(req == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if(StringUtil.isBlank(req.getOrderId())){
            LogUtil.info(MODULE, "订单编号不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311000); 
        }
        try {
           return this.orderBackSV.calCulateBackInfo(req);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;           
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310037);
        }
    }
    
    /**
     * 
     * TODO 修改退款金额后重新冻结积分（可选）. 
     * @see com.zengshi.ecp.pmph.dubbo.interfaces.IReturnBackRSV#modifyBackMoney(com.zengshi.ecp.pmph.dubbo.dto.ROrdReturnRefundReq)
     */
    @Override
    public boolean modifyBackMoney(ROrdReturnRefundReq req) throws BusinessException {
        if(req == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if(StringUtil.isBlank(req.getOrderId())){
            LogUtil.info(MODULE, "订单编号不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311000); 
        }
        try {
            return this.orderBackSV.modifyBackMoney(req);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;           
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310037);
        }
    }

}
