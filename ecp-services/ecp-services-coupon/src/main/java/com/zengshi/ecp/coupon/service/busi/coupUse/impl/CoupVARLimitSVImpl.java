package com.zengshi.ecp.coupon.service.busi.coupUse.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.coupon.dao.mapper.busi.CoupVarLimitMapper;
import com.zengshi.ecp.coupon.dao.model.CoupVarLimit;
import com.zengshi.ecp.coupon.dao.model.CoupVarLimitCriteria;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupCheckParmReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupVarLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupLimitCheckRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupVarLimitRespDTO;
import com.zengshi.ecp.coupon.dubbo.impl.converter.Converter;
import com.zengshi.ecp.coupon.dubbo.util.CouponConstants;
import com.zengshi.ecp.coupon.service.busi.combination.interfaces.ICoupCreateInitSV;
import com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupVARLimitSV;
import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

public class CoupVARLimitSVImpl extends GeneralSQLSVImpl implements ICoupVARLimitSV{


	@Resource(name = "seq_coup_var_limit_id")
    private PaasSequence seq_coup_var_limit_id;
	@Resource
	private Converter<CoupVarLimitReqDTO,CoupVarLimit> coupVarLimitConverter;
	@Resource
	private Converter<CoupVarLimit, CoupVarLimitRespDTO> coupVarLimitRespDTOConverter;
	@Resource
	private CoupVarLimitMapper coupVarLimitMapper;
	@Resource
    private ICoupCreateInitSV coupCreateInitSV;
	
	@Override
	public void save(CoupReqDTO coupReqDTO) throws BusinessException {
		List<CoupVarLimitReqDTO> coupGatgLimitReqDTOs = coupReqDTO.getCoupVarLimitReqDTOs();
		if(CollectionUtils.isNotEmpty(coupGatgLimitReqDTOs)){
			for (CoupVarLimitReqDTO coupVarLimitReqDTO : coupGatgLimitReqDTOs) {
				//优惠券品类限制信息入参
				coupVarLimitReqDTO.setCoupId(coupReqDTO.getCoupInfoReqDTO().getId());//优惠券信息ID
				coupVarLimitReqDTO.setId(seq_coup_var_limit_id.nextValue());
				coupVarLimitReqDTO.setCreateStaff(coupReqDTO.getStaff().getId());
				coupVarLimitReqDTO.setCreateTime(DateUtil.getSysDate());
				//如果是 编辑
				if(CouponConstants.CoupSys.edit_EDIT.equals(coupReqDTO.getEdit())){
					coupVarLimitReqDTO.setUpdateStaff(coupReqDTO.getStaff().getId());
					coupVarLimitReqDTO.setUpdateTime(DateUtil.getSysDate());
				}
				if(StringUtil.isEmpty(coupVarLimitReqDTO.getStatus())){
				     coupVarLimitReqDTO.setStatus(CouponConstants.CoupSys.status_1);
	              }
				CoupVarLimit coupVarLimit = coupVarLimitConverter.convert(coupVarLimitReqDTO);
				coupVarLimitMapper.insert(coupVarLimit);
			}
		}
	}

	@Override
	public void update(CoupReqDTO coupReqDTO) throws BusinessException {
		// 逻辑删除此coup_id的相关信息
		this.deleteStatus(coupReqDTO);
		//设置编辑
		coupReqDTO.setEdit(CouponConstants.CoupSys.edit_EDIT);
		// 添加此coup_id的相关信息
		this.save(coupReqDTO);
	}

	/**
	 * 
	 * 查询优惠券共同使用规则表.  
	 * 
	 * @author huanghe5 
	 * @param CoupVarLimitReqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@Override
	public List<CoupVarLimitRespDTO> queryCoupVARList(
			CoupVarLimitReqDTO coupVarLimitReqDTO) throws BusinessException {
		CoupVarLimitCriteria example = new CoupVarLimitCriteria();
		coupCreateInitSV.initCoupVarLimit(coupVarLimitReqDTO, example);
		if(StringUtil.isBlank(coupVarLimitReqDTO.getStatus())){
			coupVarLimitReqDTO.setStatus(CouponConstants.CoupSys.status_1);
		}
		List<CoupVarLimit> beans = coupVarLimitMapper.selectByExample(example);
		
		if (CollectionUtils.isEmpty(beans)) {
            return null;
        }
		
		List<CoupVarLimitRespDTO> reList = new ArrayList<CoupVarLimitRespDTO>();
		for (CoupVarLimit coupVarLimit : beans) {
			reList.add(coupVarLimitRespDTOConverter.convert(coupVarLimit));
		}
		
		return reList;
	}

	public void deleteStatus(CoupReqDTO coupReqDTO) throws BusinessException {
		CoupVarLimit bean = new CoupVarLimit();
		bean.setStatus(CouponConstants.CoupSys.status_0);//失效
		CoupVarLimitCriteria example = new CoupVarLimitCriteria();
		CoupVarLimitCriteria.Criteria cr = example.createCriteria();
		
		if (coupReqDTO.getCoupInfoReqDTO().getId() != null && coupReqDTO.getCoupInfoReqDTO().getId() > 0) {
			cr.andCoupIdEqualTo(coupReqDTO.getCoupInfoReqDTO().getId());// 优惠券信息ID
			
			coupVarLimitMapper.updateByExampleSelective(bean, example);
		}else if(coupReqDTO.getCoupBlackLimitReqDTO().getCoupId()!=null && coupReqDTO.getCoupBlackLimitReqDTO().getCoupId()>0){
			cr.andCoupIdEqualTo(coupReqDTO.getCoupBlackLimitReqDTO().getCoupId());// 优惠券信息ID
			
			coupVarLimitMapper.updateByExampleSelective(bean, example);
		}
	}

	/**
	 * 
	 * checkCoupLimit:下单时校验优惠券限制条件. <br/> 
	 * 
	 * @author huanghe5
	 * @param ordCartCommRequest
	 * @param coupId
	 * @return Judge : 1:通过 0:不通过  2:多个单品组合
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@Override
	public CoupLimitCheckRespDTO checkCoupLimit(ROrdCartItemCommRequest ordCartItemCommRequest,
			CoupCheckParmReqDTO coupCheckParmReqDTO,CoupLimitCheckRespDTO coupLimitCheckRespDTO) throws BusinessException {
		coupLimitCheckRespDTO.setCoupId(coupCheckParmReqDTO.getCoupId());
		coupLimitCheckRespDTO.setGdsId(ordCartItemCommRequest.getGdsId());
		coupLimitCheckRespDTO.setSkuId(ordCartItemCommRequest.getSkuId());
		CoupVarLimitReqDTO coupVarLimitReqDTO = new CoupVarLimitReqDTO();
		coupVarLimitReqDTO.setCoupId(coupCheckParmReqDTO.getCoupId());
		coupVarLimitReqDTO.setStatus(CouponConstants.CoupSys.status_1);
		//根据coupId查询限制规则信息
		List<CoupVarLimitRespDTO> bean = this.queryCoupVARList(coupVarLimitReqDTO);
		if(CollectionUtils.isNotEmpty(bean)){
			List<Long> beans = new ArrayList<Long>();
			for (CoupVarLimitRespDTO coupVarLimitRespDTO : bean) {
				beans.add(coupVarLimitRespDTO.getCoupId2());
			}
			coupLimitCheckRespDTO.setCoupVarLong(beans);
		}
		coupLimitCheckRespDTO.setJudge(CouponConstants.CoupSys.judge_1);
		return coupLimitCheckRespDTO;
	}
	
	
	
}

