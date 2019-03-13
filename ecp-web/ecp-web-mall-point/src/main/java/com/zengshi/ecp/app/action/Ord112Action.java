package com.zengshi.ecp.app.action;

import com.zengshi.ecp.app.req.Ord109Req;
import com.zengshi.ecp.app.req.Ord112Req;
import com.zengshi.ecp.app.resp.*;
import com.zengshi.ecp.order.dubbo.dto.RCustomerOrdResponse;
import com.zengshi.ecp.order.dubbo.dto.RQueryOrderRequest;
import com.zengshi.ecp.order.dubbo.dto.SOrderDetailsSub;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdMainRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.SystemException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
@Service("ord112")
@Action(bizcode = "ord112", userCheck = true)
@Scope("prototype")
public class Ord112Action extends AppBaseAction<Ord112Req, Ord112Resp> {
    
    @Resource
    private IOrdMainRSV ordMainRSV;
    
    private static final String MODULE = Ord112Action.class.getName();

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
            List<Ord11201Resp> ord11201Resps = new ArrayList<Ord11201Resp>();
            for(RCustomerOrdResponse rord : rdors.getResult()){
                for(SOrderDetailsSub sds : rord.getsOrderDetailsSubs()){
                    Ord11201Resp ord11201Resp = new Ord11201Resp();
                    ObjectCopyUtil.copyObjValue(sds, ord11201Resp, "", false);
                    ord11201Resp.setPayTime(rord.getsCustomerOrdMain().getPayTime());
                    ord11201Resp.setOrderTime(rord.getsCustomerOrdMain().getOrderTime());
                    ord11201Resps.add(ord11201Resp);
                }
            }
            this.response.setOrd11201Resps(ord11201Resps);
        }
        
    }

}
