package com.zengshi.ecp.coupon.dubbo.impl.convert;

import javax.annotation.Resource;

import com.zengshi.ecp.coupon.dao.model.CoupConsume;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupConsumeRespDTO;
import com.zengshi.ecp.coupon.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.coupon.dubbo.impl.populator.impl.AbstractConverter;
import com.zengshi.ecp.coupon.service.util.CoupSiteUtil;
import com.zengshi.ecp.coupon.service.util.CoupUtil;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.paas.utils.StringUtil;

/**
 */
public class CoupConsumeDTOConverter extends AbstractConverter<CoupConsume,CoupConsumeRespDTO> {
	@Resource(name="shopInfoRSV")
    private IShopInfoRSV shopInfoRSV;
	@Override
    public void populate(CoupConsume coupConsume,CoupConsumeRespDTO coupConsumeRespDTO) {
        ConversionHelper.copyProperties(coupConsume, coupConsumeRespDTO,null,false);
        
        if (!StringUtil.isEmpty(coupConsumeRespDTO.getSiteId())) {
			if (StringUtil.isEmpty(coupConsumeRespDTO.getSiteName())) {
				// siteName 根据siteId 获得展示
				coupConsumeRespDTO.setSiteName(CoupSiteUtil.getSiteInfo(
						coupConsumeRespDTO.getSiteId()).getSiteName());
			}
		}
        if (CoupUtil.isCoupNotLong(coupConsumeRespDTO.getShopId())) {
    		ShopInfoResDTO shopInfoRespDTO = shopInfoRSV
    				.findShopInfoByShopID(coupConsumeRespDTO.getShopId());
    		if (shopInfoRespDTO != null) {
    			coupConsumeRespDTO.setShopName(shopInfoRespDTO.getShopName());
    		}
    	}
	}
    
    
}
