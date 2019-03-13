/** 
 * Project Name:ecp-web-mall 
 * File Name:DemoAction.java 
 * Package Name:com.zengshi.ecp.app.action 
 * Date:2016-2-22下午6:51:19 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.app.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Staff110Req;
import com.zengshi.ecp.app.resp.Staff110Resp;
import com.zengshi.ecp.order.dubbo.dto.ROrdCountResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdEvaluationRequest;
import com.zengshi.ecp.order.dubbo.dto.RQueryOrderRequest;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdEvaluationRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdMainRSV;
import com.zengshi.ecp.staff.dubbo.dto.AcctInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctSumResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IAcctManageRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustManageRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.StringUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall Maven Webapp <br>
 * Description: 用户域：个人中心获取相关数据action<br>
 * Date:2016-3-30下午3:26:26  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */

@Service("staff110")
@Action(bizcode="staff110", userCheck=true)
@Scope("prototype")
public class Staff110Action extends AppBaseAction<Staff110Req, Staff110Resp> {

    @Resource
    private IOrdMainRSV orderMainRSV;
    
    @Resource
    private IOrdEvaluationRSV ordEvaluationRSV;
    
    @Resource
    private IAcctManageRSV acctManageRSV;
    
    @Resource
    private ICustManageRSV custManageRSV;
    
    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        
        /*1、获取入参*/
        RQueryOrderRequest orderReq = new RQueryOrderRequest();
        orderReq.setStaffId(orderReq.getStaff().getId());
        orderReq.setCurrentSiteId(orderReq.getCurrentSiteId());
        orderReq.setSiteId(orderReq.getCurrentSiteId());
        
        /*2、调用业务查询接口，获取待付款、待发货、待收货订单数量*/
        ROrdCountResponse ordCountRes = orderMainRSV.queryOrderCountByStaff(orderReq);
        
        /*3、调用业务查询接口，获取待评价订单数量*/
        ROrdEvaluationRequest ordEva = new ROrdEvaluationRequest();
        ordEva.setStaffId(ordEva.getStaff().getId());
        Long comment = ordEvaluationRSV.queryEvaluationWaitCount(ordEva);
        
        /*4、调用接口，查询资金账户总金额*/
        AcctInfoReqDTO acct = new AcctInfoReqDTO();
        acct.setStaffId(acct.getStaff().getId());
        acct.setAdaptType(StaffConstants.Acct.ADAPT_TYPE_SHOP);
        AcctSumResDTO sum = acctManageRSV.queryAcctSumRelatedShops(acct);
        
        /*5、获取用户基本信息*/
        CustInfoResDTO cust = custManageRSV.findCustInfoById(acct.getStaff().getId());
        
        /*6、返回结果*/
        if (ordCountRes != null) {
            this.response.setOrderPayCnt(ordCountRes.getRequestStatusPay()==null?0L:ordCountRes.getRequestStatusPay());//待付款订单数量
            this.response.setOrderSendCnt(ordCountRes.getRequestStatusSend()==null?0L:ordCountRes.getRequestStatusSend());//待发货数量
            this.response.setOrderRecept(ordCountRes.getRequestStatusRecept()==null?0L:ordCountRes.getRequestStatusRecept());//待收货订单数量
        }
        if (sum != null && sum.getBalance() != null) {
            this.response.setAcctTotal(sum.getBalance());
        } else {
            this.response.setAcctTotal(0L);
        }
        this.response.setOrderCommentCnt(comment == null ? 0L : comment);
        
        this.response.setStaffCode(cust.getStaffCode());
        this.response.setCustLevelName(cust.getCustLevelName());
        if (StringUtil.isBlank(cust.getCustPic())) {
            cust.setCustPic(ImageUtil.getDefaultImageId());
        }
        this.response.setCustPic(ImageUtil.getImageUrl(cust.getCustPic()));
    }

}

