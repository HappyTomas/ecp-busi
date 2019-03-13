package com.zengshi.ecp.cms.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.zengshi.ecp.cms.dubbo.dto.CmsHotSearchReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsHotSearchRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsHotSearchRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsHotSearchSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsPlaceSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * Name: ECP <br>
 * Project Name:ecp-services-cms <br>
 * Description: <br>
 * Date:2015年8月7日下午5:11:10  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxm9
 * @version  
 * @since JDK 1.7
 */  
public class CmsHotSearchRSVImpl implements ICmsHotSearchRSV{
    
    @Resource
    private ICmsHotSearchSV sv;
    @Resource
    private ICmsPlaceSV cmsPlaceSV;
    
    //定义个常量，用于表示模块名称，可以使用：当前类的类名
    private static final String MODULE = CmsHotSearchRSVImpl.class.getName();

    /** 
     * TODO 新增热门搜索. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsHotSearchRSV#saveCmsHotSearch(com.zengshi.ecp.cms.dubbo.dto.CmsHotSearchReqDTO) 
     */
    @Override   
    public CmsHotSearchRespDTO addCmsHotSearch(CmsHotSearchReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用新增热门搜索开始，入参：{dto="+dto.toString()+"}");
        CmsHotSearchRespDTO respDTO = new CmsHotSearchRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isBlank(dto.getHotSearchName())){
            LogUtil.error(MODULE, "入参hotSearchName为空！");
            String[] keyInfos = {"hotSearchName"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
//        if(StringUtil.isEmpty(dto.getPlaceId())){
//            LogUtil.error(MODULE, "入参placeId为空！");
//            String[] keyInfos = {"placeId"};
//            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
//        }
        if(StringUtil.isEmpty(dto.getSiteId())){
            LogUtil.error(MODULE, "入参siteId为空！");
            String[] keyInfos = {"placeId"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
//        if(StringUtil.isEmpty(dto.getTemplateId())){
//            LogUtil.error(MODULE, "入参templateId为空！");
//            String[] keyInfos = {"placeId"};
//            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
//        }
//        
        /*2.调service层接口*/
        try{   
            respDTO = sv.addCmsHotSearch(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "新增热门搜索失败:",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_HOTSEARCH_500101);
        }
        return respDTO;
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsHotSearchRSV#updateCmsHotSearch(com.zengshi.ecp.cms.dubbo.dto.CmsHotSearchReqDTO) 
     */
    @Override
    public CmsHotSearchRespDTO updateCmsHotSearch(CmsHotSearchReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用更新热门搜索开始，入参：{dto="+dto.toString()+"}");
        CmsHotSearchRespDTO respDTO = new CmsHotSearchRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = {"id"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtils.isBlank(dto.getHotSearchName())){
            LogUtil.error(MODULE, "入参hotSearchName为空！");
            String[] keyInfos = {"hotSearchName"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
//        if(StringUtil.isEmpty(dto.getPlaceId())){
//            LogUtil.error(MODULE, "入参placeId为空！");
//            String[] keyInfos = {"placeId"};
//            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
//        }
        if(StringUtil.isEmpty(dto.getSiteId())){
            LogUtil.error(MODULE, "入参siteId为空！");
            String[] keyInfos = {"placeId"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
//        if(StringUtil.isEmpty(dto.getTemplateId())){
//            LogUtil.error(MODULE, "入参templateId为空！");
//            String[] keyInfos = {"placeId"};
//            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
//        }
        
        /*2.调service层接口*/
        try{    
            respDTO = sv.updateCmsHotSearch(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新热门搜索失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_HOTSEARCH_500102);
        }
        return respDTO;
    }
    
    /** 
     * TODO 查询热门搜索，分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsHotSearchRSV#queryCmsHotSearchPage(com.zengshi.ecp.cms.dubbo.dto.CmsHotSearchReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsHotSearchRespDTO> queryCmsHotSearchPage(CmsHotSearchReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用分页查询热门搜索开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索热门搜索  */
        PageResponseDTO<CmsHotSearchRespDTO> pageInfo = PageResponseDTO.buildByBaseInfo(dto, CmsHotSearchRespDTO.class);
        try{
            pageInfo =  sv.queryCmsHotSearchPage(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "查询热门搜索失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_HOTSEARCH_500103);
        }
        return pageInfo;
    }
    
    /** 
     * TODO 查询单个热门搜索（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsHotSearchRSV#queryCmsHotSearch(com.zengshi.ecp.cms.dubbo.dto.CmsHotSearchReqDTO) 
     */
    @Override
    public CmsHotSearchRespDTO queryCmsHotSearch(CmsHotSearchReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用查询热门搜索开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = {"id"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.根据条件检索热门搜索  */
        CmsHotSearchRespDTO cmsHotSearchRespDTO = new CmsHotSearchRespDTO();
        try {
            cmsHotSearchRespDTO = sv.queryCmsHotSearch(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "根据ID查询热门搜索出现异常！",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_HOTSEARCH_500103);
        }
        return cmsHotSearchRespDTO;
    }
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsHotSearchRSV#deleteCmsHotSearch(java.lang.Long) 
     */
    @Override
    public void deleteCmsHotSearch(String id) throws BusinessException {
        LogUtil.info(MODULE, "调用删除热门搜索开始，入参：{id="+id+"}");
        /* 1.验证前店入参 */
        if(StringUtils.isBlank(id)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = {"id"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑删除热门搜索  */
        try{
            sv.deleteCmsHotSearch(id);
        } catch(Exception err){
            LogUtil.error(MODULE, "删除热门搜索失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_HOTSEARCH_500101);
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsHotSearchRSV#deleteCmsHotSearchBatch(java.util.List) 
     */
    @Override
    public void deleteCmsHotSearchBatch(List<String> list) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除热门搜索开始，入参：{list="+list.toArray()+"}");
        /* 1.验证前店入参 */
        if(CollectionUtils.isEmpty(list)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = {"list"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑批量删除热门搜索  */
        try{
            sv.deleteCmsHotSearchBatch(list);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量删除热门搜索失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_HOTSEARCH_500104);
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsHotSearchRSV#changeStatusCmsHotSearch(java.lang.Long, java.lang.String) 
     */
    @Override
    public void changeStatusCmsHotSearch(String id,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除热门搜索开始，入参：{id="+id+",status="+status+"}");
        /* 1.验证前店入参 */
        if(StringUtils.isBlank(id)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = {"id"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtils.isBlank(status)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = {"status"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        /* 2.更新热门搜索状态  */
        try{
            sv.changeStatusCmsHotSearch(id, status);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新热门搜索状态失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_HOTSEARCH_500106);
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsHotSearchRSV#changeStatusCmsHotSearchBatch(java.util.List, java.lang.String) 
     */
    @Override
    public void changeStatusCmsHotSearchBatch(List<String> list,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除热门搜索开始，入参：{list="+list.toArray()+",status="+status+"}");
        /* 1.验证前店入参 */
        if(CollectionUtils.isEmpty(list)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = {"list"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtils.isBlank(status)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = {"status"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.更新热门搜索状态  */
        try{
            sv.changeStatusCmsHotSearchBatch(list,status);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量更新热门搜索状态失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_HOTSEARCH_500107);
        }
    }
    
    /** 
     * TODO 查询热门搜索  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public List<CmsHotSearchRespDTO> queryCmsHotSearchList(CmsHotSearchReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用查询热门搜索开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索楼层热门搜索  */
        List<CmsHotSearchRespDTO> list = new ArrayList<CmsHotSearchRespDTO>();
        try {
            list = sv.queryCmsHotSearchList(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询热门搜索出现异常！",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_HOTSEARCH_500103);
        }
        
        return list;
    }

}
