package com.zengshi.ecp.cms.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.cms.dubbo.dto.CmsModularPropValReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularPropValRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularPropValRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsModularPropValSV;
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
public class CmsModularPropValRSVImpl implements ICmsModularPropValRSV{
    
    @Resource
    private ICmsModularPropValSV sv;
    
    //定义个常量，用于表示模块名称，可以使用：当前类的类名
    private static final String MODULE = CmsModularPropValRSVImpl.class.getName();


    /** 
     * TODO 新增模块属性值. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularPropValRSV#saveCmsModularPropVal(com.zengshi.ecp.cms.dubbo.dto.CmsModularPropValReqDTO) 
     */
    @Override
    public CmsModularPropValRespDTO addCmsModularPropVal(CmsModularPropValReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用新增模块属性值开始，入参：{dto="+dto.toString()+"}");
        CmsModularPropValRespDTO respDTO = new CmsModularPropValRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getPropId())){
            LogUtil.error(MODULE, "入参PropId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="PropId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getPropValue())){
            LogUtil.error(MODULE, "入参PropValue为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="PropValue";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{   
            respDTO = sv.addCmsModularPropVal(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "新增模块属性值失败:",err);
            throw new BusinessException("CMS.MODULARPROPVAL.500101");
        }
        
        return respDTO;
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularPropValRSV#updateCmsModularPropVal(com.zengshi.ecp.cms.dubbo.dto.CmsModularPropValReqDTO) 
     */
    @Override
    public CmsModularPropValRespDTO updateCmsModularPropVal(CmsModularPropValReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用更新模块属性值开始，入参：{dto="+dto.toString()+"}");
        CmsModularPropValRespDTO respDTO = new CmsModularPropValRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{    
            respDTO = sv.updateCmsModularPropVal(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新模块属性值失败！",err);
            throw new BusinessException("CMS.MODULARPROPVAL.500102");
        }
        
        return respDTO;
    }
    
    /** 
     * TODO 查询模块属性值，分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularPropValRSV#queryCmsModularPropValPage(com.zengshi.ecp.cms.dubbo.dto.CmsModularPropValReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsModularPropValRespDTO> queryCmsModularPropValPage(CmsModularPropValReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用分页查询模块属性值开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索模块属性值  */
        PageResponseDTO<CmsModularPropValRespDTO> pageInfo = PageResponseDTO.buildByBaseInfo(dto, CmsModularPropValRespDTO.class);
        try{
            pageInfo =  sv.queryCmsModularPropValPage(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "查询模块属性值失败！",err);
            throw new BusinessException("CMS.MODULARPROPVAL.500103");
        }
        return pageInfo;
    }
    
    /** 
     * TODO 查询模块属性值  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public List<CmsModularPropValRespDTO> queryCmsModularPropValList(CmsModularPropValReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用查询模块属性值开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索模块属性值  */
        List<CmsModularPropValRespDTO> list = new ArrayList<CmsModularPropValRespDTO>();
        try {
            list = sv.queryCmsModularPropValList(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询模块属性值出现异常！",e);
            throw new BusinessException("CMS.MODULARPROPVAL.500103");
        }
        
        return list;
    }
    
    /** 
     * TODO 查询单个模块属性值（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularPropValRSV#queryCmsModularPropVal(com.zengshi.ecp.cms.dubbo.dto.CmsModularPropValReqDTO) 
     */
    @Override
    public CmsModularPropValRespDTO queryCmsModularPropVal(CmsModularPropValReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用查询模块属性值开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.根据条件检索模块属性值  */
        CmsModularPropValRespDTO cmsModularPropValRespDTO = new CmsModularPropValRespDTO();
        try {
            cmsModularPropValRespDTO = sv.queryCmsModularPropVal(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "根据ID查询模块属性值出现异常！",e);
            throw new BusinessException("CMS.MODULARPROPVAL.500103");
        }
        
        return cmsModularPropValRespDTO;
    }
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularPropValRSV#deleteCmsModularPropVal(java.lang.Long) 
     */
    @Override
    public void deleteCmsModularPropVal(String id) throws BusinessException {
        LogUtil.info(MODULE, "调用删除模块属性值开始，入参：{id="+id+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isBlank(id)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑删除模块属性值  */
        try{
            sv.deleteCmsModularPropVal(id);
        } catch(Exception err){
            LogUtil.error(MODULE, "删除模块属性值失败！",err);
            throw new BusinessException("CMS.MODULARPROPVAL.500101");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularPropValRSV#deleteCmsModularPropValBatch(java.util.List) 
     */
    @Override
    public void deleteCmsModularPropValBatch(List<String> list) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除模块属性值开始，入参：{list="+list.toArray()+"}");
        /* 1.验证前店入参 */
        if(CollectionUtils.isEmpty(list)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="list";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑批量删除模块属性值  */
        try{
            sv.deleteCmsModularPropValBatch(list);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量删除模块属性值失败！",err);
            throw new BusinessException("CMS.MODULARPROPVAL.500104");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularPropValRSV#changeStatusCmsModularPropVal(java.lang.Long, java.lang.String) 
     */
    @Override
    public void changeStatusCmsModularPropVal(String id,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除模块属性值开始，入参：{id="+id+",status="+status+"}");
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
        /* 2.更新模块属性值状态  */
        try{
            sv.changeStatusCmsModularPropVal(id, status);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新模块属性值状态失败！",err);
            throw new BusinessException("CMS.MODULARPROPVAL.500106");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularPropValRSV#changeStatusCmsModularPropValBatch(java.util.List, java.lang.String) 
     */
    @Override
    public void changeStatusCmsModularPropValBatch(List<String> list,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除模块属性值开始，入参：{list="+list.toArray()+",status="+status+"}");
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
        
        /* 2.更新模块属性值状态  */
        try{
            sv.changeStatusCmsModularPropValBatch(list,status);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量更新模块属性值状态失败！",err);
            throw new BusinessException("CMS.MODULARPROPVAL.500107");
        }
    }
    
}

