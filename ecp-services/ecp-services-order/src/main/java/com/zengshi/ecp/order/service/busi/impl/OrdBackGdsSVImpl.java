package com.zengshi.ecp.order.service.busi.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.zengshi.ecp.goods.dubbo.constants.GdsOption;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.order.dao.mapper.busi.OrdBackGdsMapper;
import com.zengshi.ecp.order.dao.model.OrdBackGds;
import com.zengshi.ecp.order.dao.model.OrdBackGdsCriteria;
import com.zengshi.ecp.order.dubbo.dto.RBackApplyResp;
import com.zengshi.ecp.order.dubbo.dto.RBackGdsResp;
import com.zengshi.ecp.order.dubbo.dto.RBackOrdSubReq;
import com.zengshi.ecp.order.dubbo.dto.ROrderBackReq;
import com.zengshi.ecp.order.dubbo.dto.SBaseAndSubInfo;
import com.zengshi.ecp.order.dubbo.dto.SOrderDetailsSub;
import com.zengshi.ecp.order.dubbo.util.BackConstants;
import com.zengshi.ecp.order.dubbo.util.OrderUtils;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdBackGdsSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdSubSV;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.db.sequence.Sequence;

public class OrdBackGdsSVImpl implements IOrdBackGdsSV {
    
    @Resource
    private IOrdSubSV ordSubSV;
    
    @Resource
    private IGdsSkuInfoQueryRSV gdsSkuInfoQueryRSV;
    
    @Resource
    private OrdBackGdsMapper ordBackGdsMapper;
    
    @Resource(name = "seq_ord_back_gds")
    private Sequence seqOrdBackGds;

    private static final String MODULE = OrdBackGdsSVImpl.class.getName();

    @Override
    public void saveOrdBackGds(OrdBackGds ordBackGds) {
        ordBackGds.setId(this.seqOrdBackGds.nextValue());
        this.ordBackGdsMapper.insert(ordBackGds);
    }

    @Override
    public List<RBackGdsResp> queryBackGdsByBackId(RBackApplyResp rBackApplyResp) {
        OrdBackGdsCriteria obgc = new OrdBackGdsCriteria();
        obgc.createCriteria().andBackIdEqualTo(rBackApplyResp.getBackId())
                             .andOrderIdEqualTo(rBackApplyResp.getOrderId());
        List<OrdBackGds> obs = this.ordBackGdsMapper.selectByExample(obgc);
        if(CollectionUtils.isEmpty(obs)){
            return null;
        }
        List<RBackGdsResp> rbgs = new ArrayList<RBackGdsResp>();
        for(OrdBackGds ob :obs){
            RBackGdsResp rBackGdsResp = new RBackGdsResp();
            ObjectCopyUtil.copyObjValue(ob, rBackGdsResp, null, false);
            GdsSkuInfoReqDTO gdsInfoReqDTO = new GdsSkuInfoReqDTO();
            gdsInfoReqDTO.setId(ob.getSkuId());
            gdsInfoReqDTO.setGdsId(ob.getGdsId());
            List<Long> propIds = new ArrayList<Long>();
            propIds.add(1004l);
            propIds.add(1050l);
            gdsInfoReqDTO.setPropIds(propIds);
            gdsInfoReqDTO.setSkuQuery(new GdsOption.SkuQueryOption[] { GdsOption.SkuQueryOption.BASIC,
                    GdsOption.SkuQueryOption.MAINPIC,GdsOption.SkuQueryOption.PROP});
            GdsSkuInfoRespDTO gdsInfoRespDTO = gdsSkuInfoQueryRSV
                    .querySkuInfoByOptions(gdsInfoReqDTO);

            if(gdsInfoRespDTO != null){
                rBackGdsResp.setZsCode(OrderUtils.getZsCode(gdsInfoRespDTO)); //书号
            }

            rbgs.add(rBackGdsResp);
        }
        return rbgs;
    }

