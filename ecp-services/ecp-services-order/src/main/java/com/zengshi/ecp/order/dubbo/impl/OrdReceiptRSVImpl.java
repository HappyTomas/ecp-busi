package com.zengshi.ecp.order.dubbo.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dubbo.dto.ROrdMainResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdReceiptRequest;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdReceiptRSV;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.facade.interfaces.eventual.IOrdReceiptMainSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdMainSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

public class OrdReceiptRSVImpl implements IOrdReceiptRSV {
    
    private static final String MODULE = OrdReceiptRSVImpl.class.getName();
    
    @Resource
    private IOrdReceiptMainSV ordReceiptMainSV;
    
    @Resource
    private IOrdMainSV ordMainSV;

    @Override
    public void orderReceipt(ROrdReceiptRequest rOrdReceiptRequest) throws BusinessException {
        if(rOrdReceiptRequest == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if (StringUtil.isBlank(rOrdReceiptRequest.getOrderId())) {
            LogUtil.info(MODULE, "订单号为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311000);
        }
//        if (rOrdReceiptRequest.getShopId() == null || rOrdReceiptRequest.getShopId() < 1) {
//            LogUtil.info(MODULE, "店铺ID为空");
//            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311002);
//        }
        if (rOrdReceiptRequest.getStaffId() == null || rOrdReceiptRequest.getStaffId() < 1) {
            LogUtil.info(MODULE, "买家ID为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311003);
        }
        LogUtil.info(MODULE, rOrdReceiptRequest.toString());
        try {
            this.ordReceiptMainSV.orderReceipt(rOrdReceiptRequest);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常==="+be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常==="+e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310003);
        }
    }
    
    @Override
    public List<ROrdMainResponse> queryNotReceiptOrder(ROrdReceiptRequest rOrdReceiptRequest)
            throws BusinessException {
        if(rOrdReceiptRequest == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if(rOrdReceiptRequest.getTableIndex()<0){
            LogUtil.info(MODULE, "分表标识不能小于0");
            throw new BusinessException(MsgConstants.PayInputMsgCode.PAY_INPUT_300012); 
        }
        LogUtil.info(MODULE, rOrdReceiptRequest.toString());
        try {
            return this.ordMainSV.queryNotReceiptOrder(rOrdReceiptRequest);
            
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常==="+be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常==="+e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310003);
        }
    }

}

