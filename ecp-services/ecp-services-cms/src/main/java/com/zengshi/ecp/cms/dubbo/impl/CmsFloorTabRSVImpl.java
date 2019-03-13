package com.zengshi.ecp.cms.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.cms.dubbo.dto.CmsFloorTabReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorTabRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorTabRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorTabSV;
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
public class CmsFloorTabRSVImpl implements ICmsFloorTabRSV{
    
    @Resource
    private ICmsFloorTabSV sv;
    @Resource
    private ICmsPlaceSV cmsPlaceSV;
    
    //定义个常量，用于表示模块名称，可以使用：当前类的类名
    private static final String MODULE = CmsFloorTabRSVImpl.class.getName();


    /** 
     * TODO 新增楼层页签. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorTabRSV#saveCmsFloorTab(com.zengshi.ecp.cms.dubbo.dto.CmsFloorTabReqDTO) 
     */
    @Override
    public CmsFloorTabRespDTO addCmsFloorTab(CmsFloorTabReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用新增楼层页签开始，入参：{dto="+dto.toString()+"}");
        CmsFloorTabRespDTO respDTO = new CmsFloorTabRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isBlank(dto.getTabName())){
            LogUtil.error(MODULE, "入参TabName为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="TabName";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isBlank(dto.getIsLink())){
            LogUtil.error(MODULE, "入参IsLink为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="IsLink";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{   
            respDTO = sv.addCmsFloorTab(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "新增楼层页签失败:",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORTAB_500101);
        }
        
        return respDTO;
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorTabRSV#updateCmsFloorTab(com.zengshi.ecp.cms.dubbo.dto.CmsFloorTabReqDTO) 
     */
    @Override
    public CmsFloorTabRespDTO updateCmsFloorTab(CmsFloorTabReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用更新楼层页签开始，入参：{dto="+dto.toString()+"}");
        CmsFloorTabRespDTO respDTO = new CmsFloorTabRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isBlank(dto.getTabName())){
            LogUtil.error(MODULE, "入参TabName为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="TabName";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isBlank(dto.getIsLink())){
            LogUtil.error(MODULE, "入参IsLink为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="IsLink";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{    
            respDTO = sv.updateCmsFloorTab(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新楼层页签失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORTAB_500102);
        }
        
        return respDTO;
    }
    
    /** 
     * TODO 查询楼层页签，分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorTabRSV#queryCmsFloorTabPage(com.zengshi.ecp.cms.dubbo.dto.CmsFloorTabReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsFloorTabRespDTO> queryCmsFloorTabPage(CmsFloorTabReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用分页查询楼层页签开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索楼层页签  */
        PageResponseDTO<CmsFloorTabRespDTO> pageInfo = PageResponseDTO.buildByBaseInfo(dto, CmsFloorTabRespDTO.class);
        try{
            pageInfo =  sv.queryCmsFloorTabPage(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "查询楼层页签失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORTAB_500103);
        }
        return pageInfo;
    }
    
    /** 
     * TODO 查询楼层页签  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public List<CmsFloorTabRespDTO> queryCmsFloorTabList(CmsFloorTabReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用查询楼层页签开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索楼层页签  */
        List<CmsFloorTabRespDTO> list = new ArrayList<CmsFloorTabRespDTO>();
        try {
            list = sv.queryCmsFloorTabList(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询楼层页签出现异常！",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORTAB_500103);
        }
        
        return list;
    }
    
    /** 
     * TODO 查询单个楼层页签（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorTabRSV#queryCmsFloorTab(com.zengshi.ecp.cms.dubbo.dto.CmsFloorTabReqDTO) 
     */
    @Override
    public CmsFloorTabRespDTO queryCmsFloorTab(CmsFloorTabReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用查询楼层页签开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.根据条件检索楼层页签  */
        CmsFloorTabRespDTO cmsFloorTabRespDTO = new CmsFloorTabRespDTO();
        try {
            cmsFloorTabRespDTO = sv.queryCmsFloorTab(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "根据ID查询楼层页签出现异常！",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORTAB_500103);
        }
        
        return cmsFloorTabRespDTO;
    }
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorTabRSV#deleteCmsFloorTab(java.lang.Long) 
     */
    @Override
    public void deleteCmsFloorTab(String id) throws BusinessException {
        LogUtil.info(MODULE, "调用删除楼层页签开始，入参：{id="+id+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isBlank(id)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑删除楼层页签  */
        try{
            sv.deleteCmsFloorTab(id);
        } catch(Exception err){
            LogUtil.error(MODULE, "删除楼层页签失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORTAB_500101);
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorTabRSV#deleteCmsFloorTabBatch(java.util.List) 
     */
    @Override
    public void deleteCmsFloorTabBatch(List<String> list) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除楼层页签开始，入参：{list="+list.toArray()+"}");
        /* 1.验证前店入参 */
        if(CollectionUtils.isEmpty(list)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="list";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑批量删除楼层页签  */
        try{
            sv.deleteCmsFloorTabBatch(list);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量删除楼层页签失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORTAB_500104);
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorTabRSV#changeStatusCmsFloorTab(java.lang.Long, java.lang.String) 
     */
    @Override
    public void changeStatusCmsFloorTab(String id,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除楼层页签开始，入参：{id="+id+",status="+status+"}");
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
        /* 2.更新楼层页签状态  */
        try{
            sv.changeStatusCmsFloorTab(id, status);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新楼层页签状态失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORTAB_500106);
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorTabRSV#changeStatusCmsFloorTabBatch(java.util.List, java.lang.String) 
     */
    @Override
    public void changeStatusCmsFloorTabBatch(List<String> list,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除楼层页签开始，入参：{list="+list.toArray()+",status="+status+"}");
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
        
        /* 2.更新楼层页签状态  */
        try{
            sv.changeStatusCmsFloorTabBatch(list,status);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量更新楼层页签状态失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORTAB_500107);
        }
    }
    
}

