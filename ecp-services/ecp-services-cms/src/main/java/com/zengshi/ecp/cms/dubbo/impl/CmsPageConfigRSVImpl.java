package com.zengshi.ecp.cms.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPreReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPreRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPubReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPubRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutPreRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutPubRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageAttrPubReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageAttrPubRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLayoutItemReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLayoutItemRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLayoutRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLibReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLibRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageConfigRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsPageAttrPubSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsPageConfigSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsPageInfoSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-cms <br>
 * Description: <br>
 * Date:2015年8月7日下午5:11:10  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author jiangzh
 * @version  
 * @since JDK 1.6 
 */  
public class CmsPageConfigRSVImpl implements ICmsPageConfigRSV{
    
    @Resource
    private ICmsPageConfigSV sv;
    @Resource
    private ICmsPageInfoSV cmsPageInfoSV;
    @Resource
    private ICmsPageAttrPubSV cmsPageAttrPubSV;
    
    //定义个常量，用于表示模块名称，可以使用：当前类的类名
    private static final String MODULE = CmsPageConfigRSVImpl.class.getName();
    
    /** 
     * accessPageInfoPub:(商城访问已发布的页面). <br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    @Override
    public CmsPageInfoRespDTO accessPageInfoPub(CmsPageInfoReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用商城访问发布页面开始，入参：{dto="+dto.toString()+"}");
        CmsPageInfoRespDTO pageInfoRespDTO  = new CmsPageInfoRespDTO();

        List<CmsLayoutPubRespDTO> layoutPubRespDTOList = new ArrayList<CmsLayoutPubRespDTO>();
        List<CmsPageInfoRespDTO> pageInfoList = new ArrayList<CmsPageInfoRespDTO>(); 
        try {
            dto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);//有效
            pageInfoList = cmsPageInfoSV.queryCmsPageInfoList(dto);
            if(CollectionUtils.isNotEmpty(pageInfoList)){
                //页面信息
                pageInfoRespDTO = pageInfoList.get(0);
                if(pageInfoRespDTO != null && StringUtil.isNotEmpty(pageInfoRespDTO.getId())){
                    //查询已发布布局
                    CmsPageInfoReqDTO pageInfoReqDTO = new CmsPageInfoReqDTO();
                    pageInfoReqDTO.setId(pageInfoRespDTO.getId());
                    layoutPubRespDTOList = sv.initPageConfigPub(pageInfoReqDTO);
                    pageInfoRespDTO.setLayoutPubRespDTOList(layoutPubRespDTOList);
                    //查询已发布页面属性
                    CmsPageAttrPubReqDTO pageAttrPubReqDTO = new CmsPageAttrPubReqDTO();
                    pageAttrPubReqDTO.setPageId(pageInfoRespDTO.getId());
                    pageAttrPubReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                    CmsPageAttrPubRespDTO pageAttrPubRespDTO = cmsPageAttrPubSV.queryCmsPageAttrPub(pageAttrPubReqDTO);
                    pageInfoRespDTO.setPageAttrPubRespDTO(pageAttrPubRespDTO);
                }
            }
        } catch (Exception e) {
            LogUtil.error(MODULE, "商城访问发布页面出现异常！",e);
            throw new BusinessException("CMS.PAGECONFIG.500109");
        }
        return pageInfoRespDTO;
    }
    
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
    public List<CmsLayoutPreRespDTO> initPageConfigPre(CmsPageInfoReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用初始化查询布局预览列表开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参CmsPageInfoReqDTO.id为空！");
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,new String[] {"CmsPageInfoReqDTO.id"}); 
        }
        
        /* 2.根据条件检索基础模块  */
        List<CmsLayoutPreRespDTO> list = new ArrayList<CmsLayoutPreRespDTO>();
        try {
            list = sv.initPageConfigPre(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "初始化查询布局预览列表出现异常！",e);
            throw new BusinessException("CMS.PAGECONFIG.500103");
        }
        
