package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Ord011Req;
import com.zengshi.ecp.app.resp.Ord01101Resp;
import com.zengshi.ecp.app.resp.Ord01102Resp;
import com.zengshi.ecp.app.resp.Ord01103Resp;
import com.zengshi.ecp.app.resp.Ord011Resp;
import com.zengshi.ecp.order.dubbo.dto.ROrdExpressDetailsResp;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsResponse;
import com.zengshi.ecp.order.dubbo.dto.SOrderDetailsDelivery;
import com.zengshi.ecp.order.dubbo.dto.SOrderDetailsSub;
import com.zengshi.ecp.order.dubbo.dto.SOrderDetailsTrack;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdDetailsRSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 订单详情<br>
 * Date:2016年3月5日上午10:30:29 <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author cbl
 * @version
 * @since JDK 1.6
 */
@Service("ord011")
@Action(bizcode = "ord011", userCheck = true)
@Scope("prototype")
public class Ord011Action extends AppBaseAction<Ord011Req, Ord011Resp> {
    @Resource
    private IOrdDetailsRSV ordDetailsRSV;
    
    private static final String MODULE = Ord011Action.class.getName();
    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        
        ROrderDetailsRequest rdto = new ROrderDetailsRequest();
        if (StringUtil.isBlank(this.request.getOrderId())) {
            throw new BusinessException("订单号不能为空");
        }
        rdto.setOrderId(this.request.getOrderId());
        
        ROrderDetailsResponse resp = ordDetailsRSV.queryOrderDetails(rdto);
        
        ObjectCopyUtil.copyObjValue(resp.getsOrderDetailsMain(), this.response, "", false);
        
        this.response.setDiscountCoupSum(resp.getsOrderDetailsDiscount().getDiscountCoupSum());
        this.response.setDiscountGdsSum(resp.getsOrderDetailsDiscount().getDiscountGdsSum());
        this.response.setDiscountOrderSum(resp.getsOrderDetailsDiscount().getDiscountOrderSum());
        this.response.setDiscountCoupCodeSum(resp.getsOrderDetailsDiscount().getDiscountCoupCodeSum());
        this.response.setOrderId(resp.getsOrderDetailsMain().getId());
        if(resp.getsOrderDetailsComm() != null){
            this.response.setInvoiceTitle(resp.getsOrderDetailsComm().getInvoiceTitle());
            this.response.setInvoiceContent(resp.getsOrderDetailsComm().getInvoiceContent());
            this.response.setTaxpayerNo(resp.getsOrderDetailsComm().getTaxpayerNo());
        }
        List<Ord01101Resp> ord01101Resps = new ArrayList<Ord01101Resp>();
        for(SOrderDetailsSub sOrderDetailsSub:resp.getsOrderDetailsSubs()){
            Ord01101Resp ord01101Resp = new Ord01101Resp();
            ObjectCopyUtil.copyObjValue(sOrderDetailsSub, ord01101Resp, "", false);
            ord01101Resps.add(ord01101Resp);
        }
        this.response.setOrd01101Resps(ord01101Resps);
        List<Ord01102Resp> ord01102Resps = new ArrayList<Ord01102Resp>();
        for(SOrderDetailsTrack sOrderDetailsTrack:resp.getsOrderDetailsTracks()){
            Ord01102Resp ord01102Resp = new Ord01102Resp();
            ObjectCopyUtil.copyObjValue(sOrderDetailsTrack, ord01102Resp, "", false);
            ord01102Resps.add(ord01102Resp);
        }
        this.response.setOrd01102Resps(ord01102Resps);
        
        List<Ord01103Resp> ord01103Resps = new ArrayList<Ord01103Resp>();
        for(SOrderDetailsDelivery sOrderDetailsDelivery:resp.getsOrderDetailsDeliverys()){
            Ord01103Resp ord01103Resp = new Ord01103Resp();
            ObjectCopyUtil.copyObjValue(sOrderDetailsDelivery, ord01103Resp, "", false);
            ord01103Resps.add(ord01103Resp);
        }
        this.response.setOrd01103Resps(ord01103Resps);
        String b = BaseParamUtil.fetchParamValue("ORD_PAY_LIMIT","0");
        int limitHour = Integer.valueOf(b); 
        long hour = limitHour * 3600000 -(DateUtil.getSysDate().getTime() - resp.getsOrderDetailsMain().getOrderTime().getTime());
        long hh = hour/3600000;  
        long minutes = hour/60000-hh*60;  
        
        this.response.setLimitHour(hh);
        this.response.setLimitMinutes(minutes);
        
    }

}
