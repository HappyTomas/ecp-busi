package com.zengshi.ecp.busi.cms.gdscategory.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.velocity.AiToolUtil;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.cms.gdscategory.vo.CmsCategoryVO;
import com.zengshi.ecp.busi.cms.gdscategory.vo.CmsCatgSelectorReqVO;
import com.zengshi.ecp.busi.cms.gdscategory.vo.CmsGdsCategoryRespVO;
import com.zengshi.ecp.busi.cms.gdscategory.vo.CmsGdsCategoryTreeVO;
import com.zengshi.ecp.busi.cms.gdscategory.vo.CmsGdsCategoryVO;
import com.zengshi.ecp.busi.goods.common.GdsBaseController;
import com.zengshi.ecp.cms.dubbo.dto.CmsGdsCategoryReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsGdsCategoryRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceCategoryReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceCategoryRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsGdsCategoryRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceCategoryRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsSiteRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.common.StringReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatalogRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsPropGroupRSV;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopCacheRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.ResourceMsgUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping({ "/cms/category" })
public class CmsGdsCategoryController extends GdsBaseController {
    private static final String BASE_URL = "/cms/gdsCategory/category-";

    public static final String CATLOG_ID_PREFIX = "";

    public static final HashMap<String, String> extMap = new HashMap();

    private static final String BASE_OPEN_URL = "/cms/gdsCategory/open/";

    @Resource(name = "gdsCategoryRSV")
    private IGdsCategoryRSV gdsCategoryRSV;
    
    @Resource(name = "cmsGdsCategoryRSV")
    private ICmsGdsCategoryRSV cmsGdsCategoryRSV;

    @Resource(name = "gdsCatalogRSV")
    private IGdsCatalogRSV gdsCatalogRSV;

    @Resource(name = "shopCacheRSV")
    private IShopCacheRSV shopCacheRSV;

    @Resource(name = "gdsPropGroupRSV")
    private IGdsPropGroupRSV gdsPropGroupRSV;

    @Resource
    private ICmsGdsCategoryRSV iCmsGdsCategoryRSV;

    @Resource
    private ICmsPlaceCategoryRSV iCmsPlaceCategoryRSV;
    
    @Resource
    private ICmsSiteRSV cmsSiteRSV;

    static {
        extMap.put("image", "jpg,png");
    }

    @RequestMapping({ "/{catgType}main" })
    public String mainPage(@PathVariable("catgType") String catgType, Model model,
            CmsGdsCategoryVO reqVO) throws Exception {
        /*switch (catgType) {
        case "plat":
            // 设置平台分类条件。
            reqVO.setCatgType(GdsConstants.GdsCategory.CATG_TYPE_1);
            break;
        case "shop":
            // 设置店铺分类条件。
            reqVO.setCatgType(GdsConstants.GdsCategory.CATG_TYPE_2);
            break;
        default:
            return null;
        // break;
        }*/

        model.addAttribute("reqVO", reqVO);
        model.addAttribute("view", Boolean.valueOf(true));

        BaseSysCfgRespDTO sysCfgRespDTO = SysCfgUtil.fetchSysCfg("GDS_CATEGORY_MAX_LEVEL");
        if (sysCfgRespDTO != null)
            model.addAttribute("maxLevel", sysCfgRespDTO.getParaValue());

        return "/cms/gdsCategory/category-main";
    }

