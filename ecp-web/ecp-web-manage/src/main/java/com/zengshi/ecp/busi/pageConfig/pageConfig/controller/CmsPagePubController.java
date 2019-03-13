/**
 * 
 */
package com.zengshi.ecp.busi.pageConfig.pageConfig.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPubReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPubRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutPubRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageAttrPubReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageAttrPubRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorGdsRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageAttrPubRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageConfigRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageInfoRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * CMS模板化 - 页面发布
 * 
 * @author Terry
 *
 */
@Controller
@RequestMapping(value="/page-pub")
public class CmsPagePubController extends EcpBaseController {

    @Resource(name = "cmsPageInfoRSV")
    private ICmsPageInfoRSV cmsPageInfoRSV;
    
    @Resource(name = "cmsPageAttrPubRSV")
    private ICmsPageAttrPubRSV cmsPageAttrPubRSV;
    
    @Resource(name = "cmsPageConfigRSV")
    private ICmsPageConfigRSV cmsPageConfigRSV;
    
    @Resource(name = "cmsFloorGdsRSV")
    private ICmsFloorGdsRSV cmsFloorGdsRSV;
    
    @Resource(name = "gdsInfoQueryRSV")
    private IGdsInfoQueryRSV gdsInfoQueryRSV;
    
    /**
     * 
     * 功能描述：初始化
     *
     * @author  曾海沥(Terry)
     * <p>创建日期 ：2016年5月13日 下午6:49:41</p>
     *
     * @param model
     * @param pageId
     * @return
     * @throws Exception
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping("/init")
    public String init(Model model, Long pageId,boolean pc, HttpServletRequest request) throws Exception{
    	 
        CmsPageInfoReqDTO arg0 = new CmsPageInfoReqDTO();
        arg0.setId(pageId);
        CmsPageInfoRespDTO pageInfo = cmsPageInfoRSV.queryCmsPageInfo(arg0);
        List<CmsLayoutPubRespDTO> pageConfig = new ArrayList<CmsLayoutPubRespDTO>();
        CmsPageInfoReqDTO pinfo = new CmsPageInfoReqDTO();
        pinfo.setId(pageId);
        pageConfig = cmsPageConfigRSV.initPageConfigPub(pinfo);
            
    	CmsPageAttrPubRespDTO pageAttr = this.qryPageAttrPubByPgId(pageId);
    	model.addAttribute("pageConfig", pageConfig);
    	model.addAttribute("pageTypeId", pageInfo.getPageTypeId());
    	model.addAttribute("pageId", pageId);
    	model.addAttribute("pageAttr",pageAttr);
    	//用于标识是否是看的发布页面
    	model.addAttribute("pagePub", true);
    	model.addAttribute("pc", pc);
    	model.addAttribute("pageInfo", pageInfo);
    	// WEB端页面
		if (CmsConstants.PlatformType.CMS_PLATFORMTYPE_01.equals(pageInfo.getPlatformType())) {
			return "/pageConfig/pageConfig/publish/page-common-publish";
		} else {// 移动端页面
			return "/pageConfig/pageConfigWap/publish/page-common-publish-wap";
		}
    }

    /**
     * 
     * 功能描述：根据布局项目读取布局项详细配置内容
     *
     * @author  曾海沥(Terry)
     * <p>创建日期 ：2016年5月11日 上午9:39:01</p>
     *
     * @param model
     * @return
     * @throws Exception
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping("/loadLayoutItemProp")
    @ResponseBody
    public JSONObject loadLayoutItemProp(Model model,Long itemId) throws Exception{
    	CmsLayoutItemPubReqDTO req = new CmsLayoutItemPubReqDTO();
    	//req.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
    	req.setId(itemId);
    	CmsLayoutItemPubRespDTO resp = cmsPageConfigRSV.queryLayoutItemPub(req);
    	JSONObject obj = new JSONObject();
    	obj.put("itemProp", resp);
    	return obj;
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
    private CmsPageAttrPubRespDTO qryPageAttrPubByPgId(Long pageId) throws Exception{
    	CmsPageAttrPubRespDTO pageAttrPub = null;
        if(StringUtil.isNotEmpty(pageId)){
            CmsPageAttrPubReqDTO reqDto = new CmsPageAttrPubReqDTO();
            reqDto.setPageId(pageId);
            reqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
            List<CmsPageAttrPubRespDTO> pageAttrPubs = null;
            pageAttrPubs = cmsPageAttrPubRSV.queryCmsAttrPubList(reqDto);
            if(CollectionUtils.isNotEmpty(pageAttrPubs)) pageAttrPub = pageAttrPubs.get(0);
            //扩展数据
            if(pageAttrPub !=null ){
                //扩展背景图展示方式
                if(StringUtil.isBlank(pageAttrPub.getBackgroupShowType())){//空则设置为不平铺
                	pageAttrPub.setBackgroupShowType(CmsConstants.BackgroupShowType.CMS_BACKGROUPSHOWTYPE_02);
                }
            }
        }
        return pageAttrPub;
    }
}
