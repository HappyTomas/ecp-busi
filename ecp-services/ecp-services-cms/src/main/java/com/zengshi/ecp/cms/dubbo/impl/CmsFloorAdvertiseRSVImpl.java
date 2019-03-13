package com.zengshi.ecp.cms.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.cms.dubbo.dto.CmsFloorAdvertiseReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorAdvertiseRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorAdvertiseRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorAdvertiseSV;
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
public class CmsFloorAdvertiseRSVImpl implements ICmsFloorAdvertiseRSV{
    
    @Resource
    private ICmsFloorAdvertiseSV sv;
    @Resource
    private ICmsPlaceSV cmsPlaceSV;
    
    //定义个常量，用于表示模块名称，可以使用：当前类的类名
    private static final String MODULE = CmsFloorAdvertiseRSVImpl.class.getName();


    /** 
     * TODO 新增楼层广告. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorAdvertiseRSV#saveCmsFloorAdvertise(com.zengshi.ecp.cms.dubbo.dto.CmsFloorAdvertiseReqDTO) 
     */
    @Override
    public CmsFloorAdvertiseRespDTO addCmsFloorAdvertise(CmsFloorAdvertiseReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用新增楼层广告开始，入参：{dto="+dto.toString()+"}");
        CmsFloorAdvertiseRespDTO respDTO = new CmsFloorAdvertiseRespDTO();
        /*1.验证前店入参*/
        
        /*2.调service层接口*/
        try{   
            respDTO = sv.addCmsFloorAdvertise(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "新增楼层广告失败:",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORADVERTISE_500101);
        }
        return respDTO;
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorAdvertiseRSV#updateCmsFloorAdvertise(com.zengshi.ecp.cms.dubbo.dto.CmsFloorAdvertiseReqDTO) 
     */
    @Override
    public CmsFloorAdvertiseRespDTO updateCmsFloorAdvertise(CmsFloorAdvertiseReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用更新楼层广告开始，入参：{dto="+dto.toString()+"}");
        CmsFloorAdvertiseRespDTO respDTO = new CmsFloorAdvertiseRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        /*2.调service层接口*/
        try{    
            respDTO = sv.updateCmsFloorAdvertise(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新楼层广告失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORADVERTISE_500102);
        }
        return respDTO;
    }
    
    /** 
     * TODO 查询楼层广告，分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorAdvertiseRSV#queryCmsFloorAdvertisePage(com.zengshi.ecp.cms.dubbo.dto.CmsFloorAdvertiseReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsFloorAdvertiseRespDTO> queryCmsFloorAdvertisePage(CmsFloorAdvertiseReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用分页查询楼层广告开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索楼层广告  */
        PageResponseDTO<CmsFloorAdvertiseRespDTO> pageInfo = PageResponseDTO.buildByBaseInfo(dto, CmsFloorAdvertiseRespDTO.class);
        try{
            pageInfo =  sv.queryCmsFloorAdvertisePage(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "查询楼层广告失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORADVERTISE_500103);
        }
        return pageInfo;
    }
    
    /** 
     * TODO 查询楼层广告  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public List<CmsFloorAdvertiseRespDTO> queryCmsFloorAdvertiseList(CmsFloorAdvertiseReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用查询楼层广告开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索楼层广告  */
        List<CmsFloorAdvertiseRespDTO> list = new ArrayList<CmsFloorAdvertiseRespDTO>();
        try {
            list = sv.queryCmsFloorAdvertiseList(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询楼层广告出现异常！",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORADVERTISE_500103);
        }
        
        return list;
    }
    
    /** 
     * TODO 查询单个楼层广告（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorAdvertiseRSV#queryCmsFloorAdvertise(com.zengshi.ecp.cms.dubbo.dto.CmsFloorAdvertiseReqDTO) 
     */
    @Override
    public CmsFloorAdvertiseRespDTO queryCmsFloorAdvertise(CmsFloorAdvertiseReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用查询楼层广告开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = {"id"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.根据条件检索楼层广告  */
        CmsFloorAdvertiseRespDTO cmsFloorAdvertiseRespDTO = new CmsFloorAdvertiseRespDTO();
        try {
            cmsFloorAdvertiseRespDTO = sv.queryCmsFloorAdvertise(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "根据ID查询楼层广告出现异常！",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORADVERTISE_500103);
        }
        
        return cmsFloorAdvertiseRespDTO;
    }
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorAdvertiseRSV#deleteCmsFloorAdvertise(java.lang.Long) 
     */
    @Override
    public void deleteCmsFloorAdvertise(String id) throws BusinessException {
        LogUtil.info(MODULE, "调用删除楼层广告开始，入参：{id="+id+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isBlank(id)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑删除楼层广告  */
        try{
            sv.deleteCmsFloorAdvertise(id);
        } catch(Exception err){
            LogUtil.error(MODULE, "删除楼层广告失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORADVERTISE_500101);
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorAdvertiseRSV#deleteCmsFloorAdvertiseBatch(java.util.List) 
     */
    @Override
    public void deleteCmsFloorAdvertiseBatch(List<String> list) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除楼层广告开始，入参：{list="+list.toArray()+"}");
        /* 1.验证前店入参 */
        if(CollectionUtils.isEmpty(list)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="list";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑批量删除楼层广告  */
        try{
            sv.deleteCmsFloorAdvertiseBatch(list);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量删除楼层广告失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORADVERTISE_500104);
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorAdvertiseRSV#changeStatusCmsFloorAdvertise(java.lang.Long, java.lang.String) 
     */
    @Override
    public void changeStatusCmsFloorAdvertise(String id,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除楼层广告开始，入参：{id="+id+",status="+status+"}");
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
        /* 2.更新楼层广告状态  */
        try{
            sv.changeStatusCmsFloorAdvertise(id, status);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新楼层广告状态失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORADVERTISE_500106);
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorAdvertiseRSV#changeStatusCmsFloorAdvertiseBatch(java.util.List, java.lang.String) 
     */
    @Override
    public void changeStatusCmsFloorAdvertiseBatch(List<String> list,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除楼层广告开始，入参：{list="+list.toArray()+",status="+status+"}");
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
        
        /* 2.更新楼层广告状态  */
        try{
            sv.changeStatusCmsFloorAdvertiseBatch(list,status);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量更新楼层广告状态失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORADVERTISE_500107);
        }
    }

}

