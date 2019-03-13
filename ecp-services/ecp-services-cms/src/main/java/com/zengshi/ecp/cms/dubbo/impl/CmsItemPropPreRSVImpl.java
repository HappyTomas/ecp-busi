package com.zengshi.ecp.cms.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPreReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPreRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsItemPropPreRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsItemPropPreSV;
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
public class CmsItemPropPreRSVImpl implements ICmsItemPropPreRSV{
    
    @Resource
    private ICmsItemPropPreSV sv;
    
    //定义个常量，用于表示模块名称，可以使用：当前类的类名
    private static final String MODULE = CmsItemPropPreRSVImpl.class.getName();


    /** 
     * TODO 新增布局项属性. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsItemPropPreRSV#saveCmsItemPropPre(com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPreReqDTO) 
     */
    @Override
    public CmsItemPropPreRespDTO addCmsItemPropPre(CmsItemPropPreReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用新增布局项属性开始，入参：{dto="+dto.toString()+"}");
        CmsItemPropPreRespDTO respDTO = new CmsItemPropPreRespDTO();
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
        
        /*2.调service层接口*/
        try{   
            respDTO = sv.addCmsItemPropPre(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "新增布局项属性失败:",err);
            throw new BusinessException("CMS.ITEMPROPPRE.500101");
        }
        
        return respDTO;
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsItemPropPreRSV#updateCmsItemPropPre(com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPreReqDTO) 
     */
    @Override
    public CmsItemPropPreRespDTO updateCmsItemPropPre(CmsItemPropPreReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用更新布局项属性开始，入参：{dto="+dto.toString()+"}");
        CmsItemPropPreRespDTO respDTO = new CmsItemPropPreRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{    
            respDTO = sv.updateCmsItemPropPre(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新布局项属性失败！",err);
            throw new BusinessException("CMS.ITEMPROPPRE.500102");
        }
        
        return respDTO;
    }
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsItemPropPreRSV#updateCmsItemPropPre(com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPreReqDTO) 
     */
    @Override 
    public void updateCmsItemPropPreList(List<CmsItemPropPreReqDTO> list) throws BusinessException {
    	LogUtil.info(MODULE, "调用更新布局项属性开始，入参：{list="+list.toArray()+"}");
    	try{    
            sv.updateCmsItemPropPreList(list);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新布局项属性失败！",err);
            throw new BusinessException("CMS.ITEMPROPPRE.500102");
        }
    }
    
    /** 
     * TODO 查询布局项属性，分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsItemPropPreRSV#queryCmsItemPropPrePage(com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPreReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsItemPropPreRespDTO> queryCmsItemPropPrePage(CmsItemPropPreReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用分页查询布局项属性开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索布局项属性  */
        PageResponseDTO<CmsItemPropPreRespDTO> pageInfo = PageResponseDTO.buildByBaseInfo(dto, CmsItemPropPreRespDTO.class);
        try{
            pageInfo =  sv.queryCmsItemPropPrePage(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "查询布局项属性失败！",err);
            throw new BusinessException("CMS.ITEMPROPPRE.500103");
        }
        return pageInfo;
    }
    
    /** 
     * TODO 查询布局项属性  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public List<CmsItemPropPreRespDTO> queryCmsItemPropPreList(CmsItemPropPreReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用查询布局项属性开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索布局项属性  */
        List<CmsItemPropPreRespDTO> list = new ArrayList<CmsItemPropPreRespDTO>();
        try {
            list = sv.queryCmsItemPropPreList(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询布局项属性出现异常！",e);
            throw new BusinessException("CMS.ITEMPROPPRE.500103");
        }
        
        return list;
    }
    
    /** 
     * TODO 查询布局项属性  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public List<CmsItemPropPreRespDTO> queryCmsItemPropValuePreList(CmsItemPropPreReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用查询布局项属性开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索布局项属性  */
        List<CmsItemPropPreRespDTO> list = new ArrayList<CmsItemPropPreRespDTO>();
        try {
            list = sv.queryCmsItemPropValuePreList(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询布局项属性出现异常！",e);
            throw new BusinessException("CMS.ITEMPROPPRE.500103");
        }
        
        return list;
    }
    
    /** 
     * TODO 查询单个布局项属性（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsItemPropPreRSV#queryCmsItemPropPre(com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPreReqDTO) 
     */
    @Override
    public CmsItemPropPreRespDTO queryCmsItemPropPre(CmsItemPropPreReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用查询布局项属性开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.根据条件检索布局项属性  */
        CmsItemPropPreRespDTO cmsItemPropPreRespDTO = new CmsItemPropPreRespDTO();
        try {
            cmsItemPropPreRespDTO = sv.queryCmsItemPropPre(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "根据ID查询布局项属性出现异常！",e);
            throw new BusinessException("CMS.ITEMPROPPRE.500103");
        }
        
        return cmsItemPropPreRespDTO;
    }
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsItemPropPreRSV#deleteCmsItemPropPre(java.lang.Long) 
     */
    @Override
    public void deleteCmsItemPropPre(String id) throws BusinessException {
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
            sv.deleteCmsItemPropPre(id);
        } catch(Exception err){
            LogUtil.error(MODULE, "删除布局项属性失败！",err);
            throw new BusinessException("CMS.ITEMPROPPRE.500101");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsItemPropPreRSV#deleteCmsItemPropPreBatch(java.util.List) 
     */
    @Override
    public void deleteCmsItemPropPreBatch(List<String> list) throws BusinessException {
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
            sv.deleteCmsItemPropPreBatch(list);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量删除布局项属性失败！",err);
            throw new BusinessException("CMS.ITEMPROPPRE.500104");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsItemPropPreRSV#changeStatusCmsItemPropPre(java.lang.Long, java.lang.String) 
     */
    @Override
    public void changeStatusCmsItemPropPre(String id,String status) throws BusinessException {
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
            sv.changeStatusCmsItemPropPre(id, status);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新布局项属性状态失败！",err);
            throw new BusinessException("CMS.ITEMPROPPRE.500106");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsItemPropPreRSV#changeStatusCmsItemPropPreBatch(java.util.List, java.lang.String) 
     */
    @Override
    public void changeStatusCmsItemPropPreBatch(List<String> list,String status) throws BusinessException {
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
            sv.changeStatusCmsItemPropPreBatch(list,status);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量更新布局项属性状态失败！",err);
            throw new BusinessException("CMS.ITEMPROPPRE.500107");
        }
    }

}

