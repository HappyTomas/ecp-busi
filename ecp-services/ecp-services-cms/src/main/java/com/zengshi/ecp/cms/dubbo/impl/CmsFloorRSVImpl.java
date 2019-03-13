package com.zengshi.ecp.cms.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.zengshi.ecp.cms.dubbo.dto.CmsFloorInfoDetailReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorInfoDetailRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorSV;
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
public class CmsFloorRSVImpl implements ICmsFloorRSV{
    
    @Resource
    private ICmsFloorSV sv;
    @Resource
    private ICmsPlaceSV cmsPlaceSV;
    
    //定义个常量，用于表示模块名称，可以使用：当前类的类名
    private static final String MODULE = CmsFloorRSVImpl.class.getName();


    /** 
     * TODO 新增楼层. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorRSV#saveCmsFloor(com.zengshi.ecp.cms.dubbo.dto.CmsFloorReqDTO) 
     */
    @Override
    public CmsFloorRespDTO addCmsFloor(CmsFloorReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用新增楼层开始，入参：{dto="+dto.toString()+"}");
        CmsFloorRespDTO respDTO = new CmsFloorRespDTO();
        /*1.验证前店入参*/
        if(StringUtils.isBlank(dto.getFloorName())){
            LogUtil.error(MODULE, "入参FloorName为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="FloorName";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getSiteId())){
            LogUtil.error(MODULE, "入参SiteId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="SiteId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getTemplateId())){
            LogUtil.error(MODULE, "入参TemplateId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="TemplateId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getPlaceId())){
            LogUtil.error(MODULE, "入参placeId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="placeId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{   
            respDTO = sv.addCmsFloor(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "新增楼层失败:",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOOR_500101);
        }
        
        return respDTO;
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorRSV#updateCmsFloor(com.zengshi.ecp.cms.dubbo.dto.CmsFloorReqDTO) 
     */
    @Override
    public CmsFloorRespDTO updateCmsFloor(CmsFloorReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用更新楼层开始，入参：{dto="+dto.toString()+"}");
        CmsFloorRespDTO respDTO = new CmsFloorRespDTO();
        /*1.验证前店入参*/
        if(dto.getId() == null){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtils.isBlank(dto.getFloorName())){
            LogUtil.error(MODULE, "入参FloorName为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="FloorName";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getSiteId())){
            LogUtil.error(MODULE, "入参SiteId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="SiteId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getTemplateId())){
            LogUtil.error(MODULE, "入参TemplateId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="TemplateId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getPlaceId())){
            LogUtil.error(MODULE, "入参placeId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="placeId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{    
            respDTO = sv.updateCmsFloor(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新楼层失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOOR_500102);
        }
        
        return respDTO;
    }
    
    /** 
     * TODO 查询楼层，分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorRSV#queryCmsFloorPage(com.zengshi.ecp.cms.dubbo.dto.CmsFloorReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsFloorRespDTO> queryCmsFloorPage(CmsFloorReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用分页查询楼层开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索楼层  */
        PageResponseDTO<CmsFloorRespDTO> pageInfo = PageResponseDTO.buildByBaseInfo(dto, CmsFloorRespDTO.class);
        try{
            pageInfo =  sv.queryCmsFloorPage(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "查询楼层失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOOR_500103);
        }
        return pageInfo;
    }
    
    /** 
     * TODO 查询楼层  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public List<CmsFloorRespDTO> queryCmsFloorList(CmsFloorReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用查询楼层开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索楼层  */
        List<CmsFloorRespDTO> list = new ArrayList<CmsFloorRespDTO>();
        try {
            list = sv.queryCmsFloorList(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询楼层出现异常！",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOOR_500103);
        }
        
        return list;
    }
    
    /** 
     * TODO 查询单个楼层（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorRSV#queryCmsFloor(com.zengshi.ecp.cms.dubbo.dto.CmsFloorReqDTO) 
     */
    @Override
    public CmsFloorRespDTO queryCmsFloor(CmsFloorReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用查询楼层开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        if(dto.getId() == null){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.根据条件检索楼层  */
        CmsFloorRespDTO cmsFloorRespDTO = new CmsFloorRespDTO();
        try {
            cmsFloorRespDTO = sv.queryCmsFloor(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "根据ID查询楼层出现异常！",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOOR_500103);
        }
        
        return cmsFloorRespDTO;
    }
    /** 
     * queryFloorInfoDetail:(根据选项查询楼层信息). <br/> 
     * TODO(楼层id – 必选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    @Override
    public CmsFloorInfoDetailRespDTO queryFloorInfoDetail(CmsFloorInfoDetailReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用查询楼层开始，入参：{dto="+dto.toString()+"}");
        
        /* 1.验证前店入参 */
        if(dto.getFloorId() == null){
            LogUtil.error(MODULE, "入参floorId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="floorId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.根据条件检索楼层  */
        CmsFloorInfoDetailRespDTO respDTO = null;
        try {
            respDTO = sv.queryFloorInfoDetail(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "根据楼层id查询楼层信息详情出现异常！",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOOR_500103);
        }
        
        return respDTO;
    }
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorRSV#deleteCmsFloor(java.lang.Long) 
     */
    @Override
    public void deleteCmsFloor(String id) throws BusinessException {
        LogUtil.info(MODULE, "调用删除楼层开始，入参：{id="+id+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isBlank(id)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑删除楼层  */
        try{
            sv.deleteCmsFloor(id);
        } catch(Exception err){
            LogUtil.error(MODULE, "删除楼层失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOOR_500101);
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorRSV#deleteCmsFloorBatch(java.util.List) 
     */
    @Override
    public void deleteCmsFloorBatch(List<String> list) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除楼层开始，入参：{list="+list.toArray()+"}");
        /* 1.验证前店入参 */
        if(CollectionUtils.isEmpty(list)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="list";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑批量删除楼层  */
        try{
            sv.deleteCmsFloorBatch(list);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量删除楼层失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOOR_500104);
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorRSV#changeStatusCmsFloor(java.lang.Long, java.lang.String) 
     */
    @Override
    public void changeStatusCmsFloor(String id,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除楼层开始，入参：{id="+id+",status="+status+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isBlank(id)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtils.isBlank(status)){
            LogUtil.error(MODULE, "入参status为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="status";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        /* 2.更新楼层状态  */
        try{
            sv.changeStatusCmsFloor(id, status);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新楼层状态失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOOR_500106);
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorRSV#changeStatusCmsFloorBatch(java.util.List, java.lang.String) 
     */
    @Override
    public void changeStatusCmsFloorBatch(List<String> list,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除楼层开始，入参：{list="+list.toArray()+",status="+status+"}");
        /* 1.验证前店入参 */
        if(CollectionUtils.isEmpty(list)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="list";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtils.isBlank(status)){
            LogUtil.error(MODULE, "入参status为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="status";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.更新楼层状态  */
        try{
            sv.changeStatusCmsFloorBatch(list,status);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量更新楼层状态失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOOR_500107);
        }
    }

}

