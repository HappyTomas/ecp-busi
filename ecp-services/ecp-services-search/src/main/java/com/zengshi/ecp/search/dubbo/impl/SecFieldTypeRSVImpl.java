package com.zengshi.ecp.search.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.zengshi.ecp.search.dao.model.SecFieldType;
import com.zengshi.ecp.search.dubbo.dto.SecFieldTypeReqDTO;
import com.zengshi.ecp.search.dubbo.dto.SecFieldTypeRespDTO;
import com.zengshi.ecp.search.dubbo.interfaces.ISecFieldTypeRSV;
import com.zengshi.ecp.search.dubbo.util.BeanTransfermationUtils;
import com.zengshi.ecp.search.service.common.interfaces.ISecFieldTypeSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-search-server-xhs <br>
 * Description: <br>
 * Date:2016年3月10日上午9:35:09  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public class SecFieldTypeRSVImpl implements ISecFieldTypeRSV {
    
    @Resource
    public ISecFieldTypeSV secFieldTypeSV;

    @Override
    public List<SecFieldTypeRespDTO> listSecFieldType(SecFieldTypeReqDTO secFieldTypeReqDTO)
            throws BusinessException {
        List<SecFieldType> list=secFieldTypeSV.listSecFieldType(secFieldTypeReqDTO);
        
        List<SecFieldTypeRespDTO> retList=new ArrayList<SecFieldTypeRespDTO>();
        
        if(CollectionUtils.isNotEmpty(list)){
            for(SecFieldType secFieldType:list){
                SecFieldTypeRespDTO secFieldTypeRespDTO = new SecFieldTypeRespDTO();
                BeanTransfermationUtils.bo2dto(secFieldTypeRespDTO, secFieldType);
                retList.add(secFieldTypeRespDTO);
            }
        }
        
        return retList;
    }

}

