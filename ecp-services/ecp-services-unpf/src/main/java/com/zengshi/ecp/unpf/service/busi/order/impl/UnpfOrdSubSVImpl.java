package com.zengshi.ecp.unpf.service.busi.order.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.zengshi.ecp.goods.dubbo.constants.GdsOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dao.mapper.busi.UnpfOrdSubMapper;
import com.zengshi.ecp.unpf.dao.model.UnpfOrdSub;
import com.zengshi.ecp.unpf.dao.model.UnpfOrdSubCriteria;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfOrdMainReq;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfOrdSubReq;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfOrdSubResp;
import com.zengshi.ecp.unpf.dubbo.util.UnpfConstants;
import com.zengshi.ecp.unpf.service.busi.order.interfaces.IUnpfOrdSubSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.db.sequence.Sequence;

/**
 * @author: cbl
 * @date: 2016/11/8.
 */
public class UnpfOrdSubSVImpl implements IUnpfOrdSubSV {

    @Resource
    private UnpfOrdSubMapper unpfOrdSubMapper;

    @Resource
    private IGdsInfoQueryRSV gdsInfoQueryRSV;

    @Resource(name = "seq_unpf_ord_sub")
    private Sequence seqUnpfOrdSub;

    @Override
    public List<RUnpfOrdSubResp> queryUnpfOrdSub(RUnpfOrdMainReq rUnpfOrdMainReq) throws BusinessException {

        UnpfOrdSubCriteria uosc = new UnpfOrdSubCriteria();
        UnpfOrdSubCriteria.Criteria ca = uosc.createCriteria();
        if(StringUtil.isNotBlank(rUnpfOrdMainReq.getId()) ){
            ca.andOrderIdEqualTo(rUnpfOrdMainReq.getId());
        }
        if(StringUtil.isNotBlank(rUnpfOrdMainReq.getOuterId())){
            ca.andOuterIdEqualTo(rUnpfOrdMainReq.getOuterId());
        }
        if(StringUtil.isNotBlank(rUnpfOrdMainReq.getPlatType())){
            ca.andPlatTypeEqualTo(rUnpfOrdMainReq.getPlatType());
        }
        if("1".equals(rUnpfOrdMainReq.getBuyerMsg())){
            List<String> status = new ArrayList<>();
            status.add("02");
            status.add("04");
            ca.andStatusIn(status);
        }
        List<UnpfOrdSub> unpfOrdSubs = unpfOrdSubMapper.selectByExample(uosc);
        if(CollectionUtils.isEmpty(unpfOrdSubs)){
            return  null;
        }
        List<RUnpfOrdSubResp> rUnpfOrdSubResps = new ArrayList<RUnpfOrdSubResp>();
        for(UnpfOrdSub uos :unpfOrdSubs){
            RUnpfOrdSubResp rUnpfOrdSubResp = new RUnpfOrdSubResp();
            ObjectCopyUtil.copyObjValue(uos, rUnpfOrdSubResp,null,false);
            rUnpfOrdSubResp.setOrderAmount(Long.parseLong(uos.getOrderAmount()));

            if("1".equals(uos.getSysFlag())){
                GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
                gdsInfoReqDTO.setId(Long.parseLong(uos.getGdsId()));
                gdsInfoReqDTO.setGdsQueryOptions(new GdsOption.GdsQueryOption[] { GdsOption.GdsQueryOption.BASIC,
                        GdsOption.GdsQueryOption.MAINPIC });
                gdsInfoReqDTO.setSkuQuerys(new GdsOption.SkuQueryOption[]{GdsOption.SkuQueryOption.PROP});
                GdsInfoRespDTO gdsInfoRespDTO = gdsInfoQueryRSV.queryGdsInfoByOption(gdsInfoReqDTO);
                if(gdsInfoRespDTO != null){
                    rUnpfOrdSubResp.setUrl(gdsInfoRespDTO.getUrl());
                    //新增制品编号
                    String zpCode = "";
                    if(gdsInfoRespDTO.getSkus()!=null&&gdsInfoRespDTO.getSkus().size()>0){
                    	if(gdsInfoRespDTO.getSkus().get(0).getAllPropMaps()!=null){
                    		GdsPropRespDTO propDto = gdsInfoRespDTO.getSkus().get(0).getAllPropMaps().get(UnpfConstants.ZpProp.PROP_ID);
                    		if(propDto!=null){
                    			if( propDto.getValues()!=null&&propDto.getValues().size()>0){
                    				zpCode = propDto.getValues().get(0).getPropValue();
                    			}
                    		}
                    	}
                    }
                    rUnpfOrdSubResp.setZpCode(zpCode);
                }
            }
            rUnpfOrdSubResps.add(rUnpfOrdSubResp);
        }
        return rUnpfOrdSubResps;
    }

