package com.zengshi.ecp.goods.service.common.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.util.StringUtils;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.goods.dao.mapper.common.GdsPropMapper;
import com.zengshi.ecp.goods.dao.mapper.common.GdsType2PropMapper;
import com.zengshi.ecp.goods.dao.mapper.common.GdsTypeMapper;
import com.zengshi.ecp.goods.dao.mapper.common.manual.GdsType2PropManualMapper;
import com.zengshi.ecp.goods.dao.model.GdsProp;
import com.zengshi.ecp.goods.dao.model.GdsType;
import com.zengshi.ecp.goods.dao.model.GdsType2Prop;
import com.zengshi.ecp.goods.dao.model.GdsType2PropCriteria;
import com.zengshi.ecp.goods.dao.model.GdsType2PropCriteria.Criteria;
import com.zengshi.ecp.goods.dao.model.GdsTypeCriteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsType2PropRelationRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsType2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsType2PropRespDTO;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsPropSV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsPropValueSV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsType2PropSV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsTypeSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

public class GdsType2PropSVImpl extends AbstractSVImpl implements IGdsType2PropSV {
    
    @Resource
    private GdsType2PropMapper gdsType2PropMapper;

    @Resource
    private GdsType2PropManualMapper gdsType2PropManualMapper;

    @Resource
    private GdsPropMapper gdsPropMapper;

    @Resource
    private IGdsPropSV gdsPropSV;

    @Resource
    private IGdsPropValueSV gdsPropValueSV;
    @Resource
    private IGdsTypeSV gdsTypeSV;
    @Resource
    private GdsTypeMapper gdsTypeMapper;

    private static final String DEFAULT_ORDER_BY = "CREATE_TIME DESC";

    @Override
    public GdsType2Prop saveGdsType2Prop(GdsType2Prop gdsType2Prop) throws BusinessException {
        Timestamp now = now();
        gdsType2Prop.setCreateTime(now);
        gdsType2Prop.setUpdateTime(now);
        gdsType2Prop.setStatus(GdsConstants.Commons.STATUS_VALID);
        gdsType2PropMapper.insert(gdsType2Prop);
        return gdsType2Prop;
    }

    @Override
    public void batchAddGdsType2Props(Long typeId, List<Long> propIds, Long createStaff,
            boolean skipWhenExist) throws BusinessException {
        StringBuffer errorMsg = new StringBuffer();

        if (typeId==null||typeId==0) {
            errorMsg.append("typeId,");
        }
        if (propIds == null || propIds.isEmpty()) {
            errorMsg.append("propIds,");
        }
        if (errorMsg.length() > 0) {
            errorMsg.deleteCharAt(errorMsg.length() - 1);
            throw new BusinessException(
                    GdsErrorConstants.Commons.ERROR_GOODS_200100,
                    new String[] { errorMsg.toString() });
        }
        Timestamp now = now();
        for (Long propId : propIds) {
            if (queryExist(typeId, propId, GdsConstants.Commons.STATUS_VALID)) {
                if (skipWhenExist) {
                    continue;
                } else {
                    throw new BusinessException(
                            GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200308,
                            new String[] { propId.toString() });
                }
            }
            GdsType2Prop obj = new GdsType2Prop();
            obj.setTypeId(typeId);
            obj.setCreateStaff(createStaff);
            obj.setCreateTime(now);
            obj.setUpdateStaff(createStaff);
            obj.setUpdateTime(now);
            obj.setIfBasic(GdsConstants.GdsCatg2Prop.IS_BASIC_0);
            obj.setIfHaveto(GdsConstants.GdsCatg2Prop.IS_REQUIRE_0);
            obj.setIfSearch(GdsConstants.GdsCatg2Prop.IS_SEARCH_0);
            obj.setIfGdsInput(GdsConstants.GdsCatg2Prop.IS_GDS_INPUT_0);
            obj.setPropId(propId);
            obj.setIfAbleEidt(GdsConstants.Commons.STATUS_INVALID);
            obj.setStatus(GdsConstants.Commons.STATUS_VALID);
            gdsType2PropMapper.insert(obj);
        }
    }

