package com.zengshi.ecp.order.service.busi.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.order.dao.mapper.busi.OrdSubShopIdxMapper;
import com.zengshi.ecp.order.dao.model.OrdSubShopIdx;
import com.zengshi.ecp.order.dao.model.OrdSubShopIdxCriteria;
import com.zengshi.ecp.order.dubbo.dto.RGoodSaleRequest;
import com.zengshi.ecp.order.dubbo.dto.RGoodSaleResponse;
import com.zengshi.ecp.order.dubbo.dto.SBaseAndStatusInfo;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdSubShopIdxSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.db.sequence.Sequence;

public class OrdSubShopIdxSVImpl extends GeneralSQLSVImpl  implements IOrdSubShopIdxSV {
    @Resource
    private OrdSubShopIdxMapper ordSubShopIdxMapper;

    @Resource(name = "seq_ord_sub_shop_idx")
    private Sequence seqOrdSubShopIndex;
    @Override
    public void saveOrdSubShopIdx(OrdSubShopIdx ordSubShopIdx) {
        ordSubShopIdx.setId(seqOrdSubShopIndex.nextValue());
        this.ordSubShopIdxMapper.insert(ordSubShopIdx);
    }
    @Override
    public void updateOrderStatus(SBaseAndStatusInfo sOrderStatusInfo) {
        OrdSubShopIdxCriteria osc = new OrdSubShopIdxCriteria();
        osc.createCriteria().andOrderIdEqualTo(sOrderStatusInfo.getOrderId());
        OrdSubShopIdx os = new OrdSubShopIdx();
        os.setStatus(sOrderStatusInfo.getStatus());
        this.ordSubShopIdxMapper.updateByExampleSelective(os, osc);
    }
    
    @Override
    public PageResponseDTO<RGoodSaleResponse> queryOrdSubShopIdx(RGoodSaleRequest rGoodSaleRequest){
        final OrdSubShopIdxCriteria osc = new OrdSubShopIdxCriteria();
        osc.setLimitClauseCount(rGoodSaleRequest.getPageSize());
        osc.setLimitClauseStart(rGoodSaleRequest.getStartRowIndex());
        osc.setOrderByClause("order_time desc");
        osc.createCriteria().andOrderIdLike("RW%");
        osc.createCriteria().andCategoryCodeIsNull();
        
        return super.queryByPagination(rGoodSaleRequest, osc, true, new PaginationCallback<OrdSubShopIdx, RGoodSaleResponse>() {

            @Override
            public List<OrdSubShopIdx> queryDB(BaseCriteria bCriteria) {
                return ordSubShopIdxMapper.selectByExample((OrdSubShopIdxCriteria) bCriteria) ;
            }

            @Override
            public long queryTotal(BaseCriteria bCriteria) {
                return ordSubShopIdxMapper.countByExample((OrdSubShopIdxCriteria) bCriteria);
            }

            @Override
            public RGoodSaleResponse warpReturnObject(OrdSubShopIdx ordSubShopIdx) {
            	RGoodSaleResponse goodSale = new RGoodSaleResponse();
            	goodSale.setOrderId(ordSubShopIdx.getOrderId());
            	goodSale.setIndexId(ordSubShopIdx.getId());
            	goodSale.setOrderSubId(ordSubShopIdx.getOrderSubId());
                return goodSale;
            }

        });
    }
    @Override
    public PageResponseDTO<RGoodSaleResponse> queryOrdSubShopIdx1(RGoodSaleRequest rGoodSaleRequest){
        final OrdSubShopIdxCriteria osc = new OrdSubShopIdxCriteria();
        osc.setLimitClauseCount(rGoodSaleRequest.getPageSize());
        osc.setLimitClauseStart(rGoodSaleRequest.getStartRowIndex());
        osc.setOrderByClause("order_time desc");
        osc.createCriteria().andOrderIdLike("RW%").andPayTypeIsNull();
        
        
        return super.queryByPagination(rGoodSaleRequest, osc, true, new PaginationCallback<OrdSubShopIdx, RGoodSaleResponse>() {

            @Override
            public List<OrdSubShopIdx> queryDB(BaseCriteria bCriteria) {
                return ordSubShopIdxMapper.selectByExample((OrdSubShopIdxCriteria) bCriteria) ;
            }

            @Override
            public long queryTotal(BaseCriteria bCriteria) {
                return ordSubShopIdxMapper.countByExample((OrdSubShopIdxCriteria) bCriteria);
            }

            @Override
            public RGoodSaleResponse warpReturnObject(OrdSubShopIdx ordSubShopIdx) {
            	RGoodSaleResponse goodSale = new RGoodSaleResponse();
            	goodSale.setOrderId(ordSubShopIdx.getOrderId());
            	goodSale.setIndexId(ordSubShopIdx.getId());
            	goodSale.setOrderSubId(ordSubShopIdx.getOrderSubId());
                return goodSale;
            }

        });
    }
    
	@Override
	public void updateSubOrderBackInfo(String subOrderId,Long backMoney,Long backNum) {
		// TODO Auto-generated method stub
		OrdSubShopIdxCriteria osc = new OrdSubShopIdxCriteria();
        osc.createCriteria().andOrderSubIdEqualTo(subOrderId);
        List<OrdSubShopIdx> idxs = ordSubShopIdxMapper.selectByExample(osc);
        OrdSubShopIdx os = new OrdSubShopIdx();
        //判断是否曾经有过退款退货记录
        if(idxs!=null&&idxs.size()>0){
        	OrdSubShopIdx idx = idxs.get(0);
        	if(idx.getBackMoney()!=null){
        		 os.setBackMoney(backMoney+idx.getBackMoney());
        	}else{
        		 os.setBackMoney(backMoney);
        	}
        }
        os.setHasBackNum(backNum);
        OrdSubShopIdxCriteria osc1 = new OrdSubShopIdxCriteria();
        osc1.createCriteria().andOrderSubIdEqualTo(subOrderId);
    
        this.ordSubShopIdxMapper.updateByExampleSelective(os, osc1);
	}

}

