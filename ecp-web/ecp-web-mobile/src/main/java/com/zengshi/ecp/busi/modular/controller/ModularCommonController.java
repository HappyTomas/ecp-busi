package com.zengshi.ecp.busi.modular.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.busi.modular.vo.ModularCommonVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageAttrPubRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageConfigRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageInfoRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-busi-ecp-web-mall <br>
 * Description:<br>
 * Date:2016年6月13日下午7:11:56  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author zhanbh
 * @version  
 * @since JDK 1.6
 */
@RequestMapping(value="/modularcommon")
@Controller
public class ModularCommonController extends EcpBaseController {
    @Resource(name = "cmsPageConfigRSV")
    private ICmsPageConfigRSV cmsPageConfigRSV;
    
    @Resource(name = "cmsPageInfoRSV")
    private ICmsPageInfoRSV cmsPageInfoRSV;
    
    @Resource(name = "cmsPageAttrPubRSV")
    private ICmsPageAttrPubRSV cmsPageAttrPubRSV;
    
    private static String MODULAR = ModularCommonController.class.getName();
    
    /** 
     * init:(商城访问页面配置管理发布的一般页面入口). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param model
     * @param modularCommonVO
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping()
    public String init(Model model,ModularCommonVO modularCommonVO,HttpSession session){
        CmsPageInfoRespDTO pageInfo = null;
        String returnUrl = "/modular/common/modular-common-page";
        //52 为二级页 返回二级页页面
        if(StringUtil.isNotEmpty(modularCommonVO.getPageTypeId()) && 52l == modularCommonVO.getPageTypeId().longValue()){
            returnUrl = "/modular/common/modular-common-page";
        }
        boolean pc=false;
        CmsPageInfoReqDTO pageInfoReqDTO = new CmsPageInfoReqDTO();
        ObjectCopyUtil.copyObjValue(modularCommonVO, pageInfoReqDTO, null, false);
        pageInfoReqDTO.setId(modularCommonVO.getPageId());
        pageInfoReqDTO.setPlatformType(CmsConstants.PlatformType.CMS_PLATFORMTYPE_03);
        try {
            pageInfo = cmsPageConfigRSV.accessPageInfoPub(pageInfoReqDTO);
            } catch (BusinessException e) {
                LogUtil.error(MODULAR, "获取发布页面属性信息失败！", e);
            } catch (Exception e) {
                LogUtil.error(MODULAR, "获取发布页面属性信息失败！", e);
        }
        if(pageInfo != null && pageInfo.getPageTypeId() != null){
            model.addAttribute("pageInfo", pageInfo);
            model.addAttribute("pageId", pageInfo.getId());
            model.addAttribute("shopId", pageInfo.getShopId());
            model.addAttribute("pageTypeId", pageInfo.getPageTypeId()+"");
            model.addAttribute("pageConfig", pageInfo.getLayoutPubRespDTOList());
            model.addAttribute("pageAttr",pageInfo.getPageAttrPubRespDTO());
            //用于标识是否是看的发布页面
            model.addAttribute("pagePub", true);
            model.addAttribute("pc", pc);
            //用于标记展示终端类型  当为app时表示是再app商城访问页面，像领取优惠券需做特殊处理
            model.addAttribute("displayPlatType", modularCommonVO.getDisplayPlatType());
            model.addAttribute("staffId", modularCommonVO.getStaffId());
            session.setAttribute("currSiteId", "1");
        }
        
        return returnUrl;
    }
    /** 
     * pageId:(商城访问页面配置管理发布的促销页面入口). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author yedw 
     * @param pageId 
     * @param model
     * @param modularCommonVO
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping({ "/prom/{pageId}" })
    public String prom(@PathVariable("pageId") String pageId,Model model,ModularCommonVO modularCommonVO,HttpSession session){
    	modularCommonVO.setPageId(Long.parseLong(pageId));
    	modularCommonVO.setPageTypeId(51l);//51为促销活动页
    	return this.init(model, modularCommonVO, session);
    }
    /** 
     * second:(商城访问页面配置管理发布的二级页面入口). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author yedw 
     * @param model
     * @param modularCommonVO
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping({ "/second/{pageId}" })
    public String second(@PathVariable("pageId") String pageId,Model model,ModularCommonVO modularCommonVO,HttpSession session){
        modularCommonVO.setPageId(Long.parseLong(pageId));
        modularCommonVO.setPageTypeId(52l);//52为二级页
        return this.init(model, modularCommonVO, session);
    }
}

