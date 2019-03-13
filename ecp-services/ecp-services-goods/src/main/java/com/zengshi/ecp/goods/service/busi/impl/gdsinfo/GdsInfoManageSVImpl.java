package com.zengshi.ecp.goods.service.busi.impl.gdsinfo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsInfoMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsVerifyShopIdxMapper;
import com.zengshi.ecp.goods.dao.model.GdsCategory;
import com.zengshi.ecp.goods.dao.model.GdsCatg2Prop;
import com.zengshi.ecp.goods.dao.model.GdsGds2Catg;
import com.zengshi.ecp.goods.dao.model.GdsGds2Prop;
import com.zengshi.ecp.goods.dao.model.GdsInfo;
import com.zengshi.ecp.goods.dao.model.GdsInfoCriteria;
import com.zengshi.ecp.goods.dao.model.GdsProp;
import com.zengshi.ecp.goods.dao.model.GdsSkuInfo;
import com.zengshi.ecp.goods.dao.model.GdsVerifyShopIdx;
import com.zengshi.ecp.goods.dao.model.GdsVerifyShopIdxCriteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsGuessYLReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropValueRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsScoreExtReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsScoreExtRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsVerifyReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.common.LongReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoAddReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfoidx.GdsGds2CatgCatgIdxReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2CatgReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2CatgRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2MediaReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2MediaRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2PropRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2PriceReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2PriceRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.price.GdsPriceReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.goods.dubbo.util.GdsCacheUtil;
import com.zengshi.ecp.goods.dubbo.util.GdsMessageUtil;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsMediaSV;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsScoreExtSV;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsStockSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsInfoManageSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsInfoQuerySV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsSkuInfoManageSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsSkuInfoQuerySV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfoidx.IGdsInfoIDXSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfores.IGdsInfo2CatgSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfores.IGdsInfo2MediaSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfores.IGdsInfo2PropSV;
import com.zengshi.ecp.goods.service.busi.interfaces.price.IGdsPriceSV;
import com.zengshi.ecp.goods.service.common.impl.AbstractSVImpl;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsCategorySV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsCatg2PropSV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsGuessYLSV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsPlatRecomSV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsPropSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.StaffLocaleUtil;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.unpf.dubbo.interfaces.gdssend.IUnpfGdsUnsendRSV;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.FileUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

