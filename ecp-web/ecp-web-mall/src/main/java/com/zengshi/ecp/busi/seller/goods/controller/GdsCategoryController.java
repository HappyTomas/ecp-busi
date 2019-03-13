/** 
 * Project Name:ecp-web-manage 
 * File Name:GdsCategoryManageController.java 
 * Package Name:com.zengshi.ecp.busi.goods.category 
 * Date:2015年8月29日下午4:15:30 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */
package com.zengshi.ecp.busi.seller.goods.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.velocity.AiToolUtil;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.seller.goods.vo.category.CategoryPageVO;
import com.zengshi.ecp.busi.seller.goods.vo.category.CategoryRespVO;
import com.zengshi.ecp.busi.seller.goods.vo.category.CategoryTreeVO;
import com.zengshi.ecp.busi.seller.goods.vo.category.CategoryVO;
import com.zengshi.ecp.busi.seller.goods.vo.category.CatgSelectorReqVO;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalog2SiteReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalogReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalogRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PGroupReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PGroupRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PropRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatlog2ShopReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropGroupRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.common.LongListRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.common.StringReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatalog2SiteRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatalogRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatlog2ShopRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsPropGroupRSV;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.staff.dubbo.dto.SellerResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopCacheRSV;
import com.zengshi.ecp.system.filter.SellerLocaleUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.ResourceMsgUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSONObject;

/**
 * 分类管理Controller <br>
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2015年8月29日下午4:15:30 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version
 * @since JDK 1.6
 */
@Controller
@RequestMapping("/seller/goods/category")
public class GdsCategoryController extends GdsBaseController {

    private static final String BASE_URL = "/seller/goods/category/";

    /**
     * 分类树目录ID前缀.
     */
    public static final String CATLOG_ID_PREFIX = "CATLOG-";

    /**
     * 分类树店铺ID前缀。
     */
    public static final String SHOP_ID_PREFIX = "SHOP-";

    // 定义允许上传的文件扩展名
    public static final HashMap<String, String> extMap = new HashMap<String, String>();

    static {
        extMap.put("image", "jpg,png");
    }

    /**
     * 弹出框基础路径.
     */
    private static final String BASE_OPEN_URL = BASE_URL+"open/";

    @Resource(name = "gdsCategoryRSV")
    private IGdsCategoryRSV gdsCategoryRSV;

    @Resource(name = "gdsCatalogRSV")
    private IGdsCatalogRSV gdsCatalogRSV;

    @Resource(name = "shopCacheRSV")
    private IShopCacheRSV shopCacheRSV;

    @Resource(name = "gdsPropGroupRSV")
    private IGdsPropGroupRSV gdsPropGroupRSV;
    @Resource(name = "gdsCatalog2SiteRSV")
    private IGdsCatalog2SiteRSV gdsCatalog2SiteRSV;
    @Resource(name="gdsCatlog2ShopRSV")
    private IGdsCatlog2ShopRSV gdsCatlog2ShopRSV;
    
