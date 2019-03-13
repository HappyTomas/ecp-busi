package com.zengshi.ecp.order.service.busi.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dao.model.OrdMainShopIdxCriteria;
import com.zengshi.ecp.order.dubbo.dto.ROrdCountResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdPayRelReq;
import com.zengshi.ecp.server.front.exception.BusinessException;
import org.springframework.beans.BeanUtils;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.order.dao.mapper.busi.OrdMainShopIdxMapper;
import com.zengshi.ecp.order.dao.model.OrdMain;
import com.zengshi.ecp.order.dao.model.OrdMainCriteria;
import com.zengshi.ecp.order.dao.model.OrdMainShopIdx;
import com.zengshi.ecp.order.dao.model.OrdMainShopIdxCriteria;
import com.zengshi.ecp.order.dao.model.OrdMainShopIdxCriteria.Criteria;
import com.zengshi.ecp.order.dubbo.dto.RQueryOrderRequest;
import com.zengshi.ecp.order.dubbo.dto.SBaseAndStatusInfo;
import com.zengshi.ecp.order.dubbo.dto.SOrderIdx;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdMainShopIdxSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.StringUtil;
import com.db.sequence.Sequence;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-order <br>
 * Description: <br>
 * Date:2015年8月11日下午5:44:01  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author cbl
 * @version  
 * @since JDK 1.6 
 */  
public class OrdMainShopIdxSVImpl extends GeneralSQLSVImpl implements IOrdMainShopIdxSV {

    @Resource
    private OrdMainShopIdxMapper ordMainShopIdxMapper;

    @Resource(name = "seq_ord_main_shop_idx")
    private Sequence seqOrdMainShopIndex;
    

    @Override
    public void saveOrdMainShopIdx(final OrdMainShopIdx ordMainShopIdx) {
        ordMainShopIdx.setId(this.seqOrdMainShopIndex.nextValue());
        this.ordMainShopIdxMapper.insert(ordMainShopIdx);
    }

    @Override
    public void updateOrderStatus(SBaseAndStatusInfo sBaseAndStatusInfo) {
        final OrdMainShopIdxCriteria omsic = new OrdMainShopIdxCriteria();
        omsic.createCriteria().andOrderIdEqualTo(sBaseAndStatusInfo.getOrderId())
                              .andShopIdEqualTo(sBaseAndStatusInfo.getShopId());
        OrdMainShopIdx omsi = new OrdMainShopIdx();
        omsi.setStatus(sBaseAndStatusInfo.getStatus());
        this.ordMainShopIdxMapper.updateByExampleSelective(omsi, omsic);
    }
    
    @Override
    public void updateOrderPayTranNo(ROrdPayRelReq rOrdPayRelReq){  
        final OrdMainShopIdxCriteria omsic = new OrdMainShopIdxCriteria();
        omsic.createCriteria().andOrderIdEqualTo(rOrdPayRelReq.getOrderId());
        OrdMainShopIdx omsi = new OrdMainShopIdx();
        omsi.setPayTranNo(rOrdPayRelReq.getJoinOrderid()); 
        this.ordMainShopIdxMapper.updateByExampleSelective(omsi, omsic);
    }
    
    private void setCriteria(OrdMainShopIdxCriteria.Criteria criteria,RQueryOrderRequest rQueryOrderRequest){
        if(StringUtil.isNotBlank(rQueryOrderRequest.getSysType())){
            criteria.andSysTypeEqualTo(rQueryOrderRequest.getSysType());
        }
        if(rQueryOrderRequest.getBegDate() != null){
            criteria.andOrderTimeGreaterThanOrEqualTo(rQueryOrderRequest.getBegDate());
        }
        if(rQueryOrderRequest.getEndDate() != null){
            criteria.andOrderTimeLessThanOrEqualTo(rQueryOrderRequest.getEndDate());
        }
        if(rQueryOrderRequest.getSiteId()!= null){
            criteria.andSiteIdEqualTo(rQueryOrderRequest.getSiteId());
        }
        if(StringUtil.isNotBlank(rQueryOrderRequest.getOrderId())){
            criteria.andOrderIdEqualTo(rQueryOrderRequest.getOrderId());
        }
        if(rQueryOrderRequest.getStaffId() != null && rQueryOrderRequest.getStaffId() > 0){
            criteria.andStaffIdEqualTo(rQueryOrderRequest.getStaffId());
        }
//        if(StringUtil.isNotBlank(rQueryOrderRequest.getStatus())){
//            ca.andStatusEqualTo(rQueryOrderRequest.getStatus());
//        }
        if(StringUtil.isNotBlank(rQueryOrderRequest.getContactName())){
            criteria.andContactNameEqualTo(rQueryOrderRequest.getContactName());
        }
        if(StringUtil.isNotBlank(rQueryOrderRequest.getContactPhone())){
            criteria.andContactPhoneEqualTo(rQueryOrderRequest.getContactPhone());
        }
        if(StringUtil.isNotBlank(rQueryOrderRequest.getInvoiceType())){
            if("2".equals(rQueryOrderRequest.getInvoiceType())){
                criteria.andInvoiceTypeEqualTo(rQueryOrderRequest.getInvoiceType());
            } else {
                criteria.andInvoiceTypeNotEqualTo("2");
            }
        }
        if(StringUtil.isNotBlank(rQueryOrderRequest.getDispatchType())){
            criteria.andDispatchTypeEqualTo(rQueryOrderRequest.getDispatchType());
        }
        List<String> status = new ArrayList<String>();
        if(OrdConstants.ShopRequestStatus.REQUEST_STATUS_SEND.equals(rQueryOrderRequest.getStatus())){   //01-待发货
            status.add(OrdConstants.OrderStatus.ORDER_STATUS_PAID);  //支付完成
            status.add(OrdConstants.OrderStatus.ORDER_STATUS_SEND_SECTION); //部分发货
            criteria.andStatusIn(status);
            
        } else if(OrdConstants.ShopRequestStatus.REQUEST_STATUS_DELY.equals(rQueryOrderRequest.getStatus())){ //02-已发货
            status.add(OrdConstants.OrderStatus.ORDER_STATUS_SEND_ALL);  //已发货
            status.add(OrdConstants.OrderStatus.ORDER_STATUS_RECEPT); //已收货
            status.add(OrdConstants.OrderStatus.ORDER_STATUS_CLOSE); //关闭
//            status.add(OrdConstants.OrderStatus.ORDER_STATUS_BACKGDS);//已退货
//            status.add(OrdConstants.OrderStatus.ORDER_STATUS_REFUND); //已退款
            criteria.andStatusIn(status);
            
        }  
    }

