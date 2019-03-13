package com.zengshi.ecp.cms.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsAppFloorRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsAppFloorSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-cms-server <br>
 * Description: <br>
 * Date:2016年2月24日下午5:05:36  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author zhanbh
 * @version  
 * @since JDK 1.6
 */
public class CmsAppFloorRSVImpl implements ICmsAppFloorRSV {

    @Resource
    private ICmsAppFloorSV cmsAppFloorSV;
    
    //定义个常量，用于表示模块名称，可以使用：当前类的类名
    private static final String MODULE = CmsAppFloorRSVImpl.class.getName();
    
    /**
     * 
     * TODO 新增app楼层. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsAppFloorRSV#addCmsAppFloor(com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorReqDTO)
     */
    @Override
    public CmsAppFloorRespDTO addCmsAppFloor(CmsAppFloorReqDTO dto) throws BusinessException {
        CmsAppFloorRespDTO respDTO = new CmsAppFloorRespDTO();
        if(dto != null){
            LogUtil.info(MODULE, "调用新增app楼层开始，入参：{dto="+dto.toString()+"}");
            /*1.验证前店入参*/
            if(StringUtil.isBlank(dto.getFloorName())){
                LogUtil.error(MODULE, "入参FloorName为空！");
                throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,new String[]{"FloorName"});
            }
            if(StringUtil.isBlank(dto.getStatus())){
                LogUtil.error(MODULE, "入参Status为空！");
                throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,new String[]{"Status"});
            }
            if(StringUtil.isEmpty(dto.getSortNo())){
                LogUtil.error(MODULE, "入参SortNo为空！");
                throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,new String[]{"SortNo"});
            }
            if(StringUtil.isEmpty(dto.getFloorTemplateId())){
                LogUtil.error(MODULE, "入参FloorTemplateId为空！");
                throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,new String[]{"FloorTemplateId"});
            }
            if(StringUtil.isEmpty(dto.getSiteId())){
                LogUtil.error(MODULE, "入参SiteId为空！");
                throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,new String[]{"SiteId"});
            }
            
            /*2.调service层接口*/
            try {
                respDTO = cmsAppFloorSV.addCmsAppFloor(dto);
            } catch (Exception err) {
                LogUtil.error(MODULE, "新增app楼层失败:",err);
                throw new BusinessException(CmsConstants.MsgCode.CMS_APPFLOOR_500101);
            }
        }else{
            LogUtil.error(MODULE, "调用新增app楼层失败，入参dto为空！");
            throw new BusinessException(CmsConstants.MsgCode.CMS_APPFLOOR_500101);
        }
        return respDTO;
    }

    /**
     * 
     * TODO 删除app楼层（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsAppFloorRSV#deleteCmsAppFloor(java.lang.String)
     */
    @Override
    public void deleteCmsAppFloor(String id) throws BusinessException {
        LogUtil.info(MODULE, "调用删除app楼层开始，入参：{id="+id+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isBlank(id)){
            LogUtil.error(MODULE, "入参id为空！");
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,new String[]{"id"});
        }
        /* 2.根据id删除app楼层  */
        try {
            cmsAppFloorSV.deleteCmsAppFloor(id);
        } catch (Exception err) {
            LogUtil.error(MODULE, "删除app楼层失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_APPFLOOR_500104);
        }
    }

    /**
     * 
     * TODO 批量删除app楼层（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsAppFloorRSV#deleteCmsAppFloorBatch(java.util.List)
     */
    @Override
    public void deleteCmsAppFloorBatch(List<String> list) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除app楼层开始，入参：{id="+list.toArray()+"}");
        /* 1.验证前店入参 */
        if(CollectionUtils.isEmpty(list)){
            LogUtil.error(MODULE, "入参id列表为空！");
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,new String[]{"id列表"});
        }
        /* 2.根据id列表删除app楼层  */
        try {
            cmsAppFloorSV.deleteCmsAppFloorBatch(list);
        } catch (Exception err) {
            LogUtil.error(MODULE, "批量删除app楼层失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_APPFLOOR_500105);
        }
    }

    /**
     * 
     * TODO 修改app楼层状态（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsAppFloorRSV#changeStatusCmsAppFloor(java.lang.String, java.lang.String)
     */
    @Override
    public void changeStatusCmsAppFloor(String id, String status) throws BusinessException {
        LogUtil.info(MODULE, "调用修改app楼层状态开始，入参：{id="+id+",status="+status+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isBlank(id)){
            LogUtil.error(MODULE, "入参id为空！");
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,new String[]{"id"});
        }
        if(StringUtil.isBlank(status)){
            LogUtil.error(MODULE, "入参status为空！");
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,new String[]{"status"});
        }
        /* 2.根据id更新app楼层状态  */
        try {
            cmsAppFloorSV.changeStatusCmsAppFloor(id, status);
        } catch (Exception err) {
            LogUtil.error(MODULE, "更新app楼层状态失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_APPFLOOR_500106);
        }
    }

    /**
     * 
     * TODO 批量修改app楼层状态（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsAppFloorRSV#changeStatusCmsAppFloorBatch(java.util.List, java.lang.String)
     */
    @Override
    public void changeStatusCmsAppFloorBatch(List<String> list, String status)
            throws BusinessException {
        LogUtil.info(MODULE, "调用批量修改app楼层状态开始，入参：{id="+list.toArray()+",status="+status+"}");
        /* 1.验证前店入参 */
        if(CollectionUtils.isEmpty(list)){
            LogUtil.error(MODULE, "入参id列表为空！");
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,new String[]{"id列表"});
        }
        if(StringUtil.isBlank(status)){
            LogUtil.error(MODULE, "入参status为空！");
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,new String[]{"status"});
        }
        /* 2.根据id列表删除app楼层  */
        try {
            cmsAppFloorSV.changeStatusCmsAppFloorBatch(list, status);
        } catch (Exception err) {
            LogUtil.error(MODULE, "批量修改app楼层状态失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_APPFLOOR_500107);
        }
    }

    /**
     * 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsAppFloorRSV#updateCmsAppFloor(com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorReqDTO)
     */
    @Override
    public CmsAppFloorRespDTO updateCmsAppFloor(CmsAppFloorReqDTO dto) throws BusinessException {
        CmsAppFloorRespDTO respDTO = new CmsAppFloorRespDTO();
        if(dto != null){
            LogUtil.info(MODULE, "调用修改app楼层开始，入参：{dto="+dto.toString()+"}");
            /*1.验证前店入参*/
            if(StringUtil.isBlank(dto.getFloorName())){
                LogUtil.error(MODULE, "入参FloorName为空！");
                throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,new String[]{"FloorName"});
            }
            if(StringUtil.isEmpty(dto.getFloorTemplateId())){
                LogUtil.error(MODULE, "入参FloorTemplateId为空！");
                throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,new String[]{"FloorTemplateId"});
            }
            if(StringUtil.isEmpty(dto.getSiteId())){
                LogUtil.error(MODULE, "入参SiteId为空！");
                throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,new String[]{"SiteId"});
            }
            
            /*2.调service层接口*/
            try {
                respDTO = cmsAppFloorSV.updateCmsAppFloor(dto);
            } catch (Exception err) {
                LogUtil.error(MODULE, "修改app楼层失败:",err);
                throw new BusinessException(CmsConstants.MsgCode.CMS_APPFLOOR_500102);
            }
        }else{
            LogUtil.error(MODULE, "调用修改app楼层失败，入参dto为空！");
            throw new BusinessException(CmsConstants.MsgCode.CMS_APPFLOOR_500102);
        }
        return respDTO;
    }

    /**
     * 
     * TODO 查询app楼层，分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsAppFloorRSV#queryCmsAppFloorPage(com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorReqDTO)
     */
    @Override
    public PageResponseDTO<CmsAppFloorRespDTO> queryCmsAppFloorPage(CmsAppFloorReqDTO dto)
            throws BusinessException {
        PageResponseDTO<CmsAppFloorRespDTO> pageInfo = PageResponseDTO.buildByBaseInfo(dto, CmsAppFloorRespDTO.class);
        if(dto != null){
            LogUtil.info(MODULE, "调用分页查询app楼层开始，入参：{dto="+dto.toString()+"}");
            /* 1.验证前店入参 */
            
            /* 2.根据条件检索app楼层  */
            try {
                pageInfo = cmsAppFloorSV.queryCmsAppFloorPage(dto);
            } catch (Exception err) {
                LogUtil.error(MODULE, "查询app楼层失败！",err);
                throw new BusinessException(CmsConstants.MsgCode.CMS_APPFLOOR_500103);
            }
        }else{
            LogUtil.error(MODULE, "调用分页查询app楼层失败，入参dto为空");
            throw new BusinessException(CmsConstants.MsgCode.CMS_APPFLOOR_500103);
        }
        return pageInfo;
    }

    /**
     * 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsAppFloorRSV#queryCmsAppFloor(com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorReqDTO)
     */
    @Override
    public CmsAppFloorRespDTO queryCmsAppFloor(CmsAppFloorReqDTO dto) throws BusinessException {
        CmsAppFloorRespDTO cmsAppFloorRespDTO = new CmsAppFloorRespDTO();
        if(dto != null){
            LogUtil.info(MODULE, "调用查询app楼层开始，入参：{dto="+dto.toString()+"}");
            /* 1.验证前店入参 */
            if(StringUtil.isEmpty(dto.getId())){
                LogUtil.error(MODULE, "入参id为空！");
                throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,new String[]{"id"});
            }
            /* 2.根据条件检索app楼层  */
            try {
                cmsAppFloorRespDTO = cmsAppFloorSV.queryCmsAppFloor(dto);
            } catch (Exception err) {
                LogUtil.error(MODULE, "查询app楼层失败！",err);
                throw new BusinessException(CmsConstants.MsgCode.CMS_APPFLOOR_500103);
            }
        }else{
            LogUtil.error(MODULE, "调用查询app楼层失败，入参dto为空");
            throw new BusinessException(CmsConstants.MsgCode.CMS_APPFLOOR_500103);
        }
        return cmsAppFloorRespDTO;
    }

    /**
     * 
     * TODO 查询APP楼层  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsAppFloorRSV#queryCmsAppFloorList(com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorReqDTO)
     */
    @Override
    public List<CmsAppFloorRespDTO> queryCmsAppFloorList(CmsAppFloorReqDTO dto)
            throws BusinessException {
        List<CmsAppFloorRespDTO> list = new ArrayList<CmsAppFloorRespDTO>();
        if(dto != null){
            LogUtil.info(MODULE, "调用查询app楼层列表开始，入参：{dto="+dto.toString()+"}");
            /* 1.验证前店入参 */
            
            /* 2.根据条件检索app楼层  */
            try {
                list = cmsAppFloorSV.queryCmsAppFloorList(dto);
            } catch (Exception err) {
                LogUtil.error(MODULE, "查询app楼层列表失败！",err);
                throw new BusinessException(CmsConstants.MsgCode.CMS_APPFLOOR_500103);
            }
        }else{
            LogUtil.error(MODULE, "调用查询app楼层列表失败，入参dto为空");
            throw new BusinessException(CmsConstants.MsgCode.CMS_APPFLOOR_500103);
        }
        return list;
    }

}

