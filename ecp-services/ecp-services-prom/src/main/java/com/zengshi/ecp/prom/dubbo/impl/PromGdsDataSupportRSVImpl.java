package com.zengshi.ecp.prom.dubbo.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.general.solr.dto.SearchDataDupPagerPageReqDTO;
import com.zengshi.ecp.general.solr.dto.SearchDataDupPagerPageRespDTO;
import com.zengshi.ecp.general.solr.dto.SearchDataDupPagerRowPageReqDTO;
import com.zengshi.ecp.general.solr.dto.SearchDataDupPagerRowPageRespDTO;
import com.zengshi.ecp.general.solr.dto.SearchDataDupPagerRowRowReqDTO;
import com.zengshi.ecp.general.solr.dto.SearchDataDupPagerRowRowRespDTO;
import com.zengshi.ecp.general.solr.interfaces.ISearchDataDupPagerSupport;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalog2SiteReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalogRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.category.GdsCategoryCompareRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatalog2SiteRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.prom.dubbo.dto.PromGdsRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoResponseDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuLimitDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuRespDTO;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.dubbo.util.PromUtil;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromInfoSV;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromQuerySV;
import com.zengshi.ecp.prom.service.busi.sku.impl.GdsCatgSVImpl;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2016-3-2 <br>
 * Copyright (c) 2016 AI <br>
 * 
 * @author huangjx
 */
public class PromGdsDataSupportRSVImpl implements ISearchDataDupPagerSupport{

    @Resource
    private IPromInfoSV promInfoSV;

    @Resource
    private IPromQuerySV promQuerySV;

    @Resource
    private IGdsSkuInfoQueryRSV gdsSkuInfoQueryRSV;
    
    @Resource
    private IGdsCategoryRSV gdsCategoryRSV;
    
    @Resource
    private IGdsCatalog2SiteRSV gdsCatalog2SiteRSV;
    
    @Resource
    private GdsCatgSVImpl  gdsCatgSV;
    
    @Override
    public SearchDataDupPagerPageRespDTO getDataPage(SearchDataDupPagerPageReqDTO searchDataPageReqDTO) {
        
        SearchDataDupPagerPageRespDTO dataPageRespDTO = new SearchDataDupPagerPageRespDTO();
         // 返回初始化
        dataPageRespDTO.setDbIndexOver(false);
        dataPageRespDTO.setPagerOver(false);
        
        dataPageRespDTO.setInsideDbIndexOver(false);
        dataPageRespDTO.setInsidePagerOver(false);
        //判断是否超出库
        BaseSysCfgRespDTO sysCfg = SysCfgUtil.fetchSysCfg("SYS_TABLE_INDEX_COUNT");
        int cnt = Integer.parseInt(sysCfg.getParaValue());
        
        if(cnt<searchDataPageReqDTO.getDbIndex()){
            dataPageRespDTO.setDbIndexOver(true);
            dataPageRespDTO.setInsidePagerOver(true);
            dataPageRespDTO.setInsideDbIndexOver(true);
            return dataPageRespDTO;
        }
        
        // 取有效的促销列表 通过分页值
        PromInfoDTO promInfoDTO=new PromInfoDTO();
        promInfoDTO.setStatus(PromConstants.PromInfo.STATUS_10);
        promInfoDTO.setPageNo(searchDataPageReqDTO.getPageNo());
        promInfoDTO.setPageSize(searchDataPageReqDTO.getPageSize());
        promInfoDTO.setTableIndex(searchDataPageReqDTO.getDbIndex());
        
        //promInfoDTO.setId(3424l);
        //分库 分页查询促销数据
        PageResponseDTO<PromInfoResponseDTO> page= promInfoSV.queryPromInfoForPage(promInfoDTO, "");
        
        if(page==null || page.getPageCount()<=0){
            dataPageRespDTO.setPagerOver(true);
            dataPageRespDTO.setInsidePagerOver(true);
            dataPageRespDTO.setDbIndexOver(true);
            dataPageRespDTO.setInsideDbIndexOver(true);
            return dataPageRespDTO;
        }else{
           if(CollectionUtils.isEmpty(page.getResult())){
               dataPageRespDTO.setPagerOver(true);
               dataPageRespDTO.setInsidePagerOver(true);
               dataPageRespDTO.setDbIndexOver(true);
               dataPageRespDTO.setInsideDbIndexOver(true);
               return dataPageRespDTO;
           }
           for (PromInfoResponseDTO dto: page.getResult()){
               SearchDataDupPagerRowPageReqDTO searchDataRowReqDTO=new SearchDataDupPagerRowPageReqDTO();
               searchDataRowReqDTO.setId(dto.getId().toString());
               searchDataRowReqDTO.setInsideDbIndex(searchDataPageReqDTO.getInsideDbIndex());
               searchDataRowReqDTO.setInsidePageNo(searchDataPageReqDTO.getInsidePageNo());
               searchDataRowReqDTO.setInsidePageSize(searchDataPageReqDTO.getInsidePageSize());
               searchDataRowReqDTO.setPageNo(searchDataPageReqDTO.getPageNo());
               searchDataRowReqDTO.setPageSize(searchDataPageReqDTO.getPageSize());
               
               SearchDataDupPagerRowPageRespDTO respRow=this.getDataRowPage(searchDataRowReqDTO);
               dataPageRespDTO.setDataList(respRow.getDataList());
               dataPageRespDTO.setInsidePagerOver(respRow.isInsidePagerOver());
               dataPageRespDTO.setInsideDbIndexOver(respRow.isInsideDbIndexOver());
           }
        }
        return dataPageRespDTO;
    }
    
