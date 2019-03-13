package com.zengshi.ecp.goods.service.busi.impl.gdsinfores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.zengshi.ecp.goods.dao.mapper.busi.GdsSku2PropMapper;
import com.zengshi.ecp.goods.dao.model.GdsSku2Prop;
import com.zengshi.ecp.goods.dao.model.GdsSku2PropCriteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropValueRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfores.IGdsSkuInfo2PropSV;
import com.zengshi.ecp.goods.service.common.impl.AbstractSVImpl;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsPropSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: 单品属性关系操作 <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: <br>
 * Date:2016年3月23日上午11:53:16 <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public class GdsSkuInfo2PropSVImpl extends AbstractSVImpl implements IGdsSkuInfo2PropSV {

    @Resource
    private GdsSku2PropMapper gdsSku2PropMapper;

    @Resource
    private IGdsPropSV gdsPropSV;

    @Override
    public List<GdsPropRespDTO> getSkuPropsBySkuId(Long gdsId, Long skuId, List<Long> propIds) throws BusinessException {
        return GdsUtils.doConvert(getSkuPropsModelBySkuId(gdsId, skuId, propIds), GdsPropRespDTO.class);
    }

    @Override
    public List<GdsSku2Prop> getSkuPropsModelBySkuId(Long gdsId, Long skuId, List<Long> propIds) throws BusinessException {
        GdsSku2PropReqDTO reqDTO = new GdsSku2PropReqDTO();
        reqDTO.setGdsId(gdsId);
        reqDTO.setSkuId(skuId);
        reqDTO.setPropIds(propIds);
        reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
        return queryGdsSkuInfo2Prop(reqDTO);
    }

    @Override
    public List<GdsPropRespDTO> convertSku2propTOProp(List<GdsSku2Prop> sku2Props) throws BusinessException {
        // 创建映射Map，以属性ID为key，属性Resp对象为值
        Map<String, GdsPropRespDTO> propMaps = new HashMap<String, GdsPropRespDTO>();
        for (GdsSku2Prop gdsGds2Prop : sku2Props) {
            String propId = gdsGds2Prop.getPropId().toString();
            if (propMaps.containsKey(propId)) {
                // 构建新的value，添加到values列表中
                GdsPropRespDTO prop = propMaps.get(propId);
                List<GdsPropValueRespDTO> values = prop.getValues();
                if (CollectionUtils.isEmpty(values)) {
                    values = new ArrayList<GdsPropValueRespDTO>();
                }
                boolean in = false;
                for (GdsPropValueRespDTO gdsPropValueRespDTO : values) {
                    if (gdsPropValueRespDTO.getPropValue().equals(gdsGds2Prop.getPropValue())) {
                        in = true;
                    }
                }
                if (!in) {
                    GdsPropValueRespDTO propValue = new GdsPropValueRespDTO();
                    propValue.setId(gdsGds2Prop.getPropValueId());
                    if (gdsGds2Prop.getPropValue() == null) {
                        propValue.setPropValue("");
                    } else {
                        propValue.setPropValue(gdsGds2Prop.getPropValue());
                    }
                    propValue.setMediaId(gdsGds2Prop.getPropMediaUuid());
                    values.add(propValue);
                }
                prop.setValues(values);
            } else {
                // 构建新的属性，并且构建value
                GdsPropRespDTO prop = new GdsPropRespDTO();
                prop.setId(gdsGds2Prop.getPropId());
                prop.setPropName(gdsGds2Prop.getPropName());
                prop.setIfHaveto(gdsGds2Prop.getIfHaveto());
                prop.setIfBasic(gdsGds2Prop.getIfBasic());
                prop.setPropType(gdsGds2Prop.getPropType());
                prop.setPropValueType(gdsGds2Prop.getPropValueType());
                prop.setPropInputType(gdsGds2Prop.getPropInputType());
                List<GdsPropValueRespDTO> values = new ArrayList<GdsPropValueRespDTO>();
                GdsPropValueRespDTO propValue = new GdsPropValueRespDTO();
                propValue.setId(gdsGds2Prop.getPropValueId());
                if (gdsGds2Prop.getPropValue() == null) {
                    propValue.setPropValue("");
                } else {
                    propValue.setPropValue(gdsGds2Prop.getPropValue());
                }
                propValue.setMediaId(gdsGds2Prop.getPropMediaUuid());
                values.add(propValue);

                prop.setValues(values);
                propMaps.put(propId, prop);
            }
        }
        return GdsUtils.convertMapToList(propMaps);
    }

    @Override
    public GdsSku2Prop queryGdsSku2Prop(GdsSku2PropReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO);
        paramCheck(new Object[] { reqDTO.getSkuId(), reqDTO.getPropId() }, new String[] { "skuId", "propId" });
        reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
        List<GdsSku2Prop> gdsSku2PropLst = queryGdsSkuInfo2Prop(reqDTO);
        if (CollectionUtils.isNotEmpty(gdsSku2PropLst)) {
            return gdsSku2PropLst.get(0);
        }
        return null;
    }

    @Override
    public List<GdsSku2Prop> queryGdsSkuInfo2Prop(GdsSku2PropReqDTO reqDTO) throws BusinessException {
        GdsSku2PropCriteria gdsSku2PropCriteria = new GdsSku2PropCriteria();
        if (reqDTO != null) {
            GdsSku2PropCriteria.Criteria criteria = gdsSku2PropCriteria.createCriteria();
            if (StringUtil.isNotBlank(reqDTO.getStatus())) {
                criteria.andStatusEqualTo(reqDTO.getStatus());
            }
            initAutormCriteria(reqDTO, criteria);
        }
        return gdsSku2PropMapper.selectByExample(gdsSku2PropCriteria);
    }

    /**
     * 
     * updateSku2Prop:(更新单品，属性关系). <br/> 
     * 
     * @author linwb3
     * @param reqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    @Override
    public void updateSku2Prop(GdsSku2PropReqDTO reqDTO,GdsSku2PropReqDTO query) throws BusinessException {
        paramNullCheck(reqDTO);
        paramNullCheck(query);
        paramNullCheck(query.getGdsId());
        paramNullCheck(query.getSkuId());

        GdsSku2PropCriteria example = new GdsSku2PropCriteria();
        GdsSku2PropCriteria.Criteria criteria = example.createCriteria();
        initAutormCriteria(query, criteria);
        criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        Long staffId = reqDTO.getStaff().getId();

        // 填充信息
        GdsSku2Prop gdsGds2Prop = new GdsSku2Prop();
        ObjectCopyUtil.copyObjValue(reqDTO, gdsGds2Prop, null, false);

        // 查询对应的属性信息，补充属性信息
//        GdsProp propModel = gdsPropSV.queryGdsPropByPK(reqDTO.getPropId());
//        ObjectCopyUtil.copyObjValue(propModel, gdsGds2Prop, null, false);
        gdsGds2Prop.setUpdateStaff(staffId);
        gdsGds2Prop.setUpdateTime(DateUtil.getSysDate());
        gdsSku2PropMapper.updateByExampleSelective(gdsGds2Prop, example);
    }
    
    
    /**
     * 
     * updateGds2PropGdsStatus:(批量更新单品属性关系商品状态). <br/> 
     * 
     * @author linwb3
     * @param reqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    @Override
    public void updateSku2PropGdsStatus(GdsSku2PropReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO);
        paramNullCheck(reqDTO.getGdsId());
        paramNullCheck(reqDTO.getSkuId());
        paramNullCheck(reqDTO.getGdsStatus());
        GdsSku2PropReqDTO info=new GdsSku2PropReqDTO();
        info.setGdsStatus(reqDTO.getGdsStatus());
        info.setStaff(reqDTO.getStaff());
        
        GdsSku2PropReqDTO query=new GdsSku2PropReqDTO();
        query.setGdsId(reqDTO.getGdsId());
        query.setSkuId(reqDTO.getSkuId());
        updateSku2Prop(info,query);
    }

    /**
     * 
     * saveSku2Prop:(保存单品，属性关系). <br/> 
     * 
     * @author linwb3
     * @param reqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    @Override
    public void saveSku2Prop(GdsSku2PropReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO);
        paramCheck(new Object[] { reqDTO.getGdsId(), reqDTO.getSkuId(), reqDTO.getPropId() }, new String[] { "reqDTO.gdsId", "reqDTO.skuId", "reqDTO.propId" });
        Long staffId = reqDTO.getStaff().getId();

        GdsSku2Prop gdsGds2Prop = new GdsSku2Prop();
        ObjectCopyUtil.copyObjValue(reqDTO, gdsGds2Prop, null, false);

        // 查询对应的属性信息，补充属性信息
        gdsGds2Prop.setCreateStaff(staffId);
        gdsGds2Prop.setCreateTime(DateUtil.getSysDate());
        gdsGds2Prop.setUpdateStaff(staffId);
        gdsGds2Prop.setUpdateTime(DateUtil.getSysDate());
        gdsGds2Prop.setStatus(GdsConstants.Commons.STATUS_VALID);
        gdsSku2PropMapper.insertSelective(gdsGds2Prop);
    }

    /**
     * 
     * delSku2Prop:(逻辑删除 单品，属性关系). <br/> 
     * 
     * @author linwb3
     * @param reqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    @Override
    public void delSku2Prop(GdsSku2PropReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO);
        paramNullCheck(reqDTO.getGdsId());
        paramNullCheck(reqDTO.getSkuId());
        reqDTO.setStatus(GdsConstants.Commons.STATUS_INVALID);
        updateSku2Prop(reqDTO,reqDTO);
    }

    /**
     * 
     * realDelSku2Prop:(<font color='red'>物理删除单品与属性关联关系<font/>). <br/>
     * 
     * @author linwb3
     * @param reqDTO
     * @since JDK 1.6
     */
    @Override
    public void realDelSku2Prop(GdsSku2PropReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO);
        paramNullCheck(reqDTO.getGdsId());
        paramNullCheck(reqDTO.getSkuId());


        GdsSku2PropCriteria example = new GdsSku2PropCriteria();
        GdsSku2PropCriteria.Criteria criteria = example.createCriteria();
        initAutormCriteria(reqDTO, criteria);
        gdsSku2PropMapper.deleteByExample(example);
    }

    /**
     * 
     * initAutormCriteria:(查询条件拼接). <br/> 
     * 
     * @author linwb3
     * @param reqDTO
     * @param criteria 
     * @since JDK 1.6
     */
    private void initAutormCriteria(GdsSku2PropReqDTO reqDTO, GdsSku2PropCriteria.Criteria criteria) {
        if (reqDTO.getGdsId() != null && reqDTO.getGdsId().longValue() != 0) {
            criteria.andGdsIdEqualTo(reqDTO.getGdsId());
        }
        if (reqDTO.getSkuId() != null && reqDTO.getSkuId().longValue() != 0) {
            criteria.andSkuIdEqualTo(reqDTO.getSkuId());
        }
        if (CollectionUtils.isNotEmpty(reqDTO.getPropIds())) {
            criteria.andPropIdIn(reqDTO.getPropIds());
        }
        if (CollectionUtils.isNotEmpty(reqDTO.getPropInputTypes())) {
            criteria.andPropInputTypeIn(reqDTO.getPropInputTypes());
        }
        if (CollectionUtils.isNotEmpty(reqDTO.getPropTypes())) {
            criteria.andPropTypeIn(reqDTO.getPropTypes());
        }
        if (CollectionUtils.isNotEmpty(reqDTO.getPropValueTypes())) {
            criteria.andPropValueTypeIn(reqDTO.getPropValueTypes());
        }
        if (reqDTO.getPropId() != null) {
            criteria.andPropIdEqualTo(reqDTO.getPropId());
        }
        if (StringUtil.isNotBlank(reqDTO.getPropName())) {
            criteria.andPropNameEqualTo(reqDTO.getPropName());
        }
        if (reqDTO.getPropValueId() != null) {
            criteria.andPropValueIdEqualTo(reqDTO.getPropValueId());
        }
        if (StringUtil.isNotBlank(reqDTO.getPropValue())) {
            criteria.andPropValueEqualTo(reqDTO.getPropValue());
        }
        if (StringUtil.isNotBlank(reqDTO.getPropType())) {
            criteria.andPropTypeEqualTo(reqDTO.getPropType());
        }
        if (StringUtil.isNotBlank(reqDTO.getPropValueType())) {
            criteria.andPropValueTypeEqualTo(reqDTO.getPropValueType());
        }
        if (StringUtil.isNotBlank(reqDTO.getPropInputType())) {
            criteria.andPropInputTypeEqualTo(reqDTO.getPropInputType());
        }
        if (StringUtil.isNotBlank(reqDTO.getIfBasic())) {
            criteria.andIfBasicEqualTo(reqDTO.getIfBasic());
        }
        if (StringUtil.isNotBlank(reqDTO.getIfCheck())) {
            criteria.andIfCheckEqualTo(reqDTO.getIfCheck());
        }
        if (StringUtil.isNotBlank(reqDTO.getIfMust())) {
            criteria.andIfHavetoEqualTo(reqDTO.getIfMust());
        }
        if (StringUtil.isNotBlank(reqDTO.getPropInputRule())) {
            criteria.andPropInputRuleEqualTo(reqDTO.getPropInputRule());
        }
        if (StringUtil.isNotBlank(reqDTO.getGdsStatus())) {
            criteria.andGdsStatusEqualTo(reqDTO.getGdsStatus());
        }
    }

}
