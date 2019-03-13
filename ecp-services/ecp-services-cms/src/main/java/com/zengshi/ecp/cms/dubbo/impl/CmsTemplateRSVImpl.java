package com.zengshi.ecp.cms.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsPlaceSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
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
public class CmsTemplateRSVImpl implements ICmsTemplateRSV{
    
    @Resource
    private ICmsTemplateSV sv;
    @Resource
    private ICmsPlaceSV cmsPlaceSV;
    
    //定义个常量，用于表示模块名称，可以使用：当前类的类名
    private static final String MODULE = CmsTemplateRSVImpl.class.getName();


    /** 
     * TODO 新增模板. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateRSV#saveCmsTemplate(com.zengshi.ecp.cms.dubbo.dto.CmsTemplateReqDTO) 
     */
    @Override
    public CmsTemplateRespDTO addCmsTemplate(CmsTemplateReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用新增模板开始，入参：{dto="+dto.toString()+"}");
        CmsTemplateRespDTO respDTO = new CmsTemplateRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isBlank(dto.getTemplateName())){
            LogUtil.error(MODULE, "入参TemplateName为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="TemplateName";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isBlank(dto.getTemplateClass())){
            LogUtil.error(MODULE, "入参templateClass为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="templateClass";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getSiteId())){
            LogUtil.error(MODULE, "入参SiteId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="SiteId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{   
            respDTO = sv.addCmsTemplate(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "新增模板失败:",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_TEMPLATE_500101);
        }
        
        return respDTO;
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateRSV#updateCmsTemplate(com.zengshi.ecp.cms.dubbo.dto.CmsTemplateReqDTO) 
     */
    @Override
    public CmsTemplateRespDTO updateCmsTemplate(CmsTemplateReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用更新模板开始，入参：{dto="+dto.toString()+"}");
        CmsTemplateRespDTO respDTO = new CmsTemplateRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isBlank(dto.getTemplateName())){
            LogUtil.error(MODULE, "入参TemplateName为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="TemplateName";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isBlank(dto.getTemplateClass())){
            LogUtil.error(MODULE, "入参templateClass为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="templateClass";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getSiteId())){
            LogUtil.error(MODULE, "入参SiteId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="SiteId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{    
            respDTO = sv.updateCmsTemplate(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新模板失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_TEMPLATE_500102);
        }
        
        return respDTO;
    }
    
    /** 
     * TODO 查询模板，分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateRSV#queryCmsTemplatePage(com.zengshi.ecp.cms.dubbo.dto.CmsTemplateReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsTemplateRespDTO> queryCmsTemplatePage(CmsTemplateReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用分页查询模板开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索模板  */
        PageResponseDTO<CmsTemplateRespDTO> pageInfo = PageResponseDTO.buildByBaseInfo(dto, CmsTemplateRespDTO.class);
        try{
            pageInfo =  sv.queryCmsTemplatePage(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "查询模板失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_TEMPLATE_500103);
        }
        return pageInfo;
    }
    
    /** 
     * TODO 查询模板  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public List<CmsTemplateRespDTO> queryCmsTemplateList(CmsTemplateReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用查询模板开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索模板  */
        List<CmsTemplateRespDTO> list = new ArrayList<CmsTemplateRespDTO>();
        try {
            list = sv.queryCmsTemplateList(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询模板出现异常！",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_TEMPLATE_500103);
        }
        
        return list;
    }
    
    /** 
     * TODO 查询单个模板（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateRSV#queryCmsTemplate(com.zengshi.ecp.cms.dubbo.dto.CmsTemplateReqDTO) 
     */
    @Override
    public CmsTemplateRespDTO queryCmsTemplate(CmsTemplateReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用查询模板开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.根据条件检索模板  */
        CmsTemplateRespDTO cmsTemplateRespDTO = new CmsTemplateRespDTO();
        try {
            cmsTemplateRespDTO = sv.queryCmsTemplate(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "根据ID查询模板出现异常！",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_TEMPLATE_500103);
        }
        
        return cmsTemplateRespDTO;
    }
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateRSV#deleteCmsTemplate(java.lang.Long) 
     */
    @Override
    public void deleteCmsTemplate(String id) throws BusinessException {
        LogUtil.info(MODULE, "调用删除模板开始，入参：{id="+id+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isBlank(id)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑删除模板  */
        try{
            sv.deleteCmsTemplate(id);
        } catch(Exception err){
            LogUtil.error(MODULE, "删除模板失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_TEMPLATE_500101);
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateRSV#deleteCmsTemplateBatch(java.util.List) 
     */
    @Override
    public void deleteCmsTemplateBatch(List<String> list) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除模板开始，入参：{list="+list.toArray()+"}");
        /* 1.验证前店入参 */
        if(CollectionUtils.isEmpty(list)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="list";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑批量删除模板  */
        try{
            sv.deleteCmsTemplateBatch(list);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量删除模板失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_TEMPLATE_500104);
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateRSV#changeStatusCmsTemplate(java.lang.Long, java.lang.String) 
     */
    @Override
    public void changeStatusCmsTemplate(String id,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除模板开始，入参：{id="+id+",status="+status+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isBlank(id)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isBlank(status)){
            LogUtil.error(MODULE, "入参status为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="status";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        /* 2.更新模板状态  */
        try{
            sv.changeStatusCmsTemplate(id, status);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新模板状态失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_TEMPLATE_500106);
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateRSV#changeStatusCmsTemplateBatch(java.util.List, java.lang.String) 
     */
    @Override
    public void changeStatusCmsTemplateBatch(List<String> list,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除模板开始，入参：{list="+list.toArray()+",status="+status+"}");
        /* 1.验证前店入参 */
        if(CollectionUtils.isEmpty(list)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="list";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isBlank(status)){
            LogUtil.error(MODULE, "入参status为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="status";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.更新模板状态  */
        try{
            sv.changeStatusCmsTemplateBatch(list,status);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量更新模板状态失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_TEMPLATE_500107);
        }
    }

}