    @Override
    public SearchDataDupPagerRowPageRespDTO getDataRowPage(SearchDataDupPagerRowPageReqDTO searchDataRowReqDTO) {

        SearchDataDupPagerRowPageRespDTO dataRowRespDTO = new SearchDataDupPagerRowPageRespDTO();
        // 返回初始化
        dataRowRespDTO.setInsideDbIndexOver(false);
        dataRowRespDTO.setInsidePagerOver(false);
        
        BaseSysCfgRespDTO sysCfg = SysCfgUtil.fetchSysCfg("SYS_TABLE_INDEX_COUNT");
        int cnt = Integer.parseInt(sysCfg.getParaValue());
        
        if(cnt<searchDataRowReqDTO.getInsideDbIndex()){
            dataRowRespDTO.setInsideDbIndexOver(true);
            dataRowRespDTO.setInsidePagerOver(true);
            return dataRowRespDTO;
        }

        /*
         * 1、取一个促销id 1.1促销id全场参与 1.2促销id 部分参与 1.2.1 部分分类参与 1.2.2 部分单品参与  1.2.3 分类+单品同时参与
         * 
         * 2、根据促销id 和分页信息 获得分页数据 分页每条数据 过滤是否是有效数据 2.1 黑名单分类 2.2 黑名单单品 2.3黑名单分类+黑名单单品
         * 
         * 3、内部分页处理完成 直接return 数据
         */
        
        PromInfoDTO promInfoDTO = promInfoSV.queryPromInfoDTOById(Long.valueOf(searchDataRowReqDTO.getId()));
        // 没有找到促销信息
        if (promInfoDTO == null) {
            dataRowRespDTO.setInsidePagerOver(true);
            dataRowRespDTO.setInsideDbIndexOver(true);
            return dataRowRespDTO;
        }
        if (promInfoDTO.getId() == null) {
            dataRowRespDTO.setInsidePagerOver(true);
            dataRowRespDTO.setInsideDbIndexOver(true);
            return dataRowRespDTO;
        }
        //通过站点获得catglog
        GdsCatalog2SiteReqDTO reqDTO=new GdsCatalog2SiteReqDTO();
        reqDTO.setSiteId(promInfoDTO.getSiteId());
        reqDTO.setCatalogStatus(GdsConstants.Commons.STATUS_VALID);
        List<GdsCatalogRespDTO> l= gdsCatalog2SiteRSV.queryRelationBySiteId(reqDTO);
        
        List<Long> catalogIds=new ArrayList<Long>();
        
        if(!CollectionUtils.isEmpty(l)){
              for(GdsCatalogRespDTO dto:l){
                  catalogIds.add(dto.getId());
              }
        }
        
        // 返回数据容器
        Map returnMap = new HashMap();

        // 分类黑名单
        Map catgBlackMap = new HashMap();
        List catgBlackList=new ArrayList<String>();

        // 单品黑名单
        Map skuBlackMap = new HashMap();

        if (!(StringUtil.isEmpty(promInfoDTO.getIfBlacklist()) || PromConstants.PromInfo.IF_BLACKLIST_0
                .equals(promInfoDTO.getIfBlacklist()))) {

            // 取黑名单数据
            PromSkuLimitDTO promSkuLimitDTO = new PromSkuLimitDTO();
            promSkuLimitDTO.setPromId(promInfoDTO.getId());
            List<PromSkuLimitDTO> limitList = promQuerySV.listPromotionSkuLimit(promSkuLimitDTO);

            if (!CollectionUtils.isEmpty(limitList)) {
                for (PromSkuLimitDTO dto : limitList) {
                    // 分类黑名单数据
                    if (PromConstants.PromSku.JOIN_TYPE_1.equals(dto.getJoinType())) {
                        catgBlackList.add(dto.getCatgCode());
                    }
                    // /单品黑名单数据
                    if (PromConstants.PromSku.JOIN_TYPE_2.equals(dto.getJoinType())) {
                        skuBlackMap.put(dto.getSkuId(), dto.getSkuId());
                    }
                }
                if(!CollectionUtils.isEmpty(catgBlackList)){
                    //查找所有子分类信息
                    catgBlackMap.clear();
                    catgBlackMap=this.querySubCatg(catgBlackList);
                }
            }
        }

        
        // 默认全部为待上架 和 已上架
        List<String> gdsStList = new ArrayList<String>();
        gdsStList.add(GdsConstants.GdsInfo.GDS_STATUS_WAITSHELVES);
        gdsStList.add(GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES);

        if (PromConstants.PromInfo.JOIN_RANGE_1.equals(promInfoDTO.getJoinRange())) {

            // 如果全场参与 调用商品模块接口
            dataRowRespDTO.setDataList(this.searchAllData(searchDataRowReqDTO, dataRowRespDTO,
                    promInfoDTO, catgBlackMap, skuBlackMap,catalogIds));
            return dataRowRespDTO;

        } else {
            // 如果部分参与
            PromSkuDTO promSkuDTO = new PromSkuDTO();
            // 1取参与的分类
            promSkuDTO.setJoinType(PromConstants.PromSku.JOIN_TYPE_1);
            promSkuDTO.setPromId(promInfoDTO.getId());
            promSkuDTO.setIfValid(PromConstants.PromSku.IF_VALID_1);

            List<PromSkuDTO> catgList = promQuerySV.listPromotionSku(promSkuDTO);
            
            PromSkuDTO promSkuDTO1 = new PromSkuDTO();
            promSkuDTO1.setJoinType(PromConstants.PromSku.JOIN_TYPE_2);
            promSkuDTO1.setPromId(promInfoDTO.getId());
            promSkuDTO1.setIfValid(PromConstants.PromSku.IF_VALID_1);
            PageResponseDTO<PromSkuRespDTO>  page=promQuerySV.pagePromotionSku(promSkuDTO1);
            
            //中间变量无分类和单品信息
            String ifConditons="0";
            List<String> catgCodes = new ArrayList<String>();
          
            if (!CollectionUtils.isEmpty(catgList)) {
                for (PromSkuDTO dto : catgList) {
                    catgCodes.add(dto.getCatgCode());
                }
                //查找所有子分类信息
                Map<String,String> map=this.querySubCatg(catgCodes);
                if(!CollectionUtils.isEmpty(map)){
                    catgCodes.clear();
                    catgCodes= new ArrayList<String>(map.keySet());
                }
            }
            if (!CollectionUtils.isEmpty(catgCodes) && page!=null && page.getCount()>0) {
                //有分类信息 和单品信息
                ifConditons="3";
            }else{
                if(page!=null && page.getCount()>0){
                    //有单品信息
                    ifConditons="2";
                }
                if(!CollectionUtils.isEmpty(catgCodes)){
                    //有分类信息
                    ifConditons="1";
                }
            }
            
            switch (ifConditons)
            {
                     //无数据配置
                case "0":
                     //没有任何数据 直接结束
                     dataRowRespDTO.setInsidePagerOver(true);
                     break;
                     //有分类
               case "1":
                    dataRowRespDTO.setDataList(this.searchCatgSkuData(searchDataRowReqDTO,
                           dataRowRespDTO, promInfoDTO, catgBlackMap, skuBlackMap, catgCodes,catalogIds));
                    break;
                    //有单品
               case "2":
                   // 2取参与的单品
                   //暂时这么处理 因为底下只有一个库 否则会有重复数据
                   if(searchDataRowReqDTO.getInsideDbIndex()>1){
                       dataRowRespDTO.setInsidePagerOver(true);
                   }else{
                       dataRowRespDTO.setDataList(this.searchSkuData(searchDataRowReqDTO, dataRowRespDTO, promInfoDTO, catgBlackMap, skuBlackMap));
                   }
                    break; 
                    //既有分类 又有单品
               case "3":
                        dataRowRespDTO.setDataList(this.searchCatgSkuData(searchDataRowReqDTO,
                           dataRowRespDTO, promInfoDTO, catgBlackMap, skuBlackMap, catgCodes,catalogIds));
                        //暂时这么处理 因为底下只有一个库 否则会有重复数据
                        if(searchDataRowReqDTO.getInsideDbIndex()>1){
                            dataRowRespDTO.setInsidePagerOver(true);
                        }else{
                            //分类数据取值结束
                            if(dataRowRespDTO.isInsidePagerOver()){
                                dataRowRespDTO.setInsidePagerOver(false);
                               //需要取单品数据
                               dataRowRespDTO.setDataList(this.searchSkuData(searchDataRowReqDTO, dataRowRespDTO, promInfoDTO, catgBlackMap, skuBlackMap));
                            }
                        }
                    break;
               default :
            }
        }
        return dataRowRespDTO;
    }
    
