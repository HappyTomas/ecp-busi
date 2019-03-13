package com.zengshi.ecp.prom.service.busi.group.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.prom.dao.mapper.busi.PromChkMapper;
import com.zengshi.ecp.prom.dao.model.PromChk;
import com.zengshi.ecp.prom.dao.model.PromChkCriteria;
import com.zengshi.ecp.prom.dubbo.dto.PromChkDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromChkResponseDTO;
import com.zengshi.ecp.prom.dubbo.impl.converter.Converter;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.busi.group.interfaces.IPromGroupChkSV;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromInfoSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromGroupChkSVImpl extends GeneralSQLSVImpl implements IPromGroupChkSV {

    private static final String MODULE = PromGroupChkSVImpl.class.getName();

    @Resource
    private PromChkMapper promChkMapper;

    @Resource
    private Converter<PromChkDTO, PromChk> promChkConverter;

    @Resource
    private Converter<PromChk, PromChkResponseDTO> promChkResponseDTOConverter;
    
    @Resource
    private IPromInfoSV promInfoSV;
    
    @Resource(name = "seq_prom_chk_id")
    private PaasSequence seq_prom_chk_id;

    /**
     * 保存促销审核
     * 
     * @param promGroupDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void savePromGroupChk(PromChkDTO promChkDTO) throws BusinessException {
        
        PromChk promChk = new PromChk();
        promChkDTO.setId(seq_prom_chk_id.nextValue());
        promChk = promChkConverter.convert(promChkDTO);
        promChkMapper.insert(promChk);
        
        //更新促销 审核状态
        String status=null;
        if(PromConstants.PromGroupChk.STATUS_1.equals(promChkDTO.getStatus())){
            //通过
            status=PromConstants.PromInfo.STATUS_10;
        }
        if(PromConstants.PromGroupChk.STATUS_0.equals(promChkDTO.getStatus())){
            //不通过
            status=PromConstants.PromInfo.STATUS_50;//审核不通过
        }
        promInfoSV.editPromInfoStatusByPromId(promChkDTO.getPromId(), promChkDTO.getCreateStaff(), status, promChkDTO.getCreateTime());
        
    }

    /**
     * 促销审核信息
     * 
     * @param id
     * @throws BusinessException
     * @author huangjx
     */
    public PromChkResponseDTO queryPromGroupChkById(Long id) throws BusinessException {
        return promChkResponseDTOConverter.convert(promChkMapper.selectByPrimaryKey(id));
    }
    
    /**
     * 促销审核信息
     * @param promId
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromChkResponseDTO> queryPromGroupChkByPromId(Long promId) throws BusinessException {
        
        PromChkCriteria example=new PromChkCriteria();
        PromChkCriteria.Criteria cr=example.createCriteria();
        //促销编码
        cr.andPromIdEqualTo(promId);
        example.setOrderByClause(" id desc ");
        List<PromChk> promChkList=promChkMapper.selectByExample(example);
        //为空验证
        if(CollectionUtils.isEmpty(promChkList)){
            return null;
        }
        //返回格式转换
        List<PromChkResponseDTO> reList=new ArrayList<PromChkResponseDTO>();
        for(PromChk dto:promChkList){
            reList.add(promChkResponseDTOConverter.convert(dto));
        }
        return reList;
    }
}
