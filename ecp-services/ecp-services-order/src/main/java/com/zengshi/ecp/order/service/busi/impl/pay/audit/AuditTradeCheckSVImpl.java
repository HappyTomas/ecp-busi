package com.zengshi.ecp.order.service.busi.impl.pay.audit;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.order.dao.mapper.busi.AuditTradeCheckMapper;
import com.zengshi.ecp.order.dao.mapper.busi.manual.AuditTradeCheckManualMapper;
import com.zengshi.ecp.order.dao.model.AuditTradeCheck;
import com.zengshi.ecp.order.dao.model.AuditTradeCheckCriteria;
import com.zengshi.ecp.order.dubbo.dto.pay.RAuditTradeCheckRequest;
import com.zengshi.ecp.order.dubbo.dto.pay.RAuditTradeCheckResponse;
import com.zengshi.ecp.order.dubbo.dto.pay.RAuditTradeCheckTotalResponse;
import com.zengshi.ecp.order.dubbo.util.MsgConstants.PayInputMsgCode;
import com.zengshi.ecp.order.dubbo.util.OrdConstants.PayStatus;
import com.zengshi.ecp.order.service.busi.interfaces.pay.audit.IAuditTradeCheckSV;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IStaffUnionRSV;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-order-server <br>
 * Description: <br>
 * Date:2015年10月8日下午2:58:25  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author weijq
 * @version  
 * @since JDK 1.6
 */
public class AuditTradeCheckSVImpl extends GeneralSQLSVImpl implements IAuditTradeCheckSV{
    
    @Resource
    private AuditTradeCheckMapper auditTradeCheckMapper;
    
    @Resource
    private AuditTradeCheckManualMapper auditTradeCheckManualMapper;
    
    @Resource
    private IStaffUnionRSV staffUnionRSV;
    
    @Resource
    private IShopInfoRSV shopInfoRSV;