    /**
     * TODO
     * @see com.zengshi.ecp.general.solr.interfaces.ISearchDataDupPagerSupport#getDataRow(com.zengshi.ecp.general.solr.dto.SearchDataDupPagerRowRowReqDTO)
     * @param rowReqDTO
     * @return
     * @author huangjx
     */
    @Override
    public SearchDataDupPagerRowRowRespDTO getDataRow(SearchDataDupPagerRowRowReqDTO rowReqDTO) {
        
        SearchDataDupPagerRowRowRespDTO searchDataDupPagerRowRowRespDTO=new SearchDataDupPagerRowRowRespDTO();
        
        PromInfoDTO promInfoDTO = promInfoSV.queryPromInfoDTOById(Long.valueOf(rowReqDTO.getId()));
        
        if(promInfoDTO==null){
            return searchDataDupPagerRowRowRespDTO;
        }
        
        PromGdsRespDTO respDTO = new PromGdsRespDTO();
        respDTO.setCatgList(null);
        respDTO.setEndTime(promInfoDTO.getEndTime());
        respDTO.setKeyWord(promInfoDTO.getKeyWord());
        respDTO.setPromId(promInfoDTO.getId());
        respDTO.setPromTheme(promInfoDTO.getPromTheme());
        respDTO.setPromTypeCode(promInfoDTO.getPromTypeCode());
        respDTO.setShopId(promInfoDTO.getShopId());
        respDTO.setSiteId(promInfoDTO.getSiteId());
        respDTO.setStartTime(promInfoDTO.getStartTime());
        
        GdsSkuInfoReqDTO reqdto=new GdsSkuInfoReqDTO();
        reqdto.setId(Long.valueOf(rowReqDTO.getInsideId()));
        GdsSkuInfoRespDTO  skuInfoRespDTO=new GdsSkuInfoRespDTO();
        skuInfoRespDTO=gdsSkuInfoQueryRSV.querySkuInfoByOptions(reqdto);
        if(skuInfoRespDTO!=null){
            respDTO.setStatus(skuInfoRespDTO.getGdsStatus());
            respDTO.setGdsId(skuInfoRespDTO.getGdsId());
            respDTO.setGdsName(skuInfoRespDTO.getGdsName());
            respDTO.setIsbn(skuInfoRespDTO.getIsbn());
            respDTO.setSkuId(skuInfoRespDTO.getId());
            respDTO.setSkuName(skuInfoRespDTO.getGdsName());
            String[] cs=GdsUtils.getCatgs(skuInfoRespDTO.getPlatCatgs());
            if(cs!=null){
                respDTO.setCatgList( Arrays.asList(cs));
            }
        }
        searchDataDupPagerRowRowRespDTO.setData(PromUtil.ConvertObjToMap(respDTO, PromGdsRespDTO.class));
        return searchDataDupPagerRowRowRespDTO;
    }

