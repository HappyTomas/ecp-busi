package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Pay103Req;
import com.zengshi.ecp.app.resp.Pay10301Resp;
import com.zengshi.ecp.app.resp.Pay103Resp;
import com.zengshi.ecp.order.dubbo.dto.ROrdMainResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdMainsResponse;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 订单支付列表--多店铺<br>
 * Date:2016年3月5日上午10:33:01  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author cbl
 * @version  
 * @since JDK 1.6 
 */  
@Service("pay103")
@Action(bizcode = "pay103", userCheck = true)
@Scope("prototype")
public class Pay103Action extends AppBaseAction<Pay103Req, Pay103Resp> {
    
    private static final String MODULE = Pay103Action.class.getName();

    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        
        String redisKey = this.request.getRedisKey();
        ROrdMainsResponse rOrdMainsResponse = (ROrdMainsResponse) CacheUtil.getItem(redisKey);
        if(rOrdMainsResponse == null){
            throw new BusinessException("支付超时");
        }
        List<Pay10301Resp> pay10301Resps = new ArrayList<Pay10301Resp>();
        for(ROrdMainResponse rOrdMainResp: rOrdMainsResponse.getOrdMainList()){
            Pay10301Resp pay10301Resp = new Pay10301Resp();
            pay10301Resp.setOrderId(rOrdMainResp.getOrderCode());
            pay10301Resp.setShopId(rOrdMainResp.getShopId());
            pay10301Resp.setShopName(rOrdMainResp.getShopName());
            pay10301Resp.setRealMoney(rOrdMainResp.getRealMoney());
            pay10301Resp.setOrderScore(rOrdMainResp.getOrderScore());
            pay10301Resps.add(pay10301Resp);
        }
        this.response.setRedisKey(redisKey);
        this.response.setPay10301Resps(pay10301Resps);
        // 获取合并支付的开关 1-表示开启  0-表示关闭
        BaseSysCfgRespDTO payMergeSysCfg = SysCfgUtil.fetchSysCfg(OrdConstants.OrdPayRel.SWITCH_PAY_MERGE);
        if(OrdConstants.OrdPayRel.PAY_MERGE_ENABLE.equals(payMergeSysCfg.getParaValue())){
        	this.response.setMergePay(true);
        }else{
        	this.response.setMergePay(false);
        }
    }

}
