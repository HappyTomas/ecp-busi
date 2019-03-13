package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Ord012Req;
import com.zengshi.ecp.app.resp.Ord01201Resp;
import com.zengshi.ecp.app.resp.Ord01202Resp;
import com.zengshi.ecp.app.resp.Ord012Resp;
import com.zengshi.ecp.order.dubbo.dto.RCustomerOrdResponse;
import com.zengshi.ecp.order.dubbo.dto.RQueryOrderRequest;
import com.zengshi.ecp.order.dubbo.dto.SOrderDetailsSub;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdDetailsRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdMainRSV;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 我的订单<br>
 * Date:2016年3月5日上午10:30:16 <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author cbl
 * @version
 * @since JDK 1.6
 */
@Service("ord012")
@Action(bizcode = "ord012", userCheck = true)
@Scope("prototype")
public class Ord012Action extends AppBaseAction<Ord012Req, Ord012Resp> {
    
    @Resource
    private IOrdMainRSV ordMainRSV;
    
    private static final String MODULE = Ord012Action.class.getName();

    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        RQueryOrderRequest rdor = new RQueryOrderRequest();
//        String status = OrdConstants.CustomerRequestStatus.REQUEST_STATUS_ALL;
        rdor.setStaffId(rdor.getStaff().getId());
        rdor.setSiteId(this.request.getSiteId());
        rdor.setSysType("00");
        rdor.setStatus(this.request.getStatus());
        rdor.setPageNo(this.request.getPageNo());
        rdor.setPageSize(this.request.getPageSize());
        if(this.request.getShopId() != null){
            rdor.setShopId(this.request.getShopId());
        }
//        ObjectCopyUtil.copyObjValue(this.request, rdor, "", false);
        PageResponseDTO<RCustomerOrdResponse> rdors = this.ordMainRSV.queryOrderByStaffId(rdor);
        if(rdors == null || rdors.getResult() == null){
            this.response.setCount(0);
            this.response.setPageNo(this.request.getPageNo());
            this.response.setPageSize(this.request.getPageSize());
        } else {
            this.response.setCount(rdors.getCount());
            this.response.setPageNo(rdors.getPageNo());
            this.response.setPageSize(rdors.getPageSize());
            List<Ord01201Resp> ord01201Resps = new ArrayList<Ord01201Resp>();
            for(RCustomerOrdResponse rord : rdors.getResult()){
                Ord01201Resp ord01201Resp = new Ord01201Resp();
                List<Ord01202Resp> ord01202Resps = new ArrayList<Ord01202Resp>();
                for(SOrderDetailsSub sds : rord.getsOrderDetailsSubs()){
                    Ord01202Resp ord01202Resp = new Ord01202Resp();
                    ObjectCopyUtil.copyObjValue(sds, ord01202Resp, "", false);
                    ord01202Resps.add(ord01202Resp);
                }
                ObjectCopyUtil.copyObjValue(rord.getsCustomerOrdMain(), ord01201Resp, "", false);
                ord01201Resp.setOrd01102Resps(ord01202Resps);
                ord01201Resps.add(ord01201Resp);
            }
            this.response.setOrd01201Resps(ord01201Resps);
        }
        
    }

}
