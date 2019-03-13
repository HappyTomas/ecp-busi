package com.zengshi.ecp.search.test.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.search.dubbo.dto.SecConfig2ObjectReqDTO;
import com.zengshi.ecp.search.dubbo.dto.SecFieldReqDTO;
import com.zengshi.ecp.search.dubbo.dto.SecObjectReqDTO;
import com.zengshi.ecp.search.dubbo.dto.SecObjectRespDTO;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import com.zengshi.ecp.search.service.common.interfaces.ISecObjectSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.test.EcpServicesTest;

public class SecObjectSVImplTest extends EcpServicesTest{
    
    @Resource
    private ISecObjectSV secObjectSV;
    
    @Test
    public void testPager(){
        SecConfig2ObjectReqDTO secConfig2ObjectReqDTO=new SecConfig2ObjectReqDTO();
        secConfig2ObjectReqDTO.setConfigId(1l);
        secConfig2ObjectReqDTO.setObjectType("0");
        secConfig2ObjectReqDTO.setStatus(SearchConstants.STATUS_VALID);
        PageResponseDTO<SecObjectRespDTO> resp=this.secObjectSV.querySecObjectPage(secConfig2ObjectReqDTO);
        System.out.println(resp);
    }
    
    @Test
    public void testSave(){
        SecObjectReqDTO secObjectReqDTO=new SecObjectReqDTO();
        secObjectReqDTO.setConfigId(2011l);
        secObjectReqDTO.setObjectNamecn("测试数据对象中文名");
        secObjectReqDTO.setObjectType("1");
        secObjectReqDTO.setObjectUniquefieldName("rid");
        
        List<SecFieldReqDTO> secFieldReqDTOList=new ArrayList<SecFieldReqDTO>();
        
        SecFieldReqDTO secFieldReqDTO=new SecFieldReqDTO();
        secFieldReqDTO.setObjectId(2011l);
        secFieldReqDTO.setFieldBeanFieldName("fieldname");
        secFieldReqDTO.setFieldIndexName("indexname");
        secFieldReqDTO.setFieldTypeName("typename");
        secFieldReqDTOList.add(secFieldReqDTO);
        
        secFieldReqDTO=new SecFieldReqDTO();
        secFieldReqDTO.setObjectId(2011l);
        secFieldReqDTO.setFieldBeanFieldName("fieldname2");
        secFieldReqDTO.setFieldIndexName("indexname2");
        secFieldReqDTO.setFieldTypeName("typename2");
        secFieldReqDTOList.add(secFieldReqDTO);
        
        secObjectReqDTO.setSecFieldReqDTOList(secFieldReqDTOList);
        
        
        secObjectSV.saveSecObjectAll(secObjectReqDTO);
    }

}

