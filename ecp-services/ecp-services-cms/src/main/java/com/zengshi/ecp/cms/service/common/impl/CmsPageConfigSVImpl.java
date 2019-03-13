package com.zengshi.ecp.cms.service.common.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dubbo.dto.CmsComponentReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsComponentRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPreReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPreRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPubReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPubRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutAttrPreReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutAttrPreRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutAttrPubReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPreReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPreRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPubReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPubRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutPreReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutPreRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutPubReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutPubRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutTypeReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutTypeRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularComponentReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularComponentRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageAttrPreReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageAttrPreRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageAttrPubReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPicHotPreReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPicHotPreRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPicHotPubReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLayoutItemReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLayoutItemRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLayoutReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLayoutRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLibReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLibRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants.ParamStatus;
import com.zengshi.ecp.cms.dubbo.util.CmsUtil;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsComponentSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsItemPropPreSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsItemPropPubSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutAttrPreSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutAttrPubSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutItemPreSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutItemPubSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutPreSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutPubSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutTypeSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsModularComponentSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsModularSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsPageAttrPreSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsPageAttrPubSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsPageConfigSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsPageInfoSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsPicHotPreSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsPicHotPubSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateLayoutItemSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateLayoutSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateLibSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-cms <br>
 * Description: <br>
 * Date:2015年8月7日下午5:11:43  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author jiangzh
 * @version  
 * @since JDK 1.6 
 */  
@Service("cmsPageConfigSV")
public class CmsPageConfigSVImpl extends GeneralSQLSVImpl implements ICmsPageConfigSV {
    
    @Resource
    private ICmsLayoutPreSV layoutPreSV;
    @Resource
    private ICmsLayoutPubSV layoutPubSV;
    @Resource
    private ICmsLayoutItemPreSV layoutItemPreSV;
    @Resource
    private ICmsLayoutItemPubSV layoutItemPubSV;
    @Resource
    private ICmsItemPropPreSV itemPropPreSV;
    @Resource
    private ICmsItemPropPubSV itemPropPubSV;
    @Resource
    private ICmsModularSV modularSV;
    @Resource
    private ICmsTemplateLibSV templateLibSV;
    @Resource
    private ICmsTemplateLayoutSV templateLayoutSV;
    @Resource
    private ICmsTemplateLayoutItemSV templateLayoutItemSV;
    @Resource
    private ICmsPageAttrPreSV pageAttrPreSV;
    @Resource
    private ICmsPageAttrPubSV pageAttrPubSV;
    @Resource
    private ICmsLayoutPreSV pageLayoutPreSV;
    @Resource
    private ICmsLayoutPubSV pageLayoutPubSV;
    @Resource
    private ICmsLayoutAttrPreSV layoutAttrPreSV;
    @Resource
    private ICmsLayoutAttrPubSV layoutAttrPubSV;
    @Resource
    private ICmsPicHotPreSV picHotPreSV;
    @Resource
    private ICmsPicHotPubSV picHotPubSV;
    @Resource
    private ICmsLayoutTypeSV layoutTypeSV;
    @Resource
    private ICmsPageInfoSV cmsPageInfoSV;
    @Resource
    private ICmsComponentSV componentSV;
    @Resource
    private ICmsModularComponentSV modularComponentSV;
    
