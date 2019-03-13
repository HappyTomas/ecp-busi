package com.zengshi.ecp.coupon.service.busi.coupUse.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.coupon.dao.mapper.busi.CoupShopLimitMapper;
import com.zengshi.ecp.coupon.dao.model.CoupShopLimit;
import com.zengshi.ecp.coupon.dao.model.CoupShopLimitCriteria;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupCheckParmReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupShopLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupLimitCheckRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupShopLimitRespDTO;
import com.zengshi.ecp.coupon.dubbo.impl.converter.Converter;
import com.zengshi.ecp.coupon.dubbo.util.CouponConstants;
import com.zengshi.ecp.coupon.service.busi.combination.interfaces.ICoupCreateInitSV;
import com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupShopLimitSV;
import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

public class CoupShopLimitSVImpl extends GeneralSQLSVImpl implements ICoupShopLimitSV{

	@Resource(name = "seq_coup_shop_limit_id")
    private PaasSequence seq_coup_shop_limit_id;
	@Resource
	private Converter<CoupShopLimitReqDTO,CoupShopLimit> coupShopLimitConverter;
	@Resource
	private Converter<CoupShopLimit, CoupShopLimitRespDTO> coupShopLimitRespDTOConverter;
	@Resource
	private CoupShopLimitMapper coupShopLimitMapper;
	@Resource
    private ICoupCreateInitSV coupCreateInitSV;
	
	@Override
	public void save(CoupReqDTO coupReqDTO) throws BusinessException {
		List<CoupShopLimitReqDTO> coupShopLimitReqDTOs = coupReqDTO.getCoupShopLimitReqDTOs();
		if(CollectionUtils.isNotEmpty(coupShopLimitReqDTOs)){
			for (CoupShopLimitReqDTO coupShopLimitReqDTO : coupShopLimitReqDTOs) {
				//优惠券品类限制信息入参
				coupShopLimitReqDTO.setCoupId(coupReqDTO.getCoupInfoReqDTO().getId());//优惠券信息ID
				coupShopLimitReqDTO.setId(seq_coup_shop_limit_id.nextValue());
				coupShopLimitReqDTO.setCreateStaff(coupReqDTO.getStaff().getId());
				coupShopLimitReqDTO.setCreateTime(DateUtil.getSysDate());
				//如果是 编辑
				if(CouponConstants.CoupSys.edit_EDIT.equals(coupReqDTO.getEdit())){
					coupShopLimitReqDTO.setUpdateStaff(coupReqDTO.getStaff().getId());
					coupShopLimitReqDTO.setUpdateTime(DateUtil.getSysDate());
				}

			    if(StringUtil.isEmpty(coupShopLimitReqDTO.getStatus())){
			        coupShopLimitReqDTO.setStatus(CouponConstants.CoupSys.status_1);
                }
				CoupShopLimit coupShopLimit = coupShopLimitConverter.convert(coupShopLimitReqDTO);
				coupShopLimitMapper.insert(coupShopLimit);
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
	 * 查询店铺规则表.  
	 * 
	 * @author huanghe5 
	 * @param CoupShopLimitReqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@Override
	public List<CoupShopLimitRespDTO> queryCoupShopList(
			CoupShopLimitReqDTO coupShopLimitReqDTO,String typeLimit) throws BusinessException {
		CoupShopLimitCriteria example = new CoupShopLimitCriteria();
		coupCreateInitSV.initCoupShopLimit(coupShopLimitReqDTO, example);
		if(StringUtil.isBlank(coupShopLimitReqDTO.getStatus())){
			coupShopLimitReqDTO.setStatus(CouponConstants.CoupSys.status_1);
		}
		
		List<CoupShopLimit> beans = coupShopLimitMapper.selectByExample(example);
		
		if (CollectionUtils.isEmpty(beans)) {
            return null;
        }
		
		List<CoupShopLimitRespDTO> reList = new ArrayList<CoupShopLimitRespDTO>();
		for (CoupShopLimit coupShopLimit : beans) {
			reList.add(coupShopLimitRespDTOConverter.convert(coupShopLimit));
		}
		
		return reList;
	}

	@Override
	public void deleteStatus(CoupReqDTO coupReqDTO) throws BusinessException {
		CoupShopLimit bean = new CoupShopLimit();
		bean.setStatus(CouponConstants.CoupSys.status_0);//失效
		CoupShopLimitCriteria example = new CoupShopLimitCriteria();
		CoupShopLimitCriteria.Criteria cr = example.createCriteria();
		if (coupReqDTO.getCoupInfoReqDTO().getId() != null && coupReqDTO.getCoupInfoReqDTO().getId() > 0) {
			cr.andCoupIdEqualTo(coupReqDTO.getCoupInfoReqDTO().getId());// 优惠券信息ID
			
			coupShopLimitMapper.updateByExampleSelective(bean, example);
		}else if(coupReqDTO.getCoupBlackLimitReqDTO().getCoupId()!=null && coupReqDTO.getCoupBlackLimitReqDTO().getCoupId()>0){
			cr.andCoupIdEqualTo(coupReqDTO.getCoupBlackLimitReqDTO().getCoupId());// 优惠券信息ID
			
			coupShopLimitMapper.updateByExampleSelective(bean, example);
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
		CoupShopLimitReqDTO coupShopLimitReqDTO = new CoupShopLimitReqDTO();
		coupShopLimitReqDTO.setCoupId(coupCheckParmReqDTO.getCoupId());
		coupShopLimitReqDTO.setShopId(ordCartItemCommRequest.getShopId());
		coupShopLimitReqDTO.setStatus(CouponConstants.CoupSys.status_1);
		//根据coupId查询限制规则信息
		List<CoupShopLimitRespDTO> bean = this.queryCoupShopList(coupShopLimitReqDTO,coupLimitCheckRespDTO.getTypeLimit());
		if(CollectionUtils.isNotEmpty(bean)){
			coupLimitCheckRespDTO.setJudge(CouponConstants.CoupSys.judge_1);
			return coupLimitCheckRespDTO;
		}
		coupLimitCheckRespDTO.setJudge(CouponConstants.CoupSys.judge_0);
		return coupLimitCheckRespDTO;
	}

}

