package com.zengshi.ecp.cms.service.common.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dao.mapper.common.CmsItemPropPreMapper;
import com.zengshi.ecp.cms.dao.model.CmsItemPropPre;
import com.zengshi.ecp.cms.dao.model.CmsItemPropPreCriteria;
import com.zengshi.ecp.cms.dao.model.CmsItemPropPreCriteria.Criteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsItemPropGroupPreRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsItemPropGroupValuePreRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPreReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPreRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsItemPropValuePreRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsItemPropPreSV;
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
@Service("cmsItemPropPreSV")
public class CmsItemPropPreSVImpl extends GeneralSQLSVImpl implements ICmsItemPropPreSV {

    @Resource(name = "SEQ_CMS_ITEM_PROP_PRE")
    private PaasSequence seqCmsItemPropPre;
    @Resource
    private CmsItemPropPreMapper cmsItemPropPreMapper;
    @Resource
    private ICmsPageInfoSV cmsPageInfoSV;

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsItemPropPreSV#saveCmsItemPropPre(com.zengshi.ecp.cms.dao.model.CmsItemPropPre)
     */
    @Override
    public CmsItemPropPreRespDTO addCmsItemPropPre(CmsItemPropPreReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsItemPropPre bo = new CmsItemPropPre();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        long id = seqCmsItemPropPre.nextValue();
        bo.setId(id);
        bo.setCreateTime(DateUtil.getSysDate());
        bo.setCreateStaff(dto.getStaff().getId());
        
        /*2.调dao层接口*/
        cmsItemPropPreMapper.insertSelective(bo);
        
        /*3.将结果返回*/
        CmsItemPropPreRespDTO respDTO = new CmsItemPropPreRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }

