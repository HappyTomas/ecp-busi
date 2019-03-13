package com.zengshi.ecp.goods.service.busi.impl.gdsinfoidx;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsGds2CatgCatgIdxMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsInfoShopIdxMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsSku2PropPropIdxMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsSkuInfoGdsIdxMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsSkuInfoShopIdxMapper;
import com.zengshi.ecp.goods.dao.model.GdsGds2CatgCatgIdx;
import com.zengshi.ecp.goods.dao.model.GdsGds2CatgCatgIdxCriteria;
import com.zengshi.ecp.goods.dao.model.GdsInfoShopIdx;
import com.zengshi.ecp.goods.dao.model.GdsInfoShopIdxCriteria;
import com.zengshi.ecp.goods.dao.model.GdsSku2PropPropIdx;
import com.zengshi.ecp.goods.dao.model.GdsSku2PropPropIdxCriteria;
import com.zengshi.ecp.goods.dao.model.GdsSkuInfoGdsIdx;
import com.zengshi.ecp.goods.dao.model.GdsSkuInfoGdsIdxCriteria;
import com.zengshi.ecp.goods.dao.model.GdsSkuInfoShopIdx;
import com.zengshi.ecp.goods.dao.model.GdsSkuInfoShopIdxCriteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalog2SiteReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalogRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfoidx.GdsGds2CatgCatgIdxRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfoidx.GdsInfoShopIdxRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfoidx.GdsSku2PropPropIdxReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfoidx.GdsSkuInfoGdsIdxReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfoidx.GdsSkuInfoShopIdxRespDTO;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsInfoQuerySV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsSkuInfoQuerySV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfoidx.IGdsInfoQueryIDXSV;
import com.zengshi.ecp.goods.service.common.impl.AbstractSVImpl;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsCatalog2SiteSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import org.apache.commons.collections.MapUtils;

