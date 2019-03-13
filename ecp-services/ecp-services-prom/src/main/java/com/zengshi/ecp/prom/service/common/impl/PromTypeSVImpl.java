package com.zengshi.ecp.prom.service.common.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.prom.dao.mapper.common.PromTypeMapper;
import com.zengshi.ecp.prom.dao.model.PromType;
import com.zengshi.ecp.prom.dao.model.PromTypeCriteria;
import com.zengshi.ecp.prom.dao.model.PromTypeCriteria.Criteria;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeResponseDTO;
import com.zengshi.ecp.prom.dubbo.impl.converter.Converter;
import com.zengshi.ecp.prom.dubbo.util.PromCacheUtil;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.common.interfaces.IPromTypeSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromTypeSVImpl implements IPromTypeSV {

    private static final String MODULE = PromTypeSVImpl.class.getName();

    @Resource
    private PromTypeMapper promTypeMapper;

    @Resource(name = "promTypeResponseDTOConverter")
    private Converter<PromType, PromTypeResponseDTO> converter;
    /**
     * 促销类型
     * 
     * @param promDTO
     * @return
     * @throws Exception
     * @author huangjx
     */
    public PromTypeResponseDTO queryPromTypeByDB(String promTypeCode) throws BusinessException{
        
        PromTypeResponseDTO promTypeResponseDTO = new PromTypeResponseDTO();
        
        PromType promType = promTypeMapper.selectByPrimaryKey(promTypeCode);
        if (promType != null) {
            promTypeResponseDTO = converter.convert(promType);
        }
        return promTypeResponseDTO;
    }
    /**
     * TODO促销类型对象
     * 
     * @see com.zengshi.ecp.prom.service.common.interfaces.IPromTypeSV#queryPromTypeByCode(java.lang.String)
     * @param PromTypeResponseDTO
     * @return
     * @throws Exception
     * @author huangjx
     */

    @Override
    public PromTypeResponseDTO queryPromTypeByCode(String promTypeCode) throws BusinessException {
        
        PromTypeResponseDTO promTypeResponseDTO = new PromTypeResponseDTO();
        
        //先取缓存 如果没有在取表数据
        promTypeResponseDTO=PromCacheUtil.getPromTypeCache(promTypeCode);
        if(promTypeResponseDTO!=null){
            return promTypeResponseDTO;
        }
        PromType promType = promTypeMapper.selectByPrimaryKey(promTypeCode);
        if (promType != null) {
            promTypeResponseDTO = converter.convert(promType);
            //加入缓存
            CacheUtil.addItem((PromConstants.PromKey.TYPE_PROM+promTypeCode), promTypeResponseDTO);
        }
        return promTypeResponseDTO;
    }

    /**
     * TODO 促销类型列表
     * 
     * @see com.zengshi.ecp.prom.service.common.interfaces.IPromTypeSV#queryPromTypeList(com.zengshi.ecp.prom.dubbo.dto.PromTypeDTO)
     * @param PromTypeResponseDTO
     * @return
     * @throws Exception
     * @author huangjx
     */
    @Override
    public List<PromTypeResponseDTO> queryPromTypeList(PromTypeDTO promTypeDTO)
            throws BusinessException {
      //先从缓存 取数据 如果没有在从表取数据 待实现
        PromTypeCriteria example = new PromTypeCriteria();
        
        //默认有效选择
        if(StringUtil.isEmpty(promTypeDTO.getStatus())){
            promTypeDTO.setStatus(PromConstants.PromType.STATUS_1);
        }
        this.initPromTypeParm(promTypeDTO, example);
        //排序
        example.setOrderByClause("sort_no asc");
        List<PromType> list = promTypeMapper.selectByExample(example);
        List<PromTypeResponseDTO> reList = new ArrayList<PromTypeResponseDTO>();
        if (list != null)
            for (PromType promType : list) {
                reList.add(converter.convert(promType));
            }
        return reList;
    }

    /**
     * 初始化参数条件
     * 
     * @param promTypeDTO
     * @author huangjx
     */
    private void initPromTypeParm(PromTypeDTO promTypeDTO, PromTypeCriteria example) {
        if (promTypeDTO != null) {
            Criteria cr = example.createCriteria();
            if (!StringUtil.isEmpty(promTypeDTO.getPromTypeCode())) {
                cr.andPromTypeCodeEqualTo(promTypeDTO.getPromTypeCode());// 促销类型代码
            }
            if (!StringUtil.isEmpty(promTypeDTO.getPromTypeName())) {
                cr.andPromTypeNameLike(PromConstants.PromSys.LIKE_PERCENT+promTypeDTO.getPromTypeName()+PromConstants.PromSys.LIKE_PERCENT);// 促销类型名称
            }
            if (!StringUtil.isEmpty(promTypeDTO.getPromClass())) {
                cr.andPromClassEqualTo(promTypeDTO.getPromClass());// 促销分类10订单 20单品
                                                                   // 30外围活动--无优惠规则，只有限制条件
            }
            if (!StringUtil.isEmpty(promTypeDTO.getIfShow())) {
                cr.andIfShowEqualTo(promTypeDTO.getIfShow());// 是否展示 是否页面展示 1展示 0不展示
            }
            if (!StringUtil.isEmpty(promTypeDTO.getStatus())) {
                cr.andStatusEqualTo(promTypeDTO.getStatus());// 状态 状态，0无效，1有效
            }
            if (!StringUtil.isEmpty(promTypeDTO.getJsonBeanId())) {
                cr.andJsonBeanIdEqualTo(promTypeDTO.getJsonBeanId());
            }
            if (!StringUtil.isEmpty(promTypeDTO.getServiceId())) {
                cr.andServiceIdEqualTo(promTypeDTO.getServiceId());
            }
            // 是否叠加
            if (!StringUtil.isEmpty(promTypeDTO.getIfComposit())) {
                cr.andIfCompositEqualTo(promTypeDTO.getIfComposit());
            }
        }
    }
}
