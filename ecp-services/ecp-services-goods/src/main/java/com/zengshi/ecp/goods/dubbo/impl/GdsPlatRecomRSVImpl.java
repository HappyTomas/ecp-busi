package com.zengshi.ecp.goods.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.zengshi.ecp.goods.dao.model.GdsPlatRecom;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsPlatRecomReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPlatRecomRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoDetailRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsPlatRecomRSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsInfoQuerySV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsPlatRecomSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;

public class GdsPlatRecomRSVImpl extends AbstractRSVImpl implements IGdsPlatRecomRSV{

	@Resource
	private IGdsPlatRecomSV gdsPlatRecomSV;
	@Resource
	private IGdsInfoQuerySV gdsInfoQuerySV;
	
	/**
	 * 添加猜你喜欢
	 * 其中商品编码,单品编码，单品名称必传
	 * TODO 简单描述该方法的实现功能（可选）. 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsPlatRecomRSV#saveGdsPlatRecom(com.zengshi.ecp.goods.dubbo.dto.GdsPlatRecomReqDTO)
	 */
	@Override
	public void saveGdsPlatRecom(GdsPlatRecomReqDTO reqDTO)
			throws BusinessException {
		paramNullCheck(reqDTO,true, "reqDTO");
		paramNullCheck(reqDTO.getGdsId(), "reqDTO.gdsId");
		paramNullCheck(reqDTO.getGdsName(), "reqDTO.gdsName");
		
		GdsPlatRecom GdsPlatRecom=new GdsPlatRecom();
		ObjectCopyUtil.copyObjValue(reqDTO, GdsPlatRecom, null, false);
		gdsPlatRecomSV.saveGdsPlatRecom(GdsPlatRecom);
	}

	/**
	 * 判断猜你记录是否存在
	 * TODO 简单描述该方法的实现功能（可选）. 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsPlatRecomRSV#queryExist(com.zengshi.ecp.goods.dubbo.dto.GdsPlatRecomReqDTO)
	 */
	@Override
	public Boolean queryExist(GdsPlatRecomReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO,true, "reqDTO");
		paramNullCheck(reqDTO.getGdsId(), "reqDTO.gdsId");
		
