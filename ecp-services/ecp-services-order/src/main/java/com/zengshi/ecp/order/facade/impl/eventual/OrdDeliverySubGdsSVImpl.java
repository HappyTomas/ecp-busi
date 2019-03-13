package com.zengshi.ecp.order.facade.impl.eventual;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import com.zengshi.ecp.goods.dubbo.dto.DeliverySkuStcokMainReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.DeliverySkuStcokReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoExternalRSV;
import com.zengshi.ecp.order.dao.model.OrdSub;
import com.zengshi.ecp.order.dubbo.dto.RConfirmDeliveRequest;
import com.zengshi.ecp.order.dubbo.dto.RConfirmSubInfo;
import com.zengshi.ecp.order.dubbo.dto.SBaseAndBatchInfo;
import com.zengshi.ecp.order.dubbo.dto.SBaseAndSubInfo;
import com.zengshi.ecp.order.dubbo.util.DelyConstants;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.facade.interfaces.eventual.IOrdDeliverySubGdsSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdDeliveryEntitySV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdSubSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.alibaba.fastjson.JSON;
import com.distribute.tx.common.TransactionStatus;

public class OrdDeliverySubGdsSVImpl implements IOrdDeliverySubGdsSV {
    
    @Resource
    private IOrdDeliveryEntitySV ordDeliveryEntitySV;
    
    @Resource
    private IOrdSubSV ordSubSV;

//    @Resource
//    private IGdsStockRSV gdsStockRSV;
    
    @Resource
    private IGdsInfoExternalRSV gdsInfoExternalRSV;

    private static final String MODULE = OrdDeliverySubGdsSVImpl.class.getName();
    
    @Override
    public void joinTransaction(JSONObject message, TransactionStatus status, String transactionName) {
        final RConfirmDeliveRequest rcdr= JSON.parseObject(message.toString(), RConfirmDeliveRequest.class);
        LogUtil.info(MODULE,"============="+rcdr.toString());
        LogUtil.info(MODULE,"============="+rcdr.getrConfirmSubInfo().toString());
        DeliverySkuStcokMainReqDTO dsmd = new DeliverySkuStcokMainReqDTO();
        dsmd.setDeliverySno(rcdr.getBatchId());
        dsmd.setStaffId(rcdr.getStaff().getId());
        List<DeliverySkuStcokReqDTO> dssds = new ArrayList<DeliverySkuStcokReqDTO>();
        for(RConfirmSubInfo rcs:rcdr.getrConfirmSubInfo()){
            DeliverySkuStcokReqDTO dssd = new DeliverySkuStcokReqDTO();
            dssd.setOrderId(rcdr.getOrderId());
            dssd.setSubOrder(rcs.getOrderSubId());
            dssd.setStockId(rcs.getStockId());
            dssd.setIsDelivAll(rcs.getIsSendAll());
            dssd.setDeliveryCount(rcs.getDeliveryAmount());
            if(rcdr.getBatchId() != null){
                dssd.setDeliverySno(rcdr.getBatchId().toString());
            }

            SBaseAndSubInfo sbai = new SBaseAndSubInfo();
            sbai.setOrderId(rcdr.getOrderId());
            sbai.setOrderSubId(rcs.getOrderSubId());
            OrdSub os = this.ordSubSV.findByOrderSubId(sbai);
            if(os == null){
                LogUtil.info(MODULE, "未找到["+rcs.getOrderSubId()+"]该子订单信息");
                throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312002);
            }
            
            dssd.setSkuId(os.getSkuId());
            dssd.setPrnFlag(os.getPrnFlag());
            dssd.setIsSerial(false);
            dssd.setGdsTypeId(os.getGdsType());
            if(DelyConstants.IsDelyImportEntity.IS_DELY_IMPORT_ENTITY_TRUE.equals(rcs.getIsImport())){
                dssd.setIsSerial(true);
                SBaseAndBatchInfo sba = new SBaseAndBatchInfo();
                sba.setShopId(rcdr.getShopId());
                sba.setOrderSubId(rcs.getOrderSubId());
                sba.setBatchId(rcdr.getBatchId());
                dssd.setSerialNoList(this.ordDeliveryEntitySV.queryBySubId(sba));
            }
            dssds.add(dssd);
        }
        LogUtil.info(MODULE, dssds.toString());
        dsmd.setDeliverySkuStcokDTOs(dssds);
        LogUtil.info(MODULE, dsmd.toString());
        try {
            this.gdsInfoExternalRSV.deliverOrderGds(dsmd);
            System.out.println("+++++++++++++++++++++++++++++");
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            status.setRollbackOnly();
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            status.setRollbackOnly();
            throw new BusinessException(MsgConstants.OtherSysMsgCode.CALL_GOODS_SERVER_340000);
        }
        
    }

}

