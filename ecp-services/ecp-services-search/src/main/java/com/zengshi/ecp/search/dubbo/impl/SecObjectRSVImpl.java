package com.zengshi.ecp.search.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.search.dubbo.dto.SecConfig2ObjectReqDTO;
import com.zengshi.ecp.search.dubbo.dto.SecObjectReqDTO;
import com.zengshi.ecp.search.dubbo.dto.SecObjectRespDTO;
import com.zengshi.ecp.search.dubbo.interfaces.ISecObjectRSV;
import com.zengshi.ecp.search.dubbo.util.SearchCacheUtils;
import com.zengshi.ecp.search.service.common.interfaces.ISecObjectSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.CacheUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-search-server <br>
 * Description: <br>
 * Date:2016年3月9日下午4:09:56  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public class SecObjectRSVImpl implements ISecObjectRSV{
    
    @Resource
    public ISecObjectSV secObjectSV;

    @Override
    public long saveSecObjectAll(SecObjectReqDTO secObjectReqDTO) throws BusinessException {
        long id= secObjectSV.saveSecObjectAll(secObjectReqDTO);
        
        //缓存失效
        CacheUtil.delItem(SearchCacheUtils.CACHE_KEY_SECCONFIGMAP);
        SearchCacheUtils.notityWatchers();
        
        return id;
    }

    @Override
    public void deleteSecObjectAll(SecObjectReqDTO secObjectReqDTO) throws BusinessException {
        secObjectSV.deleteSecObjectAll(secObjectReqDTO);
        
        //缓存失效
        CacheUtil.delItem(SearchCacheUtils.CACHE_KEY_SECCONFIGMAP);
        SearchCacheUtils.notityWatchers();
    }

    @Override
    public void updateSecObjectAll(SecObjectReqDTO secObjectReqDTO) throws BusinessException {
        secObjectSV.updateSecObjectAll(secObjectReqDTO);
        
        //缓存失效
        CacheUtil.delItem(SearchCacheUtils.CACHE_KEY_SECCONFIGMAP);
        SearchCacheUtils.notityWatchers();
    }

    @Override
    public SecObjectRespDTO querySecObjectByIdAll(SecObjectReqDTO secObjectReqDTO)
            throws BusinessException {
        return secObjectSV.querySecObjectByIdAll(secObjectReqDTO);
    }

    @Override
    public PageResponseDTO<SecObjectRespDTO> querySecObjectPage(
            SecConfig2ObjectReqDTO secConfig2ObjectReqDTO) throws BusinessException {
        return secObjectSV.querySecObjectPage(secConfig2ObjectReqDTO);
    }

}

