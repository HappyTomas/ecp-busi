package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Ord109Req;
import com.zengshi.ecp.app.resp.Ord10901Resp;
import com.zengshi.ecp.app.resp.Ord10902Resp;
import com.zengshi.ecp.app.resp.Ord109Resp;
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
@Service("ord109")
@Action(bizcode = "ord109", userCheck = true)
@Scope("prototype")
public class Ord109Action extends AppBaseAction<Ord109Req, Ord109Resp> {
    
    @Resource
    private IOrdMainRSV ordMainRSV;
    
    private static final String MODULE = Ord109Action.class.getName();

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
            List<Ord10901Resp> ord10901Resps = new ArrayList<Ord10901Resp>();
            for(RCustomerOrdResponse rord : rdors.getResult()){
                Ord10901Resp ord10901Resp = new Ord10901Resp();
                List<Ord10902Resp> ord10902Resps = new ArrayList<Ord10902Resp>();
                for(SOrderDetailsSub sds : rord.getsOrderDetailsSubs()){
                    Ord10902Resp ord10902Resp = new Ord10902Resp();
                    ObjectCopyUtil.copyObjValue(sds, ord10902Resp, "", false);
                    ord10902Resps.add(ord10902Resp);
                }
                ObjectCopyUtil.copyObjValue(rord.getsCustomerOrdMain(), ord10901Resp, "", false);
                ord10901Resp.setOrd01102Resps(ord10902Resps);
                ord10901Resps.add(ord10901Resp);
            }
            this.response.setOrd10901Resps(ord10901Resps);
        }
        
    }

}