    @RequestMapping({ "/categoryupdate" })
    @ResponseBody
    public CmsGdsCategoryRespVO updateCategory(Model model, @Valid CmsGdsCategoryVO reqVO)
            throws Exception {
        LogUtil.info(this.MODULE, String.format("更新分类信息，参数：%s",
                new Object[] { ToStringBuilder.reflectionToString(reqVO) }));
        CmsGdsCategoryRespVO respVO = new CmsGdsCategoryRespVO();
        try {
            CmsGdsCategoryReqDTO reqDTO = new CmsGdsCategoryReqDTO();
            ObjectCopyUtil.copyObjValue(reqVO, reqDTO, null, false);
            reqDTO.setId(reqVO.getCatgId());
            reqDTO.setCatgParent(null);
            reqDTO.setCatgLevel(null);

            this.iCmsGdsCategoryRSV.updateCmsGdsCategory(reqDTO);
            CmsGdsCategoryRespDTO cmsGdsCategoryRespDTO = this.iCmsGdsCategoryRSV
                    .queryCmsGdsCategory(reqDTO);
            cmsGdsCategoryRespDTO.setCatgCodeZH(reqVO.getCatgCodeZH());
            ObjectCopyUtil.copyObjValue(cmsGdsCategoryRespDTO, reqVO, null, true);
            respVO.setCmsGdsCategoryVO(reqVO);
            respVO.setResultFlag("ok");
        } catch (BusinessException e) {
            LogUtil.error(this.MODULE,
                    String.format("编辑分类信息异常，分类ID=%s", new Object[] { reqVO.getCatgId() }));
            respVO.setResultFlag("expt");
            respVO.setResultMsg(e.getErrorMessage());
        }
        return respVO;
    }
    /**
	 * loadNodes:(全量加载树节点). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author huangxm9
	 * @return
	 * @throws Exception
	 * @since JDK 1.6
	 */
    @RequestMapping(value="/loadnodes")
    @ResponseBody
    public String loadNodes(Model model, CmsGdsCategoryVO categoryVO){
        LogUtil.info(MODULE, "==========categoryVO:" + categoryVO.toString() + ";");
        
        CmsGdsCategoryReqDTO categoryReqDTO = new CmsGdsCategoryReqDTO();
        ObjectCopyUtil.copyObjValue(categoryVO, categoryReqDTO, null, false);
        
        categoryReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        List<CmsGdsCategoryRespDTO> categoryResDTOList = cmsGdsCategoryRSV.queryCmsCategoryInfo(categoryReqDTO);
//        List<CmsGdsCategoryRespDTO> virtualCategoryList = this.queryVirtualCategoryList();
//        categoryResDTOList.addAll(virtualCategoryList);
        if(CollectionUtils.isNotEmpty(categoryResDTOList))
        {
            List<CmsGdsCategoryTreeVO> trees = new ArrayList<CmsGdsCategoryTreeVO>();
            for (CmsGdsCategoryRespDTO resDTO:categoryResDTOList) 
            {
            	CmsGdsCategoryTreeVO node = new CmsGdsCategoryTreeVO();
                node.setId(String.valueOf(resDTO.getId()));
                node.setName(resDTO.getCatgName());
                node.setpId(null==resDTO.getCatgParent()?"-"+resDTO.getSiteId():resDTO.getCatgParent());
                node.setIsParent(false);
                for (CmsGdsCategoryRespDTO resDTO2:categoryResDTOList){
                	if(node.getId().toString().equals(resDTO2.getCatgParent())){
                		node.setIsParent(true);
                		break;
                	}
                } 
                node.setSiteId(String.valueOf(resDTO.getSiteId()));
                trees.add(node);
            }
            String json = JSONObject.toJSONString(trees);
            return json;
        }
        return null;
    }
    @RequestMapping({ "/asyncData/getNodes" })
    @ResponseBody
    public String getNodes(Model model, CmsGdsCategoryVO reqVO, HttpServletResponse response)
            throws Exception {
        LogUtil.info(this.MODULE, "获取分类树型节点,参数" + ToStringBuilder.reflectionToString(reqVO));
        List<CmsGdsCategoryTreeVO> trees = new ArrayList<CmsGdsCategoryTreeVO>();
        CmsGdsCategoryReqDTO reqDTO = new CmsGdsCategoryReqDTO();
        ObjectCopyUtil.copyObjValue(reqVO, reqDTO, null, true);
        String id = reqVO.getId();
        List<CmsGdsCategoryRespDTO> categorys = null;
        if (StringUtil.isBlank(reqVO.getId())) {
            categorys = this.iCmsGdsCategoryRSV.queryCmsGdsCategoryInit();
            categorys.addAll(this.queryVirtualCategoryList());
            for (int i = 0; i < categorys.size(); ++i) {
                CmsGdsCategoryTreeVO vo = buildCatalogNodeItem(
                        (CmsGdsCategoryRespDTO) categorys.get(i), null);
                trees.add(vo);
            }

        } else {
            // if (id.startsWith("CATLOG_ID_PREFIX")) {
            // id = id.replace("CATLOG_ID_PREFIX", "");
            // reqDTO.setCatgId(id);
            // }
            reqDTO.setId(id);
            CmsGdsCategoryReqDTO cmsGdsCategoryReqDTO = new CmsGdsCategoryReqDTO();
            ObjectCopyUtil.copyObjValue(reqDTO, cmsGdsCategoryReqDTO, null, true);
            categorys = this.iCmsGdsCategoryRSV.queryCmsCategorySons(reqDTO);
            convert2TreeList(trees, categorys);
        }

        String json = JSONObject.toJSONString(trees);
        LogUtil.info(this.MODULE, String.format("返回JSON数据：%s", new Object[] { json }));
        return json;
    }

