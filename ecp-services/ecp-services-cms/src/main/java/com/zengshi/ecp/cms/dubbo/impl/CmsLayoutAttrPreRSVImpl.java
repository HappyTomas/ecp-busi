package com.zengshi.ecp.cms.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutAttrPreReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutAttrPreRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutAttrPreRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutAttrPreSV;
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
public class CmsLayoutAttrPreRSVImpl implements ICmsLayoutAttrPreRSV{
    
    @Resource
    private ICmsLayoutAttrPreSV sv;
    
    //定义个常量，用于表示模块名称，可以使用：当前类的类名
    private static final String MODULE = CmsLayoutAttrPreRSVImpl.class.getName();


    /** 
     * TODO 新增页面属性. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutAttrPreRSV#saveCmsLayoutAttrPre(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutAttrPreReqDTO) 
     */
    @Override
    public CmsLayoutAttrPreRespDTO addCmsLayoutAttrPre(CmsLayoutAttrPreReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用新增页面属性开始，入参：{dto="+dto.toString()+"}");
        CmsLayoutAttrPreRespDTO respDTO = new CmsLayoutAttrPreRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getLayoutId())){
            LogUtil.error(MODULE, "入参LayoutId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="LayoutId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{   
            respDTO = sv.addCmsLayoutAttrPre(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "新增页面属性失败:",err);
            throw new BusinessException("CMS.LAYOUTATTRPRE.500101");
        }
        
        return respDTO;
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutAttrPreRSV#updateCmsLayoutAttrPre(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutAttrPreReqDTO) 
     */
    @Override
    public CmsLayoutAttrPreRespDTO updateCmsLayoutAttrPre(CmsLayoutAttrPreReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用更新页面属性开始，入参：{dto="+dto.toString()+"}");
        CmsLayoutAttrPreRespDTO respDTO = new CmsLayoutAttrPreRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
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
            respDTO = sv.updateCmsLayoutAttrPre(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新页面属性失败！",err);
            throw new BusinessException("CMS.LAYOUTATTRPRE.500102");
        }
        
        return respDTO;
    }
    
    /** 
     * TODO 查询页面属性，分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutAttrPreRSV#queryCmsLayoutAttrPrePage(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutAttrPreReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsLayoutAttrPreRespDTO> queryCmsLayoutAttrPrePage(CmsLayoutAttrPreReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用分页查询页面属性开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索页面属性  */
        PageResponseDTO<CmsLayoutAttrPreRespDTO> pageInfo = PageResponseDTO.buildByBaseInfo(dto, CmsLayoutAttrPreRespDTO.class);
        try{
            pageInfo =  sv.queryCmsLayoutAttrPrePage(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "查询页面属性失败！",err);
            throw new BusinessException("CMS.LAYOUTATTRPRE.500103");
        }
        return pageInfo;
    }
    
    /** 
     * TODO 查询页面属性  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public List<CmsLayoutAttrPreRespDTO> queryCmsLayoutAttrPreList(CmsLayoutAttrPreReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用查询页面属性开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索页面属性  */
        List<CmsLayoutAttrPreRespDTO> list = new ArrayList<CmsLayoutAttrPreRespDTO>();
        try {
            list = sv.queryCmsLayoutAttrPreList(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询页面属性出现异常！",e);
            throw new BusinessException("CMS.LAYOUTATTRPRE.500103");
        }
        
        return list;
    }
    
    /** 
     * TODO 查询单个页面属性（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutAttrPreRSV#queryCmsLayoutAttrPre(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutAttrPreReqDTO) 
     */
    @Override
    public CmsLayoutAttrPreRespDTO queryCmsLayoutAttrPre(CmsLayoutAttrPreReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用查询页面属性开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.根据条件检索页面属性  */
        CmsLayoutAttrPreRespDTO cmsLayoutAttrPreRespDTO = new CmsLayoutAttrPreRespDTO();
        try {
            cmsLayoutAttrPreRespDTO = sv.queryCmsLayoutAttrPre(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "根据ID查询页面属性出现异常！",e);
            throw new BusinessException("CMS.LAYOUTATTRPRE.500103");
        }
        
        return cmsLayoutAttrPreRespDTO;
    }
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutAttrPreRSV#deleteCmsLayoutAttrPre(java.lang.Long) 
     */
    @Override
    public void deleteCmsLayoutAttrPre(String id) throws BusinessException {
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
            sv.deleteCmsLayoutAttrPre(id);
        } catch(Exception err){
            LogUtil.error(MODULE, "删除页面属性失败！",err);
            throw new BusinessException("CMS.LAYOUTATTRPRE.500101");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutAttrPreRSV#deleteCmsLayoutAttrPreBatch(java.util.List) 
     */
    @Override
    public void deleteCmsLayoutAttrPreBatch(List<String> list) throws BusinessException {
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
            sv.deleteCmsLayoutAttrPreBatch(list);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量删除页面属性失败！",err);
            throw new BusinessException("CMS.LAYOUTATTRPRE.500104");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutAttrPreRSV#changeStatusCmsLayoutAttrPre(java.lang.Long, java.lang.String) 
     */
    @Override
    public void changeStatusCmsLayoutAttrPre(String id,String status) throws BusinessException {
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
            sv.changeStatusCmsLayoutAttrPre(id, status);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新页面属性状态失败！",err);
            throw new BusinessException("CMS.LAYOUTATTRPRE.500106");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutAttrPreRSV#changeStatusCmsLayoutAttrPreBatch(java.util.List, java.lang.String) 
     */
    @Override
    public void changeStatusCmsLayoutAttrPreBatch(List<String> list,String status) throws BusinessException {
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
            sv.changeStatusCmsLayoutAttrPreBatch(list,status);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量更新页面属性状态失败！",err);
            throw new BusinessException("CMS.LAYOUTATTRPRE.500107");
        }
    }
    
    /** 
     * TODO 查询页面属性  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    /*@Override
    public List<CmsLayoutAttrPreRespDTO> queryCmsDefaultLayoutAttrPreList() throws BusinessException {
        LogUtil.info(MODULE, "调用查询页面属性开始，入参：{}");
         1.验证前店入参 
        
         2.根据条件检索页面属性  
        List<CmsLayoutAttrPreRespDTO> list = new ArrayList<CmsLayoutAttrPreRespDTO>();
        try {
            //2.1缓存查询
            Map<Long ,CmsLayoutAttrPreRespDTO> map = (HashMap<Long ,CmsLayoutAttrPreRespDTO> )CacheUtil.getItem(CmsConstants.ParamConfig.CMS.LAYOUTATTRPRE.CACHE);
            if(CollectionUtils.isEmpty(map)){
                for(CmsLayoutAttrPreRespDTO respDTO:map.values()){
                    if(respDTO != null && CmsConstants.IsNot.CMS_ISNOT_1.equals(respDTO.getIsdefault())){
                        list.add(respDTO);
                    }
                }
            }else{//2.2 当缓存中没有时，取表
                CmsLayoutAttrPreReqDTO dto = new CmsLayoutAttrPreReqDTO();
                dto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                dto.setIsdefault(CmsConstants.IsNot.CMS_ISNOT_1);
                list = sv.queryCmsLayoutAttrPreList(dto);
            }
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询页面属性出现异常！",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS.LAYOUTATTRPRE.500103);
        }
        
        return list;
    }*/

}

