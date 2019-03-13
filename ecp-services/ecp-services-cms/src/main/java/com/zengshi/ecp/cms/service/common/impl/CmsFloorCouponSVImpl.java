package com.zengshi.ecp.cms.service.common.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dao.mapper.common.CmsFloorCouponMapper;
import com.zengshi.ecp.cms.dao.model.CmsFloorCoupon;
import com.zengshi.ecp.cms.dao.model.CmsFloorCouponCriteria;
import com.zengshi.ecp.cms.dao.model.CmsFloorCouponCriteria.Criteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorCouponReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorCouponRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorCouponSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsPlaceSV;
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

@Service("cmsFloorCouponSV")
public class CmsFloorCouponSVImpl extends GeneralSQLSVImpl implements ICmsFloorCouponSV{

	@Resource(name = "SEQ_CMS_FLOOR_COUPON")
    private PaasSequence seqCmsFloorCoupon;
    
    @Resource
    private CmsFloorCouponMapper cmsFloorCouponMapper;
    
    @Resource
    private ICmsPlaceSV cmsPlaceSV;
    
    @Resource
    private ICmsFloorSV cmsFloorSV;
    
    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorCouponSV#saveCmsFloorCoupon(com.zengshi.ecp.cms.dao.model.CmsFloorCoupon)
     */
    @Override
    public CmsFloorCouponRespDTO addCmsFloorCoupon(CmsFloorCouponReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsFloorCoupon bo = new CmsFloorCoupon();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        long id = seqCmsFloorCoupon.nextValue();
        bo.setId(id);
        bo.setCreateTime(DateUtil.getSysDate());
        bo.setCreateStaff(dto.getStaff().getId());
        
        /*2.调dao层接口*/
        cmsFloorCouponMapper.insertSelective(bo);
        
        /*3.将结果返回*/
        CmsFloorCouponRespDTO respDTO = new CmsFloorCouponRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorCouponSV#saveCmsFloorCoupon(com.zengshi.ecp.cms.dao.model.CmsFloorCoupon)
     */
    @Override
    public List<CmsFloorCouponRespDTO> addCmsFloorCouponBatch(List<CmsFloorCouponReqDTO> list) throws BusinessException {
		List<CmsFloorCouponRespDTO> cmsFloorCouponRespDTOs = new ArrayList<CmsFloorCouponRespDTO>();
		for (int i = 0; i < list.size(); i++) {
			cmsFloorCouponRespDTOs.add(this.addCmsFloorCoupon(list.get(i)));
		}
		return cmsFloorCouponRespDTOs;
    }
    
    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorCouponSV#updateCmsFloorCoupon(com.zengshi.ecp.cms.dao.model.CmsFloorCoupon)
     */
    @Override
    public List<CmsFloorCouponRespDTO> updateCmsFloorCouponBatch(List<CmsFloorCouponReqDTO> list) throws BusinessException {
       List<CmsFloorCouponRespDTO> cmsFloorCouponRespDTOs = new ArrayList<CmsFloorCouponRespDTO>();
       for(int i=0;i<list.size();i++){
    	   cmsFloorCouponRespDTOs.add(this.queryCmsFloorCoupon(list.get(i)));
       }
       return cmsFloorCouponRespDTOs;
    }
    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorCouponSV#updateCmsFloorCoupon(com.zengshi.ecp.cms.dao.model.CmsFloorCoupon)
     */
    @Override
    public CmsFloorCouponRespDTO updateCmsFloorCoupon(CmsFloorCouponReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsFloorCoupon bo = new CmsFloorCoupon();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /*2.更新楼层商品的原子方法*/
        return this.updateCmsFloorCoupon(bo);
    }
    
    /** 
     * updateCmsFloorCoupon:(更新楼层商品的原子方法). <br/> 
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
    public CmsFloorCouponRespDTO updateCmsFloorCoupon(CmsFloorCoupon bo) throws BusinessException {
        cmsFloorCouponMapper.updateByPrimaryKeySelective(bo);
        /*3.将结果返回*/
        CmsFloorCouponRespDTO respDTO = new CmsFloorCouponRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /** 
     * updateCmsFloorCouponByExample:(更新楼层商品的原子方法). <br/> 
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
    public CmsFloorCouponRespDTO updateCmsFloorCouponByExample(CmsFloorCouponReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsFloorCoupon bo = new CmsFloorCoupon();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /* 2.根据条件检索楼层商品 */
        CmsFloorCouponCriteria cmsFloorCouponCriteria = new CmsFloorCouponCriteria();
        Criteria criteria = cmsFloorCouponCriteria.createCriteria();
        if (StringUtil.isNotEmpty(bo.getCouponId())) {
            criteria.andCouponIdEqualTo(bo.getCouponId());
        }
        cmsFloorCouponMapper.updateByExampleSelective(bo, cmsFloorCouponCriteria);
        
        /*3.将结果返回*/
        CmsFloorCouponRespDTO respDTO = new CmsFloorCouponRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorCouponSV#deleteCmsFloorCoupon(java.lang.Long)
     */
    @Override
    public void deleteCmsFloorCoupon(String id) throws BusinessException {
        CmsFloorCoupon bo = new CmsFloorCoupon();
        bo.setId(Long.valueOf(id));
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        this.updateCmsFloorCoupon(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorCouponSV#deleteCmsFloorCouponBatch(java.util.List)
     */
    @Override
    public void deleteCmsFloorCouponBatch(List<String> list) throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.deleteCmsFloorCoupon(id);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorCouponSV#changeStatusCmsFloorCoupon(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsFloorCoupon(String id, String status) throws BusinessException {
        CmsFloorCoupon bo = new CmsFloorCoupon();
        bo.setId(Long.valueOf(id));
        bo.setStatus(status);
        this.updateCmsFloorCoupon(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorCouponSV#changeStatusCmsFloorCouponBatch(java.util.List,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsFloorCouponBatch(List<String> list, String status)
            throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.changeStatusCmsFloorCoupon(id, status);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorCouponSV#queryCmsFloorCoupon(com.zengshi.ecp.cms.dubbo.dto.CmsFloorCouponReqDTO)
     */
    @Override
    public CmsFloorCouponRespDTO queryCmsFloorCoupon(CmsFloorCouponReqDTO dto) throws BusinessException {
        CmsFloorCouponRespDTO cmsFloorCouponRespDTO = new CmsFloorCouponRespDTO();
        if (StringUtil.isNotEmpty(dto.getId())) {
            /* 1.查询  */
            CmsFloorCoupon bo = cmsFloorCouponMapper.selectByPrimaryKey(dto.getId());
            cmsFloorCouponRespDTO = conversionObject(bo);
        }
        
        return cmsFloorCouponRespDTO;
    }
    
    /**
     * TODO 查询楼层商品列表，无分页（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorCouponSV#queryCmsFloorCouponList(com.zengshi.ecp.cms.dubbo.dto.CmsFloorCouponReqDTO)
     */
    @Override
    public List<CmsFloorCouponRespDTO> queryCmsFloorCouponList(CmsFloorCouponReqDTO dto) throws BusinessException {

        /*1.组装查询条件*/
        CmsFloorCouponCriteria cmsFloorCouponCriteria = new CmsFloorCouponCriteria();
        com.zengshi.ecp.cms.dao.model.CmsFloorCouponCriteria.Criteria criteria = cmsFloorCouponCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
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
        if (StringUtil.isNotEmpty(dto.getPlaceId())) {
            criteria.andPlaceIdEqualTo(dto.getPlaceId());
        }
        if (StringUtil.isNotEmpty(dto.getFloorId())) {
            criteria.andFloorIdEqualTo(dto.getFloorId());
        }
        cmsFloorCouponCriteria.setOrderByClause("SORT_NO ASC, CREATE_TIME DESC");
        
        /*2.迭代查询结果*/
        List<CmsFloorCouponRespDTO> cmsFloorCouponRespDTOList =  new ArrayList<CmsFloorCouponRespDTO>();
        List<CmsFloorCoupon> cmsFloorCouponList = cmsFloorCouponMapper.selectByExample(cmsFloorCouponCriteria);
        if(CollectionUtils.isNotEmpty(cmsFloorCouponList)){
            for(CmsFloorCoupon bo :cmsFloorCouponList){
                CmsFloorCouponRespDTO cmsFloorCouponRespDTO = conversionObject(bo);
                cmsFloorCouponRespDTOList.add(cmsFloorCouponRespDTO);
            }
        }
        
        return cmsFloorCouponRespDTOList;
    }


    /** 
     * TODO 查询楼层商品，分页（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorCouponSV#queryCmsFloorCouponPage(com.zengshi.ecp.cms.dubbo.dto.CmsFloorCouponReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsFloorCouponRespDTO> queryCmsFloorCouponPage(CmsFloorCouponReqDTO dto)
            throws BusinessException {

        /* 1.根据条件检索楼层商品 */
        CmsFloorCouponCriteria cmsFloorCouponCriteria = new CmsFloorCouponCriteria();
        com.zengshi.ecp.cms.dao.model.CmsFloorCouponCriteria.Criteria criteria = cmsFloorCouponCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
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
        if (StringUtil.isNotEmpty(dto.getPlaceId())) {
            criteria.andPlaceIdEqualTo(dto.getPlaceId());
        }
        if (StringUtil.isNotEmpty(dto.getFloorId())) {
            criteria.andFloorIdEqualTo(dto.getFloorId());
        }
        cmsFloorCouponCriteria.setOrderByClause("SORT_NO ASC, CREATE_TIME DESC");
        cmsFloorCouponCriteria.setLimitClauseCount(dto.getPageSize());
        cmsFloorCouponCriteria.setLimitClauseStart(dto.getStartRowIndex());
        
        return super.queryByPagination(dto,cmsFloorCouponCriteria,false,new PaginationCallback<CmsFloorCoupon, CmsFloorCouponRespDTO>(){

            @Override
            public List<CmsFloorCoupon> queryDB(BaseCriteria criteria) {
                return cmsFloorCouponMapper.selectByExample((CmsFloorCouponCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return cmsFloorCouponMapper.countByExample((CmsFloorCouponCriteria)criteria);
            }

            @Override
            public CmsFloorCouponRespDTO warpReturnObject(CmsFloorCoupon bo) {
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
    private CmsFloorCouponRespDTO conversionObject(CmsFloorCoupon bo){
        CmsFloorCouponRespDTO dto = new CmsFloorCouponRespDTO();
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
        // 2 根据内容位置ID获取楼层信息
        if (StringUtil.isNotEmpty(dto.getFloorId())) {
            CmsFloorReqDTO cmsFloorDTO = new CmsFloorReqDTO();
            cmsFloorDTO.setId(dto.getFloorId());
            CmsFloorRespDTO cmsFloorRespDTO = cmsFloorSV.queryCmsFloor(cmsFloorDTO);
            if (cmsFloorRespDTO != null) {
                dto.setFloorName(cmsFloorRespDTO.getFloorName());
            }
        }
        
        //4.遍历将编码转中文 
        String statusZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_STATUS, dto.getStatus());
        dto.setStatusZH(statusZH);
        return dto;
    }

    @Override
    public void deleteCmsFloorCoupon(CmsFloorCouponReqDTO dto) throws BusinessException {
        boolean updateable = false;
        CmsFloorCoupon bo = new CmsFloorCoupon();
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());

        CmsFloorCouponCriteria cmsFloorCouponCriteria = new CmsFloorCouponCriteria();
        CmsFloorCouponCriteria.Criteria criteria = cmsFloorCouponCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
            updateable = true;
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            criteria.andStatusEqualTo(dto.getStatus());
            updateable = true;
        }
        if (StringUtil.isNotEmpty(dto.getPlaceId())) {
            criteria.andPlaceIdEqualTo(dto.getPlaceId());
            updateable = true;
        }
        if (StringUtil.isNotEmpty(dto.getFloorId())) {
            criteria.andFloorIdEqualTo(dto.getFloorId());
            updateable = true;
        }
        if (StringUtil.isNotEmpty(dto.getCouponId())) {
            criteria.andCouponIdEqualTo(dto.getCouponId());
            updateable = true;
        }

        if(updateable){
            cmsFloorCouponMapper.updateByExampleSelective(bo, cmsFloorCouponCriteria);
        }
    }
}
