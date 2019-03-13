package com.zengshi.ecp.coupon.service.busi.coupUse.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.coupon.dao.mapper.busi.CoupUseLimitMapper;
import com.zengshi.ecp.coupon.dao.model.CoupUseLimit;
import com.zengshi.ecp.coupon.dao.model.CoupUseLimitCriteria;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupCheckParmReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupUseLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupLimitCheckRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupUseLimitRespDTO;
import com.zengshi.ecp.coupon.dubbo.impl.converter.Converter;
import com.zengshi.ecp.coupon.dubbo.util.CouponConstants;
import com.zengshi.ecp.coupon.service.busi.combination.interfaces.ICoupCreateInitSV;
import com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupUseLimitSV;
import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

public class CoupUseLimitSVImpl extends GeneralSQLSVImpl implements ICoupUseLimitSV{
	
	@Resource(name = "seq_coup_use_limit_id")
    private PaasSequence seq_coup_use_limit_id;
	@Resource
	private Converter<CoupUseLimitReqDTO,CoupUseLimit> coupUseLimitConverter;
	@Resource
	private Converter<CoupUseLimit, CoupUseLimitRespDTO> coupUseLimitRespDTOConverter;
	@Resource
	private CoupUseLimitMapper coupUseLimitMapper;
	@Resource
    private ICoupCreateInitSV coupCreateInitSV;
	
	/**
	 * 
	 * Date:2015-10-22下午4:27:51  <br>
	 * @author huanghe5
	 * @see com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupRuleSV#save(com.zengshi.ecp.coupon.dubbo.dto.req.CoupReqDTO)
	 */
	@Override
	public void save(CoupReqDTO coupReqDTO) throws BusinessException {
		List<CoupUseLimitReqDTO> coupUseLimitReqDTOs = coupReqDTO.getCoupUseLimitReqDTOs();
		if(CollectionUtils.isNotEmpty(coupUseLimitReqDTOs)){
			for (CoupUseLimitReqDTO coupUseLimitReqDTO : coupUseLimitReqDTOs) {
				//优惠券品类限制信息入参
				coupUseLimitReqDTO.setCoupId(coupReqDTO.getCoupInfoReqDTO().getId());//优惠券信息ID
				coupUseLimitReqDTO.setId(seq_coup_use_limit_id.nextValue());
				coupUseLimitReqDTO.setCreateStaff(coupReqDTO.getStaff().getId());
				coupUseLimitReqDTO.setCreateTime(DateUtil.getSysDate());
				//如果是 编辑
				if(CouponConstants.CoupSys.edit_EDIT.equals(coupReqDTO.getEdit())){
					coupUseLimitReqDTO.setUpdateStaff(coupReqDTO.getStaff().getId());
					coupUseLimitReqDTO.setUpdateTime(DateUtil.getSysDate());
				}

				if(StringUtil.isEmpty(coupUseLimitReqDTO.getStatus())){
				    coupUseLimitReqDTO.setStatus(CouponConstants.CoupSys.status_1);
				}
				CoupUseLimit coupUseLimit = coupUseLimitConverter.convert(coupUseLimitReqDTO);
				coupUseLimitMapper.insert(coupUseLimit);
			}
		}
	}

	/**
	 * 
	 * Date:2015-10-22下午4:27:45  <br>
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
	 * 查询优惠券其他使用参数规则表.  
	 * 
	 * @author huanghe5 
	 * @param CoupUseLimitReqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@Override
	public List<CoupUseLimitRespDTO> queryCoupUseList(
			CoupUseLimitReqDTO coupUseLimitReqDTO) throws BusinessException {
		CoupUseLimitCriteria example = new CoupUseLimitCriteria();
		coupCreateInitSV.initCoupUseLimit(coupUseLimitReqDTO, example);
		if(StringUtil.isBlank(coupUseLimitReqDTO.getStatus())){
			coupUseLimitReqDTO.setStatus(CouponConstants.CoupSys.status_1);
		}
		List<CoupUseLimit> beans = coupUseLimitMapper.selectByExample(example);
		
		if (CollectionUtils.isEmpty(beans)) {
            return null;
        }
		
		List<CoupUseLimitRespDTO> reList = new ArrayList<CoupUseLimitRespDTO>();
		for (CoupUseLimit coupUseLimit : beans) {
			reList.add(coupUseLimitRespDTOConverter.convert(coupUseLimit));
		}
		
		return reList;
	}
	
	/**
	 * 
	 * Date:2015-10-22下午4:27:39  <br>
	 * @author huanghe5
	 * @see com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupRuleSV#deleteStatus(com.zengshi.ecp.coupon.dubbo.dto.req.CoupReqDTO)
	 */
	@Override
	public void deleteStatus(CoupReqDTO coupReqDTO) throws BusinessException {
		CoupUseLimit bean = new CoupUseLimit();
		bean.setStatus(CouponConstants.CoupSys.status_0);//失效
		CoupUseLimitCriteria example = new CoupUseLimitCriteria();
		CoupUseLimitCriteria.Criteria cr = example.createCriteria();
		if (coupReqDTO.getCoupInfoReqDTO().getId() != null && coupReqDTO.getCoupInfoReqDTO().getId() > 0) {
			cr.andCoupIdEqualTo(coupReqDTO.getCoupInfoReqDTO().getId());// 优惠券信息ID
			
			coupUseLimitMapper.updateByExampleSelective(bean, example);
		}else if(coupReqDTO.getCoupBlackLimitReqDTO().getCoupId()!=null && coupReqDTO.getCoupBlackLimitReqDTO().getCoupId()>0){
			cr.andCoupIdEqualTo(coupReqDTO.getCoupBlackLimitReqDTO().getCoupId());// 优惠券信息ID
			
			coupUseLimitMapper.updateByExampleSelective(bean, example);
		}
	}