        return list;
    }
    
    /** 
     * initPageConfigPubLayoutAndEdit:(初始化装修页面). <br/> 
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
    public List<CmsLayoutPubRespDTO> initPageConfigPub(CmsPageInfoReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用初始化查询布局发布列表开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参CmsPageInfoReqDTO.id为空！");
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,new String[] {"CmsPageInfoReqDTO.id"}); 
        }
        
        /* 2.根据条件检索基础模块  */
        List<CmsLayoutPubRespDTO> list = new ArrayList<CmsLayoutPubRespDTO>();
        try {
            list = sv.initPageConfigPub(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "初始化查询布局发布列表出现异常！",e);
            throw new BusinessException("CMS.PAGECONFIG.500103");
        }
        
        return list;
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
    public List<CmsTemplateLayoutRespDTO> initTemplateConfig(CmsTemplateLibReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用初始化查询布局预览列表开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参CmsTemplateLayoutReqDTO.id为空！");
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,new String[] {"CmsPageInfoReqDTO.id"}); 
        }
        
        /* 2.根据条件检索基础模块  */
        List<CmsTemplateLayoutRespDTO> list = new ArrayList<CmsTemplateLayoutRespDTO>();
        try {
            list = sv.initTemplateConfig(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "初始化查询布局预览列表出现异常！",e);
            throw new BusinessException("CMS.PAGECONFIG.500103");
        }
        
        return list;
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
    public CmsLayoutItemPreRespDTO queryLayoutItemPre(CmsLayoutItemPreReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用查询布局项预览列表开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参CmsLayoutItemPreReqDTO.id为空！");
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,new String[] {"CmsLayoutItemPreReqDTO.id"}); 
        }
        
        /* 2.根据条件检索基础模块  */
        CmsLayoutItemPreRespDTO layoutItemPreRespDTO = null;
        try {
            layoutItemPreRespDTO = sv.queryLayoutItemPre(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "初始化查询布局预览列表出现异常！",e);
            throw new BusinessException("CMS.PAGECONFIG.500103");
        }
        return layoutItemPreRespDTO;
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
    public CmsLayoutItemPubRespDTO queryLayoutItemPub(CmsLayoutItemPubReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用查询布局项发布列表开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参CmsLayoutItemPubReqDTO.id为空！");
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,new String[] {"CmsLayoutItemPubReqDTO.id"}); 
        }
        
        /* 2.根据条件检索基础模块  */
        CmsLayoutItemPubRespDTO layoutItemPubRespDTO = null;
        try {
            layoutItemPubRespDTO = sv.queryLayoutItemPub(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "初始化查询布局发布列表出现异常！",e);
            throw new BusinessException("CMS.PAGECONFIG.500103");
        }
        return layoutItemPubRespDTO;
    }
    
    /** 
     * TODO(页面编辑-页面配置发布).<br/>  
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public boolean savePageConfigPub(CmsPageInfoReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用页面配置发布开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参CmsPageInfoReqDTO.id为空！");
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,new String[] {"CmsPageInfoReqDTO.id"}); 
        }
        
        /* 2.根据条件检索基础模块  */
        boolean flag = true;
        try {
            flag = sv.savePageConfigPub(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "页面配置发布出现异常！",e);
            throw new BusinessException("CMS.PAGECONFIG.500103");
        }
        
        return flag;
    }
    
    /** 
     * TODO(页面编辑-页面配置发布).<br/>  
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public boolean deletePageInfo(CmsPageInfoReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用使页面失效开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参CmsPageInfoReqDTO.id为空！");
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,new String[] {"CmsPageInfoReqDTO.id"}); 
        }
        
        /* 2.根据条件检索基础模块  */
        boolean flag = true;
        try {
            flag = sv.deletePageInfo(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "使页面失效出现异常！",e);
            throw new BusinessException("CMS.PAGEINFO.500103");
        }
        
        return flag;
    }
    
    /** 
     * TODO 保存为模板（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageConfigRSV#savePageConfigToTemplate(com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLibReqDTO) 
     */
    @Override
    public CmsTemplateLibRespDTO savePageConfigToTemplate(CmsTemplateLibReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用保存为模板开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isEmpty(dto.getPageInfoId())){
            LogUtil.error(MODULE, "入参CmsTemplateLibReqDTO.getPageInfoId为空！");
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,new String[] {"CmsTemplateLibReqDTO.getPageInfoId"}); 
        }
        if(StringUtil.isEmpty(dto.getTemplateName())){
            LogUtil.error(MODULE, "入参CmsTemplateLibReqDTO.getTemplateName为空！");
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,new String[] {"CmsTemplateLibReqDTO.getTemplateName"}); 
        }
        if(StringUtil.isEmpty(dto.getPageTypeId())){
            LogUtil.error(MODULE, "入参CmsTemplateLibReqDTO.getPageTypeId为空！");
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,new String[] {"CmsTemplateLibReqDTO.getPageTypeId"}); 
        }
        if(StringUtil.isEmpty(dto.getTemplateType())){
            LogUtil.error(MODULE, "入参CmsTemplateLibReqDTO.getTemplateType为空！");
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,new String[] {"CmsTemplateLibReqDTO.getTemplateType"}); 
        }
        
        /* 2.根据条件检索基础模块  */
        CmsTemplateLibRespDTO templateLibRespDTO = null;
        try {
            templateLibRespDTO = sv.savePageConfigToTemplate(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "保存为模板出现异常！",e);
            throw new BusinessException("CMS.PAGECONFIG.500103");
        }
        
        return templateLibRespDTO;
    }

    @Override
    public CmsPageInfoRespDTO saveTemplateToPageConfig(CmsPageInfoReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用页面使用模板开始，入参：{dto="+dto.toString()+"}");
        CmsPageInfoRespDTO respDTO = new CmsPageInfoRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getId())){//新增
            if(StringUtil.isBlank(dto.getPageName())){
                LogUtil.error(MODULE, "入参PageName为空！");
                String[] keyInfos = new String[1];
                keyInfos[0]="PageName";
                throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
            }
            if(null == dto.getSiteId()){
                LogUtil.error(MODULE, "入参SiteId为空！");
                String[] keyInfos = new String[1];
                keyInfos[0]="SiteId";
                throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
            }
            if(StringUtil.isEmpty(dto.getPageTypeId())){
                LogUtil.error(MODULE, "入参PageTypeId为空！");
                String[] keyInfos = new String[1];
                keyInfos[0]="PageTypeId";
                throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
            }
            if(StringUtil.isBlank(dto.getPlatformType())){
                LogUtil.error(MODULE, "入参PlatformType为空！");
                String[] keyInfos = new String[1];
                keyInfos[0]="PlatformType";
                throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
            }
        }
        
        
        if(StringUtil.isEmpty(dto.getTemplateId())){
            LogUtil.error(MODULE, "入参TemplateId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="TemplateId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{   
            respDTO = sv.saveTemplateToPageConfig(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "页面使用模板失败:",err);
            throw new BusinessException("CMS.PAGECONFIG.500101");
        }
        
        return respDTO;
    }

    @Override
    public CmsLayoutItemPreRespDTO deleteModularInLayoutItemRre(CmsLayoutItemPreReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用删除布局项中的模块开始，入参：{dto="+dto.toString()+"}");
        CmsLayoutItemPreRespDTO respDto = null;
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="ID";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        /*2.调service层接口*/
        try{   
            respDto = sv.deleteModularInLayoutItemRre(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "删除布局项中的模块失败:",err);
            throw new BusinessException("CMS.PAGECONFIG.500101");
        }
        return respDto;
    }
    /**
     * 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageConfigRSV#deleteModularInTemplateItem(com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLayoutItemReqDTO)
     */
    @Override
    public CmsTemplateLayoutItemRespDTO deleteModularInTemplateItem(CmsTemplateLayoutItemReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用删除模板布局项中的模块开始，入参：{dto="+dto.toString()+"}");
        CmsTemplateLayoutItemRespDTO respDto = null;
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="ID";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        /*2.调service层接口*/
        try{   
            respDto = sv.deleteModularInTemplateItem(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "删除模板布局项中的模块失败:",err);
            throw new BusinessException("CMS.PAGECONFIG.500101");
        }
        return respDto;
    }
    
}

