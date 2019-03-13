package com.zengshi.ecp.unpf.dubbo.impl.stock;

import com.zengshi.ecp.aip.third.dubbo.dto.req.BaseShopAuthReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.req.GdsStockThirdReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.req.SkuStockThirdReqDTO;
import com.zengshi.ecp.aip.third.dubbo.interfaces.IStockRSV;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.category.GdsCategoryCompareReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.category.GdsCategoryCompareRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.unpf.dao.model.UnpfGdsSend;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfGdsCatgLimitReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfGdsCatgLimitRespDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfGdsLimitReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.auth.UnpfShopAuthReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.auth.UnpfShopAuthRespDTO;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfGdsSendReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.stock.GdsStockReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.stock.SkuStockReqDTO;
import com.zengshi.ecp.unpf.dubbo.interfaces.IUnpfShopStockLimitRSV;
import com.zengshi.ecp.unpf.dubbo.interfaces.stock.IUnpfStockRSV;
import com.zengshi.ecp.unpf.dubbo.util.UnpfConstants;
import com.zengshi.ecp.unpf.service.busi.auth.interfaces.IUnpfShopAuthSV;
import com.zengshi.ecp.unpf.service.busi.good.send.interfaces.IUnpfGoodSendSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;



/**
* @author  huangjx: 
* @date 创建时间：2016年11月10日 上午9:10:38 
* @version 1.0 
* Copyright (c) 2016 AI <br>
* */ 
public class UnpfStockRSVImpl implements IUnpfStockRSV {

	private final String MODULE = getClass().getName();
	
	 @Resource
	 private IStockRSV stockRSV;
	 
	 @Resource
	 private IUnpfGoodSendSV unpfGoodSendSV;
	 
	 @Resource
	 private IUnpfShopAuthSV unpfShopAuthSV;

	@Resource
	private IUnpfShopStockLimitRSV unpfShopsTockLimitRSV;

	@Resource
	private IGdsInfoQueryRSV gdsInfoQueryRSV;

	@Resource
	private IGdsCategoryRSV gdsCategoryRSV;

