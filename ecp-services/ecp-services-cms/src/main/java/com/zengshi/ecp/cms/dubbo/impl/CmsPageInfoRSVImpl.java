package com.zengshi.ecp.cms.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageInfoRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsPageInfoSV;
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
public class CmsPageInfoRSVImpl implements ICmsPageInfoRSV{
    
    @Resource
    private ICmsPageInfoSV sv;
    
    //定义个常量，用于表示模块名称，可以使用：当前类的类名
    private static final String MODULE = CmsPageInfoRSVImpl.class.getName();


    /** 
     * TODO 新增页面. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageInfoRSV#saveCmsPageInfo(com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoReqDTO) 
     */
    @Override
    public CmsPageInfoRespDTO addCmsPageInfo(CmsPageInfoReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用新增页面开始，入参：{dto="+dto.toString()+"}");
        CmsPageInfoRespDTO respDTO = new CmsPageInfoRespDTO();
        /*1.验证前店入参*/
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
        
        /*2.调service层接口*/
        try{   
            respDTO = sv.addCmsPageInfo(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "新增页面失败:",err);
            throw new BusinessException("CMS.PAGEINFO.500101");
        }
        
        return respDTO;
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageInfoRSV#updateCmsPageInfo(com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoReqDTO) 
     */
    @Override
    public CmsPageInfoRespDTO updateCmsPageInfo(CmsPageInfoReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用更新页面开始，入参：{dto="+dto.toString()+"}");
        CmsPageInfoRespDTO respDTO = new CmsPageInfoRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
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
        
        /*2.调service层接口*/
        try{    
            respDTO = sv.updateCmsPageInfo(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新页面失败！",err);
            throw new BusinessException("CMS.PAGEINFO.500102");
        }
        
        return respDTO;
    }
    
    /** 
     * TODO 查询页面，分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageInfoRSV#queryCmsPageInfoPage(com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsPageInfoRespDTO> queryCmsPageInfoPage(CmsPageInfoReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用分页查询页面开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索页面  */
        PageResponseDTO<CmsPageInfoRespDTO> pageInfo = PageResponseDTO.buildByBaseInfo(dto, CmsPageInfoRespDTO.class);
        try{
            pageInfo =  sv.queryCmsPageInfoPage(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "查询页面失败！",err);
            throw new BusinessException("CMS.PAGEINFO.500103");
        }
        return pageInfo;
    }
    
    /** 
     * TODO 查询页面  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public List<CmsPageInfoRespDTO> queryCmsPageInfoList(CmsPageInfoReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用查询页面开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索页面  */
        List<CmsPageInfoRespDTO> list = new ArrayList<CmsPageInfoRespDTO>();
        try {
            list = sv.queryCmsPageInfoList(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询页面出现异常！",e);
            throw new BusinessException("CMS.PAGEINFO.500103");
        }
        
        return list;
    }
    
    /** 
     * TODO 查询单个页面（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageInfoRSV#queryCmsPageInfo(com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoReqDTO) 
     */
    @Override
    public CmsPageInfoRespDTO queryCmsPageInfo(CmsPageInfoReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用查询页面开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.根据条件检索页面  */
        CmsPageInfoRespDTO cmsPageInfoRespDTO = new CmsPageInfoRespDTO();
        try {
            cmsPageInfoRespDTO = sv.queryCmsPageInfo(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "根据ID查询页面出现异常！",e);
            throw new BusinessException("CMS.PAGEINFO.500103");
        }
        
        return cmsPageInfoRespDTO;
    }
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageInfoRSV#deleteCmsPageInfo(java.lang.Long) 
     */
    @Override
    public void deleteCmsPageInfo(String id) throws BusinessException {
        LogUtil.info(MODULE, "调用删除页面开始，入参：{id="+id+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isBlank(id)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑删除页面  */
        try{
            sv.deleteCmsPageInfo(id);
        } catch(Exception err){
            LogUtil.error(MODULE, "删除页面失败！",err);
            throw new BusinessException("CMS.PAGEINFO.500101");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageInfoRSV#deleteCmsPageInfoBatch(java.util.List) 
     */
    @Override
    public void deleteCmsPageInfoBatch(List<String> list) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除页面开始，入参：{list="+list.toArray()+"}");
        /* 1.验证前店入参 */
        if(CollectionUtils.isEmpty(list)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="list";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑批量删除页面  */
        try{
            sv.deleteCmsPageInfoBatch(list);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量删除页面失败！",err);
            throw new BusinessException("CMS.PAGEINFO.500104");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageInfoRSV#changeStatusCmsPageInfo(java.lang.Long, java.lang.String) 
     */
    @Override
    public void changeStatusCmsPageInfo(String id,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除页面开始，入参：{id="+id+",status="+status+"}");
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
        /* 2.更新页面状态  */
        try{
            sv.changeStatusCmsPageInfo(id, status);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新页面状态失败！",err);
            throw new BusinessException("CMS.PAGEINFO.500106");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageInfoRSV#changeStatusCmsPageInfoBatch(java.util.List, java.lang.String) 
     */
    @Override
    public void changeStatusCmsPageInfoBatch(List<String> list,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除页面开始，入参：{list="+list.toArray()+",status="+status+"}");
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
        
        /* 2.更新页面状态  */
        try{
            sv.changeStatusCmsPageInfoBatch(list,status);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量更新页面状态失败！",err);
            throw new BusinessException("CMS.PAGEINFO.500107");
        }
    }
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageInfoRSV#changeStatusCmsPageInfo(java.lang.Long, java.lang.String) 
     */
    @Override
    public void doDefaultCmsPageInfo(String id,String isDefault) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除页面开始，入参：{id="+id+",isDefault="+isDefault+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isBlank(id)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isBlank(isDefault)){
            LogUtil.error(MODULE, "入参isDefault为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="isDefault";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        /* 2.更新页面状态  */
        try{
            sv.doDefaultCmsPageInfo(id, isDefault);
        } catch(Exception err){
            LogUtil.error(MODULE, "设置默认页面失败！",err);
            throw new BusinessException("CMS.PAGEINFO.500108");
        }
    }
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageInfoRSV#changeStatusCmsPageInfoBatch(java.util.List, java.lang.String) 
     */
    @Override
    public void doDefaultCmsPageInfoBatch(List<String> list,String isDefault) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除页面开始，入参：{list="+list.toArray()+",isDefault="+isDefault+"}");
        /* 1.验证前店入参 */
        if(CollectionUtils.isEmpty(list)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="list";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isBlank(isDefault)){
            LogUtil.error(MODULE, "入参isDefault为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="isDefault";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.更新页面状态  */
        try{
            sv.doDefaultCmsPageInfoBatch(list,isDefault);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量设置默认页面失败！",err);
            throw new BusinessException("CMS.PAGEINFO.500109");
        }
    }
    
    /** 
     * TODO 查询页面  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    /*@Override
    public List<CmsPageInfoRespDTO> queryCmsDefaultPageInfoList() throws BusinessException {
        LogUtil.info(MODULE, "调用查询页面开始，入参：{}");
         1.验证前店入参 
        
         2.根据条件检索页面  
        List<CmsPageInfoRespDTO> list = new ArrayList<CmsPageInfoRespDTO>();
        try {
            //2.1缓存查询
            Map<Long ,CmsPageInfoRespDTO> map = (HashMap<Long ,CmsPageInfoRespDTO> )CacheUtil.getItem(CmsConstants.ParamConfig.CMS_PAGEINFO_CACHE);
            if(CollectionUtils.isEmpty(map)){
                for(CmsPageInfoRespDTO respDTO:map.values()){
                    if(respDTO != null && CmsConstants.IsNot.CMS_ISNOT_1.equals(respDTO.getIsdefault())){
                        list.add(respDTO);
                    }
                }
            }else{//2.2 当缓存中没有时，取表
                CmsPageInfoReqDTO dto = new CmsPageInfoReqDTO();
                dto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                dto.setIsdefault(CmsConstants.IsNot.CMS_ISNOT_1);
                list = sv.queryCmsPageInfoList(dto);
            }
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询页面出现异常！",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_PAGEINFO_500103);
        }
        
        return list;
    }*/

}

