package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Pay102Req;
import com.zengshi.ecp.app.resp.Pay102Resp;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsResponse;
import com.zengshi.ecp.order.dubbo.dto.SOrderDetailsMain;
import com.zengshi.ecp.order.dubbo.dto.pay.PayWayRequest;
import com.zengshi.ecp.order.dubbo.dto.pay.PayWayResponse;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdDetailsRSV;
import com.zengshi.ecp.order.dubbo.interfaces.pay.IPayWayRSV;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 订单提交成功页面--我的订单付款<br>
 * Date:2016年3月5日上午10:32:46  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author cbl
 * @version  
 * @since JDK 1.6 
 */  
@Service("pay102")
@Action(bizcode = "pay102", userCheck = true)
@Scope("prototype")
public class Pay102Action extends AppBaseAction<Pay102Req, Pay102Resp> {
    
    @Resource
    private IPayWayRSV payWayRSV;
    
    @Resource
    private IOrdDetailsRSV ordDetailsRSV;
    
    private static final String MODULE = Pay102Action.class.getName();

    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        
        ROrderDetailsRequest rdto = new ROrderDetailsRequest();
        /* ROrderDetailsResponse resp = new ROrderDetailsResponse(); */
        List<PayWayResponse> wayList = new ArrayList<PayWayResponse>();
        String showOrderIds = "";
        long mergeTotalRealMoney = 0;
        long shopId = 0l;
        try{
/*            rdto.setOrderId(this.request.getOrderId());
            resp = ordDetailsRSV.queryOrderDetails(rdto); 
            PayWayRequest  waydto = new PayWayRequest();
            waydto.setShopId(this.request.getShopId());  
            wayList = payWayRSV.getPayWay(waydto);*/
        	if(!StringUtil.isBlank(this.request.getOrderId())){
                String[] orderIds = this.request.getOrderId().split(":");
                for(int index = 0; index < orderIds.length; index ++){
        			rdto.setOrderId(orderIds[index]);
        			ROrderDetailsResponse orderDetailsResponse = ordDetailsRSV.queryOrderDetails(rdto);
        			SOrderDetailsMain ordMain = orderDetailsResponse.getsOrderDetailsMain();
        			if(index == 0){
                    	shopId = ordMain.getShopId();
                    	showOrderIds = ordMain.getId();	
                    }else{
                    	showOrderIds = showOrderIds + "," + ordMain.getId();	
                    }
        			mergeTotalRealMoney += ordMain.getRealMoney();
            	}
            }
            // 获取合并支付的开关 1-表示开启  0-表示关闭
            BaseSysCfgRespDTO payMergeSysCfg = SysCfgUtil.fetchSysCfg(OrdConstants.OrdPayRel.SWITCH_PAY_MERGE);
            if(OrdConstants.OrdPayRel.PAY_MERGE_ENABLE.equals(payMergeSysCfg.getParaValue())){
            	shopId = OrdConstants.OrdPayRel.PAY_WAY_MERGE_CFG_ID;
            }
            PayWayRequest waydto = new PayWayRequest();
            waydto.setShopId(shopId);
            wayList = payWayRSV.getPayWay(waydto);
        }catch(Exception e){
            LogUtil.error(MODULE, e.getMessage(),e);
        }
        
        this.response.setWayList(wayList);
        this.response.setOrderId(showOrderIds);
        this.response.setMergeTotalRealMoney(mergeTotalRealMoney);
/*        java.util.Date now = new Date();
        long l=now.getTime()-resp.getsOrderDetailsMain().getOrderTime().getTime();
        long day=l/(24*60*60*1000);
        long hour=24-(l/(60*60*1000)-day*24);
        this.response.setHour(hour);*/
    }

}
