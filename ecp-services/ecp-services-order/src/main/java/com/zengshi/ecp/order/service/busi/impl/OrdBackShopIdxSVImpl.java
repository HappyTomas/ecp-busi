package com.zengshi.ecp.order.service.busi.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.order.dao.mapper.busi.OrdBackShopIdxMapper;
import com.zengshi.ecp.order.dao.model.OrdBackApply;
import com.zengshi.ecp.order.dao.model.OrdBackShopIdx;
import com.zengshi.ecp.order.dao.model.OrdBackShopIdxCriteria;
import com.zengshi.ecp.order.dubbo.dto.RBackApplyResp;
import com.zengshi.ecp.order.dubbo.dto.ROrderBackReq;
import com.zengshi.ecp.order.dubbo.util.BackConstants;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdBackShopIdxSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.db.sequence.Sequence;

public class OrdBackShopIdxSVImpl extends GeneralSQLSVImpl implements IOrdBackShopIdxSV {
    
    @Resource
    private OrdBackShopIdxMapper ordBackShopIdxMapper;
    
    @Resource(name = "seq_ord_back_shop_idx")
    private Sequence seqOrdBackShopIdx;

    private static final String MODULE = OrdBackShopIdxSVImpl.class.getName();

    @Override
    public void saveOrdBackShopIdx(OrdBackShopIdx ordBackShopIdx) {
        ordBackShopIdx.setId(this.seqOrdBackShopIdx.nextValue());
        this.ordBackShopIdxMapper.insert(ordBackShopIdx);
    }

    @Override
    public PageResponseDTO<RBackApplyResp> queryBackGdsByShop(ROrderBackReq rOrderBackReq)
            throws BusinessException {
        OrdBackShopIdxCriteria obasic = new OrdBackShopIdxCriteria();
        obasic.setLimitClauseCount(rOrderBackReq.getPageSize());
        obasic.setLimitClauseStart(rOrderBackReq.getStartRowIndex());
        obasic.setOrderByClause("apply_time desc");
        OrdBackShopIdxCriteria.Criteria ca = obasic.createCriteria()
                                                   .andShopIdEqualTo(rOrderBackReq.getShopId());
        if(rOrderBackReq.getBegDate() != null){
            ca.andApplyTimeGreaterThan(rOrderBackReq.getBegDate());
        }
        if(rOrderBackReq.getEndDate() != null){
            ca.andApplyTimeLessThanOrEqualTo(rOrderBackReq.getEndDate());
        }
        if(StringUtil.isNotBlank(rOrderBackReq.getApplyType())){
            ca.andApplyTypeEqualTo(rOrderBackReq.getApplyType());
        }
        if(rOrderBackReq.getSiteId()!= null){
            ca.andSiteIdEqualTo(rOrderBackReq.getSiteId());
        }
        if(StringUtil.isNotBlank(rOrderBackReq.getOrderId())){
            ca.andOrderIdEqualTo(rOrderBackReq.getOrderId().trim());
        }
        if(StringUtil.isNotBlank(rOrderBackReq.getPayTranNo())){
            ca.andPayTranNoEqualTo(rOrderBackReq.getPayTranNo().trim());
        }
        if(StringUtil.isNotBlank(rOrderBackReq.getTabFlag())){
            if("00".equals(rOrderBackReq.getTabFlag())){
                List<String> status = new ArrayList<String>();
                status.add(BackConstants.Status.APPLY);
                status.add(BackConstants.Status.REVIEW_PASS);
                status.add(BackConstants.Status.SEND);
                status.add(BackConstants.Status.WAIT_REFUND); 
                ca.andStatusIn(status);
            } else {
                List<String> status = new ArrayList<String>();
                status.add(BackConstants.Status.REFUNDED); 
                status.add(BackConstants.Status.REFUSE);
                ca.andStatusIn(status);
            }
        }
        if(StringUtil.isNotBlank(rOrderBackReq.getStatus())){
            ca.andStatusEqualTo(rOrderBackReq.getStatus());
        }
        return super.queryByPagination(rOrderBackReq, obasic, true, new PaginationCallback<OrdBackShopIdx, RBackApplyResp>() {

            @Override
            public List<OrdBackShopIdx> queryDB(BaseCriteria bCriteria) {
                return ordBackShopIdxMapper.selectByExample((OrdBackShopIdxCriteria)bCriteria) ;
            }

            @Override
            public long queryTotal(BaseCriteria bCriteria) {
                return ordBackShopIdxMapper.countByExample((OrdBackShopIdxCriteria)bCriteria);
            }

            @Override
            public List<Comparator<OrdBackShopIdx>> defineComparators() {
                List<Comparator<OrdBackShopIdx>> ls = new ArrayList<Comparator<OrdBackShopIdx>>();
                ls.add(new Comparator<OrdBackShopIdx>(){
                    @Override
                    public int compare(OrdBackShopIdx o1, OrdBackShopIdx o2) {
                        return o2.getApplyTime().compareTo(o1.getApplyTime());
                    }
                });
                return ls;
            }

            @Override
            public RBackApplyResp warpReturnObject(OrdBackShopIdx ordBackApplyShopIdx) {
                RBackApplyResp sdoi = new RBackApplyResp();
                BeanUtils.copyProperties(ordBackApplyShopIdx, sdoi);
                return sdoi;
            }
        });
    }

    @Override
    public void updateBackApplyFromInput(OrdBackApply ordBackApply) throws BusinessException {
        OrdBackShopIdxCriteria omc = new OrdBackShopIdxCriteria();
        omc.createCriteria().andBackIdEqualTo(ordBackApply.getId()).andOrderIdEqualTo(ordBackApply.getOrderId());
        OrdBackShopIdx oba = new OrdBackShopIdx();
        ObjectCopyUtil.copyObjValue(ordBackApply, oba, null, false);
        oba.setId(null);
        this.ordBackShopIdxMapper.updateByExampleSelective(oba, omc);
    }

}

