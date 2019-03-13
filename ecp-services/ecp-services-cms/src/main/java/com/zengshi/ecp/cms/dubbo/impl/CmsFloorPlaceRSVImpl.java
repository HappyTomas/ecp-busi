package com.zengshi.ecp.cms.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.zengshi.ecp.cms.dubbo.dto.CmsFloorPlaceReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorPlaceRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorPlaceRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorPlaceSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

public class CmsFloorPlaceRSVImpl implements ICmsFloorPlaceRSV{

	 
    @Resource
    private ICmsFloorPlaceSV sv;
    
    //定义个常量，用于表示模块名称，可以使用：当前类的类名
    private static final String MODULE = CmsFloorPlaceRSVImpl.class.getName();

    /** 
     * TODO 新增楼层模板内容位置. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorPlaceRSV#saveCmsFloorPlace(com.zengshi.ecp.cms.dubbo.dto.CmsFloorPlaceReqDTO) 
     */
    @Override   
    public CmsFloorPlaceRespDTO addCmsFloorPlace(CmsFloorPlaceReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用新增楼层模板内容位置开始，入参：{dto="+dto.toString()+"}");
        CmsFloorPlaceRespDTO respDTO = new CmsFloorPlaceRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getFloorTemplateId())){
            LogUtil.error(MODULE, "入参FloorTemplateId为空！");
            String[] keyInfos = {"FloorTemplateId"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getPlaceWidth())){
        	LogUtil.error(MODULE, "入参PlaceWidth为空！");
            String[] keyInfos = {"PlaceWidth"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getPlaceHeight())){
        	LogUtil.error(MODULE, "入参PlaceHeight为空！");
            String[] keyInfos = {"PlaceHeight"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getPlaceSize())){
        	LogUtil.error(MODULE, "入参PlaceSize为空！");
            String[] keyInfos = {"PlaceSize"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        /*2.调service层接口*/
        try{   
            respDTO = sv.addCmsFloorPlace(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "新增楼层模板内容位置失败:",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORPLACE_500101);
        }
        return respDTO;
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorPlaceRSV#updateCmsFloorPlace(com.zengshi.ecp.cms.dubbo.dto.CmsFloorPlaceReqDTO) 
     */
    @Override
    public CmsFloorPlaceRespDTO updateCmsFloorPlace(CmsFloorPlaceReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用更新楼层模板内容位置开始，入参：{dto="+dto.toString()+"}");
        CmsFloorPlaceRespDTO respDTO = new CmsFloorPlaceRespDTO();
        /*1.验证前店入参*/
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = {"id"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getFloorTemplateId())){
            LogUtil.error(MODULE, "入参FloorTemplateId为空！");
            String[] keyInfos = {"FloorTemplateId"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getPlaceWidth())){
            LogUtil.error(MODULE, "入参PlaceWidth为空！");
            String[] keyInfos = {"PlaceWidth"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getPlaceHeight())){
            LogUtil.error(MODULE, "入参PlaceHeight为空！");
            String[] keyInfos = {"PlaceHeight"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getPlaceSize())){
            LogUtil.error(MODULE, "入参PlaceSize为空！");
            String[] keyInfos = {"PlaceSize"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        /*2.调service层接口*/
        try{    
            respDTO = sv.updateCmsFloorPlace(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新楼层模板内容位置失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORPLACE_500102);
        }
        return respDTO;
    }
    
    /** 
     * TODO 查询楼层模板内容位置，分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorPlaceRSV#queryCmsFloorPlacePage(com.zengshi.ecp.cms.dubbo.dto.CmsFloorPlaceReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsFloorPlaceRespDTO> queryCmsFloorPlacePage(CmsFloorPlaceReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用分页查询楼层模板内容位置开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索楼层模板内容位置  */
        PageResponseDTO<CmsFloorPlaceRespDTO> pageInfo = PageResponseDTO.buildByBaseInfo(dto, CmsFloorPlaceRespDTO.class);
        try{
            pageInfo =  sv.queryCmsFloorPlacePage(dto);
        } catch(Exception err){
            LogUtil.error(MODULE, "查询楼层模板内容位置失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORPLACE_500103);
        }
        return pageInfo;
    }
    
    /** 
     * TODO 查询单个楼层模板内容位置（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorPlaceRSV#queryCmsFloorPlace(com.zengshi.ecp.cms.dubbo.dto.CmsFloorPlaceReqDTO) 
     */
    @Override
    public CmsFloorPlaceRespDTO queryCmsFloorPlace(CmsFloorPlaceReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用查询楼层模板内容位置开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        if(StringUtil.isEmpty(dto.getId())){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = {"id"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.根据条件检索楼层模板内容位置  */
        CmsFloorPlaceRespDTO cmsFloorPlaceRespDTO = new CmsFloorPlaceRespDTO();
        try {
            cmsFloorPlaceRespDTO = sv.queryCmsFloorPlace(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "根据ID查询楼层模板内容位置出现异常！",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORPLACE_500103);
        }
        return cmsFloorPlaceRespDTO;
    }
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorPlaceRSV#deleteCmsFloorPlace(java.lang.Long) 
     */
    @Override
    public void deleteCmsFloorPlace(String id) throws BusinessException {
        LogUtil.info(MODULE, "调用删除楼层模板内容位置开始，入参：{id="+id+"}");
        /* 1.验证前店入参 */
        if(StringUtils.isBlank(id)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = {"id"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑删除楼层模板内容位置  */
        try{
            sv.deleteCmsFloorPlace(id);
        } catch(Exception err){
            LogUtil.error(MODULE, "删除楼层模板内容位置失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORPLACE_500104);
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorPlaceRSV#deleteCmsFloorPlaceBatch(java.util.List) 
     */
    @Override
    public void deleteCmsFloorPlaceBatch(List<String> list) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除楼层模板内容位置开始，入参：{list="+list.toArray()+"}");
        /* 1.验证前店入参 */
        if(CollectionUtils.isEmpty(list)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = {"list"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.逻辑批量删除楼层模板内容位置  */
        try{
            sv.deleteCmsFloorPlaceBatch(list);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量删除楼层模板内容位置失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORPLACE_500104);
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorPlaceRSV#changeStatusCmsFloorPlace(java.lang.Long, java.lang.String) 
     */
    @Override
    public void changeStatusCmsFloorPlace(String id,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除楼层模板内容位置开始，入参：{id="+id+",status="+status+"}");
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
        /* 2.更新楼层模板内容位置状态  */
        try{
            sv.changeStatusCmsFloorPlace(id, status);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新楼层模板内容位置状态失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORPLACE_500106);
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorPlaceRSV#changeStatusCmsFloorPlaceBatch(java.util.List, java.lang.String) 
     */
    @Override
    public void changeStatusCmsFloorPlaceBatch(List<String> list,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除楼层模板内容位置开始，入参：{list="+list.toArray()+",status="+status+"}");
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
        
        /* 2.更新楼层模板内容位置状态  */
        try{
            sv.changeStatusCmsFloorPlaceBatch(list,status);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量更新楼层模板内容位置状态失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORPLACE_500107);
        }
    }
    
    /** 
     * TODO 查询楼层模板内容位置  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public List<CmsFloorPlaceRespDTO> queryCmsFloorPlaceList(CmsFloorPlaceReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用查询楼层模板内容位置开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索楼层楼层模板内容位置  */
        List<CmsFloorPlaceRespDTO> list = new ArrayList<CmsFloorPlaceRespDTO>();
        try {
            list = sv.queryCmsFloorPlaceList(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询楼层模板内容位置出现异常！",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORPLACE_500103);
        }
        
        return list;
    }
}
