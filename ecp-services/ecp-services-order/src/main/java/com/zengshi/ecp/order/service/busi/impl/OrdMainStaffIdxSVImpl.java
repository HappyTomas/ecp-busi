package com.zengshi.ecp.order.service.busi.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import org.springframework.beans.BeanUtils;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.order.dao.mapper.busi.OrdMainStaffIdxMapper;
import com.zengshi.ecp.order.dao.model.OrdMain;
import com.zengshi.ecp.order.dao.model.OrdMainShopIdx;
import com.zengshi.ecp.order.dao.model.OrdMainShopIdxCriteria;
import com.zengshi.ecp.order.dao.model.OrdMainStaffIdx;
import com.zengshi.ecp.order.dao.model.OrdMainStaffIdxCriteria;
import com.zengshi.ecp.order.dao.model.OrdMainStaffIdxCriteria.Criteria;
import com.zengshi.ecp.order.dubbo.dto.ROrdCountResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdPayRelReq;
import com.zengshi.ecp.order.dubbo.dto.RQueryOrderRequest;
import com.zengshi.ecp.order.dubbo.dto.SBaseAndStatusInfo;
import com.zengshi.ecp.order.dubbo.dto.SOrderIdx;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdMainStaffIdxSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.StringUtil;
import com.db.sequence.Sequence;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-order <br>
 * Description: <br>
 * Date:2015年8月11日下午5:44:06  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author cbl
 * @version  
 * @since JDK 1.6 
 */  
public class OrdMainStaffIdxSVImpl extends GeneralSQLSVImpl implements IOrdMainStaffIdxSV {

    @Resource
    private OrdMainStaffIdxMapper ordMainStaffIdxMapper;

    @Resource(name = "seq_ord_main_staff_idx")
    private Sequence seqOrdMainStaffIndex;

    @Override
    public void saveOrdMainStaffIdx(final OrdMainStaffIdx ordMainStaffIdx) {
        ordMainStaffIdx.setId(this.seqOrdMainStaffIndex.nextValue());
        this.ordMainStaffIdxMapper.insert(ordMainStaffIdx);
    }


    @Override
    public void updateOrderStatus(SBaseAndStatusInfo sBaseAndStatusInfo) {
        
        final OrdMainStaffIdxCriteria omsic = new OrdMainStaffIdxCriteria();
        omsic.createCriteria().andOrderIdEqualTo(sBaseAndStatusInfo.getOrderId())
                              .andStaffIdEqualTo(sBaseAndStatusInfo.getStaffId());
        OrdMainStaffIdx omsi = new OrdMainStaffIdx();
        omsi.setStatus(sBaseAndStatusInfo.getStatus());
        this.ordMainStaffIdxMapper.updateByExampleSelective(omsi, omsic);
    }

    @Override
    public void updateOrderPayTranNo(ROrdPayRelReq rOrdPayRelReq){  
        final OrdMainStaffIdxCriteria omsic = new OrdMainStaffIdxCriteria();
        omsic.createCriteria().andOrderIdEqualTo(rOrdPayRelReq.getOrderId());
        OrdMainStaffIdx omsi = new OrdMainStaffIdx();
        omsi.setPayTranNo(rOrdPayRelReq.getJoinOrderid()); 
        this.ordMainStaffIdxMapper.updateByExampleSelective(omsi, omsic);
    }
    