    @Override
    public List<OrdBackGds> queryBackGdsStatusByOrderSubId(RBackOrdSubReq rBackOrdSubReq) {
        OrdBackGdsCriteria obgc = new OrdBackGdsCriteria();
        
        List<String> status = new ArrayList<String>();
        status.add(BackConstants.Status.APPLY);
        status.add(BackConstants.Status.REVIEW_PASS);
        status.add(BackConstants.Status.SEND);
        status.add(BackConstants.Status.WAIT_REFUND);
        status.add(BackConstants.Status.REFUNDED);
        obgc.createCriteria().andOrderSubIdEqualTo(rBackOrdSubReq.getOrderSubId())
                             .andOrderIdEqualTo(rBackOrdSubReq.getOrderId())
                             .andStatusIn(status);
        List<OrdBackGds> obs = this.ordBackGdsMapper.selectByExample(obgc);
        if(CollectionUtils.isEmpty(obs)){
            return null;
        }
        return obs;
    }
    
    @Override
    public List<OrdBackGds> queryHasBackBackGdsByOrderSubId(RBackOrdSubReq rBackOrdSubReq) {
        OrdBackGdsCriteria obgc = new OrdBackGdsCriteria();    
        obgc.createCriteria().andOrderSubIdEqualTo(rBackOrdSubReq.getOrderSubId())
                             .andOrderIdEqualTo(rBackOrdSubReq.getOrderId())
                             .andStatusEqualTo(BackConstants.Status.REFUNDED);
        List<OrdBackGds> obs = this.ordBackGdsMapper.selectByExample(obgc);
        if(CollectionUtils.isEmpty(obs)){
            return null;
        }
        return obs;
    }

    @Override
    public List<RBackGdsResp> queryBackGds(ROrderBackReq rOrderBackReq) {
        OrdBackGdsCriteria obgc = new OrdBackGdsCriteria();
        obgc.createCriteria().andBackIdEqualTo(rOrderBackReq.getBackId())
                             .andOrderIdEqualTo(rOrderBackReq.getOrderId());
        obgc.setOrderByClause("order_sub_id asc");
        List<OrdBackGds> obs = this.ordBackGdsMapper.selectByExample(obgc);
        if(CollectionUtils.isEmpty(obs)){
            return null;
        }
        List<RBackGdsResp> rbgs = new ArrayList<RBackGdsResp>();
        for(OrdBackGds ob :obs){
            RBackGdsResp rBackGdsResp = new RBackGdsResp();
            ObjectCopyUtil.copyObjValue(ob, rBackGdsResp, null, false);
            
            SBaseAndSubInfo sBaseAndSubInfo = new SBaseAndSubInfo();
            sBaseAndSubInfo.setOrderId(rOrderBackReq.getOrderId());
            sBaseAndSubInfo.setOrderSubId(ob.getOrderSubId());
            SOrderDetailsSub sos =  this.ordSubSV.queryOrderDetailsSubBySubId(sBaseAndSubInfo);
            rBackGdsResp.setDiscountMoney(sos.getDiscountMoney());
            rBackGdsResp.setGdsUrl(sos.getGdsUrl());
            rBackGdsResp.setPicId(sos.getPicId());
            rBackGdsResp.setScore(sos.getScore());
            rBackGdsResp.setZsCode(sos.getZsCode());
            rbgs.add(rBackGdsResp);
        }
        return rbgs;
    }

    @Override
    public void updateOrdBackGdsFromInput(OrdBackGds ordBackGds) {
        OrdBackGdsCriteria obgc = new OrdBackGdsCriteria();
        obgc.createCriteria().andBackIdEqualTo(ordBackGds.getBackId())
                             .andOrderIdEqualTo(ordBackGds.getOrderId());
        this.ordBackGdsMapper.updateByExampleSelective(ordBackGds, obgc);
    }

    @Override
    public OrdBackGds queryOrdBackGds(OrdBackGds ordBackGds) {
        OrdBackGdsCriteria obgc = new OrdBackGdsCriteria();
        obgc.createCriteria().andSkuIdEqualTo(ordBackGds.getSkuId())
        .andOrderSubIdEqualTo(ordBackGds.getOrderSubId())
        .andStatusEqualTo(ordBackGds.getStatus());        
        List<OrdBackGds> list = this.ordBackGdsMapper.selectByExample(obgc);
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
    }

}