    @RequestMapping({ "/asyncData/selector/getNodes" })
    @ResponseBody
    public String getSelectorNodes(Model model, CmsGdsCategoryVO reqVO,
            HttpServletResponse response) throws Exception {
        LogUtil.info(this.MODULE, "获取分类树型节点,参数" + ToStringBuilder.reflectionToString(reqVO));
        List<CmsGdsCategoryTreeVO> trees = new ArrayList<CmsGdsCategoryTreeVO>();

        CmsGdsCategoryReqDTO reqDTO = new CmsGdsCategoryReqDTO();
        ObjectCopyUtil.copyObjValue(reqVO, reqDTO, null, true);
        // String id = reqVO.getCatgId();

        if ((StringUtil.isBlank(reqVO.getId())) && (reqVO.getShowRoot() == 0)) {
            List<CmsGdsCategoryRespDTO> catgList = null;
            CmsGdsCategoryReqDTO dto = new CmsGdsCategoryReqDTO();
            dto.setCatgName(reqVO.getCatgName());
            dto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
            if(StringUtil.isNotBlank(reqVO.getSiteId())){
            	dto.setSiteId(Long.valueOf(reqVO.getSiteId()));
            }else{
            	dto.setSiteId(Long.valueOf(reqVO.getCurrentSiteId()));
            }
            catgList = iCmsGdsCategoryRSV.queryCmsCategoryInfo(dto);
            
            convert2TreeList(trees, catgList);
        } else {
            List<CmsGdsCategoryRespDTO> catgList = null;
            catgList = this.iCmsGdsCategoryRSV.queryCmsCategorySons(reqDTO);
            convert2TreeList(trees, catgList);
        }

        String json = JSONObject.toJSONString(trees);
        LogUtil.info(this.MODULE, String.format("返回JSON数据：%s", new Object[] { json }));
        return json;
    }

    @RequestMapping({ "/asyncData/catgsearch" })
    @ResponseBody
    public String categorySearch(Model model, CmsCategoryVO reqVO) throws Exception {
        LogUtil.info(this.MODULE, "搜索树型节点,参数" + ToStringBuilder.reflectionToString(reqVO));
        CmsGdsCategoryReqDTO dto = new CmsGdsCategoryReqDTO();
        dto.setCatgName(reqVO.getCatgName());
        dto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        List<CmsGdsCategoryRespDTO> page = iCmsGdsCategoryRSV.queryCmsCategoryInfo(dto);
        List<CmsGdsCategoryTreeVO> trees = new ArrayList<CmsGdsCategoryTreeVO>();
        if (page != null && page.size() > 0) {
            for (CmsGdsCategoryRespDTO cmsGdsCategoryRespDTO : page) {
                CmsGdsCategoryTreeVO vo = new CmsGdsCategoryTreeVO();
                vo.setId(cmsGdsCategoryRespDTO.getId());
                vo.setName(cmsGdsCategoryRespDTO.getCatgName());
                vo.setIsRoot(false);
                vo.setCatgLevel(cmsGdsCategoryRespDTO.getCatgLevel());
                vo.setIsParent(false);
                trees.add(vo);
            }
        }
        String json = JSONObject.toJSONString(trees);
        LogUtil.info(this.MODULE, String.format("返回JSON数据：%s", new Object[] { json }));
        return json;
    }