	@Override
	public void updateStock(GdsStockReqDTO gdsStockReqDTO) throws BusinessException{
		
		if(gdsStockReqDTO==null){
			throw new BusinessException("unpf.100026");
		}
		if(gdsStockReqDTO.getGdsId()==null){
			throw new BusinessException("unpf.100027");
		}
		if(gdsStockReqDTO.getShopId()==null){
			throw new BusinessException("unpf.100028");
		}
		if(gdsStockReqDTO.getQuanties()==null){
			throw new BusinessException("unpf.100029");
		}
		
		//推送各个平台库存量变化
		UnpfGdsSendReqDTO unpfGdsSendReqDTO=new UnpfGdsSendReqDTO();
		unpfGdsSendReqDTO.setPlatType(gdsStockReqDTO.getPlatType());
		unpfGdsSendReqDTO.setGdsId(gdsStockReqDTO.getGdsId());
		unpfGdsSendReqDTO.setShopId(gdsStockReqDTO.getShopId());
		
		List<UnpfGdsSend> sends=unpfGoodSendSV.querySends(unpfGdsSendReqDTO);
		
		if(!CollectionUtils.isEmpty(sends)){
			for(UnpfGdsSend s:sends){
				if(StringUtils.isNotEmpty(s.getOutGdsId())){

					GdsStockThirdReqDTO gdsStockThirdReqDTO=new GdsStockThirdReqDTO();
					
					//初始化 授权基本信息
					UnpfShopAuthReqDTO q=new UnpfShopAuthReqDTO();
					q.setId(s.getShopAuthId());
					UnpfShopAuthRespDTO  uppfAuth=unpfShopAuthSV.queryPlatType4ShopById(q);

					// 需要验证黑名单服务。如果当前商品或者商品分类属于设置的授权黑名单数据，不能发送，也不清理数据。
					if (isCatgLimit(s.getGdsId(), uppfAuth)) {
						LogUtil.error(MODULE, "gds=:"+s.getGdsId() + " 黑名单分类 不需要推送");
						continue;
					}
					if (isGdsLimit(s.getGdsId(), uppfAuth)) {
						LogUtil.error(MODULE, "gds=:"+s.getGdsId() + " 黑名单商品 不需要推送");
						continue;
					}



					BaseShopAuthReqDTO baseShopAuthReqDTO = new BaseShopAuthReqDTO();

					ObjectCopyUtil.copyObjValue(uppfAuth, baseShopAuthReqDTO,
							null, false);

					baseShopAuthReqDTO.setAuthId(uppfAuth.getId());
					
					ObjectCopyUtil.copyObjValue(baseShopAuthReqDTO, gdsStockThirdReqDTO,
							null, false);
					
					//初始化商品 单品基本信息
					gdsStockThirdReqDTO.setGdsId(s.getGdsId());
					gdsStockThirdReqDTO.setOutGdsId(Long.valueOf(s.getOutGdsId()));
					gdsStockThirdReqDTO.setShopId(s.getShopId());
					gdsStockThirdReqDTO.setPlatType(s.getPlatType());
					
			        Long quanties = 0L;
			        if(!CollectionUtils.isEmpty(gdsStockReqDTO.getSkuInfos())){
						List<SkuStockThirdReqDTO> skuInfos=new ArrayList<SkuStockThirdReqDTO>();
						for(SkuStockReqDTO sku:gdsStockReqDTO.getSkuInfos()){
							SkuStockThirdReqDTO skuThird=new SkuStockThirdReqDTO();
							ObjectCopyUtil.copyObjValue(sku, skuThird,
									null, false);
							skuThird.setQuanties(getResultStock(sku.getQuanties()));
							quanties += skuThird.getQuanties();
							skuInfos.add(skuThird);
						}
						gdsStockThirdReqDTO.setSkuInfos(skuInfos);
					}
			       // gdsStockThirdReqDTO.setQuanties(getResultStock(gdsStockReqDTO.getQuanties()));			        
			        gdsStockThirdReqDTO.setQuanties(quanties);			        
			        
					//更新库存量
					
					//需要过滤 错误信息？？？？？？？？？？
					try{
				    	stockRSV.updateStock(gdsStockThirdReqDTO);
					}catch(BusinessException ex){
						LogUtil.error(MODULE, ex.toString());
					}catch (Exception e) {
						LogUtil.error(MODULE, e.toString());
					}
				}
				
			}
		}
	}
    /**
     * 获取最终推送库存量
     * @param baseQuanties
     * @return
     */
	private Long getResultStock(Long baseQuanties){
		//如果库存为0或者空，直接返回0
		if(baseQuanties==null || baseQuanties==0){
			return 0l;
		}
        // 获取第三方平台天猫库存推送比例
        BaseSysCfgRespDTO unpfTMStockScale = SysCfgUtil.fetchSysCfg(UnpfConstants.StockLimit.UNPF_TM_STOCK_SCALE);
        // 获取第三方平台天猫库存推送上限
        BaseSysCfgRespDTO unpfTMStockLimit = SysCfgUtil.fetchSysCfg(UnpfConstants.StockLimit.UNPF_TM_STOCK_LIMTI);
        // 如果比例和上限都没配置或者为0，直接返回库存数
        if(StringUtil.isEmpty(unpfTMStockScale) && StringUtil.isEmpty(unpfTMStockLimit)){
			return baseQuanties;
		}		
        BigDecimal result = new BigDecimal(baseQuanties);
        if(StringUtil.isNotEmpty(unpfTMStockScale)){
        	result=result.multiply(new BigDecimal(unpfTMStockScale.getParaValue())).setScale(1, BigDecimal.ROUND_DOWN);
		}
        if(StringUtil.isNotEmpty(unpfTMStockLimit)){
        	if(result.compareTo(new BigDecimal(unpfTMStockLimit.getParaValue()))==1){
        		return new Long(unpfTMStockLimit.getParaValue());
        	}
        }
        return result.longValue();
	}