/**
 * 
 * Title: 商品，单品索引查询接口 <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: <br>
 * Date:2016年3月24日下午3:30:16 <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public class GdsInfoQueryIDXSVImpl extends AbstractSVImpl implements IGdsInfoQueryIDXSV {

    @Resource
    private GdsSkuInfoGdsIdxMapper gdsSkuInfoGdsIdxMapper;

    @Resource
    private GdsGds2CatgCatgIdxMapper gds2CatgCatgIdxMapper;

    @Resource
    private GdsSkuInfoShopIdxMapper gdsSkuInfoShopIdxMapper;

    @Resource
    private GdsInfoShopIdxMapper gdsInfoShopIdxMapper;

    @Resource
    private GdsSku2PropPropIdxMapper gdsSku2PropPropIdxMapper;

    @Resource
    private IGdsCatalog2SiteSV catalog2SiteSV;

    @Resource
    private IGdsInfoQuerySV gdsInfoQuerySV;

    @Resource
    private IGdsSkuInfoQuerySV gdsSkuInfoQuerySV;

    /**
     * 
     * querySkuGdsIdxs:(根据商品编码查询对应的单品信息). <br/>
     * 
     * @author linwb3
     * @param reqDTO
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    @Override
    public List<GdsSkuInfoGdsIdx> querySkuGdsIdxs(GdsSkuInfoGdsIdxReqDTO reqDTO) throws BusinessException {
        GdsSkuInfoGdsIdxCriteria gdsIdxCriteria = new GdsSkuInfoGdsIdxCriteria();
        if (reqDTO != null) {
            GdsSkuInfoGdsIdxCriteria.Criteria criteria = gdsIdxCriteria.createCriteria();
            if (reqDTO.getGdsId() != null && reqDTO.getGdsId().longValue() != 0) {
                criteria.andGdsIdEqualTo(reqDTO.getGdsId());
            }
            if (reqDTO.getSkuId() != null && reqDTO.getSkuId().longValue() != 0) {
                criteria.andSkuIdEqualTo(reqDTO.getSkuId());
            }
            if (StringUtil.isNotBlank(reqDTO.getGdsStatus())) {
                criteria.andGdsStatusEqualTo(reqDTO.getGdsStatus());
            }
            if (CollectionUtils.isNotEmpty(reqDTO.getGdsstatues())) {
                criteria.andGdsStatusIn(reqDTO.getGdsstatues());
            }
            if (reqDTO.getShopId() != null) {
                criteria.andShopIdEqualTo(reqDTO.getShopId());
            }
        }
        gdsIdxCriteria.setOrderByClause(" update_time desc");
        List<GdsSkuInfoGdsIdx> gdsIdxs = gdsSkuInfoGdsIdxMapper.selectByExample(gdsIdxCriteria);
        if (CollectionUtils.isEmpty(gdsIdxs)) {
            return new ArrayList<GdsSkuInfoGdsIdx>();
        }
        return gdsIdxs;
    }

    /**
     * 
     * getGdsIdsByCatg:(根据分类编码获取商品列表). <br/>
     * 
     * @author linwb3
     * @param reqDTO
     * @return
     * @since JDK 1.6
     */
    @Override
    public List<Long> getGdsIdsByCatg(GdsInfoReqDTO reqDTO) {
        List<Long> gdsIds = new ArrayList<Long>();
        if(reqDTO.getPageNo() ==null){
            reqDTO.setPageNo(1);
        }
        if(reqDTO.getPageSize() ==null){
            reqDTO.setPageNo(5);
        }
        PageResponseDTO<GdsGds2CatgCatgIdxRespDTO> page = queryGds2CatgByCatgCode(reqDTO);
        List<GdsGds2CatgCatgIdxRespDTO> catgs = page.getResult();
        if (CollectionUtils.isNotEmpty(catgs)) {
            for (GdsGds2CatgCatgIdxRespDTO gdsGds2CatgCatgIdxRespDTO : catgs) {
                gdsIds.add(gdsGds2CatgCatgIdxRespDTO.getGdsId());
            }
        }
        return gdsIds;
    }

    /**
     * 
     * queryGdsInfoByCatgCode:(根据分类编码获取商品列表 分页). <br/>
     * 
     * @author linwb3
     * @param reqDTO
     * @return
     * @since JDK 1.6
     */
    @Override
    public PageResponseDTO<GdsInfoRespDTO> queryGdsInfoByCatgCode(GdsInfoReqDTO reqDTO) {
        PageResponseDTO<GdsGds2CatgCatgIdxRespDTO> page = queryGds2CatgByCatgCode(reqDTO);
        List<GdsGds2CatgCatgIdxRespDTO> catgs = page.getResult();
        List<GdsInfoRespDTO> gdsInfos = new ArrayList<GdsInfoRespDTO>();
        if (reqDTO.getFullInfo()) {
            if (CollectionUtils.isNotEmpty(catgs)) {
                for (GdsGds2CatgCatgIdxRespDTO gdsGds2CatgCatgIdxRespDTO : catgs) {
                    GdsInfoRespDTO gdsInfo = new GdsInfoRespDTO();
                    ObjectCopyUtil.copyObjValue(gdsGds2CatgCatgIdxRespDTO, gdsInfo, null, false);
                    gdsInfo.setMainCatgs(gdsGds2CatgCatgIdxRespDTO.getCatgCode());
                    gdsInfo.setId(gdsGds2CatgCatgIdxRespDTO.getGdsId());
                    gdsInfos.add(gdsInfo);
                }
            }
        } else {
            if (CollectionUtils.isNotEmpty(catgs)) {
                for (GdsGds2CatgCatgIdxRespDTO gdsGds2CatgCatgIdxRespDTO : catgs) {
                    GdsInfoReqDTO req = new GdsInfoReqDTO();
                    req.setId(gdsGds2CatgCatgIdxRespDTO.getGdsId());
                    GdsInfoRespDTO gdsInfo = gdsInfoQuerySV.queryGdsInfoByOption(req, req.getGdsQueryOptions());
                    gdsInfos.add(gdsInfo);
                }
            }
        }
        PageResponseDTO<GdsInfoRespDTO> resultPages = new PageResponseDTO<GdsInfoRespDTO>();
        resultPages.setCount(page.getCount());
        resultPages.setPageCount(page.getPageCount());
        resultPages.setPageSize(page.getPageSize());
        resultPages.setPageNo(page.getPageNo());
        resultPages.setResult(gdsInfos);
        return resultPages;
    }

    /**
     * 
     * getSkusByGdsId:(通过商品id查询对应的商品列表). <br/>
     * 
     * @author linwb3
     * @param gdsId
     * @return
     * @since JDK 1.6
     */
    @Override
    public List<GdsInfoShopIdx> getGdsIdsByShopId(GdsInfoReqDTO gdsInfoReqDTO) {
        GdsInfoShopIdxCriteria gdsInfoShopIdxCriteria = initCriteria(gdsInfoReqDTO);
        List<GdsInfoShopIdx> shopIdxs = gdsInfoShopIdxMapper.selectByExample(gdsInfoShopIdxCriteria);
        if (CollectionUtils.isEmpty(shopIdxs)) {
            return new ArrayList<GdsInfoShopIdx>();
        }
        return shopIdxs;
    }

    /**
     * 
     * getSkusByGdsId:(通过商品id查询对应的商品列表 分页). <br/>
     * 
     * @author linwb3
     * @param gdsId
     * @return
     * @since JDK 1.6
     */
    @Override
    public PageResponseDTO<GdsInfoShopIdxRespDTO> getGdsIdsPageByShopId(GdsInfoReqDTO gdsInfoReqDTO) {
        GdsInfoShopIdxCriteria gdsInfoShopIdxCriteria = initCriteria(gdsInfoReqDTO);
        gdsInfoShopIdxCriteria.setLimitClauseCount(gdsInfoReqDTO.getPageSize());
        gdsInfoShopIdxCriteria.setLimitClauseStart(gdsInfoReqDTO.getStartRowIndex());
        if (StringUtil.isNotBlank(gdsInfoReqDTO.getPriceSort())) {
            gdsInfoShopIdxCriteria.setOrderByClause(gdsInfoReqDTO.getPriceSort());
        }
        PageResponseDTO<GdsInfoShopIdxRespDTO> pages = super.queryByPagination(gdsInfoReqDTO, gdsInfoShopIdxCriteria, true, new GdsShopIdxRespPaginationCallback());
        return pages;
    }

    /**
     * 
     * getGdsSkuInfoByShopId:(查询店铺下的单品列表 通过店铺索引表 ). <br/>
     * 
     * @author linwb3
     * @param gdsSkuInfoReqDTO
     * @return
     * @since JDK 1.6
     */
    @Override
    public List<GdsSkuInfoShopIdx> getGdsSkuInfoByShopId(GdsSkuInfoReqDTO gdsSkuInfoReqDTO) {
        GdsSkuInfoShopIdxCriteria gdsInfoShopIdxCriteria = initCriteria(gdsSkuInfoReqDTO);
        List<GdsSkuInfoShopIdx> pages = gdsSkuInfoShopIdxMapper.selectByExample(gdsInfoShopIdxCriteria);
        return pages;
    }

    /**
     * 
     * getGdsSkuInfoByShopId:(查询店铺下的单品列表 通过店铺索引表 ). <br/>
     * 
     * @author linwb3
     * @param gdsSkuInfoReqDTO
     * @return
     * @since JDK 1.6
     */
    @Override
    public PageResponseDTO<GdsSkuInfoShopIdxRespDTO> getGdsSkuInfoPageByShopId(GdsSkuInfoReqDTO gdsSkuInfoReqDTO) {
        // 分页查询
        GdsSkuInfoShopIdxCriteria gdsInfoShopIdxCriteria = initCriteria(gdsSkuInfoReqDTO);
        gdsInfoShopIdxCriteria.setLimitClauseCount(gdsSkuInfoReqDTO.getPageSize());
        gdsInfoShopIdxCriteria.setLimitClauseStart(gdsSkuInfoReqDTO.getStartRowIndex());
        PageResponseDTO<GdsSkuInfoShopIdxRespDTO> pages = super.queryByPagination(gdsSkuInfoReqDTO, gdsInfoShopIdxCriteria, true, new GdsSkuInfoShopIdxRespPaginationCallback());
        return pages;
    }

    @Override
    public PageResponseDTO<GdsSkuInfoRespDTO> getSkuInfoListByPropIdx(GdsSku2PropPropIdxReqDTO reqDTO, SkuQueryOption... skuQuerys) {
        GdsSku2PropPropIdxCriteria criteria = new GdsSku2PropPropIdxCriteria();
        criteria.setLimitClauseStart(reqDTO.getStartRowIndex());
        criteria.setLimitClauseCount(reqDTO.getPageSize());
        GdsSku2PropPropIdxCriteria.Criteria c = criteria.createCriteria();
        try {
            initCriteria(c, reqDTO);
        } catch (ParseException exception) {
            throw new BusinessException();
        }
        PageResponseDTO<GdsSkuInfoRespDTO> page = super.queryByPagination(reqDTO, criteria, true, new GdsSku2PropPropIdxPaginationCallback(reqDTO.getPropIds(),skuQuerys));
        return page;
    }
    
    @Override
	public Long countGdsInfoByShopIDAndStatus(GdsInfoReqDTO reqDTO)
			throws BusinessException {
    	GdsInfoShopIdxCriteria gdsInfoShopIdxCriteria = new GdsInfoShopIdxCriteria();
		initAutormCriteria(reqDTO, gdsInfoShopIdxCriteria);
		return gdsInfoShopIdxMapper.countByExample(gdsInfoShopIdxCriteria);
	}

	private GdsInfoShopIdxCriteria initCriteria(GdsInfoReqDTO gdsInfoReqDTO) {
        GdsInfoShopIdxCriteria gdsInfoShopIdxCriteria = gdsInfoReqDTO.getCriteria();// new
                                                                                    // GdsInfoShopIdxCriteria();
        initAutormCriteriaWithAuth(gdsInfoReqDTO, gdsInfoShopIdxCriteria);
        gdsInfoShopIdxCriteria.setOrderByClause("update_time desc");
        return gdsInfoShopIdxCriteria;
    }

    private GdsInfoShopIdxCriteria.Criteria initAutormCriteria(GdsInfoReqDTO gdsInfoReqDTO, GdsInfoShopIdxCriteria gdsInfoShopIdxCriteria) {
        GdsInfoShopIdxCriteria.Criteria criteria = gdsInfoShopIdxCriteria.createCriteria();
        
        if(StringUtil.isNotBlank(gdsInfoReqDTO.getGdsNameEqual())){
            criteria.andGdsNameEqualTo(gdsInfoReqDTO.getGdsNameEqual());
        }
        if(null != gdsInfoReqDTO.getShopId()){
          criteria.andShopIdEqualTo(gdsInfoReqDTO.getShopId());
        }

        if (gdsInfoReqDTO.getId() != null) {
            criteria.andGdsIdEqualTo(gdsInfoReqDTO.getId());
        }

        if (StringUtil.isNotBlank(gdsInfoReqDTO.getIsbn())) {
            criteria.andIsbnLike("%" + gdsInfoReqDTO.getIsbn() + "%");
        }

        // 查询创建时间
        if (gdsInfoReqDTO.getBegCreateTime() != null) {
            criteria.andCreateTimeGreaterThanOrEqualTo(gdsInfoReqDTO.getBegCreateTime());
        }
        if (gdsInfoReqDTO.getEndCreateTime() != null) {
            criteria.andCreateTimeLessThanOrEqualTo(gdsInfoReqDTO.getEndCreateTime());
        }

        // 查询更新时间
        if (gdsInfoReqDTO.getBegUpdateTime() != null) {
            criteria.andUpdateTimeGreaterThanOrEqualTo(gdsInfoReqDTO.getBegUpdateTime());
        }
        if (gdsInfoReqDTO.getEndUpdateTime() != null) {
            criteria.andCreateTimeLessThanOrEqualTo(gdsInfoReqDTO.getEndUpdateTime());
        }
        
        // 签发时间
        if (gdsInfoReqDTO.getPutoffTimeBegin() != null) {
            criteria.andPutoffTimeGreaterThanOrEqualTo(gdsInfoReqDTO.getPutoffTimeBegin());
        }
        if (gdsInfoReqDTO.getPutoffTimeEnd() != null) {
            criteria.andPutoffTimeLessThanOrEqualTo(gdsInfoReqDTO.getPutoffTimeEnd());
        }
        
        // 上架时间
        if (gdsInfoReqDTO.getPutonTimeBegin() != null) {
            criteria.andPutonTimeGreaterThanOrEqualTo(gdsInfoReqDTO.getPutonTimeBegin());
        }
        if (gdsInfoReqDTO.getPutonTimeEnd() != null) {
            criteria.andPutonTimeLessThanOrEqualTo(gdsInfoReqDTO.getPutonTimeEnd());
        }
        if (StringUtil.isNotBlank(gdsInfoReqDTO.getGdsName())) {
            criteria.andGdsNameLike("%" + gdsInfoReqDTO.getGdsName() + "%");
        }

        if (CollectionUtils.isNotEmpty(gdsInfoReqDTO.getGdsStatusArr())) {
            criteria.andGdsStatusIn(gdsInfoReqDTO.getGdsStatusArr());
        } else if (StringUtil.isNotBlank(gdsInfoReqDTO.getGdsStatus())) {
            criteria.andGdsStatusEqualTo(gdsInfoReqDTO.getGdsStatus());
        }

        if (CollectionUtils.isNotEmpty(gdsInfoReqDTO.getCatalogIds())) {
            criteria.andCatlogIdIn(gdsInfoReqDTO.getCatalogIds());
        }

        if (StringUtil.isNotBlank(gdsInfoReqDTO.getPlatCatgs()) && StringUtil.isNotBlank(gdsInfoReqDTO.getColumnCatgs())) {
            criteria.andPlatCatgsLike("%<" + gdsInfoReqDTO.getPlatCatgs() + ">%"+"<" + gdsInfoReqDTO.getColumnCatgs() + ">%");
        }else{
            if (StringUtil.isNotBlank(gdsInfoReqDTO.getPlatCatgs())){
               criteria.andPlatCatgsLike("%<" + gdsInfoReqDTO.getPlatCatgs() + ">%");
            }
            if (StringUtil.isNotBlank(gdsInfoReqDTO.getColumnCatgs())){
                criteria.andPlatCatgsLike("%<" + gdsInfoReqDTO.getColumnCatgs() + ">%");
             }
        }
        if (StringUtil.isNotBlank(gdsInfoReqDTO.getShopCatgs())) {
            criteria.andShopCatgsLike("%<" + gdsInfoReqDTO.getShopCatgs() + ">%");
        }
        if (gdsInfoReqDTO.getGdsTypeId() != null && gdsInfoReqDTO.getGdsTypeId() != 0) {
            criteria.andGdsTypeIdEqualTo(gdsInfoReqDTO.getGdsTypeId());
        }
        if (StringUtil.isNotBlank(gdsInfoReqDTO.getIfRecomm())) {
            criteria.andIfRecommEqualTo(gdsInfoReqDTO.getIfRecomm());
        }
        if (StringUtil.isNotBlank(gdsInfoReqDTO.getIfScoreGds())) {
            criteria.andIfScoreGdsEqualTo(gdsInfoReqDTO.getIfScoreGds());
        }
        if (gdsInfoReqDTO.getBegUpdateTime() != null) {
            criteria.andUpdateTimeGreaterThan(gdsInfoReqDTO.getBegUpdateTime());
        }
        if (gdsInfoReqDTO.getEndUpdateTime() != null) {
            criteria.andUpdateTimeLessThan(gdsInfoReqDTO.getEndUpdateTime());
        }
        if(CollectionUtils.isNotEmpty(gdsInfoReqDTO.getExcludeGdsTypes())){
            criteria.andGdsTypeIdNotIn(gdsInfoReqDTO.getExcludeGdsTypes());
        }
        if(CollectionUtils.isNotEmpty(gdsInfoReqDTO.getSiteIds())){
            List<Long> cataLogIds = new ArrayList<>();
            for(Long siteId : gdsInfoReqDTO.getSiteIds()){
                GdsCatalog2SiteReqDTO reqDTO = new GdsCatalog2SiteReqDTO();
                reqDTO.setSiteId(siteId);

                List<GdsCatalogRespDTO> catalogRespDTOs = catalog2SiteSV.queryRelationBySiteId(reqDTO);
                for (GdsCatalogRespDTO catalogRespDTO : catalogRespDTOs) {
                    if(!cataLogIds.contains(catalogRespDTO.getId())){
                        cataLogIds.add(catalogRespDTO.getId()); 
                    }
                }
            }
            if (cataLogIds.size() > 0) {
                criteria.andCatlogIdIn(cataLogIds);
            }else{
                criteria.andCatlogIdEqualTo(-99L);
            }
        }
        //新增主分类过滤条件
        if(StringUtil.isNotBlank(gdsInfoReqDTO.getMainCatgs())){
        	//处理多个分类，入参使用catgs
            criteria.andMainCatgsIn(gdsInfoReqDTO.getCatgs());
        }
        if(StringUtil.isNotBlank(gdsInfoReqDTO.getExt1())){
            criteria.andExt1EqualTo(gdsInfoReqDTO.getExt1());
        }
        
        if(StringUtil.isNotBlank(gdsInfoReqDTO.getExt2())){
            criteria.andExt2EqualTo(gdsInfoReqDTO.getExt2());
        }
        
        if(StringUtil.isNotBlank(gdsInfoReqDTO.getExt3())){
            criteria.andExt3EqualTo(gdsInfoReqDTO.getExt3());
        }
        
        if(StringUtil.isNotBlank(gdsInfoReqDTO.getExt4())){
            criteria.andExt4EqualTo(gdsInfoReqDTO.getExt4()); 
        }
        
        if(StringUtil.isNotBlank(gdsInfoReqDTO.getExt5())){
            criteria.andExt5EqualTo(gdsInfoReqDTO.getExt5());
        }
        
        
        if(gdsInfoReqDTO.isExt1Null()){
        	criteria.andExt1IsNull();
        }
        if(gdsInfoReqDTO.isExt1NotNull()){
        	criteria.andExt1IsNotNull();
        }
        
        
        if(gdsInfoReqDTO.isExt2Null()){
        	criteria.andExt2IsNull();
        }
        if(gdsInfoReqDTO.isExt2NotNull()){
        	criteria.andExt2IsNotNull();
        }
        
        if(gdsInfoReqDTO.isExt3Null()){
        	criteria.andExt3IsNull();
        }
        if(gdsInfoReqDTO.isExt3NotNull()){
        	criteria.andExt3IsNotNull();
        }
        
        if(gdsInfoReqDTO.isExt4Null()){
        	criteria.andExt1IsNull();
        }
        if(gdsInfoReqDTO.isExt4NotNull()){
        	criteria.andExt1IsNotNull();
        }
        
        if(gdsInfoReqDTO.isExt5Null()){
        	criteria.andExt5IsNull();
        }
        if(gdsInfoReqDTO.isExt5NotNull()){
        	criteria.andExt5IsNotNull();
        }
        
        
        return criteria;
    }
    
    
    

    private void initAutormCriteriaWithAuth(GdsInfoReqDTO gdsInfoReqDTO, GdsInfoShopIdxCriteria gdsInfoShopIdxCriteria) {

        List<GdsInfoShopIdxCriteria.Criteria> criteriaLst = gdsInfoShopIdxCriteria.getOredCriteria();

        if (CollectionUtils.isEmpty(criteriaLst)) {
            initAutormCriteria(gdsInfoReqDTO, gdsInfoShopIdxCriteria);
        } else {
            for (Iterator<GdsInfoShopIdxCriteria.Criteria> iterator = criteriaLst.iterator(); iterator.hasNext();) {
                GdsInfoShopIdxCriteria.Criteria criteria = iterator.next();
                
                if(null != gdsInfoReqDTO.getShopId()){
                    criteria.andShopIdEqualTo(gdsInfoReqDTO.getShopId());
                }

                if (gdsInfoReqDTO.getId() != null) {
                    criteria.andGdsIdEqualTo(gdsInfoReqDTO.getId());
                }

                if (StringUtil.isNotBlank(gdsInfoReqDTO.getIsbn())) {
                    if(gdsInfoReqDTO.isIfIsbnFuzzyQeury()){
                        criteria.andIsbnLike("%" + gdsInfoReqDTO.getIsbn() + "%");
                    }else{//精确查询
                        criteria.andIsbnEqualTo(gdsInfoReqDTO.getIsbn());
                    }
                }

                // 查询创建时间
                if (gdsInfoReqDTO.getBegCreateTime() != null) {
                    criteria.andCreateTimeGreaterThanOrEqualTo(gdsInfoReqDTO.getBegCreateTime());
                }
                if (gdsInfoReqDTO.getEndCreateTime() != null) {
                    criteria.andCreateTimeLessThanOrEqualTo(gdsInfoReqDTO.getEndCreateTime());
                }

                // 查询更新时间
                if (gdsInfoReqDTO.getBegUpdateTime() != null) {
                    criteria.andUpdateTimeGreaterThanOrEqualTo(gdsInfoReqDTO.getBegUpdateTime());
                }
                if (gdsInfoReqDTO.getEndUpdateTime() != null) {
                    criteria.andCreateTimeLessThanOrEqualTo(gdsInfoReqDTO.getEndUpdateTime());
                }
                
                if(StringUtil.isNotBlank(gdsInfoReqDTO.getGdsNameEqual())){
                    criteria.andGdsNameEqualTo(gdsInfoReqDTO.getGdsNameEqual());
                }
                
                // 签发时间
                if (gdsInfoReqDTO.getPutoffTimeBegin() != null) {
                    criteria.andPutoffTimeGreaterThanOrEqualTo(gdsInfoReqDTO.getPutoffTimeBegin());
                }
                if (gdsInfoReqDTO.getPutoffTimeEnd() != null) {
                    criteria.andPutoffTimeLessThanOrEqualTo(gdsInfoReqDTO.getPutoffTimeEnd());
                }
                
                // 上架时间
                if (gdsInfoReqDTO.getPutonTimeBegin() != null) {
                    criteria.andPutonTimeGreaterThanOrEqualTo(gdsInfoReqDTO.getPutonTimeBegin());
                }
                if (gdsInfoReqDTO.getPutonTimeEnd() != null) {
                    criteria.andPutonTimeLessThanOrEqualTo(gdsInfoReqDTO.getPutonTimeEnd());
                }
                if (StringUtil.isNotBlank(gdsInfoReqDTO.getGdsName())) {
                    criteria.andGdsNameLike("%" + gdsInfoReqDTO.getGdsName() + "%");
                }

                if (CollectionUtils.isNotEmpty(gdsInfoReqDTO.getGdsStatusArr())) {
                    criteria.andGdsStatusIn(gdsInfoReqDTO.getGdsStatusArr());
                } else if (StringUtil.isNotBlank(gdsInfoReqDTO.getGdsStatus())) {
                    criteria.andGdsStatusEqualTo(gdsInfoReqDTO.getGdsStatus());
                }

                if (CollectionUtils.isNotEmpty(gdsInfoReqDTO.getCatalogIds())) {
                    criteria.andCatlogIdIn(gdsInfoReqDTO.getCatalogIds());
                }

                if (StringUtil.isNotBlank(gdsInfoReqDTO.getPlatCatgs())) {
                    criteria.andPlatCatgsLike("%<" + gdsInfoReqDTO.getPlatCatgs() + ">%");
                }
                if (StringUtil.isNotBlank(gdsInfoReqDTO.getPlatCatgs()) && StringUtil.isNotBlank(gdsInfoReqDTO.getColumnCatgs())) {
                    criteria.andPlatCatgsLike("%<" + gdsInfoReqDTO.getPlatCatgs() + ">%"+"<" + gdsInfoReqDTO.getColumnCatgs() + ">%");
                }else{
                    if (StringUtil.isNotBlank(gdsInfoReqDTO.getPlatCatgs())){
                       criteria.andPlatCatgsLike("%<" + gdsInfoReqDTO.getPlatCatgs() + ">%");
                    }
                    if (StringUtil.isNotBlank(gdsInfoReqDTO.getColumnCatgs())){
                        criteria.andPlatCatgsLike("%<" + gdsInfoReqDTO.getColumnCatgs() + ">%");
                     }
                }
                if (StringUtil.isNotBlank(gdsInfoReqDTO.getShopCatgs())) {
                    criteria.andShopCatgsLike("%<" + gdsInfoReqDTO.getShopCatgs() + ">%");
                }
                if (gdsInfoReqDTO.getGdsTypeId() != null && gdsInfoReqDTO.getGdsTypeId() != 0) {
                    criteria.andGdsTypeIdEqualTo(gdsInfoReqDTO.getGdsTypeId());
                }
                if (StringUtil.isNotBlank(gdsInfoReqDTO.getIfRecomm())) {
                    criteria.andIfRecommEqualTo(gdsInfoReqDTO.getIfRecomm());
                }

                if (StringUtil.isNotBlank(gdsInfoReqDTO.getIfScoreGds())) {
                    criteria.andIfScoreGdsEqualTo(gdsInfoReqDTO.getIfScoreGds());
                }
                if(CollectionUtils.isNotEmpty(gdsInfoReqDTO.getExcludeGdsTypes())){
                    criteria.andGdsTypeIdNotIn(gdsInfoReqDTO.getExcludeGdsTypes());
                }
                if(CollectionUtils.isNotEmpty(gdsInfoReqDTO.getSiteIds())){
                    List<Long> cataLogIds = new ArrayList<>();
                    for(Long siteId : gdsInfoReqDTO.getSiteIds()){
                        GdsCatalog2SiteReqDTO reqDTO = new GdsCatalog2SiteReqDTO();
                        reqDTO.setSiteId(siteId);

                        List<GdsCatalogRespDTO> catalogRespDTOs = catalog2SiteSV.queryRelationBySiteId(reqDTO);
                        for (GdsCatalogRespDTO catalogRespDTO : catalogRespDTOs) {
                            if(!cataLogIds.contains(catalogRespDTO.getId())){
                                cataLogIds.add(catalogRespDTO.getId()); 
                            }
                        }
                    }
                    if (cataLogIds.size() > 0) {
                        criteria.andCatlogIdIn(cataLogIds);
                    }else{
                        criteria.andCatlogIdEqualTo(-99L);
                    }
                }
                //新增主分类过滤条件
                if(StringUtil.isNotBlank(gdsInfoReqDTO.getMainCatgs())){
                	//处理多个分类，入参使用catgs
                    criteria.andMainCatgsIn(gdsInfoReqDTO.getCatgs());
                }
                
                if(StringUtil.isNotBlank(gdsInfoReqDTO.getExt1())){
                    criteria.andExt1EqualTo(gdsInfoReqDTO.getExt1());
                }
                
                if(StringUtil.isNotBlank(gdsInfoReqDTO.getExt2())){
                    criteria.andExt2EqualTo(gdsInfoReqDTO.getExt2());
                }
                
                if(StringUtil.isNotBlank(gdsInfoReqDTO.getExt3())){
                    criteria.andExt3EqualTo(gdsInfoReqDTO.getExt3());
                }
                
                if(StringUtil.isNotBlank(gdsInfoReqDTO.getExt4())){
                    criteria.andExt4EqualTo(gdsInfoReqDTO.getExt4()); 
                }
                
                if(StringUtil.isNotBlank(gdsInfoReqDTO.getExt5())){
                    criteria.andExt5EqualTo(gdsInfoReqDTO.getExt5());
                }
            }
        }
    }

    /**
     * 
     * queryGds2CatgByCatgCode:(分页获取商品分类关系分类索引信息). <br/>
     * 
     * @author linwb3
     * @param reqDTO
     * @return
     * @since JDK 1.6
     */
    private PageResponseDTO<GdsGds2CatgCatgIdxRespDTO> queryGds2CatgByCatgCode(GdsInfoReqDTO reqDTO) {
        GdsGds2CatgCatgIdxCriteria example = new GdsGds2CatgCatgIdxCriteria();
        GdsGds2CatgCatgIdxCriteria.Criteria criteria = example.createCriteria();

        String catgCode = reqDTO.getMainCatgs();
        if (StringUtil.isNotBlank(catgCode)) {
            criteria.andCatgCodeEqualTo(catgCode);
        } else if (CollectionUtils.isNotEmpty(reqDTO.getCatgs())) {
            criteria.andCatgCodeIn(reqDTO.getCatgs());
        } else {
            throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100, new String[] { "catgCode" });
        }
        if (reqDTO.getId()!=null) {
            criteria.andGdsIdNotEqualTo(reqDTO.getId());
        }
        criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        if (StringUtil.isNotBlank(reqDTO.getGdsStatus())) {
            criteria.andGdsStatusEqualTo(reqDTO.getGdsStatus());
        }
        example.setOrderByClause("gds_id desc");
        example.setLimitClauseCount(reqDTO.getPageSize());
        example.setLimitClauseStart(reqDTO.getStartRowIndex());
        PageResponseDTO<GdsGds2CatgCatgIdxRespDTO> page = super.queryByPagination(reqDTO, example, true, new Gds2CatgCatgIdxRespPaginationCallback());
        return page;
    }

    private GdsSkuInfoShopIdxCriteria initCriteria(GdsSkuInfoReqDTO gdsSkuInfoReqDTO) {
        GdsSkuInfoShopIdxCriteria gdsSkuInfoShopIdxCriteria = gdsSkuInfoReqDTO.getCriteria();// new
                                                                                             // GdsSkuInfoShopIdxCriteria();
        initAutormCriteriaWithAuth(gdsSkuInfoReqDTO, gdsSkuInfoShopIdxCriteria);

        /*
         * if (StringUtil.isNotBlank(gdsSkuInfoReqDTO.getIfScoreGds())) {
         * criteria.andIfScoreGdsEqualTo(gdsSkuInfoReqDTO.getIfScoreGds()); } if
         * (gdsSkuInfoReqDTO.getCurrentSiteId() != null) { GdsCatalog2SiteReqDTO reqDTO = new
         * GdsCatalog2SiteReqDTO(); reqDTO.setSiteId(gdsSkuInfoReqDTO.getCurrentSiteId());
         * 
         * List<GdsCatalogRespDTO> catalogRespDTOs = catalog2SiteSV.queryRelationBySiteId(reqDTO);
         * List<Long> cataLogIds = new ArrayList<>(); for (GdsCatalogRespDTO catalogRespDTO :
         * catalogRespDTOs) { cataLogIds.add(catalogRespDTO.getId()); } if (cataLogIds.size() > 0) {
         * criteria.andCatlogIdIn(cataLogIds); } }
         */

        if(CollectionUtils.isNotEmpty(gdsSkuInfoReqDTO.getSortRule())){
            String orderByClause="";
            for(String s:gdsSkuInfoReqDTO.getSortRule()){
                String rules[]=s.split(",");
                orderByClause+=rules[0];
                orderByClause+=" ";
                orderByClause+=rules[1];
                orderByClause+=" ";
            }
            gdsSkuInfoShopIdxCriteria.setOrderByClause(orderByClause);
        }else{
            gdsSkuInfoShopIdxCriteria.setOrderByClause("update_time desc");
        }

        return gdsSkuInfoShopIdxCriteria;
    }

    private void initAutormCriteriaWithAuth(GdsSkuInfoReqDTO gdsSkuInfoReqDTO, GdsSkuInfoShopIdxCriteria gdsSkuInfoShopIdxCriteria) {
        List<GdsSkuInfoShopIdxCriteria.Criteria> lst = gdsSkuInfoShopIdxCriteria.getOredCriteria();
        if (CollectionUtils.isEmpty(lst)) {
            initAutormCriteria(gdsSkuInfoReqDTO, gdsSkuInfoShopIdxCriteria);
        } else {
            for (Iterator<GdsSkuInfoShopIdxCriteria.Criteria> iterator = lst.iterator(); iterator.hasNext();) {
                GdsSkuInfoShopIdxCriteria.Criteria criteria = iterator.next();
                if(gdsSkuInfoReqDTO.getShopId()!=null && gdsSkuInfoReqDTO.getShopId().longValue()!=0L){
                    criteria.andShopIdEqualTo(gdsSkuInfoReqDTO.getShopId());
                }
                if (StringUtil.isNotBlank(gdsSkuInfoReqDTO.getGdsName())) {
                    criteria.andGdsNameLike("%" + gdsSkuInfoReqDTO.getGdsName() + "%");
                }

                if (CollectionUtils.isNotEmpty(gdsSkuInfoReqDTO.getGdsStatusArr())) {
                    criteria.andGdsStatusIn(gdsSkuInfoReqDTO.getGdsStatusArr());
                } else if (StringUtil.isNotBlank(gdsSkuInfoReqDTO.getGdsStatus())) {
                    criteria.andGdsStatusEqualTo(gdsSkuInfoReqDTO.getGdsStatus());
                }

                if (gdsSkuInfoReqDTO.getId() != null) {
                    criteria.andSkuIdEqualTo(gdsSkuInfoReqDTO.getId());
                }
                
                if (null != gdsSkuInfoReqDTO.getGdsId()   && 0L!=gdsSkuInfoReqDTO.getGdsId().longValue()) {
                    criteria.andGdsIdEqualTo(gdsSkuInfoReqDTO.getGdsId());
                }

                if (CollectionUtils.isNotEmpty(gdsSkuInfoReqDTO.getCatalogIds())) {
                    criteria.andCatlogIdIn(gdsSkuInfoReqDTO.getCatalogIds());
                }

                if (StringUtil.isNotBlank(gdsSkuInfoReqDTO.getIsbn())) {
                    criteria.andIsbnLike("%" + gdsSkuInfoReqDTO.getIsbn() + "%");
                }

                // 查询创建时间
                if (gdsSkuInfoReqDTO.getBegCreateTime() != null) {
                    criteria.andCreateTimeGreaterThanOrEqualTo(gdsSkuInfoReqDTO.getBegCreateTime());
                }
                if (gdsSkuInfoReqDTO.getEndCreateTime() != null) {
                    criteria.andCreateTimeLessThanOrEqualTo(gdsSkuInfoReqDTO.getEndCreateTime());
                }

                // 查询更新时间
                if (gdsSkuInfoReqDTO.getBegUpdateTime() != null) {
                    criteria.andUpdateTimeGreaterThanOrEqualTo(gdsSkuInfoReqDTO.getBegUpdateTime());
                }
                if (gdsSkuInfoReqDTO.getEndUpdateTime() != null) {
                    criteria.andCreateTimeLessThanOrEqualTo(gdsSkuInfoReqDTO.getEndUpdateTime());
                }

                if (StringUtil.isNotBlank(gdsSkuInfoReqDTO.getPlatCatgs())) {
                    criteria.andPlatCatgsLike("%<" + gdsSkuInfoReqDTO.getPlatCatgs() + ">%");
                }
                
                if (StringUtil.isNotBlank(gdsSkuInfoReqDTO.getShopCatgs())) {
                    criteria.andShopCatgsLike("%<" + gdsSkuInfoReqDTO.getShopCatgs() + ">%");
                }
                if (StringUtil.isNotBlank(gdsSkuInfoReqDTO.getIfRecomm())) {
                    criteria.andIfRecommEqualTo(gdsSkuInfoReqDTO.getIfRecomm());
                }
                if (gdsSkuInfoReqDTO.getGdsTypeId() != null && gdsSkuInfoReqDTO.getGdsTypeId() != 0) {
                    criteria.andGdsTypeIdEqualTo(gdsSkuInfoReqDTO.getGdsTypeId());
                }

                if (StringUtil.isNotBlank(gdsSkuInfoReqDTO.getIfScoreGds())) {
                    criteria.andIfScoreGdsEqualTo(gdsSkuInfoReqDTO.getIfScoreGds());
                }
                
                if(CollectionUtils.isNotEmpty(gdsSkuInfoReqDTO.getSiteIds())){
                    List<Long> cataLogIds = new ArrayList<>();
                    for(Long siteId : gdsSkuInfoReqDTO.getSiteIds()){
                        GdsCatalog2SiteReqDTO reqDTO = new GdsCatalog2SiteReqDTO();
                        reqDTO.setSiteId(siteId);

                        List<GdsCatalogRespDTO> catalogRespDTOs = catalog2SiteSV.queryRelationBySiteId(reqDTO);
                        for (GdsCatalogRespDTO catalogRespDTO : catalogRespDTOs) {
                            if(!cataLogIds.contains(catalogRespDTO.getId())){
                                cataLogIds.add(catalogRespDTO.getId()); 
                            }
                        }
                    }
                    if (cataLogIds.size() > 0) {
                        criteria.andCatlogIdIn(cataLogIds);
                    }else{
                        criteria.andCatlogIdEqualTo(-99L);
                    }
                }
            }

        }
    }

    private GdsSkuInfoShopIdxCriteria.Criteria initAutormCriteria(GdsSkuInfoReqDTO gdsSkuInfoReqDTO, GdsSkuInfoShopIdxCriteria gdsSkuInfoShopIdxCriteria) {
        GdsSkuInfoShopIdxCriteria.Criteria criteria = gdsSkuInfoShopIdxCriteria.createCriteria();
        
        if(null != gdsSkuInfoReqDTO.getShopId()){
          criteria.andShopIdEqualTo(gdsSkuInfoReqDTO.getShopId());
        }
        if (StringUtil.isNotBlank(gdsSkuInfoReqDTO.getGdsName())) {
            criteria.andGdsNameLike("%" + gdsSkuInfoReqDTO.getGdsName() + "%");
        }

        if (CollectionUtils.isNotEmpty(gdsSkuInfoReqDTO.getGdsStatusArr())) {
            criteria.andGdsStatusIn(gdsSkuInfoReqDTO.getGdsStatusArr());
        } else if (StringUtil.isNotBlank(gdsSkuInfoReqDTO.getGdsStatus())) {
            criteria.andGdsStatusEqualTo(gdsSkuInfoReqDTO.getGdsStatus());
        }
        

        if (gdsSkuInfoReqDTO.getId() != null) {
            criteria.andSkuIdEqualTo(gdsSkuInfoReqDTO.getId());
        }
        
        if (null != gdsSkuInfoReqDTO.getGdsId()   && 0L!=gdsSkuInfoReqDTO.getGdsId().longValue()) {
            criteria.andGdsIdEqualTo(gdsSkuInfoReqDTO.getGdsId());
        }

        if (CollectionUtils.isNotEmpty(gdsSkuInfoReqDTO.getCatalogIds())) {
            criteria.andCatlogIdIn(gdsSkuInfoReqDTO.getCatalogIds());
        }

        if (StringUtil.isNotBlank(gdsSkuInfoReqDTO.getIsbn())) {
            criteria.andIsbnLike("%" + gdsSkuInfoReqDTO.getIsbn() + "%");
        }

        // 查询创建时间
        if (gdsSkuInfoReqDTO.getBegCreateTime() != null) {
            criteria.andCreateTimeGreaterThanOrEqualTo(gdsSkuInfoReqDTO.getBegCreateTime());
        }
        if (gdsSkuInfoReqDTO.getEndCreateTime() != null) {
            criteria.andCreateTimeLessThanOrEqualTo(gdsSkuInfoReqDTO.getEndCreateTime());
        }

        // 查询更新时间
        if (gdsSkuInfoReqDTO.getBegUpdateTime() != null) {
            criteria.andUpdateTimeGreaterThanOrEqualTo(gdsSkuInfoReqDTO.getBegUpdateTime());
        }
        if (gdsSkuInfoReqDTO.getEndUpdateTime() != null) {
            criteria.andCreateTimeLessThanOrEqualTo(gdsSkuInfoReqDTO.getEndUpdateTime());
        }

        if (StringUtil.isNotBlank(gdsSkuInfoReqDTO.getPlatCatgs())) {
            criteria.andPlatCatgsLike("%<" + gdsSkuInfoReqDTO.getPlatCatgs() + ">%");
        }
        if (StringUtil.isNotBlank(gdsSkuInfoReqDTO.getShopCatgs())) {
            criteria.andShopCatgsLike("%<" + gdsSkuInfoReqDTO.getShopCatgs() + ">%");
        }
        if (StringUtil.isNotBlank(gdsSkuInfoReqDTO.getIfRecomm())) {
            criteria.andIfRecommEqualTo(gdsSkuInfoReqDTO.getIfRecomm());
        }
        if (gdsSkuInfoReqDTO.getGdsTypeId() != null && gdsSkuInfoReqDTO.getGdsTypeId() != 0) {
            criteria.andGdsTypeIdEqualTo(gdsSkuInfoReqDTO.getGdsTypeId());
        }
        if (StringUtil.isNotBlank(gdsSkuInfoReqDTO.getIfScoreGds())) {
            criteria.andIfScoreGdsEqualTo(gdsSkuInfoReqDTO.getIfScoreGds());
        }
        if(CollectionUtils.isNotEmpty(gdsSkuInfoReqDTO.getSiteIds())){
            List<Long> cataLogIds = new ArrayList<>();
            for(Long siteId : gdsSkuInfoReqDTO.getSiteIds()){
                GdsCatalog2SiteReqDTO reqDTO = new GdsCatalog2SiteReqDTO();
                reqDTO.setSiteId(siteId);

                List<GdsCatalogRespDTO> catalogRespDTOs = catalog2SiteSV.queryRelationBySiteId(reqDTO);
                for (GdsCatalogRespDTO catalogRespDTO : catalogRespDTOs) {
                    if(!cataLogIds.contains(catalogRespDTO.getId())){
                        cataLogIds.add(catalogRespDTO.getId()); 
                    }
                }
            }
            if (cataLogIds.size() > 0) {
                criteria.andCatlogIdIn(cataLogIds);
            }else{
                criteria.andCatlogIdEqualTo(-99L);
            }
        }

        return criteria;
    }

    private void initCriteria(GdsSku2PropPropIdxCriteria.Criteria c, GdsSku2PropPropIdxReqDTO reqDTO) throws ParseException {

        if (StringUtil.isNotBlank(reqDTO.getIfBasic())) {
            c.andIfBasicEqualTo(reqDTO.getIfBasic());
        }
        if (StringUtil.isNotBlank(reqDTO.getIfCheck())) {
            c.andIfCheckEqualTo(reqDTO.getIfCheck());
        }
        if (StringUtil.isNotBlank(reqDTO.getIfHaveto())) {
            c.andIfHavetoEqualTo(reqDTO.getIfHaveto());
        }
        if (StringUtil.isNotBlank(reqDTO.getPropName())) {
            c.andPropNameEqualTo(reqDTO.getPropName());
        }
        if (StringUtil.isNotBlank(reqDTO.getPropType())) {
            c.andPropTypeEqualTo(reqDTO.getPropType());
        }
        if (StringUtil.isNotBlank(reqDTO.getPropValueType())) {
            c.andPropValueTypeEqualTo(reqDTO.getPropValueType());
        }
        if (StringUtil.isNotBlank(reqDTO.getStatus())) {
            c.andStatusEqualTo(reqDTO.getStatus());
        } else {
            c.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        }
        if (StringUtil.isNotBlank(reqDTO.getGdsStatus())) {
            c.andGdsStatusEqualTo(reqDTO.getGdsStatus());
        }
        if (null != reqDTO.getPropId()) {
            c.andPropIdEqualTo(reqDTO.getPropId());
        }
        if (null != reqDTO.getPropValueId()) {
            c.andPropValueIdEqualTo(reqDTO.getPropValueId());
        }
        if (null != reqDTO.getShopId()) {
            c.andShopIdEqualTo(reqDTO.getShopId());
        }
        if (null != reqDTO.getSkuId()) {
            c.andSkuIdEqualTo(reqDTO.getSkuId());
        }
        // 排除当前商品
        if (null != reqDTO.getGdsId()) {
            c.andGdsIdNotEqualTo(reqDTO.getGdsId());
        }
        
        if(CollectionUtils.isNotEmpty(reqDTO.getPropValues())){
            c.andPropValueIn(reqDTO.getPropValues());
        }
        
        if (null != reqDTO.getPropValueNullQuery() && true == reqDTO.getPropValueNullQuery()) {
            c.andPropValueIsNull();
        } else {
            if (StringUtil.isNotBlank(reqDTO.getPropValue())) {
                c.andPropValueEqualTo(reqDTO.getPropValue());
            }
            if (StringUtil.isNotBlank(reqDTO.getPropValueALike())) {
                c.andPropValueLike("%"+reqDTO.getPropValueALike()+"%");
            }
            if (StringUtil.isNotBlank(reqDTO.getPropValueELike())) {
                c.andPropValueLike(reqDTO.getPropValueELike()+"%");
            }
            if (StringUtil.isNotBlank(reqDTO.getPropValuePLike())) {
                c.andPropValueLike("%"+reqDTO.getPropValuePLike());
            }
            if (null != reqDTO.getBeginTime()) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String beginTime = dateFormat.format(reqDTO.getBeginTime());
                c.andPropValueGreaterThanOrEqualTo(beginTime);
                c.andPropValueIsNotNull();
            }
            if (null != reqDTO.getEndTime()) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String endTime = dateFormat.format(reqDTO.getEndTime());
                c.andPropValueLessThanOrEqualTo(endTime);
                c.andPropValueIsNotNull();
            }
        }

    }

