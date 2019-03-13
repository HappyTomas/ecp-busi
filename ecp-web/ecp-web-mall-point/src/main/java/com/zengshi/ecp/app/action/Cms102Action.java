package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Cms102Req;
import com.zengshi.ecp.app.resp.Cms102Resp;
import com.zengshi.ecp.app.resp.cms.CategoryRespVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsGdsCategoryReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsGdsCategoryRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceCategoryReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceCategoryRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsGdsCategoryRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceCategoryRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.system.util.ParamsTool;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.SystemException;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 获取积分APP全部分类服务<br>
 * Date:2016-2-22下午6:51:19  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author zhanbh
 * @version  
 * @since JDK 1.6 
 */

@Service("cms102")
@Action(bizcode="cms102", userCheck=false)
@Scope("prototype")
public class Cms102Action extends AppBaseAction<Cms102Req, Cms102Resp> {

    private static String MODULE = Cms102Action.class.getName();
    
    private final Long PLACEID = 703l;
   
    @Resource(name = "cmsGdsCategoryRSV")
    private ICmsGdsCategoryRSV cmsGdsCategoryRSV;
    
    @Resource(name = "cmsPlaceCategoryRSV")
    private ICmsPlaceCategoryRSV cmsPlaceCategoryRSV;
    
    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        LogUtil.info(MODULE, "==========开始进入cms102 app获取全部分类服务============");

        if(this.request ==null){
            this.request = new Cms102Req();
        }
        // 1.根据内容位置获取根节点
        CmsPlaceCategoryReqDTO placeCatgReqDto = new CmsPlaceCategoryReqDTO();

        // 1.1 设置查询条件
        Long placeId = this.PLACEID;
        if (StringUtil.isNotEmpty(this.request.getPlaceId())) {
            placeId = this.request.getPlaceId();
        }
        placeCatgReqDto.setPlaceId(placeId);
        placeCatgReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);// 只查询有效的

        // 1.2 调用服务，查询内容位置栏目关系记录
        List<CmsPlaceCategoryRespDTO> placeCatgList = null;
        try {
            placeCatgList = cmsPlaceCategoryRSV.queryCmsPlaceCategory(placeCatgReqDto);
        } catch (Exception e) {
            LogUtil.info(MODULE, "==========cms102 app获取全部分类服务==查询内容位置栏目关系记录 发生异常============");
            placeCatgList = null;
            e.printStackTrace();
        }

        // 1.3 调用服务，查询根节点信息
        CmsGdsCategoryRespDTO catgParent = null;// 根节点
        CmsGdsCategoryReqDTO catgReqDto = new CmsGdsCategoryReqDTO();
        if (CollectionUtils.isNotEmpty(placeCatgList)) {
            catgReqDto.setId(placeCatgList.get(0).getCatgId());
            catgReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
            try {
                catgParent = cmsGdsCategoryRSV.queryCmsGdsCategory(catgReqDto);
            } catch (Exception e) {
                LogUtil.info(MODULE, "==========cms102 app获取全部分类服务==查询根节点详细信息 发生异常============");
                catgParent = null;
                e.printStackTrace();
            }
        }

        // 2.查询二级节点
        CmsGdsCategoryReqDTO childCatgReqDto = new CmsGdsCategoryReqDTO();
        List<CmsGdsCategoryRespDTO> childCatgList = null;// 二级节点
        if (catgParent != null && StringUtil.isNotBlank(catgParent.getId())) {
            childCatgReqDto.setId(catgParent.getId());
            childCatgReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
            try {
                childCatgList = cmsGdsCategoryRSV.queryCmsCategorySons(childCatgReqDto);
            } catch (Exception e) {
                LogUtil.info(MODULE, "==========cms102 app获取全部分类服务==查询二级节点详细信息 发生异常============");
                childCatgList = null;
                e.printStackTrace();
            }

        }

        // 3.查询三级节点
        if (!CollectionUtils.isEmpty(childCatgList)) {
            catgParent.setCmsChildCatg(childCatgList);
            for (CmsGdsCategoryRespDTO dto : childCatgList) {
                if (StringUtil.isNotBlank(dto.getId())) {
                    CmsGdsCategoryReqDTO childThreeCatgReqDto = new CmsGdsCategoryReqDTO();// 更新dto
                    childThreeCatgReqDto.setId(dto.getId());
                    childThreeCatgReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);// 只查询有效的
                    try {
                        dto.setCmsChildCatg(cmsGdsCategoryRSV.queryCmsCategorySons(childThreeCatgReqDto));
                    } catch (Exception e) {
                        LogUtil.info(MODULE,"==========cms102 app获取全部分类服务==查询三级节点详细信息 发生异常============");
                        e.printStackTrace();
                    }
                }
            }
        }

        // 4.转化数据
        List<CategoryRespVO> catgList = null;

        catgList = this.getCatgRespVOList(catgParent);

        if (CollectionUtils.isNotEmpty(catgList)) {
            this.response.setCatgList(catgList);
        }

    }

    private List<CategoryRespVO> getCatgRespVOList(CmsGdsCategoryRespDTO catgParent) {
        List<CategoryRespVO> catgVoList= new ArrayList<CategoryRespVO>();
       
        //转化二级分类
        if(catgParent!=null && CollectionUtils.isNotEmpty(catgParent.getCmsChildCatg())){
            List<CmsGdsCategoryRespDTO> childCatgList = catgParent.getCmsChildCatg();
            for(int i = 0; i<childCatgList.size();i++){
                CategoryRespVO catgVO = new CategoryRespVO();
                CmsGdsCategoryRespDTO catgDto = childCatgList.get(i);
               
                //基本信息复制
                ObjectCopyUtil.copyObjValue(catgDto, catgVO, null, false);
                
                if(StringUtil.isNotEmpty(catgVO.getId())){
                    //转化子分类
                    List<CategoryRespVO> chileCatgVOs = this.getCatgRespVOList(catgDto);
                    if(CollectionUtils.isNotEmpty(chileCatgVOs)){
                        String childStr = "";
                        for(int childCount = 0 ; childCount < chileCatgVOs.size() ;childCount++){
                            CategoryRespVO respVo = chileCatgVOs.get(childCount);
                            childStr += respVo.getCatgName();
                            if(childCount == chileCatgVOs.size()-1){
                                break;
                            }
                            childStr +="/";
                        }
                        catgVO.setChildStr(childStr);
                        catgVO.setChildCatg(chileCatgVOs);
                    }
                    
                    //图标地址转化
                    catgVO.setVfsUrl(ParamsTool.getImageUrl(catgDto.getMediaUuid(), ""));
                    //链接地址转换
                    if(StringUtil.isNotBlank(catgDto.getCatgCode())){
                        catgVO.setCatgUrl("/pmph/jfGoodList/pageInit?catgCode="+catgDto.getCatgCode());
                    }else{
                        catgVO.setCatgUrl("/pmph/jfGoodList/pageInit?keyWord="+catgDto.getCatgName());
                    }
                    catgVoList.add(catgVO);
                }
            }
        }
        
        return catgVoList;
    }

}