    @Override
    public PageResponseDTO<GdsType2PropRespDTO> queryGdsType2PropRespDTOPaging(
            GdsType2PropReqDTO dto) throws BusinessException {
        GdsType2PropCriteria criteria = new GdsType2PropCriteria();
        if(dto!=null){
            criteria.setLimitClauseStart(dto.getStartRowIndex());
            criteria.setLimitClauseCount(dto.getPageSize());
        }
        criteria.setOrderByClause(DEFAULT_ORDER_BY);
        Criteria c = criteria.createCriteria();
        // 属性名查询条件.
        if (dto!=null && StringUtil.isNotBlank(dto.getPropName())) {
            GdsPropReqDTO propReqDTO = new GdsPropReqDTO();
            propReqDTO.setPropName(dto.getPropName());
            propReqDTO.setPageSize(Integer.MAX_VALUE);
            List<Long> propIds = gdsPropSV.queryPropIds(propReqDTO);

            if (!CollectionUtils.isEmpty(propIds)) {
                if (propIds.size() == 1) {
                    c.andPropIdEqualTo(propIds.get(0));
                } else {
                    c.andPropIdIn(propIds);
                }
            } else {
                c.andPropIdEqualTo(-99999L);
            }
        }
        if (dto != null) {
            initCriteria(c, dto);
        }
        return super.queryByPagination(dto, criteria, false,
                new GdsType2PropRespDTOPaginationCallback());
    }
    
    private Criteria initCriteria(Criteria c, GdsType2PropReqDTO dto) {
        if (dto.getTypeId()!=null&&dto.getTypeId()!=0) {
            c.andTypeIdEqualTo(dto.getTypeId());
        }
        if (StringUtils.hasText(dto.getStatus())) {
            c.andStatusEqualTo(dto.getStatus());
        }
        if (StringUtils.hasText(dto.getIfBasic())) {
            c.andIfBasicEqualTo(dto.getIfBasic());
        }
        if (StringUtils.hasText(dto.getIfHaveto())) {
            c.andIfHavetoEqualTo(dto.getIfHaveto());
        }
        if (StringUtils.hasText(dto.getIfSearch())) {
            c.andIfSearchEqualTo(dto.getIfSearch());
        }
        if (null != dto.getPropId()) {
            c.andPropIdEqualTo(dto.getPropId());
        }
        return c;
    }
    
    private void initCriteria(GdsType2PropReqDTO reqDTO,
            GdsType2PropCriteria.Criteria criteria) {
        criteria.andTypeIdEqualTo(reqDTO.getTypeId());
        if (StringUtil.isNotBlank(reqDTO.getIfBasic())) {
            criteria.andIfBasicEqualTo(reqDTO.getIfBasic());
        }
        if (reqDTO.getPropId() != null) {
            criteria.andPropIdEqualTo(reqDTO.getPropId());
        }
        if (reqDTO.getIfSearch() != null) {
            criteria.andIfSearchEqualTo(reqDTO.getIfSearch());
        }
        if (reqDTO.isIfGdsInputQuery()) {
            // 如果是录入，不需要过滤次状态
        } else if (reqDTO.getIfGdsInput() != null) {
            criteria.andIfGdsInputEqualTo(reqDTO.getIfGdsInput());
        }
        if (reqDTO.getIfHaveto() != null) {
            criteria.andIfHavetoEqualTo(reqDTO.getIfHaveto());
        }
        if (StringUtil.isBlank(reqDTO.getStatus())) {
            criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        } else {
            criteria.andStatusEqualTo(reqDTO.getStatus());
        }

    }
    
    protected class GdsType2PropRespDTOPaginationCallback extends
            PaginationCallback<GdsType2Prop, GdsType2PropRespDTO> {

        @Override
        public List<GdsType2Prop> queryDB(BaseCriteria criteria) {
            return gdsType2PropMapper
                    .selectByExample((GdsType2PropCriteria) criteria);
        }

        @Override
        public long queryTotal(BaseCriteria criteria) {
            return gdsType2PropMapper
                    .countByExample((GdsType2PropCriteria) criteria);
        }

        @Override
        public GdsType2PropRespDTO warpReturnObject(GdsType2Prop t) {
            GdsType2PropRespDTO dto = new GdsType2PropRespDTO();
            ObjectCopyUtil.copyObjValue(t, dto, null, true);
            GdsProp gdsProp = gdsPropMapper.selectByPrimaryKey(t.getPropId());
            ObjectCopyUtil.copyObjValue(gdsProp, dto, null, true);
            dto.setIfAbleEidt(t.getIfAbleEidt());
            dto.setPropName(gdsProp.getPropName());
            dto.setPropValueTypeName(BaseParamUtil.fetchParamValue(
                    "GDS_PROP_VALUE_TYPE", gdsProp.getPropValueType()));
            dto.setPropTypeName(BaseParamUtil.fetchParamValue("GDS_PROP_TYPE",
                    gdsProp.getPropType()));
            dto.setStatus(BaseParamUtil.fetchParamValue("PUBLIC_PARAM_STATUS",
                    gdsProp.getStatus()));
            return dto;
        }

    }

