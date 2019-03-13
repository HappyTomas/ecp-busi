package com.zengshi.ecp.cms.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.zengshi.ecp.cms.dubbo.dto.CmsFloorGdsReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorGdsRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorGdsRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorGdsSV;
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
public class CmsFloorGdsRSVImpl implements ICmsFloorGdsRSV{
    
    @Resource
    private ICmsFloorGdsSV sv;
    @Resource
    private ICmsPlaceSV cmsPlaceSV;
    
    //定义个常量，用于表示模块名称，可以使用：当前类的类名
    private static final String MODULE = CmsFloorGdsRSVImpl.class.getName();


    /** 
     * TODO 新增楼层商品. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorGdsRSV#saveCmsFloorGds(com.zengshi.ecp.cms.dubbo.dto.CmsFloorGdsReqDTO) 
     */
    @Override
    public CmsFloorGdsRespDTO addCmsFloorGds(CmsFloorGdsReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用新增楼层商品开始，入参：{dto="+dto.toString()+"}");
        CmsFloorGdsRespDTO respDTO = new CmsFloorGdsRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getGdsId())){
            LogUtil.error(MODULE, "入参GdsId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="GdsId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        /*if(dto.getTabId()==null){
            LogUtil.error(MODULE, "入参TabId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="TabId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }*/
        
        /*2.调service层接口*/
        try{   
            respDTO = sv.addCmsFloorGds(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "新增楼层商品失败:",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORGDS_500101);
        }
        return respDTO;
    }

    /** 
     * TODO 新增楼层商品. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorGdsRSV#saveCmsFloorGds(com.zengshi.ecp.cms.dubbo.dto.CmsFloorGdsReqDTO) 
     */
    @Override
    public List<CmsFloorGdsRespDTO> addCmsFloorGdsBatch(List<CmsFloorGdsReqDTO> list) throws BusinessException {
        LogUtil.info(MODULE, "调用新增楼层商品开始，入参：{dto="+list.toString()+"}");
//        CmsFloorGdsRespDTO respDTO = new CmsFloorGdsRespDTO();
        /*1.验证前店入参*/
		for (int i = 0; i < list.size(); i++) {
			if (StringUtil.isEmpty(list.get(i).getGdsId())) {
				LogUtil.error(MODULE, "入参GdsId为空！");
				String[] keyInfos = new String[1];
				keyInfos[0] = "GdsId";
				throw new BusinessException(
						CmsConstants.MsgCode.CMS_COMMON_500102, keyInfos);
			}
		}
        /*2.调service层接口*/
		List<CmsFloorGdsRespDTO> cmsFloorGdsRespDTOs = null;
        try{   
        	cmsFloorGdsRespDTOs = sv.addCmsFloorGdsBatch(list);
        } catch(Exception err){
            LogUtil.error(MODULE, "新增楼层商品失败:",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORGDS_500101);
        }
        return cmsFloorGdsRespDTOs;
    }
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorGdsRSV#updateCmsFloorGds(com.zengshi.ecp.cms.dubbo.dto.CmsFloorGdsReqDTO) 
     */
    @Override
    public CmsFloorGdsRespDTO updateCmsFloorGds(CmsFloorGdsReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用更新楼层商品开始，入参：{dto="+dto.toString()+"}");
        CmsFloorGdsRespDTO respDTO = new CmsFloorGdsRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getGdsId())){
            LogUtil.error(MODULE, "入参GdsId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="GdsId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        /*if(dto.getTabId()==null){
            LogUtil.error(MODULE, "入参TabId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="TabId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }*/
        
        /*2.调service层接口*/
        try{    
            respDTO = sv.updateCmsFloorGds(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新楼层商品失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORGDS_500102);
        }
        return respDTO;
    }
    
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorGdsRSV#updateCmsFloorGds(com.zengshi.ecp.cms.dubbo.dto.CmsFloorGdsReqDTO) 
     */
    @Override
    public List<CmsFloorGdsRespDTO> updateCmsFloorGdsBatch(List<CmsFloorGdsReqDTO> list) throws BusinessException {
        LogUtil.info(MODULE, "调用更新楼层商品开始，入参：{dto="+list.toString()+"}");
//        CmsFloorGdsRespDTO respDTO = new CmsFloorGdsRespDTO();
        /*1.验证前店入参*/
		for (int i = 0; i < list.size(); i++) {
			if (StringUtil.isEmpty(list.get(i).getId())) {
				LogUtil.error(MODULE, "入参ID为空！");
				String[] keyInfos = new String[1];
				keyInfos[0] = "id";
				throw new BusinessException(
						CmsConstants.MsgCode.CMS_COMMON_500102, keyInfos);
			}
			if (StringUtil.isEmpty(list.get(i).getGdsId())) {
				LogUtil.error(MODULE, "入参GdsId为空！");
				String[] keyInfos = new String[1];
				keyInfos[0] = "GdsId";
				throw new BusinessException(
						CmsConstants.MsgCode.CMS_COMMON_500102, keyInfos);
			}
		}
        /*2.调service层接口*/
		List<CmsFloorGdsRespDTO> cmsFloorGdsRespDTOs = null;
        try{    
            cmsFloorGdsRespDTOs = sv.updateCmsFloorGdsBatch(list);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新楼层商品失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORGDS_500102);
        }
        return cmsFloorGdsRespDTOs;
    }
    
    /** 
     * TODO 查询楼层商品，分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorGdsRSV#queryCmsFloorGdsPage(com.zengshi.ecp.cms.dubbo.dto.CmsFloorGdsReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsFloorGdsRespDTO> queryCmsFloorGdsPage(CmsFloorGdsReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用分页查询楼层商品开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索楼层商品  */
        PageResponseDTO<CmsFloorGdsRespDTO> pageInfo = PageResponseDTO.buildByBaseInfo(dto, CmsFloorGdsRespDTO.class);
        try{
            pageInfo =  sv.queryCmsFloorGdsPage(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "查询楼层商品失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORGDS_500103);
        }
        return pageInfo;
    }
    
    /** 
     * TODO 查询楼层商品  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public List<CmsFloorGdsRespDTO> queryCmsFloorGdsList(CmsFloorGdsReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用查询楼层商品开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索内容位置  */
        List<CmsFloorGdsRespDTO> list = new ArrayList<CmsFloorGdsRespDTO>();
        try {
            list = sv.queryCmsFloorGdsList(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询楼层商品出现异常！",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORGDS_500103);
        }
        
        return list;
    }
    
    /** 
     * TODO 查询单个楼层商品（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorGdsRSV#queryCmsFloorGds(com.zengshi.ecp.cms.dubbo.dto.CmsFloorGdsReqDTO) 
     */
    @Override
    public CmsFloorGdsRespDTO queryCmsFloorGds(CmsFloorGdsReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用查询楼层商品开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.根据条件检索楼层商品  */
        CmsFloorGdsRespDTO cmsFloorGdsRespDTO = new CmsFloorGdsRespDTO();
        try {
            cmsFloorGdsRespDTO = sv.queryCmsFloorGds(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "根据ID查询楼层商品出现异常！",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORGDS_500103);
        }
        
        return cmsFloorGdsRespDTO;
    }
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorGdsRSV#deleteCmsFloorGds(java.lang.Long) 
     */
    @Override
    public void deleteCmsFloorGds(String id) throws BusinessException {
        LogUtil.info(MODULE, "调用删除楼层商品开始，入参：{id="+id+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isBlank(id)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑删除楼层商品  */
        try{
            sv.deleteCmsFloorGds(id);
        } catch(Exception err){
            LogUtil.error(MODULE, "删除楼层商品失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORGDS_500101);
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorGdsRSV#deleteCmsFloorGdsBatch(java.util.List) 
     */
    @Override
    public void deleteCmsFloorGdsBatch(List<String> list) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除楼层商品开始，入参：{list="+list.toArray()+"}");
        /* 1.验证前店入参 */
        if(CollectionUtils.isEmpty(list)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="list";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑批量删除楼层商品  */
        try{
            sv.deleteCmsFloorGdsBatch(list);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量删除楼层商品失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORGDS_500104);
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorGdsRSV#changeStatusCmsFloorGds(java.lang.Long, java.lang.String) 
     */
    @Override
    public void changeStatusCmsFloorGds(String id,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除楼层商品开始，入参：{id="+id+",status="+status+"}");
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
        /* 2.更新楼层商品状态  */
        try{
            sv.changeStatusCmsFloorGds(id, status);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新楼层商品状态失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORGDS_500106);
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorGdsRSV#changeStatusCmsFloorGdsBatch(java.util.List, java.lang.String) 
     */
    @Override
    public void changeStatusCmsFloorGdsBatch(List<String> list,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除楼层商品开始，入参：{list="+list.toArray()+",status="+status+"}");
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
        
        /* 2.更新楼层商品状态  */
        try{
            sv.changeStatusCmsFloorGdsBatch(list,status);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量更新楼层商品状态失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORGDS_500107);
        }
    }

    /**
     * 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorGdsRSV#updateCmsFloorGdsForProm(com.zengshi.ecp.cms.dubbo.dto.CmsFloorGdsReqDTO)
     */
    @Override
    public int deleteCmsFloorGdsForProm(CmsFloorGdsReqDTO dto)
            throws BusinessException {
        if(dto == null ){
            dto = new CmsFloorGdsReqDTO();
        }
        LogUtil.info(MODULE, "调用更新楼层商品开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isEmpty(dto.getPromId())){
            LogUtil.error(MODULE, "入参PromId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="PromId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        //设为失效
        dto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        
        dto.setIsProm(CmsConstants.IsNot.CMS_ISNOT_1);
    
        /* 2.根据条件检索楼层商品  */
        int successCount = -1;
        try {
            successCount = sv.deleteCmsFloorGdsForProm(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "根据PromID删除楼层商品出现异常！",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORGDS_500103);
        }
        
        return successCount;
    }

}

