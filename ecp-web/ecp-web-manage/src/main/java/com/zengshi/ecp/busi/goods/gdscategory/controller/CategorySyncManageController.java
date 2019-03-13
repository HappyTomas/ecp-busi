package com.zengshi.ecp.busi.goods.gdscategory.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.velocity.AiToolUtil;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.goods.common.GdsBaseController;
import com.zengshi.ecp.busi.goods.gdscategory.vo.CategoryRespVO;
import com.zengshi.ecp.busi.goods.gdscategory.vo.CategorySyncTreeVO;
import com.zengshi.ecp.busi.goods.gdscategory.vo.CategorySyncVO;
import com.zengshi.ecp.busi.goods.gdscategory.vo.CategoryVO;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalogRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgSyncReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgSyncRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategorySyncRSV;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.server.front.dto.BaseParamDTO;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping(value = "/goods/gdsCatgSync")
public class CategorySyncManageController extends GdsBaseController {
    /**
     * 分类树目录ID前缀.
     */
    public static final String CATLOG_ID_PREFIX = "CATLOG-";

    @Resource
    private IGdsCategorySyncRSV gdsCategorySyncRSV;

    @Resource(name = "gdsCategoryRSV")
    private IGdsCategoryRSV gdsCategoryRSV;

    @RequestMapping(value = "/syncManage")
    public String syncManage(CategorySyncVO categorySyncVO) {

        return "/goods/gdsCatgSync/sync-grid";

    }

    @RequestMapping(value = "/listCatgSync")
    @ResponseBody
    public Model listCatgSync(Model model, CategorySyncVO categorySyncVO) throws Exception {
        LogUtil.debug(MODULE, "请求参数为：" + categorySyncVO.toString());
        GdsCatgSyncReqDTO catgSyncReqDTO = categorySyncVO.toBaseInfo(GdsCatgSyncReqDTO.class);
        if (StringUtil.isNotBlank(categorySyncVO.getSrcSystem())) {
            catgSyncReqDTO.setSrcSystem(categorySyncVO.getSrcSystem());
        }
        if (StringUtil.isNotBlank(categorySyncVO.getCatgName())) {
            catgSyncReqDTO.setCatgName(categorySyncVO.getCatgName());
        }
        if (StringUtil.isNotBlank(categorySyncVO.getMapCatgCode())) {
            catgSyncReqDTO.setMapCatgCode(categorySyncVO.getMapCatgCode());
        }
        catgSyncReqDTO.setIfMap("1");
        PageResponseDTO<GdsCatgSyncRespDTO> t = gdsCategorySyncRSV
                .queryGdsCatgSyncPageInfo(catgSyncReqDTO);

        // 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        if (t.getResult() == null) {
            t.setResult(new ArrayList<GdsCatgSyncRespDTO>());
        }

