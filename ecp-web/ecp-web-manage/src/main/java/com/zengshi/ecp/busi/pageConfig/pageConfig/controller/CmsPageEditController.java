/**
 * 
 */
package com.zengshi.ecp.busi.pageConfig.pageConfig.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.pageConfig.pageConfig.vo.CmsLayoutItemReqVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPreReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPreRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutPreRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageAttrPreReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageAttrPreRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutPreRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutTypeRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageAttrPreRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageConfigRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageInfoRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageTypeRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * 
 * 模板管理 - 页面编辑
 * 
 * @author Terry
 *
 */
@Controller
@RequestMapping(value="/page-edit")
public class CmsPageEditController extends EcpBaseController {
    private String MODUAL = CmsPageEditController.class.getName();
    @Resource(name = "cmsPageInfoRSV")
    private ICmsPageInfoRSV cmsPageInfoRSV;
    @Resource(name = "cmsPageTypeRSV")
    private ICmsPageTypeRSV cmsPageTypeRSV;
    @Resource(name = "cmsLayoutPreRSV")
    private ICmsLayoutPreRSV cmsLayoutPreRSV;
    @Resource(name = "cmsLayoutTypeRSV")
    private ICmsLayoutTypeRSV cmsLayoutTypeRSV;
    @Resource(name = "cmsModularRSV")
    private ICmsModularRSV cmsModularRSV;
    @Resource(name = "cmsPageAttrPreRSV")
    private ICmsPageAttrPreRSV cmsPageAttrPreRSV;
    @Resource(name = "cmsPageConfigRSV")
    private ICmsPageConfigRSV cmsPageConfigRSV;
    
