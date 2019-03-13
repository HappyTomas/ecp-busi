package com.zengshi.ecp.cms.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.cms.dubbo.dto.CmsModularReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsModularSV;
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
public class CmsModularRSVImpl implements ICmsModularRSV{
    
    @Resource
    private ICmsModularSV sv;
    
    //定义个常量，用于表示模块名称，可以使用：当前类的类名
    private static final String MODULE = CmsModularRSVImpl.class.getName();

    /** 
     * TODO 是否存在模块.
     * 存在：true
     * 不存在：false 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public boolean isExistModular(CmsModularReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用查询模块开始，入参：{dto="+dto.toString()+"}");
        /*1.验证前店入参*/
        if(StringUtil.isBlank(dto.getModularName())){
            LogUtil.error(MODULE, "入参ModularName为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="ModularName";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getStatusSet())){
            LogUtil.error(MODULE, "入参StatusSet为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="StatusSet";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.根据条件检索页面类型  */
        return sv.isExistModular(dto);
    }

    /** 
     * TODO 新增基础模块. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularRSV#saveCmsModular(com.zengshi.ecp.cms.dubbo.dto.CmsModularReqDTO) 
     */
    @Override
    public CmsModularRespDTO addCmsModular(CmsModularReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用新增基础模块开始，入参：{dto="+dto.toString()+"}");
        CmsModularRespDTO respDTO = new CmsModularRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getModularName())){
            LogUtil.error(MODULE, "入参ModularName为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="ModularName";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getModularType())){
            LogUtil.error(MODULE, "入参ModularType为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="ModularType";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{   
            respDTO = sv.addCmsModular(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "新增基础模块失败:",err);
            throw new BusinessException("CMS.MODULAR.500101");
        }
        
        return respDTO;
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularRSV#updateCmsModular(com.zengshi.ecp.cms.dubbo.dto.CmsModularReqDTO) 
     */
    @Override
    public CmsModularRespDTO updateCmsModular(CmsModularReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用更新基础模块开始，入参：{dto="+dto.toString()+"}");
        CmsModularRespDTO respDTO = new CmsModularRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{    
            respDTO = sv.updateCmsModular(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新基础模块失败！",err);
            throw new BusinessException("CMS.MODULAR.500102");
        }
        
        return respDTO;
    }
    
    /** 
     * TODO 查询基础模块，分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularRSV#queryCmsModularPage(com.zengshi.ecp.cms.dubbo.dto.CmsModularReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsModularRespDTO> queryCmsModularPage(CmsModularReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用分页查询基础模块开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索基础模块  */
        PageResponseDTO<CmsModularRespDTO> pageInfo = PageResponseDTO.buildByBaseInfo(dto, CmsModularRespDTO.class);
        try{
            pageInfo =  sv.queryCmsModularPage(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "查询基础模块失败！",err);
            throw new BusinessException("CMS.MODULAR.500103");
        }
        return pageInfo;
    }
    
    /** 
     * TODO 查询基础模块  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public List<CmsModularRespDTO> queryCmsModularList(CmsModularReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用查询基础模块开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索基础模块  */
        List<CmsModularRespDTO> list = new ArrayList<CmsModularRespDTO>();
        try {
            list = sv.queryCmsModularList(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询基础模块出现异常！",e);
            throw new BusinessException("CMS.MODULAR.500103");
        }
        
        return list;
    }
    
    /** 
     * TODO 查询单个基础模块（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularRSV#queryCmsModular(com.zengshi.ecp.cms.dubbo.dto.CmsModularReqDTO) 
     */
    @Override
    public CmsModularRespDTO queryCmsModular(CmsModularReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用查询基础模块开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.根据条件检索基础模块  */
        CmsModularRespDTO cmsModularRespDTO = new CmsModularRespDTO();
        try {
            cmsModularRespDTO = sv.queryCmsModular(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "根据ID查询基础模块出现异常！",e);
            throw new BusinessException("CMS.MODULAR.500103");
        }
        
        return cmsModularRespDTO;
    }
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularRSV#deleteCmsModular(java.lang.Long) 
     */
    @Override
    public void deleteCmsModular(String id) throws BusinessException {
        LogUtil.info(MODULE, "调用删除基础模块开始，入参：{id="+id+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isBlank(id)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑删除基础模块  */
        try{
            sv.deleteCmsModular(id);
        } catch(Exception err){
            LogUtil.error(MODULE, "删除基础模块失败！",err);
            throw new BusinessException("CMS.MODULAR.500101");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularRSV#deleteCmsModularBatch(java.util.List) 
     */
    @Override
    public void deleteCmsModularBatch(List<String> list) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除基础模块开始，入参：{list="+list.toArray()+"}");
        /* 1.验证前店入参 */
        if(CollectionUtils.isEmpty(list)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="list";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑批量删除基础模块  */
        try{
            sv.deleteCmsModularBatch(list);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量删除基础模块失败！",err);
            throw new BusinessException("CMS.MODULAR.500104");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularRSV#changeStatusCmsModular(java.lang.Long, java.lang.String) 
     */
    @Override
    public void changeStatusCmsModular(String id,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除基础模块开始，入参：{id="+id+",status="+status+"}");
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
        /* 2.更新基础模块状态  */
        try{
            sv.changeStatusCmsModular(id, status);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新基础模块状态失败！",err);
            throw new BusinessException("CMS.MODULAR.500106");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularRSV#changeStatusCmsModularBatch(java.util.List, java.lang.String) 
     */
    @Override
    public void changeStatusCmsModularBatch(List<String> list,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除基础模块开始，入参：{list="+list.toArray()+",status="+status+"}");
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
        
        /* 2.更新基础模块状态  */
        try{
            sv.changeStatusCmsModularBatch(list,status);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量更新基础模块状态失败！",err);
            throw new BusinessException("CMS.MODULAR.500107");
        }
    }
    
    /** 
     * TODO 查询基础模块  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    /*@Override
    public List<CmsModularRespDTO> queryCmsDefaultModularList() throws BusinessException {
        LogUtil.info(MODULE, "调用查询基础模块开始，入参：{}");
         1.验证前店入参 
        
         2.根据条件检索基础模块  
        List<CmsModularRespDTO> list = new ArrayList<CmsModularRespDTO>();
        try {
            //2.1缓存查询
            Map<Long ,CmsModularRespDTO> map = (HashMap<Long ,CmsModularRespDTO> )CacheUtil.getItem(CmsConstants.ParamConfig.CMS.MODULAR.CACHE);
            if(CollectionUtils.isEmpty(map)){
                for(CmsModularRespDTO respDTO:map.values()){
                    if(respDTO != null && CmsConstants.IsNot.CMS_ISNOT_1.equals(respDTO.getIsdefault())){
                        list.add(respDTO);
                    }
                }
            }else{//2.2 当缓存中没有时，取表
                CmsModularReqDTO dto = new CmsModularReqDTO();
                dto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                dto.setIsdefault(CmsConstants.IsNot.CMS_ISNOT_1);
                list = sv.queryCmsModularList(dto);
            }
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询基础模块出现异常！",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS.MODULAR.500103);
        }
        
        return list;
    }*/

}