    @Override
    public int batchDeleteCatg2Prop(Long typeId, List<Long> propIds, Long updateStaff)
            throws BusinessException {
        if(CollectionUtils.isNotEmpty(propIds) && typeId!=null&&typeId!=0){
            GdsType2Prop record = new GdsType2Prop();
            record.setUpdateTime(now());
            record.setUpdateStaff(updateStaff);
            record.setStatus(GdsConstants.Commons.STATUS_INVALID);
            
            GdsType2PropCriteria example = new GdsType2PropCriteria();
            GdsType2PropCriteria.Criteria c = example.createCriteria();
            c.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
            c.andIfAbleEidtNotEqualTo(GdsConstants.Commons.STATUS_VALID);
            c.andTypeIdEqualTo(typeId);
            c.andPropIdIn(propIds);
            
            return gdsType2PropMapper.updateByExampleSelective(record, example);
        }
        return 0;
    }

    @Override
    public List<Long> queryConfigedPropIds(Long typeId, String... status) throws BusinessException {
        return gdsType2PropManualMapper.queryConfigedPropIds(typeId, status);
    }

    @Override
    public boolean queryExist(Long typeId, Long propId, String... status) throws BusinessException {
        GdsType2PropCriteria criteria = new GdsType2PropCriteria();
        Criteria c = criteria.createCriteria();
        c.andTypeIdEqualTo(typeId);
        c.andPropIdEqualTo(propId);
        initStatusCriteria(c, status);
        return gdsType2PropMapper.countByExample(criteria) > 0;
    }

    @Override
    public boolean executeIsBaseConfig(Long typeId, Long propId, String isBasic, Long updateStaff)
            throws BusinessException {
        StringBuffer errorMsg = new StringBuffer();
        if (typeId==null||typeId==0) {
            errorMsg.append("typeId,");
        }

        if (propId == null) {
            errorMsg.append("propId,");
        }

        if (!StringUtils.hasText(isBasic)) {
            errorMsg.append("isBasic,");
        }

        if (errorMsg.length() > 0) {
            errorMsg.deleteCharAt(errorMsg.length() - 1);
            throw new BusinessException(
                    GdsErrorConstants.Commons.ERROR_GOODS_200100,
                    new String[] { errorMsg.toString() });
        }
        int i = -1;
        switch (isBasic) {
        case GdsConstants.GdsCatg2Prop.IS_BASIC_0:
        case GdsConstants.GdsCatg2Prop.IS_BASIC_1:
            GdsType2PropReqDTO dto = createConfigCondition(typeId, propId,
                    isBasic, null, null, updateStaff);
            i = gdsType2PropManualMapper.executeIsBasicConfig(dto);
            break;
        default:
            throw new BusinessException(
                    GdsErrorConstants.Commons.ERROR_GOODS_200098,
                    new String[] { "isBasic" });
        }

        return i > 0;
    }
    
    /**
     * 创建是否基础属性配置条件.<br/>
     * 
     * @param typeId
     * 
     * @param propId
     * 
     * @param isBasic
     * 
     * @param isRequired
     * 
     * @param updateStaff
     * 
     * @return
     * 
     * @since JDK 1.6
     */
    private GdsType2PropReqDTO createConfigCondition(Long typeId,
            Long propId, String isBasic, String isRequired, String isSearch,
            Long updateStaff) {
        GdsType2PropReqDTO dto = new GdsType2PropReqDTO();
        dto.setIfBasic(isBasic);
        dto.setIfHaveto(isRequired);
        dto.setIfSearch(isSearch);
        dto.setUpdateStaff(updateStaff);
        dto.setUpdateTime(now());
        dto.setStatus(GdsConstants.Commons.STATUS_VALID);
        dto.setTypeId(typeId);
        dto.setPropId(propId);
        return dto;
    }

