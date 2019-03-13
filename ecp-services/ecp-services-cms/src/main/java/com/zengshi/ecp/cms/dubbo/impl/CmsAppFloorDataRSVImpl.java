package com.zengshi.ecp.cms.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorDataReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorDataRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsAppFloorDataRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsAppFloorDataSV;
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
public class CmsAppFloorDataRSVImpl implements ICmsAppFloorDataRSV {

    @Resource
    private ICmsAppFloorDataSV CmsAppFloorDataSV;
    
    //定义个常量，用于表示模块名称，可以使用：当前类的类名
    private static final String MODULE = CmsAppFloorDataRSVImpl.class.getName();
    
    /**
     * 
     * TODO 新增app楼层数据. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsAppFloorDataRSV#addCmsAppFloorData(com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorDataReqDTO)
     */
    @Override
    public CmsAppFloorDataRespDTO addCmsAppFloorData(CmsAppFloorDataReqDTO dto) throws BusinessException {
        CmsAppFloorDataRespDTO respDTO = new CmsAppFloorDataRespDTO();
        if(dto != null){
            LogUtil.info(MODULE, "调用新增app楼层数据开始，入参：{dto="+dto.toString()+"}");
            /*1.验证前店入参*/
            if(StringUtil.isEmpty(dto.getAppFloorId())){
                LogUtil.error(MODULE, "入参AppFloorId为空！");
                throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,new String[]{"AppFloorId"});
            }
            if(StringUtil.isEmpty(dto.getFloorPlaceId())){
                LogUtil.error(MODULE, "入参FloorPlaceId为空！");
                throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,new String[]{"FloorPlaceId"});
            }
            if(StringUtil.isBlank(dto.getName())){
                LogUtil.error(MODULE, "入参Name为空！");
                throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,new String[]{"Name"});
            }
            if(StringUtil.isEmpty(dto.getVfsId())){
                LogUtil.error(MODULE, "入参VfsId为空！");
                throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,new String[]{"VfsId"});
            }
            if(StringUtil.isEmpty(dto.getLinkType())){
                LogUtil.error(MODULE, "入参LinkType为空！");
                throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,new String[]{"LinkType"});
            }
            if(StringUtil.isBlank(dto.getStatus())){
                LogUtil.error(MODULE, "入参Status为空！");
                throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,new String[]{"Status"});
            }
            
            /*2.调service层接口*/
            try {
                respDTO = CmsAppFloorDataSV.addCmsAppFloorData(dto);
            } catch (Exception err) {
                LogUtil.error(MODULE, "新增app楼层数据失败:",err);
                throw new BusinessException(CmsConstants.MsgCode.CMS_APPFLOORDATA_500101);
            }
        }else{
            LogUtil.error(MODULE, "调用新增app楼层数据失败，入参dto为空！");
            throw new BusinessException(CmsConstants.MsgCode.CMS_APPFLOORDATA_500101);
        }
        return respDTO;
    }

    /**
     * 
     * TODO 删除app楼层数据（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsAppFloorDataRSV#deleteCmsAppFloorData(java.lang.String)
     */
    @Override
    public void deleteCmsAppFloorData(String id) throws BusinessException {
        LogUtil.info(MODULE, "调用删除app楼层数据开始，入参：{id="+id+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isBlank(id)){
            LogUtil.error(MODULE, "入参id为空！");
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,new String[]{"id"});
        }
        /* 2.根据id删除app楼层数据  */
        try {
            CmsAppFloorDataSV.deleteCmsAppFloorData(id);
        } catch (Exception err) {
            LogUtil.error(MODULE, "删除app楼层数据失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_APPFLOORDATA_500104);
        }
    }

    /**
     * 
     * TODO 批量删除app楼层数据（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsAppFloorDataRSV#deleteCmsAppFloorDataBatch(java.util.List)
     */
    @Override
    public void deleteCmsAppFloorDataBatch(List<String> list) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除app楼层数据开始，入参：{id="+list.toArray()+"}");
        /* 1.验证前店入参 */
        if(CollectionUtils.isEmpty(list)){
            LogUtil.error(MODULE, "入参id列表为空！");
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,new String[]{"id列表"});
        }
        /* 2.根据id列表删除app楼层数据  */
        try {
            CmsAppFloorDataSV.deleteCmsAppFloorDataBatch(list);
        } catch (Exception err) {
            LogUtil.error(MODULE, "批量删除app楼层数据失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_APPFLOORDATA_500105);
        }
    }

    /**
     * 
     * TODO 修改app楼层数据状态（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsAppFloorDataRSV#changeStatusCmsAppFloorData(java.lang.String, java.lang.String)
     */
    @Override
    public void changeStatusCmsAppFloorData(String id, String status) throws BusinessException {
        LogUtil.info(MODULE, "调用修改app楼层数据状态开始，入参：{id="+id+",status="+status+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isBlank(id)){
            LogUtil.error(MODULE, "入参id为空！");
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,new String[]{"id"});
        }
        if(StringUtil.isBlank(status)){
            LogUtil.error(MODULE, "入参status为空！");
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,new String[]{"status"});
        }
        /* 2.根据id更新app楼层数据状态  */
        try {
            CmsAppFloorDataSV.changeStatusCmsAppFloorData(id, status);
        } catch (Exception err) {
            LogUtil.error(MODULE, "更新app楼层数据状态失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_APPFLOORDATA_500106);
        }
    }

    /**
     * 
     * TODO 批量修改app楼层数据状态（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsAppFloorDataRSV#changeStatusCmsAppFloorDataBatch(java.util.List, java.lang.String)
     */
    @Override
    public void changeStatusCmsAppFloorDataBatch(List<String> list, String status)
            throws BusinessException {
        LogUtil.info(MODULE, "调用批量修改app楼层数据状态开始，入参：{id="+list.toArray()+",status="+status+"}");
        /* 1.验证前店入参 */
        if(CollectionUtils.isEmpty(list)){
            LogUtil.error(MODULE, "入参id列表为空！");
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,new String[]{"id列表"});
        }
        if(StringUtil.isBlank(status)){
            LogUtil.error(MODULE, "入参status为空！");
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,new String[]{"status"});
        }
        /* 2.根据id列表删除app楼层数据  */
        try {
            CmsAppFloorDataSV.changeStatusCmsAppFloorDataBatch(list, status);
        } catch (Exception err) {
            LogUtil.error(MODULE, "批量修改app楼层数据状态失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_APPFLOORDATA_500107);
        }
    }

    /**
     * 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsAppFloorDataRSV#updateCmsAppFloorData(com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorDataReqDTO)
     */
    @Override
    public CmsAppFloorDataRespDTO updateCmsAppFloorData(CmsAppFloorDataReqDTO dto) throws BusinessException {
        CmsAppFloorDataRespDTO respDTO = new CmsAppFloorDataRespDTO();
        if(dto != null){
            LogUtil.info(MODULE, "调用修改app楼层数据开始，入参：{dto="+dto.toString()+"}");
            if(StringUtil.isEmpty(dto.getAppFloorId())){
                LogUtil.error(MODULE, "入参AppFloorId为空！");
                throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,new String[]{"AppFloorId"});
            }
            if(StringUtil.isEmpty(dto.getFloorPlaceId())){
                LogUtil.error(MODULE, "入参FloorPlaceId为空！");
                throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,new String[]{"FloorPlaceId"});
            }
            if(StringUtil.isBlank(dto.getName())){
                LogUtil.error(MODULE, "入参Name为空！");
                throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,new String[]{"Name"});
            }
            if(StringUtil.isEmpty(dto.getLinkType())){
                LogUtil.error(MODULE, "入参LinkType为空！");
                throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,new String[]{"LinkType"});
            }
            
            /*2.调service层接口*/
            try {
                respDTO = CmsAppFloorDataSV.updateCmsAppFloorData(dto);
            } catch (Exception err) {
                LogUtil.error(MODULE, "修改app楼层数据失败:",err);
                throw new BusinessException(CmsConstants.MsgCode.CMS_APPFLOORDATA_500102);
            }
        }else{
            LogUtil.error(MODULE, "调用修改app楼层数据失败，入参dto为空！");
            throw new BusinessException(CmsConstants.MsgCode.CMS_APPFLOORDATA_500102);
        }
        return respDTO;
    }

    /**
     * 
     * TODO 查询app楼层数据，分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsAppFloorDataRSV#queryCmsAppFloorDataPage(com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorDataReqDTO)
     */
    @Override
    public PageResponseDTO<CmsAppFloorDataRespDTO> queryCmsAppFloorDataPage(CmsAppFloorDataReqDTO dto)
            throws BusinessException {
        PageResponseDTO<CmsAppFloorDataRespDTO> pageInfo = PageResponseDTO.buildByBaseInfo(dto, CmsAppFloorDataRespDTO.class);
        if(dto != null){
            LogUtil.info(MODULE, "调用分页查询app楼层数据开始，入参：{dto="+dto.toString()+"}");
            /* 1.验证前店入参 */
            
            /* 2.根据条件检索app楼层数据  */
            try {
                pageInfo = CmsAppFloorDataSV.queryCmsAppFloorDataPage(dto);
            } catch (Exception err) {
                LogUtil.error(MODULE, "查询app楼层数据失败！",err);
                throw new BusinessException(CmsConstants.MsgCode.CMS_APPFLOORDATA_500103);
            }
        }else{
            LogUtil.error(MODULE, "调用分页查询app楼层数据失败，入参dto为空");
            throw new BusinessException(CmsConstants.MsgCode.CMS_APPFLOORDATA_500103);
        }
        return pageInfo;
    }

    /**
     * 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsAppFloorDataRSV#queryCmsAppFloorData(com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorDataReqDTO)
     */
    @Override
    public CmsAppFloorDataRespDTO queryCmsAppFloorData(CmsAppFloorDataReqDTO dto) throws BusinessException {
        CmsAppFloorDataRespDTO CmsAppFloorDataRespDTO = new CmsAppFloorDataRespDTO();
        if(dto != null){
            LogUtil.info(MODULE, "调用查询app楼层数据开始，入参：{dto="+dto.toString()+"}");
            /* 1.验证前店入参 */
            if(StringUtil.isEmpty(dto.getId())){
                LogUtil.error(MODULE, "入参id为空！");
                throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,new String[]{"id"});
            }
            /* 2.根据条件检索app楼层数据  */
            try {
                CmsAppFloorDataRespDTO = CmsAppFloorDataSV.queryCmsAppFloorData(dto);
            } catch (Exception err) {
                LogUtil.error(MODULE, "查询app楼层数据失败！",err);
                throw new BusinessException(CmsConstants.MsgCode.CMS_APPFLOORDATA_500103);
            }
        }else{
            LogUtil.error(MODULE, "调用查询app楼层数据失败，入参dto为空");
            throw new BusinessException(CmsConstants.MsgCode.CMS_APPFLOORDATA_500103);
        }
        return CmsAppFloorDataRespDTO;
    }

    /**
     * 
     * TODO 查询app楼层数据  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsAppFloorDataRSV#queryCmsAppFloorDataList(com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorDataReqDTO)
     */
    @Override
    public List<CmsAppFloorDataRespDTO> queryCmsAppFloorDataList(CmsAppFloorDataReqDTO dto)
            throws BusinessException {
        List<CmsAppFloorDataRespDTO> list = new ArrayList<CmsAppFloorDataRespDTO>();
        if(dto != null){
            LogUtil.info(MODULE, "调用查询app楼层数据列表开始，入参：{dto="+dto.toString()+"}");
            /* 1.验证前店入参 */
            
            /* 2.根据条件检索app楼层数据  */
            try {
                list = CmsAppFloorDataSV.queryCmsAppFloorDataList(dto);
            } catch (Exception err) {
                LogUtil.error(MODULE, "查询app楼层数据列表失败！",err);
                throw new BusinessException(CmsConstants.MsgCode.CMS_APPFLOORDATA_500103);
            }
        }else{
            LogUtil.error(MODULE, "调用查询app楼层数据列表失败，入参dto为空");
            throw new BusinessException(CmsConstants.MsgCode.CMS_APPFLOORDATA_500103);
        }
        return list;
    }

}

