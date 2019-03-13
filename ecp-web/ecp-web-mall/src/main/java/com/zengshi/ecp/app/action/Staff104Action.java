/** 
 * Project Name:ecp-web-mall 
 * File Name:DemoAction.java 
 * Package Name:com.zengshi.ecp.app.action 
 * Date:2016-2-22下午6:51:19 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.app.action;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.app.req.Staff104Req;
import com.zengshi.ecp.app.resp.AcctTrade;
import com.zengshi.ecp.app.resp.Staff104Resp;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctTradeReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctTradeResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IAcctManageRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall Maven Webapp <br>
 * Description: 用户域：查询资金账户交易明细<br>
 * Date:2016-3-4下午3:43:32  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */

@Service("staff104")
@Action(bizcode="staff104", userCheck=true)
@Scope("prototype")
public class Staff104Action extends AppBaseAction<Staff104Req, Staff104Resp> {

    @Resource
    private IAcctManageRSV acctManageRSV;
    
    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        
        /*1、获取入参*/
        Long shopId = this.request.getShopId();//店铺id
        String acctType = this.request.getAcctType();//资金账户类型
        String adaptType = this.request.getAdaptType();//资金适用类型
        
        AcctTradeReqDTO tradeReq = new AcctTradeReqDTO();
        tradeReq.setStaffId(tradeReq.getStaff().getId());
        tradeReq.setShopId(shopId);
        tradeReq.setAdaptType(adaptType);
        tradeReq.setAcctType(acctType);
        tradeReq.setPageNo(this.request.getPageNo());
        tradeReq.setPageSize(this.request.getPageSize());
        
        /*2、调用业务查询接口，查询我的资金账户*/
        PageResponseDTO<AcctTradeResDTO> page = acctManageRSV.listAcctTrade(tradeReq);
        
        /*3、组装结果集、返回结果*/
        List<AcctTrade> resList = new ArrayList<AcctTrade>();
        if (page != null && !CollectionUtils.isEmpty(page.getResult())) {
            for (AcctTradeResDTO res : page.getResult()) {
                AcctTrade acctTrade = new AcctTrade();
                acctTrade.setOrderId(res.getOrderId());
                acctTrade.setTradeMoney(res.getTradeMoney());
                String tradeTypeName = "";
                if(StaffConstants.Trade.TRADE_TYPE_PAY.equals(res.getTradeType())) {
                    tradeTypeName = "购买商品付款";
                } else if(StaffConstants.Trade.TRADE_TYPE_RECHARGE.equals(res.getTradeType())) {
                    tradeTypeName = "充值";
                } else if(StaffConstants.Trade.TRADE_TYPE_CANCEL.equals(res.getTradeType())) {
                    tradeTypeName = "取消订单";
                } else if (StaffConstants.Trade.TRADE_TYPE_REFUND.equals(res.getTradeType())) {
                    tradeTypeName = "订单退款";
                } else if (StaffConstants.Trade.TRADE_TYPE_BACK.equals(res.getTradeType())) {
                    tradeTypeName = "订单退货";
                }
                acctTrade.setTradeTypeName(tradeTypeName);
                acctTrade.setDebitCredit(res.getDebitCredit());
                acctTrade.setCreateTime(res.getCreateTime());
                resList.add(acctTrade);
            }
            this.response.setResList(resList);
        } else {
            this.response.setResList(new ArrayList<AcctTrade>());
        }
    }

}