    @Override
    public boolean executeIsRequireConfig(Long typeId, Long propId, String isRequired,
            Long updateStaff) throws BusinessException {
        StringBuffer errorMsg = new StringBuffer();
        if (typeId==null||typeId==0) {
            errorMsg.append("catgCode,");
        }

        if (propId == null) {
            errorMsg.append("propId,");
        }

        if (!StringUtils.hasText(isRequired)) {
            errorMsg.append("isRequired,");
        }

        if (errorMsg.length() > 0) {
            errorMsg.deleteCharAt(errorMsg.length() - 1);
            throw new BusinessException(
                    GdsErrorConstants.Commons.ERROR_GOODS_200100,
                    new String[] { errorMsg.toString() });
        }

        int i = -1;
        switch (isRequired) {
        case GdsConstants.GdsCatg2Prop.IS_REQUIRE_0:
        case GdsConstants.GdsCatg2Prop.IS_REQUIRE_1:
            GdsType2PropReqDTO dto = createConfigCondition(typeId, propId,
                    null, isRequired, null, updateStaff);
            i = gdsType2PropManualMapper.executeIsRequireConfig(dto);
            break;
        default:
            throw new BusinessException(
                    GdsErrorConstants.Commons.ERROR_GOODS_200098,
                    new String[] { "isRequired" });
        }

        return i > 0;
    }

    @Override
    public boolean executeIsSearchConfig(Long typeId, Long propId, String isSearch, Long updateStaff)
            throws BusinessException {
        StringBuffer errorMsg = new StringBuffer();
        if (typeId==null||typeId==0) {
            errorMsg.append("catgCode,");
        }

        if (propId == null) {
            errorMsg.append("propId,");
        }

        if (!StringUtils.hasText(isSearch)) {
            errorMsg.append("isSearch,");
        }

        if (errorMsg.length() > 0) {
            errorMsg.deleteCharAt(errorMsg.length() - 1);
            throw new BusinessException(
                    GdsErrorConstants.Commons.ERROR_GOODS_200100,
                    new String[] { errorMsg.toString() });
        }

        int i = -1;
        switch (isSearch) {
        case GdsConstants.GdsCatg2Prop.IS_SEARCH_0:
        case GdsConstants.GdsCatg2Prop.IS_SEARCH_1:
            GdsType2PropReqDTO dto = createConfigCondition(typeId, propId,
                    null, null, isSearch, updateStaff);
            i = gdsType2PropManualMapper.executeIsSearchConfig(dto);
            break;
        default:
            throw new BusinessException(
                    GdsErrorConstants.Commons.ERROR_GOODS_200098,
                    new String[] { "isSearch" });
        }

        return i > 0;
    }

    @Override
    public GdsType2PropRelationRespDTO queryTypeProps(GdsType2PropReqDTO reqDTO)
            throws BusinessException {
        GdsType2PropRelationRespDTO relationRespDTO = new GdsType2PropRelationRespDTO();
        List<GdsPropRespDTO> basics = new ArrayList<GdsPropRespDTO>();
        List<GdsPropRespDTO> specs = new ArrayList<GdsPropRespDTO>();
        List<GdsPropRespDTO> params = new ArrayList<GdsPropRespDTO>();
        List<GdsPropRespDTO> others = new ArrayList<GdsPropRespDTO>();
        List<GdsPropRespDTO> editoParam = new ArrayList<GdsPropRespDTO>();
        List<GdsPropRespDTO> fileParam = new ArrayList<GdsPropRespDTO>();
        List<GdsType2Prop> gdsType2Props = null;
        
        gdsType2Props = queryConfigedProps(reqDTO);

        /**
         * 查询对应的分类信息
         */
        if (!CollectionUtils.isEmpty(gdsType2Props)) {
            for (GdsType2Prop gdsType2Prop : gdsType2Props) {
                GdsPropRespDTO gdsPropRespDTO = new GdsPropRespDTO();
                GdsProp prop = gdsPropSV.queryGdsPropByPK(gdsType2Prop
                        .getPropId());
                if(prop == null){
                    continue;
                }
                ObjectCopyUtil.copyObjValue(gdsType2Prop, gdsPropRespDTO, null,
                        false);
                ObjectCopyUtil.copyObjValue(prop, gdsPropRespDTO, null, false);
                gdsPropRespDTO
                        .setValues(gdsPropValueSV.queryPropValuesByPropId(
                                prop.getId(),
                                new String[] { GdsConstants.Commons.STATUS_VALID }));
                if (GdsConstants.GdsProp.GDS_PROP_VALUE_INPUT_TYPE_RICHTXT
                        .equals(gdsPropRespDTO.getPropInputType())) {
                    //富文本
                    editoParam.add(gdsPropRespDTO);
                } else if (GdsConstants.GdsProp.GDS_PROP_VALUE_INPUT_TYPE_FILE
                        .equals(gdsPropRespDTO.getPropInputType())){
                    //文件
                    fileParam.add(gdsPropRespDTO);
                } else if (GdsUtils.isEqualsValid(gdsType2Prop.getIfBasic())) {
                    basics.add(gdsPropRespDTO);
                } else if (GdsConstants.GdsProp.PROP_TYPE_1.equals(prop
                        .getPropType())) {
                    specs.add(gdsPropRespDTO);
                } else if (GdsConstants.GdsProp.PROP_TYPE_2.equals(prop
                        .getPropType())) {
                    params.add(gdsPropRespDTO);
                } else if (GdsConstants.GdsProp.PROP_TYPE_3.equals(prop
                        .getPropType())) {
                    others.add(gdsPropRespDTO);
                }
            }
        }
        relationRespDTO.setBasics(basics);
        relationRespDTO.setOthers(others);
        relationRespDTO.setParams(params);
        relationRespDTO.setSpecs(specs);
        relationRespDTO.setEditoParam(editoParam);
        relationRespDTO.setFileParam(fileParam);
        return relationRespDTO;
    }

