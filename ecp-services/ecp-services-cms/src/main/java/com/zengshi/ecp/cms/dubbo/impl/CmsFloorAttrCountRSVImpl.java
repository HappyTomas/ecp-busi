package com.zengshi.ecp.cms.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.cms.dubbo.dto.CmsFloorAttrCountReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorAttrCountRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorAttrCountRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorAttrCountSV;
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
public class CmsFloorAttrCountRSVImpl implements ICmsFloorAttrCountRSV{
    
    @Resource
    private ICmsFloorAttrCountSV sv;
    @Resource
    private ICmsPlaceSV cmsPlaceSV;
    
    //定义个常量，用于表示模块名称，可以使用：当前类的类名
    private static final String MODULE = CmsFloorAttrCountRSVImpl.class.getName();


    /** 
     * TODO 新增楼层属性数量. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorAttrCountRSV#saveCmsFloorAttrCount(com.zengshi.ecp.cms.dubbo.dto.CmsFloorAttrCountReqDTO) 
     */
    @Override
    public CmsFloorAttrCountRespDTO addCmsFloorAttrCount(CmsFloorAttrCountReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用新增楼层属性数量开始，入参：{dto="+dto.toString()+"}");
        CmsFloorAttrCountRespDTO respDTO = new CmsFloorAttrCountRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isBlank(dto.getFloorAttr())){
            LogUtil.error(MODULE, "入参FloorAttr为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="FloorAttr";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getShowCount())){
            LogUtil.error(MODULE, "入参ShowCount为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="ShowCount";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{   
            respDTO = sv.addCmsFloorAttrCount(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "新增楼层属性数量失败:",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORATTRCOUNT_500101);
        }
        return respDTO;
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorAttrCountRSV#updateCmsFloorAttrCount(com.zengshi.ecp.cms.dubbo.dto.CmsFloorAttrCountReqDTO) 
     */
    @Override
    public CmsFloorAttrCountRespDTO updateCmsFloorAttrCount(CmsFloorAttrCountReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用更新楼层属性数量开始，入参：{dto="+dto.toString()+"}");
        CmsFloorAttrCountRespDTO respDTO = new CmsFloorAttrCountRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isBlank(dto.getFloorAttr())){
            LogUtil.error(MODULE, "入参FloorAttr为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="FloorAttr";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getShowCount())){
            LogUtil.error(MODULE, "入参ShowCount为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="ShowCount";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{    
            respDTO = sv.updateCmsFloorAttrCount(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新楼层属性数量失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORATTRCOUNT_500102);
        }
        return respDTO;
    }
    
    /** 
     * TODO 查询楼层属性数量，分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorAttrCountRSV#queryCmsFloorAttrCountPage(com.zengshi.ecp.cms.dubbo.dto.CmsFloorAttrCountReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsFloorAttrCountRespDTO> queryCmsFloorAttrCountPage(CmsFloorAttrCountReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用分页查询楼层属性数量开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索楼层属性数量  */
        PageResponseDTO<CmsFloorAttrCountRespDTO> pageInfo = PageResponseDTO.buildByBaseInfo(dto, CmsFloorAttrCountRespDTO.class);
        try{
            pageInfo =  sv.queryCmsFloorAttrCountPage(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "查询楼层属性数量失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORATTRCOUNT_500103);
        }
        return pageInfo;
    }
    
    /** 
     * TODO 查询楼层标签  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public List<CmsFloorAttrCountRespDTO> queryCmsFloorAttrCountList(CmsFloorAttrCountReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用查询楼层标签开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索楼层标签  */
        List<CmsFloorAttrCountRespDTO> list = new ArrayList<CmsFloorAttrCountRespDTO>();
        try {
            list = sv.queryCmsFloorAttrCountList(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询楼层标签出现异常！",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORLABEL_500103);
        }
        
        return list;
    }
    
    /** 
     * TODO 查询单个楼层属性数量（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorAttrCountRSV#queryCmsFloorAttrCount(com.zengshi.ecp.cms.dubbo.dto.CmsFloorAttrCountReqDTO) 
     */
    @Override
    public CmsFloorAttrCountRespDTO queryCmsFloorAttrCount(CmsFloorAttrCountReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用查询楼层属性数量开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.根据条件检索楼层属性数量  */
        CmsFloorAttrCountRespDTO cmsFloorAttrCountRespDTO = new CmsFloorAttrCountRespDTO();
        try {
            cmsFloorAttrCountRespDTO = sv.queryCmsFloorAttrCount(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "根据ID查询楼层属性数量出现异常！",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORATTRCOUNT_500103);
        }
        
        return cmsFloorAttrCountRespDTO;
    }
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorAttrCountRSV#deleteCmsFloorAttrCount(java.lang.Long) 
     */
    @Override
    public void deleteCmsFloorAttrCount(String id) throws BusinessException {
        LogUtil.info(MODULE, "调用删除楼层属性数量开始，入参：{id="+id+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isBlank(id)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑删除楼层属性数量  */
        try{
            sv.deleteCmsFloorAttrCount(id);
        } catch(Exception err){
            LogUtil.error(MODULE, "删除楼层属性数量失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORATTRCOUNT_500101);
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorAttrCountRSV#deleteCmsFloorAttrCountBatch(java.util.List) 
     */
    @Override
    public void deleteCmsFloorAttrCountBatch(List<String> list) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除楼层属性数量开始，入参：{list="+list.toArray()+"}");
        /* 1.验证前店入参 */
        if(CollectionUtils.isEmpty(list)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="list";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑批量删除楼层属性数量  */
        try{
            sv.deleteCmsFloorAttrCountBatch(list);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量删除楼层属性数量失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORATTRCOUNT_500104);
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorAttrCountRSV#changeStatusCmsFloorAttrCount(java.lang.Long, java.lang.String) 
     */
    @Override
    public void changeStatusCmsFloorAttrCount(String id,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除楼层属性数量开始，入参：{id="+id+",status="+status+"}");
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
        /* 2.更新楼层属性数量状态  */
        try{
            sv.changeStatusCmsFloorAttrCount(id, status);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新楼层属性数量状态失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORATTRCOUNT_500106);
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorAttrCountRSV#changeStatusCmsFloorAttrCountBatch(java.util.List, java.lang.String) 
     */
    @Override
    public void changeStatusCmsFloorAttrCountBatch(List<String> list,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除楼层属性数量开始，入参：{list="+list.toArray()+",status="+status+"}");
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
        
        /* 2.更新楼层属性数量状态  */
        try{
            sv.changeStatusCmsFloorAttrCountBatch(list,status);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量更新楼层属性数量状态失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORATTRCOUNT_500107);
        }
    }

}

