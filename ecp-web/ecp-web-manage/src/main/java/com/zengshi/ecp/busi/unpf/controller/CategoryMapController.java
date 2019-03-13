package com.zengshi.ecp.busi.unpf.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.goods.gdscategory.vo.CategorySyncTreeVO;
import com.zengshi.ecp.busi.goods.gdscategory.vo.CategoryTreeVO;
import com.zengshi.ecp.busi.goods.gdscategory.vo.CategoryVO;
import com.zengshi.ecp.busi.unpf.vo.UnpfCategoryTreeNodeVO;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalog2SiteReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalogReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalogRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PropRelationRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgSyncReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgSyncRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatlog2ShopReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropValueReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropValueRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.common.LongListRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatalog2SiteRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatalogRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategorySyncRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatlog2ShopRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsPropRSV;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfCatgRelaReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfCatgRelaRespDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfPropRelaReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfPropRelaRespDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfPropReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfPropRespDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfPropValueRelaReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfPropValueRelaRespDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfPropValueReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfPropValueRespDTO;
import com.zengshi.ecp.unpf.dubbo.interfaces.IUnpfCategoryRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping(value="/unpf/catemap")
public class CategoryMapController extends EcpBaseController{

    private static final String MODULE = CategoryMapController.class.getName();
    public static final String CATLOG_ID_PREFIX = "CATLOG-";

    @Resource
    private IGdsCategorySyncRSV gdsCategorySyncRSV; 
    @Resource
    private IUnpfCategoryRSV unpfCategoryRSV;
    @Resource
    private IGdsCategoryRSV gdsCategoryRSV;
    @Resource
    private IGdsPropRSV gdsPropRSV;
    
    @Resource(name = "gdsCatalogRSV")
    private IGdsCatalogRSV gdsCatalogRSV;
    
    @Resource(name = "gdsCatalog2SiteRSV")
    private IGdsCatalog2SiteRSV gdsCatalog2SiteRSV;
    
    @Resource(name="gdsCatlog2ShopRSV")
    private IGdsCatlog2ShopRSV gdsCatlog2ShopRSV;
    
    @RequestMapping(value="/index")
    public String index(Model model) {
        return "/unpf/catemap/catemap-index";
    }
    