    @RequestMapping({ "/asyncData/selector/catgsearch" })
    @ResponseBody
    public String selectorCmsGdsCategorySearch(Model model, CmsGdsCategoryVO reqVO)
            throws Exception {
        LogUtil.info(MODULE, "搜索树型节点,参数" + ToStringBuilder.reflectionToString(reqVO));
        paramCheck(new Object[] { reqVO.getCatgName() }, new String[] { "catgName" });
        CmsGdsCategoryReqDTO dto = new CmsGdsCategoryReqDTO();
        dto.setCatgName(reqVO.getCatgName());
        if(StringUtil.isNotBlank(reqVO.getSiteId())){
        	dto.setSiteId(Long.valueOf(reqVO.getSiteId()));
        }else{
        	dto.setSiteId(Long.valueOf(reqVO.getCurrentSiteId()));
        }
        List<CmsGdsCategoryRespDTO> page = iCmsGdsCategoryRSV.queryCmsCategoryInfo(dto);
        List<CmsGdsCategoryTreeVO> trees = new ArrayList<CmsGdsCategoryTreeVO>();
        if (page != null && page.size() > 0) {
            for (CmsGdsCategoryRespDTO cmsGdsCategoryRespDTO : page) {
                CmsGdsCategoryTreeVO vo = new CmsGdsCategoryTreeVO();
                vo.setId(cmsGdsCategoryRespDTO.getId());
                vo.setName(cmsGdsCategoryRespDTO.getCatgName());
                vo.setCatgCode(cmsGdsCategoryRespDTO.getCatgCode());
                vo.setIsRoot(false);
                vo.setCatgLevel(cmsGdsCategoryRespDTO.getCatgLevel());
                vo.setIsParent(false);
                trees.add(vo);
            }
        }
        String json = JSONObject.toJSONString(trees);
        LogUtil.info(MODULE, String.format("返回JSON数据：%s", json));
        return json;
    }

    /**
     * 弹出框，选择树 popupCategorySelect:(这里用一句话描述这个方法的作用). <br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author gxq
     * @param model
     * @param reqVO
     * @return
     * @throws Exception
     * @since JDK 1.6
     * 
     * @修改：huangxm9 原因：分类管理中的分类被删除后，应该将有关联的已发布的分类位置管理中的记录也一并改成已失效
     */
    @RequestMapping({ "/open/catgselect" })
    public String popupCategorySelect(Model model, CmsCatgSelectorReqVO reqVO) throws Exception {
        LogUtil.info(MODULE,
                String.format("弹出分类选择树，[参数：%s]", ToStringBuilder.reflectionToString(reqVO)));
        if ("1".equals(reqVO.getCheckType())) {
            model.addAttribute("multi", "1");
        }
        model.addAttribute("reqVO", reqVO);
        return "/cms/gdsCategory/open/category-select";
    }

    @RequestMapping({ "/categorydel" })
    @ResponseBody
    public EcpBaseResponseVO deleteCategory(Model model,
            @RequestParam(value = "catgId", required = true) String catgId) throws Exception {
        LogUtil.info(this.MODULE, String.format("删除分类,分类ID=%s", new Object[] { catgId }));

        EcpBaseResponseVO respVO = new EcpBaseResponseVO();
        StringReqDTO reqDTO = new StringReqDTO();
        reqDTO.setValue(catgId);
        try {
            CmsGdsCategoryReqDTO condition = new CmsGdsCategoryReqDTO();
            condition.setId(catgId);

            List sonsCategoryRespDTOs = this.iCmsGdsCategoryRSV.queryCmsCategorySons(condition);
            if (sonsCategoryRespDTOs.size() > 0) {
                respVO.setResultFlag("expt");
                respVO.setResultMsg(ResourceMsgUtil.getMessage("此节点下存在有效子节点，无法进行删除。", null));
                return respVO;
            }
            this.iCmsGdsCategoryRSV.deleteCmsGdsCategory(catgId);
            // 分类管理中的分类被删除后，应该将有关联的已发布的分类位置管理中的记录也一并改成已失效 start
            CmsPlaceCategoryReqDTO cmsPlaceCategoryReqDTO = new CmsPlaceCategoryReqDTO();
            List<CmsPlaceCategoryRespDTO> list = null;
            cmsPlaceCategoryReqDTO.setCatgId(catgId);
            cmsPlaceCategoryReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
            list = iCmsPlaceCategoryRSV.queryCmsPlaceCategory(cmsPlaceCategoryReqDTO);
            for (int i = 0; i < list.size(); i++) {
                iCmsPlaceCategoryRSV.deleteCmsPlaceCategory(String.valueOf(list.get(i).getId()));
            }
            // end
            respVO.setResultFlag("ok");
        } catch (BusinessException e) {
            LogUtil.error(this.MODULE, String.format("删除分类异常，分类ID=%s", new Object[] { catgId }));
            respVO.setResultFlag("expt");
            respVO.setResultMsg(e.getErrorMessage());
        }
        return respVO;
    }

