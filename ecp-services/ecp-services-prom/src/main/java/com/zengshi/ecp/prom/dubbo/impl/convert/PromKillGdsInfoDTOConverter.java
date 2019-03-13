package com.zengshi.ecp.prom.dubbo.impl.convert;

import java.math.BigDecimal;
import java.sql.Date;

import javax.annotation.Resource;

import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.common.LongReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoExternalRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.prom.dao.model.PromSku;
import com.zengshi.ecp.prom.dubbo.dto.KillGdsInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuPriceRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromStockLimitDTO;
import com.zengshi.ecp.prom.dubbo.impl.populator.impl.AbstractConverter;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromUtilRSV;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromQuerySV;
import com.zengshi.paas.utils.FileUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 */
public class PromKillGdsInfoDTOConverter extends
        AbstractConverter<PromSku, KillGdsInfoDTO> {
	
	    @Resource
	    private IPromQuerySV promQuerySV;
	    
	    @Resource
	    private IGdsSkuInfoQueryRSV gdsSkuInfoQueryRSV;
	    
	    @Resource
	    private IGdsInfoExternalRSV gdsInfoExternalRSV;
	    
	    @Resource
	    private IPromUtilRSV promUtilRSV;
    @Override
    public void populate(PromSku promSku, KillGdsInfoDTO killGdsInfoDTO) {
	//商品查询选项
	SkuQueryOption[] skuQueryOptions = new SkuQueryOption[] { SkuQueryOption.BASIC, SkuQueryOption.MAINPIC,SkuQueryOption.PROP};
	PromRuleCheckDTO promRuleCheckDTO = new PromRuleCheckDTO();
	
	GdsSkuInfoReqDTO skuInfoReqDTO = new GdsSkuInfoReqDTO();
	skuInfoReqDTO.setId(promSku.getSkuId());
	skuInfoReqDTO.setGdsId(promSku.getGdsId());
	skuInfoReqDTO.setStatus(promSku.getStatus());
	skuInfoReqDTO.setSkuQuery(skuQueryOptions);
	GdsSkuInfoRespDTO gdsSkuInfoRespDTO = gdsSkuInfoQueryRSV.querySkuInfoByOptions(skuInfoReqDTO);
	if(StringUtil.isNotEmpty(gdsSkuInfoRespDTO)){
		promRuleCheckDTO.setBasePrice(gdsSkuInfoRespDTO.getRealPrice());
		promRuleCheckDTO.setBuyPrice(gdsSkuInfoRespDTO.getDiscountPrice());
		promRuleCheckDTO.setGdsId(gdsSkuInfoRespDTO.getGdsId());
		promRuleCheckDTO.setSkuId(gdsSkuInfoRespDTO.getId());
		promRuleCheckDTO.setCalDate(new Date(System.currentTimeMillis()));
		promRuleCheckDTO.setShopId(gdsSkuInfoRespDTO.getShopId());
		promRuleCheckDTO.setGdsName(gdsSkuInfoRespDTO.getGdsName());
		promRuleCheckDTO.setStaff(promRuleCheckDTO.getStaff());
		promRuleCheckDTO.setStaffId(promRuleCheckDTO.getStaffId());
		promRuleCheckDTO.setIfCalPrice(Boolean.TRUE);
		
		//计算价格
		PromInfoDTO promInfoDTO =new PromInfoDTO();
		promInfoDTO.setId(promSku.getPromId());
		PromSkuPriceRespDTO priceDTO=promQuerySV.calSkuPriceByPromId(promInfoDTO,promRuleCheckDTO);
		//封装价格
		killGdsInfoDTO.setBasePrice(priceDTO.getDiscountCaclPrice().longValue());//真实价
		killGdsInfoDTO.setBuyPrice(priceDTO.getDiscountFinalPrice().longValue());//购买价
		PromStockLimitDTO stocklimit = promUtilRSV.queryPromStockLimit(String.valueOf(promSku.getPromId()),String.valueOf(promSku.getSkuId()));
		LongReqDTO  id = new LongReqDTO ();
		id.setId(gdsSkuInfoRespDTO.getGdsTypeId());
		killGdsInfoDTO.setGdsTypeFlag(gdsInfoExternalRSV.isNeedStockAmount(id));
		if("11".equals(gdsSkuInfoRespDTO.getGdsStatus())){
			killGdsInfoDTO.setBuyCnt(stocklimit.getBuyCnt());
			killGdsInfoDTO.setPercent(new BigDecimal(stocklimit.getBuyCnt()).multiply(new BigDecimal(100)).divide(new BigDecimal(promSku.getPromCnt()),2, BigDecimal.ROUND_HALF_EVEN));
			if(stocklimit.getBuyCnt()<(promSku.getPromCnt())){
				killGdsInfoDTO.setIfSell("1");//1:表示秒杀商品数量还有剩余  0表示秒杀商品数量为零
			}
		}else{
			killGdsInfoDTO.setPercent(new BigDecimal(100));
			killGdsInfoDTO.setBuyCnt(promSku.getPromCnt());
		}
		//封装秒杀商品展示字段
		killGdsInfoDTO.setKillPrice(priceDTO.getDiscountFinalPrice().longValue());//秒杀价
		killGdsInfoDTO.setPromCnt(promSku.getPromCnt());//促销总个数
		killGdsInfoDTO.setSkuId(promSku.getSkuId());//单品Id
		killGdsInfoDTO.setGdsId(promSku.getGdsId());//商品Id
		if(StringUtil.isNotBlank(gdsSkuInfoRespDTO.getGdsDesc())){
			killGdsInfoDTO.setGdsDesc(FileUtil.readFile2Text(gdsSkuInfoRespDTO.getGdsDesc(), null));//商品描述
		}else{
			if(gdsSkuInfoRespDTO.getAllPropMaps()!=null){
				if(!StringUtil.isEmpty(gdsSkuInfoRespDTO.getAllPropMaps().get("1020"))){
					if(!CollectionUtils.isEmpty(gdsSkuInfoRespDTO.getAllPropMaps().get("1020").getValues())||gdsSkuInfoRespDTO.getAllPropMaps().get("1020").getValues().size()!=0){
						killGdsInfoDTO.setGdsDesc(FileUtil.readFile2Text(gdsSkuInfoRespDTO.getAllPropMaps().get("1020").getValues().get(0).getPropValue(), null));//商品详情
					}
				}
			}
		}
		if(StringUtil.isNotEmpty(gdsSkuInfoRespDTO.getMainPic())){
			killGdsInfoDTO.setMediaUuid(gdsSkuInfoRespDTO.getMainPic().getMediaUuid());
			killGdsInfoDTO.setURL(gdsSkuInfoRespDTO.getMainPic().getURL());
		}
		killGdsInfoDTO.setGdsName(gdsSkuInfoRespDTO.getGdsName());
		killGdsInfoDTO.setDetailURL(gdsSkuInfoRespDTO.getUrl());//商品详情页地址
	}
    
	}
}
