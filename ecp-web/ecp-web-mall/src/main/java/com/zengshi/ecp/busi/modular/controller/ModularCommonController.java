package com.zengshi.ecp.busi.modular.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.busi.modular.vo.ModularCommonVO;
import com.zengshi.ecp.busi.shop.shopIndex.vo.ShopInfoVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageAttrPubRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageConfigRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageInfoRSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-busi-ecp-web-mall <br>
 * Description:<br>
 * Date:2016年6月13日下午7:11:56  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author gxq
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
    
    @Resource
    private IShopInfoRSV iShopInfoRSV;
    
    private static String MODULAR = ModularCommonController.class.getName();
    
    /** 
     * init:(商城访问页面配置管理发布的页面入口). <br/> 
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
    public String init(Model model,ModularCommonVO modularCommonVO){
        CmsPageInfoRespDTO pageInfo = new CmsPageInfoRespDTO();
        String returnUrl = "redirect:/shopIndex/"+modularCommonVO.getShopId();
        try {
            CmsPageInfoReqDTO pageInfoReqDTO = new CmsPageInfoReqDTO();
            pageInfoReqDTO.setId(modularCommonVO.getPageId());
            pageInfoReqDTO.setShopId(modularCommonVO.getShopId());
            pageInfoReqDTO.setPageTypeId(modularCommonVO.getPageTypeId());
            pageInfo = cmsPageConfigRSV.accessPageInfoPub(pageInfoReqDTO);
            if(pageInfo != null && pageInfo.getPageTypeId() != null){
                model.addAttribute("pageInfo", pageInfo);
                model.addAttribute("pageId", pageInfo.getId());
                model.addAttribute("shopId", pageInfo.getShopId());
                model.addAttribute("pageTypeId", pageInfo.getPageTypeId()+"");
                model.addAttribute("pageConfig", pageInfo.getLayoutPubRespDTOList());
                model.addAttribute("pageAttr",pageInfo.getPageAttrPubRespDTO());
                //用于标识是否是看的发布页面
                model.addAttribute("pagePub", true);
                if(pageInfo.getPageTypeId() == 1l){
                    try {
                        ShopInfoVO shopInfoVO = new ShopInfoVO();
                        ShopInfoResDTO shopInfoResDTO = iShopInfoRSV.findShopInfoByShopID(modularCommonVO.getShopId());
                        ObjectCopyUtil.copyObjValue(shopInfoResDTO, shopInfoVO, null, false);
                        shopInfoVO.setSmallLogoPathURL(ImageUtil.getImageUrl(shopInfoResDTO.getLogoPath() + "_48x48!"));
                        model.addAttribute("shopInfoResp", shopInfoVO);
                    } catch (BusinessException e) {
                        LogUtil.error(MODULAR, "获取店铺信息失败！", e);
                    } catch (Exception e) {
                        LogUtil.error(MODULAR, "获取店铺信息失败！", e);
                    }
                }
                if(pageInfo.getPageTypeId() == 1l){//店铺首页的
                    returnUrl =  "/modular/shopindex/modular-shop-index";
                }else{//其他页面暂时都走的公共入口
                    returnUrl = "/modular/common/modular-common-page";
                }
            }
        } catch (BusinessException e) {
            LogUtil.error(MODULAR, "获取发布页面属性信息失败！", e);
        } catch (Exception e) {
            LogUtil.error(MODULAR, "获取发布页面属性信息失败！", e);
        }
        return returnUrl;
    }
    
}