    @RequestMapping({ "/categoryview" })
    @ResponseBody
    public CmsGdsCategoryRespVO view(@RequestParam(value = "catgId", required = true) String catgId,
            Model model) throws Exception {
        LogUtil.info(this.MODULE, String.format("显示分类信息,参数：catgId=%s", new Object[] { catgId }));

        CmsGdsCategoryRespVO respVO = new CmsGdsCategoryRespVO();
        try {
            CmsGdsCategoryReqDTO reqDTO = new CmsGdsCategoryReqDTO();
            reqDTO.setId(catgId);
            CmsGdsCategoryRespDTO respDTO = this.iCmsGdsCategoryRSV.queryCmsGdsCategory(reqDTO);

            if ((respDTO == null) || (GdsUtils.isEqualsInvalid(respDTO.getStatus()))) {
                respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                respVO.setResultMsg(new BusinessException("显示商品分类遇到问题").getErrorMessage());
            }

            // 根据关联商品分类id查出相应的名称
            String catgCodeZHs = "";
            String[] catgCodes = null;
            if (StringUtil.isNotBlank(respDTO.getCatgCode())) {
                catgCodes = respDTO.getCatgCode().split("_");
            }
            if(null != catgCodes && 0 < catgCodes.length){
                int index = 0;
                for(String catg : catgCodes){
                    if(StringUtil.isNotBlank(catg)){
                        GdsCategoryReqDTO gdsCategoryReqDTO = new GdsCategoryReqDTO();
                        GdsCategoryRespDTO gdsCategoryRespDTO = new GdsCategoryRespDTO();
                        gdsCategoryReqDTO.setCatgCode(catg);
                        gdsCategoryRespDTO = this.gdsCategoryRSV.queryGdsCategoryByPK(gdsCategoryReqDTO);
                        if (gdsCategoryRespDTO != null){
                            if(0 != index){
                                catgCodeZHs += " , ";
                            }
                            catgCodeZHs += gdsCategoryRespDTO.getCatgName();
                            index ++;
                        }
                    }
                }
            }
                
            respDTO.setCatgCodeZH(catgCodeZHs);   
            CmsGdsCategoryVO vo = new CmsGdsCategoryVO();
            vo = (CmsGdsCategoryVO) GdsUtils.doObjConvert(respDTO, CmsGdsCategoryVO.class, null,null);
            vo.setCatgId(respDTO.getId());
            if (StringUtil.isNotBlank(respDTO.getMediaUuid())) {
                vo.setMediaURL(new AiToolUtil().genImageUrl(respDTO.getMediaUuid(), "300x200"));
            }
            model.addAttribute("respDTO", respDTO);
            if (StringUtil.isNotBlank(respDTO.getShowCatgId())) {
                CmsGdsCategoryReqDTO reqDTOName = new CmsGdsCategoryReqDTO();
                reqDTOName.setId(respDTO.getShowCatgId());
                CmsGdsCategoryRespDTO reqdto = this.iCmsGdsCategoryRSV
                        .queryCmsGdsCategory(reqDTOName);
                if (reqdto != null) {
                    vo.setShowCatgIdName(reqdto.getCatgName());
                }
            }
            respVO.setCmsGdsCategoryVO(vo);

        } catch (BusinessException e) {
            LogUtil.error(this.MODULE, "查询商品分类遇到异常！", e);
            respVO.setResultMsg(e.getErrorMessage());
            respVO.setResultFlag("expt");
            throw new BusinessException(e.getErrorCode());
        }

        return respVO;
    }

