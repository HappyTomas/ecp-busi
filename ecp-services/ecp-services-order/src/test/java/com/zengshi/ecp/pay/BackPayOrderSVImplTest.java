package com.zengshi.ecp.pay;

import com.zengshi.ecp.order.dubbo.dto.*;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdBackApplySV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdCalculateSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdSubSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.ecp.staff.dubbo.dto.OrderBackMainReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.OrderBackSubReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IStaffUnionRSV;
import com.zengshi.paas.utils.LogUtil;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Title: ECP 退款订单积分子事务测试<br>
 * Project Name:ecp-services-order-server <br>
 * Description: <br>
 * Date:2016年8月10日下午2:36:22  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author lwy
 * @version  
 * @since JDK 1.6
 */
public class BackPayOrderSVImplTest  extends EcpServicesTest{
    
    @Resource
    private IOrdCalculateSV ordCalculateSV;
    
    @Resource
    private IStaffUnionRSV staffUnionRSV;
    
    @Autowired(required = false)
    private IOrdBackApplySV ordBackApplySV;
    
    @Resource
    private IOrdSubSV ordSubSV;
    
    private static final String MODULE = BackPayOrderSVImplTest.class.getName();
    
    @Test
    public void testBackApplySubTrainaction() {
        try {
            String json = "{\"appName\":\"ai-ecp\",\"backId\":17040,\"backPicList\":[\"57a9aa89554f55379123cc33\",\"\",\"\"],\"currentSiteId\":1,\"endRowIndex\":10,\"locale\":\"zh_CN\",\"orderId\":\"RW15120200002122\",\"pageNo\":1,\"pageSize\":10,\"staff\":{\"id\":182,\"staffClass\":\"20\",\"staffCode\":\"chengbl\",\"staffLevelCode\":\"04\"},\"startRowIndex\":0,\"threadId\":\"127.0.0.1:-7066532975424028864\"}";
            final RBackConfirmReq rBackConfirmReq = JSON.parseObject(json, RBackConfirmReq.class);
            LogUtil.info(MODULE,"BackPayStaffSVImpl============="+rBackConfirmReq.toString());
            dealMethod(rBackConfirmReq);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "支付回调客户域接口处理异常", be);
            be.printStackTrace();
          
            throw new BusinessException(be.getErrorCode());
        } catch (Exception e) {
            LogUtil.error(MODULE, "支付回调客户域接口处理异常", e);
        
            throw new BusinessException(MsgConstants.OtherSysMsgCode.CALL_STAFF_SERVER_342000);
        }
    }

    
    public void dealMethod(RBackConfirmReq rBackConfirmReq) {
        ROrderBackReq rOrderBackReq  = new ROrderBackReq();
        rOrderBackReq.setOrderId(rBackConfirmReq.getOrderId());
        rOrderBackReq.setBackId(rBackConfirmReq.getBackId());
        RBackReviewResp rBackReviewResp = ordBackApplySV.queryBackIdInfo(rOrderBackReq);
        
        OrderBackMainReqDTO<OrderBackSubReqDTO> orderBackMainReqDTO = new OrderBackMainReqDTO<OrderBackSubReqDTO>();
        orderBackMainReqDTO.setStaffId(rBackReviewResp.getrBackApplyResp().getStaffId());
        orderBackMainReqDTO.setOrderId(rBackReviewResp.getrBackApplyResp().getOrderId());
        orderBackMainReqDTO.setRefundOrBack(rBackReviewResp.getrBackApplyResp().getApplyType());
        RBackReviewReq rBackReviewReq= new RBackReviewReq();
        rBackReviewReq.setApplyType(rBackReviewResp.getrBackApplyResp().getApplyType());
        rBackReviewReq.setBackId(rBackReviewResp.getrBackApplyResp().getBackId());
        rBackReviewReq.setOrderId(rBackReviewResp.getrBackApplyResp().getOrderId());
        orderBackMainReqDTO.setScale(ordCalculateSV.calCulateScale(rBackReviewReq,"1".equals(rBackReviewResp.getrBackApplyResp().getIsEntire())));
        orderBackMainReqDTO.setSiteId(rBackReviewResp.getrBackApplyResp().getSiteId());
        orderBackMainReqDTO.setLastFlag("1".equals(rBackReviewResp.getrBackApplyResp().getIsEntire()));
        orderBackMainReqDTO.setBackId(rBackReviewResp.getrBackApplyResp().getBackId());
        List<OrderBackSubReqDTO> orderBackSubReqDTOs = new ArrayList<OrderBackSubReqDTO>();
        for(RBackGdsResp rBackGdsResp: rBackReviewResp.getrBackGdsResps()){
            OrderBackSubReqDTO orderBackSubReqDTO = new OrderBackSubReqDTO();
            orderBackSubReqDTO.setOrderId(rBackConfirmReq.getOrderId());
            orderBackSubReqDTO.setStaffId(rBackReviewResp.getrBackApplyResp().getStaffId());
            orderBackSubReqDTO.setSubOrderId(rBackGdsResp.getOrderSubId());
            
            SBaseAndSubInfo sOrderAOrderSubInfo = new SBaseAndSubInfo();
            sOrderAOrderSubInfo.setOrderSubId(rBackGdsResp.getOrderSubId());
            sOrderAOrderSubInfo.setOrderId(rBackGdsResp.getOrderId());
            //计算子订单分摊比例 
            Long subAmount = ordSubSV.findByOrderSubId(sOrderAOrderSubInfo).getOrderAmount();
            Long scale = new BigDecimal(rBackGdsResp.getNum() * 1000000).divide(new BigDecimal(subAmount),2).longValue();
            orderBackSubReqDTO.setScale(scale);
            orderBackSubReqDTOs.add(orderBackSubReqDTO);
        }
        orderBackMainReqDTO.setList(orderBackSubReqDTOs);
        
        OrderBackSubReqDTO orderBackSubReqDTO = new OrderBackSubReqDTO();
        orderBackSubReqDTO.setStaffId(rBackReviewResp.getrBackApplyResp().getStaffId());
        orderBackSubReqDTO.setOrderId(rBackReviewResp.getrBackApplyResp().getOrderId());
        orderBackSubReqDTO.setLastFlag(orderBackMainReqDTO.isLastFlag());
        orderBackSubReqDTO.setScale(orderBackMainReqDTO.getScale());
        orderBackSubReqDTO.setRefundOrBack(rBackReviewResp.getrBackApplyResp().getApplyType());
        orderBackSubReqDTO.setBackId(rBackReviewResp.getrBackApplyResp().getBackId());
        LogUtil.error(MODULE, "调用客户域入参："+JSON.toJSONString(orderBackMainReqDTO)+"===="+JSON.toJSONString(orderBackSubReqDTO));
        staffUnionRSV.saveScoreAcctForOrderBack(orderBackMainReqDTO, orderBackSubReqDTO);
        
    }
}

