package com.zengshi.ecp.order.service.busi.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dao.mapper.busi.OrdSubShareMapper;
import com.zengshi.ecp.order.dao.model.OrdSub;
import com.zengshi.ecp.order.dao.model.OrdSubShare;
import com.zengshi.ecp.order.dao.model.OrdSubShareCriteria;
import com.zengshi.ecp.order.dao.model.OrdSubShareCriteria.Criteria;
import com.zengshi.ecp.order.dubbo.dto.OrdSubShareReq;
import com.zengshi.ecp.order.dubbo.dto.OrdSubShareResp;
import com.zengshi.ecp.order.dubbo.dto.SBaseAndSubInfo;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdSubSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdSubShareSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.db.sequence.Sequence;

public class OrdSubShareSVImpl implements IOrdSubShareSV{

	@Resource(name = "seq_ord_sub_share")
	private Sequence seqOrdSubShare;
	
	@Resource
	private OrdSubShareMapper ordSubShareMapper;
	
    @Resource
    private IOrdSubSV ordSubSV;
	 
	@Override
	public void saveOrdSubShare(OrdSubShare ordSubShare) throws  BusinessException {
			ordSubShare.setId(seqOrdSubShare.nextValue());
			ordSubShareMapper.insertSelective(ordSubShare);
	}

	@Override
	public void updateOrdSubShareStatus(OrdSubShareReq req) throws BusinessException {
		// TODO Auto-generated method stub
		OrdSubShare ordSubShare = new OrdSubShare();
		ordSubShare.setStatus(req.getStatus());
		OrdSubShareCriteria example = new OrdSubShareCriteria();
		example.createCriteria().andOrderIdEqualTo(req.getOrderId());
		ordSubShareMapper.updateByExampleSelective(ordSubShare, example);
	}

	@Override
	public List<OrdSubShareResp> queryOrdSubShareList(OrdSubShareReq req) {
		// TODO Auto-generated method stub
		List<OrdSubShareResp> ordSubShareResps = new ArrayList<OrdSubShareResp>();
		OrdSubShareCriteria example = new OrdSubShareCriteria();
		Criteria criteria = example.createCriteria().andOrderIdEqualTo(req.getOrderId());		
		if(StringUtil.isNotBlank(req.getStatus())){
			criteria.andStatusEqualTo(req.getStatus());
		}
		List<OrdSubShare> list = ordSubShareMapper.selectByExample(example);
		if(list!=null){
			for(OrdSubShare ordSubShare:list){
				OrdSubShareResp ordSubShareResp = new OrdSubShareResp();
				ObjectCopyUtil.copyObjValue(ordSubShare, ordSubShareResp, "", false);
				SBaseAndSubInfo sOrderAOrderSubInfo = new SBaseAndSubInfo();
				sOrderAOrderSubInfo.setOrderId(ordSubShare.getOrderId());
				sOrderAOrderSubInfo.setOrderSubId(ordSubShare.getSubOrdId());
				OrdSub ordSub = ordSubSV.findByOrderSubId(sOrderAOrderSubInfo);
				ordSubShareResp.setRealMoney(ordSub.getRealMoney());
				ordSubShareResps.add(ordSubShareResp);
			}
		}
		return ordSubShareResps;
	}

}
