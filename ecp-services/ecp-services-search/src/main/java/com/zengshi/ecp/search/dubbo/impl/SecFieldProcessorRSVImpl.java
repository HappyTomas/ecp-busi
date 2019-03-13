package com.zengshi.ecp.search.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.zengshi.ecp.search.dao.model.SecFieldProcessor;
import com.zengshi.ecp.search.dubbo.dto.SecFieldProcessorReqDTO;
import com.zengshi.ecp.search.dubbo.dto.SecFieldProcessorRespDTO;
import com.zengshi.ecp.search.dubbo.interfaces.ISecFieldProcessorRSV;
import com.zengshi.ecp.search.dubbo.util.BeanTransfermationUtils;
import com.zengshi.ecp.search.dubbo.util.SearchCacheUtils;
import com.zengshi.ecp.search.service.common.interfaces.ISecFieldProcessorSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.CacheUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-search-server-xhs <br>
 * Description: <br>
 * Date:2016年3月10日上午9:33:41  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public class SecFieldProcessorRSVImpl implements ISecFieldProcessorRSV {
    
    @Resource
    public ISecFieldProcessorSV secFieldProcessorSV;

    @Override
    public String saveSecFieldProcessor(SecFieldProcessorReqDTO secFieldProcessorReqDTO)
            throws BusinessException {
        String name= secFieldProcessorSV.saveSecFieldProcessor(secFieldProcessorReqDTO);
        
        //缓存失效
        CacheUtil.delItem(SearchCacheUtils.CACHE_KEY_SECFIELDPROCESSORLIST);
        SearchCacheUtils.notityWatchers();
        
        return name;
    }

    @Override
    public List<SecFieldProcessorRespDTO> listSecFieldProcessor(
            SecFieldProcessorReqDTO secFieldProcessorReqDTO) throws BusinessException {
        List<SecFieldProcessor> list=secFieldProcessorSV.listSecFieldProcessor(secFieldProcessorReqDTO);
        
        List<SecFieldProcessorRespDTO> retList=new ArrayList<SecFieldProcessorRespDTO>();
        
        if(CollectionUtils.isNotEmpty(list)){
            for(SecFieldProcessor secFieldProcessor:list){
                SecFieldProcessorRespDTO secFieldProcessorRespDTO = new SecFieldProcessorRespDTO();
                BeanTransfermationUtils.bo2dto(secFieldProcessorRespDTO, secFieldProcessor);
                retList.add(secFieldProcessorRespDTO);
            }
        }
        
        return retList;
    }

}

