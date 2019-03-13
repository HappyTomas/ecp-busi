package com.zengshi.ecp.cms.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.cms.dubbo.dto.CmsPageAttrPubReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageAttrPubRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageAttrPubRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsPageAttrPubSV;
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
public class CmsPageAttrPubRSVImpl implements ICmsPageAttrPubRSV{
    
    @Resource
    private ICmsPageAttrPubSV sv;
    
    //定义个常量，用于表示模块名称，可以使用：当前类的类名
    private static final String MODULE = CmsPageAttrPubRSVImpl.class.getName();


    /** 
     * TODO 新增页面属性. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageAttrPubRSV#saveCmsAttrPub(com.zengshi.ecp.cms.dubbo.dto.CmsPageAttrPubReqDTO) 
     */
    @Override
    public CmsPageAttrPubRespDTO addCmsAttrPub(CmsPageAttrPubReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用新增页面属性开始，入参：{dto="+dto.toString()+"}");
        CmsPageAttrPubRespDTO respDTO = new CmsPageAttrPubRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getPageId())){
            LogUtil.error(MODULE, "入参PageId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="PageId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getPageAttrId())){
            LogUtil.error(MODULE, "入参PageAttrId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="PageAttrId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{   
            respDTO = sv.addCmsPageAttrPub(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "新增页面属性失败:",err);
            throw new BusinessException("CMS.PAGEATTRPUB.500101");
        }
        
        return respDTO;
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageAttrPubRSV#updateCmsAttrPub(com.zengshi.ecp.cms.dubbo.dto.CmsPageAttrPubReqDTO) 
     */
    @Override
    public CmsPageAttrPubRespDTO updateCmsAttrPub(CmsPageAttrPubReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用更新页面属性开始，入参：{dto="+dto.toString()+"}");
        CmsPageAttrPubRespDTO respDTO = new CmsPageAttrPubRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{    
            respDTO = sv.updateCmsPageAttrPub(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新页面属性失败！",err);
            throw new BusinessException("CMS.PAGEATTRPUB.500102");
        }
        
        return respDTO;
    }
    
    /** 
     * TODO 查询页面属性，分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageAttrPubRSV#queryCmsAttrPubPage(com.zengshi.ecp.cms.dubbo.dto.CmsPageAttrPubReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsPageAttrPubRespDTO> queryCmsAttrPubPage(CmsPageAttrPubReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用分页查询页面属性开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索页面属性  */
        PageResponseDTO<CmsPageAttrPubRespDTO> pageInfo = PageResponseDTO.buildByBaseInfo(dto, CmsPageAttrPubRespDTO.class);
        try{
            pageInfo =  sv.queryCmsPageAttrPubPage(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "查询页面属性失败！",err);
            throw new BusinessException("CMS.PAGEATTRPUB.500103");
        }
        return pageInfo;
    }
    
    /** 
     * TODO 查询页面属性  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public List<CmsPageAttrPubRespDTO> queryCmsAttrPubList(CmsPageAttrPubReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用查询页面属性开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索页面属性  */
        List<CmsPageAttrPubRespDTO> list = new ArrayList<CmsPageAttrPubRespDTO>();
        try {
            list = sv.queryCmsPageAttrPubList(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询页面属性出现异常！",e);
            throw new BusinessException("CMS.PAGEATTRPUB.500103");
        }
        
        return list;
    }
    
    /** 
     * TODO 查询单个页面属性（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageAttrPubRSV#queryCmsAttrPub(com.zengshi.ecp.cms.dubbo.dto.CmsPageAttrPubReqDTO) 
     */
    @Override
    public CmsPageAttrPubRespDTO queryCmsAttrPub(CmsPageAttrPubReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用查询页面属性开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.根据条件检索页面属性  */
        CmsPageAttrPubRespDTO cmsAttrPubRespDTO = new CmsPageAttrPubRespDTO();
        try {
            cmsAttrPubRespDTO = sv.queryCmsPageAttrPub(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "根据ID查询页面属性出现异常！",e);
            throw new BusinessException("CMS.PAGEATTRPUB.500103");
        }
        
        return cmsAttrPubRespDTO;
    }
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageAttrPubRSV#deleteCmsAttrPub(java.lang.Long) 
     */
    @Override
    public void deleteCmsAttrPub(String id) throws BusinessException {
        LogUtil.info(MODULE, "调用删除页面属性开始，入参：{id="+id+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isBlank(id)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑删除页面属性  */
        try{
            sv.deleteCmsPageAttrPub(id);
        } catch(Exception err){
            LogUtil.error(MODULE, "删除页面属性失败！",err);
            throw new BusinessException("CMS.PAGEATTRPUB.500101");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageAttrPubRSV#deleteCmsAttrPubBatch(java.util.List) 
     */
    @Override
    public void deleteCmsAttrPubBatch(List<String> list) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除页面属性开始，入参：{list="+list.toArray()+"}");
        /* 1.验证前店入参 */
        if(CollectionUtils.isEmpty(list)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="list";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑批量删除页面属性  */
        try{
            sv.deleteCmsPageAttrPubBatch(list);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量删除页面属性失败！",err);
            throw new BusinessException("CMS.PAGEATTRPUB.500104");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageAttrPubRSV#changeStatusCmsAttrPub(java.lang.Long, java.lang.String) 
     */
    @Override
    public void changeStatusCmsAttrPub(String id,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除页面属性开始，入参：{id="+id+",status="+status+"}");
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
        /* 2.更新页面属性状态  */
        try{
            sv.changeStatusCmsPageAttrPub(id, status);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新页面属性状态失败！",err);
            throw new BusinessException("CMS.PAGEATTRPUB.500106");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageAttrPubRSV#changeStatusCmsAttrPubBatch(java.util.List, java.lang.String) 
     */
    @Override
    public void changeStatusCmsAttrPubBatch(List<String> list,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除页面属性开始，入参：{list="+list.toArray()+",status="+status+"}");
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
        
        /* 2.更新页面属性状态  */
        try{
            sv.changeStatusCmsPageAttrPubBatch(list,status);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量更新页面属性状态失败！",err);
            throw new BusinessException("CMS.PAGEATTRPUB.500107");
        }
    }
    
    /** 
     * TODO 查询页面属性  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    /*@Override
    public List<CmsAttrPubRespDTO> queryCmsDefaultAttrPubList() throws BusinessException {
        LogUtil.info(MODULE, "调用查询页面属性开始，入参：{}");
         1.验证前店入参 
        
         2.根据条件检索页面属性  
        List<CmsAttrPubRespDTO> list = new ArrayList<CmsAttrPubRespDTO>();
        try {
            //2.1缓存查询
            Map<Long ,CmsAttrPubRespDTO> map = (HashMap<Long ,CmsAttrPubRespDTO> )CacheUtil.getItem(CmsConstants.ParamConfig.CMS.PAGEATTRPUB.CACHE);
            if(CollectionUtils.isEmpty(map)){
                for(CmsAttrPubRespDTO respDTO:map.values()){
                    if(respDTO != null && CmsConstants.IsNot.CMS_ISNOT_1.equals(respDTO.getIsdefault())){
                        list.add(respDTO);
                    }
                }
            }else{//2.2 当缓存中没有时，取表
                CmsAttrPubReqDTO dto = new CmsAttrPubReqDTO();
                dto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                dto.setIsdefault(CmsConstants.IsNot.CMS_ISNOT_1);
                list = sv.queryCmsAttrPubList(dto);
            }
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询页面属性出现异常！",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS.PAGEATTRPUB.500103);
        }
        
        return list;
    }*/

}

