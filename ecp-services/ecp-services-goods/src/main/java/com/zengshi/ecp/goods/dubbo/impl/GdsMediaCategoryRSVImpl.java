package com.zengshi.ecp.goods.dubbo.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.goods.dao.model.GdsMediaCategory;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.common.StringReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsMediaCategoryRSV;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsMediaCategorySV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
/**
 * 
 * 媒体分类管理实现类. <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月27日下午3:50:23  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6
 */
public class GdsMediaCategoryRSVImpl extends AbstractRSVImpl implements IGdsMediaCategoryRSV {
    @Resource(name="gdsMediaCategorySV")
    private IGdsMediaCategorySV gdsMediaCategorySV;

	@Override
	public GdsMediaCategoryRespDTO saveGdsMediaCategory(
			GdsMediaCategoryReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO);
        paramNullCheck(reqDTO.getCatgName(),true,"reqDTO.catgName");
        return gdsMediaCategorySV.saveGdsMediaCatg(reqDTO);
	}

	@Override
	public void deleteGdsMediaCategory(GdsMediaCategoryReqDTO reqDTO)
			throws BusinessException {
		 paramNullCheck(reqDTO,true);
        paramNullCheck(reqDTO.getCatgCode(),true, "reqDTO.catgCode");
        // 判断是否存在子级有效分类,如果存在则不允许删除当前分类信息.
        if(0 < gdsMediaCategorySV.executeCountSubMediaCatg(reqDTO.getCatgCode(), GdsConstants.Commons.STATUS_VALID)){
            throw new BusinessException(GdsErrorConstants.GdsMediaCategory.ERROR_GOODS_MEDIACATG_200203,new String[]{reqDTO.getCatgCode()});
        }
        gdsMediaCategorySV.executeDeleteGdsMediaCategoryByPK(reqDTO.getCatgCode(),reqDTO.getStaff().getId());
	}

	@Override
	public void editGdsMediaCategory(GdsMediaCategoryReqDTO reqDTO)
			throws BusinessException {
		paramNullCheck(reqDTO,true);
        paramNullCheck(reqDTO.getCatgCode(),true, "reqDTO.catgCode");
        paramNullCheck(reqDTO.getCatgName(),true, "reqDTO.catgName");
       /* if(gdsMediaCategorySV.queryExist(reqDTO.getCatgName(),reqDTO.getCatgLevel(),GdsConstants.Commons.STATUS_VALID)){
            throw new BusinessException(GdsErrorConstants.GdsMediaCategory.ERROR_GOODS_MEDIACATG_200205);
        }*/
        GdsMediaCategory record = new GdsMediaCategory();
        ObjectCopyUtil.copyObjValue(reqDTO, record, null, true);
        record.setUpdateStaff(reqDTO.getStaff().getId());
        gdsMediaCategorySV.edisGdsMediaCategory(record);
	}

	@Override
	public PageResponseDTO<GdsMediaCategoryRespDTO> queryGdsMediaCategoryPaging(
			GdsMediaCategoryReqDTO reqDTO) throws BusinessException {
		 paramNullCheck(reqDTO,false);
	     return gdsMediaCategorySV.queryGdsMediaCategoryRespDTOPaging(reqDTO);
	}

	@Override
	public GdsMediaCategoryRespDTO queryGdsMediaCategoryByPK(
			GdsMediaCategoryReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO, "reqDTO");
		paramNullCheck(reqDTO.getCatgCode(), "reqDTO.catgCode");
		GdsMediaCategory record = gdsMediaCategorySV.queryGdsMediaCategoryByPk(reqDTO.getCatgCode());
		if(null != record){
			return GdsUtils.doObjConvert(record, GdsMediaCategoryRespDTO.class, null, null);
		}
		return null;
	}

	@Override
	public void deleteGdsMediaCategoryByPK(StringReqDTO reqDTO)
			throws BusinessException {
		paramNullCheck(reqDTO,true);
        paramNullCheck(reqDTO.getValue(),"reqDTO.value");
        // 判断是否存在子级有效分类,如果存在则不允许删除当前分类信息.
        if(0 < gdsMediaCategorySV.executeCountSubMediaCatg(reqDTO.getValue(), GdsConstants.Commons.STATUS_VALID)){
            throw new BusinessException(GdsErrorConstants.GdsMediaCategory.ERROR_GOODS_MEDIACATG_200203,new String[]{reqDTO.getValue()});
        }
	    gdsMediaCategorySV.executeDeleteGdsMediaCategoryByPK(reqDTO.getValue(), reqDTO.getStaff().getId());
	}
   

	@Override
	public List<GdsMediaCategoryRespDTO> queryRootCategory(
			GdsMediaCategoryReqDTO reqDTO) throws BusinessException {
		    paramNullCheck(reqDTO, false);
		    paramNullCheck(reqDTO.getShopId(), "shopId");
	        if(null == reqDTO.getStatus()){
	            reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
	        }
	        // 设置默认层级为1,表示查询根分类.
	        reqDTO.setCatgLevel(GdsConstants.GdsCategory.TOP_LEVEL);
	        reqDTO.setPageSize(Integer.MAX_VALUE);
	        
	        PageResponseDTO<GdsMediaCategoryRespDTO> page = gdsMediaCategorySV.queryGdsMediaCategoryRespDTOPaging(reqDTO);
	        return page.getResult();
	}

	@Override
	public List<GdsMediaCategoryRespDTO> querySubCategory(
			GdsMediaCategoryReqDTO reqDTO) throws BusinessException {
		    paramNullCheck(reqDTO.getCatgParent(), false, "reqDTO.catgParent");
	        
	        if(null == reqDTO.getStatus()){
	            reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
	        }
			
			List<GdsMediaCategory> lst = gdsMediaCategorySV.querySubMediaCatgs(reqDTO.getCatgParent(), GdsConstants.Commons.STATUS_VALID);
			if(CollectionUtils.isNotEmpty(lst)){
				return GdsUtils.doConvert(lst, GdsMediaCategoryRespDTO.class, null, null);
			}
			return null;
	}

	@Override
	public Long executeCountSubMediaCatg(GdsMediaCategoryReqDTO reqDTO)
			throws BusinessException {
		paramNullCheck(reqDTO);
		paramNullCheck(reqDTO.getCatgCode(), "catgCode");
		return gdsMediaCategorySV.executeCountSubMediaCatg(reqDTO.getCatgCode(), GdsConstants.Commons.STATUS_VALID);
	}
	
	@Override
    public List<GdsMediaCategoryRespDTO> queryCategoryTraceUpon(
            GdsMediaCategoryReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO, "reqDTO");
        paramNullCheck(reqDTO.getCatgCode(), "reqDTO.catgCode");
        return gdsMediaCategorySV.queryCategoryTraceUpon(reqDTO);
    }

}

