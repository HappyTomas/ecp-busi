package com.zengshi.ecp.cms.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.cms.dubbo.dto.CmsModularParaPropReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularParaPropRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularParaPropRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsModularParaPropSV;
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
public class CmsModularParaPropRSVImpl implements ICmsModularParaPropRSV{
    
    @Resource
    private ICmsModularParaPropSV sv;
    
    //定义个常量，用于表示模块名称，可以使用：当前类的类名
    private static final String MODULE = CmsModularParaPropRSVImpl.class.getName();

    /** 
     * TODO 是否存在模块.
     * 存在：true
     * 不存在：false 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public boolean isExistModularParaProp(CmsModularParaPropReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用查询模块开始，入参：{dto="+dto.toString()+"}");
        /*1.验证前店入参*/
        if(StringUtil.isBlank(dto.getPropName())){
            LogUtil.error(MODULE, "入参PropName为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="PropName";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
//        if(StringUtil.isEmpty(dto.getModularId())){
//            LogUtil.error(MODULE, "入参ModularId为空！");
//            String[] keyInfos = new String[1];
//            keyInfos[0]="ModularId";
//            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
//        }
        if(StringUtil.isEmpty(dto.getStatusSet())){
            LogUtil.error(MODULE, "入参StatusSet为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="StatusSet";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.根据条件检索页面类型  */
        return sv.isExistModularParaProp(dto);
    }

    /** 
     * TODO 新增模块属性. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularParaPropRSV#saveCmsModularParaProp(com.zengshi.ecp.cms.dubbo.dto.CmsModularParaPropReqDTO) 
     */
    @Override
    public CmsModularParaPropRespDTO addCmsModularParaProp(CmsModularParaPropReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用新增模块属性开始，入参：{dto="+dto.toString()+"}");
        CmsModularParaPropRespDTO respDTO = new CmsModularParaPropRespDTO();
        /*1.验证前店入参*/
//        if(StringUtil.isEmpty(dto.getModularId())){
//            LogUtil.error(MODULE, "入参ModularId为空！");
//            String[] keyInfos = new String[1];
//            keyInfos[0]="ModularId";
//            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
//        }
        if(StringUtil.isEmpty(dto.getPropName())){
            LogUtil.error(MODULE, "入参PropName为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="PropName";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{   
            respDTO = sv.addCmsModularParaProp(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "新增模块属性失败:",err);
            throw new BusinessException("CMS.MODULARPARAPROP.500101");
        }
        
        return respDTO;
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularParaPropRSV#updateCmsModularParaProp(com.zengshi.ecp.cms.dubbo.dto.CmsModularParaPropReqDTO) 
     */
    @Override
    public CmsModularParaPropRespDTO updateCmsModularParaProp(CmsModularParaPropReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用更新模块属性开始，入参：{dto="+dto.toString()+"}");
        CmsModularParaPropRespDTO respDTO = new CmsModularParaPropRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{    
            respDTO = sv.updateCmsModularParaProp(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新模块属性失败！",err);
            throw new BusinessException("CMS.MODULARPARAPROP.500102");
        }
        
        return respDTO;
    }
    
    /** 
     * TODO 查询模块属性，分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularParaPropRSV#queryCmsModularParaPropPage(com.zengshi.ecp.cms.dubbo.dto.CmsModularParaPropReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsModularParaPropRespDTO> queryCmsModularParaPropPage(CmsModularParaPropReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用分页查询模块属性开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索模块属性  */
        PageResponseDTO<CmsModularParaPropRespDTO> pageInfo = PageResponseDTO.buildByBaseInfo(dto, CmsModularParaPropRespDTO.class);
        try{
            pageInfo =  sv.queryCmsModularParaPropPage(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "查询模块属性失败！",err);
            throw new BusinessException("CMS.MODULARPARAPROP.500103");
        }
        return pageInfo;
    }
    
    /** 
     * TODO 查询模块属性  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public List<CmsModularParaPropRespDTO> queryCmsModularParaPropList(CmsModularParaPropReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用查询模块属性开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索模块属性  */
        List<CmsModularParaPropRespDTO> list = new ArrayList<CmsModularParaPropRespDTO>();
        try {
            list = sv.queryCmsModularParaPropList(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询模块属性出现异常！",e);
            throw new BusinessException("CMS.MODULARPARAPROP.500103");
        }
        
        return list;
    }
    
    /** 
     * TODO 查询单个模块属性（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularParaPropRSV#queryCmsModularParaProp(com.zengshi.ecp.cms.dubbo.dto.CmsModularParaPropReqDTO) 
     */
    @Override
    public CmsModularParaPropRespDTO queryCmsModularParaProp(CmsModularParaPropReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用查询模块属性开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.根据条件检索模块属性  */
        CmsModularParaPropRespDTO cmsModularParaPropRespDTO = new CmsModularParaPropRespDTO();
        try {
            cmsModularParaPropRespDTO = sv.queryCmsModularParaProp(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "根据ID查询模块属性出现异常！",e);
            throw new BusinessException("CMS.MODULARPARAPROP.500103");
        }
        
        return cmsModularParaPropRespDTO;
    }
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularParaPropRSV#deleteCmsModularParaProp(java.lang.Long) 
     */
    @Override
    public void deleteCmsModularParaProp(String id) throws BusinessException {
        LogUtil.info(MODULE, "调用删除模块属性开始，入参：{id="+id+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isBlank(id)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑删除模块属性  */
        try{
            sv.deleteCmsModularParaProp(id);
        } catch(Exception err){
            LogUtil.error(MODULE, "删除模块属性失败！",err);
            throw new BusinessException("CMS.MODULARPARAPROP.500101");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularParaPropRSV#deleteCmsModularParaPropBatch(java.util.List) 
     */
    @Override
    public void deleteCmsModularParaPropBatch(List<String> list) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除模块属性开始，入参：{list="+list.toArray()+"}");
        /* 1.验证前店入参 */
        if(CollectionUtils.isEmpty(list)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="list";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑批量删除模块属性  */
        try{
            sv.deleteCmsModularParaPropBatch(list);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量删除模块属性失败！",err);
            throw new BusinessException("CMS.MODULARPARAPROP.500104");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularParaPropRSV#changeStatusCmsModularParaProp(java.lang.Long, java.lang.String) 
     */
    @Override
    public void changeStatusCmsModularParaProp(String id,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除模块属性开始，入参：{id="+id+",status="+status+"}");
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
        /* 2.更新模块属性状态  */
        try{
            sv.changeStatusCmsModularParaProp(id, status);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新模块属性状态失败！",err);
            throw new BusinessException("CMS.MODULARPARAPROP.500106");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularParaPropRSV#changeStatusCmsModularParaPropBatch(java.util.List, java.lang.String) 
     */
    @Override
    public void changeStatusCmsModularParaPropBatch(List<String> list,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除模块属性开始，入参：{list="+list.toArray()+",status="+status+"}");
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
        
        /* 2.更新模块属性状态  */
        try{
            sv.changeStatusCmsModularParaPropBatch(list,status);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量更新模块属性状态失败！",err);
            throw new BusinessException("CMS.MODULARPARAPROP.500107");
        }
    }

}