    @Override
    public PageResponseDTO<SOrderIdx> queryOrderByShopIdPage(final RQueryOrderRequest rQueryOrderRequest) {
        final OrdMainShopIdxCriteria omsic = new OrdMainShopIdxCriteria();
        omsic.setLimitClauseCount(rQueryOrderRequest.getPageSize());
        omsic.setLimitClauseStart(rQueryOrderRequest.getStartRowIndex());
        omsic.setOrderByClause("order_id desc");
        Criteria ca = omsic.createCriteria().andShopIdEqualTo(rQueryOrderRequest.getShopId());
        setCriteria(ca,rQueryOrderRequest);
        //在退款、退货流程中的过滤掉
        List<String> status1 = new ArrayList<String>();
        status1.add(OrdConstants.OrderTwoStatus.STATUS_BACKGDS_YES);
        status1.add(OrdConstants.OrderTwoStatus.STATUS_REFUND_YES);
        ca.andOrderTwoStatusNotIn(status1);
        
        OrdMainShopIdxCriteria.Criteria criteria = omsic.createCriteria();
        setCriteria(criteria,rQueryOrderRequest);
        criteria.andShopIdEqualTo(rQueryOrderRequest.getShopId());
        criteria.andOrderTwoStatusIsNull();
       
        if(OrdConstants.ShopRequestStatus.REQUEST_STATUS_SEND.equals(rQueryOrderRequest.getStatus())){   //01-待发货
            omsic.setOrderByClause("order_id asc");
        }  
        omsic.or(criteria);
        
        
        return super.queryByPagination(rQueryOrderRequest, omsic, true, new PaginationCallback<OrdMainShopIdx, SOrderIdx>() {

            @Override
            public List<OrdMainShopIdx> queryDB(BaseCriteria bCriteria) {
                return ordMainShopIdxMapper.selectByExample((OrdMainShopIdxCriteria)bCriteria) ;
            }

            @Override
            public long queryTotal(BaseCriteria bCriteria) {
                return ordMainShopIdxMapper.countByExample((OrdMainShopIdxCriteria)bCriteria);
            }

            @Override
            public List<Comparator<OrdMainShopIdx>> defineComparators() {
                List<Comparator<OrdMainShopIdx>> ls = new ArrayList<Comparator<OrdMainShopIdx>>();
                ls.add(new Comparator<OrdMainShopIdx>(){

                    @Override
                    public int compare(OrdMainShopIdx o1, OrdMainShopIdx o2) {
                        if(OrdConstants.ShopRequestStatus.REQUEST_STATUS_SEND.equals(rQueryOrderRequest.getStatus())){
                            return o1.getOrderId().compareTo(o2.getOrderId());
                        } else {
                            return o2.getOrderId().compareTo(o1.getOrderId());
                        }
                        
                    }
                    
                });
                return ls;
            }

            @Override
            public SOrderIdx warpReturnObject(OrdMainShopIdx ordMainShopIdx) {
                    SOrderIdx sdoi = new SOrderIdx();
                    BeanUtils.copyProperties(ordMainShopIdx, sdoi);
                    return sdoi;
            }

            
        });
    }
    

    @Override
    public void updateOrderTwoStatusByOrderId(OrdMain ordMain) {
        OrdMainShopIdxCriteria omsic = new OrdMainShopIdxCriteria();
        omsic.createCriteria().andOrderIdEqualTo(ordMain.getId())
                              .andShopIdEqualTo(ordMain.getShopId());
        OrdMainShopIdx omsi = new OrdMainShopIdx();
        omsi.setOrderTwoStatus(ordMain.getOrderTwoStatus());
        this.ordMainShopIdxMapper.updateByExampleSelective(omsi, omsic);
    }

    @Override
    public Long queryOrderCountByShop(RQueryOrderRequest rQueryOrderRequest) throws BusinessException {
        ROrdCountResponse  rocr = new ROrdCountResponse();
        final OrdMainShopIdxCriteria omsic = new OrdMainShopIdxCriteria();
        OrdMainShopIdxCriteria.Criteria ca = omsic.createCriteria().andStaffIdEqualTo(rQueryOrderRequest.getShopId());
        if(StringUtil.isNotBlank(rQueryOrderRequest.getStatus())){
            ca.andStatusEqualTo(rQueryOrderRequest.getStatus());
        }
        return this.ordMainShopIdxMapper.countByExample(omsic);
    }
}