    /**
     * 全部单品查询
     * @param searchDataRowReqDTO
     * @param dataRowRespDTO
     * @param promInfoDTO
     * @param catgBlackMap
     * @param skuBlackMap
     * @return
     * @author huangjx
     */
    private List<Map<String, Object>> searchAllData(SearchDataDupPagerRowPageReqDTO searchDataRowReqDTO,
            SearchDataDupPagerRowPageRespDTO dataRowRespDTO, PromInfoDTO promInfoDTO, Map catgBlackMap,
            Map skuBlackMap,List catalogIds) {

        List<Map<String, Object>> l = new ArrayList<Map<String, Object>>();
        // 默认全部为待上架 和 已上架
        List<String> gdsStList = new ArrayList<String>();
        gdsStList.add(GdsConstants.GdsInfo.GDS_STATUS_WAITSHELVES);
        gdsStList.add(GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES);

        // 单品查询条件
        GdsSkuInfoReqDTO gdsSkuInfoReqDTO = new GdsSkuInfoReqDTO();
        gdsSkuInfoReqDTO.setTableIndex(searchDataRowReqDTO.getInsideDbIndex());
        gdsSkuInfoReqDTO.setGdsStatusArr(gdsStList);
        gdsSkuInfoReqDTO.setPageSize(searchDataRowReqDTO.getInsidePageSize());
        gdsSkuInfoReqDTO.setPageNo(searchDataRowReqDTO.getInsidePageNo());
        gdsSkuInfoReqDTO.setShopId(promInfoDTO.getShopId());
        gdsSkuInfoReqDTO.setCatalogIds(catalogIds);
        
        PageResponseDTO<GdsSkuInfoRespDTO> page = gdsSkuInfoQueryRSV
                .querySkuInfoListPageALLDB(gdsSkuInfoReqDTO);

        if (searchDataRowReqDTO.getInsidePageNo() > page.getPageCount()) {
            // 单品库数据处理完成
            dataRowRespDTO.setInsidePagerOver(true);
            return null;
        }
        if (page.getPageCount() > 0) {
            for (GdsSkuInfoRespDTO dto : page.getResult()) {
                // 检查是否为黑名单
                if (!skuBlackMap.containsKey(dto.getId())) {
                    
                    //分类代码
                    List<String> platformCatgList=new ArrayList<String>();
                    String[] cs=GdsUtils.getCatgs(dto.getPlatCatgs());
                    if(cs!=null){
                        platformCatgList=Arrays.asList(cs);
                    }
                    
                   // if (!catgBlackMap.containsKey(dto.getMainCatgs())) {
                    if  (!this.ifBlackCatg(platformCatgList,catgBlackMap)){
                        // 组织参数
                        PromGdsRespDTO respDTO = new PromGdsRespDTO();
                        respDTO.setCatgList(platformCatgList);
                        respDTO.setEndTime(promInfoDTO.getEndTime());
                        respDTO.setGdsId(dto.getGdsId());
                        respDTO.setGdsName(dto.getGdsName());
                        respDTO.setIsbn(dto.getIsbn());
                        respDTO.setKeyWord(promInfoDTO.getKeyWord());
                        respDTO.setPromId(promInfoDTO.getId());
                        respDTO.setPromTheme(promInfoDTO.getPromTheme());
                        respDTO.setPromTypeCode(promInfoDTO.getPromTypeCode());
                        respDTO.setShopId(promInfoDTO.getShopId());
                        respDTO.setSiteId(promInfoDTO.getSiteId());
                        respDTO.setSkuId(dto.getId());
                        respDTO.setSkuName(dto.getGdsName());
                        respDTO.setStartTime(promInfoDTO.getStartTime());
                        respDTO.setStatus(dto.getGdsStatus());
                        l.add(PromUtil.ConvertObjToMap(respDTO, PromGdsRespDTO.class));
                    }
                }

            }
        }

        return l;
    }

