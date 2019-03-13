package com.zengshi.ecp.coupon.service.busi.coupUse.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.coupon.dao.mapper.busi.CoupFullLimitMapper;
import com.zengshi.ecp.coupon.dao.model.CoupFullLimit;
import com.zengshi.ecp.coupon.dao.model.CoupFullLimitCriteria;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupCheckParmReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupFullLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupFullLimitRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupLimitCheckRespDTO;
import com.zengshi.ecp.coupon.dubbo.impl.converter.Converter;
import com.zengshi.ecp.coupon.dubbo.util.CouponConstants;
import com.zengshi.ecp.coupon.service.busi.combination.interfaces.ICoupCreateInitSV;
import com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupFullLimitSV;
import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.general.order.dto.CoupSkuRespDTO;
import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-coupon-server <br>
 * Description: <br>
 * Date:2015-10-21下午4:17:03  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huanghe5
 * @version  
 * @since JDK 1.7
 */
public class CoupFullLimitSVImpl extends GeneralSQLSVImpl implements ICoupFullLimitSV{

	private static final String MODULE = CoupFullLimitSVImpl.class.getName();
	
	@Resource(name = "seq_coup_full_limit_id")
    private PaasSequence seq_coup_full_limit_id;
	@Resource
	private Converter<CoupFullLimitReqDTO,CoupFullLimit> coupFullLimitConverter;
	@Resource
	private Converter<CoupFullLimit, CoupFullLimitRespDTO> coupFullLimitRespDTOConverter;
	@Resource
	private CoupFullLimitMapper coupFullLimitMapper;
	@Resource
    private ICoupCreateInitSV coupCreateInitSV;
	
	/**
	 * 
	 * Date:2015-10-21下午4:17:15  <br>
	 * @author huanghe5
	 * @see com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupRuleSV#save(com.zengshi.ecp.coupon.dubbo.dto.req.CoupReqDTO)
	 */
	@Override
	public void save(CoupReqDTO coupReqDTO) throws BusinessException {
		
		List<CoupFullLimitReqDTO> coupFullLimitReqDTOs = coupReqDTO.getCoupFullLimitReqDTOs();
		if(CollectionUtils.isNotEmpty(coupFullLimitReqDTOs)){
			for (CoupFullLimitReqDTO coupFullLimitReqDTO : coupFullLimitReqDTOs) {
				//优惠券品类限制信息入参
				coupFullLimitReqDTO.setCoupId(coupReqDTO.getCoupInfoReqDTO().getId());//优惠券信息ID
				coupFullLimitReqDTO.setId(seq_coup_full_limit_id.nextValue());
				coupFullLimitReqDTO.setCreateStaff(coupReqDTO.getStaff().getId());
				coupFullLimitReqDTO.setCreateTime(DateUtil.getSysDate());
				//如果是 编辑
				if(CouponConstants.CoupSys.edit_EDIT.equals(coupReqDTO.getEdit())){
					coupFullLimitReqDTO.setUpdateStaff(coupReqDTO.getStaff().getId());
					coupFullLimitReqDTO.setUpdateTime(DateUtil.getSysDate());
				}
				
			    if(StringUtil.isEmpty(coupFullLimitReqDTO.getStatus())){
			        coupFullLimitReqDTO.setStatus(CouponConstants.CoupSys.status_1);
                 }
				CoupFullLimit coupFullLimit = coupFullLimitConverter.convert(coupFullLimitReqDTO);
				if(CouponConstants.CoupFullLimit.TYPE_1
						.equals(coupFullLimitReqDTO.getType())){
					Long sumLimit = coupFullLimitReqDTO.getSumLimit();
					if(sumLimit.longValue()>=coupReqDTO.getCoupInfoReqDTO().getCoupValue().longValue()){
						
						coupFullLimit.setSumLimit(sumLimit);
						coupFullLimit.setAmount(null);
					}else{
						LogUtil.error(MODULE, "优惠券满减面额小于优惠券面额"+coupFullLimitReqDTO.getSumLimit()+">"+coupReqDTO.getCoupInfoReqDTO().getCoupValue());
						throw new BusinessException("coupon.error.450020");
					}
				}else if(CouponConstants.CoupFullLimit.TYPE_2
						.equals(coupFullLimitReqDTO.getType())){
					coupFullLimit.setSumLimit(null);
					coupFullLimit.setAmount(coupFullLimitReqDTO.getAmount());
				}
				coupFullLimitMapper.insert(coupFullLimit);
			}
		}
	}
	
	/**
	 * 
	 * Date:2015-10-21下午8:53:18  <br>
	 * @author huanghe5
	 * @see com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupRuleSV#update(com.zengshi.ecp.coupon.dubbo.dto.req.CoupReqDTO)
	 */
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
	 * 查询满减限制表
	 * 
	 * @author huanghe5 
	 * @param CoupFullLimitReqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@Override
	public List<CoupFullLimitRespDTO> queryCoupFullList(
			CoupFullLimitReqDTO coupFullLimitReqDTO) throws BusinessException {
		CoupFullLimitCriteria example = new CoupFullLimitCriteria();
		coupCreateInitSV.initCoupFullLimit(coupFullLimitReqDTO, example);
		if(StringUtil.isBlank(coupFullLimitReqDTO.getStatus())){
			coupFullLimitReqDTO.setStatus(CouponConstants.CoupSys.status_1);
		}
		List<CoupFullLimit> beans = coupFullLimitMapper.selectByExample(example);
		
		if (CollectionUtils.isEmpty(beans)) {
            return null;
        }
		
		List<CoupFullLimitRespDTO> reList = new ArrayList<CoupFullLimitRespDTO>();
		for (CoupFullLimit coupFullLimit : beans) {
			reList.add(coupFullLimitRespDTOConverter.convert(coupFullLimit));
		}
		
		return reList;
	}
	
