package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Cms006Req;
import com.zengshi.ecp.app.resp.Cms006Resp;
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
 * Description: 获取APP首页分类服务<br>
 * Date:2016-2-22下午6:51:19  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author zhanbh
 * @version  
 * @since JDK 1.6 
 */

@Service("cms006")
@Action(bizcode="cms006", userCheck=false)
@Scope("prototype")
public class Cms006Action extends AppBaseAction<Cms006Req, Cms006Resp> {

    private static String MODULE = Cms006Action.class.getName();
   
    private final Long SITEID = 1L;
    
    private int childSize = 3;
    
    @Resource(name = "cmsGdsCategoryRSV")
    private ICmsGdsCategoryRSV cmsGdsCategoryRSV;
    
    @Resource(name = "cmsPlaceCategoryRSV")
    private ICmsPlaceCategoryRSV cmsPlaceCategoryRSV;
    
    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        LogUtil.info(MODULE, "==========开始进入cms006 app获取首页分类服务============");
        
        if(StringUtil.isNotEmpty(this.request.getCatgSize()) && this.request.getCatgSize() > 0){
            this.childSize = this.request.getCatgSize() - 1;
        }
        
        if(StringUtil.isNotEmpty(this.request.getPlaceId())){
           
            //1.根据内容位置获取根节点
            CmsPlaceCategoryReqDTO placeCatgReqDto = new CmsPlaceCategoryReqDTO();
            
            //1.1 设置查询条件
            placeCatgReqDto.setPlaceId(this.request.getPlaceId());
            
            placeCatgReqDto.setSiteId(this.SITEID);
            
            placeCatgReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);//只查询有效的
            
            //1.2 调用服务，查询内容位置栏目关系记录
            List<CmsPlaceCategoryRespDTO> placeCatgList = null; 
            try {
                placeCatgList = cmsPlaceCategoryRSV.queryCmsPlaceCategory(placeCatgReqDto);
            } catch (Exception e) {
                LogUtil.info(MODULE, "==========cms006 app获取首页分类服务==查询内容位置栏目关系记录 发生异常============");
                placeCatgList = null;
                e.printStackTrace();
            }
            
            //1.3 调用服务，查询根节点信息
            CmsGdsCategoryRespDTO catgParent = null;// 根节点
            CmsGdsCategoryReqDTO catgReqDto = new CmsGdsCategoryReqDTO();
            if(CollectionUtils.isNotEmpty(placeCatgList)){
                catgReqDto.setId(placeCatgList.get(0).getCatgId());
                catgReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                try {
                    catgParent = cmsGdsCategoryRSV.queryCmsGdsCategory(catgReqDto);
                } catch (Exception e) {
                    LogUtil.info(MODULE, "==========cms006 app获取首页分类服务==查询根节点详细信息 发生异常============");
                    catgParent = null;
                    e.printStackTrace();
                }
            }
            
            //2.查询二级节点
            CmsGdsCategoryReqDTO childCatgReqDto = new CmsGdsCategoryReqDTO();
            List<CmsGdsCategoryRespDTO> childCatgList = null;// 二级节点
            if (catgParent != null && StringUtil.isNotBlank(catgParent.getId())) {
                childCatgReqDto.setId(catgParent.getId());
                childCatgReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                try {
                    childCatgList = cmsGdsCategoryRSV.queryCmsCategorySons(childCatgReqDto);
                } catch (Exception e) {
                    LogUtil.info(MODULE, "==========cms007 app获取全部分类服务==查询二级节点详细信息 发生异常============");
                    childCatgList = null;
                    e.printStackTrace();
                }
            }
            
            //3.转化数据
            List<CategoryRespVO> catgList = null;
            if(CollectionUtils.isNotEmpty(childCatgList)){
                catgList = this.getCatgRespVOList (catgParent,childCatgList);
            }
            
            if(CollectionUtils.isNotEmpty(catgList)){
                this.response.setCatgList(catgList); 
            }
        }else{
            LogUtil.info(MODULE, "==========cms006 app获取首页分类服务==入参placeId 为空============");
        }
        
    }

    /**
     * 
     * getCatgRespVOList:(转化分类数据). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author 
     * @param catgParent
     * @param childCatgList
     * @return 
     * @since JDK 1.6
     */
    private List<CategoryRespVO> getCatgRespVOList(CmsGdsCategoryRespDTO catgParent,List<CmsGdsCategoryRespDTO> childCatgList) {
        List<CategoryRespVO> catgVoList= new ArrayList<CategoryRespVO>();
       
        //转化子分类
        if(CollectionUtils.isNotEmpty(childCatgList)){
            for(int i = 0,j=0;j<this.childSize && i<childCatgList.size();i++){
                CategoryRespVO catgVO = new CategoryRespVO();
                CmsGdsCategoryRespDTO catgDto = childCatgList.get(i);
               
                //基本信息复制
                ObjectCopyUtil.copyObjValue(catgDto, catgVO, null, false);
                
                if(StringUtil.isNotEmpty(catgVO.getId())){
                    //图片地址转化
                    catgVO.setVfsUrl(ParamsTool.getImageUrl(null, ""));
                    //链接地址转换
                    if(StringUtil.isNotBlank(catgDto.getCatgCode())){
                        catgVO.setCatgUrl("?goPage=goodlist?catgCode="+catgDto.getCatgCode());
                    }else{
                        catgVO.setCatgUrl("?goPage=goodlist?keyWord="+catgDto.getCatgName());
                    }
                    catgVoList.add(catgVO);
                    j++;
                }
            }
        }
        
        //转化父分类
        if(catgParent != null){
            CategoryRespVO catgVO = new CategoryRespVO();
            
            //基本信息复制
            ObjectCopyUtil.copyObjValue(catgParent, catgVO, null, false);
           
            //图片地址转化
            catgVO.setVfsUrl(ParamsTool.getImageUrl(null, ""));
            //链接地址转换
            catgVO.setCatgUrl("?goPage=ranking");
            
            if(StringUtil.isNotEmpty(catgVO.getId())){
                catgVoList.add(catgVO);
            }
            
        }
        return catgVoList;
    }

}

