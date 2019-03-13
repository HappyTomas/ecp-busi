package com.zengshi.ecp.coupon.service.busi.coupUse.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.coupon.dao.mapper.busi.CoupGetLimitMapper;
import com.zengshi.ecp.coupon.dao.model.CoupGetLimit;
import com.zengshi.ecp.coupon.dao.model.CoupGetLimitCriteria;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupCheckParmReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupGetLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupGetLimitRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupLimitCheckRespDTO;
import com.zengshi.ecp.coupon.dubbo.impl.converter.Converter;
import com.zengshi.ecp.coupon.dubbo.util.CouponConstants;
import com.zengshi.ecp.coupon.service.busi.combination.interfaces.ICoupCreateInitSV;
import com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupGetLimitSV;
import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

public class CoupGetLimitSVImpl extends GeneralSQLSVImpl implements ICoupGetLimitSV{

	@Resource(name = "seq_coup_get_limit_id")
    private PaasSequence seq_coup_get_limit_id;
	@Resource
	private Converter<CoupGetLimitReqDTO,CoupGetLimit> coupGetLimitConverter;
	@Resource
	private Converter<CoupGetLimit, CoupGetLimitRespDTO> coupGetLimitRespDTOConverter;
	@Resource
	private CoupGetLimitMapper coupGetLimitMapper;
	@Resource
    private ICoupCreateInitSV coupCreateInitSV;
	
	/**
	 * 
	 * Date:2015-10-21下午9:52:43  <br>
	 * @author huanghe5
	 * @see com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupRuleSV#save(com.zengshi.ecp.coupon.dubbo.dto.req.CoupReqDTO)
	 */
	@Override
	public void save(CoupReqDTO coupReqDTO) throws BusinessException {
		List<CoupGetLimitReqDTO> coupGetLimitReqDTOs = coupReqDTO.getCoupGetLimitReqDTOs();
		if(CollectionUtils.isNotEmpty(coupGetLimitReqDTOs)){
			for (CoupGetLimitReqDTO coupGetLimitReqDTO : coupGetLimitReqDTOs) {
				//优惠券黑名单入参信息
				coupGetLimitReqDTO.setCoupId(coupReqDTO.getCoupInfoReqDTO().getId());//优惠券信息ID
				coupGetLimitReqDTO.setId(seq_coup_get_limit_id.nextValue());
				coupGetLimitReqDTO.setCreateStaff(coupReqDTO.getStaff().getId());
				coupGetLimitReqDTO.setCreateTime(DateUtil.getSysDate());
				//如果是 编辑
				if(CouponConstants.CoupSys.edit_EDIT.equals(coupReqDTO.getEdit())){
					coupGetLimitReqDTO.setUpdateStaff(coupReqDTO.getStaff().getId());
					coupGetLimitReqDTO.setUpdateTime(DateUtil.getSysDate());
				}
				if(StringUtil.isEmpty(coupGetLimitReqDTO.getStatus())){
				     coupGetLimitReqDTO.setStatus(CouponConstants.CoupSys.status_1);
	             }
				CoupGetLimit coupGetLimit = coupGetLimitConverter.convert(coupGetLimitReqDTO);
				coupGetLimitMapper.insert(coupGetLimit);
			}
		}
	}
	
	/**
	 * 
	 * Date:2015-10-21下午9:52:51  <br>
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
	 * 查询领取权限规则表.  
	 * 
	 * @author huanghe5 
	 * @param CoupGetLimitReqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@Override
	public List<CoupGetLimitRespDTO> queryCoupGetList(
			CoupGetLimitReqDTO coupGetLimitReqDTO) throws BusinessException {
		CoupGetLimitCriteria example = new CoupGetLimitCriteria();
		coupCreateInitSV.initCoupGetLimit(coupGetLimitReqDTO, example);
		if(StringUtil.isBlank(coupGetLimitReqDTO.getStatus())){
			coupGetLimitReqDTO.setStatus(CouponConstants.CoupSys.status_1);
		}
		List<CoupGetLimit> beans = coupGetLimitMapper.selectByExampleWithBLOBs(example);
		
		
		if (CollectionUtils.isEmpty(beans)) {
            return null;
        }
		
		List<CoupGetLimitRespDTO> reList = new ArrayList<CoupGetLimitRespDTO>();
		for (CoupGetLimit coupGetLimit : beans) {
			reList.add(coupGetLimitRespDTOConverter.convert(coupGetLimit));
		}
		
		return reList;
	}
	
	/**
	 * 
	 * Date:2015-10-21下午9:52:57  <br>
	 * @author huanghe5
	 * @see com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupRuleSV#deleteStatus(com.zengshi.ecp.coupon.dubbo.dto.req.CoupReqDTO)
	 */
	@Override
	public void deleteStatus(CoupReqDTO coupReqDTO) throws BusinessException {
		
		CoupGetLimit bean = new CoupGetLimit();
		bean.setStatus(CouponConstants.CoupSys.status_0);//失效
		CoupGetLimitCriteria example = new CoupGetLimitCriteria();
		CoupGetLimitCriteria.Criteria cr = example.createCriteria();
		
		if (coupReqDTO.getCoupInfoReqDTO().getId() != null && coupReqDTO.getCoupInfoReqDTO().getId() > 0) {
			cr.andCoupIdEqualTo(coupReqDTO.getCoupInfoReqDTO().getId());// 优惠券信息ID
			
			coupGetLimitMapper.updateByExampleSelective(bean, example);
		}else if(coupReqDTO.getCoupBlackLimitReqDTO().getCoupId()!=null && coupReqDTO.getCoupBlackLimitReqDTO().getCoupId()>0){
			cr.andCoupIdEqualTo(coupReqDTO.getCoupBlackLimitReqDTO().getCoupId());// 优惠券信息ID
			
			coupGetLimitMapper.updateByExampleSelective(bean, example);
		}
		
	}

	/**
	 * 
	 * checkCoupLimit:下单时校验优惠券限制条件. <br/> 
	 * 
	 * @author huanghe5
	 * @param ordCartCommRequest
	 * @param coupId
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@Override
	public CoupLimitCheckRespDTO checkCoupLimit(ROrdCartItemCommRequest ordCartItemCommRequest,
			CoupCheckParmReqDTO coupCheckParmReqDTO,CoupLimitCheckRespDTO coupLimitCheckRespDTO) throws BusinessException {
		coupLimitCheckRespDTO.setJudge(CouponConstants.CoupSys.judge_1);
		return coupLimitCheckRespDTO;
	}

}

