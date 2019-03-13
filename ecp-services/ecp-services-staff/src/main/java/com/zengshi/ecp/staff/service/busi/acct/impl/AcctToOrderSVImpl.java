package com.zengshi.ecp.staff.service.busi.acct.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.general.order.dto.AcctInfoCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.server.front.dto.BaseParamDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.staff.dao.model.AcctInfo;
import com.zengshi.ecp.staff.dao.model.AcctInfoKey;
import com.zengshi.ecp.staff.dubbo.dto.AcctTradeReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctTradeResDTO;
import com.zengshi.ecp.staff.dubbo.dto.OrderAcctMainResDTO;
import com.zengshi.ecp.staff.dubbo.dto.OrderAcctSubResDTO;
import com.zengshi.ecp.staff.dubbo.dto.OrderBackMainReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.OrderBackSubReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.TransactionContentReqDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.acct.interfaces.IAcctInfoSV;
import com.zengshi.ecp.staff.service.busi.acct.interfaces.IAcctToOrderSV;
import com.zengshi.ecp.staff.service.busi.acct.interfaces.IAcctTradeSV;
import com.zengshi.ecp.staff.service.util.StaffTools;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 提供于订单对资金帐户使用情况的公共接口的实现<br>
 * Date:2015年10月20日下午2:42:39  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
public class AcctToOrderSVImpl implements IAcctToOrderSV {
    
    private static final String MODULE = AcctToOrderSVImpl.class.getName();

    @Resource(name="staffTools")
    private StaffTools staffTools;
    
    @Resource
    private IAcctInfoSV acctInfoSV; //资金帐户信息
    
    @Resource
    private IAcctTradeSV acctTradeSV;  //资金帐户交易
    