    ////////-------------------- 分类树数据获取  ----------------------///////////
    @RequestMapping("/asyncData/category/left")
    @ResponseBody
    public String categoryleft(Model model, CategoryVO reqVO, HttpServletResponse response)
    {
        LogUtil.info(MODULE,"获取分类树型节点,参数"+ToStringBuilder.reflectionToString(reqVO));
        List<CategoryTreeVO> trees = new ArrayList<CategoryTreeVO>();
        // 判断是否有传分类类型参数
        if(StringUtil.isBlank(reqVO.getCatgType())){
            LogUtil.warn(MODULE, "catgType为空,返回空数据!");
            return JSONObject.toJSONString(trees);
        }
        GdsCategoryReqDTO reqDTO = new GdsCategoryReqDTO();
        
        // 是否需要权限验证。如果入参为FALSE或者false则表示需要进行权限验证
        boolean isNeedAuth = Boolean.FALSE.toString().equalsIgnoreCase(reqVO.getIgnoreDataAuth());
        List<Long> catlogIdsWithCurrentUser = fetchCatlogIdsByCurrentUser(reqVO.getShopIds());
        
        ObjectCopyUtil.copyObjValue(reqVO, reqDTO, "", false);
        reqDTO.setShopId(null);//取平台分类，不需要店铺Id
        String id = reqVO.getId();
        // 节点ID与父节点ID同为空，为目录树加载（即分类树初始化显示顶级目录）
        if(StringUtil.isBlank(reqVO.getId())){
            switch (reqVO.getCatgType()) {
            // 平台分类根节点为目录信息,添加目录树节点数据。
            case GdsConstants.GdsCategory.CATG_TYPE_1:
                // 不显示目录
                if(CategoryVO.SHOW_ROOT_NO == reqVO.getShowRoot()){
                    // 目录ID不为空,查询分类信息.
                    
                    List<GdsCategoryRespDTO> catgLst = null;
                    // 查询根分类。
                    // catgLst = gdsCategoryRSV.queryRootCategory(reqDTO);
                    if(null != catlogIdsWithCurrentUser){
                        reqDTO.setCatlogIds(catlogIdsWithCurrentUser);
                    }
                    
                    if(isNeedAuth){
                        catgLst = gdsCategoryRSV.queryRootCategoryWithAuth(reqDTO);
                    }else{
                        catgLst = gdsCategoryRSV.queryRootCategory(reqDTO);
                    }
                    convert2TreeList(trees, catgLst,reqDTO,reqVO);
                }else{
                    List<GdsCatalogRespDTO> catlogs = null;
                    if(null != reqVO.getCatlogId()){
                        catlogs = loadCatalogRespDTO(reqVO);
                    }else if(null != reqVO.getSiteId()){
                        GdsCatalog2SiteReqDTO c2sReqDTO =  new GdsCatalog2SiteReqDTO();
                        c2sReqDTO.setSiteId(reqVO.getSiteId());
                        c2sReqDTO.setCatalogStatus(GdsConstants.Commons.STATUS_VALID);
                        catlogs = gdsCatalog2SiteRSV.queryRelationBySiteId(c2sReqDTO);
                    }
                    if(CollectionUtils.isNotEmpty(catlogs)){
                        for(GdsCatalogRespDTO catlogRespDTO : catlogs){
                            if(null != catlogIdsWithCurrentUser && catlogIdsWithCurrentUser.contains(catlogRespDTO.getId())){
                                CategoryTreeVO vo = buildCatalogNodeItem(catlogRespDTO, null);
                                trees.add(vo);
                            }else{
                                CategoryTreeVO vo = buildCatalogNodeItem(catlogRespDTO, null);
                                trees.add(vo);
                            }
                        }
                    }
                }
                break;
 
            default:
                break;
            }
        }else{
            // 目录ID不为空,查询分类信息.
            List<GdsCategoryRespDTO> catgLst = null;
            
            if(id.startsWith(CATLOG_ID_PREFIX)){
                id = id.replace(CATLOG_ID_PREFIX, "");
                reqDTO.setCatlogId(Long.valueOf(id));
                // 查询根分类。
                if(isNeedAuth){
                  catgLst = gdsCategoryRSV.queryRootCategoryWithAuth(reqDTO);
                }else{
                    catgLst = gdsCategoryRSV.queryRootCategory(reqDTO);
                }
                
            }else{
                // 否则加载子分类。
                reqDTO.setCatgParent(id);
                if(isNeedAuth){
                   catgLst = gdsCategoryRSV.querySubCategoryWithAuth(reqDTO);
                }else{
                    catgLst = gdsCategoryRSV.querySubCategory(reqDTO);
                }
            }
            convert2TreeList(trees,catgLst,reqDTO,reqVO);
        }
        String json = JSONObject.toJSONString(trees);
        LogUtil.info(MODULE, String.format("返回JSON数据：%s", json));
        return json;
    }
    @RequestMapping("/asyncData/category/left/search")
    @ResponseBody
    public String categoryleftsearch(Model model, CategoryVO reqVO, HttpServletResponse response)
    {
        LogUtil.info(MODULE, "搜索树型节点,参数" + ToStringBuilder.reflectionToString(reqVO));

        GdsCategoryReqDTO reqDTO = new GdsCategoryReqDTO();
        ObjectCopyUtil.copyObjValue(reqVO, reqDTO, null, true);
        reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
        reqDTO.setPageSize(Integer.MAX_VALUE);
        reqDTO.setShopId(null);
        
        // 是否需要权限验证。如果入参为FALSE或者false则表示需要进行权限验证
        boolean isNeedAuth = Boolean.FALSE.toString().equalsIgnoreCase(reqVO.getIgnoreDataAuth());
        

        // PageResponseDTO<GdsCategoryRespDTO> page = gdsCategoryRSV.queryGdsCategoryPaging(reqDTO);
        PageResponseDTO<GdsCategoryRespDTO> page = null;
        List<Long> catlogIdsWithCurrentUser = fetchCatlogIdsByCurrentUser(reqVO.getShopIds());
        
        if(CollectionUtils.isNotEmpty(reqVO.getShopIds())){
            if(GdsConstants.GdsCategory.CATG_TYPE_2.equals(reqVO.getCatgType())){
                if(CollectionUtils.isNotEmpty(reqVO.getShopIds())){
                   reqDTO.setShopIdLst(reqVO.getShopIds());
                }
            }else if(GdsConstants.GdsCategory.CATG_TYPE_1.equals(reqVO.getCatgType())){
                if(null != catlogIdsWithCurrentUser){
                    reqDTO.setCatlogIds(catlogIdsWithCurrentUser); 
                }
            }
        }
        
        
        if(isNeedAuth){
            page = gdsCategoryRSV.queryGdsCategoryPagingWithAuth(reqDTO);
        }else{
            page = gdsCategoryRSV.queryGdsCategoryPaging(reqDTO);
        }
        

        List<GdsCategoryRespDTO> nodes = page.getResult();

        List<CategoryTreeVO> trees = new ArrayList<CategoryTreeVO>();
        /*
         * 树展示描述:树型菜单要挂在店铺或者目录节点下,需要根据分类类型以决定是添加根商铺树或者根目录树.
         */
        if (CollectionUtils.isNotEmpty(nodes)) {
            // 用于维持根节点ID.
            List<String> rootIds = new ArrayList<String>();
            List<String> catgCodes = new ArrayList<String>();


            Map<Long, GdsCatalogRespDTO> catlogMap = new HashMap<Long, GdsCatalogRespDTO>();

            for (GdsCategoryRespDTO respDTO : nodes) {
                // 平台分类需要生成目录根节点.

                if (CategoryVO.SHOW_ROOT_YES == reqVO.getShowRoot()) {
                    if (GdsConstants.GdsCategory.CATG_TYPE_1.equals(reqVO.getCatgType())) {
                        String rootId = respDTO.getCatlogId().toString();
                        if (!rootIds.contains(rootId)) {
                            GdsCatalogReqDTO catlogReqDTO = new GdsCatalogReqDTO();
                            catlogReqDTO.setId(respDTO.getCatlogId());
                            GdsCatalogRespDTO catlog = gdsCatalogRSV
                                    .queryGdsCatalogByPK(catlogReqDTO);
                            if (null == catlog || GdsUtils.isEqualsInvalid(catlog.getStatus())) {
                                continue;
                            }
                            trees.add(buildCatalogNodeItem(catlog, true));
                            rootIds.add(rootId);
                        }
                    } 
                }

                if (CategoryVO.SHOW_ROOT_NO == reqVO.getShowRoot()) {
                    if (GdsConstants.GdsCategory.CATG_TYPE_1.equals(reqVO.getCatgType())) {
                        if (!catlogMap.containsKey(respDTO.getCatlogId())) {
                            GdsCatalogReqDTO catlogReqDTO = new GdsCatalogReqDTO();
                            catlogReqDTO.setId(respDTO.getCatlogId());
                            GdsCatalogRespDTO catlog = gdsCatalogRSV
                                    .queryGdsCatalogByPK(catlogReqDTO);
                            catlogMap.put(respDTO.getCatlogId(), catlog);
                        }

                        if (null == catlogMap.get(respDTO.getCatlogId())
                                || GdsUtils.isEqualsInvalid(catlogMap.get(respDTO.getCatlogId())
                                        .getStatus())) {
                            continue;
                        }
                    } 
                }

                // 获取树轨迹－begin。
                GdsCategoryReqDTO traceReqDTO = new GdsCategoryReqDTO();
                traceReqDTO.setCatgCode(respDTO.getCatgCode());
                List<GdsCategoryRespDTO> catgTraceLst = gdsCategoryRSV.queryCategoryTraceUpon(traceReqDTO);
                if(CollectionUtils.isNotEmpty(catgTraceLst)){
                    for(GdsCategoryRespDTO catgTrace : catgTraceLst){
                        if(catgCodes.contains(catgTrace.getCatgCode())){
                            continue;
                        }
                        catgCodes.add(catgTrace.getCatgCode());
                        // 创建分类树项.
                        CategoryTreeVO vo = new CategoryTreeVO();
                        vo.setId(catgTrace.getCatgCode());
                        vo.setName(catgTrace.getCatgName());
                        vo.setIsRoot(false);
                        if (null != catgTrace.getCatlogId()) {
                            vo.setCatlogId(catgTrace.getCatlogId().toString());
                            if (StringUtil.isBlank(catgTrace.getCatgParent())) {
                                vo.setpId(CATLOG_ID_PREFIX + catgTrace.getCatlogId());
                            } else {
                                vo.setpId(catgTrace.getCatgParent());
                            }
                        } 
                        vo.setCatgLevel(catgTrace.getCatgLevel());
                    
                        vo.setIsParent(false);
                        vo.setOpen(true);
                        
                        
                        /*
                         * if(StringUtil.isNotBlank(respDTO.getMediaUuid())){
                         * vo.setIcon(new AiToolUtil().genImageUrl(respDTO.getMediaUuid(), "18x18")); }
                         */
                        trees.add(vo);
                    }
                }
                // 获取树轨迹－end。
            }
        }
        String json = JSONObject.toJSONString(trees);
        LogUtil.info(MODULE, String.format("返回JSON数据：%s", json));
        return json;
    }
    @RequestMapping("/asyncData/category/right")
    @ResponseBody
    public String categoryright(Model model, UnpfCategoryTreeNodeVO node, HttpServletResponse response)
    {
        List<UnpfCategoryTreeNodeVO> trees = new ArrayList<UnpfCategoryTreeNodeVO>();
        
        if (StringUtil.isEmpty(node)) {
            return JSONObject.toJSONString(trees);
        }
        
        GdsCatgSyncReqDTO catgSyncReqDTO = new GdsCatgSyncReqDTO();

        if (StringUtil.isBlank(node.getId())) {
            catgSyncReqDTO.setCatgParent("0");
        }else {
            catgSyncReqDTO.setCatgParent(node.getId());
        }
        if (StringUtil.isNotBlank(node.getShopId())) {
            catgSyncReqDTO.setShopId(Long.valueOf(node.getShopId()));               
        }
        if (StringUtil.isNotBlank(node.getShopAuthId())) {
            catgSyncReqDTO.setShopAuthId(Long.valueOf(node.getShopAuthId()));
        }
        catgSyncReqDTO.setSrcSystem(node.getSrcSystem());
        catgSyncReqDTO.setPageSize(Integer.MAX_VALUE);
        
        PageResponseDTO<GdsCatgSyncRespDTO> page = gdsCategorySyncRSV.queryGdsCatgSyncPageInfo(catgSyncReqDTO);
        if (page!= null && CollectionUtils.isNotEmpty(page.getResult())) {
            for(GdsCatgSyncRespDTO row : page.getResult()){
                trees.add(responseToNode(row));
            }
        }
        
        return JSONObject.toJSONString(trees);
    }
    @RequestMapping("/asyncData/category/right/search")
    @ResponseBody
    public String categoryrightsearch(Model model, UnpfCategoryTreeNodeVO node, HttpServletResponse response)
    {

        GdsCatgSyncReqDTO reqDTO = new GdsCatgSyncReqDTO();
        reqDTO.setCatgName(node.getName());
        if (StringUtil.isNotBlank(node.getSrcSystem())) {
            reqDTO.setSrcSystem(node.getSrcSystem());
        } 
        if (StringUtil.isNotBlank(node.getShopAuthId())) {
            reqDTO.setShopAuthId(Long.valueOf(node.getShopAuthId()));
        }
        if (StringUtil.isNotBlank(node.getShopId())) {
            reqDTO.setShopId(Long.valueOf(node.getShopId()));
        }
        reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
        reqDTO.setPageSize(Integer.MAX_VALUE);

        PageResponseDTO<GdsCatgSyncRespDTO> page = gdsCategorySyncRSV
                .queryGdsCatgSyncPageInfo(reqDTO);

        List<GdsCatgSyncRespDTO> nodes = page.getResult();

        List<UnpfCategoryTreeNodeVO> trees = new ArrayList<UnpfCategoryTreeNodeVO>();
        /*
         * 树展示描述:树型菜单要挂在店铺或者目录节点下,需要根据分类类型以决定是添加根商铺树或者根目录树.
         */
        if (CollectionUtils.isNotEmpty(nodes)) {
            // 用于维持根节点ID.
            List<String> catgCodes = new ArrayList<String>();

            for (GdsCatgSyncRespDTO respDTO : nodes) {

                // 获取树轨迹－begin。
                GdsCatgSyncReqDTO traceReqDTO = new GdsCatgSyncReqDTO();
                traceReqDTO.setCatgCode(respDTO.getCatgCode());
                traceReqDTO.setSrcSystem(respDTO.getSrcSystem());
                if (StringUtil.isNotEmpty(respDTO.getShopId())) {
                    reqDTO.setShopId(respDTO.getShopId());
                }
                if (StringUtil.isNotEmpty(respDTO.getShopAuthId())) {
                    reqDTO.setShopAuthId(respDTO.getShopAuthId());
                }
                List<GdsCatgSyncRespDTO> catgTraceLst = gdsCategorySyncRSV
                        .queryCatgSyncTraceUpon(traceReqDTO);
                if (CollectionUtils.isNotEmpty(catgTraceLst)) {
                    for (GdsCatgSyncRespDTO catgTrace : catgTraceLst) {
                        if (catgCodes.contains(catgTrace.getCatgCode())) {
                            continue;
                        }
                        catgCodes.add(catgTrace.getCatgCode());
                        UnpfCategoryTreeNodeVO nodeVO = responseToNode(catgTrace);
                        nodeVO.setIsParent(!catgTrace.getIfLeafNode());
                        nodeVO.setOpen(true);
                        trees.add(nodeVO);
                    }
                }
                // 获取树轨迹－end。

            }
        }
        String json = JSONObject.toJSONString(trees);
        LogUtil.info(MODULE, String.format("返回JSON数据：%s", json));
        return json;
    }
    ////////-------------------- 分类属性数据获取  ----------------------///////////
    @RequestMapping("/asyncData/attribute/left")
    @ResponseBody
    public String attributeleft(Model model, UnpfCategoryTreeNodeVO node, HttpServletResponse response)
    {
        //调用商品接口，获取分类下的属性列表
        List<UnpfCategoryTreeNodeVO> trees = new ArrayList<UnpfCategoryTreeNodeVO>();
        
        GdsCatg2PropReqDTO reqDTO = new GdsCatg2PropReqDTO();
        reqDTO.setCatgCode(node.getId());
        reqDTO.setPageSize(Integer.MAX_VALUE);
        reqDTO.setIfContainTopProp(true);
        
        GdsCatg2PropRelationRespDTO props = gdsCategoryRSV.queryCategoryProps(reqDTO);
        
        if (props != null && CollectionUtils.isNotEmpty(props.getSpecs())) {
            List<GdsPropRespDTO> propList =props.getSpecs();
            for(GdsPropRespDTO row : propList){
                UnpfCategoryTreeNodeVO nodeVO = new  UnpfCategoryTreeNodeVO();
                nodeVO.setId(String.valueOf(row.getId()));
                nodeVO.setName(row.getPropName());
                nodeVO.setIsParent(false);
                nodeVO.setpId(node.getId());
                nodeVO.setShopId(node.getShopId());
                nodeVO.setShopAuthId(node.getShopAuthId());
                nodeVO.setSrcSystem(node.getSrcSystem());
                
                trees.add(nodeVO);
            }
        }
        
        return JSONObject.toJSONString(trees);
    }
    @RequestMapping("/asyncData/attribute/right")
    @ResponseBody
    public String attributeRight(Model model, UnpfCategoryTreeNodeVO node, HttpServletResponse response)
    {
        List<UnpfCategoryTreeNodeVO> trees = new ArrayList<UnpfCategoryTreeNodeVO>();
        if (StringUtil.isEmpty(node)) {
            return JSONObject.toJSONString(trees);
        }
        
        UnpfPropReqDTO reqDTO = new UnpfPropReqDTO();
        reqDTO.setCatgCode(node.getId());
        reqDTO.setPlatType(node.getSrcSystem());
        if (StringUtil.isNotEmpty(node.getShopId())) {
            reqDTO.setShopId(Long.valueOf(node.getShopId()));               
        }
        if (StringUtil.isNotEmpty(node.getShopAuthId())) {
            reqDTO.setShopAuthId(Long.valueOf(node.getShopAuthId()));
        }
        reqDTO.setPageSize(Integer.MAX_VALUE);
        
        List<UnpfPropRespDTO> records = unpfCategoryRSV.queryUnpfProp(reqDTO);
        if (CollectionUtils.isNotEmpty(records)) {
            for(UnpfPropRespDTO row : records){
                trees.add(responseToNode(row));
            }
        }
             
        return JSONObject.toJSONString(trees);
    }
    
