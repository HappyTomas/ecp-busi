package com.zengshi.ecp.search.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.search.dao.model.SecSolrServer;
import com.zengshi.ecp.search.dubbo.dto.SecSolrServerRespDTO;
import com.zengshi.ecp.search.dubbo.interfaces.ISecSolrServerRSV;
import com.zengshi.ecp.search.dubbo.util.BeanTransfermationUtils;
import com.zengshi.ecp.search.service.common.interfaces.ISecSolrServerSV;
import com.zengshi.ecp.server.front.exception.BusinessException;


/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-search <br>
 * Description: <br>
 * Date:2015年8月19日下午8:08:13  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public class SecSolrServerRSVImpl implements ISecSolrServerRSV {
    
    @Resource
    public ISecSolrServerSV secSolrServerSV;

    @Override
    public List<SecSolrServerRespDTO> listSecSolrServer() throws BusinessException {
        List<SecSolrServerRespDTO> secSolrServerRespDTOList=new ArrayList<SecSolrServerRespDTO>();
        List<SecSolrServer> secSolrServerList=secSolrServerSV.listSecSolrServer(); 
        
        if(secSolrServerList!=null&&!secSolrServerList.isEmpty()){
            
            SecSolrServerRespDTO secSolrServerRespDTO;
            for(SecSolrServer secSolrServer:secSolrServerList){
                secSolrServerRespDTO=new SecSolrServerRespDTO();
                BeanTransfermationUtils.bo2dto(secSolrServerRespDTO, secSolrServer);
                secSolrServerRespDTOList.add(secSolrServerRespDTO);
            }
        }
        
        return secSolrServerRespDTOList;
    }

}

