package com.zengshi.ecp.goods.service.common.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.goods.dao.mapper.common.GdsCategorySyncMapper;
import com.zengshi.ecp.goods.dao.model.GdsCategory;
import com.zengshi.ecp.goods.dao.model.GdsCategorySync;
import com.zengshi.ecp.goods.dao.model.GdsCategorySyncCriteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsDataImportConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgSyncReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgSyncRespDTO;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsCategorySV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsCategorySyncSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

public class GdsCategorySyncSVImpl extends AbstractSVImpl implements IGdsCategorySyncSV {
    @Resource
    private GdsCategorySyncMapper categorySyncMapper;

    @Resource(name = "seq_gds_category_sync")
    private PaasSequence sequence;

    @Resource
    private IGdsCategorySV gdsCategorySV;

    @Override
    public void addGdsCatgSyncInfo(GdsCatgSyncReqDTO catgSyncReqDTO) throws Exception {
        GdsCategorySync categorySync = new GdsCategorySync();
        ObjectCopyUtil.copyObjValue(catgSyncReqDTO, categorySync, null, false);
        categorySync.setId(sequence.nextValue());
        //categorySync.setCreateTime(new Timestamp(System.currentTimeMillis()));
        //categorySync.setCreateStaff(catgSyncReqDTO.getStaff().getId());
        categorySync.setStatus(GdsConstants.Commons.STATUS_VALID);
        preInsert(catgSyncReqDTO, categorySync);
        categorySyncMapper.insertSelective(categorySync);
    }

    @Override
    public void updateGdsCatgSyncInfo(GdsCatgSyncReqDTO catgSyncReqDTO) throws Exception {
        GdsCategorySync categorySync = new GdsCategorySync();

        GdsCategorySyncCriteria categorySyncExample = new GdsCategorySyncCriteria();
        GdsCategorySyncCriteria.Criteria categorySyncCriteria = categorySyncExample
                .createCriteria();
        categorySyncCriteria.andCatgCodeEqualTo(catgSyncReqDTO.getCatgCode());
        categorySyncCriteria.andSrcSystemEqualTo(catgSyncReqDTO.getSrcSystem());
        categorySyncCriteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        ObjectCopyUtil.copyObjValue(catgSyncReqDTO, categorySync, null, false);
        preUpdate(catgSyncReqDTO, categorySync);
        //categorySync.setUpdateStaff(catgSyncReqDTO.getStaff().getId());
        //categorySync.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        categorySyncMapper.updateByExampleSelective(categorySync, categorySyncExample);

    }

    @Override
    public void deleteGdsCatgSyncInfo(GdsCatgSyncReqDTO catgSyncReqDTO) throws Exception {

        GdsCategorySync categorySync = new GdsCategorySync();
        GdsCategorySyncCriteria categorySyncExample = new GdsCategorySyncCriteria();
        GdsCategorySyncCriteria.Criteria categorySyncCriteria = categorySyncExample
                .createCriteria();
        categorySyncCriteria.andSrcSystemEqualTo(catgSyncReqDTO.getSrcSystem());
        categorySyncCriteria.andCatgCodeEqualTo(catgSyncReqDTO.getCatgCode());
        categorySync.setStatus(GdsConstants.Commons.STATUS_INVALID);
        preUpdate(catgSyncReqDTO, categorySync);
        //categorySync.setUpdateStaff(catgSyncReqDTO.getStaff().getId());
        //categorySync.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        categorySyncMapper.updateByExampleSelective(categorySync, categorySyncExample);
    }

    @Override
    public void cancelGdsCatgSyncInfo(GdsCatgSyncReqDTO catgSyncReqDTO) throws Exception {

        GdsCategorySync categorySync = new GdsCategorySync();
        GdsCategorySyncCriteria categorySyncExample = new GdsCategorySyncCriteria();
        GdsCategorySyncCriteria.Criteria categorySyncCriteria = categorySyncExample
                .createCriteria();
        if (catgSyncReqDTO.getId() != null) {
            categorySyncCriteria.andIdEqualTo(catgSyncReqDTO.getId());
        } else {
            categorySyncCriteria.andCatgCodeEqualTo(catgSyncReqDTO.getCatgCode());
            categorySyncCriteria.andSrcSystemEqualTo(catgSyncReqDTO.getSrcSystem());
            categorySyncCriteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        }
        categorySync.setMapCatgCode("-1");

        categorySync.setUpdateStaff(catgSyncReqDTO.getStaff().getId());
        categorySync.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        categorySyncMapper.updateByExampleSelective(categorySync, categorySyncExample);
    }

