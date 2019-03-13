package com.zengshi.ecp.busi.search.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.busi.search.SearchUtil;
import com.zengshi.ecp.busi.search.vo.CategoryTreeVO;
import com.zengshi.ecp.busi.search.vo.CategoryVO;
import com.zengshi.ecp.busi.search.vo.GoodSearchPageReqVO;
import com.zengshi.ecp.busi.search.vo.ScrollResult;
import com.zengshi.ecp.cms.dubbo.dto.CmsHotSearchReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsHotSearchRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsHotSearchRSV;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalogReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalogRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PropRelationRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatalogRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.search.dubbo.search.SearchFacade;
import com.zengshi.ecp.search.dubbo.search.SearchParam;
import com.zengshi.ecp.search.dubbo.search.result.CollationReuslt;
import com.zengshi.ecp.search.dubbo.search.result.SearchResult;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import com.zengshi.ecp.server.front.dto.BaseStaff;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SiteLocaleUtil;
import com.zengshi.ecp.server.front.util.StaffLocaleUtil;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopCacheRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by HDF on 2016/6/28.
 */
@Controller
@RequestMapping(value = "/search")
public class SearchController extends EcpBaseController {
	protected String MODULE = getClass().getName();
    private static final String GDS_COLLECTION_NAME = "gdscollection";
    private static final String SHOP_COLLECTION_NAME = "shopcollection";
    private static final String GDS_CATEGORY_BOOK = "2700";
    private static final String GDS_CATEGORY_NUM = "1199";
    /**
     * 分类树目录ID前缀.
     */
    public static final String CATLOG_ID_PREFIX = "CATLOG-";

    /**
     * 分类树店铺ID前缀。
     */
    public static final String SHOP_ID_PREFIX = "SHOP-";
    @Resource
    private ICmsHotSearchRSV cmsHotSearchRSV;
    
    @Resource(name = "gdsCategoryRSV")
    private IGdsCategoryRSV gdsCategoryRSV;
    
    @Resource(name = "shopCacheRSV")
    private IShopCacheRSV shopCacheRSV;
    
    @Resource(name = "gdsCatalogRSV")
    private IGdsCatalogRSV gdsCatalogRSV;
    
    @RequestMapping()
    public String search(Model model,GoodSearchPageReqVO vo) throws BusinessException {
        //所有翻页由scroll发起
        model.addAttribute("vo", vo);
        model.addAttribute("searchType", "1");
        model.addAttribute("keyword",vo.getKeyword());
        
        if(StringUtil.isNotBlank(vo.getCategory())){
        	GdsCategoryReqDTO reqDTO=new GdsCategoryReqDTO();
        	reqDTO.setCatgCode(vo.getCategory());
        	//搜索页面功能 需要传入用户
        	BaseStaff staff = StaffLocaleUtil.getStaff();
        	reqDTO.setStaff(staff);
        	GdsCategoryRespDTO categoryRespDTO= gdsCategoryRSV.queryGdsCategoryByPK(reqDTO);
        	model.addAttribute("catgName",categoryRespDTO.getCatgName());
        }
        
        /*List<GdsPropRespDTO> gdsPropRespDTOList = gdsCatg2PropRelationRespDTO.getSearchProps();
        if (CollectionUtils.isNotEmpty(gdsPropRespDTOList)) {
            extraRespVO.setPropList(gdsPropRespDTOList);
        }*/
        return "/search/search-result";

        /*// 初始分页长度设置
        vo.setPageSize(SearchUtil.DEFAULT_PAGESIZE);

        SearchResult<Map<String,Object>> result = SearchUtil.searchGood(vo);
        model.addAttribute("vo", vo);
        if (result.isSuccess()) {

            EcpBasePageRespVO<Map<String,Object>> pageRespVO = SearchUtil.renderRespVO(vo, result);
            super.addPageToModel(model, pageRespVO);
            model.addAttribute("keyword",vo.getKeyword());
            return "/search/search-result";
        }

        throw new BusinessException(result.getMessage());*/

    }

