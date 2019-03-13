/** 
 * Project Name:ecp-services-goods-server 
 * File Name:GdsCollectCntSVImpl.java 
 * Package Name:com.zengshi.ecp.goods.service.busi.impl 
 * Date:2016-1-14上午10:24:56 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.service.busi.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.goods.dao.mapper.busi.GdsCollectCntMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.manual.GdsCollectCntExtraMapper;
import com.zengshi.ecp.goods.dao.model.GdsCollectCnt;
import com.zengshi.ecp.goods.dao.model.GdsCollectCntCriteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsDataImportConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCollectReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCollectRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.common.LongRespDTO;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsCollectCntSV;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsCollectSV;
import com.zengshi.ecp.goods.service.common.impl.AbstractSVImpl;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods-server <br>
 * Description:商品信息收藏量服务. <br>
 * Date:2016-1-14上午10:24:56  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public class GdsCollectCntSVImpl extends AbstractSVImpl implements
		IGdsCollectCntSV {
	@Resource
	private GdsCollectCntMapper gdsCollectCntMapper;
	
	@Resource
	private GdsCollectCntExtraMapper gdsCollectCntExtraMapper;
	@Resource
	private IGdsCollectSV gdsCollectSV;

	/** 
	 * TODO 简单描述该方法的实现功能（可选）. 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsCollectCntSV#addGdsCollectCnt(com.zengshi.ecp.goods.dubbo.dto.GdsCollectReqDTO) 
	 */
	@Override
	public void addGdsCollectCnt(GdsCollectReqDTO reqDTO) throws BusinessException {
		GdsCollectCnt record = new GdsCollectCnt();
		record.setSkuId(reqDTO.getSkuId());
		record.setGdsId(reqDTO.getGdsId());
		record.setGdsName(reqDTO.getGdsName());
		record.setShopId(reqDTO.getShopId());
		record.setStatus(GdsConstants.Commons.STATUS_VALID);
		record.setCreateTime(now());
		record.setUpdateTime(record.getCreateTime());
        record.setCollAmount(1L);
        record.setCreateStaff(reqDTO.getStaffId());
        record.setUpdateStaff(reqDTO.getStaffId());
        gdsCollectCntMapper.insertSelective(record);        
	}

	
	/** 
	 * TODO 简单描述该方法的实现功能（可选）. 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsCollectCntSV#updateGdsCollectCntByExample(com.zengshi.ecp.goods.dubbo.dto.GdsCollectReqDTO) 
	 */
	@Override
	public void executeGdsCollectCntInc(GdsCollectReqDTO reqDTO) {
		paramCheck(new Object[]{reqDTO.getSkuId(),reqDTO.getGdsId()}, new String[]{"reqDTO.skuId","reqDTO.gdsId"});
		GdsCollectCntCriteria gdsCollectCntCriteria = new GdsCollectCntCriteria();
		GdsCollectCntCriteria.Criteria c = gdsCollectCntCriteria.createCriteria();
		c.andSkuIdEqualTo(reqDTO.getSkuId());
		//c.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
		int i = gdsCollectCntExtraMapper.executeGdsCollectCntInc(gdsCollectCntCriteria);
		if( 0 == i){
			addGdsCollectCnt(reqDTO);
		}
	}
	
	@Override
	public void executeGdsCollectCntDec(GdsCollectReqDTO reqDTO) {
		paramCheck(new Object[]{reqDTO.getId()}, new String[]{"reqDTO.id"});
		
		GdsCollectRespDTO gcrd = gdsCollectSV.queryGdsCollectByPK(reqDTO.getId());

		if(null != gcrd){
			GdsCollectCntCriteria gdsCollectCntCriteria = new GdsCollectCntCriteria();
			GdsCollectCntCriteria.Criteria c = gdsCollectCntCriteria.createCriteria();
			c.andSkuIdEqualTo(gcrd.getSkuId());
			c.andShopIdEqualTo(gcrd.getShopId());
			//c.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
			c.andCollAmountGreaterThan(0L);
			gdsCollectCntExtraMapper.executeGdsCollectCntDec(gdsCollectCntCriteria);
		}
		
	}


	@Override
	public LongRespDTO executeCount(GdsCollectReqDTO reqDTO) {
		paramNullCheck(reqDTO, "reqDTO");
		paramCheck(new Object[]{reqDTO.getGdsId(),reqDTO.getShopId()}, new String[]{"reqDTO.gdsId","reqDTO.shopId"});
		LongRespDTO respDTO = new LongRespDTO();
		Long l = 0L;
		GdsCollectCntCriteria gdsCollectCntCriteria = new GdsCollectCntCriteria();
		GdsCollectCntCriteria.Criteria c = gdsCollectCntCriteria.createCriteria();
		c.andGdsIdEqualTo(reqDTO.getGdsId());
		c.andShopIdEqualTo(reqDTO.getShopId());
	    List<GdsCollectCnt> lst = gdsCollectCntMapper.selectByExample(gdsCollectCntCriteria);
	    if(CollectionUtils.isNotEmpty(lst)){
	    	for(GdsCollectCnt cnt : lst){
	    		l += cnt.getCollAmount();
	    	}
	    }
	    respDTO.setValue(l);
		return respDTO;
	}
	
	
	
	
	
	

}

