package com.zengshi.ecp.search.test.index;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import com.zengshi.ecp.frame.context.EcpFrameContextHolder;
import com.zengshi.ecp.general.solr.dto.SearchDataPageReqDTO;
import com.zengshi.ecp.general.solr.dto.SearchDataPageRespDTO;
import com.zengshi.ecp.general.solr.interfaces.ISearchDataSupport;
import com.zengshi.ecp.search.dubbo.dto.IndexReusltRespDTO;
import com.zengshi.ecp.search.dubbo.dto.SecConfigReqDTO;
import com.zengshi.ecp.search.dubbo.interfaces.ISecConfigPlanRSV;
import com.zengshi.ecp.search.dubbo.search.util.SearchUtils;
import com.zengshi.ecp.search.dubbo.util.SearchMessageKeyContants;
import com.zengshi.ecp.search.index.IndexManager;
import com.zengshi.ecp.search.service.common.interfaces.ISecOperLogSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.paas.utils.LogUtil;

public class IndexTest extends EcpServicesTest{
    
    @Resource
    private ISecConfigPlanRSV secConfigPlanRSV;
    
    @Resource
    public ISecOperLogSV secOperLogSV;
    
    @Test
    public void testInsidePager() {
        
    }
    
    @Test
    public void testSearchData() {
        
        // 创建其它域提供的DubboService注入实例
        ISearchDataSupport dubboServiceObject = EcpFrameContextHolder.getBean("gdsSearchDataListSupportRSV",
                ISearchDataSupport.class);

        long dbCount=0l;
        Map<String,String> idCountMap=new HashMap<String,String>();
        boolean flag = true;
        int pageNo=0;
        SearchDataPageReqDTO arg0=new SearchDataPageReqDTO();
        arg0.setPageSize(50);
        arg0.setJsonParams("[{\"catalogId\":\"1\"}]");
        while (flag) {
            arg0.setPageNo(++pageNo);
            SearchDataPageRespDTO searchDataPageRespDTO=dubboServiceObject.getDataPage(arg0);
            if(CollectionUtils.isNotEmpty(searchDataPageRespDTO.getDataList())){
                dbCount+=searchDataPageRespDTO.getDataList().size();
                
                for(Map<String,Object> map:searchDataPageRespDTO.getDataList()){
                    idCountMap.put(map.get("id").toString(), map.get("id").toString());
                }
                
            }else{
                flag=false;
            }

        }
        
        System.out.println("=========================框架分页全量查询数据总量:"+dbCount);
        System.out.println("=========================商品编码不重复的数据总量:"+idCountMap.size());

    }
    
    @Test
    public void testDbdeltaMessage(){
//        DeltaUtils.sendDbDeltaMessage(EOperType.CATG, "T_GDS_INFO", "", "", 1115+"", 1+"");
    }
    
    @Test
    public void testIndex(){
        SecConfigReqDTO req=new SecConfigReqDTO();
        req.setId(11l);
        long start=System.currentTimeMillis();
        IndexReusltRespDTO indexReusltRespDTO=IndexManager.reFullImportIndex(req, true);    
        long end=System.currentTimeMillis();
        long cost=(end-start);
        LogUtil.info("", "重新索引总耗时:"+cost+"ms");
    }
    
    @Test
    public void testIndex2(){
        SecConfigReqDTO req=new SecConfigReqDTO();
        req.setId(254l);
        secConfigPlanRSV.reFullImportIndex(req, true);
        
        while(true){
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    @Test
    public void testIndex3(){
        ISearchDataSupport dubboServiceObject = null;
        try {

            // 创建其它域提供的DubboService注入实例
            dubboServiceObject = EcpFrameContextHolder.getBean("shopSearchDataListSupportRSV",
                    ISearchDataSupport.class);
//            dubboServiceObject = (ISearchDataSupport) EcpFrameContextHolder.getBean("shopSearchDataListSupportRSV");
        } catch (Exception e) {
            throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_IMPORT_FULL,
                    new String[] { "T_SHOP_INFO",
                            "动态获取dubboService实例失败：" + SearchUtils.getExceptionMessage(e) });
        }

        SearchDataPageReqDTO searchDataPageReqDTO = new SearchDataPageReqDTO();
        searchDataPageReqDTO.setPageSize(200);
        searchDataPageReqDTO.setPageNo(1);
        searchDataPageReqDTO.setDbIndex(1);
        
        SearchDataPageRespDTO searchDataPageRespDTO = null;
        for(int i=1;i<5;i++){
            try {
                searchDataPageRespDTO = dubboServiceObject.getDataPage(searchDataPageReqDTO);
            } catch (Exception e) {
                throw new BusinessException(
                        SearchMessageKeyContants.Error.KEY_ERROR_IMPORT_FULL, new String[] {
                                "T_SHOP_INFO",
                                "dubbo服务调用失败【getDataPage】："
                                        + SearchUtils.getExceptionMessage(e) });
            }

            if (searchDataPageRespDTO == null) {
                throw new BusinessException(
                        SearchMessageKeyContants.Error.KEY_ERROR_GETSEARCHDATA, new String[] {
                                "T_SHOP_INFO",
                                "return result searchDataPageRespDTO null" });
            }
            System.out.println(searchDataPageRespDTO);
        }
        
    }

}