    @RequestMapping(value = "/hotkeyword")
    public String hotkeyword(Model model) throws BusinessException {
       List<String> hotkeywords = new ArrayList<String>();

        CmsHotSearchReqDTO cmsHotSearchReqDTO = new CmsHotSearchReqDTO();
        cmsHotSearchReqDTO.setPageNo(1);
        cmsHotSearchReqDTO.setPageSize(18);
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
    public List<String> hotkeyword(String pageNo,int size) throws BusinessException {
        List<String> hotkeywords = new ArrayList<String>();
        int pn=2;

        if(StringUtils.isNotBlank(pageNo)){
            pn=Integer.parseInt(pageNo)+1;
        }
        CmsHotSearchReqDTO cmsHotSearchReqDTO = new CmsHotSearchReqDTO();
        cmsHotSearchReqDTO.setPageNo(1);
        cmsHotSearchReqDTO.setPageSize(size);
        cmsHotSearchReqDTO.setSiteId(SiteLocaleUtil.getSite());
        cmsHotSearchReqDTO.setStatus(SearchConstants.STATUS_1);
        PageResponseDTO<CmsHotSearchRespDTO> pageResp = this.cmsHotSearchRSV
                .queryCmsHotSearchPage(cmsHotSearchReqDTO);
        if (CollectionUtils.isNotEmpty(pageResp.getResult())) {
            for (CmsHotSearchRespDTO cmsHotSearchRespDTO : pageResp.getResult()) {
                hotkeywords.add(cmsHotSearchRespDTO.getHotSearchName());
            }
        }
        Collections.shuffle(hotkeywords);
        return hotkeywords;
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
            scrollResult.setShopId(vo.getShopId());
            scrollResult.setCategory(vo.getCategory());
            scrollResult.setAdid(vo.getAdid());
            return scrollResult;
        }

        throw new BusinessException(result.getMessage());
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
        result = SearchFacade.suggest2(searchParam,null);

        if (result.isSuccess()) {
            return result.getResultList();
        }

        throw new BusinessException(result.getMessage());

    }