    @Override
    public PageResponseDTO<SOrderIdx> queryOrderByStaffIdPage(
            RQueryOrderRequest rQueryOrderRequest) {
        final OrdMainStaffIdxCriteria omsic = new OrdMainStaffIdxCriteria();
        omsic.setLimitClauseCount(rQueryOrderRequest.getPageSize());
        omsic.setLimitClauseStart(rQueryOrderRequest.getStartRowIndex());
        omsic.setOrderByClause("order_id desc");
        
        Criteria ca = omsic.createCriteria().andStaffIdEqualTo(rQueryOrderRequest.getStaffId())
                                            .andSysTypeEqualTo(rQueryOrderRequest.getSysType());
        if(rQueryOrderRequest.getBegDate() != null){
            ca.andOrderTimeGreaterThanOrEqualTo(rQueryOrderRequest.getBegDate());
        }
        if(rQueryOrderRequest.getEndDate() != null){
            ca.andOrderTimeLessThanOrEqualTo(rQueryOrderRequest.getEndDate());
        }
        if(StringUtil.isNotBlank(rQueryOrderRequest.getContactName())){
            ca.andContactNameEqualTo(rQueryOrderRequest.getContactName());
        }
        if(rQueryOrderRequest.getSiteId() != null && rQueryOrderRequest.getSiteId() != 0l){
            ca.andSiteIdEqualTo(rQueryOrderRequest.getSiteId());
        }
        if(rQueryOrderRequest.getShopId() != null && rQueryOrderRequest.getShopId() != 0L){
            ca.andShopIdEqualTo(rQueryOrderRequest.getShopId());
        }
        if(StringUtil.isNotBlank(rQueryOrderRequest.getContactPhone())){
            ca.andContactPhoneEqualTo(rQueryOrderRequest.getContactPhone());
        }
        if(StringUtil.isNotBlank(rQueryOrderRequest.getInvoiceType())){
            if("2".equals(rQueryOrderRequest.getInvoiceType())){
                ca.andInvoiceTypeEqualTo(rQueryOrderRequest.getInvoiceType());
            } else {
                ca.andInvoiceTypeNotEqualTo("2");
            }
        }
        if(OrdConstants.CustomerRequestStatus.REQUEST_STATUS_PAY.equals(rQueryOrderRequest.getStatus())){   //01-待付款
            
            ca.andStatusEqualTo(OrdConstants.OrderStatus.ORDER_STATUS_SUBMIT);//已提交的订单
            
        } else if(OrdConstants.CustomerRequestStatus.REQUEST_STATUS_SEND.equals(rQueryOrderRequest.getStatus())){   //02-待发货
            
            List<String> status = new ArrayList<String>();
            status.add(OrdConstants.OrderStatus.ORDER_STATUS_PAID);  //支付完成
            status.add(OrdConstants.OrderStatus.ORDER_STATUS_SEND_SECTION); //部分发货
            ca.andStatusIn(status);
            
        } else if(OrdConstants.CustomerRequestStatus.REQUEST_STATUS_RECEPT.equals(rQueryOrderRequest.getStatus())){ //03-待收货
            
            ca.andStatusEqualTo(OrdConstants.OrderStatus.ORDER_STATUS_SEND_ALL);//全部发货
            
        } else if(OrdConstants.CustomerRequestStatus.REQUEST_STATUS_RECEPTED.equals(rQueryOrderRequest.getStatus())){ //04-已收货
            
            List<String> status = new ArrayList<String>();
            status.add(OrdConstants.OrderStatus.ORDER_STATUS_RECEPT);  //已收货
            status.add(OrdConstants.OrderStatus.ORDER_STATUS_CLOSE); //关闭
            ca.andStatusIn(status);
            
        } else if(OrdConstants.CustomerRequestStatus.REQUEST_STATUS_CANCLE.equals(rQueryOrderRequest.getStatus())){ //05-已取消
            
            List<String> status = new ArrayList<String>();
            status.add(OrdConstants.OrderStatus.ORDER_STATUS_CANCLE);//取消订单
            status.add(OrdConstants.OrderStatus.ORDER_STATUS_BACKGDS);  //全部退货
            status.add(OrdConstants.OrderStatus.ORDER_STATUS_REFUND); //退款
            ca.andStatusIn(status);
        } 
        
        
        return super.queryByPagination(rQueryOrderRequest, omsic, true, new PaginationCallback<OrdMainStaffIdx, SOrderIdx>() {

            @Override
            public List<OrdMainStaffIdx> queryDB(BaseCriteria bCriteria) {
                return ordMainStaffIdxMapper.selectByExample((OrdMainStaffIdxCriteria)bCriteria) ;
            }

            @Override
            public long queryTotal(BaseCriteria bCriteria) {
                return ordMainStaffIdxMapper.countByExample((OrdMainStaffIdxCriteria)bCriteria);
            }
            
            @Override
            public List<Comparator<OrdMainStaffIdx>> defineComparators() {
                List<Comparator<OrdMainStaffIdx>> ls = new ArrayList<Comparator<OrdMainStaffIdx>>();
                ls.add(new Comparator<OrdMainStaffIdx>(){

                    @Override
                    public int compare(OrdMainStaffIdx o1, OrdMainStaffIdx o2) {
                        return o2.getOrderId().compareTo(o1.getOrderId());
                    }
                    
                });
                return ls;
            }

            @Override
            public SOrderIdx warpReturnObject(OrdMainStaffIdx ordMainStaffIdx) {
                SOrderIdx sdoi = new SOrderIdx();
                BeanUtils.copyProperties(ordMainStaffIdx, sdoi);
                return sdoi;
            }
        });
    }