//    private String getTime(Long data){
//        return (System.currentTimeMillis()-data)+"ms";
//    }
    
    /**
     * 商品店铺分页查询类
     * 
     * @author linwb3
     * @version GdsInfoQuerySVImpl
     * @since JDK 1.6
     */
    protected class GdsShopIdxRespPaginationCallback extends PaginationCallback<GdsInfoShopIdx, GdsInfoShopIdxRespDTO> {

        //modified by huangdf@izengshi.com # 解决数据量大时，往后分页性能和效率问题。
        @Override
        public int calcRows() {
            return 1000;
        }

        @Override
        public List<GdsInfoShopIdx> queryDB(BaseCriteria criteria) {
            //Long data3=System.currentTimeMillis();
            List<GdsInfoShopIdx> result=gdsInfoShopIdxMapper.selectByExample((GdsInfoShopIdxCriteria) criteria);
            //LogUtil.error("", "执行店铺索引查询SQL耗时"+getTime(data3));
            return result;
        }

        @Override
        public long queryTotal(BaseCriteria criteria) {
            //Long data3=System.currentTimeMillis();
            long count=gdsInfoShopIdxMapper.countByExample((GdsInfoShopIdxCriteria) criteria);
            //LogUtil.error("", "执行店铺索引计数SQL耗时"+getTime(data3));
            return count;
        }

        @Override
        public GdsInfoShopIdxRespDTO warpReturnObject(GdsInfoShopIdx t) {
            GdsInfoShopIdxRespDTO dto = copyShopIdx2Info(t);
            return dto;
        }


    }
    
    private GdsInfoShopIdxRespDTO copyShopIdx2Info(GdsInfoShopIdx t) {
        GdsInfoShopIdxRespDTO dto = new GdsInfoShopIdxRespDTO();
        dto.setShopId(t.getShopId());
        dto.setGdsId(t.getGdsId());
        dto.setGdsName(t.getGdsName());
        dto.setGdsSubHead(t.getGdsSubHead());
        dto.setGuidePrice(t.getGuidePrice());
        dto.setGdsTypeId(t.getGdsTypeId());
        dto.setGdsStatus(t.getGdsStatus());
        dto.setGdsApprove(t.getGdsApprove());
        dto.setShopCatgs(t.getShopCatgs());
        dto.setPlatCatgs(t.getPlatCatgs());
        dto.setMainCatgs(t.getMainCatgs());
        dto.setCatlogId(t.getCatlogId());
        dto.setIfSendscore(t.getIfSendscore());
        dto.setIfSalealone(t.getIfSalealone());
        dto.setIfRecomm(t.getIfRecomm());
        dto.setIfNew(t.getIfNew());
        dto.setIfStocknotice(t.getIfStocknotice());
        dto.setIfFree(t.getIfFree());
        dto.setIfDisperseStock(t.getIfDisperseStock());
        dto.setIfEntityCode(t.getIfEntityCode());
        dto.setIfSeniorPrice(t.getIfSeniorPrice());
        dto.setIfScoreGds(t.getIfScoreGds());
        dto.setSortNo(t.getSortNo());
        dto.setCreateTime(t.getCreateTime());
        dto.setCreateStaff(t.getCreateStaff());
        dto.setUpdateStaff(t.getUpdateStaff());
        dto.setUpdateTime(t.getUpdateTime());
        dto.setIsbn(t.getIsbn());
        return dto;
    }

    /**
     * 商品分类分类分页查询类
     * 
     * @author linwb3
     * @version GdsInfoQuerySVImpl
     * @since JDK 1.6
     */
    protected class Gds2CatgCatgIdxRespPaginationCallback extends PaginationCallback<GdsGds2CatgCatgIdx, GdsGds2CatgCatgIdxRespDTO> {

        @Override
        public List<GdsGds2CatgCatgIdx> queryDB(BaseCriteria criteria) {
            return gds2CatgCatgIdxMapper.selectByExample((GdsGds2CatgCatgIdxCriteria) criteria);
        }

        @Override
        public long queryTotal(BaseCriteria criteria) {
            return gds2CatgCatgIdxMapper.countByExample((GdsGds2CatgCatgIdxCriteria) criteria);
        }

        @Override
        public GdsGds2CatgCatgIdxRespDTO warpReturnObject(GdsGds2CatgCatgIdx t) {
            GdsGds2CatgCatgIdxRespDTO dto = new GdsGds2CatgCatgIdxRespDTO();
            ObjectCopyUtil.copyObjValue(t, dto, null, true);
            return dto;
        }

    }

    /********************
     * protected methods.
     ********************/
    protected class GdsSkuInfoShopIdxRespPaginationCallback extends PaginationCallback<GdsSkuInfoShopIdx, GdsSkuInfoShopIdxRespDTO> {

        @Override
        public List<GdsSkuInfoShopIdx> queryDB(BaseCriteria criteria) {
            return gdsSkuInfoShopIdxMapper.selectByExample((GdsSkuInfoShopIdxCriteria) criteria);
        }

        @Override
        public long queryTotal(BaseCriteria criteria) {
            return gdsSkuInfoShopIdxMapper.countByExample((GdsSkuInfoShopIdxCriteria) criteria);
        }

        @Override
        public GdsSkuInfoShopIdxRespDTO warpReturnObject(GdsSkuInfoShopIdx t) {
            GdsSkuInfoShopIdxRespDTO dto = new GdsSkuInfoShopIdxRespDTO();
            ObjectCopyUtil.copyObjValue(t, dto, null, true);
            return dto;
        }

    }

    /**
     * 
     * Title: 商品属性索引分页查询回调. <br>
     * Project Name:ecp-services-goods-server <br>
     * Description: <br>
     * Date:2015-10-22上午9:41:40 <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     * 
     * @author liyong7
     * @version GdsSkuInfoQuerySVImpl
     * @since JDK 1.6
     */
    protected class GdsSku2PropPropIdxPaginationCallback extends PaginationCallback<GdsSku2PropPropIdx, GdsSkuInfoRespDTO> {

        private SkuQueryOption[] options;
        private List<Long> propIds;

        public GdsSku2PropPropIdxPaginationCallback(List<Long> propIds,SkuQueryOption... options) {
            this.options = options;
            this.propIds = propIds;
        }

        @Override
        public List<GdsSku2PropPropIdx> queryDB(BaseCriteria criteria) {
            return gdsSku2PropPropIdxMapper.selectByExample((GdsSku2PropPropIdxCriteria) criteria);
        }

        @Override
        public long queryTotal(BaseCriteria criteria) {
            return gdsSku2PropPropIdxMapper.countByExample((GdsSku2PropPropIdxCriteria) criteria);
        }

        @Override
        public GdsSkuInfoRespDTO warpReturnObject(GdsSku2PropPropIdx t) {
            /*
             * GdsSkuInfoRespDTO dto = new GdsSkuInfoRespDTO(); dto.setId(t.getSkuId());
             * dto.setGdsId(t.getGdsId());
             */
            // ObjectCopyUtil.copyObjValue(t, dto, null, true);
            GdsSkuInfoReqDTO condition = new GdsSkuInfoReqDTO();
            condition.setId(t.getSkuId());
            condition.setGdsId(t.getGdsId());
            condition.setPropIds(propIds);
            return gdsSkuInfoQuerySV.querySkuInfoByOptions(condition, options);
        }

    }

}
