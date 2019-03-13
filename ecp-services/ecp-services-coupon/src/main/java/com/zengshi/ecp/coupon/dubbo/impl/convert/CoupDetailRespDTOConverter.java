package com.zengshi.ecp.coupon.dubbo.impl.convert;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.zengshi.ecp.coupon.dao.model.CoupDetail;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupFullLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupFullLimitRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupMeRespDTO;
import com.zengshi.ecp.coupon.dubbo.impl.converter.ConversionHelper;
import com.zengshi.ecp.coupon.dubbo.impl.populator.impl.AbstractConverter;
import com.zengshi.ecp.coupon.dubbo.util.CouponConstants;
import com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupFullLimitSV;
import com.zengshi.ecp.coupon.service.util.CoupSiteUtil;
import com.zengshi.ecp.coupon.service.util.CoupUtil;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-coupon-server <br>
 * Description: <br>
 * Date:2015-10-31下午2:43:35  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huanghe5
 * @version  
 * @since JDK 1.7
 */
public class CoupDetailRespDTOConverter extends AbstractConverter<CoupDetail, CoupMeRespDTO> {
    
	@Resource(name="shopInfoRSV")
    private IShopInfoRSV shopInfoRSV;
	
	@Resource
    private ICoupFullLimitSV coupFullLimitSV;
	
	@Override
    public void populate(CoupDetail coupDetail, CoupMeRespDTO coupMeRespDTO) {
        
        ConversionHelper.copyProperties(coupDetail, coupMeRespDTO,null,false);
        
        if (!StringUtil.isEmpty(coupMeRespDTO.getSiteId())) {
			if (StringUtil.isEmpty(coupMeRespDTO.getSiteName())) {
				// siteName 根据siteId 获得展示
				coupMeRespDTO.setSiteName(CoupSiteUtil.getSiteInfo(
						coupMeRespDTO.getSiteId()).getSiteName());
			}
		}
		if (CoupUtil.isCoupNotLong(coupMeRespDTO.getShopId())) {
			ShopInfoResDTO shopInfoRespDTO = shopInfoRSV
					.findShopInfoByShopID(coupMeRespDTO.getShopId());
			if (shopInfoRespDTO != null) {
				coupMeRespDTO.setShopName(shopInfoRespDTO.getShopName());
			}
		}
		//优惠券展示条件
		if(StringUtil.isNotEmpty(coupMeRespDTO.getUseRuleCode())){
			Map<String, Object> map = (Map<String, Object>) JSON
					.parse(coupMeRespDTO.getUseRuleCode());
			if(map!=null){
				//140:满减  110:品类 130:黑名单
				if(map.get(CouponConstants.CoupDetail.RLUE_140) != null){
					if(CoupUtil.isCoupNotLong(coupMeRespDTO.getCoupId())){
						CoupFullLimitReqDTO coupFullLimitReqDTO = new CoupFullLimitReqDTO();
						coupFullLimitReqDTO.setCoupId(coupMeRespDTO.getCoupId());
						List<CoupFullLimitRespDTO> coupFullBeans= coupFullLimitSV.queryCoupFullList(coupFullLimitReqDTO);
						if(CollectionUtils.isNotEmpty(coupFullBeans)){
							for (CoupFullLimitRespDTO coupFullLimitRespDTO : coupFullBeans) {
								if (CouponConstants.CoupFullLimit.TYPE_1
										.equals(coupFullLimitRespDTO.getType())) {
									if(coupFullLimitRespDTO.getSumLimit()!=null){
										coupMeRespDTO.setConditions(CouponConstants.CoupDetail.RLUE_140_EXPLAIN+coupFullLimitRespDTO.getSumLimit().longValue()/100.00+"元使用");
									}
								}else {
									coupMeRespDTO.setConditions(CouponConstants.CoupDetail.RLUE_140_EXPLAIN+coupFullLimitRespDTO.getAmount()+"件使用");
								}
							}
						}
					}
				}else if(map.get(CouponConstants.CoupDetail.RLUE_110) != null||map.get(CouponConstants.CoupDetail.RLUE_130) != null){
					coupMeRespDTO.setConditions(CouponConstants.CoupDetail.RLUE_110130_EXPLAIN);
				}else{
					coupMeRespDTO.setConditions(CouponConstants.CoupDetail.RLUE_EXPLAIN);
				}
			}
		}
		
		try {
			if (coupMeRespDTO.getActiveTime() != null && coupMeRespDTO.getInactiveTime() != null) {
				coupMeRespDTO.setCoupStatus(CouponConstants.CoupDetail.coupStatus_0);
				int i = CoupUtil.daysBetween(coupMeRespDTO.getActiveTime(), DateUtil.getSysDate());
				if (i < 3) {
					coupMeRespDTO.setCoupStatus(CouponConstants.CoupDetail.coupStatus_1);
				}
				int j = CoupUtil.daysBetween(DateUtil.getSysDate(),coupMeRespDTO.getInactiveTime());
				if(j < 4){
					coupMeRespDTO.setCoupStatus(CouponConstants.CoupDetail.coupStatus_2);
				}
				//已使用
				if(CouponConstants.CoupDetail.IF_USE_1.equals(coupDetail.getIfUse())){
					coupMeRespDTO.setCoupStatus(CouponConstants.CoupDetail.coupStatus_3);
				}
				//已过期
				if (CoupUtil.timestampToDate(DateUtil.getSysDate()).getTime() > coupMeRespDTO.getInactiveTime().getTime()) {
					coupMeRespDTO.setCoupStatus(CouponConstants.CoupDetail.coupStatus_3);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
