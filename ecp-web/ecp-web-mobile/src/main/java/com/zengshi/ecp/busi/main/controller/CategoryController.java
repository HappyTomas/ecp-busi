package com.zengshi.ecp.busi.main.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.busi.main.vo.ComponentReqVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsGdsCategoryReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsGdsCategoryRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceCategoryReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceCategoryRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsGdsCategoryRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceCategoryRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**微信商城-分类菜单
 * Title: ECP <br>
 * Project Name:ecp-web-mobile <br>
 * Description: <br>
 * Date:2016年8月16日下午5:32:09  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author jiangzh
 * @version  
 * @since JDK 1.6 
 */  
@Controller
@RequestMapping(value = "/category")
public class CategoryController extends EcpBaseController {

    private static String MODULE = CategoryController.class.getName();
    
    //首页分类 place_id
    private static Long CATEGORY_PLACE_ID=2101L;
    
    @Resource(name = "cmsGdsCategoryRSV")
    private ICmsGdsCategoryRSV cmsGdsCategoryRSV;
    @Resource(name = "cmsPlaceCategoryRSV")
    private ICmsPlaceCategoryRSV cmsPlaceCategoryRSV;
    
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
        String url = "/main/category/category";// 返回页面
        CmsPlaceCategoryReqDTO cmsPlaceCatgReqDto = new CmsPlaceCategoryReqDTO();
        cmsPlaceCatgReqDto.setPlaceId(CATEGORY_PLACE_ID);
        cmsPlaceCatgReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);//只查询有效的
        List<CmsPlaceCategoryRespDTO> catgParentList = cmsPlaceCategoryRSV.queryCmsPlaceCategory(cmsPlaceCatgReqDto);
        CmsGdsCategoryRespDTO catgParent = null;// 父节点
        if (CollectionUtils.isNotEmpty(catgParentList) && catgParentList.get(0) != null) {
            // 1.查找分类根节点 且查找其两级有效子分类
            CmsGdsCategoryReqDTO cmsGdsCatgReqDto = new CmsGdsCategoryReqDTO();
            cmsGdsCatgReqDto.setId(catgParentList.get(0).getCatgId());
            cmsGdsCatgReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);//只查询有效的
            catgParent = cmsGdsCategoryRSV.queryCmsGdsCategory(cmsGdsCatgReqDto,(short) 3, CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
            model.addAttribute("catgParent", catgParent);
        }
        return url;
    }
    
    /**
     * qryCatgList:(获取商城首页的商品分类树信息). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param reqVO
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/qryMallCatgList")
    public String qryMallCatgList(Model model,ComponentReqVO reqVO) throws Exception {
        if(reqVO == null ){
            reqVO = new ComponentReqVO();
        }
        LogUtil.info(MODULE, "==========reqVO :" + reqVO.toString() + ";");
        CmsGdsCategoryRespDTO catgParent = null;// 父节点
        try{
            if(StringUtil.isNotEmpty(reqVO.getPlaceId())){//内容位置ID
                // 1.根据内容位置Id 查找分类根节点
                CmsPlaceCategoryReqDTO cmsPlaceCatgReqDto = new CmsPlaceCategoryReqDTO();
                cmsPlaceCatgReqDto.setPlaceId(reqVO.getPlaceId());
                cmsPlaceCatgReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);//只查询有效的
                List<CmsPlaceCategoryRespDTO> catgParentList = cmsPlaceCategoryRSV.queryCmsPlaceCategory(cmsPlaceCatgReqDto);
                if (CollectionUtils.isNotEmpty(catgParentList) && catgParentList.get(0) != null) {
                    // 1.查找分类根节点 且查找其两级有效子分类
                    CmsGdsCategoryReqDTO cmsGdsCatgReqDto = new CmsGdsCategoryReqDTO();
                    cmsGdsCatgReqDto.setId(catgParentList.get(0).getCatgId());
                    cmsGdsCatgReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);//只查询有效的
                    catgParent = cmsGdsCategoryRSV.queryCmsGdsCategory(cmsGdsCatgReqDto,(short) 2, CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                }
            }
        }catch(Exception err){
            LogUtil.error(MODULE, "查询商品分类出现异常:",err);
            model.addAttribute("errMsg", "查询分类出现异常！");
        }
        
        model.addAttribute("catgParent", catgParent);
        return reqVO.getReturnUrl();
    }

}
