package com.zengshi.ecp.cms.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.cms.dubbo.dto.CmsPageColorReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageColorRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageColorRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsPageColorSV;
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
public class CmsPageColorRSVImpl implements ICmsPageColorRSV{
    
    @Resource
    private ICmsPageColorSV sv;
    
    //定义个常量，用于表示模块名称，可以使用：当前类的类名
    private static final String MODULE = CmsPageColorRSVImpl.class.getName();


    /** 
     * TODO 新增页面. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageColorRSV#saveCmsPageColor(com.zengshi.ecp.cms.dubbo.dto.CmsPageColorReqDTO) 
     */
    @Override
    public CmsPageColorRespDTO addCmsPageColor(CmsPageColorReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用新增页面开始，入参：{dto="+dto.toString()+"}");
        CmsPageColorRespDTO respDTO = new CmsPageColorRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isBlank(dto.getColorName())){
            LogUtil.error(MODULE, "入参ColorName为空！");
            String[] keyColors = new String[1];
            keyColors[0]="ColorName";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyColors); 
        }
        if(StringUtil.isBlank(dto.getColorStyle())){
        	LogUtil.error(MODULE, "入参ColorStyle为空！");
        	String[] keyColors = new String[1];
        	keyColors[0]="ColorStyle";
        	throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyColors); 
        }
        
        /*2.调service层接口*/
        try{   
            respDTO = sv.addCmsPageColor(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "新增页面失败:",err);
            throw new BusinessException("CMS.PAGEColor.500101");
        }
        
        return respDTO;
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageColorRSV#updateCmsPageColor(com.zengshi.ecp.cms.dubbo.dto.CmsPageColorReqDTO) 
     */
    @Override
    public CmsPageColorRespDTO updateCmsPageColor(CmsPageColorReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用更新页面开始，入参：{dto="+dto.toString()+"}");
        CmsPageColorRespDTO respDTO = new CmsPageColorRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyColors = new String[1];
            keyColors[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyColors); 
        }
        if(StringUtil.isBlank(dto.getColorName())){
            LogUtil.error(MODULE, "入参ColorName为空！");
            String[] keyColors = new String[1];
            keyColors[0]="ColorName";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyColors); 
        }
        if(StringUtil.isBlank(dto.getColorStyle())){
        	LogUtil.error(MODULE, "入参ColorStyle为空！");
        	String[] keyColors = new String[1];
        	keyColors[0]="ColorStyle";
        	throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyColors); 
        }
        
        /*2.调service层接口*/
        try{    
            respDTO = sv.updateCmsPageColor(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新页面失败！",err);
            throw new BusinessException("CMS.PAGEColor.500102");
        }
        
        return respDTO;
    }
    
    /** 
     * TODO 查询页面，分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageColorRSV#queryCmsPageColorPage(com.zengshi.ecp.cms.dubbo.dto.CmsPageColorReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsPageColorRespDTO> queryCmsPageColorPage(CmsPageColorReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用分页查询页面开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索页面  */
        PageResponseDTO<CmsPageColorRespDTO> pageColor = PageResponseDTO.buildByBaseInfo(dto, CmsPageColorRespDTO.class);
        try{
            pageColor =  sv.queryCmsPageColorPage(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "查询页面失败！",err);
            throw new BusinessException("CMS.PAGEColor.500103");
        }
        return pageColor;
    }
    
    /** 
     * TODO 查询页面  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public List<CmsPageColorRespDTO> queryCmsPageColorList(CmsPageColorReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用查询页面开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索页面  */
        List<CmsPageColorRespDTO> list = new ArrayList<CmsPageColorRespDTO>();
        try {
            list = sv.queryCmsPageColorList(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询页面出现异常！",e);
            throw new BusinessException("CMS.PAGEColor.500103");
        }
        
        return list;
    }
    
    /** 
     * TODO 查询单个页面（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageColorRSV#queryCmsPageColor(com.zengshi.ecp.cms.dubbo.dto.CmsPageColorReqDTO) 
     */
    @Override
    public CmsPageColorRespDTO queryCmsPageColor(CmsPageColorReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用查询页面开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyColors = new String[1];
            keyColors[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyColors); 
        }
        
        /* 2.根据条件检索页面  */
        CmsPageColorRespDTO cmsPageColorRespDTO = new CmsPageColorRespDTO();
        try {
            cmsPageColorRespDTO = sv.queryCmsPageColor(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "根据ID查询页面出现异常！",e);
            throw new BusinessException("CMS.PAGEColor.500103");
        }
        
        return cmsPageColorRespDTO;
    }
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageColorRSV#deleteCmsPageColor(java.lang.Long) 
     */
    @Override
    public void deleteCmsPageColor(String id) throws BusinessException {
        LogUtil.info(MODULE, "调用删除页面开始，入参：{id="+id+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isBlank(id)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyColors = new String[1];
            keyColors[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyColors); 
        }
        
        /* 2.逻辑删除页面  */
        try{
            sv.deleteCmsPageColor(id);
        } catch(Exception err){
            LogUtil.error(MODULE, "删除页面失败！",err);
            throw new BusinessException("CMS.PAGEColor.500101");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageColorRSV#deleteCmsPageColorBatch(java.util.List) 
     */
    @Override
    public void deleteCmsPageColorBatch(List<String> list) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除页面开始，入参：{list="+list.toArray()+"}");
        /* 1.验证前店入参 */
        if(CollectionUtils.isEmpty(list)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyColors = new String[1];
            keyColors[0]="list";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyColors); 
        }
        
        /* 2.逻辑批量删除页面  */
        try{
            sv.deleteCmsPageColorBatch(list);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量删除页面失败！",err);
            throw new BusinessException("CMS.PAGEColor.500104");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageColorRSV#changeStatusCmsPageColor(java.lang.Long, java.lang.String) 
     */
    @Override
    public void changeStatusCmsPageColor(String id,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除页面开始，入参：{id="+id+",status="+status+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isBlank(id)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyColors = new String[1];
            keyColors[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyColors); 
        }
        if(StringUtil.isBlank(status)){
            LogUtil.error(MODULE, "入参status为空！");
            String[] keyColors = new String[1];
            keyColors[0]="status";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyColors); 
        }
        /* 2.更新页面状态  */
        try{
            sv.changeStatusCmsPageColor(id, status);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新页面状态失败！",err);
            throw new BusinessException("CMS.PAGEColor.500106");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageColorRSV#changeStatusCmsPageColorBatch(java.util.List, java.lang.String) 
     */
    @Override
    public void changeStatusCmsPageColorBatch(List<String> list,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除页面开始，入参：{list="+list.toArray()+",status="+status+"}");
        /* 1.验证前店入参 */
        if(CollectionUtils.isEmpty(list)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyColors = new String[1];
            keyColors[0]="list";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyColors); 
        }
        if(StringUtil.isBlank(status)){
            LogUtil.error(MODULE, "入参status为空！");
            String[] keyColors = new String[1];
            keyColors[0]="status";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyColors); 
        }
        
        /* 2.更新页面状态  */
        try{
            sv.changeStatusCmsPageColorBatch(list,status);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量更新页面状态失败！",err);
            throw new BusinessException("CMS.PAGEColor.500107");
        }
    }
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageColorRSV#changeStatusCmsPageColor(java.lang.Long, java.lang.String) 
     */
    @Override
    public void doDefaultCmsPageColor(String id,String isDefault) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除页面开始，入参：{id="+id+",isDefault="+isDefault+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isBlank(id)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyColors = new String[1];
            keyColors[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyColors); 
        }
        if(StringUtil.isBlank(isDefault)){
            LogUtil.error(MODULE, "入参isDefault为空！");
            String[] keyColors = new String[1];
            keyColors[0]="isDefault";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyColors); 
        }
        /* 2.更新页面状态  */
        try{
            sv.doDefaultCmsPageColor(id, isDefault);
        } catch(Exception err){
            LogUtil.error(MODULE, "设置默认页面失败！",err);
            throw new BusinessException("CMS.PAGEColor.500108");
        }
    }
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageColorRSV#changeStatusCmsPageColorBatch(java.util.List, java.lang.String) 
     */
    @Override
    public void doDefaultCmsPageColorBatch(List<String> list,String isDefault) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除页面开始，入参：{list="+list.toArray()+",isDefault="+isDefault+"}");
        /* 1.验证前店入参 */
        if(CollectionUtils.isEmpty(list)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyColors = new String[1];
            keyColors[0]="list";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyColors); 
        }
        if(StringUtil.isBlank(isDefault)){
            LogUtil.error(MODULE, "入参isDefault为空！");
            String[] keyColors = new String[1];
            keyColors[0]="isDefault";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyColors); 
        }
        
        /* 2.更新页面状态  */
        try{
            sv.doDefaultCmsPageColorBatch(list,isDefault);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量设置默认页面失败！",err);
            throw new BusinessException("CMS.PAGEColor.500109");
        }
    }
    
    /** 
     * TODO 查询页面  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    /*@Override
    public List<CmsPageColorRespDTO> queryCmsDefaultPageColorList() throws BusinessException {
        LogUtil.Color(MODULE, "调用查询页面开始，入参：{}");
         1.验证前店入参 
        
         2.根据条件检索页面  
        List<CmsPageColorRespDTO> list = new ArrayList<CmsPageColorRespDTO>();
        try {
            //2.1缓存查询
            Map<Long ,CmsPageColorRespDTO> map = (HashMap<Long ,CmsPageColorRespDTO> )CacheUtil.getItem(CmsConstants.ParamConfig.CMS_PAGEColor_CACHE);
            if(CollectionUtils.isEmpty(map)){
                for(CmsPageColorRespDTO respDTO:map.values()){
                    if(respDTO != null && CmsConstants.IsNot.CMS_ISNOT_1.equals(respDTO.getIsdefault())){
                        list.add(respDTO);
                    }
                }
            }else{//2.2 当缓存中没有时，取表
                CmsPageColorReqDTO dto = new CmsPageColorReqDTO();
                dto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                dto.setIsdefault(CmsConstants.IsNot.CMS_ISNOT_1);
                list = sv.queryCmsPageColorList(dto);
            }
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询页面出现异常！",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_PAGEColor_500103);
        }
        
        return list;
    }*/

}

