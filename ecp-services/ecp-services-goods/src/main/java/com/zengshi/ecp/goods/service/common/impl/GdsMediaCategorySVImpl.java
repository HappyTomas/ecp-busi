/** 
 * Project Name:ecp-services-goods 
 * File Name:GdsMedisCategorySVImpl.java 
 * Package Name:com.zengshi.ecp.goods.service.common.impl 
 * Date:2015年8月21日下午7:42:26 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.service.common.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.StringUtils;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.goods.dao.mapper.common.GdsMediaCategoryMapper;
import com.zengshi.ecp.goods.dao.model.GdsCategoryView;
import com.zengshi.ecp.goods.dao.model.GdsMediaCategory;
import com.zengshi.ecp.goods.dao.model.GdsMediaCategoryCriteria;
import com.zengshi.ecp.goods.dao.model.GdsMediaCategoryCriteria.Criteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.util.GdsCategoryCacheUtil;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsMediaCategorySV;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月21日下午7:42:26  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public class GdsMediaCategorySVImpl extends AbstractSVImpl implements IGdsMediaCategorySV {

    @Resource(name="seq_gds_media_category")
    private PaasSequence seqGdsMediaCategory;
    @Resource
    private GdsMediaCategoryMapper gdsMediaCategoryMapper;
    
    private static final String DEFAULT_ORDER_BY = "SORT_NO,CREATE_TIME";
    
    
    @Override
    public GdsMediaCategory saveGdsMedisCatg(GdsMediaCategory record)
            throws BusinessException {
        GdsMediaCategory parent = null;
    	
    	if(StringUtils.hasText(record.getCatgParent())){
        	 parent = queryGdsMediaCategoryByPk(record.getCatgParent());
        	if(null == parent || GdsUtils.isEqualsInvalid(parent.getStatus())){
        		throw new BusinessException(GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200311);
        	}else{
        		maxLevelCheck((short)(parent.getCatgLevel()+1));
        		record.setCatgLevel((short)(parent.getCatgLevel()+1));
        	}
        	if(GdsConstants.GdsCategory.IF_LEAF_1.equals(parent.getIfLeaf())){
                parent.setIfLeaf(GdsConstants.GdsCategory.IF_LEAF_0);
                parent.setUpdateStaff(record.getUpdateStaff());
                gdsMediaCategoryMapper.updateByPrimaryKeySelective(parent);
            }
        }else{
        	record.setCatgLevel(GdsConstants.GdsCategory.TOP_LEVEL);
        }
    	
        Timestamp now = now();
        if(!StringUtils.hasText(record.getCatgCode())){
        	record.setCatgCode(seqGdsMediaCategory.nextValue().toString());
        }
        if(null == record.getCreateTime()){
        	record.setCreateTime(now);
        }
        if(null == record.getUpdateTime()){
        	record.setUpdateTime(now);
        }
        
        if(StringUtils.isEmpty(record.getIfLeaf())){
        	record.setIfLeaf(GdsConstants.GdsCategory.IF_LEAF_1);
        }
        gdsMediaCategoryMapper.insert(record);
    	
    	
        return record;
    }
    
    

    @Override
	public GdsMediaCategoryRespDTO saveGdsMediaCatg(GdsMediaCategoryReqDTO reqDTO)
			throws BusinessException {
    	GdsMediaCategory record = new GdsMediaCategory();
    	ObjectCopyUtil.copyObjValue(reqDTO, record, null, true);
    	preInsert(reqDTO, record);
    	saveGdsMedisCatg(record);
    	return GdsUtils.doObjConvert(record, GdsMediaCategoryRespDTO.class, null, null);
	}



	@Override
    public GdsMediaCategory edisGdsMediaCategory(GdsMediaCategory gdsMediaCategory) throws BusinessException {
	    GdsMediaCategory oldRecord = queryGdsMediaCategoryByPk(gdsMediaCategory.getCatgCode());
        gdsMediaCategory.setUpdateTime(now());
        if(StringUtil.isNotBlank(gdsMediaCategory.getCatgParent())){
            if(gdsMediaCategory.getCatgCode().equals(gdsMediaCategory.getCatgParent())){
                LogUtil.error(MODULE, "父分类ID与当前分类ID相等");
                throw new BusinessException(GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200328);
            }
            GdsMediaCategoryReqDTO parentQuery = new GdsMediaCategoryReqDTO();
            parentQuery.setCatgCode(gdsMediaCategory.getCatgParent());
            GdsMediaCategory parent = queryGdsMediaCategoryByPk(gdsMediaCategory.getCatgParent());
            if(null != parent && (parent.getCatgLevel() - oldRecord.getCatgLevel()) >= 0){
                LogUtil.error(MODULE, "父分类层级高于当前分类层级,修改失败!");
                throw new BusinessException(GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200328);
            }
        }
        int i = gdsMediaCategoryMapper.updateByPrimaryKeySelective(gdsMediaCategory);
        if(i == 0){
            throw new BusinessException(GdsErrorConstants.GdsMediaCategory.ERROR_GOODS_MEDIACATG_200200);
        }
        return gdsMediaCategory;
    }
	
	
	

    @Override
    public GdsMediaCategory queryGdsMediaCategoryByPK(String catgCode, boolean isValid)
            throws BusinessException {
        GdsMediaCategory gdsMediaCatg = gdsMediaCategoryMapper.selectByPrimaryKey(catgCode);
        if( null != gdsMediaCatg){
            if(isValid && !GdsConstants.Commons.STATUS_VALID.equals(gdsMediaCatg.getStatus())){
                gdsMediaCatg = null;
            }
        }
        return gdsMediaCatg;
    }
    
    
    @Override
    public GdsMediaCategoryRespDTO queryGdsMediaCategoryByPK(GdsMediaCategoryReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO, "reqDTO");
        paramCheck(new Object[]{reqDTO}, new String[]{reqDTO.getCatgCode()});
        GdsMediaCategoryRespDTO respDTO = null;
        try {
            
            GdsMediaCategory record = gdsMediaCategoryMapper.selectByPrimaryKey(reqDTO.getCatgCode());
            if (null != record) {
                respDTO = new GdsMediaCategoryRespDTO();
                ObjectCopyUtil.copyObjValue(record, respDTO, null, false);
            }
        } catch (Exception e) {
            BusinessException be = new BusinessException(GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200317, new String[] { reqDTO.getCatgCode() });
            LogUtil.error(MODULE, be.getErrorMessage(), e);
            throw be;
        }
        return respDTO;
    }

    @Override
    public PageResponseDTO<GdsMediaCategoryRespDTO> queryGdsMediaCategoryRespDTOPaging(
            GdsMediaCategoryReqDTO dto) throws BusinessException {
        GdsMediaCategoryCriteria criteria = new GdsMediaCategoryCriteria();
        criteria.setLimitClauseStart(dto.getStartRowIndex());
        criteria.setLimitClauseCount(dto.getPageSize());
        criteria.setOrderByClause(DEFAULT_ORDER_BY);
        Criteria c = criteria.createCriteria();
        initCriteria(c,dto);
        return super.queryByPagination(dto, criteria, false, new GdsMediaCategoryRespDTOPaginationCallback());
    }
    
    @Override
    public int executeDeleteGdsMediaCategoryByPK(String catgCode, Long updateStaff)
            throws BusinessException {
        GdsMediaCategory catg = gdsMediaCategoryMapper.selectByPrimaryKey(catgCode);
        if(null == catg || GdsConstants.Commons.STATUS_INVALID.equals(catg.getStatus())){
            throw new BusinessException(GdsErrorConstants.GdsMediaCategory.ERROR_GOODS_MEDIACATG_200201,new String[]{catgCode});
        }
        if(executeCountSubMediaCatg(catgCode, GdsConstants.Commons.STATUS_VALID) > 0){
            throw new BusinessException(GdsErrorConstants.GdsMediaCategory.ERROR_GOODS_MEDIACATG_200203,new String[]{catg.getCatgName()});
        }
        catg.setUpdateStaff(updateStaff);
        catg.setUpdateTime(now());
        catg.setStatus(GdsConstants.Commons.STATUS_INVALID);
        int i = gdsMediaCategoryMapper.updateByPrimaryKeySelective(catg);
        
        updateIfLeafByPK(catg.getCatgParent(),updateStaff);
        if( 0 >= i){
           throw new BusinessException(GdsErrorConstants.GdsMediaCategory.ERROR_GOODS_MEDIACATG_200202); 
        }
        return i;
    }
    
    
    @Override
    public void updateIfLeafByPK(String catgCode,Long updateStaff)
            throws BusinessException {
        if(!StringUtils.hasText(catgCode)){
            return;   
        }
        GdsMediaCategory record = queryGdsMediaCategoryByPk(catgCode);
        if(null == record || GdsUtils.isEqualsInvalid(record.getStatus())){
            throw new BusinessException(GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200303);
        }
        // 统计指定分类编码下是否存在有效子分类,根据统计结果,对ifLeaf的当前值作出合理化变更
        if(executeCountSubMediaCatg(catgCode, GdsConstants.Commons.STATUS_VALID) <= 0){
            if(GdsConstants.GdsCategory.IF_LEAF_0.equals(record.getIfLeaf())){
                record.setIfLeaf(GdsConstants.GdsCategory.IF_LEAF_1);
            }
        }else if(executeCountSubMediaCatg(catgCode, GdsConstants.Commons.STATUS_VALID) > 0){
            if(GdsConstants.GdsCategory.IF_LEAF_1.equals(record.getIfLeaf())){
                record.setIfLeaf(GdsConstants.GdsCategory.IF_LEAF_0);
            }
        }
        if(null != updateStaff){
           record.setUpdateStaff(updateStaff);
        }
        record.setUpdateTime(now());
        gdsMediaCategoryMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public boolean queryExist(String catgName, Short level, String... status)
            throws BusinessException {
        return queryExist(catgName, null, level, status);
    }

    @Override
	public boolean queryExist(String catgName, Long shopId, Short level,
			String... status) throws BusinessException {
    	StringBuffer errorMsg = new StringBuffer();
        if(!StringUtils.hasText(catgName)){
            errorMsg.append("catgName,");
        }
        if(null == level){
            errorMsg.append("level,");
        }
        if(errorMsg.length() > 0){
            errorMsg.deleteCharAt(errorMsg.length() - 1);
            throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100,new String[]{errorMsg.toString()});
        }
        GdsMediaCategoryCriteria criteria = new GdsMediaCategoryCriteria();
        Criteria c = criteria.createCriteria();
        c.andCatgNameEqualTo(catgName);
        c.andCatgLevelEqualTo(level);
        initStatusCriteria(c, status);
        if(null != shopId){
        	c.andShopIdEqualTo(shopId);
        }
        return gdsMediaCategoryMapper.countByExample(criteria) > 0;
	}



	@Override
    public long executeCountSubMediaCatg(String catgCode, String... status)
            throws BusinessException {
        StringBuffer errorMsg = new StringBuffer();
        if(!StringUtils.hasText(catgCode)){
            errorMsg.append("catgCode,");
        }
        if(errorMsg.length() > 0){
            errorMsg.deleteCharAt(errorMsg.length() - 1);
            throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100,new String[]{errorMsg.toString()});
        }
        GdsMediaCategoryCriteria criteria = new GdsMediaCategoryCriteria();
        Criteria c = criteria.createCriteria();
        c.andCatgParentEqualTo(catgCode);
        initStatusCriteria(c, status);
        return gdsMediaCategoryMapper.countByExample(criteria);
    }

    @Override
    public List<GdsMediaCategory> querySubMediaCatgs(String catgCode, String... status)
            throws BusinessException {
        GdsMediaCategory catg = queryGdsMediaCategoryByPK(catgCode, true);
        if(catg == null){
            throw new BusinessException(GdsErrorConstants.GdsMediaCategory.ERROR_GOODS_MEDIACATG_200201,new String[]{catgCode});
        }else{
            GdsMediaCategoryCriteria criteria = new GdsMediaCategoryCriteria();
            criteria.setOrderByClause(DEFAULT_ORDER_BY);
            Criteria c = criteria.createCriteria();
            c.andCatgParentEqualTo(catgCode);
            initStatusCriteria(c, status);
            return gdsMediaCategoryMapper.selectByExample(criteria);
        }
    }

    @Override
    public GdsMediaCategory queryGdsMediaCategoryByPk(String catgCode) throws BusinessException {
        return gdsMediaCategoryMapper.selectByPrimaryKey(catgCode);
    }
    

    @Override
	public List<GdsMediaCategory> queryRootCategory(String... status)
			throws BusinessException {
    	 GdsMediaCategoryCriteria criteria = new GdsMediaCategoryCriteria();
         criteria.setOrderByClause(DEFAULT_ORDER_BY);
         Criteria c = criteria.createCriteria();
         c.andCatgLevelEqualTo(GdsConstants.GdsCategory.TOP_LEVEL);
         initStatusCriteria(c, status);
         return gdsMediaCategoryMapper.selectByExample(criteria);
	}



	@Override
    public void batchDelete(List<String> catgCodes, Long updateStaff) throws BusinessException {
        StringBuffer errorMsg = new StringBuffer();
        if(null == catgCodes || !catgCodes.isEmpty()){
            errorMsg.append("catgCode,");
        }
        if(errorMsg.length() > 0){
            errorMsg.deleteCharAt(errorMsg.length() - 1);
            throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100,new String[]{errorMsg.toString()});
        }
        int total = 0;
        for(String catgCode : catgCodes){
            try{
                this.executeDeleteGdsMediaCategoryByPK(catgCode, updateStaff);
                ++ total;
            }catch(Exception e){
                if(!(e instanceof BusinessException)){
                    throw new BusinessException(GdsErrorConstants.GdsMediaCategory.ERROR_GOODS_MEDIACATG_200202);
                }
            }
        }
        if( catgCodes.size() != total){
            throw new BusinessException(GdsErrorConstants.GdsMediaCategory.ERROR_GOODS_MEDIACATG_200204);
        }
    }

	
	@Override
    public List<GdsMediaCategoryRespDTO> queryCategoryTraceUpon(GdsMediaCategoryReqDTO reqDTO) throws BusinessException {
	    paramNullCheck(reqDTO, "reqDTO");
        paramCheck(new Object[] { reqDTO.getCatgCode() }, new String[] { "reqDTO.catgCode" });

        GdsMediaCategoryRespDTO respDTO = queryGdsMediaCategoryByPK(reqDTO);
        /*
         * if(null == respDTO || GdsUtils.isEqualsInvalid(respDTO.getStatus())){ return null; }
         */
        if (null == respDTO) {
            return null;
        }
        List<GdsMediaCategoryRespDTO> lst = new ArrayList<GdsMediaCategoryRespDTO>();
        lst.add(respDTO);
        int loopCount = 0;
        while (null != respDTO && StringUtil.isNotBlank(respDTO.getCatgParent()) && ++ loopCount < 20) {
            GdsMediaCategoryReqDTO condition = new GdsMediaCategoryReqDTO();
            condition.setCatgCode(respDTO.getCatgParent());
            respDTO = queryGdsMediaCategoryByPK(condition);
            /*
             * record = queryGdsCategoryById(record.getCatgParent()); GdsCategoryRespDTO temp = new
             * GdsCategoryRespDTO(); ObjectCopyUtil.copyObjValue(record, temp, null, true);
             */
            if (null != respDTO) {
                lst.add(respDTO);
            }
        }
        if (lst.size() > 1) {
            Collections.reverse(lst);
        }
        return lst;
    }



    /*
     * 
     * 媒体分类分页查询回调实现类.<br>
     * Project Name:ecp-services-goods <br>
     * Description: <br>
     * Date:2015年8月20日上午9:22:15  <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     * 
     * @author liyong7
     * @version GdsLabelSVImpl 
     * @since JDK 1.6
     */
    protected class GdsMediaCategoryRespDTOPaginationCallback extends PaginationCallback<GdsMediaCategory, GdsMediaCategoryRespDTO>{

        @Override
        public List<GdsMediaCategory> queryDB(BaseCriteria criteria) {
           return gdsMediaCategoryMapper.selectByExample((GdsMediaCategoryCriteria)criteria);
        }

        @Override
        public long queryTotal(BaseCriteria criteria) {
           return gdsMediaCategoryMapper.countByExample((GdsMediaCategoryCriteria)criteria);
        }

        @Override
        public GdsMediaCategoryRespDTO warpReturnObject(GdsMediaCategory t) {
            GdsMediaCategoryRespDTO dto = new GdsMediaCategoryRespDTO();
            ObjectCopyUtil.copyObjValue(t, dto, null, true);
            return dto;
        }
        
    }
    
    
    /* 
     * 初始化分页查询条件. <br/> 
     * 
     * @author liyong7
     * @param criteria
     * @param dto 
     * @since JDK 1.6 
     */ 
    private void initCriteria(Criteria c, GdsMediaCategoryReqDTO dto) {
        if(StringUtils.hasText(dto.getCatgCode())){
            c.andCatgCodeEqualTo(dto.getCatgCode());
        }
        if(StringUtils.hasText(dto.getCatgName())){
            c.andCatgNameLike("%"+dto.getCatgName()+"%");
        }
        if(StringUtils.hasText(dto.getStatus())){
            c.andStatusEqualTo(dto.getStatus());
        }
        if(dto.getShopId()!=null){
            c.andShopIdEqualTo(dto.getShopId());
        }
        if( null != dto.getCatgLevel()){
            c.andCatgLevelEqualTo(dto.getCatgLevel());
        }
        if(null != dto.getCatgParent()){
            c.andCatgParentEqualTo(dto.getCatgParent());
        }
        
        if(CollectionUtils.isNotEmpty(dto.getShopIdLst())){
        	if(1 == dto.getShopIdLst().size()){
        		c.andShopIdEqualTo(dto.getShopIdLst().get(0));
        	}else{
        		c.andShopIdIn(dto.getShopIdLst());
        	}
        }
        if(StringUtil.isNotBlank(dto.getStatus())){
        	c.andStatusEqualTo(dto.getStatus());
        }
    }
    
    private void maxLevelCheck(Short currentLevel){
    	// 查询最大层级配置.
    	BaseSysCfgRespDTO sysCfgRespDTO = SysCfgUtil.fetchSysCfg("GDS_CATEGORY_MAX_LEVEL");
    	if(null != sysCfgRespDTO && StringUtil.isNotBlank(sysCfgRespDTO.getParaValue())){
    		if(Short.valueOf(sysCfgRespDTO.getParaValue()) < currentLevel){
    			throw new BusinessException(GdsErrorConstants.GdsMediaCategory.ERROR_GOODS_MEDIACATG_200206,new String[]{sysCfgRespDTO.getParaValue()});
    		}
    	}
    }
    
}