    /**
     * 
     * 分类查找单品
     * @param searchDataRowReqDTO
     * @param dataRowRespDTO
     * @param promInfoDTO
     * @param catgBlackMap
     * @param skuBlackMap
     * @param catalogIds
     * @return
     * @author huangjx
     */
    private List<Map<String, Object>> searchCatgSkuData(SearchDataDupPagerRowPageReqDTO searchDataRowReqDTO,
            SearchDataDupPagerRowPageRespDTO dataRowRespDTO, PromInfoDTO promInfoDTO, Map catgBlackMap,
            Map skuBlackMap, List catgCodes, List catalogIds) {

        List<Map<String, Object>> l = new ArrayList<Map<String, Object>>();
        // 默认全部为待上架 和 已上架
        List<String> gdsStList = new ArrayList<String>();
        gdsStList.add(GdsConstants.GdsInfo.GDS_STATUS_WAITSHELVES);
        gdsStList.add(GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES);

        GdsSkuInfoReqDTO gdsSkuInfoReqDTO = new GdsSkuInfoReqDTO();
        gdsSkuInfoReqDTO.setTableIndex(searchDataRowReqDTO.getInsideDbIndex());
        gdsSkuInfoReqDTO.setGdsStatusArr(gdsStList);
        gdsSkuInfoReqDTO.setCatalogIds(catalogIds);
        gdsSkuInfoReqDTO.setCatgs(catgCodes);
        
        gdsSkuInfoReqDTO.setPageSize(searchDataRowReqDTO.getInsidePageSize());
        gdsSkuInfoReqDTO.setPageNo(searchDataRowReqDTO.getInsidePageNo());
        gdsSkuInfoReqDTO.setShopId(promInfoDTO.getShopId());

        Integer pNo=0;
        PageResponseDTO<GdsSkuInfoRespDTO> page=null;
        if(dataRowRespDTO.getxParams()==null){
             page = gdsSkuInfoQueryRSV
                    .querySkuInfoListPageALLDB(gdsSkuInfoReqDTO);
            if(page!=null){
                pNo=Integer.valueOf(String.valueOf(page.getPageCount()));
            }
        }else{
            if(dataRowRespDTO.getxParams().get("pageNo")!=null){
                pNo=(Integer)dataRowRespDTO.getxParams().get("pageNo");
            }
            
        }

        if (searchDataRowReqDTO.getInsidePageNo() > pNo) {
            // 单品库数据处理完成
            dataRowRespDTO.setInsidePagerOver(true);
            Map m=new HashMap();
            m.put("pageNo", pNo);
            dataRowRespDTO.setxParams(m);
            return null;
        }

        if (pNo > 0) {
            for (GdsSkuInfoRespDTO dto : page.getResult()) {
                // 检查是否为黑名单
                if (!skuBlackMap.containsKey(dto.getId())) {
                    //分类代码
                    List<String> platformCatgList=new ArrayList<String>();
                    String[] cs=GdsUtils.getCatgs(dto.getPlatCatgs());
                    if(cs!=null){
                        platformCatgList=Arrays.asList(cs);
                    }
                    
                   // if (!catgBlackMap.containsKey(dto.getMainCatgs())) {
                    if  (!this.ifBlackCatg(platformCatgList,catgBlackMap)){
                        // 组织参数
                        PromGdsRespDTO respDTO = new PromGdsRespDTO();
                        respDTO.setCatgList(platformCatgList);
                        respDTO.setEndTime(promInfoDTO.getEndTime());
                        respDTO.setGdsId(dto.getGdsId());
                        respDTO.setGdsName(dto.getGdsName());
                        respDTO.setIsbn(dto.getIsbn());
                        respDTO.setKeyWord(promInfoDTO.getKeyWord());
                        respDTO.setPromId(promInfoDTO.getId());
                        respDTO.setPromTheme(promInfoDTO.getPromTheme());
                        respDTO.setPromTypeCode(promInfoDTO.getPromTypeCode());
                        respDTO.setShopId(dto.getShopId());
                        respDTO.setSiteId(promInfoDTO.getSiteId());
                        respDTO.setSkuId(dto.getId());
                        respDTO.setSkuName(dto.getGdsName());
                        respDTO.setStartTime(promInfoDTO.getStartTime());
                        respDTO.setStatus(dto.getGdsStatus());
                        l.add(PromUtil.ConvertObjToMap(respDTO, PromGdsRespDTO.class));
                    }
                }

            }
        }

        return l;
    }

