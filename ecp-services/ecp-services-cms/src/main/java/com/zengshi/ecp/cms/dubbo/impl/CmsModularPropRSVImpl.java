package com.zengshi.ecp.cms.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.cms.dubbo.dto.CmsModularParaPropRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularPropReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularPropRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularPropRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsModularPropSV;
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
public class CmsModularPropRSVImpl implements ICmsModularPropRSV{
    
    @Resource
    private ICmsModularPropSV sv;
    
    //定义个常量，用于表示模块名称，可以使用：当前类的类名
    private static final String MODULE = CmsModularPropRSVImpl.class.getName();


    /** 
     * TODO 新增模块与属性关系. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularPropRSV#saveCmsModularProp(com.zengshi.ecp.cms.dubbo.dto.CmsModularPropReqDTO) 
     */
    @Override
    public CmsModularPropRespDTO addCmsModularProp(CmsModularPropReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用新增模块与属性关系开始，入参：{dto="+dto.toString()+"}");
        CmsModularPropRespDTO respDTO = new CmsModularPropRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getPropId())){
            LogUtil.error(MODULE, "入参PropId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="PropId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getModularId())){
            LogUtil.error(MODULE, "入参PropValue为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="PropValue";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{   
            respDTO = sv.addCmsModularProp(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "新增模块与属性关系失败:",err);
            throw new BusinessException("CMS.MODULARPROP.500101");
        }
        
        return respDTO;
    }
    /** 
     * TODO 新增模块与属性关系. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularPropRSV#saveCmsModularProp(com.zengshi.ecp.cms.dubbo.dto.CmsModularPropReqDTO) 
     */
    @Override
    public void addCmsModularPropList(List<CmsModularPropReqDTO> list) throws BusinessException {
    	for (CmsModularPropReqDTO cmsModularPropReqDTO:list) {
			this.addCmsModularProp(cmsModularPropReqDTO);
		}
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularPropRSV#updateCmsModularProp(com.zengshi.ecp.cms.dubbo.dto.CmsModularPropReqDTO) 
     */
    @Override
    public CmsModularPropRespDTO updateCmsModularProp(CmsModularPropReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用更新模块与属性关系开始，入参：{dto="+dto.toString()+"}");
        CmsModularPropRespDTO respDTO = new CmsModularPropRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{    
            respDTO = sv.updateCmsModularProp(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新模块与属性关系失败！",err);
            throw new BusinessException("CMS.MODULARPROP.500102");
        }
        
        return respDTO;
    }
    
    /** 
     * TODO 查询模块与属性关系，分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularPropRSV#queryCmsModularPropPage(com.zengshi.ecp.cms.dubbo.dto.CmsModularPropReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsModularPropRespDTO> queryCmsModularPropPage(CmsModularPropReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用分页查询模块与属性关系开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索模块与属性关系  */
        PageResponseDTO<CmsModularPropRespDTO> pageInfo = PageResponseDTO.buildByBaseInfo(dto, CmsModularPropRespDTO.class);
        try{
            pageInfo =  sv.queryCmsModularPropPage(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "查询模块与属性关系失败！",err);
            throw new BusinessException("CMS.MODULARPROP.500103");
        }
        return pageInfo;
    }
    
    /** 
     * queryCmsModularParaPropList:(查询模块下的属性列表). <br/> 
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
    public List<CmsModularParaPropRespDTO> queryCmsModularParaPropList(CmsModularPropReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用查询模块与属性关系开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索模块与属性关系  */
        List<CmsModularParaPropRespDTO> list = new ArrayList<CmsModularParaPropRespDTO>();
        try {
            list = sv.queryCmsModularParaPropList(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询模块属性列表出现异常！",e);
            throw new BusinessException("CMS.MODULARPROP.500108");
        }
        
        return list;
    }
    
    /** 
     * TODO 查询模块与属性关系  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public List<CmsModularPropRespDTO> queryCmsModularPropList(CmsModularPropReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用查询模块与属性关系开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索模块与属性关系  */
        List<CmsModularPropRespDTO> list = new ArrayList<CmsModularPropRespDTO>();
        try {
            list = sv.queryCmsModularPropList(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询模块与属性关系出现异常！",e);
            throw new BusinessException("CMS.MODULARPROP.500103");
        }
        
        return list;
    }
    
    /** 
     * TODO 查询单个模块与属性关系（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularPropRSV#queryCmsModularProp(com.zengshi.ecp.cms.dubbo.dto.CmsModularPropReqDTO) 
     */
    @Override
    public CmsModularPropRespDTO queryCmsModularProp(CmsModularPropReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用查询模块与属性关系开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.根据条件检索模块与属性关系  */
        CmsModularPropRespDTO cmsModularPropRespDTO = new CmsModularPropRespDTO();
        try {
            cmsModularPropRespDTO = sv.queryCmsModularProp(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "根据ID查询模块与属性关系出现异常！",e);
            throw new BusinessException("CMS.MODULARPROP.500103");
        }
        
        return cmsModularPropRespDTO;
    }
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularPropRSV#deleteCmsModularProp(java.lang.Long) 
     */
    @Override
    public void deleteCmsModularProp(String id) throws BusinessException {
        LogUtil.info(MODULE, "调用删除模块与属性关系开始，入参：{id="+id+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isBlank(id)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑删除模块与属性关系  */
        try{
            sv.deleteCmsModularProp(id);
        } catch(Exception err){
            LogUtil.error(MODULE, "删除模块与属性关系失败！",err);
            throw new BusinessException("CMS.MODULARPROP.500101");
        }
    }
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularPropRSV#deleteCmsModularProp(java.lang.Long) 
     */
    @Override
    public void deleteCmsModularPropByModularId(String modularId) throws BusinessException {
    	LogUtil.info(MODULE, "调用删除模块与属性关系开始，入参：{id="+modularId+"}");
    	/* 1.验证前店入参 */
    	if(StringUtil.isBlank(modularId)){
    		LogUtil.error(MODULE, "入参modularId为空！");
    		String[] keyInfos = new String[1];
    		keyInfos[0]="id";
    		throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
    	}
    	
    	/* 2.逻辑删除模块与属性关系  */
    	try{
    		sv.deleteCmsModularPropByModularId(modularId);
    	} catch(Exception err){
    		LogUtil.error(MODULE, "删除模块与属性关系失败！",err);
    		throw new BusinessException("CMS.MODULARPROP.500101");
    	}
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularPropRSV#deleteCmsModularPropBatch(java.util.List) 
     */
    @Override
    public void deleteCmsModularPropBatch(List<String> list) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除模块与属性关系开始，入参：{list="+list.toArray()+"}");
        /* 1.验证前店入参 */
        if(CollectionUtils.isEmpty(list)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="list";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑批量删除模块与属性关系  */
        try{
            sv.deleteCmsModularPropBatch(list);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量删除模块与属性关系失败！",err);
            throw new BusinessException("CMS.MODULARPROP.500104");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularPropRSV#changeStatusCmsModularProp(java.lang.Long, java.lang.String) 
     */
    @Override
    public void changeStatusCmsModularProp(String id,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除模块与属性关系开始，入参：{id="+id+",status="+status+"}");
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
        /* 2.更新模块与属性关系状态  */
        try{
            sv.changeStatusCmsModularProp(id, status);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新模块与属性关系状态失败！",err);
            throw new BusinessException("CMS.MODULARPROP.500106");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularPropRSV#changeStatusCmsModularPropBatch(java.util.List, java.lang.String) 
     */
    @Override
    public void changeStatusCmsModularPropBatch(List<String> list,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除模块与属性关系开始，入参：{list="+list.toArray()+",status="+status+"}");
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
        
        /* 2.更新模块与属性关系状态  */
        try{
            sv.changeStatusCmsModularPropBatch(list,status);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量更新模块与属性关系状态失败！",err);
            throw new BusinessException("CMS.MODULARPROP.500107");
        }
    }
    
}

