package com.zengshi.ecp.prom.dubbo.impl;

import javax.annotation.Resource;

import org.drools.core.util.StringUtils;

import com.zengshi.ecp.prom.dubbo.dto.PromGroupDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromGroupResponseDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoResponseDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromGroupRSV;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.busi.group.interfaces.IPromGroupSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.DateUtil;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromGroupRSVImpl implements IPromGroupRSV {

    private static final String MODULE = PromGroupRSVImpl.class.getName();
    
    @Resource
    private IPromGroupSV promGroupSV;

    /**
     * TODO主题促销保存
     * @see com.zengshi.ecp.prom.dubbo.interfaces.IPromGroupRSV#savePromGroup(com.zengshi.ecp.prom.dubbo.dto.PromGroupDTO)
     * @param promGroupDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void savePromGroup(PromGroupDTO promGroupDTO) throws BusinessException{
        
        if(promGroupDTO==null){
            //传入参数为空
            throw new BusinessException("prom.400076");
        }
        if(promGroupDTO.getCreateStaff()==null){
            promGroupDTO.setCreateStaff(promGroupDTO.getStaff().getId());
        }
        if(promGroupDTO.getCreateTime()==null){
            promGroupDTO.setCreateTime(DateUtil.getSysDate());
        }
        promGroupDTO.setStatus(PromConstants.PromGroup.STATUS_2);
        promGroupSV.savePromGroup(promGroupDTO);
    }
    /**
     * TODO主题促销编辑保存
     * @see com.zengshi.ecp.prom.dubbo.interfaces.IPromGroupRSV#saveEditPromGroup(com.zengshi.ecp.prom.dubbo.dto.PromGroupDTO)
     * @param promGroupDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void saveEditPromGroup(PromGroupDTO promGroupDTO) throws BusinessException{
        if(promGroupDTO==null){
            //传入参数为空
            throw new BusinessException("prom.400076");
        }
        if(promGroupDTO.getId()==null){
            //传入参数为空
            throw new BusinessException("prom.400076");
        }
        //为空 默认0
        if(StringUtils.isEmpty(promGroupDTO.getStatus())){
             promGroupDTO.setStatus(PromConstants.PromGroup.STATUS_2);
        }
        if(promGroupDTO.getUpdateStaff()==null){
            promGroupDTO.setUpdateStaff(promGroupDTO.getStaff().getId());
        }
        if(promGroupDTO.getUpdateTime()==null){
            promGroupDTO.setUpdateTime(DateUtil.getSysDate());
        }
        promGroupSV.saveEditPromGroup(promGroupDTO);
    }

    /**
     * TODO主题促销 提交
     * @see com.zengshi.ecp.prom.dubbo.interfaces.IPromGroupRSV#createPromGroup(com.zengshi.ecp.prom.dubbo.dto.PromGroupDTO)
     * @param promGroupDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void createPromGroup(PromGroupDTO promGroupDTO) throws BusinessException{
        
        if(promGroupDTO==null){
            //传入参数为空
            throw new BusinessException("prom.400076");
        }
        promGroupDTO.setStatus(PromConstants.PromGroup.STATUS_1);
        
        if(promGroupDTO.getCreateStaff()==null){
            promGroupDTO.setCreateStaff(promGroupDTO.getStaff().getId());
        }
        if(promGroupDTO.getCreateTime()==null){
            promGroupDTO.setCreateTime(DateUtil.getSysDate());
        }
        
        promGroupSV.savePromGroup(promGroupDTO);
    }
    /**
     * TODO主题促销 编辑提交
     * @see com.zengshi.ecp.prom.dubbo.interfaces.IPromGroupRSV#createEditPromGroup(com.zengshi.ecp.prom.dubbo.dto.PromGroupDTO)
     * @param promGroupDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void createEditPromGroup(PromGroupDTO promGroupDTO) throws BusinessException{
        if(promGroupDTO==null){
            //传入参数为空
            throw new BusinessException("prom.400076");
        }
        if(promGroupDTO.getId()==null){
            //传入参数为空
            throw new BusinessException("prom.400076");
        }
        //为空 默认1
        if(StringUtils.isEmpty(promGroupDTO.getStatus())){
            promGroupDTO.setStatus(PromConstants.PromGroup.STATUS_1);
       }
        
        if(promGroupDTO.getUpdateStaff()==null){
            promGroupDTO.setUpdateStaff(promGroupDTO.getStaff().getId());
        }
        if(promGroupDTO.getUpdateTime()==null){
            promGroupDTO.setUpdateTime(DateUtil.getSysDate());
        }
        promGroupSV.saveEditPromGroup(promGroupDTO);
    }

    /**
     * TODO获得主题促销列表
     * @see com.zengshi.ecp.prom.dubbo.interfaces.IPromGroupRSV#queryPromGroupList(com.zengshi.ecp.prom.dubbo.dto.PromGroupDTO)
     * @param promGroupDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PageResponseDTO<PromGroupResponseDTO> queryPromGroupList(PromGroupDTO promGroupDTO)
            throws BusinessException{
        return promGroupSV.queryPromGroupForPage(promGroupDTO, "1");
    }

    /**
     * TODO 获得某个主题下 店铺参加列表
     * @see com.zengshi.ecp.prom.dubbo.interfaces.IPromGroupRSV#queryPromGroup4Shop(java.lang.Long, com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO)
     * @param groupId
     * @param promInfoDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PageResponseDTO<PromInfoResponseDTO> queryPromGroup4Shop(Long groupId, PromInfoDTO promInfoDTO)
            throws BusinessException{
        
        return promGroupSV.queryPromGroup4Shop(groupId, promInfoDTO);
    }

    /**
     * TODO获得主题组对象
     * @see com.zengshi.ecp.prom.dubbo.interfaces.IPromGroupRSV#queryPromGroupById(com.zengshi.ecp.prom.dubbo.dto.PromGroupDTO)
     * @param promGroupDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PromGroupResponseDTO queryPromGroupById(PromGroupDTO promGroupDTO) throws BusinessException{
        
        if(promGroupDTO==null){
            throw new BusinessException("prom.400086");
        }
        if(promGroupDTO.getId()==null){
            throw new BusinessException("prom.400087");
        }
        return promGroupSV.queryPromGroupById(promGroupDTO.getId());
    }

}