	/**
	 * 
	 * Date:2015-10-21下午8:53:28  <br>
	 * @author huanghe5
	 * @see com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupRuleSV#deleteStatus(com.zengshi.ecp.coupon.dubbo.dto.req.CoupReqDTO)
	 */
	@Override
	public void deleteStatus(CoupReqDTO coupReqDTO) throws BusinessException {
		
		CoupFullLimit bean = new CoupFullLimit();
		bean.setStatus(CouponConstants.CoupSys.status_0);//失效
		CoupFullLimitCriteria example = new CoupFullLimitCriteria();
		CoupFullLimitCriteria.Criteria cr = example.createCriteria();
		if (coupReqDTO.getCoupInfoReqDTO().getId() != null && coupReqDTO.getCoupInfoReqDTO().getId() > 0) {
			cr.andCoupIdEqualTo(coupReqDTO.getCoupInfoReqDTO().getId());// 优惠券信息ID
			
			coupFullLimitMapper.updateByExampleSelective(bean, example);
		}else if(coupReqDTO.getCoupFullLimitReqDTO().getCoupId()!=null && coupReqDTO.getCoupFullLimitReqDTO().getCoupId()>0){
			cr.andCoupIdEqualTo(coupReqDTO.getCoupFullLimitReqDTO().getCoupId());// 优惠券信息ID
			
			coupFullLimitMapper.updateByExampleSelective(bean, example);
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
		CoupFullLimitReqDTO coupFullLimitReqDTO = new CoupFullLimitReqDTO();
		coupFullLimitReqDTO.setCoupId(coupCheckParmReqDTO.getCoupId());
		
		//根据coupId查询限制规则信息
		List<CoupFullLimitRespDTO> bean = this.queryCoupFullList(coupFullLimitReqDTO);
		if(CollectionUtils.isNotEmpty(bean)){
			for (CoupFullLimitRespDTO coupFullLimitRespDTO : bean) {
				
				if(CouponConstants.CoupFullLimit.TYPE_2.equals(coupFullLimitRespDTO.getType())){
					//if(ordCartItemCommRequest.getOrderAmount()>=coupFullLimitRespDTO.getAmount()){
						coupLimitCheckRespDTO.setJudge(CouponConstants.CoupSys.judge_1);
						return coupLimitCheckRespDTO;
					//}
				}else{
					//SkuGroup为了单品组合校验的信息存放 比如满减 多个单品价格总和满减
					coupLimitCheckRespDTO.setSkuGroup(CouponConstants.CoupSys.judge_2);
					coupLimitCheckRespDTO.setJudge(CouponConstants.CoupSys.judge_1);
					coupLimitCheckRespDTO.setSumLimit(coupFullLimitRespDTO.getSumLimit());//满减金额
					return coupLimitCheckRespDTO;
				}
			}
		}
		coupLimitCheckRespDTO.setJudge(CouponConstants.CoupSys.judge_0);
		return coupLimitCheckRespDTO;
	}

	/**
	 * checkCoupLimit:下单时校验优惠券限制条件. <br/> 
	 * 
	 * @author huanghe5
	 * @param ordCartCommRequest
	 * @param coupId
	 * @return 1:通过 0:不通过  2:多个单品组合
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@Override
	public boolean checkCoupFullLimit(List<CoupSkuRespDTO> coupSkus,Long coupId,Long coupValue)
			throws BusinessException {
		
		CoupFullLimitReqDTO coupFullLimitReqDTO = new CoupFullLimitReqDTO();
		coupFullLimitReqDTO.setCoupId(coupId);
		coupFullLimitReqDTO.setStatus(CouponConstants.CoupSys.status_1);
		//根据coupId查询限制规则信息
		List<CoupFullLimitRespDTO> bean = this.queryCoupFullList(coupFullLimitReqDTO);
		
		//满减多个单品价格组合校验
		if(CollectionUtils.isNotEmpty(bean)){
			for (CoupFullLimitRespDTO coupFullLimitRespDTO : bean) {
				//满减
				if(CouponConstants.CoupFullLimit.TYPE_1.equals(coupFullLimitRespDTO.getType())){
					Long i =0l;
					for (CoupSkuRespDTO coupSkuRespDTO : coupSkus) {
						i+=coupSkuRespDTO.getDiscountMoney();
					}
					//单品价格综合>=满减规则价格 &&符合优惠券使用的单品金额>=优惠券面额
					if(i.longValue()>=coupFullLimitRespDTO.getSumLimit().longValue()&&coupValue.longValue()<=i.longValue()){
						return true;
					}else{
						return false;
					}
				}
			}
		}
		return false;
	}
	
	

}

