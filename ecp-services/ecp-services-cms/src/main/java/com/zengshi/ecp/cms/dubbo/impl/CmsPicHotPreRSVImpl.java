package com.zengshi.ecp.cms.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.cms.dubbo.dto.CmsPicHotPreReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPicHotPreRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPicHotPreRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsPicHotPreSV;
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
public class CmsPicHotPreRSVImpl implements ICmsPicHotPreRSV{
    
    @Resource
    private ICmsPicHotPreSV sv;
    
    //定义个常量，用于表示模块名称，可以使用：当前类的类名
    private static final String MODULE = CmsPicHotPreRSVImpl.class.getName();


    /** 
     * TODO 新增图片热点. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPicHotPreRSV#saveCmsPicHotPre(com.zengshi.ecp.cms.dubbo.dto.CmsPicHotPreReqDTO) 
     */
    @Override
    public CmsPicHotPreRespDTO addCmsPicHotPre(CmsPicHotPreReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用新增图片热点开始，入参：{dto="+dto.toString()+"}");
        CmsPicHotPreRespDTO respDTO = new CmsPicHotPreRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getItemPropId())){
            LogUtil.error(MODULE, "入参ItemPropId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="ItemPropId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getRelativeCoord())){
            LogUtil.error(MODULE, "入参RelativeCoord为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="RelativeCoord";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{   
            respDTO = sv.addCmsPicHotPre(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "新增图片热点失败:",err);
            throw new BusinessException("CMS.PICTHOTPRE.500101");
        }
        
        return respDTO;
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPicHotPreRSV#updateCmsPicHotPre(com.zengshi.ecp.cms.dubbo.dto.CmsPicHotPreReqDTO) 
     */
    @Override
    public CmsPicHotPreRespDTO updateCmsPicHotPre(CmsPicHotPreReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用更新图片热点开始，入参：{dto="+dto.toString()+"}");
        CmsPicHotPreRespDTO respDTO = new CmsPicHotPreRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{    
            respDTO = sv.updateCmsPicHotPre(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新图片热点失败！",err);
            throw new BusinessException("CMS.PICTHOTPRE.500102");
        }
        
        return respDTO;
    }
    
    /** 
     * TODO 查询图片热点，分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPicHotPreRSV#queryCmsPicHotPrePage(com.zengshi.ecp.cms.dubbo.dto.CmsPicHotPreReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsPicHotPreRespDTO> queryCmsPicHotPrePage(CmsPicHotPreReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用分页查询图片热点开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索图片热点  */
        PageResponseDTO<CmsPicHotPreRespDTO> pageInfo = PageResponseDTO.buildByBaseInfo(dto, CmsPicHotPreRespDTO.class);
        try{
            pageInfo =  sv.queryCmsPicHotPrePage(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "查询图片热点失败！",err);
            throw new BusinessException("CMS.PICTHOTPRE.500103");
        }
        return pageInfo;
    }
    
    /** 
     * TODO 查询图片热点  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public List<CmsPicHotPreRespDTO> queryCmsPicHotPreList(CmsPicHotPreReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用查询图片热点开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索图片热点  */
        List<CmsPicHotPreRespDTO> list = new ArrayList<CmsPicHotPreRespDTO>();
        try {
            list = sv.queryCmsPicHotPreList(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询图片热点出现异常！",e);
            throw new BusinessException("CMS.PICTHOTPRE.500103");
        }
        
        return list;
    }
    
    /** 
     * TODO 查询单个图片热点（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPicHotPreRSV#queryCmsPicHotPre(com.zengshi.ecp.cms.dubbo.dto.CmsPicHotPreReqDTO) 
     */
    @Override
    public CmsPicHotPreRespDTO queryCmsPicHotPre(CmsPicHotPreReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用查询图片热点开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.根据条件检索图片热点  */
        CmsPicHotPreRespDTO cmsPicHotPreRespDTO = new CmsPicHotPreRespDTO();
        try {
            cmsPicHotPreRespDTO = sv.queryCmsPicHotPre(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "根据ID查询图片热点出现异常！",e);
            throw new BusinessException("CMS.PICTHOTPRE.500103");
        }
        
        return cmsPicHotPreRespDTO;
    }
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPicHotPreRSV#deleteCmsPicHotPre(java.lang.Long) 
     */
    @Override
    public void deleteCmsPicHotPre(String id) throws BusinessException {
        LogUtil.info(MODULE, "调用删除图片热点开始，入参：{id="+id+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isBlank(id)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑删除图片热点  */
        try{
            sv.deleteCmsPicHotPre(id);
        } catch(Exception err){
            LogUtil.error(MODULE, "删除图片热点失败！",err);
            throw new BusinessException("CMS.PICTHOTPRE.500101");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPicHotPreRSV#deleteCmsPicHotPreBatch(java.util.List) 
     */
    @Override
    public void deleteCmsPicHotPreBatch(List<String> list) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除图片热点开始，入参：{list="+list.toArray()+"}");
        /* 1.验证前店入参 */
        if(CollectionUtils.isEmpty(list)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="list";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑批量删除图片热点  */
        try{
            sv.deleteCmsPicHotPreBatch(list);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量删除图片热点失败！",err);
            throw new BusinessException("CMS.PICTHOTPRE.500104");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPicHotPreRSV#changeStatusCmsPicHotPre(java.lang.Long, java.lang.String) 
     */
    @Override
    public void changeStatusCmsPicHotPre(String id,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除图片热点开始，入参：{id="+id+",status="+status+"}");
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
        /* 2.更新图片热点状态  */
        try{
            sv.changeStatusCmsPicHotPre(id, status);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新图片热点状态失败！",err);
            throw new BusinessException("CMS.PICTHOTPRE.500106");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPicHotPreRSV#changeStatusCmsPicHotPreBatch(java.util.List, java.lang.String) 
     */
    @Override
    public void changeStatusCmsPicHotPreBatch(List<String> list,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除图片热点开始，入参：{list="+list.toArray()+",status="+status+"}");
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
        
        /* 2.更新图片热点状态  */
        try{
            sv.changeStatusCmsPicHotPreBatch(list,status);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量更新图片热点状态失败！",err);
            throw new BusinessException("CMS.PICTHOTPRE.500107");
        }
    }
    
    /** 
     * TODO 查询图片热点  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    /*@Override
    public List<CmsPicHotPreRespDTO> queryCmsDefaultPicHotPreList() throws BusinessException {
        LogUtil.info(MODULE, "调用查询图片热点开始，入参：{}");
         1.验证前店入参 
        
         2.根据条件检索图片热点  
        List<CmsPicHotPreRespDTO> list = new ArrayList<CmsPicHotPreRespDTO>();
        try {
            //2.1缓存查询
            Map<Long ,CmsPicHotPreRespDTO> map = (HashMap<Long ,CmsPicHotPreRespDTO> )CacheUtil.getItem(CmsConstants.ParamConfig.CMS.PICTHOTPRE.CACHE);
            if(CollectionUtils.isEmpty(map)){
                for(CmsPicHotPreRespDTO respDTO:map.values()){
                    if(respDTO != null && CmsConstants.IsNot.CMS_ISNOT_1.equals(respDTO.getIsdefault())){
                        list.add(respDTO);
                    }
                }
            }else{//2.2 当缓存中没有时，取表
                CmsPicHotPreReqDTO dto = new CmsPicHotPreReqDTO();
                dto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                dto.setIsdefault(CmsConstants.IsNot.CMS_ISNOT_1);
                list = sv.queryCmsPicHotPreList(dto);
            }
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询图片热点出现异常！",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS.PICTHOTPRE.500103);
        }
        
        return list;
    }*/

}