import scala.actors.threadpool.Arrays;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: 商品业务操作(更新，写入) <br>
 * Date:2015年8月25日下午5:20:36 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public class GdsInfoManageSVImpl extends AbstractSVImpl implements IGdsInfoManageSV {

	/**
	 * 商品主表序列
	 */
	@Resource(name = "seq_gds_info")
	private PaasSequence seqGdsInfo;

	/**
	 * 商品主表操作Mapper
	 */
	@Resource
	private GdsInfoMapper gdsInfoMapper;

	/**
	 * 平台分类推荐表
	 */
	@Resource
	private IGdsPlatRecomSV gdsPlatRecomSV;

	/**
	 * 商品操作审核店铺索引维度Mapper
	 */
	@Resource
	private GdsVerifyShopIdxMapper gdsVerifyShopIdxMapper;

	/**
	 * 价格服务
	 */
	@Autowired(required = false)
	private IGdsPriceSV gdsPriceSV;

	/**
	 * 单品管理SV
	 */
	@Resource
	private IGdsSkuInfoManageSV gdsSkuInfoManageSV;

	/**
	 * 单品查询SV
	 */
	@Resource
	private IGdsSkuInfoQuerySV gdsSkuInfoQuerySV;

	/**
	 * 分类属性关系SV
	 */
	@Resource
	private IGdsCatg2PropSV gdsCatg2PropSV;

	/**
	 * 猜你喜欢SV
	 */
	@Resource
	private IGdsGuessYLSV gdsGuessYLSV;

	/**
	 * 商品属性SV
	 */
	@Resource
	private IGdsPropSV gdsPropSV;

	/**
	 * 商品图片SV
	 */
	@Resource
	private IGdsMediaSV gdsMediaSV;

	/**
	 * 商品索引表操作SV
	 */
	@Resource
	private IGdsInfoIDXSV gdsInfoIDXSV;

	/**
	 * 商品查询SV
	 */
	@Resource
	private IGdsInfoQuerySV gdsInfoQuerySV;

	/**
	 * 商品分类关系SV
	 */
	@Resource
	private IGdsInfo2CatgSV gdsInfo2CatgSV;

	/**
	 * 商品图片关系SV
	 */
	@Resource
	private IGdsInfo2MediaSV gdsInfo2MediaSV;

	/**
	 * 商品属性关系SV
	 */
	@Resource
	private IGdsInfo2PropSV gdsInfo2PropSV;

	/**
	 * 商品分类操作SV
	 */
	@Resource
	private IGdsCategorySV gdsCategorySV;

	/**
	 * 商品库存SV
	 */
	@Resource
	private IGdsStockSV gdsStockSV;

	/**
	 * 商品积分SV
	 */
	@Resource
	private IGdsScoreExtSV gdsScoreExtSV;

	/**
	 * 店铺信息SV
	 */
	@Resource
	private IShopInfoRSV shopInfoRSV;
	
	@Resource
	private IUnpfGdsUnsendRSV unpfGdsUnsendRSV;
	
	@Resource
    private IGdsInfoQueryRSV iGdsInfoQueryRSV;

	/**
	 * 
	 * 添加商品.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsInfoManageSV#addGdsInfo(com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoAddReqDTO)
	 */
	@Override
	public GdsInfoRespDTO addGdsInfo(GdsInfoAddReqDTO gdsInfoAddReqDTO) throws BusinessException {
		// 获取当前用户
		Long staffId = gdsInfoAddReqDTO.getStaff().getId();
		GdsInfoReqDTO gdsInfoReqDTO = gdsInfoAddReqDTO.getGdsInfoReqDTO();
		gdsInfoReqDTO.setStaff(gdsInfoAddReqDTO.getStaff());
		// 初始化配置信息
		gdsInfoReqDTO = initGdsIfParam(gdsInfoReqDTO);
		// 封装商品信息
		List<GdsGds2CatgReqDTO> catgs = gdsInfoAddReqDTO.getGds2CatgReqDTOs();
		String platCatgs = getCatgStr(catgs, GdsConstants.GdsCategory.CATG_TYPE_1);
		String shopCatgs = getCatgStr(catgs, GdsConstants.GdsCategory.CATG_TYPE_2);
		gdsInfoReqDTO.setPlatCatgs(platCatgs);
		gdsInfoReqDTO.setShopCatgs(shopCatgs);
		if (gdsInfoReqDTO.getCatlogId() == null && StringUtil.isNotBlank(gdsInfoReqDTO.getMainCatgs())) {
			GdsCategory category = gdsCategorySV.queryGdsCategoryById(gdsInfoReqDTO.getMainCatgs());
			gdsInfoReqDTO.setCatlogId(category.getCatlogId());
		}

		// 保存商品
		GdsInfo gdsInfo = saveGdsInfo(gdsInfoReqDTO);

		// 获取商品编码，店铺编码
		Long gdsId = gdsInfo.getId();
		Long shopId = gdsInfo.getShopId();

		// 积分配置录入
		if (GdsUtils.isEqualsValid(gdsInfoReqDTO.getIfScoreGds())) {
			List<GdsScoreExtReqDTO> scores = gdsInfoAddReqDTO.getGdsScoreExtReqDTOs();
			if (CollectionUtils.isNotEmpty(scores)) {
				for (GdsScoreExtReqDTO gdsScoreExtReqDTO : scores) {
					gdsScoreExtReqDTO.setGdsId(gdsId);
					gdsScoreExtReqDTO.setShopId(shopId);
					gdsScoreExtReqDTO.setStaff(GdsUtils.getStaff(staffId));
					if (gdsScoreExtReqDTO.getScore() == null) {
						gdsScoreExtReqDTO.setScore(0L);
					}
					if (gdsScoreExtReqDTO.getPrice() == null) {
						gdsScoreExtReqDTO.setPrice(0L);
					}
				}
				gdsScoreExtSV.saveGdsScoreExtList(scores);
			}
		}

		// 保存商品分类信息
		if (CollectionUtils.isNotEmpty(catgs)) {
			int count = 0;
			for (GdsGds2CatgReqDTO gds2CatgReqDTO : catgs) {
				// 主分类计数，只能有一个
				if (GdsConstants.GdsInfo.GDS_2_CATG_RTYPE_MAIN.equals(gds2CatgReqDTO.getGds2catgType())) {
					count++;
				}
				gds2CatgReqDTO.setGdsId(gdsId);
				gds2CatgReqDTO.setGdsName(gdsInfoReqDTO.getGdsName());
				
				// 设置catlogId与catgPath
				GdsCategoryReqDTO query = new GdsCategoryReqDTO();
				query.setCatgCode(gds2CatgReqDTO.getCatgCode());
				GdsCategoryRespDTO catgRespDTO = gdsCategorySV.queryGdsCategoryById(query);
				if(null != catgRespDTO){
				    gds2CatgReqDTO.setCatlogId(catgRespDTO.getCatlogId());
				    String cp = getCatgStr(Arrays.asList(new GdsGds2CatgReqDTO[]{gds2CatgReqDTO}), catgRespDTO.getCatgType());
				    gds2CatgReqDTO.setCatgPath(cp);
				}
				//gds2CatgReqDTO.setCatgPath(platCatgs);
				gds2CatgReqDTO.setShopId(shopId);
				gds2CatgReqDTO.setIsbn(gdsInfoReqDTO.getIsbn());
				if(StringUtil.isNotBlank(gdsInfoReqDTO.getExt1())){
					gds2CatgReqDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_OFFLINE);
				}else {
					gds2CatgReqDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_WAITSHELVES);
				}
				
				gds2CatgReqDTO.setStaff(GdsUtils.getStaff(staffId));
				gdsInfo2CatgSV.saveGds2Catg(gds2CatgReqDTO);
			}
			if (count == 0) {
				// 没有主分类
				throw new BusinessException(GdsErrorConstants.GdsInfo.ERROR_GOODS_GDSINFO_210002);
			} else if (count > 1) {
				// 主分类超过一个
				throw new BusinessException(GdsErrorConstants.GdsInfo.ERROR_GOODS_GDSINFO_210003);
			}
		} else {
			// 商品没有所属分类
			throw new BusinessException(GdsErrorConstants.GdsInfo.ERROR_GOODS_GDSINFO_210002);
		}

		// 初始化分类属性配置
		Map<String, GdsCatg2Prop> maps = new HashMap<String, GdsCatg2Prop>();
		initPropConfigMap(gdsInfoReqDTO, maps);

		// 保存普通参数/扩展 属性信息到商品属性关系表
		List<GdsGds2PropReqDTO> props = gdsInfoAddReqDTO.getGds2PropReqDTOs();
		if (CollectionUtils.isNotEmpty(props)) {
			for (GdsGds2PropReqDTO prop : props) {
				prop.setGdsId(gdsId);
				prop.setShopId(shopId);
				if(StringUtil.isNotBlank(gdsInfoReqDTO.getExt1())){
					prop.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_OFFLINE);
				}else {
					prop.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_WAITSHELVES);
				}
				setGds2PropConfig(gdsInfoReqDTO, maps, prop);
				prop.setStaff(GdsUtils.getStaff(staffId));
				gdsInfo2PropSV.saveGds2Prop(prop);
			}
		}

		// 保存单品属性到商品属性关系表
		List<GdsGds2PropReqDTO> skuParams = gdsInfoAddReqDTO.getSkuProps();
		if (CollectionUtils.isNotEmpty(skuParams)) {
			for (GdsGds2PropReqDTO prop : skuParams) {
				prop.setGdsId(gdsId);
				prop.setShopId(shopId);
				if(StringUtil.isNotBlank(gdsInfoReqDTO.getExt1())){
					prop.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_OFFLINE);
				}else {
					prop.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_WAITSHELVES);
				}
				// 如果没有传类型 则默认为规格属性
				if (StringUtil.isBlank(prop.getPropType())) {
					prop.setPropType(GdsConstants.GdsProp.PROP_VALUE_TYPE_1);
				}
				prop.setStaff(GdsUtils.getStaff(staffId));
				setGds2PropConfig(gdsInfoReqDTO, maps, prop);
				gdsInfo2PropSV.saveGds2Prop(prop);
			}
		}

		// 保存商品索引表信息
		List<GdsGds2PropReqDTO> allProps = new ArrayList<GdsGds2PropReqDTO>();
		if (CollectionUtils.isNotEmpty(gdsInfoAddReqDTO.getGds2PropReqDTOs())) {
			allProps.addAll(gdsInfoAddReqDTO.getGds2PropReqDTOs());
		}
		if (CollectionUtils.isNotEmpty(gdsInfoAddReqDTO.getSkuProps())) {
			allProps.addAll(gdsInfoAddReqDTO.getSkuProps());
		}
		gdsInfoIDXSV.addGdsInfoIDX(gdsInfo, catgs, allProps);

		// 保存单品信息
		List<Long> skuIds = saveSkuInfo(gdsInfoAddReqDTO, gdsInfoReqDTO, gdsInfo, gdsId, props);

		// 保存商品级别价格
		List<GdsSku2PriceReqDTO> prices = gdsInfoAddReqDTO.getSku2PriceReqDTOs();
		if (CollectionUtils.isNotEmpty(prices) && GdsUtils.isEqualsValid(gdsInfoAddReqDTO.getGdsInfoReqDTO().getIfLadderPrice())) {
			for (GdsSku2PriceReqDTO gdsSku2PriceReqDTO : prices) {
				gdsSku2PriceReqDTO.setrType(GdsConstants.GdsPrice.GDS_PRICE_RTYPE_GDS);
				gdsSku2PriceReqDTO.setGdsId(gdsId);
				gdsSku2PriceReqDTO.setShopId(shopId);
				if (StringUtil.isEmpty(gdsSku2PriceReqDTO.getPriceTypeCode())) {// 如果价格类型编码为空，则默认为阶梯价
					gdsSku2PriceReqDTO.setPriceTypeCode(GdsConstants.GdsInfo.SKU_PRICE_TYPE_LADDER);
				}
				gdsSku2PriceReqDTO.setStaff(GdsUtils.getStaff(staffId));
				gdsPriceSV.saveGdsSkuPrice(gdsSku2PriceReqDTO);
			}
		}

		// 保存图片信息
		List<GdsGds2MediaReqDTO> pics = gdsInfoAddReqDTO.getGds2MediaReqDTOs();
		if (CollectionUtils.isNotEmpty(pics)) {
			for (GdsGds2MediaReqDTO gds2MediaReqDTO : pics) {
				gds2MediaReqDTO.setGdsId(gdsId);
				gds2MediaReqDTO.setShopId(shopId);
				gds2MediaReqDTO.setStaff(GdsUtils.getStaff(staffId));
				gdsInfo2MediaSV.saveGds2Media(gds2MediaReqDTO);
			}
		}

		GdsInfoRespDTO gdsInfoRespDTO = new GdsInfoRespDTO();
		ObjectCopyUtil.copyObjValue(gdsInfo, gdsInfoRespDTO, null, false);
		gdsInfoRespDTO.setSkuIds(skuIds);
		
		
		// 增加第三方平台统一推送逻辑。
        /*BaseSysCfgRespDTO sysCfg = SysCfgUtil.fetchSysCfg("SYS_GDS_SENDUNPF_SWICH");
		if(sysCfg!=null && "1".equals(sysCfg.getParaValue()) && 
		        StringUtil.isBlank(gdsInfoAddReqDTO.getGdsInfoReqDTO().getExt1())){
		    UnpfGdsUnsendReqDTO unSendReqDTO = new UnpfGdsUnsendReqDTO();
	        unSendReqDTO.setGdsId(gdsInfoRespDTO.getId());
	        unSendReqDTO.setShopId(gdsInfoRespDTO.getShopId());
	        unSendReqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
	        unpfGdsUnsendRSV.saveUnsendGds(unSendReqDTO);
		}*/

		return gdsInfoRespDTO;
	}

	/**
	 * 
	 * saveSkuInfo:(保存单品信息). <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfoAddReqDTO
	 * @param gdsInfoReqDTO
	 * @param gdsInfo
	 * @param gdsId
	 * @param props
	 * @return
	 * @since JDK 1.6
	 */
	private List<Long> saveSkuInfo(GdsInfoAddReqDTO gdsInfoAddReqDTO, GdsInfoReqDTO gdsInfoReqDTO, GdsInfo gdsInfo, Long gdsId, List<GdsGds2PropReqDTO> props) {
		List<Long> skuIds = new ArrayList<Long>();
		List<GdsSkuInfoReqDTO> skus = gdsInfoAddReqDTO.getSkuInfoReqDTOs();
		if (CollectionUtils.isNotEmpty(skus)) {
			for (GdsSkuInfoReqDTO skuInfoReqDTO : skus) {
				ObjectCopyUtil.copyObjValue(gdsInfo, skuInfoReqDTO, null, false);
				skuInfoReqDTO.setGdsId(gdsId);
				skuInfoReqDTO.setStaff(gdsInfoAddReqDTO.getStaff());
				skuInfoReqDTO.setIsbn(gdsInfoReqDTO.getIsbn());
				skuInfoReqDTO.setCompanyId(gdsInfoAddReqDTO.getCompanyId());
				skuInfoReqDTO.setGdsProps(GdsUtils.doConvert(props, GdsSku2PropReqDTO.class));
				skuInfoReqDTO.setStaff(gdsInfoReqDTO.getStaff());
				//传入Ext1参数用于判断，决定保存时商品的状态
				skuInfoReqDTO.setExt1(gdsInfoReqDTO.getExt1());
				long skuId = gdsSkuInfoManageSV.saveGdsSkuInfo(skuInfoReqDTO);
				skuIds.add(skuId);

			}
		} else {
			// 如果没有传入单品列表，则默认创建一个单品
			GdsSkuInfoReqDTO skuInfoReqDTO = new GdsSkuInfoReqDTO();
			ObjectCopyUtil.copyObjValue(gdsInfo, skuInfoReqDTO, null, false);
			skuInfoReqDTO.setGdsId(gdsId);
			skuInfoReqDTO.setIsbn(gdsInfoReqDTO.getIsbn());
			skuInfoReqDTO.setCompanyId(gdsInfoAddReqDTO.getCompanyId());
			skuInfoReqDTO.setSku2PriceReqDTOs(gdsInfoAddReqDTO.getSku2PriceReqDTOs());
			skuInfoReqDTO.setGdsProps(GdsUtils.doConvert(props, GdsSku2PropReqDTO.class));
			skuInfoReqDTO.setStaff(gdsInfoReqDTO.getStaff());
			long skuId = gdsSkuInfoManageSV.saveGdsSkuInfo(skuInfoReqDTO);
			skuIds.add(skuId);
		}
		return skuIds;
	}

	/**
	 * 
	 * initPropConfigMap:(初始化分类，属性Map). <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfoReqDTO
	 * @param maps
	 * @since JDK 1.6
	 */
	private void initPropConfigMap(GdsInfoReqDTO gdsInfoReqDTO, Map<String, GdsCatg2Prop> maps) {
		if (gdsInfoReqDTO.isCopyPropFromConfiged()) {
			GdsCatg2PropReqDTO reqDto = new GdsCatg2PropReqDTO();
			reqDto.setCatgCode(gdsInfoReqDTO.getMainCatgs());
			reqDto.setIfContainTopProp(true);
			reqDto.setIfGdsInputQuery(false);
			List<GdsCatg2Prop> rspDto = gdsCatg2PropSV.queryConfigedPropsWithParents(reqDto);
			if (CollectionUtils.isNotEmpty(rspDto)) {
				for (GdsCatg2Prop gdsCatg2Prop : rspDto) {
					maps.put(gdsCatg2Prop.getPropId().toString(), gdsCatg2Prop);
				}
			}
		}
	}

	/**
	 * 
	 * setGds2PropConfig:(初始化商品，属性关系默认值). <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfoReqDTO
	 * @param maps
	 * @param prop
	 * @since JDK 1.6
	 */
	private void setGds2PropConfig(GdsInfoReqDTO gdsInfoReqDTO, Map<String, GdsCatg2Prop> maps, GdsGds2PropReqDTO prop) {
		if (gdsInfoReqDTO.isCopyPropFromConfiged()) {
			if (prop.getPropId() != null) {
				String propId = prop.getPropId().toString();
				GdsCatg2Prop obj = maps.get(propId);
				if (obj != null) {
					prop.setIfBasic(obj.getIfBasic());
					prop.setIfMust(obj.getIfHaveto());
				}
				// 查询对应的属性信息，补充属性信息
				GdsProp propModel = gdsPropSV.queryGdsPropByPK(prop.getPropId());
				ObjectCopyUtil.copyObjValue(propModel, prop, null, false);
			}
		}
	}

	/**
	 * 
	 * editGdsInfoAndReference:(编辑商品信息以及其附属信息). <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfoAddReqDTO
	 * @since JDK 1.6
	 */
	@Override
	public List<Long> editGdsInfoAndReference(GdsInfoAddReqDTO gdsInfoAddReqDTO) throws BusinessException {
		List<Long> skuIds = new ArrayList<Long>();
		GdsInfoReqDTO gdsInfoReqDTO = gdsInfoAddReqDTO.getGdsInfoReqDTO();
		gdsInfoReqDTO.setStaff(gdsInfoAddReqDTO.getStaff());
		gdsInfoReqDTO.setIsUpdateShipTemplate(true);
		String isbn = gdsInfoReqDTO.getIsbn();
		// 如果是本地编辑，不是导入
		if (gdsInfoAddReqDTO.getIfLocalEdit() != null && "1".equals(gdsInfoAddReqDTO.getIfLocalEdit())) {
			// 获取到变更的商品信息（名称，价格，属性）；
			String gdsChangePropStr = this.getGdsChangePropStr(gdsInfoAddReqDTO);
			gdsInfoReqDTO.setChangePropStr(gdsChangePropStr);
		}else{
			gdsInfoReqDTO.setCopyPropFromConfiged(true);
		}

		// 编辑商品信息
		List<GdsGds2CatgReqDTO> catgs = gdsInfoAddReqDTO.getGds2CatgReqDTOs();
		String platCatgCode = getCatgStr(catgs, GdsConstants.GdsCategory.CATG_TYPE_1);
		String shopCatgCode = getCatgStr(catgs, GdsConstants.GdsCategory.CATG_TYPE_2);
		gdsInfoAddReqDTO.getGdsInfoReqDTO().setPlatCatgs(platCatgCode);
		gdsInfoAddReqDTO.getGdsInfoReqDTO().setShopCatgs(shopCatgCode);
		GdsInfo before = new GdsInfo();
		GdsInfo now = editGdsInfoAtom(gdsInfoReqDTO, before);

		Long staffId = gdsInfoAddReqDTO.getStaff().getId();
		Long gdsId = gdsInfoAddReqDTO.getGdsInfoReqDTO().getId();
		Long shopId = gdsInfoAddReqDTO.getGdsInfoReqDTO().getShopId();
		GdsInfo gdsInfo = new GdsInfo();
		ObjectCopyUtil.copyObjValue(gdsInfoAddReqDTO.getGdsInfoReqDTO(), gdsInfo, null, false);

		// 编辑商品分类信息
		if (CollectionUtils.isNotEmpty(catgs)) {
			delGds2Catg(gdsId, staffId);
			for (GdsGds2CatgReqDTO gds2CatgReqDTO : catgs) {
				gds2CatgReqDTO.setGdsId(gdsId);
				gds2CatgReqDTO.setShopId(shopId);
				gds2CatgReqDTO.setIsbn(isbn);
				gds2CatgReqDTO.setGdsStatus(now.getGdsStatus());
				gds2CatgReqDTO.setStaff(GdsUtils.getStaff(staffId));
				gds2CatgReqDTO.setGdsName(gdsInfoReqDTO.getGdsName());
				// 设置catlogId与catgPath
                GdsCategoryReqDTO query = new GdsCategoryReqDTO();
                query.setCatgCode(gds2CatgReqDTO.getCatgCode());
                GdsCategoryRespDTO catgRespDTO = gdsCategorySV.queryGdsCategoryById(query);
                if(null != catgRespDTO){
                    gds2CatgReqDTO.setCatlogId(catgRespDTO.getCatlogId());
                    String cp = getCatgStr(Arrays.asList(new GdsGds2CatgReqDTO[]{gds2CatgReqDTO}), catgRespDTO.getCatgType());
                    gds2CatgReqDTO.setCatgPath(cp);
                }
				//gds2CatgReqDTO.setCatgPath(platCatgCode);
				gdsInfo2CatgSV.saveGds2Catg(gds2CatgReqDTO);
			}
		}

		// 编辑商品积分信息
		editGdsScoreInfo(gdsInfoAddReqDTO, gdsId, shopId, gdsInfoReqDTO);

		// 保存商品级别价格
		saveGdsPrice(gdsInfoAddReqDTO, gdsId, shopId);

		// 编辑属性信息
		List<GdsGds2PropReqDTO> props = gdsInfoAddReqDTO.getGds2PropReqDTOs();
		List<GdsGds2PropReqDTO> skuParams = gdsInfoAddReqDTO.getSkuProps();
		List<GdsGds2Prop> beforeProps = gdsInfo2PropSV.queryGds2PropsModelByGdsId(now.getId());

		// 对比前后属性，如果不属于本次编辑范围的属性，则保留
		compareProps(props, skuParams, beforeProps);

		// 删除属性关系
		if (CollectionUtils.isNotEmpty(props) || CollectionUtils.isNotEmpty(skuParams)) {
			delGds2Prop(gdsId, staffId);
		}

		// 初始化分类属性配置
		Map<String, GdsCatg2Prop> maps = new HashMap<String, GdsCatg2Prop>();
		initPropConfigMap(gdsInfoReqDTO, maps);

		// 新增商品属性
		if (CollectionUtils.isNotEmpty(props)) {
			for (GdsGds2PropReqDTO prop : props) {
				prop.setGdsId(gdsId);
				prop.setGdsStatus(now.getGdsStatus());
				prop.setShopId(shopId);
				prop.setStaff(GdsUtils.getStaff(staffId));
				setGds2PropConfig(gdsInfoReqDTO, maps, prop);
				gdsInfo2PropSV.saveGds2Prop(prop);
			}
		}
		// 冗余单品属性
		if (CollectionUtils.isNotEmpty(skuParams)) {
			for (GdsGds2PropReqDTO prop : skuParams) {
				prop.setShopId(shopId);
				prop.setGdsId(gdsId);
				prop.setGdsStatus(now.getGdsStatus());
				prop.setStaff(GdsUtils.getStaff(staffId));
				setGds2PropConfig(gdsInfoReqDTO, maps, prop);
				gdsInfo2PropSV.saveGds2Prop(prop);
			}
		}

		// 编辑商品属性，分类索引
		List<GdsGds2PropReqDTO> allProps = new ArrayList<GdsGds2PropReqDTO>();
		if (CollectionUtils.isNotEmpty(props)) {
			allProps.addAll(props);
		}
		if (CollectionUtils.isNotEmpty(skuParams)) {
			allProps.addAll(skuParams);
		}
		gdsInfoIDXSV.editGdsInfoIDX(null, catgs, allProps);

		// 编辑单品信息
		editGdsSkuInfo(gdsInfoAddReqDTO, skuIds, isbn, before, now, gdsId, shopId, gdsInfo, props);

		// 编辑商品图片信息
		List<GdsGds2MediaReqDTO> pics = gdsInfoAddReqDTO.getGds2MediaReqDTOs();
		if (CollectionUtils.isNotEmpty(pics) && gdsInfoReqDTO.isUpdatePic()) {
			delGds2Media(gdsId, staffId);
			for (GdsGds2MediaReqDTO gds2MediaReqDTO : pics) {
				gds2MediaReqDTO.setGdsId(gdsId);
				gds2MediaReqDTO.setShopId(shopId);
				gds2MediaReqDTO.setStaff(GdsUtils.getStaff(staffId));
				gdsInfo2MediaSV.saveGds2Media(gds2MediaReqDTO);
			}
		}
		return skuIds;
	}

	/**
	 * 
	 * saveGdsPrice:(保存商品级价格). <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfoAddReqDTO
	 * @param gdsId
	 * @param shopId
	 * @since JDK 1.6
	 */
	private void saveGdsPrice(GdsInfoAddReqDTO gdsInfoAddReqDTO, Long gdsId, Long shopId) {
		List<GdsSku2PriceReqDTO> prices = gdsInfoAddReqDTO.getSku2PriceReqDTOs();
		if (CollectionUtils.isNotEmpty(prices) && GdsUtils.isEqualsValid(gdsInfoAddReqDTO.getGdsInfoReqDTO().getIfLadderPrice())) {
			int count = 0;
			for (GdsSku2PriceReqDTO gdsSku2PriceReqDTO : prices) {
				gdsSku2PriceReqDTO.setrType(GdsConstants.GdsPrice.GDS_PRICE_RTYPE_GDS);
				gdsSku2PriceReqDTO.setGdsId(gdsId);
				gdsSku2PriceReqDTO.setShopId(shopId);
				if (StringUtil.isEmpty(gdsSku2PriceReqDTO.getPriceTypeCode())) {// 如果价格类型编码为空，则默认为阶梯价
					gdsSku2PriceReqDTO.setPriceTypeCode(GdsConstants.GdsInfo.SKU_PRICE_TYPE_LADDER);
				}

				if (count == 0) {
					// 先删除
					gdsPriceSV.delAllPrice(gdsSku2PriceReqDTO);
					count++;
				}
				gdsPriceSV.saveGdsSkuPrice(gdsSku2PriceReqDTO);
			}
		}
	}

	/**
	 * 
	 * editGdsSkuInfo:(商品-单品编辑操作). <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfoAddReqDTO
	 * @param skuIds
	 * @param isbn
	 * @param before
	 * @param now
	 * @param gdsId
	 * @param shopId
	 * @param gdsInfo
	 * @param props
	 * @since JDK 1.6
	 */
	private void editGdsSkuInfo(GdsInfoAddReqDTO gdsInfoAddReqDTO, List<Long> skuIds, String isbn, GdsInfo before, GdsInfo now, Long gdsId, Long shopId, GdsInfo gdsInfo, List<GdsGds2PropReqDTO> props) {
		List<Long> beforeSkuIds = gdsInfoQuerySV.querySkuIdsGdsId(gdsId);
		List<GdsSkuInfoReqDTO> skus = gdsInfoAddReqDTO.getSkuInfoReqDTOs();
		if (CollectionUtils.isNotEmpty(skus)) {
		    for (Long skuIdBefore : beforeSkuIds) {
                Long skuId = skuIdBefore.longValue();
                boolean in = false;
                for (GdsSkuInfoReqDTO skuInfoReqDTO : skus) {
                    if (skuInfoReqDTO.getId() != null && skuInfoReqDTO.getId().longValue() == skuId.longValue()) {
                        in = true;
                    }
                }
                if (!in) {
                    GdsSkuInfoReqDTO gdsSkuInfoReqDTO = new GdsSkuInfoReqDTO();
                    gdsSkuInfoReqDTO.setId(skuId);
                    gdsSkuInfoReqDTO.setGdsId(gdsId);
                    gdsSkuInfoReqDTO.setIsbn(isbn);
                    gdsSkuInfoReqDTO.setShopId(shopId);
                    gdsSkuInfoReqDTO.setStaff(gdsInfoAddReqDTO.getStaff());
                    gdsSkuInfoManageSV.deleteGdsSkuInfo(gdsSkuInfoReqDTO);
                }
            }
		    
			for (GdsSkuInfoReqDTO skuInfoReqDTO : skus) {
				skuInfoReqDTO.setGdsName(gdsInfoAddReqDTO.getGdsInfoReqDTO().getGdsName());
				skuInfoReqDTO.setGdsId(gdsId);
				skuInfoReqDTO.setShopId(gdsInfoAddReqDTO.getGdsInfoReqDTO().getShopId());
				skuInfoReqDTO.setIsbn(isbn);
				Long skuId = skuInfoReqDTO.getId();
				ObjectCopyUtil.copyObjValue(gdsInfo, skuInfoReqDTO, "id", false);
				boolean in = false;
				if(null != skuId && beforeSkuIds.contains(skuId)){
				    in = true;
				}
				/*for (Long skuIdBefore : beforeSkuIds) {
					if (skuId != null && skuId.longValue() == skuIdBefore.longValue()) {
						in = true;
					}
				}*/
				skuInfoReqDTO.setGdsProps(GdsUtils.doConvert(props, GdsSku2PropReqDTO.class));
				if (skuId != null && in) {
					skuIds.add(skuId);
					gdsSkuInfoManageSV.editGdsSkuInfoAndReference(skuInfoReqDTO);
					sendGdsStcokCatgMessage(before, now, shopId, skuId);
				} else {
					gdsSkuInfoManageSV.saveGdsSkuInfo(skuInfoReqDTO);
				}
			}
		}
	}

	private void sendGdsStcokCatgMessage(GdsInfo before, GdsInfo now, Long shopId, Long skuId) {
		String catg = before.getMainCatgs();
		if (StringUtil.isNotBlank(catg) && StringUtil.isNotBlank(now.getMainCatgs()) && !catg.equals(now.getMainCatgs())) {
			StockInfoReqDTO stockInfoReqDTO = new StockInfoReqDTO();
			stockInfoReqDTO.setGdsId(before.getId());
			stockInfoReqDTO.setSkuId(skuId);
			stockInfoReqDTO.setShopId(shopId);
			stockInfoReqDTO.setCatgCode(now.getMainCatgs());
			ShopInfoResDTO shop = shopInfoRSV.findShopInfoByShopID(shopId);
			stockInfoReqDTO.setCompanyId(shop.getCompanyId());
			GdsMessageUtil.sendStockCatgChangeMessage(stockInfoReqDTO);

			// 删除之前的分类索引
			String catgCode = before.getMainCatgs();
			Long gdsId = before.getId();
			GdsGds2CatgCatgIdxReqDTO reqDTO = new GdsGds2CatgCatgIdxReqDTO();
			reqDTO.setCatgCode(catgCode);
			reqDTO.setGdsId(gdsId);
			gdsInfoIDXSV.delGds2CatgCatgIDX(reqDTO);
		}
	}

	/**
	 * 
	 * compareProps:(比较属性，如果本次属性没有并且之前有，则继续保留). <br/>
	 * 
	 * @author linwb3
	 * @param props
	 * @param skuParams
	 * @param beforeProps
	 * @since JDK 1.6
	 */
	private void compareProps(List<GdsGds2PropReqDTO> props, List<GdsGds2PropReqDTO> skuParams, List<GdsGds2Prop> beforeProps) {

		if (CollectionUtils.isNotEmpty(beforeProps)) {
			// 遍历之前的所有属性
			for (GdsGds2Prop gdsGds2Prop : beforeProps) {

				// 默认当前属性在本次编辑的传入属性中不存在
				boolean in = false;
				Long propId = gdsGds2Prop.getPropId();
				
				  // 规格属性编辑必须删除，所以不做比较
                if (GdsConstants.GdsProp.PROP_TYPE_1.equals(gdsGds2Prop.getPropType())) {
                    continue;
                }

				// 判断属性是否存在于当前的商品属性
				if (CollectionUtils.isNotEmpty(props)) {
					for (GdsGds2PropReqDTO gdsGds2PropReqDTO : props) {
						Long nowPropId = gdsGds2PropReqDTO.getPropId();
						// 如果存在相同属性id,则认为 该属性属于本次编辑范围，不做保留
						if (nowPropId.longValue() == propId.longValue()) {
							in = true;
							continue;
						}
					}
					if (in) {
						continue;
					}
				}

				if (CollectionUtils.isNotEmpty(skuParams)) {

					// 判断属性是否存在于当前的单品属性
					for (GdsGds2PropReqDTO gdsGds2PropReqDTO : skuParams) {
						Long nowPropId = gdsGds2PropReqDTO.getPropId();
						// 如果存在相同属性id,则认为 该属性属于本次编辑范围，不做保留
						if (nowPropId.longValue() == propId.longValue()) {
							in = true;
							continue;
						}
					}
				}

				if (in) {
					continue;
				} else {
					// 如果属性不存在于本次编辑的属性范围内，则直接保留
					GdsGds2PropReqDTO addInfo = new GdsGds2PropReqDTO();
					ObjectCopyUtil.copyObjValue(gdsGds2Prop, addInfo, null, false);
					props.add(addInfo);
				}
			}
		}
	}

	/**
	 * 
	 * editGdsScoreInfo:(编辑商品积分信息). <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfoAddReqDTO
	 * @param gdsId
	 * @param shopId
	 * @param gdsInfoReqDTO
	 * @since JDK 1.6
	 */
	private void editGdsScoreInfo(GdsInfoAddReqDTO gdsInfoAddReqDTO, Long gdsId, Long shopId, GdsInfoReqDTO gdsInfoReqDTO) {
		if (GdsUtils.isEqualsValid(gdsInfoReqDTO.getIfScoreGds())) {
			GdsScoreExtReqDTO gdsScoreExtReqDTO = new GdsScoreExtReqDTO();
			gdsScoreExtReqDTO.setGdsId(gdsId);
			gdsScoreExtReqDTO.setShopId(shopId);
			List<GdsScoreExtRespDTO> scores = gdsScoreExtSV.queryGdsScoreExtByGds(gdsScoreExtReqDTO);
			List<GdsScoreExtReqDTO> nows = gdsInfoAddReqDTO.getGdsScoreExtReqDTOs();

			for (GdsScoreExtReqDTO now : nows) {
				boolean in = false;
				Long nowId = now.getId();
				now.setGdsId(gdsId);
				now.setShopId(shopId);
				if (now.getScore() == null) {
					now.setScore(0L);
				}
				if (now.getPrice() == null) {
					now.setPrice(0L);
				}
				for (GdsScoreExtRespDTO before : scores) {
					if (nowId != null && nowId.longValue() == before.getId().longValue()) {
						in = true;
					}
				}
				if (in) {
					gdsScoreExtSV.editGdsScoreExt(now);
				} else {
					gdsScoreExtSV.addGdsScoreExt(now);
				}
			}

			for (GdsScoreExtRespDTO before : scores) {
				boolean in = false;
				Long beforeId = before.getId();
				for (GdsScoreExtReqDTO now : nows) {
					Long nowId = now.getId();
					if (nowId != null && nowId.longValue() == before.getId().longValue()) {
						in = true;
					}
				}
				if (!in) {
					GdsScoreExtReqDTO gdsScore = new GdsScoreExtReqDTO();
					gdsScore.setId(beforeId);
					gdsScoreExtSV.deleteGdsSoreExt(gdsScore);
				}
			}
		}
	}

	/**
	 * 
	 * TODO 编辑商品主表信息并更新商品主表索引表.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsInfoManageSV#editGdsInfo(com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO)
	 */
	@Override
	public GdsInfo editGdsInfo(GdsInfoReqDTO gdsInfoReqDTO) throws BusinessException {
		// 更新商品原子服务
		GdsInfo gdsInfo = editGdsInfoAtom(gdsInfoReqDTO, null);

		// 更新属性关系（更新商品状态---当操作为商品上下架时）
		if (StringUtil.isNotBlank(gdsInfoReqDTO.getGdsStatus())) {
			// 更新商品属性关系
			List<GdsGds2Prop> propResp = gdsInfo2PropSV.queryGds2PropsModelByGdsId(gdsInfoReqDTO.getId());
			List<GdsGds2PropReqDTO> propReq = GdsUtils.doConvert(propResp, GdsGds2PropReqDTO.class);
			if (CollectionUtils.isNotEmpty(propReq)) {
				// 删除商品属性关系
				for (GdsGds2PropReqDTO gdsGds2PropReqDTO : propReq) {
					gdsGds2PropReqDTO.setGdsStatus(gdsInfoReqDTO.getGdsStatus());
					gdsGds2PropReqDTO.setGdsId(gdsInfoReqDTO.getId());
					gdsGds2PropReqDTO.setStaff(gdsInfoReqDTO.getStaff());
					gdsInfo2PropSV.updateGds2PropGdsStatus(gdsGds2PropReqDTO);
				}
			}

			// 更新商品分类关系表
			List<GdsGds2Catg> catgResp = gdsInfo2CatgSV.queryGds2CatgsModelByGdsId(gdsInfo.getId());
			List<GdsGds2CatgReqDTO> catgReq = GdsUtils.doConvert(catgResp, GdsGds2CatgReqDTO.class);
			if (CollectionUtils.isNotEmpty(catgReq)) {
				for (GdsGds2CatgReqDTO gds2CatgReqDTO : catgReq) {
					gds2CatgReqDTO.setGdsStatus(gdsInfoReqDTO.getGdsStatus());
					gds2CatgReqDTO.setGdsId(gdsInfoReqDTO.getId());
					gds2CatgReqDTO.setStaff(gdsInfoReqDTO.getStaff());
					gdsInfo2CatgSV.updateGds2CatgGdsStatus(gds2CatgReqDTO);
				}
			}
			// 更新属性索引表
			gdsInfoIDXSV.editGdsInfoIDX(null, catgReq, propReq);
		}
		return gdsInfo;

	}

	/**
	 * 
	 * editGdsInfoAtom:(更新商品原子服务). <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfoReqDTO
	 * @since JDK 1.6
	 */
	private GdsInfo editGdsInfoAtom(GdsInfoReqDTO gdsInfoReqDTO, GdsInfo before) {
		// 编辑商品
		Long gdsId = gdsInfoReqDTO.getId();
		GdsInfoReqDTO gdsInfoReq = new GdsInfoReqDTO();
		gdsInfoReq.setId(gdsId);
		GdsInfo gdsInfoTemp = new GdsInfo();
		if (before != null) {
			gdsInfoTemp = gdsInfoQuerySV.queryGdsInfoModelFromDB(gdsInfoReq);
			ObjectCopyUtil.copyObjValue(gdsInfoTemp, before, null, false);
		}

		GdsInfo gdsInfo = new GdsInfo();
		ObjectCopyUtil.copyObjValue(gdsInfoReqDTO, gdsInfo, null, false);
		editGdsInfo(gdsInfoReqDTO.getStaff().getId(), gdsInfoReqDTO.getIsUpdateShipTemplate(), gdsInfoReqDTO.getShipTemplateId(), gdsInfo);

		if (before == null) {
			gdsInfo = gdsInfoQuerySV.queryGdsInfoModelFromDB(gdsInfoReq);
		} else {
			gdsInfo = gdsInfoTemp;
		}
		ObjectCopyUtil.copyObjValue(gdsInfoReqDTO, gdsInfo, null, false);
		// 更新商品主表索引
		gdsInfoIDXSV.editGdsInfoIDX(gdsInfo, null, null);
		return gdsInfo;
	}

	/**
	 * 
	 * deleteGdsInfo:(删除商品). <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfoReqDTO
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	@Override
	public List<Long> deleteGdsInfo(GdsInfoReqDTO gdsInfoReqDTO) throws BusinessException {
		List<Long> skuIds = new ArrayList<Long>();
		// 删除商品信息
		gdsInfoReqDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_DELETE);
		executeGdsShelves(gdsInfoReqDTO);
		// 删除猜你喜欢记录
		GdsGuessYLReqDTO dto = new GdsGuessYLReqDTO();
		dto.setGdsId(gdsInfoReqDTO.getId());
		gdsGuessYLSV.deleteGdsGuessYLByGdsId(dto);
		gdsPlatRecomSV.deletGdsPlatRecomByGdsId(gdsInfoReqDTO);
		GdsMessageUtil.sendGdsDelMessage(gdsInfoReqDTO);
		// 遍历单品列表
		List<GdsSkuInfo> skus = gdsInfoQuerySV.querySkuInfosByGdsId(gdsInfoReqDTO.getId());

		for (GdsSkuInfo sku : skus) {
			// 调用库存清除方法
			skuIds.add(sku.getId());
			StockInfoReqDTO stockInfoReqDTO = new StockInfoReqDTO();
			stockInfoReqDTO.setSkuId(sku.getId());
			stockInfoReqDTO.setShopId(sku.getShopId());
			stockInfoReqDTO.setCompanyId(gdsInfoReqDTO.getCompanyId());
			stockInfoReqDTO.setExt1(sku.getExt1());
			try {
				gdsStockSV.deleteStockInfoBySkuInfo(stockInfoReqDTO);
			} catch (Exception e) {
				LogUtil.error(MODULE, "库存清除失败", e);
				throw new BusinessException(GdsErrorConstants.GdsInfo.ERROR_GOODS_GDSINFO_210007);
			}
		}
		// 删除商品缓存
		delGdsInfoCache(gdsInfoReqDTO.getId());
		// 删除单品缓存
		for (GdsSkuInfo sku : skus) {
			delSkuInfoCache(sku.getId());
		}
		return skuIds;
	}

	/**
	 * 
	 * batchDelGdsInfo:(批量删除商品). <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfoReqDTO
	 * @since JDK 1.6
	 */
	@Override
	public void batchDelGdsInfo(GdsInfoReqDTO gdsInfoReqDTO) {
		Long[] ids = gdsInfoReqDTO.getIds();
		if (!ArrayUtils.isEmpty(ids)) {
			for (Long id : ids) {
				gdsInfoReqDTO.setId(id);
				deleteGdsInfo(gdsInfoReqDTO);
			}
		}
	}

	/**
	 * 
	 * batchOffShelvesGdsInfoByShopId:(批量下架店铺商品). <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfoReqDTO
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	@Override
	public void batchOffShelvesGdsInfoByShopId(GdsInfoReqDTO gdsInfoReqDTO) throws BusinessException {
		ShopInfoResDTO shop = shopInfoRSV.findShopInfoByShopID(gdsInfoReqDTO.getShopId());
		if (shop != null && StaffConstants.shopInfo.SHOP_STATUS_INVALID.equals(shop.getShopStatus())) {
			gdsInfoReqDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES);
			List<GdsInfoRespDTO> gdsInfoRespDTOs = gdsInfoQuerySV.queryGdsInfoList(gdsInfoReqDTO);
			if (CollectionUtils.isNotEmpty(gdsInfoRespDTOs)) {
				for (GdsInfoRespDTO gdsInfoRespDTO : gdsInfoRespDTOs) {
					if (!GdsConstants.GdsInfo.GDS_STATUS_DELETE.equals(gdsInfoRespDTO.getGdsStatus()) && !GdsConstants.GdsInfo.GDS_STATUS_OFFSHELVES.equals(gdsInfoRespDTO.getGdsStatus())) {
						GdsInfoReqDTO gdsShelves = new GdsInfoReqDTO();
						gdsShelves.setId(gdsInfoRespDTO.getId());
						gdsShelves.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_OFFSHELVES);
						executeGdsShelves(gdsShelves);
					}
				}
			}
		}
	}

	/**
	 * 
	 * TODO 商品上下架操作.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsInfoManageSV#doGdsShelves(com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO)
	 */
	@Override
	public List<Long> executeGdsShelves(GdsInfoReqDTO gdsInfoReqDTO) throws BusinessException {
	    
	    /*if(gdsInfoReqDTO.isHandTransaction()){
    	    DistributedTransactionManager.beginTransaction();
            LogUtil.info(MODULE, "[商品域]产品批缇上下架,触发声明式事务beginTransaction()");
	    }*/
		String gdsStatus = gdsInfoReqDTO.getGdsStatus();
		Long gdsId = gdsInfoReqDTO.getId();
		List<Long> skuIds = new ArrayList<Long>();

		List<GdsSkuInfo> skus = null;
		if (GdsUtils.isOnShelves(gdsStatus) || GdsUtils.isOffShelves(gdsStatus)) {
			// 上下架操作，只查询待上架，已上架，已下架单品
			String[] status = GdsUtils.getNoDeleteStatusArr();
			skus = gdsInfoQuerySV.querySkuInfosByGdsId(gdsId, status);
		} else {
			skus = gdsInfoQuerySV.querySkuInfosByGdsId(gdsId);
		}

		if (CollectionUtils.isNotEmpty(skus)) {
			for (GdsSkuInfo skuInfo : skus) {
				skuIds.add(skuInfo.getId());
				String skuStatus = skuInfo.getGdsStatus();
				if (StringUtil.isNotBlank(skuStatus) && !skuStatus.equals(gdsStatus)) {
					GdsSkuInfoReqDTO skuInfoReqDTO = new GdsSkuInfoReqDTO();
					skuInfoReqDTO.setGdsStatus(gdsStatus);
					skuInfoReqDTO.setId(skuInfo.getId());
					skuInfoReqDTO.setGdsId(skuInfo.getGdsId());
					skuInfoReqDTO.setShopId(skuInfo.getShopId());
					skuInfoReqDTO.setStaff(gdsInfoReqDTO.getStaff());
					// 单品上下架，删除操作操作
					gdsSkuInfoManageSV.executeSkuShelvesOnly(skuInfoReqDTO);
				}
			}
		}

		GdsInfoReqDTO shelvesReq = new GdsInfoReqDTO();
		shelvesReq.setId(gdsInfoReqDTO.getId());
		shelvesReq.setShopId(gdsInfoReqDTO.getShopId());
		shelvesReq.setGdsStatus(gdsInfoReqDTO.getGdsStatus());

		// 上下架，删除操作不编辑运费模板
		shelvesReq.setIsUpdateShipTemplate(false);
		shelvesReq.setStaff(gdsInfoReqDTO.getStaff());
		GdsInfo gdsInfo = editGdsInfo(shelvesReq);

		/*if(gdsInfoReqDTO.isHandTransaction()){
            DistributedTransactionManager.endTransaction();
            LogUtil.info(MODULE, "[商品域]产品批量上下架,触发声明式事务endTransaction()");
		}*/
		
		if(gdsInfoReqDTO.isSendIdxMsg()){
		   // 发送商品上下架消息
		   GdsUtils.sendGdsIndexMsg(gdsInfoReqDTO.getGdsStatus(), "T_GDS_INFO", MODULE, gdsInfoReqDTO.getId(), gdsInfo.getCatlogId());
		}
		
		delGdsInfoCache(gdsId);
		if (CollectionUtils.isNotEmpty(skus)) {
			for (GdsSkuInfo skuInfo : skus) {
				Long skuId = skuInfo.getId();
				// 删除单品缓存
				delSkuInfoCache(skuId);
			}
		}

		return skuIds;
	}

	/**
	 * 
	 * TODO 商品批量上架，下架，删除操作.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsInfoManageSV#doGdsShelves(com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO)
	 */
	@Override
	public void batchExecuteGdsShelves(GdsInfoReqDTO gdsInfoReqDTO) throws BusinessException {
		Long[] ids = gdsInfoReqDTO.getIds();
		if (!ArrayUtils.isEmpty(ids)) {
			for (Long id : ids) {
				gdsInfoReqDTO.setId(id);
				GdsInfoRespDTO gdsInfoRespDTO = gdsInfoQuerySV.queryGdsInfoByOption(gdsInfoReqDTO);
				// 如果是非删除操作
				if (!GdsConstants.GdsInfo.GDS_STATUS_DELETE.equals(gdsInfoRespDTO.getGdsStatus())) {
					GdsInfoReqDTO gdsShelves = new GdsInfoReqDTO();
					gdsShelves.setId(gdsInfoRespDTO.getId());
					gdsShelves.setGdsStatus(gdsInfoReqDTO.getGdsStatus());
					gdsShelves.setShopId(gdsInfoReqDTO.getShopId());
					executeGdsShelves(gdsShelves);
				} else {
					// 如果是删除操作
					GdsInfoReqDTO gdsShelves = new GdsInfoReqDTO();
					gdsShelves.setId(gdsInfoRespDTO.getId());
					gdsShelves.setGdsStatus(gdsInfoReqDTO.getGdsStatus());
					gdsShelves.setShopId(gdsInfoReqDTO.getShopId());
					deleteGdsInfo(gdsInfoReqDTO);
				}
			}
		}
	}

	/**
	 * 
	 * editGdsShipTemplate:(编辑运费模板). <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfoReqDTO
	 * @since JDK 1.6
	 */
	@Override
	public List<Long> editGdsShipTemplate(GdsInfoReqDTO gdsInfoReqDTO) throws BusinessException {
		List<Long> skuIds = new ArrayList<Long>();
		Long gdsId = gdsInfoReqDTO.getId();

		gdsInfoReqDTO.setIsUpdateShipTemplate(true);
		// 修改商品主表信息,并且更新索引表
		GdsInfo gdsInfo = editGdsInfoAtom(gdsInfoReqDTO, null);

		// 更新所有未删除单品的运费模板信息
		List<Long> skuIdsNow = gdsInfoQuerySV.querySkuIdsGdsId(gdsId, GdsUtils.getNoDeleteStatusArr());
		if (CollectionUtils.isNotEmpty(skuIdsNow)) {
			for (Long skuId : skuIdsNow) {
				Long shopId = gdsInfo.getShopId();
				GdsSkuInfoReqDTO dto = new GdsSkuInfoReqDTO();
				dto.setId(skuId);
				dto.setShopId(shopId);
				dto.setGdsId(gdsId);
				skuIds.add(skuId);
				if (gdsInfoReqDTO.getShipTemplateId() == null || gdsInfoReqDTO.getShipTemplateId() == 0) {
					dto.setShipTemplateId(-1L);
				} else {
					dto.setShipTemplateId(gdsInfoReqDTO.getShipTemplateId());
				}
				gdsSkuInfoManageSV.editGdsSkuInfo(dto);
			}
		}
		return skuIds;
	}

	/**
	 * 
	 * delGds2Media:(删除商品媒体关系). <br/>
	 * 
	 * @author linwb3
	 * @param gdsId
	 * @param staffId
	 * @since JDK 1.6
	 */
	private void delGds2Media(Long gdsId, Long staffId) {
		GdsGds2MediaReqDTO reqDTO = new GdsGds2MediaReqDTO();
		reqDTO.setGdsId(gdsId);
		reqDTO.setStaff(GdsUtils.getStaff(staffId));
		gdsInfo2MediaSV.delGds2Media(reqDTO);
	}

	/**
	 * 
	 * delSkus:(删除商品对应所有单品). <br/>
	 * 
	 * @author linwb3
	 * @param gdsId
	 * @param staffId
	 * @since JDK 1.6
	 */
	private void delSkus(Long gdsId, Long shopId, Long staffId) {
		List<Long> skuIds = gdsInfoQuerySV.querySkuIdsGdsId(gdsId);
		if (CollectionUtils.isNotEmpty(skuIds)) {
			for (Long skuId : skuIds) {
				GdsSkuInfoReqDTO gdsSkuInfoReqDTO = new GdsSkuInfoReqDTO();
				gdsSkuInfoReqDTO.setId(skuId);
				gdsSkuInfoReqDTO.setStaff(GdsUtils.getStaff(staffId));
				gdsSkuInfoReqDTO.setGdsId(gdsId);
				gdsSkuInfoReqDTO.setShopId(shopId);
				gdsSkuInfoManageSV.deleteGdsSkuInfo(gdsSkuInfoReqDTO);
			}
		}
	}

	/**
	 * 
	 * delGds2Prop:(删除商品属性关系). <br/>
	 * 
	 * @author linwb3
	 * @param gdsId
	 * @param staffId
	 * @since JDK 1.6
	 */
	private void delGds2Prop(Long gdsId, Long staffId) {
		GdsGds2PropReqDTO reqDTO = new GdsGds2PropReqDTO();
		reqDTO.setGdsId(gdsId);
		reqDTO.setStaff(GdsUtils.getStaff(staffId));
		gdsInfo2PropSV.delGds2Prop(reqDTO);
	}

	/**
	 * 
	 * delGds2Catg:(删除商品分类关系). <br/>
	 * 
	 * @author linwb3
	 * @param gdsId
	 * @param staffId
	 * @since JDK 1.6
	 */
	private void delGds2Catg(Long gdsId, Long staffId) {
		GdsGds2CatgReqDTO reqDTO = new GdsGds2CatgReqDTO();
		reqDTO.setGdsId(gdsId);
		reqDTO.setStaff(GdsUtils.getStaff(staffId));
		gdsInfo2CatgSV.delGds2Catg(reqDTO);
	}

	/**
	 * 
	 * delGdsInfo:(删除商品信息). <br/>
	 * 
	 * @author linwb3
	 * @param gdsId
	 * @param shopId
	 * @param staffId
	 * @return
	 * @since JDK 1.6
	 */
	private GdsInfo delGdsInfo(Long gdsId, Long shopId, Long staffId) {
		GdsInfoCriteria gdsInfoCriteria = new GdsInfoCriteria();
		gdsInfoCriteria.createCriteria().andIdEqualTo(gdsId);
		GdsInfo gdsInfo = new GdsInfo();
		gdsInfo.setId(gdsId);
		gdsInfo.setShopId(shopId);
		gdsInfo.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_DELETE);
		gdsInfo.setUpdateTime(DateUtil.getSysDate());
		gdsInfo.setUpdateStaff(staffId);
		gdsInfoMapper.updateByExampleSelective(gdsInfo, gdsInfoCriteria);
		return gdsInfo;
	}

	/**
	 * 
	 * saveGdsInfo:(保存商品主表信息项，最原子服务，所有保存主表必须调用). <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfoAddReqDTO
	 * @return
	 * @since JDK 1.6
	 */
	private GdsInfo saveGdsInfo(GdsInfoReqDTO gdsInfoReqDTO) {
		Long gdsId = seqGdsInfo.nextValue();
		Long staffId = gdsInfoReqDTO.getStaff().getId();
		GdsInfo gdsInfo = new GdsInfo();
		ObjectCopyUtil.copyObjValue(gdsInfoReqDTO, gdsInfo, null, false);
		if (gdsInfoReqDTO.getShipTemplateId() == null) {
			gdsInfo.setShipTemplateId(GdsConstants.GdsInfo.GDS_INFO_TEMPLATEID_NULL);
		}
		gdsInfo.setId(gdsId);
		gdsInfo.setIsbn(gdsInfoReqDTO.getIsbn());
		gdsInfo.setCreateStaff(staffId);
		gdsInfo.setCreateTime(DateUtil.getSysDate());
		gdsInfo.setUpdateStaff(staffId);
		gdsInfo.setUpdateTime(DateUtil.getSysDate());
		if(StringUtil.isNotBlank(gdsInfo.getExt1())){
			gdsInfo.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_OFFLINE);
		}else{
			gdsInfo.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_WAITSHELVES);
		}
		gdsInfo.setGdsApprove(GdsConstants.GdsInfo.GDS_APPROVE_PASS);
		gdsInfoMapper.insertSelective(gdsInfo);
		return gdsInfo;
	}

	/**
	 * 
	 * editGdsInfo:(编辑商品-最原子编辑，所有编辑肯定调用这个方法). <br/>
	 * 
	 * @author linwb3
	 * @param staffId
	 * @param isUpdateShipTemplate
	 *            是否更新运费模板，运费模板如果无配置 默认为-1
	 * @param shipTemplateId
	 * @param gdsInfo
	 * @since JDK 1.6
	 */
	private void editGdsInfo(Long staffId, boolean isUpdateShipTemplate, Long shipTemplateId, GdsInfo gdsInfo) {
		if (isUpdateShipTemplate) {
			if (gdsInfo.getShipTemplateId() != null && gdsInfo.getShipTemplateId().longValue() != 0) {
				gdsInfo.setShipTemplateId(shipTemplateId);
			} else {
				gdsInfo.setShipTemplateId(GdsConstants.GdsInfo.GDS_INFO_TEMPLATEID_NULL);
			}
		}
		gdsInfo.setUpdateStaff(staffId);
		gdsInfo.setUpdateTime(DateUtil.getSysDate());
		gdsInfoMapper.updateByPrimaryKeySelective(gdsInfo);
	}

	/**
	 * 
	 * getCatgStr:(拼接分类存储结构 结构为<分类编码><分类编码>). <br/>
	 * 
	 * @author linwb3
	 * @param catgs
	 *            商品分类关系dto列表
	 * @param type
	 * @return
	 * @since JDK 1.6
	 */
	private String getCatgStr(List<GdsGds2CatgReqDTO> catgs, String type) {
		StringBuffer sb = new StringBuffer();
		for (GdsGds2CatgReqDTO catg : catgs) {
			// 查询包含本身的所有上级分类
			List<GdsCategoryRespDTO> categorys = gdsCategorySV.queryCategoryTraceUpon(catg.getCatgCode());
			// 如果不为空，并且为需要获取的分类类型
			if (CollectionUtils.isNotEmpty(categorys)) {
				for (GdsCategoryRespDTO category : categorys) {
					if (type.equals(category.getCatgType())) {
						sb.append("<").append(category.getCatgCode()).append(">");
					}
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 
	 * getGdsChangePropStr:(人卫商城获取变更的商品信息). <br/>
	 * 
	 * @author zjh
	 * @param gdsInfoAddReqDTO
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	private String getGdsChangePropStr(GdsInfoAddReqDTO gdsInfoAddReqDTO) throws BusinessException {
		StringBuilder changePropStr = new StringBuilder();

		GdsInfoReqDTO gdsInfoReqDTO = gdsInfoAddReqDTO.getGdsInfoReqDTO();
		GdsInfoRespDTO gdsInfoRespDTO = gdsInfoQuerySV.queryGdsInfoByOption(gdsInfoReqDTO, new GdsQueryOption[] { GdsQueryOption.BASIC, GdsQueryOption.PROP }, new GdsOption.SkuQueryOption[] { SkuQueryOption.BASIC, SkuQueryOption.PRICE });
		String oldChangeStr = gdsInfoRespDTO.getChangePropStr();
		if (oldChangeStr != null) {
			changePropStr.append(oldChangeStr);
		}
		String gdsName = gdsInfoRespDTO.getGdsName();
		if (!gdsName.equals(gdsInfoReqDTO.getGdsName())) {
			if (oldChangeStr == null || !oldChangeStr.contains("gdsName")) {
				changePropStr.append("gdsName,");
			}

		}
		List<GdsSkuInfoRespDTO> gdsSkuInfoRespDTOs = gdsInfoRespDTO.getSkus();
		GdsSkuInfoRespDTO gdsSkuInfoRespDTO = gdsSkuInfoRespDTOs.get(0);
		// 如果之前导入的数据价格不为空，则比对前后的价格是否相等
		if (gdsSkuInfoRespDTO.getSku2PriceRespDTOs() != null && gdsSkuInfoRespDTO.getSku2PriceRespDTOs().size() > 0 && gdsSkuInfoRespDTO.getSku2PriceRespDTOs().get(0).getPrice().getPrice() != null) {

			GdsSku2PriceRespDTO gdsSku2PriceRespDTO = gdsSkuInfoRespDTO.getSku2PriceRespDTOs().get(0);

			Long price = gdsSku2PriceRespDTO.getPrice().getPrice();
			List<GdsSkuInfoReqDTO> gdsSkuInfoReqDTOs = gdsInfoAddReqDTO.getSkuInfoReqDTOs();
			GdsSkuInfoReqDTO gdsSkuInfoReqDTO = gdsSkuInfoReqDTOs.get(0);
			GdsSku2PriceReqDTO gdsSku2PriceReqDTO = gdsSkuInfoReqDTO.getSku2PriceReqDTOs().get(0);
			GdsPriceReqDTO gdsPriceReqDTO = (GdsPriceReqDTO) gdsSku2PriceReqDTO.getPrice();
			// 如果价格前后不等或者编辑删除了价格
			if (gdsPriceReqDTO == null || !price.equals(gdsPriceReqDTO.getPrice())) {
				if (oldChangeStr == null || !oldChangeStr.contains("gdsPrice")) {

					changePropStr.append("gdsPrice,");
				}
			}
		} else {
			List<GdsSkuInfoReqDTO> gdsSkuInfoReqDTOs = gdsInfoAddReqDTO.getSkuInfoReqDTOs();
			GdsSkuInfoReqDTO gdsSkuInfoReqDTO = gdsSkuInfoReqDTOs.get(0);
			GdsSku2PriceReqDTO gdsSku2PriceReqDTO = gdsSkuInfoReqDTO.getSku2PriceReqDTOs().get(0);
			GdsPriceReqDTO gdsPriceReqDTO = (GdsPriceReqDTO) gdsSku2PriceReqDTO.getPrice();
			if (gdsPriceReqDTO != null && gdsPriceReqDTO.getPrice() != null) {
				if (oldChangeStr == null || !oldChangeStr.contains("gdsPrice")) {

					changePropStr.append("gdsPrice,");
				}
			}
		}
		// 获取商品已有属性
		List<GdsPropRespDTO> gdsPropRespDTOs = gdsInfoRespDTO.getProps();
		// 获取商品编辑的属性
		List<GdsGds2PropReqDTO> gds2PropReqDTOs = gdsInfoAddReqDTO.getGds2PropReqDTOs();
		if (CollectionUtils.isNotEmpty(gds2PropReqDTOs)) {
			for (GdsGds2PropReqDTO gdsGds2PropReqDTO : gds2PropReqDTOs) {
				if (CollectionUtils.isNotEmpty(gdsPropRespDTOs)) {
					for (GdsPropRespDTO gdsPropRespDTO : gdsPropRespDTOs) {
						// 循环，查找属性id相同的，比对属性遍及前后的值，不同的做记录到商品的changPropStr属性；
						if (gdsPropRespDTO.getId().equals(gdsGds2PropReqDTO.getPropId())) {

							List<GdsPropValueRespDTO> gdsPropValueRespDTOs = gdsPropRespDTO.getValues();

							if (gdsPropValueRespDTOs == null || gdsPropValueRespDTOs.size() == 0) {
								if (gdsGds2PropReqDTO.getPropValue() != null) {
									if (oldChangeStr == null || !oldChangeStr.contains(gdsGds2PropReqDTO.getPropId().toString())) {
										changePropStr.append(gdsGds2PropReqDTO.getPropId() + ",");
									}
								}

							} else {
								String val = gdsPropValueRespDTOs.get(0).getPropValue();
								if (val == null) {
									if (gdsGds2PropReqDTO.getPropValue() != null) {
										if (oldChangeStr == null || !oldChangeStr.contains(gdsGds2PropReqDTO.getPropId().toString())) {
											changePropStr.append(gdsGds2PropReqDTO.getPropId() + ",");
										}
									}

								} else {
									if (gdsGds2PropReqDTO.getPropValue() == null) {
										if (oldChangeStr == null || !oldChangeStr.contains(gdsGds2PropReqDTO.getPropId().toString())) {
											changePropStr.append(gdsGds2PropReqDTO.getPropId() + ",");
										}

									} else {
										// 富文本比较录入内容
										if (GdsConstants.GdsProp.GDS_PROP_VALUE_INPUT_TYPE_RICHTXT.equals(gdsGds2PropReqDTO.getPropInputType())) {
											String valReq = FileUtil.readFile2Text(gdsGds2PropReqDTO.getPropValue(), "UTF-8").trim();
											String valResp = FileUtil.readFile2Text(val, "UTF-8").trim();
											if (!valReq.equals(valResp)) {

												if (oldChangeStr == null || !oldChangeStr.contains(gdsGds2PropReqDTO.getPropId().toString())) {
													changePropStr.append(gdsGds2PropReqDTO.getPropId() + ",");
												}
											}
										} else {
											if (!gdsGds2PropReqDTO.getPropValue().equals(val)) {
												if (oldChangeStr == null || !oldChangeStr.contains(gdsGds2PropReqDTO.getPropId().toString())) {

													changePropStr.append(gdsGds2PropReqDTO.getPropId() + ",");
												}
											}
										}

									}

								}
							}

						}
					}
				}
			}

		}

		return changePropStr.toString();
	}

	private void delSkuInfoCache(Long skuId) {
		try {
			CacheUtil.delItem(GdsConstants.GdsInfoCacheKey.SKU_CACHE_KEY_PREFIX + skuId);
		} catch (Exception e) {
			LogUtil.error(MODULE, "delete skuInfo cache failed! please check  Cache Server!", e);
		}
	}

	private void delGdsInfoCache(Long gdsId) {
		// 删除商品主图缓存
		try {
			CacheUtil.delItem(GdsConstants.GdsInfoCacheKey.GDS_MAINPIC_CACHE_KEY_PREFIX + gdsId);
		} catch (Exception e) {
			LogUtil.error(MODULE, "del gdsInfo main pic cache failed! ! please check  Cache Server!", e);
		}

		// 删除商品信息缓存
		try {
			CacheUtil.delItem(GdsConstants.GdsInfoCacheKey.GDS_CACHE_KEY_PREFIX + gdsId);
		} catch (Exception e) {
			LogUtil.error(MODULE, "edit gdsInfo cache failed! please check  Cache Server!", e);
		}
	}

	@Override
	public void addGdsVerify(GdsVerifyReqDTO gdsVerifyReqDTO) throws BusinessException {

		// 上架校验
		if (GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES.equals(gdsVerifyReqDTO.getOperateType())) {
			// 上下架操作，只查询待上架，已上架，已下架单品
			Long gdsId = gdsVerifyReqDTO.getGdsId();
			String[] status = GdsUtils.getNoDeleteStatusArr();
			List<Long> skuIds = gdsInfoQuerySV.querySkuIdsGdsId(gdsId, status);

			if (CollectionUtils.isNotEmpty(skuIds)) {
				for (Long skuId : skuIds) {
					GdsSkuInfoReqDTO gdsSkuInfoReqDTO = new GdsSkuInfoReqDTO();
					gdsSkuInfoReqDTO.setId(skuId);
					gdsSkuInfoReqDTO.setGdsId(gdsId);

					GdsSkuInfoReqDTO query = new GdsSkuInfoReqDTO();
					query.setId(skuId);
					GdsSkuInfo before = gdsSkuInfoQuerySV.querySkuInfoFromDB(query);
					gdsSkuInfoManageSV.onShelvesCheck(gdsSkuInfoReqDTO, before);
				}
			}
		}

		// 获取商品的一些基本信息
		GdsInfoReqDTO dto = new GdsInfoReqDTO();
		dto.setId(gdsVerifyReqDTO.getGdsId());
		dto.setShopId(gdsVerifyReqDTO.getShopId());
		List<Long> propIds = new ArrayList<Long>();
		// 出版日期:1005作者:1001
		propIds.add(1005L);
		propIds.add(1001L);
		dto.setPropIds(propIds);
		dto.setGdsQueryOptions(new GdsQueryOption[] { GdsQueryOption.BASIC, GdsQueryOption.SHIPTEMPLATE, GdsQueryOption.PROP });
		PageResponseDTO<GdsInfoRespDTO> list = new PageResponseDTO<GdsInfoRespDTO>();
		list = gdsInfoQuerySV.queryGdsInfoListPage(dto);
		Long gdsTypeId = null;
		if (list != null && list.getResult() != null && list.getResult().size() > 0) {
			GdsInfoRespDTO respDto = list.getResult().get(0);
			gdsVerifyReqDTO.setIsbn(respDto.getIsbn());
			gdsVerifyReqDTO.setCatgCode(respDto.getMainCatgs());
			gdsVerifyReqDTO.setGdsName(respDto.getGdsName());
			gdsTypeId = respDto.getGdsTypeId();
		}
		/**
		 * 1、先失效掉该商品以前的历史记录（条件：审核通过、审核拒绝）status 置 0
		 */
		GdsVerifyShopIdxCriteria gdsVerifyShopIdxCriteria1 = new GdsVerifyShopIdxCriteria();
		GdsVerifyShopIdxCriteria.Criteria criteria1 = gdsVerifyShopIdxCriteria1.createCriteria();
		criteria1.andShopIdEqualTo(gdsVerifyReqDTO.getShopId());
		if (!StringUtil.isEmpty(gdsVerifyReqDTO.getGdsId())) {
			criteria1.andGdsIdEqualTo(gdsVerifyReqDTO.getGdsId());
		}
		if (!StringUtil.isEmpty(gdsVerifyReqDTO.getSkuId())) {
			criteria1.andSkuIdEqualTo(gdsVerifyReqDTO.getSkuId());
		}
		List<String> listStatus = new ArrayList<String>();
		listStatus.add(GdsConstants.GdsVerify.VERIFY_APPROVED);// 审核通过
		listStatus.add(GdsConstants.GdsVerify.VERIFY_REFUSE);// 审核拒绝
		criteria1.andVerifyStatusIn(listStatus);

		GdsVerifyShopIdx gdsVerifyShopIdx1 = new GdsVerifyShopIdx();
		gdsVerifyShopIdx1.setStatus(GdsConstants.Commons.STATUS_INVALID);
		gdsVerifyShopIdxMapper.updateByExampleSelective(gdsVerifyShopIdx1, gdsVerifyShopIdxCriteria1);
		/**
		 * 然后新增一条记录为有效的
		 */
		GdsVerifyShopIdx gdsVerifyShopIdx = new GdsVerifyShopIdx();
		ObjectCopyUtil.copyObjValue(gdsVerifyReqDTO, gdsVerifyShopIdx, null, false);
		Timestamp time = new Timestamp(System.currentTimeMillis());
		Long createStaff = gdsVerifyReqDTO.getStaff().getId();
		// 设置新增的记录为有效状态
		gdsVerifyShopIdx.setStatus(GdsConstants.Commons.STATUS_VALID);
		// 新增的记录，审核状态都为提交待审核状态（00）
		gdsVerifyShopIdx.setVerifyStatus(GdsConstants.GdsVerify.WAITE_VERIFY);
		gdsVerifyShopIdx.setGdsTypeId(gdsTypeId);
		gdsVerifyShopIdx.setOperateStaff(createStaff);
		gdsVerifyShopIdx.setOperateTime(time);
		gdsVerifyShopIdx.setCreateStaff(createStaff);
		gdsVerifyShopIdx.setCreateTime(time);
		gdsVerifyShopIdxMapper.insert(gdsVerifyShopIdx);
	}

	@Override
	public List<GdsSkuInfo> editGdsVerify(GdsVerifyReqDTO gdsVerifyReqDTO) throws BusinessException {
		/**
		 * 1、优先获取待审核状态的操作类型，防止后面更新了无法获取。
		 */
		GdsVerifyShopIdxCriteria gdsVerifyShopIdxQry = new GdsVerifyShopIdxCriteria();
		GdsVerifyShopIdxCriteria.Criteria criteriaQry = gdsVerifyShopIdxQry.createCriteria();
		criteriaQry.andShopIdEqualTo(gdsVerifyReqDTO.getShopId());
		if (!StringUtil.isEmpty(gdsVerifyReqDTO.getGdsId())) {
			criteriaQry.andGdsIdEqualTo(gdsVerifyReqDTO.getGdsId());
		}
		if (!StringUtil.isEmpty(gdsVerifyReqDTO.getSkuId())) {
			criteriaQry.andSkuIdEqualTo(gdsVerifyReqDTO.getSkuId());
		}
		criteriaQry.andVerifyStatusEqualTo(GdsConstants.GdsVerify.WAITE_VERIFY);
		List<GdsVerifyShopIdx> qryList = gdsVerifyShopIdxMapper.selectByExample(gdsVerifyShopIdxQry);
		String operateType = "";
		if (StringUtil.isNotEmpty(qryList) && qryList.size() > 0) {
			operateType = qryList.get(0).getOperateType();
		} else {
			throw new BusinessException();
		}

		/**
		 * 2、更新当前最新的记录 （条件：提交待审核状态的）
		 */
		GdsVerifyShopIdxCriteria gdsVerifyShopIdxCriteria = new GdsVerifyShopIdxCriteria();
		GdsVerifyShopIdxCriteria.Criteria criteria = gdsVerifyShopIdxCriteria.createCriteria();
		criteria.andShopIdEqualTo(gdsVerifyReqDTO.getShopId());
		if (!StringUtil.isEmpty(gdsVerifyReqDTO.getGdsId())) {
			criteria.andGdsIdEqualTo(gdsVerifyReqDTO.getGdsId());
		}
		if (!StringUtil.isEmpty(gdsVerifyReqDTO.getSkuId())) {
			criteria.andSkuIdEqualTo(gdsVerifyReqDTO.getSkuId());
		}
		criteria.andVerifyStatusEqualTo(GdsConstants.GdsVerify.WAITE_VERIFY);
		GdsVerifyShopIdx gdsVerifyShopIdx = new GdsVerifyShopIdx();
		ObjectCopyUtil.copyObjValue(gdsVerifyReqDTO, gdsVerifyShopIdx, null, false);
		Timestamp time = new Timestamp(System.currentTimeMillis());
		Long staff = gdsVerifyReqDTO.getStaff().getId();
		// 更新人
		gdsVerifyShopIdx.setUpdateStaff(staff);
		// 更新时间
		gdsVerifyShopIdx.setUpdateTime(time);
		// 审核时间
		gdsVerifyShopIdx.setVerifyTime(time);
		// 审核人
		gdsVerifyShopIdx.setVerifyStaff(staff);
		int operFlag = gdsVerifyShopIdxMapper.updateByExampleSelective(gdsVerifyShopIdx, gdsVerifyShopIdxCriteria);
		/**
		 * 3、审核后，根据审核状态判断是否通过或者拒绝 1)、如果通过，则操作类型更新商品的状态
		 * 如果为上架操作类型（11），则调用商品的上架更新操作服务；如果为删除操作类型（99），则调用商品的删除更新操作服务
		 */
		List<GdsSkuInfo> skuInfos = new ArrayList<GdsSkuInfo>();
		List<Long> skuIds = null;
		if (GdsConstants.GdsVerify.VERIFY_APPROVED.equals(gdsVerifyReqDTO.getVerifyStatus())) {
			GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
			gdsInfoReqDTO.setId(gdsVerifyReqDTO.getGdsId());
			gdsInfoReqDTO.setShopId(gdsVerifyReqDTO.getShopId());
			if (GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES.equals(operateType)) {
				// 上架
				gdsInfoReqDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES);
				skuIds = executeGdsShelves(gdsInfoReqDTO);
				if (CollectionUtils.isNotEmpty(skuIds)) {
					for (Long skuId : skuIds) {
						GdsSkuInfo sku = new GdsSkuInfo();
						sku.setId(skuId);
						sku.setGdsId(gdsVerifyReqDTO.getGdsId());
						sku.setShopId(gdsVerifyReqDTO.getShopId());
						sku.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES);
						skuInfos.add(sku);
					}
				}
			} else if (GdsConstants.GdsInfo.GDS_STATUS_DELETE.equals(operateType)) {
				// 删除
				gdsInfoReqDTO.setCompanyId(gdsVerifyReqDTO.getCompanyId());
				skuIds = deleteGdsInfo(gdsInfoReqDTO);
				if (CollectionUtils.isNotEmpty(skuIds)) {
					for (Long skuId : skuIds) {
						GdsSkuInfo sku = new GdsSkuInfo();
						sku.setId(skuId);
						sku.setGdsId(gdsVerifyReqDTO.getGdsId());
						sku.setShopId(gdsVerifyReqDTO.getShopId());
						sku.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_OFFSHELVES);
						skuInfos.add(sku);
					}
				}
			}else if (GdsConstants.GdsInfo.GDS_STATUS_OFFLINE.equals(operateType)) {
				// (已上架)更新覆盖
				GdsInfoAddReqDTO gdsInfoAddReqDTO = executeOfflineEdit01(gdsVerifyReqDTO, skuInfos, gdsInfoReqDTO);
				skuIds = editGdsInfoAndReference(gdsInfoAddReqDTO);
				//删除缓存
				delAllCache(gdsInfoAddReqDTO.getGdsInfoReqDTO().getId(), skuIds);
				//覆盖后删除线下记录
				GdsInfoReqDTO delCondition = new GdsInfoReqDTO();
				delCondition.setId(gdsVerifyReqDTO.getGdsId());
				delCondition.setShopId(gdsVerifyReqDTO.getShopId());
				delCondition.setCompanyId(gdsVerifyReqDTO.getCompanyId());
				deleteGdsInfo(delCondition);
				/*gdsVo.setOperateId(String.valueOf(gdsVerifyAndUpdateVO.getId()));
	        	gdsVo.setOperateFlag("9");
	        	gdsVo.setShopId(gdsVerifyAndUpdateVO.getShopId());
	        	gdsRemove(gdsVo);*/
		        if (CollectionUtils.isNotEmpty(skuIds)) {
		            for (Long skuId : skuIds) {
		                GdsSkuInfo sku = new GdsSkuInfo();
		                sku.setId(skuId);
		                sku.setGdsId(gdsVerifyReqDTO.getGdsId());
		                sku.setShopId(gdsVerifyReqDTO.getShopId());
		                sku.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES);
		                skuInfos.add(sku);
		            }
		        }
		       /* GdsInfoMessageDTO msg = new GdsInfoMessageDTO();
		        msg.setGdsId(gdsInfoAddReqDTO.getGdsInfoReqDTO().getId());
		        msg.setSkuIds(skuIds);
		        msg.setGdsStatus(gdsInfoAddReqDTO.getGdsInfoReqDTO().getGdsStatus());
		        msg.setCatlogId(gdsInfoAddReqDTO.getGdsInfoReqDTO().getCatlogId());
		        ThreadPoolExecutorUtil.commitTask(new GdsInfoMessageTask(msg));*/

			}
		}
		return skuInfos;
	}

	
	private GdsInfoAddReqDTO executeOfflineEdit01(GdsVerifyReqDTO gdsVerifyReqDTO, List<GdsSkuInfo> skuInfos,
            GdsInfoReqDTO gdsInfoReqDTO) {
        //List<Long> skuIds;
        // (已上架)更新
        //获取审核记录对应商品记录
        GdsInfoReqDTO gdsInfoReqDTO2 = new GdsInfoReqDTO();
        gdsInfoReqDTO2.setId(gdsVerifyReqDTO.getGdsId());
        gdsInfoReqDTO2.setShopId(gdsVerifyReqDTO.getShopId());
        GdsQueryOption[] gdsQueryOptions = new GdsQueryOption[] { GdsQueryOption.ALL};
        SkuQueryOption[] skuQueryOptions = new SkuQueryOption[] { SkuQueryOption.ALL};
        gdsInfoReqDTO2.setGdsQueryOptions(gdsQueryOptions);
        gdsInfoReqDTO2.setSkuQuerys(skuQueryOptions);
        GdsInfoRespDTO result = iGdsInfoQueryRSV.queryGdsInfoByOption(gdsInfoReqDTO2);
        //List<GdsInfoRespDTO> list= result.getResult();
        //获取将要被替换的记录当前的状态(可能在审核过程中旧记录的状态已改变)
        GdsInfoReqDTO reqDTO = new GdsInfoReqDTO();
        reqDTO.setId(Long.valueOf(result.getExt1()));
        reqDTO.setShopId(result.getShopId());
        GdsInfoRespDTO resultOld = iGdsInfoQueryRSV.queryGdsInfoByOption(reqDTO);
        String gdsStatus = resultOld.getGdsStatus();
        
        //对应商品记录
        List<GdsScoreExtReqDTO> gdsScoreExtReqDTOs = new ArrayList<>();
        List<GdsGds2MediaReqDTO> gds2MediaReqDTOs = new ArrayList<>();
        List<GdsSkuInfoReqDTO> skuInfoReqDTOs = new ArrayList<>();
        List<GdsSku2PriceReqDTO> sku2PriceReqDTOs = new ArrayList<>();
       // if(CollectionUtils.isNotEmpty(list)){
//            ObjectCopyUtil.copyObjValue(list.get(0), gdsInfoReqDTO, null, false);
//            GdsQueryOption[] gdsQueryOptions = new GdsQueryOption[] { GdsQueryOption.ALL};
//            SkuQueryOption[] skuQueryOptions = new SkuQueryOption[] { SkuQueryOption.ALL};
//            gdsInfoReqDTO.setGdsQueryOptions(gdsQueryOptions);
//            gdsInfoReqDTO.setSkuQuerys(skuQueryOptions);
            //GdsInfoDetailRespDTO gdsInfoDetailRespDTO = iGdsInfoQueryRSV.queryGdsInfoDetail(gdsInfoReqDTO);
  //          GdsInfoRespDTO gdsInfoRespDTO = iGdsInfoQueryRSV.queryGdsInfoByOption(gdsInfoReqDTO);
        if(result!=null){
        	 ObjectCopyUtil.copyObjValue(result, gdsInfoReqDTO, null, false);
             gdsInfoReqDTO.setId(Long.valueOf(result.getExt1()));
             gdsInfoReqDTO.setGdsStatus(gdsStatus);
             gdsInfoReqDTO.setGdsApprove(GdsConstants.GdsInfo.GDS_APPROVE_PASS);
             gdsInfoReqDTO.setExt1("");
        }
        
            List<GdsSkuInfoRespDTO> skuList = result.getSkus();
            
            for (Iterator<GdsSkuInfoRespDTO> iterator = skuList.iterator(); iterator.hasNext();) {
                GdsSkuInfoRespDTO gdsSkuInfoRespDTO = iterator.next();
                
                GdsSkuInfoReqDTO gdsSkuInfoReqDTO = new GdsSkuInfoReqDTO();
                if(gdsSkuInfoRespDTO.getExt2()!=null && gdsSkuInfoRespDTO.getExt2()!=""){
                	gdsSkuInfoRespDTO.setId(Long.valueOf(gdsSkuInfoRespDTO.getExt2()));
                }else{
                	gdsSkuInfoRespDTO.setId(null);
                }
                gdsSkuInfoRespDTO.setGdsId(Long.valueOf(result.getExt1()));
                gdsSkuInfoRespDTO.setGdsStatus(gdsStatus);
                gdsSkuInfoRespDTO.setGdsApprove(GdsConstants.GdsInfo.GDS_APPROVE_PASS);
                gdsSkuInfoRespDTO.setExt2("");
                gdsSkuInfoRespDTO.setExt1("");
                ObjectCopyUtil.copyObjValue(gdsSkuInfoRespDTO, gdsSkuInfoReqDTO, null, false);
                //获取companyId
                ShopInfoResDTO shopInfo = shopInfoRSV.findShopInfoByShopID(gdsSkuInfoRespDTO.getShopId());
                Long companyId = null;
                if(StringUtil.isNotEmpty(shopInfo)){
                    companyId = shopInfo.getCompanyId();
                }else{
                    throw new BusinessException("web.gds.2000012");
                }
                if(StringUtil.isEmpty(companyId)){
                    throw new BusinessException("web.gds.2000013");
                }
                gdsSkuInfoReqDTO.setCompanyId(companyId);
                //转换属性值
                List<GdsSku2PropReqDTO> gdsSku2PropReqDTOs = new ArrayList<>();
                for (GdsPropRespDTO gdsPropRespDTO : gdsSkuInfoRespDTO.getProps()) {
                	 GdsSku2PropReqDTO gdsSku2PropReqDTO = new GdsSku2PropReqDTO();
                	 ObjectCopyUtil.copyObjValue(gdsPropRespDTO, gdsSku2PropReqDTO, null, false);
                	 gdsSku2PropReqDTO.setPropId(gdsPropRespDTO.getId());
                	 gdsSku2PropReqDTO.setPropValueId(gdsPropRespDTO.getValues().get(0).getId());
                	 gdsSku2PropReqDTO.setPropValue(gdsPropRespDTO.getValues().get(0).getPropValue());
                	 gdsSku2PropReqDTOs.add(gdsSku2PropReqDTO);
				}
                gdsSkuInfoReqDTO.setSku2PropReqDTOs(gdsSku2PropReqDTOs);
                
                skuInfoReqDTOs.add(gdsSkuInfoReqDTO);
                
                //sku2PriceReqDTOs
                if(CollectionUtils.isNotEmpty(gdsSkuInfoRespDTO.getSku2PriceRespDTOs())){
                    for (GdsSku2PriceRespDTO gdsSku2PriceRespDTO : gdsSkuInfoRespDTO.getSku2PriceRespDTOs()) {
                      //sku2PriceReqDTOs
                        GdsSku2PriceReqDTO gdsSku2PriceReqDTO = new GdsSku2PriceReqDTO();
                        ObjectCopyUtil.copyObjValue(gdsSku2PriceRespDTO, gdsSku2PriceReqDTO, null, false);
                        gdsSku2PriceReqDTO.setGdsId(Long.valueOf(result.getExt1()));
                        sku2PriceReqDTOs.add(gdsSku2PriceReqDTO);
                    }
                }else{

                    GdsSku2PriceReqDTO gdsSku2PriceReqDTO = new GdsSku2PriceReqDTO();
                    gdsSku2PriceReqDTO.setGdsId(Long.valueOf(result.getExt1()));
                    GdsPriceReqDTO gdsPriceReq = new GdsPriceReqDTO();
                    gdsPriceReq.setPrice(gdsSkuInfoRespDTO.getCommonPrice());
                    gdsSku2PriceReqDTO.setPrice(gdsPriceReq);
                    gdsSku2PriceReqDTO.setPriceId(1L);
                    gdsSku2PriceReqDTO.setPriceTypeCode(GdsConstants.GdsInfo.SKU_PRICE_TYPE_ORDINARY);
                    sku2PriceReqDTOs.add(gdsSku2PriceReqDTO);
                }
                
                gdsSkuInfoReqDTO.setSku2PriceReqDTOs(sku2PriceReqDTOs);
                
            }

            //gds2MediaReqDTOs
          
            if(CollectionUtils.isNotEmpty(result.getMedias())){
                for (GdsGds2MediaRespDTO gdsGds2MediaRespDTO : result.getMedias()) {
                	GdsGds2MediaReqDTO gdsGds2MediaReqDTO = new GdsGds2MediaReqDTO();
                    gdsGds2MediaRespDTO.setGdsId(Long.valueOf(result.getExt1()));
                    ObjectCopyUtil.copyObjValue(gdsGds2MediaRespDTO, gdsGds2MediaReqDTO, null, false);
                    gds2MediaReqDTOs.add(gdsGds2MediaReqDTO);
                }
            }
            //gdsScoreExtRespDTO
            
            if(CollectionUtils.isNotEmpty(result.getScores())){
                for (GdsScoreExtRespDTO gdsScoreExtRespDTO : result.getScores()) {
                	GdsScoreExtReqDTO gdsScoreExtReqDTO = new GdsScoreExtReqDTO();
                    gdsScoreExtRespDTO.setId(Long.valueOf(result.getExt1()));
                    ObjectCopyUtil.copyObjValue(gdsScoreExtRespDTO, gdsScoreExtReqDTO, null, false);
                    gdsScoreExtReqDTOs.add(gdsScoreExtReqDTO);
                }
            }
           
        //}
        
        LongReqDTO gdsId = new LongReqDTO();
        gdsId.setId(gdsVerifyReqDTO.getGdsId());
        //gds2PropReqDTOs skuProps
        List<GdsGds2PropReqDTO> gds2PropReqDTOs = new ArrayList<>();
        
        List<GdsGds2PropReqDTO> skuProps = new ArrayList<>();
        List<GdsGds2PropRespDTO> gdsGds2PropRespDTO = iGdsInfoQueryRSV.queryGds2PropsByGdsId(gdsId);
        if(CollectionUtils.isNotEmpty(gdsGds2PropRespDTO)){
            for (GdsGds2PropRespDTO gdsGds2PropRespDTO2 : gdsGds2PropRespDTO) {
            	GdsGds2PropReqDTO gds2PropReqDTO = new GdsGds2PropReqDTO();
                gdsGds2PropRespDTO2.setGdsId(Long.valueOf(result.getExt1()));
                gdsGds2PropRespDTO2.setGdsStatus(gdsStatus);
                ObjectCopyUtil.copyObjValue(gdsGds2PropRespDTO2, gds2PropReqDTO, null, false);
                if("1".equals(gdsGds2PropRespDTO2.getPropType())){
                	skuProps.add(gds2PropReqDTO);
                }else{
                	 gds2PropReqDTOs.add(gds2PropReqDTO);
                }
               
                
            }
        }
        //gds2CatgReqDTOs
        List<GdsGds2CatgReqDTO> gds2CatgReqDTOs = new ArrayList<>();
        
        List<GdsGds2CatgRespDTO> gdsGds2CatgRespDTO = iGdsInfoQueryRSV.queryGds2CatgsByGdsId(gdsId);
        if(CollectionUtils.isNotEmpty(gdsGds2CatgRespDTO)){
            for (GdsGds2CatgRespDTO gdsGds2CatgRespDTO2 : gdsGds2CatgRespDTO) {
            	GdsGds2CatgReqDTO gdsGds2CatgReqDTO = new GdsGds2CatgReqDTO();
                gdsGds2CatgRespDTO2.setGdsId(Long.valueOf(result.getExt1()));
                gdsGds2CatgRespDTO2.setGdsStatus(gdsStatus);
                ObjectCopyUtil.copyObjValue(gdsGds2CatgRespDTO2, gdsGds2CatgReqDTO, null, false);
                gds2CatgReqDTOs.add(gdsGds2CatgReqDTO);
            }
        }
        
        GdsInfoAddReqDTO gdsInfoAddReqDTO = new GdsInfoAddReqDTO();
        gdsInfoAddReqDTO.setGds2PropReqDTOs(gds2PropReqDTOs);
        gdsInfoAddReqDTO.setSkuProps(skuProps);
        gdsInfoAddReqDTO.setGds2CatgReqDTOs(gds2CatgReqDTOs);
        gdsInfoAddReqDTO.setSku2PriceReqDTOs(sku2PriceReqDTOs);
        gdsInfoAddReqDTO.setSkuInfoReqDTOs(skuInfoReqDTOs);
        gdsInfoAddReqDTO.setGds2MediaReqDTOs(gds2MediaReqDTOs);
        gdsInfoAddReqDTO.setGdsScoreExtReqDTOs(gdsScoreExtReqDTOs);
        gdsInfoAddReqDTO.setGdsInfoReqDTO(gdsInfoReqDTO);
        gdsInfoAddReqDTO.setIfLocalEdit("1");
        gdsInfoAddReqDTO.setStaff(StaffLocaleUtil.getStaff());
        return gdsInfoAddReqDTO;
        
        
        //ObjectCopyUtil.copyObjValue(gdsInfoReqDTO, gdsInfoAddReqDTO.getGdsInfoReqDTO(), null, false);
        /*skuIds = editGdsInfoAndReference(gdsInfoAddReqDTO);*/
        /*skuIds = executeGdsShelves(gdsInfoReqDTO);*/
        /*if (CollectionUtils.isNotEmpty(skuIds)) {
            for (Long skuId : skuIds) {
                GdsSkuInfo sku = new GdsSkuInfo();
                sku.setId(skuId);
                sku.setGdsId(gdsVerifyReqDTO.getGdsId());
                sku.setShopId(gdsVerifyReqDTO.getShopId());
                sku.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES);
                skuInfos.add(sku);
            }
        }*/
    }
	
	

	
	

	private GdsInfoReqDTO initGdsIfParam(GdsInfoReqDTO gdsInfoReqDTO) {

		if (StringUtil.isBlank(gdsInfoReqDTO.getIfDisperseStock())) {
			gdsInfoReqDTO.setIfDisperseStock(GdsConstants.Commons.STATUS_INVALID);
		}
		if (StringUtil.isBlank(gdsInfoReqDTO.getIfEntityCode())) {
			gdsInfoReqDTO.setIfEntityCode(GdsConstants.Commons.STATUS_INVALID);
		}
		if (StringUtil.isBlank(gdsInfoReqDTO.getIfFree())) {
			gdsInfoReqDTO.setIfFree(GdsConstants.Commons.STATUS_INVALID);
		}
		if (StringUtil.isBlank(gdsInfoReqDTO.getIfLadderPrice())) {
			gdsInfoReqDTO.setIfLadderPrice(GdsConstants.Commons.STATUS_INVALID);
		}
		if (StringUtil.isBlank(gdsInfoReqDTO.getIfNew())) {
			gdsInfoReqDTO.setIfNew(GdsConstants.Commons.STATUS_INVALID);
		}
		if (StringUtil.isBlank(gdsInfoReqDTO.getIfRecomm())) {
			gdsInfoReqDTO.setIfRecomm(GdsConstants.Commons.STATUS_INVALID);
		}
		if (StringUtil.isBlank(gdsInfoReqDTO.getIfSalealone())) {
			gdsInfoReqDTO.setIfSalealone(GdsConstants.Commons.STATUS_INVALID);
		}
		if (StringUtil.isBlank(gdsInfoReqDTO.getIfScoreGds())) {
			gdsInfoReqDTO.setIfScoreGds(GdsConstants.Commons.STATUS_INVALID);
		}
		return gdsInfoReqDTO;
	}
	
	@Override
	public int addGdsVerifyOffline(GdsVerifyReqDTO gdsVerifyReqDTO) throws BusinessException {

		// 上架校验
		if (GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES.equals(gdsVerifyReqDTO.getOperateType())) {
			// 上下架操作，只查询待上架，已上架，已下架单品
			Long gdsId = gdsVerifyReqDTO.getGdsId();
			String[] status = GdsUtils.getNoDeleteStatusArr();
			List<Long> skuIds = gdsInfoQuerySV.querySkuIdsGdsId(gdsId, status);

			if (CollectionUtils.isNotEmpty(skuIds)) {
				for (Long skuId : skuIds) {
					GdsSkuInfoReqDTO gdsSkuInfoReqDTO = new GdsSkuInfoReqDTO();
					gdsSkuInfoReqDTO.setId(skuId);
					gdsSkuInfoReqDTO.setGdsId(gdsId);

					GdsSkuInfoReqDTO query = new GdsSkuInfoReqDTO();
					query.setId(skuId);
					GdsSkuInfo before = gdsSkuInfoQuerySV.querySkuInfoFromDB(query);
					gdsSkuInfoManageSV.onShelvesCheck(gdsSkuInfoReqDTO, before);
				}
			}
		}

		// 获取商品的一些基本信息
		GdsInfoReqDTO dto = new GdsInfoReqDTO();
		dto.setId(gdsVerifyReqDTO.getGdsId());
		dto.setShopId(gdsVerifyReqDTO.getShopId());
		List<Long> propIds = new ArrayList<Long>();
		// 出版日期:1005作者:1001
		propIds.add(1005L);
		propIds.add(1001L);
		dto.setPropIds(propIds);
		dto.setGdsQueryOptions(new GdsQueryOption[] { GdsQueryOption.BASIC, GdsQueryOption.SHIPTEMPLATE, GdsQueryOption.PROP });
		PageResponseDTO<GdsInfoRespDTO> list = new PageResponseDTO<GdsInfoRespDTO>();
		list = gdsInfoQuerySV.queryGdsInfoListPage(dto);
		Long gdsTypeId = null;
		if (list != null && list.getResult() != null && list.getResult().size() > 0) {
			GdsInfoRespDTO respDto = list.getResult().get(0);
			gdsVerifyReqDTO.setIsbn(respDto.getIsbn());
			gdsVerifyReqDTO.setCatgCode(respDto.getMainCatgs());
			gdsVerifyReqDTO.setGdsName(respDto.getGdsName());
			gdsTypeId = respDto.getGdsTypeId();
		}
		/**
		 * 1、先失效掉该商品以前的历史记录（条件：审核通过、审核拒绝）status 置 0
		 */
		GdsVerifyShopIdxCriteria gdsVerifyShopIdxCriteria1 = new GdsVerifyShopIdxCriteria();
		GdsVerifyShopIdxCriteria.Criteria criteria1 = gdsVerifyShopIdxCriteria1.createCriteria();
		criteria1.andShopIdEqualTo(gdsVerifyReqDTO.getShopId());
		if (!StringUtil.isEmpty(gdsVerifyReqDTO.getGdsId())) {
			criteria1.andGdsIdEqualTo(gdsVerifyReqDTO.getGdsId());
		}
		if (!StringUtil.isEmpty(gdsVerifyReqDTO.getSkuId())) {
			criteria1.andSkuIdEqualTo(gdsVerifyReqDTO.getSkuId());
		}
		List<String> listStatus = new ArrayList<String>();
		listStatus.add(GdsConstants.GdsVerify.VERIFY_APPROVED);// 审核通过
		listStatus.add(GdsConstants.GdsVerify.VERIFY_REFUSE);// 审核拒绝
		criteria1.andVerifyStatusIn(listStatus);

		GdsVerifyShopIdx gdsVerifyShopIdx1 = new GdsVerifyShopIdx();
		gdsVerifyShopIdx1.setStatus(GdsConstants.Commons.STATUS_INVALID);
		gdsVerifyShopIdxMapper.updateByExampleSelective(gdsVerifyShopIdx1, gdsVerifyShopIdxCriteria1);
		/**
		 * 然后新增一条记录为有效的
		 */
		GdsVerifyShopIdx gdsVerifyShopIdx = new GdsVerifyShopIdx();
		ObjectCopyUtil.copyObjValue(gdsVerifyReqDTO, gdsVerifyShopIdx, null, false);
		Timestamp time = new Timestamp(System.currentTimeMillis());
		Long createStaff = gdsVerifyReqDTO.getStaff().getId();
		// 设置新增的记录为有效状态
		gdsVerifyShopIdx.setStatus(GdsConstants.Commons.STATUS_VALID);
		// 新增的记录，审核状态都为提交待审核状态（00）
		gdsVerifyShopIdx.setVerifyStatus(GdsConstants.GdsVerify.WAITE_VERIFY);
		gdsVerifyShopIdx.setGdsTypeId(gdsTypeId);
		gdsVerifyShopIdx.setOperateStaff(createStaff);
		gdsVerifyShopIdx.setOperateTime(time);
		gdsVerifyShopIdx.setCreateStaff(createStaff);
		gdsVerifyShopIdx.setCreateTime(time);
		return(gdsVerifyShopIdxMapper.insert(gdsVerifyShopIdx));
	}
	//新增审核-已上架商品线下记录的审核
	@Override
	public List<GdsSkuInfo> editGdsVerifyShelved(GdsVerifyReqDTO gdsVerifyReqDTO) throws BusinessException {
		/**
		 * 1、优先获取待审核状态的操作类型，防止后面更新了无法获取。
		 */
		GdsVerifyShopIdxCriteria gdsVerifyShopIdxQry = new GdsVerifyShopIdxCriteria();
		GdsVerifyShopIdxCriteria.Criteria criteriaQry = gdsVerifyShopIdxQry.createCriteria();
		criteriaQry.andShopIdEqualTo(gdsVerifyReqDTO.getShopId());
		if (!StringUtil.isEmpty(gdsVerifyReqDTO.getGdsId())) {
			criteriaQry.andGdsIdEqualTo(gdsVerifyReqDTO.getGdsId());
		}
		if (!StringUtil.isEmpty(gdsVerifyReqDTO.getSkuId())) {
			criteriaQry.andSkuIdEqualTo(gdsVerifyReqDTO.getSkuId());
		}
		criteriaQry.andVerifyStatusEqualTo(GdsConstants.GdsVerify.WAITE_VERIFY);
		List<GdsVerifyShopIdx> qryList = gdsVerifyShopIdxMapper.selectByExample(gdsVerifyShopIdxQry);
		String operateType = "";
		if (StringUtil.isNotEmpty(qryList) && qryList.size() > 0) {
			operateType = qryList.get(0).getOperateType();
		} else {
			throw new BusinessException();
		}

		/**
		 * 2、更新当前最新的记录 （条件：提交待审核状态的）
		 */
		GdsVerifyShopIdxCriteria gdsVerifyShopIdxCriteria = new GdsVerifyShopIdxCriteria();
		GdsVerifyShopIdxCriteria.Criteria criteria = gdsVerifyShopIdxCriteria.createCriteria();
		criteria.andShopIdEqualTo(gdsVerifyReqDTO.getShopId());
		if (!StringUtil.isEmpty(gdsVerifyReqDTO.getGdsId())) {
			criteria.andGdsIdEqualTo(gdsVerifyReqDTO.getGdsId());
		}
		if (!StringUtil.isEmpty(gdsVerifyReqDTO.getSkuId())) {
			criteria.andSkuIdEqualTo(gdsVerifyReqDTO.getSkuId());
		}
		criteria.andVerifyStatusEqualTo(GdsConstants.GdsVerify.WAITE_VERIFY);
		GdsVerifyShopIdx gdsVerifyShopIdx = new GdsVerifyShopIdx();
		ObjectCopyUtil.copyObjValue(gdsVerifyReqDTO, gdsVerifyShopIdx, null, false);
		Timestamp time = new Timestamp(System.currentTimeMillis());
		Long staff = gdsVerifyReqDTO.getStaff().getId();
		// 更新人
		gdsVerifyShopIdx.setUpdateStaff(staff);
		// 更新时间
		gdsVerifyShopIdx.setUpdateTime(time);
		// 审核时间
		gdsVerifyShopIdx.setVerifyTime(time);
		// 审核人
		gdsVerifyShopIdx.setVerifyStaff(staff);
		int operFlag = gdsVerifyShopIdxMapper.updateByExampleSelective(gdsVerifyShopIdx, gdsVerifyShopIdxCriteria);
		/**
		 * 3、审核后，返回审核结果
		 */
		/**
		 * 3、审核后，根据审核状态判断是否通过或者拒绝 1)、如果通过，则操作类型更新商品的状态
		 * 如果为上架操作类型（11），则调用商品的上架更新操作服务；如果为删除操作类型（99），则调用商品的删除更新操作服务
		 */
		List<GdsSkuInfo> skuInfos = new ArrayList<GdsSkuInfo>();
		List<Long> skuIds = null;
		if (GdsConstants.GdsVerify.VERIFY_APPROVED.equals(gdsVerifyReqDTO.getVerifyStatus())) {
			GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
			gdsInfoReqDTO.setId(gdsVerifyReqDTO.getGdsId());
			gdsInfoReqDTO.setShopId(gdsVerifyReqDTO.getShopId());
			if (GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES.equals(operateType)) {
				// 上架
				gdsInfoReqDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES);
				skuIds = executeGdsShelves(gdsInfoReqDTO);
				if (CollectionUtils.isNotEmpty(skuIds)) {
					for (Long skuId : skuIds) {
						GdsSkuInfo sku = new GdsSkuInfo();
						sku.setId(skuId);
						sku.setGdsId(gdsVerifyReqDTO.getGdsId());
						sku.setShopId(gdsVerifyReqDTO.getShopId());
						sku.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES);
						skuInfos.add(sku);
					}
				}
			} else if (GdsConstants.GdsInfo.GDS_STATUS_DELETE.equals(operateType)) {
				// 删除
				gdsInfoReqDTO.setCompanyId(gdsVerifyReqDTO.getCompanyId());
				skuIds = deleteGdsInfo(gdsInfoReqDTO);
				if (CollectionUtils.isNotEmpty(skuIds)) {
					for (Long skuId : skuIds) {
						GdsSkuInfo sku = new GdsSkuInfo();
						sku.setId(skuId);
						sku.setGdsId(gdsVerifyReqDTO.getGdsId());
						sku.setShopId(gdsVerifyReqDTO.getShopId());
						sku.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_OFFSHELVES);
						skuInfos.add(sku);
					}
				}
			}else if (GdsConstants.GdsInfo.GDS_STATUS_OFFLINE.equals(operateType)) {
				// (已上架)更新覆盖
				GdsInfoAddReqDTO gdsInfoAddReqDTO = executeOfflineEdit01(gdsVerifyReqDTO, skuInfos, gdsInfoReqDTO);
				skuIds = editGdsInfoAndReference(gdsInfoAddReqDTO);
				//删除缓存
				delAllCache(gdsInfoAddReqDTO.getGdsInfoReqDTO().getId(), skuIds);
				GdsInfoReqDTO delCondition = new GdsInfoReqDTO();
				delCondition.setId(gdsVerifyReqDTO.getGdsId());
				delCondition.setShopId(gdsVerifyReqDTO.getShopId());
				delCondition.setCompanyId(gdsVerifyReqDTO.getCompanyId());
				deleteGdsInfo(delCondition);
				/*gdsVo.setOperateId(String.valueOf(gdsVerifyAndUpdateVO.getId()));
	        	gdsVo.setOperateFlag("9");
	        	gdsVo.setShopId(gdsVerifyAndUpdateVO.getShopId());
	        	gdsRemove(gdsVo);*/
		        if (CollectionUtils.isNotEmpty(skuIds)) {
		            for (Long skuId : skuIds) {
		                GdsSkuInfo sku = new GdsSkuInfo();
		                sku.setId(skuId);
		                sku.setGdsId(gdsVerifyReqDTO.getGdsId());
		                sku.setShopId(gdsVerifyReqDTO.getShopId());
		                sku.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES);
		                skuInfos.add(sku);
		            }
		        }
			}
		}
		return skuInfos;
	}
	@Override
	public void delAllCache(Long gdsId, List<Long> skuIds) {
		GdsCacheUtil.delGdsInfoCache(gdsId);
		GdsCacheUtil.delGdsPicCache(gdsId);
		if (CollectionUtils.isNotEmpty(skuIds)) {
			for (Long skuId : skuIds) {
				GdsCacheUtil.delSkuInfoCache(skuId);
				GdsCacheUtil.delSkuPicCache(skuId);
			}
		}
	}
}