	// 是否分类黑名单
	private boolean isCatgLimit(Long gdsId, UnpfShopAuthRespDTO unpfGdsSendReqDTO) {

		boolean ifLimit=false;

		UnpfGdsCatgLimitReqDTO catgLimitReqDTO = new UnpfGdsCatgLimitReqDTO();
		catgLimitReqDTO.setPlatType(unpfGdsSendReqDTO.getPlatType());
		catgLimitReqDTO.setShopAuthId(unpfGdsSendReqDTO.getId());
		catgLimitReqDTO.setShopId(unpfGdsSendReqDTO.getShopId());
		catgLimitReqDTO.setStatus("1");
		catgLimitReqDTO.setPageSize(10);
		//取分页数值
		PageResponseDTO<UnpfGdsCatgLimitRespDTO> page=unpfShopsTockLimitRSV.queryCatgLimitPage(catgLimitReqDTO);

		if(page!=null && com.alibaba.dubbo.common.utils.CollectionUtils.isNotEmpty(page.getResult())){

			//调用接口 获得gds 当前分类
			GdsInfoReqDTO dto=new GdsInfoReqDTO();
			dto.setId(gdsId);
			GdsOption.GdsQueryOption[] gdsQuery=new GdsOption.GdsQueryOption[1];
			gdsQuery[0]=GdsOption.GdsQueryOption.CATG;//分类
			dto.setGdsQueryOptions(gdsQuery);
			GdsInfoRespDTO gdsDTO= gdsInfoQueryRSV.queryGdsInfoByOption(dto);

			List<String> platformCatgList=new ArrayList<String>();

			if(gdsDTO!=null){
				//如果下架 删除等 gdsDTO为null
				if(!com.alibaba.dubbo.common.utils.CollectionUtils.isEmpty(gdsDTO.getPlatformCategory())){
					for(GdsCategoryRespDTO p:gdsDTO.getPlatformCategory()){
						platformCatgList.add(p.getCatgCode());
					}
				}
			}

			long count=page.getPageCount();
			//获得每个黑名单分类和商品分类比较
			for(int pNo=1;pNo<=count;pNo++){
				catgLimitReqDTO.setPageNo(pNo);
				PageResponseDTO<UnpfGdsCatgLimitRespDTO>  pageTemp=unpfShopsTockLimitRSV.queryCatgLimitPage(catgLimitReqDTO);

				if(pageTemp!=null && com.alibaba.dubbo.common.utils.CollectionUtils.isNotEmpty(pageTemp.getResult())){
					for(UnpfGdsCatgLimitRespDTO r:pageTemp.getResult()){
						//调用接口 获得 当前分类下子节点
						Integer result=  this.compareCatg(platformCatgList,r.getCatgCode());
						if(GdsCategoryCompareRespDTO.RESULT_EQUAL.equals(result) || GdsCategoryCompareRespDTO.RESULT_GREAT_THAN.equals(result) ){
							//返回结果关系
							ifLimit=true;
							break;
						}
					}
				}
			}

			return ifLimit;

		}
		return ifLimit;
	}

	// 是否商品黑名单
	private boolean isGdsLimit(Long gdsId, UnpfShopAuthRespDTO unpfGdsSendReqDTO) {

		UnpfGdsLimitReqDTO gdsLimitReqDTO = new UnpfGdsLimitReqDTO();
		gdsLimitReqDTO.setGdsId(gdsId);
		gdsLimitReqDTO.setLimitType("2");
		gdsLimitReqDTO.setPlatType(unpfGdsSendReqDTO.getPlatType());
		gdsLimitReqDTO.setShopAuthId(unpfGdsSendReqDTO.getId());
		gdsLimitReqDTO.setShopId(unpfGdsSendReqDTO.getShopId());
		gdsLimitReqDTO.setStatus("1");
		if (unpfShopsTockLimitRSV.checkLimitExits(gdsLimitReqDTO)) {
			return true;
		}
		return false;

	}

	/**
	 * 比较分类
	 * @param sourceCatg
	 * @param targetCatg
	 * @return
	 * @throws BusinessException
	 * @author huangjx
	 */
	private Integer compareCatg(String sourceCatg,String targetCatg) throws BusinessException {

		if(StringUtil.isEmpty(sourceCatg) || StringUtil.isEmpty(targetCatg)) {
			return GdsCategoryCompareRespDTO.RESULT_ERROR;
		}
		GdsCategoryCompareReqDTO compareReqDTO= new GdsCategoryCompareReqDTO();
		compareReqDTO.setSourceCode(sourceCatg);
		compareReqDTO.setTargetCode(targetCatg);
		GdsCategoryCompareRespDTO dto=gdsCategoryRSV.executeCompare(compareReqDTO);
		return dto.getResult();
	}

	/**
	 * 源分类集合 和目标分类比较
	 * @param sourceCatgs
	 * @param targetCatg
	 * @return
	 * @author huangjx
	 */
	private Integer compareCatg(List<String> sourceCatgs,String targetCatg){

		Integer result=GdsCategoryCompareRespDTO.RESULT_ERROR;
		if(com.alibaba.dubbo.common.utils.CollectionUtils.isEmpty(sourceCatgs) || StringUtil.isEmpty(targetCatg)) {
			return result;
		}
		//验证比较
		for(String sourceCatg:sourceCatgs){
			result=this.compareCatg(sourceCatg, targetCatg);
			if(GdsCategoryCompareRespDTO.RESULT_EQUAL.equals(result) || GdsCategoryCompareRespDTO.RESULT_GREAT_THAN.equals(result) ){
				break;
			}
		}
		return result;
	}
}


