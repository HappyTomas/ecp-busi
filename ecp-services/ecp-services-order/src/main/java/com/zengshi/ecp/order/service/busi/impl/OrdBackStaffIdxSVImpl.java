package com.zengshi.ecp.order.service.busi.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.order.dao.mapper.busi.OrdBackStaffIdxMapper;
import com.zengshi.ecp.order.dao.model.OrdBackApply;
import com.zengshi.ecp.order.dao.model.OrdBackStaffIdx;
import com.zengshi.ecp.order.dao.model.OrdBackStaffIdxCriteria;
import com.zengshi.ecp.order.dubbo.dto.RBackApplyResp;
import com.zengshi.ecp.order.dubbo.dto.ROrderBackReq;
import com.zengshi.ecp.order.dubbo.util.BackConstants;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdBackStaffIdxSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.db.sequence.Sequence;

public class OrdBackStaffIdxSVImpl extends GeneralSQLSVImpl implements IOrdBackStaffIdxSV {
    
    @Resource
    private OrdBackStaffIdxMapper ordBackStaffIdxMapper;
    
    @Resource(name = "seq_ord_back_staff_idx")
    private Sequence seqOrdBackStaffIdx;

    private static final String MODULE = OrdBackStaffIdxSVImpl.class.getName();

    @Override
    public void saveOrdBackStaffIdx(OrdBackStaffIdx ordBackApplyStaffIdx) {
        ordBackApplyStaffIdx.setId(this.seqOrdBackStaffIdx.nextValue());
        this.ordBackStaffIdxMapper.insert(ordBackApplyStaffIdx);
    }

    @Override
    public PageResponseDTO<RBackApplyResp> queryBackGdsByStaff(ROrderBackReq rOrderBackReq) throws BusinessException {
        OrdBackStaffIdxCriteria obasic = new OrdBackStaffIdxCriteria();
        obasic.setLimitClauseCount(rOrderBackReq.getPageSize());
        obasic.setLimitClauseStart(rOrderBackReq.getStartRowIndex());
        obasic.setOrderByClause("apply_time desc");
        OrdBackStaffIdxCriteria.Criteria ca = obasic.createCriteria()
                                                    .andStaffIdEqualTo(rOrderBackReq.getStaff().getId());
                                                    
        if(rOrderBackReq.getBegDate() != null){
            ca.andApplyTimeGreaterThan(rOrderBackReq.getBegDate());
        }
        if(rOrderBackReq.getEndDate() != null){
            ca.andApplyTimeLessThanOrEqualTo(rOrderBackReq.getEndDate());
        }
        if(rOrderBackReq.getApplyType().equals(BackConstants.ApplyType.BACK_GDS)){
            ca.andApplyTypeEqualTo(BackConstants.ApplyType.BACK_GDS);
        } else if(rOrderBackReq.getApplyType().equals(BackConstants.ApplyType.REFUND)){
            ca.andApplyTypeEqualTo(BackConstants.ApplyType.REFUND);
        }
        if(rOrderBackReq.getSiteId()!= null){
            ca.andSiteIdEqualTo(rOrderBackReq.getSiteId());
        }
        if(StringUtil.isNotBlank(rOrderBackReq.getOrderId())){
            ca.andOrderIdEqualTo(rOrderBackReq.getOrderId().trim());
        }
        if(StringUtil.isNotBlank(rOrderBackReq.getTabFlag())){
            ca.andApplyTypeEqualTo(rOrderBackReq.getTabFlag().trim());
        }
        return super.queryByPagination(rOrderBackReq, obasic, true, new PaginationCallback<OrdBackStaffIdx, RBackApplyResp>() {

            @Override
            public List<OrdBackStaffIdx> queryDB(BaseCriteria bCriteria) {
                return ordBackStaffIdxMapper.selectByExample((OrdBackStaffIdxCriteria)bCriteria) ;
            }

            @Override
            public long queryTotal(BaseCriteria bCriteria) {
                return ordBackStaffIdxMapper.countByExample((OrdBackStaffIdxCriteria)bCriteria);
            }

            @Override
            public List<Comparator<OrdBackStaffIdx>> defineComparators() {
                List<Comparator<OrdBackStaffIdx>> ls = new ArrayList<Comparator<OrdBackStaffIdx>>();
                ls.add(new Comparator<OrdBackStaffIdx>(){
                    @Override
                    public int compare(OrdBackStaffIdx o1, OrdBackStaffIdx o2) {
                        return o2.getApplyTime().compareTo(o1.getApplyTime());
                    }
                });
                return ls;
            }

            @Override
            public RBackApplyResp warpReturnObject(OrdBackStaffIdx ordBackApplyStaffIdx) {
                RBackApplyResp sdoi = new RBackApplyResp();
                BeanUtils.copyProperties(ordBackApplyStaffIdx, sdoi);
                return sdoi;
            }
        });
    }

    @Override
    public void updateBackApplyFromInput(OrdBackApply ordBackApply) throws BusinessException {
        OrdBackStaffIdxCriteria omc = new OrdBackStaffIdxCriteria();
        omc.createCriteria().andBackIdEqualTo(ordBackApply.getId()).andOrderIdEqualTo(ordBackApply.getOrderId());
        OrdBackStaffIdx oba = new OrdBackStaffIdx();
        ObjectCopyUtil.copyObjValue(ordBackApply, oba, null, false);
        oba.setId(null);
        this.ordBackStaffIdxMapper.updateByExampleSelective(oba, omc);
    }

}

