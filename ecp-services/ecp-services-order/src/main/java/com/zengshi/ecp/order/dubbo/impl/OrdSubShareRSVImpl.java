package com.zengshi.ecp.order.dubbo.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dubbo.dto.OrdSubShareReq;
import com.zengshi.ecp.order.dubbo.dto.OrdSubShareResp;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdSubShareRSV;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdSubShareSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

public class OrdSubShareRSVImpl implements IOrdSubShareRSV {
	private static final String MODULE = OrdSubShareRSVImpl.class.getName();
	
	@Resource
	private IOrdSubShareSV ordSubShareSV;
	@Override
	public void updateOrdSubShareStatus(OrdSubShareReq req) throws BusinessException {
		// TODO Auto-generated method stub
		if(req == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if(StringUtil.isEmpty(req.getOrderId())){
            LogUtil.info(MODULE, "订单号为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311000);
        }
        if(StringUtil.isEmpty(req.getStatus())){
            LogUtil.info(MODULE, "状态不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311200);
        }
        try{
        	ordSubShareSV.updateOrdSubShareStatus(req);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310001);
        }
	}

	@Override
	public List<OrdSubShareResp> queryOrdSubShareList(OrdSubShareReq req) throws BusinessException {
		// TODO Auto-generated method stub
		List<OrdSubShareResp> list = null;
		if(req == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if(StringUtil.isEmpty(req.getOrderId())){
            LogUtil.info(MODULE, "订单号为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311000);
        }
        try{
        	list = ordSubShareSV.queryOrdSubShareList(req);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310001);
        }
        return list;
	}

}
