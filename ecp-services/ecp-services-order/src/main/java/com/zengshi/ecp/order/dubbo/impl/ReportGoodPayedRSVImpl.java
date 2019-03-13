package com.zengshi.ecp.order.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dubbo.dto.RGoodSaleRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdStaffTradeInfoReq;
import com.zengshi.ecp.order.dubbo.dto.ROrdStaffTradeInfoResp;
import com.zengshi.ecp.order.dubbo.dto.RQueryGoodPayedRequest;
import com.zengshi.ecp.order.dubbo.dto.RSalesChartRequest;
import com.zengshi.ecp.order.dubbo.dto.RSalesChartResponse;
import com.zengshi.ecp.order.dubbo.interfaces.IReportGoodPayedRSV;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.service.busi.interfaces.report.IGoodPayedSV;
import com.zengshi.ecp.order.service.busi.interfaces.report.IGoodStaffPayedSV;
import com.zengshi.ecp.order.service.busi.interfaces.report.IOrdGdsSaleSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;

public class ReportGoodPayedRSVImpl implements IReportGoodPayedRSV {
    
    @Resource
    private IGoodPayedSV goodPayedSV;

    @Resource
    private IGoodStaffPayedSV goodStaffPayedSV;
    
    @Resource
    private IOrdGdsSaleSV ordGdsSaleSV;
    
    private static final String MODULE = ReportGoodPayedRSVImpl.class.getName();

    @Override
    public Long querySumBuyNumBySkuId(RQueryGoodPayedRequest rQueryGoodPayedRequest)
            throws BusinessException {
        
        if(rQueryGoodPayedRequest==null){
            LogUtil.info(MODULE, "入参不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if(rQueryGoodPayedRequest.getSkuId() == null){
            LogUtil.info(MODULE, "单品ID为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_331000);            
        }
        Long sumNum = 0l;
        try {
            sumNum = this.goodPayedSV.querySumBuyNumBySkuId(rQueryGoodPayedRequest);
        } catch (BusinessException be) {             
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_330000);
        }
        return sumNum;
    }

    @Override
    public RSalesChartResponse querySkuSalesChart(RSalesChartRequest rSalesChartRequest)
            throws BusinessException {
        if(rSalesChartRequest==null){
            LogUtil.info(MODULE, "入参不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if(rSalesChartRequest.getSiteId() == null){
            LogUtil.info(MODULE, "站点ID为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300002);            
        }
        if(rSalesChartRequest.getTopNum() == 0){
            rSalesChartRequest.setTopNum(10);
        }
        return this.goodPayedSV.querySkuSalesChart(rSalesChartRequest);
    }

    @Override
    public Long querySumBuyNumByGoodStaff(RQueryGoodPayedRequest rQueryGoodPayedRequest)
            throws BusinessException{
        if(rQueryGoodPayedRequest==null){
            LogUtil.info(MODULE, "入参不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000);
        }
        if(rQueryGoodPayedRequest.getSkuId() == null){
            LogUtil.info(MODULE, "单品ID为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_331000);
        }
        Long sumNum = 0l;
        try {
            sumNum = this.goodStaffPayedSV.querySumBuyNumByGoodStaff(rQueryGoodPayedRequest);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_330000);
        }
        return sumNum;

    }
    @Override
    public Long queryStaffBuyNumByGoodStaff(RQueryGoodPayedRequest rQueryGoodPayedRequest)
            throws BusinessException{
        if(rQueryGoodPayedRequest==null){
            LogUtil.info(MODULE, "入参不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000);
        }
        if(rQueryGoodPayedRequest.getSkuId() == null){
            LogUtil.info(MODULE, "单品ID为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_331000);
        }
        Long sumNum = 0l;
        try {
            sumNum = this.goodStaffPayedSV.queryStaffBuyNumByGoodStaff(rQueryGoodPayedRequest);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_330000);
        }
        return sumNum;

    }
    @Override
    public ROrdStaffTradeInfoResp queryStaffTradeTimes(ROrdStaffTradeInfoReq rOrdStaffTradeInfoReq)
            throws BusinessException {
        if(rOrdStaffTradeInfoReq==null){
            LogUtil.info(MODULE, "入参不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000);
        }
        ROrdStaffTradeInfoResp  resp = null;
        try {
            resp = this.goodStaffPayedSV.queryStaffTradeTimes(rOrdStaffTradeInfoReq);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_330000);
        }
        return resp;
    }

    @Override
    public Long querySumBuyNumByShopId(RGoodSaleRequest rGoodSaleRequest) throws BusinessException {
        if(rGoodSaleRequest==null){
            LogUtil.info(MODULE, "入参不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000);
        }
        if(rGoodSaleRequest.getShopId() ==null || rGoodSaleRequest.getShopId() < 1){
            LogUtil.info(MODULE, "店铺id不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311002);
        }
        Long  resp = null;
        try {
            resp = this.ordGdsSaleSV.querySumBuyNumByShopId(rGoodSaleRequest);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_330000);
        }
        return resp;
    }
}