    /**
     * 
     * 促销单品列表
     * @param searchDataRowReqDTO
     * @param dataRowRespDTO
     * @param promInfoDTO
     * @param catgBlackMap
     * @param skuBlackMap
     * @return
     * @author huangjx
     */
    private List<Map<String, Object>> searchSkuData(SearchDataDupPagerRowPageReqDTO searchDataRowReqDTO,
            SearchDataDupPagerRowPageRespDTO dataRowRespDTO,  PromInfoDTO promInfoDTO,
            Map catgBlackMap, Map skuBlackMap) {

        List<Map<String, Object>> l = new ArrayList<Map<String, Object>>();

        PromSkuDTO promSkuDTO = new PromSkuDTO();

        Map map=dataRowRespDTO.getxParams();
        Integer pNo=0;
        if(map!=null){
            pNo=(Integer)map.get("pageNo");
        }
        promSkuDTO.setJoinType(PromConstants.PromSku.JOIN_TYPE_2);
        promSkuDTO.setPromId(promInfoDTO.getId());
        promSkuDTO.setIfValid(PromConstants.PromSku.IF_VALID_1);
        promSkuDTO.setPageSize(searchDataRowReqDTO.getInsidePageSize());
        promSkuDTO.setPageNo(searchDataRowReqDTO.getInsidePageNo()-pNo);
        promSkuDTO.setTableIndex(searchDataRowReqDTO.getInsideDbIndex());

        PageResponseDTO<PromSkuRespDTO> page = promQuerySV.pagePromotionSku(promSkuDTO);
        
        if (searchDataRowReqDTO.getInsidePageNo() > (page.getPageCount()+pNo)) {
            // 单品库数据处理完成
            dataRowRespDTO.setInsidePagerOver(true);
            return null;
        }
        
        if (page.getPageCount() > 0) {
            
            if(CollectionUtils.isEmpty(page.getResult())){
                // 单品库数据处理完成
                dataRowRespDTO.setInsidePagerOver(true);
                return null;
            }
            for (PromSkuRespDTO dto : page.getResult()) {
                // 检查是否为黑名单
                if (!skuBlackMap.containsKey(dto.getSkuId())) {
                    
                    GdsSkuInfoReqDTO reqdto=new GdsSkuInfoReqDTO();
                    reqdto.setId(dto.getSkuId().longValue());
                    GdsSkuInfoRespDTO  skuInfoRespDTO=new GdsSkuInfoRespDTO();
                    skuInfoRespDTO=gdsSkuInfoQueryRSV.querySkuInfoByOptions(reqdto);
                    
                    //分类代码
                    List<String> platformCatgList=new ArrayList<String>();
                    if(skuInfoRespDTO!=null){
                        String[] cs=GdsUtils.getCatgs(skuInfoRespDTO.getPlatCatgs());
                        if(cs!=null){
                            platformCatgList= Arrays.asList(cs);
                        }
                    }
                   // if (!catgBlackMap.containsKey(dto.getMainCatgs())) {
                    if  (!this.ifBlackCatg(platformCatgList,catgBlackMap)){
                        // 组织参数
                        PromGdsRespDTO respDTO = new PromGdsRespDTO();
                        respDTO.setCatgList(platformCatgList);
                        respDTO.setEndTime(promInfoDTO.getEndTime());
                        respDTO.setGdsId(dto.getGdsId());
                        //调用 商品信息 获得isbn号
                        if(skuInfoRespDTO!=null){
                            respDTO.setIsbn(skuInfoRespDTO.getIsbn());
                            respDTO.setCatgList( platformCatgList);
                            respDTO.setGdsName(skuInfoRespDTO.getGdsName());
                            respDTO.setSkuName(skuInfoRespDTO.getGdsName());
                            respDTO.setShopId(skuInfoRespDTO.getShopId());
                            respDTO.setStatus(skuInfoRespDTO.getGdsStatus());
                        }
                        respDTO.setKeyWord(promInfoDTO.getKeyWord());
                        respDTO.setPromId(promInfoDTO.getId());
                        respDTO.setPromTheme(promInfoDTO.getPromTheme());
                        respDTO.setPromTypeCode(promInfoDTO.getPromTypeCode());
                        respDTO.setSiteId(promInfoDTO.getSiteId());
                        respDTO.setSkuId(dto.getSkuId());
                        respDTO.setStartTime(promInfoDTO.getStartTime());
                        l.add(PromUtil.ConvertObjToMap(respDTO, PromGdsRespDTO.class));
                    }
                }

            }
        }
        return l;
    }
    