		return gdsPlatRecomSV.queryExist(reqDTO.getCatgCode(), reqDTO.getGdsId(), reqDTO.getSkuId(), GdsConstants.Commons.STATUS_VALID);
	}

	/**
	 * 删除猜你喜欢
	 * TODO 简单描述该方法的实现功能（可选）. 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsPlatRecomRSV#deleteGdsPlatRecom(com.zengshi.ecp.goods.dubbo.dto.GdsPlatRecomReqDTO)
	 */
	@Override
	public Integer deleteGdsPlatRecom(GdsPlatRecomReqDTO reqDTO)
			throws BusinessException {
		paramNullCheck(reqDTO,true, "reqDTO");
		paramNullCheck(reqDTO.getId(), "reqDTO.id");
		
		return gdsPlatRecomSV.deleteGdsPlatRecom(reqDTO.getId(), reqDTO.getStaff().getId());
	}

	
	/**
	 * 批量删除猜你喜欢
	 * TODO 简单描述该方法的实现功能（可选）. 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsPlatRecomRSV#deleteGdsPlatRecom(com.zengshi.ecp.goods.dubbo.dto.GdsPlatRecomReqDTO)
	 */
	@Override
	public Integer executeBatchDeleteGdsPlatRecom(GdsPlatRecomReqDTO reqDTO)
			throws BusinessException {
		paramNullCheck(reqDTO,true, "reqDTO");
		paramNullCheck(reqDTO.getIds(), "reqDTO.ids");
		return gdsPlatRecomSV.executeBatchDeleteGdsPlatRecom(reqDTO.getIds(), reqDTO.getStaff().getId());
	}

	/**
	 * 分页查询平台推荐信息
	 * 入参可选，分类编码，单品名称
	 * TODO 简单描述该方法的实现功能（可选）. 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsPlatRecomRSV#queryGdsPlatRecomRespDTOPaging(com.zengshi.ecp.goods.dubbo.dto.GdsPlatRecomReqDTO)
	 */
	@Override
	public PageResponseDTO<GdsPlatRecomRespDTO> queryGdsPlatRecomRespDTOPaging(
			GdsPlatRecomReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO,true, "reqDTO");
		return gdsPlatRecomSV.queryGdsPlatRecomRespDTOPaging(reqDTO);
	}

	/**
	 * 查询单个平台推荐信息
	 * TODO 简单描述该方法的实现功能（可选）. 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsPlatRecomRSV#queryGdsPlatRecomByPK(com.zengshi.ecp.goods.dubbo.dto.GdsPlatRecomReqDTO)
	 */
	@Override
	public GdsPlatRecomRespDTO queryGdsPlatRecomByPK(GdsPlatRecomReqDTO reqDTO)
			throws BusinessException {
		paramNullCheck(reqDTO,true, "reqDTO");
		paramNullCheck(reqDTO.getId(), "reqDTO.id");
		
		return gdsPlatRecomSV.queryGdsPlatRecomByPK(reqDTO.getId());
	}
	

	@Override
	public List<GdsInfoDetailRespDTO> queryGdsInfoDetailByCatgCode(
			GdsPlatRecomReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO);
		paramNullCheck(reqDTO.getCatgCode(), "reqDTO.catgCode");
		List<GdsInfoDetailRespDTO> lst = new ArrayList<GdsInfoDetailRespDTO>();	
		int size = reqDTO.getPageSize();
		long pageCount = 0L;
		long currentPage = 1L;
		
		reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
		PageResponseDTO<GdsPlatRecomRespDTO> page = gdsPlatRecomSV.queryGdsPlatRecomRespDTOPaging(reqDTO);
		
        pageCount = page.getPageCount();
        
        iteratorQuery(reqDTO, lst, pageCount, currentPage, page);
        
        if(CollectionUtils.isEmpty(lst)){
        	page.setPageNo(1);
        	reqDTO.setCatgCode(null);
        	currentPage = 1L;
        	reqDTO.setIfDefault(GdsConstants.Commons.STATUS_VALID);
        	page = gdsPlatRecomSV.queryGdsPlatRecomRespDTOPaging(reqDTO);
        	pageCount = page.getPageCount();
        	iteratorQuery(reqDTO, lst, pageCount, currentPage, page);
        }
               
		return lst;
	}

	/** 
	 * 根据分类查询推荐,如果数量未满足继续递归查询.<br/>
	 * 
	 * @author liyong7
	 * @param reqDTO
	 * @param lst
	 * @param pageCount
	 * @param currentPage
	 * @param page 
	 * @since JDK 1.6 
	 */ 
	private void iteratorQuery(GdsPlatRecomReqDTO reqDTO,
			List<GdsInfoDetailRespDTO> lst, long pageCount, long currentPage,
			PageResponseDTO<GdsPlatRecomRespDTO> page) {
		if(0 < pageCount && currentPage <= pageCount){
        	List<GdsPlatRecomRespDTO> result = page.getResult();
        	GdsInfoReqDTO condition = new GdsInfoReqDTO();
        	for(GdsPlatRecomRespDTO recomRespDTO : result){
        		condition.setId(recomRespDTO.getGdsId());
        		condition.setGdsQueryOptions(new GdsQueryOption[]{GdsQueryOption.BASIC});
        		condition.setSkuQuerys(new SkuQueryOption[]{SkuQueryOption.BASIC,SkuQueryOption.MAINPIC,SkuQueryOption.PROP,SkuQueryOption.CAlDISCOUNT});
        		GdsInfoDetailRespDTO detailRespDTO = null;
        		try{
        			detailRespDTO = gdsInfoQuerySV.queryGdsInfoDetail(condition);
            		if(null != detailRespDTO && null != detailRespDTO.getId()){
            			lst.add(detailRespDTO);
            		}
        		}catch (Exception e) {
        			LogUtil.error(MODULE, "根据分类查询平台推荐遇到异常!", e);
				}
        		
        	}
        }
		++ currentPage;
		if(lst.size() != page.getPageSize() && reqDTO.getPageNo() < pageCount){
			reqDTO.setPageNo((int) currentPage);
			PageResponseDTO<GdsPlatRecomRespDTO> tempPage = gdsPlatRecomSV.queryGdsPlatRecomRespDTOPaging(reqDTO);
			iteratorQuery(reqDTO, lst, pageCount, currentPage, tempPage);
		}
	}

	@Override
	public void editGdsPlatRecom(GdsPlatRecomReqDTO reqDTO)
			throws BusinessException {
		paramNullCheck(reqDTO,true, "reqDTO");
		paramNullCheck(reqDTO.getId(), "reqDTO.id");
		GdsPlatRecom gdsPlatRecom=new GdsPlatRecom();
		ObjectCopyUtil.copyObjValue(reqDTO, gdsPlatRecom, null, false);
		gdsPlatRecom.setUpdateStaff(reqDTO.getStaff().getId());
		gdsPlatRecom.setUpdateTime(DateUtil.getSysDate());
		gdsPlatRecomSV.editGdsPlatRecom(gdsPlatRecom);
	}

}

