package com.zengshi.ecp.goods.service.busi.impl.excelImp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsExcelImpMapper;
import com.zengshi.ecp.goods.dao.model.GdsExcelImp;
import com.zengshi.ecp.goods.dao.model.GdsExcelImpCriteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.excelImp.ExcelImportGdsModelDTO;
import com.zengshi.ecp.goods.dubbo.dto.excelImp.GdsExcelImpPropValueInfo;
import com.zengshi.ecp.goods.dubbo.dto.excelImp.GdsExcelImpReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.excelImp.GdsExcelImpRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoAddReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2CatgReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2PriceReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.price.GdsPriceReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoReqDTO;
import com.zengshi.ecp.goods.service.busi.interfaces.excelImp.IGdsExcelImpSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsInfoQuerySV;
import com.zengshi.ecp.goods.service.common.impl.AbstractSVImpl;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.MoneyUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GdsExcelImpSVImpl extends AbstractSVImpl implements IGdsExcelImpSV {

	@Resource
	private GdsExcelImpMapper gdsExcelImpMapper;
	@Resource
	private IShopInfoRSV shopInfoRSV;
	@Resource
	private IGdsInfoQuerySV gdsInfoQuerySV;
	
	
	private static final String MODULE = GdsExcelImpSVImpl.class.getName();
	// 正则表达式
	// 数字
	private static final String digitalReg = "[0-9]+";

	private static final String VALIDATE_ERROR_CATG_CODE = "分类编码不合法！";

	private void validateGdsExcelImpInfo(ExcelImportGdsModelDTO excelImportGdsModelDTO) {
		GdsExcelImp gdsExcelImp = new GdsExcelImp();

		//
		// if(excelImportGdsModelDTO.getGdsDetailDesc() ) {
		//
		// }

	}

	@Override
	public void addGdsExcelImp(GdsExcelImpReqDTO excelImpReqDTO) throws Exception {
		LogUtil.debug(MODULE, excelImpReqDTO.toString());
		GdsExcelImp excelImp = new GdsExcelImp();
		ObjectCopyUtil.copyObjValue(excelImpReqDTO, excelImp, null, false);
		if(null!=excelImp.getGdsName()&&128<excelImp.getGdsName().trim().length()){
			excelImp.setGdsName(excelImp.getGdsName().subSequence(0, 120)+"...");
		}
		if(null!=excelImp.getGdsTitle()&&100<excelImp.getGdsTitle().trim().length()){
			excelImp.setGdsTitle(excelImp.getGdsTitle().subSequence(0, 90)+"...");
		}
		if(null!=excelImp.getFailReason()&&128<excelImp.getFailReason().trim().length()){
			excelImp.setFailReason(excelImp.getFailReason().subSequence(0, 120)+"...");
		}
		if(null!=excelImp.getGdsPropStr()&&1024<excelImp.getGdsPropStr().trim().length()){
			excelImp.setGdsPropStr(excelImp.getGdsPropStr().subSequence(0, 1016)+"...");
		}
		gdsExcelImpMapper.insertSelective(excelImp);

	}

	@Override
	public PageResponseDTO<GdsExcelImpRespDTO> queryGdsExcelImpByImportId(GdsExcelImpReqDTO excelImpReqDTO)
			throws Exception {
		GdsExcelImpCriteria example = new GdsExcelImpCriteria();
		GdsExcelImpCriteria.Criteria criteria = example.createCriteria();
		criteria.andImportIdEqualTo(excelImpReqDTO.getImportId());
	
		if (!StringUtil.isBlank(excelImpReqDTO.getStatus())) {

			if (GdsConstants.Commons.STATUS_VALID.equals(excelImpReqDTO.getStatus())) {

				criteria.andFailReasonIsNull();
			} else if (GdsConstants.Commons.STATUS_INVALID.equals(excelImpReqDTO.getStatus())) {
				criteria.andFailReasonIsNotNull();
			}
		}
		example.setLimitClauseStart(excelImpReqDTO.getStartRowIndex());
		example.setLimitClauseCount(excelImpReqDTO.getPageSize());
		return this.queryByPagination(excelImpReqDTO, example, true,
				new PaginationCallback<GdsExcelImp, GdsExcelImpRespDTO>() {

					@Override
					public List<GdsExcelImp> queryDB(BaseCriteria arg0) {

						return gdsExcelImpMapper.selectByExample((GdsExcelImpCriteria) arg0);
					}

					@Override
					public long queryTotal(BaseCriteria arg0) {
						return gdsExcelImpMapper.countByExample((GdsExcelImpCriteria) arg0);
					}

					@Override
					public GdsExcelImpRespDTO warpReturnObject(GdsExcelImp arg0) {
						GdsExcelImpRespDTO gdsExcelImpRespDTO = new GdsExcelImpRespDTO();
						ObjectCopyUtil.copyObjValue(arg0, gdsExcelImpRespDTO, null, false);
						return gdsExcelImpRespDTO;
					}
				});

	}

	@Override
	public GdsInfoAddReqDTO transformAddGdsExcelImp(GdsExcelImpReqDTO excelImpReqDTO)throws Exception {
		GdsInfoAddReqDTO addReqDTO = new GdsInfoAddReqDTO();
		GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
		gdsInfoReqDTO.setGdsName(excelImpReqDTO.getGdsName());
		gdsInfoReqDTO.setGdsTypeId(excelImpReqDTO.getGdsType());
		// 特别处理企业编码
		ShopInfoResDTO shopInfoResDTO = shopInfoRSV.findShopInfoByShopID(excelImpReqDTO.getShopId());
		gdsInfoReqDTO.setCompanyId(shopInfoResDTO.getCompanyId());
		gdsInfoReqDTO.setShopId(excelImpReqDTO.getShopId());
		//设置指导价
		gdsInfoReqDTO.setGuidePrice(excelImpReqDTO.getGdsPrice());
		//设置主分类
		gdsInfoReqDTO.setMainCatgs(excelImpReqDTO.getCatgCode());
		gdsInfoReqDTO.setGdsSubHead(excelImpReqDTO.getGdsTitle());
		addReqDTO.setCompanyId(shopInfoResDTO.getCompanyId());
	
		addReqDTO.setGdsInfoReqDTO(gdsInfoReqDTO);
		//拼接单品信息
		List<GdsSkuInfoReqDTO> gdsSkuInfoReqDTOs = new ArrayList<GdsSkuInfoReqDTO>();
		GdsSkuInfoReqDTO gdsSkuInfoReqDTO = new GdsSkuInfoReqDTO();
		gdsSkuInfoReqDTO.setRealCount(excelImpReqDTO.getGdsStock());
		gdsSkuInfoReqDTO.setGdsTypeId(excelImpReqDTO.getGdsType());
		gdsSkuInfoReqDTO.setCompanyId(shopInfoResDTO.getCompanyId());
		gdsSkuInfoReqDTO.setCommonPrice(excelImpReqDTO.getGdsPrice());
		gdsSkuInfoReqDTOs.add(gdsSkuInfoReqDTO);
		addReqDTO.setSkuInfoReqDTOs(gdsSkuInfoReqDTOs);
		//商品分类
		List<GdsGds2CatgReqDTO> catgReqDTOs = new ArrayList<GdsGds2CatgReqDTO>();
		addReqDTO.setGds2CatgReqDTOs(catgReqDTOs);
		GdsGds2CatgReqDTO catgReqDTO = new GdsGds2CatgReqDTO();
		catgReqDTO.setCatgCode(excelImpReqDTO.getCatgCode());
		catgReqDTO.setCatgType(GdsConstants.GdsCategory.CATG_TYPE_1);
		catgReqDTO.setGds2catgType("1");
		catgReqDTO.setGdsName(excelImpReqDTO.getGdsName());
		catgReqDTOs.add(catgReqDTO);
//		catgReqDTO.setCatgPath(catgPath);
		// 保存普通参数/扩展 属性信息到商品属性关系表
		List<GdsGds2PropReqDTO> props = new ArrayList<GdsGds2PropReqDTO>();

		// 保存单品属性到商品属性关系表
		List<GdsGds2PropReqDTO> skuParams = new ArrayList<GdsGds2PropReqDTO>();
		addReqDTO.setGds2PropReqDTOs(props);
		addReqDTO.setSkuProps(skuParams);
		if(StringUtil.isNotBlank(excelImpReqDTO.getGdsPropStr() )){
			if (excelImpReqDTO.getGdsPropStr() != null ) {
				JSONArray jsonArray = JSONArray.fromObject(excelImpReqDTO.getGdsPropStr());
				Iterator iterator = jsonArray.iterator();
				while (iterator.hasNext()) {

					JSONObject jsonObject = (JSONObject) iterator.next();
					GdsExcelImpPropValueInfo excelImpPropValueInfo =	(GdsExcelImpPropValueInfo) JSONObject.toBean(jsonObject, GdsExcelImpPropValueInfo.class);
					// 特别处理isbn号
//					if (1002 == excelImpPropValueInfo.getPropId()) {
//						gdsInfoReqDTO.setIsbn(excelImpPropValueInfo.getPropValue());
//					}
					GdsGds2PropReqDTO gds2PropReqDTO = new GdsGds2PropReqDTO(); 
					ObjectCopyUtil.copyObjValue(excelImpPropValueInfo, gds2PropReqDTO, null, false);
					//按照属性类型分别存放属性
					if(GdsConstants.GdsProp.PROP_TYPE_1.equals(excelImpPropValueInfo.getPropType())){
				
						skuParams.add(gds2PropReqDTO);
					}else{
						props.add(gds2PropReqDTO);
						
					}
					
				}
			}
		}
		


//单品价格
//		List<GdsSku2PriceReqDTO> prices = new ArrayList<GdsSku2PriceReqDTO>();
//		GdsSku2PriceReqDTO gdsSku2PriceReqDTO = new GdsSku2PriceReqDTO();
//		gdsSku2PriceReqDTO.setPriceTypeCode(GdsConstants.GdsInfo.SKU_PRICE_TYPE_ORDINARY);
//		GdsPriceReqDTO priceDto = new GdsPriceReqDTO();
//		priceDto.setPrice(excelImpReqDTO.getGdsPrice());
//		gdsSku2PriceReqDTO.setPrice(priceDto);
//		gdsSku2PriceReqDTO.setPriceTypeCode(GdsConstants.GdsInfo.SKU_PRICE_TYPE_ORDINARY);
//		prices.add(gdsSku2PriceReqDTO);
//		addReqDTO.setSku2PriceReqDTOs(prices);


		return addReqDTO;
	}

	
	@Override
	public GdsInfoAddReqDTO transformEditGdsExcelImp(GdsExcelImpReqDTO excelImpReqDTO)throws Exception {
		GdsInfoAddReqDTO addReqDTO = new GdsInfoAddReqDTO();
		addReqDTO.setStaff(excelImpReqDTO.getStaff());
		GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
		gdsInfoReqDTO.setStaff(excelImpReqDTO.getStaff());
		gdsInfoReqDTO.setGdsName(excelImpReqDTO.getGdsName());
		gdsInfoReqDTO.setId(excelImpReqDTO.getGdsId());
		gdsInfoReqDTO.setGdsSubHead(excelImpReqDTO.getGdsTitle());
		
	//获取商品信息
		
		GdsInfoRespDTO gdsInfoRespDTO = gdsInfoQuerySV.queryGdsInfoByOption(gdsInfoReqDTO, new GdsQueryOption[]{
				GdsQueryOption.BASIC,GdsQueryOption.CATG
		}, new SkuQueryOption[]{SkuQueryOption.BASIC});
		gdsInfoReqDTO.setGdsTypeId(gdsInfoRespDTO.getGdsTypeId());
//		// 特别处理企业编码
//		ShopInfoResDTO shopInfoResDTO = shopInfoRSV.findShopInfoByShopID(excelImpReqDTO.getShopId());
		gdsInfoReqDTO.setShopId(gdsInfoRespDTO.getShopId());
		//设置指导价
		gdsInfoReqDTO.setGuidePrice(gdsInfoRespDTO.getGuidePrice());
		//设置主分类
		gdsInfoReqDTO.setMainCatgs(gdsInfoRespDTO.getMainCatgs());
//		gdsInfoReqDTO.setGdsSubHead(gdsInfoRespDTO.getGdsSubHead());
	
		addReqDTO.setGdsInfoReqDTO(gdsInfoReqDTO);
		//拼接单品信息
		GdsSkuInfoRespDTO skuDto = gdsInfoRespDTO.getSkus().get(0);
		List<GdsSkuInfoReqDTO> gdsSkuInfoReqDTOs = new ArrayList<GdsSkuInfoReqDTO>();
		GdsSkuInfoReqDTO gdsSkuInfoReqDTO = new GdsSkuInfoReqDTO();
		gdsSkuInfoReqDTO.setStaff(excelImpReqDTO.getStaff());
		gdsSkuInfoReqDTO.setId(skuDto.getId());
		gdsSkuInfoReqDTOs.add(gdsSkuInfoReqDTO);
		addReqDTO.setSkuInfoReqDTOs(gdsSkuInfoReqDTOs);
	
//		商品分类
		List<GdsGds2CatgReqDTO> catgReqDTOs = new ArrayList<GdsGds2CatgReqDTO>();
		addReqDTO.setGds2CatgReqDTOs(catgReqDTOs);
		GdsGds2CatgReqDTO catgReqDTO = new GdsGds2CatgReqDTO();
		catgReqDTO.setStaff(excelImpReqDTO.getStaff());
		catgReqDTO.setCatgCode(		gdsInfoRespDTO.getMainCategory().getCatgCode());
		catgReqDTO.setCatgType(GdsConstants.GdsCategory.CATG_TYPE_1);
		catgReqDTO.setGds2catgType("1");
		catgReqDTO.setGdsName(excelImpReqDTO.getGdsName());
		catgReqDTOs.add(catgReqDTO);
		catgReqDTO.setCatgPath(gdsInfoRespDTO.getMainCategory().getCatgPath());
		// 保存普通参数/扩展 属性信息到商品属性关系表
		List<GdsGds2PropReqDTO> props = new ArrayList<GdsGds2PropReqDTO>();

		// 保存单品属性到商品属性关系表
		List<GdsGds2PropReqDTO> skuParams = new ArrayList<GdsGds2PropReqDTO>();
		addReqDTO.setGds2PropReqDTOs(props);
		addReqDTO.setSkuProps(skuParams);
		if(StringUtil.isNotBlank(excelImpReqDTO.getGdsPropStr() )){
			if (excelImpReqDTO.getGdsPropStr() != null ) {
				JSONArray jsonArray = JSONArray.fromObject(excelImpReqDTO.getGdsPropStr());
				Iterator iterator = jsonArray.iterator();
				while (iterator.hasNext()) {

					JSONObject jsonObject = (JSONObject) iterator.next();
					GdsExcelImpPropValueInfo excelImpPropValueInfo =	(GdsExcelImpPropValueInfo) JSONObject.toBean(jsonObject, GdsExcelImpPropValueInfo.class);
					// 特别处理isbn号
//					if (1002 == excelImpPropValueInfo.getPropId()) {
//						gdsInfoReqDTO.setIsbn(excelImpPropValueInfo.getPropValue());
//					}
					GdsGds2PropReqDTO gds2PropReqDTO = new GdsGds2PropReqDTO(); 
					gds2PropReqDTO.setStaff(excelImpReqDTO.getStaff());
					ObjectCopyUtil.copyObjValue(excelImpPropValueInfo, gds2PropReqDTO, null, false);
					//按照属性类型分别存放属性
					if(GdsConstants.GdsProp.PROP_TYPE_1.equals(excelImpPropValueInfo.getPropType())){
				
						skuParams.add(gds2PropReqDTO);
					}else{
						props.add(gds2PropReqDTO);
						
					}
					
				}
			}
		}
		


//单品价格
//		List<GdsSku2PriceReqDTO> prices = new ArrayList<GdsSku2PriceReqDTO>();
//		GdsSku2PriceReqDTO gdsSku2PriceReqDTO = new GdsSku2PriceReqDTO();
//		gdsSku2PriceReqDTO.setPriceTypeCode(GdsConstants.GdsInfo.SKU_PRICE_TYPE_ORDINARY);
//		GdsPriceReqDTO priceDto = new GdsPriceReqDTO();
//		priceDto.setPrice(excelImpReqDTO.getGdsPrice());
//		gdsSku2PriceReqDTO.setPrice(priceDto);
//		gdsSku2PriceReqDTO.setPriceTypeCode(GdsConstants.GdsInfo.SKU_PRICE_TYPE_ORDINARY);
//		prices.add(gdsSku2PriceReqDTO);
//		addReqDTO.setSku2PriceReqDTOs(prices);


		return addReqDTO;
	}

}
