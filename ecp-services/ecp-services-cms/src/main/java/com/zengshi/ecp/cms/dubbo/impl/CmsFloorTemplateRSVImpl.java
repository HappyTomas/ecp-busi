package com.zengshi.ecp.cms.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.zengshi.ecp.cms.dubbo.dto.CmsFloorTemplateReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorTemplateRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorTemplateRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorTemplateSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

public class CmsFloorTemplateRSVImpl implements ICmsFloorTemplateRSV{

	 
    @Resource
    private ICmsFloorTemplateSV sv;
    
    //定义个常量，用于表示模块名称，可以使用：当前类的类名
    private static final String MODULE = CmsFloorTemplateRSVImpl.class.getName();

    /** 
     * TODO 新增楼层模板. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorTemplateRSV#saveCmsFloorTemplate(com.zengshi.ecp.cms.dubbo.dto.CmsFloorTemplateReqDTO) 
     */
    @Override   
    public CmsFloorTemplateRespDTO addCmsFloorTemplate(CmsFloorTemplateReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用新增楼层模板开始，入参：{dto="+dto.toString()+"}");
        CmsFloorTemplateRespDTO respDTO = new CmsFloorTemplateRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isBlank(dto.getTemplateName())){
            LogUtil.error(MODULE, "入参TemplateName为空！");
            String[] keyInfos = {"TemplateName"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getTemplateNo())){
            LogUtil.error(MODULE, "入参TemplateNo为空！");
            String[] keyInfos = {"TemplateNo"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getVfsId())){
        	LogUtil.error(MODULE, "入参VfsId为空！");
            String[] keyInfos = {"VfsId"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getFloorPlaceNum())){
            LogUtil.error(MODULE, "入参FloorPlaceNum为空！");
            String[] keyInfos = {"FloorPlaceNum"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        /*2.调service层接口*/
        try{   
            respDTO = sv.addCmsFloorTemplate(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "新增楼层模板失败:",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORTEMPLATE_500101);
        }
        return respDTO;
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorTemplateRSV#updateCmsFloorTemplate(com.zengshi.ecp.cms.dubbo.dto.CmsFloorTemplateReqDTO) 
     */
    @Override
    public CmsFloorTemplateRespDTO updateCmsFloorTemplate(CmsFloorTemplateReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用更新楼层模板开始，入参：{dto="+dto.toString()+"}");
        CmsFloorTemplateRespDTO respDTO = new CmsFloorTemplateRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = {"id"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isBlank(dto.getTemplateName())){
            LogUtil.error(MODULE, "入参TemplateName为空！");
            String[] keyInfos = {"TemplateName"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getTemplateNo())){
            LogUtil.error(MODULE, "入参TemplateNo为空！");
            String[] keyInfos = {"TemplateNo"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getVfsId())){
            LogUtil.error(MODULE, "入参VfsId为空！");
            String[] keyInfos = {"VfsId"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getFloorPlaceNum())){
            LogUtil.error(MODULE, "入参FloorPlaceNum为空！");
            String[] keyInfos = {"FloorPlaceNum"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        /*2.调service层接口*/
        try{    
            respDTO = sv.updateCmsFloorTemplate(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新楼层模板失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORTEMPLATE_500102);
        }
        return respDTO;
    }
    
    /** 
     * TODO 查询楼层模板，分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorTemplateRSV#queryCmsFloorTemplatePage(com.zengshi.ecp.cms.dubbo.dto.CmsFloorTemplateReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsFloorTemplateRespDTO> queryCmsFloorTemplatePage(CmsFloorTemplateReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用分页查询楼层模板开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索楼层模板  */
        PageResponseDTO<CmsFloorTemplateRespDTO> pageInfo = PageResponseDTO.buildByBaseInfo(dto, CmsFloorTemplateRespDTO.class);
        try{
            pageInfo =  sv.queryCmsFloorTemplatePage(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "查询楼层模板失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORTEMPLATE_500103);
        }
        return pageInfo;
    }
    
    /** 
     * TODO 查询单个楼层模板（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorTemplateRSV#queryCmsFloorTemplate(com.zengshi.ecp.cms.dubbo.dto.CmsFloorTemplateReqDTO) 
     */
    @Override
    public CmsFloorTemplateRespDTO queryCmsFloorTemplate(CmsFloorTemplateReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用查询楼层模板开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = {"id"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.根据条件检索楼层模板  */
        CmsFloorTemplateRespDTO cmsFloorTemplateRespDTO = new CmsFloorTemplateRespDTO();
        try {
            cmsFloorTemplateRespDTO = sv.queryCmsFloorTemplate(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "根据ID查询楼层模板出现异常！",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORTEMPLATE_500103);
        }
        return cmsFloorTemplateRespDTO;
    }
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorTemplateRSV#deleteCmsFloorTemplate(java.lang.Long) 
     */
    @Override
    public void deleteCmsFloorTemplate(String id) throws BusinessException {
        LogUtil.info(MODULE, "调用删除楼层模板开始，入参：{id="+id+"}");
        /* 1.验证前店入参 */
        if(StringUtils.isBlank(id)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = {"id"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑删除楼层模板  */
        try{
            sv.deleteCmsFloorTemplate(id);
        } catch(Exception err){
            LogUtil.error(MODULE, "删除楼层模板失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORTEMPLATE_500104);
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorTemplateRSV#deleteCmsFloorTemplateBatch(java.util.List) 
     */
    @Override
    public void deleteCmsFloorTemplateBatch(List<String> list) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除楼层模板开始，入参：{list="+list.toArray()+"}");
        /* 1.验证前店入参 */
        if(CollectionUtils.isEmpty(list)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = {"list"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑批量删除楼层模板  */
        try{
            sv.deleteCmsFloorTemplateBatch(list);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量删除楼层模板失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORTEMPLATE_500104);
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorTemplateRSV#changeStatusCmsFloorTemplate(java.lang.Long, java.lang.String) 
     */
    @Override
    public void changeStatusCmsFloorTemplate(String id,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除楼层模板开始，入参：{id="+id+",status="+status+"}");
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
        /* 2.更新楼层模板状态  */
        try{
            sv.changeStatusCmsFloorTemplate(id, status);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新楼层模板状态失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORTEMPLATE_500106);
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorTemplateRSV#changeStatusCmsFloorTemplateBatch(java.util.List, java.lang.String) 
     */
    @Override
    public void changeStatusCmsFloorTemplateBatch(List<String> list,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除楼层模板开始，入参：{list="+list.toArray()+",status="+status+"}");
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
        
        /* 2.更新楼层模板状态  */
        try{
            sv.changeStatusCmsFloorTemplateBatch(list,status);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量更新楼层模板状态失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORTEMPLATE_500107);
        }
    }
    
    /** 
     * TODO 查询楼层模板  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public List<CmsFloorTemplateRespDTO> queryCmsFloorTemplateList(CmsFloorTemplateReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用查询楼层模板开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索楼层楼层模板  */
        List<CmsFloorTemplateRespDTO> list = new ArrayList<CmsFloorTemplateRespDTO>();
        try {
            list = sv.queryCmsFloorTemplateList(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询楼层模板出现异常！",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORTEMPLATE_500103);
        }
        
        return list;
    }
}