    /**
     * 
     * 功能描述：初始化页面编辑界面
     *
     * @author  曾海沥(Terry)
     * <p>创建日期 ：2016年5月13日 下午3:47:08</p>
     *
     * @param model
     * @param pageId
     * @return
     * @throws Exception
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping("/init")
    public String init(Model model, Long pageId) throws Exception{
    	model.addAttribute("pageType", "edit");
        CmsPageAttrPreRespDTO pageAttrPre = this.qryPageAttrPreByPgId(pageId);
    	CmsPageInfoRespDTO pageInfo = new CmsPageInfoRespDTO();
    	pageInfo.setId(pageId);
    	model.addAttribute("pageInfo", pageInfo);
    	model.addAttribute("pageAttrPre",pageAttrPre);
    	return "/pageConfig/pageConfig/pageConfig-index";
    }
    
    /**
     * 
     * 功能描述：页面编辑内容载入
     *
     * @author  曾海沥(Terry)
     * <p>创建日期 ：2016年5月13日 下午3:45:51</p>
     *
     * @param model
     * @param pageId
     * @return
     * @throws Exception
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
	@RequestMapping("/pageEdit")
	public String pageEdit(Model model, Long pageId) throws Exception {
		CmsPageInfoReqDTO arg0 = new CmsPageInfoReqDTO();
		arg0.setId(pageId);
		CmsPageInfoRespDTO pageInfo = cmsPageInfoRSV.queryCmsPageInfo(arg0);
		if (pageInfo != null) {
			model.addAttribute("pageTypeId", pageInfo.getPageTypeId());
		}
		List<CmsLayoutPreRespDTO> pageConfig = new ArrayList<CmsLayoutPreRespDTO>();
		CmsPageInfoReqDTO pinfo = new CmsPageInfoReqDTO();
        pinfo.setId(pageId);
		pageConfig = cmsPageConfigRSV.initPageConfigPre(pinfo);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("pageConfig", pageConfig);
		// WEB端页面
		if (CmsConstants.PlatformType.CMS_PLATFORMTYPE_01.equals(pageInfo.getPlatformType())) {
			return "/pageConfig/pageConfig/edit/page-common-edit";
		} else {// 移动端页面
			return "/pageConfig/pageConfigWap/edit/page-common-edit-wap";
		}
	}
	/**
	 * 
	 * getItemComponentVm:(获取布局项的模块入口VM). <br/> 
	 * TODO(这里描述这个方法适用条件 – 可选).<br/> 
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	 * 
	 * @author zhanbh 
	 * @param model
	 * @param reqVO
	 * @return
	 * @throws Exception 
	 * @since JDK 1.6
	 */
	@RequestMapping("/getItemComponentVm")
    public String getItemComponentVm(Model model, CmsLayoutItemReqVO reqVO) throws Exception {
	    String componentVmUrl = reqVO.getComponentVmUrl();
	    if(StringUtil.isNotBlank(componentVmUrl)){
	        componentVmUrl = componentVmUrl.replaceAll("\\.vm$", "");
	    }
	    CmsLayoutItemPreReqDTO reqDTO = new CmsLayoutItemPreReqDTO();
	    if(null == reqVO.getId()){
	        return componentVmUrl;
	    }
	    reqDTO.setId(reqVO.getId());
        CmsLayoutItemPreRespDTO itemInfo = null;
        try {
            itemInfo = cmsPageConfigRSV.queryLayoutItemPre(reqDTO);
        } catch (Exception e) {
            LogUtil.error(MODUAL, "查询布局项信息异常", e);
        }
        //获取页面信息  主要是wap会用到
        CmsPageInfoRespDTO pageInfo = null;
        if(null != itemInfo && null != itemInfo.getPageId()){
            CmsPageInfoReqDTO pageInfoReqDto = new CmsPageInfoReqDTO();
            pageInfoReqDto.setId(itemInfo.getPageId());
            try {
                pageInfo = cmsPageInfoRSV.queryCmsPageInfo(pageInfoReqDto);
            } catch (Exception e) {
                LogUtil.error(MODUAL, "查询页面信息异常", e);
            }
        }
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("crossItem", itemInfo);
        model.addAttribute("pageEdit", true);
        return componentVmUrl;
    }
    /**
     * 
     * 功能描述：获得页面属性
     *
     * @author  曾海沥(Terry)
     * <p>创建日期 ：2016年5月13日 下午4:17:16</p>
     *
     * @param pageId
     * @return
     * @throws Exception
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    private CmsPageAttrPreRespDTO qryPageAttrPreByPgId(Long pageId) throws Exception{
        CmsPageAttrPreRespDTO pageAttrPre = null;
        if(StringUtil.isNotEmpty(pageId)){
            CmsPageAttrPreReqDTO reqDto = new CmsPageAttrPreReqDTO();
            reqDto.setPageId(pageId);
            
            List<CmsPageAttrPreRespDTO> pageAttrPres = null;
            pageAttrPres = this.cmsPageAttrPreRSV.queryCmsAttrPreList(reqDto);
            if(CollectionUtils.isNotEmpty(pageAttrPres)){
                pageAttrPre = pageAttrPres.get(0);
            }
            //扩展数据
            if(pageAttrPre !=null ){
                //扩展背景图展示方式
                if(StringUtil.isBlank(pageAttrPre.getBackgroupShowType())){//空则设置为不平铺
                    pageAttrPre.setBackgroupShowType(CmsConstants.BackgroupShowType.CMS_BACKGROUPSHOWTYPE_02);
                }
            }
        }
        return pageAttrPre;
    }
    
    /**
     * 
     * 功能描述：移除页面布局项
     *
     * @author  曾海沥(Terry)
     * <p>创建日期 ：2016年5月13日 下午3:48:43</p>
     *
     * @param model
     * @param pageId
     * @return
     * @throws Exception
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @SuppressWarnings("unused")
	@RequestMapping("/removeLayoutItem")
    @ResponseBody
    public EcpBaseResponseVO removeLayoutItem(Model model,Long itemId) throws Exception{
    	EcpBaseResponseVO vo = new EcpBaseResponseVO();
    	try {
    		CmsLayoutItemPreReqDTO	 req = new CmsLayoutItemPreReqDTO();
        	req.setId(itemId);
        	CmsLayoutItemPreRespDTO resp = cmsPageConfigRSV.deleteModularInLayoutItemRre(req);
        	vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
		}
    	return vo;
    }
    /**
     * 
     * 功能描述：保存页面背景色等属性
     *
     * @author  曾海沥(Terry)
     * <p>创建日期 ：2016年5月13日 下午3:48:43</p>
     *
     * @param model
     * @param pageId
     * @return
     * @throws Exception
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping("/savePageAttr")
    @ResponseBody
    public EcpBaseResponseVO savePageAttr(Model model,CmsPageAttrPreReqDTO pageAttr) throws Exception{
    	EcpBaseResponseVO vo = new EcpBaseResponseVO();
    	
    	vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
    	
    	return vo;
    }
}