    /**
     * 
     * 返回跟节点
     * 
     * @author liyong7
     * @param model
     * @param reqVO
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @RequestMapping("/asyncData/getRootNodes")
    @ResponseBody
    public String getRootNodes(Model model, CategoryVO reqVO, HttpServletResponse response)
            throws Exception {
    	List<CategoryTreeVO> trees = new ArrayList<CategoryTreeVO>();
    	GdsCategoryReqDTO reqDTO = new GdsCategoryReqDTO();
    	BaseSysCfgRespDTO sysCfg = new BaseSysCfgRespDTO();
    	try{
    		sysCfg = SysCfgUtil.fetchSysCfg("MOBILE_SEARCH_BEGIN_CATEGORY");
    	}catch(Exception e){
    		LogUtil.error(MODULE, "根据系统参数编码：MOBILE_SEARCH_BEGIN_CATEGORY 从数据库中获取到的信息为空");
    	}
    	String categoryIds = sysCfg.getParaValue();
    	String ids[] = categoryIds.split(",");
    	List<String> catgParents = new ArrayList<String>();
    	for(String id : ids){
    		catgParents.add(id);
    	}
    	reqDTO.setCatgParents(catgParents);
    	reqDTO.setPageSize(Integer.MAX_VALUE);
    	reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
    	reqDTO.setOrderBy("CATG_LEVEL DESC,CATG_CODE");
    	PageResponseDTO<GdsCategoryRespDTO> rootDto = gdsCategoryRSV.queryGdsCategoryPaging(reqDTO);
    	List<GdsCategoryRespDTO> gdsCategoryList = rootDto.getResult();
    	if(CollectionUtils.isNotEmpty(gdsCategoryList)){
    		convert2TreeList(trees, gdsCategoryList,reqDTO);
    		return JSONObject.toJSONString(trees);
    	}else{
    		return "";
    	}
    }
    /**
     * 
     * 分类树异步获取数据。
     * 
     * @author liyong7
     * @param model
     * @param reqVO
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @RequestMapping("/asyncData/getNodes")
    @ResponseBody
    public String getNodes(Model model, CategoryVO reqVO, HttpServletResponse response)
            throws Exception {
        LogUtil.info(MODULE, "获取分类树型节点,参数" + ToStringBuilder.reflectionToString(reqVO));
        List<CategoryTreeVO> trees = new ArrayList<CategoryTreeVO>();
        // 判断是否有传分类类型参数
        if (StringUtil.isBlank(reqVO.getCatgType())) {
            LogUtil.warn(MODULE, "catgType为空,返回空数据!");
            return JSONObject.toJSONString(trees);
        }
        GdsCategoryReqDTO reqDTO = new GdsCategoryReqDTO();
        ObjectCopyUtil.copyObjValue(reqVO, reqDTO, null, true);
        String id = reqVO.getId();

        // 节点ID与父节点ID同为空，为目录树加载（即分类树初始化显示顶级目录）
        if (StringUtil.isBlank(reqVO.getId())) {
            switch (reqVO.getCatgType()) {
            // 平台分类根节点为目录信息,添加目录树节点数据。
            case GdsConstants.GdsCategory.CATG_TYPE_1:
                List<GdsCatalogRespDTO> catlogs = loadCatalogRespDTO(reqVO);
                if (CollectionUtils.isNotEmpty(catlogs)) {
                    for (GdsCatalogRespDTO catlogRespDTO : catlogs) {
                        CategoryTreeVO vo = buildCatalogNodeItem(catlogRespDTO, null);
                        trees.add(vo);
                    }
                }
                break;
            // 店铺分类根节点为目录信息。
            case GdsConstants.GdsCategory.CATG_TYPE_2:
                // 获取店铺信息.
                Map<Long, ShopInfoResDTO> map = shopCacheRSV.getCacheShop();
                if (MapUtils.isNotEmpty(map)) {
                    Set<Entry<Long, ShopInfoResDTO>> entrySet = map.entrySet();
                    for (Iterator<Entry<Long, ShopInfoResDTO>> iterator = entrySet.iterator(); iterator
                            .hasNext();) {
                        Entry<Long, ShopInfoResDTO> entry = (Entry<Long, ShopInfoResDTO>) iterator
                                .next();
                        ShopInfoResDTO dto = entry.getValue();
                        if (null != dto && GdsUtils.isEqualsValid(dto.getShopStatus())) {
                            CategoryTreeVO vo = buildShopNodeItem(dto, null);
                            trees.add(vo);
                        }
                    }
                }
                break;
            default:
                break;
            }
        } else {
            // 目录ID不为空,查询分类信息.
            List<GdsCategoryRespDTO> catgLst = null;

            if (id.startsWith(CATLOG_ID_PREFIX)) {
                id = id.replace(CATLOG_ID_PREFIX, "");
                reqDTO.setCatlogId(Long.valueOf(id));
                // 查询根分类。
                catgLst = gdsCategoryRSV.queryRootCategory(reqDTO);

            } else if (id.startsWith(SHOP_ID_PREFIX)) {
                id = id.replace(SHOP_ID_PREFIX, "");
                reqDTO.setShopId(Long.valueOf(id));
                // 查询根分类。
                catgLst = gdsCategoryRSV.queryRootCategory(reqDTO);
            } else {
                // 否则加载子分类。
                reqDTO.setCatgParent(id);
                catgLst = gdsCategoryRSV.querySubCategory(reqDTO);
            }
            convert2TreeList(trees, catgLst,reqDTO);
        }
        String json = JSONObject.toJSONString(trees);
        //JSONObject.parse(JSONObject.toJSONString(trees));
        LogUtil.info(MODULE, String.format("返回JSON数据：%s", json));
        return json;
    }
    
    private CategoryTreeVO buildShopNodeItem(ShopInfoResDTO dto, Boolean isOpen) {
        CategoryTreeVO vo = new CategoryTreeVO();
        vo.setId(SHOP_ID_PREFIX + dto.getId());
        vo.setName(dto.getShopName());
        vo.setpId(null);
        vo.setCatgLevel(null);
        vo.setIsParent(true);
        vo.setIsRoot(true);
        vo.setClick(false);
        vo.setOpen(isOpen);
        return vo;
    }
    
    private List<GdsCatalogRespDTO> loadCatalogRespDTO(CategoryVO reqVO) {
        GdsCatalogReqDTO reqDTO = new GdsCatalogReqDTO();
        reqDTO.setPageSize(Integer.MAX_VALUE);
        reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
        reqDTO.setId(reqVO.getCatlogId());
        PageResponseDTO<GdsCatalogRespDTO> page = gdsCatalogRSV
                .queryGdsCatalogRespDTOPaging(reqDTO);
        return page.getResult();
    }
    
    private CategoryTreeVO buildCatalogNodeItem(GdsCatalogRespDTO catlogRespDTO, Boolean isOpen) {
        CategoryTreeVO vo = new CategoryTreeVO();
        vo.setId(CATLOG_ID_PREFIX + catlogRespDTO.getId());
        vo.setName(catlogRespDTO.getCatlogName());
        vo.setpId(null);
        vo.setCatgLevel(null);
        vo.setIsParent(true);
        if (null != catlogRespDTO.getId()) {
            vo.setCatlogId(catlogRespDTO.getId().toString());
        }
        vo.setIsRoot(true);
        vo.setClick(false);
        vo.setOpen(isOpen);
        return vo;
    }
    
    private void convert2TreeList(List<CategoryTreeVO> trees, List<GdsCategoryRespDTO> catgLst,
            GdsCategoryReqDTO categoryReqDTO) {
        if (CollectionUtils.isNotEmpty(catgLst)) {
            for (GdsCategoryRespDTO respDTO : catgLst) {
                CategoryTreeVO vo = new CategoryTreeVO();
                vo.setId(respDTO.getCatgCode());
                vo.setName(respDTO.getCatgName());
                vo.setIsRoot(false);
                if (null != respDTO.getCatlogId()) {
                    vo.setCatlogId(respDTO.getCatlogId().toString());
                    if (StringUtil.isBlank(respDTO.getCatgParent())) {
                        vo.setpId(CATLOG_ID_PREFIX + respDTO.getCatlogId());
                    } else {
                        vo.setpId(respDTO.getCatgParent());
                    }
                } else if (null != respDTO.getShopId()) {
                    vo.setShopId(respDTO.getShopId().toString());
                    if (StringUtil.isBlank(respDTO.getCatgParent())) {
                        vo.setpId(SHOP_ID_PREFIX + respDTO.getShopId());
                    } else {
                        vo.setpId(respDTO.getCatgParent());
                    }
                }
                vo.setCatgLevel(respDTO.getCatgLevel());
                //如果指定显示最大节点
                if (categoryReqDTO.getMaxShowNode() != null || !"".equals(categoryReqDTO.getMaxShowNode())) {
                    if (respDTO.getCatgLevel().toString().equals(categoryReqDTO.getMaxShowNode())) {
                        vo.setIsParent(false);
                    }else{
                        vo.setIsParent(GdsConstants.GdsCategory.IF_LEAF_0.equals(respDTO.getIfLeaf()) ? true
                                : false);
                    }
                } else {
                    vo.setIsParent(GdsConstants.GdsCategory.IF_LEAF_0.equals(respDTO.getIfLeaf()) ? true
                            : false);
                }
                /*
                 * if(StringUtil.isNotBlank(respDTO.getMediaUuid())){
                 * vo.setIcon(new AiToolUtil().genImageUrl(respDTO.getMediaUuid(), "18x18")); }
                 */
                trees.add(vo);
            }
        }
    }
    
    //动态添加搜索条件
    @RequestMapping("/getSearchCon")
    @ResponseBody
    public String getSearchCon(Model model, CategoryVO reqVO, HttpServletResponse response)
            throws Exception {
    	List<GdsPropRespDTO> gdsPropRespDTOList = new ArrayList<GdsPropRespDTO>();
    	GdsCatg2PropReqDTO reqDTO = new GdsCatg2PropReqDTO();
        reqDTO.setCatgCode(reqVO.getCatgCode());
        GdsCatg2PropRelationRespDTO gdsCatg2PropRelationRespDTO = gdsCategoryRSV
                .queryCategoryPropsByCondition(reqDTO);
        if (gdsCatg2PropRelationRespDTO != null) {
            gdsPropRespDTOList = gdsCatg2PropRelationRespDTO.getSearchProps();
        }
        
    	if(CollectionUtils.isNotEmpty(gdsPropRespDTOList)){
    		return JSONObject.toJSONString(gdsPropRespDTOList);
    	}else{
    		return "";
    	}
    }
}
