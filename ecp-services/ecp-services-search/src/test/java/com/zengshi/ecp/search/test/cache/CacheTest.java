package com.zengshi.ecp.search.test.cache;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import com.zengshi.ecp.search.dubbo.dto.SecFieldProcessorRespDTO;
import com.zengshi.ecp.search.dubbo.dto.SecFieldTypeRespDTO;
import com.zengshi.ecp.search.dubbo.dto.SecObjectProcessorRespDTO;
import com.zengshi.ecp.search.dubbo.search.SearchException;
import com.zengshi.ecp.search.dubbo.util.SearchCacheUtils;
import com.zengshi.ecp.server.test.EcpServicesTest;

public class CacheTest extends EcpServicesTest {

    @Test
    public void testZk() {
        try {
            SearchCacheUtils.getSecZkStr();
        } catch (SearchException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRedirect() {
        SearchCacheUtils.getSecRedirectList();
    }

    @Test
    public void testSolrServer() {
        try {
            SearchCacheUtils.getSecSolrServerList();
        } catch (SearchException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConfig() {
        try {
            SearchCacheUtils.getSecConfigReqDTOMap();
        } catch (SearchException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConfig2() {
        try {
            SearchCacheUtils.getSecConfigFieldParam2FieldNameMap("gdscollection");
        } catch (SearchException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testArgs() {
        HashMap<String, String> map=SearchCacheUtils.getSecArgsMap();
        System.out.println(map);
    }
    
    @Test
    public void testFieldProcessor() {
        List<SecFieldProcessorRespDTO> list=SearchCacheUtils.getSecFieldProcessorList();
        System.out.println(list);
    }
    
    @Test
    public void testFieldType() {
        List<SecFieldTypeRespDTO> list=SearchCacheUtils.getSecFieldTypeList();
        System.out.println(list);
    }
    
    @Test
    public void testObjectProcessor() {
        List<SecObjectProcessorRespDTO> list=SearchCacheUtils.getSecObjectProcessorList();
        System.out.println(list);
    }
    
    @Test
    public void testClean() {
        SearchCacheUtils.clean(); 
    }

}
