package com.zengshi.ecp.app.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Pay101Req;
import com.zengshi.ecp.app.resp.Pay101Resp;
import com.zengshi.ecp.order.dubbo.dto.ROrdMainResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdMainsResponse;
import com.zengshi.ecp.order.dubbo.dto.pay.PayWayRequest;
import com.zengshi.ecp.order.dubbo.dto.pay.PayWayResponse;
import com.zengshi.ecp.order.dubbo.interfaces.pay.IPayWayRSV;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 订单提交成功页面--正常支付<br>
 * Date:2016年3月5日上午10:32:21  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author cbl
 * @version  
 * @since JDK 1.6 
 */  
@Service("pay101")
@Action(bizcode = "pay101", userCheck = true)
@Scope("prototype")
public class Pay101Action extends AppBaseAction<Pay101Req, Pay101Resp> {
    
    @Resource
    private IPayWayRSV payWayRSV;
    
    private static final String MODULE = Pay101Action.class.getName();

    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        
        String redisKey = this.request.getRedisKey();
        ROrdMainsResponse rOrdMainsResponse = (ROrdMainsResponse) CacheUtil.getItem(redisKey);
        if(rOrdMainsResponse == null || rOrdMainsResponse.getOrdMainList() == null || 
        		rOrdMainsResponse.getOrdMainList().size() == 0){
            throw new BusinessException("支付超时");
        }
        String showOrderId = "";
        long mergeTotalRealMoney = 0l;
        long shopId = 0l;
        for(int index = 0; index < rOrdMainsResponse.getOrdMainList().size(); index ++){
        	ROrdMainResponse orderMain = rOrdMainsResponse.getOrdMainList().get(index);
        	mergeTotalRealMoney += orderMain.getRealMoney();
        	if(index == 0){
        		showOrderId = orderMain.getId();
        		shopId = orderMain.getShopId();
        	}else{
        		showOrderId = showOrderId+","+orderMain.getId();	
        	}
        }
        PayWayRequest waydto = new PayWayRequest();
        // 获取合并支付的开关 1-表示开启  0-表示关闭
        BaseSysCfgRespDTO payMergeSysCfg = SysCfgUtil.fetchSysCfg(OrdConstants.OrdPayRel.SWITCH_PAY_MERGE);
        if(OrdConstants.OrdPayRel.PAY_MERGE_ENABLE.equals(payMergeSysCfg.getParaValue())){
        	shopId = OrdConstants.OrdPayRel.PAY_WAY_MERGE_CFG_ID;
        }
        waydto.setShopId(shopId);
        List<PayWayResponse> wayList = null;
        try{
            wayList = payWayRSV.getPayWay(waydto);
        }catch(Exception e){
            LogUtil.error(MODULE, "获取支付通道异常",e);
        }
        if(wayList == null){
            throw new BusinessException("获取支付通道异常");
        }
//        this.response.setOrderId(orderMain.getId());
//        this.response.setRealMoney(orderMain.getRealMoney());
//        this.response.setWayList(wayList);
        this.response.setOrderId(showOrderId);
        this.response.setRealMoney(mergeTotalRealMoney);
        this.response.setWayList(wayList);
    }

}
