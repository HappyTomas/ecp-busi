package com.zengshi.ecp.busi.main.controller;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
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

/**商城-分类菜单
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2015年8月17日下午6:54:49 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author jiangzh
 * @version
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value = "/category")
public class CategoryController extends EcpBaseController {

    private static String MODULE = CategoryController.class.getName();
    
    @Resource(name = "cmsGdsCategoryRSV")
    private ICmsGdsCategoryRSV cmsGdsCategoryRSV;
    @Resource(name = "cmsPlaceCategoryRSV")
    private ICmsPlaceCategoryRSV cmsPlaceCategoryRSV;
    
    /**
     * qryCatgList:(获取商品分类树信息). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author zhanbh 2015.10.27
     * @param model
     * @param gdsSize
     * @param placeWidth
     * @param placeHeight
     * @param status
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @RequestMapping(value = "/qryCatgList")
    public String qryCatgList(Model model,ComponentReqVO reqVO) throws Exception {
        LogUtil.info(MODULE, "==========reqVO :" + reqVO.toString() + ";");

        String url = "/main/sidebarGdsCatg/sidebar-catagory-1";
        if(StringUtil.isNotEmpty(reqVO.getMenuType())){
            url = "/main/sidebarGdsCatg/sidebar-catagory-"+reqVO.getMenuType(); 
        }else{
            url = "/main/sidebarGdsCatg/sidebar-catagory-1";
        }
        
        CmsGdsCategoryRespDTO catgParent = null;// 父节点
        List<CmsGdsCategoryRespDTO> cmsGdsCatgRespDtoList = null;// 二级节点
        List<List<CmsGdsCategoryRespDTO>> cmsShowGdsCatgRespDtoList = new ArrayList<List<CmsGdsCategoryRespDTO>>();// 右侧显示节点
        
        try{
            if(StringUtil.isNotEmpty(reqVO.getPlaceId())){//内容位置ID
                // 1.根据内容Id 查找分类根节点
                CmsGdsCategoryReqDTO cmsGdsCatgReqDto = new CmsGdsCategoryReqDTO();
                CmsPlaceCategoryReqDTO cmsPlaceCatgReqDto = new CmsPlaceCategoryReqDTO();
                cmsPlaceCatgReqDto.setPlaceId(reqVO.getPlaceId());
                cmsPlaceCatgReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);//只查询有效的
                List<CmsPlaceCategoryRespDTO> catgParentList = cmsPlaceCategoryRSV.queryCmsPlaceCategory(cmsPlaceCatgReqDto);
                if (!CollectionUtils.isEmpty(catgParentList)) {
                    cmsGdsCatgReqDto.setId(catgParentList.get(0).getCatgId());
                    cmsGdsCatgReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);//只查询有效的
                    catgParent = cmsGdsCategoryRSV.queryCmsGdsCategory(cmsGdsCatgReqDto);
                }
                // 2. 根据根节点找二级节点
                if (catgParent != null && StringUtil.isNotBlank(catgParent.getId())) {
                    cmsGdsCatgReqDto = new CmsGdsCategoryReqDTO();// 更新dto
                    cmsGdsCatgReqDto.setId(catgParent.getId());
                    cmsGdsCatgReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);//只查询有效的
                    cmsGdsCatgRespDtoList = cmsGdsCategoryRSV.queryCmsCategorySons(cmsGdsCatgReqDto);
                }
                // 3.根据二级节点 查找三级节点
                if (!CollectionUtils.isEmpty(cmsGdsCatgRespDtoList)) {
                    // 3.1 找cms的子节点
                    cmsGdsCatgReqDto = new CmsGdsCategoryReqDTO();// 更新dto
                    for (CmsGdsCategoryRespDTO dto : cmsGdsCatgRespDtoList) {
                        if(StringUtil.isNotBlank(dto.getId())){
                            cmsGdsCatgReqDto.setId(dto.getId());
                            cmsGdsCatgReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);//只查询有效的
                            dto.setCmsChildCatg(cmsGdsCategoryRSV.queryCmsCategorySons(cmsGdsCatgReqDto));
                        }
                    }
                    // 3.2 找右边显示的的子节点
                    List<CmsGdsCategoryRespDTO> showChilCatgList = null;
                    cmsGdsCatgReqDto = new CmsGdsCategoryReqDTO();// 更新dto
                    for (CmsGdsCategoryRespDTO dto : cmsGdsCatgRespDtoList) {
                        showChilCatgList = null;
                        if (StringUtil.isNotBlank(dto.getShowCatgId())) {
                            cmsGdsCatgReqDto.setId(dto.getShowCatgId());
                            cmsGdsCatgReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);// 只查询有效的
                            showChilCatgList = cmsGdsCategoryRSV.queryCmsCategorySons(cmsGdsCatgReqDto);
                        }
                        // 3.2.1 查询cms四级节点
                        if (!CollectionUtils.isEmpty(showChilCatgList)) {
                            cmsGdsCatgReqDto = new CmsGdsCategoryReqDTO();// 更新dto
                            for (CmsGdsCategoryRespDTO showCatgRespDto : showChilCatgList) {
                                if (StringUtil.isNotBlank(showCatgRespDto.getId())) {
                                    cmsGdsCatgReqDto.setId(showCatgRespDto.getId());
                                    cmsGdsCatgReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);// 只查询有效的
                                    showCatgRespDto.setCmsChildCatg(cmsGdsCategoryRSV.queryCmsCategorySons(cmsGdsCatgReqDto));
                                }
                            } 
                        } 
                        dto.setCmsShowChildCatg(showChilCatgList);
                        
                        //将右侧显示分类放到一起  没有则放入一个空列表
                        if(!CollectionUtils.isEmpty(showChilCatgList)){
                            cmsShowGdsCatgRespDtoList.add(showChilCatgList);
                        }else{
                            cmsShowGdsCatgRespDtoList.add(new ArrayList<CmsGdsCategoryRespDTO>());
                        }
                    } 
                } 
            }
            
        }catch(Exception err){
            LogUtil.error(MODULE, "查询商品分类出现异常:",err);
        }
        
        model.addAttribute("catgParent", catgParent);
        model.addAttribute("catgChild", cmsGdsCatgRespDtoList);
        model.addAttribute("placeHeight", reqVO.getPlaceHeight());
        model.addAttribute("showCatg", cmsShowGdsCatgRespDtoList);
        return url;
    }

}