    /** 
     * initPageConfigLayoutAndEdit:(初始化装修页面). <br/> 
     * TODO(1、初始化布局管理页面).<br/> 
     * TODO(2、初始化页面编辑页面).<br/> 
     * TODO(3、初始化预览页面).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    @Override
    public List<CmsLayoutPreRespDTO> initPageConfigPre(CmsPageInfoReqDTO dto) throws BusinessException{
        return queryLayoutPreList(dto);
    }
    
    /** 
     * initPageConfigLayoutAndEdit:(初始化装修页面). <br/> 
     * TODO(1、初始化布局管理页面).<br/> 
     * TODO(2、初始化页面编辑页面).<br/> 
     * TODO(3、初始化发布页面).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    @Override
    public List<CmsLayoutPubRespDTO> initPageConfigPub(CmsPageInfoReqDTO dto) throws BusinessException{
        return queryLayoutPubList(dto);
    }
    
    /** 
     * initTemplateConfig:(初始化模板装修页面). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    @Override
    public List<CmsTemplateLayoutRespDTO> initTemplateConfig(CmsTemplateLibReqDTO dto) throws BusinessException{
        return queryTemplateLayoutList(dto);
    }
    
    /** 
     * queryLayoutPreListLayoutAndEdit:(查询布局预览列表). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public List<CmsLayoutPreRespDTO> queryLayoutPreList(CmsPageInfoReqDTO dto) throws BusinessException{
        
        //1 根据页面ID查询布局(未发布，已发布)
        List<CmsLayoutPreRespDTO> layoutPreRespDTOList = getLayoutPreRespDTOList(dto);
        //2 根据布局ID查询布局项(未发布，已发布)及对应布局类型
        if(CollectionUtils.isNotEmpty(layoutPreRespDTOList)){
            for(CmsLayoutPreRespDTO layoutPreRespDTO:layoutPreRespDTOList){
                if(StringUtil.isNotEmpty(layoutPreRespDTO) && StringUtil.isNotEmpty(layoutPreRespDTO.getId())){
                    //2.1 查询布局类型
                    getLayoutPreRespDTO(layoutPreRespDTO);
                    //2.2 查询布局项列表
                    List<CmsLayoutItemPreRespDTO> layoutItemPreRespDTOList = qryLayoutItemPreRespDTOList(layoutPreRespDTO);
                    //2.3 封装布局项
                    Map<Integer,List<CmsLayoutItemPreRespDTO>> layoutItemPreRespDTOMap = qryLayoutItemPreRespDTOMap(layoutItemPreRespDTOList,layoutPreRespDTO);
                    //2.4 封装布局下的布局项，以列当作KEY,列下的布局项List为value.
                    layoutItemPreRespDTOMap = getLayoutItemPreRespDTOMap(layoutItemPreRespDTOMap,layoutItemPreRespDTOList);
                    //2.5 将布局项集合关联到相应的布局对象中
                    layoutPreRespDTO.setLayoutItemPreRespDTOMap(layoutItemPreRespDTOMap);
                }
            }
        }
        return layoutPreRespDTOList;
    }
    
    /** 
     * queryLayoutPubListLayoutAndEdit:(查询布局发布列表). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public List<CmsLayoutPubRespDTO> queryLayoutPubList(CmsPageInfoReqDTO dto) throws BusinessException{
        
        //1 根据页面ID查询布局(未发布，已发布)
        List<CmsLayoutPubRespDTO> layoutPubRespDTOList = getLayoutPubRespDTOList(dto);
        //2 根据布局ID查询布局项(未发布，已发布)及对应布局类型
        if(CollectionUtils.isNotEmpty(layoutPubRespDTOList)){
            for(CmsLayoutPubRespDTO layoutPubRespDTO:layoutPubRespDTOList){
                if(StringUtil.isNotEmpty(layoutPubRespDTO) && StringUtil.isNotEmpty(layoutPubRespDTO.getId())){
                    //2.1 查询布局类型
                    getLayoutPubRespDTO(layoutPubRespDTO);
                    //2.2 查询布局项列表
                    List<CmsLayoutItemPubRespDTO> layoutItemPubRespDTOList = qryLayoutItemPubRespDTOList(layoutPubRespDTO);
                    //2.3 封装布局项
                    Map<Integer,List<CmsLayoutItemPubRespDTO>> layoutItemPubRespDTOMap = qryLayoutItemPubRespDTOMap(layoutItemPubRespDTOList,layoutPubRespDTO);
                    //2.4 封装布局下的布局项，以列当作KEY,列下的布局项List为value.
                    layoutItemPubRespDTOMap = getLayoutItemPubRespDTOMap(layoutItemPubRespDTOMap,layoutItemPubRespDTOList);
                    //2.5 将布局项集合关联到相应的布局对象中
                    layoutPubRespDTO.setLayoutItemPubRespDTOMap(layoutItemPubRespDTOMap);
                }
            }
        }
        return layoutPubRespDTOList;
    }
    
    /** 
     * queryTemplateLayoutList:(查询模板布局列表). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public List<CmsTemplateLayoutRespDTO> queryTemplateLayoutList(CmsTemplateLibReqDTO dto) throws BusinessException{
        
        //1 根据模板ID查询布局(未发布，已发布)
        List<CmsTemplateLayoutRespDTO> templateLayoutRespDTOList = getTemplateLayoutRespDTOList(dto);
        //2 根据布局ID查询布局项(未发布，已发布)及对应布局类型
        if(CollectionUtils.isNotEmpty(templateLayoutRespDTOList)){
            for(CmsTemplateLayoutRespDTO templateLayoutRespDTO:templateLayoutRespDTOList){
                if(StringUtil.isNotEmpty(templateLayoutRespDTO) && StringUtil.isNotEmpty(templateLayoutRespDTO.getId())){
                    //2.1 查询布局类型
                    getTemplateLayoutRespDTO(templateLayoutRespDTO);
                    //2.2 查询布局项列表
                    List<CmsTemplateLayoutItemRespDTO> templateLayoutItemRespDTOList = qryTemplateLayoutItemRespDTOList(templateLayoutRespDTO);
                    //2.3 封装布局项
                    Map<Integer,List<CmsTemplateLayoutItemRespDTO>> templateLayoutItemRespDTOMap = qryTemplateLayoutItemRespDTOMap(templateLayoutItemRespDTOList,templateLayoutRespDTO);
                    //2.4 封装布局下的布局项，以列当作KEY,列下的布局项List为value.
                    templateLayoutItemRespDTOMap = getTemplateLayoutItemRespDTOMap(templateLayoutItemRespDTOMap,templateLayoutItemRespDTOList);
                    //2.5 将布局项集合关联到相应的布局对象中
                    templateLayoutRespDTO.setTemplateLayoutItemRespDTOMap(templateLayoutItemRespDTOMap);
                }
            }
        }
        return templateLayoutRespDTOList;
    }
    
    /** 
     * queryLayoutItemPre:(根据布局项ID查询布局项预览表及模块和布局项与属性预览表). <br/> 
     * TODO(1、根据布局项ID查询布局项预览表).<br/> 
     * TODO(2、根据模块ID查询模块表).<br/> 
     * TODO(3、根据布局项ID查询布局项与属性预览表).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    @Override
    public CmsLayoutItemPreRespDTO queryLayoutItemPre(CmsLayoutItemPreReqDTO dto) throws BusinessException{
        
        //1 根据布局项ID查询布局项
        CmsLayoutItemPreRespDTO layoutItemPreRespDTO = layoutItemPreSV.queryCmsLayoutItemPre(dto);
        if(layoutItemPreRespDTO != null){
            //2.1 根据模块ID查询模块
            if(StringUtil.isNotEmpty(layoutItemPreRespDTO) && StringUtil.isNotEmpty(layoutItemPreRespDTO.getModularId())){
                CmsModularReqDTO modularReqDTO = new CmsModularReqDTO();
                modularReqDTO.setId(layoutItemPreRespDTO.getModularId());
                CmsModularRespDTO modularRespDTO = modularSV.queryCmsModular(modularReqDTO);
                if(StringUtil.isNotEmpty(layoutItemPreRespDTO.getPageId())){
                    //获取页面类型
                    CmsPageInfoReqDTO  pageInfoReqDto = new CmsPageInfoReqDTO();
                    CmsPageInfoRespDTO pageInfo = null;
                    pageInfoReqDto.setId(layoutItemPreRespDTO.getPageId());
                    pageInfo = cmsPageInfoSV.queryCmsPageInfo(pageInfoReqDto);
                    if(null != pageInfo){
                        //获取模块对应组件
                        getModularRespDTO(modularRespDTO,pageInfo.getPageTypeId());
                    }
                }
                layoutItemPreRespDTO.setModularRespDTO(modularRespDTO);
            }
            //2.3 根据布局项ID查询布局项与属性关系
            if(StringUtil.isNotEmpty(layoutItemPreRespDTO) && StringUtil.isNotEmpty(layoutItemPreRespDTO.getId())){
                getItemPropPreRespDTOList(layoutItemPreRespDTO);
                /*CmsItemPropPreReqDTO itemPropPreReqDTO = new CmsItemPropPreReqDTO();
                itemPropPreReqDTO.setItemId(layoutItemPreRespDTO.getId());
                itemPropPreReqDTO.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
                List<CmsItemPropPreRespDTO> itemPropPreRespDTOList = itemPropPreSV.queryCmsItemPropPreList(itemPropPreReqDTO);
                layoutItemPreRespDTO.setItemPropPreRespDTOList(itemPropPreRespDTOList);*/
            }
        }
        return layoutItemPreRespDTO;
    }
    
    /** 
     * queryLayoutItemPub:(根据布局项ID查询布局项发布表及模块和布局项与属性发布表). <br/> 
     * TODO(1、根据布局项ID查询布局项发布表).<br/> 
     * TODO(2、根据模块ID查询模块表).<br/> 
     * TODO(3、根据布局项ID查询布局项与属性发布表).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    @Override
    public CmsLayoutItemPubRespDTO queryLayoutItemPub(CmsLayoutItemPubReqDTO dto) throws BusinessException{
        
        //1 根据布局项ID查询布局项
        CmsLayoutItemPubRespDTO layoutItemPubRespDTO = layoutItemPubSV.queryCmsLayoutItemPub(dto);
        if(layoutItemPubRespDTO != null){
            //2.1 根据模块ID查询模块
            if(StringUtil.isNotEmpty(layoutItemPubRespDTO) && StringUtil.isNotEmpty(layoutItemPubRespDTO.getModularId())){
                CmsModularReqDTO modularReqDTO = new CmsModularReqDTO();
                modularReqDTO.setId(layoutItemPubRespDTO.getModularId());
                CmsModularRespDTO modularRespDTO = modularSV.queryCmsModular(modularReqDTO);
                layoutItemPubRespDTO.setModularRespDTO(modularRespDTO);
            }
            //2.2 根据预览布局项ID查询布局项与属性关系（发布表与预览表逻辑上有些区别，取预览布局项ID）
            if(StringUtil.isNotEmpty(layoutItemPubRespDTO) && StringUtil.isNotEmpty(layoutItemPubRespDTO.getItemId())){
                getItemPropPubRespDTOList(layoutItemPubRespDTO);
                /*CmsItemPropPubReqDTO itemPropPubReqDTO = new CmsItemPropPubReqDTO();
                itemPropPubReqDTO.setItemId(layoutItemPubRespDTO.getItemId());//取预览布局项ID
                itemPropPubReqDTO.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
                List<CmsItemPropPubRespDTO> itemPropPubRespDTOList = itemPropPubSV.queryCmsItemPropPubList(itemPropPubReqDTO);
                layoutItemPubRespDTO.setItemPropPubRespDTOList(itemPropPubRespDTOList);*/
            }
        }
        return layoutItemPubRespDTO;
    }
    
    /** 
     * updatePageAttrPub:(更新页面属性到发布表). <br/> 
     * TODO(更新页面属性预览表).<br/> 
     * TODO(更新页面属性发布表).<br/> 
     * 
     * @author jiangzh 
     * @param dto
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public boolean updatePageAttrPub(CmsPageInfoReqDTO dto) throws BusinessException{
        if(dto==null || StringUtil.isEmpty(dto.getId())){
            return false;
        }
        //1 将发布表相关记录删除
        CmsPageAttrPubReqDTO attrPubReqDTO = new CmsPageAttrPubReqDTO();
        attrPubReqDTO.setPageId(dto.getId());
        attrPubReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        pageAttrPubSV.deleteCmsPageAttrPubByExample(attrPubReqDTO);
        
        //2 查询页面属性预览表,将页面属性预览表数据 拷贝至 页面属性发布表
        CmsPageAttrPreReqDTO pageAttrPreReqDTO = new CmsPageAttrPreReqDTO();
        pageAttrPreReqDTO.setPageId(dto.getId());
        pageAttrPreReqDTO.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
        //2.1 查询页面属性预览表  并将结果同步到发布表
        List<CmsPageAttrPreRespDTO>  pageAttrList = pageAttrPreSV.queryCmsPageAttrPreList(pageAttrPreReqDTO);
        if(CollectionUtils.isNotEmpty(pageAttrList)){
            for(CmsPageAttrPreRespDTO preRespDto : pageAttrList){
                if(preRespDto != null && StringUtil.isNotEmpty(preRespDto.getId())){
                    //将预览表标记为已发布
                    if(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0.equalsIgnoreCase(preRespDto.getStatus())){
                        CmsPageAttrPreReqDTO preReqDto = new CmsPageAttrPreReqDTO();
                        preReqDto.setId(preRespDto.getId());
                        preReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                        pageAttrPreSV.updateCmsPageAttrPre(preReqDto);
                    }
                    
                    //同步到发布表
                    CmsPageAttrPubReqDTO pubReqDto = new CmsPageAttrPubReqDTO();
                    ObjectCopyUtil.copyObjValue(preRespDto, pubReqDto, null, true);
                    pubReqDto.setId(null);
                    pubReqDto.setPageAttrId(preRespDto.getId());
                    pubReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                    this.pageAttrPubSV.addCmsPageAttrPub(pubReqDto);
                }
            }
        }
        return true;    
        
    }
    
    /** 
     * updatePageLayoutPub:(更新页面布局). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param dto
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public boolean updatePageLayoutPub(CmsPageInfoReqDTO dto) throws BusinessException{
        if(dto==null || StringUtil.isEmpty(dto.getId())){
            return false;
        }
        
        //1 将发布表相关记录删除
        CmsLayoutPubReqDTO layoutPubReqDTO = new CmsLayoutPubReqDTO();
        layoutPubReqDTO.setPageId(dto.getId());
        layoutPubReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        pageLayoutPubSV.deleteCmsLayoutPubByExample(layoutPubReqDTO);
        
        //2 查询页面属性预览表,将页面属性预览表数据 拷贝至 页面属性发布表
        CmsLayoutPreReqDTO layoutPreReqDTO = new CmsLayoutPreReqDTO();
        layoutPreReqDTO.setPageId(dto.getId());
        layoutPreReqDTO.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
        //2.1 查询页面属性预览表  并将结果同步到发布表
        List<CmsLayoutPreRespDTO>  layoutList = pageLayoutPreSV.queryCmsLayoutPreList(layoutPreReqDTO);
        if(CollectionUtils.isNotEmpty(layoutList)){
            for(CmsLayoutPreRespDTO preRespDto : layoutList){
                if(preRespDto != null && StringUtil.isNotEmpty(preRespDto.getId())){
                    //将预览表标记为已发布
                    if(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0.equalsIgnoreCase(preRespDto.getStatus())){
                        CmsLayoutPreReqDTO preReqDto = new CmsLayoutPreReqDTO();
                        preReqDto.setId(preRespDto.getId());
                        preReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                        pageLayoutPreSV.updateCmsLayoutPre(preReqDto);
                    }
                    
                    //同步到发布表
                    CmsLayoutPubReqDTO pubReqDto = new CmsLayoutPubReqDTO();
                    ObjectCopyUtil.copyObjValue(preRespDto, pubReqDto, null, true);
                    pubReqDto.setId(null);
                    pubReqDto.setLayoutId(preRespDto.getId());
                    pubReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                    this.pageLayoutPubSV.addCmsLayoutPub(pubReqDto);
                }
            }
        }    
        return true;
    }
    /** 
     * updatePageLayoutAttrPub:(更新页面布局). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param dto
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public boolean updatePageLayoutAttrPub(CmsPageInfoReqDTO dto) throws BusinessException{
        if(dto==null || StringUtil.isEmpty(dto.getId())){
            return false;
        }
            
        //1 将发布表相关记录删除
        CmsLayoutAttrPubReqDTO layoutAttrPubReqDTO = new CmsLayoutAttrPubReqDTO();
        layoutAttrPubReqDTO.setPageId(dto.getId());
        layoutAttrPubReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        layoutAttrPubSV.deleteCmsLayoutAttrPubByExample(layoutAttrPubReqDTO);
        
        //2 查询页面属性预览表,将页面属性预览表数据 拷贝至 页面属性发布表
        CmsLayoutAttrPreReqDTO layoutAttrPreReqDTO = new CmsLayoutAttrPreReqDTO();
        layoutAttrPreReqDTO.setPageId(dto.getId());
        layoutAttrPreReqDTO.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
        //2.1 查询页面属性预览表  并将结果同步到发布表
        List<CmsLayoutAttrPreRespDTO>  layoutAttrList = layoutAttrPreSV.queryCmsLayoutAttrPreList(layoutAttrPreReqDTO);
        if(CollectionUtils.isNotEmpty(layoutAttrList)){
            for(CmsLayoutAttrPreRespDTO preRespDto : layoutAttrList){
                if(preRespDto != null && StringUtil.isNotEmpty(preRespDto.getId())){
                    //将预览表标记为已发布
                    if(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0.equalsIgnoreCase(preRespDto.getStatus())){
                        CmsLayoutAttrPreReqDTO preReqDto = new CmsLayoutAttrPreReqDTO();
                        preReqDto.setId(preRespDto.getId());
                        preReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                        layoutAttrPreSV.updateCmsLayoutAttrPre(preReqDto);
                    }
                    
                    //同步到发布表
                    CmsLayoutAttrPubReqDTO pubReqDto = new CmsLayoutAttrPubReqDTO();
                    ObjectCopyUtil.copyObjValue(preRespDto, pubReqDto, null, true);
                    pubReqDto.setId(null);
                    pubReqDto.setLayoutAttrId(preRespDto.getId());
                    pubReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                    this.layoutAttrPubSV.addCmsLayoutAttrPub(pubReqDto);
                }
            }
        }
        return true;
    }
    
    /** 
     * updateLayoutItemPub:(更新页面布局项). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param dto
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public boolean updateLayoutItemPub(CmsPageInfoReqDTO dto) throws BusinessException{
        if(dto==null || StringUtil.isEmpty(dto.getId())){
            return false;
        }
         
        //1 将发布表相关记录删除
        CmsLayoutItemPubReqDTO layoutItemPubReqDTO = new CmsLayoutItemPubReqDTO();
        layoutItemPubReqDTO.setPageId(dto.getId());
        layoutItemPubReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        layoutItemPubSV.deleteCmsLayoutItemPubByExample(layoutItemPubReqDTO);
        
        //2 查询预览表,将预览表数据 拷贝至 发布表
        CmsLayoutItemPreReqDTO layoutItemPreReqDTO = new CmsLayoutItemPreReqDTO();
        layoutItemPreReqDTO.setPageId(dto.getId());
        layoutItemPreReqDTO.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
        //2.1 查询预览表  并将结果同步到发布表
        List<CmsLayoutItemPreRespDTO>  layoutItemList = layoutItemPreSV.queryCmsLayoutItemPreList(layoutItemPreReqDTO);
        if(CollectionUtils.isNotEmpty(layoutItemList)){
            for(CmsLayoutItemPreRespDTO preRespDto : layoutItemList){
                if(preRespDto != null && StringUtil.isNotEmpty(preRespDto.getId())){
                    //将预览表标记为已发布
                    if(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0.equalsIgnoreCase(preRespDto.getStatus())){
                        CmsLayoutItemPreReqDTO preReqDto = new CmsLayoutItemPreReqDTO();
                        preReqDto.setId(preRespDto.getId());
                        preReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                        layoutItemPreSV.updateCmsLayoutItemPre(preReqDto);
                    }
                    
                    //同步到发布表
                    CmsLayoutItemPubReqDTO pubReqDto = new CmsLayoutItemPubReqDTO();
                    ObjectCopyUtil.copyObjValue(preRespDto, pubReqDto, null, true);
                    pubReqDto.setId(null);
                    pubReqDto.setItemId(preRespDto.getId());
                    pubReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                    this.layoutItemPubSV.addCmsLayoutItemPub(pubReqDto);
                }
            }
        }
        return true;
    }
    
    /** 
     * updateItemPropPub:(更新页面布局项). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param dto
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public boolean updateItemPropPub(CmsPageInfoReqDTO dto) throws BusinessException{
        if(dto==null || StringUtil.isEmpty(dto.getId())){
            return false;
        }
        
        //1 将发布表相关记录删除
        CmsItemPropPubReqDTO itemPropPubReqDTO = new CmsItemPropPubReqDTO();
        itemPropPubReqDTO.setPageId(dto.getId());
        itemPropPubReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        itemPropPubSV.deleteCmsItemPropPubByExample(itemPropPubReqDTO);
        
        //2 查询预览表,将预览表数据 拷贝至 发布表
        CmsItemPropPreReqDTO itemPropPreReqDTO = new CmsItemPropPreReqDTO();
        itemPropPreReqDTO.setPageId(dto.getId());
        itemPropPreReqDTO.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
        //2.1 查询预览表  并将结果同步到发布表
        List<CmsItemPropPreRespDTO>  itemPropList = itemPropPreSV.queryCmsItemPropPreList(itemPropPreReqDTO);
        if(CollectionUtils.isNotEmpty(itemPropList)){
            for(CmsItemPropPreRespDTO preRespDto : itemPropList){
                if(preRespDto != null && StringUtil.isNotEmpty(preRespDto.getId())){
                    //将预览表标记为已发布
                    if(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0.equalsIgnoreCase(preRespDto.getStatus())){
                        CmsItemPropPreReqDTO preReqDto = new CmsItemPropPreReqDTO();
                        preReqDto.setId(preRespDto.getId());
                        preReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                        itemPropPreSV.updateCmsItemPropPre(preReqDto);
                    }
                    
                    //同步到发布表
                    CmsItemPropPubReqDTO pubReqDto = new CmsItemPropPubReqDTO();
                    ObjectCopyUtil.copyObjValue(preRespDto, pubReqDto, null, true);
                    pubReqDto.setId(null);
                    pubReqDto.setItemPropId(preRespDto.getId());
                    pubReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                    this.itemPropPubSV.addCmsItemPropPub(pubReqDto);
                }
            }
        }
        return true;
    }
    
    /** 
     * updatePicHotPub:(更新页面布局项). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param dto
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public boolean updatePicHotPub(CmsPageInfoReqDTO dto) throws BusinessException{
        if(dto==null || StringUtil.isEmpty(dto.getId())){
            return false;
        }
        //1 将发布表相关记录删除
        CmsPicHotPubReqDTO picHotPubReqDTO = new CmsPicHotPubReqDTO();
        picHotPubReqDTO.setPageId(dto.getId());
        picHotPubReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        picHotPubSV.deleteCmsPicHotPubByExample(picHotPubReqDTO);
        
        //2 查询预览表,将预览表数据 拷贝至 发布表
        CmsPicHotPreReqDTO picHotPreReqDTO = new CmsPicHotPreReqDTO();
        picHotPreReqDTO.setPageId(dto.getId());
        //picHotPreReqDTO.setItemPropId(itemPropId);
        picHotPreReqDTO.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
        //2.1 查询预览表  并将结果同步到发布表
        List<CmsPicHotPreRespDTO>  picHotList = picHotPreSV.queryCmsPicHotPreList(picHotPreReqDTO);
        if(CollectionUtils.isNotEmpty(picHotList)){
            for(CmsPicHotPreRespDTO preRespDto : picHotList){
                if(preRespDto != null && StringUtil.isNotEmpty(preRespDto.getId())){
                    //将预览表标记为已发布
                    if(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0.equalsIgnoreCase(preRespDto.getStatus())){
                        CmsPicHotPreReqDTO preReqDto = new CmsPicHotPreReqDTO();
                        preReqDto.setId(preRespDto.getId());
                        preReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                        picHotPreSV.updateCmsPicHotPre(preReqDto);
                    }
                    CmsItemPropPubReqDTO cmsItemPropPubReqDTO=new CmsItemPropPubReqDTO();
                    cmsItemPropPubReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                    cmsItemPropPubReqDTO.setItemPropId(preRespDto.getItemPropId());
                    List<CmsItemPropPubRespDTO> cmsItemPropPubRespDTOList=itemPropPubSV.queryCmsItemPropPubList(cmsItemPropPubReqDTO);
                    CmsItemPropPubRespDTO cmsItemPropPubRespDTO=new CmsItemPropPubRespDTO();
                    if(null!=cmsItemPropPubRespDTOList &&cmsItemPropPubRespDTOList.size()>0){
                    	cmsItemPropPubRespDTO=cmsItemPropPubRespDTOList.get(0);
                    }
                    //同步到发布表
                    CmsPicHotPubReqDTO pubReqDto = new CmsPicHotPubReqDTO();
                    ObjectCopyUtil.copyObjValue(preRespDto, pubReqDto, null, true);
                    pubReqDto.setId(null);
                    pubReqDto.setHotId(dto.getId());
                    pubReqDto.setItemPropId(cmsItemPropPubRespDTO.getId());
                    pubReqDto.setPicId(cmsItemPropPubRespDTO.getPropValue());
                    pubReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                    this.picHotPubSV.addCmsPicHotPub(pubReqDto);
                }
            }
        }
        return true;
    }
    
    /** 
     * deletePageInfo:(将页面失效). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param dto
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    @Override
    public boolean deletePageInfo(CmsPageInfoReqDTO dto) throws BusinessException{
        if(dto==null || StringUtil.isEmpty(dto.getId())){
            return false;
        }
        
        //1、将页面信息表失效
        cmsPageInfoSV.deleteCmsPageInfo(String.valueOf(dto.getId()));
        
        //2 将页面属性表（预览、发布）失效
        CmsPageAttrPreReqDTO attrPreReqDTO = new CmsPageAttrPreReqDTO();
        attrPreReqDTO.setPageId(dto.getId());
        attrPreReqDTO.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
        pageAttrPreSV.deleteCmsPageAttrPreByExample(attrPreReqDTO);
        CmsPageAttrPubReqDTO attrPubReqDTO = new CmsPageAttrPubReqDTO();
        attrPubReqDTO.setPageId(dto.getId());
        attrPubReqDTO.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
        pageAttrPubSV.deleteCmsPageAttrPubByExample(attrPubReqDTO);
        
        //3 将页面布局（预览、发布）失效
        CmsLayoutPreReqDTO layoutPreReqDTO = new CmsLayoutPreReqDTO();
        layoutPreReqDTO.setPageId(dto.getId());
        layoutPreReqDTO.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
        pageLayoutPreSV.deleteCmsLayoutPreByExample(layoutPreReqDTO);
        CmsLayoutPubReqDTO layoutPubReqDTO = new CmsLayoutPubReqDTO();
        layoutPubReqDTO.setPageId(dto.getId());
        layoutPubReqDTO.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
        pageLayoutPubSV.deleteCmsLayoutPubByExample(layoutPubReqDTO);
        
        //4 将布局属性（预览、发布）失效
        CmsLayoutAttrPreReqDTO layoutAttrPreReqDTO = new CmsLayoutAttrPreReqDTO();
        layoutAttrPreReqDTO.setPageId(dto.getId());
        layoutAttrPreReqDTO.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
        layoutAttrPreSV.deleteCmsLayoutAttrPreByExample(layoutAttrPreReqDTO);
        CmsLayoutAttrPubReqDTO layoutAttrPubReqDTO = new CmsLayoutAttrPubReqDTO();
        layoutAttrPubReqDTO.setPageId(dto.getId());
        layoutAttrPubReqDTO.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
        layoutAttrPubSV.deleteCmsLayoutAttrPubByExample(layoutAttrPubReqDTO);
        
        //5 将页面布局项（预览、发布）失效
        CmsLayoutItemPreReqDTO layoutItemPreReqDTO = new CmsLayoutItemPreReqDTO();
        layoutItemPreReqDTO.setPageId(dto.getId());
        layoutItemPreReqDTO.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
        layoutItemPreSV.deleteCmsLayoutItemPreByExample(layoutItemPreReqDTO);
        CmsLayoutItemPubReqDTO layoutItemPubReqDTO = new CmsLayoutItemPubReqDTO();
        layoutItemPubReqDTO.setPageId(dto.getId());
        layoutItemPubReqDTO.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
        layoutItemPubSV.deleteCmsLayoutItemPubByExample(layoutItemPubReqDTO);
        
        //6 将布局项属性（预览、发布）失效
        CmsItemPropPreReqDTO itemPropPreReqDTO = new CmsItemPropPreReqDTO();
        itemPropPreReqDTO.setPageId(dto.getId());
        itemPropPreReqDTO.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
        itemPropPreSV.deleteCmsItemPropPreByExample(itemPropPreReqDTO);
        CmsItemPropPubReqDTO itemPropPubReqDTO = new CmsItemPropPubReqDTO();
        itemPropPubReqDTO.setPageId(dto.getId());
        itemPropPubReqDTO.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
        itemPropPubSV.deleteCmsItemPropPubByExample(itemPropPubReqDTO);
        
        //7 将图片热点信息预览表数据 拷贝至 图片热点信息发布表
        CmsPicHotPreReqDTO picHotPreReqDTO = new CmsPicHotPreReqDTO();
        picHotPreReqDTO.setPageId(dto.getId());
        picHotPreReqDTO.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
        picHotPreSV.deleteCmsPicHotPreByExample(picHotPreReqDTO);
        CmsPicHotPubReqDTO picHotPubReqDTO = new CmsPicHotPubReqDTO();
        picHotPubReqDTO.setPageId(dto.getId());
        picHotPubReqDTO.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
        picHotPubSV.deleteCmsPicHotPubByExample(picHotPubReqDTO);
        
        return true;
    }
    
    /** 
     * savePageConfigPub:(保存装修发布). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param dto
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    @Override
    public boolean savePageConfigPub(CmsPageInfoReqDTO dto) throws BusinessException{
        if(dto==null || StringUtil.isEmpty(dto.getId())){
            return false;
        }
        //根据页面ID查询页面信息
        CmsPageInfoRespDTO entity = this.cmsPageInfoSV.queryCmsPageInfo(dto);
        CmsPageInfoReqDTO reqDTO=new CmsPageInfoReqDTO();
        if(1L==entity.getPageTypeId()){//店铺首页，将店铺的其他首页置为未发布
        	reqDTO.setShopId(entity.getShopId());
        	reqDTO.setPageTypeId(entity.getPageTypeId());
        	reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        	//查询页面信息，并将页面信息置为未发布
            List<CmsPageInfoRespDTO> list=cmsPageInfoSV.queryCmsPageInfoList(reqDTO);
            if(list!=null&&list.size()>0){
                for (CmsPageInfoRespDTO respDTO:list) {
                    if(!entity.getId().equals(respDTO.getId())){
                        cmsPageInfoSV.changeStatusCmsPageInfo(respDTO.getId().toString(), CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);
                    }
                }
            }
        }else if(50L==entity.getPageTypeId()){//移动端首页，将移动端首页的其他首页置为未发布
            reqDTO.setSiteId(entity.getSiteId());
            reqDTO.setPlatformType(entity.getPlatformType());
            reqDTO.setPageTypeId(entity.getPageTypeId());
            reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
            //查询页面信息，并将页面信息置为未发布
            List<CmsPageInfoRespDTO> list=cmsPageInfoSV.queryCmsPageInfoList(reqDTO);
            if(list!=null&&list.size()>0){
                for (CmsPageInfoRespDTO respDTO:list) {
                    if(!entity.getId().equals(respDTO.getId())){
                        cmsPageInfoSV.changeStatusCmsPageInfo(respDTO.getId().toString(), CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);
                    }
                }
            }
        }
        
        //1 将页面信息更新为发布
        dto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        cmsPageInfoSV.updateCmsPageInfo(dto);
        //2 将页面属性预览表数据 拷贝至 页面属性发布表
        this.updatePageAttrPub(dto);
        //3 将页面布局预览表数据 拷贝至 页面布局发布表
        this.updatePageLayoutPub(dto);
        //4 将布局属性预览表数据 拷贝至布局属性发布表
        this.updatePageLayoutAttrPub(dto);
        //5 将页面布局项预览表数据 拷贝至 页面布局项发布表
        this.updateLayoutItemPub(dto);
        //6 将布局项属性预览表数据 拷贝至 布局项属性发布表
        this.updateItemPropPub(dto);
        //7 将图片热点信息预览表数据 拷贝至 图片热点信息发布表
        this.updatePicHotPub(dto);
        
        return true;
    }
    
    /** 
     * savePageConfigToTemplate:(装修保存至模板). <br/> 
     * TODO(模板下的布局及布局项的状态都将传进来的模板状态一致   没传状态则为未发布).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    @Override
    public CmsTemplateLibRespDTO savePageConfigToTemplate(CmsTemplateLibReqDTO dto) throws BusinessException{
        if(dto==null || StringUtil.isEmpty(dto.getPageInfoId())){
            return null;
        }
        //1 保存模板表
        //1.1 设置参数
        if(StringUtil.isBlank(dto.getStatus())){
            dto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);
        }
        if(StringUtil.isBlank(dto.getIsDefTemplate())){
            dto.setIsDefTemplate(CmsConstants.IsNot.CMS_ISNOT_0);
        }
        
        CmsTemplateLibRespDTO templateLibRespDTO = null;
        if(StringUtil.isNotEmpty(dto.getId())){//替换当前模板
            templateLibRespDTO = templateLibSV.updateCmsTemplateLib(dto);
        }else{//新增模板
            templateLibRespDTO = templateLibSV.addCmsTemplateLibNoLayout(dto);
        }
           
        //2 根据页面ID查询布局(未发布，已发布)
        //2.1 查询预览表布局
        CmsLayoutPreReqDTO layoutPreReqDTO = new CmsLayoutPreReqDTO();
        layoutPreReqDTO.setPageId(dto.getPageInfoId());
        layoutPreReqDTO.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
        List<CmsLayoutPreRespDTO> layoutPreRespDTOList = layoutPreSV.queryCmsLayoutPreList(layoutPreReqDTO);
       
        //2.2 将预览表记录转化为模板布局参数
        if(CollectionUtils.isNotEmpty(layoutPreRespDTOList)){
            for(CmsLayoutPreRespDTO layoutPreRespDTO:layoutPreRespDTOList){
                if(layoutPreRespDTO !=null && StringUtil.isNotEmpty(layoutPreRespDTO.getId())){
                    CmsTemplateLayoutReqDTO templateLayoutReqDTO = new CmsTemplateLayoutReqDTO();//模板布局数据对象，用于入库
                    ObjectCopyUtil.copyObjValue(layoutPreRespDTO, templateLayoutReqDTO, null, false);
                    templateLayoutReqDTO.setId(null);
                    templateLayoutReqDTO.setTemplateId(templateLibRespDTO.getId());
                    templateLayoutReqDTO.setStatus(dto.getStatus());//跟模板的状态同步
                    
                    //2.3 查询预览表布局项数据
                    CmsLayoutItemPreReqDTO layoutItemPreReqDTO = new CmsLayoutItemPreReqDTO();
                    layoutItemPreReqDTO.setLayoutId(layoutPreRespDTO.getId());
                    layoutItemPreReqDTO.setPageId(layoutPreRespDTO.getPageId());
                    layoutItemPreReqDTO.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
                    List<CmsLayoutItemPreRespDTO> layoutItemPreRespDTOList = layoutItemPreSV.queryCmsLayoutItemPreList(layoutItemPreReqDTO); 
                    //2.4 将预览表记录转化为模板布局项参数  添加到模板布局参数
                    if(CollectionUtils.isNotEmpty(layoutItemPreRespDTOList)){
                        templateLayoutReqDTO.setTemplateLayoutItemReqDTOList(new ArrayList<CmsTemplateLayoutItemReqDTO>());
                        for(CmsLayoutItemPreRespDTO itemPreRestDTO : layoutItemPreRespDTOList){
                            CmsTemplateLayoutItemReqDTO templateLayoutItemReqDTO = new CmsTemplateLayoutItemReqDTO();//模板布局项数据对象，用于入库
                            ObjectCopyUtil.copyObjValue(itemPreRestDTO, templateLayoutItemReqDTO, null, false);
                            templateLayoutItemReqDTO.setId(null);
                            templateLayoutItemReqDTO.setStatus(dto.getStatus());//与模板状态同步
                            templateLayoutItemReqDTO.setTemplateId(templateLibRespDTO.getId());
                            //添加到模板布局参数
                            templateLayoutReqDTO.getTemplateLayoutItemReqDTOList().add(templateLayoutItemReqDTO);
                        }
                    }
                    //2.5 添加模板布局
                    this.templateLayoutSV.addCmsTemplateLayout(templateLayoutReqDTO);
                }
            }
        }
        
        return templateLibRespDTO;
    }
    
    /** 
     * saveTemplateToPageConfig:(模板复制到页面). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    @Override
    public CmsPageInfoRespDTO saveTemplateToPageConfig(CmsPageInfoReqDTO dto) throws BusinessException{
        if(StringUtil.isEmpty(dto.getTemplateId())){
            return null;
        }
        
        CmsPageInfoRespDTO pageInfoRespDTO = null;
        //保存页面信息
        if(StringUtil.isEmpty(dto.getId())){//新增
            pageInfoRespDTO = cmsPageInfoSV.addCmsPageInfo(dto);
        }else{//修改
            CmsPageInfoReqDTO oldReqDto= new CmsPageInfoReqDTO();
            oldReqDto.setId(dto.getId());
           
            pageInfoRespDTO = cmsPageInfoSV.updateCmsPageInfo(dto);
        }
        
        //使用模板
        if(pageInfoRespDTO!=null && StringUtil.isNotEmpty(pageInfoRespDTO.getId())){
            //1 失效当前页面下的所有布局及布局项
            if(StringUtil.isNotEmpty(dto.getId())){//修改
                CmsLayoutPreReqDTO layoutPreReqDTO = new CmsLayoutPreReqDTO();
                layoutPreReqDTO.setPageId(dto.getId());
                layoutPreReqDTO.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
                this.layoutPreSV.deleteCmsLayoutPreByExample(layoutPreReqDTO);
                
                CmsLayoutItemPreReqDTO layoutItemPreReqDTO = new CmsLayoutItemPreReqDTO();
                layoutItemPreReqDTO.setPageId(dto.getId());
                layoutItemPreReqDTO.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
                this.layoutItemPreSV.deleteCmsLayoutItemPreByExample(layoutItemPreReqDTO);
                
                // 将布局属性（预览、发布）失效
                CmsLayoutAttrPreReqDTO layoutAttrPreReqDTO = new CmsLayoutAttrPreReqDTO();
                layoutAttrPreReqDTO.setPageId(dto.getId());
                layoutAttrPreReqDTO.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
                layoutAttrPreSV.deleteCmsLayoutAttrPreByExample(layoutAttrPreReqDTO);
                // 将布局项属性（预览、发布）失效
                CmsItemPropPreReqDTO itemPropPreReqDTO = new CmsItemPropPreReqDTO();
                itemPropPreReqDTO.setPageId(dto.getId());
                itemPropPreReqDTO.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
                itemPropPreSV.deleteCmsItemPropPreByExample(itemPropPreReqDTO);
                
                //7将图片热点信息预览表数据 拷贝至 图片热点信息发布表
                CmsPicHotPreReqDTO picHotPreReqDTO = new CmsPicHotPreReqDTO();
                picHotPreReqDTO.setPageId(dto.getId());
                picHotPreReqDTO.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
                picHotPreSV.deleteCmsPicHotPreByExample(picHotPreReqDTO);
            }
            //2. 复制布局到页面
            //2.1 查询布局
            CmsTemplateLayoutReqDTO templateLayoutReqDTO = new CmsTemplateLayoutReqDTO();
            templateLayoutReqDTO.setTemplateId(dto.getTemplateId());
            templateLayoutReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
            List<CmsTemplateLayoutRespDTO> templateLayoutRespDTOList = templateLayoutSV.queryCmsTemplateLayoutList(templateLayoutReqDTO);
           
            //2.2 将预览表记录转化为模板布局参数
            if(CollectionUtils.isNotEmpty(templateLayoutRespDTOList)){
                for(CmsTemplateLayoutRespDTO templateLayoutRespDTO:templateLayoutRespDTOList){
                    if(templateLayoutRespDTO !=null && StringUtil.isNotEmpty(templateLayoutRespDTO.getId())){
                        CmsLayoutPreReqDTO layoutReqDTO = new CmsLayoutPreReqDTO();
                        ObjectCopyUtil.copyObjValue(templateLayoutRespDTO, layoutReqDTO, null, false);
                        layoutReqDTO.setId(null);
                        layoutReqDTO.setPageId(pageInfoRespDTO.getId());
                        layoutReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);
                        
                        //2.3 查询预览表布局项数据
                        CmsTemplateLayoutItemReqDTO templateLayoutItemReqDTO = new CmsTemplateLayoutItemReqDTO();
                        templateLayoutItemReqDTO.setLayoutId(templateLayoutRespDTO.getId());
                        templateLayoutItemReqDTO.setTemplateId(templateLayoutRespDTO.getTemplateId());
                        templateLayoutItemReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                        List<CmsTemplateLayoutItemRespDTO> templateLayoutItemRespDTOList = this.templateLayoutItemSV.queryCmsTemplateLayoutItemList(templateLayoutItemReqDTO); 
                        //2.4 将预览表记录转化为模板布局项参数  添加到模板布局参数
                        if(CollectionUtils.isNotEmpty(templateLayoutItemRespDTOList)){
                            layoutReqDTO.setLayoutItemPreList(new ArrayList<CmsLayoutItemPreReqDTO>());
                            for(CmsTemplateLayoutItemRespDTO templateItemRespDTO : templateLayoutItemRespDTOList){
                                CmsLayoutItemPreReqDTO layoutItemReqDTO = new CmsLayoutItemPreReqDTO();
                                ObjectCopyUtil.copyObjValue(templateItemRespDTO, layoutItemReqDTO, null, false);
                                layoutItemReqDTO.setId(null);
                                layoutItemReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);
                                layoutItemReqDTO.setPageId(pageInfoRespDTO.getId());
                                //添加到模板布局参数
                                layoutReqDTO.getLayoutItemPreList().add(layoutItemReqDTO);
                            }
                            
                            //2.5 添加模板布局
                            this.layoutPreSV.addCmsLayoutPre(layoutReqDTO);
                        }
                    }
                }
            }
        }
        
        return pageInfoRespDTO;
    }

    /**
     * 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageConfigSV#deleteModularInLayoutItemRre(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPubReqDTO)
     */
    @Override
    public CmsLayoutItemPreRespDTO deleteModularInLayoutItemRre(CmsLayoutItemPreReqDTO dto)
            throws BusinessException {
        if(dto == null || StringUtil.isEmpty(dto.getId())){
            return null;
        }
        
        CmsLayoutItemPreRespDTO layoutItemPreRespDto = null;
        layoutItemPreRespDto = this.layoutItemPreSV.queryCmsLayoutItemPre(dto);
        if(layoutItemPreRespDto == null || StringUtil.isEmpty(layoutItemPreRespDto.getId())){
            return null;
        }
        
        ObjectCopyUtil.copyObjValue(layoutItemPreRespDto, dto, null, true);
        Long modularId = layoutItemPreRespDto.getModularId();
        
        if(StringUtil.isEmpty(modularId)){
            return layoutItemPreRespDto;
        }
        
        //将布局项中的modular置空
        dto.setModularId(null);
        dto.setStatus(ParamStatus.CMS_PARAMSTATUS_0);
        layoutItemPreRespDto = this.layoutItemPreSV.updateCmsLayoutItemPreSensitive(dto);
        
        //删除对应的模块参数值
        CmsItemPropPreReqDTO itemPropRreReqDto = new CmsItemPropPreReqDTO();
        itemPropRreReqDto.setItemId(layoutItemPreRespDto.getId());
        itemPropRreReqDto.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
        this.itemPropPreSV.deleteCmsItemPropPreByExample(itemPropRreReqDto);
        
        return layoutItemPreRespDto;
    }
    
    /**
     * 
     * deleteModularInTemplateItem:(删除模板布局项中的模块). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    @Override
    public CmsTemplateLayoutItemRespDTO deleteModularInTemplateItem(
            CmsTemplateLayoutItemReqDTO dto) throws BusinessException {
        if(dto == null || StringUtil.isEmpty(dto.getId())){
            return null;
        }
        
        CmsTemplateLayoutItemRespDTO templateLayoutItemRespDTO = null;
        templateLayoutItemRespDTO = this.templateLayoutItemSV.queryCmsTemplateLayoutItem(dto);
        if(templateLayoutItemRespDTO == null || StringUtil.isEmpty(templateLayoutItemRespDTO.getId())){
            return null;
        }
        
        ObjectCopyUtil.copyObjValue(templateLayoutItemRespDTO, dto, null, true);
        Long modularId = templateLayoutItemRespDTO.getModularId();
        
        if(StringUtil.isEmpty(modularId)){
            return templateLayoutItemRespDTO;
        }
        
        //将布局项中的modular置空
        dto.setModularId(null);
        dto.setStatus(ParamStatus.CMS_PARAMSTATUS_0);
        templateLayoutItemRespDTO = this.templateLayoutItemSV.updateTemplateLayoutItemSensitive(dto);
        
        return templateLayoutItemRespDTO;
    }
    
    /** 
     * getLayoutPreRespDTOList:(查询布局列表-预览). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param pageInfoReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public List<CmsLayoutPreRespDTO> getLayoutPreRespDTOList(CmsPageInfoReqDTO pageInfoReqDTO) throws BusinessException{
        CmsLayoutPreReqDTO layoutPreReqDTO = new CmsLayoutPreReqDTO();
        layoutPreReqDTO.setPageId(pageInfoReqDTO.getId());
        layoutPreReqDTO.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
        return layoutPreSV.queryCmsLayoutPreList(layoutPreReqDTO);
    }
    
    /** 
     * getLayoutPubRespDTOList:(查询布局列表-发布). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param pageInfoReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public List<CmsLayoutPubRespDTO> getLayoutPubRespDTOList(CmsPageInfoReqDTO pageInfoReqDTO) throws BusinessException{
        CmsLayoutPubReqDTO layoutPubReqDTO = new CmsLayoutPubReqDTO();
        layoutPubReqDTO.setPageId(pageInfoReqDTO.getId());
        layoutPubReqDTO.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
        return layoutPubSV.queryCmsLayoutPubList(layoutPubReqDTO);
    }
    
    /** 
     * getTemplateLayoutRespDTOList:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param pageInfoReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public List<CmsTemplateLayoutRespDTO> getTemplateLayoutRespDTOList(CmsTemplateLibReqDTO templateLibReqDTO) throws BusinessException{
        CmsTemplateLayoutReqDTO templateLayoutReqDTO = new CmsTemplateLayoutReqDTO();
        templateLayoutReqDTO.setTemplateId(templateLibReqDTO.getId());
        templateLayoutReqDTO.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
        return templateLayoutSV.queryCmsTemplateLayoutList(templateLayoutReqDTO);
    }
    
    /** 
     * getLayoutPreRespDTO:(封装布局对象-预览). <br/> 
     * TODO(1、根据布局类型ID查询布局).<br/> 
     * TODO(2、将布局中布局项封装成MAP).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param layoutPreRespDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void getLayoutPreRespDTO(CmsLayoutPreRespDTO layoutPreRespDTO) throws BusinessException{
        if(StringUtil.isNotEmpty(layoutPreRespDTO.getLayoutTypeId())){
            CmsLayoutTypeReqDTO layoutTypeReqDTO = new CmsLayoutTypeReqDTO();
            layoutTypeReqDTO.setId(layoutPreRespDTO.getLayoutTypeId());
            CmsLayoutTypeRespDTO layoutTypeRespDTO = this.layoutTypeSV.queryCmsLayoutType(layoutTypeReqDTO);
            //布局项尺寸
            if(layoutTypeRespDTO != null && StringUtil.isNotEmpty(layoutTypeRespDTO.getLayoutItemSize())){
                String[] layoutItemSizeArray =layoutTypeRespDTO.getLayoutItemSize().split("\\|");
                if(layoutItemSizeArray != null && layoutItemSizeArray.length>0){
                    Map<String,String> layoutItemSizeMap = new HashMap<String,String>();
                    for(int i=0;i<layoutItemSizeArray.length;i++){
                        layoutItemSizeMap.put(String.valueOf(i), layoutItemSizeArray[i].trim());
                    }
                    layoutTypeRespDTO.setLayoutItemSizeMap(layoutItemSizeMap);
                }
            }
            layoutPreRespDTO.setCmsLayoutTypeRespDTO(layoutTypeRespDTO);
        }
    }
    
    /** 
     * getLayoutPubRespDTO:(封装布局对象-发布). <br/> 
     * TODO(1、根据布局类型ID查询布局).<br/> 
     * TODO(2、将布局中布局项封装成MAP).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param layoutPreRespDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void getLayoutPubRespDTO(CmsLayoutPubRespDTO layoutPubRespDTO) throws BusinessException{
        if(StringUtil.isNotEmpty(layoutPubRespDTO.getLayoutTypeId())){
            CmsLayoutTypeReqDTO layoutTypeReqDTO = new CmsLayoutTypeReqDTO();
            layoutTypeReqDTO.setId(layoutPubRespDTO.getLayoutTypeId());
            CmsLayoutTypeRespDTO layoutTypeRespDTO = this.layoutTypeSV.queryCmsLayoutType(layoutTypeReqDTO);
            //布局项尺寸
            if(layoutTypeRespDTO != null && StringUtil.isNotEmpty(layoutTypeRespDTO.getLayoutItemSize())){
                String[] layoutItemSizeArray =layoutTypeRespDTO.getLayoutItemSize().split("\\|");
                if(layoutItemSizeArray != null && layoutItemSizeArray.length>0){
                    Map<String,String> layoutItemSizeMap = new HashMap<String,String>();
                    for(int i=0;i<layoutItemSizeArray.length;i++){
                        layoutItemSizeMap.put(String.valueOf(i), layoutItemSizeArray[i].trim());
                    }
                    layoutTypeRespDTO.setLayoutItemSizeMap(layoutItemSizeMap);
                }
            }
            layoutPubRespDTO.setCmsLayoutTypeRespDTO(layoutTypeRespDTO);
        }
    }
    
    /** 
     * getTemplateLayoutRespDTO:(封装布局对象-模板). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param templateLayoutRespDTO
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void getTemplateLayoutRespDTO(CmsTemplateLayoutRespDTO templateLayoutRespDTO) throws BusinessException{
        if(StringUtil.isNotEmpty(templateLayoutRespDTO.getLayoutTypeId())){
            CmsLayoutTypeReqDTO layoutTypeReqDTO = new CmsLayoutTypeReqDTO();
            layoutTypeReqDTO.setId(templateLayoutRespDTO.getLayoutTypeId());
            CmsLayoutTypeRespDTO layoutTypeRespDTO = this.layoutTypeSV.queryCmsLayoutType(layoutTypeReqDTO);
            //布局项尺寸
            if(layoutTypeRespDTO != null && StringUtil.isNotEmpty(layoutTypeRespDTO.getLayoutItemSize())){
                String[] layoutItemSizeArray =layoutTypeRespDTO.getLayoutItemSize().split("\\|");
                if(layoutItemSizeArray != null && layoutItemSizeArray.length>0){
                    Map<String,String> layoutItemSizeMap = new HashMap<String,String>();
                    for(int i=0;i<layoutItemSizeArray.length;i++){
                        layoutItemSizeMap.put(String.valueOf(i), layoutItemSizeArray[i].trim());
                    }
                    layoutTypeRespDTO.setLayoutItemSizeMap(layoutItemSizeMap);
                }
            }
            templateLayoutRespDTO.setCmsLayoutTypeRespDTO(layoutTypeRespDTO);
        }
    }
    
    /** 
     * qryLayoutItemPreRespDTOList:(查询布局项列表-预览). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param layoutPreRespDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public List<CmsLayoutItemPreRespDTO> qryLayoutItemPreRespDTOList(CmsLayoutPreRespDTO layoutPreRespDTO) throws BusinessException{
        CmsLayoutItemPreReqDTO layoutItemPreReqDTO = new CmsLayoutItemPreReqDTO();
        layoutItemPreReqDTO.setLayoutId(layoutPreRespDTO.getId());
        layoutItemPreReqDTO.setPageId(layoutPreRespDTO.getPageId());
        layoutItemPreReqDTO.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
        return layoutItemPreSV.queryCmsLayoutItemPreList(layoutItemPreReqDTO);
    }
    
    /** 
     * qryLayoutItemPubRespDTOList:(查询布局项列表-发布). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param layoutPreRespDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public List<CmsLayoutItemPubRespDTO> qryLayoutItemPubRespDTOList(CmsLayoutPubRespDTO layoutPubRespDTO) throws BusinessException{
        CmsLayoutItemPubReqDTO layoutItemPubReqDTO = new CmsLayoutItemPubReqDTO();
        layoutItemPubReqDTO.setLayoutId(layoutPubRespDTO.getLayoutId());
        layoutItemPubReqDTO.setPageId(layoutPubRespDTO.getPageId());
        layoutItemPubReqDTO.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
        return layoutItemPubSV.queryCmsLayoutItemPubList(layoutItemPubReqDTO);
    }
    
    /** 
     * qryTemplateLayoutItemRespDTOList:(查询布局项列表-模板). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param templateLayoutRespDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public List<CmsTemplateLayoutItemRespDTO> qryTemplateLayoutItemRespDTOList(CmsTemplateLayoutRespDTO templateLayoutRespDTO) throws BusinessException{
        CmsTemplateLayoutItemReqDTO templateLayoutItemReqDTO = new CmsTemplateLayoutItemReqDTO();
        templateLayoutItemReqDTO.setLayoutId(templateLayoutRespDTO.getId());
        templateLayoutItemReqDTO.setTemplateId(templateLayoutRespDTO.getTemplateId());
        templateLayoutItemReqDTO.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
        return templateLayoutItemSV.queryCmsTemplateLayoutItemList(templateLayoutItemReqDTO);
    }
    
    /** 
     * qryLayoutItemPreRespDTOMap:(封装布局项-预览). <br/> 
     * TODO(1、根据布局项中的模块ID查询模块).<br/> 
     * TODO(2、根据模块ID与页面类型查询模块与组件关系表).<br/> 
     * TODO(3、根据布局项ID查询布局项与属性关系).<br/> 
     * TODO(4、将布局项的列加入MAP（去重）).<br/> 
     * 
     * @author jiangzh 
     * @param layoutItemPreRespDTOList
     * @param layoutPreRespDTO
     * @return 
     * @since JDK 1.6 
     */ 
    public Map<Integer, List<CmsLayoutItemPreRespDTO>> qryLayoutItemPreRespDTOMap(
            List<CmsLayoutItemPreRespDTO> layoutItemPreRespDTOList,
            CmsLayoutPreRespDTO layoutPreRespDTO) {
        Map<Integer,List<CmsLayoutItemPreRespDTO>> layoutItemPreRespDTOMap = null;
        if(CollectionUtils.isNotEmpty(layoutItemPreRespDTOList)){
            layoutItemPreRespDTOMap = new HashMap<Integer,List<CmsLayoutItemPreRespDTO>>();
            for(CmsLayoutItemPreRespDTO layoutItemPreRespDTO : layoutItemPreRespDTOList){
                //2.2.1 根据模块ID查询模块,并封装模块,同时set至布局项
                if(StringUtil.isNotEmpty(layoutItemPreRespDTO) && StringUtil.isNotEmpty(layoutItemPreRespDTO.getModularId())){
                    CmsModularReqDTO modularReqDTO = new CmsModularReqDTO();
                    modularReqDTO.setId(layoutItemPreRespDTO.getModularId());
                    CmsModularRespDTO modularRespDTO = modularSV.queryCmsModular(modularReqDTO);
                    layoutItemPreRespDTO.setModularRespDTO(modularRespDTO);
                    //2.2.1.1 根据（模块ID与页面类型）查询模块与组件关系表
                    Long pageTypeId = layoutPreRespDTO.getCmsLayoutTypeRespDTO().getPageTypeId();
                    getModularRespDTO(modularRespDTO,pageTypeId);
                }
                //2.2.2 根据布局项ID查询布局项与属性关系
                getItemPropPreRespDTOList(layoutItemPreRespDTO);
                //2.2.3 将布局项根据列（Integer）放入MAP，去掉重复的列。
                if(StringUtil.isNotEmpty(layoutItemPreRespDTO) && StringUtil.isNotEmpty(layoutItemPreRespDTO.getItemNo())){
                    layoutItemPreRespDTOMap.put(layoutItemPreRespDTO.getItemNo(), null);
                }
            }
        }
        return layoutItemPreRespDTOMap;
    }
    
    /** 
     * getItemPropPreRespDTOList:(根据布局项ID获取属性LIST). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param layoutItemPreRespDTO 
     * @since JDK 1.6 
     */ 
    public void getItemPropPreRespDTOList(CmsLayoutItemPreRespDTO layoutItemPreRespDTO) {
        if(StringUtil.isNotEmpty(layoutItemPreRespDTO) && StringUtil.isNotEmpty(layoutItemPreRespDTO.getId())){
            CmsItemPropPreReqDTO itemPropPreReqDTO = new CmsItemPropPreReqDTO();
            itemPropPreReqDTO.setItemId(layoutItemPreRespDTO.getId());
            itemPropPreReqDTO.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
            itemPropPreReqDTO.setPageId(layoutItemPreRespDTO.getPageId());//wap 端服务改造
            List<CmsItemPropPreRespDTO> itemPropPreRespDTOList = itemPropPreSV.queryCmsItemPropValuePreList(itemPropPreReqDTO);
            layoutItemPreRespDTO.setItemPropPreRespDTOList(itemPropPreRespDTOList);
        } 
    }
    
    /** 
     * getModularRespDTO:(封装模块). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param layoutItemPreRespDTO
     * @param layoutPreRespDTO 
     * @since JDK 1.6 
     */ 
    public void getModularRespDTO(CmsModularRespDTO modularRespDTO,Long pageTypeId) {
        //2.2.1.1 根据（模块ID与页面类型）查询模块与组件关系表
        if(null != modularRespDTO
                && StringUtil.isNotEmpty(modularRespDTO.getId())//模块ID
                && StringUtil.isNotEmpty(pageTypeId)){//页面类型ID
            CmsModularComponentReqDTO modularComponentReqDTO  = new CmsModularComponentReqDTO();
            modularComponentReqDTO.setModularId(modularRespDTO.getId());
            modularComponentReqDTO.setApplyPageType(String.valueOf(pageTypeId));
            List<CmsModularComponentRespDTO> modularComponentRespDTOList=  modularComponentSV.queryCmsModularComponentList(modularComponentReqDTO);
            if(CollectionUtils.isNotEmpty(modularComponentRespDTOList)){
                CmsModularComponentRespDTO modularComponentRespDTO = modularComponentRespDTOList.get(0);
                if(null != modularComponentRespDTO){
                    //在模块中SET模块与组件关系对象
                    modularRespDTO.setModularComponentRespDTO(modularComponentRespDTO); 
                    //在模块中SET组件对象
                    if(StringUtil.isNotEmpty(modularComponentRespDTO.getComponentId())){
                        CmsComponentReqDTO componentReqDTO = new CmsComponentReqDTO();
                        componentReqDTO.setId(modularComponentRespDTO.getComponentId());
                        CmsComponentRespDTO componentRespDTO = componentSV.queryCmsComponent(componentReqDTO);
                        modularComponentRespDTO.setComponentRespDTO(componentRespDTO);
                    }
                }
            }
        }
            
    }
    
    /** 
     * qryLayoutItemPubRespDTOMap:(封装布局项-发布). <br/> 
     * TODO(1、根据布局项中的模块ID查询模块).<br/> 
     * TODO(2、根据模块ID与页面类型查询模块与组件关系表).<br/> 
     * TODO(3、根据布局项ID查询布局项与属性关系).<br/> 
     * TODO(4、将布局项的列加入MAP（去重）).<br/> 
     * 
     * @author jiangzh 
     * @param layoutItemPreRespDTOList
     * @param layoutPreRespDTO
     * @return 
     * @since JDK 1.6 
     */ 
    public Map<Integer, List<CmsLayoutItemPubRespDTO>> qryLayoutItemPubRespDTOMap(
            List<CmsLayoutItemPubRespDTO> layoutItemPubRespDTOList,
            CmsLayoutPubRespDTO layoutPubRespDTO) {
        Map<Integer,List<CmsLayoutItemPubRespDTO>> layoutItemPubRespDTOMap = null;
        if(CollectionUtils.isNotEmpty(layoutItemPubRespDTOList)){
            layoutItemPubRespDTOMap = new HashMap<Integer,List<CmsLayoutItemPubRespDTO>>();
            for(CmsLayoutItemPubRespDTO layoutItemPubRespDTO:layoutItemPubRespDTOList){
                //2.2.1 根据模块ID查询模块,并封装模块,同时set至布局项
                if(StringUtil.isNotEmpty(layoutItemPubRespDTO) && StringUtil.isNotEmpty(layoutItemPubRespDTO.getModularId())){
                    CmsModularReqDTO modularReqDTO = new CmsModularReqDTO();
                    modularReqDTO.setId(layoutItemPubRespDTO.getModularId());
                    CmsModularRespDTO modularRespDTO = modularSV.queryCmsModular(modularReqDTO);
                    layoutItemPubRespDTO.setModularRespDTO(modularRespDTO);
                    //2.2.1.1 根据（模块ID与页面类型）查询模块与组件关系表
                    Long pageTypeId = layoutPubRespDTO.getCmsLayoutTypeRespDTO().getPageTypeId();
                    getModularRespDTO(modularRespDTO,pageTypeId);
                    //2.2.1.1 根据组件ID查询组件
                    //getComponentRespDTO(modularRespDTO);
                }
                //2.2.2 根据布局项ID查询布局项与属性关系
                getItemPropPubRespDTOList(layoutItemPubRespDTO);
                //2.2.3 将布局项与属性列放入数据。
                if(StringUtil.isNotEmpty(layoutItemPubRespDTO.getItemNo())){
                    layoutItemPubRespDTOMap.put(layoutItemPubRespDTO.getItemNo(), new ArrayList<CmsLayoutItemPubRespDTO>());
                }
            }
        }
        return layoutItemPubRespDTOMap;
    }
    
    /** 
     * getItemPropPreRespDTOList:(根据布局项ID获取属性LIST). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param layoutItemPreRespDTO 
     * @since JDK 1.6 
     */ 
    public void getItemPropPubRespDTOList(CmsLayoutItemPubRespDTO layoutItemPubRespDTO) {
        if(StringUtil.isNotEmpty(layoutItemPubRespDTO) && StringUtil.isNotEmpty(layoutItemPubRespDTO.getId())){
            CmsItemPropPubReqDTO itemPropPubReqDTO = new CmsItemPropPubReqDTO();
            itemPropPubReqDTO.setItemId(layoutItemPubRespDTO.getItemId());
            itemPropPubReqDTO.setStatus(ParamStatus.CMS_PARAMSTATUS_1);
            itemPropPubReqDTO.setPageId(layoutItemPubRespDTO.getPageId());//wap 端服务改造  
            List<CmsItemPropPubRespDTO> itemPropPubRespDTOList = itemPropPubSV.queryCmsItemPropValuePubList(itemPropPubReqDTO);
            layoutItemPubRespDTO.setItemPropPubRespDTOList(itemPropPubRespDTOList);
        }
    }
    
    /** 
     * qryTemplateLayoutItemRespDTOMap:(封装布局项-模板). <br/> 
     * TODO(1、根据布局项中的模块ID查询模块).<br/> 
     * TODO(2、根据模块ID与页面类型查询模块与组件关系表).<br/> 
     * TODO(3、根据布局项ID查询布局项与属性关系).<br/> 
     * TODO(4、将布局项的列加入MAP（去重）).<br/> 
     * 
     * @author jiangzh 
     * @param templateLayoutItemRespDTOList
     * @param templateLayoutRespDTO
     * @return 
     * @since JDK 1.6 
     */ 
    public Map<Integer, List<CmsTemplateLayoutItemRespDTO>> qryTemplateLayoutItemRespDTOMap(
            List<CmsTemplateLayoutItemRespDTO> templateLayoutItemRespDTOList,
            CmsTemplateLayoutRespDTO templateLayoutRespDTO) {
        Map<Integer,List<CmsTemplateLayoutItemRespDTO>> templateLayoutItemRespDTOMap = null;
        if(CollectionUtils.isNotEmpty(templateLayoutItemRespDTOList)){
            templateLayoutItemRespDTOMap = new HashMap<Integer,List<CmsTemplateLayoutItemRespDTO>>();
            for(CmsTemplateLayoutItemRespDTO templateLayoutItemRespDTO : templateLayoutItemRespDTOList){
                //2.2.1 根据模块ID查询模块,并封装模块,同时set至布局项
                if(StringUtil.isNotEmpty(templateLayoutItemRespDTO) && StringUtil.isNotEmpty(templateLayoutItemRespDTO.getModularId())){
                    CmsModularReqDTO modularReqDTO = new CmsModularReqDTO();
                    modularReqDTO.setId(templateLayoutItemRespDTO.getModularId());
                    CmsModularRespDTO modularRespDTO = modularSV.queryCmsModular(modularReqDTO);
                    templateLayoutItemRespDTO.setModularRespDTO(modularRespDTO);
                    //2.2.1.1 根据（模块ID与页面类型）查询模块与组件关系表
                    Long pageTypeId = templateLayoutRespDTO.getCmsLayoutTypeRespDTO().getPageTypeId();
                    getModularRespDTO(modularRespDTO,pageTypeId);
                    //2.2.1.1 根据组件ID查询组件
                    //getComponentRespDTO(modularRespDTO);
                }
                //2.2.3 将布局项根据列（Integer）放入MAP，去掉重复的列。
                if(StringUtil.isNotEmpty(templateLayoutItemRespDTO) && StringUtil.isNotEmpty(templateLayoutItemRespDTO.getItemNo())){
                    templateLayoutItemRespDTOMap.put(templateLayoutItemRespDTO.getItemNo(), null);
                }
            }
        }
        return templateLayoutItemRespDTOMap;
    }
    
    /** 
     * getLayoutItemPreRespDTOMap:(封装布局项-预览). <br/> 
     * TODO(1、迭代布局项，将布局项组装成MAP,将列当作KEY,列下的布局项List为value).<br/> 
     * TODO(2、删除模块为空的布局项).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param layoutItemPreRespDTOMap
     * @param layoutItemPreRespDTOList
     * @return 
     * @since JDK 1.6 
     */ 
    public Map<Integer, List<CmsLayoutItemPreRespDTO>> getLayoutItemPreRespDTOMap(
            Map<Integer, List<CmsLayoutItemPreRespDTO>> layoutItemPreRespDTOMap,
            List<CmsLayoutItemPreRespDTO> layoutItemPreRespDTOList) {
        //封装布局下的布局项，以列当作KEY,列下的布局项List为value.
        List<CmsLayoutItemPreReqDTO> delItemList = new ArrayList<CmsLayoutItemPreReqDTO>();//需要删除的布局项
        if(layoutItemPreRespDTOMap != null && layoutItemPreRespDTOMap.size()>0){
            layoutItemPreRespDTOMap = CmsUtil.sortMapByKey(layoutItemPreRespDTOMap);
            Set<Entry<Integer, List<CmsLayoutItemPreRespDTO>>> mapEntrySet = layoutItemPreRespDTOMap.entrySet();
            for (Map.Entry<Integer, List<CmsLayoutItemPreRespDTO>> entry : mapEntrySet) {
                List<CmsLayoutItemPreRespDTO> itemList = new ArrayList<CmsLayoutItemPreRespDTO>();
                //将列号相同布局项加入LIST
                if(CollectionUtils.isNotEmpty(layoutItemPreRespDTOList)){
                    for(CmsLayoutItemPreRespDTO layoutItemPreRespDTO:layoutItemPreRespDTOList){
                        if(StringUtil.isNotEmpty(layoutItemPreRespDTO) && entry.getKey().equals(layoutItemPreRespDTO.getItemNo())){
                            itemList.add(layoutItemPreRespDTO);
                        }
                    }
                }
                //过滤需要删除的布局项
                int itemSize = itemList.size();
                for(int i = 0 ; i < itemList.size(); i++){
                    CmsLayoutItemPreRespDTO layoutItemPreRespDTO = itemList.get(i);
                        if(itemSize > 1 && (itemSize-delItemList.size()) > 1){
                            if(StringUtil.isEmpty(layoutItemPreRespDTO.getModularId())){//将空布局项删除
                                CmsLayoutItemPreReqDTO delItemReqDto = new CmsLayoutItemPreReqDTO();
                                delItemReqDto.setId(layoutItemPreRespDTO.getId());
                                delItemReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
                                delItemList.add(delItemReqDto);
                                layoutItemPreRespDTO = null;
                                itemList.remove(i);
                                i--;
                            }
                        }
                }
                //将布局项进行按大到小排序
                Collections.sort(itemList, new Comparator<CmsLayoutItemPreRespDTO>() {
                    public int compare(CmsLayoutItemPreRespDTO arg0, CmsLayoutItemPreRespDTO arg1) {
                        return arg0.getRowNo().compareTo(arg1.getRowNo());
                    }
                });
                layoutItemPreRespDTOMap.put(entry.getKey(), itemList);
            }  
        };
        //将模块为空的布局项删除
        if(CollectionUtils.isNotEmpty(delItemList)){
            layoutItemPreSV.updateCmsLayoutItemPreBatch(delItemList);
        }
        
        return layoutItemPreRespDTOMap;
    }
    
    /** 
     * getLayoutItemPubRespDTOMap:(封装布局项MAP-发布). <br/> 
     * TODO(1、迭代布局项，将布局项组装成MAP,将列当作KEY,列下的布局项List为value).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param layoutItemPubRespDTOMap
     * @param layoutItemPubRespDTOList
     * @return 
     * @since JDK 1.6 
     */ 
    public Map<Integer,List<CmsLayoutItemPubRespDTO>> getLayoutItemPubRespDTOMap(Map<Integer,List<CmsLayoutItemPubRespDTO>> layoutItemPubRespDTOMap,List<CmsLayoutItemPubRespDTO> layoutItemPubRespDTOList){
        //封装布局下的布局项，以列当作KEY,列下的布局项List为value.
        if(layoutItemPubRespDTOMap != null && layoutItemPubRespDTOMap.size()>0){
            layoutItemPubRespDTOMap = CmsUtil.sortMapByKey(layoutItemPubRespDTOMap);
            for (Map.Entry<Integer, List<CmsLayoutItemPubRespDTO>> entry : layoutItemPubRespDTOMap.entrySet()) {  
                List<CmsLayoutItemPubRespDTO> itemList = new ArrayList<CmsLayoutItemPubRespDTO>();
                //将列号相同布局项加入LIST
                if(CollectionUtils.isNotEmpty(layoutItemPubRespDTOList)){
                    for(CmsLayoutItemPubRespDTO layoutItemPubRespDTO:layoutItemPubRespDTOList){
                        if(entry.getKey().equals(layoutItemPubRespDTO.getItemNo())){
                            itemList.add(layoutItemPubRespDTO);
                        }
                    }
                }
                //将布局项进行按大到小排序
                Collections.sort(itemList, new Comparator<CmsLayoutItemPubRespDTO>() {
                    public int compare(CmsLayoutItemPubRespDTO arg0, CmsLayoutItemPubRespDTO arg1) {
                        return arg0.getRowNo().compareTo(arg1.getRowNo());
                    }
                });
                layoutItemPubRespDTOMap.put(entry.getKey(), itemList);  
            }  
        };
        return layoutItemPubRespDTOMap;
    }
    
    /** 
     * getTemplateLayoutItemRespDTOMap:(封装布局项-模板). <br/> 
     * TODO(1、迭代布局项，将布局项组装成MAP,将列当作KEY,列下的布局项List为value).<br/> 
     * TODO(2、删除模块为空的布局项).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param layoutItemPreRespDTOMap
     * @param layoutItemPreRespDTOList
     * @return 
     * @since JDK 1.6 
     */ 
    public Map<Integer,List<CmsTemplateLayoutItemRespDTO>> getTemplateLayoutItemRespDTOMap(Map<Integer,List<CmsTemplateLayoutItemRespDTO>> templateLayoutItemRespDTOMap,List<CmsTemplateLayoutItemRespDTO> templateLayoutItemRespDTOList){
        if(templateLayoutItemRespDTOMap != null && templateLayoutItemRespDTOMap.size()>0){
            templateLayoutItemRespDTOMap = CmsUtil.sortMapByKey(templateLayoutItemRespDTOMap);
            for (Map.Entry<Integer, List<CmsTemplateLayoutItemRespDTO>> entry : templateLayoutItemRespDTOMap.entrySet()) {  
                List<CmsTemplateLayoutItemRespDTO> itemList = new ArrayList<CmsTemplateLayoutItemRespDTO>();
                if(CollectionUtils.isNotEmpty(templateLayoutItemRespDTOList)){
                    for(CmsTemplateLayoutItemRespDTO templateLayoutItemRespDTO:templateLayoutItemRespDTOList){
                        if(StringUtil.isNotEmpty(templateLayoutItemRespDTO) && entry.getKey().equals(templateLayoutItemRespDTO.getItemNo())){
                            itemList.add(templateLayoutItemRespDTO);
                        }
                    }
                }
                Collections.sort(itemList, new Comparator<CmsTemplateLayoutItemRespDTO>() {
                    public int compare(CmsTemplateLayoutItemRespDTO arg0, CmsTemplateLayoutItemRespDTO arg1) {
                        return arg0.getRowNo().compareTo(arg1.getRowNo());
                    }
                });
                templateLayoutItemRespDTOMap.put(entry.getKey(), itemList);
            }  
        };
        return templateLayoutItemRespDTOMap;
    }
    
}

