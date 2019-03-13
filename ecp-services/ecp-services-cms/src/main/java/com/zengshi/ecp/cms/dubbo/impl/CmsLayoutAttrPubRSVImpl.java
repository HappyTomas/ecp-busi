package com.zengshi.ecp.cms.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutAttrPubReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutAttrPubRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutAttrPubRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutAttrPubSV;
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
public class CmsLayoutAttrPubRSVImpl implements ICmsLayoutAttrPubRSV{
    
    @Resource
    private ICmsLayoutAttrPubSV sv;
    
    //定义个常量，用于表示模块名称，可以使用：当前类的类名
    private static final String MODULE = CmsLayoutAttrPubRSVImpl.class.getName();


    /** 
     * TODO 新增页面属性. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutAttrPubRSV#saveCmsLayoutAttrPub(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutAttrPubReqDTO) 
     */
    @Override
    public CmsLayoutAttrPubRespDTO addCmsLayoutAttrPub(CmsLayoutAttrPubReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用新增页面属性开始，入参：{dto="+dto.toString()+"}");
        CmsLayoutAttrPubRespDTO respDTO = new CmsLayoutAttrPubRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getLayoutAttrId())){
            LogUtil.error(MODULE, "入参LayoutAttrId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="LayoutAttrId";
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
            respDTO = sv.addCmsLayoutAttrPub(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "新增页面属性失败:",err);
            throw new BusinessException("CMS.LAYOUTATTRPUB.500101");
        }
        
        return respDTO;
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutAttrPubRSV#updateCmsLayoutAttrPub(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutAttrPubReqDTO) 
     */
    @Override
    public CmsLayoutAttrPubRespDTO updateCmsLayoutAttrPub(CmsLayoutAttrPubReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用更新页面属性开始，入参：{dto="+dto.toString()+"}");
        CmsLayoutAttrPubRespDTO respDTO = new CmsLayoutAttrPubRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{    
            respDTO = sv.updateCmsLayoutAttrPub(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新页面属性失败！",err);
            throw new BusinessException("CMS.LAYOUTATTRPUB.500102");
        }
        
        return respDTO;
    }
    
    /** 
     * TODO 查询页面属性，分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutAttrPubRSV#queryCmsLayoutAttrPubPage(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutAttrPubReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsLayoutAttrPubRespDTO> queryCmsLayoutAttrPubPage(CmsLayoutAttrPubReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用分页查询页面属性开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索页面属性  */
        PageResponseDTO<CmsLayoutAttrPubRespDTO> pageInfo = PageResponseDTO.buildByBaseInfo(dto, CmsLayoutAttrPubRespDTO.class);
        try{
            pageInfo =  sv.queryCmsLayoutAttrPubPage(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "查询页面属性失败！",err);
            throw new BusinessException("CMS.LAYOUTATTRPUB.500103");
        }
        return pageInfo;
    }
    
    /** 
     * TODO 查询页面属性  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public List<CmsLayoutAttrPubRespDTO> queryCmsLayoutAttrPubList(CmsLayoutAttrPubReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用查询页面属性开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索页面属性  */
        List<CmsLayoutAttrPubRespDTO> list = new ArrayList<CmsLayoutAttrPubRespDTO>();
        try {
            list = sv.queryCmsLayoutAttrPubList(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询页面属性出现异常！",e);
            throw new BusinessException("CMS.LAYOUTATTRPUB.500103");
        }
        
        return list;
    }
    
    /** 
     * TODO 查询单个页面属性（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutAttrPubRSV#queryCmsLayoutAttrPub(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutAttrPubReqDTO) 
     */
    @Override
    public CmsLayoutAttrPubRespDTO queryCmsLayoutAttrPub(CmsLayoutAttrPubReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用查询页面属性开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.根据条件检索页面属性  */
        CmsLayoutAttrPubRespDTO cmsLayoutAttrPubRespDTO = new CmsLayoutAttrPubRespDTO();
        try {
            cmsLayoutAttrPubRespDTO = sv.queryCmsLayoutAttrPub(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "根据ID查询页面属性出现异常！",e);
            throw new BusinessException("CMS.LAYOUTATTRPUB.500103");
        }
        
        return cmsLayoutAttrPubRespDTO;
    }
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutAttrPubRSV#deleteCmsLayoutAttrPub(java.lang.Long) 
     */
    @Override
    public void deleteCmsLayoutAttrPub(String id) throws BusinessException {
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
            sv.deleteCmsLayoutAttrPub(id);
        } catch(Exception err){
            LogUtil.error(MODULE, "删除页面属性失败！",err);
            throw new BusinessException("CMS.LAYOUTATTRPUB.500101");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutAttrPubRSV#deleteCmsLayoutAttrPubBatch(java.util.List) 
     */
    @Override
    public void deleteCmsLayoutAttrPubBatch(List<String> list) throws BusinessException {
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
            sv.deleteCmsLayoutAttrPubBatch(list);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量删除页面属性失败！",err);
            throw new BusinessException("CMS.LAYOUTATTRPUB.500104");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutAttrPubRSV#changeStatusCmsLayoutAttrPub(java.lang.Long, java.lang.String) 
     */
    @Override
    public void changeStatusCmsLayoutAttrPub(String id,String status) throws BusinessException {
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
            sv.changeStatusCmsLayoutAttrPub(id, status);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新页面属性状态失败！",err);
            throw new BusinessException("CMS.LAYOUTATTRPUB.500106");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutAttrPubRSV#changeStatusCmsLayoutAttrPubBatch(java.util.List, java.lang.String) 
     */
    @Override
    public void changeStatusCmsLayoutAttrPubBatch(List<String> list,String status) throws BusinessException {
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
            sv.changeStatusCmsLayoutAttrPubBatch(list,status);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量更新页面属性状态失败！",err);
            throw new BusinessException("CMS.LAYOUTATTRPUB.500107");
        }
    }
    
    /** 
     * TODO 查询页面属性  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    /*@Override
    public List<CmsLayoutAttrPubRespDTO> queryCmsDefaultLayoutAttrPubList() throws BusinessException {
        LogUtil.info(MODULE, "调用查询页面属性开始，入参：{}");
         1.验证前店入参 
        
         2.根据条件检索页面属性  
        List<CmsLayoutAttrPubRespDTO> list = new ArrayList<CmsLayoutAttrPubRespDTO>();
        try {
            //2.1缓存查询
            Map<Long ,CmsLayoutAttrPubRespDTO> map = (HashMap<Long ,CmsLayoutAttrPubRespDTO> )CacheUtil.getItem(CmsConstants.ParamConfig.CMS.LAYOUTATTRPUB.CACHE);
            if(CollectionUtils.isEmpty(map)){
                for(CmsLayoutAttrPubRespDTO respDTO:map.values()){
                    if(respDTO != null && CmsConstants.IsNot.CMS_ISNOT_1.equals(respDTO.getIsdefault())){
                        list.add(respDTO);
                    }
                }
            }else{//2.2 当缓存中没有时，取表
                CmsLayoutAttrPubReqDTO dto = new CmsLayoutAttrPubReqDTO();
                dto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                dto.setIsdefault(CmsConstants.IsNot.CMS_ISNOT_1);
                list = sv.queryCmsLayoutAttrPubList(dto);
            }
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询页面属性出现异常！",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS.LAYOUTATTRPUB.500103);
        }
        
        return list;
    }*/

}

