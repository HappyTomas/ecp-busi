/** 
 * Project Name:ecp-services-demo 
 * File Name:DemoInfoClientTest.java 
 * Package Name:com.zengshi.ecp.test.client 
 * Date:2015-8-3下午6:55:12 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 *//*
package com.zengshi.ecp.test.client;


import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zengshi.ecp.general.solr.dto.SearchDataPageReqDTO;
import com.zengshi.ecp.general.solr.dto.SearchDataPageRespDTO;
import com.zengshi.ecp.general.solr.dto.SearchDataRowReqDTO;
import com.zengshi.ecp.general.solr.interfaces.ISearchDataSupport;
import com.zengshi.ecp.server.front.exception.BusinessException;

*//**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 *//*
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:dubbo/client/dubbo-test-prom.xml")
public class PromGdsDataSupportRSVTest extends AbstractJUnit4SpringContextTests {

    @Resource
    private ISearchDataSupport promGdsDataSupportRSV;
    
    @Test
    public void testPromGdsSupportAll() {
        SearchDataRowReqDTO searchDataRowReqDTO=new SearchDataRowReqDTO();
        for(int i=1;i<=15;i++){
            searchDataRowReqDTO.setId(3403);
            searchDataRowReqDTO.setPageNo(1);
            searchDataRowReqDTO.setInsideDbIndex(1);
            searchDataRowReqDTO.setInsidePageNo(i);
            searchDataRowReqDTO.setInsidePageSize(100);
            promGdsDataSupportRSV.getDataRow(searchDataRowReqDTO);
        }
      
        System.out.println("哈哈啥地方哈市的飞洒地方");
    }
    @Test
    public void testPromGdsSupportSku() {
        SearchDataRowReqDTO searchDataRowReqDTO=new SearchDataRowReqDTO();
        for(int i=1;i<=4;i++){
            searchDataRowReqDTO.setId(3399);
            searchDataRowReqDTO.setPageNo(1);
            searchDataRowReqDTO.setInsideDbIndex(1);
            searchDataRowReqDTO.setInsidePageNo(i);
            searchDataRowReqDTO.setInsidePageSize(100);
            promGdsDataSupportRSV.getDataRow(searchDataRowReqDTO);
        }
      
        System.out.println("sku测试");
    }
       
    @Test
    public void testPromGdsSupportSkuAndCatg() {
        SearchDataRowReqDTO searchDataRowReqDTO=new SearchDataRowReqDTO();
        for(int i=1;i<=4;i++){
            searchDataRowReqDTO.setId(3412);
            searchDataRowReqDTO.setPageNo(1);
            searchDataRowReqDTO.setInsideDbIndex(1);
            searchDataRowReqDTO.setInsidePageNo(i);
            searchDataRowReqDTO.setInsidePageSize(100);
            promGdsDataSupportRSV.getDataRow(searchDataRowReqDTO);
        }
      
        System.out.println("sku测试 and Catg测试");
    }
    @Test
    public void testInsidePager() {
        
        SearchDataPageReqDTO searchDataPageReqDTO = new SearchDataPageReqDTO();
        searchDataPageReqDTO.setPageSize(1);
        int pageNo = 0;
        int dbIndex =1;
        Map<String,Object> xParams = null;
        //数据源接口内部分页参数控制
        int insidePageNo=0;
        int insideDbIndex =1;
        searchDataPageReqDTO.setInsidePageSize(100);
        //数据源接口内部分页参数控制
        
        while (true) {
            pageNo++;
            searchDataPageReqDTO.setPageNo(pageNo);
            searchDataPageReqDTO.setDbIndex(dbIndex);
            searchDataPageReqDTO.setxParams(xParams);
            
            SearchDataPageRespDTO searchDataPageRespDTO =null;
            
            insidePageNo=0;
            
            while (true) {
                insidePageNo++;
              
                //数据源内部分页
                searchDataPageReqDTO.setInsidePageNo(insidePageNo);
                searchDataPageReqDTO.setInsideDbIndex(insideDbIndex);
                searchDataPageReqDTO.setxParams(xParams);
                
                searchDataPageRespDTO = promGdsDataSupportRSV
                        .getDataPage(searchDataPageReqDTO);

                if (searchDataPageRespDTO == null) {
                    throw new BusinessException(
                            "asdf",
                            new String[] { "",
                                    "return result searchDataPageRespDTO null" });
                }
                
                xParams=searchDataPageRespDTO.getxParams();
                
                //单表分页完毕
                if(searchDataPageRespDTO.isInsidePagerOver()){
                    
                    //重新初始化查询参数
                    insideDbIndex++;
                    insidePageNo=0;
                }
                
                //全表扫描完毕
                if(searchDataPageRespDTO.isInsideDbIndexOver()){
                    
                    //重新初始化查询参数
                    insideDbIndex=1;
                    insidePageNo=0;
                    break;
                }
                
            }
            
            //单表分页完毕
            if(searchDataPageRespDTO.isPagerOver()){
                
                //重新初始化查询参数
                dbIndex++;
                pageNo=0;
            }
            
            //全表扫描完毕
            if(searchDataPageRespDTO.isDbIndexOver()){
                break;
            }
                
        }
        
    }
}
*/