    @Override
    public ROrdCountResponse queryOrderCountByStaff(RQueryOrderRequest rQueryOrderRequest)
            throws BusinessException {
        ROrdCountResponse  rocr = new ROrdCountResponse();
        final OrdMainStaffIdxCriteria omsic = new OrdMainStaffIdxCriteria();
        Criteria ca = omsic.createCriteria().andStaffIdEqualTo(rQueryOrderRequest.getStaffId())
                                            .andSiteIdEqualTo(rQueryOrderRequest.getCurrentSiteId());
        Long ret = 0l;
        if(StringUtil.isBlank(rQueryOrderRequest.getStatus())) {
            //全部订单数量
            ret = this.ordMainStaffIdxMapper.countByExample(omsic);
            rocr.setRequestStatusAll(ret);
            //01-待付款
            final OrdMainStaffIdxCriteria omsic1 = new OrdMainStaffIdxCriteria();
            omsic1.createCriteria().andStaffIdEqualTo(rQueryOrderRequest.getStaffId())
                                                .andSiteIdEqualTo(rQueryOrderRequest.getCurrentSiteId())
                                                .andStatusEqualTo(OrdConstants.OrderStatus.ORDER_STATUS_SUBMIT);
            ret = this.ordMainStaffIdxMapper.countByExample(omsic1);
            rocr.setRequestStatusPay(ret);
            
            //02-待发货
            List<String> status = new ArrayList<String>();
            status.add(OrdConstants.OrderStatus.ORDER_STATUS_PAID);  //支付完成
            status.add(OrdConstants.OrderStatus.ORDER_STATUS_SEND_SECTION); //部分发货
            final OrdMainStaffIdxCriteria omsic2 = new OrdMainStaffIdxCriteria();
            omsic2.createCriteria().andStaffIdEqualTo(rQueryOrderRequest.getStaffId())
                                                .andSiteIdEqualTo(rQueryOrderRequest.getCurrentSiteId())
                                                .andStatusIn(status);
            ret = this.ordMainStaffIdxMapper.countByExample(omsic2);
            rocr.setRequestStatusSend(ret);
            //03-待收货
            final OrdMainStaffIdxCriteria omsic3 = new OrdMainStaffIdxCriteria();
            omsic3.createCriteria().andStaffIdEqualTo(rQueryOrderRequest.getStaffId())
                                                .andSiteIdEqualTo(rQueryOrderRequest.getCurrentSiteId())
                                                .andStatusEqualTo(OrdConstants.OrderStatus.ORDER_STATUS_SEND_ALL);
            ret = this.ordMainStaffIdxMapper.countByExample(omsic3);
            rocr.setRequestStatusRecept(ret);
            //04-已收货
            final OrdMainStaffIdxCriteria omsic4 = new OrdMainStaffIdxCriteria();
            omsic4.createCriteria().andStaffIdEqualTo(rQueryOrderRequest.getStaffId())
                                                .andSiteIdEqualTo(rQueryOrderRequest.getCurrentSiteId())
                                                .andStatusEqualTo(OrdConstants.OrderStatus.ORDER_STATUS_RECEPT);
            ret = this.ordMainStaffIdxMapper.countByExample(omsic4);
            rocr.setRequestStatusReceptde(ret);
            //05-已取消
            final OrdMainStaffIdxCriteria omsic5 = new OrdMainStaffIdxCriteria();
            List<String> status5 = new ArrayList<String>();
            status5.add(OrdConstants.OrderStatus.ORDER_STATUS_CANCLE);//取消订单
            status5.add(OrdConstants.OrderStatus.ORDER_STATUS_BACKGDS);  //全部退货
            status5.add(OrdConstants.OrderStatus.ORDER_STATUS_REFUND); //退款
            omsic5.createCriteria().andStaffIdEqualTo(rQueryOrderRequest.getStaffId())
                                                .andSiteIdEqualTo(rQueryOrderRequest.getCurrentSiteId())
                                                .andStatusIn(status5);
            ret = this.ordMainStaffIdxMapper.countByExample(omsic5);
            rocr.setRequestStatusCancle(ret);
            
        } else {
            if(OrdConstants.CustomerRequestStatus.REQUEST_STATUS_PAY.equals(rQueryOrderRequest.getStatus())){   //01-待付款
                
                ca.andStatusEqualTo(OrdConstants.OrderStatus.ORDER_STATUS_SUBMIT);
                ret = this.ordMainStaffIdxMapper.countByExample(omsic);
                rocr.setRequestStatusPay(ret);
                
            } else if(OrdConstants.CustomerRequestStatus.REQUEST_STATUS_SEND.equals(rQueryOrderRequest.getStatus())){   //02-待发货
                
                List<String> status = new ArrayList<String>();
                status.add(OrdConstants.OrderStatus.ORDER_STATUS_PAID);  //支付完成
                status.add(OrdConstants.OrderStatus.ORDER_STATUS_SEND_SECTION); //部分发货
                ca.andStatusIn(status);
                ret = this.ordMainStaffIdxMapper.countByExample(omsic);
                rocr.setRequestStatusSend(ret);
                
            } else if(OrdConstants.CustomerRequestStatus.REQUEST_STATUS_RECEPT.equals(rQueryOrderRequest.getStatus())){ //03-待收货
                
                ca.andStatusEqualTo(OrdConstants.OrderStatus.ORDER_STATUS_SEND_ALL);//全部发货
                ret = this.ordMainStaffIdxMapper.countByExample(omsic);
                rocr.setRequestStatusRecept(ret);
                
            } else if(OrdConstants.CustomerRequestStatus.REQUEST_STATUS_RECEPTED.equals(rQueryOrderRequest.getStatus())){ //04-已收货
                
                ca.andStatusEqualTo(OrdConstants.OrderStatus.ORDER_STATUS_RECEPT);//已收货
                ret = this.ordMainStaffIdxMapper.countByExample(omsic);
                rocr.setRequestStatusReceptde(ret);
                
            } else if(OrdConstants.CustomerRequestStatus.REQUEST_STATUS_CANCLE.equals(rQueryOrderRequest.getStatus())){ //05-已取消
                
                List<String> status5 = new ArrayList<String>();
                status5.add(OrdConstants.OrderStatus.ORDER_STATUS_CANCLE);//取消订单
                status5.add(OrdConstants.OrderStatus.ORDER_STATUS_BACKGDS);  //全部退货
                status5.add(OrdConstants.OrderStatus.ORDER_STATUS_REFUND); //退款
                
                ca.andStatusIn(status5);//取消订单
                ret = this.ordMainStaffIdxMapper.countByExample(omsic);
                rocr.setRequestStatusCancle(ret);
            } else if(OrdConstants.CustomerRequestStatus.REQUEST_STATUS_ALL.equals(rQueryOrderRequest.getStatus())){
                ret = this.ordMainStaffIdxMapper.countByExample(omsic);
                rocr.setRequestStatusAll(ret);
            } else {
                
            }
            
        }
        return rocr;
    }


