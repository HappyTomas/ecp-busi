package com.zengshi.ecp.order.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.order.dao.model.OrdCartItem;
import com.zengshi.ecp.order.dubbo.dto.RGroupChgRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartChgRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartChgResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartItemRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartItemResponse;
import com.zengshi.ecp.order.dubbo.dto.RPreOrdSubResponse;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdCartItemRSV;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdCartItemSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdCartSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;


public class OrdCartItemRSVImpl implements IOrdCartItemRSV {
    
    private static final String MODULE = OrdCartItemRSVImpl.class.getName();
    
    @Resource
    private IOrdCartItemSV ordCartItemSV;
    
    @Resource
    private IOrdCartSV ordCartSV;

    @Override
    public ROrdCartChgResponse updateOrdCartItemAmount(ROrdCartChgRequest info)  throws BusinessException{
        if(info==null){
            LogUtil.info(MODULE, "入参不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if(info.getrOrdCartItemRequest().getStaffId()==null){
            LogUtil.info(MODULE, "买家id不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311003);            
        }
        if(info.getrOrdCartItemRequest().getId()==null){
            LogUtil.info(MODULE, "购物车明细id不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311003);            
        }  
        ROrdCartChgResponse rpor = null;
        try {
           this.ordCartItemSV.updateOrdCartItemAmount(info.getrOrdCartItemRequest());
           rpor = this.ordCartSV.queryAssembleParamForProm(info);
        } catch (BusinessException be) {
            
            LogUtil.error(MODULE, "===业务异常==="+be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常==="+e);
            
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_350005);
        }        
        return rpor;
    }

    @Override
    public ROrdCartChgResponse delOrdCartItem(ROrdCartChgRequest info)  throws BusinessException{
        if(info==null){
            LogUtil.info(MODULE, "入参不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if(info.getrOrdCartItemRequest().getStaffId()==null){
            LogUtil.info(MODULE, "买家id不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311003);            
        }
        if(info.getrOrdCartItemRequest().getId()==null){
            LogUtil.info(MODULE, "购物车明细id不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311003);            
        }
        ROrdCartChgResponse rpor = null;
        try {
            
            this.ordCartItemSV.deleteOrdCartItem(info.getrOrdCartItemRequest());
            rpor = this.ordCartSV.queryAssembleParamForProm(info);
            
         } catch (BusinessException be) {             
             LogUtil.error(MODULE, "===业务异常==="+be);
             throw be;
         } catch (Exception e) {
             LogUtil.error(MODULE, "===系统异常==="+e);
             throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_350006);
         } 
        return rpor;
    }

    @Override
    public ROrdCartChgResponse updateOrdCartItemProm(ROrdCartChgRequest info) throws BusinessException {
        if(info==null){
            LogUtil.info(MODULE, "入参不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if(info.getrOrdCartItemRequest().getStaffId()==null){
            LogUtil.info(MODULE, "买家id不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311003);            
        }
        if(info.getrOrdCartItemRequest().getId()==null){
            LogUtil.info(MODULE, "购物车明细id不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311003);            
        }
        ROrdCartChgResponse rpor = null;
        try {
            this.ordCartItemSV.updateOrdCartItemProm(info.getrOrdCartItemRequest());
            rpor = this.ordCartSV.queryAssembleParamForProm(info);
         } catch (BusinessException be) {             
             LogUtil.error(MODULE, "===业务异常==="+be);
             throw be;
         } catch (Exception e) {
             LogUtil.error(MODULE, "===系统异常==="+e);
             throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_350007);
         }   
        return rpor;
    }

    @Override
    public void updateGroupAmount(RGroupChgRequest rGroupChgRequest) throws BusinessException {
        if(rGroupChgRequest==null){
            LogUtil.info(MODULE, "入参不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        try {
            this.ordCartItemSV.updateGroupAmount(rGroupChgRequest);
         } catch (BusinessException be) {             
             LogUtil.error(MODULE, "===业务异常==="+be);
             throw be;
         } catch (Exception e) {
             LogUtil.error(MODULE, "===系统异常==="+e);
             throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_350008);
         } 
    }

    @Override
    public void deleteGroup(RGroupChgRequest rGroupChgRequest) throws BusinessException {
        if(rGroupChgRequest==null){
            LogUtil.info(MODULE, "入参不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        try {
            this.ordCartItemSV.deleteGroup(rGroupChgRequest);
         } catch (BusinessException be) {             
             LogUtil.error(MODULE, "===业务异常==="+be);
             throw be;
         } catch (Exception e) {
             LogUtil.error(MODULE, "===系统异常==="+e);
             throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_350009);
         } 
    }

    @Override
    public void updateOrdCartItemAmountNoProm(ROrdCartItemRequest rOrdCartItemRequest)
            throws BusinessException {
        if(rOrdCartItemRequest==null){
            LogUtil.info(MODULE, "入参不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if(rOrdCartItemRequest.getStaffId()==null){
            LogUtil.info(MODULE, "买家id不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311003);            
        }
        try {
           this.ordCartItemSV.updateOrdCartItemAmount(rOrdCartItemRequest);
        } catch (BusinessException be) {
            
            LogUtil.error(MODULE, "===业务异常==="+be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常==="+e);
            
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_350005);
        }        
    }

    @Override
    public ROrdCartItemResponse queryCartItemByItemId(ROrdCartItemCommRequest rOrdCartItemRequest)
            throws BusinessException {
        ROrdCartItemResponse ordCartItemResp = null;
        if(rOrdCartItemRequest==null){
            LogUtil.info(MODULE, "入参不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if(rOrdCartItemRequest.getId()==null){
            LogUtil.info(MODULE, "购物车明细id不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311003);            
        }
        try {
            ordCartItemResp = new ROrdCartItemResponse();
            OrdCartItem ordCartItem = this.ordCartItemSV.queryCartItemByKey(rOrdCartItemRequest.getId());
            
            ObjectCopyUtil.copyObjValue(ordCartItem, ordCartItemResp, "", false);
        } catch (BusinessException be) {             
             LogUtil.error(MODULE, "===业务异常==="+be);
             throw be;
         } catch (Exception e) {
             LogUtil.error(MODULE, "===系统异常==="+e);            
             throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_350005);
         }        
        return ordCartItemResp;
    }

}

