package com.zengshi.ecp.search.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.search.dao.model.SecRedirect;
import com.zengshi.ecp.search.dubbo.dto.SecRedirectReqDTO;
import com.zengshi.ecp.search.dubbo.dto.SecRedirectRespDTO;
import com.zengshi.ecp.search.dubbo.interfaces.ISecRedirectRSV;
import com.zengshi.ecp.search.dubbo.util.BeanTransfermationUtils;
import com.zengshi.ecp.search.dubbo.util.SearchCacheUtils;
import com.zengshi.ecp.search.service.common.interfaces.ISecRedirectSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.CacheUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-search <br>
 * Description: <br>
 * Date:2015年8月19日下午8:09:03  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public class SecRedirectRSVImpl implements ISecRedirectRSV {
    
    @Resource
    public ISecRedirectSV secRedirectSV;

    @Override
    public long saveSecRedirect(SecRedirectReqDTO secRedirectReqDTO) throws BusinessException {
        long id= secRedirectSV.saveSecRedirect(secRedirectReqDTO);
        
        //缓存失效
        CacheUtil.delItem(SearchCacheUtils.CACHE_KEY_SECREDIRECTLIST);
        SearchCacheUtils.notityWatchers();
        
        return id;
    }

    @Override
    public void deleteSecRedirect(SecRedirectReqDTO secRedirectReqDTO) throws BusinessException {
        secRedirectSV.deleteSecRedirect(secRedirectReqDTO);
        
        //缓存失效
        CacheUtil.delItem(SearchCacheUtils.CACHE_KEY_SECREDIRECTLIST);
        SearchCacheUtils.notityWatchers();
    }

    @Override
    public void updateSecRedirect(SecRedirectReqDTO secRedirectReqDTO) throws BusinessException {
        secRedirectSV.updateSecRedirect(secRedirectReqDTO);
        
        //缓存失效
        CacheUtil.delItem(SearchCacheUtils.CACHE_KEY_SECREDIRECTLIST);
        SearchCacheUtils.notityWatchers();
    }

    @Override
    public SecRedirectRespDTO querySecRedirectById(SecRedirectReqDTO secRedirectReqDTO) throws BusinessException {
        SecRedirect secRedirect=secRedirectSV.querySecRedirectById(secRedirectReqDTO.getId());
        if(secRedirect==null){
            return null;
        }
        SecRedirectRespDTO secRedirectRespDTO=new SecRedirectRespDTO();
        BeanTransfermationUtils.bo2dto(secRedirectRespDTO, secRedirect);
        return secRedirectRespDTO;
    }

    @Override
    public List<SecRedirectRespDTO> listSecRedirect() throws BusinessException {
        List<SecRedirectRespDTO> secSolrServerRespDTOList=new ArrayList<SecRedirectRespDTO>();
        List<SecRedirect> secRedirectList=secRedirectSV.listSecRedirect();  
        
        if(secRedirectList!=null&&!secRedirectList.isEmpty()){
            
            SecRedirectRespDTO secRedirectRespDTO;
            for(SecRedirect secRedirect:secRedirectList){
                secRedirectRespDTO=new SecRedirectRespDTO();
                BeanTransfermationUtils.bo2dto(secRedirectRespDTO, secRedirect);
                secSolrServerRespDTOList.add(secRedirectRespDTO);
            }
        }
        
        return secSolrServerRespDTOList;
    }

    @Override
    public PageResponseDTO<SecRedirectRespDTO> querySecRedirectPage(
            SecRedirectReqDTO secRedirectReqDTO) throws BusinessException {
        return secRedirectSV.querySecRedirectPage(secRedirectReqDTO);
    }

}