        return respDTO;
    }
    /**
     * 
     */
    @Override
    public void updateCmsItemPropPreList(List<CmsItemPropPreReqDTO> list) throws BusinessException {
    	if(!list.isEmpty()){
    		if(list != null && list.size() > 0){
    			CmsItemPropPreReqDTO item0=list.get(0);
    			CmsItemPropPreReqDTO deleteReq=new CmsItemPropPreReqDTO();
    			deleteReq.setPageId(item0.getPageId());
    			deleteReq.setItemId(item0.getItemId());
    			List<CmsItemPropPreRespDTO> listResp=this.queryCmsItemPropPreList(deleteReq);
    			for (CmsItemPropPreRespDTO deleteResp:listResp) {
    				this.deleteCmsItemPropPre(deleteResp.getId().toString());
				}
				for (CmsItemPropPreReqDTO item : list) {
					item.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);
					if(item.getId() == null){
						//新增
						this.addCmsItemPropPre(item);
					}else{
						//修改
						this.updateCmsItemPropPre(item);
					}
				}
			}
    	}
    }
    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsItemPropPreSV#updateCmsItemPropPre(com.zengshi.ecp.cms.dao.model.CmsItemPropPre)
     */
    @Override
    public CmsItemPropPreRespDTO updateCmsItemPropPre(CmsItemPropPreReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsItemPropPre bo = new CmsItemPropPre();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /*2.更新布局属性关系的原子方法*/
        return this.updateCmsItemPropPre(bo);
    }
    
    /** 
     * updateCmsItemPropPre:(更新布局属性关系的原子方法). <br/> 
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
    public CmsItemPropPreRespDTO updateCmsItemPropPre(CmsItemPropPre bo) throws BusinessException {
        cmsItemPropPreMapper.updateByPrimaryKeySelective(bo);
        CmsItemPropPreRespDTO respDTO = new CmsItemPropPreRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsItemPropPreSV#deleteCmsItemPropPre(java.lang.Long)
     */
    @Override
    public void deleteCmsItemPropPre(String id) throws BusinessException {
        CmsItemPropPre bo = new CmsItemPropPre();
        bo.setId(Long.parseLong(id));
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        bo.setUpdateTime(DateUtil.getSysDate());
        this.updateCmsItemPropPre(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsItemPropPreSV#deleteCmsItemPropPreBatch(java.util.List)
     */
    @Override
    public void deleteCmsItemPropPreBatch(List<String> list) throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.deleteCmsItemPropPre(id);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsItemPropPreSV#changeStatusCmsItemPropPre(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsItemPropPre(String id, String status) throws BusinessException {
        CmsItemPropPre bo = new CmsItemPropPre();
        bo.setId(Long.parseLong(id));
        bo.setStatus(status);
        this.updateCmsItemPropPre(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsItemPropPreSV#changeStatusCmsItemPropPreBatch(java.util.List,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsItemPropPreBatch(List<String> list, String status)
            throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.changeStatusCmsItemPropPre(id, status);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsItemPropPreSV#queryCmsItemPropPre(com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPreReqDTO)
     */
    @Override
    public CmsItemPropPreRespDTO queryCmsItemPropPre(CmsItemPropPreReqDTO dto) throws BusinessException {
        CmsItemPropPreRespDTO cmsItemPropPreRespDTO = new CmsItemPropPreRespDTO();
        if (StringUtil.isNotEmpty(dto.getId())) {
            /* 1.查询  */
            CmsItemPropPre bo = cmsItemPropPreMapper.selectByPrimaryKey(dto.getId());
            cmsItemPropPreRespDTO = conversionObject(bo);
        }
        
        return cmsItemPropPreRespDTO;
    }
    
    /**
     * TODO 查询布局属性关系列表，无分页（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsItemPropPreSV#queryCmsItemPropPreList(com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPreReqDTO)
     */
    @Override
    public List<CmsItemPropPreRespDTO> queryCmsItemPropPreList(CmsItemPropPreReqDTO dto) throws BusinessException {

        /*1.组装查询条件*/
        CmsItemPropPreCriteria cmsItemPropPreCriteria = new CmsItemPropPreCriteria();
        Criteria criteria = cmsItemPropPreCriteria.createCriteria();
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
        cmsItemPropPreCriteria.setOrderByClause("SORT_NO ASC,CREATE_TIME ASC");
        
        /*2.迭代查询结果*/
        List<CmsItemPropPreRespDTO> cmsItemPropPreRespDTOList =  new ArrayList<CmsItemPropPreRespDTO>();
        List<CmsItemPropPre> cmsItemPropPreList = cmsItemPropPreMapper.selectByExample(cmsItemPropPreCriteria);
        if(CollectionUtils.isNotEmpty(cmsItemPropPreList)){
            for(CmsItemPropPre bo :cmsItemPropPreList){
                CmsItemPropPreRespDTO cmsItemPropPreRespDTO = conversionObject(bo);
                cmsItemPropPreRespDTOList.add(cmsItemPropPreRespDTO);
            }
        }
        
        return cmsItemPropPreRespDTOList;

    }
    
    /**
     * TODO 查询布局属性关系列表，无分页（可选）.
     * 封装布局属性列表。
     * 待WAP功能测试完成后，再将WEB的功能统一改回用WAP，需调整此处，不需要判断页面类型。
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsItemPropPreSV#queryCmsItemPropPreList(com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPreReqDTO)
     */
    @Override
    public List<CmsItemPropPreRespDTO> queryCmsItemPropValuePreList(CmsItemPropPreReqDTO dto) throws BusinessException {

        //1.组装查询条件
        CmsItemPropPreCriteria cmsItemPropPreCriteria = new CmsItemPropPreCriteria();
        Criteria criteria = cmsItemPropPreCriteria.createCriteria();
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
        //状态查询 begin
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
        cmsItemPropPreCriteria.setOrderByClause("PROP_GROUP_ID ASC,CONTROL_PROP_ID ASC,SORT_NO ASC");
        
        //布局属性LIST
        List<CmsItemPropPreRespDTO> cmsItemPropPreRespDTOList =  new ArrayList<CmsItemPropPreRespDTO>();
        //查询结果
        List<CmsItemPropPre> cmsItemPropPreList = cmsItemPropPreMapper.selectByExample(cmsItemPropPreCriteria);
        //2.处理查询结果
        if(dto != null && dto.getPageId() != null){
            CmsPageInfoReqDTO pageInfoReqDTO = new CmsPageInfoReqDTO();
            pageInfoReqDTO.setId(dto.getPageId());
            CmsPageInfoRespDTO pageInfoRespDTO = cmsPageInfoSV.queryCmsPageInfo(pageInfoReqDTO);
            //此逻辑为处理WAP与WEB调用服务区别，待WAP功能调整结束后，再将WEB功能废弃，统一用WAP，同时这边的逻辑需改成原来的。
            if(pageInfoRespDTO != null && CmsConstants.PlatformType.CMS_PLATFORMTYPE_03.equals(pageInfoRespDTO.getPlatformType())){//平台类型为wap时，调移动端的服务
                //属性大组MAP
                Map<Integer, List<CmsItemPropPre>> itemPropPreMap = new HashMap<Integer, List<CmsItemPropPre>>();
                //根据属性大组字段分组（PROP_GROUP_ID）
                if(CollectionUtils.isNotEmpty(cmsItemPropPreList)){
                    for(int i=0;i<cmsItemPropPreList.size();i++){
                        CmsItemPropPre boi = cmsItemPropPreList.get(i);
                        List<CmsItemPropPre> cmsItemPropGroupPreList = new ArrayList<CmsItemPropPre>();
                        for(int j=0;j<cmsItemPropPreList.size();j++){
                            CmsItemPropPre boj = cmsItemPropPreList.get(j);
                            if(boi.getPropGroupId().equals(boj.getPropGroupId())){//将属性大组字段相同的放入一个LIST。
                                cmsItemPropGroupPreList.add(boj);
                            }
                        }
                        itemPropPreMap.put(boi.getPropGroupId()==null?0:boi.getPropGroupId(),cmsItemPropGroupPreList);
                    }
                }  
                //迭代大属性组MAP，根据KEY判断是否为属性组（0：普通属性，非0:组属性）
                if(itemPropPreMap != null){
                    for (Map.Entry<Integer, List<CmsItemPropPre>> entry : itemPropPreMap.entrySet()) {
                        List<CmsItemPropPre> itemPropPreList = entry.getValue();
                        if(entry.getKey() == 0){//普通属性
                            getItemPropPreRespDTOList(itemPropPreList,cmsItemPropPreRespDTOList);
                        }else{//大组属性
                            getItemPropSmallGroupPreRespDTOList(itemPropPreList,cmsItemPropPreRespDTOList);
                        }
                    }
                }
            }else{//页面类型非移动端时，调WEB的服务
                getItemPropPreRespDTOList(cmsItemPropPreList,cmsItemPropPreRespDTOList);
            }
        }
        return cmsItemPropPreRespDTOList;
    }
    
    /** 
     * getItemPropSmallGroupPreRespDTOList:(对组属性的数据进行封装). <br/> 
     * TODO(1、先根据属性小组字段（CONTROL_PROP_ID）进行封装，同时以小组ID为KEY(CONTROL_PROP_ID)构成MAP).<br/> 
     * TODO(2、迭代MAP，将小组数据按普通属性进行封装).<br/> 
     * TODO(3、返回组属性LIST).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param cmsItemPropPreList
     * @param cmsItemPropPreRespDTOList
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public List<CmsItemPropPreRespDTO> getItemPropSmallGroupPreRespDTOList(
            List<CmsItemPropPre> cmsItemPropPreList,
            List<CmsItemPropPreRespDTO> cmsItemPropPreRespDTOList) throws BusinessException {
        
        List<List<CmsItemPropGroupPreRespDTO>> itemPropGroupPreRespDTOList = new ArrayList<List<CmsItemPropGroupPreRespDTO>>();
        Map<Long, List<CmsItemPropPre>> itemPropPreMap = new HashMap<Long, List<CmsItemPropPre>>();
        //根据属性小组字段分组（CONTROL_PROP_ID）
        if(CollectionUtils.isNotEmpty(cmsItemPropPreList)){
            for(int i=0;i<cmsItemPropPreList.size();i++){
                CmsItemPropPre boi = cmsItemPropPreList.get(i);
                List<CmsItemPropPre> cmsItemPropSmallGroupPreList = new ArrayList<CmsItemPropPre>();
                for(int j=0;j<cmsItemPropPreList.size();j++){
                    CmsItemPropPre boj = cmsItemPropPreList.get(j);
                    if(boi.getControlPropId().equals(boj.getControlPropId())){//将属性小组字段相同的放入一个LIST。
                        cmsItemPropSmallGroupPreList.add(boj);
                    }
                }
                itemPropPreMap.put(boi.getControlPropId(),cmsItemPropSmallGroupPreList);
            }
        }  
        //迭代小组，将小组按普通属性方式进行数据封装
        if(itemPropPreMap != null){
            for (Map.Entry<Long, List<CmsItemPropPre>> entry : itemPropPreMap.entrySet()) {
                List<CmsItemPropPre> itemPropPreList = entry.getValue();
                List<CmsItemPropGroupPreRespDTO> cmsItemPropGroupPreRespDTOList = new ArrayList<CmsItemPropGroupPreRespDTO>();
                getItemPropGroupPreRespDTOList(itemPropPreList,cmsItemPropGroupPreRespDTOList);
                itemPropGroupPreRespDTOList.add(cmsItemPropGroupPreRespDTOList);
            }
        }
        //将属性组中的对象拷贝外层对象中，用于前店属性判断。
        CmsItemPropPreRespDTO cmsItemPropPreRespDTO = new CmsItemPropPreRespDTO();
        if(CollectionUtils.isNotEmpty(itemPropGroupPreRespDTOList)){
            List<CmsItemPropGroupPreRespDTO> itemPropGroupPreRespDTOList2 = itemPropGroupPreRespDTOList.get(0);
            if(CollectionUtils.isNotEmpty(itemPropGroupPreRespDTOList2)){
                CmsItemPropGroupPreRespDTO cmsItemPropGroupPreRespDTO = itemPropGroupPreRespDTOList2.get(0);
                if(cmsItemPropGroupPreRespDTO != null){
                    ObjectCopyUtil.copyObjValue(cmsItemPropGroupPreRespDTO, cmsItemPropPreRespDTO, null, false);
                } 
            }
        }
        cmsItemPropPreRespDTO.setItemPropGroupPreRespDTOList(itemPropGroupPreRespDTOList);
        cmsItemPropPreRespDTOList.add(cmsItemPropPreRespDTO);
        
        return cmsItemPropPreRespDTOList;
    }
    
    /** 
     * getItemPropGroupPreRespDTOList:(对组属性进行封装). <br/> 
     * TODO(1、根据属性ID一致放入一个LIST，同时以属性ID为KEY，构成MAP).<br/> 
     * TODO(2、迭代MAP，并将数据封装成小组数据的LIST).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param cmsItemPropPreList
     * @param cmsItemPropGroupPreRespDTOList
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public List<CmsItemPropGroupPreRespDTO> getItemPropGroupPreRespDTOList(
            List<CmsItemPropPre> cmsItemPropPreList,
            List<CmsItemPropGroupPreRespDTO> cmsItemPropGroupPreRespDTOList) throws BusinessException {
        
        Map<Long, List<CmsItemPropGroupValuePreRespDTO>> map = new HashMap<Long, List<CmsItemPropGroupValuePreRespDTO>>();
        if(CollectionUtils.isNotEmpty(cmsItemPropPreList)){
            for(int i=0;i<cmsItemPropPreList.size();i++){
                CmsItemPropPre boi = cmsItemPropPreList.get(i);
                List<CmsItemPropGroupValuePreRespDTO> cmsItemPropGroupValuePreRespDTOList = new ArrayList<CmsItemPropGroupValuePreRespDTO>();
                for(int j=0;j<cmsItemPropPreList.size();j++){
                    CmsItemPropPre boj = cmsItemPropPreList.get(j);
                    if(boi.getPropId().equals(boj.getPropId())){//将属性ID一致的放入一个LIST（如果为多选，该LIST.size>0）。
                        CmsItemPropGroupValuePreRespDTO cmsItemPropGroupValuePreRespDTO = changeToItemPropGroupValuePreRespDTO(boj);
                        cmsItemPropGroupValuePreRespDTOList.add(cmsItemPropGroupValuePreRespDTO);
                    }
                }
                map.put(boi.getPropId(), cmsItemPropGroupValuePreRespDTOList);
            }
        }  
        if(map != null){
            for (Map.Entry<Long, List<CmsItemPropGroupValuePreRespDTO>> entry : map.entrySet()) {
                if(CollectionUtils.isNotEmpty(entry.getValue())){
                    CmsItemPropGroupPreRespDTO cmsItemPropGroupPreRespDTO = new CmsItemPropGroupPreRespDTO();
                    CmsItemPropGroupValuePreRespDTO cmsItemPropGroupValuePreRespDTO = entry.getValue().get(0);
                    if(cmsItemPropGroupValuePreRespDTO != null){
                        ObjectCopyUtil.copyObjValue(cmsItemPropGroupValuePreRespDTO, cmsItemPropGroupPreRespDTO, null, false);
                    }
                    cmsItemPropGroupPreRespDTO.setItemPropGroupValuePreRespDTOList(entry.getValue());
                    cmsItemPropGroupPreRespDTOList.add(cmsItemPropGroupPreRespDTO);
                }
            }
        }
        return cmsItemPropGroupPreRespDTOList;
    }
    
    /** 
     * getItemPropPreRespDTOList:(对普通属性进行封装). <br/> 
     * TODO(1、根据属性ID一致放入一个LIST，同时以属性ID为KEY，构成MAP).<br/> 
     * TODO(2、迭代MAP，将数据组装成返回的页面LIST).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param cmsItemPropPreList
     * @param cmsItemPropPreRespDTOList
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public List<CmsItemPropPreRespDTO> getItemPropPreRespDTOList(
            List<CmsItemPropPre> cmsItemPropPreList,
            List<CmsItemPropPreRespDTO> cmsItemPropPreRespDTOList) throws BusinessException {
        
        Map<Long, List<CmsItemPropValuePreRespDTO>> map = new HashMap<Long, List<CmsItemPropValuePreRespDTO>>();
        if(CollectionUtils.isNotEmpty(cmsItemPropPreList)){
            for(int i=0;i<cmsItemPropPreList.size();i++){
                CmsItemPropPre boi = cmsItemPropPreList.get(i);
                List<CmsItemPropValuePreRespDTO> cmsItemPropValuePreRespDTOList = new ArrayList<CmsItemPropValuePreRespDTO>();
                for(int j=0;j<cmsItemPropPreList.size();j++){
                    CmsItemPropPre boj = cmsItemPropPreList.get(j);
                    if(boi.getPropId().equals(boj.getPropId())){//将属性ID一致的放入一个LIST（如果为多选，该LIST.size>0）。
                        CmsItemPropValuePreRespDTO cmsItemPropValuePreRespDTO = changeToItemPropValuePreRespDTO(boj);
                        cmsItemPropValuePreRespDTOList.add(cmsItemPropValuePreRespDTO);
                    }
                }
                map.put(boi.getPropId(), cmsItemPropValuePreRespDTOList);
            }
        }  
        if(map != null){
            for (Map.Entry<Long, List<CmsItemPropValuePreRespDTO>> entry : map.entrySet()) {
                if(CollectionUtils.isNotEmpty(entry.getValue())){
                    CmsItemPropPreRespDTO cmsItemPropPreRespDTO = new CmsItemPropPreRespDTO();
                    CmsItemPropValuePreRespDTO cmsItemPropValuePreRespDTO = entry.getValue().get(0);
                    if(cmsItemPropValuePreRespDTO != null){
                        ObjectCopyUtil.copyObjValue(cmsItemPropValuePreRespDTO, cmsItemPropPreRespDTO, null, false);
                    }
                    cmsItemPropPreRespDTO.setItemPropValuePreRespDTOList(entry.getValue());
                    cmsItemPropPreRespDTOList.add(cmsItemPropPreRespDTO);
                }
            }
        }
        return cmsItemPropPreRespDTOList;
    }


    /** 
     * TODO 查询布局属性关系，分页（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsItemPropPreSV#queryCmsItemPropPrePage(com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPreReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsItemPropPreRespDTO> queryCmsItemPropPrePage(CmsItemPropPreReqDTO dto)
            throws BusinessException {

        /* 1.根据条件检索布局属性关系 */
        CmsItemPropPreCriteria cmsItemPropPreCriteria = new CmsItemPropPreCriteria();
        Criteria criteria = cmsItemPropPreCriteria.createCriteria();
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
        cmsItemPropPreCriteria.setOrderByClause("SORT_NO ASC,CREATE_TIME ASC");
        cmsItemPropPreCriteria.setLimitClauseCount(dto.getPageSize());
        cmsItemPropPreCriteria.setLimitClauseStart(dto.getStartRowIndex());
        
        return super.queryByPagination(dto,cmsItemPropPreCriteria,false,new PaginationCallback<CmsItemPropPre, CmsItemPropPreRespDTO>(){

            @Override
            public List<CmsItemPropPre> queryDB(BaseCriteria criteria) {
                return cmsItemPropPreMapper.selectByExample((CmsItemPropPreCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return cmsItemPropPreMapper.countByExample((CmsItemPropPreCriteria)criteria);
            }

            @Override
            public CmsItemPropPreRespDTO warpReturnObject(CmsItemPropPre bo) {
                return conversionObject(bo);
            }
        
        });

    }
    
    /**
     * 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutTypeSV#deleteCmsLayoutTypeSelective(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutTypeReqDTO)
     */
    @Override
    public void deleteCmsItemPropPreByExample(CmsItemPropPreReqDTO dto) throws BusinessException {
        /* 1.根据条件检索布局属性关系 */
        CmsItemPropPreCriteria cmsItemPropPreCriteria = new CmsItemPropPreCriteria();
        Criteria criteria = cmsItemPropPreCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getItemId())) {
            criteria.andItemIdEqualTo(dto.getItemId());
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
        
        CmsItemPropPre bo = new CmsItemPropPre();
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        
        cmsItemPropPreMapper.updateByExampleSelective(bo, cmsItemPropPreCriteria);
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
    private CmsItemPropPreRespDTO conversionObject(CmsItemPropPre bo){
        CmsItemPropPreRespDTO dto = new CmsItemPropPreRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, dto, null, false);
        }
        
        return dto;
    }
    
    /** 
     * changeToItemPropValuePreRespDTO:(将bo转换成DTO，同时翻译有关字段). <br/> 
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
    private CmsItemPropValuePreRespDTO changeToItemPropValuePreRespDTO(CmsItemPropPre bo){
        CmsItemPropValuePreRespDTO dto = new CmsItemPropValuePreRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, dto, null, false);
        }
        
        return dto;
    }
    
    private CmsItemPropGroupValuePreRespDTO changeToItemPropGroupValuePreRespDTO(CmsItemPropPre bo){
        CmsItemPropGroupValuePreRespDTO dto = new CmsItemPropGroupValuePreRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, dto, null, false);
        }
        
        return dto;
    }

}
