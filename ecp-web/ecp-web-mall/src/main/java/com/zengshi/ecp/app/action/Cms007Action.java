package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Cms007Req;
import com.zengshi.ecp.app.resp.Cms007Resp;
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
 * Description: 获取APP全部分类服务<br>
 * Date:2016-2-22下午6:51:19  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author zhanbh
 * @version  
 * @since JDK 1.6 
 */

@Service("cms007")
@Action(bizcode="cms007", userCheck=false)
@Scope("prototype")
public class Cms007Action extends AppBaseAction<Cms007Req, Cms007Resp> {

    private static String MODULE = Cms007Action.class.getName();
    
    private final Long PLACEID = 2101l;
   
    @Resource(name = "cmsGdsCategoryRSV")
    private ICmsGdsCategoryRSV cmsGdsCategoryRSV;
    
    @Resource(name = "cmsPlaceCategoryRSV")
    private ICmsPlaceCategoryRSV cmsPlaceCategoryRSV;
    
    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        LogUtil.info(MODULE, "==========开始进入pmphCms007 app获取全部分类服务============");

        if(this.request ==null){
            this.request = new Cms007Req();
        }
        
        //获取需要查找的分类
        String catgId = "";
        try {
            catgId = this.initCatgId(this.request);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.error(MODULE, "==========pmphCms007 app获取全部分类服务==查询内容位置分类关系记录 发生异常============");
            e.printStackTrace();
            throw new Exception("获取分类异常！");
        }
        
        //获取子分类
        List<CmsGdsCategoryRespDTO> childCatgList = null;// 二级节点
        if(StringUtil.isNotBlank(catgId)){
            // 查询子节点
            CmsGdsCategoryReqDTO childCatgReqDto = new CmsGdsCategoryReqDTO();
            childCatgReqDto.setId(catgId);
            childCatgReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
            try {
                childCatgList = cmsGdsCategoryRSV.queryCmsCategorySons(childCatgReqDto);
            } catch (Exception e) {
                LogUtil.info(MODULE, "==========cms007 app获取全部分类服务==查询二级节点详细信息 发生异常============");
                e.printStackTrace();
                throw new Exception("获取子分类异常！");
            }
        }
        // 4.转化数据
        List<CategoryRespVO> catgList = null;

        catgList = this.getCatgRespVOList(childCatgList);

        if (CollectionUtils.isNotEmpty(catgList)) {
            this.response.setCatgList(catgList);
        }

    }
    /**
     * 
     * initCatgId:(根据请求参数  获取需要查找的有效分类id). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param req
     * @return 
     * @since JDK 1.6
     */
    private String initCatgId(Cms007Req req) throws BusinessException, SystemException, Exception {
        String catgId = req.getId();
        //1 没有指定分类则查找内容位置下的分类
        if(StringUtil.isBlank(catgId)){
            // 1.1根据内容位置获取根节点
            CmsPlaceCategoryReqDTO placeCatgReqDto = new CmsPlaceCategoryReqDTO();

            // 1.1 设置查询条件
            Long placeId = this.PLACEID;
            if (StringUtil.isNotEmpty(req.getPlaceId())) {
                placeId = req.getPlaceId();
            }
            placeCatgReqDto.setPlaceId(placeId);
            placeCatgReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);// 只查询有效的

            // 1.2 调用服务，查询内容位置栏目关系记录
            List<CmsPlaceCategoryRespDTO> placeCatgList = cmsPlaceCategoryRSV.queryCmsPlaceCategory(placeCatgReqDto);
            // 1.3 获取有效的分类id
            if (CollectionUtils.isNotEmpty(placeCatgList)) {
                for(CmsPlaceCategoryRespDTO dto : placeCatgList){
                    if(this.checkCatgUsefull(dto.getCatgId()) != null){
                        catgId = dto.getCatgId();
                        break;
                    }
                }
            }
        }else{
            if(this.checkCatgUsefull(catgId) == null){
                catgId = "";
            }
        }
        
        return catgId;
    }
    /**
     * 
     * checkCatgUsefull:(检查分类是否有效。有效则返回分类信息，无效返回null). <br/> 
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
    private CmsGdsCategoryRespDTO checkCatgUsefull(String catgId)throws BusinessException, SystemException, Exception{
        CmsGdsCategoryRespDTO catg = null;
        if(StringUtil.isNotBlank(catgId)){
            CmsGdsCategoryReqDTO catgReqDto = new CmsGdsCategoryReqDTO();
            catgReqDto.setId(catgId);
            try {
                catg = cmsGdsCategoryRSV.queryCmsGdsCategory(catgReqDto);
            } catch (Exception e) {
                LogUtil.error(MODULE, "==========pmphCms007 app获取全部分类服务==查询节点详细信息检查有效性 发生异常============");
                catg = null;
                throw e;
            }
            if(catg==null || StringUtil.isBlank(catg.getId()) || !CmsConstants.ParamStatus.CMS_PARAMSTATUS_1.equalsIgnoreCase(catg.getStatus())){
                catg = null;
            }
        }
        return catg;
    }
    /**
     * 
     * getCatgRespVOList:(转化数据). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param catgParent
     * @return 
     * @since JDK 1.6
     */
    private List<CategoryRespVO> getCatgRespVOList(List<CmsGdsCategoryRespDTO> childCatgList) {
        List<CategoryRespVO> catgVoList= new ArrayList<CategoryRespVO>();
        //转化二级分类
        if(CollectionUtils.isNotEmpty(childCatgList)){
            for(int i = 0; i<childCatgList.size();i++){
                CategoryRespVO catgVO = new CategoryRespVO();
                CmsGdsCategoryRespDTO catgDto = childCatgList.get(i);
               
                //基本信息复制
                ObjectCopyUtil.copyObjValue(catgDto, catgVO, null, false);
                
                if(StringUtil.isNotEmpty(catgVO.getId())){
                    //图标地址转化
                    catgVO.setVfsUrl(ParamsTool.getImageUrl(catgDto.getMediaUuid(), ""));
                    //链接地址转换
                    if(StringUtil.isNotBlank(catgDto.getCatgCode())){
                        catgVO.setCatgUrl("/pmph/goodList/pageInit?catgCode="+catgDto.getCatgCode());
                    }else{
                        catgVO.setCatgUrl("/pmph/goodList/pageInit?keyWord="+catgDto.getCatgName());
                    }
                    catgVoList.add(catgVO);
                }
            }
        }
        return catgVoList;
    }

}

