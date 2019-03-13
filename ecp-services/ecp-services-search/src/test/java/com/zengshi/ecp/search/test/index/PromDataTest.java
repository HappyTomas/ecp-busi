package com.zengshi.ecp.search.test.index;

import java.util.Map;

import org.junit.Test;

import com.zengshi.ecp.frame.context.EcpFrameContextHolder;
import com.zengshi.ecp.general.solr.dto.SearchDataDupPagerPageReqDTO;
import com.zengshi.ecp.general.solr.dto.SearchDataDupPagerPageRespDTO;
import com.zengshi.ecp.general.solr.dto.SearchDataDupPagerRowPageReqDTO;
import com.zengshi.ecp.general.solr.dto.SearchDataDupPagerRowPageRespDTO;
import com.zengshi.ecp.general.solr.interfaces.ISearchDataDupPagerSupport;
import com.zengshi.ecp.search.dubbo.search.util.SearchUtils;
import com.zengshi.ecp.search.dubbo.util.SearchCacheUtils;
import com.zengshi.ecp.search.dubbo.util.SearchMessageKeyContants;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.test.EcpServicesTest;

public class PromDataTest extends EcpServicesTest {
    
    //全量
    @Test
    public void full(){
        
        String dubboServiceName = "promGdsDataSupportRSV";
        
        int pageNo = 0;
        int dbIndex =1;
        Map<String,Object> xParams = null;
        
        // 创建其它域提供的DubboService注入实例
        ISearchDataDupPagerSupport dubboServiceObject = EcpFrameContextHolder.getBean(dubboServiceName,
                ISearchDataDupPagerSupport.class);
        
        SearchDataDupPagerPageReqDTO searchDataPageReqDTO = new SearchDataDupPagerPageReqDTO();
        searchDataPageReqDTO.setJsonParams("");
        
        //二级分页目前外部分页设置只支持1
        searchDataPageReqDTO.setPageSize(1);
        
        int insidePageNo=0;
        int insideDbIndex =1;
        searchDataPageReqDTO.setInsidePageSize(200);
        
        //逐表扫描放到业务域中控制
        while (true) {
            pageNo++;
            searchDataPageReqDTO.setPageNo(pageNo);
            searchDataPageReqDTO.setDbIndex(dbIndex);
            searchDataPageReqDTO.setxParams(xParams);
            
            SearchDataDupPagerPageRespDTO searchDataPageRespDTO =null;
            
            //数据源内部分页页码重置
            insidePageNo=0;
            
            while (true) {
                insidePageNo++;
              
                //数据源内部分页
                searchDataPageReqDTO.setInsidePageNo(insidePageNo);
                searchDataPageReqDTO.setInsideDbIndex(insideDbIndex);
                searchDataPageReqDTO.setxParams(xParams);
                
                searchDataPageRespDTO = dubboServiceObject
                        .getDataPage(searchDataPageReqDTO);

                if (searchDataPageRespDTO == null) {
                    throw new BusinessException(
                            SearchMessageKeyContants.Error.KEY_ERROR_GETSEARCHDATA,
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
    
    //增量-促销
    @Test
    public void delta(){
        
        String dubboServiceName = "promGdsDataSupportRSV";
        
        // 创建其它域提供的DubboService注入实例
        ISearchDataDupPagerSupport dubboServiceObject = EcpFrameContextHolder.getBean(dubboServiceName,
                ISearchDataDupPagerSupport.class);
        
        SearchDataDupPagerRowPageReqDTO searchDataRowReqDTO = new SearchDataDupPagerRowPageReqDTO();
        searchDataRowReqDTO.setId("");
        searchDataRowReqDTO.setJsonParams("");
        
        Map<String,Object> xParams = null;

        // 暂时只支持主键类型是Number类型的数据
        SearchDataDupPagerRowPageRespDTO searchDataRowRespDTO = null;
        
        int insidePageNo=0;
        int insideDbIndex =1;
        int insidePageSize=Integer.parseInt(SearchCacheUtils.getSecArgsMap().get("PAGE_FULLIMPORT"));
        searchDataRowReqDTO.setInsidePageSize(insidePageSize);
        
        // 先删除suggest索引数据
        
        while (true) {
            insidePageNo++;
            searchDataRowReqDTO.setInsidePageNo(insidePageNo);
            searchDataRowReqDTO.setInsideDbIndex(insideDbIndex);
            searchDataRowReqDTO.setxParams(xParams);
            
            try {
                
                searchDataRowRespDTO = dubboServiceObject.getDataRowPage(searchDataRowReqDTO);
            } catch (Exception e) {
                throw new BusinessException(
                        SearchMessageKeyContants.Error.KEY_ERROR_GETSEARCHDATA,
                        new String[] { "",
                                SearchUtils.getExceptionMessage(e) });
            }
            
            if (searchDataRowRespDTO == null) {
                throw new BusinessException(
                        SearchMessageKeyContants.Error.KEY_ERROR_GETSEARCHDATA,
                        new String[] { "",
                                "return result searchDataRowRespDTO null" });
            }
            
            xParams=searchDataRowRespDTO.getxParams();

            //单表分页完毕
            if(searchDataRowRespDTO.isInsidePagerOver()){
                
                //重新初始化查询参数
                insideDbIndex++;
                insidePageNo=0;
            }
            
            //全表扫描完毕
            if(searchDataRowRespDTO.isInsideDbIndexOver()){
                break;
            }
            
        }
        
    }
    
    
}
