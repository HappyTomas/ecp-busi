package com.zengshi.ecp.cms.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.cms.dubbo.dto.CmsPageTypeReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageTypeRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageTypeRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsPageTypeSV;
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
public class CmsPageTypeRSVImpl implements ICmsPageTypeRSV{
    
    @Resource
    private ICmsPageTypeSV sv;
    
    //定义个常量，用于表示模块名称，可以使用：当前类的类名
    private static final String MODULE = CmsPageTypeRSVImpl.class.getName();

    /** 
     * TODO 是否存在页面类型.
     * 存在：true
     * 不存在：false 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public boolean isExistPageType(CmsPageTypeReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用查询页面类型开始，入参：{dto="+dto.toString()+"}");
        /*1.验证前店入参*/
        if(StringUtil.isBlank(dto.getPageTypeName())){
            LogUtil.error(MODULE, "入参PageTypeName为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="PageTypeName";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getStatusSet())){
            LogUtil.error(MODULE, "入参StatusSet为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="StatusSet";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.根据条件检索页面类型  */
        return sv.isExistPageType(dto);
    }

    /** 
     * TODO 新增页面类型. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageTypeRSV#saveCmsPageType(com.zengshi.ecp.cms.dubbo.dto.CmsPageTypeReqDTO) 
     */
    @Override
    public CmsPageTypeRespDTO addCmsPageType(CmsPageTypeReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用新增页面类型开始，入参：{dto="+dto.toString()+"}");
        CmsPageTypeRespDTO respDTO = new CmsPageTypeRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isBlank(dto.getPageTypeName())){
            LogUtil.error(MODULE, "入参PageTypeName为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="PageTypeName";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{   
            respDTO = sv.addCmsPageType(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "新增页面类型失败:",err);
            throw new BusinessException("CMS.PAGETYPE.500101");
        }
        
        return respDTO;
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageTypeRSV#updateCmsPageType(com.zengshi.ecp.cms.dubbo.dto.CmsPageTypeReqDTO) 
     */
    @Override
    public CmsPageTypeRespDTO updateCmsPageType(CmsPageTypeReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用更新页面类型开始，入参：{dto="+dto.toString()+"}");
        CmsPageTypeRespDTO respDTO = new CmsPageTypeRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isBlank(dto.getPageTypeName())){
            LogUtil.error(MODULE, "入参PageTypeName为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="PageTypeName";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{    
            respDTO = sv.updateCmsPageType(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新页面类型失败！",err);
            throw new BusinessException("CMS.PAGETYPE.500102");
        }
        
        return respDTO;
    }
    
    /** 
     * TODO 查询页面类型，分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageTypeRSV#queryCmsPageTypePage(com.zengshi.ecp.cms.dubbo.dto.CmsPageTypeReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsPageTypeRespDTO> queryCmsPageTypePage(CmsPageTypeReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用分页查询页面类型开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索页面类型  */
        PageResponseDTO<CmsPageTypeRespDTO> pageInfo = PageResponseDTO.buildByBaseInfo(dto, CmsPageTypeRespDTO.class);
        try{
            pageInfo =  sv.queryCmsPageTypePage(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "查询页面类型失败！",err);
            throw new BusinessException("CMS.PAGETYPE.500103");
        }
        return pageInfo;
    }
    
    /** 
     * TODO 查询页面类型  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public List<CmsPageTypeRespDTO> queryCmsPageTypeList(CmsPageTypeReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用查询页面类型开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索页面类型  */
        List<CmsPageTypeRespDTO> list = new ArrayList<CmsPageTypeRespDTO>();
        try {
            list = sv.queryCmsPageTypeList(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询页面类型出现异常！",e);
            throw new BusinessException("CMS.PAGETYPE.500103");
        }
        
        return list;
    }
    
    /** 
     * TODO 查询单个页面类型（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageTypeRSV#queryCmsPageType(com.zengshi.ecp.cms.dubbo.dto.CmsPageTypeReqDTO) 
     */
    @Override
    public CmsPageTypeRespDTO queryCmsPageType(CmsPageTypeReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用查询页面类型开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.根据条件检索页面类型  */
        CmsPageTypeRespDTO cmsPageTypeRespDTO = new CmsPageTypeRespDTO();
        try {
            cmsPageTypeRespDTO = sv.queryCmsPageType(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "根据ID查询页面类型出现异常！",e);
            throw new BusinessException("CMS.PAGETYPE.500103");
        }
        
        return cmsPageTypeRespDTO;
    }
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageTypeRSV#deleteCmsPageType(java.lang.Long) 
     */
    @Override
    public void deleteCmsPageType(String id) throws BusinessException {
        LogUtil.info(MODULE, "调用删除页面类型开始，入参：{id="+id+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isBlank(id)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑删除页面类型  */
        try{
            sv.deleteCmsPageType(id);
        } catch(Exception err){
            LogUtil.error(MODULE, "删除页面类型失败！",err);
            throw new BusinessException("CMS.PAGETYPE.500101");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageTypeRSV#deleteCmsPageTypeBatch(java.util.List) 
     */
    @Override
    public void deleteCmsPageTypeBatch(List<String> list) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除页面类型开始，入参：{list="+list.toArray()+"}");
        /* 1.验证前店入参 */
        if(CollectionUtils.isEmpty(list)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="list";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑批量删除页面类型  */
        try{
            sv.deleteCmsPageTypeBatch(list);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量删除页面类型失败！",err);
            throw new BusinessException("CMS.PAGETYPE.500104");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageTypeRSV#changeStatusCmsPageType(java.lang.Long, java.lang.String) 
     */
    @Override
    public void changeStatusCmsPageType(String id,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除页面类型开始，入参：{id="+id+",status="+status+"}");
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
        /* 2.更新页面类型状态  */
        try{
            sv.changeStatusCmsPageType(id, status);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新页面类型状态失败！",err);
            throw new BusinessException("CMS.PAGETYPE.500106");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageTypeRSV#changeStatusCmsPageTypeBatch(java.util.List, java.lang.String) 
     */
    @Override
    public void changeStatusCmsPageTypeBatch(List<String> list,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除页面类型开始，入参：{list="+list.toArray()+",status="+status+"}");
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
        
        /* 2.更新页面类型状态  */
        try{
            sv.changeStatusCmsPageTypeBatch(list,status);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量更新页面类型状态失败！",err);
            throw new BusinessException("CMS.PAGETYPE.500107");
        }
    }
    
    /** 
     * TODO 查询页面类型  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    /*@Override
    public List<CmsPageTypeRespDTO> queryCmsDefaultPageTypeList() throws BusinessException {
        LogUtil.info(MODULE, "调用查询页面类型开始，入参：{}");
         1.验证前店入参 
        
         2.根据条件检索页面类型  
        List<CmsPageTypeRespDTO> list = new ArrayList<CmsPageTypeRespDTO>();
        try {
            //2.1缓存查询
            Map<Long ,CmsPageTypeRespDTO> map = (HashMap<Long ,CmsPageTypeRespDTO> )CacheUtil.getItem(CmsConstants.ParamConfig.CMS_PAGETYPE_CACHE);
            if(CollectionUtils.isEmpty(map)){
                for(CmsPageTypeRespDTO respDTO:map.values()){
                    if(respDTO != null && CmsConstants.IsNot.CMS_ISNOT_1.equals(respDTO.getIsdefault())){
                        list.add(respDTO);
                    }
                }
            }else{//2.2 当缓存中没有时，取表
                CmsPageTypeReqDTO dto = new CmsPageTypeReqDTO();
                dto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                dto.setIsdefault(CmsConstants.IsNot.CMS_ISNOT_1);
                list = sv.queryCmsPageTypeList(dto);
            }
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询页面类型出现异常！",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_PAGETYPE_500103);
        }
        
        return list;
    }*/

}

