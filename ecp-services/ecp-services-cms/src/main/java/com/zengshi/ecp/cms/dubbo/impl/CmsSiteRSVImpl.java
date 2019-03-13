package com.zengshi.ecp.cms.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.cms.dubbo.dto.CmsSiteReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsSiteRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsPlaceSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsSiteSV;
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
public class CmsSiteRSVImpl implements ICmsSiteRSV{
    
    @Resource
    private ICmsSiteSV sv;
    @Resource
    private ICmsPlaceSV cmsPlaceSV;
    
    //定义个常量，用于表示模块名称，可以使用：当前类的类名
    private static final String MODULE = CmsSiteRSVImpl.class.getName();


    /** 
     * TODO 新增站点. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsSiteRSV#saveCmsSite(com.zengshi.ecp.cms.dubbo.dto.CmsSiteReqDTO) 
     */
    @Override
    public CmsSiteRespDTO addCmsSite(CmsSiteReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用新增站点开始，入参：{dto="+dto.toString()+"}");
        CmsSiteRespDTO respDTO = new CmsSiteRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isBlank(dto.getSiteName())){
            LogUtil.error(MODULE, "入参SiteName为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="SiteName";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isBlank(dto.getSiteUrl())){
            LogUtil.error(MODULE, "入参SiteUrl为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="SiteUrl";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{   
            respDTO = sv.addCmsSite(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "新增站点失败:",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_SITE_500101);
        }
        
        return respDTO;
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsSiteRSV#updateCmsSite(com.zengshi.ecp.cms.dubbo.dto.CmsSiteReqDTO) 
     */
    @Override
    public CmsSiteRespDTO updateCmsSite(CmsSiteReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用更新站点开始，入参：{dto="+dto.toString()+"}");
        CmsSiteRespDTO respDTO = new CmsSiteRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isBlank(dto.getSiteName())){
            LogUtil.error(MODULE, "入参SiteName为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="SiteName";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isBlank(dto.getSiteUrl())){
            LogUtil.error(MODULE, "入参SiteUrl为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="SiteUrl";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{    
            respDTO = sv.updateCmsSite(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新站点失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_SITE_500102);
        }
        
        return respDTO;
    }
    
    /** 
     * TODO 查询站点，分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsSiteRSV#queryCmsSitePage(com.zengshi.ecp.cms.dubbo.dto.CmsSiteReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsSiteRespDTO> queryCmsSitePage(CmsSiteReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用分页查询站点开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索站点  */
        PageResponseDTO<CmsSiteRespDTO> pageInfo = PageResponseDTO.buildByBaseInfo(dto, CmsSiteRespDTO.class);
        try{
            pageInfo =  sv.queryCmsSitePage(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "查询站点失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_SITE_500103);
        }
        return pageInfo;
    }
    
    /** 
     * TODO 查询站点  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public List<CmsSiteRespDTO> queryCmsSiteList(CmsSiteReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用查询站点开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索站点  */
        List<CmsSiteRespDTO> list = new ArrayList<CmsSiteRespDTO>();
        try {
            list = sv.queryCmsSiteList(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询站点出现异常！",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_SITE_500103);
        }
        
        return list;
    }
    
    /** 
     * TODO 查询单个站点（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsSiteRSV#queryCmsSite(com.zengshi.ecp.cms.dubbo.dto.CmsSiteReqDTO) 
     */
    @Override
    public CmsSiteRespDTO queryCmsSite(CmsSiteReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用查询站点开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.根据条件检索站点  */
        CmsSiteRespDTO cmsSiteRespDTO = new CmsSiteRespDTO();
        try {
            cmsSiteRespDTO = sv.queryCmsSite(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "根据ID查询站点出现异常！",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_SITE_500103);
        }
        
        return cmsSiteRespDTO;
    }
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsSiteRSV#deleteCmsSite(java.lang.Long) 
     */
    @Override
    public void deleteCmsSite(String id) throws BusinessException {
        LogUtil.info(MODULE, "调用删除站点开始，入参：{id="+id+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isBlank(id)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑删除站点  */
        try{
            sv.deleteCmsSite(id);
        } catch(Exception err){
            LogUtil.error(MODULE, "删除站点失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_SITE_500101);
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsSiteRSV#deleteCmsSiteBatch(java.util.List) 
     */
    @Override
    public void deleteCmsSiteBatch(List<String> list) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除站点开始，入参：{list="+list.toArray()+"}");
        /* 1.验证前店入参 */
        if(CollectionUtils.isEmpty(list)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="list";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑批量删除站点  */
        try{
            sv.deleteCmsSiteBatch(list);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量删除站点失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_SITE_500104);
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsSiteRSV#changeStatusCmsSite(java.lang.Long, java.lang.String) 
     */
    @Override
    public void changeStatusCmsSite(String id,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除站点开始，入参：{id="+id+",status="+status+"}");
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
        /* 2.更新站点状态  */
        try{
            sv.changeStatusCmsSite(id, status);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新站点状态失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_SITE_500106);
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsSiteRSV#changeStatusCmsSiteBatch(java.util.List, java.lang.String) 
     */
    @Override
    public void changeStatusCmsSiteBatch(List<String> list,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除站点开始，入参：{list="+list.toArray()+",status="+status+"}");
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
        
        /* 2.更新站点状态  */
        try{
            sv.changeStatusCmsSiteBatch(list,status);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量更新站点状态失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_SITE_500107);
        }
    }
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsSiteRSV#changeStatusCmsSite(java.lang.Long, java.lang.String) 
     */
    @Override
    public void doDefaultCmsSite(String id,String isDefault) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除站点开始，入参：{id="+id+",isDefault="+isDefault+"}");
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
        /* 2.更新站点状态  */
        try{
            sv.doDefaultCmsSite(id, isDefault);
        } catch(Exception err){
            LogUtil.error(MODULE, "设置默认站点失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_SITE_500108);
        }
    }
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsSiteRSV#changeStatusCmsSiteBatch(java.util.List, java.lang.String) 
     */
    @Override
    public void doDefaultCmsSiteBatch(List<String> list,String isDefault) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除站点开始，入参：{list="+list.toArray()+",isDefault="+isDefault+"}");
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
        
        /* 2.更新站点状态  */
        try{
            sv.doDefaultCmsSiteBatch(list,isDefault);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量设置默认站点失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_SITE_500109);
        }
    }
    
    /** 
     * TODO 查询站点  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    /*@Override
    public List<CmsSiteRespDTO> queryCmsDefaultSiteList() throws BusinessException {
        LogUtil.info(MODULE, "调用查询站点开始，入参：{}");
         1.验证前店入参 
        
         2.根据条件检索站点  
        List<CmsSiteRespDTO> list = new ArrayList<CmsSiteRespDTO>();
        try {
            //2.1缓存查询
            Map<Long ,CmsSiteRespDTO> map = (HashMap<Long ,CmsSiteRespDTO> )CacheUtil.getItem(CmsConstants.ParamConfig.CMS_SITE_CACHE);
            if(CollectionUtils.isEmpty(map)){
                for(CmsSiteRespDTO respDTO:map.values()){
                    if(respDTO != null && CmsConstants.IsNot.CMS_ISNOT_1.equals(respDTO.getIsdefault())){
                        list.add(respDTO);
                    }
                }
            }else{//2.2 当缓存中没有时，取表
                CmsSiteReqDTO dto = new CmsSiteReqDTO();
                dto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                dto.setIsdefault(CmsConstants.IsNot.CMS_ISNOT_1);
                list = sv.queryCmsSiteList(dto);
            }
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询站点出现异常！",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_SITE_500103);
        }
        
        return list;
    }*/

}

