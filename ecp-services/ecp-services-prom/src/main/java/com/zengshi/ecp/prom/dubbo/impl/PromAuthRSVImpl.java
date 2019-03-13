package com.zengshi.ecp.prom.dubbo.impl;

import java.util.List;

import javax.annotation.Resource;

import org.drools.core.util.StringUtils;
import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.prom.dubbo.dto.PromType4ShopDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromType4ShopResponseDTO;
import com.zengshi.ecp.prom.dubbo.dto.QueryPromType4ShopDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromAuthRSV;
import com.zengshi.ecp.prom.service.busi.auth.interfaces.IPromType4ShopSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromAuthRSVImpl implements IPromAuthRSV {

    private static final String MODULE = PromAuthRSVImpl.class.getName();
    
    @Resource
    private IPromType4ShopSV promType4ShopSV;
    /**
     * 促销授权店铺 保存
     * 
     * @param promGroupDTO
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromType4ShopDTO> savePromType4Shop(List<PromType4ShopDTO> promType4ShopDTOList)
            throws BusinessException{
        
        if(CollectionUtils.isEmpty(promType4ShopDTOList)){
             //传入参数为空
            throw new BusinessException("prom.400076");
        }
        
        //验证 店铺是否已经有效的授权 待实现 并且返回错误说明
        List<PromType4ShopDTO>  l=promType4ShopSV.validPromType4Shop(promType4ShopDTOList);
        
        if(CollectionUtils.isEmpty(l)){
            promType4ShopSV.savePromType4Shop(promType4ShopDTOList);
        }else{
            return l;
        }
        
        return null;
    }

    /**
     * 促销授权店铺 编辑保存
     * 
     * @param promGroupDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void updatePromType4ShopById(PromType4ShopDTO promType4ShopDTO) throws BusinessException{
        
        if(promType4ShopDTO==null){
            //传入参数为空
           throw new BusinessException("prom.400076");
       }
        if(promType4ShopDTO.getId()==null){
            //传入参数为空
           throw new BusinessException("prom.400062");
       }
        promType4ShopSV.updatePromType4ShopById(promType4ShopDTO);
    }
    
 
    /**
     * 编辑状态
     * @param id
     * @param status
     * @throws BusinessException
     * @author huangjx
     */
    public void updateStatusById(PromType4ShopDTO promType4ShopDTO) throws BusinessException{
        
        if(promType4ShopDTO==null){
            //传入参数为空
           throw new BusinessException("prom.400076");
       }
        if(promType4ShopDTO.getId()==null){
            //传入参数为空
            throw new BusinessException("prom.400062");
       }
        if(StringUtils.isEmpty(promType4ShopDTO.getStatus())){
            //状态为空
           throw new BusinessException("prom.400082");
       }
        
        promType4ShopSV.updateStatus(promType4ShopDTO);
    }

    /**
     * TODO促销类型 授权店铺列表
     * @see com.zengshi.ecp.prom.dubbo.interfaces.IPromAuthRSV#queryPromType4ShopList(com.zengshi.ecp.prom.dubbo.dto.QueryPromType4ShopDTO)
     * @param queryPromType4ShopDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PageResponseDTO<PromType4ShopResponseDTO> queryPromType4ShopForPage(
            QueryPromType4ShopDTO queryPromType4ShopDTO) throws BusinessException{
        
           return promType4ShopSV.queryPromType4ShopForPage(queryPromType4ShopDTO);
    }

    /**
     * TODO根据id活动 授权店铺信息
     * @see com.zengshi.ecp.prom.dubbo.interfaces.IPromAuthRSV#queryPromType4ShopById(com.zengshi.ecp.prom.dubbo.dto.PromType4ShopDTO)
     * @param promType4ShopDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PromType4ShopResponseDTO queryPromType4ShopById(PromType4ShopDTO promType4ShopDTO) throws BusinessException{
        
        if(promType4ShopDTO==null){
            //传入参数为空
           throw new BusinessException("prom.400086");
       }
        if(promType4ShopDTO.getId()==null){
            //传入参数为空
           throw new BusinessException("prom.400076");
       }
        return promType4ShopSV.queryPromType4ShopById(promType4ShopDTO.getId());
    }

}