    @RequestMapping({ "/categorysave" })
    @ResponseBody
    public CmsGdsCategoryRespVO save(@Valid CmsGdsCategoryVO reqVO) throws Exception {
        LogUtil.info(this.MODULE, String.format("新增分类,参数：%s",
                new Object[] { ReflectionToStringBuilder.toString(reqVO) }));

        CmsGdsCategoryRespVO respVO = new CmsGdsCategoryRespVO();
        try {
            if (StringUtil.isNotBlank(reqVO.getCatgParent())) {
                CmsGdsCategoryReqDTO parentQueryDTO = new CmsGdsCategoryReqDTO();
                parentQueryDTO.setId(reqVO.getCatgParent());
                CmsGdsCategoryRespDTO parent = this.iCmsGdsCategoryRSV
                        .queryCmsGdsCategory(parentQueryDTO);
                if ((parent == null) || (GdsUtils.isEqualsInvalid(parent.getStatus()))) {
                    throw new BusinessException("error.goods.category.200311");
                }

            }

            CmsGdsCategoryReqDTO reqDTO = new CmsGdsCategoryReqDTO();

            ObjectCopyUtil.copyObjValue(reqVO, reqDTO, null, true);

            reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
            if (StringUtil.isEmpty(reqDTO.getSortNo())) {
                reqDTO.setSortNo(1);
            }
            if (StringUtil.isBlank(reqDTO.getIfLeaf())) {
                reqDTO.setIfLeaf(GdsConstants.Commons.STATUS_VALID);
            }
            CmsGdsCategoryRespDTO respDTO = this.iCmsGdsCategoryRSV.addCmsGdsCategory(reqDTO);
            if (respDTO != null && StringUtil.isNotBlank(respDTO.getCatgParent())) {
                // 更新父节点为不是叶子节点
                CmsGdsCategoryReqDTO dto = new CmsGdsCategoryReqDTO();
                CmsGdsCategoryRespDTO respDTO2 = new CmsGdsCategoryRespDTO();
                dto.setId(respDTO.getCatgParent());
                respDTO2 = iCmsGdsCategoryRSV.queryCmsGdsCategory(dto);
                // dto.setIfLeaf("0");
                respDTO2.setIfLeaf("0");
                // dto.setCatgName(respDTO.getCatgName());
                ObjectCopyUtil.copyObjValue(respDTO2, dto, null, true);
                CmsGdsCategoryRespDTO a = iCmsGdsCategoryRSV.updateCmsGdsCategory(dto);
            }

            ObjectCopyUtil.copyObjValue(respDTO, reqVO, null, false);
            reqVO.setCatgId(respDTO.getId());
            respVO.setCmsGdsCategoryVO(reqVO);
            respVO.setResultFlag("ok");
        } catch (BusinessException e) {
            LogUtil.error(this.MODULE, "新增商品分类异常", e);
            respVO.setResultMsg(e.getErrorMessage());
            respVO.setResultFlag("expt");
        }
        return respVO;
    }

    public Map<String, String> getExtMap() {
        return extMap;
    }

    public Long getMaxSize() {
        return Long.valueOf(102400L);
    }

    /*
     * 
     * convert2TreeList:分类信息转换成分类树列表. <br/>
     * 
     * @author huangxm9
     * 
     * @param trees
     * 
     * @param catgLst
     * 
     * @since JDK 1.6
     */
    private void convert2TreeList(List<CmsGdsCategoryTreeVO> trees,
            List<CmsGdsCategoryRespDTO> catgLst) {
        if (CollectionUtils.isNotEmpty(catgLst)) {
            for (CmsGdsCategoryRespDTO respDTO : catgLst) {
                CmsGdsCategoryTreeVO vo = new CmsGdsCategoryTreeVO();
                vo.setId(respDTO.getId());
                vo.setName(respDTO.getCatgName());
                vo.setCatgCode(respDTO.getCatgCode());
                vo.setIsRoot(false);
                // if (null != respDTO.getCatlogId()) {
                // vo.setCatlogId(respDTO.getCatgId().toString());
                if (StringUtil.isBlank(respDTO.getCatgParent())) {
                    vo.setpId(CATLOG_ID_PREFIX + respDTO.getId());
                } else {
                    vo.setpId(respDTO.getCatgParent());
                }
                // }
                vo.setCatgLevel(respDTO.getCatgLevel());
                // 如果指定显示最大节点
                // if (categoryReqDTO.getMaxShowNode() != null ||
                // !"".equals(categoryReqDTO.getMaxShowNode())) {
                // if (respDTO.getCatgLevel().toString().equals(categoryReqDTO.getMaxShowNode())) {
                // vo.setIsParent(false);
                // }else{
                // vo.setIsParent(GdsConstants.GdsCategory.IF_LEAF_0.equals(respDTO.getIfLeaf()) ?
                // true
                // : false);
                // }
                // }
                // else {
                vo.setIsParent(GdsConstants.GdsCategory.IF_LEAF_0.equals(respDTO.getIfLeaf()) ? true : false);
                // }
                trees.add(vo);
            }
        }
    }

