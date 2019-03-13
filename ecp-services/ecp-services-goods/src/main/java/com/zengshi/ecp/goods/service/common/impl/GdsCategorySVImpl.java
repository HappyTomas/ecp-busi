/** 
 * Project Name:ecp-services-goods 
 * File Name:GdsCategorySVImpl.java 
 * Package Name:com.zengshi.ecp.goods.service.common.impl 
 * Date:2015年8月12日下午8:05:59 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */
package com.zengshi.ecp.goods.service.common.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.StringUtils;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.goods.dao.mapper.common.GdsCategoryMapper;
import com.zengshi.ecp.goods.dao.mapper.common.GdsCategorySyncMapper;
import com.zengshi.ecp.goods.dao.mapper.common.GdsCategoryViewMapper;
import com.zengshi.ecp.goods.dao.model.GdsCategory;
import com.zengshi.ecp.goods.dao.model.GdsCategoryCriteria;
import com.zengshi.ecp.goods.dao.model.GdsCategoryCriteria.Criteria;
import com.zengshi.ecp.goods.dao.model.GdsCategorySync;
import com.zengshi.ecp.goods.dao.model.GdsCategorySyncCriteria;
import com.zengshi.ecp.goods.dao.model.GdsCategoryView;
import com.zengshi.ecp.goods.dao.model.GdsCategoryViewCriteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsDataImportConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalog2SiteReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalogRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.category.GdsCategoryCompareReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.category.GdsCategoryCompareRespDTO;
import com.zengshi.ecp.goods.dubbo.util.GdsCategoryCacheUtil;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsCatalog2SiteSV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsCategorySV;
import com.zengshi.ecp.server.auth.DataAuthType;
import com.zengshi.ecp.server.auth.DataAuthValid;
import com.zengshi.ecp.server.auth.DataObject;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ResourceMsgUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月12日下午8:05:59 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version
 * @since JDK 1.6
 */
public class GdsCategorySVImpl extends AbstractSVImpl implements IGdsCategorySV {

    @Resource(name = "seq_gds_category")
    private PaasSequence seqGdsCategory;

    @Resource
    private GdsCategoryMapper gdsCategoryMapper;

    @Resource
    private IGdsCatalog2SiteSV gdsCatalog2SiteSV;

    private static final String DEFAULT_ORDER_BY = "SORT_NO,CREATE_TIME";

    @Resource
    private GdsCategorySyncMapper gdsCategorySyncMapper;

    @Resource
    private GdsCategoryViewMapper gdsCategoryViewMapper;

    @Override
    public GdsCategoryRespDTO queryGdsCategoryByPK(GdsCategoryReqDTO reqDTO) throws BusinessException {
        try {
            GdsCategoryRespDTO respDTO = GdsCategoryCacheUtil.getCategoryByCatgCodeFromCache(reqDTO.getCatgCode());
            if (null != respDTO) {
                if (StringUtil.isNotBlank(reqDTO.getStatus())) {
                    if (!respDTO.getStatus().equals(reqDTO.getStatus())) {
                        return null;
                    }
                }
            } else {
                GdsCategoryView catgView = gdsCategoryViewMapper.selectByPrimaryKey(reqDTO.getCatgCode());
                if (null != catgView) {
                    respDTO =copyView2Resp(catgView);
                    if (GdsUtils.isEqualsValid(catgView.getStatus())) {
                        GdsCategoryCacheUtil.cacheCategory(respDTO);
                    }
                }
            }
            if (Boolean.TRUE == reqDTO.getIsContainSub()) {
                recursiveQuery(respDTO);
            }
            return respDTO;
        } catch (Exception e) {
            BusinessException be = new BusinessException(GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200317, new String[] { reqDTO.getCatgCode() });
            LogUtil.error(MODULE, be.getErrorMessage(), e);
            throw be;
        }

    }

    @Override
    public GdsCategoryRespDTO saveGdsCategory(GdsCategoryReqDTO reqDTO) throws BusinessException {
        GdsCategory record = copyReq2Info(reqDTO);
        preInsert(reqDTO, record);
        int i = saveGdsCategory(record);
        if (i <= 0) {
            throw new BusinessException(GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200310);
        }
        GdsCategoryRespDTO respDTO = copyInfo2Resp(record);
        return respDTO;
    }

    /**
     * @see com.zengshi.ecp.goods.service.common.interfaces.IGdsCategorySV#saveGdsCategory(com.zengshi.ecp.goods.dao.model.GdsCategory)
     */
    @Override
    public int saveGdsCategory(GdsCategory gdsCategory) throws BusinessException {

        GdsCategory parent = null;

        if (StringUtils.hasText(gdsCategory.getCatgParent())) {
            parent = queryGdsCategoryById(gdsCategory.getCatgParent());
            if (null == parent || GdsUtils.isEqualsInvalid(parent.getStatus())) {
                throw new BusinessException(GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200311);
            } else {
                maxLevelCheck((short) (parent.getCatgLevel() + 1));
                gdsCategory.setCatgLevel((short) (parent.getCatgLevel() + 1));
            }
            if (GdsConstants.GdsCategory.IF_LEAF_1.equals(parent.getIfLeaf())) {
                parent.setIfLeaf(GdsConstants.GdsCategory.IF_LEAF_0);
                parent.setUpdateStaff(gdsCategory.getUpdateStaff());
                editGdsCategory(parent);
            }
        } else {
            gdsCategory.setCatgLevel(GdsConstants.GdsCategory.TOP_LEVEL);
        }

        if (StringUtil.isBlank(gdsCategory.getStatus())) {
            gdsCategory.setStatus(GdsConstants.Commons.STATUS_VALID);
        }

        Timestamp now = now();
        if (!StringUtils.hasText(gdsCategory.getCatgCode())) {
            gdsCategory.setCatgCode(seqGdsCategory.nextValue().toString());
        }
        if (null == gdsCategory.getCreateTime()) {
            gdsCategory.setCreateTime(now);
        }
        if (null == gdsCategory.getUpdateTime()) {
            gdsCategory.setUpdateTime(now);
        }
        if (StringUtils.isEmpty(gdsCategory.getIfLeaf())) {
            gdsCategory.setIfLeaf(GdsConstants.GdsCategory.IF_LEAF_1);
        }
        return gdsCategoryMapper.insert(gdsCategory);
    }

    /**
     * @see com.zengshi.ecp.goods.service.common.interfaces.IGdsCategorySV#queryGdsCategoryById(java.lang.Long)
     */
    @Override
    public GdsCategory queryGdsCategoryById(String catgCode) throws BusinessException {
        GdsCategory record = null;
        if (StringUtil.isNotBlank(catgCode)) {
            GdsCategoryReqDTO reqDTO = new GdsCategoryReqDTO();
            reqDTO.setCatgCode(catgCode);
            GdsCategoryRespDTO respDTO = this.queryGdsCategoryByPK(reqDTO);
            if (null != respDTO) {
                record=copyResp2Info(respDTO);
            }
        }
        return record;
    }

