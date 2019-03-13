package com.zengshi.ecp.search.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.search.dao.model.SecArgs;
import com.zengshi.ecp.search.dubbo.dto.SecArgsRespDTO;
import com.zengshi.ecp.search.dubbo.interfaces.ISecArgsRSV;
import com.zengshi.ecp.search.dubbo.util.BeanTransfermationUtils;
import com.zengshi.ecp.search.service.common.interfaces.ISecArgsSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-search-server-xhs <br>
 * Description: <br>
 * Date:2016年1月20日下午8:05:42  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public class SecArgsRSVImpl implements ISecArgsRSV {
    
    @Resource
    public ISecArgsSV secArgsSV;

    @Override
    public List<SecArgsRespDTO> listSecArgs() throws BusinessException {
        List<SecArgsRespDTO> secArgsRespDTOList=new ArrayList<SecArgsRespDTO>();
        List<SecArgs> secArgsList=secArgsSV.listSecArgs();
        
        if(secArgsList!=null&&!secArgsList.isEmpty()){
            
            SecArgsRespDTO secArgsRespDTO;
            for(SecArgs secArgs:secArgsList){
                secArgsRespDTO=new SecArgsRespDTO();
                BeanTransfermationUtils.bo2dto(secArgsRespDTO, secArgs);
                secArgsRespDTOList.add(secArgsRespDTO);
            }
        }
        
        return secArgsRespDTOList;
    }

}

