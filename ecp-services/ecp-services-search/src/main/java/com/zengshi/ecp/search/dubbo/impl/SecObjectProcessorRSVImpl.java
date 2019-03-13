package com.zengshi.ecp.search.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.zengshi.ecp.search.dao.model.SecObjectProcessor;
import com.zengshi.ecp.search.dubbo.dto.SecObjectProcessorReqDTO;
import com.zengshi.ecp.search.dubbo.dto.SecObjectProcessorRespDTO;
import com.zengshi.ecp.search.dubbo.interfaces.ISecObjectProcessorRSV;
import com.zengshi.ecp.search.dubbo.util.BeanTransfermationUtils;
import com.zengshi.ecp.search.dubbo.util.SearchCacheUtils;
import com.zengshi.ecp.search.service.common.interfaces.ISecObjectProcessorSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.CacheUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-search-server-xhs <br>
 * Description: <br>
 * Date:2016年3月10日上午9:34:23  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public class SecObjectProcessorRSVImpl implements ISecObjectProcessorRSV {
    
    @Resource
    public ISecObjectProcessorSV secObjectProcessorSV;

    @Override
    public String saveSecObjectProcessor(SecObjectProcessorReqDTO secObjectProcessorReqDTO)
            throws BusinessException {
        String name= secObjectProcessorSV.saveSecObjectProcessor(secObjectProcessorReqDTO);
        
        //缓存失效
        CacheUtil.delItem(SearchCacheUtils.CACHE_KEY_SECOBJECTPROCESSORLIST);
        SearchCacheUtils.notityWatchers();
        
        return name;
    }

    @Override
    public List<SecObjectProcessorRespDTO> listSecObjectProcessor(
            SecObjectProcessorReqDTO secObjectProcessorReqDTO) throws BusinessException {
        List<SecObjectProcessor> list=secObjectProcessorSV.listSecObjectProcessor(secObjectProcessorReqDTO);
        
        List<SecObjectProcessorRespDTO> retList=new ArrayList<SecObjectProcessorRespDTO>();
        
        if(CollectionUtils.isNotEmpty(list)){
            for(SecObjectProcessor secObjectProcessor:list){
                SecObjectProcessorRespDTO secObjectProcessorRespDTO = new SecObjectProcessorRespDTO();
                BeanTransfermationUtils.bo2dto(secObjectProcessorRespDTO, secObjectProcessor);
                retList.add(secObjectProcessorRespDTO);
            }
        }
        
        return retList;
        
    }

}

