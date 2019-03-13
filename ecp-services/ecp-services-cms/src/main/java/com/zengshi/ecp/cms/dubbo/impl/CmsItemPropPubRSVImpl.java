package com.zengshi.ecp.cms.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPreReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPreRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPubReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPubRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsItemPropPubRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsItemPropPubSV;
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
public class CmsItemPropPubRSVImpl implements ICmsItemPropPubRSV{
    
    @Resource
    private ICmsItemPropPubSV sv;
    
    //定义个常量，用于表示模块名称，可以使用：当前类的类名
    private static final String MODULE = CmsItemPropPubRSVImpl.class.getName();


    /** 
     * TODO 新增布局项属性. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsItemPropPubRSV#saveCmsItemPropPub(com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPubReqDTO) 
     */
    @Override
    public CmsItemPropPubRespDTO addCmsItemPropPub(CmsItemPropPubReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用新增布局项属性开始，入参：{dto="+dto.toString()+"}");
        CmsItemPropPubRespDTO respDTO = new CmsItemPropPubRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getItemId())){
            LogUtil.error(MODULE, "入参ItemId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="ItemId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getPropId())){
            LogUtil.error(MODULE, "入参PropId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="PropId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getItemPropId())){
            LogUtil.error(MODULE, "入参ItemPropId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="ItemPropId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{   
            respDTO = sv.addCmsItemPropPub(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "新增布局项属性失败:",err);
            throw new BusinessException("CMS.ITEMPROPPUB.500101");
        }
        
        return respDTO;
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsItemPropPubRSV#updateCmsItemPropPub(com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPubReqDTO) 
     */
    @Override
    public CmsItemPropPubRespDTO updateCmsItemPropPub(CmsItemPropPubReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用更新布局项属性开始，入参：{dto="+dto.toString()+"}");
        CmsItemPropPubRespDTO respDTO = new CmsItemPropPubRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{    
            respDTO = sv.updateCmsItemPropPub(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新布局项属性失败！",err);
            throw new BusinessException("CMS.ITEMPROPPUB.500102");
        }
        
        return respDTO;
    }
    
    /** 
     * TODO 查询布局项属性，分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsItemPropPubRSV#queryCmsItemPropPubPage(com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPubReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsItemPropPubRespDTO> queryCmsItemPropPubPage(CmsItemPropPubReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用分页查询布局项属性开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索布局项属性  */
        PageResponseDTO<CmsItemPropPubRespDTO> pageInfo = PageResponseDTO.buildByBaseInfo(dto, CmsItemPropPubRespDTO.class);
        try{
            pageInfo =  sv.queryCmsItemPropPubPage(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "查询布局项属性失败！",err);
            throw new BusinessException("CMS.ITEMPROPPUB.500103");
        }
        return pageInfo;
    }
    
    /** 
     * TODO 查询布局项属性  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public List<CmsItemPropPubRespDTO> queryCmsItemPropPubList(CmsItemPropPubReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用查询布局项属性开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索布局项属性  */
        List<CmsItemPropPubRespDTO> list = new ArrayList<CmsItemPropPubRespDTO>();
        try {
            list = sv.queryCmsItemPropPubList(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询布局项属性出现异常！",e);
            throw new BusinessException("CMS.ITEMPROPPUB.500103");
        }
        
        return list;
    }
    
    /** 
     * TODO 查询布局项属性  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public List<CmsItemPropPubRespDTO> queryCmsItemPropValuePubList(CmsItemPropPubReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用查询布局项属性开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索布局项属性  */
        List<CmsItemPropPubRespDTO> list = new ArrayList<CmsItemPropPubRespDTO>();
        try {
            list = sv.queryCmsItemPropValuePubList(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询布局项属性出现异常！",e);
            throw new BusinessException("CMS.ITEMPROPPUB.500103");
        }
        
        return list;
    }
    
    /** 
     * queryCmsItemPropValuePubListForWap:(这里用一句话描述这个方法的作用). <br/> 
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
    public List<CmsItemPropPubRespDTO> queryCmsItemPropValuePubListForWap(CmsItemPropPubReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用查询布局项属性开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索布局项属性  */
        List<CmsItemPropPubRespDTO> list = new ArrayList<CmsItemPropPubRespDTO>();
        try {
            list = sv.queryCmsItemPropValuePubListForWap(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询布局项属性出现异常！",e);
            throw new BusinessException("CMS.ITEMPROPPRE.500103");
        }
        
        return list;
    }
    
    /** 
     * TODO 查询单个布局项属性（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsItemPropPubRSV#queryCmsItemPropPub(com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPubReqDTO) 
     */
    @Override
    public CmsItemPropPubRespDTO queryCmsItemPropPub(CmsItemPropPubReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用查询布局项属性开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.根据条件检索布局项属性  */
        CmsItemPropPubRespDTO cmsItemPropPubRespDTO = new CmsItemPropPubRespDTO();
        try {
            cmsItemPropPubRespDTO = sv.queryCmsItemPropPub(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "根据ID查询布局项属性出现异常！",e);
            throw new BusinessException("CMS.ITEMPROPPUB.500103");
        }
        
        return cmsItemPropPubRespDTO;
    }
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsItemPropPubRSV#deleteCmsItemPropPub(java.lang.Long) 
     */
    @Override
    public void deleteCmsItemPropPub(String id) throws BusinessException {
        LogUtil.info(MODULE, "调用删除布局项属性开始，入参：{id="+id+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isBlank(id)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑删除布局项属性  */
        try{
            sv.deleteCmsItemPropPub(id);
        } catch(Exception err){
            LogUtil.error(MODULE, "删除布局项属性失败！",err);
            throw new BusinessException("CMS.ITEMPROPPUB.500101");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsItemPropPubRSV#deleteCmsItemPropPubBatch(java.util.List) 
     */
    @Override
    public void deleteCmsItemPropPubBatch(List<String> list) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除布局项属性开始，入参：{list="+list.toArray()+"}");
        /* 1.验证前店入参 */
        if(CollectionUtils.isEmpty(list)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="list";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑批量删除布局项属性  */
        try{
            sv.deleteCmsItemPropPubBatch(list);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量删除布局项属性失败！",err);
            throw new BusinessException("CMS.ITEMPROPPUB.500104");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsItemPropPubRSV#changeStatusCmsItemPropPub(java.lang.Long, java.lang.String) 
     */
    @Override
    public void changeStatusCmsItemPropPub(String id,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除布局项属性开始，入参：{id="+id+",status="+status+"}");
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
        /* 2.更新布局项属性状态  */
        try{
            sv.changeStatusCmsItemPropPub(id, status);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新布局项属性状态失败！",err);
            throw new BusinessException("CMS.ITEMPROPPUB.500106");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsItemPropPubRSV#changeStatusCmsItemPropPubBatch(java.util.List, java.lang.String) 
     */
    @Override
    public void changeStatusCmsItemPropPubBatch(List<String> list,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除布局项属性开始，入参：{list="+list.toArray()+",status="+status+"}");
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
        
        /* 2.更新布局项属性状态  */
        try{
            sv.changeStatusCmsItemPropPubBatch(list,status);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量更新布局项属性状态失败！",err);
            throw new BusinessException("CMS.ITEMPROPPUB.500107");
        }
    }
    
}

