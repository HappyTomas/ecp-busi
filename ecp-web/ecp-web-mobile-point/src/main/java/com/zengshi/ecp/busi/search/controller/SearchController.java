package com.zengshi.ecp.busi.search.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.util.ApplicationContextUtil;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.busi.search.SearchUtil;
import com.zengshi.ecp.busi.search.vo.GoodSearchPageReqVO;
import com.zengshi.ecp.busi.search.vo.ScrollResult;
import com.zengshi.ecp.cms.dubbo.dto.CmsHotSearchReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsHotSearchRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsHotSearchRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatalog2SiteRSV;
import com.zengshi.ecp.search.dubbo.search.SearchFacade;
import com.zengshi.ecp.search.dubbo.search.SearchParam;
import com.zengshi.ecp.search.dubbo.search.result.CollationReuslt;
import com.zengshi.ecp.search.dubbo.search.result.SearchResult;
import com.zengshi.ecp.search.dubbo.search.translator.GdsTranslator;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SiteLocaleUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mobile-point <br>
 * Description: <br>
 * Date:2016年8月29日下午2:18:17 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author xiaosm3
 * @version
 * @since JDK 1.7
 */
@Controller
@RequestMapping(value = "/search")
public class SearchController extends EcpBaseController {

    private final static String EXTRA_VO_ATTR = "extra";
    private static final String GDS_COLLECTION_NAME = "gdscollection";
    private static final String SHOP_COLLECTION_NAME = "shopcollection";
    @Resource
    private ICmsHotSearchRSV cmsHotSearchRSV;
    
    @RequestMapping()
    public String search(Model model,GoodSearchPageReqVO vo) throws BusinessException {

        // 初始分页长度设置
        vo.setPageSize(SearchUtil.DEFAULT_PAGESIZE);
        SearchResult<Map<String,Object>> result = SearchUtil.searchGood(vo);
        model.addAttribute("vo", vo);
        if (result.isSuccess()) {
        	EcpBasePageRespVO<Map<String,Object>> pageRespVO = SearchUtil.renderRespVO(vo, result);
            super.addPageToModel(model, pageRespVO);
            model.addAttribute("keyword",vo.getKeyword());
            return "/search/search-result";
        }

        throw new BusinessException(result.getMessage());

    }
    
    @RequestMapping(value = "/show")
    public String show(Model model,GoodSearchPageReqVO vo) throws BusinessException {

        // 初始分页长度设置
        vo.setPageSize(SearchUtil.DEFAULT_PAGESIZE);
        SearchResult<Map<String,Object>> result = SearchUtil.searchGood(vo);
        model.addAttribute("vo", vo);
        if (result.isSuccess()) {

            EcpBasePageRespVO<Map<String,Object>> pageRespVO = SearchUtil.renderRespVO(vo, result);
            super.addPageToModel(model, pageRespVO);
            model.addAttribute("keyword",vo.getKeyword());
            return "/search/search-show";
        }

        
        
        throw new BusinessException(result.getMessage());

    }
    
    @RequestMapping(value = "/hotkeyword")
    public String hotkeyword(Model model) throws BusinessException {
       List<String> hotkeywords = new ArrayList<String>();

        CmsHotSearchReqDTO cmsHotSearchReqDTO = new CmsHotSearchReqDTO();
        cmsHotSearchReqDTO.setPageNo(1);
        cmsHotSearchReqDTO.setPageSize(10);
        cmsHotSearchReqDTO.setSiteId(SiteLocaleUtil.getSite());
        cmsHotSearchReqDTO.setStatus(SearchConstants.STATUS_1);
        PageResponseDTO<CmsHotSearchRespDTO> pageResp = this.cmsHotSearchRSV
                .queryCmsHotSearchPage(cmsHotSearchReqDTO);
        if (CollectionUtils.isNotEmpty(pageResp.getResult())) {
            for (CmsHotSearchRespDTO cmsHotSearchRespDTO : pageResp.getResult()) {
                hotkeywords.add(cmsHotSearchRespDTO.getHotSearchName());
            }
        }

        model.addAttribute("hotkeywords",hotkeywords);

        return "/search/kw-panel/kwText";
    }

    @RequestMapping(value = "/hotkeyword/exchange")
    @ResponseBody
    public List<String> hotkeyword(String pageNo) throws BusinessException {
        List<String> hotkeywords = new ArrayList<String>();

        int pn=2;

        if(StringUtils.isNotBlank(pageNo)){
            pn=Integer.parseInt(pageNo)+1;
        }

        CmsHotSearchReqDTO cmsHotSearchReqDTO = new CmsHotSearchReqDTO();
        cmsHotSearchReqDTO.setPageNo(pn);
        cmsHotSearchReqDTO.setPageSize(10);
        cmsHotSearchReqDTO.setSiteId(SiteLocaleUtil.getSite());
        cmsHotSearchReqDTO.setStatus(SearchConstants.STATUS_1);
        PageResponseDTO<CmsHotSearchRespDTO> pageResp = this.cmsHotSearchRSV
                .queryCmsHotSearchPage(cmsHotSearchReqDTO);
        if (CollectionUtils.isNotEmpty(pageResp.getResult())) {
            for (CmsHotSearchRespDTO cmsHotSearchRespDTO : pageResp.getResult()) {
                hotkeywords.add(cmsHotSearchRespDTO.getHotSearchName());
            }
        }

        return hotkeywords;
    }
  
   @RequestMapping(value = "/suggest")
   @ResponseBody
   public List<CollationReuslt> suggest(GoodSearchPageReqVO vo) throws BusinessException {

       SearchParam searchParam = new SearchParam();
       if("1".equals(vo.getSearchType())){//商品
           searchParam.setCollectionName(GDS_COLLECTION_NAME);
       }else if("2".equals(vo.getSearchType())){//店铺
           searchParam.setCollectionName(SHOP_COLLECTION_NAME);
       }else{
           searchParam.setCollectionName(GDS_COLLECTION_NAME);
       }
       searchParam.setKeyword(vo.getKeyword());
       SearchResult<CollationReuslt> result = null;

       searchParam.setCurrentSiteId(SiteLocaleUtil.getSite());
//       result = SearchFacade.suggest2(searchParam,null);
       result = SearchFacade.suggest2(searchParam,new GdsTranslator(ApplicationContextUtil.getBean("gdsCatalog2SiteRSV",IGdsCatalog2SiteRSV.class)));
       if (result.isSuccess()) {
           return result.getResultList();
       }

       throw new BusinessException(result.getMessage());

   }
   
    @RequestMapping(value = "/scroll")
    @ResponseBody
    public ScrollResult scroll(GoodSearchPageReqVO vo) throws BusinessException{

        // 初始分页长度设置
        vo.setPageSize(SearchUtil.DEFAULT_PAGESIZE);

        vo.setPageNumber(vo.getPage());

        SearchResult<Map<String,Object>> result = SearchUtil.searchGood(vo);

        if (result.isSuccess()) {
        	ScrollResult scrollResult= SearchUtil.renderRespVO(result);
        	scrollResult.setCategory(vo.getCategory());
        	scrollResult.setAdid(vo.getAdid());
            return scrollResult;
        }

        throw new BusinessException(result.getMessage());
    }
    
}
