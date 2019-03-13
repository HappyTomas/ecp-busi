package com.zengshi.ecp.cms.service.common.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.bouncycastle.cms.CMSContentInfoParser;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dao.mapper.common.CmsItemPropPubMapper;
import com.zengshi.ecp.cms.dao.model.CmsItemPropPub;
import com.zengshi.ecp.cms.dao.model.CmsItemPropPubCriteria;
import com.zengshi.ecp.cms.dao.model.CmsItemPropPubCriteria.Criteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsItemPropGroupPubRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsItemPropGroupValuePubRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPubReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPubRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsItemPropValuePubRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsItemPropPubSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsPageInfoSV;
import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
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
@Service("cmsItemPropPubSV")
public class CmsItemPropPubSVImpl extends GeneralSQLSVImpl implements ICmsItemPropPubSV {

    @Resource(name = "SEQ_CMS_ITEM_PROP_PUB")
    private PaasSequence seqCmsItemPropPub;
    @Resource
    private CmsItemPropPubMapper cmsItemPropPubMapper;
    @Resource
    private ICmsPageInfoSV cmsPageInfoSV;


    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsItemPropPubSV#saveCmsItemPropPub(com.zengshi.ecp.cms.dao.model.CmsItemPropPub)
     */
    @Override
    public CmsItemPropPubRespDTO addCmsItemPropPub(CmsItemPropPubReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsItemPropPub bo = new CmsItemPropPub();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        long id = seqCmsItemPropPub.nextValue();
        bo.setId(id);
        bo.setCreateTime(DateUtil.getSysDate());
        bo.setCreateStaff(dto.getStaff().getId());
        
        /*2.调dao层接口*/
        cmsItemPropPubMapper.insertSelective(bo);
        
        /*3.将结果返回*/
        CmsItemPropPubRespDTO respDTO = new CmsItemPropPubRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }

        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsItemPropPubSV#updateCmsItemPropPub(com.zengshi.ecp.cms.dao.model.CmsItemPropPub)
     */
    @Override
    public CmsItemPropPubRespDTO updateCmsItemPropPub(CmsItemPropPubReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsItemPropPub bo = new CmsItemPropPub();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /*2.更新楼层的原子方法*/
        return this.updateCmsItemPropPub(bo);
    }
    
