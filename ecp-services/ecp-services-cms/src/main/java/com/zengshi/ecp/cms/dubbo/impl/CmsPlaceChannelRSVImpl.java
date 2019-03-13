package com.zengshi.ecp.cms.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceChannelReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceChannelRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceChannelRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsPlaceChannelSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

public class CmsPlaceChannelRSVImpl implements ICmsPlaceChannelRSV {

    @Resource
    private ICmsPlaceChannelSV sv;
    
    
  //定义个常量，用于表示模块名称，可以使用：当前类的类名
    private static final String MODULE = CmsPlaceChannelRSVImpl.class.getName();


    /** 
     * TODO 新增内容位置与栏目关系. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceChannelRSV#saveCmsPlaceChannel(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceChannelReqDTO) 
     */
    @Override
    public CmsPlaceChannelRespDTO addCmsPlaceChannel(CmsPlaceChannelReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用新增内容位置与栏目关系开始，入参：{dto="+dto.toString()+"}");
        CmsPlaceChannelRespDTO respDTO = new CmsPlaceChannelRespDTO();
        /*1.验证前店入参*/
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
        if(StringUtil.isEmpty(dto.getChannelId())){
            LogUtil.error(MODULE, "入参channelId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="channelId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{   
            respDTO = sv.addCmsPlaceChannel(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "新增内容位置与栏目关系失败:",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_PLACECHANNEL_500101);
        }
        
        return respDTO;
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceChannelRSV#updateCmsPlaceChannel(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceChannelReqDTO) 
     */
    @Override
    public CmsPlaceChannelRespDTO updateCmsPlaceChannel(CmsPlaceChannelReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用更新内容位置与栏目关系开始，入参：{dto="+dto.toString()+"}");
        CmsPlaceChannelRespDTO respDTO = new CmsPlaceChannelRespDTO();
        /*1.验证前店入参*/
        if(dto.getId() == null){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
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
        if(StringUtil.isEmpty(dto.getChannelId())){
            LogUtil.error(MODULE, "入参channelId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="channelId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{    
            respDTO = sv.updateCmsPlaceChannel(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新内容位置与栏目关系失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_PLACECHANNEL_500102);
        }
        
        return respDTO;
    }
    
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceChannelRSV#changeStatusCmsPlaceChannel(java.lang.Long, java.lang.String) 
     */
    @Override
    public void changeStatusCmsPlaceChannel(String id,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除内容位置与栏目树开始，入参：{id="+id+",status="+status+"}");
        /* 1.验证前店入参 */
        if(StringUtils.isBlank(id)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = {"id"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtils.isBlank(status)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = {"status"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        /* 2.更新内容位置与栏目树状态  */
        try{
            sv.changeStatusCmsPlaceChannel(id, status);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新内容位置与栏目树状态失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_PLACECHANNEL_500106);
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceChannelRSV#changeStatusCmsPlaceChannelBatch(java.util.List, java.lang.String) 
     */
    @Override
    public void changeStatusCmsPlaceChannelBatch(List<String> list,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除内容位置与栏目树开始，入参：{list="+list.toArray()+",status="+status+"}");
        /* 1.验证前店入参 */
        if(CollectionUtils.isEmpty(list)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = {"list"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtils.isBlank(status)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = {"status"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.更新内容位置与栏目树状态  */
        try{
            sv.changeStatusCmsPlaceChannelBatch(list,status);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量更新内容位置与栏目树状态失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_PLACECHANNEL_500107);
        }
    }
    
    
    /** 
     * TODO 查询内容位置与栏目关系，分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceChannelRSV#queryCmsPlaceChannelPage(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceChannelReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsPlaceChannelRespDTO> queryCmsPlaceChannelPage(CmsPlaceChannelReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用分页查询内容位置与栏目关系开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索内容位置与栏目关系  */
        PageResponseDTO<CmsPlaceChannelRespDTO> pageInfo = PageResponseDTO.buildByBaseInfo(dto, CmsPlaceChannelRespDTO.class);
        try{
            pageInfo =  sv.queryCmsPlaceChannelPage(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "查询内容位置与栏目关系失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_PLACECHANNEL_500103);
        }
        return pageInfo;
    }
    
    /** 
     * TODO 查询内容位置与栏目关系  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public List<CmsPlaceChannelRespDTO> queryCmsPlaceChannelList(CmsPlaceChannelReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用查询内容位置与栏目关系开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索内容位置与栏目关系  */
        List<CmsPlaceChannelRespDTO> list = new ArrayList<CmsPlaceChannelRespDTO>();
        try {
            list = sv.queryCmsPlaceChannelList(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询内容位置与栏目关系出现异常！",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_PLACECHANNEL_500103);
        }
        
        return list;
    }
    
    /** 
     * TODO 查询单个内容位置与栏目关系（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceChannelRSV#queryCmsPlaceChannel(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceChannelReqDTO) 
     */
    @Override
    public CmsPlaceChannelRespDTO queryCmsPlaceChannel(CmsPlaceChannelReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用查询内容位置与栏目关系开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        if(dto.getId() == null){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.根据条件检索内容位置与栏目关系  */
        CmsPlaceChannelRespDTO cmsPlaceChannelRespDTO = new CmsPlaceChannelRespDTO();
        try {
            cmsPlaceChannelRespDTO = sv.queryCmsPlaceChannel(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "根据ID查询内容位置与栏目关系出现异常！",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_PLACECHANNEL_500103);
        }
        
        return cmsPlaceChannelRespDTO;
    }
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceChannelRSV#deleteCmsPlaceChannel(java.lang.Long) 
     */
    @Override
    public void deleteCmsPlaceChannel(String id) throws BusinessException {
        LogUtil.info(MODULE, "调用删除内容位置与栏目关系开始，入参：{id="+id+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isBlank(id)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑删除内容位置与栏目关系  */
        try{
            sv.deleteCmsPlaceChannel(id);
        } catch(Exception err){
            LogUtil.error(MODULE, "删除内容位置与栏目关系失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_PLACECHANNEL_500101);
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceChannelRSV#deleteCmsPlaceChannelBatch(java.util.List) 
     */
    @Override
    public void deleteCmsPlaceChannelBatch(List<String> list) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除内容位置与栏目关系开始，入参：{list="+list.toArray()+"}");
        /* 1.验证前店入参 */
        if(CollectionUtils.isEmpty(list)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="list";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑批量删除内容位置与栏目关系  */
        try{
            sv.deleteCmsPlaceChannelBatch(list);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量删除内容位置与栏目关系失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_PLACECHANNEL_500104);
        }
    }

    
}

