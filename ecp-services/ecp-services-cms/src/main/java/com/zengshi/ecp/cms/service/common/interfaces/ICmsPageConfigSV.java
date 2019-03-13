package com.zengshi.ecp.cms.service.common.interfaces;

import java.util.List;

import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPreReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPreRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPubReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPubRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutPreRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutPubRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLayoutItemReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLayoutItemRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLayoutRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLibReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLibRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

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
public interface ICmsPageConfigSV extends IGeneralSQLSV{
    
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
    public List<CmsLayoutPreRespDTO> initPageConfigPre(CmsPageInfoReqDTO dto) throws BusinessException;
    
    /** 
     * initPageConfigLayoutAndEdit:(初始化装修页面.店铺). <br/> 
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
    public List<CmsLayoutPubRespDTO> initPageConfigPub(CmsPageInfoReqDTO dto) throws BusinessException;
    
    /** 
     * initTemplateConfig:(初始化装修页面). <br/> 
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
    public List<CmsTemplateLayoutRespDTO> initTemplateConfig(CmsTemplateLibReqDTO dto) throws BusinessException;
    
    /** 
     * deletePageInfo:(将页面失效). <br/> 
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
    public boolean deletePageInfo(CmsPageInfoReqDTO dto) throws BusinessException;
    
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
    public boolean savePageConfigPub(CmsPageInfoReqDTO dto) throws BusinessException;
    
    /** 
     * savePageConfigToTemplate:(装修保存至模板). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param dto
     * @return CmsTemplateLibRespDTO
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public CmsTemplateLibRespDTO savePageConfigToTemplate(CmsTemplateLibReqDTO dto) throws BusinessException;

    /**
     * 
     * saveTemplateToPageConfig:(模板复制到页面). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author IME 
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public CmsPageInfoRespDTO saveTemplateToPageConfig(CmsPageInfoReqDTO dto) throws BusinessException;
    
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
    public CmsLayoutItemPreRespDTO queryLayoutItemPre(CmsLayoutItemPreReqDTO dto) throws BusinessException;
    
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
    public CmsLayoutItemPubRespDTO queryLayoutItemPub(CmsLayoutItemPubReqDTO dto) throws BusinessException;

    /**
     * 
     * deleteModularInLayoutItemRre:(删除布局项中的模块). <br/> 
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
    public CmsLayoutItemPreRespDTO deleteModularInLayoutItemRre(CmsLayoutItemPreReqDTO dto) throws BusinessException;
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
    public CmsTemplateLayoutItemRespDTO deleteModularInTemplateItem(CmsTemplateLayoutItemReqDTO dto) throws BusinessException;
    
}

