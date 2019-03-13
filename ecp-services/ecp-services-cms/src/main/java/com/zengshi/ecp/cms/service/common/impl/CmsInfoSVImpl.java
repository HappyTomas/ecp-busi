/** 
 * Project Name:ecp-services-cms 
 * File Name:CmsInfoSVImpl.java 
 * Package Name:com.zengshi.ecp.cms.service.common.impl 
 * Date:2015-8-6下午2:30:53 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.cms.service.common.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dao.mapper.common.CmsInfoMapper;
import com.zengshi.ecp.cms.dao.model.CmsInfo;
import com.zengshi.ecp.cms.dao.model.CmsInfoCriteria;
import com.zengshi.ecp.cms.dao.model.CmsInfoCriteria.Criteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsInfoRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsInfoSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsPlaceSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsSiteSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateSV;
import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-cms <br>
 * Description: 页面操作信息实现类<br>
 * Date:2015-8-6下午2:30:53  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author wenyf
 * @version  
 * @since JDK 1.7 
 */
@Service("cmsInfoSV")
public class CmsInfoSVImpl extends GeneralSQLSVImpl implements ICmsInfoSV {
    
    @Resource
    private CmsInfoMapper cmsInfoMapper;
    
    @Resource(name="SEQ_CMS_INFO")
    private PaasSequence seqCmsInfo;

    @Resource
    private ICmsPlaceSV cmsPlaceSV;

    @Resource
    private ICmsSiteSV cmsSiteSV;
    
