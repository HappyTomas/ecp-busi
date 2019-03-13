package com.zengshi.ecp.unpf.service.busi.order.impl;

import com.zengshi.ecp.aip.third.dubbo.constants.AipThirdConstants;
import com.zengshi.ecp.aip.third.dubbo.dto.req.OrderShipReqDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dao.model.UnpfOrdMain;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfOrdMainReq;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfSendMainReq;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfSendSubReq;
import com.zengshi.ecp.unpf.dubbo.util.UnpfOrdConstants;
import com.zengshi.ecp.unpf.service.busi.order.interfaces.IUnpfDeliverSV;
import com.zengshi.ecp.unpf.service.busi.order.interfaces.IUnpfOrdDeliverBatchSV;
import com.zengshi.ecp.unpf.service.busi.order.interfaces.IUnpfOrdMainSV;
import com.zengshi.paas.utils.LogUtil;

import javax.annotation.Resource;

/**
 * @author: cbl
 * @date: 2016/11/16.
 */
public class UnpfDeliverSVImpl implements IUnpfDeliverSV {

    @Resource
    private IUnpfOrdDeliverBatchSV unpfOrdDeliverBatchSV;

    @Resource
    private IUnpfOrdMainSV unpfOrdMainSV;

    private static final String MODULE = UnpfDeliverSVImpl.class.getName();

    @Override
    public void saveOrderDeliver(final RUnpfSendMainReq rUnpfSendMainReq) throws BusinessException {

        try {
            Long batchId = unpfOrdDeliverBatchSV.saveUnpfOrdDeliveryBatch(rUnpfSendMainReq);
            rUnpfSendMainReq.setBatchId(batchId);

            RUnpfOrdMainReq unpfOrdMainReq = new RUnpfOrdMainReq();
            unpfOrdMainReq.setId(rUnpfSendMainReq.getOrderId());
            UnpfOrdMain unpfOrdMain =  unpfOrdMainSV.queryUnpfOrdMainIn(unpfOrdMainReq);
            //只处理天猫或有赞订单
            boolean taobao = AipThirdConstants.Commons.TAOBAO.equals(unpfOrdMain.getPlatType());
            boolean youzan = AipThirdConstants.Commons.YOUZAN.equals(unpfOrdMain.getPlatType());
            if(taobao || youzan){
                OrderShipReqDTO orderShipReqDTO = new OrderShipReqDTO();
                if (taobao) {
                    LogUtil.info(MODULE, "=============天猫订单发货开始处理");
                    orderShipReqDTO.setTid(Long.parseLong(unpfOrdMain.getOuterId()));
                } else {
                    LogUtil.info(MODULE, "=============有赞订单发货开始处理");
                    orderShipReqDTO.setTradeId(unpfOrdMain.getOuterId());
                    orderShipReqDTO.setTid(0L);
                }

                orderShipReqDTO.setOutSid(rUnpfSendMainReq.getExpressNo());
                orderShipReqDTO.setCompanyCode(rUnpfSendMainReq.getExpressId());
                orderShipReqDTO.setShopId(unpfOrdMain.getShopId());
                orderShipReqDTO.setPlatType(unpfOrdMain.getPlatType());
                if(UnpfOrdConstants.OrderStatus.ORDER_STATUS_SEND_SECTION.equals(unpfOrdMain.getStatus())){ //部分发货
                    String subSid = "";
                    for(RUnpfSendSubReq sub : rUnpfSendMainReq.getUnpfSendSubReqList()){
                        subSid += sub.getOuterSubId();
                        subSid += ",";
                    }
                    String tmp = subSid.substring(0,subSid.length() - 1);
                    orderShipReqDTO.setSubTid(tmp);
                    orderShipReqDTO.setIsSplit(1L);
                }
                this.unpfOrdDeliverBatchSV.deliverGoods(orderShipReqDTO);
            } else {
                LogUtil.info(MODULE,"=============其它平台开始处理");
            }
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException("unpf.200001");
        }
    }
}
