package com.zengshi.ecp.order.dubbo.impl;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.order.dubbo.dto.REntityImportRequest;
import com.zengshi.ecp.order.dubbo.dto.REntityInputRequest;
import com.zengshi.ecp.order.dubbo.dto.RFileImportRequest;
import com.zengshi.ecp.order.dubbo.dto.RShowEntityResponse;
import com.zengshi.ecp.order.dubbo.dto.RShowImportInfoResponse;
import com.zengshi.ecp.order.dubbo.dto.RShowImportRequest;
import com.zengshi.ecp.order.dubbo.dto.RShowImportResponse;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdEntityAddRSV;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdEntityImportSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdFileImportSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

public class OrdEntityAddRSVImpl implements IOrdEntityAddRSV {

    private static final String MODULE = OrdEntityAddRSVImpl.class.getName();

    @Resource
    private IOrdEntityImportSV ordEntityImportSV;

    @Resource
    private IOrdFileImportSV ordFileImportSV;

    @Override
    public void entityInput(REntityInputRequest rEntityInputRequest)  throws BusinessException{
        if(rEntityInputRequest == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if (StringUtil.isBlank(rEntityInputRequest.getOrderId())) {
            LogUtil.info(MODULE, "订单号为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311000);
        }
        if (StringUtil.isBlank(rEntityInputRequest.getOrderSubId())) {
            LogUtil.info(MODULE, "子订单号为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311001);
        }
        if (rEntityInputRequest.getShopId() == null || rEntityInputRequest.getShopId() < 1) {
            LogUtil.info(MODULE, "店铺ID为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311002);
        }
        if (rEntityInputRequest.getStaffId() == null || rEntityInputRequest.getStaffId() < 1) {
            LogUtil.info(MODULE, "买家ID为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311003);
        }
        if (rEntityInputRequest.getSendNum() == null || rEntityInputRequest.getSendNum() < 1) {
            LogUtil.info(MODULE, "发货数量小于0");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311100);
        }
        if (CollectionUtils.isEmpty(rEntityInputRequest.getEntitys())) {
            LogUtil.info(MODULE, "录入的实体编号为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311101);
        }
        try {
            this.ordEntityImportSV.saveEntityInput(rEntityInputRequest);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常==="+be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常==="+e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310010);
        }
    }

    @Override
    public void deleteByOrdSubEntity(REntityInputRequest rEntityInputRequest)  throws BusinessException{
        if(rEntityInputRequest == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if (StringUtil.isBlank(rEntityInputRequest.getOrderId())) {
            LogUtil.info(MODULE, "订单号为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311000);
        }
        if (StringUtil.isBlank(rEntityInputRequest.getOrderSubId())) {
            LogUtil.info(MODULE, "子订单号为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311001);
        }
        if (rEntityInputRequest.getShopId() == null || rEntityInputRequest.getShopId() < 1) {
            LogUtil.info(MODULE, "店铺ID为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311002);
        }
        try {
            this.ordEntityImportSV.deleteByOrdSubId(rEntityInputRequest);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常==="+be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常==="+e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310011);
        }
    }

    @Override
    public PageResponseDTO<RShowEntityResponse> queryOrderSubEntity(
            REntityInputRequest rEntityInputRequest)  throws BusinessException{
        if(rEntityInputRequest == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if (StringUtil.isBlank(rEntityInputRequest.getOrderId())) {
            LogUtil.info(MODULE, "订单号为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311000);
        }
        if (StringUtil.isBlank(rEntityInputRequest.getOrderSubId())) {
            LogUtil.info(MODULE, "子订单号为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311001);
        }
        if (rEntityInputRequest.getShopId() == null || rEntityInputRequest.getShopId() < 1) {
            LogUtil.info(MODULE, "店铺ID为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311002);
        }
        PageResponseDTO<RShowEntityResponse> rser = null;
        try {
            rser = this.ordEntityImportSV.queryOrderSubEntityPage(rEntityInputRequest);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常==="+be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常==="+e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310012);
        }
        return rser;
    }

    @Override
    public void entityImport(RFileImportRequest rEntityAddImportRequest)  throws BusinessException{
        if(rEntityAddImportRequest == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if (StringUtil.isBlank(rEntityAddImportRequest.getOrderId())) {
            LogUtil.info(MODULE, "订单号为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311000);
        }
        if (rEntityAddImportRequest.getShopId() == null || rEntityAddImportRequest.getShopId() < 1) {
            LogUtil.info(MODULE, "店铺ID为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311002);
        }
        if (StringUtil.isBlank(rEntityAddImportRequest.getFileName())) {
            LogUtil.info(MODULE, "批导文件不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311000);
        }
        rEntityAddImportRequest.setModuleName(OrdConstants.Common.COMMON_NAME);
        try {
            this.ordEntityImportSV.saveEntityImport(rEntityAddImportRequest);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常==="+be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常==="+e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310013);
        }
    }

    @Override
    public PageResponseDTO<RShowImportResponse> queryImportStatus(
            RShowImportRequest rShowImportRequest) throws BusinessException {
        if(rShowImportRequest == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if (rShowImportRequest.getShopId() == null || rShowImportRequest.getShopId() < 1) {
            LogUtil.info(MODULE, "店铺ID为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311002);
        }
        PageResponseDTO<RShowImportResponse> rsir = null;
        try {
            rsir= this.ordFileImportSV.queryAddImportStatus(rShowImportRequest);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常==="+be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常==="+e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310014);
        }
        
        return rsir; 
    }

    @Override
    public PageResponseDTO<RShowImportInfoResponse> queryFailImportInfo(
            REntityImportRequest rEntityImportRequest) throws BusinessException {
        
        if(rEntityImportRequest == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        
        if (rEntityImportRequest.getShopId() < 1) {
            LogUtil.info(MODULE, "店铺ID为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311002);
        }
        if (rEntityImportRequest.getImportNo() == null || rEntityImportRequest.getImportNo() < 1) {
            LogUtil.info(MODULE, "批次号为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311105);
        }
        PageResponseDTO<RShowImportInfoResponse> rsi = null;
        
        try {
            rsi = this.ordEntityImportSV.queryFailImportInfo(rEntityImportRequest);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常==="+be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常==="+e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310015);
        }
        return rsi;
    }

    @Override
    public void deleteFailImport(REntityImportRequest rEntityImportRequest)  throws BusinessException{
        if(rEntityImportRequest == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if (rEntityImportRequest.getShopId() == null || rEntityImportRequest.getShopId() < 1) {
            LogUtil.info(MODULE, "店铺ID为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311002);
        }
        if (rEntityImportRequest.getImportNo() == null || rEntityImportRequest.getImportNo() < 1) {
            LogUtil.info(MODULE, "批次号为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311105);
        }
        try {
            this.ordEntityImportSV.deleteFailImport(rEntityImportRequest);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常==="+be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常==="+e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310016);
        }
    }

}
