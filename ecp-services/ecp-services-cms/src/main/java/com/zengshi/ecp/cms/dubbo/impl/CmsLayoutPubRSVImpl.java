package com.zengshi.ecp.cms.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutPubReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutPubRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutPubRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutPubSV;
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
public class CmsLayoutPubRSVImpl implements ICmsLayoutPubRSV{
    
    @Resource
    private ICmsLayoutPubSV sv;
    
    //定义个常量，用于表示模块名称，可以使用：当前类的类名
    private static final String MODULE = CmsLayoutPubRSVImpl.class.getName();


    /** 
     * TODO 新增布局项. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutPubRSV#saveCmsLayoutPub(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutPubReqDTO) 
     */
    @Override
    public CmsLayoutPubRespDTO addCmsLayoutPub(CmsLayoutPubReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用新增布局项开始，入参：{dto="+dto.toString()+"}");
        CmsLayoutPubRespDTO respDTO = new CmsLayoutPubRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getPageId())){
            LogUtil.error(MODULE, "入参PageId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="PageId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getLayoutId())){
            LogUtil.error(MODULE, "入参LayoutId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="LayoutId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{   
            respDTO = sv.addCmsLayoutPub(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "新增布局项失败:",err);
            throw new BusinessException("CMS.LAYOUTPUB.500101");
        }
        
        return respDTO;
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutPubRSV#updateCmsLayoutPub(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutPubReqDTO) 
     */
    @Override
    public CmsLayoutPubRespDTO updateCmsLayoutPub(CmsLayoutPubReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用更新布局项开始，入参：{dto="+dto.toString()+"}");
        CmsLayoutPubRespDTO respDTO = new CmsLayoutPubRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{    
            respDTO = sv.updateCmsLayoutPub(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新布局项失败！",err);
            throw new BusinessException("CMS.LAYOUTPUB.500102");
        }
        
        return respDTO;
    }
    
    /** 
     * TODO 查询布局项，分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutPubRSV#queryCmsLayoutPubPage(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutPubReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsLayoutPubRespDTO> queryCmsLayoutPubPage(CmsLayoutPubReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用分页查询布局项开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索布局项  */
        PageResponseDTO<CmsLayoutPubRespDTO> pageInfo = PageResponseDTO.buildByBaseInfo(dto, CmsLayoutPubRespDTO.class);
        try{
            pageInfo =  sv.queryCmsLayoutPubPage(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "查询布局项失败！",err);
            throw new BusinessException("CMS.LAYOUTPUB.500103");
        }
        return pageInfo;
    }
    
    /** 
     * TODO 查询布局项  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public List<CmsLayoutPubRespDTO> queryCmsLayoutPubList(CmsLayoutPubReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用查询布局项开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索布局项  */
        List<CmsLayoutPubRespDTO> list = new ArrayList<CmsLayoutPubRespDTO>();
        try {
            list = sv.queryCmsLayoutPubList(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询布局项出现异常！",e);
            throw new BusinessException("CMS.LAYOUTPUB.500103");
        }
        
        return list;
    }
    
    /** 
     * TODO 查询单个布局项（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutPubRSV#queryCmsLayoutPub(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutPubReqDTO) 
     */
    @Override
    public CmsLayoutPubRespDTO queryCmsLayoutPub(CmsLayoutPubReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用查询布局项开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.根据条件检索布局项  */
        CmsLayoutPubRespDTO cmsLayoutPubRespDTO = new CmsLayoutPubRespDTO();
        try {
            cmsLayoutPubRespDTO = sv.queryCmsLayoutPub(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "根据ID查询布局项出现异常！",e);
            throw new BusinessException("CMS.LAYOUTPUB.500103");
        }
        
        return cmsLayoutPubRespDTO;
    }
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutPubRSV#deleteCmsLayoutPub(java.lang.Long) 
     */
    @Override
    public void deleteCmsLayoutPub(String id) throws BusinessException {
        LogUtil.info(MODULE, "调用删除布局项开始，入参：{id="+id+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isBlank(id)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑删除布局项  */
        try{
            sv.deleteCmsLayoutPub(id);
        } catch(Exception err){
            LogUtil.error(MODULE, "删除布局项失败！",err);
            throw new BusinessException("CMS.LAYOUTPUB.500101");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutPubRSV#deleteCmsLayoutPubBatch(java.util.List) 
     */
    @Override
    public void deleteCmsLayoutPubBatch(List<String> list) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除布局项开始，入参：{list="+list.toArray()+"}");
        /* 1.验证前店入参 */
        if(CollectionUtils.isEmpty(list)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="list";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑批量删除布局项  */
        try{
            sv.deleteCmsLayoutPubBatch(list);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量删除布局项失败！",err);
            throw new BusinessException("CMS.LAYOUTPUB.500104");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutPubRSV#changeStatusCmsLayoutPub(java.lang.Long, java.lang.String) 
     */
    @Override
    public void changeStatusCmsLayoutPub(String id,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除布局项开始，入参：{id="+id+",status="+status+"}");
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
        /* 2.更新布局项状态  */
        try{
            sv.changeStatusCmsLayoutPub(id, status);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新布局项状态失败！",err);
            throw new BusinessException("CMS.LAYOUTPUB.500106");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutPubRSV#changeStatusCmsLayoutPubBatch(java.util.List, java.lang.String) 
     */
    @Override
    public void changeStatusCmsLayoutPubBatch(List<String> list,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除布局项开始，入参：{list="+list.toArray()+",status="+status+"}");
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
        
        /* 2.更新布局项状态  */
        try{
            sv.changeStatusCmsLayoutPubBatch(list,status);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量更新布局项状态失败！",err);
            throw new BusinessException("CMS.LAYOUTPUB.500107");
        }
    }
    
    /** 
     * TODO 查询布局项  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    /*@Override
    public List<CmsLayoutPubRespDTO> queryCmsDefaultLayoutPubList() throws BusinessException {
        LogUtil.info(MODULE, "调用查询布局项开始，入参：{}");
         1.验证前店入参 
        
         2.根据条件检索布局项  
        List<CmsLayoutPubRespDTO> list = new ArrayList<CmsLayoutPubRespDTO>();
        try {
            //2.1缓存查询
            Map<Long ,CmsLayoutPubRespDTO> map = (HashMap<Long ,CmsLayoutPubRespDTO> )CacheUtil.getItem(CmsConstants.ParamConfig.CMS.LAYOUTPUB.CACHE);
            if(CollectionUtils.isEmpty(map)){
                for(CmsLayoutPubRespDTO respDTO:map.values()){
                    if(respDTO != null && CmsConstants.IsNot.CMS_ISNOT_1.equals(respDTO.getIsdefault())){
                        list.add(respDTO);
                    }
                }
            }else{//2.2 当缓存中没有时，取表
                CmsLayoutPubReqDTO dto = new CmsLayoutPubReqDTO();
                dto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                dto.setIsdefault(CmsConstants.IsNot.CMS_ISNOT_1);
                list = sv.queryCmsLayoutPubList(dto);
            }
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询布局项出现异常！",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS.LAYOUTPUB.500103);
        }
        
        return list;
    }*/

}