    /**
     * 通过 分类 获得子分类列表
     * @param catgIds
     * @return
     * @author huangjx
     */
    private Map<String,String> querySubCatg(List<String> catgIds){
        
        //子分类MAP
        Map<String,String> catgMap=new HashMap<String,String>();
        
        if(CollectionUtils.isEmpty(catgIds)){
            return null;
        }
        GdsCategoryReqDTO reqDTO=new GdsCategoryReqDTO();
        for(String catgId:catgIds){
            catgMap.put(catgId,catgId);
            reqDTO.setCatgParent(String.valueOf(catgId));
            List<GdsCategoryRespDTO> l=gdsCategoryRSV.querySubCategoryConnectByCatgParent(reqDTO);
            if(!CollectionUtils.isEmpty(l)){
                for(GdsCategoryRespDTO dto:l){
                    catgMap.put(dto.getCatgCode(), dto.getCatgCode());
                }
            }
        }
        return catgMap;
    }
    
    /**
     * 比较分类是否属于黑名单
     * @param platformCatgList  单品对应的分类列表
     * @param catgCode          促销设置的分类代码
     * @return
     * @author huangjx
     */
    private boolean ifBlackCatg(List<String> platformCatgList,Map<String,String> catgMap){
        
        //黑名单列表为空
        if(CollectionUtils.isEmpty(catgMap)){
            return Boolean.FALSE;
        }
        if(CollectionUtils.isEmpty(platformCatgList)){
            return Boolean.FALSE;
        }
        //默认非黑名单
        boolean returnValue=Boolean.FALSE;
        
        for (Map.Entry<String, String> entry : catgMap.entrySet()) {
            Integer result = gdsCatgSV.compareCatg(platformCatgList, entry.getKey());
            if (GdsCategoryCompareRespDTO.RESULT_EQUAL.equals(result)
                    || GdsCategoryCompareRespDTO.RESULT_LESS_THAN.equals(result)) {
                //黑名单
                returnValue= Boolean.TRUE;
                break;
             }
           }
        return returnValue;
    }
}