        for (GdsCatgSyncRespDTO catgSyncRespDTO : t.getResult()) {
            BaseParamDTO srcSystem = BaseParamUtil.fetchParamDTO("GDS_SRC_SYSTEM",
                    catgSyncRespDTO.getSrcSystem());
            catgSyncRespDTO.setSrcSystem(srcSystem.getSpValue());

        }
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);
        return super.addPageToModel(model, respVO);

    }

    @RequestMapping(value = "/delCatgSync")
    public void delCatgSync(CategorySyncVO categorySyncVO) throws Exception {
        GdsCatgSyncReqDTO catgSyncReqDTO = new GdsCatgSyncReqDTO();
        catgSyncReqDTO.setId(categorySyncVO.getDelId());
        gdsCategorySyncRSV.cancelGdsCatgSyncInfo(catgSyncReqDTO);
    }

    @RequestMapping(value = "/delBatchCatgSync")
    public void delBatchCatgSync(CategorySyncVO categorySyncVO) throws Exception {
        String idsStr = categorySyncVO.getDelIds();
        String[] ids = idsStr.split(",");

        List<Long> delIds = new ArrayList<Long>();
        for (String id : ids) {

            delIds.add(Long.parseLong(id));
        }
        GdsCatgSyncReqDTO catgSyncReqDTO = new GdsCatgSyncReqDTO();
        catgSyncReqDTO.setIds(delIds);
        gdsCategorySyncRSV.batchCancelGdsCatgSyncInfo(catgSyncReqDTO);
    }

    @RequestMapping("/mainPage")
    public String mainPage(Model model, CategoryVO reqVO) throws Exception {

        model.addAttribute("reqVO", reqVO);
        model.addAttribute("view", true);
        // 查询最大层级配置.
        BaseSysCfgRespDTO sysCfgRespDTO = SysCfgUtil.fetchSysCfg("GDS_CATEGORY_MAX_LEVEL");
        if (null != sysCfgRespDTO) {
            model.addAttribute("maxLevel", sysCfgRespDTO.getParaValue());
        }
        return "/goods/gdsCatgSync/catgsync-main";

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
    public String getNodes(Model model, CategorySyncVO reqVO, HttpServletResponse response)
            throws Exception {
        LogUtil.info(MODULE, "获取分类树型节点,参数" + ToStringBuilder.reflectionToString(reqVO));
        List<CategorySyncTreeVO> trees = new ArrayList<CategorySyncTreeVO>();

        GdsCatgSyncReqDTO reqDTO = new GdsCatgSyncReqDTO();
        ObjectCopyUtil.copyObjValue(reqVO, reqDTO, null, true);
        String id = reqVO.getId();

        // 节点ID与父节点ID同为空，为目录树加载（即分类树初始化显示顶级目录）
        if (StringUtil.isBlank(reqVO.getId())) {

            List<GdsCatalogRespDTO> catlogs = loadCatalogRespDTO(reqVO);
            if (CollectionUtils.isNotEmpty(catlogs)) {
                for (GdsCatalogRespDTO catlogRespDTO : catlogs) {
                    CategorySyncTreeVO vo = buildCatalogNodeItem(catlogRespDTO, null);
                    trees.add(vo);
                }
            }

        } else {
            // 目录ID不为空,查询分类信息.
            List<GdsCatgSyncRespDTO> catgLst = null;
            // 过滤映射状态
            // reqDTO.setIfMap(reqVO.getIfMap());

            if (id.startsWith(CATLOG_ID_PREFIX)) {
                id = id.replace(CATLOG_ID_PREFIX, "");
                reqDTO.setSrcSystem(id);
                // 查询根分类。
                catgLst = gdsCategorySyncRSV.queryRootCatgSyncBySrcSys(reqDTO);

            } else {
                // 否则加载子分类。
                reqDTO.setCatgCode(id);
                reqDTO.setSrcSystem(reqVO.getSrcSystem());
                catgLst = gdsCategorySyncRSV.querySubCatgSyncByParent(reqDTO);
            }
            convert2TreeList(trees, catgLst, reqDTO);
        }
        String json = JSONObject.toJSONString(trees);
        LogUtil.info(MODULE, String.format("返回JSON数据：%s", json));
        return json;
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
    private void convert2TreeList(List<CategorySyncTreeVO> trees, List<GdsCatgSyncRespDTO> catgLst,
            GdsCatgSyncReqDTO categorySyncReqDTO) {
        if (CollectionUtils.isNotEmpty(catgLst)) {
            for (GdsCatgSyncRespDTO respDTO : catgLst) {
                CategorySyncTreeVO vo = new CategorySyncTreeVO();
                vo.setId(respDTO.getCatgCode());
                if (respDTO.getMapCatgCode() != null && !"-1".equals(respDTO.getMapCatgCode())) {
                    vo.setName("<font color=\"red\">"+respDTO.getCatgName()+ "</font>");
                } else {
                    vo.setName(respDTO.getCatgName());
                }
                vo.setIsRoot(false);
                if (null != respDTO.getSrcSystem()) {
                    vo.setSrcSystem(respDTO.getSrcSystem().toString());
                    if (StringUtil.isBlank(respDTO.getCatgParent())) {
                        vo.setpId(CATLOG_ID_PREFIX + respDTO.getSrcSystem());
                    } else {
                        vo.setpId(respDTO.getCatgParent());
                    }
                }
                vo.setIsParent(!respDTO.getIfLeafNode());

                trees.add(vo);
            }
        }
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
    @RequestMapping("/asyncData/catgSyncsearch")
    @ResponseBody
    public String catgSyncsearch(Model model, CategorySyncVO reqVO) throws Exception {
        LogUtil.info(MODULE, "搜索树型节点,参数" + ToStringBuilder.reflectionToString(reqVO));
        // paramCheck(new Object[] { reqVO.getCatgName() }, new String[] { "catgType", "catgName"
        // });
        GdsCatgSyncReqDTO reqDTO = new GdsCatgSyncReqDTO();
        reqDTO.setCatgName(reqVO.getCatgName());
        if (StringUtil.isNotBlank(reqVO.getSrcSystem())) {
            reqDTO.setSrcSystem(reqVO.getSrcSystem());
        } else {
            reqDTO.setSrcSystem(reqVO.getCatgSrcSystem());
        }

        reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
        reqDTO.setPageSize(Integer.MAX_VALUE);
        // 过滤映射状态
        // reqDTO.setIfMap(reqVO.getIfMap());
        PageResponseDTO<GdsCatgSyncRespDTO> page = gdsCategorySyncRSV
                .queryGdsCatgSyncPageInfo(reqDTO);

        List<GdsCatgSyncRespDTO> nodes = page.getResult();

        List<CategorySyncTreeVO> trees = new ArrayList<CategorySyncTreeVO>();
        /*
         * 树展示描述:树型菜单要挂在店铺或者目录节点下,需要根据分类类型以决定是添加根商铺树或者根目录树.
         */
        if (CollectionUtils.isNotEmpty(nodes)) {
            // 用于维持根节点ID.
            List<String> rootIds = new ArrayList<String>();
            List<String> catgCodes = new ArrayList<String>();
            Map<Long, ShopInfoResDTO> shopMap = null;

            for (GdsCatgSyncRespDTO respDTO : nodes) {
                // 平台分类需要生成目录根节点.
                String rootId = respDTO.getSrcSystem().toString();
                if (!rootIds.contains(rootId)) {
                    GdsCatalogRespDTO catlog = queryGdsCatalogRespBySrcSystem(respDTO
                            .getSrcSystem());
                    if (null == catlog || GdsUtils.isEqualsInvalid(catlog.getStatus())) {
                        continue;
                    }
                    trees.add(buildCatalogNodeItem(catlog, true));
                    rootIds.add(rootId);
                }

                // 获取树轨迹－begin。
                GdsCatgSyncReqDTO traceReqDTO = new GdsCatgSyncReqDTO();
                traceReqDTO.setCatgCode(respDTO.getCatgCode());
                traceReqDTO.setSrcSystem(respDTO.getSrcSystem());
                List<GdsCatgSyncRespDTO> catgTraceLst = gdsCategorySyncRSV
                        .queryCatgSyncTraceUpon(traceReqDTO);
                if (CollectionUtils.isNotEmpty(catgTraceLst)) {
                    for (GdsCatgSyncRespDTO catgTrace : catgTraceLst) {
                        if (catgCodes.contains(catgTrace.getCatgCode())) {
                            continue;
                        }
                        catgCodes.add(catgTrace.getCatgCode());
                        // 创建分类树项.
                        CategorySyncTreeVO vo = new CategorySyncTreeVO();
                        vo.setId(catgTrace.getCatgCode());
                        if (catgTrace.getMapCatgCode() != null && !"-1".equals(catgTrace.getMapCatgCode())) {
                            vo.setName("<font color=\"red\">"+catgTrace.getCatgName()+ "</font>");
                        } else {
                            vo.setName(catgTrace.getCatgName());
                        }
                        vo.setIsRoot(false);
                        if (null != catgTrace.getSrcSystem()) {
                            vo.setSrcSystem(catgTrace.getSrcSystem().toString());
                            if (StringUtil.isBlank(catgTrace.getCatgParent()) ||catgTrace.getCatgParent().equals("-1") ) {
                                vo.setpId(CATLOG_ID_PREFIX + catgTrace.getSrcSystem());
                            } else {
                                vo.setpId(catgTrace.getCatgParent());
                            }
                        }
                        // vo.setCatgLevel(catgTrace.getCatgLevel());

                        // vo.setIsParent(GdsConstants.GdsCategory.IF_LEAF_0.equals(catgTrace
                        // .getIfLeaf()) ? true : false);
                        vo.setIsParent(!catgTrace.getIfLeafNode());
                        vo.setOpen(true);

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
    private CategorySyncTreeVO buildCatalogNodeItem(GdsCatalogRespDTO catlogRespDTO, Boolean isOpen) {
        CategorySyncTreeVO vo = new CategorySyncTreeVO();
        vo.setId(CATLOG_ID_PREFIX + catlogRespDTO.getCatlogCode());
        vo.setName(catlogRespDTO.getCatlogName());
        vo.setpId(null);
        // vo.setCatgLevel(null);
        vo.setIsParent(true);
        if (null != catlogRespDTO.getCatlogCode()) {
            vo.setSrcSystem(catlogRespDTO.getCatlogCode().toString());
        }
        vo.setIsRoot(true);
        vo.setClick(false);
        vo.setOpen(isOpen);
        return vo;
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
    private List<GdsCatalogRespDTO> loadCatalogRespDTO(CategorySyncVO reqVO) {
        List<GdsCatalogRespDTO> catalogRespDTOs = new ArrayList<GdsCatalogRespDTO>();

        if (StringUtil.isNotBlank(reqVO.getCatgSrcSystem())) {
            BaseParamDTO baseParamDTO = BaseParamUtil.fetchParamDTO("GDS_SRC_SYSTEM",
                    reqVO.getCatgSrcSystem());
            GdsCatalogRespDTO catalogRespDTO = new GdsCatalogRespDTO();
            catalogRespDTO.setCatlogCode(baseParamDTO.getSpCode());
            catalogRespDTO.setCatlogName(baseParamDTO.getSpValue());
            catalogRespDTOs.add(catalogRespDTO);
        } else {
            List<BaseParamDTO> baseParamDTOs = BaseParamUtil.fetchParamList("GDS_SRC_SYSTEM");
            if (baseParamDTOs != null) {
                for (BaseParamDTO baseParamDTO : baseParamDTOs) {
                    GdsCatalogRespDTO catalogRespDTO = new GdsCatalogRespDTO();
                    catalogRespDTO.setCatlogCode(baseParamDTO.getSpCode());
                    catalogRespDTO.setCatlogName(baseParamDTO.getSpValue());
                    catalogRespDTOs.add(catalogRespDTO);
                }

            }
        }
        return catalogRespDTOs;
    }

    private GdsCatalogRespDTO queryGdsCatalogRespBySrcSystem(String srcSystem) {

        GdsCatalogRespDTO catalogRespDTO = new GdsCatalogRespDTO();
        BaseParamDTO baseParamDTO = BaseParamUtil.fetchParamDTO("GDS_SRC_SYSTEM", srcSystem);
        if (baseParamDTO != null) {
            catalogRespDTO.setCatlogCode(baseParamDTO.getSpCode());
            catalogRespDTO.setCatlogName(baseParamDTO.getSpValue());
            return catalogRespDTO;
        } else {

            return null;
        }
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
    @RequestMapping(value = "/categorySyncView")
    @ResponseBody
    public CategoryRespVO view(@RequestParam(value = "catgCode", required = true)
    String catgCode, @RequestParam(value = "srcSystem", required = true)
    String srcSystem, Model model) throws Exception {

        LogUtil.info(MODULE,
                String.format("显示分类信息,参数：catgCode=%s,srcSystem=%s", catgCode, srcSystem));

        CategoryRespVO respVO = new CategoryRespVO();
        try {
            GdsCatgSyncReqDTO catgSyncReqDTO = new GdsCatgSyncReqDTO();
            catgSyncReqDTO.setCatgCode(catgCode);
            catgSyncReqDTO.setSrcSystem(srcSystem);
            GdsCatgSyncRespDTO catgSyncRespDTO = gdsCategorySyncRSV
                    .queryGdsCategoryInfoByOriginCatgCode(catgSyncReqDTO);
            if (catgSyncRespDTO==null || StringUtil.isBlank(catgSyncRespDTO.getMapCatgCode())
                    || "-1".equals(catgSyncRespDTO.getMapCatgCode())) {
                respVO.setCategoryVO(null);
            } else {
                if (catgSyncRespDTO != null
                        && !GdsUtils.isEqualsInvalid(catgSyncRespDTO.getStatus())) {
                    GdsCategoryReqDTO reqDTO = new GdsCategoryReqDTO();
                    reqDTO.setCatgCode(catgSyncRespDTO.getMapCatgCode());
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
                            vo.setMediaURL(new AiToolUtil().genImageUrl(vo.getMediaUuid(),
                                    "300x200"));
                        }
                        respVO.setCategoryVO(vo);

                        if (StringUtil.isNotBlank(vo.getCatgParent())) {
                            GdsCategoryReqDTO parentReqDTO = new GdsCategoryReqDTO();
                            parentReqDTO.setCatgCode(vo.getCatgParent());
                            GdsCategoryRespDTO parentRespDTO = gdsCategoryRSV
                                    .queryGdsCategoryByPK(parentReqDTO);
                            if (null != parentRespDTO) {
                                CategoryVO parentVO = GdsUtils.doObjConvert(parentRespDTO,
                                        CategoryVO.class, null, null);
                                respVO.setParentVO(parentVO);
                            }
                        }

                    }
                } else {
                    respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                    respVO.setResultMsg(new BusinessException(
                            GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200303)
                            .getErrorMessage());

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

    @RequestMapping(value = "/saveCategorySync")
    @ResponseBody
    public CategoryRespVO saveCategorySync(CategorySyncVO categorySyncVO, Model model)
            throws Exception {
        GdsCatgSyncReqDTO catgSyncReqDTO = new GdsCatgSyncReqDTO();
        catgSyncReqDTO.setCatgCode(categorySyncVO.getCatgCode());
        catgSyncReqDTO.setMapCatgCode(categorySyncVO.getMapCatgCode());
        catgSyncReqDTO.setSrcSystem(categorySyncVO.getSrcSystem());
        CategoryRespVO respVO = new CategoryRespVO();

        try {
            gdsCategorySyncRSV.updateGdsCatgSyncInfo(catgSyncReqDTO);

            respVO.setResultFlag("success");
        } catch (BusinessException e) {

            respVO.setResultFlag("fail");
            respVO.setResultMsg(e.getErrorMessage());
        }
        return respVO;
    }

    @RequestMapping(value = "/cancelCategorySync")
    @ResponseBody
    public CategoryRespVO cancelCategorySync(CategorySyncVO categorySyncVO, Model model)
            throws Exception {

        GdsCatgSyncReqDTO catgSyncReqDTO = new GdsCatgSyncReqDTO();
        catgSyncReqDTO.setCatgCode(categorySyncVO.getCatgCode());
        // catgSyncReqDTO.setMapCatgCode(categorySyncVO.getMapCatgCode());
        catgSyncReqDTO.setSrcSystem(categorySyncVO.getSrcSystem());
        CategoryRespVO respVO = new CategoryRespVO();
        try {
            gdsCategorySyncRSV.cancelGdsCatgSyncInfo(catgSyncReqDTO);

            respVO.setResultFlag("success");
        } catch (BusinessException e) {

            respVO.setResultFlag("fail");
            respVO.setResultMsg(e.getErrorMessage());
        }
        return respVO;
    }
}
