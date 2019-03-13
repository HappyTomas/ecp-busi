package com.zengshi.ecp.cms.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.zengshi.ecp.cms.dubbo.dto.CmsSiteInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteInfoRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsSiteInfoRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsSiteInfoSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

public class CmsSiteInfoRSVImpl implements ICmsSiteInfoRSV{

	 
    @Resource
    private ICmsSiteInfoSV sv;
    
    //定义个常量，用于表示模块名称，可以使用：当前类的类名
    private static final String MODULE = CmsSiteInfoRSVImpl.class.getName();

    /** 
     * TODO 新增网站信息. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsSiteInfoRSV#saveCmsSiteInfo(com.zengshi.ecp.cms.dubbo.dto.CmsSiteInfoReqDTO) 
     */
    @Override   
    public CmsSiteInfoRespDTO addCmsSiteInfo(CmsSiteInfoReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用新增网站信息开始，入参：{dto="+dto.toString()+"}");
        CmsSiteInfoRespDTO respDTO = new CmsSiteInfoRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isBlank(dto.getSiteInfoName())){
            LogUtil.error(MODULE, "入参SiteInfoName为空！");
            String[] keyInfos = {"SiteInfoName"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getSiteId())){
        	LogUtil.error(MODULE, "入参sietId为空！");
            String[] keyInfos = {"siteId"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        /*if(StringUtil.isBlank(dto.getIsrecolumn())){
        	LogUtil.error(MODULE, "入参isrecolum为空！");
            String[] keyInfos = {"isrecolum"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isBlank(dto.getStaticId())){
        	LogUtil.error(MODULE, "入参staticId为空！");
            String[] keyInfos = {"staticId"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }*/
        /*2.调service层接口*/
        try{   
            respDTO = sv.addCmsSiteInfo(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "新增网站信息失败:",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_SITEINFO_500101);
        }
        return respDTO;
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsSiteInfoRSV#updateCmsSiteInfo(com.zengshi.ecp.cms.dubbo.dto.CmsSiteInfoReqDTO) 
     */
    @Override
    public CmsSiteInfoRespDTO updateCmsSiteInfo(CmsSiteInfoReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用更新网站信息开始，入参：{dto="+dto.toString()+"}");
        CmsSiteInfoRespDTO respDTO = new CmsSiteInfoRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = {"id"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try{    
            respDTO = sv.updateCmsSiteInfo(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新网站信息失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_SITEINFO_500102);
        }
        return respDTO;
    }
    
    /** 
     * TODO 查询网站信息，分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsSiteInfoRSV#queryCmsSiteInfoPage(com.zengshi.ecp.cms.dubbo.dto.CmsSiteInfoReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsSiteInfoRespDTO> queryCmsSiteInfoPage(CmsSiteInfoReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用分页查询网站信息开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索网站信息  */
        PageResponseDTO<CmsSiteInfoRespDTO> pageInfo = PageResponseDTO.buildByBaseInfo(dto, CmsSiteInfoRespDTO.class);
        try{
            pageInfo =  sv.queryCmsSiteInfoPage(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "查询网站信息失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_SITEINFO_500103);
        }
        return pageInfo;
    }
    
    /** 
     * TODO 查询单个网站信息（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsSiteInfoRSV#queryCmsSiteInfo(com.zengshi.ecp.cms.dubbo.dto.CmsSiteInfoReqDTO) 
     */
    @Override
    public CmsSiteInfoRespDTO queryCmsSiteInfo(CmsSiteInfoReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用查询网站信息开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = {"id"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.根据条件检索网站信息  */
        CmsSiteInfoRespDTO cmsSiteInfoRespDTO = new CmsSiteInfoRespDTO();
        try {
            cmsSiteInfoRespDTO = sv.queryCmsSiteInfo(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "根据ID查询网站信息出现异常！",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_SITEINFO_500103);
        }
        return cmsSiteInfoRespDTO;
    }
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsSiteInfoRSV#deleteCmsSiteInfo(java.lang.Long) 
     */
    @Override
    public void deleteCmsSiteInfo(String id) throws BusinessException {
        LogUtil.info(MODULE, "调用删除网站信息开始，入参：{id="+id+"}");
        /* 1.验证前店入参 */
        if(StringUtils.isBlank(id)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = {"id"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑删除网站信息  */
        try{
            sv.deleteCmsSiteInfo(id);
        } catch(Exception err){
            LogUtil.error(MODULE, "删除网站信息失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_SITEINFO_500104);
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsSiteInfoRSV#deleteCmsSiteInfoBatch(java.util.List) 
     */
    @Override
    public void deleteCmsSiteInfoBatch(List<String> list) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除网站信息开始，入参：{list="+list.toArray()+"}");
        /* 1.验证前店入参 */
        if(CollectionUtils.isEmpty(list)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = {"list"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑批量删除网站信息  */
        try{
            sv.deleteCmsSiteInfoBatch(list);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量删除网站信息失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_SITEINFO_500104);
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsSiteInfoRSV#changeStatusCmsSiteInfo(java.lang.Long, java.lang.String) 
     */
    @Override
    public void changeStatusCmsSiteInfo(String id,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除网站信息开始，入参：{id="+id+",status="+status+"}");
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
        /* 2.更新网站信息状态  */
        try{
            sv.changeStatusCmsSiteInfo(id, status);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新网站信息状态失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_SITEINFO_500106);
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsSiteInfoRSV#changeStatusCmsSiteInfoBatch(java.util.List, java.lang.String) 
     */
    @Override
    public void changeStatusCmsSiteInfoBatch(List<String> list,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除网站信息开始，入参：{list="+list.toArray()+",status="+status+"}");
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
        
        /* 2.更新网站信息状态  */
        try{
            sv.changeStatusCmsSiteInfoBatch(list,status);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量更新网站信息状态失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_SITEINFO_500107);
        }
    }
    
    /** 
     * TODO 查询网站信息  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public List<CmsSiteInfoRespDTO> queryCmsSiteInfoList(CmsSiteInfoReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用查询网站信息开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索楼层网站信息  */
        List<CmsSiteInfoRespDTO> list = new ArrayList<CmsSiteInfoRespDTO>();
        try {
            list = sv.queryCmsSiteInfoList(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询网站信息出现异常！",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_SITEINFO_500103);
        }
        
        return list;
    }

    @Override
    public List<CmsSiteInfoRespDTO> queryTopSiteInfoBySiteId(Long siteId) throws BusinessException {
        if(StringUtil.isEmpty(siteId)){
            LogUtil.error(MODULE, "入参siteId为空！");
            String[] keyInfos = {"siteId"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        return sv.queryTopSiteInfoBySiteId(siteId);
    }
}