    @Resource
    private ICmsTemplateSV cmsTemplateSV;
    /** 
     * TODO 页面信息新增操作，返回页面信息ID（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsInfoSV#saveCmsInfo(com.zengshi.ecp.cms.dao.model.CmsInfo) 
     */
    @Override
    public CmsInfoRespDTO saveCmsInfo(CmsInfoReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        long id=seqCmsInfo.nextValue();
        CmsInfo bo=new CmsInfo();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, "ID", true);
        }
        bo.setId(id);
        bo.setCreateTime(DateUtil.getSysDate());
        bo.setCreateStaff(dto.getStaff().getId());
        
        /*2.调dao层接口*/
        cmsInfoMapper.insertSelective(bo);
        
        /*3.将结果返回*/
        CmsInfoRespDTO respDTO = new CmsInfoRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, true);
        }
        
        return respDTO;
    }
    
    /** 
     * TODO 编辑页面信息（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsInfoSV#updateCmsInfo(com.zengshi.ecp.cms.dao.model.CmsInfo) 
     */
    @Override
    public CmsInfoRespDTO updateCmsInfo(CmsInfoReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsInfo bo=new CmsInfo();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, "", true);
        }
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /*2.调dao层接口*/
        cmsInfoMapper.updateByPrimaryKeySelective(bo);
        
        /*3.将结果返回*/
        CmsInfoRespDTO respDTO = new CmsInfoRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, true);
        }
        
        return respDTO;
    }
    
    /** 
     * TODO 根据页面信息ID查询页面信息，返回页面信息对象（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsInfoSV#queryCmsInfo(long) 
     */
    @Override
    public CmsInfoRespDTO queryCmsInfo(long id) throws BusinessException {
        CmsInfoRespDTO dto = new CmsInfoRespDTO();
        if (id != 0) {
            /* 1.查询  */
            CmsInfo bo=cmsInfoMapper.selectByPrimaryKey(id);
            dto = conversionObject(bo);
        }
        return dto;
    }
    
    /** 
     * TODO 根据ID删除页面信息，做逻辑删除，状态编码修改为 2（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsInfoSV#deleteCmsInfo(long) 
     */
    @Override
    public void deleteCmsInfo(CmsInfoReqDTO dto) throws BusinessException {
        CmsInfo cmsInfo=new CmsInfo();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, cmsInfo, "", false);
        }
        //STATUS 状态1有效，0无效，2删除 
        cmsInfo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        cmsInfoMapper.updateByPrimaryKeySelective(cmsInfo);
    }
    
    /** 
     * TODO 根据条件查询页面信息列表,按照创建时间倒序排列，返回列表对象（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsInfoSV#queryCmsInfos(com.zengshi.ecp.cms.dao.model.CmsInfoCriteria) 
     */
    @Override
    public List<CmsInfoRespDTO> queryCmsInfoList(CmsInfoReqDTO cmsInfoDTO) throws BusinessException {
        CmsInfoCriteria cmsInfoCriteria=new CmsInfoCriteria();
        Criteria criteria=cmsInfoCriteria.createCriteria();
        /*1.组装查询条件，如果条件为空，则不进行组装*/
        if(StringUtil.isNotEmpty(cmsInfoDTO.getId())){
            criteria.andIdEqualTo(cmsInfoDTO.getId());
        }
        if (!StringUtil.isBlank(cmsInfoDTO.getInfoTitle())) {
            criteria.andInfoTitleLike("%"+cmsInfoDTO.getInfoTitle().trim()+"%"); 
        }
        if (!StringUtil.isBlank(cmsInfoDTO.getInfoType())) {
            criteria.andInfoTypeEqualTo(cmsInfoDTO.getInfoType());
        }
        if (!StringUtil.isBlank(cmsInfoDTO.getStatus())) {
            criteria.andStatusEqualTo(cmsInfoDTO.getStatus());
        }
        //发布时间
        if(StringUtil.isNotEmpty(cmsInfoDTO.getStartPubTime())&&StringUtil.isNotEmpty(cmsInfoDTO.getEndPubTime())){
            String startPubTimeStr = DateUtil.getDateString(cmsInfoDTO.getStartPubTime(), "yyyy-MM-dd")+" 00:00:00";
            String endPubTimeStr = DateUtil.getDateString(cmsInfoDTO.getEndPubTime(), "yyyy-MM-dd")+" 23:59:59";
            criteria.andPubTimeBetween(Timestamp.valueOf(startPubTimeStr), Timestamp.valueOf(endPubTimeStr));
        }else if(StringUtil.isNotEmpty(cmsInfoDTO.getStartPubTime())){
            String startPubTimeStr = DateUtil.getDateString(cmsInfoDTO.getStartPubTime(), "yyyy-MM-dd")+" 00:00:00";
            criteria.andPubTimeGreaterThanOrEqualTo(Timestamp.valueOf(startPubTimeStr)); 
        }else if(StringUtil.isNotEmpty(cmsInfoDTO.getEndPubTime())) {
            String endPubTimeStr = DateUtil.getDateString(cmsInfoDTO.getEndPubTime(), "yyyy-MM-dd")+" 23:59:59";
            criteria.andPubTimeLessThanOrEqualTo(Timestamp.valueOf(endPubTimeStr));
        }
        //失效时间    
        if(StringUtil.isNotEmpty(cmsInfoDTO.getStartLostTime())&&StringUtil.isNotEmpty(cmsInfoDTO.getEndLostTime())){
            String startLostTimeStr = DateUtil.getDateString(cmsInfoDTO.getStartLostTime(), "yyyy-MM-dd")+" 00:00:00";
            String endLostTimeStr = DateUtil.getDateString(cmsInfoDTO.getEndLostTime(), "yyyy-MM-dd")+" 23:59:59";
            criteria.andLostTimeBetween(Timestamp.valueOf(startLostTimeStr), Timestamp.valueOf(endLostTimeStr));
        }else if (StringUtil.isNotEmpty(cmsInfoDTO.getStartLostTime())) {
            String startLostTimeStr = DateUtil.getDateString(cmsInfoDTO.getStartLostTime(), "yyyy-MM-dd")+" 00:00:00";
            criteria.andLostTimeGreaterThanOrEqualTo(Timestamp.valueOf(startLostTimeStr));
        }else if (StringUtil.isNotEmpty(cmsInfoDTO.getEndLostTime())) {
            String endLostTimeStr = DateUtil.getDateString(cmsInfoDTO.getEndLostTime(), "yyyy-MM-dd")+" 23:59:59";
            criteria.andLostTimeLessThanOrEqualTo(Timestamp.valueOf(endLostTimeStr));
        }
        //当前时间
        if(StringUtil.isNotEmpty(cmsInfoDTO.getThisTime())){
            criteria.andPubTimeLessThanOrEqualTo(cmsInfoDTO.getThisTime()); 
            criteria.andLostTimeGreaterThanOrEqualTo(cmsInfoDTO.getThisTime());
        }
        /*2.排序条件*/
        cmsInfoCriteria.setOrderByClause("PUB_TIME DESC, CREATE_TIME DESC");
        
        /*3.调dao层接口,迭代查询结果*/
        List<CmsInfoRespDTO> cmsInfoRespDTOList =  new ArrayList<CmsInfoRespDTO>();
        List<CmsInfo> list = cmsInfoMapper.selectByExample(cmsInfoCriteria);
        if(CollectionUtils.isNotEmpty(list)){
            for(CmsInfo bo :list){
                CmsInfoRespDTO cmsInfoRespDTO = conversionObject(bo);
                cmsInfoRespDTOList.add(cmsInfoRespDTO);
            }
        }
        
        /*4.返回查询结果*/
        return cmsInfoRespDTOList;
    }
    
    /** 
     * TODO 批量删除页面信息（可选）. 
     * @see com.zengshi.ecp.cms.stervice.common.interfaces.ICmsInfoSV#deleteCmsInfoatch(java.util.List) 
     */
    @Override
    public void deleteCmsInfosBatch(List<String> list) throws BusinessException {
         for (int i = 0; i < list.size(); i++) {
              String id=list.get(i);
              CmsInfoReqDTO dto = new CmsInfoReqDTO();
              dto.setId(Long.parseLong(id));
              this.deleteCmsInfo(dto);
         }
    }
    
    /** 
     * TODO 设置页面信息生效（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsInfoSV#activeCmsInfo(long) 
     */
    @Override
    public void activeCmsInfo(CmsInfoReqDTO dto) throws BusinessException {
        CmsInfo cmsInfo=new CmsInfo();
        ObjectCopyUtil.copyObjValue(dto, cmsInfo, "", false);
        //STATUS 状态1有效，0无效，2删除 
        cmsInfo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        cmsInfoMapper.updateByPrimaryKeySelective(cmsInfo);
    }
    
    /** 
     * TODO 设置页面信息失效（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsInfoSV#invalidCmsInfo(long) 
     */
    @Override
    public void invalidCmsInfo(CmsInfoReqDTO dto) throws BusinessException {
        CmsInfo cmsInfo=new CmsInfo();
        ObjectCopyUtil.copyObjValue(dto, cmsInfo, "", false);
        //STATUS 状态1有效，0无效，2删除 
        cmsInfo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);
        cmsInfoMapper.updateByPrimaryKeySelective(cmsInfo);
    }
   
    
    /** 
     * TODO 分页查询页面信息列表（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsInfoSV#findByPage(com.zengshi.ecp.server.front.dto.BaseInfo) 
     */
    @Override
    public PageResponseDTO<CmsInfoRespDTO> queryCmsInfoPage(CmsInfoReqDTO cmsInfoDTO) throws BusinessException{
        CmsInfoCriteria cmsInfoCriteria=new CmsInfoCriteria();
        Criteria criteria=cmsInfoCriteria.createCriteria();
        
        /*1.组装查询条件，如果条件为空，则不进行组装*/
        if(StringUtil.isNotEmpty(cmsInfoDTO.getId())){
            criteria.andIdEqualTo(cmsInfoDTO.getId());
        }
        if (!StringUtil.isBlank(cmsInfoDTO.getInfoTitle())) {
            criteria.andInfoTitleLike("%"+cmsInfoDTO.getInfoTitle().trim()+"%"); 
        }
        if (!StringUtil.isBlank(cmsInfoDTO.getInfoType())) {
            criteria.andInfoTypeEqualTo(cmsInfoDTO.getInfoType());
        }
        if (!StringUtil.isBlank(cmsInfoDTO.getStatus())) {
            criteria.andStatusEqualTo(cmsInfoDTO.getStatus());
        }
        //发布时间
        if(StringUtil.isNotEmpty(cmsInfoDTO.getStartPubTime())&&StringUtil.isNotEmpty(cmsInfoDTO.getEndPubTime())){
            String startPubTimeStr = DateUtil.getDateString(cmsInfoDTO.getStartPubTime(), "yyyy-MM-dd")+" 00:00:00";
            String endPubTimeStr = DateUtil.getDateString(cmsInfoDTO.getEndPubTime(), "yyyy-MM-dd")+" 23:59:59";
            criteria.andPubTimeBetween(Timestamp.valueOf(startPubTimeStr), Timestamp.valueOf(endPubTimeStr));
        }else if(StringUtil.isNotEmpty(cmsInfoDTO.getStartPubTime())){
            String startPubTimeStr = DateUtil.getDateString(cmsInfoDTO.getStartPubTime(), "yyyy-MM-dd")+" 00:00:00";
            criteria.andPubTimeGreaterThanOrEqualTo(Timestamp.valueOf(startPubTimeStr)); 
        }else if(StringUtil.isNotEmpty(cmsInfoDTO.getEndPubTime())) {
            String endPubTimeStr = DateUtil.getDateString(cmsInfoDTO.getEndPubTime(), "yyyy-MM-dd")+" 23:59:59";
            criteria.andPubTimeLessThanOrEqualTo(Timestamp.valueOf(endPubTimeStr));
        }
        //失效时间    
        if(StringUtil.isNotEmpty(cmsInfoDTO.getStartLostTime())&&StringUtil.isNotEmpty(cmsInfoDTO.getEndLostTime())){
            String startLostTimeStr = DateUtil.getDateString(cmsInfoDTO.getStartLostTime(), "yyyy-MM-dd")+" 00:00:00";
            String endLostTimeStr = DateUtil.getDateString(cmsInfoDTO.getEndLostTime(), "yyyy-MM-dd")+" 23:59:59";
            criteria.andLostTimeBetween(Timestamp.valueOf(startLostTimeStr), Timestamp.valueOf(endLostTimeStr));
        }else if (StringUtil.isNotEmpty(cmsInfoDTO.getStartLostTime())) {
            String startLostTimeStr = DateUtil.getDateString(cmsInfoDTO.getStartLostTime(), "yyyy-MM-dd")+" 00:00:00";
            criteria.andLostTimeGreaterThanOrEqualTo(Timestamp.valueOf(startLostTimeStr));
        }else if (StringUtil.isNotEmpty(cmsInfoDTO.getEndLostTime())) {
            String endLostTimeStr = DateUtil.getDateString(cmsInfoDTO.getEndLostTime(), "yyyy-MM-dd")+" 23:59:59";
            criteria.andLostTimeLessThanOrEqualTo(Timestamp.valueOf(endLostTimeStr));
        }
        if(StringUtil.isNotEmpty(cmsInfoDTO.getThisTime())){
            criteria.andPubTimeLessThanOrEqualTo(cmsInfoDTO.getThisTime()); 
            criteria.andLostTimeGreaterThanOrEqualTo(cmsInfoDTO.getThisTime());
        }
        if (StringUtil.isNotEmpty(cmsInfoDTO.getSiteId())) {
            criteria.andSiteIdEqualTo(cmsInfoDTO.getSiteId());
        }
        if (StringUtil.isNotEmpty(cmsInfoDTO.getTemplateId())) {
            criteria.andTemplateIdEqualTo(cmsInfoDTO.getTemplateId());
        }
        if (StringUtil.isNotEmpty(cmsInfoDTO.getPlaceId())) {
            criteria.andPlaceIdEqualTo(cmsInfoDTO.getPlaceId());
        }
        
        cmsInfoCriteria.setLimitClauseCount(cmsInfoDTO.getPageSize());
        cmsInfoCriteria.setLimitClauseStart(cmsInfoDTO.getStartRowIndex());
        cmsInfoCriteria.setOrderByClause("PUB_TIME DESC, CREATE_TIME DESC");
        
        return super.queryByPagination(cmsInfoDTO, cmsInfoCriteria,true, new PaginationCallback<CmsInfo, CmsInfoRespDTO>() {
            //查询记录集
            @Override
            public List<CmsInfo> queryDB(BaseCriteria criteria) {
                
                return cmsInfoMapper.selectByExample((CmsInfoCriteria)criteria);
            }
            //查询总记录数
            @Override
            public long queryTotal(BaseCriteria criteria) {
                return cmsInfoMapper.countByExample((CmsInfoCriteria)criteria);
            }

            //查询结果转换
            @Override
            public CmsInfoRespDTO warpReturnObject(CmsInfo bo) {
                return conversionObject(bo);
            }
        });
    }
    
    /** 
     * conversionObject:(将bo转换成DTO，同时翻译有关字段). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param bo 
     * @return 
     * @since JDK 1.6 
     */ 
    private CmsInfoRespDTO conversionObject(CmsInfo bo){
        CmsInfoRespDTO dto = new CmsInfoRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, dto, null, false);
        }
        // 1 查询内容位置服务，获取内容位置对应的名称
        if (StringUtil.isNotEmpty(dto.getPlaceId())) {
            CmsPlaceReqDTO cmsPlaceDTO = new CmsPlaceReqDTO();
            cmsPlaceDTO.setId(dto.getPlaceId());
            CmsPlaceRespDTO cmsPlaceRespDTO = cmsPlaceSV.queryCmsPlace(cmsPlaceDTO);
            if (cmsPlaceRespDTO != null) {
                dto.setPlaceName(cmsPlaceRespDTO.getPlaceName());
            }
        }
        // 2 查询站点服务，获取站点对应的名称
        if (StringUtil.isNotEmpty(dto.getSiteId())) {
            CmsSiteReqDTO reqDTO = new CmsSiteReqDTO();
            reqDTO.setId(bo.getSiteId());
            CmsSiteRespDTO respDTO = cmsSiteSV.queryCmsSite(reqDTO);
            if(respDTO != null){
                dto.setSiteName(respDTO.getSiteName());
            }
        }
        
        // 3 查询模板服务，获取模板对应的名称
        if (StringUtil.isNotEmpty(dto.getTemplateId())) {
            CmsTemplateReqDTO reqDTO = new CmsTemplateReqDTO();
            reqDTO.setId(bo.getTemplateId());
            CmsTemplateRespDTO respDTO = cmsTemplateSV.queryCmsTemplate(reqDTO);
            if(respDTO != null){
                dto.setTemplateName(respDTO.getTemplateName());
            }
        }
        
        //4.遍历将编码转中文 
        dto.setTypeName(BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_INFO_TYPE, dto.getInfoType()));
        dto.setStatusName(BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_STATUS, dto.getStatus()));
        
        return dto;
    }

}

