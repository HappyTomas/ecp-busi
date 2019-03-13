package com.zengshi.ecp.prom.service.common.msg.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import org.drools.core.util.StringUtils;
import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.prom.dao.mapper.common.PromTypeMsgMapper;
import com.zengshi.ecp.prom.dao.model.PromTypeMsg;
import com.zengshi.ecp.prom.dao.model.PromTypeMsgCriteria;
import com.zengshi.ecp.prom.dao.model.PromTypeMsgCriteria.Criteria;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeMsgDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeMsgResponseDTO;
import com.zengshi.ecp.prom.dubbo.impl.converter.Converter;
import com.zengshi.ecp.prom.dubbo.util.PromCacheUtil;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.common.msg.interfaces.IPromMsgSV;
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
public class PromMsgSVImpl implements IPromMsgSV {

    private static final String MODULE = PromMsgSVImpl.class.getName();

    @Resource
    private PromTypeMsgMapper promTypeMsgMapper;

    @Resource
    private Converter<PromTypeMsg, PromTypeMsgResponseDTO> promTypeMsgResponseDTOConverter;

    /**
     * 提醒信息
     * 
     * @param id
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    @Override
    public PromTypeMsgResponseDTO queryPromMsgById(Long id) throws BusinessException {
        
        PromTypeMsg promTypeMsg = promTypeMsgMapper.selectByPrimaryKey(id);
        return promTypeMsgResponseDTOConverter.convert(promTypeMsg);
    }

    /**
     * 提醒信息
     * 
     * @param promTypeCode
     * @param status
     * @param position
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    @Override
    public PromTypeMsgResponseDTO queryPromMsgByCode(String promTypeCode, String status,
            String position) throws BusinessException {
        
        if (StringUtil.isEmpty(promTypeCode)) {
            // prom.400058 = 传入参数 促销类型代码不能为空！
            throw new BusinessException("prom.400058");
        }
        if (StringUtil.isEmpty(status)) {
            // prom.400059 = 传入参数 状态不能为空！
            throw new BusinessException("prom.400059");
        }
        if (StringUtil.isEmpty(position)) {
            // prom.400060 = 传入参数 位置代码不能为空！
            throw new BusinessException("prom.400060");
        }

        PromTypeMsgResponseDTO dto=new PromTypeMsgResponseDTO();
        //先取缓存 如果没有在取表数据
        dto=PromCacheUtil.getPromTypeMsgCache(promTypeCode+status+position);
        
        if(dto!=null){
            return dto;
        }
        
        PromTypeMsgCriteria example = new PromTypeMsgCriteria();
        Criteria cr = example.createCriteria();
        cr.andPromTypeCodeEqualTo(promTypeCode);
        cr.andStatusEqualTo(status);
        cr.andPositionEqualTo(position);
        
        List<PromTypeMsg> promTypeMsgList = promTypeMsgMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(promTypeMsgList)) {
            return null;
        }
        PromTypeMsgResponseDTO respdto= promTypeMsgResponseDTOConverter.convert(promTypeMsgList.get(0));
        CacheUtil.addItem((PromConstants.PromKey.CODE_PROM_TYPE_MSG+promTypeCode+status+position), respdto);
        return respdto;
    }

    /**
     * 提醒信息 列表
     * 
     * @param promTypeMsgDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    @Override
    public List<PromTypeMsgResponseDTO> queryPromMsgList(PromTypeMsgDTO promTypeMsgDTO)
            throws BusinessException {

        // 构造查询条件
        PromTypeMsgCriteria example = new PromTypeMsgCriteria();
        this.initPromTypeMsgParm(promTypeMsgDTO, example);
        List<PromTypeMsg> promTypeMsgList = promTypeMsgMapper.selectByExample(example);
        // 为空 返回
        if (CollectionUtils.isEmpty(promTypeMsgList)) {
            return null;
        }
        // model promTypeMsg 转为 PromTypeMsgResponseDTO
        List<PromTypeMsgResponseDTO> reList = new ArrayList<PromTypeMsgResponseDTO>();
        for (PromTypeMsg promTypeMsg : promTypeMsgList) {
            reList.add(promTypeMsgResponseDTOConverter.convert(promTypeMsg));
        }
        return reList;
    }

    /**
     * 初始化查询条件
     * 
     * @param promTypeMsgDTO
     * @param example
     * @author huangjx
     */
    private void initPromTypeMsgParm(PromTypeMsgDTO promTypeMsgDTO, PromTypeMsgCriteria example) {

        if (promTypeMsgDTO == null) {
            return;
        }
        Criteria cr = example.createCriteria();
        //序列值
        if (promTypeMsgDTO.getId() != null) {
            cr.andIdEqualTo(promTypeMsgDTO.getId());
        }
        //促销类型编码
        if (!StringUtils.isEmpty(promTypeMsgDTO.getPromTypeCode())) {
            cr.andPromTypeCodeEqualTo(promTypeMsgDTO.getPromTypeCode());
        }
        //状态
        if (!StringUtils.isEmpty(promTypeMsgDTO.getStatus())) {
            cr.andStatusEqualTo(promTypeMsgDTO.getStatus());
        }
        //位置
        if (!StringUtils.isEmpty(promTypeMsgDTO.getPosition())) {
            cr.andPositionEqualTo(promTypeMsgDTO.getPosition());
        }
    }
}
