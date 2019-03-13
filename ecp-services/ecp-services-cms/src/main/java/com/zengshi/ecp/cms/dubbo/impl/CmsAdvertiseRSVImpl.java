package com.zengshi.ecp.cms.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.zengshi.ecp.cms.dubbo.dto.CmsAdvertiseReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsAdvertiseRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsAdvertiseRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsAdvertiseSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsPlaceSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

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
public class CmsAdvertiseRSVImpl implements ICmsAdvertiseRSV{
    
    @Resource
    private ICmsAdvertiseSV sv;
    @Resource
    private ICmsPlaceSV cmsPlaceSV;
    
    //定义个常量，用于表示模块名称，可以使用：当前类的类名
    private static final String MODULE = CmsAdvertiseRSVImpl.class.getName();

    /** 
     * TODO 新增广告. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsAdvertiseRSV#saveCmsAdvertise(com.zengshi.ecp.cms.dubbo.dto.CmsAdvertiseReqDTO) 
     */
    @Override   
    public CmsAdvertiseRespDTO addCmsAdvertise(CmsAdvertiseReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用新增广告开始，入参：{dto="+dto.toString()+"}");
        CmsAdvertiseRespDTO respDTO = new CmsAdvertiseRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isBlank(dto.getAdvertiseTitle())){
            LogUtil.error(MODULE, "入参advertiseTitle为空！");
            String[] keyInfos = {"advertiseTitle"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getPlaceId())){
            LogUtil.error(MODULE, "入参placeId为空！");
            String[] keyInfos = {"placeId"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isBlank(dto.getLinkType())){
            LogUtil.error(MODULE, "入参linkType为空！");
            String[] keyInfos = {"linkType"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isBlank(dto.getLinkName())){
            LogUtil.error(MODULE, "入参linkName为空！");
            String[] keyInfos = {"linkName"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtils.isBlank(dto.getVfsId())){
            LogUtil.error(MODULE, "入参vfsId为空！");
            String[] keyInfos = {"vfsId"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtils.isBlank(dto.getSubSystem())){
            LogUtil.error(MODULE, "入参subSystem为空！");
            String[] keyInfos = {"subSystem"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{   
            respDTO = sv.addCmsAdvertise(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "新增广告失败:",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_ADVERTISE_500101);
        }
        return respDTO;
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsAdvertiseRSV#updateCmsAdvertise(com.zengshi.ecp.cms.dubbo.dto.CmsAdvertiseReqDTO) 
     */
    @Override
    public CmsAdvertiseRespDTO updateCmsAdvertise(CmsAdvertiseReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用更新广告开始，入参：{dto="+dto.toString()+"}");
        CmsAdvertiseRespDTO respDTO = new CmsAdvertiseRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = {"id"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtils.isBlank(dto.getAdvertiseTitle())){
            LogUtil.error(MODULE, "入参advertiseTitle为空！");
            String[] keyInfos = {"advertiseTitle"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getPlaceId())){
            LogUtil.error(MODULE, "入参placeId为空！");
            String[] keyInfos = {"placeId"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtils.isBlank(dto.getLinkType())){
            LogUtil.error(MODULE, "入参linkType为空！");
            String[] keyInfos = {"linkType"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtils.isBlank(dto.getLinkName())){
            LogUtil.error(MODULE, "入参linkName为空！");
            String[] keyInfos = {"linkName"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtils.isBlank(dto.getVfsId())){
            LogUtil.error(MODULE, "入参vfsId为空！");
            String[] keyInfos = {"vfsId"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtils.isBlank(dto.getSubSystem())){
            LogUtil.error(MODULE, "入参platType为空！");
            String[] keyInfos = {"platType"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{    
            respDTO = sv.updateCmsAdvertise(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新广告失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_ADVERTISE_500102);
        }
        return respDTO;
    }
    
    /** 
     * TODO 查询广告，分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsAdvertiseRSV#queryCmsAdvertisePage(com.zengshi.ecp.cms.dubbo.dto.CmsAdvertiseReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsAdvertiseRespDTO> queryCmsAdvertisePage(CmsAdvertiseReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用分页查询广告开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索广告  */
        PageResponseDTO<CmsAdvertiseRespDTO> pageInfo = PageResponseDTO.buildByBaseInfo(dto, CmsAdvertiseRespDTO.class);
        try{
            pageInfo =  sv.queryCmsAdvertisePage(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "查询广告失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_ADVERTISE_500103);
        }
        return pageInfo;
    }
    
    /** 
     * TODO 查询单个广告（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsAdvertiseRSV#queryCmsAdvertise(com.zengshi.ecp.cms.dubbo.dto.CmsAdvertiseReqDTO) 
     */
    @Override
    public CmsAdvertiseRespDTO queryCmsAdvertise(CmsAdvertiseReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用查询广告开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = {"id"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.根据条件检索广告  */
        CmsAdvertiseRespDTO cmsAdvertiseRespDTO = new CmsAdvertiseRespDTO();
        try {
            cmsAdvertiseRespDTO = sv.queryCmsAdvertise(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "根据ID查询广告出现异常！",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_ADVERTISE_500103);
        }
        return cmsAdvertiseRespDTO;
    }
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsAdvertiseRSV#deleteCmsAdvertise(java.lang.Long) 
     */
    @Override
    public void deleteCmsAdvertise(String id) throws BusinessException {
        LogUtil.info(MODULE, "调用删除广告开始，入参：{id="+id+"}");
        /* 1.验证前店入参 */
        if(StringUtils.isBlank(id)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = {"id"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑删除广告  */
        try{
            sv.deleteCmsAdvertise(id);
        } catch(Exception err){
            LogUtil.error(MODULE, "删除广告失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_ADVERTISE_500101);
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsAdvertiseRSV#deleteCmsAdvertiseBatch(java.util.List) 
     */
    @Override
    public void deleteCmsAdvertiseBatch(List<String> list) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除广告开始，入参：{list="+list.toArray()+"}");
        /* 1.验证前店入参 */
        if(CollectionUtils.isEmpty(list)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = {"list"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑批量删除广告  */
        try{
            sv.deleteCmsAdvertiseBatch(list);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量删除广告失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_ADVERTISE_500104);
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsAdvertiseRSV#changeStatusCmsAdvertise(java.lang.Long, java.lang.String) 
     */
    @Override
    public void changeStatusCmsAdvertise(String id,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除广告开始，入参：{id="+id+",status="+status+"}");
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
        /* 2.更新广告状态  */
        try{
            sv.changeStatusCmsAdvertise(id, status);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新广告状态失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_ADVERTISE_500106);
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsAdvertiseRSV#changeStatusCmsAdvertiseBatch(java.util.List, java.lang.String) 
     */
    @Override
    public void changeStatusCmsAdvertiseBatch(List<String> list,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除广告开始，入参：{list="+list.toArray()+",status="+status+"}");
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
        
        /* 2.更新广告状态  */
        try{
            sv.changeStatusCmsAdvertiseBatch(list,status);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量更新广告状态失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_ADVERTISE_500107);
        }
    }
    
    /** 
     * TODO 查询广告  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public List<CmsAdvertiseRespDTO> queryCmsAdvertiseList(CmsAdvertiseReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用查询广告开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索楼层广告  */
        List<CmsAdvertiseRespDTO> list = new ArrayList<CmsAdvertiseRespDTO>();
        try {
            list = sv.queryCmsAdvertiseList(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询广告出现异常！",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_ADVERTISE_500103);
        }
        
        return list;
    }

}

