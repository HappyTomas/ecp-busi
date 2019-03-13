/** 
 * Project Name:ecp-services-cms 
 * File Name:CmsInfoRSVImpl.java 
 * Package Name:com.zengshi.ecp.cms.dubbo.impl 
 * Date:2015-8-6下午2:51:39 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.cms.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.cms.dubbo.dto.CmsInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsInfoRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsInfoRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsInfoSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-cms <br>
 * Description: 页面信息操作对外服务实现类<br>
 * Date:2015-8-6下午2:51:39  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author wenyf
 * @version  
 * @since JDK 1.7
 */
public class CmsInfoRSVImpl implements ICmsInfoRSV {
    
    @Resource
    private ICmsInfoSV iCmsInfoSV;
    
    //定义个常量，用于表示模块名称，可以使用：当前类的类名
    private static final String MODULE = CmsInfoRSVImpl.class.getName();

    
    /** 
     * TODO 页面信息新增操作
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsInfoRSV#saveCmsInfo(com.zengshi.ecp.cms.dubbo.dto.CmsInfoReqDTO) 
     */
    @Override
    public CmsInfoRespDTO saveCmsInfo(CmsInfoReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用新增页面信息开始，入参：{dto="+dto.toString()+"}");
        CmsInfoRespDTO respDTO = new CmsInfoRespDTO();
        /*1.验证前店入参*/
        if (StringUtil.isBlank(dto.getInfoTitle())) {
            LogUtil.error(MODULE, "入参infoTitle为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="infoTitle";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if (StringUtil.isBlank(dto.getInfoType())) {
            LogUtil.error(MODULE, "入参infoType为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="infoType";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if (StringUtil.isEmpty(dto.getPubTime())) {
            LogUtil.error(MODULE, "入参pubTime为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="pubTime";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if (StringUtil.isEmpty(dto.getLostTime())) {
            LogUtil.error(MODULE, "入参lostTime为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="lostTime";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if (StringUtil.isBlank(dto.getStaticId())) {
            LogUtil.error(MODULE, "入参StaticId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="staticId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getSiteId())){
        	LogUtil.error(MODULE, "入参siteId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="siteId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getTemplateId())){
        	LogUtil.error(MODULE, "入参templateId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="templateId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getPlaceId())){
        	LogUtil.error(MODULE, "入参placeId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="placeId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try {
            respDTO = iCmsInfoSV.saveCmsInfo(dto);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.error(MODULE, "新增页面信息失败:",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_INFO_500101);
        }
        
        return respDTO;
        
    }
    
    /** 
     * TODO 页面信息修改服务（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsInfoRSV#updateCmsInfo(com.zengshi.ecp.cms.dubbo.dto.CmsInfoReqDTO) 
     */
    @Override
    public CmsInfoRespDTO updateCmsInfo(CmsInfoReqDTO dto) throws BusinessException{
        LogUtil.info(MODULE, "调用修改页面信息开始，入参：{dto="+dto.toString()+"}");
        CmsInfoRespDTO respDTO = new CmsInfoRespDTO();
        /*1.验证前店入参*/
        if (StringUtil.isEmpty(dto.getId())) {
            LogUtil.error(MODULE, "入参id为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if (StringUtil.isBlank(dto.getInfoTitle())) {
            LogUtil.error(MODULE, "入参infoTitle为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="infoTitle";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if (StringUtil.isBlank(dto.getInfoType())) {
            LogUtil.error(MODULE, "入参infoType为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="infoType";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if (StringUtil.isEmpty(dto.getPubTime())) {
            LogUtil.error(MODULE, "入参pubTime为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="pubTime";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if (StringUtil.isEmpty(dto.getLostTime())) {
            LogUtil.error(MODULE, "入参lostTime为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="lostTime";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if (StringUtil.isBlank(dto.getStaticId())) {
            LogUtil.error(MODULE, "入参StaticId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="StaticId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getSiteId())){
        	LogUtil.error(MODULE, "入参siteId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="siteId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getTemplateId())){
        	LogUtil.error(MODULE, "入参templateId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="templateId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtil.isEmpty(dto.getPlaceId())){
        	LogUtil.error(MODULE, "入参placeId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="placeId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        /*2.调service层接口*/
        try {
            respDTO = iCmsInfoSV.updateCmsInfo(dto);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.error(MODULE, "修改页面信息失败:",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_INFO_500102);
        }
        
        return respDTO;
        
    }

    /** 
     * TODO 根据页面信息ID查询页面信息，返回页面信息对象（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsInfoRSV#queryCmsInfo(long) 
     */
    @Override
    public CmsInfoRespDTO queryCmsInfo(CmsInfoReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用查询页面信息开始，入参：{dto="+dto.toString()+"}");
        /*1.验证前店入参*/
        if (StringUtil.isBlank(StringUtil.toString(dto.getId()))) {
            LogUtil.error(MODULE, "入参id为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        CmsInfoRespDTO cmsInfoRespDTO=new CmsInfoRespDTO();
        try {
            cmsInfoRespDTO=iCmsInfoSV.queryCmsInfo(dto.getId().longValue());
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.error(MODULE, "查询页面信息失败:",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_INFO_500103);
        }
        
        /*3.返回页面信息出参对象*/
        return cmsInfoRespDTO;
    }
    
    /** 
     * TODO 根据页面信息ID删除页面信息，只做逻辑删除，修改状态为2（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsInfoRSV#deleteCmsInfo(long) 
     */
    @Override
    public void deleteCmsInfo(CmsInfoReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用删除页面信息开始，入参：{dto="+dto.toString()+"}");
        /*1.验证前店入参*/
        if (StringUtil.isBlank(StringUtil.toString(dto.getId()))) {
            LogUtil.error(MODULE, "入参id为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try {
            iCmsInfoSV.deleteCmsInfo(dto);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.error(MODULE, "删除页面信息失败:",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_INFO_500104);
        }
        
    }
    
    /**
     * 使页面信息生效
     * activeCmsInfo:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author wenyf 
     * @param dto
     * @throws BusinessException 
     * @since JDK 1.7
     */
    @Override
    public void activeCmsInfo(CmsInfoReqDTO dto) throws BusinessException{
        LogUtil.info(MODULE, "调用使页面信息生效开始，入参：{dto="+dto.toString()+"}");
        /*1.验证前店入参*/
        if (StringUtil.isBlank(StringUtil.toString(dto.getId()))) {
            LogUtil.error(MODULE, "入参id为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try {
            iCmsInfoSV.activeCmsInfo(dto);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.error(MODULE, "使页面信息生效失败:",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_INFO_500106);
        }
    }
    
    /**
     * 使页面信息失效
     * invalidCmsInfo:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author wenyf 
     * @param dto
     * @throws BusinessException 
     * @since JDK 1.7
     */
    @Override
    public void invalidCmsInfo(CmsInfoReqDTO dto) throws BusinessException{
        LogUtil.info(MODULE, "调用使页面信息失效开始，入参：{dto="+dto.toString()+"}");
        /*1.验证前店入参*/
        if (StringUtil.isBlank(StringUtil.toString(dto.getId()))) {
            LogUtil.error(MODULE, "入参id为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /*2.调service层接口*/
        try {
            iCmsInfoSV.invalidCmsInfo(dto);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.error(MODULE, "使页面信息失效失败:",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_INFO_500107);
        }
    }
    
    /** 
     * TODO 根据入参条件查询页面信息列表，返回列表对象（分页）（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsInfoRSV#queryCmsInfoList(com.zengshi.ecp.server.front.dto.BaseInfo) 
     */
    @Override
    public PageResponseDTO<CmsInfoRespDTO> queryCmsInfoPage(CmsInfoReqDTO dto) throws BusinessException {
        LogUtil.info(MODULE, "调用分页查询页面信息列表开始，入参：{dto="+dto.toString()+"}");
        /*1.验证前店入参*/
        
        /*2.调service层接口*/
        PageResponseDTO<CmsInfoRespDTO> pageInfo = PageResponseDTO.buildByBaseInfo(dto, CmsInfoRespDTO.class);
        try {
            pageInfo=iCmsInfoSV.queryCmsInfoPage(dto);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.error(MODULE, "分页查询页面信息失败:",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_INFO_500105);
        }
       
        /*3.返回分页查询结果对象*/
        return pageInfo;
    }
    
    /** 
     * TODO 查询内容位置  无分页（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public List<CmsInfoRespDTO> queryCmsInfoList(CmsInfoReqDTO dto)
            throws BusinessException {
        LogUtil.info(MODULE, "调用查询内容位置开始，入参：{dto="+dto.toString()+"}");
        /* 1.验证前店入参 */
        
        /* 2.根据条件检索内容位置  */
        List<CmsInfoRespDTO> list = new ArrayList<CmsInfoRespDTO>();
        try {
            list = iCmsInfoSV.queryCmsInfoList(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询内容位置出现异常！",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_PLACE_500103);
        }
        
        return list;
    }
    
}