    @Override
    public void saveAuditTradeCheck(AuditTradeCheck auditTradeCheck) {
        if (auditTradeCheck == null) {
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300000);
        }
        auditTradeCheckMapper.insert(auditTradeCheck);
    }
    
    @Override
    public PageResponseDTO<RAuditTradeCheckResponse> getTAuditTradeChecks(RAuditTradeCheckRequest auditTradeCheckRequest){
        AuditTradeCheckCriteria example = getCriteria(auditTradeCheckRequest); 
        example.setLimitClauseStart(auditTradeCheckRequest.getStartRowIndex());
        example.setLimitClauseCount(auditTradeCheckRequest.getPageSize());
        PageResponseDTO<RAuditTradeCheckResponse> pageResponseDTO= queryByPagination(auditTradeCheckRequest,example);
        if(CollectionUtils.isNotEmpty(pageResponseDTO.getResult())) {
        	for(RAuditTradeCheckResponse auditTradeCheckResponse:pageResponseDTO.getResult()){
        		if(auditTradeCheckResponse.getStaffId()!=null){
        			
        			CustInfoResDTO custInfoResDTO = this.staffUnionRSV.findCustOrAdminByStaffId(auditTradeCheckResponse.getStaffId());
        			if(custInfoResDTO != null && custInfoResDTO.getStaffCode() != null){
        				auditTradeCheckResponse.setStaffName(custInfoResDTO.getStaffCode());
        			} else {
        				auditTradeCheckResponse.setStaffName(auditTradeCheckResponse.getStaffId().toString());
        			}
        		}
        		if(auditTradeCheckResponse.getShopId()!=null){
                    
                    ShopInfoResDTO shopInfoResDTO = this.shopInfoRSV.findShopInfoByShopID(auditTradeCheckResponse.getShopId());
                    if(shopInfoResDTO != null && shopInfoResDTO.getShopName() != null){
                        auditTradeCheckResponse.setShopName(shopInfoResDTO.getShopName());
                    } else {
                        auditTradeCheckResponse.setShopName(auditTradeCheckResponse.getShopId().toString());
                    }
                }
        	}
        }
        return pageResponseDTO;
    };
    
    private PageResponseDTO<RAuditTradeCheckResponse> queryByPagination(BaseInfo baseInfo,BaseCriteria criteria){
        return super.queryByPagination(baseInfo, criteria, true, new PaginationCallback<AuditTradeCheck, RAuditTradeCheckResponse>() {

            @Override
            public List<AuditTradeCheck> queryDB(BaseCriteria bCriteria) {
                return auditTradeCheckMapper.selectByExample((AuditTradeCheckCriteria)bCriteria) ;
            }

            @Override
            public long queryTotal(BaseCriteria bCriteria) {
                return auditTradeCheckMapper.countByExample((AuditTradeCheckCriteria)bCriteria);
            }

            @Override
            public List<Comparator<AuditTradeCheck>> defineComparators() {
                List<Comparator<AuditTradeCheck>> ls = new ArrayList<Comparator<AuditTradeCheck>>();
                ls.add(new Comparator<AuditTradeCheck>(){

                    @Override
                    public int compare(AuditTradeCheck auditTradeCheck1, AuditTradeCheck auditTradeCheck2) {
                        if(auditTradeCheck1.getPayTime() == null){
                            return -1;
                        }else if(auditTradeCheck2.getPayTime() == null){
                            return 1;
                        }else{
                            return auditTradeCheck2.getPayTime().compareTo(auditTradeCheck1.getPayTime());
                        } 
                    }
                    
                });
                return ls;
            }

            @Override
            public RAuditTradeCheckResponse warpReturnObject(AuditTradeCheck auditTradeCheck) {
                RAuditTradeCheckResponse sdoi = new RAuditTradeCheckResponse();
                    BeanUtils.copyProperties(auditTradeCheck, sdoi);
                    return sdoi;
            }

            
        });
      }

    @Override
    public RAuditTradeCheckTotalResponse getTAuditTradeChecksTotal(
            RAuditTradeCheckRequest auditTradeCheckRequest) {
    	if(PayStatus.AUDIT_TRADE_CHECK_AUDIT_TYPE_01.equals(auditTradeCheckRequest.getAuditType())){
    		AuditTradeCheckCriteria example = getCriteria(auditTradeCheckRequest);
    		Long transAmounts = auditTradeCheckManualMapper.countSumTransAmountByExample(example);
    		Long transOrderCounts = auditTradeCheckMapper.countByExample(example);
    		RAuditTradeCheckRequest auditTradeCheckRequest1 = new RAuditTradeCheckRequest();
    		BeanUtils.copyProperties(auditTradeCheckRequest, auditTradeCheckRequest1);
    		auditTradeCheckRequest1.setAuditStatus(PayStatus.AUDIT_STATUS_SUCC);
    		AuditTradeCheckCriteria example1 = getCriteria(auditTradeCheckRequest1);
    		Long successOrderCounts = auditTradeCheckMapper.countByExample(example1);
    		Long successOrderAmounts = auditTradeCheckManualMapper.countSumOrderMoneyByExample(example1);
    		RAuditTradeCheckTotalResponse response = new RAuditTradeCheckTotalResponse();
    		response.setSuccessOrderAmounts(successOrderAmounts);
    		response.setSuccessOrderCounts(successOrderCounts);
    		response.setTransAmounts(transAmounts);
    		response.setTransOrderCounts(transOrderCounts);
    		return response;
    		
    	}else if(PayStatus.AUDIT_TRADE_CHECK_AUDIT_TYPE_02.equals(auditTradeCheckRequest.getAuditType())){
    		AuditTradeCheckCriteria example = getCriteria(auditTradeCheckRequest);
    		Long transAmounts = auditTradeCheckManualMapper.countSumRefundTransAmountByExample(example);
    		Long transOrderCounts = auditTradeCheckMapper.countByExample(example);
    		RAuditTradeCheckRequest auditTradeCheckRequest1 = new RAuditTradeCheckRequest();
    		BeanUtils.copyProperties(auditTradeCheckRequest, auditTradeCheckRequest1);
    		auditTradeCheckRequest1.setAuditStatus(PayStatus.AUDIT_STATUS_SUCC);
    		AuditTradeCheckCriteria example1 = getCriteria(auditTradeCheckRequest1);
    		Long successOrderCounts = auditTradeCheckMapper.countByExample(example1);
    		Long successOrderAmounts = auditTradeCheckManualMapper.countSumRefundOrderAmountByExample(example1);
    		RAuditTradeCheckTotalResponse response = new RAuditTradeCheckTotalResponse();
    		response.setSuccessOrderAmounts(successOrderAmounts);
    		response.setSuccessOrderCounts(successOrderCounts);
    		response.setTransAmounts(transAmounts);
    		response.setTransOrderCounts(transOrderCounts);
    		return response;
    	}
    	return null;
    }
    
    private AuditTradeCheckCriteria getCriteria(
            RAuditTradeCheckRequest auditTradeCheckRequest) {
        AuditTradeCheckCriteria example = new AuditTradeCheckCriteria();
        AuditTradeCheckCriteria.Criteria criteria = example.createCriteria();
        criteria.andCheckDateBetween(auditTradeCheckRequest.getStartTime(), auditTradeCheckRequest.getEndTime());
        if(!StringUtil.isBlank(auditTradeCheckRequest.getOrderId())){
            criteria.andOrderIdEqualTo(auditTradeCheckRequest.getOrderId());
        }
        if(!StringUtil.isBlank(auditTradeCheckRequest.getPayWay())){
            criteria.andPayWayEqualTo(auditTradeCheckRequest.getPayWay());
        }
        if(!StringUtil.isBlank(auditTradeCheckRequest.getAuditStatus())){
            criteria.andAuditStatusEqualTo(auditTradeCheckRequest.getAuditStatus());
        }
        if(auditTradeCheckRequest.getShopId()!=null){
            criteria.andShopIdEqualTo(auditTradeCheckRequest.getShopId());
        }
        if(auditTradeCheckRequest.getStaffId()!=null){
            criteria.andStaffIdEqualTo(auditTradeCheckRequest.getStaffId());
        }
        if(!StringUtil.isBlank(auditTradeCheckRequest.getAuditType())){
            criteria.andAuditTypeEqualTo(auditTradeCheckRequest.getAuditType());
        }
        return example;
    }
    
}