    ////////-------------------- 属性值数据获取  ----------------------///////////
    @RequestMapping("/asyncData/attribute/value/left")
    @ResponseBody
    public String categoryValueLeft(Model model, UnpfCategoryTreeNodeVO node, HttpServletResponse response)
    {
        //调用商品域接口，获取属性下的值
        List<UnpfCategoryTreeNodeVO> trees = new ArrayList<UnpfCategoryTreeNodeVO>();
        GdsPropValueReqDTO reqDTO = new GdsPropValueReqDTO();
        reqDTO.setPageSize(Integer.MAX_VALUE);
        
        reqDTO.setPropId(Long.valueOf(node.getId()));
        
        PageResponseDTO<GdsPropValueRespDTO> propValuePage = gdsPropRSV.queryGdsPropValuePaging(reqDTO);
        
        if (propValuePage != null && CollectionUtils.isNotEmpty(propValuePage.getResult())) {
            List<GdsPropValueRespDTO> valuesList = propValuePage.getResult();
            for(GdsPropValueRespDTO propvalue : valuesList){
                UnpfCategoryTreeNodeVO nodeVO = new  UnpfCategoryTreeNodeVO();
                nodeVO.setId(String.valueOf(propvalue.getId()));
                nodeVO.setpId(node.getId());
                nodeVO.setName(propvalue.getPropValue());
                nodeVO.setIsParent(false);
                nodeVO.setShopId(node.getShopId());
                nodeVO.setShopAuthId(node.getShopAuthId());
                nodeVO.setSrcSystem(node.getSrcSystem());
                
                trees.add(nodeVO);
            }
        }
        
        return JSONObject.toJSONString(trees);
    }
    @RequestMapping("/asyncData/attribute/value/right")
    @ResponseBody
    public String attributeValueRight(Model model, UnpfCategoryTreeNodeVO node, HttpServletResponse response)
    {
        List<UnpfCategoryTreeNodeVO> trees = new ArrayList<UnpfCategoryTreeNodeVO>();
        if (StringUtil.isEmpty(node)) {
            return JSONObject.toJSONString(trees);
        }
        
        UnpfPropValueReqDTO reqDTO = new UnpfPropValueReqDTO();
        
        reqDTO.setPropCode(node.getId());
        reqDTO.setPlatType(node.getSrcSystem());
        if (StringUtil.isNotEmpty(node.getShopId())) {
            reqDTO.setShopId(Long.valueOf(node.getShopId()));               
        }
        if (StringUtil.isNotEmpty(node.getShopAuthId())) {
            reqDTO.setShopAuthId(Long.valueOf(node.getShopAuthId()));
        }
        reqDTO.setPageSize(Integer.MAX_VALUE);

        List<UnpfPropValueRespDTO> records = unpfCategoryRSV.queryUnpfPropValue(reqDTO);
        if (CollectionUtils.isNotEmpty(records)) {
            for(UnpfPropValueRespDTO row : records){
                trees.add(responseToNode(row));
            }
        }
        
        return JSONObject.toJSONString(trees);
    }  
    @RequestMapping("/select/category/relation")
    public String selectCategory(Model model, String leftNodeJson, String rightNodeJson,HttpServletResponse response)
    {
        List<UnpfCatgRelaRespDTO> relationsLeft = null; 
        boolean leftJsonFail = true;
        if (leftJsonFail && StringUtil.isNotBlank(leftNodeJson)) {
            
            UnpfCategoryTreeNodeVO leftNode = jsonToNode(leftNodeJson);

            UnpfCatgRelaReqDTO relaReqDTO = new UnpfCatgRelaReqDTO();
            
            relaReqDTO.setCatgCode(leftNode.getId());
            relaReqDTO.setPlatType(leftNode.getSrcSystem());
            if (StringUtil.isNotEmpty(leftNode.getShopId())) {
                relaReqDTO.setShopId(Long.valueOf(leftNode.getShopId()));               
            }
            if (StringUtil.isNotEmpty(leftNode.getShopAuthId())) {
                relaReqDTO.setShopAuthId(Long.valueOf(leftNode.getShopAuthId()));
            }
            relationsLeft =unpfCategoryRSV.selectCatgRelaList(relaReqDTO);     
        }
        
        List<UnpfCatgRelaRespDTO> relationsRight = null;
        boolean rightJsonFail = false;
        if (rightJsonFail && StringUtil.isNotBlank(rightNodeJson)) {
            
            UnpfCategoryTreeNodeVO rightNode = jsonToNode(rightNodeJson);
            
            UnpfCatgRelaReqDTO relaReqDTO = new UnpfCatgRelaReqDTO();
            
            relaReqDTO.setOutCatgCode(rightNode.getId());
            relaReqDTO.setPlatType(rightNode.getSrcSystem());
            if (StringUtil.isNotEmpty(rightNode.getShopId())) {
                relaReqDTO.setShopId(Long.valueOf(rightNode.getShopId()));               
            }
            if (StringUtil.isNotEmpty(rightNode.getShopAuthId())) {
                relaReqDTO.setShopAuthId(Long.valueOf(rightNode.getShopAuthId()));
            }
            relationsRight =unpfCategoryRSV.selectCatgRelaList(relaReqDTO);     
        }
        
        List<UnpfCatgRelaRespDTO> relationAll = new ArrayList<>();
        Set<Long> idSet = new HashSet<Long>();
        if (CollectionUtils.isNotEmpty(relationsLeft)) {
            for(UnpfCatgRelaRespDTO row : relationsLeft){
                if (!idSet.contains(row.getId())) {
                    relationAll.add(row);
                    idSet.add(row.getId());
                }
            }
        }
        if (CollectionUtils.isNotEmpty(relationsRight)) {
            for(UnpfCatgRelaRespDTO row : relationsRight){
                if (!idSet.contains(row.getId())) {
                    relationAll.add(row);
                    idSet.add(row.getId());
                }
            }
        }
        
        model.addAttribute("category-relation", relationAll);
        
        return "unpf/catemap/load/category-relation-table";
    }
    @RequestMapping("/select/category/attribute/relation")
    public String selectAttribute(Model model, String leftNodeJson, String rightNodeJson,HttpServletResponse response)
    {
        List<UnpfPropRelaRespDTO> relationsLeft = null;
        boolean leftJsonFail = true;
        if (leftJsonFail && StringUtil.isNotBlank(leftNodeJson)) {
            
            UnpfCategoryTreeNodeVO leftNode = jsonToNode(leftNodeJson);
            
            UnpfPropRelaReqDTO propRelReqDTO = new UnpfPropRelaReqDTO();
            propRelReqDTO.setPropId(Long.valueOf(leftNode.getId()));
            propRelReqDTO.setPlatType(leftNode.getSrcSystem());
            if (StringUtil.isNotEmpty(leftNode.getShopId())) {
                propRelReqDTO.setShopId(Long.valueOf(leftNode.getShopId()));               
            }
            if (StringUtil.isNotEmpty(leftNode.getShopAuthId())) {
                propRelReqDTO.setShopAuthId(Long.valueOf(leftNode.getShopAuthId()));
            }
            relationsLeft = unpfCategoryRSV.selectPropRelaList(propRelReqDTO);
        }
        
        List<UnpfPropRelaRespDTO> relationsRight = null;
        boolean rightJsonFail = false;
        if (rightJsonFail && StringUtil.isNotBlank(rightNodeJson)) {
            UnpfCategoryTreeNodeVO rightNode = jsonToNode(rightNodeJson);
            UnpfPropRelaReqDTO propRelReqDTO = new UnpfPropRelaReqDTO();
            propRelReqDTO.setOutPropId(rightNode.getId());
            propRelReqDTO.setPlatType(rightNode.getSrcSystem());
            if (StringUtil.isNotEmpty(rightNode.getShopId())) {
                propRelReqDTO.setShopId(Long.valueOf(rightNode.getShopId()));               
            }            
            if (StringUtil.isNotEmpty(rightNode.getShopAuthId())) {
                propRelReqDTO.setShopAuthId(Long.valueOf(rightNode.getShopAuthId()));
            }
            relationsRight = unpfCategoryRSV.selectPropRelaList(propRelReqDTO);
        }
        
        List<UnpfPropRelaRespDTO> relationAll = new ArrayList<>();
        Set<Long> idSet = new HashSet<Long>();
        if (CollectionUtils.isNotEmpty(relationsLeft)) {
            for(UnpfPropRelaRespDTO row : relationsLeft){
                if (!idSet.contains(row.getId())) {
                    relationAll.add(row);
                    idSet.add(row.getId());
                }
            }
        }
        if (CollectionUtils.isNotEmpty(relationsRight)) {
            for(UnpfPropRelaRespDTO row : relationsRight){
                if (!idSet.contains(row.getId())) {
                    relationAll.add(row);
                    idSet.add(row.getId());
                }
            }
        }
        
        model.addAttribute("attribute-relation", relationAll);

        return "unpf/catemap/load/attribute-relation-table";   
    }
    @RequestMapping("/select/category/attribute/value/relation")
    public String selectValue(Model model, String leftNodeJson, String rightNodeJson,HttpServletResponse response)
    {
        List<UnpfPropValueRelaRespDTO> relationsLeft = null;
        boolean leftJsonFail = true;
        if (leftJsonFail && StringUtil.isNotBlank(leftNodeJson)) {
            
            UnpfCategoryTreeNodeVO leftNode = jsonToNode(leftNodeJson);

            UnpfPropValueRelaReqDTO propValueRelaReqDTO = new UnpfPropValueRelaReqDTO();
            propValueRelaReqDTO.setVid(leftNode.getId());
            propValueRelaReqDTO.setPropId(leftNode.getpId());
            propValueRelaReqDTO.setPlatType(leftNode.getSrcSystem());
            if (StringUtil.isNotEmpty(leftNode.getShopId())) {
                propValueRelaReqDTO.setShopId(Long.valueOf(leftNode.getShopId()));               
            }   
            if (StringUtil.isNotEmpty(leftNode.getShopAuthId())) {
                propValueRelaReqDTO.setShopAuthId(Long.valueOf(leftNode.getShopAuthId()));
            }
            
            relationsLeft = unpfCategoryRSV.selectPropValueRelaList(propValueRelaReqDTO);

        }
        
        List<UnpfPropValueRelaRespDTO> relationsRight = null;
        boolean rightJsonFail = false;
        if (rightJsonFail && StringUtil.isNotBlank(rightNodeJson)) {
            
            UnpfCategoryTreeNodeVO rightNode = jsonToNode(rightNodeJson);
            
            UnpfPropValueRelaReqDTO propValueRelaReqDTO = new UnpfPropValueRelaReqDTO();
            propValueRelaReqDTO.setOutVid(rightNode.getId());
            propValueRelaReqDTO.setOutPropId(rightNode.getpId());
            propValueRelaReqDTO.setPlatType(rightNode.getSrcSystem());
            if (StringUtil.isNotEmpty(rightNode.getShopId())) {
                propValueRelaReqDTO.setShopId(Long.valueOf(rightNode.getShopId()));               
            } 
            if (StringUtil.isNotEmpty(rightNode.getShopAuthId())) {
                propValueRelaReqDTO.setShopAuthId(Long.valueOf(rightNode.getShopAuthId()));
            }
            relationsRight = unpfCategoryRSV.selectPropValueRelaList(propValueRelaReqDTO);

        }
        List<UnpfPropValueRelaRespDTO> relationAll = new ArrayList<>();
        Set<Long> idSet = new HashSet<Long>();
        if (CollectionUtils.isNotEmpty(relationsLeft)) {
            for(UnpfPropValueRelaRespDTO row : relationsLeft){
                if (!idSet.contains(row.getId())) {
                    relationAll.add(row);
                    idSet.add(row.getId());
                }
            }
        }
        if (CollectionUtils.isNotEmpty(relationsRight)) {
            for(UnpfPropValueRelaRespDTO row : relationsRight){
                if (!idSet.contains(row.getId())) {
                    relationAll.add(row);
                    idSet.add(row.getId());
                }
            }
        }
        
        
        model.addAttribute("value-relation", relationAll);
        
        return "unpf/catemap/load/value-relation-table";
    }
    @RequestMapping("/add/category/relation")
    @ResponseBody
    public EcpBaseResponseVO add(Model model, String leftNodeJson, String rightNodeJson,@RequestParam("type")String type,HttpServletResponse response)
    {
        EcpBaseResponseVO responseVO = null;
        switch (type) {
        case "category":
            responseVO = insertCategoryRelation(leftNodeJson, rightNodeJson);
            break;
        case "attribute":
            responseVO = insertCategoryAttributeRelation(leftNodeJson, rightNodeJson);
            break;
        case "value":
            responseVO = insertCategoryAttributeValueRelation(leftNodeJson, rightNodeJson);
            break;
        default:
            break;
        }
        
        return responseVO;
    }
    private EcpBaseResponseVO insertCategoryRelation(String leftNodeJson, String rightNodeJson){
        
        EcpBaseResponseVO responseVO = new EcpBaseResponseVO();

        UnpfCategoryTreeNodeVO leftNode = jsonToNode(leftNodeJson);
        UnpfCategoryTreeNodeVO rightNode = jsonToNode(rightNodeJson);
        
        if (StringUtil.isNotEmpty(leftNode) && StringUtil.isNotEmpty(rightNode)) {
            
            UnpfCatgRelaReqDTO relaReqDTO = new UnpfCatgRelaReqDTO();
            relaReqDTO.setCatgCode(leftNode.getId());
            //relaReqDTO.setOutCatgCode(rightNode.getId());
            relaReqDTO.setPlatType(rightNode.getSrcSystem());
            if (StringUtil.isNotEmpty(rightNode.getShopId())) {
                relaReqDTO.setShopId(Long.valueOf(rightNode.getShopId()));               
            }
            if (StringUtil.isNotEmpty(rightNode.getShopAuthId())) {
                relaReqDTO.setShopAuthId(Long.valueOf(rightNode.getShopAuthId()));                
            }
            UnpfCatgRelaRespDTO checkResult = unpfCategoryRSV.checkCatgRela(relaReqDTO);
            relaReqDTO.setOutCatgCode(rightNode.getId());
            if (checkResult == null) {
                unpfCategoryRSV.insertCatgRela(relaReqDTO);
                responseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            }else {
                responseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                String resultMsg = "平台内部分类【"+checkResult.getCatgCodeName()+"】已经关联【"+checkResult.getOutCatgCodeName()+"】分类！";
                responseVO.setResultMsg(resultMsg);
            }
        }
        return responseVO;
    }
    private EcpBaseResponseVO insertCategoryAttributeRelation(String leftNodeJson, String rightNodeJson){
        
        EcpBaseResponseVO responseVO = new EcpBaseResponseVO();
        
        UnpfCategoryTreeNodeVO leftNode = jsonToNode(leftNodeJson);
        UnpfCategoryTreeNodeVO rightNode = jsonToNode(rightNodeJson);
        
        if (StringUtil.isNotEmpty(leftNode) && StringUtil.isNotEmpty(rightNode)) {
            UnpfPropRelaReqDTO propRelReqDTO = new UnpfPropRelaReqDTO();
            
            propRelReqDTO.setPropId(Long.valueOf(leftNode.getId()));
            propRelReqDTO.setPropName(leftNode.getName());
            propRelReqDTO.setOutPropId(rightNode.getId());
            propRelReqDTO.setOutPromName(rightNode.getName());
            if (StringUtil.isNotEmpty(rightNode.getShopId())) {
                propRelReqDTO.setShopId(Long.valueOf(rightNode.getShopId()));               
            }
            if (StringUtil.isNotEmpty(rightNode.getShopAuthId())) {
                propRelReqDTO.setShopAuthId(Long.valueOf(rightNode.getShopAuthId()));                
            }
            propRelReqDTO.setPlatType(rightNode.getSrcSystem());
            
            UnpfPropRelaRespDTO checkResult = unpfCategoryRSV.checkPropRela(propRelReqDTO);
            if (checkResult == null) {
                unpfCategoryRSV.insertPropRela(propRelReqDTO);
                responseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            }else {
                responseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                String resultMsg = "平台内部属性【"+checkResult.getPropName()+"】已经关联【"+checkResult.getOutPromName()+"】属性！";
                responseVO.setResultMsg(resultMsg);
            }

        }
        return responseVO;
    }
    private EcpBaseResponseVO insertCategoryAttributeValueRelation(String leftNodeJson, String rightNodeJson){
        
        EcpBaseResponseVO responseVO = new EcpBaseResponseVO();

        UnpfCategoryTreeNodeVO leftNode = jsonToNode(leftNodeJson);
        UnpfCategoryTreeNodeVO rightNode = jsonToNode(rightNodeJson);
        
        if (StringUtil.isNotEmpty(leftNode) && StringUtil.isNotEmpty(rightNode)) {
            
            UnpfPropValueRelaReqDTO propValueRelaReqDTO = new UnpfPropValueRelaReqDTO();
            
            propValueRelaReqDTO.setPropId(leftNode.getpId());
            propValueRelaReqDTO.setVid(leftNode.getId());
            propValueRelaReqDTO.setVidName(leftNode.getName());
            
            propValueRelaReqDTO.setOutPropId(rightNode.getpId());
            propValueRelaReqDTO.setOutVid(rightNode.getId());
            propValueRelaReqDTO.setOutVidName(rightNode.getName());
            if (StringUtil.isNotEmpty(rightNode.getShopId())) {
                propValueRelaReqDTO.setShopId(Long.valueOf(rightNode.getShopId()));               
            }
            if (StringUtil.isNotEmpty(rightNode.getShopAuthId())) {
                propValueRelaReqDTO.setShopAuthId(Long.valueOf(rightNode.getShopAuthId()));                
            }
            propValueRelaReqDTO.setPlatType(rightNode.getSrcSystem());
            
            if (existFatherRelation(leftNode, rightNode)) {
                UnpfPropValueRelaRespDTO checkResult = unpfCategoryRSV.checkPropValueRela(propValueRelaReqDTO);
                if (checkResult == null) {
                    unpfCategoryRSV.insertPropValueRela(propValueRelaReqDTO);
                    responseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
                }else {
                    responseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                    String resultMsg = "平台内部属性值【"+checkResult.getVidName()+"】已经关联【"+checkResult.getOutVidName()+"】属性值！";
                    responseVO.setResultMsg(resultMsg);
                }
            }else {
                responseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                String resultMsg = "请先建立【"+leftNode.getName()+"】与【"+rightNode.getName()+"】的父属性关联关系！";
                responseVO.setResultMsg(resultMsg);
            }
            

        }
        return responseVO;
    }
    private boolean existFatherRelation(UnpfCategoryTreeNodeVO leftNode, UnpfCategoryTreeNodeVO rightNode){
        UnpfPropRelaReqDTO propRelReqDTO = new UnpfPropRelaReqDTO();
        propRelReqDTO.setPropId(Long.valueOf(leftNode.getpId()));
        propRelReqDTO.setOutPropId(rightNode.getpId());
        if (StringUtil.isNotEmpty(rightNode.getShopId())) {
            propRelReqDTO.setShopId(Long.valueOf(rightNode.getShopId()));               
        }
        if (StringUtil.isNotEmpty(rightNode.getShopAuthId())) {
            propRelReqDTO.setShopAuthId(Long.valueOf(rightNode.getShopAuthId()));                
        }
        propRelReqDTO.setPlatType(rightNode.getSrcSystem());
        
        List<UnpfPropRelaRespDTO> propRelaList = unpfCategoryRSV.selectPropRelaList(propRelReqDTO);
        
        return CollectionUtils.isNotEmpty(propRelaList);
    }
    private boolean existCatgRelation(UnpfCategoryTreeNodeVO node, boolean bOutCatg){
        UnpfCatgRelaReqDTO relaReqDTO = new UnpfCatgRelaReqDTO();
        if (bOutCatg) {
            relaReqDTO.setOutCatgCode(node.getId());
        }else {
            relaReqDTO.setCatgCode(node.getId());
        }
        relaReqDTO.setPlatType(node.getSrcSystem());
        if (StringUtil.isNotEmpty(node.getShopId())) {
            relaReqDTO.setShopId(Long.valueOf(node.getShopId()));               
        }
        if (StringUtil.isNotEmpty(node.getShopAuthId())) {
            relaReqDTO.setShopAuthId(Long.valueOf(node.getShopAuthId()));                
        }
        UnpfCatgRelaRespDTO checkResult = unpfCategoryRSV.checkCatgRela(relaReqDTO);
        
        if (checkResult == null) {
            return false;
        }
        return true;
    }
    
