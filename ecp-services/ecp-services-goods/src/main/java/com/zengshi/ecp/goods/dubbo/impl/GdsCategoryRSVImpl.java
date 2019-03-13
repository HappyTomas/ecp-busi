package com.zengshi.ecp.goods.dubbo.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.zengshi.ecp.goods.dao.model.GdsCatg2Prop;
import com.zengshi.ecp.goods.dao.model.GdsGds2Catg;
import com.zengshi.ecp.goods.dao.model.GdsProp;
import com.zengshi.ecp.goods.dubbo.comparator.GdsPropRespDTOComparator;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PGroupReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PGroupRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PropRelationRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PropRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropGroupReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropGroupRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.category.GdsCategoryCompareReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.category.GdsCategoryCompareRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.common.StringReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2CatgReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.goods.dubbo.util.GdsCategoryCacheUtil;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsInfoManageSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsInfoQuerySV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfoidx.IGdsInfoIDXSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfores.IGdsInfo2CatgSV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsCategorySV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsCatg2PGroupSV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsCatg2PropSV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsPropGroupSV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsPropSV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsPropValueSV;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
/**
 * 
 * 商品分类管理实现类. <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月27日下午3:50:23  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6
 */
public class GdsCategoryRSVImpl extends AbstractRSVImpl implements IGdsCategoryRSV {
    @Resource(name="gdsCategorySV")
    private IGdsCategorySV gdsCategorySV;
    @Resource(name="gdsCatg2PropSV")
    private IGdsCatg2PropSV gdsCatg2PropSV;
    @Resource(name="gdsPropSV")
    private IGdsPropSV gdsPropSV;
    @Resource(name="gdsCatg2PGroupSV")
    private IGdsCatg2PGroupSV gdsCatg2PGroupSV;
    @Resource(name="gdsPropGroupSV")
    private IGdsPropGroupSV gdsPropGroupSV;
    @Resource(name="gdsPropValueSV")
    private IGdsPropValueSV gdsPropValueSV;
    @Resource(name="gdsInfoQuerySV")
    private IGdsInfoQuerySV gdsInfoQuerySV;
	@Resource
	private IGdsInfoIDXSV gdsInfoIDXSV;
	
	@Autowired(required = false)
	private IGdsInfoManageSV gdsInfoManageSV;
	
	@Resource
	private IGdsInfo2CatgSV gdsInfo2CatgSV;
    
    

    @Override
    public GdsCategoryRespDTO saveGdsCategory(GdsCategoryReqDTO reqDTO) throws BusinessException {
          paramNullCheck(reqDTO,true);
          paramNullCheck(reqDTO.getCatgName(),true,"reqDTO.catgName");
          GdsCategoryRespDTO respDTO = gdsCategorySV.saveGdsCategory(reqDTO);
          reqDTO.setCatgCode(respDTO.getCatgCode());
          return gdsCategorySV.queryGdsCategoryByPK(reqDTO);
    }