    /** 
     * updateCmsItemPropPub:(更新楼层的原子方法). <br/> 
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
    public CmsItemPropPubRespDTO updateCmsItemPropPub(CmsItemPropPub bo) throws BusinessException {
        cmsItemPropPubMapper.updateByPrimaryKeySelective(bo);
        CmsItemPropPubRespDTO respDTO = new CmsItemPropPubRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsItemPropPubSV#deleteCmsItemPropPub(java.lang.Long)
     */
    @Override
    public void deleteCmsItemPropPub(String id) throws BusinessException {
        CmsItemPropPub bo = new CmsItemPropPub();
        bo.setId(Long.parseLong(id));
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        this.updateCmsItemPropPub(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsItemPropPubSV#deleteCmsItemPropPubBatch(java.util.List)
     */
    @Override
    public void deleteCmsItemPropPubBatch(List<String> list) throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.deleteCmsItemPropPub(id);
            }
        }
    }
    /**
     * 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsItemPropPubSV#deleteCmsItemPropPubByExample(com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPubReqDTO)
     */
    @Override
    public int deleteCmsItemPropPubByExample(CmsItemPropPubReqDTO dto) throws BusinessException {
        /*1.组装删除bo*/
        CmsItemPropPub bo = new CmsItemPropPub();
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /* 2.查询条件 */
        CmsItemPropPubCriteria cmsItemPropPubCriteria = new CmsItemPropPubCriteria();
        Criteria criteria = cmsItemPropPubCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getPageId())) {
            criteria.andPageIdEqualTo(dto.getPageId());
        }
        if (StringUtil.isNotEmpty(dto.getItemPropId())) {
            criteria.andItemPropIdEqualTo(dto.getItemPropId());
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            criteria.andStatusEqualTo(dto.getStatus());
        }
        if (StringUtil.isNotEmpty(dto.getItemId())) {
            criteria.andItemIdEqualTo(dto.getItemId());
        }
        if (StringUtil.isNotEmpty(dto.getPropId())) {
            criteria.andPropIdEqualTo(dto.getPropId());
        }
        return cmsItemPropPubMapper.updateByExampleSelective(bo, cmsItemPropPubCriteria);
    }
    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsItemPropPubSV#changeStatusCmsItemPropPub(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsItemPropPub(String id, String status) throws BusinessException {
        CmsItemPropPub bo = new CmsItemPropPub();
        bo.setId(Long.parseLong(id));
        bo.setStatus(status);
        this.updateCmsItemPropPub(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsItemPropPubSV#changeStatusCmsItemPropPubBatch(java.util.List,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsItemPropPubBatch(List<String> list, String status)
            throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.changeStatusCmsItemPropPub(id, status);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsItemPropPubSV#queryCmsItemPropPub(com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPubReqDTO)
     */
    @Override
    public CmsItemPropPubRespDTO queryCmsItemPropPub(CmsItemPropPubReqDTO dto) throws BusinessException {
        CmsItemPropPubRespDTO cmsItemPropPubRespDTO = new CmsItemPropPubRespDTO();
        if (StringUtil.isNotEmpty(dto.getId())) {
            /* 1.查询  */
            CmsItemPropPub bo = cmsItemPropPubMapper.selectByPrimaryKey(dto.getId());
            cmsItemPropPubRespDTO = conversionObject(bo);
        }
        
        return cmsItemPropPubRespDTO;
    }
    
    /**
     * TODO 查询楼层列表，无分页（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsItemPropPubSV#queryCmsItemPropPubList(com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPubReqDTO)
     */
    @Override
    public List<CmsItemPropPubRespDTO> queryCmsItemPropPubList(CmsItemPropPubReqDTO dto) throws BusinessException {

        /*1.组装查询条件*/
        CmsItemPropPubCriteria cmsItemPropPubCriteria = new CmsItemPropPubCriteria();
        Criteria criteria = cmsItemPropPubCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getItemId())) {
            criteria.andItemIdEqualTo(dto.getItemId());
        }
        if (StringUtil.isNotEmpty(dto.getItemPropId())) {
            criteria.andItemPropIdEqualTo(dto.getItemPropId());
        }
        if (StringUtil.isNotEmpty(dto.getPageId())) {
            criteria.andPageIdEqualTo(dto.getPageId());
        }
        if (StringUtil.isNotEmpty(dto.getPropValueId())) {
            criteria.andPropValueIdEqualTo(dto.getPropValueId());
        }
        if (StringUtil.isNotEmpty(dto.getIsAutobuild())) {
            criteria.andIsAutobuildEqualTo(dto.getIsAutobuild());
        }
        /*状态查询 begin*/
        if (dto.getStatusSet() == null) {
            dto.setStatusSet(new HashSet<String>());
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            dto.getStatusSet().add(dto.getStatus());
        }
        if (CollectionUtils.isNotEmpty(dto.getStatusSet())) {
            criteria.andStatusIn(new ArrayList<String>(dto.getStatusSet()));
        }
        /*状态查询 end*/
        cmsItemPropPubCriteria.setOrderByClause("SORT_NO ASC,CREATE_TIME ASC");
        
        /*2.迭代查询结果*/
        List<CmsItemPropPubRespDTO> cmsItemPropPubRespDTOList =  new ArrayList<CmsItemPropPubRespDTO>();
        List<CmsItemPropPub> cmsItemPropPubList = cmsItemPropPubMapper.selectByExample(cmsItemPropPubCriteria);
        if(CollectionUtils.isNotEmpty(cmsItemPropPubList)){
            for(CmsItemPropPub bo :cmsItemPropPubList){
                CmsItemPropPubRespDTO cmsItemPropPubRespDTO = conversionObject(bo);
                cmsItemPropPubRespDTOList.add(cmsItemPropPubRespDTO);
            }
        }
        
        return cmsItemPropPubRespDTOList;

    }
    
    /**
     * TODO 查询布局属性关系列表，无分页（可选）.
     * 1、根据属性ID，将属性ID一致的记录组装成LIST。
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsItemPropPubSV#queryCmsItemPropPubList(com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPubReqDTO)
     */
    @Override
    public List<CmsItemPropPubRespDTO> queryCmsItemPropValuePubList(CmsItemPropPubReqDTO dto) throws BusinessException {

        /*1.组装查询条件*/
        CmsItemPropPubCriteria cmsItemPropPubCriteria = new CmsItemPropPubCriteria();
        Criteria criteria = cmsItemPropPubCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getItemId())) {
            criteria.andItemIdEqualTo(dto.getItemId());
        }
        if (StringUtil.isNotEmpty(dto.getItemPropId())) {
            criteria.andItemPropIdEqualTo(dto.getItemPropId());
        }
        if (StringUtil.isNotEmpty(dto.getPageId())) {
            criteria.andPageIdEqualTo(dto.getPageId());
        }
        if (StringUtil.isNotEmpty(dto.getPropValueId())) {
            criteria.andPropValueIdEqualTo(dto.getPropValueId());
        }
        if (StringUtil.isNotEmpty(dto.getIsAutobuild())) {
            criteria.andIsAutobuildEqualTo(dto.getIsAutobuild());
        }
        /*状态查询 begin*/
        if (dto.getStatusSet() == null) {
            dto.setStatusSet(new HashSet<String>());
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            dto.getStatusSet().add(dto.getStatus());
        }
        if (CollectionUtils.isNotEmpty(dto.getStatusSet())) {
            criteria.andStatusIn(new ArrayList<String>(dto.getStatusSet()));
        }
        //状态查询 end
        cmsItemPropPubCriteria.setOrderByClause("PROP_GROUP_ID ASC,CONTROL_PROP_ID ASC,SORT_NO ASC");
        
        //布局属性LIST
        List<CmsItemPropPubRespDTO> cmsItemPropPubRespDTOList =  new ArrayList<CmsItemPropPubRespDTO>();
        //查询结果
        List<CmsItemPropPub> cmsItemPropPubList = cmsItemPropPubMapper.selectByExample(cmsItemPropPubCriteria);
        //2.处理查询结果
        if(dto != null && dto.getPageId() != null){
            CmsPageInfoReqDTO pageInfoReqDTO = new CmsPageInfoReqDTO();
            pageInfoReqDTO.setId(dto.getPageId());
            CmsPageInfoRespDTO pageInfoRespDTO = cmsPageInfoSV.queryCmsPageInfo(pageInfoReqDTO);
            //此逻辑为处理WAP与WEB调用服务区别，待WAP功能调整结束后，再将WEB功能废弃，统一用WAP，同时这边的逻辑需改成原来的。
            if(pageInfoRespDTO != null && CmsConstants.PlatformType.CMS_PLATFORMTYPE_03.equals(pageInfoRespDTO.getPlatformType()) ){//平台类型为wap时，调移动端的服务
                //属性大组MAP
                Map<Integer, List<CmsItemPropPub>> itemPropPubMap = new HashMap<Integer, List<CmsItemPropPub>>();
                //根据属性大组字段分组（PROP_GROUP_ID）
                if(CollectionUtils.isNotEmpty(cmsItemPropPubList)){
                    for(int i=0;i<cmsItemPropPubList.size();i++){
                        CmsItemPropPub boi = cmsItemPropPubList.get(i);
                        List<CmsItemPropPub> cmsItemPropGroupPubList = new ArrayList<CmsItemPropPub>();
                        for(int j=0;j<cmsItemPropPubList.size();j++){
                            CmsItemPropPub boj = cmsItemPropPubList.get(j);
                            if(boi.getPropGroupId().equals(boj.getPropGroupId())){//将属性大组字段相同的放入一个LIST。
                                cmsItemPropGroupPubList.add(boj);
                            }
                        }
                        itemPropPubMap.put(boi.getPropGroupId()==null?0:boi.getPropGroupId(),cmsItemPropGroupPubList);
                    }
                }  
                //迭代大属性组MAP，根据KEY判断是否为属性组（0：普通属性，非0:组属性）
                if(itemPropPubMap != null){
                    for (Map.Entry<Integer, List<CmsItemPropPub>> entry : itemPropPubMap.entrySet()) {
                        List<CmsItemPropPub> itemPropPubList = entry.getValue();
                        if(entry.getKey() == 0){//普通属性
                            getItemPropPubRespDTOList(itemPropPubList,cmsItemPropPubRespDTOList);
                        }else{//大组属性
                            getItemPropSmallGroupPubRespDTOList(itemPropPubList,cmsItemPropPubRespDTOList);
                        }
                    }
                }
            }else{//页面类型非移动端时，调WEB的服务
                getItemPropPubRespDTOList(cmsItemPropPubList,cmsItemPropPubRespDTOList);
                /*Map<Long, List<CmsItemPropValuePubRespDTO>> map = new HashMap<Long, List<CmsItemPropValuePubRespDTO>>();
                if(CollectionUtils.isNotEmpty(cmsItemPropPubList)){
                    for(int i=0;i<cmsItemPropPubList.size();i++){
                        CmsItemPropPub boi = cmsItemPropPubList.get(i);
                        List<CmsItemPropValuePubRespDTO> cmsItemPropValuePubRespDTOList = new ArrayList<CmsItemPropValuePubRespDTO>();
                        for(int j=0;j<cmsItemPropPubList.size();j++){
                            CmsItemPropPub boj = cmsItemPropPubList.get(j);
                            if(boi.getPropId().equals(boj.getPropId())){//将属性ID一至的放入一个LIST（如果为多选，该LIST.size>0）。
                                CmsItemPropValuePubRespDTO cmsItemPropValuePubRespDTO = changeToItemPropValuePubRespDTO(boj);
                                cmsItemPropValuePubRespDTOList.add(cmsItemPropValuePubRespDTO);
                            }
                        }
                        map.put(boi.getPropId(), cmsItemPropValuePubRespDTOList);
                    }
                }  
                if(map != null){
                    for (Map.Entry<Long, List<CmsItemPropValuePubRespDTO>> entry : map.entrySet()) {
                        if(CollectionUtils.isNotEmpty(entry.getValue())){
                            CmsItemPropPubRespDTO cmsItemPropPubRespDTO = new CmsItemPropPubRespDTO();
                            CmsItemPropValuePubRespDTO cmsItemPropValuePubRespDTO = entry.getValue().get(0);
                            if(cmsItemPropValuePubRespDTO != null){
                                ObjectCopyUtil.copyObjValue(cmsItemPropValuePubRespDTO, cmsItemPropPubRespDTO, null, false);
                            }
                            cmsItemPropPubRespDTO.setItemPropValuePubRespDTOList(entry.getValue());
                            cmsItemPropPubRespDTOList.add(cmsItemPropPubRespDTO);
                        }
                    }
                }*/
            }
        }
        return cmsItemPropPubRespDTOList;
    }
    
    /**
     * TODO 查询布局属性关系列表，无分页（可选）.
     * 1、根据属性ID，将属性ID一致的记录组装成LIST。
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsItemPropPubSV#queryCmsItemPropPubList(com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPubReqDTO)
     */
    @Override
    public List<CmsItemPropPubRespDTO> queryCmsItemPropValuePubListForWap(CmsItemPropPubReqDTO dto) throws BusinessException {

        /*1.组装查询条件*/
        CmsItemPropPubCriteria cmsItemPropPubCriteria = new CmsItemPropPubCriteria();
        Criteria criteria = cmsItemPropPubCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getPageId())) {
            criteria.andPageIdEqualTo(dto.getPageId());
        }
        if (StringUtil.isNotEmpty(dto.getItemId())) {
            criteria.andItemIdEqualTo(dto.getItemId());
        }
        if (StringUtil.isNotEmpty(dto.getPropValueId())) {
            criteria.andPropValueIdEqualTo(dto.getPropValueId());
        }
        if (StringUtil.isNotEmpty(dto.getIsAutobuild())) {
            criteria.andIsAutobuildEqualTo(dto.getIsAutobuild());
        }
        /*状态查询 begin*/
        if (dto.getStatusSet() == null) {
            dto.setStatusSet(new HashSet<String>());
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            dto.getStatusSet().add(dto.getStatus());
        }
        if (CollectionUtils.isNotEmpty(dto.getStatusSet())) {
            criteria.andStatusIn(new ArrayList<String>(dto.getStatusSet()));
        }
        /*状态查询 end*/
        cmsItemPropPubCriteria.setOrderByClause("PROP_GROUP_ID ASC,CONTROL_PROP_ID ASC");
        
        /*2.迭代查询结果*/
        List<CmsItemPropPub> cmsItemPropPubList = cmsItemPropPubMapper.selectByExample(cmsItemPropPubCriteria);
        //布局属性LIST
        List<CmsItemPropPubRespDTO> cmsItemPropPubRespDTOList = new ArrayList<CmsItemPropPubRespDTO>();
        //属性大组MAP
        Map<Integer, List<CmsItemPropPub>> itemPropPubMap = new HashMap<Integer, List<CmsItemPropPub>>();
        //根据属性大组字段分组（PROP_GROUP_ID）
        if(CollectionUtils.isNotEmpty(cmsItemPropPubList)){
            for(int i=0;i<cmsItemPropPubList.size();i++){
                CmsItemPropPub boi = cmsItemPropPubList.get(i);
                List<CmsItemPropPub> cmsItemPropGroupPubList = new ArrayList<CmsItemPropPub>();
                for(int j=0;j<cmsItemPropPubList.size();j++){
                    CmsItemPropPub boj = cmsItemPropPubList.get(j);
                    if(boi.getPropGroupId().equals(boj.getPropGroupId())){//将属性大组字段相同的放入一个LIST。
                        cmsItemPropGroupPubList.add(boj);
                    }
                }
                itemPropPubMap.put(boi.getPropGroupId()==null?0:boi.getPropGroupId(),cmsItemPropGroupPubList);
            }
        }  
        //迭代大属性组MAP，根据KEY判断是否为属性组（0：普通属性，非0:组属性）
        if(itemPropPubMap != null){
            for (Map.Entry<Integer, List<CmsItemPropPub>> entry : itemPropPubMap.entrySet()) {
                List<CmsItemPropPub> itemPropPubList = entry.getValue();
                if(entry.getKey() == 0){//普通属性
                    getItemPropPubRespDTOList(itemPropPubList,cmsItemPropPubRespDTOList);
                }else{//大组属性
                    getItemPropSmallGroupPubRespDTOList(itemPropPubList,cmsItemPropPubRespDTOList);
                }
            }
        }
        return cmsItemPropPubRespDTOList;
    }
    
    /** 
     * getItemPropSmallGroupPubRespDTOList:(对组属性的数据进行封装). <br/> 
     * TODO(1、先根据属性小组字段（CONTROL_PROP_ID）进行封装，同时以小组ID为KEY(CONTROL_PROP_ID)构成MAP).<br/> 
     * TODO(2、迭代MAP，将小组数据按普通属性进行封装).<br/> 
     * TODO(3、返回组属性LIST).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param cmsItemPropPubList
     * @param cmsItemPropPubRespDTOList
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public List<CmsItemPropPubRespDTO> getItemPropSmallGroupPubRespDTOList(
            List<CmsItemPropPub> cmsItemPropPubList,
            List<CmsItemPropPubRespDTO> cmsItemPropPubRespDTOList) throws BusinessException {
        
        List<List<CmsItemPropGroupPubRespDTO>> itemPropGroupPubRespDTOList = new ArrayList<List<CmsItemPropGroupPubRespDTO>>();
        Map<Long, List<CmsItemPropPub>> itemPropPubMap = new HashMap<Long, List<CmsItemPropPub>>();
        //根据属性小组字段分组（CONTROL_PROP_ID）
        if(CollectionUtils.isNotEmpty(cmsItemPropPubList)){
            for(int i=0;i<cmsItemPropPubList.size();i++){
                CmsItemPropPub boi = cmsItemPropPubList.get(i);
                List<CmsItemPropPub> cmsItemPropSmallGroupPubList = new ArrayList<CmsItemPropPub>();
                for(int j=0;j<cmsItemPropPubList.size();j++){
                    CmsItemPropPub boj = cmsItemPropPubList.get(j);
                    if(boi.getControlPropId().equals(boj.getControlPropId())){//将属性小组字段相同的放入一个LIST。
                        cmsItemPropSmallGroupPubList.add(boj);
                    }
                }
                itemPropPubMap.put(boi.getControlPropId(),cmsItemPropSmallGroupPubList);
            }
        }  
        //迭代小组，将小组按普通属性方式进行数据封装
        if(itemPropPubMap != null){
            for (Map.Entry<Long, List<CmsItemPropPub>> entry : itemPropPubMap.entrySet()) {
                List<CmsItemPropPub> itemPropPubList = entry.getValue();
                List<CmsItemPropGroupPubRespDTO> cmsItemPropGroupPubRespDTOList = new ArrayList<CmsItemPropGroupPubRespDTO>();
                getItemPropGroupPubRespDTOList(itemPropPubList,cmsItemPropGroupPubRespDTOList);
                itemPropGroupPubRespDTOList.add(cmsItemPropGroupPubRespDTOList);
            }
        }
        //将属性组中的对象拷贝外层对象中，用于前店属性判断。
        CmsItemPropPubRespDTO cmsItemPropPubRespDTO = new CmsItemPropPubRespDTO();
        if(CollectionUtils.isNotEmpty(itemPropGroupPubRespDTOList)){
            List<CmsItemPropGroupPubRespDTO> itemPropGroupPubRespDTOList2 = itemPropGroupPubRespDTOList.get(0);
            if(CollectionUtils.isNotEmpty(itemPropGroupPubRespDTOList2)){
                CmsItemPropGroupPubRespDTO cmsItemPropGroupPubRespDTO = itemPropGroupPubRespDTOList2.get(0);
                if(cmsItemPropGroupPubRespDTO != null){
                    ObjectCopyUtil.copyObjValue(cmsItemPropGroupPubRespDTO, cmsItemPropPubRespDTO, null, false);
                } 
            }
        }
        cmsItemPropPubRespDTO.setItemPropGroupPubRespDTOList(itemPropGroupPubRespDTOList);
        cmsItemPropPubRespDTOList.add(cmsItemPropPubRespDTO);
        
        return cmsItemPropPubRespDTOList;
    }
    
    /** 
     * getItemPropGroupPubRespDTOList:(对组属性进行封装). <br/> 
     * TODO(1、根据属性ID一致放入一个LIST，同时以属性ID为KEY，构成MAP).<br/> 
     * TODO(2、迭代MAP，并将数据封装成小组数据的LIST).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param cmsItemPropPubList
     * @param cmsItemPropGroupPubRespDTOList
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public List<CmsItemPropGroupPubRespDTO> getItemPropGroupPubRespDTOList(
            List<CmsItemPropPub> cmsItemPropPubList,
            List<CmsItemPropGroupPubRespDTO> cmsItemPropGroupPubRespDTOList) throws BusinessException {
        
        Map<Long, List<CmsItemPropGroupValuePubRespDTO>> map = new HashMap<Long, List<CmsItemPropGroupValuePubRespDTO>>();
        if(CollectionUtils.isNotEmpty(cmsItemPropPubList)){
            for(int i=0;i<cmsItemPropPubList.size();i++){
                CmsItemPropPub boi = cmsItemPropPubList.get(i);
                List<CmsItemPropGroupValuePubRespDTO> cmsItemPropGroupValuePubRespDTOList = new ArrayList<CmsItemPropGroupValuePubRespDTO>();
                for(int j=0;j<cmsItemPropPubList.size();j++){
                    CmsItemPropPub boj = cmsItemPropPubList.get(j);
                    if(boi.getPropId().equals(boj.getPropId())){//将属性ID一致的放入一个LIST（如果为多选，该LIST.size>0）。
                        CmsItemPropGroupValuePubRespDTO cmsItemPropGroupValuePubRespDTO = changeToItemPropGroupValuePubRespDTO(boj);
                        cmsItemPropGroupValuePubRespDTOList.add(cmsItemPropGroupValuePubRespDTO);
                    }
                }
                map.put(boi.getPropId(), cmsItemPropGroupValuePubRespDTOList);
            }
        }  
        if(map != null){
            for (Map.Entry<Long, List<CmsItemPropGroupValuePubRespDTO>> entry : map.entrySet()) {
                if(CollectionUtils.isNotEmpty(entry.getValue())){
                    CmsItemPropGroupPubRespDTO cmsItemPropGroupPubRespDTO = new CmsItemPropGroupPubRespDTO();
                    CmsItemPropGroupValuePubRespDTO cmsItemPropGroupValuePubRespDTO = entry.getValue().get(0);
                    if(cmsItemPropGroupValuePubRespDTO != null){
                        ObjectCopyUtil.copyObjValue(cmsItemPropGroupValuePubRespDTO, cmsItemPropGroupPubRespDTO, null, false);
                    }
                    cmsItemPropGroupPubRespDTO.setItemPropGroupValuePubRespDTOList(entry.getValue());
                    cmsItemPropGroupPubRespDTOList.add(cmsItemPropGroupPubRespDTO);
                }
            }
        }
        return cmsItemPropGroupPubRespDTOList;
    }
    
    /** 
     * getItemPropPubRespDTOList:(对普通属性进行封装). <br/> 
     * TODO(1、根据属性ID一致放入一个LIST，同时以属性ID为KEY，构成MAP).<br/> 
     * TODO(2、迭代MAP，将数据组装成返回的页面LIST).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param cmsItemPropPubList
     * @param cmsItemPropPubRespDTOList
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public List<CmsItemPropPubRespDTO> getItemPropPubRespDTOList(
            List<CmsItemPropPub> cmsItemPropPubList,
            List<CmsItemPropPubRespDTO> cmsItemPropPubRespDTOList) throws BusinessException {
        
        Map<Long, List<CmsItemPropValuePubRespDTO>> map = new HashMap<Long, List<CmsItemPropValuePubRespDTO>>();
        if(CollectionUtils.isNotEmpty(cmsItemPropPubList)){
            for(int i=0;i<cmsItemPropPubList.size();i++){
                CmsItemPropPub boi = cmsItemPropPubList.get(i);
                List<CmsItemPropValuePubRespDTO> cmsItemPropValuePubRespDTOList = new ArrayList<CmsItemPropValuePubRespDTO>();
                for(int j=0;j<cmsItemPropPubList.size();j++){
                    CmsItemPropPub boj = cmsItemPropPubList.get(j);
                    if(boi.getPropId().equals(boj.getPropId())){//将属性ID一至的放入一个LIST（如果为多选，该LIST.size>0）。
                        CmsItemPropValuePubRespDTO cmsItemPropValuePubRespDTO = changeToItemPropValuePubRespDTO(boj);
                        cmsItemPropValuePubRespDTOList.add(cmsItemPropValuePubRespDTO);
                    }
                }
                map.put(boi.getPropId(), cmsItemPropValuePubRespDTOList);
            }
        }  
        if(map != null){
            for (Map.Entry<Long, List<CmsItemPropValuePubRespDTO>> entry : map.entrySet()) {
                if(CollectionUtils.isNotEmpty(entry.getValue())){
                    CmsItemPropPubRespDTO cmsItemPropPubRespDTO = new CmsItemPropPubRespDTO();
                    CmsItemPropValuePubRespDTO cmsItemPropValuePubRespDTO = entry.getValue().get(0);
                    if(cmsItemPropValuePubRespDTO != null){
                        ObjectCopyUtil.copyObjValue(cmsItemPropValuePubRespDTO, cmsItemPropPubRespDTO, null, false);
                    }
                    cmsItemPropPubRespDTO.setItemPropValuePubRespDTOList(entry.getValue());
                    cmsItemPropPubRespDTOList.add(cmsItemPropPubRespDTO);
                }
            }
        }
        return cmsItemPropPubRespDTOList;
    }
    
    /** 
     * TODO 查询楼层，分页（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsItemPropPubSV#queryCmsItemPropPubPage(com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPubReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsItemPropPubRespDTO> queryCmsItemPropPubPage(CmsItemPropPubReqDTO dto)
            throws BusinessException {

        /* 1.根据条件检索楼层 */
        CmsItemPropPubCriteria cmsItemPropPubCriteria = new CmsItemPropPubCriteria();
        Criteria criteria = cmsItemPropPubCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getItemId())) {
            criteria.andItemIdEqualTo(dto.getItemId());
        }
        if (StringUtil.isNotEmpty(dto.getItemPropId())) {
            criteria.andItemPropIdEqualTo(dto.getItemPropId());
        }
        if (StringUtil.isNotEmpty(dto.getPageId())) {
            criteria.andPageIdEqualTo(dto.getPageId());
        }
        if (StringUtil.isNotEmpty(dto.getPropValueId())) {
            criteria.andPropValueIdEqualTo(dto.getPropValueId());
        }
        if (StringUtil.isNotEmpty(dto.getIsAutobuild())) {
            criteria.andIsAutobuildEqualTo(dto.getIsAutobuild());
        }
        /*状态查询 begin*/
        if (dto.getStatusSet() == null) {
            dto.setStatusSet(new HashSet<String>());
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            dto.getStatusSet().add(dto.getStatus());
        }
        if (CollectionUtils.isNotEmpty(dto.getStatusSet())) {
            criteria.andStatusIn(new ArrayList<String>(dto.getStatusSet()));
        }
        /*状态查询 end*/
        cmsItemPropPubCriteria.setOrderByClause("SORT_NO ASC,CREATE_TIME ASC");
        cmsItemPropPubCriteria.setLimitClauseCount(dto.getPageSize());
        cmsItemPropPubCriteria.setLimitClauseStart(dto.getStartRowIndex());
        
        return super.queryByPagination(dto,cmsItemPropPubCriteria,false,new PaginationCallback<CmsItemPropPub, CmsItemPropPubRespDTO>(){

            @Override
            public List<CmsItemPropPub> queryDB(BaseCriteria criteria) {
                return cmsItemPropPubMapper.selectByExample((CmsItemPropPubCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return cmsItemPropPubMapper.countByExample((CmsItemPropPubCriteria)criteria);
            }

            @Override
            public CmsItemPropPubRespDTO warpReturnObject(CmsItemPropPub bo) {
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
    private CmsItemPropPubRespDTO conversionObject(CmsItemPropPub bo){
        CmsItemPropPubRespDTO dto = new CmsItemPropPubRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, dto, null, false);
        }
        
        // 1 查询内容位置服务，获取内容位置对应的名称
        
        // 2.遍历将编码转中文 
        /*String statusZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_STATUS, dto.getStatus());
        dto.setStatusZH(statusZH);
        String isAutobuildZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.PUBLIC_PARAM_ISNOT, dto.getIsAutobuild());
        dto.setIsAutobuildZH(isAutobuildZH);*/
        
        return dto;
    }
    
    /** 
     * changeToItemPropValuePubRespDTO:(将bo转换成DTO，同时翻译有关字段). <br/> 
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
    private CmsItemPropValuePubRespDTO changeToItemPropValuePubRespDTO(CmsItemPropPub bo){
        CmsItemPropValuePubRespDTO dto = new CmsItemPropValuePubRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, dto, null, false);
        }
        
        // 2.遍历将编码转中文 
        /*String statusZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_STATUS, dto.getStatus());
        dto.setStatusZH(statusZH);
        String isAutobuildZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.PUBLIC_PARAM_ISNOT, dto.getIsAutobuild());
        dto.setIsAutobuildZH(isAutobuildZH);*/
        
        return dto;
    }
    
    private CmsItemPropGroupValuePubRespDTO changeToItemPropGroupValuePubRespDTO(CmsItemPropPub bo){
        CmsItemPropGroupValuePubRespDTO dto = new CmsItemPropGroupValuePubRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, dto, null, false);
        }
        
        // 2.遍历将编码转中文 
        /*String statusZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_STATUS, dto.getStatus());
        dto.setStatusZH(statusZH);
        String isAutobuildZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.PUBLIC_PARAM_ISNOT, dto.getIsAutobuild());
        dto.setIsAutobuildZH(isAutobuildZH);*/
        
        return dto;
    }

}