    @RequestMapping("/delete/category/relation")
    @ResponseBody
    public Model delete(Model model, @RequestParam("relationId")String relationId,@RequestParam("type")String type, HttpServletResponse response)
    {
        switch (type) {
        case "category":
            if (StringUtil.isNotBlank(relationId)) {
                unpfCategoryRSV.deleteCatgRelaByKey(Long.valueOf(relationId));
            }
            break;
        case "attribute":
            if (StringUtil.isNotBlank(relationId)) {
                unpfCategoryRSV.deletePropRelaByKey(Long.valueOf(relationId));
            }
            break;
        case "value":
            if (StringUtil.isNotBlank(relationId)) {
                unpfCategoryRSV.deletePropValueRelaByKey(Long.valueOf(relationId));
            }
            break;
        default:
            break;
        }

        return model;
    }
    private UnpfCategoryTreeNodeVO responseToNode(GdsCatgSyncRespDTO response){
        UnpfCategoryTreeNodeVO node = new UnpfCategoryTreeNodeVO();

        if (response == null) {
            return node;
        }
        
        node.setId(response.getCatgCode());
        node.setpId(response.getCatgParent());
        node.setName(response.getCatgName());
        node.setIsParent(!response.getIfLeafNode());
        node.setSrcSystem(response.getSrcSystem());
        node.setShopId(String.valueOf(response.getShopId()));
        node.setShopAuthId(String.valueOf(response.getShopAuthId()));
        /*
        if (existCatgRelation(node, true)) {
            node.setName("<font color=\"red\">"+response.getCatgName()+ "</font>");
        }*/
        return node;
        
    }
    private UnpfCategoryTreeNodeVO responseToNode(UnpfPropRespDTO response){
        UnpfCategoryTreeNodeVO node = new UnpfCategoryTreeNodeVO();

        if (response == null) {
            return node;
        }
        
        node.setId(response.getPropCode());
        node.setName(response.getPropName());
        node.setIsParent(false);
        node.setSrcSystem(response.getPlatType());
        node.setShopId(String.valueOf(response.getShopId()));
        node.setShopAuthId(String.valueOf(response.getShopAuthId()));
        
        return node;
        
    }
    private UnpfCategoryTreeNodeVO responseToNode(UnpfPropValueRespDTO response){
        UnpfCategoryTreeNodeVO node = new UnpfCategoryTreeNodeVO();

        if (response == null) {
            return node;
        }
        
        node.setId(response.getPropVCode());
        node.setName(response.getPropVName());
        node.setIsParent(false);
        node.setSrcSystem(response.getPlatType());
        node.setShopId(String.valueOf(response.getShopId()));
        node.setShopAuthId(String.valueOf(response.getShopAuthId()));
        
        return node;
        
    }
    private UnpfCategoryTreeNodeVO jsonToNode(String json){
        UnpfCategoryTreeNodeVO node = new UnpfCategoryTreeNodeVO();
        
        if (StringUtil.isBlank(json)) {
            return null;
        }
        JSONObject nodeObject = (JSONObject) JSONObject.parse(json);
        String name = (String) nodeObject.get("name");
        String id = (String) nodeObject.get("id");
        String pId = (String) nodeObject.get("pId");
        boolean isParent = nodeObject.getBooleanValue("isParent");
        String srcSystem = (String) nodeObject.get("srcSystem");
        String shopId = (String) nodeObject.get("shopId");
        String shopAuthId = (String) nodeObject.get("shopAuthId");
        
        node.setId(id);
        node.setName(name);
        node.setpId(pId);
        node.setIsParent(isParent);
        node.setSrcSystem(srcSystem);
        node.setShopId(shopId);
        node.setShopAuthId(shopAuthId);
        
        return node;
        
    }
    private void convert2TreeList(List<CategoryTreeVO> trees, List<GdsCategoryRespDTO> catgLst,
            GdsCategoryReqDTO categoryReqDTO, CategoryVO reqVO) {
        if (CollectionUtils.isNotEmpty(catgLst)) {
            for (GdsCategoryRespDTO respDTO : catgLst) {
                CategoryTreeVO vo = new CategoryTreeVO();
                vo.setId(respDTO.getCatgCode());
                //判断当前平台分类是否有映射关系
                UnpfCategoryTreeNodeVO node = new UnpfCategoryTreeNodeVO();
                node.setId(respDTO.getCatgCode());
                if (StringUtil.isNotEmpty(reqVO.getShopId())) {
                    node.setShopId(reqVO.getShopId().toString());
                }
                node.setShopAuthId(reqVO.getShopAuthId());
                node.setSrcSystem(reqVO.getSrcSystem());
                if (existCatgRelation(node, false)) {
                    vo.setName("<font color=\"red\">"+respDTO.getCatgName()+ "</font>");
                }else {
                    vo.setName(respDTO.getCatgName());
                }
                vo.setIsRoot(false);
                if (null != respDTO.getCatlogId()) {
                    vo.setCatlogId(respDTO.getCatlogId().toString());
                    if (StringUtil.isBlank(respDTO.getCatgParent())) {
                        vo.setpId(CATLOG_ID_PREFIX + respDTO.getCatlogId());
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
    private List<GdsCatalogRespDTO> loadCatalogRespDTO(CategoryVO reqVO) {
        GdsCatalogReqDTO reqDTO = new GdsCatalogReqDTO();
        reqDTO.setPageSize(Integer.MAX_VALUE);
        reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
        reqDTO.setId(reqVO.getCatlogId());
        PageResponseDTO<GdsCatalogRespDTO> page = gdsCatalogRSV
                .queryGdsCatalogRespDTOPaging(reqDTO);
        return page.getResult();
    }
    private List<Long> fetchCatlogIdsByCurrentUser(List<Long> selectedShopIds){
        List<Long> catlogIds = null;
        if(CollectionUtils.isNotEmpty(selectedShopIds)){
            catlogIds = new ArrayList<Long>();
            for (Iterator<Long> iterator = selectedShopIds.iterator(); iterator.hasNext();) {
                Long shopId = iterator.next();
                GdsCatlog2ShopReqDTO relationQuery = new GdsCatlog2ShopReqDTO();
                relationQuery.setShopId(shopId);
                LongListRespDTO longListResp = gdsCatlog2ShopRSV.queryGdsCatlog2ShopRespDTOByShopId(relationQuery);
                if(null != longListResp && CollectionUtils.isNotEmpty(longListResp.getLst())){
                    for(Long l : longListResp.getLst()){
                        if(!catlogIds.contains(l)){
                            catlogIds.add(l);
                        }
                    }
                }
            }
        }
        return catlogIds;
    }
}