    @Override
    public void deleteGdsCategory(GdsCategoryReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO,true);
        paramNullCheck(reqDTO.getCatgCode(),true, "reqDTO.catgCode");
        // 判断是否存在子级有效分类,如果存在则不允许删除当前分类信息.
        if(0 < gdsCategorySV.countSubCategory(reqDTO.getCatgCode(),GdsConstants.Commons.STATUS_VALID)){
            throw new BusinessException(GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200306);
        }
        gdsCategorySV.deleteGdsCategory(reqDTO);
        
    }

    @Override
    public void editGdsCategory(GdsCategoryReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO,true);
        paramNullCheck(reqDTO.getCatgCode(),true, "reqDTO.catgCode");
        paramNullCheck(reqDTO.getCatgName(),true, "reqDTO.catgName");
       /* if(gdsCategorySV.queryExist(reqDTO.getCatgName(),reqDTO.getCatgType(), reqDTO.getCatgLevel(),reqDTO.getShopId(), new String[]{reqDTO.getCatgCode()}, GdsConstants.Commons.STATUS_VALID)){
            throw new BusinessException(GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200307);
        }*/
        if(gdsCategorySV.editGdsCategory(reqDTO) <= 0){
            throw new BusinessException(GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200301);
        }
    }

    @Override
    public PageResponseDTO<GdsCategoryRespDTO> queryGdsCategoryPaging(GdsCategoryReqDTO reqDTO)
            throws BusinessException {
        paramNullCheck(reqDTO,false);
        return gdsCategorySV.queryGdsCatetoryRespDTOPaging(reqDTO);
    }
    
    @Override
    public PageResponseDTO<GdsCategoryRespDTO> queryGdsCategoryPagingWithAuth(GdsCategoryReqDTO reqDTO)
            throws BusinessException {
        paramNullCheck(reqDTO,false);
        return gdsCategorySV.queryGdsCatetoryRespDTOPagingWithAuth(reqDTO);
    }

    @Override
    public GdsCategoryRespDTO queryGdsCategoryByPK(GdsCategoryReqDTO reqDTO)
            throws BusinessException {
        paramNullCheck(reqDTO,false);
        paramNullCheck(reqDTO.getCatgCode(), false, "reqDTO.catgCode");
        // 获取查询请求状态，如果状态没有设置，则默认设置状态为有效状态。
      /*  if(StringUtil.isBlank(reqDTO.getStatus())){
            reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
        }*/
        GdsCategoryRespDTO respDTO = gdsCategorySV.queryGdsCategoryByPK(reqDTO);
		return respDTO;
    }

	@Override
	public GdsCatg2PropRelationRespDTO queryCategoryProps(
			GdsCatg2PropReqDTO reqDTO) throws BusinessException {
        if(null == reqDTO.getStatus()){
            reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
        }
		return gdsCatg2PropSV.queryCategoryProps(reqDTO);
	}
	
	

	@Override
    public void deleteGdsCategoryByPK(StringReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO, true);
        paramNullCheck(reqDTO.getValue(), false, "reqDTO.value");
     // 判断是否存在子级有效分类,如果存在则不允许删除当前分类信息.
        if(0 < gdsCategorySV.countSubCategory(reqDTO.getValue(),GdsConstants.Commons.STATUS_VALID)){
            throw new BusinessException(GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200306);
        }
        gdsCategorySV.executeDisableGdsCategory(reqDTO.getValue(), reqDTO.getStaff().getId());
        GdsCategoryCacheUtil.removeCategory(reqDTO.getValue());
    }

	@Override
	public List<GdsCategoryRespDTO> queryRootCategory(GdsCategoryReqDTO reqDTO)
			throws BusinessException {
        paramNullCheck(reqDTO, false);
		if(null ==reqDTO.getCatgType()){
			reqDTO.setCatgType(GdsConstants.GdsCategory.CATG_TYPE_1);
		}
        if(null == reqDTO.getStatus()){
            reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
        }
        // 设置默认层级为1,表示查询根分类.
        reqDTO.setCatgLevel(GdsConstants.GdsCategory.TOP_LEVEL);
        reqDTO.setPageSize(Integer.MAX_VALUE);
        
        PageResponseDTO<GdsCategoryRespDTO> page = gdsCategorySV.queryGdsCatetoryRespDTOPaging(reqDTO);
        return page.getResult();
	}
	@Override
	public List<GdsCategoryRespDTO> queryExaCategory(GdsCategoryReqDTO reqDTO)
			throws BusinessException {
        paramNullCheck(reqDTO, false);
		if(null ==reqDTO.getCatgType()){
			reqDTO.setCatgType(GdsConstants.GdsCategory.CATG_TYPE_1);
		}
        if(null == reqDTO.getStatus()){
            reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
        }
        // 设置默认层级为1,表示查询根分类.
        if(reqDTO.getCatgLevel()==null || reqDTO.getCatgLevel()==0){
        	reqDTO.setCatgLevel(GdsConstants.GdsCategory.TOP_LEVEL);
        }
        reqDTO.setPageSize(Integer.MAX_VALUE);
        
        PageResponseDTO<GdsCategoryRespDTO> page = gdsCategorySV.queryGdsCatetoryRespDTOPaging(reqDTO);
        return page.getResult();
	}
	

	@Override
	public List<GdsCategoryRespDTO> queryRootCategoryWithAuth(
			GdsCategoryReqDTO reqDTO) throws BusinessException {
		    paramNullCheck(reqDTO, false);
			if(null ==reqDTO.getCatgType()){
				reqDTO.setCatgType(GdsConstants.GdsCategory.CATG_TYPE_1);
			}
	        if(null == reqDTO.getStatus()){
	            reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
	        }
	        // 设置默认层级为1,表示查询根分类.
	        reqDTO.setCatgLevel(GdsConstants.GdsCategory.TOP_LEVEL);
	        reqDTO.setPageSize(Integer.MAX_VALUE);
	        PageResponseDTO<GdsCategoryRespDTO> page = null;
        	reqDTO.setCatgLevel(null);
        	page = gdsCategorySV.queryGdsCatetoryRespDTOPagingWithAuth(reqDTO);

	        return filterRootCategory(page.getResult());
	        
	}
	
	
	@Override
	public List<GdsCategoryRespDTO> querySubCategoryWithAuth(
			GdsCategoryReqDTO reqDTO) throws BusinessException {
		    paramNullCheck(reqDTO.getCatgParent(), false, "reqDTO.catgParent");
	        
	        if(null == reqDTO.getStatus()){
	            reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
	        }
			
	        reqDTO.setPageSize(Integer.MAX_VALUE);
	        
	        PageResponseDTO<GdsCategoryRespDTO> page = null;
        	reqDTO.setCatgLevel(null);
        	page = gdsCategorySV.queryGdsCatetoryRespDTOPagingWithAuth(reqDTO);

		   return filterRootCategory(page.getResult());
	}

	@Override
	public List<GdsCategoryRespDTO> querySubCategory(GdsCategoryReqDTO reqDTO) {
		
        paramNullCheck(reqDTO.getCatgParent(), false, "reqDTO.catgParent");
        
        if(null == reqDTO.getStatus()){
            reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
        }
		
        reqDTO.setPageSize(Integer.MAX_VALUE);
        
        PageResponseDTO<GdsCategoryRespDTO> page = gdsCategorySV.queryGdsCatetoryRespDTOPaging(reqDTO);
        return page.getResult();
	}

	@Override
	public PageResponseDTO<GdsCatg2PropRespDTO> queryConfigedPropsPaging(
			GdsCatg2PropReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO, false, "reqDTO");
        paramNullCheck(reqDTO.getCatgCode(), "reqDTO.catgCode");
        return gdsCatg2PropSV.queryConfigedGdsCatg2PropRespDTOPaging(reqDTO);
	}

	@Override
	public PageResponseDTO<GdsPropRespDTO> queryOptionalPropsPaging(
			GdsCatg2PropReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO, false, "reqDTO");
        paramNullCheck(reqDTO.getCatgCode(), "reqDTO.catgCode");
        List<Long> excludePropIds = gdsCatg2PropSV.queryConfigedPropIds(reqDTO.getCatgCode(), GdsConstants.Commons.STATUS_VALID);
        GdsPropReqDTO propReqDTO = new GdsPropReqDTO();
        ObjectCopyUtil.copyObjValue(reqDTO, propReqDTO, null, true);
        propReqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
        propReqDTO.setPropName(reqDTO.getPropName());
        
        return gdsPropSV.queryGdsPropRespDTOPaging(propReqDTO, excludePropIds, null);
	}

	@Override
	public GdsCategoryRespDTO queryRootCategoryByPK(GdsCategoryReqDTO reqDTO)
			throws BusinessException {
		paramNullCheck(reqDTO, "reqDTO");
		paramNullCheck(reqDTO.getCatgCode(), "reqDTO.catgCode");
		return  gdsCategorySV.queryRootCategoryByPK(reqDTO.getCatgCode());
	}

	@Override
	public void addProps(GdsCatg2PropReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO, "reqDTO");
		paramCheck(new Object[]{reqDTO.getCatgCode(),reqDTO.getPropIds()}, new String[]{"reqDTO.catgCode","reqDTO.propIds"});
		List<Long> propIds = reqDTO.getPropIds();
		
		if(CollectionUtils.isNotEmpty(propIds)){
			Long createStaff = (null == reqDTO.getCreateStaff() ? reqDTO.getStaff().getId() : reqDTO.getCreateStaff());
			gdsCatg2PropSV.batchAddGdsCatg2Props(reqDTO.getCatgCode(), propIds, createStaff , true);
		}
		
	}

	@Override
	public void deleteProps(GdsCatg2PropReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO, "reqDTO");
		paramCheck(new Object[]{reqDTO.getCatgCode(),reqDTO.getPropIds()}, new String[]{"reqDTO.catgCode","reqDTO.propIds"});
		List<Long> propIds = reqDTO.getPropIds();
		
		if(CollectionUtils.isNotEmpty(propIds)){
			Long createStaff = (null == reqDTO.getCreateStaff() ? reqDTO.getStaff().getId() : reqDTO.getCreateStaff());
			gdsCatg2PropSV.batchDeleteCatg2Prop(reqDTO.getCatgCode(), propIds, createStaff);
		}
	}

	@Override
	public void addGroups(GdsCatg2PGroupReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO, "reqDTO");
		paramCheck(new Object[]{reqDTO.getCatgCode(),reqDTO.getGroupIds()}, new String[]{"reqDTO.catgCode","reqDTO.groupIds"});
		List<Long> ids = reqDTO.getGroupIds();
		
		if(CollectionUtils.isNotEmpty(ids)){
			Long createStaff = (null == reqDTO.getCreateStaff() ? reqDTO.getStaff().getId() : reqDTO.getCreateStaff());
			
			for(Long id : ids){
				GdsPropGroupRespDTO groupRespDTO = gdsPropGroupSV.queryGdsPropGroupAndProps(id);
				if(null != groupRespDTO && CollectionUtils.isNotEmpty(groupRespDTO.getProps())){
					List<Long> propIds = new ArrayList<Long>();
					for(GdsPropRespDTO propRespDTO : groupRespDTO.getProps()){
						propIds.add(propRespDTO.getId());
					}
					gdsCatg2PropSV.batchAddGdsCatg2Props(reqDTO.getCatgCode(), propIds, createStaff, true);
				}
			}
		}
	}

	@Override
	public void deleteGroups(GdsCatg2PGroupReqDTO reqDTO)
			throws BusinessException {
		paramNullCheck(reqDTO, "reqDTO");
		paramCheck(new Object[]{reqDTO.getCatgCode(),reqDTO.getGroupIds()}, new String[]{"reqDTO.catgCode","reqDTO.groupIds"});
		List<Long> ids = reqDTO.getGroupIds();
		
		if(CollectionUtils.isNotEmpty(ids)){
			Long createStaff = (null == reqDTO.getCreateStaff() ? reqDTO.getStaff().getId() : reqDTO.getCreateStaff());
			gdsCatg2PGroupSV.batchDeleteCatg2Prop(reqDTO.getCatgCode(), ids, createStaff);
		}
	}

	@Override
	public void executeIsBaseConfig(GdsCatg2PropReqDTO reqDTO)
			throws BusinessException {
		paramCheck(new Object[]{reqDTO.getCatgCode(),reqDTO.getPropId(),reqDTO.getIfBasic()}, new String[]{"reqDTO.catgCode","reqDTO.propId","reqDTO.ifBasic"});
		gdsCatg2PropSV.executeIsBaseConfig(reqDTO.getCatgCode(), reqDTO.getPropId(), reqDTO.getIfBasic(), reqDTO.getStaff().getId());
	}
	
	
	@Override
	public void executeIsRequireConfig(GdsCatg2PropReqDTO reqDTO)
			throws BusinessException {
		paramCheck(new Object[]{reqDTO.getCatgCode(),reqDTO.getPropId(),reqDTO.getIfHaveto()}, new String[]{"reqDTO.catgCode","reqDTO.propId","reqDTO.ifHaveto"});
		gdsCatg2PropSV.executeIsRequireConfig(reqDTO.getCatgCode(), reqDTO.getPropId(), reqDTO.getIfHaveto(), reqDTO.getStaff().getId());
	}
	
	
	@Override
	public void executeIsSearchConfig(GdsCatg2PropReqDTO reqDTO)
			throws BusinessException {
		paramCheck(new Object[]{reqDTO.getCatgCode(),reqDTO.getPropId(),reqDTO.getIfSearch()}, new String[]{"reqDTO.catgCode","reqDTO.propId","reqDTO.ifSearch"});
		gdsCatg2PropSV.executeIsSearchConfig(reqDTO.getCatgCode(), reqDTO.getPropId(), reqDTO.getIfSearch(), reqDTO.getStaff().getId());
	}
	
	

	@Override
	public GdsCatg2PropRelationRespDTO querySearchProps(
			GdsCatg2PropReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO, "reqDTO");
		paramNullCheck(reqDTO.getCatgCode(), "reqDTO.catgCode");
		reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
		reqDTO.setIfSearch(GdsConstants.GdsCatg2Prop.IS_SEARCH_1);
		reqDTO.setPageSize(Integer.MAX_VALUE);
		// return gdsCatg2PropSV.queryCategoryPropsByCondition(reqDTO);
		List<GdsCatg2Prop> lst = gdsCatg2PropSV.queryConfigedPropsWithParents(reqDTO);
		GdsCatg2PropRelationRespDTO result = new GdsCatg2PropRelationRespDTO();
		List<GdsPropRespDTO> searchProps = new ArrayList<GdsPropRespDTO>();
		if (!CollectionUtils.isEmpty(lst)) {
			for (GdsCatg2Prop gdsCatg2Prop : lst) {
				GdsProp prop = gdsPropSV.queryGdsPropByPK(
						gdsCatg2Prop.getPropId(), true);
				if (null == prop) {
					continue;
				}
				GdsPropRespDTO gdsPropRespDTO = new GdsPropRespDTO();
				ObjectCopyUtil.copyObjValue(prop, gdsPropRespDTO, null, true);
				gdsPropRespDTO.setValues(gdsPropValueSV
						.queryPropValuesByPropId(prop.getId(),
								GdsConstants.Commons.STATUS_VALID));
				searchProps.add(gdsPropRespDTO);
			}
		}
		
        Collections.sort(searchProps, new GdsPropRespDTOComparator());		
		result.setSearchParams(searchProps);
		return result;
	}
	@Override
	public PageResponseDTO<GdsCatg2PGroupRespDTO> queryConfigedPropgroupPaging(
			GdsCatg2PGroupReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO, "reqDTO");
		paramNullCheck(reqDTO.getCatgCode(), "reqDTO.catgCode");
		return gdsCatg2PGroupSV.queryConfigedGdsPropGroupRespDTOPaging(reqDTO);
	}

	@Override
	public PageResponseDTO<GdsPropGroupRespDTO> queryOptionalPropgroupPaging(
			GdsCatg2PGroupReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO, "reqDTO");
		paramNullCheck(reqDTO.getCatgCode(), "reqDTO.catgCode");
		
		//List<Long> ids = gdsCatg2PGroupSV.queryConfigedPropgroupsIds(reqDTO.getCatgCode(), GdsConstants.Commons.STATUS_VALID);
		GdsPropGroupReqDTO groupReqDTO = new GdsPropGroupReqDTO();
		ObjectCopyUtil.copyObjValue(reqDTO, groupReqDTO, null, true);
		groupReqDTO.setGroupName(reqDTO.getGroupName());
		groupReqDTO.setId(reqDTO.getPropGroupId());
		
		return gdsPropGroupSV.queryGdsPropGroupRespDTOPaging(groupReqDTO, null, null);
	}

	@Override
	public List<GdsCategoryRespDTO> queryCategoryTraceUpon(
			GdsCategoryReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO, "reqDTO");
		paramNullCheck(reqDTO.getCatgCode(), "reqDTO.catgCode");
		return gdsCategorySV.queryCategoryTraceUpon(reqDTO.getCatgCode());
	}
	
	
	
	@Override
	public GdsCategoryCompareRespDTO executeCompare(
			GdsCategoryCompareReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO, "reqDTO");
		paramCheck(new Object[]{reqDTO.getSourceCode(),reqDTO.getTargetCode()}, new String[]{"reqDTO.sourceCode","reqDTO.targetCode"});
		return gdsCategorySV.executeCompare(reqDTO);
	}
	
	

	@Override
	public void executeIfGdsInputConfig(GdsCatg2PropReqDTO reqDTO)
			throws BusinessException {
		paramCheck(new Object[]{reqDTO.getCatgCode(),reqDTO.getPropId(),reqDTO.getIfGdsInput()}, new String[]{"reqDTO.catgCode","reqDTO.propId","reqDTO.ifGdsInput"});
		gdsCatg2PropSV.executePropConfig(reqDTO);
	}
	
	

	@Override
	public Long countSubCategory(GdsCategoryReqDTO reqDTO)
			throws BusinessException {
		paramCheck(new Object[]{reqDTO.getCatgCode()}, new String[]{"reqDTO.catgCode"});
		return gdsCategorySV.countSubCategory(reqDTO.getCatgCode(), GdsConstants.Commons.STATUS_VALID);
	}
	
	

	@Override
	public GdsCategoryRespDTO queryCategoryLimitByCatgCodeAndTargetLevel(
			GdsCategoryReqDTO reqDTO) throws BusinessException {
		return gdsCategorySV.queryCategoryLimitByCatgCodeAndTargetLevel(reqDTO);
	}

	@Override
	public Long queryUneditableCatg2Prop(GdsCatg2PropReqDTO reqDTO)throws BusinessException{
		paramNullCheck(reqDTO);
		paramCheck(new Object[]{reqDTO.getCatgCode(),reqDTO.getPropIds()}, new String[]{"reqDTO.catgCode","reqDTO.propIds"});
		return gdsCatg2PropSV.queryUneditable(reqDTO.getCatgCode(), reqDTO.getPropIds());
	}
	
	
	
	@Override
	public void repairGds2Catg(BaseInfo baseInfo) throws BusinessException {
		boolean flag = true;
		int i = 1;
		while (flag) {
			LogUtil.warn(MODULE, "====================开始数据修复......");

			GdsInfoReqDTO gdsInfoReqDTO=new GdsInfoReqDTO();
			gdsInfoReqDTO.setPageNo(i);
			gdsInfoReqDTO.setPageSize(200);
//			gdsInfoReqDTO.setCatalogIds(ids);
			PageResponseDTO<GdsInfoRespDTO> gdsInfos=gdsInfoQuerySV.queryGdsInfoListPageALLDB(gdsInfoReqDTO);
			List<GdsInfoRespDTO> dataList = gdsInfos.getResult();
			
			
			//File file=new File("C:\\dev\\repair.logs");
			/*try {
				file.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}*/
			if (CollectionUtils.isNotEmpty(dataList)) {
				for (GdsInfoRespDTO gdsInfoRespDTO : dataList) {
					// 更新商品分类关系表
					GdsInfoReqDTO req=new GdsInfoReqDTO();
					req.setId(gdsInfoRespDTO.getId());
					GdsInfoRespDTO resp=gdsInfoQuerySV.queryGdsInfoByOption(req);
					List<GdsGds2Catg> catgResp = gdsInfo2CatgSV.queryGds2CatgsModelByGdsId(gdsInfoRespDTO.getId());
					List<GdsGds2CatgReqDTO> catgReq = GdsUtils.doConvert(catgResp, GdsGds2CatgReqDTO.class);
					if (CollectionUtils.isNotEmpty(catgReq)) {
						for (GdsGds2CatgReqDTO gds2CatgReqDTO : catgReq) {
							gds2CatgReqDTO.setGdsStatus(resp.getGdsStatus());
//							gdsinfo.updateGds2Catg(gdsInfoRespDTO.getId(), 178L, gds2CatgReqDTO);
						}
					}
					// 更新属性索引表
					gdsInfoIDXSV.editGdsInfoIDX(null, catgReq, null);
					
					LogUtil.warn(MODULE, "=================正在修复id:"+gdsInfoRespDTO.getId());
					
				/*	try {
						FileUtils.writeStringToFile(file, "正在修复id:"+gdsInfoRespDTO.getId()+"\n", true);
					} catch (IOException e) {
						e.printStackTrace();
					}*/

				}
			} else {
				flag = false;
			}
			i++;
		}
		LogUtil.warn(MODULE, "====================数据修复结束!!!!!!!!");
	}
	
	
	
	
	
	@Override
	public List<GdsCategoryRespDTO> querySubCategoryConnectByCatgParent(
			GdsCategoryReqDTO reqDTO) throws BusinessException {
		return gdsCategorySV.querySubCategoryConnectByCatgParent(reqDTO);
	}

	//=====================================================
	//===================Private Methods===================
    //=====================================================
	/*
     * 
     * filterRootCategory:从结果集中过滤出最顶级分类。
     * 
     * @author liyong7
     * @param result 
     * @since JDK 1.6
     */
	private List<GdsCategoryRespDTO> filterRootCategory(List<GdsCategoryRespDTO> result) {
		if(CollectionUtils.isEmpty(result)){
			return result;
		}
		List<GdsCategoryRespDTO> lst = new ArrayList<GdsCategoryRespDTO>();
		Map<String, GdsCategoryRespDTO> map = toMap(result);
		List<String> rootCatgCodes = new ArrayList<String>();
		for (Iterator<GdsCategoryRespDTO> iterator = result.iterator(); iterator.hasNext();) {
			GdsCategoryRespDTO respDTO =  iterator.next();
			List<String> pathCodeLst = catgPathToArray(respDTO);
			for (Iterator<String> iterator2 = pathCodeLst.iterator(); iterator2
					.hasNext();) {
				String path =  iterator2.next();
				if(map.containsKey(path)){
					if(!rootCatgCodes.contains(path)){
						rootCatgCodes.add(path);
						lst.add(map.get(path));
					}
					break;
				}
			}
		}
		return lst;
	}
    /*
     * 
     * catgPathToArray:分类族谱转换成分类编码List. <br/> 
     * 
     * @author liyong7
     * @param respDTO
     * @return 
     * @since JDK 1.6
     */
	private List<String> catgPathToArray(GdsCategoryRespDTO respDTO) {
		String catgPath = respDTO.getCatgPath();
		catgPath = catgPath.replace("<", "");
		String[] ary = catgPath.split(">");
		return Arrays.asList(ary);
	}
    /*
     * 
     * toMap:查询结果转成Map. <br/> 
     * 
     * @author liyong7
     * @param result
     * @return 
     * @since JDK 1.6
     */
	private Map<String, GdsCategoryRespDTO> toMap(
			List<GdsCategoryRespDTO> result) {
		Map<String,GdsCategoryRespDTO> map = null;
		if(CollectionUtils.isNotEmpty(result)){
			map = new HashMap<String, GdsCategoryRespDTO>();
			for (Iterator<GdsCategoryRespDTO> iterator = result.iterator(); iterator.hasNext();) {
				GdsCategoryRespDTO respDTO =  iterator.next();
				map.put(respDTO.getCatgCode(), respDTO);
			}
		}
		return map;
	}
	/*
	 * 
	 * 递归查询所有子级分类。
	 * 
	 * @author liyong7
	 * @param respDTO 
	 * @since JDK 1.6
	 */
	private void recursiveQuery(GdsCategoryRespDTO respDTO){
		if(respDTO.hasChildren()){
			List<GdsCategoryRespDTO> lst = gdsCategorySV.querySubGdsCategory(respDTO.getCatgCode(), GdsConstants.Commons.STATUS_VALID);
			respDTO.setChildren(lst);
			for(GdsCategoryRespDTO temp : lst){
				recursiveQuery(temp);
			}
		}
	}

	@Override
	public GdsCatg2PropRelationRespDTO queryCategoryPropsByCondition(GdsCatg2PropReqDTO reqDTO){
		
		paramNullCheck(reqDTO, "reqDTO");
		paramNullCheck(reqDTO.getCatgCode(), "reqDTO.catgCode");
		reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
		reqDTO.setIfSearch(GdsConstants.GdsCatg2Prop.IS_SEARCH_1);
		reqDTO.setPageSize(Integer.MAX_VALUE);
		List<GdsCatg2Prop> lst = gdsCatg2PropSV.queryConfigedProps(reqDTO);
		GdsCatg2PropRelationRespDTO result = new GdsCatg2PropRelationRespDTO();
		List<GdsPropRespDTO> searchProps = new ArrayList<GdsPropRespDTO>();
		if (!CollectionUtils.isEmpty(lst)) {
			for (GdsCatg2Prop gdsCatg2Prop : lst) {
				GdsProp prop = gdsPropSV.queryGdsPropByPK(
						gdsCatg2Prop.getPropId(), true);
				if (null == prop) {
					continue;
				}
				GdsPropRespDTO gdsPropRespDTO = new GdsPropRespDTO();
				ObjectCopyUtil.copyObjValue(prop, gdsPropRespDTO, null, true);
				gdsPropRespDTO.setValues(gdsPropValueSV
						.queryPropValuesByPropId(prop.getId(),
								GdsConstants.Commons.STATUS_VALID));
				searchProps.add(gdsPropRespDTO);
			}
		}
		
        Collections.sort(searchProps, new GdsPropRespDTOComparator());		
		result.setSearchParams(searchProps);
		return result;
	}
    
	 @Override
	    public PageResponseDTO<GdsCategoryRespDTO> queryCustCommission(GdsCategoryReqDTO reqDTO)
	            throws BusinessException {
	        paramNullCheck(reqDTO,false);
	        return gdsCategorySV.queryCustCommissionPaging(reqDTO);
	    }

}

