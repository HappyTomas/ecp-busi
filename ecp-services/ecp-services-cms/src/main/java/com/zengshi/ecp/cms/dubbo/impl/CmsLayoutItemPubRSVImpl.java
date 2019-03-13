package com.zengshi.ecp.cms.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPubReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPubRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutItemPubRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutItemPubSV;
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
public class CmsLayoutItemPubRSVImpl implements ICmsLayoutItemPubRSV{
    
    @Resource
    private ICmsLayoutItemPubSV sv;
    
    //定义个常量，用于表示模块名称，可以使用：当前类的类名
    private static final String MODULE = CmsLayoutItemPubRSVImpl.class.getName();


    /** 
     * TODO 新增页面布局项. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutItemPubRSV#saveCmsLayoutItemPub(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPubReqDTO) 
     */
    @Override
    public CmsLayoutItemPubRespDTO addCmsLayoutItemPub(CmsLayoutItemPubReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用新增页面布局项开始，入参：{dto="+dto.toString()+"}");
        CmsLayoutItemPubRespDTO respDTO = new CmsLayoutItemPubRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getLayoutId())){
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
            respDTO = sv.addCmsLayoutItemPub(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "新增页面布局项失败:",err);
            throw new BusinessException("CMS.LAYOUTITEMPUB.500101");
        }
        
        return respDTO;
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutItemPubRSV#updateCmsLayoutItemPub(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPubReqDTO) 
     */
    @Override
    public CmsLayoutItemPubRespDTO updateCmsLayoutItemPub(CmsLayoutItemPubReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用更新页面布局项开始，入参：{dto="+dto.toString()+"}");
        CmsLayoutItemPubRespDTO respDTO = new CmsLayoutItemPubRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{    
            respDTO = sv.updateCmsLayoutItemPub(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新页面布局项失败！",err);
            throw new BusinessException("CMS.LAYOUTITEMPUB.500102");
        }
        
        return respDTO;
    }
    
    /** 
     * TODO 查询页面布局项，分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutItemPubRSV#queryCmsLayoutItemPubPage(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPubReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsLayoutItemPubRespDTO> queryCmsLayoutItemPubPage(CmsLayoutItemPubReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用分页查询页面布局项开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索页面布局项  */
        PageResponseDTO<CmsLayoutItemPubRespDTO> pageInfo = PageResponseDTO.buildByBaseInfo(dto, CmsLayoutItemPubRespDTO.class);
        try{
            pageInfo =  sv.queryCmsLayoutItemPubPage(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "查询页面布局项失败！",err);
            throw new BusinessException("CMS.LAYOUTITEMPUB.500103");
        }
        return pageInfo;
    }
    
    /** 
     * TODO 查询页面布局项  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public List<CmsLayoutItemPubRespDTO> queryCmsLayoutItemPubList(CmsLayoutItemPubReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用查询页面布局项开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索页面布局项  */
        List<CmsLayoutItemPubRespDTO> list = new ArrayList<CmsLayoutItemPubRespDTO>();
        try {
            list = sv.queryCmsLayoutItemPubList(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询页面布局项出现异常！",e);
            throw new BusinessException("CMS.LAYOUTITEMPUB.500103");
        }
        
        return list;
    }
    
    /** 
     * TODO 查询单个页面布局项（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutItemPubRSV#queryCmsLayoutItemPub(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPubReqDTO) 
     */
    @Override
    public CmsLayoutItemPubRespDTO queryCmsLayoutItemPub(CmsLayoutItemPubReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用查询页面布局项开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.根据条件检索页面布局项  */
        CmsLayoutItemPubRespDTO cmsLayoutItemPubRespDTO = new CmsLayoutItemPubRespDTO();
        try {
            cmsLayoutItemPubRespDTO = sv.queryCmsLayoutItemPub(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "根据ID查询页面布局项出现异常！",e);
            throw new BusinessException("CMS.LAYOUTITEMPUB.500103");
        }
        
        return cmsLayoutItemPubRespDTO;
    }
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutItemPubRSV#deleteCmsLayoutItemPub(java.lang.Long) 
     */
    @Override
    public void deleteCmsLayoutItemPub(String id) throws BusinessException {
        LogUtil.info(MODULE, "调用删除页面布局项开始，入参：{id="+id+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isBlank(id)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑删除页面布局项  */
        try{
            sv.deleteCmsLayoutItemPub(id);
        } catch(Exception err){
            LogUtil.error(MODULE, "删除页面布局项失败！",err);
            throw new BusinessException("CMS.LAYOUTITEMPUB.500101");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutItemPubRSV#deleteCmsLayoutItemPubBatch(java.util.List) 
     */
    @Override
    public void deleteCmsLayoutItemPubBatch(List<String> list) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除页面布局项开始，入参：{list="+list.toArray()+"}");
        /* 1.验证前店入参 */
        if(CollectionUtils.isEmpty(list)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="list";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑批量删除页面布局项  */
        try{
            sv.deleteCmsLayoutItemPubBatch(list);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量删除页面布局项失败！",err);
            throw new BusinessException("CMS.LAYOUTITEMPUB.500104");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutItemPubRSV#changeStatusCmsLayoutItemPub(java.lang.Long, java.lang.String) 
     */
    @Override
    public void changeStatusCmsLayoutItemPub(String id,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除页面布局项开始，入参：{id="+id+",status="+status+"}");
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
        /* 2.更新页面布局项状态  */
        try{
            sv.changeStatusCmsLayoutItemPub(id, status);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新页面布局项状态失败！",err);
            throw new BusinessException("CMS.LAYOUTITEMPUB.500106");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutItemPubRSV#changeStatusCmsLayoutItemPubBatch(java.util.List, java.lang.String) 
     */
    @Override
    public void changeStatusCmsLayoutItemPubBatch(List<String> list,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除页面布局项开始，入参：{list="+list.toArray()+",status="+status+"}");
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
        
        /* 2.更新页面布局项状态  */
        try{
            sv.changeStatusCmsLayoutItemPubBatch(list,status);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量更新页面布局项状态失败！",err);
            throw new BusinessException("CMS.LAYOUTITEMPUB.500107");
        }
    }
    
    /** 
     * TODO 查询页面布局项  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    /*@Override
    public List<CmsLayoutItemPubRespDTO> queryCmsDefaultLayoutItemPubList() throws BusinessException {
        LogUtil.info(MODULE, "调用查询页面布局项开始，入参：{}");
         1.验证前店入参 
        
         2.根据条件检索页面布局项  
        List<CmsLayoutItemPubRespDTO> list = new ArrayList<CmsLayoutItemPubRespDTO>();
        try {
            //2.1缓存查询
            Map<Long ,CmsLayoutItemPubRespDTO> map = (HashMap<Long ,CmsLayoutItemPubRespDTO> )CacheUtil.getItem(CmsConstants.ParamConfig.CMS.LAYOUTITEMPUB.CACHE);
            if(CollectionUtils.isEmpty(map)){
                for(CmsLayoutItemPubRespDTO respDTO:map.values()){
                    if(respDTO != null && CmsConstants.IsNot.CMS_ISNOT_1.equals(respDTO.getIsdefault())){
                        list.add(respDTO);
                    }
                }
            }else{//2.2 当缓存中没有时，取表
                CmsLayoutItemPubReqDTO dto = new CmsLayoutItemPubReqDTO();
                dto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                dto.setIsdefault(CmsConstants.IsNot.CMS_ISNOT_1);
                list = sv.queryCmsLayoutItemPubList(dto);
            }
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询页面布局项出现异常！",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS.LAYOUTITEMPUB.500103);
        }
        
        return list;
    }*/

}

