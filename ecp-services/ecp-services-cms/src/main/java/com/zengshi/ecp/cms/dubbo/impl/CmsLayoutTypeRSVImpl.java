package com.zengshi.ecp.cms.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutTypeReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutTypeRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutTypeRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutTypeSV;
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
public class CmsLayoutTypeRSVImpl implements ICmsLayoutTypeRSV{
    
    @Resource
    private ICmsLayoutTypeSV sv;
    
    //定义个常量，用于表示模块名称，可以使用：当前类的类名
    private static final String MODULE = CmsLayoutTypeRSVImpl.class.getName();

    /** 
     * TODO 是否存在页面类型.
     * 存在：true
     * 不存在：false 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public boolean isExistLayoutType(CmsLayoutTypeReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用查询布局类型开始，入参：{dto="+dto.toString()+"}");
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getPageTypeId())){
            LogUtil.error(MODULE, "入参PageTypeId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="PageTypeId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getLayoutTypeName())){
            LogUtil.error(MODULE, "入参LayoutTypeName为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="LayoutTypeName";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getStatusSet())){
            LogUtil.error(MODULE, "入参StatusSet为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="StatusSet";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.根据条件检索页面类型  */
        return sv.isExistLayoutType(dto);
    }
    
    
    /** 
     * TODO 新增布局项类型. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutTypeRSV#saveCmsLayoutType(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutTypeReqDTO) 
     */
    @Override
    public CmsLayoutTypeRespDTO addCmsLayoutType(CmsLayoutTypeReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用新增布局项类型开始，入参：{dto="+dto.toString()+"}");
        CmsLayoutTypeRespDTO respDTO = new CmsLayoutTypeRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getLayoutItemSize())){
            LogUtil.error(MODULE, "入参LayoutItemSize为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="LayoutItemSize";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getLayoutTypeName())){
            LogUtil.error(MODULE, "入参LayoutTypeName为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="LayoutTypeName";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{   
            respDTO = sv.addCmsLayoutType(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "新增布局项类型失败:",err);
            throw new BusinessException("CMS.LAYOUTTYPE.500101");
        }
        
        return respDTO;
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutTypeRSV#updateCmsLayoutType(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutTypeReqDTO) 
     */
    @Override
    public CmsLayoutTypeRespDTO updateCmsLayoutType(CmsLayoutTypeReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用更新布局项类型开始，入参：{dto="+dto.toString()+"}");
        CmsLayoutTypeRespDTO respDTO = new CmsLayoutTypeRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{    
            respDTO = sv.updateCmsLayoutType(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新布局项类型失败！",err);
            throw new BusinessException("CMS.LAYOUTTYPE.500102");
        }
        
        return respDTO;
    }
    
    /** 
     * TODO 查询布局项类型，分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutTypeRSV#queryCmsLayoutTypePage(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutTypeReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsLayoutTypeRespDTO> queryCmsLayoutTypePage(CmsLayoutTypeReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用分页查询布局项类型开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索布局项类型  */
        PageResponseDTO<CmsLayoutTypeRespDTO> pageInfo = PageResponseDTO.buildByBaseInfo(dto, CmsLayoutTypeRespDTO.class);
        try{
            pageInfo =  sv.queryCmsLayoutTypePage(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "查询布局项类型失败！",err);
            throw new BusinessException("CMS.LAYOUTTYPE.500103");
        }
        return pageInfo;
    }
    
    /** 
     * TODO 查询布局项类型  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public List<CmsLayoutTypeRespDTO> queryCmsLayoutTypeList(CmsLayoutTypeReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用查询布局项类型开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索布局项类型  */
        List<CmsLayoutTypeRespDTO> list = new ArrayList<CmsLayoutTypeRespDTO>();
        try {
            list = sv.queryCmsLayoutTypeList(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询布局项类型出现异常！",e);
            throw new BusinessException("CMS.LAYOUTTYPE.500103");
        }
        
        return list;
    }
    
    /** 
     * TODO 查询单个布局项类型（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutTypeRSV#queryCmsLayoutType(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutTypeReqDTO) 
     */
    @Override
    public CmsLayoutTypeRespDTO queryCmsLayoutType(CmsLayoutTypeReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用查询布局项类型开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.根据条件检索布局项类型  */
        CmsLayoutTypeRespDTO cmsLayoutTypeRespDTO = new CmsLayoutTypeRespDTO();
        try {
            cmsLayoutTypeRespDTO = sv.queryCmsLayoutType(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "根据ID查询布局项类型出现异常！",e);
            throw new BusinessException("CMS.LAYOUTTYPE.500103");
        }
        
        return cmsLayoutTypeRespDTO;
    }
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutTypeRSV#deleteCmsLayoutType(java.lang.Long) 
     */
    @Override
    public void deleteCmsLayoutType(String id) throws BusinessException {
        LogUtil.info(MODULE, "调用删除布局项类型开始，入参：{id="+id+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isBlank(id)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑删除布局项类型  */
        try{
            sv.deleteCmsLayoutType(id);
        } catch(Exception err){
            LogUtil.error(MODULE, "删除布局项类型失败！",err);
            throw new BusinessException("CMS.LAYOUTTYPE.500101");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutTypeRSV#deleteCmsLayoutTypeBatch(java.util.List) 
     */
    @Override
    public void deleteCmsLayoutTypeBatch(List<String> list) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除布局项类型开始，入参：{list="+list.toArray()+"}");
        /* 1.验证前店入参 */
        if(CollectionUtils.isEmpty(list)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="list";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑批量删除布局项类型  */
        try{
            sv.deleteCmsLayoutTypeBatch(list);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量删除布局项类型失败！",err);
            throw new BusinessException("CMS.LAYOUTTYPE.500104");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutTypeRSV#changeStatusCmsLayoutType(java.lang.Long, java.lang.String) 
     */
    @Override
    public void changeStatusCmsLayoutType(String id,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除布局项类型开始，入参：{id="+id+",status="+status+"}");
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
        /* 2.更新布局项类型状态  */
        try{
            sv.changeStatusCmsLayoutType(id, status);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新布局项类型状态失败！",err);
            throw new BusinessException("CMS.LAYOUTTYPE.500106");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutTypeRSV#changeStatusCmsLayoutTypeBatch(java.util.List, java.lang.String) 
     */
    @Override
    public void changeStatusCmsLayoutTypeBatch(List<String> list,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除布局项类型开始，入参：{list="+list.toArray()+",status="+status+"}");
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
        
        /* 2.更新布局项类型状态  */
        try{
            sv.changeStatusCmsLayoutTypeBatch(list,status);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量更新布局项类型状态失败！",err);
            throw new BusinessException("CMS.LAYOUTTYPE.500107");
        }
    }
    
    /** 
     * TODO 查询布局项类型  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    /*@Override
    public List<CmsLayoutTypeRespDTO> queryCmsDefaultLayoutTypeList() throws BusinessException {
        LogUtil.info(MODULE, "调用查询布局项类型开始，入参：{}");
         1.验证前店入参 
        
         2.根据条件检索布局项类型  
        List<CmsLayoutTypeRespDTO> list = new ArrayList<CmsLayoutTypeRespDTO>();
        try {
            //2.1缓存查询
            Map<Long ,CmsLayoutTypeRespDTO> map = (HashMap<Long ,CmsLayoutTypeRespDTO> )CacheUtil.getItem(CmsConstants.ParamConfig.CMS.LAYOUTTYPE.CACHE);
            if(CollectionUtils.isEmpty(map)){
                for(CmsLayoutTypeRespDTO respDTO:map.values()){
                    if(respDTO != null && CmsConstants.IsNot.CMS_ISNOT_1.equals(respDTO.getIsdefault())){
                        list.add(respDTO);
                    }
                }
            }else{//2.2 当缓存中没有时，取表
                CmsLayoutTypeReqDTO dto = new CmsLayoutTypeReqDTO();
                dto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                dto.setIsdefault(CmsConstants.IsNot.CMS_ISNOT_1);
                list = sv.queryCmsLayoutTypeList(dto);
            }
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询布局项类型出现异常！",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS.LAYOUTTYPE.500103);
        }
        
        return list;
    }*/

}

