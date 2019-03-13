package com.zengshi.ecp.cms.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.zengshi.ecp.cms.dubbo.dto.CmsLinkReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLinkRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsLinkRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsLinkSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

public class CmsLinkRSVImpl implements ICmsLinkRSV{

	 
    @Resource
    private ICmsLinkSV sv;
    
    //定义个常量，用于表示模块名称，可以使用：当前类的类名
    private static final String MODULE = CmsLinkRSVImpl.class.getName();

    /** 
     * TODO 新增链接. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLinkRSV#saveCmsLink(com.zengshi.ecp.cms.dubbo.dto.CmsLinkReqDTO) 
     */
    @Override   
    public CmsLinkRespDTO addCmsLink(CmsLinkReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用新增链接开始，入参：{dto="+dto.toString()+"}");
        CmsLinkRespDTO respDTO = new CmsLinkRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isBlank(dto.getLinkName())){
            LogUtil.error(MODULE, "入参LinkName为空！");
            String[] keyInfos = {"LinkName"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getLinkUrl())){
        	LogUtil.error(MODULE, "入参linkUrl为空！");
            String[] keyInfos = {"linkUrl"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getLinkType())){
        	LogUtil.error(MODULE, "入参linkType为空！");
            String[] keyInfos = {"linkType"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getSiteId())){
        	LogUtil.error(MODULE, "入参sietId为空！");
            String[] keyInfos = {"siteId"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        /*2.调service层接口*/
        try{   
            respDTO = sv.addCmsLink(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "新增链接失败:",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_LINK_500101);
        }
        return respDTO;
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLinkRSV#updateCmsLink(com.zengshi.ecp.cms.dubbo.dto.CmsLinkReqDTO) 
     */
    @Override
    public CmsLinkRespDTO updateCmsLink(CmsLinkReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用更新链接开始，入参：{dto="+dto.toString()+"}");
        CmsLinkRespDTO respDTO = new CmsLinkRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = {"id"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isBlank(dto.getLinkName())){
            LogUtil.error(MODULE, "入参LinkName为空！");
            String[] keyInfos = {"LinkName"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getLinkUrl())){
        	LogUtil.error(MODULE, "入参linkUrl为空！");
            String[] keyInfos = {"linkUrl"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getLinkType())){
        	LogUtil.error(MODULE, "入参linkType为空！");
            String[] keyInfos = {"linkType"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getSiteId())){
        	LogUtil.error(MODULE, "入参sietId为空！");
            String[] keyInfos = {"siteId"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        /*2.调service层接口*/
        try{    
            respDTO = sv.updateCmsLink(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新链接失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_LINK_500102);
        }
        return respDTO;
    }
    
    /** 
     * TODO 查询链接，分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLinkRSV#queryCmsLinkPage(com.zengshi.ecp.cms.dubbo.dto.CmsLinkReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsLinkRespDTO> queryCmsLinkPage(CmsLinkReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用分页查询链接开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索链接  */
        PageResponseDTO<CmsLinkRespDTO> pageInfo = PageResponseDTO.buildByBaseInfo(dto, CmsLinkRespDTO.class);
        try{
            pageInfo =  sv.queryCmsLinkPage(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "查询链接失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_LINK_500103);
        }
        return pageInfo;
    }
    
    /** 
     * TODO 查询单个链接（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLinkRSV#queryCmsLink(com.zengshi.ecp.cms.dubbo.dto.CmsLinkReqDTO) 
     */
    @Override
    public CmsLinkRespDTO queryCmsLink(CmsLinkReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用查询链接开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = {"id"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.根据条件检索链接  */
        CmsLinkRespDTO cmsLinkRespDTO = new CmsLinkRespDTO();
        try {
            cmsLinkRespDTO = sv.queryCmsLink(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "根据ID查询链接出现异常！",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_LINK_500103);
        }
        return cmsLinkRespDTO;
    }
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLinkRSV#deleteCmsLink(java.lang.Long) 
     */
    @Override
    public void deleteCmsLink(String id) throws BusinessException {
        LogUtil.info(MODULE, "调用删除链接开始，入参：{id="+id+"}");
        /* 1.验证前店入参 */
        if(StringUtils.isBlank(id)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = {"id"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑删除链接  */
        sv.deleteCmsLink(id);
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLinkRSV#deleteCmsLinkBatch(java.util.List) 
     */
    @Override
    public void deleteCmsLinkBatch(List<String> list) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除链接开始，入参：{list="+list.toArray()+"}");
        /* 1.验证前店入参 */
        if(CollectionUtils.isEmpty(list)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = {"list"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑批量删除链接  */
        try{
            sv.deleteCmsLinkBatch(list);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量删除链接失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_LINK_500104);
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLinkRSV#changeStatusCmsLink(java.lang.Long, java.lang.String) 
     */
    @Override
    public void changeStatusCmsLink(String id,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除链接开始，入参：{id="+id+",status="+status+"}");
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
        /* 2.更新链接状态  */
        try{
            sv.changeStatusCmsLink(id, status);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新链接状态失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_LINK_500106);
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsLinkRSV#changeStatusCmsLinkBatch(java.util.List, java.lang.String) 
     */
    @Override
    public void changeStatusCmsLinkBatch(List<String> list,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除链接开始，入参：{list="+list.toArray()+",status="+status+"}");
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
        
        /* 2.更新链接状态  */
        try{
            sv.changeStatusCmsLinkBatch(list,status);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量更新链接状态失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_LINK_500107);
        }
    }
    
    /** 
     * TODO 查询链接  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public List<CmsLinkRespDTO> queryCmsLinkList(CmsLinkReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用查询链接开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索楼层链接  */
        List<CmsLinkRespDTO> list = new ArrayList<CmsLinkRespDTO>();
        try {
            list = sv.queryCmsLinkList(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询链接出现异常！",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_LINK_500103);
        }
        
        return list;
    }
}
