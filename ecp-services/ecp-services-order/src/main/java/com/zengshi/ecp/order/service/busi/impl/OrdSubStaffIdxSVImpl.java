package com.zengshi.ecp.order.service.busi.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dubbo.dto.*;
import com.zengshi.paas.utils.ObjectCopyUtil;
import org.springframework.beans.BeanUtils;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.order.dao.mapper.busi.OrdSubStaffIdxMapper;
import com.zengshi.ecp.order.dao.model.OrdSubStaffIdx;
import com.zengshi.ecp.order.dao.model.OrdSubStaffIdxCriteria;
import com.zengshi.ecp.order.dao.model.OrdSubStaffIdxCriteria.Criteria;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdSubStaffIdxSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.db.sequence.Sequence;
import org.springframework.util.CollectionUtils;
import org.stringtemplate.v4.ST;

public class OrdSubStaffIdxSVImpl extends GeneralSQLSVImpl implements IOrdSubStaffIdxSV {
    @Resource
    private OrdSubStaffIdxMapper ordSubStaffIdxMapper;

    @Resource(name = "seq_ord_sub_staff_idx")
    private Sequence seqOrdSubStaffIndex;
    @Override
    public void saveOrdSubStaffIdx(OrdSubStaffIdx ordSubStaffIdx) {
        ordSubStaffIdx.setId(seqOrdSubStaffIndex.nextValue());
        this.ordSubStaffIdxMapper.insert(ordSubStaffIdx);
    }
    @Override
    public PageResponseDTO<ROrdEvaluationResponse> querySubForEvaluationWait(
            ROrdEvaluationRequest rOrdEvaluationRequest) {
        
        final OrdSubStaffIdxCriteria osc = new OrdSubStaffIdxCriteria();
        osc.setLimitClauseCount(rOrdEvaluationRequest.getPageSize());
        osc.setLimitClauseStart(rOrdEvaluationRequest.getStartRowIndex());
        Criteria ca = osc.createCriteria().andStaffIdEqualTo(rOrdEvaluationRequest.getStaffId())
                                          .andEvalFlagEqualTo(OrdConstants.Common.COMMON_INVALID)
                                          .andStatusEqualTo(OrdConstants.OrderStatus.ORDER_STATUS_RECEPT)
                                          .andSiteIdEqualTo(1l);
        if(rOrdEvaluationRequest.getGdsId() != null && rOrdEvaluationRequest.getGdsId() > 0){
            ca.andGdsIdEqualTo(rOrdEvaluationRequest.getGdsId());
        }
        if(rOrdEvaluationRequest.getSkuId() != null && rOrdEvaluationRequest.getSkuId() > 0){
            ca.andSkuIdEqualTo(rOrdEvaluationRequest.getSkuId());
        }
        osc.setOrderByClause("order_id desc");
        return super.queryByPagination(rOrdEvaluationRequest, osc, true,
                new PaginationCallback<OrdSubStaffIdx, ROrdEvaluationResponse>() {

                    @Override
                    public List<OrdSubStaffIdx> queryDB(BaseCriteria bCriteria) {
                        return ordSubStaffIdxMapper.selectByExample((OrdSubStaffIdxCriteria) bCriteria);
                    }

                    @Override
                    public long queryTotal(BaseCriteria bCriteria) {
                        return ordSubStaffIdxMapper.countByExample((OrdSubStaffIdxCriteria) bCriteria);
                    }
                    
                    

                    

                    @Override
                    public List<Comparator<OrdSubStaffIdx>> defineComparators() {
                        List<Comparator<OrdSubStaffIdx>> ls = new ArrayList<Comparator<OrdSubStaffIdx>>();
                        ls.add(new Comparator<OrdSubStaffIdx>(){

                            @Override
                            public int compare(OrdSubStaffIdx o1, OrdSubStaffIdx o2) {
                                return o2.getOrderId().compareTo(o1.getOrderId());
                            }
                            
                        });
                        return ls;
                    }

                    @Override
                    public ROrdEvaluationResponse warpReturnObject(OrdSubStaffIdx ordSub) {
                        ROrdEvaluationResponse sdoi = new ROrdEvaluationResponse();
                        BeanUtils.copyProperties(ordSub, sdoi);
                        sdoi.setSubId(ordSub.getOrderSubId());
                        return sdoi;
                    }
                });
    }
    @Override
    public void updateEvalFlag(ROrdEvaluatedRequest rOrdEvaluatedRequest) {
        OrdSubStaffIdxCriteria osc = new OrdSubStaffIdxCriteria();
        osc.createCriteria().andOrderIdEqualTo(rOrdEvaluatedRequest.getOrderId())
                            .andOrderSubIdEqualTo(rOrdEvaluatedRequest.getSubId());
        OrdSubStaffIdx os = new OrdSubStaffIdx();
//        os.setStatus(sBaseAndStatusInfo.getStatus());
        os.setEvalFlag(OrdConstants.Common.COMMON_VALID);
        this.ordSubStaffIdxMapper.updateByExampleSelective(os, osc);
    }
    @Override
    public void updateOrderStatus(SBaseAndStatusInfo sOrderStatusInfo) {
        
        OrdSubStaffIdxCriteria osc = new OrdSubStaffIdxCriteria();
        osc.createCriteria().andOrderIdEqualTo(sOrderStatusInfo.getOrderId());
        OrdSubStaffIdx os = new OrdSubStaffIdx();
        os.setStatus(sOrderStatusInfo.getStatus());
        this.ordSubStaffIdxMapper.updateByExampleSelective(os, osc);
    }
    @Override
    public PageResponseDTO<SOrderSubIdx> queryOrderSubByStaffId(
            RQueryOrderRequest rQueryOrderRequest) {
        final OrdSubStaffIdxCriteria osc = new OrdSubStaffIdxCriteria();
        osc.setLimitClauseCount(rQueryOrderRequest.getPageSize());
        osc.setLimitClauseStart(rQueryOrderRequest.getStartRowIndex());
        Criteria ca = osc.createCriteria().andStaffIdEqualTo(rQueryOrderRequest.getStaffId());
        if(rQueryOrderRequest.getSiteId() != null){
            ca.andGdsIdEqualTo(rQueryOrderRequest.getSiteId());
        }
//        if(rOrdEvaluationRequest.getSkuId() != null && rOrdEvaluationRequest.getSkuId() > 0){
//            ca.andSkuIdEqualTo(rOrdEvaluationRequest.getSkuId());
//        }
        osc.setOrderByClause("order_sub_id desc");
        return super.queryByPagination(rQueryOrderRequest, osc, true,
                new PaginationCallback<OrdSubStaffIdx, SOrderSubIdx>() {

                    @Override
                    public List<OrdSubStaffIdx> queryDB(BaseCriteria bCriteria) {
                        return ordSubStaffIdxMapper.selectByExample((OrdSubStaffIdxCriteria) bCriteria);
                    }

                    @Override
                    public long queryTotal(BaseCriteria bCriteria) {
                        return ordSubStaffIdxMapper.countByExample((OrdSubStaffIdxCriteria) bCriteria);
                    }

                    @Override
                    public List<Comparator<OrdSubStaffIdx>> defineComparators() {
                        List<Comparator<OrdSubStaffIdx>> ls = new ArrayList<Comparator<OrdSubStaffIdx>>();
                        ls.add(new Comparator<OrdSubStaffIdx>(){

                            @Override
                            public int compare(OrdSubStaffIdx o1, OrdSubStaffIdx o2) {
                                return o2.getOrderSubId().compareTo(o1.getOrderSubId());
                            }
                        });
                        return ls;
                    }

                    @Override
                    public SOrderSubIdx warpReturnObject(OrdSubStaffIdx ordSub) {
                        SOrderSubIdx sdoi = new SOrderSubIdx();
                        BeanUtils.copyProperties(ordSub, sdoi);
                        return sdoi;
                    }
                });
    }