	/**
	 * 
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
	public CoupLimitCheckRespDTO checkCoupLimit(ROrdCartItemCommRequest ordCartItemCommRequest,
			CoupCheckParmReqDTO coupCheckParmReqDTO,CoupLimitCheckRespDTO coupLimitCheckRespDTO) throws BusinessException {
		String ruleCode = coupCheckParmReqDTO.getRuleCode();
		CoupUseLimitReqDTO coupUseLimitReqDTO = new CoupUseLimitReqDTO();
		coupUseLimitReqDTO.setCoupId(coupCheckParmReqDTO.getCoupId());
		coupUseLimitReqDTO.setStatus(CouponConstants.CoupSys.status_1);
//		coupUseLimitReqDTO.setUseRuleKey(ruleCode);
		coupLimitCheckRespDTO.setOrdUseNum(CouponConstants.CoupSys.defaultUseNo);//默认使用1张
		//根据coupId查询限制规则信息
		List<CoupUseLimitRespDTO> bean = this.queryCoupUseList(coupUseLimitReqDTO);
		if(CollectionUtils.isNotEmpty(bean)){
			String s ="0";
			for (CoupUseLimitRespDTO coupUseLimitRespDTO : bean) {
				//180:免邮
		        //160:单个订单同类型优惠券使用张数 (如果无值系统默认为1张)
		        //170:渠道来源使用限制 (1:WEB端,2:手机端,3:微信端等等)
				if (CouponConstants.CoupPara.RULE_CODE_180.equals(coupUseLimitRespDTO.getUseRuleKey())) {
					s = "1";
					coupLimitCheckRespDTO.setNoExpress(CouponConstants.CoupSys.noExpress_1);
				}else if (CouponConstants.CoupPara.RULE_CODE_170.equals(coupUseLimitRespDTO.getUseRuleKey())) {			    
				    if (coupUseLimitRespDTO.getUseRuleValue() != null && coupCheckParmReqDTO.getSource()!=null && coupUseLimitRespDTO.getUseRuleValue().contains(coupCheckParmReqDTO.getSource())) {
						s = "1";
					} else {
					    coupLimitCheckRespDTO.setJudge(CouponConstants.CoupSys.judge_0);
				        return coupLimitCheckRespDTO;
					}
				}else if(CouponConstants.CoupPara.RULE_CODE_160
						.equals(coupUseLimitRespDTO.getUseRuleKey())){
					if(coupUseLimitRespDTO.getUseRuleValue()!=null){
						coupLimitCheckRespDTO.setOrdUseNum(Integer.valueOf(coupUseLimitRespDTO.getUseRuleValue()));
					}
					s="1";
				}
			}
			coupLimitCheckRespDTO.setJudge(s);
			return coupLimitCheckRespDTO;
		}
		
		//bean为空 ，即没有限制规则，可直接放入可使用优惠券的列表里
		coupLimitCheckRespDTO.setJudge(CouponConstants.CoupSys.judge_1);
		
		return coupLimitCheckRespDTO;
	}
	
	/**
	 * checkCoupLimit:下单时校验优惠券限制条件. <br/> 
	 * 
	 * @author huanghe5
	 * @param ordCartItemCommRequest
	 * @param coupId
	 * @param custLevelValue
	 * @param source
	 * @return 1:通过 0:不通过  2:多个单品组合
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@Override
	public CoupLimitCheckRespDTO checkCoupLimit(
			ROrdCartItemCommRequest ordCartItemCommRequest, Long coupId,
			String custLevelValue, String source,String ruleCode) throws BusinessException {
		CoupLimitCheckRespDTO coupLimitCheckRespDTO = new CoupLimitCheckRespDTO();
		coupLimitCheckRespDTO.setCoupId(coupId);
		coupLimitCheckRespDTO.setGdsId(ordCartItemCommRequest.getGdsId());
		coupLimitCheckRespDTO.setSkuId(ordCartItemCommRequest.getSkuId());
		CoupUseLimitReqDTO coupUseLimitReqDTO = new CoupUseLimitReqDTO();
		coupUseLimitReqDTO.setCoupId(coupId);
		coupUseLimitReqDTO.setUseRuleKey(ruleCode);
		//根据coupId查询限制规则信息
		List<CoupUseLimitRespDTO> bean = this.queryCoupUseList(coupUseLimitReqDTO);
		if(CollectionUtils.isNotEmpty(bean)){
			String s ="0";
			for (CoupUseLimitRespDTO coupUseLimitRespDTO : bean) {
				//180:免邮
		        //160:单个订单同类型优惠券使用张数 (如果无值系统默认为1张)
		        //170:渠道来源使用限制 (0:WEB端,1:手机端,3:微信端等等)
				if (CouponConstants.CoupPara.RULE_CODE_180.equals(ruleCode)) {
					s = "1";
				}else if (CouponConstants.CoupPara.RULE_CODE_170.equals(ruleCode)) {
					if (coupUseLimitRespDTO.getUseRuleValue().contains(source)) {
						s = "1";
					} else {
						s = "0";
					}
				}else if(CouponConstants.CoupPara.RULE_CODE_160
						.equals(ruleCode)){
					if(coupUseLimitRespDTO.getUseRuleValue()!=null){
						coupLimitCheckRespDTO.setOrdUseNum(Integer.valueOf(coupUseLimitRespDTO.getUseRuleValue()));
					}
					s="1";
				}
			}
			coupLimitCheckRespDTO.setJudge(s);
			return coupLimitCheckRespDTO;
		}
		
		coupLimitCheckRespDTO.setJudge(CouponConstants.CoupSys.judge_0);
		
		return coupLimitCheckRespDTO;
	}
	
	
	

}