    @Override
    public GdsType2PropRelationRespDTO queryTypePropsByCondition(GdsType2PropReqDTO reqDTO)
            throws BusinessException {
        GdsType gdsType = gdsTypeSV.queryGdsTypeByPK(reqDTO.getTypeId());
        if (null == gdsType
                || GdsUtils.isEqualsInvalid(gdsType.getStatus())) {
            return null;
        }
        GdsType2PropRelationRespDTO relationRespDTO = new GdsType2PropRelationRespDTO();
        List<GdsPropRespDTO> searchProps = new ArrayList<GdsPropRespDTO>();
        // 查询出分类已配置属性关联关系.
        PageResponseDTO<GdsType2PropRespDTO> page = queryGdsType2PropRespDTOPaging(reqDTO);
        List<GdsType2PropRespDTO> type2PropRespDTOs = page.getResult();
        if (!CollectionUtils.isEmpty(type2PropRespDTOs)) {
            for (GdsType2PropRespDTO gdsType2PropRespDTO : type2PropRespDTOs) {
                GdsProp prop = gdsPropSV.queryGdsPropByPK(
                        gdsType2PropRespDTO.getPropId(), true);
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
        relationRespDTO.setSearchParams(searchProps);
        return relationRespDTO;
    }

    @Override
    public boolean executePropConfig(GdsType2PropReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO);
        paramCheck(new Object[] { reqDTO.getTypeId(), reqDTO.getPropId() },
                new String[] { "typeId", "propId" });
        configValueChk(reqDTO);

        GdsType2Prop record = new GdsType2Prop();
        ObjectCopyUtil.copyObjValue(reqDTO, record, null, true);
        preUpdate(reqDTO, record);

        GdsType2PropCriteria criteria = new GdsType2PropCriteria();
        Criteria c = criteria.createCriteria();
        c.andTypeIdEqualTo(reqDTO.getTypeId());
        c.andPropIdEqualTo(reqDTO.getPropId());
        c.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);

        return gdsType2PropMapper.updateByExampleSelective(record, criteria) > 0;
    }
    
    private void configValueChk(GdsType2PropReqDTO reqDTO) {
        StringBuilder msg = new StringBuilder();

        if (StringUtil.isNotBlank(reqDTO.getIfBasic())) {
            if (!GdsConstants.Commons.STATUS_VALID.equals(reqDTO.getIfBasic())
                    && !GdsConstants.Commons.STATUS_INVALID.equals(reqDTO
                            .getIfBasic())) {
                msg.append("ifBasic,");
            }
        }

        if (StringUtil.isNotBlank(reqDTO.getIfGdsInput())) {
            if (!GdsConstants.Commons.STATUS_VALID.equals(reqDTO
                    .getIfGdsInput())
                    && !GdsConstants.Commons.STATUS_INVALID.equals(reqDTO
                            .getIfGdsInput())) {
                msg.append("ifGdsInput,");
            }
        }

        if (StringUtil.isNotBlank(reqDTO.getIfHaveto())) {
            if (!GdsConstants.Commons.STATUS_VALID.equals(reqDTO.getIfHaveto())
                    && !GdsConstants.Commons.STATUS_INVALID.equals(reqDTO
                            .getIfHaveto())) {
                msg.append("ifHaveto,");
            }
        }

        if (StringUtil.isNotBlank(reqDTO.getIfSearch())) {
            if (!GdsConstants.Commons.STATUS_VALID.equals(reqDTO.getIfSearch())
                    && !GdsConstants.Commons.STATUS_INVALID.equals(reqDTO
                            .getIfSearch())) {
                msg.append("ifSearch,");
            }
        }

        if (msg.length() > 0) {
            msg.deleteCharAt(msg.length() - 1);
            throw new BusinessException(
                    GdsErrorConstants.Commons.ERROR_GOODS_200098,
                    new String[] { msg.toString() });
        }

    }