    @Override
    public void saveUnpfOrdSub(RUnpfOrdSubReq rUnpfOrdSubReq) throws BusinessException {
        UnpfOrdSub unpfOrdSub = new UnpfOrdSub();
        ObjectCopyUtil.copyObjValue(rUnpfOrdSubReq,unpfOrdSub,null,false);
        if(StringUtils.isNotEmpty(rUnpfOrdSubReq.getDiscountFee())){
		   Long discountMoney = new Double(Double.parseDouble(rUnpfOrdSubReq.getDiscountFee())*100).longValue();
		   unpfOrdSub.setDiscountMoney(discountMoney);
	    }
	    if(StringUtils.isNotEmpty(rUnpfOrdSubReq.getRealMoney())){
		   Long realMoney = new Double(Double.parseDouble(rUnpfOrdSubReq.getRealMoney())*100).longValue();
		   unpfOrdSub.setRealMoney(realMoney);
	    }
	    if(StringUtils.isNotEmpty(rUnpfOrdSubReq.getOrderMoney())){
		   Long orderMoney = new Double(Double.parseDouble(rUnpfOrdSubReq.getOrderMoney())*100).longValue();
		   unpfOrdSub.setOrderMoney(orderMoney);
	    }
        DateFormat dfmt = new SimpleDateFormat("yyMMdd");
        String nowDate = dfmt.format(new Date());
        unpfOrdSub.setId("UNS" + nowDate + StringUtil.lPad(seqUnpfOrdSub.nextValue().toString(), "0", 8));
        this.unpfOrdSubMapper.insert(unpfOrdSub);
    }

    @Override
    public void updateUnpfOrdSub(RUnpfOrdSubReq rUnpfOrdSubReq) throws BusinessException {
        UnpfOrdSub unpfOrdSub = new UnpfOrdSub();
        ObjectCopyUtil.copyObjValue(rUnpfOrdSubReq,unpfOrdSub,null,false);
        unpfOrdSub.setUpdateTime(DateUtil.getSysDate());
        UnpfOrdSubCriteria uosc = new UnpfOrdSubCriteria();
        uosc.createCriteria().andIdEqualTo(rUnpfOrdSubReq.getId()).andOrderIdEqualTo(rUnpfOrdSubReq.getOrderId());
        this.unpfOrdSubMapper.updateByExampleSelective(unpfOrdSub,uosc);
    }

    @Override
    public RUnpfOrdSubResp queryUnpfOrdSubIn(RUnpfOrdSubReq rUnpfOrdSubReq) throws BusinessException {
        UnpfOrdSubCriteria uosc = new UnpfOrdSubCriteria();
        UnpfOrdSubCriteria.Criteria ca = uosc.createCriteria();
        if(StringUtil.isNotBlank(rUnpfOrdSubReq.getId()) ){
            ca.andIdEqualTo(rUnpfOrdSubReq.getId());
        }
        if(StringUtil.isNotBlank(rUnpfOrdSubReq.getOuterSubId())){
            ca.andOuterSubIdEqualTo(rUnpfOrdSubReq.getOuterSubId());
        }
        if(StringUtil.isNotBlank(rUnpfOrdSubReq.getPlatType())){
            ca.andPlatTypeEqualTo(rUnpfOrdSubReq.getPlatType());
        }
        List<UnpfOrdSub> unpfOrdSubs = unpfOrdSubMapper.selectByExample(uosc);
        if(CollectionUtils.isEmpty(unpfOrdSubs)){
            return  null;
        }
        RUnpfOrdSubResp rUnpfOrdSubResp = new RUnpfOrdSubResp();
        ObjectCopyUtil.copyObjValue(unpfOrdSubs.get(0), rUnpfOrdSubResp,null,false);
        return rUnpfOrdSubResp;
    }
}