    @Override
    public void updateOrderTwoStatusByOrderId(OrdMain ordMain) {
        OrdMainStaffIdxCriteria omsic = new OrdMainStaffIdxCriteria();
        omsic.createCriteria().andOrderIdEqualTo(ordMain.getId())
                              .andStaffIdEqualTo(ordMain.getStaffId());
        OrdMainStaffIdx omsi = new OrdMainStaffIdx();
        omsi.setOrderTwoStatus(ordMain.getOrderTwoStatus());
        this.ordMainStaffIdxMapper.updateByExampleSelective(omsi, omsic);
    }

    @Override
    public PageResponseDTO<SOrderIdx> queryOrderSelectiveStatus(RQueryOrderRequest rQueryOrderRequest) {
        final OrdMainStaffIdxCriteria omsic = new OrdMainStaffIdxCriteria();
        omsic.setLimitClauseCount(rQueryOrderRequest.getPageSize());
        omsic.setLimitClauseStart(rQueryOrderRequest.getStartRowIndex());
        omsic.setOrderByClause("order_id desc");

        Criteria ca = omsic.createCriteria().andStaffIdEqualTo(rQueryOrderRequest.getStaffId())
                .andSysTypeEqualTo(rQueryOrderRequest.getSysType());
        if(rQueryOrderRequest.getBegDate() != null){
            ca.andOrderTimeGreaterThanOrEqualTo(rQueryOrderRequest.getBegDate());
        }
        if(rQueryOrderRequest.getEndDate() != null){
            ca.andOrderTimeLessThanOrEqualTo(rQueryOrderRequest.getEndDate());
        }
        if(StringUtil.isNotBlank(rQueryOrderRequest.getContactName())){
            ca.andContactNameEqualTo(rQueryOrderRequest.getContactName());
        }
        if(rQueryOrderRequest.getSiteId() != null && rQueryOrderRequest.getSiteId() != 0l){
            ca.andSiteIdEqualTo(rQueryOrderRequest.getSiteId());
        }
        if(StringUtil.isNotBlank(rQueryOrderRequest.getContactPhone())){
            ca.andContactPhoneEqualTo(rQueryOrderRequest.getContactPhone());
        }
        if(StringUtil.isNotBlank(rQueryOrderRequest.getInvoiceType())){
            if("2".equals(rQueryOrderRequest.getInvoiceType())){
                ca.andInvoiceTypeEqualTo(rQueryOrderRequest.getInvoiceType());
            } else {
                ca.andInvoiceTypeNotEqualTo("2");
            }
        }
        if (CollectionUtils.isNotEmpty(rQueryOrderRequest.getStatusList())) {

            ca.andStatusIn(rQueryOrderRequest.getStatusList());
        }

        return super.queryByPagination(rQueryOrderRequest, omsic, true, new PaginationCallback<OrdMainStaffIdx, SOrderIdx>() {

            @Override
            public List<OrdMainStaffIdx> queryDB(BaseCriteria bCriteria) {
                return ordMainStaffIdxMapper.selectByExample((OrdMainStaffIdxCriteria)bCriteria) ;
            }

            @Override
            public long queryTotal(BaseCriteria bCriteria) {
                return ordMainStaffIdxMapper.countByExample((OrdMainStaffIdxCriteria)bCriteria);
            }

            @Override
            public List<Comparator<OrdMainStaffIdx>> defineComparators() {
                List<Comparator<OrdMainStaffIdx>> ls = new ArrayList<Comparator<OrdMainStaffIdx>>();
                ls.add(new Comparator<OrdMainStaffIdx>(){

                    @Override
                    public int compare(OrdMainStaffIdx o1, OrdMainStaffIdx o2) {
                        return o2.getOrderId().compareTo(o1.getOrderId());
                    }

                });
                return ls;
            }

            @Override
            public SOrderIdx warpReturnObject(OrdMainStaffIdx ordMainStaffIdx) {
                SOrderIdx sdoi = new SOrderIdx();
                BeanUtils.copyProperties(ordMainStaffIdx, sdoi);
                return sdoi;
            }
        });
    }
}
