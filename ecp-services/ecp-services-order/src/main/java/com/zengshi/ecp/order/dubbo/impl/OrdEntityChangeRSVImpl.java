package com.zengshi.ecp.order.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dubbo.dto.REntityChgImportRequest;
import com.zengshi.ecp.order.dubbo.dto.REntityCodeChgRequest;
import com.zengshi.ecp.order.dubbo.dto.REntityImportRequest;
import com.zengshi.ecp.order.dubbo.dto.RShowEntityChgRequest;
import com.zengshi.ecp.order.dubbo.dto.RShowEntityChgResponse;
import com.zengshi.ecp.order.dubbo.dto.RShowImportChgInfoResponse;
import com.zengshi.ecp.order.dubbo.dto.RShowImportRequest;
import com.zengshi.ecp.order.dubbo.dto.RShowImportResponse;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdEntityChangeRSV;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdDeliveryEntitySV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdEntityChangeSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdFileImportSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

public class OrdEntityChangeRSVImpl implements IOrdEntityChangeRSV {

    private static final String MODULE = OrdEntityChangeRSVImpl.class.getName();

    @Resource
    private IOrdDeliveryEntitySV ordDeliveryEntitySV;

    @Resource
    private IOrdFileImportSV ordFileImportSV;

    @Resource
    private IOrdEntityChangeSV ordEntityChangeSV;

    @Override
    public PageResponseDTO<RShowEntityChgResponse> queryEntityChg(
            RShowEntityChgRequest rShowEntityChgRequest)  throws BusinessException{
        if(rShowEntityChgRequest == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if (rShowEntityChgRequest.getBegDate() == null) {
            LogUtil.info(MODULE, "开始时间不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311004);
        }
        if (rShowEntityChgRequest.getEndDate() == null) {
            LogUtil.info(MODULE, "结束时间不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311004);
        }
        if (rShowEntityChgRequest.getShopId() == null || rShowEntityChgRequest.getShopId() < 1) {
            LogUtil.info(MODULE, "店铺id");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311002);
        }

        PageResponseDTO<RShowEntityChgResponse> rsec = null;
        try {
            rsec = this.ordDeliveryEntitySV.queryEntityChgPage(rShowEntityChgRequest);
        } catch (BusinessException be) {
            LogUtil.info(MODULE, be.getErrorCode() + "===" + be.getErrorMessage());
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常==="+e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310020);
        }
        return rsec;

    }

    @Override
    public RShowImportChgInfoResponse entityCodeChange(REntityCodeChgRequest rEntityCodeChgRequest)  throws BusinessException{
        if(rEntityCodeChgRequest == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        
        if (rEntityCodeChgRequest.getShopId() == null || rEntityCodeChgRequest.getShopId() < 1) {
            LogUtil.info(MODULE, "店铺ID为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311002);
        }
        if (StringUtil.isBlank(rEntityCodeChgRequest.getOrderId())) {
            LogUtil.info(MODULE, "订单号为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311000);
        }
        if (StringUtil.isBlank(rEntityCodeChgRequest.getOldEntityCode())) {
            LogUtil.info(MODULE, "旧实体编号不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311106);
        }
        if (StringUtil.isBlank(rEntityCodeChgRequest.getNewEntityCode())) {
            LogUtil.info(MODULE, "新实体编号不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311107);
        }
        RShowImportChgInfoResponse rsi = null;
        try {
            rsi= this.ordDeliveryEntitySV.updateEntityCodeChangeFromWeb(rEntityCodeChgRequest);
        } catch (BusinessException be) {
            LogUtil.info(MODULE, be.getErrorCode() + "===" + be.getErrorMessage());
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常==="+e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310021);
        }
        return rsi;
    }

    @Override
    public void entityCodeChangeFromFile(REntityChgImportRequest rEntityChgImportRequest)  throws BusinessException{
        if(rEntityChgImportRequest == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if (rEntityChgImportRequest.getShopId() != null || rEntityChgImportRequest.getShopId() < 1) {
            LogUtil.info(MODULE, "店铺ID为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311002);
        }
        if (StringUtil.isBlank(rEntityChgImportRequest.getFileName())) {
            LogUtil.info(MODULE, "批导文件不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311000);
        }
        rEntityChgImportRequest.setModuleName(OrdConstants.Common.COMMON_NAME);
        try {
            this.ordDeliveryEntitySV.updateEntityCodeChangeFromFile(rEntityChgImportRequest);
        } catch (BusinessException be) {
            LogUtil.info(MODULE, be.getErrorCode() + "===" + be.getErrorMessage());
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常==="+e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310022);
        }
    }

    @Override
    public PageResponseDTO<RShowImportResponse> queryImportStatus(
            RShowImportRequest rShowImportRequest) throws BusinessException {
        if(rShowImportRequest == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if (rShowImportRequest.getShopId()==null || rShowImportRequest.getShopId() < 1) {
            LogUtil.info(MODULE, "店铺ID为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311002);
        }
        
        PageResponseDTO<RShowImportResponse> rsir = null;

        try {
            rsir = this.ordFileImportSV.queryChangeImportStatus(rShowImportRequest);
        } catch (BusinessException be) {
            LogUtil.info(MODULE, be.getErrorCode() + "===" + be.getErrorMessage());
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常==="+e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310023);
        }
        return rsir;
    }

    @Override
    public PageResponseDTO<RShowImportChgInfoResponse> queryFailImportInfo(
            REntityImportRequest rEntityImportRequest) throws BusinessException {
        if(rEntityImportRequest == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if (rEntityImportRequest.getShopId() == null || rEntityImportRequest.getShopId() < 1) {
            LogUtil.info(MODULE, "店铺ID为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311002);
        }
        if (rEntityImportRequest.getImportNo() == null || rEntityImportRequest.getImportNo()< 1) {
            LogUtil.info(MODULE, "批次号为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311105);
        }
        PageResponseDTO<RShowImportChgInfoResponse> rsii = null;

        try {
            rsii = this.ordEntityChangeSV.queryFailImportInfo(rEntityImportRequest);
        } catch (BusinessException be) {
            LogUtil.info(MODULE, be.getErrorCode() + "===" + be.getErrorMessage());
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常==="+e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310024);
        }
        return rsii;
    }

    @Override
    public void deleteFailImport(REntityImportRequest rEntityImportRequest) throws BusinessException {
        if(rEntityImportRequest == null){
            LogUtil.info(MODULE, "入参对象不能为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_300000); 
        }
        if (rEntityImportRequest.getShopId() == null || rEntityImportRequest.getShopId() < 1) {
            LogUtil.info(MODULE, "店铺ID为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311002);
        }
        if (rEntityImportRequest.getImportNo() == null || rEntityImportRequest.getImportNo()< 1) {
            LogUtil.info(MODULE, "批次号为空");
            throw new BusinessException(MsgConstants.InputMsgCode.ORD_INPUT_311105);
        }
        try {
            this.ordEntityChangeSV.deleteFailImport(rEntityImportRequest);
        } catch (BusinessException be) {
            LogUtil.info(MODULE, be.getErrorCode() + "===" + be.getErrorMessage());
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常==="+e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310025);
        }
    }


}