    @Override
    public List<ROrdSubStaffIdxResp> queryOrdSubStaffIdx(ROrdSubStaffIdxReq rOrdSubStaffIdxReq) {
        final OrdSubStaffIdxCriteria osc = new OrdSubStaffIdxCriteria();
        OrdSubStaffIdxCriteria.Criteria ca = osc.createCriteria().andStaffIdEqualTo(rOrdSubStaffIdxReq.getStaffId());
        List<String> status = new ArrayList<>();
        status.add(OrdConstants.OrderStatus.ORDER_STATUS_BACKGDS);
        status.add(OrdConstants.OrderStatus.ORDER_STATUS_REFUND);
        status.add(OrdConstants.OrderStatus.ORDER_STATUS_CANCLE);
        ca.andStatusNotIn(status);
        ca.andSkuIdEqualTo(rOrdSubStaffIdxReq.getSkuId());
        List<OrdSubStaffIdx> ordSubStaffIdxs = this.ordSubStaffIdxMapper.selectByExample(osc);
        if(CollectionUtils.isEmpty(ordSubStaffIdxs)){
            return null;
        }
        List<ROrdSubStaffIdxResp> ross = new ArrayList<ROrdSubStaffIdxResp>();
        for(OrdSubStaffIdx ossi:ordSubStaffIdxs){
            ROrdSubStaffIdxResp ros = new ROrdSubStaffIdxResp();
            ObjectCopyUtil.copyObjValue(ossi, ros, null, false);
            ross.add(ros);
        }
        return ross;
    }
}

