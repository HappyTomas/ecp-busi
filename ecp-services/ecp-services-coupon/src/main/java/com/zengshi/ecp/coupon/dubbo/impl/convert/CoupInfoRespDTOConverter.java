package com.zengshi.ecp.coupon.dubbo.impl.convert;

import javax.annotation.Resource;

import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.coupon.dao.mapper.common.CoupTypeMapper;
import com.zengshi.ecp.coupon.dao.model.CoupInfo;
import com.zengshi.ecp.coupon.dao.model.CoupType;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupInfoRespDTO;
import com.zengshi.ecp.coupon.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.coupon.dubbo.impl.populator.impl.AbstractConverter;
import com.zengshi.ecp.coupon.dubbo.util.CouponConstants;
import com.zengshi.ecp.coupon.service.util.CoupSiteUtil;
import com.zengshi.ecp.coupon.service.util.CoupUtil;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.paas.utils.StringUtil;

/**
 */
public class CoupInfoRespDTOConverter extends AbstractConverter<CoupInfo, CoupInfoRespDTO> {
    
    @Resource(name="shopInfoRSV")
    private IShopInfoRSV shopInfoRSV;
    
    @Resource
    private CoupTypeMapper coupTypeMapper;
    
	@Override
    public void populate(CoupInfo coupInfo, CoupInfoRespDTO coupInfoRespDTO) {
        ConversionHelper.copyProperties(coupInfo, coupInfoRespDTO,null,false);
		if (!StringUtil.isEmpty(coupInfoRespDTO.getSiteId())) {
			if (StringUtil.isEmpty(coupInfoRespDTO.getSiteName())) {
				// siteName 根据siteId 获得展示
				CmsSiteRespDTO cmsSiteRespDTO = CoupSiteUtil.getSiteInfo(
						coupInfoRespDTO.getSiteId());
				if(cmsSiteRespDTO != null){
					coupInfoRespDTO.setSiteName(cmsSiteRespDTO.getSiteName());
				}
			}
		}
		
		if(CoupUtil.isCoupNotLong(coupInfo.getCoupTypeId())){
			if (StringUtil.isEmpty(coupInfoRespDTO.getCoupTypeName())) {
				CoupType coupType =coupTypeMapper.selectByPrimaryKey(coupInfo.getCoupTypeId());
//				CoupTypeRespDTO coupTypeRespDTO = CoupCacheUtil.getCoupTypeCache(coupInfo.getCoupTypeId());
				if(coupType != null){
					coupInfoRespDTO.setCoupTypeName(coupType.getCoupTypeName());
				}
			}
		}
		
		if (CoupUtil.isCoupNotLong(coupInfo.getShopId())&&coupInfo.getShopId()>0) {
			ShopInfoResDTO shopInfoRespDTO = shopInfoRSV.findShopInfoByShopID(coupInfo.getShopId());
			if (shopInfoRespDTO != null) {
				coupInfoRespDTO.setShopName(shopInfoRespDTO.getShopName());
			}
		}

		if (!StringUtil.isEmpty(coupInfoRespDTO.getStatus())) {
			if (StringUtil.isEmpty(coupInfoRespDTO.getStatusName())) {
				coupInfoRespDTO.setStatusName(BaseParamUtil.fetchParamValue(
						CouponConstants.CoupKey.COUP_INFO_STATUS,
						coupInfoRespDTO.getStatus()));
			}
		}
        
		if (!StringUtil.isEmpty(coupInfoRespDTO.getIfBack())) {
			if (StringUtil.isEmpty(coupInfoRespDTO.getIfBackName())) {
				coupInfoRespDTO.setIfBackName(BaseParamUtil.fetchParamValue(
						CouponConstants.CoupKey.COUP_IF_BACK,coupInfoRespDTO.getIfBack()));
			}
		}
		
		if (!StringUtil.isEmpty(coupInfoRespDTO.getIfCode())) {
			if (StringUtil.isEmpty(coupInfoRespDTO.getIfCodeName())) {
				coupInfoRespDTO.setIfCodeName(BaseParamUtil.fetchParamValue(
						CouponConstants.CoupKey.COUP_IF_CODE,coupInfoRespDTO.getIfCode()));
			}
		}
		
		if (!StringUtil.isEmpty(coupInfoRespDTO.getEffType())){
			if (StringUtil.isEmpty(coupInfoRespDTO.getEffTypeName())) {
				coupInfoRespDTO.setEffTypeName(BaseParamUtil.fetchParamValue(
						CouponConstants.CoupKey.COUP_EFF_TYPE,coupInfoRespDTO.getEffType()));
			}
		}
    }
}