    @Override
    public boolean queryIsInUse(GdsType2PropReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO);
        paramNullCheck(reqDTO.getPropId(), "reqDTO.propId");
        GdsType2PropCriteria criteria = new GdsType2PropCriteria();
        Criteria c = criteria.createCriteria();
        initCriteria(c, reqDTO);
        List<Long> typeIds = gdsType2PropManualMapper
                .queryRelationTypeIdsByExample(criteria);
        if (CollectionUtils.isEmpty(typeIds)) {
            return false;
        }

        GdsTypeCriteria gdsTypeCriteria = new GdsTypeCriteria();
        GdsTypeCriteria.Criteria c2 = gdsTypeCriteria.createCriteria();
        c2.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        if (1 == typeIds.size()) {
            c2.andIdEqualTo(typeIds.get(0));
        } else {
            c2.andIdIn(typeIds);
        }

        return gdsTypeMapper.countByExample(gdsTypeCriteria) > 0;
    }

    @Override
    public List<GdsType2Prop> queryConfigedProps(GdsType2PropReqDTO reqDTO)
            throws BusinessException {
        GdsType2PropCriteria type2PropCriteria = new GdsType2PropCriteria();

        if (!reqDTO.isIfGdsInputQuery()) {
            GdsType2PropCriteria.Criteria criteria = type2PropCriteria
                    .createCriteria();
            initCriteria(reqDTO, criteria);
        } else {
            GdsType2PropCriteria.Criteria criteria = type2PropCriteria
                    .createCriteria();
            criteria.andIfGdsInputIsNull();
            initCriteria(reqDTO, criteria);

            GdsType2PropCriteria.Criteria criteria2 = type2PropCriteria
                    .createCriteria();
            criteria.andIfGdsInputEqualTo(GdsConstants.Commons.STATUS_VALID);
            initCriteria(reqDTO, criteria2);
            type2PropCriteria.or(criteria2);
        }

        return gdsType2PropMapper.selectByExample(type2PropCriteria);
    }

    @Override
    public List<Long> queryRelationTypeIdsByExample(GdsType2PropReqDTO reqDTO)
            throws BusinessException {
        paramNullCheck(reqDTO);
        paramNullCheck(reqDTO.getPropId(), "reqDTO.propId");
        GdsType2PropCriteria criteria = new GdsType2PropCriteria();
        Criteria c = criteria.createCriteria();
        initCriteria(c, reqDTO);
        if (null != reqDTO.getPropId()) {
            c.andPropIdEqualTo(reqDTO.getPropId());
        }
        if (CollectionUtils.isNotEmpty(reqDTO.getPropIds())) {
            if (1 == reqDTO.getPropIds().size()) {
                c.andPropIdEqualTo(reqDTO.getPropId());
            } else {
                c.andPropIdIn(reqDTO.getPropIds());
            }
        }
        return gdsType2PropManualMapper
                .queryRelationTypeIdsByExample(criteria);
    }

    @Override
    public Long queryUneditable(Long typeId, List<Long> propIds) {
        if(CollectionUtils.isNotEmpty(propIds) && typeId!=null&&typeId!=0){
            GdsType2PropCriteria example = new GdsType2PropCriteria();
            GdsType2PropCriteria.Criteria c = example.createCriteria();
            c.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
            c.andIfAbleEidtEqualTo(GdsConstants.Commons.STATUS_VALID);
            c.andTypeIdEqualTo(typeId);
            c.andPropIdIn(propIds);
            
            return gdsType2PropMapper.countByExample(example);
        }
        return 0L;
    }

}