    private List<CmsGdsCategoryRespDTO> loadCatalogRespDTO(CmsGdsCategoryVO reqVO) {
        CmsGdsCategoryReqDTO reqDTO = new CmsGdsCategoryReqDTO();
        reqDTO.setPageSize(Integer.valueOf(2147483647));
        reqDTO.setStatus("1");
        reqDTO.setId(reqVO.getCatgId());

        PageResponseDTO page = (PageResponseDTO) this.iCmsGdsCategoryRSV
                .queryCmsCategorySons(reqDTO);
        return page.getResult();
    }

    private CmsGdsCategoryTreeVO buildCatalogNodeItem(CmsGdsCategoryRespDTO cmsGdsCategoryRespDTO,
            Boolean isOpen) {
        CmsGdsCategoryTreeVO vo = new CmsGdsCategoryTreeVO();
        vo.setId(CATLOG_ID_PREFIX + cmsGdsCategoryRespDTO.getId());
        vo.setName(cmsGdsCategoryRespDTO.getCatgName());
        vo.setpId(null);
        vo.setCatgLevel(null);
        vo.setIsParent(true);
        vo.setIsRoot(false);
        vo.setClick(true);
        vo.setOpen(isOpen);
        return vo;
    }

    private boolean isLevelLimit(BaseSysCfgRespDTO sysCfgRespDTO, Short currentLevel) {
        return ((sysCfgRespDTO != null) && (StringUtil.isNotBlank(sysCfgRespDTO.getParaValue()))
                && (currentLevel != null) && (Short.valueOf(sysCfgRespDTO.getParaValue()).shortValue() < currentLevel.shortValue()));
    }
    
	/**
	 * querySiteList:(获取站点列表). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author huangxm9
	 * @return
	 * @throws Exception
	 * @since JDK 1.6
	 */
	private List<CmsSiteRespDTO> querySiteList(String status) throws Exception {
		CmsSiteReqDTO cmsSiteReqDTO = new CmsSiteReqDTO();
		if (StringUtils.isNotBlank(status)) {
			cmsSiteReqDTO.setStatus(status);
		}
		List<CmsSiteRespDTO> cmsSiteRespDTOList = cmsSiteRSV.queryCmsSiteList(cmsSiteReqDTO);
		return cmsSiteRespDTOList;
	}
	/**
	 * querySiteList:(获取站点列表). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author huangxm9
	 * @return
	 * @throws Exception
	 * @since JDK 1.6
	 */
	private List<CmsGdsCategoryRespDTO> queryVirtualCategoryList() {
		CmsSiteReqDTO cmsSiteReqDTO = new CmsSiteReqDTO();
		cmsSiteReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
		List<CmsSiteRespDTO> cmsSiteRespDTOList = cmsSiteRSV.queryCmsSiteList(cmsSiteReqDTO);
		List<CmsGdsCategoryRespDTO> list=new ArrayList<CmsGdsCategoryRespDTO>();
		for (CmsSiteRespDTO siteRespDTO: cmsSiteRespDTOList) {
			CmsGdsCategoryRespDTO gdsCategoryRespDTO=new CmsGdsCategoryRespDTO();
			gdsCategoryRespDTO.setId("-"+siteRespDTO.getId());
			gdsCategoryRespDTO.setCatgName(siteRespDTO.getSiteName());
			gdsCategoryRespDTO.setCatgParent("0");
			list.add(gdsCategoryRespDTO);
		}
		return list;
	}
}