    @Override
    public GdsCategoryRespDTO queryGdsCategoryById(GdsCategoryReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO, "reqDTO");
        paramNullCheck(reqDTO.getCatgCode(), "reqDTO.catgCode");
        return this.queryGdsCategoryByPK(reqDTO);
    }

    @Override
    public List<GdsCategoryRespDTO> querySubCategoryConnectByCatgParent(GdsCategoryReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO, "reqDTO");
        paramNullCheck(reqDTO.getCatgParent(), "reqDTO.catgParent");
        GdsCategoryViewCriteria criteria = new GdsCategoryViewCriteria();
        GdsCategoryViewCriteria.Criteria c = criteria.createCriteria();
        c.andCatgPathLike("%<" + reqDTO.getCatgParent() + ">%");
        c.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        List<GdsCategoryView> lst = gdsCategoryViewMapper.selectByExample(criteria);
        List<GdsCategoryRespDTO> result = new ArrayList<GdsCategoryRespDTO>();
        if (CollectionUtils.isNotEmpty(lst)) {
            for (Iterator<GdsCategoryView> iterator = lst.iterator(); iterator.hasNext();) {
                GdsCategoryView obj = iterator.next();
                GdsCategoryRespDTO respDTO = new GdsCategoryRespDTO();
                setProperties(respDTO, obj);
                result.add(respDTO);
            }
        }
        return result;

    }
    
    
    /**
     * @see com.zengshi.ecp.goods.service.common.interfaces.IGdsCategorySV#editGdsCategory(com.zengshi.ecp.goods.dao.model.GdsCategory)
     */
    @Override
    public int editGdsCategory(GdsCategory gdsCategory) throws BusinessException {
        if (gdsCategory != null && null == gdsCategory.getUpdateTime()) {
            gdsCategory.setUpdateTime(now());
        }
        if (null != gdsCategory) {
            GdsCategoryCacheUtil.removeCategory(gdsCategory.getCatgCode());
        }
        return gdsCategoryMapper.updateByPrimaryKeySelective(gdsCategory);
    }

    @Override
    public int editGdsCategory(GdsCategoryReqDTO reqDTO) throws BusinessException {
        GdsCategory record=copyReq2Info(reqDTO);
        
        if(StringUtil.isNotBlank(reqDTO.getCatgParent())){
            if(reqDTO.getCatgCode().equals(reqDTO.getCatgParent())){
                LogUtil.error(MODULE, "父分类ID与当前分类ID相等");
                throw new BusinessException(GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200328);
            }
            GdsCategoryReqDTO parentQuery = new GdsCategoryReqDTO();
            parentQuery.setCatgCode(reqDTO.getCatgParent());
            GdsCategoryRespDTO parent = queryGdsCategoryById(parentQuery);
            GdsCategoryReqDTO oldRecordQuery = new GdsCategoryReqDTO();
            oldRecordQuery.setCatgCode(reqDTO.getCatgCode());;
            GdsCategoryRespDTO oldRecord = queryGdsCategoryByPK(oldRecordQuery);
            if(null != parent && (parent.getCatgLevel() - oldRecord.getCatgLevel()) >= 0){
                LogUtil.error(MODULE, "父分类层级高于当前分类层级,修改失败!");
                throw new BusinessException(GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200328);
            }
        }
        if(reqDTO.getCommission()!=null && !"".equals(reqDTO.getCommission())){
        	record.setCommission(reqDTO.getCommission());
        }
        preUpdate(reqDTO, record);
        GdsCategory oldRecord = queryGdsCategoryById(reqDTO.getCatgCode());

        /*
         * GdsInterfaceCatg interfaceCatg =
         * gdsInterfaceCatgSV.queryInterfaceCatgByCatgCode(reqDTO.getCatgCode());
         * 
         * if(null != interfaceCatg){ List<String> updateFields = new ArrayList<String>(); String
         * updateRule = interfaceCatg.getUpdateRule(); if(StringUtil.isNotBlank(updateRule)){
         * updateFields = JSONObject.parseObject(updateRule, List.class); }else{ updateFields = new
         * ArrayList<String>(); } if(!oldRecord.getCatgName().equals(record.getCatgName())){
         * if(!updateFields.contains(DataImportConstants.GdsCategoryUpdateChkFields.CATG_NAME)){
         * updateFields.add(DataImportConstants.GdsCategoryUpdateChkFields.CATG_NAME); } }
         * if((oldRecord.getSortNo() != null && oldRecord.getSortNo().equals(reqDTO.getSortNo())) ||
         * (reqDTO.getSortNo() != null && reqDTO.getSortNo().equals(oldRecord.getSortNo()))){
         * if(!updateFields.contains(DataImportConstants.GdsCategoryUpdateChkFields.SORT_NO)){
         * updateFields.add(DataImportConstants.GdsCategoryUpdateChkFields.SORT_NO); } }
         * 
         * if(null == oldRecord.getSortNo()){ if(null != record.getSortNo()){
         * if(!updateFields.contains(DataImportConstants.GdsCategoryUpdateChkFields.SORT_NO)){
         * updateFields.add(DataImportConstants.GdsCategoryUpdateChkFields.SORT_NO); } } }else{
         * if(null == record.getSortNo() || !oldRecord.getSortNo().equals(reqDTO.getSortNo())) {
         * if(!updateFields.contains(DataImportConstants.GdsCategoryUpdateChkFields.SORT_NO)){
         * updateFields.add(DataImportConstants.GdsCategoryUpdateChkFields.SORT_NO); } } }
         * 
         * if(!CollectionUtils.isNotEmpty(updateFields)){
         * interfaceCatg.setUpdateRule(JSONObject.toJSONString(updateFields)); }
         * 
         * GdsInterfaceCatgReqDTO icrd = new GdsInterfaceCatgReqDTO();
         * ObjectCopyUtil.copyObjValue(interfaceCatg, icrd, null, true);
         * gdsInterfaceCatgSV.updateInterfaceCatg(icrd); }
         */
        int i = gdsCategoryMapper.updateByPrimaryKeySelective(record);
        if (null != oldRecord) {
            GdsCategoryCacheUtil.removeCategory(oldRecord.getCatgCode());
            if (StringUtil.isNotBlank(oldRecord.getCatgParent())) {
                GdsCategoryCacheUtil.removeCategory(oldRecord.getCatgParent());
            }
            // 分类名称修改后向索引服务发起索引消息.
            if (!oldRecord.getCatgName().equals(record.getCatgName())) {
                GdsUtils.sendGdsIndexMsg(null, reqDTO.getCatgCode(), MODULE);
            }
        }
        return i;
    }

    @Override
    public boolean queryExist(String catgName, String catgType, Short level, Long shopId, String[] excludeCatgCodes, String... status) throws BusinessException {
        GdsCategoryCriteria criteria = new GdsCategoryCriteria();
        GdsCategoryReqDTO dto = new GdsCategoryReqDTO();
        dto.setCatgName(catgName);
        dto.setCatgType(catgType);
        dto.setCatgLevel(level);
        dto.setShopId(shopId);
        Criteria c = criteria.createCriteria();
        initCriteria(c, dto);
        initStatusCriteria(c, status);
        if (null != excludeCatgCodes) {
            switch (excludeCatgCodes.length) {
            case 1:
                c.andCatgCodeNotEqualTo(excludeCatgCodes[0]);
                break;
            default:
                c.andCatgCodeNotIn(Arrays.asList(excludeCatgCodes));
                break;
            }
        }
        return gdsCategoryMapper.countByExample(criteria) > 0;
    }

    /**
     * @see com.zengshi.ecp.goods.service.common.interfaces.IGdsCategorySV#disableGdsCategory(long,
     *      java.lang.Long)
     */
    @Override
    public void executeDisableGdsCategory(String catgCode, Long updateStaff) throws BusinessException {
        paramCheck(new Object[] { catgCode }, new String[] { "catgCode" });
        GdsCategory gdsCatg = gdsCategoryMapper.selectByPrimaryKey(catgCode);
        if (gdsCatg == null || GdsConstants.Commons.STATUS_INVALID.equals(gdsCatg.getStatus())) {
            // throw new
            // BusinessException(GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200303);
            return;
        }
        /*
         * if(GdsConstants.Commons.STATUS_INVALID.equals(gdsCatg.getStatus())){ throw new
         * BusinessException(GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200304); }
         */
        /*
         * if(countExistSubValidCatg(gdsCatg.getCatgCode()) > 0){ // 存在子级有效分类,不允许禁用该分类! throw new
         * BusinessException(GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200306); }
         */
        gdsCatg.setStatus(GdsConstants.Commons.STATUS_INVALID);
        gdsCatg.setUpdateStaff(updateStaff);
        gdsCatg.setUpdateTime(now());
        gdsCategoryMapper.updateByPrimaryKeySelective(gdsCatg);

        clearCatgMap(catgCode, updateStaff);

        updateIfLeafByPK(gdsCatg.getCatgParent(), updateStaff);
        // 清理缓存.
        GdsCategoryCacheUtil.removeCategory(catgCode);
    }

    @Override
    public void deleteGdsCategory(GdsCategoryReqDTO reqDTO) throws BusinessException {
        executeDisableGdsCategory(reqDTO.getCatgCode(), reqDTO.getStaff().getId());
    }

    /**
     * @see com.zengshi.ecp.goods.service.common.interfaces.IGdsCategorySV#queryGdsCategory(java.lang.String,
     *      java.lang.String[])
     */
    @Override
    public List<GdsCategory> queryGdsCategory(String catgType, Short level, String... status) throws BusinessException {

        paramCheck(new Object[] { catgType, level }, new String[] { "catgType", "level" });

        GdsCategoryCriteria criteria = new GdsCategoryCriteria();
        criteria.setOrderByClause(DEFAULT_ORDER_BY);
        Criteria c = criteria.createCriteria();
        c.andCatgLevelEqualTo(level);
        initStatusCriteria(c, status);
        return gdsCategoryMapper.selectByExample(criteria);
    }

    /**
     * @see com.zengshi.ecp.goods.service.common.interfaces.IGdsCategorySV#queryRootGdsCategory(java.lang.String,java.lang.String[])
     */
    @Override
    public List<GdsCategoryRespDTO> queryRootGdsCategory(String catgType, Long shopId, String... status) throws BusinessException {
        GdsCategoryCriteria criteria = new GdsCategoryCriteria();
        criteria.setOrderByClause(DEFAULT_ORDER_BY);
        Criteria c = criteria.createCriteria();
        c.andCatgLevelEqualTo(GdsConstants.GdsCategory.TOP_LEVEL);
        c.andCatgTypeEqualTo(catgType);
        if (null != shopId) {
            c.andShopIdEqualTo(shopId);
        }
        initStatusCriteria(c, status);
        return GdsUtils.doConvert(gdsCategoryMapper.selectByExample(criteria), GdsCategoryRespDTO.class);
    }

    /**
     * @see com.zengshi.ecp.goods.service.common.interfaces.IGdsCategorySV#querySubGdsCategory(java.lang.String,
     *      java.lang.String[])
     */
    @Override
    public List<GdsCategoryRespDTO> querySubGdsCategory(String catgParent, String... status) throws BusinessException {
        GdsCategoryCriteria criteria = new GdsCategoryCriteria();
        criteria.setOrderByClause(DEFAULT_ORDER_BY);
        Criteria c = criteria.createCriteria();
        c.andCatgParentEqualTo(catgParent);
        initStatusCriteria(c, status);
        return GdsUtils.doConvert(gdsCategoryMapper.selectByExample(criteria), GdsCategoryRespDTO.class);
    }

    /**
     * @see com.zengshi.ecp.goods.service.common.interfaces.IGdsCategorySV#queryExistSubValidCatg(java.lang.String)
     */
    @Override
    public Long countSubCategory(String catgParent, String... status) throws BusinessException {
        GdsCategoryCriteria criteria = new GdsCategoryCriteria();
        Criteria c = criteria.createCriteria();
        c.andCatgParentEqualTo(catgParent);
        initStatusCriteria(c, status);
        return gdsCategoryMapper.countByExample(criteria);
    }

    @Override
    public PageResponseDTO<GdsCategoryRespDTO> queryGdsCatetoryRespDTOPaging(GdsCategoryReqDTO dto) throws BusinessException {
        GdsCategoryViewCriteria criteria = new GdsCategoryViewCriteria();
        criteria.setLimitClauseStart(dto.getStartRowIndex());
        criteria.setLimitClauseCount(dto.getPageSize());
        if(StringUtil.isNotBlank(dto.getOrderBy())){
            criteria.setOrderByClause(dto.getOrderBy());
        }else{
            criteria.setOrderByClause(DEFAULT_ORDER_BY);
        }
        GdsCategoryViewCriteria.Criteria c = criteria.createCriteria();
        initCatgViewCriteria(c, dto);
        return super.queryByPagination(dto, criteria, false, new GdsCatgRespDTOPaginationWithAuthCallback());
        /*
         * GdsCategoryCriteria criteria = new GdsCategoryCriteria();
         * criteria.setLimitClauseStart(dto.getStartRowIndex());
         * criteria.setLimitClauseCount(dto.getPageSize());
         * criteria.setOrderByClause(DEFAULT_ORDER_BY); Criteria c = criteria.createCriteria();
         * initCriteria(c, dto); return super.queryByPagination(dto, criteria, false, new
         * GdsCatgRespDTOPaginationCallback());
         */
    }

    @Override
    public void updateIfLeafByPK(String catgCode, Long updateStaff) throws BusinessException {
        if (!StringUtils.hasText(catgCode)) {
            return;
        }
        GdsCategory record = queryGdsCategoryById(catgCode);
        /*
         * if(null == record || GdsUtils.isEqualsInvalid(record.getStatus())){ throw new
         * BusinessException(GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200303); }
         */
        // 统计指定分类编码下是否存在有效子分类,根据统计结果,对ifLeaf的当前值作出合理化变更
        if (countSubCategory(catgCode, GdsConstants.Commons.STATUS_VALID) <= 0) {
            if (GdsConstants.GdsCategory.IF_LEAF_0.equals(record.getIfLeaf())) {
                record.setIfLeaf(GdsConstants.GdsCategory.IF_LEAF_1);
            }
        } else if (countSubCategory(catgCode, GdsConstants.Commons.STATUS_VALID) > 0) {
            if (GdsConstants.GdsCategory.IF_LEAF_1.equals(record.getIfLeaf())) {
                record.setIfLeaf(GdsConstants.GdsCategory.IF_LEAF_0);
            }
        }
        if (null != updateStaff) {
            record.setUpdateStaff(updateStaff);
        }
        record.setUpdateTime(now());
        gdsCategoryMapper.updateByPrimaryKeySelective(record);
        GdsCategoryCacheUtil.removeCategory(catgCode);
    }

    @Override
    public GdsCategoryRespDTO queryRootCategoryByPK(String catgCode) throws BusinessException {
        if (StringUtil.isBlank(catgCode)) {
            throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100, new String[] { "catgCode" });
        }
        GdsCategoryReqDTO queryCondition = new GdsCategoryReqDTO();
        queryCondition.setCatgCode(catgCode);
        GdsCategoryRespDTO respDTO = queryGdsCategoryByPK(queryCondition);
        // 根据分类编码查询为空则返回空。
        if (respDTO == null) {
            return null;
        }
        // 返回分类即为根分类则直接返回当前分类。
        if (GdsConstants.GdsCategory.TOP_LEVEL == respDTO.getCatgLevel()) {
            return respDTO;
        } else {
            while (1 < respDTO.getCatgLevel() && StringUtil.isNotBlank(respDTO.getCatgParent())) {
                queryCondition.setCatgCode(respDTO.getCatgParent());
                respDTO = queryGdsCategoryByPK(queryCondition);
                if (null == respDTO) {
                    break;
                }
            }
        }
        return respDTO;
    }

    @Override
    public List<GdsCategoryRespDTO> queryCategoryTraceUpon(String catgCode) throws BusinessException {
        paramCheck(new Object[] { catgCode }, new String[] { "catgCode" });

        GdsCategoryReqDTO reqDTO = new GdsCategoryReqDTO();
        reqDTO.setCatgCode(catgCode);
        GdsCategoryRespDTO respDTO = queryGdsCategoryByPK(reqDTO);
        /*
         * if(null == respDTO || GdsUtils.isEqualsInvalid(respDTO.getStatus())){ return null; }
         */
        if (null == respDTO) {
            return null;
        }
        List<GdsCategoryRespDTO> lst = new ArrayList<GdsCategoryRespDTO>();
        lst.add(respDTO);
        int loopCount = 0;
        while (null != respDTO && StringUtil.isNotBlank(respDTO.getCatgParent()) && ++ loopCount < 20) {
            GdsCategoryReqDTO condition = new GdsCategoryReqDTO();
            condition.setCatgCode(respDTO.getCatgParent());
            respDTO = queryGdsCategoryByPK(condition);
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

    @Override
    public GdsCategoryRespDTO queryCategoryLimitByCatgCodeAndTargetLevel(GdsCategoryReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO, "reqDTO");
        paramCheck(new Object[] { reqDTO.getCatgCode(), reqDTO.getTargetLevel() }, new String[] { "reqDTO.catgCode", "reqDTO.targetLevel" });

        if (1 > reqDTO.getTargetLevel()) {
            return null;
        }

        GdsCategoryRespDTO catgRespDTO = queryGdsCategoryByPK(reqDTO);
        if (null == catgRespDTO) {
            return null;
        }
        Short level = catgRespDTO.getCatgLevel();
        if (level.shortValue() == reqDTO.getTargetLevel().shortValue()) {
            return catgRespDTO;
        }

        if (reqDTO.getTargetLevel() > catgRespDTO.getCatgLevel()) {
            BusinessException be = new BusinessException(GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200315);
            LogUtil.warn(MODULE, be.getErrorMessage() + "catgCode=" + reqDTO.getCatgCode() + ";targetLevel=" + reqDTO.getTargetLevel());
            return null;
            // throw new
            // BusinessException(GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200315);
        }

        List<GdsCategoryRespDTO> lst = queryCategoryTraceUpon(catgRespDTO.getCatgCode());
        if (CollectionUtils.isNotEmpty(lst)) {
            int targetLevel = Integer.valueOf(reqDTO.getTargetLevel());
            if (lst.size() >= targetLevel) {
                return lst.get(targetLevel - 1);
            } else {
                return null;
            }
        } else {
            return null;
        }

    }

    @DataAuthValid(authType = DataAuthType.BIND, funcCode = GdsConstants.GdsDataAuthFunc.GDS_CATG_SEARCH_DA1007, value = "reqDTO")
    @Override
    public GdsCategoryRespDTO queryGdsCategoryByPKWithAuth(@DataObject(value = "reqDTO") GdsCategoryReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO, "reqDTO");
        paramNullCheck(reqDTO.getCatgCode(), "reqDTO.catgCode");
        try {
            GdsCategoryRespDTO respDTO = null;
            GdsCategoryViewCriteria criteria = initCatgViewCriteria(reqDTO);
            List<GdsCategoryView> lst = gdsCategoryViewMapper.selectByExample(criteria);
            if (CollectionUtils.isNotEmpty(lst)) {
                respDTO = new GdsCategoryRespDTO();
                setProperties(respDTO, lst.get(0));
            }
            return respDTO;
        } catch (Exception e) {
            BusinessException be = new BusinessException(GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200317, new String[] { reqDTO.getCatgCode() });
            LogUtil.error(MODULE, be.getErrorMessage(), e);
            throw be;
        }
    }

    @DataAuthValid(authType = DataAuthType.BIND, funcCode = GdsConstants.GdsDataAuthFunc.GDS_CATG_SEARCH_DA1007, value = "reqDTO")
    @Override
    public PageResponseDTO<GdsCategoryRespDTO> queryGdsCatetoryRespDTOPagingWithAuth(@DataObject(value = "reqDTO") GdsCategoryReqDTO dto) throws BusinessException {
        GdsCategoryViewCriteria criteria = dto.getCriteria();
        criteria.setLimitClauseStart(dto.getStartRowIndex());
        criteria.setLimitClauseCount(dto.getPageSize());
        
        if(StringUtil.isNotBlank(dto.getOrderBy())){
            criteria.setOrderByClause(dto.getOrderBy());
        }else{
            criteria.setOrderByClause(DEFAULT_ORDER_BY);
        }
        List<GdsCategoryViewCriteria.Criteria> cLst = criteria.getOredCriteria();
        if (CollectionUtils.isNotEmpty(cLst)) {
            for (Iterator<GdsCategoryViewCriteria.Criteria> iterator = cLst.iterator(); iterator.hasNext();) {
                GdsCategoryViewCriteria.Criteria c = iterator.next();
                initCatgViewCriteria(c, dto);
            }
        } else {
            GdsCategoryViewCriteria.Criteria c = criteria.createCriteria();
            initCatgViewCriteria(c, dto);
        }
        return super.queryByPagination(dto, criteria, false, new GdsCatgRespDTOPaginationWithAuthCallback());
    }

    @Override
    public GdsCategoryCompareRespDTO executeCompare(GdsCategoryCompareReqDTO reqDTO) throws BusinessException {
        paramCheck(new Object[] { reqDTO }, new String[] { "reqDTO" });
        paramCheck(new Object[] { reqDTO.getSourceCode(), reqDTO.getTargetCode() }, new String[] { "reqDTO.sourceCode", "reqDTO.targetCode" });
        GdsCategoryCompareRespDTO respDTO = new GdsCategoryCompareRespDTO();

        if (reqDTO.getSourceCode().equals(reqDTO.getTargetCode())) {
            respDTO.setResult(GdsCategoryCompareRespDTO.RESULT_EQUAL);
            return respDTO;
        }

        GdsCategoryReqDTO sourceCondition = new GdsCategoryReqDTO();
        sourceCondition.setCatgCode(reqDTO.getSourceCode());
        GdsCategoryRespDTO source = queryGdsCategoryByPK(sourceCondition);
        if (null == source) {
            respDTO.setResult(GdsCategoryCompareRespDTO.RESULT_ERROR);
            respDTO.setMsg(ResourceMsgUtil.getMessage(GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200314, new String[] { reqDTO.getSourceCode() }));
            return respDTO;
        }
        GdsCategoryReqDTO targetCondition = new GdsCategoryReqDTO();
        targetCondition.setCatgCode(reqDTO.getTargetCode());
        GdsCategoryRespDTO target = queryGdsCategoryByPK(targetCondition);
        if (null == target) {
            respDTO.setResult(GdsCategoryCompareRespDTO.RESULT_ERROR);
            respDTO.setMsg(ResourceMsgUtil.getMessage(GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200314, new String[] { reqDTO.getTargetCode() }));
            return respDTO;
        }
        // 分类层级比对.
        int levelDiff = source.getCatgLevel() - target.getCatgLevel();
        // 如果层级差为正值,表示源分类为目标分类的子分类.
        if (levelDiff > 0) {
            List<GdsCategoryRespDTO> traceLst = queryCategoryTraceUpon(source.getCatgCode());
            if (CollectionUtils.isNotEmpty(traceLst)) {
                // 轮循判断
                for (GdsCategoryRespDTO catgRespDTO : traceLst) {
                    if (reqDTO.getTargetCode().equals(catgRespDTO.getCatgCode())) {
                        respDTO.setResult(GdsCategoryCompareRespDTO.RESULT_GREAT_THAN);
                        break;
                    }
                }
            }
        } else {
            // 层级差为负值,表示源分类为目标分类的父分类.
            List<GdsCategoryRespDTO> traceLst = queryCategoryTraceUpon(target.getCatgCode());
            if (CollectionUtils.isNotEmpty(traceLst)) {
                // 轮循判断
                for (GdsCategoryRespDTO catgRespDTO : traceLst) {
                    if (reqDTO.getSourceCode().equals(catgRespDTO.getCatgCode())) {
                        respDTO.setResult(GdsCategoryCompareRespDTO.RESULT_LESS_THAN);
                        break;
                    }
                }
            }
        }

        if (null == respDTO.getResult()) {
            respDTO.setResult(GdsCategoryCompareRespDTO.RESULT_NO_RELATIONSHIP);
        }
        return respDTO;
    }

    /**
     * 商品属性之属性值分页查询回调类。 Title: ECP <br>
     * Project Name:ecp-services-goods <br>
     * Description: <br>
     * Date:2015年8月17日下午3:36:33 <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     * 
     * @author liyong7
     * @version GdsTypeSVImpl
     * @since JDK 1.6
     */
    protected class GdsCatgRespDTOPaginationCallback extends PaginationCallback<GdsCategory, GdsCategoryRespDTO> {

        @Override
        public List<GdsCategory> queryDB(BaseCriteria criteria) {
            return gdsCategoryMapper.selectByExample((GdsCategoryCriteria) criteria);
        }

        @Override
        public long queryTotal(BaseCriteria criteria) {
            return gdsCategoryMapper.countByExample((GdsCategoryCriteria) criteria);
        }

        @Override
        public GdsCategoryRespDTO warpReturnObject(GdsCategory t) {
            GdsCategoryRespDTO dto = copyInfo2Resp(t);
            return dto;
        }

    }

    /**
     * 结合权限商品分类分页查询回调类。 Title: ECP <br>
     * Project Name:ecp-services-goods <br>
     * Description: <br>
     * Date:2015年8月17日下午3:36:33 <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     * 
     * @author liyong7
     * @version GdsTypeSVImpl
     * @since JDK 1.6
     */
    protected class GdsCatgRespDTOPaginationWithAuthCallback extends PaginationCallback<GdsCategoryView, GdsCategoryRespDTO> {

        @Override
        public List<GdsCategoryView> queryDB(BaseCriteria criteria) {
            return gdsCategoryViewMapper.selectByExample((GdsCategoryViewCriteria) criteria);
        }

        @Override
        public long queryTotal(BaseCriteria criteria) {
            return gdsCategoryViewMapper.countByExample((GdsCategoryViewCriteria) criteria);
        }

        @Override
        public GdsCategoryRespDTO warpReturnObject(GdsCategoryView t) {
            GdsCategoryRespDTO dto = new GdsCategoryRespDTO();
            setProperties(dto, t);
            return dto;
        }

    }

    /*
     * 
     * 递归查询所有子级分类。
     * 
     * @author liyong7
     * 
     * @param respDTO
     * 
     * @since JDK 1.6
     */
    private void recursiveQuery(GdsCategoryRespDTO respDTO) {
        /*
         * if(respDTO.hasChildren()){ List<GdsCategoryRespDTO> lst =
         * this.querySubGdsCategory(respDTO.getCatgCode(), GdsConstants.Commons.STATUS_VALID);
         * respDTO.setChildren(lst); if(CollectionUtils.isNotEmpty(lst)){ for(GdsCategoryRespDTO
         * temp : lst){ recursiveQuery(temp); } } }
         */
        if (null != respDTO && respDTO.hasChildren()) {
            GdsCategoryViewCriteria viewCriteria = new GdsCategoryViewCriteria();
            viewCriteria.setLimitClauseCount(Integer.MAX_VALUE);
            GdsCategoryViewCriteria.Criteria c = viewCriteria.createCriteria();
            c.andCatgPathLike("%<" + respDTO.getCatgCode() + ">%");
            c.andCatgLevelGreaterThan(respDTO.getCatgLevel());
            c.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
            viewCriteria.setOrderByClause(DEFAULT_ORDER_BY);
            List<GdsCategoryView> viewLst = gdsCategoryViewMapper.selectByExample(viewCriteria);

            if (CollectionUtils.isNotEmpty(viewLst)) {
                // 根据查询结果集拼装成嵌套对象分类。
                setPropertiesTORespDTO(respDTO, viewLst);
            }
        }

    }

    /**
     * 
     * dealViewLst:返回查询结果填充成对象嵌套对象. <br/>
     * 
     * @author liyong7
     * @param respDTO
     * @param viewLst
     * @since JDK 1.6
     */
    private void setPropertiesTORespDTO(GdsCategoryRespDTO respDTO, List<GdsCategoryView> viewLst) {
        for (Iterator<GdsCategoryView> iterator = viewLst.iterator(); iterator.hasNext();) {
            GdsCategoryView gdsCategoryView = iterator.next();
            if (null != gdsCategoryView.getCatgParent() && respDTO.getCatgCode().equals(gdsCategoryView.getCatgParent())) {
                GdsCategoryRespDTO child = copyView2Resp(gdsCategoryView);
                respDTO.addSubCategory(child);
                if (GdsConstants.GdsCategory.IF_LEAF_0.equals(respDTO.getIfLeaf())) {
                    setPropertiesTORespDTO(child, viewLst);
                }
            }
        }
    }

    private void setProperties(GdsCategoryRespDTO respDTO, GdsCategoryView catgView) {
        respDTO.setCatgCode(catgView.getCatgCode());
        respDTO.setCatgLevel(catgView.getCatgLevel().shortValue());
        respDTO.setCatgName(catgView.getCatgName());
        respDTO.setCatgParent(catgView.getCatgParent());
        respDTO.setCatgPath(catgView.getCatgPath());
        respDTO.setCatgType(catgView.getCatgType());
        respDTO.setCatgTypeUnit(catgView.getCatgTypeUnit());
        respDTO.setCatgUrl(catgView.getCatgUrl());
        respDTO.setCatlogId(catgView.getCatlogId());
        respDTO.setCreateStaff(catgView.getCreateStaff());
        respDTO.setCreateTime(catgView.getCreateTime());
        respDTO.setIfAbleEdit(catgView.getIfAbleEdit());
        respDTO.setIfEntityCode(catgView.getIfEntityCode());
        respDTO.setIfLeaf(catgView.getIfLeaf());
        respDTO.setIfShow(catgView.getIfShow());
        respDTO.setMaxPrice(catgView.getMaxPrice());
        respDTO.setMediaUuid(catgView.getMediaUuid());
        respDTO.setMinPrice(catgView.getMinPrice());
        respDTO.setShopId(catgView.getShopId());
        respDTO.setSortNo(catgView.getSortNo());
        respDTO.setStatus(catgView.getStatus());
        respDTO.setUpdateStaff(catgView.getUpdateStaff());
        respDTO.setUpdateTime(catgView.getUpdateTime());
        respDTO.setCommission(catgView.getCommission());
    }

    /**
     * 初始化分页查询条件. <br/>
     * 
     * @author liyong7
     * @param criteria
     * @param dto
     * @since JDK 1.6
     */
    private Criteria initCriteria(Criteria c, GdsCategoryReqDTO dto) {
        if (StringUtils.hasText(dto.getCatgCode())) {
            c.andCatgCodeEqualTo(dto.getCatgCode());
        }
        if (StringUtils.hasText(dto.getCatgName())) {
            c.andCatgNameLike("%" + dto.getCatgName() + "%");
        }
        if (StringUtils.hasText(dto.getCatgParent())) {
            c.andCatgParentEqualTo(dto.getCatgParent());
        }
        if (StringUtils.hasText(dto.getStatus())) {
            c.andStatusEqualTo(dto.getStatus());
        }
        if (StringUtils.hasText(dto.getCatgType())) {
            c.andCatgTypeEqualTo(dto.getCatgType());
        }
        if (StringUtils.hasText(dto.getIfShow())) {
            c.andIfShowEqualTo(dto.getIfShow());
        }
        if (dto.getShopId() != null) {
            c.andShopIdEqualTo(dto.getShopId());
        }
        if (null != dto.getCatgLevel()) {
            c.andCatgLevelEqualTo(dto.getCatgLevel());
        }
        if (StringUtils.hasText(dto.getIfEntityCode())) {
            c.andIfEntityCodeEqualTo(dto.getIfEntityCode());
        }
        if (null != dto.getCatlogId()) {
            c.andCatlogIdEqualTo(dto.getCatlogId());
        }
        
        if(CollectionUtils.isNotEmpty(dto.getCatgParents())){
            if( 1 == dto.getCatgParents().size()){
                c.andCatgParentEqualTo(dto.getCatgParents().get(0));
            }else{
                c.andCatgParentIn(dto.getCatgParents());
            }
            
        }
        
        if(CollectionUtils.isNotEmpty(dto.getExcludeCatgCode())){
            if(1 == dto.getExcludeCatgCode().size()){
                c.andCatgCodeNotEqualTo(dto.getExcludeCatgCode().get(0));
            }else{
                c.andCatgCodeNotIn(dto.getExcludeCatgCode());
            }
        }
        
        
        if(CollectionUtils.isNotEmpty(dto.getShopIdLst())){
        	if(1 == dto.getShopIdLst().size()){
        		c.andShopIdEqualTo(dto.getShopIdLst().get(0));
        	}else{
        		c.andShopIdIn(dto.getShopIdLst());
        	}
        }
        
        if (null != dto.getSiteId()) {
            GdsCatalog2SiteReqDTO c2sReqDTO = new GdsCatalog2SiteReqDTO();
            c2sReqDTO.setSiteId(dto.getSiteId());
            List<GdsCatalogRespDTO> catalogLst = gdsCatalog2SiteSV.queryRelationBySiteId(c2sReqDTO);
            if (CollectionUtils.isNotEmpty(catalogLst)) {
                List<Long> catlogIds = new ArrayList<Long>();
                for (GdsCatalogRespDTO gcrd : catalogLst) {
                    catlogIds.add(gcrd.getId());
                }
                if (CollectionUtils.isNotEmpty(catlogIds)) {
                    if (catlogIds.size() == 1) {
                        c.andCatlogIdEqualTo(catlogIds.get(0));
                    } else {
                        c.andCatlogIdIn(catlogIds);
                    }
                } else {
                    c.andCatlogIdEqualTo(-99L);
                }
            }
        }
        
        if(CollectionUtils.isNotEmpty(dto.getIncludeCatgCode())){
            c.andCatgCodeIn(dto.getIncludeCatgCode());
        }
        return c;
    }

    private GdsCategoryViewCriteria initCatgViewCriteria(GdsCategoryReqDTO reqDTO) {
        GdsCategoryViewCriteria criteria = reqDTO.getCriteria();
        List<GdsCategoryViewCriteria.Criteria> cLst = criteria.getOredCriteria();
        if (cLst.isEmpty()) {
            criteria = new GdsCategoryViewCriteria();
            GdsCategoryViewCriteria.Criteria c = criteria.createCriteria();
            initCatgViewCriteria(c, reqDTO);
        } else {
            for (Iterator<GdsCategoryViewCriteria.Criteria> iterator = cLst.iterator(); iterator.hasNext();) {
                GdsCategoryViewCriteria.Criteria c = (GdsCategoryViewCriteria.Criteria) iterator.next();
                initCatgViewCriteria(c, reqDTO);
            }
        }
        return criteria;
    }

    private void initCatgViewCriteria(GdsCategoryViewCriteria.Criteria c, GdsCategoryReqDTO reqDTO) {
        if (StringUtils.hasText(reqDTO.getCatgCode())) {
            c.andCatgCodeEqualTo(reqDTO.getCatgCode());
        }
        if (reqDTO.getCommission()!=null && !"".equals(reqDTO.getCommission())) {
            c.andCommissionEqualTo(reqDTO.getCommission());
        }
        if (StringUtils.hasText(reqDTO.getCatgName())) {
            c.andCatgNameLike("%" + reqDTO.getCatgName() + "%");
        }
        if (StringUtils.hasText(reqDTO.getCatgParent())) {
            c.andCatgParentEqualTo(reqDTO.getCatgParent());
        }
        if (StringUtils.hasText(reqDTO.getStatus())) {
            c.andStatusEqualTo(reqDTO.getStatus());
        }
        if (StringUtils.hasText(reqDTO.getCatgType())) {
            c.andCatgTypeEqualTo(reqDTO.getCatgType());
        }
        if (StringUtils.hasText(reqDTO.getIfShow())) {
            c.andIfShowEqualTo(reqDTO.getIfShow());
        }
        if (reqDTO.getShopId() != null) {
            c.andShopIdEqualTo(reqDTO.getShopId());
        }
        if (null != reqDTO.getCatgLevel()) {
            c.andCatgLevelEqualTo(reqDTO.getCatgLevel());
        }
        if (StringUtils.hasText(reqDTO.getIfEntityCode())) {
            c.andIfEntityCodeEqualTo(reqDTO.getIfEntityCode());
        }
        if (null != reqDTO.getCatlogId()) {
            c.andCatlogIdEqualTo(reqDTO.getCatlogId());
        }
        
        if(CollectionUtils.isNotEmpty(reqDTO.getCatgParents())){
            if( 1 == reqDTO.getCatgParents().size()){
                c.andCatgParentEqualTo(reqDTO.getCatgParents().get(0));
            }else{
                c.andCatgParentIn(reqDTO.getCatgParents());
            }
            
        }
        
        if(StringUtils.hasText(reqDTO.getCatgPath())){
            c.andCatgPathLike("%<" + reqDTO.getCatgPath() + ">%");
        }
        
        if(CollectionUtils.isNotEmpty(reqDTO.getExcludeCatgCode())){
            if(1 == reqDTO.getExcludeCatgCode().size()){
                c.andCatgCodeNotEqualTo(reqDTO.getExcludeCatgCode().get(0));
            }else{
                c.andCatgCodeNotIn(reqDTO.getExcludeCatgCode());
            }
        }
       
        
        // 添加多个目录范围查询过滤
        if(null != reqDTO.getCatlogIds()){
        	if(!reqDTO.getCatlogIds().isEmpty()){
        	     if(1 == reqDTO.getCatlogIds().size()){
        	    	 c.andCatlogIdEqualTo(reqDTO.getCatlogIds().get(0));
        	     }else{
        	    	 c.andCatlogIdIn(reqDTO.getCatlogIds());
        	     }
        	}else{
        		c.andCatlogIdEqualTo(-9999L);
        	}
        }
        
        // 添加多个店铺范围查询过滤
        if(null != reqDTO.getShopIdLst()){
        	if(!reqDTO.getShopIdLst().isEmpty()){
        	     if(1 == reqDTO.getShopIdLst().size()){
        	    	 c.andShopIdEqualTo(reqDTO.getShopIdLst().get(0));
        	     }else{
        	    	 c.andShopIdIn(reqDTO.getShopIdLst());
        	     }
        	}else{
        		c.andShopIdEqualTo(-9999L);
        	}
        }
        
        if (null != reqDTO.getSiteId()) {
            GdsCatalog2SiteReqDTO c2sReqDTO = new GdsCatalog2SiteReqDTO();
            c2sReqDTO.setSiteId(reqDTO.getSiteId());
            List<GdsCatalogRespDTO> catalogLst = gdsCatalog2SiteSV.queryRelationBySiteId(c2sReqDTO);
            if (CollectionUtils.isNotEmpty(catalogLst)) {
                List<Long> catlogIds = new ArrayList<Long>();
                for (GdsCatalogRespDTO gcrd : catalogLst) {
                    catlogIds.add(gcrd.getId());
                }
                if (catlogIds.size() == 1) {
                    c.andCatlogIdEqualTo(catlogIds.get(0));
                } else {
                    c.andCatlogIdIn(catlogIds);
                }
            } else {
                c.andCatlogIdEqualTo(-99L);
            }
        }
        // 此处乐观认为入参maxShowNode为数值型。
        if(StringUtils.hasText(reqDTO.getMaxShowNode())){
            c.andCatgLevelLessThanOrEqualTo(Short.valueOf(reqDTO.getMaxShowNode()));
        }
        
        
        if(CollectionUtils.isNotEmpty(reqDTO.getIncludeCatgCode())){
            c.andCatgCodeIn(reqDTO.getIncludeCatgCode());
        }
    }

    /*
     * 
     * catgPathToArray:分类族谱转换成分类编码List. <br/>
     * 
     * @author liyong7
     * 
     * @param respDTO
     * 
     * @return
     * 
     * @since JDK 1.6
     */
    private List<String> catgPathToArray(GdsCategoryRespDTO respDTO) {
        String catgPath = respDTO.getCatgPath();
        catgPath = catgPath.replace("<", "");
        String[] ary = catgPath.split(">");
        return Arrays.asList(ary);
    }

    /**
     * clearCatgMap:清除分类映射. <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author liyong7
     * @param catgCode
     * @param updateStaff
     * @since JDK 1.6
     */
    private void clearCatgMap(String catgCode, Long updateStaff) {
        // 清除已经映射关联.
        GdsCategorySync gdsCategorySync = new GdsCategorySync();
        gdsCategorySync.setMapCatgCode(GdsDataImportConstants.Commons.DEFAULT_MAP_CATG_CODE);
        gdsCategorySync.setUpdateStaff(updateStaff);
        gdsCategorySync.setUpdateTime(now());
        GdsCategorySyncCriteria example = new GdsCategorySyncCriteria();
        GdsCategorySyncCriteria.Criteria c = example.createCriteria();
        c.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        c.andMapCatgCodeEqualTo(catgCode);
        gdsCategorySyncMapper.updateByExampleSelective(gdsCategorySync, example);
    }

    private void maxLevelCheck(Short currentLevel) {
        // 查询最大层级配置.
        BaseSysCfgRespDTO sysCfgRespDTO = SysCfgUtil.fetchSysCfg(GdsConstants.SysCfgConstants.GDS_CATEGORY_MAX_LEVEL);
        if (null != sysCfgRespDTO && StringUtil.isNotBlank(sysCfgRespDTO.getParaValue())) {
            if (Short.valueOf(sysCfgRespDTO.getParaValue()) < currentLevel) {
                throw new BusinessException(GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200313, new String[] { sysCfgRespDTO.getParaValue() });
            }
        }
    }
    @Override
    public PageResponseDTO<GdsCategoryRespDTO> queryCustCommissionPaging(GdsCategoryReqDTO dto) throws BusinessException {
        GdsCategoryViewCriteria criteria = new GdsCategoryViewCriteria();
        criteria.setLimitClauseStart(dto.getStartRowIndex());
        criteria.setLimitClauseCount(dto.getPageSize());
        if(StringUtil.isNotBlank(dto.getOrderBy())){
            criteria.setOrderByClause(dto.getOrderBy());
        }else{
            criteria.setOrderByClause(DEFAULT_ORDER_BY);
        }
        GdsCategoryViewCriteria.Criteria c = criteria.createCriteria();
        initCatgViewCriteria(c, dto);
        c.andCommissionIsNotNull();
        BigDecimal zore = new BigDecimal("0");
        c.andCommissionGreaterThan(zore);
        c.andCatlogIdEqualTo((long) 1);//人卫商城的目录ID
        return super.queryByPagination(dto, criteria, false, new GdsCatgRespDTOPaginationWithAuthCallback());
    }

    /**
     * copyView2Resp:(将视图对象转为返回对象 ). <br/>
     * 
     * @author linwb3
     * @param respDTO
     * @param catgView
     * @since JDK 1.6
     */
    private GdsCategoryRespDTO copyView2Resp(GdsCategoryView catgView) {
        GdsCategoryRespDTO respDTO = new GdsCategoryRespDTO();
        respDTO.setCatgCode(catgView.getCatgCode());
        respDTO.setCatgName(catgView.getCatgName());
        respDTO.setCatgLevel(catgView.getCatgLevel());
        respDTO.setCatgType(catgView.getCatgType());
        respDTO.setSortNo(catgView.getSortNo());
        respDTO.setCatgParent(catgView.getCatgParent());
        respDTO.setCatlogId(catgView.getCatlogId());
        respDTO.setIfEntityCode(catgView.getIfEntityCode());
        respDTO.setIfLeaf(catgView.getIfLeaf());
        respDTO.setCatgUrl(catgView.getCatgUrl());
        respDTO.setCatgTypeUnit(catgView.getCatgTypeUnit());
        respDTO.setMinPrice(catgView.getMinPrice());
        respDTO.setMaxPrice(catgView.getMaxPrice());
        respDTO.setShopId(catgView.getShopId());
        respDTO.setMediaUuid(catgView.getMediaUuid());
        respDTO.setStatus(catgView.getStatus());
        respDTO.setIfShow(catgView.getIfShow());
        respDTO.setIfAbleEdit(catgView.getIfAbleEdit());
        respDTO.setCatgPath(catgView.getCatgPath());
        respDTO.setUpdateStaff(catgView.getUpdateStaff());
        respDTO.setUpdateTime(catgView.getUpdateTime());
        respDTO.setCreateStaff(catgView.getCreateStaff());
        respDTO.setCreateTime(catgView.getCreateTime());
        respDTO.setCommission(catgView.getCommission());
        return respDTO;
    }
    
    /**
     * copyView2Resp:(将Info对象转为返回对象 ). <br/>
     * 
     * @author linwb3
     * @param respDTO
     * @param info
     * @since JDK 1.6
     */
    private GdsCategoryRespDTO copyInfo2Resp(GdsCategory info) {
        GdsCategoryRespDTO respDTO = new GdsCategoryRespDTO();
        respDTO.setCatgCode(info.getCatgCode());
        respDTO.setCatgName(info.getCatgName());
        respDTO.setCatgLevel(info.getCatgLevel());
        respDTO.setCatgType(info.getCatgType());
        respDTO.setSortNo(info.getSortNo());
        respDTO.setCatgParent(info.getCatgParent());
        respDTO.setCatlogId(info.getCatlogId());
        respDTO.setIfEntityCode(info.getIfEntityCode());
        respDTO.setIfLeaf(info.getIfLeaf());
        respDTO.setCatgUrl(info.getCatgUrl());
        respDTO.setCatgTypeUnit(info.getCatgTypeUnit());
        respDTO.setMinPrice(info.getMinPrice());
        respDTO.setMaxPrice(info.getMaxPrice());
        respDTO.setShopId(info.getShopId());
        respDTO.setMediaUuid(info.getMediaUuid());
        respDTO.setStatus(info.getStatus());
        respDTO.setIfShow(info.getIfShow());
        respDTO.setIfAbleEdit(info.getIfAbleEdit());
        respDTO.setUpdateStaff(info.getUpdateStaff());
        respDTO.setUpdateTime(info.getUpdateTime());
        respDTO.setCreateStaff(info.getCreateStaff());
        respDTO.setCreateTime(info.getCreateTime());
        return respDTO;
    }

    /**
     * copyResp2Info:(将返回对象转换为Info对象). <br/>
     * 
     * @author linwb3
     * @param info
     * @param resp
     * @since JDK 1.6
     */
    private GdsCategory copyResp2Info(GdsCategoryRespDTO resp) {
        GdsCategory info = new GdsCategory();
        info.setCatgCode(resp.getCatgCode());
        info.setCatgName(resp.getCatgName());
        info.setCatgLevel(resp.getCatgLevel());
        info.setCatgType(resp.getCatgType());
        info.setSortNo(resp.getSortNo());
        info.setCatgParent(resp.getCatgParent());
        info.setCatlogId(resp.getCatlogId());
        info.setIfEntityCode(resp.getIfEntityCode());
        info.setIfLeaf(resp.getIfLeaf());
        info.setCatgUrl(resp.getCatgUrl());
        info.setCatgTypeUnit(resp.getCatgTypeUnit());
        info.setMinPrice(resp.getMinPrice());
        info.setMaxPrice(resp.getMaxPrice());
        info.setShopId(resp.getShopId());
        info.setMediaUuid(resp.getMediaUuid());
        info.setStatus(resp.getStatus());
        info.setIfShow(resp.getIfShow());
        info.setIfAbleEdit(resp.getIfAbleEdit());
        info.setUpdateStaff(resp.getUpdateStaff());
        info.setUpdateTime(resp.getUpdateTime());
        info.setCreateStaff(resp.getCreateStaff());
        info.setCreateTime(resp.getCreateTime());
        return info;
    }
    
    /**
     * copyResp2Info:(将请求对象转换为Info对象). <br/>
     * 
     * @author linwb3
     * @param info
     * @param req
     * @since JDK 1.6
     */
    private GdsCategory copyReq2Info(GdsCategoryReqDTO req) {
        GdsCategory info = new GdsCategory();
        info.setCatgCode(req.getCatgCode());
        info.setCatgName(req.getCatgName());
        info.setCatgLevel(req.getCatgLevel());
        info.setCatgType(req.getCatgType());
        info.setSortNo(req.getSortNo());
        info.setCatgParent(req.getCatgParent());
        info.setCatlogId(req.getCatlogId());
        info.setIfEntityCode(req.getIfEntityCode());
        info.setIfLeaf(req.getIfLeaf());
        info.setCatgUrl(req.getCatgUrl());
        info.setCatgTypeUnit(req.getCatgTypeUnit());
        info.setMinPrice(req.getMinPrice());
        info.setMaxPrice(req.getMaxPrice());
        info.setShopId(req.getShopId());
        info.setMediaUuid(req.getMediaUuid());
        info.setStatus(req.getStatus());
        info.setIfShow(req.getIfShow());
        info.setIfAbleEdit(req.getIfAbleEdit());
        info.setUpdateStaff(req.getUpdateStaff());
        info.setUpdateTime(req.getUpdateTime());
        info.setCreateStaff(req.getCreateStaff());
        info.setCreateTime(req.getCreateTime());
        return info;
    }
    
    

}
