package com.zengshi.ecp.busi.main.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.cms.dubbo.dto.CmsGdsCategoryReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsGdsCategoryRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceCategoryReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceCategoryRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsGdsCategoryRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceCategoryRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.server.front.exception.BusinessException;
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
    
    private static Long PLACEID = 703L;//分类对应的内容位置
    
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
    public String init(Model model) {
        LogUtil.info(MODULE, "进入微信端积分全部分类初始化方法");
        String url = "/main/category/category";// 返回页面
        
        String errMsg = null;
        String sonErrMsg = null;
        //查一级分类
        CmsGdsCategoryRespDTO parentCatg = null;
        try {
            parentCatg = this.getCatgInfoByPlaceId(PLACEID, (short)1);
        } catch (BusinessException e) {
            LogUtil.error(MODULE,"查询分类业务异常！", e);
            errMsg = e.getMessage();
        }catch (Exception e) {
            LogUtil.error(MODULE,"查询分类异常！", e);
            errMsg =  "查询分类异常！";
        }
        List<CmsGdsCategoryRespDTO> catgSons = null;//一级子节点
        if(parentCatg != null){
            catgSons = parentCatg.getCmsChildCatg();
        }
        //查第一个一级子节点的子节点
        List<CmsGdsCategoryRespDTO> sonNotes = null;
        if(CollectionUtils.isNotEmpty(catgSons)){
            CmsGdsCategoryRespDTO firstSon = catgSons.get(0);
            try {
                sonNotes = this.getSonNotes(firstSon.getId());
            } catch (BusinessException e) {
                LogUtil.error(MODULE,"查询子分类业务异常！", e);
                sonErrMsg =  e.getMessage();
            }catch (Exception e) {
                LogUtil.error(MODULE,"查询子分类异常！", e);
                sonErrMsg =  "查询分类异常！";
            }
        }
        
        //返回数据
        model.addAttribute("catgSons", catgSons);
        model.addAttribute("errMsg", errMsg);
        model.addAttribute("sonNotes", sonNotes);
        model.addAttribute("sonErrMsg", sonErrMsg);
        return url;
    }
    /**
     * 
     * getCatgSons:(获取指定id的分类直接子节点). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param model
     * @param id
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/getcatgsons")
    public String getCatgSons(Model model,@RequestParam("id") String id){
        LogUtil.info(MODULE, "进入微信端积分全部分类获取分类子节点方法,入参：{id=" + id + "}");
        String url = "main/category/list/category-list";// 返回页面
        
        List<CmsGdsCategoryRespDTO> sonNotes = null;
        String sonErrMsg = null;
        if(StringUtil.isNotBlank(id)){
            try {
                sonNotes = this.getSonNotes(id);
            } catch (BusinessException e) {
                LogUtil.error(MODULE,"查询子分类业务异常！", e);
                sonErrMsg =  e.getMessage();
            }catch (Exception e) {
                LogUtil.error(MODULE,"查询子分类异常！", e);
                sonErrMsg =  "查询分类异常！";
            }
        }
        model.addAttribute("sonNotes", sonNotes);
        model.addAttribute("sonErrMsg", sonErrMsg);
        return url;
    }
    /**
     * 
     * getSonNotes:(查找分类的直接子节点). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param catgId
     * @return 
     * @since JDK 1.6
     */
    private List<CmsGdsCategoryRespDTO> getSonNotes(String catgId){
        if(StringUtil.isBlank(catgId)){
            return null;
        }
        CmsGdsCategoryReqDTO cmsGdsCatgReqDto = new CmsGdsCategoryReqDTO();
        cmsGdsCatgReqDto.setId(catgId);
        cmsGdsCatgReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        
        List<CmsGdsCategoryRespDTO> sonNotes = null;
        try {
            sonNotes = cmsGdsCategoryRSV.queryCmsCategorySons(cmsGdsCatgReqDto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询id为"+catgId.toString()+"cms分类的直接子分类异常！", e);
            throw new BusinessException("分类查询异常");
        }
        return sonNotes;
    }
    /**
     * 
     * getCatgInfo:(查询分类详情信息). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param catgId  sonLevel  0为不查子分类  ，负数为差全部 子分类
     * @return 
     * @since JDK 1.6
     */
    private CmsGdsCategoryRespDTO getCatgInfo(String catgId,short sonLevel){
        if(StringUtil.isBlank(catgId)){
            return null;
        }
        CmsGdsCategoryReqDTO cmsGdsCatgReqDto = new CmsGdsCategoryReqDTO();
        cmsGdsCatgReqDto.setId(catgId);
        cmsGdsCatgReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        CmsGdsCategoryRespDTO catg = null;
        try {
            catg = cmsGdsCategoryRSV.queryCmsGdsCategory(cmsGdsCatgReqDto,sonLevel, CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询id为"+catgId.toString()+"cms分类的详情信息异常！", e);
            throw new BusinessException("分类查询异常");
        }
        return catg;
    }
    /**
     * 
     * getCatgInfoByPlaceId:(根据内容位置id查找分类信息). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param placeId
     * @param sonLevel 0为不查子分类  ，负数为差全部 子分类
     * @return 
     * @since JDK 1.6
     */
    private CmsGdsCategoryRespDTO getCatgInfoByPlaceId(Long placeId,short sonLevel){
        if(StringUtil.isEmpty(placeId)){
            return null;
        }
        CmsGdsCategoryRespDTO respDto = null;
        
        CmsPlaceCategoryReqDTO cmsPlaceCatgReqDto = new CmsPlaceCategoryReqDTO();
        cmsPlaceCatgReqDto.setPlaceId(placeId);
        cmsPlaceCatgReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);//只查询有效的
        List<CmsPlaceCategoryRespDTO> catgParentList = null;
        try {
            catgParentList = cmsPlaceCategoryRSV.queryCmsPlaceCategory(cmsPlaceCatgReqDto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询内容为位置id为"+placeId.toString()+"关联的cms分类的记录异常！", e);
            throw new BusinessException("分类初始查询异常");
        }
        if (CollectionUtils.isNotEmpty(catgParentList)) {
            // 1.查找分类根节点 且查找其1级有效子分类
            CmsPlaceCategoryRespDTO placeCatg = catgParentList.get(0);
            respDto = this.getCatgInfo(placeCatg.getCatgId(), (short)1);
        }
        return respDto;
    }
}