    /**
     * 
     * 导航至主体页面。
     * 
     * @author liyong7
     * @param catgType
     * @param model
     * @param reqVO
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @RequestMapping("/{catgType}main")
    public String mainPage(@PathVariable("catgType")
    String catgType, Model model, CategoryVO reqVO) throws Exception {
        switch (catgType) {
        case "plat":
            // 设置平台分类条件。
            reqVO.setCatgType(GdsConstants.GdsCategory.CATG_TYPE_1);
            break;
        case "shop":
            // 设置店铺分类条件。
            reqVO.setCatgType(GdsConstants.GdsCategory.CATG_TYPE_2);
            break;
        case "point":
            // 设置店铺分类条件。
            reqVO.setCatgType(GdsConstants.GdsCategory.CATG_TYPE_1);
            reqVO.setCatlogId(2L);            
            break;
        default:
            return null;
            // break;
        }

        model.addAttribute("reqVO", reqVO);
        model.addAttribute("view", true);
        // 查询最大层级配置.
        BaseSysCfgRespDTO sysCfgRespDTO = SysCfgUtil.fetchSysCfg("GDS_CATEGORY_MAX_LEVEL");
        if (null != sysCfgRespDTO) {
            model.addAttribute("maxLevel", sysCfgRespDTO.getParaValue());
        }
        return BASE_URL + "category-main";
    }

    /**
     * 
     * 已配置属性列表.
     * 
     * @author liyong7
     * @param model
     * @param reqVO
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @RequestMapping("/listconfigedprop")
    @ResponseBody
    public Model configedPropList(Model model, CategoryPageVO reqVO) throws Exception {
        LogUtil.debug(MODULE, "获取已配置属性,入参:" + ToStringBuilder.reflectionToString(reqVO));
        GdsCatg2PropReqDTO reqDTO = reqVO.toBaseInfo(GdsCatg2PropReqDTO.class);

        EcpBasePageRespVO<Map> respVO = null;
        PageResponseDTO<GdsCatg2PropRespDTO> page = null;
        if (StringUtil.isBlank(reqVO.getCatgCode())) {
            page = new PageResponseDTO<GdsCatg2PropRespDTO>();
            page.setCount(0L);
            page.setPageSize(10);
            page.setResult(new ArrayList<GdsCatg2PropRespDTO>());
        } else {
            reqDTO.setCatgCode(reqVO.getCatgCode());
            reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
            reqDTO.setPropName(reqVO.getPropName());
            page = gdsCategoryRSV.queryConfigedPropsPaging(reqDTO);
        }

        // 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        respVO = EcpBasePageRespVO.buildByPageResponseDTO(page);
        return super.addPageToModel(model, respVO);
    }

    /**
     * 
     * 可选属性列表.
     * 
     * @author liyong7
     * @param model
     * @param reqVO
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @RequestMapping("/listoptionalprop")
    @ResponseBody
    public Model optionalPropList(Model model, CategoryPageVO reqVO) throws Exception {
        LogUtil.debug(MODULE, "获取可选配置属性,入参:" + ToStringBuilder.reflectionToString(reqVO));
        GdsCatg2PropReqDTO reqDTO = reqVO.toBaseInfo(GdsCatg2PropReqDTO.class);

        EcpBasePageRespVO<Map> respVO = null;
        PageResponseDTO<GdsPropRespDTO> page = null;
        if (StringUtil.isBlank(reqVO.getCatgCode())) {
            page = new PageResponseDTO<GdsPropRespDTO>();
            page.setCount(0L);
            page.setPageSize(10);
            page.setResult(new ArrayList<GdsPropRespDTO>());
        } else {
            reqDTO.setCatgCode(reqVO.getCatgCode());
            reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
            reqDTO.setPropName(reqVO.getPropName());
            page = gdsCategoryRSV.queryOptionalPropsPaging(reqDTO);

            if (CollectionUtils.isNotEmpty(page.getResult())) {
                for (GdsPropRespDTO gdsPropRespDTO : page.getResult()) {
                    gdsPropRespDTO.setStatus(BaseParamUtil.fetchParamValue("PUBLIC_PARAM_STATUS",
                            gdsPropRespDTO.getStatus()));

                    gdsPropRespDTO.setPropType(BaseParamUtil.fetchParamValue("GDS_PROP_TYPE",
                            gdsPropRespDTO.getPropType()));

                    gdsPropRespDTO.setPropValueType(BaseParamUtil.fetchParamValue(
                            "GDS_PROP_VALUE_TYPE", gdsPropRespDTO.getPropValueType()));
                }
            }

        }
        // 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        respVO = EcpBasePageRespVO.buildByPageResponseDTO(page);
        return super.addPageToModel(model, respVO);
    }

    /**
     * 
     * 已配置属性组列表.
     * 
     * @author liyong7
     * @param model
     * @param reqVO
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @RequestMapping("/listconfigedgroups")
    @ResponseBody
    public Model configedGroupList(Model model, CategoryPageVO reqVO) throws Exception {
        LogUtil.debug(MODULE, "获取已配置属性组,入参:" + ToStringBuilder.reflectionToString(reqVO));
        GdsCatg2PGroupReqDTO reqDTO = reqVO.toBaseInfo(GdsCatg2PGroupReqDTO.class);

        EcpBasePageRespVO<Map> respVO = null;
        PageResponseDTO<GdsCatg2PGroupRespDTO> page = null;
        if (StringUtil.isBlank(reqVO.getCatgCode())) {
            page = new PageResponseDTO<GdsCatg2PGroupRespDTO>();
            page.setCount(0L);
            page.setPageSize(10);
            page.setResult(new ArrayList<GdsCatg2PGroupRespDTO>());
        } else {
            reqDTO.setCatgCode(reqVO.getCatgCode());
            reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
            reqDTO.setGroupName(reqVO.getGroupName());

            page = gdsCategoryRSV.queryConfigedPropgroupPaging(reqDTO);
        }
        // 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        respVO = EcpBasePageRespVO.buildByPageResponseDTO(page);
        return super.addPageToModel(model, respVO);
    }

    /**
     * 
     * 可选属性组列表.
     * 
     * @author liyong7
     * @param model
     * @param reqVO
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @RequestMapping("/listoptgroups")
    @ResponseBody
    public Model optionalPropgroupList(Model model, CategoryPageVO reqVO) throws Exception {
        LogUtil.debug(MODULE, "获取可选属性组,入参:" + ToStringBuilder.reflectionToString(reqVO));
        GdsCatg2PGroupReqDTO reqDTO = reqVO.toBaseInfo(GdsCatg2PGroupReqDTO.class);

        EcpBasePageRespVO<Map> respVO = null;
        PageResponseDTO<GdsPropGroupRespDTO> page = null;
        if (StringUtil.isBlank(reqVO.getCatgCode())) {
            page = new PageResponseDTO<GdsPropGroupRespDTO>();
            page.setCount(0L);
            page.setPageSize(10);
            page.setResult(new ArrayList<GdsPropGroupRespDTO>());
        } else {
            reqDTO.setCatgCode(reqVO.getCatgCode());
            reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
            reqDTO.setGroupName(reqVO.getGroupName());
            page = gdsCategoryRSV.queryOptionalPropgroupPaging(reqDTO);
            // 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
            respVO = EcpBasePageRespVO.buildByPageResponseDTO(page);
        }
        respVO = EcpBasePageRespVO.buildByPageResponseDTO(page);
        return super.addPageToModel(model, respVO);
    }

    /**
     * 
     * 执行分类信息更新。
     * 
     * @author liyong7
     * @param model
     * @param reqVO
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @RequestMapping("/categoryupdate")
    @ResponseBody
    public CategoryRespVO updateCategory(Model model, @Valid
    CategoryVO reqVO) throws Exception {
        LogUtil.info(MODULE,
                String.format("更新分类信息，参数：%s", ToStringBuilder.reflectionToString(reqVO)));
        CategoryRespVO respVO = new CategoryRespVO();
        try {
            
            //店铺校验
            boolean flag=isShopValid(reqVO.getShopId());
            if(!flag){
                respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
                respVO.setResultMsg("不是当前登录用户的店铺！");
            }
            
            GdsCategoryReqDTO reqDTO = new GdsCategoryReqDTO();
            ObjectCopyUtil.copyObjValue(reqVO, reqDTO, null, false);
            // 不变更父节点，对父节点作清空操作。
            reqDTO.setCatgParent(null);
            reqDTO.setCatgLevel(null);
            
            GdsCategoryRespDTO originCatg = gdsCategoryRSV.queryGdsCategoryByPK(reqDTO);
            if(null == originCatg || GdsConstants.Commons.STATUS_INVALID.equals(originCatg.getStatus())){
            	throw new BusinessException(GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200303);
            }
            
            if(GdsConstants.Commons.STATUS_VALID.equals(originCatg.getIfAbleEdit())){
            	// 该分类不允许编辑或者删除 !
            	throw new BusinessException("web.gds.2000016");
            }
            
            gdsCategoryRSV.editGdsCategory(reqDTO);
            GdsCategoryRespDTO catgRespDTO = gdsCategoryRSV.queryGdsCategoryByPK(reqDTO);
            if (StringUtil.isNotBlank(catgRespDTO.getMediaUuid())) {
                catgRespDTO.setMediaURL(new AiToolUtil().genImageUrl(catgRespDTO.getMediaUuid(), "300x200"));
            }
            ObjectCopyUtil.copyObjValue(catgRespDTO, reqVO, null, true);
            respVO.setCategoryVO(reqVO);
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);

        } catch (BusinessException e) {
            LogUtil.error(MODULE, String.format("编辑分类信息异常，分类ID=%s", reqVO.getCatgCode()));
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            respVO.setResultMsg(e.getErrorMessage());
        }
        return respVO;
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
            	SellerResDTO srd =  SellerLocaleUtil.getSeller();
            	List<ShopInfoResDTO> shopLst = srd.getShoplist();
            	if(CollectionUtils.isNotEmpty(shopLst)){
            		for (Iterator<ShopInfoResDTO> iterator = shopLst.iterator(); iterator
							.hasNext();) {
						ShopInfoResDTO shopInfoResDTO = iterator
								.next();
						if(null != shopInfoResDTO && GdsUtils.isEqualsValid(shopInfoResDTO.getShopStatus())){
							CategoryTreeVO vo = buildShopNodeItem(shopInfoResDTO, null);
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
        LogUtil.info(MODULE, String.format("返回JSON数据：%s", json));
        return json;
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
    @RequestMapping("/asyncData/selector/getNodes")
    @ResponseBody
    public String getSelectorNodes(Model model,CategoryVO reqVO,HttpServletResponse response)throws Exception{
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
        List<Long> shopIdsWithCurrentUser = fetchShopIdsByCurrentUser(reqVO.getShopIds());
        
        ObjectCopyUtil.copyObjValue(reqVO, reqDTO, "", false);
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
                 // 设置目录ID范围限定条件.
                    reqDTO.setCatlogIds(catlogIdsWithCurrentUser);
                    if(isNeedAuth){
                    	catgLst = gdsCategoryRSV.queryRootCategoryWithAuth(reqDTO);
                    }else{
                    	catgLst = gdsCategoryRSV.queryRootCategory(reqDTO);
                    }
                    convert2TreeList(trees, catgLst,reqDTO);
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
                        	if(catlogIdsWithCurrentUser.contains(catlogRespDTO.getId())){
	                            CategoryTreeVO vo = buildCatalogNodeItem(catlogRespDTO, null);
	                            trees.add(vo);
                        	}
                        }
                    }
                }
                break;
                // 店铺分类根节点为目录信息。
            case GdsConstants.GdsCategory.CATG_TYPE_2:
                // 不显示店铺.
                if(CategoryVO.SHOW_ROOT_NO == reqVO.getShowRoot()){
                    // 目录ID不为空,查询分类信息.
                    List<GdsCategoryRespDTO> catgLst = null;
                    reqDTO.setShopIdLst(shopIdsWithCurrentUser);
                    // 查询根分类。
                    catgLst = gdsCategoryRSV.queryRootCategory(reqDTO);
                    convert2TreeList(trees, catgLst,reqDTO);
                }else{
                    // 获取店铺信息.
                    Map<Long, ShopInfoResDTO> map = shopCacheRSV.getCacheShop();
                    if(MapUtils.isNotEmpty(map)){
                        Set<Entry<Long, ShopInfoResDTO>> entrySet = map.entrySet();
                        for (Iterator<Entry<Long, ShopInfoResDTO>> iterator = entrySet.iterator(); iterator
                                .hasNext();) {
                            Entry<Long, ShopInfoResDTO> entry = (Entry<Long, ShopInfoResDTO>) iterator
                                    .next();
                            ShopInfoResDTO dto = entry.getValue();
                            if(null != dto && GdsUtils.isEqualsValid(dto.getShopStatus())){
                                if((null != reqVO.getShopId() && reqVO.getShopId().equals(dto.getId()))
                                		||(null != reqVO.getShopIds() && reqVO.getShopIds().contains(dto.getId()))
                                		){
                                	if(shopIdsWithCurrentUser.contains(dto.getId())){
                                		CategoryTreeVO vo = buildShopNodeItem(dto, null);
                                		trees.add(vo);
                                	}
                                }
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
                
            }else if(id.startsWith(SHOP_ID_PREFIX)){
                id = id.replace(SHOP_ID_PREFIX, "");
                reqDTO.setShopId(Long.valueOf(id));
                // 查询根分类。
                catgLst = gdsCategoryRSV.queryRootCategory(reqDTO);
            }else{
                // 否则加载子分类。
                reqDTO.setCatgParent(id);
                if(isNeedAuth){
                   catgLst = gdsCategoryRSV.querySubCategoryWithAuth(reqDTO);
                }else{
                	catgLst = gdsCategoryRSV.querySubCategory(reqDTO);
                }
            }
            convert2TreeList(trees,catgLst,reqDTO);
        }
        String json = JSONObject.toJSONString(trees);
        LogUtil.info(MODULE, String.format("返回JSON数据：%s", json));
        return json;
    }

    /**
     * 
     * 分类树查询.<br/>
     * 
     * @author liyong7
     * @param model
     * @param reqVO
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @RequestMapping("/asyncData/catgsearch")
    @ResponseBody
    public String categorySearch(Model model, CategoryVO reqVO) throws Exception {
        LogUtil.info(MODULE, "搜索树型节点,参数" + ToStringBuilder.reflectionToString(reqVO));
        paramCheck(new Object[] { reqVO.getCatgType(), reqVO.getCatgName() }, new String[] {
                "catgType", "catgName" });
        GdsCategoryReqDTO reqDTO = new GdsCategoryReqDTO();
        /*reqDTO.setCatgType(reqVO.getCatgType());
        reqDTO.setCatgName(reqVO.getCatgName());
        reqDTO.setCatlogId(reqVO.getCatlogId());
        reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);*/
        ObjectCopyUtil.copyObjValue(reqVO, reqDTO, null, true);
        reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
        reqDTO.setPageSize(Integer.MAX_VALUE);
        