    @Override
    public boolean checkOrdCart(ROrdCartsCommRequest ordCarts) throws BusinessException {
        //入参对象为空
        if (ordCarts == null) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"入参对象"});
        }
        //参数校验
        staffTools.paramCheck(new Object[] { ordCarts.getStaffId(), ordCarts.getOrdCartsCommList() },
                new String[] {"用户id", "购物车实例队列"});
        
        List<ROrdCartCommRequest> listCart = ordCarts.getOrdCartsCommList();
        if(listCart!=null&&CollectionUtils.isNotEmpty(listCart)){
            for (ROrdCartCommRequest rOrdCartCommRequest : listCart) {
                List<AcctInfoCommRequest> listAcctInfo = rOrdCartCommRequest.getAcctInfoCommRequest();
                if(listAcctInfo!=null&&CollectionUtils.isNotEmpty(listAcctInfo)){
                    for (AcctInfoCommRequest acctInfoCommRequest : listAcctInfo) {
                        AcctInfoKey acctKey = new AcctInfoKey();
                        ObjectCopyUtil.copyObjValue(acctInfoCommRequest, acctKey, null, false);
                        AcctInfo acctInfo = acctInfoSV.findAcctInfoByKey(acctKey);
                        if(acctInfo==null || acctInfo.getBalance()<acctInfoCommRequest.getDeductOrderMoney()){
                            LogUtil.info(MODULE, "订单编号："+rOrdCartCommRequest.getOrderId()+"，订单需求资金帐户余额不足。");
                            return false;
                        }
                    }
                }
            }
        }
        
        return true;
    }

    @Override
    public int saveAcctUseRollBack(ROrdCartsCommRequest ordCarts) throws BusinessException {
        LogUtil.info(MODULE, "=========开始进行资金帐户回滚==========");
        //入参对象为空
        if (ordCarts == null) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"入参对象"});
        }
        //参数校验
        staffTools.paramCheck(new Object[] { ordCarts.getStaffId(), ordCarts.getOrdCartsCommList() },
                new String[] {"用户id", "购物车实例队列"});
        
        List<ROrdCartCommRequest> listCart = ordCarts.getOrdCartsCommList();
        if(listCart!=null&&CollectionUtils.isNotEmpty(listCart)){
            for (ROrdCartCommRequest rOrdCartCommRequest : listCart) {
            	String orderId = rOrdCartCommRequest.getOrderId();
            	Long staffId = rOrdCartCommRequest.getStaffId();
            	TransactionContentReqDTO reqRefund = new TransactionContentReqDTO();
            	reqRefund.setOrderId(orderId);
            	reqRefund.setStaffId(staffId);
            	reqRefund.setTradeType(StaffConstants.Trade.TRADE_TYPE_CANCEL);
            	acctTradeSV.executeOrderRefund(reqRefund);
            }
        }
        
        LogUtil.info(MODULE, "=========结束进行资金帐户回滚==========");
        
        return 0;
    }

    @Override
    public int saveAcctUseForOrderRefund(OrderBackSubReqDTO orderReq) throws BusinessException {
        LogUtil.info(MODULE, "=========订单退款：资金帐户回退  START==========");
        //入参对象为空
        if (orderReq == null) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"入参对象"});
        }
        TransactionContentReqDTO reqRefund = new TransactionContentReqDTO();
        reqRefund.setOrderId(orderReq.getOrderId());
        reqRefund.setStaffId(orderReq.getStaffId());
        reqRefund.setTradeType(StaffConstants.Trade.TRADE_TYPE_REFUND);
        acctTradeSV.executeOrderRefund(reqRefund);
        
        LogUtil.info(MODULE, "=========订单退款：资金帐户回退  END==========");
        
        return 0;
    }

    @Override
    public int saveAcctUseForOrderBack(OrderBackSubReqDTO orderReq) throws BusinessException {
        LogUtil.info(MODULE, "=========订单退货：资金帐户回退  START==========");
        //入参对象为空
        if (orderReq == null) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"入参对象"});
        }
        acctTradeSV.executeOrderBack(orderReq);
        
        LogUtil.info(MODULE, "=========订单退货：资金帐户回退  END==========");
        
        return 1;
    }

    @Override
    public OrderAcctMainResDTO<OrderAcctSubResDTO> selAcctByBackOrder(
            OrderBackMainReqDTO<OrderBackSubReqDTO> req) throws BusinessException {
        //返回结果集
        OrderAcctMainResDTO<OrderAcctSubResDTO> result = new OrderAcctMainResDTO<OrderAcctSubResDTO>();
        //子账户列表
        List<OrderAcctSubResDTO> acctSub = new ArrayList<OrderAcctSubResDTO>();
        if(req == null || StringUtil.isBlank(req.getOrderId())){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"executeOrderRefund"});
        }
        
        /*1、查询店铺资金使用情况*/
        List<BaseParamDTO> baseList = BaseParamUtil.fetchParamList("STAFF_ACCT_TYPE");
        long tradeMoneyTotal = 0L;
        long backMoneyTotal = 0L;
        if (CollectionUtils.isNotEmpty(baseList)) {
            /*1-1、分别查询不同的店铺资金类型，进行回退操作*/
            for (BaseParamDTO dto : baseList) {
                AcctTradeReqDTO shopAcct = new AcctTradeReqDTO();
                shopAcct.setOrderId(req.getOrderId());
                shopAcct.setStaffId(req.getStaffId());
                shopAcct.setAdaptType(StaffConstants.Acct.ADAPT_TYPE_SHOP);//适用类型
                shopAcct.setAcctType(dto.getSpCode());//资金类型
                shopAcct.setDebitCredit(StaffConstants.Trade.ACCT_DC_SPEND);
                shopAcct.setPageNo(0);//不分页查询
                PageResponseDTO<AcctTradeResDTO> listOrderShop = acctTradeSV.listAcctTrade(shopAcct);
                Long listOrderShopCount = listOrderShop.getCount();
                /*1-2、如果有使用，则进行回退计算*/
                if(listOrderShopCount != 0){
                    OrderAcctSubResDTO sub = new OrderAcctSubResDTO();
                    sub.setAcctTypeName(dto.getSpValue());
                    long tradeMoneyShop = 0L;//店铺资金
                    //2.查到订单使用资金情况，进行资金回退
                    for (AcctTradeResDTO order : listOrderShop.getResult()) {
                        tradeMoneyShop += order.getTradeMoney();
                        sub.setShopId(order.getShopId());
                        sub.setAcctType(order.getAcctType());
                    }
                    sub.setUseMoney(tradeMoneyShop);//金额累加
                    tradeMoneyTotal += tradeMoneyShop;
                    //如果是最后一笔退货的子订单，则需要用总的金额，减去已回退的金额，而不能直接用比例
                    if (req.isLastFlag()) {
                        //查询已退的平台资金总额
                        AcctTradeReqDTO sumTradePlatform = new AcctTradeReqDTO();
                        sumTradePlatform.setOrderId(req.getOrderId());
                        sumTradePlatform.setStaffId(req.getStaffId());
                        sumTradePlatform.setAcctType(dto.getSpCode());//账户类型
                        sumTradePlatform.setTradeType(StaffConstants.Trade.TRADE_TYPE_BACK);//交易类型：订单退货
                        sumTradePlatform.setAdaptType(StaffConstants.Acct.ADAPT_TYPE_SHOP);//店铺资金
                        sumTradePlatform.setPageNo(0);//不分页查询
                        long sumAcctPlatform = acctTradeSV.sumAcctTrade(sumTradePlatform);//已经回退了多少
                        tradeMoneyShop = tradeMoneyShop - sumAcctPlatform;
                    } else {
                        tradeMoneyShop = tradeMoneyShop * req.getScale() / 1000000;//精度丢失，代替向下取整
                    }
                    sub.setBackMoney(tradeMoneyShop);
                    backMoneyTotal += tradeMoneyShop;
                    //如是为0，则不加入列表，<这里主要防止那种极小金额的订单>
                    if (tradeMoneyShop != 0L) {
                    	acctSub.add(sub);
                    }
                }
            }
            result.setTotalMoney(tradeMoneyTotal);
            result.setBackTotalMoney(backMoneyTotal);
            result.setList(acctSub);
        }
        
        return result;
    }

}

