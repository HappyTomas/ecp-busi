package com.zengshi.ecp.cms.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPreReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPreRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutItemPreRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutItemPreSV;
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
public class CmsLayoutItemPreRSVImpl implements ICmsLayoutItemPreRSV{
    
    @Resource
    private ICmsLayoutItemPreSV sv;
    
    //定义个常量，用于表示模块名称，可以使用：当前类的类名
    private static final String MODULE = CmsLayoutItemPreRSVImpl.class.getName();


    /** 
     * TODO 新增页面布局项. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutItemPreRSV#saveCmsLayoutItemPre(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPreReqDTO) 
     */
    @Override
    public CmsLayoutItemPreRespDTO addCmsLayoutItemPre(CmsLayoutItemPreReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用新增页面布局项开始，入参：{dto="+dto.toString()+"}");
        CmsLayoutItemPreRespDTO respDTO = new CmsLayoutItemPreRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getLayoutId())){
            LogUtil.error(MODULE, "入参LayoutId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="LayoutId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{   
            respDTO = sv.addCmsLayoutItemPre(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "新增页面布局项失败:",err);
            throw new BusinessException("CMS.LAYOUTITEMPRE.500101");
        }
        
        return respDTO;
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutItemPreRSV#updateCmsLayoutItemPre(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPreReqDTO) 
     */
    @Override
    public CmsLayoutItemPreRespDTO updateCmsLayoutItemPre(CmsLayoutItemPreReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用更新页面布局项开始，入参：{dto="+dto.toString()+"}");
        CmsLayoutItemPreRespDTO respDTO = new CmsLayoutItemPreRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        /*if(StringUtil.isEmpty(dto.getLayoutId())){
            LogUtil.error(MODULE, "入参LayoutId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="LayoutId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }*/
        
        /*2.调service层接口*/
        try{    
            respDTO = sv.updateCmsLayoutItemPre(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新页面布局项失败！",err);
            throw new BusinessException("CMS.LAYOUTITEMPRE.500102");
        }
        
        return respDTO;
    }
    /**
     * 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutItemPreRSV#updateCmsLayoutItemPreBatch(java.util.List)
     */
    @Override
    public boolean updateCmsLayoutItemPreBatch(List<CmsLayoutItemPreReqDTO> list)
            throws BusinessException {
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
            sv.updateCmsLayoutItemPreBatch(list);
        } catch (Exception e) {
            flag = false;
            LogUtil.error(MODULE, "删除布局出现异常！",e);
            throw new BusinessException("CMS.PAGECONFIG.500108");
        }
        
        return flag;
    }
    /**
     * 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutItemPreRSV#updateCmsLayoutItemPreSensitive(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPreReqDTO)
     */
    @Override
    public CmsLayoutItemPreRespDTO updateCmsLayoutItemPreSensitive(CmsLayoutItemPreReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用更新页面布局项开始（支持置空），入参：{dto="+dto.toString()+"}");
        CmsLayoutItemPreRespDTO respDTO = new CmsLayoutItemPreRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        /*if(StringUtil.isEmpty(dto.getLayoutId())){
            LogUtil.error(MODULE, "入参LayoutId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="LayoutId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }*/
        
        /*2.调service层接口*/
        try{    
            respDTO = sv.updateCmsLayoutItemPreSensitive(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新页面布局项（支持置空）失败！",err);
            throw new BusinessException("CMS.LAYOUTITEMPRE.500102");
        }
        
        return respDTO;
    }
    
    /** 
     * TODO 查询页面布局项，分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutItemPreRSV#queryCmsLayoutItemPrePage(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPreReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsLayoutItemPreRespDTO> queryCmsLayoutItemPrePage(CmsLayoutItemPreReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用分页查询页面布局项开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索页面布局项  */
        PageResponseDTO<CmsLayoutItemPreRespDTO> pageInfo = PageResponseDTO.buildByBaseInfo(dto, CmsLayoutItemPreRespDTO.class);
        try{
            pageInfo =  sv.queryCmsLayoutItemPrePage(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "查询页面布局项失败！",err);
            throw new BusinessException("CMS.LAYOUTITEMPRE.500103");
        }
        return pageInfo;
    }
    
    /** 
     * TODO 查询页面布局项  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public List<CmsLayoutItemPreRespDTO> queryCmsLayoutItemPreList(CmsLayoutItemPreReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用查询页面布局项开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索页面布局项  */
        List<CmsLayoutItemPreRespDTO> list = new ArrayList<CmsLayoutItemPreRespDTO>();
        try {
            list = sv.queryCmsLayoutItemPreList(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询页面布局项出现异常！",e);
            throw new BusinessException("CMS.LAYOUTITEMPRE.500103");
        }
        
        return list;
    }
    
    /** 
     * TODO 查询单个页面布局项（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutItemPreRSV#queryCmsLayoutItemPre(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPreReqDTO) 
     */
    @Override
    public CmsLayoutItemPreRespDTO queryCmsLayoutItemPre(CmsLayoutItemPreReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用查询页面布局项开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.根据条件检索页面布局项  */
        CmsLayoutItemPreRespDTO cmsLayoutItemPreRespDTO = new CmsLayoutItemPreRespDTO();
        try {
            cmsLayoutItemPreRespDTO = sv.queryCmsLayoutItemPre(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "根据ID查询页面布局项出现异常！",e);
            throw new BusinessException("CMS.LAYOUTITEMPRE.500103");
        }
        
        return cmsLayoutItemPreRespDTO;
    }
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutItemPreRSV#deleteCmsLayoutItemPre(java.lang.Long) 
     */
    @Override
    public void deleteCmsLayoutItemPre(String id) throws BusinessException {
        LogUtil.info(MODULE, "调用删除页面布局项开始，入参：{id="+id+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isBlank(id)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑删除页面布局项  */
        try{
            sv.deleteCmsLayoutItemPre(id);
        } catch(Exception err){
            LogUtil.error(MODULE, "删除页面布局项失败！",err);
            throw new BusinessException("CMS.LAYOUTITEMPRE.500101");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutItemPreRSV#deleteCmsLayoutItemPreBatch(java.util.List) 
     */
    @Override
    public void deleteCmsLayoutItemPreBatch(List<String> list) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除页面布局项开始，入参：{list="+list.toArray()+"}");
        /* 1.验证前店入参 */
        if(CollectionUtils.isEmpty(list)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="list";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑批量删除页面布局项  */
        try{
            sv.deleteCmsLayoutItemPreBatch(list);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量删除页面布局项失败！",err);
            throw new BusinessException("CMS.LAYOUTITEMPRE.500104");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutItemPreRSV#changeStatusCmsLayoutItemPre(java.lang.Long, java.lang.String) 
     */
    @Override
    public void changeStatusCmsLayoutItemPre(String id,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除页面布局项开始，入参：{id="+id+",status="+status+"}");
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
        /* 2.更新页面布局项状态  */
        try{
            sv.changeStatusCmsLayoutItemPre(id, status);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新页面布局项状态失败！",err);
            throw new BusinessException("CMS.LAYOUTITEMPRE.500106");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutItemPreRSV#changeStatusCmsLayoutItemPreBatch(java.util.List, java.lang.String) 
     */
    @Override
    public void changeStatusCmsLayoutItemPreBatch(List<String> list,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除页面布局项开始，入参：{list="+list.toArray()+",status="+status+"}");
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
        
        /* 2.更新页面布局项状态  */
        try{
            sv.changeStatusCmsLayoutItemPreBatch(list,status);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量更新页面布局项状态失败！",err);
            throw new BusinessException("CMS.LAYOUTITEMPRE.500107");
        }
    }


    /** 
     * TODO 查询页面布局项  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    /*@Override
    public List<CmsLayoutItemPreRespDTO> queryCmsDefaultLayoutItemPreList() throws BusinessException {
        LogUtil.info(MODULE, "调用查询页面布局项开始，入参：{}");
         1.验证前店入参 
        
         2.根据条件检索页面布局项  
        List<CmsLayoutItemPreRespDTO> list = new ArrayList<CmsLayoutItemPreRespDTO>();
        try {
            //2.1缓存查询
            Map<Long ,CmsLayoutItemPreRespDTO> map = (HashMap<Long ,CmsLayoutItemPreRespDTO> )CacheUtil.getItem(CmsConstants.ParamConfig.CMS.LAYOUTITEMPRE.CACHE);
            if(CollectionUtils.isEmpty(map)){
                for(CmsLayoutItemPreRespDTO respDTO:map.values()){
                    if(respDTO != null && CmsConstants.IsNot.CMS_ISNOT_1.equals(respDTO.getIsdefault())){
                        list.add(respDTO);
                    }
                }
            }else{//2.2 当缓存中没有时，取表
                CmsLayoutItemPreReqDTO dto = new CmsLayoutItemPreReqDTO();
                dto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                dto.setIsdefault(CmsConstants.IsNot.CMS_ISNOT_1);
                list = sv.queryCmsLayoutItemPreList(dto);
            }
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询页面布局项出现异常！",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS.LAYOUTITEMPRE.500103);
        }
        
        return list;
    }*/

}

