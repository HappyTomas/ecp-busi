package com.zengshi.ecp.cms.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutPreReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLayoutReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLayoutRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateLayoutRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateLayoutSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

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
public class CmsTemplateLayoutRSVImpl implements ICmsTemplateLayoutRSV{
    
    @Resource
    private ICmsTemplateLayoutSV sv;
    
    //定义个常量，用于表示模块名称，可以使用：当前类的类名
    private static final String MODULE = CmsTemplateLayoutRSVImpl.class.getName();


    /** 
     * TODO 新增模板布局. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateLayoutRSV#saveCmsTemplateLayout(com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLayoutReqDTO) 
     */
    @Override
    public CmsTemplateLayoutRespDTO addCmsTemplateLayout(CmsTemplateLayoutReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用新增模板布局开始，入参：{dto="+dto.toString()+"}");
        CmsTemplateLayoutRespDTO respDTO = new CmsTemplateLayoutRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getLayoutTypeId())){
            LogUtil.error(MODULE, "入参LayoutTypeId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="LayoutTypeId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getTemplateId())){
            LogUtil.error(MODULE, "入参TemplateId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="TemplateId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{   
            respDTO = sv.addCmsTemplateLayout(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "新增模板布局失败:",err);
            throw new BusinessException("CMS.TEMPLATELIB.500101");
        }
        
        return respDTO;
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateLayoutRSV#updateCmsTemplateLayout(com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLayoutReqDTO) 
     */
    @Override
    public CmsTemplateLayoutRespDTO updateCmsTemplateLayout(CmsTemplateLayoutReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用更新模板布局开始，入参：{dto="+dto.toString()+"}");
        CmsTemplateLayoutRespDTO respDTO = new CmsTemplateLayoutRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{    
            respDTO = sv.updateCmsTemplateLayout(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新模板布局失败！",err);
            throw new BusinessException("CMS.TEMPLATELIB.500102");
        }
        
        return respDTO;
    }
    
    /** 
     * TODO 查询模板布局，分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateLayoutRSV#queryCmsTemplateLayoutPage(com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLayoutReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsTemplateLayoutRespDTO> queryCmsTemplateLayoutPage(CmsTemplateLayoutReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用分页查询模板布局开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索模板布局  */
        PageResponseDTO<CmsTemplateLayoutRespDTO> pageInfo = PageResponseDTO.buildByBaseInfo(dto, CmsTemplateLayoutRespDTO.class);
        try{
            pageInfo =  sv.queryCmsTemplateLayoutPage(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "查询模板布局失败！",err);
            throw new BusinessException("CMS.TEMPLATELIB.500103");
        }
        return pageInfo;
    }
    
    /** 
     * TODO 查询模板布局  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public List<CmsTemplateLayoutRespDTO> queryCmsTemplateLayoutList(CmsTemplateLayoutReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用查询模板布局开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索模板布局  */
        List<CmsTemplateLayoutRespDTO> list = new ArrayList<CmsTemplateLayoutRespDTO>();
        try {
            list = sv.queryCmsTemplateLayoutList(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询模板布局出现异常！",e);
            throw new BusinessException("CMS.TEMPLATELIB.500103");
        }
        
        return list;
    }
    
    /** 
     * TODO 查询单个模板布局（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateLayoutRSV#queryCmsTemplateLayout(com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLayoutReqDTO) 
     */
    @Override
    public CmsTemplateLayoutRespDTO queryCmsTemplateLayout(CmsTemplateLayoutReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用查询模板布局开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.根据条件检索模板布局  */
        CmsTemplateLayoutRespDTO cmsTemplateLayoutRespDTO = new CmsTemplateLayoutRespDTO();
        try {
            cmsTemplateLayoutRespDTO = sv.queryCmsTemplateLayout(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "根据ID查询模板布局出现异常！",e);
            throw new BusinessException("CMS.TEMPLATELIB.500103");
        }
        
        return cmsTemplateLayoutRespDTO;
    }
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateLayoutRSV#deleteCmsTemplateLayout(java.lang.Long) 
     */
    @Override
    public void deleteCmsTemplateLayout(String id) throws BusinessException {
        LogUtil.info(MODULE, "调用删除模板布局开始，入参：{id="+id+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isBlank(id)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑删除模板布局  */
        try{
            sv.deleteCmsTemplateLayout(id);
        } catch(Exception err){
            LogUtil.error(MODULE, "删除模板布局失败！",err);
            throw new BusinessException("CMS.TEMPLATELIB.500101");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateLayoutRSV#deleteCmsTemplateLayoutBatch(java.util.List) 
     */
    @Override
    public void deleteCmsTemplateLayoutBatch(List<String> list) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除模板布局开始，入参：{list="+list.toArray()+"}");
        /* 1.验证前店入参 */
        if(CollectionUtils.isEmpty(list)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="list";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑批量删除模板布局  */
        try{
            sv.deleteCmsTemplateLayoutBatch(list);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量删除模板布局失败！",err);
            throw new BusinessException("CMS.TEMPLATELIB.500104");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateLayoutRSV#changeStatusCmsTemplateLayout(java.lang.Long, java.lang.String) 
     */
    @Override
    public void changeStatusCmsTemplateLayout(String id,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除模板布局开始，入参：{id="+id+",status="+status+"}");
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
        /* 2.更新模板布局状态  */
        try{
            sv.changeStatusCmsTemplateLayout(id, status);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新模板布局状态失败！",err);
            throw new BusinessException("CMS.TEMPLATELIB.500106");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateLayoutRSV#changeStatusCmsTemplateLayoutBatch(java.util.List, java.lang.String) 
     */
    @Override
    public void changeStatusCmsTemplateLayoutBatch(List<String> list,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除模板布局开始，入参：{list="+list.toArray()+",status="+status+"}");
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
        
        /* 2.更新模板布局状态  */
        try{
            sv.changeStatusCmsTemplateLayoutBatch(list,status);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量更新模板布局状态失败！",err);
            throw new BusinessException("CMS.TEMPLATELIB.500107");
        }
    }
    
    /** 
     * updateCmsTemplateLayoutBatch. <br/> 
     * 批量更新布局(删除布局、对布局重新排序)
     * 当状态为2时：
     * TODO(1、更新布局).<br/> 
     * TODO(2、根据布局更新布局项).<br/> 
     * 当状态为0时：
     * TODO(1、更新布局，更新排序。).<br/> 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutPreRSV#updateCmsLayoutPreBatch(java.util.List) 
     */
    @Override
    public boolean updateCmsTemplateLayoutBatch(List<CmsTemplateLayoutReqDTO> list) throws BusinessException {
        LogUtil.info(MODULE, "调用删除更新布局开始，入参：");
        boolean flag = true;
        /* 1.验证前店入参 */
        if(CollectionUtils.isEmpty(list)){
            flag = false;
            LogUtil.error(MODULE, "入参list为空！");
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,new String[] {"list"}); 
        }
        
        /* 2.根据条件检索基础模块  */
        try {
            sv.updateCmsTemplateLayoutBatch(list);
        } catch (Exception e) {
            flag = false;
            LogUtil.error(MODULE, "删除布局出现异常！",e);
            throw new BusinessException("CMS.PAGECONFIG.500108");
        }
        
        return flag;
    }
}

