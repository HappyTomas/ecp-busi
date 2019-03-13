package com.zengshi.ecp.order.dubbo.impl;

import com.zengshi.ecp.order.dubbo.dto.*;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdDeliveryRSV;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.facade.interfaces.eventual.IOrdDeliveryMainSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdBackApplySV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdDeliveryBatchSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

public class OrdDeliveryRSVImpl implements IOrdDeliveryRSV {
    
    private static final String MODULE = OrdDeliveryRSVImpl.class.getName();
    
    @Resource
    private IOrdDeliveryMainSV ordDeliveryMainSV;
    
    @Resource
    private IOrdDeliveryBatchSV ordDeliveryBatchSV;

    @Autowired(required = false)
    private IOrdBackApplySV ordBackApplySV;
    

    @Override
    public void orderDelivery(RConfirmDeliveRequest rConfirmDeliveRequest)  throws BusinessException{
        if(rConfirmDeliveRequest == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if(StringUtil.isBlank(rConfirmDeliveRequest.getOrderId())){
            LogUtil.info(MODULE, "订单号不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311000); 
        }
//        if(rConfirmDeliveRequest.getShopId() == null || rConfirmDeliveRequest.getShopId() < 1){
//            LogUtil.info(MODULE, "店铺ID不能为空");
//            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311002); 
//            
//        }
//        if(rConfirmDeliveRequest.getStaffId() == null || rConfirmDeliveRequest.getStaffId() < 1){
//            LogUtil.info(MODULE, "买家ID不能为空");
//            
//            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311003);
//        }
        if(rConfirmDeliveRequest.getrConfirmSubInfo() == null){
            LogUtil.info(MODULE, "子订单信息不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311102);
        }
//        if(rConfirmDeliveRequest.getIsSendAll() == null){
//            LogUtil.info(MODULE, "是否全部发货标识不能为空");
//            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311006);
//        }
        for(RConfirmSubInfo rcs:rConfirmDeliveRequest.getrConfirmSubInfo()){
            if(StringUtil.isBlank(rcs.getOrderSubId())){
                LogUtil.info(MODULE, "子订单不能为空");
                throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311001);
            }
            if(StringUtil.isBlank(rcs.getIsImport())){
                LogUtil.info(MODULE, "商品是否导入实体编号不能为空");
                throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311003);
            }
        }
        
        try {
            
            //如果订单在退款流程中不允许发货
            SRefundInfo sRefundInfo = this.ordBackApplySV.queryRefundStatus(rConfirmDeliveRequest.getOrderId());
            if(sRefundInfo != null && "1".equals(sRefundInfo.getRefundStatus())){
                LogUtil.info(MODULE, rConfirmDeliveRequest.getOrderId()+"订单在退款流程中不允许发货");
                throw new BusinessException(MsgConstants.ChkMsgCode.CHK_REFUND_APPLY);
            }
            this.ordDeliveryMainSV.orderDelivery(rConfirmDeliveRequest);
        } catch (BusinessException be) {
            
            LogUtil.error(MODULE, "===业务异常==="+be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常==="+e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310002);
        }
    }


    @Override
    public RDeliveryPrintResponse queryInvoiceInfo(RDeliveryPrintRequest rDeliveryPrintRequest) {
        if(rDeliveryPrintRequest == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if(StringUtil.isBlank(rDeliveryPrintRequest.getOrderId())){
            LogUtil.info(MODULE, "订单号不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311000); 
        }
        RDeliveryPrintResponse rdpr = null;
        try {
            rdpr = this.ordDeliveryBatchSV.queryInvoiceInfo(rDeliveryPrintRequest);
        } catch (BusinessException be) {
            
            LogUtil.error(MODULE, "===业务异常==="+be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常==="+e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310027);
        }
        return rdpr;
    }

}

