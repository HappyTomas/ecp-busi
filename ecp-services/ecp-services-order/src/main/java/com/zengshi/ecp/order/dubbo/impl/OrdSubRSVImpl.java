package com.zengshi.ecp.order.dubbo.impl;

import com.zengshi.ecp.order.dubbo.dto.*;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdSubRSV;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdExportFileSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdSubSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

public class OrdSubRSVImpl implements IOrdSubRSV {

    private static final String MODULE = OrdSubRSVImpl.class.getName();
    
    @Resource
    private IOrdSubSV ordSubSV;

    @Autowired(required=false)
    private IOrdExportFileSV ordExportFileSV;
    
    @Override
    public PageResponseDTO<RShowOrdSubResponse> queryOrderSub(RShowOrdSubRequest rShowOrdSubRequest) throws BusinessException {
        if(rShowOrdSubRequest == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if(StringUtil.isEmpty(rShowOrdSubRequest.getOrderId())){
            LogUtil.info(MODULE, "订单号为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311000);
        }
        PageResponseDTO<RShowOrdSubResponse> rsosr = null;
        
        try {
            rsosr = this.ordSubSV.queryOrderByOrderIdPage(rShowOrdSubRequest);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310001);
        }
        return rsosr;
    }

    @Override
    public PageResponseDTO<RShowOrdSubResponse> queryOrderSubByStaffId(
            RQueryOrderRequest rQueryOrderRequest) throws BusinessException {
        if(rQueryOrderRequest == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if(rQueryOrderRequest.getStaffId()==null){
            LogUtil.info(MODULE, "买家id不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311003);            
        }
        PageResponseDTO<RShowOrdSubResponse> rsosr = null;
        
        try {
            rsosr = this.ordSubSV.queryOrderSubByStaffId(rQueryOrderRequest);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310001);
        }
        return rsosr;
    }

    @Override
    public PageResponseDTO<RGoodSaleResponse> queryGoodSaleInfo(RGoodSaleRequest rGoodSaleRequest)
            throws BusinessException {
        if(rGoodSaleRequest==null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000);
        }

        PageResponseDTO<RGoodSaleResponse> r = null;
        try {
            r = this.ordSubSV.queryGoodSaleInfo(rGoodSaleRequest);
        }catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310001);
        }

        return r;
    }

    @Override
    public RGoodSaleSumResp queryGoodSaleSum(RGoodSaleRequest rGoodSaleRequest) throws BusinessException {
        if(rGoodSaleRequest==null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000);
        }

        RGoodSaleSumResp resp = new RGoodSaleSumResp();
        try {
            resp = this.ordSubSV.queryGoodSaleSum(rGoodSaleRequest);
        }catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310001);
        }

        return resp;
    }

    @Override
    public RExportExcleResponse exportGoodSaleExcel(RGoodSaleRequest rGoodSaleRequest) throws BusinessException {
        if(rGoodSaleRequest==null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000);
        }

        RExportExcleResponse resp = new RExportExcleResponse();
        try {
            resp = this.ordExportFileSV.exportGoodSaleExcel(rGoodSaleRequest);
        }catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310001);
        }

        return resp;
    }

    @Override
    public List<ROrdSubStaffIdxResp> queryOrderSubByStaffIdAndSkuid(ROrdSubStaffIdxReq rOrdSubStaffIdxReq) throws BusinessException {
        if(rOrdSubStaffIdxReq == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000);
        }
        if(rOrdSubStaffIdxReq.getStaffId()==null){
            LogUtil.info(MODULE, "买家id不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311003);
        }
        List<ROrdSubStaffIdxResp> rsosr = null;
        try {
            rsosr = this.ordSubSV.queryOrderSubByStaffIdAndSkuid(rOrdSubStaffIdxReq);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310001);
        }
        return rsosr;
    }
}

