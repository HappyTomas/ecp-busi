package com.zengshi.ecp.cms.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.cms.dubbo.dto.CmsFloorLabelReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorLabelRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorLabelRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorLabelSV;
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
public class CmsFloorLabelRSVImpl implements ICmsFloorLabelRSV{
    
    @Resource
    private ICmsFloorLabelSV sv;
    @Resource
    private ICmsPlaceSV cmsPlaceSV;
    
    //定义个常量，用于表示模块名称，可以使用：当前类的类名
    private static final String MODULE = CmsFloorLabelRSVImpl.class.getName();


    /** 
     * TODO 新增楼层标签. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorLabelRSV#saveCmsFloorLabel(com.zengshi.ecp.cms.dubbo.dto.CmsFloorLabelReqDTO) 
     */
    @Override
    public CmsFloorLabelRespDTO addCmsFloorLabel(CmsFloorLabelReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用新增楼层标签开始，入参：{dto="+dto.toString()+"}");
        CmsFloorLabelRespDTO respDTO = new CmsFloorLabelRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isBlank(dto.getLabelName())){
            LogUtil.error(MODULE, "入参LabelName为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="LabelName";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isBlank(dto.getLinkUrl())){
            LogUtil.error(MODULE, "入参LinkUrl为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="LinkUrl";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{   
            respDTO = sv.addCmsFloorLabel(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "新增楼层标签失败:",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORLABEL_500101);
        }
        return respDTO;
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorLabelRSV#updateCmsFloorLabel(com.zengshi.ecp.cms.dubbo.dto.CmsFloorLabelReqDTO) 
     */
    @Override
    public CmsFloorLabelRespDTO updateCmsFloorLabel(CmsFloorLabelReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用更新楼层标签开始，入参：{dto="+dto.toString()+"}");
        CmsFloorLabelRespDTO respDTO = new CmsFloorLabelRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isBlank(dto.getLabelName())){
            LogUtil.error(MODULE, "入参LabelName为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="LabelName";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isBlank(dto.getLinkUrl())){
            LogUtil.error(MODULE, "入参LinkUrl为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="LinkUrl";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{    
            respDTO = sv.updateCmsFloorLabel(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新楼层标签失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORLABEL_500102);
        }
        return respDTO;
    }
    
    /** 
     * TODO 查询楼层标签，分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorLabelRSV#queryCmsFloorLabelPage(com.zengshi.ecp.cms.dubbo.dto.CmsFloorLabelReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsFloorLabelRespDTO> queryCmsFloorLabelPage(CmsFloorLabelReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用分页查询楼层标签开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索楼层标签  */
        PageResponseDTO<CmsFloorLabelRespDTO> pageInfo = PageResponseDTO.buildByBaseInfo(dto, CmsFloorLabelRespDTO.class);
        try{
            pageInfo =  sv.queryCmsFloorLabelPage(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "查询楼层标签失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORLABEL_500103);
        }
        return pageInfo;
    }
    
    /** 
     * TODO 查询楼层属性  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public List<CmsFloorLabelRespDTO> queryCmsFloorLabelList(CmsFloorLabelReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用查询属性标签开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索楼层属性  */
        List<CmsFloorLabelRespDTO> list = new ArrayList<CmsFloorLabelRespDTO>();
        try {
            list = sv.queryCmsFloorLabelList(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询楼层属性出现异常！",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORATTRCOUNT_500103);
        }
        
        return list;
    }
    
    /** 
     * TODO 查询单个楼层标签（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorLabelRSV#queryCmsFloorLabel(com.zengshi.ecp.cms.dubbo.dto.CmsFloorLabelReqDTO) 
     */
    @Override
    public CmsFloorLabelRespDTO queryCmsFloorLabel(CmsFloorLabelReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用查询楼层标签开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.根据条件检索楼层标签  */
        CmsFloorLabelRespDTO cmsFloorLabelRespDTO = new CmsFloorLabelRespDTO();
        try {
            cmsFloorLabelRespDTO = sv.queryCmsFloorLabel(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "根据ID查询楼层标签出现异常！",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORLABEL_500103);
        }
        
        return cmsFloorLabelRespDTO;
    }
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorLabelRSV#deleteCmsFloorLabel(java.lang.Long) 
     */
    @Override
    public void deleteCmsFloorLabel(String id) throws BusinessException {
        LogUtil.info(MODULE, "调用删除楼层标签开始，入参：{id="+id+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isBlank(id)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑删除楼层标签  */
        try{
            sv.deleteCmsFloorLabel(id);
        } catch(Exception err){
            LogUtil.error(MODULE, "删除楼层标签失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORLABEL_500101);
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorLabelRSV#deleteCmsFloorLabelBatch(java.util.List) 
     */
    @Override
    public void deleteCmsFloorLabelBatch(List<String> list) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除楼层标签开始，入参：{list="+list.toArray()+"}");
        /* 1.验证前店入参 */
        if(CollectionUtils.isEmpty(list)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="list";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑批量删除楼层标签  */
        try{
            sv.deleteCmsFloorLabelBatch(list);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量删除楼层标签失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORLABEL_500104);
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorLabelRSV#changeStatusCmsFloorLabel(java.lang.Long, java.lang.String) 
     */
    @Override
    public void changeStatusCmsFloorLabel(String id,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除楼层标签开始，入参：{id="+id+",status="+status+"}");
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
        /* 2.更新楼层标签状态  */
        try{
            sv.changeStatusCmsFloorLabel(id, status);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新楼层标签状态失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORLABEL_500106);
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorLabelRSV#changeStatusCmsFloorLabelBatch(java.util.List, java.lang.String) 
     */
    @Override
    public void changeStatusCmsFloorLabelBatch(List<String> list,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除楼层标签开始，入参：{list="+list.toArray()+",status="+status+"}");
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
        
        /* 2.更新楼层标签状态  */
        try{
            sv.changeStatusCmsFloorLabelBatch(list,status);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量更新楼层标签状态失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORLABEL_500107);
        }
    }

}