    @Override
    public PageResponseDTO<GdsCatgSyncRespDTO> queryGdsCatgSyncPageInfo(
            GdsCatgSyncReqDTO catgSyncReqDTO) throws Exception {
        GdsCategorySyncCriteria categorySyncExample = new GdsCategorySyncCriteria();

        GdsCategorySyncCriteria.Criteria criteria = categorySyncExample.createCriteria();
        if (StringUtil.isNotBlank(catgSyncReqDTO.getMapCatgCode())) {
            criteria.andMapCatgCodeEqualTo(catgSyncReqDTO.getMapCatgCode());

        }
        if (StringUtil.isNotBlank(catgSyncReqDTO.getCatgCode())) {
            criteria.andCatgCodeEqualTo(catgSyncReqDTO.getCatgCode());

        }
        if (StringUtil.isNotBlank(catgSyncReqDTO.getCatgName())) {
            criteria.andCatgNameLike("%" + catgSyncReqDTO.getCatgName() + "%");
        }
        if (StringUtil.isNotEmpty(catgSyncReqDTO.getCatgParent())) {
            criteria.andCatgParentEqualTo(catgSyncReqDTO.getCatgParent());
        }
        if (StringUtil.isNotEmpty(catgSyncReqDTO.getSrcSystem())) {

            criteria.andSrcSystemEqualTo(catgSyncReqDTO.getSrcSystem());
        }
        if (StringUtil.isNotEmpty(catgSyncReqDTO.getShopId())) {
            criteria.andShopIdEqualTo(catgSyncReqDTO.getShopId());
        }
        if (StringUtil.isNotEmpty(catgSyncReqDTO.getShopAuthId())) {
            criteria.andShopAuthIdEqualTo(catgSyncReqDTO.getShopAuthId());
        }
        criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        if (catgSyncReqDTO.getIfMap() != null) {
            if ("1".equals(catgSyncReqDTO.getIfMap())) {
                criteria.andMapCatgCodeIsNotNull();
                criteria.andMapCatgCodeNotEqualTo("-1");
            } else if ("0".equals(catgSyncReqDTO.getIfMap())) {
                // 映射分类为-1或者为空
                categorySyncExample.or(categorySyncExample.createCriteria().andMapCatgCodeIsNull());
                criteria.andMapCatgCodeEqualTo("-1");
            }
        }
        categorySyncExample.setLimitClauseCount(catgSyncReqDTO.getPageSize());
        categorySyncExample.setLimitClauseStart(catgSyncReqDTO.getStartRowIndex());
        categorySyncExample.setOrderByClause("SORT_NO ASC,ID ASC");
        return this.queryByPagination(catgSyncReqDTO, categorySyncExample, false,
                new PaginationCallback<GdsCategorySync, GdsCatgSyncRespDTO>() {

                    @Override
                    public List<GdsCategorySync> queryDB(BaseCriteria criteria) {
                        return categorySyncMapper
                                .selectByExample((GdsCategorySyncCriteria) criteria);
                    }

                    @Override
                    public long queryTotal(BaseCriteria criteria) {
                        return categorySyncMapper
                                .countByExample((GdsCategorySyncCriteria) criteria);
                    }

                    @Override
                    public GdsCatgSyncRespDTO warpReturnObject(GdsCategorySync t) {
                        GdsCatgSyncRespDTO catgSyncRespDTO = new GdsCatgSyncRespDTO();
                        ObjectCopyUtil.copyObjValue(t, catgSyncRespDTO, null, false);
                        if (StringUtil.isNotBlank(t.getMapCatgCode())
                                && !"-1".equals(t.getMapCatgCode())) {
                            GdsCategory gdsCategory = gdsCategorySV.queryGdsCategoryById(t
                                    .getMapCatgCode());
                            catgSyncRespDTO.setMapCatgName(gdsCategory.getCatgName());
                        }
                        try {
                            catgSyncRespDTO.setIfLeafNode(ifLeaf(t));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return catgSyncRespDTO;
                    }
                });

    }

    @Override
    public void batchCancelGdsCatgSyncInfo(GdsCatgSyncReqDTO catgSyncReqDTO) throws Exception {

        GdsCategorySync categorySync = new GdsCategorySync();
        GdsCategorySyncCriteria categorySyncExample = new GdsCategorySyncCriteria();
        GdsCategorySyncCriteria.Criteria categorySyncCriteria = categorySyncExample
                .createCriteria();
        categorySyncCriteria.andIdIn(catgSyncReqDTO.getIds());
        categorySync.setMapCatgCode("-1");
        categorySync.setUpdateStaff(catgSyncReqDTO.getStaff().getId());
        categorySync.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        categorySyncMapper.updateByExampleSelective(categorySync, categorySyncExample);

    }

    @Override
    public GdsCatgSyncRespDTO queryGdsCategoryInfoByOriginCatgCode(GdsCatgSyncReqDTO catgSyncReqDTO)
            throws Exception {
        GdsCategorySyncCriteria categorySyncExample = new GdsCategorySyncCriteria();
        GdsCategorySyncCriteria.Criteria categorySyncCriteria = categorySyncExample
                .createCriteria();
        categorySyncCriteria.andCatgCodeEqualTo(catgSyncReqDTO.getCatgCode());
        categorySyncCriteria.andSrcSystemEqualTo(catgSyncReqDTO.getSrcSystem());
        categorySyncCriteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        List<GdsCategorySync> categorySyncs = categorySyncMapper
                .selectByExample(categorySyncExample);
        if (categorySyncs != null && categorySyncs.size() > 0) {
            GdsCatgSyncRespDTO catgSyncRespDTO = new GdsCatgSyncRespDTO();

            GdsCategorySync categorySync = categorySyncs.get(0);
            ObjectCopyUtil.copyObjValue(categorySync, catgSyncRespDTO, null, false);
            return catgSyncRespDTO;
        } else {
            return null;
        }

    }

    @Override
    public List<GdsCatgSyncRespDTO> queryRootCatgSyncBySrcSys(GdsCatgSyncReqDTO catgSyncReqDTO)
            throws Exception {
        GdsCategorySyncCriteria categorySyncExample = new GdsCategorySyncCriteria();
        GdsCategorySyncCriteria.Criteria categorySyncCriteria = categorySyncExample
                .createCriteria();
        categorySyncCriteria.andSrcSystemEqualTo(catgSyncReqDTO.getSrcSystem());
        //categorySyncCriteria.andCatgParentIsNull();
        categorySyncCriteria.andCatgParentEqualTo(GdsDataImportConstants.Commons.ROOT_CATG_PARENT_CODE);
        categorySyncCriteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        List<GdsCategorySync> gdsCategorySyncs = categorySyncMapper
                .selectByExample(categorySyncExample);
        List<GdsCatgSyncRespDTO> catgSyncRespDTOs = new ArrayList<GdsCatgSyncRespDTO>();
        if (gdsCategorySyncs != null && gdsCategorySyncs.size() > 0) {
            for (GdsCategorySync gdsCategorySync : gdsCategorySyncs) {
                GdsCatgSyncRespDTO catgSyncRespDTO = new GdsCatgSyncRespDTO();
                ObjectCopyUtil.copyObjValue(gdsCategorySync, catgSyncRespDTO, null, false);
                // 判断节点是否是父节点
                Boolean ifleaf = ifLeaf(gdsCategorySync);
                catgSyncRespDTO.setIfLeafNode(ifleaf);
                catgSyncRespDTOs.add(catgSyncRespDTO);
            }

        }
        return catgSyncRespDTOs;
    }

    private Boolean ifLeaf(GdsCategorySync gdsCategorySync) throws Exception {
        GdsCategorySyncCriteria categorySyncExample = new GdsCategorySyncCriteria();
        GdsCategorySyncCriteria.Criteria categorySyncCriteria = categorySyncExample
                .createCriteria();
        categorySyncCriteria.andCatgParentEqualTo(gdsCategorySync.getCatgCode());
        categorySyncCriteria.andSrcSystemEqualTo(gdsCategorySync.getSrcSystem());
        categorySyncCriteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        Long count = categorySyncMapper.countByExample(categorySyncExample);
        if (count > 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public List<GdsCatgSyncRespDTO> querySubCatgSyncByParent(GdsCatgSyncReqDTO catgSyncReqDTO)
            throws Exception {
        GdsCategorySyncCriteria categorySyncExample = new GdsCategorySyncCriteria();
        GdsCategorySyncCriteria.Criteria categorySyncCriteria = categorySyncExample
                .createCriteria();
        categorySyncCriteria.andCatgParentEqualTo(catgSyncReqDTO.getCatgCode());
        categorySyncCriteria.andSrcSystemEqualTo(catgSyncReqDTO.getSrcSystem());
        categorySyncCriteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        List<GdsCategorySync> gdsCategorySyncs = categorySyncMapper
                .selectByExample(categorySyncExample);
        List<GdsCatgSyncRespDTO> catgSyncRespDTOs = new ArrayList<GdsCatgSyncRespDTO>();
        if (gdsCategorySyncs != null && gdsCategorySyncs.size() > 0) {
            for (GdsCategorySync gdsCategorySync : gdsCategorySyncs) {
                GdsCatgSyncRespDTO catgSyncRespDTO = new GdsCatgSyncRespDTO();
                ObjectCopyUtil.copyObjValue(gdsCategorySync, catgSyncRespDTO, null, false);
                // 判断节点是否是父节点
                Boolean ifleaf = ifLeaf(gdsCategorySync);
                catgSyncRespDTO.setIfLeafNode(ifleaf);
                catgSyncRespDTOs.add(catgSyncRespDTO);

            }

        }
        return catgSyncRespDTOs;
    }

    @Override
    public List<GdsCatgSyncRespDTO> queryCatgSyncTraceUpon(GdsCatgSyncReqDTO catgSyncReqDTO)
            throws Exception {
        String catgCode = catgSyncReqDTO.getCatgCode();
        paramCheck(new Object[] { catgCode, catgSyncReqDTO.getSrcSystem() }, new String[] {
                "catgCode", "srcSystem" });

        GdsCatgSyncReqDTO reqDTO = new GdsCatgSyncReqDTO();
        reqDTO.setCatgCode(catgCode);
        reqDTO.setSrcSystem(catgSyncReqDTO.getSrcSystem());
        if (StringUtil.isNotEmpty(catgSyncReqDTO.getShopId())) {
            reqDTO.setShopId(catgSyncReqDTO.getShopId());
        }
        if (StringUtil.isNotEmpty(catgSyncReqDTO.getShopAuthId())) {
            reqDTO.setShopAuthId(catgSyncReqDTO.getShopAuthId());
        }
        GdsCatgSyncRespDTO respDTO = queryGdsCategorySyncByPK(reqDTO);
        /*
         * if(null == respDTO || GdsUtils.isEqualsInvalid(respDTO.getStatus())){ return null; }
         */
        if (null == respDTO) {
            return null;
        }
        List<GdsCatgSyncRespDTO> lst = new ArrayList<GdsCatgSyncRespDTO>();
        lst.add(respDTO);
        while (null != respDTO && StringUtil.isNotBlank(respDTO.getCatgParent())
        		&& !GdsDataImportConstants.Commons.ROOT_CATG_PARENT_CODE.equals(respDTO.getCatgParent())) {
            GdsCatgSyncReqDTO condition = new GdsCatgSyncReqDTO();
            condition.setCatgCode(respDTO.getCatgParent());
            condition.setSrcSystem(catgSyncReqDTO.getSrcSystem());
            respDTO = this.queryGdsCategorySyncByPK(condition);
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
    public GdsCatgSyncRespDTO queryGdsCategorySyncByPK(GdsCatgSyncReqDTO catgSyncReqDTO)
            throws Exception {
        GdsCategorySyncCriteria categorySyncExample = new GdsCategorySyncCriteria();
        GdsCategorySyncCriteria.Criteria categorySyncCriteria = categorySyncExample
                .createCriteria();
        categorySyncCriteria.andCatgCodeEqualTo(catgSyncReqDTO.getCatgCode());
        categorySyncCriteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        categorySyncCriteria.andSrcSystemEqualTo(catgSyncReqDTO.getSrcSystem());
        if (StringUtil.isNotEmpty(catgSyncReqDTO.getShopId())) {
            categorySyncCriteria.andShopIdEqualTo(catgSyncReqDTO.getShopId());
        }
        if (StringUtil.isNotEmpty(catgSyncReqDTO.getShopAuthId())) {
            categorySyncCriteria.andShopAuthIdEqualTo(catgSyncReqDTO.getShopAuthId());
        }
        
        List<GdsCategorySync> categorySyncs = categorySyncMapper
                .selectByExample(categorySyncExample);
        GdsCatgSyncRespDTO catgSyncRespDTO = new GdsCatgSyncRespDTO();
        if (categorySyncs != null && categorySyncs.size() > 0) {

            GdsCategorySync categorySync = categorySyncs.get(0);
            ObjectCopyUtil.copyObjValue(categorySync, catgSyncRespDTO, null, false);
            // 判断节点是否是父节点
            Boolean ifleaf = ifLeaf(categorySync);
            catgSyncRespDTO.setIfLeafNode(ifleaf);
        } else {
            return null;
        }
        return catgSyncRespDTO;
    }

    @Override
    public void deleteGdsCategorySyncTable(GdsCatgSyncReqDTO catgSyncReqDTO)
            throws BusinessException {
        
        GdsCategorySyncCriteria criteria = new GdsCategorySyncCriteria();
        
        com.zengshi.ecp.goods.dao.model.GdsCategorySyncCriteria.Criteria condition = criteria.createCriteria();
        condition.andStatusEqualTo("1");
        condition.andShopAuthIdEqualTo(catgSyncReqDTO.getShopAuthId());
        condition.andShopIdEqualTo(catgSyncReqDTO.getShopId());
        condition.andSrcSystemEqualTo(catgSyncReqDTO.getSrcSystem());
        
        try {
            categorySyncMapper.deleteByExample(criteria);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.error(MODULE, e.getMessage());
            throw e;
        }
    }

}
