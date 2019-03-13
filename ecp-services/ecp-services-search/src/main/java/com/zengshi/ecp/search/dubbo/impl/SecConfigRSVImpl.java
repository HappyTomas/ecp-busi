package com.zengshi.ecp.search.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.search.dao.model.SecConfig;
import com.zengshi.ecp.search.dubbo.dto.SecConfigReqDTO;
import com.zengshi.ecp.search.dubbo.dto.SecConfigRespDTO;
import com.zengshi.ecp.search.dubbo.interfaces.ISecConfigRSV;
import com.zengshi.ecp.search.dubbo.util.BeanTransfermationUtils;
import com.zengshi.ecp.search.dubbo.util.SearchCacheUtils;
import com.zengshi.ecp.search.service.common.interfaces.ISecConfigSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.CacheUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-search-server <br>
 * Description: <br>
 * Date:2016年3月9日下午4:10:04  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public class SecConfigRSVImpl implements ISecConfigRSV {
    
    @Resource
    public ISecConfigSV secConfigSV;

    @Override
    public long saveSecConfig(SecConfigReqDTO secConfigReqDTO) throws BusinessException {
        long id= secConfigSV.saveSecConfig(secConfigReqDTO);
        
        //缓存失效
        CacheUtil.delItem(SearchCacheUtils.CACHE_KEY_SECCONFIGMAP);
        SearchCacheUtils.notityWatchers();
        
        return id;
    }

    @Override
    public void deleteSecConfigAll(SecConfigReqDTO secConfigReqDTO) throws BusinessException {
        secConfigSV.deleteSecConfigAll(secConfigReqDTO);
        
        //缓存失效
        CacheUtil.delItem(SearchCacheUtils.CACHE_KEY_SECCONFIGMAP);
        SearchCacheUtils.notityWatchers();
    }

    @Override
    public void updateSecConfig(SecConfigReqDTO secConfigReqDTO) throws BusinessException {
        secConfigSV.updateSecConfig(secConfigReqDTO);
        
        //缓存失效
        CacheUtil.delItem(SearchCacheUtils.CACHE_KEY_SECCONFIGMAP);
        SearchCacheUtils.notityWatchers();
    }

    @Override
    public void updateSecConfigSelective(SecConfigReqDTO secConfigReqDTO) throws BusinessException {
        secConfigSV.updateSecConfigSelective(secConfigReqDTO);
        
        //缓存失效
        CacheUtil.delItem(SearchCacheUtils.CACHE_KEY_SECCONFIGMAP);
        SearchCacheUtils.notityWatchers();
    }

    @Override
    public SecConfigRespDTO querySecConfigById(SecConfigReqDTO secConfigReqDTO) throws BusinessException {
        return secConfigSV.querySecConfigById(secConfigReqDTO);
    }
    
    @Override
    public SecConfigRespDTO querySecConfigByIdAll(SecConfigReqDTO secConfigReqDTO) throws BusinessException {
        return secConfigSV.querySecConfigByIdAll(secConfigReqDTO);
    }

    @Override
    public List<SecConfigRespDTO> listSecConfigAll(SecConfigReqDTO secConfigReqDTO) throws BusinessException {
        return secConfigSV.listSecConfigAll(secConfigReqDTO);
    }

    @Override
    public List<SecConfigRespDTO> listSecConfig(SecConfigReqDTO secConfigReqDTO) throws BusinessException {
        List<SecConfigRespDTO> secConfigRespDTOList=new ArrayList<SecConfigRespDTO>();
        List<SecConfig> secConfigList=secConfigSV.listSecConfig(secConfigReqDTO);  
         
        if(secConfigList!=null&&!secConfigList.isEmpty()){
            
            SecConfigRespDTO secConfigRespDTO;
            for(SecConfig secConfig:secConfigList){
                secConfigRespDTO=new SecConfigRespDTO();
                BeanTransfermationUtils.bo2dto(secConfigRespDTO, secConfig);
                secConfigRespDTOList.add(secConfigRespDTO);
            }
        }
        
        return secConfigRespDTOList;
    }
    
    @Override
    public boolean isDupConfigName(SecConfigReqDTO secConfigReqDTO) throws BusinessException {
        return this.secConfigSV.isDupConfigName(secConfigReqDTO);
    }

    @Override
    public PageResponseDTO<SecConfigRespDTO> querySecConfigPage(SecConfigReqDTO secConfigReqDTO)
            throws BusinessException {
        return secConfigSV.querySecConfigPage(secConfigReqDTO);
    }

}

