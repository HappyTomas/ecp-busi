package com.zengshi.ecp.goods.dubbo.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.goods.dao.model.GdsProp;
import com.zengshi.ecp.goods.dao.model.GdsType2Prop;
import com.zengshi.ecp.goods.dubbo.comparator.GdsPropRespDTOComparator;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsType2PropRelationRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsType2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsType2PropRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsType2PropRSV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsPropSV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsPropValueSV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsType2PropSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

public class GdsType2PropRSVImpl extends AbstractRSVImpl implements IGdsType2PropRSV {
    
    @Resource(name="gdsPropSV")
    private IGdsPropSV gdsPropSV;
    
    @Resource(name="gdsType2PropSV")
    private IGdsType2PropSV gdsType2PropSV;
    
    @Resource(name="gdsPropValueSV")
    private IGdsPropValueSV gdsPropValueSV;

    @Override
    public Long queryUneditableType2Prop(GdsType2PropReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO);
        paramCheck(new Object[]{reqDTO.getTypeId(),reqDTO.getPropIds()}, new String[]{"reqDTO.typeId","reqDTO.propIds"});
        return gdsType2PropSV.queryUneditable(reqDTO.getTypeId(), reqDTO.getPropIds());
    }

    @Override
    public GdsType2PropRelationRespDTO queryTypeProps(GdsType2PropReqDTO reqDTO)
            throws BusinessException {
        if(null == reqDTO.getStatus()){
            reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
        }
        return gdsType2PropSV.queryTypeProps(reqDTO);
    }

    @Override
    public PageResponseDTO<GdsType2PropRespDTO> queryConfigedPropsPaging(GdsType2PropReqDTO reqDTO)
            throws BusinessException {
        paramNullCheck(reqDTO, false, "reqDTO");
        paramNullCheck(reqDTO.getTypeId(), "reqDTO.typeId");
        return gdsType2PropSV.queryGdsType2PropRespDTOPaging(reqDTO);
    }

    @Override
    public PageResponseDTO<GdsPropRespDTO> queryOptionalPropsPaging(GdsType2PropReqDTO reqDTO)
            throws BusinessException {
        paramNullCheck(reqDTO, false, "reqDTO");
        paramNullCheck(reqDTO.getTypeId(), "reqDTO.typeId");
        List<Long> excludePropIds = gdsType2PropSV.queryConfigedPropIds(reqDTO.getTypeId(), GdsConstants.Commons.STATUS_VALID);
        GdsPropReqDTO propReqDTO = new GdsPropReqDTO();
        ObjectCopyUtil.copyObjValue(reqDTO, propReqDTO, null, true);
        propReqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
        propReqDTO.setPropName(reqDTO.getPropName());
        
        return gdsPropSV.queryGdsPropRespDTOPaging(propReqDTO, excludePropIds, null);
    }

    @Override
    public void addProps(GdsType2PropReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO, "reqDTO");
        paramCheck(new Object[]{reqDTO.getTypeId(),reqDTO.getPropIds()}, new String[]{"reqDTO.typeId","reqDTO.propIds"});
        List<Long> propIds = reqDTO.getPropIds();
        
        if(CollectionUtils.isNotEmpty(propIds)){
            Long createStaff = (null == reqDTO.getCreateStaff() ? reqDTO.getStaff().getId() : reqDTO.getCreateStaff());
            gdsType2PropSV.batchAddGdsType2Props(reqDTO.getTypeId(), propIds, createStaff , true);
        }
    }

    @Override
    public void deleteProps(GdsType2PropReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO, "reqDTO");
        paramCheck(new Object[]{reqDTO.getTypeId(),reqDTO.getPropIds()}, new String[]{"reqDTO.typeId","reqDTO.propIds"});
        List<Long> propIds = reqDTO.getPropIds();
        
        if(CollectionUtils.isNotEmpty(propIds)){
            Long createStaff = (null == reqDTO.getCreateStaff() ? reqDTO.getStaff().getId() : reqDTO.getCreateStaff());
            gdsType2PropSV.batchDeleteCatg2Prop(reqDTO.getTypeId(), propIds, createStaff);
        }
    }

    @Override
    public void executeIsBaseConfig(GdsType2PropReqDTO reqDTO) throws BusinessException {
        paramCheck(new Object[]{reqDTO.getTypeId(),reqDTO.getPropId(),reqDTO.getIfBasic()}, new String[]{"reqDTO.typeId","reqDTO.propId","reqDTO.ifBasic"});
        gdsType2PropSV.executeIsBaseConfig(reqDTO.getTypeId(), reqDTO.getPropId(), reqDTO.getIfBasic(), reqDTO.getStaff().getId());
    }

    @Override
    public void executeIsRequireConfig(GdsType2PropReqDTO reqDTO) throws BusinessException {
        paramCheck(new Object[]{reqDTO.getTypeId(),reqDTO.getPropId(),reqDTO.getIfHaveto()}, new String[]{"reqDTO.typeId","reqDTO.propId","reqDTO.ifHaveto"});
        gdsType2PropSV.executeIsRequireConfig(reqDTO.getTypeId(), reqDTO.getPropId(), reqDTO.getIfHaveto(), reqDTO.getStaff().getId());
    }

    @Override
    public void executeIsSearchConfig(GdsType2PropReqDTO reqDTO) throws BusinessException {
        paramCheck(new Object[]{reqDTO.getTypeId(),reqDTO.getPropId(),reqDTO.getIfSearch()}, new String[]{"reqDTO.typeId","reqDTO.propId","reqDTO.ifSearch"});
        gdsType2PropSV.executeIsSearchConfig(reqDTO.getTypeId(), reqDTO.getPropId(), reqDTO.getIfSearch(), reqDTO.getStaff().getId());
    }

    @Override
    public GdsType2PropRelationRespDTO querySearchProps(GdsType2PropReqDTO reqDTO)
            throws BusinessException {
        paramNullCheck(reqDTO, "reqDTO");
        paramNullCheck(reqDTO.getTypeId(), "reqDTO.typeId");
        reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
        reqDTO.setIfSearch(GdsConstants.GdsCatg2Prop.IS_SEARCH_1);
        reqDTO.setPageSize(Integer.MAX_VALUE);
        List<GdsType2Prop> lst = gdsType2PropSV.queryConfigedProps(reqDTO);
        GdsType2PropRelationRespDTO result = new GdsType2PropRelationRespDTO();
        List<GdsPropRespDTO> searchProps = new ArrayList<GdsPropRespDTO>();
        if (!CollectionUtils.isEmpty(lst)) {
            for (GdsType2Prop gdsType2Prop : lst) {
                GdsProp prop = gdsPropSV.queryGdsPropByPK(
                        gdsType2Prop.getPropId(), true);
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
    public void executeIfGdsInputConfig(GdsType2PropReqDTO reqDTO) throws BusinessException {
    }

}

