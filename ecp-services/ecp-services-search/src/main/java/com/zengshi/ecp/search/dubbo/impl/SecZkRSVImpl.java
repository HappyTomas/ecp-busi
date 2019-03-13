package com.zengshi.ecp.search.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.search.dao.model.SecZk;
import com.zengshi.ecp.search.dubbo.dto.SecZkRespDTO;
import com.zengshi.ecp.search.dubbo.interfaces.ISecZkRSV;
import com.zengshi.ecp.search.dubbo.util.BeanTransfermationUtils;
import com.zengshi.ecp.search.service.common.interfaces.ISecZkSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-search <br>
 * Description: <br>
 * Date:2015年8月19日下午8:07:54  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public class SecZkRSVImpl implements ISecZkRSV {
    
    @Resource
    public ISecZkSV secZkSV;

    @Override
    public List<SecZkRespDTO> listSecZk() throws BusinessException {
        
        List<SecZkRespDTO> secZkRespDTOList=new ArrayList<SecZkRespDTO>();
        List<SecZk> secZkList=secZkSV.listSecZk();
        
        if(secZkList!=null&&!secZkList.isEmpty()){
            
            SecZkRespDTO secZkRespDTO;
            for(SecZk secZk:secZkList){
                secZkRespDTO=new SecZkRespDTO();
                BeanTransfermationUtils.bo2dto(secZkRespDTO, secZk);
                secZkRespDTOList.add(secZkRespDTO);
            }
        }
        
        return secZkRespDTOList;
    }

}

