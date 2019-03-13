package com.zengshi.ecp.coupon.service.common.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.coupon.dao.mapper.common.CoupTypeLogMapper;
import com.zengshi.ecp.coupon.dao.mapper.common.CoupTypeMapper;
import com.zengshi.ecp.coupon.dao.model.CoupType;
import com.zengshi.ecp.coupon.dao.model.CoupTypeCriteria;
import com.zengshi.ecp.coupon.dao.model.CoupTypeCriteria.Criteria;
import com.zengshi.ecp.coupon.dao.model.CoupTypeLog;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupTypeReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupTypeRespDTO;
import com.zengshi.ecp.coupon.dubbo.impl.converter.Converter;
import com.zengshi.ecp.coupon.service.common.interfaces.ICoupTypeSV;
import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-10-1 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class CoupTypeSVImpl extends GeneralSQLSVImpl implements ICoupTypeSV {

    private static final String MODULE = CoupTypeSVImpl.class.getName();

    @Resource
    private CoupTypeMapper coupTypeMapper;
    
    @Resource
    private CoupTypeLogMapper coupTypeLogMapper;

    @Resource
    private Converter<CoupTypeReqDTO, CoupType> coupTypeConverter;

    @Resource
    private Converter<CoupType, CoupTypeRespDTO> coupTypeRespDTOConverter;

    @Resource(name = "seq_coup_type_id")
    private PaasSequence seq_coup_type_id;
    
    @Resource(name = "seq_coup_type_log_id")
    private PaasSequence seq_coup_type_log_id;

    /**
     * 优惠券类型保存
     * 
     * @param coupTypeReqDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void saveCoupType(CoupTypeReqDTO coupTypeReqDTO) throws BusinessException {
        coupTypeReqDTO.setId(seq_coup_type_id.nextValue());
        coupTypeReqDTO.setCreateStaff(coupTypeReqDTO.getStaff().getId());
        coupTypeReqDTO.setCreateTime(DateUtil.getSysDate());
        coupTypeReqDTO.setSortNo(this.getMaxSortNo());//默认和id一致 如果超出integer范围呢？？？
        CoupType coupType=coupTypeConverter.convert(coupTypeReqDTO);
        //判断名称唯一性
        CoupTypeReqDTO coupTypeBean = new CoupTypeReqDTO();
        coupTypeBean.setCoupTypeName(coupTypeReqDTO.getCoupTypeName().trim());
        CoupTypeCriteria example = new CoupTypeCriteria();
        Criteria cr = example.createCriteria();
        // 类型名称
        if (!StringUtil.isEmpty(coupTypeReqDTO.getCoupTypeName())) {
            cr.andCoupTypeNameEqualTo(coupTypeReqDTO.getCoupTypeName());
        }
        List<CoupType> l=coupTypeMapper.selectByExample(example);
//        CoupTypeRespDTO bean= this.queryCoupTypeById(coupTypeBean);
        if(!CollectionUtils.isEmpty(l)){
        	throw new BusinessException("coupon.error.450025");
        }
        coupTypeMapper.insert(coupType);
        //log
        this.insertCoupTypeLog(coupTypeReqDTO);
    }
    
    
    
    private Integer getMaxSortNo( ){
    	CoupTypeCriteria example = new CoupTypeCriteria();
        example.setOrderByClause("sort_no desc");
        example.setLimitClauseCount(1);
        example.setLimitClauseStart(0);
        List<CoupType> coupType=  coupTypeMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(coupType)){
        	if(coupType.get(0)!=null){
            	if(coupType.get(0).getSortNo()!=null){
            		return coupType.get(0).getSortNo()+1;
            	}
            }
        }
        
        return 1;
    }
    /**
     * 优惠券类型 编辑保存
     * 
     * @param coupTypeReqDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void saveeditCoupType(CoupTypeReqDTO coupTypeReqDTO) throws BusinessException {
       
        coupTypeReqDTO.setUpdateStaff(coupTypeReqDTO.getStaff().getId());
        coupTypeReqDTO.setUpdateTime(DateUtil.getSysDate());
        //log
        this.insertCoupTypeLog(coupTypeReqDTO);
        
        CoupType coupType=coupTypeConverter.convert(coupTypeReqDTO);
        coupTypeMapper.updateByPrimaryKeySelective(coupType);
        
    }

    /**
     * 优惠券类型 edit
     * 
     * @param coupTypeReqDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void saveCoupTypeStatusById(CoupTypeReqDTO coupTypeReqDTO) throws BusinessException {
        
        if(StringUtil.isEmpty(coupTypeReqDTO.getUpdateStaff())){
            coupTypeReqDTO.setUpdateStaff(coupTypeReqDTO.getStaff().getId());
        }
        if(StringUtil.isEmpty(coupTypeReqDTO.getUpdateTime())){
            coupTypeReqDTO.setUpdateTime(DateUtil.getSysDate());
        }
        //log
        this.insertCoupTypeLog(coupTypeReqDTO);
        //更新
        coupTypeMapper.updateByPrimaryKeySelective(coupTypeConverter.convert(coupTypeReqDTO));
    }

    /**
     * 优惠券类型查询 编码必传
     * 
     * @param coupTypeReqDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public CoupTypeRespDTO queryCoupTypeById(CoupTypeReqDTO coupTypeReqDTO)
            throws BusinessException {
        
        //如果没有数据 在查询数据库
        CoupTypeCriteria example = new CoupTypeCriteria();
        this.initCoupTypeCond(coupTypeReqDTO, example);
        List<CoupType> l=coupTypeMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(l)){
            return null;
        }
       return coupTypeRespDTOConverter.convert(l.get(0));
    }

    /**
     * 优惠券类型查询
     * 
     * @param coupTypeReqDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<CoupTypeRespDTO> queryCoupTypeList(CoupTypeReqDTO coupTypeReqDTO)
            throws BusinessException {
        // 组织参数
        CoupTypeCriteria example = new CoupTypeCriteria();
        this.initCoupTypeCond(coupTypeReqDTO, example);
        example.setOrderByClause("sort_no asc,id desc");
        // 查询
        List<CoupType> list = coupTypeMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        // 返回解析
        List<CoupTypeRespDTO> reList = new ArrayList<CoupTypeRespDTO>();
        for (CoupType coupType : list) {
            reList.add(coupTypeRespDTOConverter.convert(coupType));
        }
        return reList;
    }

    /**
     * 优惠券类型查询
     * 
     * @param coupTypeReqDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PageResponseDTO<CoupTypeRespDTO> queryCoupTypePage(CoupTypeReqDTO coupTypeReqDTO)
            throws BusinessException {

        CoupTypeCriteria example = new CoupTypeCriteria();
        // 初始化查询条件
        this.initCoupTypeCond(coupTypeReqDTO, example);

        example.setLimitClauseCount(coupTypeReqDTO.getPageSize());
        example.setLimitClauseStart(coupTypeReqDTO.getStartRowIndex());
        example.setOrderByClause("sort_no desc,id desc");

        PageResponseDTO<CoupTypeRespDTO> page = null;
        // 返回查询分页结果集
        page = super.queryByPagination(coupTypeReqDTO, example, true,
                new PaginationCallback<CoupType, CoupTypeRespDTO>() {
                    // 查询记录集
                    @Override
                    public List<CoupType> queryDB(BaseCriteria example) {

                        return coupTypeMapper.selectByExample((CoupTypeCriteria) example);
                    }

                    // 查询总记录数
                    @Override
                    public long queryTotal(BaseCriteria example) {

                        return coupTypeMapper.countByExample((CoupTypeCriteria) example);
                    }

                    // 查询结果转换
                    @Override
                    public CoupTypeRespDTO warpReturnObject(CoupType t) {
                        return coupTypeRespDTOConverter.convert(t);
                    }
                });

        return page;

    }

    /**
     * 优惠券类型 条件组织
     * 
     * @param coupTypeReqDTO
     * @param example
     * @author huangjx
     */
    private void initCoupTypeCond(CoupTypeReqDTO coupTypeReqDTO, CoupTypeCriteria example) {

        if (coupTypeReqDTO == null) {
            return;
        }
        Criteria cr = example.createCriteria();
        // ID 查询
        if (!StringUtil.isEmpty(coupTypeReqDTO.getId())) {
            cr.andIdEqualTo(coupTypeReqDTO.getId());
        }
        // 类型名称
        if (!StringUtil.isEmpty(coupTypeReqDTO.getCoupTypeName())) {
            cr.andCoupTypeNameLike("%"+coupTypeReqDTO.getCoupTypeName()+"%");
        }
        // 领取规则
        if (!StringUtil.isEmpty(coupTypeReqDTO.getGetRuleCode())) {
            cr.andGetRuleCodeEqualTo(coupTypeReqDTO.getGetRuleCode());
        }
        // 状态
        if (!StringUtil.isEmpty(coupTypeReqDTO.getStatus())) {
            cr.andStatusEqualTo(coupTypeReqDTO.getStatus());
        }
        // 类型
        if (!StringUtil.isEmpty(coupTypeReqDTO.getTypeLimit())) {
            cr.andTypeLimitEqualTo(coupTypeReqDTO.getTypeLimit());
        }
        // 使用规则
        if (!StringUtil.isEmpty(coupTypeReqDTO.getUseRuleCode())) {
            cr.andUseRuleCodeEqualTo(coupTypeReqDTO.getUseRuleCode());
        }
        // 开始时间
        if (!StringUtil.isEmpty(coupTypeReqDTO.getCreateTimeStart())) {
            cr.andCreateTimeGreaterThanOrEqualTo(new Timestamp(coupTypeReqDTO.getCreateTimeStart().getTime()));
        }
        // 截止时间
        if (!StringUtil.isEmpty(coupTypeReqDTO.getCreateTimeEnd())) {
            cr.andCreateTimeLessThanOrEqualTo(new Timestamp(coupTypeReqDTO.getCreateTimeEnd().getTime()));
        }
        
    }
    
    /**
     * 变更log记录
     * @param coupTypeReqDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void insertCoupTypeLog(CoupTypeReqDTO coupTypeReqDTO) throws BusinessException {

        CoupTypeLog coupTypeLog=new CoupTypeLog();
        // 根据id获得 record
        CoupType coupType = coupTypeMapper.selectByPrimaryKey(coupTypeReqDTO.getId());
        
        ObjectCopyUtil.copyObjValue(coupType, coupTypeLog, "", false);
        
        // create staff 非空
        if (coupTypeReqDTO.getCreateStaff() != null) {
            coupTypeLog.setCreateStaffLog(coupTypeReqDTO.getCreateStaff());
        } 
        // update staff 非空
        if (coupTypeReqDTO.getUpdateStaff() != null) {
            coupTypeLog.setCreateStaffLog(coupTypeReqDTO.getUpdateStaff());
        } 
       // 系统时间
        if(StringUtil.isEmpty(coupTypeLog.getCreateTimeLog())){
            coupTypeLog.setCreateTimeLog(DateUtil.getSysDate());
        }
       
        //操作人
        if(StringUtil.isEmpty(coupTypeLog.getCreateStaffLog())){
            coupTypeLog.setCreateStaffLog(coupType.getCreateStaff());
        }
        coupTypeLog.setId(seq_coup_type_log_id.nextValue());
        coupTypeLog.setTypeId(coupType.getId());
        coupTypeLogMapper.insert(coupTypeLog);
    }
}
