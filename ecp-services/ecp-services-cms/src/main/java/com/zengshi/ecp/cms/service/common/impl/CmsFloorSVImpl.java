package com.zengshi.ecp.cms.service.common.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dao.mapper.common.CmsFloorMapper;
import com.zengshi.ecp.cms.dao.model.CmsFloor;
import com.zengshi.ecp.cms.dao.model.CmsFloorCriteria;
import com.zengshi.ecp.cms.dao.model.CmsFloorCriteria.Criteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorAdvertiseReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorAdvertiseRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorCouponReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorCouponRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorGdsReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorGdsRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorInfoDetailReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorInfoDetailRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorLabelReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorLabelRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorTabReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorTabRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorAdvertiseSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorCouponSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorGdsSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorLabelSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorTabSV;
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
 * Description: <br>
 * Date:2015年8月7日下午5:11:59 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author jiangzh
 * @version
 * @since JDK 1.6
 */
@Service("cmsFloorSV")
public class CmsFloorSVImpl extends GeneralSQLSVImpl implements ICmsFloorSV {

    @Resource(name = "SEQ_CMS_FLOOR")
    private PaasSequence seqCmsFloor;
    
    @Resource
    private CmsFloorMapper cmsFloorMapper;
    
    @Resource
    private ICmsPlaceSV cmsPlaceSV;
    
    @Resource
    private ICmsSiteSV cmsSiteSV;
    
    @Resource
    private ICmsTemplateSV cmsTemplateSV;
    
    @Resource
    private ICmsFloorAdvertiseSV cmsFloorAdvertiseSV;
    
    @Resource
    private ICmsFloorCouponSV cmsFloorCouponSV;
    
    @Resource
    private ICmsFloorGdsSV cmsFloorGdsSV;
    
    @Resource
    private ICmsFloorLabelSV cmsFloorLabelSV;
    
