package com.zengshi.ecp.pay;

import com.zengshi.ecp.general.order.dto.ROrdCartCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockReturnInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockReturnSubReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoExternalRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsStockRSV;
import com.zengshi.ecp.order.dao.model.OrdMain;
import com.zengshi.ecp.order.dao.model.OrdSub;
import com.zengshi.ecp.order.dubbo.dto.*;
import com.zengshi.ecp.order.dubbo.util.BackConstants;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdBackApplySV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdMainSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdSubSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Title: ECP 退款商品子事务测试<br>
 * Project Name:ecp-services-order-server <br>
 * Description: <br>
 * Date:2016年8月10日下午2:35:36  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author lwy
 * @version  
 * @since JDK 1.6
 */
public class BackPayGoodsSVImplTest  extends EcpServicesTest{
    
    @Resource
    private IGdsStockRSV gdsStockRSV;
    
    @Autowired(required = false)
    private IOrdBackApplySV ordBackApplySV;
    
    @Resource
    private IOrdMainSV ordMainSV;
    
    @Resource
    private IOrdSubSV ordSubSV;
    
    @Resource
    private IGdsInfoExternalRSV gdsInfoExternalRSV;
    
    private static final String MODULE = BackPayGoodsSVImplTest.class.getName();

   @Test
    public void testBackPayGoodsTransaction() {
        try {
            String json = "{\"appName\":\"ai-ecp\",\"backId\":17040,\"backPicList\":[\"57a9aa89554f55379123cc33\",\"\",\"\"],\"currentSiteId\":1,\"endRowIndex\":10,\"locale\":\"zh_CN\",\"orderId\":\"RW15120200002122\",\"pageNo\":1,\"pageSize\":10,\"staff\":{\"id\":182,\"staffClass\":\"20\",\"staffCode\":\"chengbl\",\"staffLevelCode\":\"04\"},\"startRowIndex\":0,\"threadId\":\"127.0.0.1:-7066532975424028864\"}";
            
            final RBackConfirmReq rBackConfirmReq = JSON.parseObject(json, RBackConfirmReq.class);
            LogUtil.info(MODULE,"BackPayGoodsSVImpl============="+rBackConfirmReq.toString());
            dealMethod(rBackConfirmReq);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "支付回调商品域接口处理异常", be);
            be.printStackTrace();
           
            throw new BusinessException(be.getErrorCode());
        } catch (Exception e) {
            LogUtil.error(MODULE, "支付回调商品域接口处理异常", e);
  
            throw new BusinessException(MsgConstants.OtherSysMsgCode.CALL_GOODS_SERVER_340000);
        }
    }

    public void dealMethod(RBackConfirmReq rBackConfirmReq) {
        ROrderBackReq rOrderBackReq  = new ROrderBackReq();
        rOrderBackReq.setOrderId(rBackConfirmReq.getOrderId());
        rOrderBackReq.setBackId(rBackConfirmReq.getBackId());
        RBackReviewResp rBackReviewResp = this.ordBackApplySV.queryBackIdInfo(rOrderBackReq);
        if(BackConstants.ApplyType.BACK_GDS.equals(rBackReviewResp.getrBackApplyResp().getApplyType())){
            StockReturnInfoReqDTO stockReturnInfoReqDTO  = new StockReturnInfoReqDTO();
            stockReturnInfoReqDTO.setStaffId(rBackReviewResp.getrBackApplyResp().getStaffId());
            List<StockReturnSubReqDTO> stockReturnSubReqDTOs = new ArrayList<StockReturnSubReqDTO>();
            for(RBackGdsResp rBackGdsResp: rBackReviewResp.getrBackGdsResps()){
                StockReturnSubReqDTO stockReturnSubReqDTO = new StockReturnSubReqDTO();
                stockReturnSubReqDTO.setOrderId(rBackConfirmReq.getOrderId());
                stockReturnSubReqDTO.setSubOrderId(rBackGdsResp.getOrderSubId());
                stockReturnSubReqDTO.setSkuId(rBackGdsResp.getSkuId());
                stockReturnSubReqDTO.setReturnNum(rBackGdsResp.getNum());
                stockReturnSubReqDTO.setBackId(rBackGdsResp.getBackId());
                stockReturnSubReqDTOs.add(stockReturnSubReqDTO);
            }
            stockReturnInfoReqDTO.setReturnSubReqDTOs(stockReturnSubReqDTOs);
            LogUtil.error(MODULE, "调用商品域入参："+JSON.toJSONString(stockReturnInfoReqDTO));
            this.gdsStockRSV.returnStockInfo(stockReturnInfoReqDTO);
        } else {
            ROrdCartsCommRequest rOrdCartsCommRequest = new ROrdCartsCommRequest();
            List<ROrdCartCommRequest> rOrdCartCommRequests = new ArrayList<ROrdCartCommRequest>();
            
            OrdMain ordMain = this.ordMainSV.queryOrderByOrderId(rBackConfirmReq.getOrderId());
            if(ordMain == null){
                LogUtil.info(MODULE, "未找到[" + rBackConfirmReq.getOrderId() + "]该订单信息");
                throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312001);
            }
            ROrdCartCommRequest  rOrdCartCommRequest = new ROrdCartCommRequest();
            //补齐订单信息
            ObjectCopyUtil.copyObjValue(ordMain, rOrdCartCommRequest, null,false);
            rOrdCartCommRequest.setOrderId(ordMain.getId());

            //补齐订单促销
            SBaseAndSubInfo sBaseAndSubInfo = new SBaseAndSubInfo();
            sBaseAndSubInfo.setOrderId(rBackConfirmReq.getOrderId());
            List<OrdSub> ordSubs = this.ordSubSV.queryOrderSubByOrderId(rBackConfirmReq.getOrderId());
            
            if (CollectionUtils.isEmpty(ordSubs)) {
                LogUtil.info(MODULE, "未找到[" + rBackConfirmReq.getOrderId() + "]该订单的子订单信息");
                throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312002);
            }
            //补齐明细信息
            List<ROrdCartItemCommRequest> rOrdCartItemCommRequests = new ArrayList<ROrdCartItemCommRequest>();
            for(OrdSub ordSub: ordSubs){
                ROrdCartItemCommRequest rOrdCartItemCommRequest = new ROrdCartItemCommRequest();
                ObjectCopyUtil.copyObjValue(ordSub, rOrdCartItemCommRequest, null, false);
                rOrdCartItemCommRequest.setOrderSubId(ordSub.getId());
                
                rOrdCartItemCommRequests.add(rOrdCartItemCommRequest);
            }
            rOrdCartCommRequest.setOrdCartItemCommList(rOrdCartItemCommRequests);
            rOrdCartCommRequests.add(rOrdCartCommRequest);
            rOrdCartsCommRequest.setOrdCartsCommList(rOrdCartCommRequests);
            rOrdCartsCommRequest.setStaffId(ordMain.getStaffId());
            
            //库存补尝
            this.gdsInfoExternalRSV.batchCancleStockPreOccupy(rOrdCartsCommRequest);
        }
        
        
        
        
    }
}

