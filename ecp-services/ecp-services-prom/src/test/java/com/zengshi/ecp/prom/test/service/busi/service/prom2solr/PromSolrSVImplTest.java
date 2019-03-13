package com.zengshi.ecp.prom.test.service.busi.service.prom2solr;

import javax.annotation.Resource;
import org.junit.Test;

import com.zengshi.ecp.prom.dubbo.dto.Prom2SolrReqDTO;
import com.zengshi.ecp.prom.service.busi.prom2solr.interfaces.IPromSolrSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.test.EcpServicesTest;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-6 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromSolrSVImplTest extends EcpServicesTest {

    @Resource
    private IPromSolrSV promSolrSV;
    
    @Test
    public void testSuccess() throws BusinessException {
        
        Prom2SolrReqDTO prom2SolrReqDTO=new Prom2SolrReqDTO();
        prom2SolrReqDTO.setSiteId(1l);
        prom2SolrReqDTO.setShopId(35l);
        prom2SolrReqDTO.setPageSize(100);
        promSolrSV.sendData(prom2SolrReqDTO);
      //  promSolrSV.sendDataThread(prom2SolrReqDTO);
    }
}
