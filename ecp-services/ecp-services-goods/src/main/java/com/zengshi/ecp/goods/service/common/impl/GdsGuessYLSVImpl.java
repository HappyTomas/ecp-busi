/** 
 * Project Name:ecp-services-goods 
 * File Name:GdsGuessYLSVImpl.java 
 * Package Name:com.zengshi.ecp.goods.service.common.impl 
 * Date:2015年8月20日上午11:18:23 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */
package com.zengshi.ecp.goods.service.common.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.goods.dao.mapper.common.GdsGuessYLMapper;
import com.zengshi.ecp.goods.dao.model.GdsCategory;
import com.zengshi.ecp.goods.dao.model.GdsGuessYL;
import com.zengshi.ecp.goods.dao.model.GdsGuessYLCriteria;
import com.zengshi.ecp.goods.dao.model.GdsGuessYLCriteria.Criteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsGuessHomePageRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsGuessYLReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsGuessYLRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoDetailRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsCatgCustDiscSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsInfoQuerySV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsSkuInfoQuerySV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsCategorySV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsGuessYLSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 平台推荐服务接口实现类。 <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月20日上午11:18:23 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public class GdsGuessYLSVImpl extends AbstractSVImpl implements IGdsGuessYLSV {

	@Resource(name = "seq_gds_platrecom")
	private PaasSequence seqGdsGuessYL;

	private static final String DEFAULT_ORDER_BY = "SORT_NO ASC,CREATE_TIME DESC";

	@Resource
	private GdsGuessYLMapper GdsGuessYLMapper;

	@Resource
	private IGdsSkuInfoQuerySV gdsSkuInfoQuerySV;

	@Resource
	private IGdsCategorySV gdsCategorySV;

	@Resource
	private IGdsInfoQuerySV gdsInfoQuerySV;

	@Resource
	private IGdsCatgCustDiscSV catgCustDiscSV;

	@Override
	public GdsGuessYL saveGdsGuessYL(GdsGuessYL gdsGuessYL) throws BusinessException {
		gdsGuessYL.setId(seqGdsGuessYL.nextValue());
		gdsGuessYL.setCreateTime(now());
		if (StringUtil.isBlank(gdsGuessYL.getStatus())) {
			gdsGuessYL.setStatus(GdsConstants.Commons.STATUS_VALID);
		}
		GdsGuessYLMapper.insert(gdsGuessYL);
		return gdsGuessYL;
	}

	@Override
	public Integer editGdsGuessYL(GdsGuessYL gdsGuessYL) throws BusinessException {

		return GdsGuessYLMapper.updateByPrimaryKeySelective(gdsGuessYL);
	}

	@Override
	public boolean queryExist(String catgCode, Long gdsId, Long skuId, String... status) throws BusinessException {
		StringBuffer errorMsg = new StringBuffer();
		if (StringUtil.isBlank(catgCode)) {
			errorMsg.append("catgCode,");
		}
		if (null == gdsId) {
			errorMsg.append("gdsId,");
		}
		if (null == skuId) {
			errorMsg.append("skuId,");
		}
		if (errorMsg.length() > 0) {
			errorMsg.deleteCharAt(errorMsg.length() - 1);
			throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100, new String[] { errorMsg.toString() });
		}
		GdsGuessYLCriteria criteria = new GdsGuessYLCriteria();
		Criteria c = criteria.createCriteria();
		c.andCatgCodeEqualTo(catgCode);
		c.andGdsIdEqualTo(gdsId);
		c.andSkuIdEqualTo(skuId);
		initStatusCriteria(c, status);
		return GdsGuessYLMapper.countByExample(criteria) > 0;
	}

	@Override
	public int deleteGdsGuessYL(Long id, Long updateStaff) throws BusinessException {
		return executeBatchDeleteGdsGuessYL(Arrays.asList(new Long[] { id }), updateStaff);
	}

	@Override
	public int executeBatchDeleteGdsGuessYL(List<Long> ids, Long updateStaff) throws BusinessException {
		if (ids != null && !ids.isEmpty()) {
			GdsGuessYL record = new GdsGuessYL();
			record.setUpdateStaff(updateStaff);
			record.setUpdateTime(now());
			record.setStatus(GdsConstants.Commons.STATUS_INVALID);
			GdsGuessYLCriteria criteria = new GdsGuessYLCriteria();
			Criteria c = criteria.createCriteria();
			if (ids.size() == 1) {
				c.andIdEqualTo(ids.get(0));
			} else {
				c.andIdIn(ids);
			}
			return GdsGuessYLMapper.updateByExampleSelective(record, criteria);
		}
		return 0;
	}

	@Override
	public PageResponseDTO<GdsGuessYLRespDTO> queryGdsGuessYLRespDTOPaging(GdsGuessYLReqDTO dto) throws BusinessException {
		GdsGuessYLCriteria criteria = new GdsGuessYLCriteria();
		if(dto!=null){
		    criteria.setLimitClauseStart(dto.getStartRowIndex());
		    criteria.setLimitClauseCount(dto.getPageSize());
		}
		criteria.setOrderByClause(DEFAULT_ORDER_BY);
		Criteria c = criteria.createCriteria();
		if (dto != null) {
			initCriteria(c, dto);
		}
		return super.queryByPagination(dto, criteria, false, new GdsGuessYLRespDTOPaginationCallback());
	}

	@Override
	public GdsGuessYLRespDTO queryGdsGuessYLByPK(Long id) throws BusinessException {
		GdsGuessYL gdsGuessYL = GdsGuessYLMapper.selectByPrimaryKey(id);
		GdsGuessYLRespDTO gdsGuessYLRespDTO = new GdsGuessYLRespDTO();
		ObjectCopyUtil.copyObjValue(gdsGuessYL, gdsGuessYLRespDTO, null, false);
		setExtractInfo(gdsGuessYL, gdsGuessYLRespDTO);
		return gdsGuessYLRespDTO;
	}

	@Override
	public GdsGuessYLRespDTO queryGdsGuessYLByPK(Long id, boolean isValid) throws BusinessException {
		GdsGuessYL gdsGuessYL = GdsGuessYLMapper.selectByPrimaryKey(id);
		GdsGuessYLRespDTO gdsGuessYLRespDTO = new GdsGuessYLRespDTO();
		ObjectCopyUtil.copyObjValue(gdsGuessYL, gdsGuessYLRespDTO, null, false);
		setExtractInfo(gdsGuessYL, gdsGuessYLRespDTO);
		if (gdsGuessYLRespDTO != null) {
			if (isValid && !GdsConstants.Commons.STATUS_VALID.equals(gdsGuessYLRespDTO.getStatus())) {
				gdsGuessYLRespDTO = null;
			}
		}
		return gdsGuessYLRespDTO;
	}

	/**
	 * 
	 * 标签分页查询回调实现类.<br>
	 * Project Name:ecp-services-goods <br>
	 * Description: <br>
	 * Date:2015年8月20日上午9:22:15 <br>
	 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
	 * 
	 * @author linwb3
	 * @version GdsLabelSVImpl
	 * @since JDK 1.6
	 */
	protected class GdsGuessYLRespDTOPaginationCallback extends PaginationCallback<GdsGuessYL, GdsGuessYLRespDTO> {

		@Override
		public List<GdsGuessYL> queryDB(BaseCriteria criteria) {
			return GdsGuessYLMapper.selectByExample((GdsGuessYLCriteria) criteria);
		}

		@Override
		public long queryTotal(BaseCriteria criteria) {
			return GdsGuessYLMapper.countByExample((GdsGuessYLCriteria) criteria);
		}

		@Override
		public GdsGuessYLRespDTO warpReturnObject(GdsGuessYL t) {
			GdsGuessYLRespDTO dto = new GdsGuessYLRespDTO();
			// TODO 需要封装单品名称进返回对象。
			// 查询列表需要获取单品名称，此处需要根据单品ID(skuId)获取单品信息或者单品名称封装入返回对象。
			// 此处可以结合缓存进行查询，若未使用缓存，则可以通过单品的SV查询获取单品实体。
			ObjectCopyUtil.copyObjValue(t, dto, null, true);
			setExtractInfo(t, dto);
			return dto;
		}

	}

	private void setExtractInfo(GdsGuessYL t, GdsGuessYLRespDTO dto) {
		GdsCategory gdsCategory = gdsCategorySV.queryGdsCategoryById(t.getCatgCode());
		if (gdsCategory != null) {
			dto.setCatgName(gdsCategory.getCatgName());
		}
	}

	/**
	 * 根据请求DTO设置查询条件. <br/>
	 * 
	 * @author linwb3
	 * @param criteria
	 * @param dto
	 * @since JDK 1.6
	 */
	private Criteria initCriteria(Criteria c, GdsGuessYLReqDTO dto) {
		if (StringUtil.isNotBlank(dto.getCatgCode())) {
			c.andCatgCodeEqualTo(dto.getCatgCode());
		}
		if (StringUtil.isNotBlank(dto.getGdsName())) {
			c.andGdsNameLike("%" + dto.getGdsName() + "%");
		}
		if (StringUtil.isNotBlank(dto.getStatus())) {
			c.andStatusEqualTo(dto.getStatus());
		} else {
			c.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
		}
		return c;
	}

	 @Override
	    public GdsGuessHomePageRespDTO queryGdsGuessForHomePage(GdsGuessYLReqDTO dto)
	            throws BusinessException {
	        GdsGuessHomePageRespDTO gdsGuessHomePageRespDTO = new GdsGuessHomePageRespDTO();

	        List<GdsCategoryRespDTO> categoryRespDTOs = new ArrayList<>();
	        List<GdsInfoDetailRespDTO> detailRespDTOs = new ArrayList<>();
	        queryGuessHomeForLimit(categoryRespDTOs,detailRespDTOs,0,5);
	        gdsGuessHomePageRespDTO.setCategoryRespDTOs(categoryRespDTOs);
	        gdsGuessHomePageRespDTO.setDetailRespDTOs(detailRespDTOs);
	        return gdsGuessHomePageRespDTO;
	    }

	    private void queryGuessHomeForLimit(List<GdsCategoryRespDTO> categoryRespDTOs,
	            List<GdsInfoDetailRespDTO> detailRespDTOs, int start, int size) throws BusinessException {

	        GdsGuessYLCriteria example = new GdsGuessYLCriteria();
	        GdsGuessYLCriteria.Criteria criteria = example.createCriteria();
	        criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
	        example.setLimitClauseStart(start);
	        example.setLimitClauseCount(size);
	        example.setOrderByClause(DEFAULT_ORDER_BY);
	        List<GdsGuessYL> gdsGuessYLs = GdsGuessYLMapper.selectByExample(example);
	        if (gdsGuessYLs != null && gdsGuessYLs.size() > 0) {
	            Set<String> catgSet = new HashSet<>();
	            for (GdsGuessYL gdsGuessYL : gdsGuessYLs) {
	                GdsInfoReqDTO infoReqDTO = new GdsInfoReqDTO();
	                infoReqDTO.setId(gdsGuessYL.getGdsId());
	                GdsQueryOption[] gdsQueryOptions = new GdsQueryOption[] { GdsQueryOption.BASIC,
	                        GdsQueryOption.PROP };
	                SkuQueryOption[] skuQueryOptions = new SkuQueryOption[] { SkuQueryOption.BASIC,
	                        SkuQueryOption.MAINPIC, SkuQueryOption.CAlDISCOUNT };
	                infoReqDTO.setGdsQueryOptions(gdsQueryOptions);
	                infoReqDTO.setSkuQuerys(skuQueryOptions);
	                GdsInfoDetailRespDTO detailRespDTO = gdsInfoQuerySV.queryGdsInfoDetail(infoReqDTO);
	                if (GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES.equals(detailRespDTO.getGdsStatus())) {
	                    detailRespDTOs.add(detailRespDTO);
	                    if (catgSet.contains(gdsGuessYL.getCatgCode())) {
	                        continue;
	                    }
	                    catgSet.add(gdsGuessYL.getCatgCode());
	                    GdsCategory category = gdsCategorySV.queryGdsCategoryById(gdsGuessYL
	                            .getCatgCode());
	                    GdsCategoryRespDTO categoryRespDTO = new GdsCategoryRespDTO();
	                    ObjectCopyUtil.copyObjValue(category, categoryRespDTO, null, false);
	                    categoryRespDTOs.add(categoryRespDTO);
	                }
	                
	                if(detailRespDTOs.size() == 5){
	                    
	                    break;
	                    
	                }
	            }
	            if (detailRespDTOs.size() < 5) {
	                queryGuessHomeForLimit(categoryRespDTOs, detailRespDTOs, start + size, size);

	            }
	        } else {

	            return;
	        }

	    }

	@Override
	public void deleteGdsGuessYLByGdsId(GdsGuessYLReqDTO dto) throws BusinessException {
		GdsGuessYLCriteria example = new GdsGuessYLCriteria();
		GdsGuessYLCriteria.Criteria criteria = example.createCriteria();
		criteria.andGdsIdEqualTo(dto.getGdsId());
		GdsGuessYL gdsGuessYL = new GdsGuessYL();
		gdsGuessYL.setStatus(GdsConstants.Commons.STATUS_INVALID);
		GdsGuessYLMapper.updateByExample(gdsGuessYL, example);

	}
}
