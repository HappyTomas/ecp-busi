package com.zengshi.ecp.cms.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLayoutItemReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLayoutItemRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateLayoutItemRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateLayoutItemSV;
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
public class CmsTemplateLayoutItemRSVImpl implements ICmsTemplateLayoutItemRSV{
    
    @Resource
    private ICmsTemplateLayoutItemSV sv;
    
    //定义个常量，用于表示模块名称，可以使用：当前类的类名
    private static final String MODULE = CmsTemplateLayoutItemRSVImpl.class.getName();


    /** 
     * TODO 新增模板布局项. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateLayoutItemRSV#saveCmsTemplateLayoutItem(com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLayoutItemReqDTO) 
     */
    @Override
    public CmsTemplateLayoutItemRespDTO addCmsTemplateLayoutItem(CmsTemplateLayoutItemReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用新增模板布局项开始，入参：{dto="+dto.toString()+"}");
        CmsTemplateLayoutItemRespDTO respDTO = new CmsTemplateLayoutItemRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getLayoutId())){
            LogUtil.error(MODULE, "入参LayoutId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="LayoutId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getTemplateId())){
            LogUtil.error(MODULE, "入参TemplateId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="TemplateId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getModularId())){
            LogUtil.error(MODULE, "入参ModularId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="ModularId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{   
            respDTO = sv.addCmsTemplateLayoutItem(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "新增模板布局项失败:",err);
            throw new BusinessException("CMS.TEMPLATELIB.500101");
        }
        
        return respDTO;
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateLayoutItemRSV#updateCmsTemplateLayoutItem(com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLayoutItemReqDTO) 
     */
    @Override
    public CmsTemplateLayoutItemRespDTO updateCmsTemplateLayoutItem(CmsTemplateLayoutItemReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用更新模板布局项开始，入参：{dto="+dto.toString()+"}");
        CmsTemplateLayoutItemRespDTO respDTO = new CmsTemplateLayoutItemRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{    
            respDTO = sv.updateCmsTemplateLayoutItem(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新模板布局项失败！",err);
            throw new BusinessException("CMS.TEMPLATELIB.500102");
        }
        
        return respDTO;
    }
    /**
     * 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateLayoutItemRSV#updateCmsTemplateLayoutItemSensitive(com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLayoutItemReqDTO)
     */
    @Override
    public CmsTemplateLayoutItemRespDTO updateCmsTemplateLayoutItemSensitive(
            CmsTemplateLayoutItemReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用更新模板布局项开始（支持置空），入参：{dto="+dto.toString()+"}");
        CmsTemplateLayoutItemRespDTO respDTO = new CmsTemplateLayoutItemRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        /*2.调service层接口*/
        try{    
            respDTO = sv.updateTemplateLayoutItemSensitive(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新页面布局项（支持置空）失败！",err);
            throw new BusinessException("CMS.LAYOUTITEMPRE.500102");
        }
        
        return respDTO;
    }
    /**
     * 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateLayoutItemRSV#updateCmsTemplateLayoutItemBatch(java.util.List)
     */
    @Override
    public boolean updateCmsTemplateLayoutItemBatch(List<CmsTemplateLayoutItemReqDTO> list)
            throws BusinessException {
        LogUtil.info(MODULE, "调用删除更新布局开始，入参：");
        boolean flag = true;
        /* 1.验证前店入参 */
        if(CollectionUtils.isEmpty(list)){
            flag = false;
            LogUtil.error(MODULE, "入参list为空！");
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,new String[] {"list"}); 
        }
        
        /* 2.根据条件检索基础模块  */
        try {
            sv.updateTemplateLayoutItemBatch(list);
        } catch (Exception e) {
            flag = false;
            LogUtil.error(MODULE, "删除布局出现异常！",e);
            throw new BusinessException("CMS.PAGECONFIG.500108");
        }
        
        return flag;
    }
    /** 
     * TODO 查询模板布局项，分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateLayoutItemRSV#queryCmsTemplateLayoutItemPage(com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLayoutItemReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsTemplateLayoutItemRespDTO> queryCmsTemplateLayoutItemPage(CmsTemplateLayoutItemReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用分页查询模板布局项开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索模板布局项  */
        PageResponseDTO<CmsTemplateLayoutItemRespDTO> pageInfo = PageResponseDTO.buildByBaseInfo(dto, CmsTemplateLayoutItemRespDTO.class);
        try{
            pageInfo =  sv.queryCmsTemplateLayoutItemPage(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "查询模板布局项失败！",err);
            throw new BusinessException("CMS.TEMPLATELIB.500103");
        }
        return pageInfo;
    }
    
    /** 
     * TODO 查询模板布局项  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public List<CmsTemplateLayoutItemRespDTO> queryCmsTemplateLayoutItemList(CmsTemplateLayoutItemReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用查询模板布局项开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索模板布局项  */
        List<CmsTemplateLayoutItemRespDTO> list = new ArrayList<CmsTemplateLayoutItemRespDTO>();
        try {
            list = sv.queryCmsTemplateLayoutItemList(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询模板布局项出现异常！",e);
            throw new BusinessException("CMS.TEMPLATELIB.500103");
        }
        
        return list;
    }
    
    /** 
     * TODO 查询单个模板布局项（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateLayoutItemRSV#queryCmsTemplateLayoutItem(com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLayoutItemReqDTO) 
     */
    @Override
    public CmsTemplateLayoutItemRespDTO queryCmsTemplateLayoutItem(CmsTemplateLayoutItemReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用查询模板布局项开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.根据条件检索模板布局项  */
        CmsTemplateLayoutItemRespDTO cmsTemplateLayoutItemRespDTO = new CmsTemplateLayoutItemRespDTO();
        try {
            cmsTemplateLayoutItemRespDTO = sv.queryCmsTemplateLayoutItem(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "根据ID查询模板布局项出现异常！",e);
            throw new BusinessException("CMS.TEMPLATELIB.500103");
        }
        
        return cmsTemplateLayoutItemRespDTO;
    }
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateLayoutItemRSV#deleteCmsTemplateLayoutItem(java.lang.Long) 
     */
    @Override
    public void deleteCmsTemplateLayoutItem(String id) throws BusinessException {
        LogUtil.info(MODULE, "调用删除模板布局项开始，入参：{id="+id+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isBlank(id)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑删除模板布局项  */
        try{
            sv.deleteCmsTemplateLayoutItem(id);
        } catch(Exception err){
            LogUtil.error(MODULE, "删除模板布局项失败！",err);
            throw new BusinessException("CMS.TEMPLATELIB.500101");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateLayoutItemRSV#deleteCmsTemplateLayoutItemBatch(java.util.List) 
     */
    @Override
    public void deleteCmsTemplateLayoutItemBatch(List<String> list) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除模板布局项开始，入参：{list="+list.toArray()+"}");
        /* 1.验证前店入参 */
        if(CollectionUtils.isEmpty(list)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="list";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑批量删除模板布局项  */
        try{
            sv.deleteCmsTemplateLayoutItemBatch(list);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量删除模板布局项失败！",err);
            throw new BusinessException("CMS.TEMPLATELIB.500104");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateLayoutItemRSV#changeStatusCmsTemplateLayoutItem(java.lang.Long, java.lang.String) 
     */
    @Override
    public void changeStatusCmsTemplateLayoutItem(String id,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除模板布局项开始，入参：{id="+id+",status="+status+"}");
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
        /* 2.更新模板布局项状态  */
        try{
            sv.changeStatusCmsTemplateLayoutItem(id, status);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新模板布局项状态失败！",err);
            throw new BusinessException("CMS.TEMPLATELIB.500106");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateLayoutItemRSV#changeStatusCmsTemplateLayoutItemBatch(java.util.List, java.lang.String) 
     */
    @Override
    public void changeStatusCmsTemplateLayoutItemBatch(List<String> list,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除模板布局项开始，入参：{list="+list.toArray()+",status="+status+"}");
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
        
        /* 2.更新模板布局项状态  */
        try{
            sv.changeStatusCmsTemplateLayoutItemBatch(list,status);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量更新模板布局项状态失败！",err);
            throw new BusinessException("CMS.TEMPLATELIB.500107");
        }
    }

    /** 
     * TODO 查询模板布局项  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    /*@Override
    public List<CmsTemplateLayoutItemRespDTO> queryCmsDefaultTemplateLayoutItemList() throws BusinessException {
        LogUtil.info(MODULE, "调用查询模板布局项开始，入参：{}");
         1.验证前店入参 
        
         2.根据条件检索模板布局项  
        List<CmsTemplateLayoutItemRespDTO> list = new ArrayList<CmsTemplateLayoutItemRespDTO>();
        try {
            //2.1缓存查询
            Map<Long ,CmsTemplateLayoutItemRespDTO> map = (HashMap<Long ,CmsTemplateLayoutItemRespDTO> )CacheUtil.getItem(CmsConstants.ParamConfig.CMS.TEMPLATELIB.CACHE);
            if(CollectionUtils.isEmpty(map)){
                for(CmsTemplateLayoutItemRespDTO respDTO:map.values()){
                    if(respDTO != null && CmsConstants.IsNot.CMS_ISNOT_1.equals(respDTO.getIsdefault())){
                        list.add(respDTO);
                    }
                }
            }else{//2.2 当缓存中没有时，取表
                CmsTemplateLayoutItemReqDTO dto = new CmsTemplateLayoutItemReqDTO();
                dto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                dto.setIsdefault(CmsConstants.IsNot.CMS_ISNOT_1);
                list = sv.queryCmsTemplateLayoutItemList(dto);
            }
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询模板布局项出现异常！",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS.TEMPLATELIB.500103);
        }
        
        return list;
    }*/

}

