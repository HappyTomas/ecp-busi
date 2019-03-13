package com.zengshi.ecp.goods.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.goods.dubbo.dto.GdsCollectReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.common.LongRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCollectCntRSV;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsCollectCntSV;

public class GdsCollectCntRSVImpl extends AbstractRSVImpl implements IGdsCollectCntRSV {
	
	@Resource
	private IGdsCollectCntSV gdsCollectCntSV;

	@Override
	public LongRespDTO executeCount(GdsCollectReqDTO reqDTO) {
		return gdsCollectCntSV.executeCount(reqDTO);
	}
	
   
	

}

