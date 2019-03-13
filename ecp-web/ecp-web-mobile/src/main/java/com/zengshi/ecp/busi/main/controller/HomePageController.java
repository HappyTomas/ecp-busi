package com.zengshi.ecp.busi.main.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zengshi.ecp.base.controller.EcpBaseController;
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
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**微信商城-首页
 * Title: ECP <br>
 * Project Name:ecp-web-mobile <br>
 * Description: <br>
 * Date:2016年8月16日下午5:32:01  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author jiangzh
 * @version  
 * @since JDK 1.6 
 */  
@Controller
@RequestMapping(value = "/homepage")
public class HomePageController extends EcpBaseController {

    private static String MODULE = HomePageController.class.getName();

    private static Long PAGETYPEID = 50L; //微信首页页面类型为50
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
     * init:页面初始化，requestMapping如果不写的话，访问地址同：Controller类的 requestmapping的URL TODO(这里描述这个方法适用条件 –
     * 可选).<br/>
     * 
     * @author jiangzh
     * @return
     * @since JDK 1.6
     */
    @RequestMapping()
    public String init(Model model,HttpSession session) {
    	String url = "/main/homepage/homepage";// 返回页面
    	try {
    		boolean pc=false;
        	CmsPageInfoReqDTO arg0 = new CmsPageInfoReqDTO();
            arg0.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
            arg0.setSiteId(arg0.getCurrentSiteId());
            arg0.setPageTypeId(PAGETYPEID);
            List<CmsPageInfoRespDTO> pageInfoList = cmsPageInfoRSV.queryCmsPageInfoList(arg0);
            CmsPageInfoRespDTO pageInfo=null;
            if(pageInfoList!=null&&pageInfoList.size()>0){
            	pageInfo=pageInfoList.get(0);
            }else{
            	model.addAttribute("noRelease", true);
            	return url;
            }
            List<CmsLayoutPubRespDTO> pageConfig = new ArrayList<CmsLayoutPubRespDTO>();
            CmsPageInfoReqDTO pinfo = new CmsPageInfoReqDTO();
            pinfo.setId(pageInfo.getId());
            pageConfig = cmsPageConfigRSV.initPageConfigPub(pinfo);
        	CmsPageAttrPubRespDTO pageAttr = this.qryPageAttrPubByPgId(pageInfo.getPageTypeId());
        	model.addAttribute("pageConfig", pageConfig);
        	model.addAttribute("pageTypeId", pageInfo.getPageTypeId());
        	model.addAttribute("pageId", pageInfo.getId());
        	model.addAttribute("pageAttr",pageAttr);
        	//用于标识是否是看的发布页面
        	model.addAttribute("pagePub", true);
        	model.addAttribute("pc", pc);
        	model.addAttribute("pageInfo", pageInfo);
        	session.setAttribute("currSiteId", "1");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
        return url;
    }
    /**
     * 
     * setSiteSession:（设置当前站点session 用于在底部导航确定地址，由于商城与积分商城公用个人中心页面引起). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param siteId
     * @param session
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/setSiteSession")
    public Map<String,Object> setSiteSession(Long siteId,HttpSession session){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        if(StringUtil.isEmpty(siteId)){
            LogUtil.info(MODULE, "设置当前站点session,siteId为空");
            resultMap.put("resultFlag", "empty");
            return resultMap;
        }
        session.setAttribute("currSiteId", siteId.toString());
        resultMap.put("resultFlag", "ok");
        LogUtil.info(MODULE, "设置当前站点session,siteId:"+siteId);
        return resultMap;
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
