package com.zengshi.ecp.cms.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLibReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLibRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateLibRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateLibSV;
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
public class CmsTemplateLibRSVImpl implements ICmsTemplateLibRSV{
    
    @Resource
    private ICmsTemplateLibSV sv;
    
    //定义个常量，用于表示模块名称，可以使用：当前类的类名
    private static final String MODULE = CmsTemplateLibRSVImpl.class.getName();

    /** 
     * TODO 是否存在模块.
     * 存在：true
     * 不存在：false 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public boolean isExistTemplateLib(CmsTemplateLibReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用查询模板开始，入参：{dto="+dto.toString()+"}");
        /*1.验证前店入参*/
        if(StringUtil.isBlank(dto.getTemplateName())){
            LogUtil.error(MODULE, "入参TemplateName为空！");
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
        
        /* 2.根据条件检索模板  */
        return sv.isExistTemplateLib(dto);
    }

    /** 
     * TODO 新增模板. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateLibRSV#saveCmsTemplateLib(com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLibReqDTO) 
     */
    @Override
    public CmsTemplateLibRespDTO addCmsTemplateLib(CmsTemplateLibReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用新增模板开始，入参：{dto="+dto.toString()+"}");
        CmsTemplateLibRespDTO respDTO = new CmsTemplateLibRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getTemplateName())){
            LogUtil.error(MODULE, "入参TemplateName为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="TemplateName";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getTemplateType())){
            LogUtil.error(MODULE, "入参TemplateType为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="TemplateType";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getPageTypeId())){
            LogUtil.error(MODULE, "入参PageTypeId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="PageTypeId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{   
            respDTO = sv.addCmsTemplateLib(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "新增模板失败:",err);
            throw new BusinessException("CMS.TEMPLATELIB.500101");
        }
        
        return respDTO;
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateLibRSV#updateCmsTemplateLib(com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLibReqDTO) 
     */
    @Override
    public CmsTemplateLibRespDTO updateCmsTemplateLib(CmsTemplateLibReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用更新模板开始，入参：{dto="+dto.toString()+"}");
        CmsTemplateLibRespDTO respDTO = new CmsTemplateLibRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{    
            respDTO = sv.updateCmsTemplateLib(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新模板失败！",err);
            throw new BusinessException("CMS.TEMPLATELIB.500102");
        }
        
        return respDTO;
    }
    
    /** 
     * TODO 查询模板，分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateLibRSV#queryCmsTemplateLibPage(com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLibReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsTemplateLibRespDTO> queryCmsTemplateLibPage(CmsTemplateLibReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用分页查询模板开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索模板  */
        PageResponseDTO<CmsTemplateLibRespDTO> pageInfo = PageResponseDTO.buildByBaseInfo(dto, CmsTemplateLibRespDTO.class);
        try{
            pageInfo =  sv.queryCmsTemplateLibPage(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "查询模板失败！",err);
            throw new BusinessException("CMS.TEMPLATELIB.500103");
        }
        return pageInfo;
    }
    
    /** 
     * TODO 查询模板  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public List<CmsTemplateLibRespDTO> queryCmsTemplateLibList(CmsTemplateLibReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用查询模板开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索模板  */
        List<CmsTemplateLibRespDTO> list = new ArrayList<CmsTemplateLibRespDTO>();
        try {
            list = sv.queryCmsTemplateLibList(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询模板出现异常！",e);
            throw new BusinessException("CMS.TEMPLATELIB.500103");
        }
        
        return list;
    }
    /**
     * 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateLibRSV#queryExactCmsTemplateLibList(com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLibReqDTO)
     */
    @Override
    public List<CmsTemplateLibRespDTO> queryExactCmsTemplateLibList(CmsTemplateLibReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用精确查询模板开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索模板  */
        List<CmsTemplateLibRespDTO> list = new ArrayList<CmsTemplateLibRespDTO>();
        try {
            list = sv.queryExactCmsTemplateLibList(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "精确查询模板出现异常！",e);
            throw new BusinessException("CMS.TEMPLATELIB.500103");
        }
        
        return list;
    }
    /** 
     * TODO 查询单个模板（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateLibRSV#queryCmsTemplateLib(com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLibReqDTO) 
     */
    @Override
    public CmsTemplateLibRespDTO queryCmsTemplateLib(CmsTemplateLibReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用查询模板开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.根据条件检索模板  */
        CmsTemplateLibRespDTO cmsTemplateLibRespDTO = new CmsTemplateLibRespDTO();
        try {
            cmsTemplateLibRespDTO = sv.queryCmsTemplateLib(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "根据ID查询模板出现异常！",e);
            throw new BusinessException("CMS.TEMPLATELIB.500103");
        }
        
        return cmsTemplateLibRespDTO;
    }
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateLibRSV#deleteCmsTemplateLib(java.lang.Long) 
     */
    @Override
    public void deleteCmsTemplateLib(String id) throws BusinessException {
        LogUtil.info(MODULE, "调用删除模板开始，入参：{id="+id+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isBlank(id)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑删除模板  */
        try{
            sv.deleteCmsTemplateLib(id);
        } catch(Exception err){
            LogUtil.error(MODULE, "删除模板失败！",err);
            throw new BusinessException("CMS.TEMPLATELIB.500101");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateLibRSV#deleteCmsTemplateLibBatch(java.util.List) 
     */
    @Override
    public void deleteCmsTemplateLibBatch(List<String> list) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除模板开始，入参：{list="+list.toArray()+"}");
        /* 1.验证前店入参 */
        if(CollectionUtils.isEmpty(list)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="list";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑批量删除模板  */
        try{
            sv.deleteCmsTemplateLibBatch(list);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量删除模板失败！",err);
            throw new BusinessException("CMS.TEMPLATELIB.500104");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateLibRSV#changeStatusCmsTemplateLib(java.lang.Long, java.lang.String) 
     */
    @Override
    public void changeStatusCmsTemplateLib(String id,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除模板开始，入参：{id="+id+",status="+status+"}");
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
        /* 2.更新模板状态  */
        try{
            sv.changeStatusCmsTemplateLib(id, status);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新模板状态失败！",err);
            throw new BusinessException("CMS.TEMPLATELIB.500106");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateLibRSV#changeStatusCmsTemplateLibBatch(java.util.List, java.lang.String) 
     */
    @Override
    public void changeStatusCmsTemplateLibBatch(List<String> list,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除模板开始，入参：{list="+list.toArray()+",status="+status+"}");
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
        
        /* 2.更新模板状态  */
        try{
            sv.changeStatusCmsTemplateLibBatch(list,status);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量更新模板状态失败！",err);
            throw new BusinessException("CMS.TEMPLATELIB.500107");
        }
    }

    /** 
     * updateTemplateLibStatusByExample:(根据条件更新模板及附件表状态). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param conditionDTO  查询条件DTO
     * @param resultDTO 结果DTO
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    @Override
    public void updateTemplateLibStatusByExample(CmsTemplateLibReqDTO conditionDTO,CmsTemplateLibReqDTO resultDTO) throws BusinessException {
        LogUtil.info(MODULE, "调用根据条件更新模板及附件表状态服务开始，入参：{conditionDTO="+conditionDTO.toString()+",resultDTO="+resultDTO.toString()+"}");
        /* 1.验证前店入参 */
        if(conditionDTO==null || StringUtil.isEmpty(conditionDTO.getId()) || StringUtil.isEmpty(conditionDTO.getStatus())){
            LogUtil.error(MODULE, "conditionDTO.ID或者conditionDTO.Status为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="conditionDTO.ID||conditionDTO.Status";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(resultDTO==null || StringUtil.isEmpty(resultDTO.getId()) || StringUtil.isEmpty(resultDTO.getStatus())){
            LogUtil.error(MODULE, "resultDTO.ID或者resultDTO.Status为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="resultDTO.ID||resultDTO.Status";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.更新模板状态  */
        try{
            sv.updateTemplateLibStatusByExample(conditionDTO, resultDTO);
        } catch(Exception err){
            LogUtil.error(MODULE, "根据条件更新模板及附件表状态失败！",err);
            throw new BusinessException("CMS.TEMPLATELIB.500107");
        }
    }

}

