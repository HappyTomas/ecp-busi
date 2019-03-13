package com.zengshi.ecp.cms.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutPreReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutPreRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutPreRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutPreSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

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
public class CmsLayoutPreRSVImpl implements ICmsLayoutPreRSV{
    
    @Resource
    private ICmsLayoutPreSV sv;
    
    //定义个常量，用于表示模块名称，可以使用：当前类的类名
    private static final String MODULE = CmsLayoutPreRSVImpl.class.getName();


    /** 
     * TODO 新增布局项. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutPreRSV#saveCmsLayoutPre(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutPreReqDTO) 
     */
    @Override
    public CmsLayoutPreRespDTO addCmsLayoutPre(CmsLayoutPreReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用新增布局项开始，入参：{dto="+dto.toString()+"}");
        CmsLayoutPreRespDTO respDTO = new CmsLayoutPreRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getPageId())){
            LogUtil.error(MODULE, "入参PageId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="PageId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{   
            respDTO = sv.addCmsLayoutPre(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "新增布局项失败:",err);
            throw new BusinessException("CMS.LAYOUTPRE.500101");
        }
        
        return respDTO;
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutPreRSV#updateCmsLayoutPre(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutPreReqDTO) 
     */
    @Override
    public CmsLayoutPreRespDTO updateCmsLayoutPre(CmsLayoutPreReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用更新布局项开始，入参：{dto="+dto.toString()+"}");
        CmsLayoutPreRespDTO respDTO = new CmsLayoutPreRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getStatus())){
            LogUtil.error(MODULE, "入参Status为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="Status";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{    
            respDTO = sv.updateCmsLayoutPre(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新布局项失败！",err);
            throw new BusinessException("CMS.LAYOUTPRE.500102");
        }
        
        return respDTO;
    }
    
    /** 
     * TODO 查询布局项，分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutPreRSV#queryCmsLayoutPrePage(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutPreReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsLayoutPreRespDTO> queryCmsLayoutPrePage(CmsLayoutPreReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用分页查询布局项开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索布局项  */
        PageResponseDTO<CmsLayoutPreRespDTO> pageInfo = PageResponseDTO.buildByBaseInfo(dto, CmsLayoutPreRespDTO.class);
        try{
            pageInfo =  sv.queryCmsLayoutPrePage(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "查询布局项失败！",err);
            throw new BusinessException("CMS.LAYOUTPRE.500103");
        }
        return pageInfo;
    }
    
    /** 
     * TODO 查询布局项  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public List<CmsLayoutPreRespDTO> queryCmsLayoutPreList(CmsLayoutPreReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用查询布局项开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索布局项  */
        List<CmsLayoutPreRespDTO> list = new ArrayList<CmsLayoutPreRespDTO>();
        try {
            list = sv.queryCmsLayoutPreList(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询布局项出现异常！",e);
            throw new BusinessException("CMS.LAYOUTPRE.500103");
        }
        
        return list;
    }
    
    /** 
     * TODO 查询单个布局项（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutPreRSV#queryCmsLayoutPre(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutPreReqDTO) 
     */
    @Override
    public CmsLayoutPreRespDTO queryCmsLayoutPre(CmsLayoutPreReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用查询布局项开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.根据条件检索布局项  */
        CmsLayoutPreRespDTO cmsLayoutPreRespDTO = new CmsLayoutPreRespDTO();
        try {
            cmsLayoutPreRespDTO = sv.queryCmsLayoutPre(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "根据ID查询布局项出现异常！",e);
            throw new BusinessException("CMS.LAYOUTPRE.500103");
        }
        
        return cmsLayoutPreRespDTO;
    }
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutPreRSV#deleteCmsLayoutPre(java.lang.Long) 
     */
    @Override
    public void deleteCmsLayoutPre(String id) throws BusinessException {
        LogUtil.info(MODULE, "调用删除布局项开始，入参：{id="+id+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isBlank(id)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑删除布局项  */
        try{
            sv.deleteCmsLayoutPre(id);
        } catch(Exception err){
            LogUtil.error(MODULE, "删除布局项失败！",err);
            throw new BusinessException("CMS.LAYOUTPRE.500101");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutPreRSV#deleteCmsLayoutPreBatch(java.util.List) 
     */
    @Override
    public void deleteCmsLayoutPreBatch(List<String> list) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除布局项开始，入参：{list="+list.toArray()+"}");
        /* 1.验证前店入参 */
        if(CollectionUtils.isEmpty(list)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="list";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑批量删除布局项  */
        try{
            sv.deleteCmsLayoutPreBatch(list);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量删除布局项失败！",err);
            throw new BusinessException("CMS.LAYOUTPRE.500104");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutPreRSV#changeStatusCmsLayoutPre(java.lang.Long, java.lang.String) 
     */
    @Override
    public void changeStatusCmsLayoutPre(String id,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除布局项开始，入参：{id="+id+",status="+status+"}");
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
        /* 2.更新布局项状态  */
        try{
            sv.changeStatusCmsLayoutPre(id, status);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新布局项状态失败！",err);
            throw new BusinessException("CMS.LAYOUTPRE.500106");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutPreRSV#changeStatusCmsLayoutPreBatch(java.util.List, java.lang.String) 
     */
    @Override
    public void changeStatusCmsLayoutPreBatch(List<String> list,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除布局项开始，入参：{list="+list.toArray()+",status="+status+"}");
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
        
        /* 2.更新布局项状态  */
        try{
            sv.changeStatusCmsLayoutPreBatch(list,status);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量更新布局项状态失败！",err);
            throw new BusinessException("CMS.LAYOUTPRE.500107");
        }
    }
    
    /** 
     * updateCmsLayoutPreBatch. <br/> 
     * 批量更新布局(删除布局、对布局重新排序)
     * 当状态为2时：
     * TODO(1、更新布局).<br/> 
     * TODO(2、根据布局更新布局属性).<br/> 
     * TODO(3、根据布局更新布局项).<br/> 
     * TODO(4、根据布局更新布局项与属性关系).<br/> 
     * TODO(5、根据布局项与属性关系ID热点表).<br/>
     * 当状态为0时：
     * TODO(1、更新布局，更新排序。).<br/> 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutPreRSV#updateCmsLayoutPreBatch(java.util.List) 
     */
    @Override
    public boolean updateCmsLayoutPreBatch(List<CmsLayoutPreReqDTO> list) throws BusinessException {
        LogUtil.info(MODULE, "调用删除更新布局开始，入参：");
        boolean flag = true;
        /* 1.验证前店入参 */
        if(CollectionUtils.isEmpty(list)){
            flag = false;
            LogUtil.error(MODULE, "入参list为空！");
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,new String[] {"list"}); 
        }
        
        /* 2.根据条件检索基础模块  */
        try {
            sv.updateCmsLayoutPreBatch(list);
        } catch (Exception e) {
            flag = false;
            LogUtil.error(MODULE, "删除布局出现异常！",e);
            throw new BusinessException("CMS.PAGECONFIG.500108");
        }
        
        return flag;
    }

}

