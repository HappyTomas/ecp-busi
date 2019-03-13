package com.zengshi.ecp.cms.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.cms.dubbo.dto.CmsPicHotPubReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPicHotPubRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPicHotPubRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsPicHotPubSV;
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
public class CmsPicHotPubRSVImpl implements ICmsPicHotPubRSV{
    
    @Resource
    private ICmsPicHotPubSV sv;
    
    //定义个常量，用于表示模块名称，可以使用：当前类的类名
    private static final String MODULE = CmsPicHotPubRSVImpl.class.getName();


    /** 
     * TODO 新增图片热点. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPicHotPubRSV#saveCmsPicHotPub(com.zengshi.ecp.cms.dubbo.dto.CmsPicHotPubReqDTO) 
     */
    @Override
    public CmsPicHotPubRespDTO addCmsPicHotPub(CmsPicHotPubReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用新增图片热点开始，入参：{dto="+dto.toString()+"}");
        CmsPicHotPubRespDTO respDTO = new CmsPicHotPubRespDTO();
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
        if(StringUtil.isEmpty(dto.getHotId())){
            LogUtil.error(MODULE, "入参HotId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="HotId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        /*2.调service层接口*/
        try{   
            respDTO = sv.addCmsPicHotPub(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "新增图片热点失败:",err);
            throw new BusinessException("CMS.PICTHOTPUB.500101");
        }
        
        return respDTO;
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPicHotPubRSV#updateCmsPicHotPub(com.zengshi.ecp.cms.dubbo.dto.CmsPicHotPubReqDTO) 
     */
    @Override
    public CmsPicHotPubRespDTO updateCmsPicHotPub(CmsPicHotPubReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用更新图片热点开始，入参：{dto="+dto.toString()+"}");
        CmsPicHotPubRespDTO respDTO = new CmsPicHotPubRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{    
            respDTO = sv.updateCmsPicHotPub(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新图片热点失败！",err);
            throw new BusinessException("CMS.PICTHOTPUB.500102");
        }
        
        return respDTO;
    }
    
    /** 
     * TODO 查询图片热点，分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPicHotPubRSV#queryCmsPicHotPubPage(com.zengshi.ecp.cms.dubbo.dto.CmsPicHotPubReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsPicHotPubRespDTO> queryCmsPicHotPubPage(CmsPicHotPubReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用分页查询图片热点开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索图片热点  */
        PageResponseDTO<CmsPicHotPubRespDTO> pageInfo = PageResponseDTO.buildByBaseInfo(dto, CmsPicHotPubRespDTO.class);
        try{
            pageInfo =  sv.queryCmsPicHotPubPage(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "查询图片热点失败！",err);
            throw new BusinessException("CMS.PICTHOTPUB.500103");
        }
        return pageInfo;
    }
    
    /** 
     * TODO 查询图片热点  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public List<CmsPicHotPubRespDTO> queryCmsPicHotPubList(CmsPicHotPubReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用查询图片热点开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索图片热点  */
        List<CmsPicHotPubRespDTO> list = new ArrayList<CmsPicHotPubRespDTO>();
        try {
            list = sv.queryCmsPicHotPubList(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询图片热点出现异常！",e);
            throw new BusinessException("CMS.PICTHOTPUB.500103");
        }
        
        return list;
    }
    
    /** 
     * TODO 查询单个图片热点（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPicHotPubRSV#queryCmsPicHotPub(com.zengshi.ecp.cms.dubbo.dto.CmsPicHotPubReqDTO) 
     */
    @Override
    public CmsPicHotPubRespDTO queryCmsPicHotPub(CmsPicHotPubReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用查询图片热点开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.根据条件检索图片热点  */
        CmsPicHotPubRespDTO cmsPicHotPubRespDTO = new CmsPicHotPubRespDTO();
        try {
            cmsPicHotPubRespDTO = sv.queryCmsPicHotPub(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "根据ID查询图片热点出现异常！",e);
            throw new BusinessException("CMS.PICTHOTPUB.500103");
        }
        
        return cmsPicHotPubRespDTO;
    }
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPicHotPubRSV#deleteCmsPicHotPub(java.lang.Long) 
     */
    @Override
    public void deleteCmsPicHotPub(String id) throws BusinessException {
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
            sv.deleteCmsPicHotPub(id);
        } catch(Exception err){
            LogUtil.error(MODULE, "删除图片热点失败！",err);
            throw new BusinessException("CMS.PICTHOTPUB.500101");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPicHotPubRSV#deleteCmsPicHotPubBatch(java.util.List) 
     */
    @Override
    public void deleteCmsPicHotPubBatch(List<String> list) throws BusinessException {
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
            sv.deleteCmsPicHotPubBatch(list);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量删除图片热点失败！",err);
            throw new BusinessException("CMS.PICTHOTPUB.500104");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPicHotPubRSV#changeStatusCmsPicHotPub(java.lang.Long, java.lang.String) 
     */
    @Override
    public void changeStatusCmsPicHotPub(String id,String status) throws BusinessException {
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
            sv.changeStatusCmsPicHotPub(id, status);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新图片热点状态失败！",err);
            throw new BusinessException("CMS.PICTHOTPUB.500106");
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPicHotPubRSV#changeStatusCmsPicHotPubBatch(java.util.List, java.lang.String) 
     */
    @Override
    public void changeStatusCmsPicHotPubBatch(List<String> list,String status) throws BusinessException {
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
            sv.changeStatusCmsPicHotPubBatch(list,status);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量更新图片热点状态失败！",err);
            throw new BusinessException("CMS.PICTHOTPUB.500107");
        }
    }
    
    /** 
     * TODO 查询图片热点  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    /*@Override
    public List<CmsPicHotPubRespDTO> queryCmsDefaultPicHotPubList() throws BusinessException {
        LogUtil.info(MODULE, "调用查询图片热点开始，入参：{}");
         1.验证前店入参 
        
         2.根据条件检索图片热点  
        List<CmsPicHotPubRespDTO> list = new ArrayList<CmsPicHotPubRespDTO>();
        try {
            //2.1缓存查询
            Map<Long ,CmsPicHotPubRespDTO> map = (HashMap<Long ,CmsPicHotPubRespDTO> )CacheUtil.getItem(CmsConstants.ParamConfig.CMS.PICTHOTPUB.CACHE);
            if(CollectionUtils.isEmpty(map)){
                for(CmsPicHotPubRespDTO respDTO:map.values()){
                    if(respDTO != null && CmsConstants.IsNot.CMS_ISNOT_1.equals(respDTO.getIsdefault())){
                        list.add(respDTO);
                    }
                }
            }else{//2.2 当缓存中没有时，取表
                CmsPicHotPubReqDTO dto = new CmsPicHotPubReqDTO();
                dto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                dto.setIsdefault(CmsConstants.IsNot.CMS_ISNOT_1);
                list = sv.queryCmsPicHotPubList(dto);
            }
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询图片热点出现异常！",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS.PICTHOTPUB.500103);
        }
        
        return list;
    }*/

}