    @Resource
    private ICmsFloorTabSV cmsFloorTabSV;


    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorSV#saveCmsFloor(com.zengshi.ecp.cms.dao.model.CmsFloor)
     */
    @Override
    public CmsFloorRespDTO addCmsFloor(CmsFloorReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsFloor bo = new CmsFloor();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        long id = seqCmsFloor.nextValue();
        bo.setId(id);
        bo.setCreateTime(DateUtil.getSysDate());
        bo.setCreateStaff(dto.getStaff().getId());
        
        /*2.调dao层接口*/
        cmsFloorMapper.insertSelective(bo);
        
        /*3.将结果返回*/
        CmsFloorRespDTO respDTO = new CmsFloorRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorSV#updateCmsFloor(com.zengshi.ecp.cms.dao.model.CmsFloor)
     */
    @Override
    public CmsFloorRespDTO updateCmsFloor(CmsFloorReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsFloor bo = new CmsFloor();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        //当数据来源为“手工配置”时，要清空该楼层页签下的关联商品分类
        if(CmsConstants.DataSource.CMS_DATA_SOURCE_01.equals(dto.getDataSource())){
            CmsFloorTabReqDTO floorTabReqDTO = new CmsFloorTabReqDTO();
            floorTabReqDTO.setFloorId(dto.getId());
            List<CmsFloorTabRespDTO> floorTabRespDTOList = cmsFloorTabSV.queryCmsFloorTabList(floorTabReqDTO);
            if(CollectionUtils.isNotEmpty(floorTabRespDTOList)){
                for(CmsFloorTabRespDTO floorTabRespDTO : floorTabRespDTOList){
                    floorTabReqDTO.setId(floorTabRespDTO.getId());
                    floorTabReqDTO.setCatgCode("");
                    cmsFloorTabSV.updateCmsFloorTab(floorTabReqDTO);
                }
            }
        }
        
        /*2.更新楼层的原子方法*/
        return this.updateCmsFloor(bo);
    }
    
    /** 
     * updateCmsFloor:(更新楼层的原子方法). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param bo
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public CmsFloorRespDTO updateCmsFloor(CmsFloor bo) throws BusinessException {
        cmsFloorMapper.updateByPrimaryKeySelective(bo);
        CmsFloorRespDTO respDTO = new CmsFloorRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorSV#deleteCmsFloor(java.lang.Long)
     */
    @Override
    public void deleteCmsFloor(String id) throws BusinessException {
        CmsFloor bo = new CmsFloor();
        bo.setId(Long.parseLong(id));
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        this.updateCmsFloor(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorSV#deleteCmsFloorBatch(java.util.List)
     */
    @Override
    public void deleteCmsFloorBatch(List<String> list) throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.deleteCmsFloor(id);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorSV#changeStatusCmsFloor(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsFloor(String id, String status) throws BusinessException {
        CmsFloor bo = new CmsFloor();
        bo.setId(Long.parseLong(id));
        bo.setStatus(status);
        this.updateCmsFloor(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorSV#changeStatusCmsFloorBatch(java.util.List,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsFloorBatch(List<String> list, String status)
            throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.changeStatusCmsFloor(id, status);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorSV#queryCmsFloor(com.zengshi.ecp.cms.dubbo.dto.CmsFloorReqDTO)
     */
    @Override
    public CmsFloorRespDTO queryCmsFloor(CmsFloorReqDTO dto) throws BusinessException {
        CmsFloorRespDTO cmsFloorRespDTO = new CmsFloorRespDTO();
        if (StringUtil.isNotEmpty(dto.getId())) {
            /* 1.查询  */
            CmsFloor bo = cmsFloorMapper.selectByPrimaryKey(dto.getId());
            cmsFloorRespDTO = conversionObject(bo);
        }
        
        return cmsFloorRespDTO;
    }
    /** 
     * queryFloorInfoDetail:(根据选项查询楼层信息). <br/> 
     * TODO(楼层id – 必选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param dto  各个选项的个数如果大于0  就查询该选项
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    @Override
    public CmsFloorInfoDetailRespDTO queryFloorInfoDetail(CmsFloorInfoDetailReqDTO dto)throws BusinessException {
        CmsFloorInfoDetailRespDTO respDto = new CmsFloorInfoDetailRespDTO();
        if(StringUtil.isNotEmpty(dto.getFloorId())){
            //1 查询楼层基本信息
            CmsFloorReqDTO floorReqDto = new CmsFloorReqDTO();
            floorReqDto.setId(dto.getFloorId());
            CmsFloorRespDTO cmsFloorRespDTO = this.queryCmsFloor(floorReqDto);
            if(cmsFloorRespDTO!=null &&StringUtil.isNotEmpty(cmsFloorRespDTO.getId())){
                respDto.setFloorBaseInfo(cmsFloorRespDTO);
                
                //2 如果状态为发布  且指定了选项  则根据条件查询楼层信息
                if(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1.equalsIgnoreCase(cmsFloorRespDTO.getStatus())){
                    if(StringUtil.isNotEmpty(dto.getAdSize()) && dto.getAdSize() >0){
                        /**
                         * 查询广告
                         */
                        CmsFloorAdvertiseReqDTO adReqDTO = new CmsFloorAdvertiseReqDTO();
                        adReqDTO.setFloorId(dto.getFloorId());
                        adReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                        adReqDTO.setPageNo(1);
                        if(StringUtil.isNotEmpty(dto.getAdSize())){
                            adReqDTO.setPageSize(dto.getAdSize()); 
                        }else{
                            adReqDTO.setPageSize(Integer.MAX_VALUE); 
                        }
                        PageResponseDTO<CmsFloorAdvertiseRespDTO> adPageInfo = cmsFloorAdvertiseSV.queryCmsFloorAdvertisePage(adReqDTO);
                        if(adPageInfo!=null){
                            respDto.setFloorAdList(adPageInfo.getResult());
                        }
                    }
                    if(StringUtil.isNotEmpty(dto.getCouponSize()) && dto.getCouponSize() >0){
                        /**
                         * 查询优惠券
                         */
                        CmsFloorCouponReqDTO coupReqDTO = new CmsFloorCouponReqDTO();
                        coupReqDTO.setFloorId(dto.getFloorId());
                        coupReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                        coupReqDTO.setPageNo(1);
                        if(StringUtil.isNotEmpty(dto.getCouponSize())){
                            coupReqDTO.setPageSize(dto.getCouponSize()); 
                        }else{
                            coupReqDTO.setPageSize(Integer.MAX_VALUE); 
                        }
                        PageResponseDTO<CmsFloorCouponRespDTO> coupPageInfo = cmsFloorCouponSV.queryCmsFloorCouponPage(coupReqDTO);
                        if(coupPageInfo!=null){
                            respDto.setFloorCouponList(coupPageInfo.getResult());
                        }
                    }
                    if(StringUtil.isNotEmpty(dto.getLabelSize()) && dto.getLabelSize() >0){
                        /**
                         * 查询标签
                         */
                        CmsFloorLabelReqDTO labelReqDTO = new CmsFloorLabelReqDTO();
                        labelReqDTO.setFloorId(dto.getFloorId());
                        labelReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                        labelReqDTO.setPageNo(1);
                        if(StringUtil.isNotEmpty(dto.getLabelSize())){
                            labelReqDTO.setPageSize(dto.getLabelSize()); 
                        }else{
                            labelReqDTO.setPageSize(Integer.MAX_VALUE); 
                        }
                        PageResponseDTO<CmsFloorLabelRespDTO> labelPageInfo = cmsFloorLabelSV.queryCmsFloorLabelPage(labelReqDTO);
                        if(labelPageInfo!=null){
                            respDto.setFloorLabelList(labelPageInfo.getResult());
                        }
                    }
                    if(StringUtil.isNotEmpty(dto.getTabSize()) && dto.getTabSize() >0){
                        /**
                         * 查询页签
                         */
                        CmsFloorTabReqDTO tabReqDTO = new CmsFloorTabReqDTO();
                        tabReqDTO.setFloorId(dto.getFloorId());
                        tabReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                        tabReqDTO.setPageNo(1);
                        if(StringUtil.isNotEmpty(dto.getTabSize())){
                            tabReqDTO.setPageSize(dto.getTabSize()); 
                        }else{
                            tabReqDTO.setPageSize(Integer.MAX_VALUE); 
                        }
                        PageResponseDTO<CmsFloorTabRespDTO> tabPageInfo = cmsFloorTabSV.queryCmsFloorTabPage(tabReqDTO);
                        if(tabPageInfo!=null){
                            respDto.setFloorTabList(tabPageInfo.getResult());
                        }
                    }
                    
                    /**
                     * 手工配置楼层查询广告 且商品在页签之后查询  可以判断楼层是否有页签
                     */
                    if(!CmsConstants.DataSource.CMS_DATA_SOURCE_02.equalsIgnoreCase(cmsFloorRespDTO.getDataSource()) 
                       &&StringUtil.isNotEmpty(dto.getTabSize()) && dto.getTabSize() > 0){
                        CmsFloorGdsReqDTO gdsReqDTO = new CmsFloorGdsReqDTO();
                        gdsReqDTO.setFloorId(dto.getFloorId());
                        
                        //设置页签 如果有返回页签  则返回第一个页签的商品
                        if(StringUtil.isNotEmpty(dto.getTabId())){
                            gdsReqDTO.setTabId(dto.getTabId());
                        }else if(CollectionUtils.isNotEmpty(respDto.getFloorTabList())){
                            gdsReqDTO.setTabId(respDto.getFloorTabList().get(0).getId()); 
                        }
                        
                        gdsReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                        gdsReqDTO.setPageNo(1);
                        if(StringUtil.isNotEmpty(dto.getGdsSize())){
                            gdsReqDTO.setPageSize(dto.getGdsSize()); 
                        }else{
                            gdsReqDTO.setPageSize(Integer.MAX_VALUE); 
                        }
                        PageResponseDTO<CmsFloorGdsRespDTO> gdsPageInfo = cmsFloorGdsSV.queryCmsFloorGdsPage(gdsReqDTO);
                        if(gdsPageInfo!=null){
                            respDto.setFloorGdsList(gdsPageInfo.getResult());
                        }
                    }
                }
            }
            
        }
        return respDto;
    }
    /**
     * TODO 查询楼层列表，无分页（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorSV#queryCmsFloorList(com.zengshi.ecp.cms.dubbo.dto.CmsFloorReqDTO)
     */
    @Override
    public List<CmsFloorRespDTO> queryCmsFloorList(CmsFloorReqDTO dto) throws BusinessException {

        /*1.组装查询条件*/
        CmsFloorCriteria cmsFloorCriteria = new CmsFloorCriteria();
        Criteria criteria = cmsFloorCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getSiteId())) {
            criteria.andSiteIdEqualTo(dto.getSiteId());
        }
        if (StringUtil.isNotEmpty(dto.getTemplateId())) {
            criteria.andTemplateIdEqualTo(dto.getTemplateId());
        }
        if (StringUtil.isNotEmpty(dto.getPlaceId())) {
            criteria.andPlaceIdEqualTo(dto.getPlaceId());
        }
        if (StringUtil.isNotEmpty(dto.getDataSource())) {
            criteria.andDataSourceEqualTo(dto.getDataSource());
        }
        if (StringUtil.isNotEmpty(dto.getCountType())) {
            criteria.andCountTypeEqualTo(dto.getCountType());
        }
        if (StringUtil.isNotBlank(dto.getFloorName())) {
            criteria.andFloorNameLike("%"+dto.getFloorName()+"%");
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            criteria.andStatusEqualTo(dto.getStatus());
        }
        cmsFloorCriteria.setOrderByClause("SORT_NO ASC, CREATE_TIME DESC");
        
        /*2.迭代查询结果*/
        List<CmsFloorRespDTO> cmsFloorRespDTOList =  new ArrayList<CmsFloorRespDTO>();
        List<CmsFloor> cmsFloorList = cmsFloorMapper.selectByExample(cmsFloorCriteria);
        if(CollectionUtils.isNotEmpty(cmsFloorList)){
            for(CmsFloor bo :cmsFloorList){
                CmsFloorRespDTO cmsFloorRespDTO = conversionObject(bo);
                cmsFloorRespDTOList.add(cmsFloorRespDTO);
            }
        }
        
        return cmsFloorRespDTOList;

    }


    /** 
     * TODO 查询楼层，分页（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorSV#queryCmsFloorPage(com.zengshi.ecp.cms.dubbo.dto.CmsFloorReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsFloorRespDTO> queryCmsFloorPage(CmsFloorReqDTO dto)
            throws BusinessException {

        /* 1.根据条件检索楼层 */
        CmsFloorCriteria cmsFloorCriteria = new CmsFloorCriteria();
        Criteria criteria = cmsFloorCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getSiteId())) {
            criteria.andSiteIdEqualTo(dto.getSiteId());
        }
        if (StringUtil.isNotEmpty(dto.getTemplateId())) {
            criteria.andTemplateIdEqualTo(dto.getTemplateId());
        }
        if (StringUtil.isNotEmpty(dto.getPlaceId())) {
            criteria.andPlaceIdEqualTo(dto.getPlaceId());
        }
        if (StringUtil.isNotEmpty(dto.getDataSource())) {
            criteria.andDataSourceEqualTo(dto.getDataSource());
        }
        if (StringUtil.isNotEmpty(dto.getCountType())) {
            criteria.andCountTypeEqualTo(dto.getCountType());
        }
        if (StringUtil.isNotBlank(dto.getFloorName())) {
            criteria.andFloorNameLike("%"+dto.getFloorName()+"%");
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            criteria.andStatusEqualTo(dto.getStatus());
        }
        cmsFloorCriteria.setOrderByClause("SORT_NO ASC, CREATE_TIME DESC");
        cmsFloorCriteria.setLimitClauseCount(dto.getPageSize());
        cmsFloorCriteria.setLimitClauseStart(dto.getStartRowIndex());
        
        return super.queryByPagination(dto,cmsFloorCriteria,false,new PaginationCallback<CmsFloor, CmsFloorRespDTO>(){

            @Override
            public List<CmsFloor> queryDB(BaseCriteria criteria) {
                return cmsFloorMapper.selectByExample((CmsFloorCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return cmsFloorMapper.countByExample((CmsFloorCriteria)criteria);
            }

            @Override
            public CmsFloorRespDTO warpReturnObject(CmsFloor bo) {
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
    private CmsFloorRespDTO conversionObject(CmsFloor bo){
        CmsFloorRespDTO dto = new CmsFloorRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, dto, null, false);
        }
        // 1 查询站点服务，获取站点对应的名称
        if (StringUtil.isNotEmpty(dto.getSiteId())) {
            CmsSiteReqDTO reqDTO = new CmsSiteReqDTO();
            reqDTO.setId(bo.getSiteId());
            CmsSiteRespDTO respDTO = cmsSiteSV.queryCmsSite(reqDTO);
            if(respDTO != null){
                dto.setSiteName(respDTO.getSiteName());
            }
        }
        
        // 2 查询模板服务，获取模板对应的名称
        if (StringUtil.isNotEmpty(dto.getTemplateId())) {
            CmsTemplateReqDTO reqDTO = new CmsTemplateReqDTO();
            reqDTO.setId(bo.getTemplateId());
            CmsTemplateRespDTO respDTO = cmsTemplateSV.queryCmsTemplate(reqDTO);
            if(respDTO != null){
                dto.setTemplateName(respDTO.getTemplateName());
            }
        }
        
        // 3 查询内容位置服务，获取内容位置对应的名称
        if (StringUtil.isNotEmpty(dto.getPlaceId())) {
            CmsPlaceReqDTO cmsPlaceDTO = new CmsPlaceReqDTO();
            cmsPlaceDTO.setId(bo.getPlaceId());
            CmsPlaceRespDTO respDTO = cmsPlaceSV.queryCmsPlace(cmsPlaceDTO);
            if(respDTO != null){
                dto.setPlaceName(respDTO.getPlaceName());
            }
        }
        
        // 4 遍历将编码转中文 
        String statusZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_STATUS, dto.getStatus());
        dto.setStatusZH(statusZH);
        String dataSourceZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_DATA_SOURCE, dto.getDataSource());
        dto.setDataSourceZH(dataSourceZH);
        String countTypeZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_COUNT_TYPE, dto.getCountType());
        dto.setCountTypeZH(countTypeZH);
        
        return dto;
    }

}
