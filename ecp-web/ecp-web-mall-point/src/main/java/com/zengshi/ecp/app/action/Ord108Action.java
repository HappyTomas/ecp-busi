package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Ord108Req;
import com.zengshi.ecp.app.resp.Ord10801Resp;
import com.zengshi.ecp.app.resp.Ord10802Resp;
import com.zengshi.ecp.app.resp.Ord10803Resp;
import com.zengshi.ecp.app.resp.Ord108Resp;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsResponse;
import com.zengshi.ecp.order.dubbo.dto.SOrderDetailsDelivery;
import com.zengshi.ecp.order.dubbo.dto.SOrderDetailsSub;
import com.zengshi.ecp.order.dubbo.dto.SOrderDetailsTrack;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdDetailsRSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
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
@Service("ord108")
@Action(bizcode = "ord108", userCheck = true)
@Scope("prototype")
public class Ord108Action extends AppBaseAction<Ord108Req, Ord108Resp> {
    @Resource
    private IOrdDetailsRSV ordDetailsRSV;
    
    private static final String MODULE = Ord108Action.class.getName();
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
        
        this.response.setOrderId(resp.getsOrderDetailsMain().getId());
        if(resp.getsOrderDetailsComm() != null){
            this.response.setInvoiceTitle(resp.getsOrderDetailsComm().getInvoiceTitle());
            this.response.setInvoiceContent(resp.getsOrderDetailsComm().getInvoiceContent());
        }
        List<Ord10801Resp> ord10801Resps = new ArrayList<Ord10801Resp>();
        for(SOrderDetailsSub sOrderDetailsSub:resp.getsOrderDetailsSubs()){
            Ord10801Resp ord10801Resp = new Ord10801Resp();
            ObjectCopyUtil.copyObjValue(sOrderDetailsSub, ord10801Resp, "", false);
            ord10801Resps.add(ord10801Resp);
        }
        this.response.setOrd10801Resps(ord10801Resps);
        List<Ord10802Resp> ord10802Resps = new ArrayList<Ord10802Resp>();
        for(SOrderDetailsTrack sOrderDetailsTrack:resp.getsOrderDetailsTracks()){
            Ord10802Resp ord10802Resp = new Ord10802Resp();
            ObjectCopyUtil.copyObjValue(sOrderDetailsTrack, ord10802Resp, "", false);
            ord10802Resps.add(ord10802Resp);
        }
        this.response.setOrd10802Resps(ord10802Resps);
        
        List<Ord10803Resp> ord10803Resps = new ArrayList<Ord10803Resp>();
        for(SOrderDetailsDelivery sOrderDetailsDelivery:resp.getsOrderDetailsDeliverys()){
            Ord10803Resp ord10803Resp = new Ord10803Resp();
            ObjectCopyUtil.copyObjValue(sOrderDetailsDelivery, ord10803Resp, "", false);
            ord10803Resps.add(ord10803Resp);
        }
        this.response.setOrd10803Resps(ord10803Resps);
    }

}