        /*if(GdsConstants.GdsCategory.CATG_TYPE_2.equals(reqVO.getCatgType())){
        	linkShops =  SellerLocaleUtil.getSeller().getShoplist();
        	if(CollectionUtils.isEmpty(linkShops)){
        		reqDTO.addShopIdToList(-9999L);
        	}else{
        		for (Iterator<ShopInfoResDTO> iterator = linkShops.iterator(); iterator
						.hasNext();) {
					ShopInfoResDTO s =  iterator
							.next();
					if(GdsUtils.isEqualsValid(s.getShopStatus())){
					     reqDTO.addShopIdToList(s.getId());
					}
				}
        	}
        }*/
        List<Long> catlogIdsWithCurrentUser = fetchCatlogIdsByCurrentUser(reqVO.getShopIds());
        List<Long> shopIdsWithCurrentUser = fetchShopIdsByCurrentUser(reqVO.getShopIds());
        
        if(GdsConstants.GdsCategory.CATG_TYPE_2.equals(reqVO.getCatgType())){
        	reqDTO.setShopIdLst(shopIdsWithCurrentUser);
        }else if(GdsConstants.GdsCategory.CATG_TYPE_1.equals(reqVO.getCatgType())){
        	reqDTO.setCatlogIds(catlogIdsWithCurrentUser);
        }else{
        	reqDTO.setShopId(-9999L);
        }

        PageResponseDTO<GdsCategoryRespDTO> page = gdsCategoryRSV.queryGdsCategoryPaging(reqDTO);

        List<GdsCategoryRespDTO> nodes = page.getResult();

        List<CategoryTreeVO> trees = new ArrayList<CategoryTreeVO>();
        /*
         * 树展示描述:树型菜单要挂在店铺或者目录节点下,需要根据分类类型以决定是添加根商铺树或者根目录树.
         */
        if (CollectionUtils.isNotEmpty(nodes)) {
            // 用于维持根节点ID.
            List<String> rootIds = new ArrayList<String>();
            List<String> catgCodes = new ArrayList<String>();
            Map<Long, ShopInfoResDTO> shopMap = new HashMap<>();
            if (GdsConstants.GdsCategory.CATG_TYPE_2.equals(reqVO.getCatgType())) {
                shopMap = shopCacheRSV.getCacheShop();
            }
           
            
            for (GdsCategoryRespDTO respDTO : nodes) {
                // 平台分类需要生成目录根节点.
                if (GdsConstants.GdsCategory.CATG_TYPE_1.equals(reqVO.getCatgType())) {
                    String rootId = respDTO.getCatlogId().toString();
                    if (!rootIds.contains(rootId)) {
                        GdsCatalogReqDTO catlogReqDTO = new GdsCatalogReqDTO();
                        catlogReqDTO.setId(respDTO.getCatlogId());
                        GdsCatalogRespDTO catlog = gdsCatalogRSV.queryGdsCatalogByPK(catlogReqDTO);
                        if (null == catlog || GdsUtils.isEqualsInvalid(catlog.getStatus())) {
                            continue;
                        }
                        trees.add(buildCatalogNodeItem(catlog, true));
                        rootIds.add(rootId);
                    }
                } else if (GdsConstants.GdsCategory.CATG_TYPE_2.equals(reqVO.getCatgType())) {
                    // 店铺分类需要生成店铺根节点.
                    String rootId = respDTO.getShopId().toString();
                    if (!rootIds.contains(rootId)) {
                        ShopInfoResDTO shop = shopMap.get(respDTO.getShopId());
                        if (null == shop || GdsUtils.isEqualsInvalid(shop.getShopStatus())) {
                            continue;
                        }
                        trees.add(buildShopNodeItem(shop, true));
                        rootIds.add(rootId);
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
                        } else if (null != catgTrace.getShopId()) {
                            vo.setShopId(catgTrace.getShopId().toString());
                            if (StringUtil.isBlank(catgTrace.getCatgParent())) {
                                vo.setpId(SHOP_ID_PREFIX + catgTrace.getShopId());
                            } else {
                                vo.setpId(catgTrace.getCatgParent());
                            }
                        }
                        vo.setCatgLevel(catgTrace.getCatgLevel());
                    
                        vo.setIsParent(GdsConstants.GdsCategory.IF_LEAF_0.equals(catgTrace.getIfLeaf()) ? true
                                : false);
                        vo.setOpen(true);
                        /*
                         * if(StringUtil.isNotBlank(respDTO.getMediaUuid())){
                         * vo.setIcon(new AiToolUtil().genImageUrl(respDTO.getMediaUuid(), "18x18")); }
                         */
                        trees.add(vo);
                	}
                }
                // 获取树轨迹－end。
                
                
                
                /*CategoryTreeVO vo = new CategoryTreeVO();
                vo.setId(respDTO.getCatgCode());
                vo.setName(respDTO.getCatgName());
                vo.setIsRoot(false);
                if (null != respDTO.getCatlogId()) {
                    vo.setCatlogId(respDTO.getCatlogId().toString());
                    vo.setpId(CATLOG_ID_PREFIX + respDTO.getCatlogId());
                } else if (null != respDTO.getShopId()) {
                    vo.setShopId(respDTO.getShopId().toString());
                    vo.setpId(SHOP_ID_PREFIX + respDTO.getShopId());
                }
                vo.setCatgLevel(respDTO.getCatgLevel());
                vo.setIsParent(false);
                trees.add(vo);*/
            }
        }
        String json = JSONObject.toJSONString(trees);
        LogUtil.info(MODULE, String.format("返回JSON数据：%s", json));
        return json;
    }

    @RequestMapping("/asyncData/selector/catgsearch")
    @ResponseBody
    public String selectorCategorySearch(Model model, CategoryVO reqVO) throws Exception {
        LogUtil.info(MODULE, "搜索树型节点,参数" + ToStringBuilder.reflectionToString(reqVO));
        paramCheck(new Object[] { reqVO.getCatgType(), reqVO.getCatgName() }, new String[] {
                "catgType", "catgName" });
        List<ShopInfoResDTO> linkShops = null;
        GdsCategoryReqDTO reqDTO = new GdsCategoryReqDTO();
        /*reqDTO.setCatgType(reqVO.getCatgType());
        reqDTO.setCatgName(reqVO.getCatgName());
        reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
        reqDTO.setCatlogId(reqVO.getCatlogId());
        reqDTO.setSiteId(reqVO.getSiteId());*/
        ObjectCopyUtil.copyObjValue(reqVO, reqDTO, null, true);
        reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
        reqDTO.setPageSize(Integer.MAX_VALUE);
        
        List<Long> catlogIdsWithCurrentUser = fetchCatlogIdsByCurrentUser(reqVO.getShopIds());
        List<Long> shopIdsWithCurrentUser = fetchShopIdsByCurrentUser(reqVO.getShopIds());
        
        
        if(GdsConstants.GdsCategory.CATG_TYPE_2.equals(reqVO.getCatgType())){
        	reqDTO.setShopIdLst(shopIdsWithCurrentUser);
        }else if(GdsConstants.GdsCategory.CATG_TYPE_1.equals(reqVO.getCatgType())){
        	reqDTO.setCatlogIds(catlogIdsWithCurrentUser);
        }else{
        	reqDTO.setShopId(-9999L);
        }
        
        // 是否需要权限验证。如果入参为FALSE或者false则表示需要进行权限验证
        boolean isNeedAuth = Boolean.FALSE.toString().equalsIgnoreCase(reqVO.getIgnoreDataAuth());
        

        // PageResponseDTO<GdsCategoryRespDTO> page = gdsCategoryRSV.queryGdsCategoryPaging(reqDTO);
        PageResponseDTO<GdsCategoryRespDTO> page = null;
        
        
        if(isNeedAuth){
        	page = gdsCategoryRSV.queryGdsCategoryPagingWithAuth(reqDTO);
        }else{
        	page = gdsCategoryRSV.queryGdsCategoryPaging(reqDTO);
        }
        

        List<GdsCategoryRespDTO> nodes = page.getResult();
        
        Map<String, GdsCategoryRespDTO> map = null;
        
        if(isNeedAuth){
        	map = toMap(nodes);
        }

        List<CategoryTreeVO> trees = new ArrayList<CategoryTreeVO>();
        /*
         * 树展示描述:树型菜单要挂在店铺或者目录节点下,需要根据分类类型以决定是添加根商铺树或者根目录树.
         */
        if (CollectionUtils.isNotEmpty(nodes)) {
            // 用于维持根节点ID.
            List<String> rootIds = new ArrayList<String>();
            List<String> catgCodes = new ArrayList<String>();

            Map<Long, ShopInfoResDTO> shopMap = new HashMap<>();
            
            if (GdsConstants.GdsCategory.CATG_TYPE_2.equals(reqVO.getCatgType())) {
                shopMap = shopCacheRSV.getCacheShop();
            }

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
                            if(catlogIdsWithCurrentUser.contains(catlog.getId())){
	                            trees.add(buildCatalogNodeItem(catlog, true));
	                            rootIds.add(rootId);
                            }
                        }
                    } else if (GdsConstants.GdsCategory.CATG_TYPE_2.equals(reqVO.getCatgType())) {
                        // 店铺分类需要生成店铺根节点.
                        String rootId = respDTO.getShopId().toString();
                        if (!rootIds.contains(rootId)) {
                            ShopInfoResDTO shop = shopMap.get(respDTO.getShopId());
                            if (null == shop || GdsUtils.isEqualsInvalid(shop.getShopStatus())) {
                                continue;
                            }
                            trees.add(buildShopNodeItem(shop, true));
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

                    } else if (GdsConstants.GdsCategory.CATG_TYPE_2.equals(reqVO.getCatgType())) {
                        if (null == shopMap.get(respDTO.getShopId())
                                || GdsUtils.isEqualsInvalid(shopMap.get(respDTO.getShopId())
                                        .getShopStatus())) {
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
                        } else if (null != catgTrace.getShopId()) {
                            vo.setShopId(catgTrace.getShopId().toString());
                            if (StringUtil.isBlank(catgTrace.getCatgParent())) {
                                vo.setpId(SHOP_ID_PREFIX + catgTrace.getShopId());
                            } else {
                                vo.setpId(catgTrace.getCatgParent());
                            }
                        }
                        vo.setCatgLevel(catgTrace.getCatgLevel());
                    
                        vo.setIsParent(GdsConstants.GdsCategory.IF_LEAF_0.equals(catgTrace.getIfLeaf()) ? true
                                : false);
                        vo.setOpen(true);
                        
                        
                        if(isNeedAuth){
	                        if(!map.containsKey(catgTrace.getCatgCode())){
	                        	vo.setChkDisabled(true);
	                        	vo.setClick(false);
	                        }else{
	                        	vo.setChkDisabled(false);
	                        	vo.setClick(true);
	                        }
                        }
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

    /**
     * 
     * 弹出分类选择树.<br/>
     * 
     * @author liyong7
     * @param model
     * @param reqVO
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @RequestMapping("/open/catgselect")
    public String popupCategorySelect(Model model, CatgSelectorReqVO reqVO) throws Exception {
        LogUtil.info(MODULE,
                String.format("弹出分类选择树，[参数：%s]", ToStringBuilder.reflectionToString(reqVO)));
        switch (reqVO.getCatgType()) {
        case GdsConstants.GdsCategory.CATG_TYPE_1:
            reqVO.setCatgType(GdsConstants.GdsCategory.CATG_TYPE_1);
            break;
        case GdsConstants.GdsCategory.CATG_TYPE_2:
            reqVO.setCatgType(GdsConstants.GdsCategory.CATG_TYPE_2);
            break;
        default:
            return null;
        }
        if ("1".equals(reqVO.getCheckType())) {
            model.addAttribute("multi", "1");
        }
        if(reqVO.getMaxShowNode()!=null){
            
            model.addAttribute("maxShowNode",reqVO.getMaxShowNode());
        }
        model.addAttribute("reqVO", reqVO);
        return BASE_OPEN_URL + "category-select";
    }

    /**
     * 
     * 选定分类删除操作。
     * 
     * @author liyong7
     * @param model
     * @param catgCode
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @RequestMapping("/categorydel")
    @ResponseBody
    public EcpBaseResponseVO deleteCategory(Model model,
            @RequestParam(value = "catgCode", required = true)
            String catgCode) throws Exception {
        LogUtil.info(MODULE, String.format("删除分类,分类ID=%s", catgCode));

        EcpBaseResponseVO respVO = new EcpBaseResponseVO();
        StringReqDTO reqDTO = new StringReqDTO();
        reqDTO.setValue(catgCode);

        try {
            GdsCategoryReqDTO condition = new GdsCategoryReqDTO();
            condition.setCatgCode(catgCode);
            Long total = gdsCategoryRSV.countSubCategory(condition);
            if (0 < total) {
                respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
                respVO.setResultMsg(ResourceMsgUtil.getMessage(
                        GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200306, null));
                return respVO;
            }
            gdsCategoryRSV.deleteGdsCategoryByPK(reqDTO);
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, String.format("删除分类异常，分类ID=%s", catgCode));
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            respVO.setResultMsg(e.getErrorMessage());
        }
        return respVO;
    }

    /**
     * 
     * 显示分类信息。
     * 
     * @author liyong7
     * @param reqVO
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @RequestMapping(value = "/categoryview")
    @ResponseBody
    public CategoryRespVO view(@RequestParam(value = "catgCode", required = true)
    String catgCode, Model model) throws Exception {

        LogUtil.info(MODULE, String.format("显示分类信息,参数：catgCode=%s", catgCode));

        CategoryRespVO respVO = new CategoryRespVO();
        try {
            GdsCategoryReqDTO reqDTO = new GdsCategoryReqDTO();
            reqDTO.setCatgCode(catgCode);
            GdsCategoryRespDTO respDTO = gdsCategoryRSV.queryGdsCategoryByPK(reqDTO);
            if (null == respDTO || GdsUtils.isEqualsInvalid(respDTO.getStatus())) {
                respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                respVO.setResultMsg(new BusinessException(
                        GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200303)
                        .getErrorMessage());
            } else {
                CategoryVO vo = new CategoryVO();
                vo = GdsUtils.doObjConvert(respDTO, CategoryVO.class, null, null);
                if (StringUtil.isNotBlank(vo.getMediaUuid())) {
                    vo.setMediaURL(new AiToolUtil().genImageUrl(vo.getMediaUuid(), "300x200"));
                }
                respVO.setCategoryVO(vo);
                
                if(StringUtil.isNotBlank(vo.getCatgParent())){
                	GdsCategoryReqDTO parentReqDTO = new GdsCategoryReqDTO();
                	parentReqDTO.setCatgCode(vo.getCatgParent());
                    GdsCategoryRespDTO parentRespDTO = gdsCategoryRSV.queryGdsCategoryByPK(parentReqDTO);
                    if(null != parentRespDTO){
                    	CategoryVO parentVO = GdsUtils.doObjConvert(parentRespDTO, CategoryVO.class, null, null);
                    	respVO.setParentVO(parentVO);
                    }
                }
                
                
            }
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "查询商品分类遇到异常！", e);
            respVO.setResultMsg(e.getErrorMessage());
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            throw new BusinessException(e.getErrorCode());
        }

        return respVO;
    }

    /**
     * 
     * 添加属性关联关系.<br/>
     * 
     * @author liyong7
     * @param model
     * @param reqVO
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @RequestMapping("/addprops")
    @ResponseBody
    public EcpBaseResponseVO addPropRelation(Model model, CategoryVO reqVO) throws Exception {
        LogUtil.debug(MODULE, "关联属性,入参:" + ToStringBuilder.reflectionToString(reqVO));
        EcpBaseResponseVO respVO = new EcpBaseResponseVO();
        paramCheck(new Object[] { reqVO.getCatgCode(), reqVO.getPropIds() }, new String[] {
                "catgCode", "propIds" });
        try {
            GdsCatg2PropReqDTO reqDTO = new GdsCatg2PropReqDTO();
            ObjectCopyUtil.copyObjValue(reqVO, reqDTO, "propIds", true);
            reqDTO.setPropIds(reqVO.getPropIds());
            gdsCategoryRSV.addProps(reqDTO);
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "分类关联属性出现异常!", e);
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            respVO.setResultMsg(e.getErrorMessage());
        }
        return respVO;
    }

    /**
     * 
     * 添加属性组.<br/>
     * 
     * @author liyong7
     * @param model
     * @param reqVO
     *            catgCode与groupIds必传.
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @RequestMapping("/addgroups")
    @ResponseBody
    public EcpBaseResponseVO addGroups(Model model, CategoryVO reqVO) throws Exception {
        LogUtil.debug(MODULE, "添加属性组,入参:" + ToStringBuilder.reflectionToString(reqVO));
        EcpBaseResponseVO respVO = new EcpBaseResponseVO();
        paramCheck(new Object[] { reqVO.getCatgCode(), reqVO.getGroupIds() }, new String[] {
                "catgCode", "groupIds" });
        try {
            GdsCatg2PGroupReqDTO reqDTO = new GdsCatg2PGroupReqDTO();
            ObjectCopyUtil.copyObjValue(reqVO, reqDTO, "groupIds", true);
            reqDTO.setGroupIds(reqVO.getGroupIds());
            gdsCategoryRSV.addGroups(reqDTO);
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "添加属性组出现异常!", e);
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            respVO.setResultMsg(e.getErrorMessage());
        }
        return respVO;
    }

    /**
     * 
     * 删除属性组.<br/>
     * 
     * @author liyong7
     * @param model
     * @param reqVO
     *            catgCode与groupIds必传.
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @RequestMapping("/delgroups")
    @ResponseBody
    public EcpBaseResponseVO deleteGroups(Model model, CategoryVO reqVO) throws Exception {
        LogUtil.debug(MODULE, "删除属性组,入参:" + ToStringBuilder.reflectionToString(reqVO));
        EcpBaseResponseVO respVO = new EcpBaseResponseVO();
        paramCheck(new Object[] { reqVO.getCatgCode(), reqVO.getGroupIds() }, new String[] {
                "catgCode", "groupIds" });
        try {
            GdsCatg2PGroupReqDTO reqDTO = new GdsCatg2PGroupReqDTO();
            ObjectCopyUtil.copyObjValue(reqVO, reqDTO, "groupIds", true);
            reqDTO.setGroupIds(reqVO.getGroupIds());
            gdsCategoryRSV.deleteGroups(reqDTO);
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "删除属性组出现异常!", e);
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            respVO.setResultMsg(e.getErrorMessage());
        }
        return respVO;
    }

    /**
     * 
     * 删除属性关联.<br/>
     * 
     * @author liyong7
     * @param model
     * @param reqVO
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @RequestMapping("/delprops")
    @ResponseBody
    public EcpBaseResponseVO delPropRelation(Model model, CategoryVO reqVO) throws Exception {
        LogUtil.debug(MODULE, "关联属性,入参:" + ToStringBuilder.reflectionToString(reqVO));
        EcpBaseResponseVO respVO = new EcpBaseResponseVO();
        paramCheck(new Object[] { reqVO.getCatgCode(), reqVO.getPropIds() }, new String[] {
                "catgCode", "propIds" });
        try {
            GdsCatg2PropReqDTO reqDTO = new GdsCatg2PropReqDTO();
            ObjectCopyUtil.copyObjValue(reqVO, reqDTO, "propIds", true);
            reqDTO.setPropIds(reqVO.getPropIds());

            gdsCategoryRSV.deleteProps(reqDTO);
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "分类关联属性出现异常!", e);
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            respVO.setResultMsg(e.getErrorMessage());
        }
        return respVO;
    }

    /**
     * 
     * 保存分类信息。<br/>
     * 
     * @author liyong7
     * @param reqVO
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @RequestMapping(value = "/categorysave")
    @ResponseBody
    public CategoryRespVO save(@Valid
    CategoryVO reqVO) throws Exception {

        LogUtil.info(MODULE, String.format("新增分类,参数：%s", ToStringBuilder.reflectionToString(reqVO)));

        CategoryRespVO respVO = new CategoryRespVO();
        try {
            
            //店铺校验
            boolean flag=isShopValid(reqVO.getShopId());
            if(!flag){
                respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
                respVO.setResultMsg("不是当前登录用户的店铺！");
            }
            
            if (StringUtil.isNotBlank(reqVO.getCatgParent())) {
                GdsCategoryReqDTO parentQueryDTO = new GdsCategoryReqDTO();
                parentQueryDTO.setCatgCode(reqVO.getCatgParent());
                GdsCategoryRespDTO parent = gdsCategoryRSV.queryGdsCategoryByPK(parentQueryDTO);
                if (null == parent || GdsUtils.isEqualsInvalid(parent.getStatus())) {
                    throw new BusinessException(
                            GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200311);
                }

                // 查询最大层级配置.
                BaseSysCfgRespDTO sysCfgRespDTO = SysCfgUtil
                        .fetchSysCfg(GdsConstants.SysCfgConstants.GDS_CATEGORY_MAX_LEVEL);

                short catgLevel = (short) (parent.getCatgLevel() + 1);

                if (isLevelLimit(sysCfgRespDTO, catgLevel)) {
                    respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
                    respVO.setResultMsg(ResourceMsgUtil.getMessage(
                            GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200313,
                            new String[] { sysCfgRespDTO.getParaValue() }));
                    return respVO;
                }

            }
            
            GdsCategoryReqDTO reqDTO = new GdsCategoryReqDTO();

            ObjectCopyUtil.copyObjValue(reqVO, reqDTO, null, true);

            reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
            reqDTO.setIfAbleEdit(GdsConstants.Commons.STATUS_INVALID);
            GdsCategoryRespDTO respDTO = gdsCategoryRSV.saveGdsCategory(reqDTO);

            ObjectCopyUtil.copyObjValue(respDTO, reqVO, null, false);

            if (StringUtil.isNotBlank(respDTO.getMediaUuid())) {
            	reqVO.setMediaURL(new AiToolUtil().genImageUrl(reqVO.getMediaUuid(), "300x200"));
            }
            
            respVO.setCategoryVO(reqVO);
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);

        } catch (BusinessException e) {
            LogUtil.error(MODULE, "新增商品分类异常", e);
            respVO.setResultMsg(e.getErrorMessage());
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);

        }

        return respVO;
    }

    /**
     * 
     * 属性配置:针对是否必填,是否基础属性,是否搜索属性进行设置.<br/>
     * 
     * @author liyong7
     * @param reqVO
     *            catgCode必传
     * @param propertyType
     *            属性类型 1-表示是否基础属性 2-表示是否必填 3-表示是否搜索属性.
     * @param propertyValue
     *            0-表示非 1-表示是
     * @param bindingResult
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @RequestMapping("/propchange")
    public EcpBaseResponseVO propertyConfig(GdsCatg2PropReqDTO reqVO,
            @RequestParam(value = "type", required = true)
            Integer type, @RequestParam(value = "value", required = true)
            String value, BindingResult bindingResult) throws Exception {

        LogUtil.info(
                MODULE,
                String.format(
                        "属性配置,入参:",
                        String.format("catgCode=[%s],propertyType=[%d],propertyValue=[%s]",
                                reqVO.getCatgCode(), type, value)));

        EcpBaseResponseVO respVO = new EcpBaseResponseVO();
        try {
            paramCheck(new Object[] { reqVO.getCatgCode(), type, value }, new String[] {
                    "catgCode", "type", "value" });

            switch (type) {
            case 1:
                reqVO.setIfBasic(value);
                gdsCategoryRSV.executeIsBaseConfig(reqVO);
                break;
            case 2:
                reqVO.setIfHaveto(value);
                gdsCategoryRSV.executeIsRequireConfig(reqVO);
                break;
            case 3:
                reqVO.setIfSearch(value);
                gdsCategoryRSV.executeIsSearchConfig(reqVO);
                break;
            case 4:
                reqVO.setIfGdsInput(value);
                gdsCategoryRSV.executeIfGdsInputConfig(reqVO);
                break;
            default:
                throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200098,
                        new String[] { "value" });
            }

            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);

        } catch (BusinessException e) {
            LogUtil.error(MODULE, "属性配置异常", e);
            respVO.setResultMsg(e.getErrorMessage());
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);

        }

        return respVO;
    }

    public Map<String, String> getExtMap() {
        return extMap;
    }

    public Long getMaxSize() {
        return 102400L;
    }
    /**
     * 
     * editCheck:分类编辑检测. <br/> 
     * 
     * @author liyong7
     * @param categoryVO
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping("/editCheck")
    public EcpBaseResponseVO editCheck(CategoryVO categoryVO) throws Exception{
    	EcpBaseResponseVO respVO = new EcpBaseResponseVO();
		LogUtil.info(MODULE, String.format("分类编辑检测,参数：catgCode=%s",
				categoryVO.getCatgCode()));

		try {
			GdsCategoryReqDTO reqDTO = new GdsCategoryReqDTO();
			reqDTO.setCatgCode(categoryVO.getCatgCode());
			GdsCategoryRespDTO respDTO = gdsCategoryRSV
					.queryGdsCategoryByPK(reqDTO);
			// 分类信息不存在则抛出异常。
			if (null == respDTO
					|| GdsUtils.isEqualsInvalid(respDTO.getStatus())) {
				respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
				respVO.setResultMsg(new BusinessException(
						GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200303)
						.getErrorMessage());
			} else {
				if (!GdsConstants.Commons.STATUS_VALID.equals(respDTO
						.getIfAbleEdit())) {
					respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
					respVO.setResultMsg("true");
				}
			}
		} catch (BusinessException e) {
			LogUtil.error(MODULE, "分类可编辑检测遇到异常！", e);
			respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
			respVO.setResultMsg(e.getErrorMessage());
		}
		return respVO;
    }
    
    
    
    /**
     * 
     * editCheck:分类编辑检测. <br/> 
     * 
     * @author liyong7
     * @param categoryVO
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping("/propDelCheck")
    public EcpBaseResponseVO propsDelCheck(Model model, CategoryVO reqVO) throws Exception{
    	EcpBaseResponseVO respVO = new EcpBaseResponseVO();
		LogUtil.info(MODULE, String.format("分类属性关联关系删除前检测,入参:",ToStringBuilder.reflectionToString(reqVO)));
		try {
			GdsCatg2PropReqDTO reqDTO = new GdsCatg2PropReqDTO();
            reqDTO.setPropIds(reqVO.getPropIds());
            reqDTO.setCatgCode(reqVO.getCatgCode());
            Long count = gdsCategoryRSV.queryUneditableCatg2Prop(reqDTO);
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
			respVO.setResultMsg(count.toString());
		} catch (BusinessException e) {
			LogUtil.error(MODULE, "分类可编辑检测遇到异常！", e);
			respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
			respVO.setResultMsg(e.getErrorMessage());
		}
		return respVO;
    }
    
    
    
    

    /*
     * 
     * convert2TreeList:分类信息转换成分类树列表. <br/>
     * 
     * @author liyong7
     * 
     * @param trees
     * 
     * @param catgLst
     * 
     * @since JDK 1.6
     */
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

    /*
     * 
     * 加载出所有有效目录信息.
     * 
     * @author liyong7
     * 
     * @return
     * 
     * @since JDK 1.6
     */
    private List<GdsCatalogRespDTO> loadCatalogRespDTO(CategoryVO reqVO) {
        GdsCatalogReqDTO reqDTO = new GdsCatalogReqDTO();
        reqDTO.setPageSize(Integer.MAX_VALUE);
        reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
        reqDTO.setId(reqVO.getCatlogId());
        PageResponseDTO<GdsCatalogRespDTO> page = gdsCatalogRSV
                .queryGdsCatalogRespDTOPaging(reqDTO);
        return page.getResult();
    }

    /*
     * 
     * 创建目录树节点.
     * 
     * @author liyong7
     * 
     * @param catlogRespDTO
     * 
     * @return
     * 
     * @since JDK 1.6
     */
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

    /*
     * 
     * 构建店铺节点.
     * 
     * @author liyong7
     * 
     * @param dto
     * 
     * @return
     * 
     * @since JDK 1.6
     */
    private CategoryTreeVO buildShopNodeItem(ShopInfoResDTO dto, Boolean isOpen) {
        CategoryTreeVO vo = new CategoryTreeVO();
        vo.setId(SHOP_ID_PREFIX + dto.getId());
        vo.setName(dto.getShopName());
        vo.setpId(null);
        vo.setCatgLevel(null);
        vo.setIsParent(true);
        vo.setIsRoot(true);
        //vo.setClick(false);
        vo.setOpen(isOpen);
        return vo;
    }
    
    
   private List<Long> fetchShopIdsByCurrentUser(List<Long> selectedShopIds){
	    List<Long> resultLst = new ArrayList<Long>();
	    List<Long> shopIds = new ArrayList<Long>();
	   	SellerResDTO srd =  SellerLocaleUtil.getSeller();
	   	List<ShopInfoResDTO> shopLst = srd.getShoplist();
	   	if(CollectionUtils.isNotEmpty(shopLst)){
	   		for (Iterator<ShopInfoResDTO> iterator = shopLst.iterator(); iterator.hasNext();) {
					ShopInfoResDTO shopInfoResDTO = iterator.next();
					Long shopId = shopInfoResDTO.getId();
	    			if(!shopIds.contains(shopId)){
	    				shopIds.add(shopId);
	    			}
	    		}
	   	}
	   	
	   	if(CollectionUtils.isNotEmpty(selectedShopIds)){
	   		for(Long sid : selectedShopIds){
	   			if(shopIds.contains(sid)){
	   				resultLst.add(sid);
	   			}
	   		}
	   	}else{
	   		return shopIds;
	   	}
	   	
	   	
	   	return resultLst;
   }
    
    
    private List<Long> fetchCatlogIdsByCurrentUser(List<Long> selectedShopIds){
        
    	List<Long> catlogIds = new ArrayList<Long>();
    	SellerResDTO srd =  SellerLocaleUtil.getSeller();
    	List<ShopInfoResDTO> shopLst = srd.getShoplist();
    	if(CollectionUtils.isNotEmpty(shopLst)){
    		for (Iterator<ShopInfoResDTO> iterator = shopLst.iterator(); iterator.hasNext();) {
				ShopInfoResDTO shopInfoResDTO = iterator.next();
				if(CollectionUtils.isNotEmpty(selectedShopIds)){
    				if(!selectedShopIds.contains(shopInfoResDTO.getId())){
    					continue;
    				}
    			}
				Long shopId = shopInfoResDTO.getId();
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
    
    private Map<String, GdsCategoryRespDTO> toMap(
			List<GdsCategoryRespDTO> result) {
		Map<String,GdsCategoryRespDTO> map = null;
		if(CollectionUtils.isNotEmpty(result)){
			map = new HashMap<String, GdsCategoryRespDTO>();
			for (Iterator<GdsCategoryRespDTO> iterator = result.iterator(); iterator.hasNext();) {
				GdsCategoryRespDTO respDTO =  iterator.next();
				map.put(respDTO.getCatgCode(), respDTO);
			}
		}
		return map;
	}

    private boolean isLevelLimit(BaseSysCfgRespDTO sysCfgRespDTO, Short currentLevel) {

        if (null != sysCfgRespDTO && StringUtil.isNotBlank(sysCfgRespDTO.getParaValue())) {
            if (null != currentLevel && Short.valueOf(sysCfgRespDTO.getParaValue()) < currentLevel) {
                return true;
            }
        }
        return false;
    }
    
}