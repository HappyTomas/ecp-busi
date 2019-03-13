package com.zengshi.ecp.coupon.dubbo.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.coupon.dubbo.dto.req.CoupCallBackReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupConsumeReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupMeReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupOrdBackReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CouponPresentReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupCodeRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupConsumeRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupMeCountRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupMeRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupOrdBackRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupOrdCheckRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupOrdNumBackRespDTO;
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupDetailRSV;
import com.zengshi.ecp.coupon.dubbo.util.CouponConstants;
import com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupConsumeSV;
import com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupDetailSV;
import com.zengshi.ecp.coupon.service.util.CoupUtil;
import com.zengshi.ecp.general.order.dto.CoupCheckBeanRespDTO;
import com.zengshi.ecp.general.order.dto.ROrdCartCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartsChkResponse;
import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-coupon-server <br>
 * Description: <br>
 * Date:2015-11-7下午12:28:06  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huanghe5
 * @version  
 * @since JDK 1.7
 */
public class CoupDetailRSVImpl implements ICoupDetailRSV {

    private static final String MODULE = CoupDetailRSVImpl.class.getName();
    
	@Resource
    private ICoupDetailSV coupDetailSV;
    
	@Resource
    private ICoupConsumeSV coupConsumeSV;
	
    /**
     * 
     * TODO 我的优惠券. 
     * Date:2015-11-6下午2:40:16  <br>
     * @author huanghe5
     * @see com.zengshi.ecp.coupon.dubbo.interfaces.ICoupRSV#queryCoupDetailPage(com.zengshi.ecp.coupon.dubbo.dto.req.CoupMeReqDTO)
     */
	@Override
	public PageResponseDTO<CoupMeRespDTO> queryCoupDetailPage(
			CoupMeReqDTO coupMeReqDTO) throws BusinessException {
		LogUtil.info(MODULE, "CoupDetailRSVImpl.queryCoupDetailPage ========我的优惠券开始.：" + coupMeReqDTO);
		if(coupMeReqDTO==null){
            throw new BusinessException(CouponConstants.CoupError.coupon_error_450001);
        }
		if(StringUtil.isEmpty(coupMeReqDTO.getStaffId())){
			if(coupMeReqDTO.getStaff()!=null){
				coupMeReqDTO.setStaffId(coupMeReqDTO.getStaff().getId());
			}
		}
		if(StringUtil.isEmpty(coupMeReqDTO.getStaffId())){
			throw new BusinessException(CouponConstants.CoupError.coupon_error_450012);
		}
		PageResponseDTO<CoupMeRespDTO> beanPage = coupDetailSV.queryCoupDetailPage(coupMeReqDTO);
		
		LogUtil.info(MODULE, "CoupDetailRSVImpl.queryCoupDetailPage ========我的优惠券结束.");
		
		return beanPage;
	}

	/**
	 * 
	 * queryCoupInfoPage:统计用户优惠券可使用,已使用,已过期集合. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupMeReqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@Override
	public Long queryCoupDetailCount(CoupMeReqDTO coupMeReqDTO)
			throws BusinessException {
		LogUtil.info(MODULE, "CoupDetailRSVImpl.queryCoupDetailCount========统计用户优惠券可使用,已使用,已过期集合开始.：" + coupMeReqDTO);
		if(coupMeReqDTO==null){
            throw new BusinessException(CouponConstants.CoupError.coupon_error_450001);
        }
		if(StringUtil.isEmpty(coupMeReqDTO.getStaffId())){
			if(coupMeReqDTO.getStaff()!=null){
				coupMeReqDTO.setStaffId(coupMeReqDTO.getStaff().getId());
			}
		}
		if(StringUtil.isEmpty(coupMeReqDTO.getStaffId())){
			throw new BusinessException(CouponConstants.CoupError.coupon_error_450012);
		}
		
		Long coupCount = coupDetailSV.queryCoupDetailCount(coupMeReqDTO);
		
		LogUtil.info(MODULE, "CoupDetailRSVImpl.queryCoupDetailCount========统计用户优惠券可使用,已使用,已过期集合结束.");
		
		return coupCount;
	}
	
	/**
	 * 
	 * queryCoupInfoPage:统计用户优惠券可使用优惠卷集合. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupMeReqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@Override
	public Long queryCoupValidCount(CoupMeReqDTO coupMeReqDTO)
			throws BusinessException {
		LogUtil.info(MODULE, "CoupDetailRSVImpl.queryCoupValidCount========统计用户优惠券可使用优惠卷集开始.：" + coupMeReqDTO);
		if(coupMeReqDTO==null){
            throw new BusinessException(CouponConstants.CoupError.coupon_error_450001);
        }
		if(StringUtil.isEmpty(coupMeReqDTO.getStaffId())){
			throw new BusinessException(CouponConstants.CoupError.coupon_error_450012);
		}
		coupMeReqDTO.setOpeType(CouponConstants.CoupDetail.opeType_1);//可使用
		
		Long coupCount = coupDetailSV.queryCoupDetailCount(coupMeReqDTO);
		
		LogUtil.info(MODULE, "CoupDetailRSVImpl.queryCoupValidCount========统计用户优惠券可使用优惠卷集结束.");
		
		return coupCount;
	}

	/**
	 * deleteCoupDetail:删除我的优惠券. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupMeReqDTO
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@Override
	public void deleteCoupDetail(CoupMeReqDTO coupMeReqDTO)
			throws BusinessException {
		LogUtil.info(MODULE, "CoupDetailRSVImpl.deleteCoupDetail========删除我的优惠券开始.：" + coupMeReqDTO);
		if(coupMeReqDTO==null){
            throw new BusinessException(CouponConstants.CoupError.coupon_error_450001);
        }
		if(StringUtil.isEmpty(coupMeReqDTO.getId())){
            throw new BusinessException(CouponConstants.CoupError.coupon_error_450002);
        }
		
		coupDetailSV.deleteCoupDetail(coupMeReqDTO);
	}

	
	/**
	 * 
	 * queryOrdCheckCoupByCode:下单时,查询优惠码是否可用. <br/> 
	 * 
	 * @author lisp
	 * @param ordCartCommRequest
	 * @return CoupCodeRespDTO
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public CoupCodeRespDTO queryOrdCheckCoupByCode(ROrdCartCommRequest ordCartCommRequest) throws BusinessException {
		LogUtil.info(MODULE, "CoupDetailRSVImpl.queryOrdCheckCoupByCode========下单时,查询优惠码对应的优惠券是否可用开始.：" + ordCartCommRequest);
		if(StringUtil.isEmpty(ordCartCommRequest)){
			throw new BusinessException(CouponConstants.CoupError.coupon_error_450002);
		}
		if(ordCartCommRequest.getStaffId()==null||ordCartCommRequest.getStaffId()<1){
			throw new BusinessException(CouponConstants.CoupError.coupon_error_450001);
		}
		if(StringUtil.isBlank(ordCartCommRequest.getCoupCode())){
			throw new BusinessException(CouponConstants.CoupError.coupon_error_450001);
		}
		CoupCodeRespDTO coupCodeRespDTO = coupDetailSV.queryOrdCheckCoupByCode(ordCartCommRequest);
		LogUtil.info(MODULE, "CoupDetailRSVImpl.queryOrdCheckCoupByCode========下单时,查询优惠码对应的优惠券是否可用结束.：" + ordCartCommRequest);

		return coupCodeRespDTO;
		
	}
	/**
	 * 
	 * queryOrdCheckCoup:下单时,查询可使用的优惠券. <br/> 
	 * 
	 * @author huanghe5
	 * @param ordCartsCommRequest
	 * @return CoupCheckRespDTO
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@Override
	public CoupOrdCheckRespDTO queryOrdCheckCoup(
			ROrdCartsCommRequest ordCartsCommRequest) throws BusinessException {
		LogUtil.info(MODULE, "CoupDetailRSVImpl.queryOrdCheckCoup========下单时,查询可使用的优惠券开始.：" + ordCartsCommRequest);
		if(ordCartsCommRequest==null){
            throw new BusinessException(CouponConstants.CoupError.coupon_error_450001);
        }
		if(CollectionUtils.isEmpty(ordCartsCommRequest.getOrdCartsCommList())){
            throw new BusinessException(CouponConstants.CoupError.coupon_error_450002);
        }
		if(ordCartsCommRequest.getStaffId()==null||ordCartsCommRequest.getStaffId()<1){
			throw new BusinessException(CouponConstants.CoupError.coupon_error_450001);
		}
		
		CoupOrdCheckRespDTO coupOrdCheckRespDTO= coupDetailSV.queryOrdCheckCoup(ordCartsCommRequest);
		LogUtil.info(MODULE, "CoupDetailRSVImpl.queryOrdCheckCoup========下单时,查询可使用的优惠券结束.：" + ordCartsCommRequest);

		return coupOrdCheckRespDTO;
	}

	
	/**
	 * 
	 * countCoupOrdSku:优惠券结算. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupOrdCheckReqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@Override
	public List<CoupConsumeReqDTO> countCoupOrdSku(
			ROrdCartsCommRequest ordCartsCommRequest)
			throws BusinessException {
		LogUtil.info(MODULE, "CoupDetailRSVImpl.countCoupOrdSku========优惠券结算开始.：" + ordCartsCommRequest);
		if(ordCartsCommRequest==null){
            throw new BusinessException(CouponConstants.CoupError.coupon_error_450001);
        }
		if(CollectionUtils.isEmpty(ordCartsCommRequest.getOrdCartsCommList())&&ordCartsCommRequest.getOrdCartsCommList().size()==0){
			LogUtil.info(MODULE, "ordCartsCommRequest.getOrdCartsCommList()========参数异常,可能是初始化造成.：" + ordCartsCommRequest.getOrdCartsCommList());
			return null;
		}
		if(CollectionUtils.isEmpty(ordCartsCommRequest.getOrdCartsCommList())){
            throw new BusinessException(CouponConstants.CoupError.coupon_error_450002);
        }
		if(ordCartsCommRequest.getStaffId()==null||ordCartsCommRequest.getStaffId()<1){
			throw new BusinessException(CouponConstants.CoupError.coupon_error_450001);
		}
		
		List<CoupConsumeReqDTO> coupConsumeReqBeans = coupDetailSV.saveCoupOrdSku(ordCartsCommRequest,CouponConstants.CoupConsume.operType_10);
		LogUtil.info(MODULE, "CoupDetailRSVImpl.countCoupOrdSku========优惠券结算结束.：" + ordCartsCommRequest);
		return coupConsumeReqBeans;
	}
	
	/**
	 * checkOrderCoup:校验用户优惠券可否使用. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupOrdCheckReqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@Override
	public ROrdCartsChkResponse checkOrder(ROrdCartsCommRequest ordCartsCommRequest) throws BusinessException {
		
		LogUtil.info(MODULE, "CoupDetailRSVImpl.checkOrderCoup========校验用户优惠券可否使用开始.：" + ordCartsCommRequest);
		if(ordCartsCommRequest==null){
			return null;
			
        }
		if(CollectionUtils.isEmpty(ordCartsCommRequest.getOrdCartsCommList())){
            return null;
        }
		if(ordCartsCommRequest.getStaffId()==null||ordCartsCommRequest.getStaffId()<1){
			return null;
		}
		
		ROrdCartsChkResponse ordCartsChkResponse;
		try {
			ordCartsChkResponse = coupDetailSV.checkOrder(ordCartsCommRequest);
		} catch (Exception e) {
			ROrdCartsChkResponse ordCartsChkResponse2 = new ROrdCartsChkResponse();
			ordCartsChkResponse2.setMsg(e.getMessage());
			ordCartsChkResponse2.setStatus(false);
			return ordCartsChkResponse2; 
		}
		LogUtil.info(MODULE, "CoupDetailRSVImpl.checkOrderCoup========校验用户优惠券可否使用结束.：" + ordCartsCommRequest);
		
		return ordCartsChkResponse;
	}

	/**
	 * 
	 * deleteOrdCoup:订单取消,优惠券还原. <br/> 
	 * 
	 * @author huanghe5
	 * @param orderId
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@Override
	public void deleteOrdCoup(ROrdCartsCommRequest ordCartsCommRequest)
			throws BusinessException {
		LogUtil.info(MODULE, "CoupDetailRSVImpl.deleteOrdCoup========订单取消,优惠券还原开始.：");
		if(ordCartsCommRequest==null||CollectionUtils.isEmpty(ordCartsCommRequest.getOrdCartsCommList())){
            throw new BusinessException(CouponConstants.CoupError.coupon_error_450001);
        }
		coupDetailSV.deleteOrdCoup(ordCartsCommRequest);
		LogUtil.info(MODULE, "CoupDetailRSVImpl.deleteOrdCoup========订单取消,优惠券还原结束.：");
	}
	
	/**
	 * presentCoupon:赠送优惠券. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupCallBackReqDTO
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@Override
	public void presentCoupon(CouponPresentReqDTO couponPresentReqDTO)
			throws BusinessException {
		LogUtil.info(MODULE, "CoupDetailRSVImpl.presentCoupon========赠送优惠券开始.入参: "+couponPresentReqDTO.toString());
		if(couponPresentReqDTO==null){
            throw new BusinessException(CouponConstants.CoupError.coupon_error_450001);
        }
		if(StringUtil.isBlank(couponPresentReqDTO.getOrderId())||CollectionUtils.isEmpty(couponPresentReqDTO.getCouponDetailPreBeans())){
			throw new BusinessException(CouponConstants.CoupError.coupon_error_450001);
		}
		coupDetailSV.presentCoupon(couponPresentReqDTO);
		
	}
	
	/**
	 * 
	 * saveCoupMes:优惠券领取. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupCallBackReqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@Override
	public void saveCoupGain(CoupCallBackReqDTO coupCallBackReqDTO) throws BusinessException {
		LogUtil.info(MODULE, "CoupDetailRSVImpl.saveCoupGain========优惠券领取开始.入参: "+coupCallBackReqDTO);
		if(coupCallBackReqDTO==null){
            throw new BusinessException(CouponConstants.CoupError.coupon_error_450001);
        }
		if(!CoupUtil.isCoupNotLong(coupCallBackReqDTO.getStaffId())){
			throw new BusinessException(CouponConstants.CoupError.coupon_error_450001);
		}
		if(!CoupUtil.isCoupNotLong(coupCallBackReqDTO.getCoupId())){
			throw new BusinessException(CouponConstants.CoupError.coupon_error_450001);
		}
		
		String s = coupDetailSV.saveCoupGain(coupCallBackReqDTO);
		if("0".equals(s)){
			throw new BusinessException("亲,优惠券领取失败.");
		}
		
		LogUtil.info(MODULE, "CoupDetailRSVImpl.saveCoupGain========优惠券领取结束");
	}
	/**
	 * 查询订单可退还优惠券
	 * @param coupDetailReqDTO
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public CoupOrdNumBackRespDTO queryOrderCoup(CoupOrdBackReqDTO coupOrdBackReqDTO) throws BusinessException {

		LogUtil.info(MODULE, "CoupDetailRSVImpl.queryOrderCoup========查询订单可退还优惠券开始.入参: " + coupOrdBackReqDTO);
		if (coupOrdBackReqDTO == null) {
			throw new BusinessException(CouponConstants.CoupError.coupon_error_450001);
		}
		if (!CoupUtil.isCoupNotLong(coupOrdBackReqDTO.getStaffId())) {
			throw new BusinessException(CouponConstants.CoupError.coupon_error_450001);
		}
		CoupOrdNumBackRespDTO bean = coupDetailSV.queryOrderCoup(coupOrdBackReqDTO);
		LogUtil.info(MODULE, "CoupDetailRSVImpl.queryOrderCoup========查询订单可退还优惠券结束");
		return bean;
	}
	

	/**
	 * 查询用户优惠券类型的剩余可用优惠券
	 * @param coupDetailReqDTO
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public CoupOrdNumBackRespDTO queryStaffCoup(CoupOrdBackReqDTO coupOrdBackReqDTO) throws BusinessException {

		LogUtil.info(MODULE, "CoupDetailRSVImpl.queryStaffCoup========查询用户优惠券类型的剩余可用优惠券开始.入参: " + coupOrdBackReqDTO);
		if (coupOrdBackReqDTO == null) {
			throw new BusinessException(CouponConstants.CoupError.coupon_error_450001);
		}
		if (!CoupUtil.isCoupNotLong(coupOrdBackReqDTO.getStaffId())) {
			throw new BusinessException(CouponConstants.CoupError.coupon_error_450001);
		}
		CoupOrdNumBackRespDTO bean = coupDetailSV.queryStaffCoup(coupOrdBackReqDTO);
		LogUtil.info(MODULE, "CoupDetailRSVImpl.queryStaffCoup========查询用户优惠券类型的剩余可用优惠券结束");
		return bean;
	}

	/**
	 * 同意退款扣减订单赠送的优惠劵
	 * 
	 * @author huanghe5
	 * @param orderId
	 * @param staffId
	 * @throws BusinessException
	 */
	@Override
	public void deductionCoup(String orderId, Long staffId) throws BusinessException {
		// TODO Auto-generated method stub
		
		LogUtil.info(MODULE, "CoupDetailRSVImpl.deductionCoup========查询用户优惠券类型的剩余可用优惠券开始 " );
		
		if (StringUtil.isBlank(orderId)) {
			throw new BusinessException(CouponConstants.CoupError.coupon_error_450001);
		}
		if (StringUtil.isEmpty(staffId)) {
			throw new BusinessException(CouponConstants.CoupError.coupon_error_450001);
		}
		
		coupDetailSV.editDeductionCoup(orderId, staffId);
		
		LogUtil.info(MODULE, "CoupDetailRSVImpl.deductionCoup========查询用户优惠券类型的剩余可用优惠券结束 " );
	}
	
	/**
	 * 
	 * blockCoup:退货同意申请冻结订单赠送的优惠劵. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupOrdBackRespDTO
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@Override
	public CoupCheckBeanRespDTO blockCoup(CoupOrdBackRespDTO coupOrdBackRespDTO)
			throws BusinessException {
		LogUtil.info(MODULE, "CoupDetailRSVImpl.blockCoup========退货同意申请冻结订单赠送的优惠劵开始 " );
		
		if (StringUtil.isBlank(coupOrdBackRespDTO.getOrderId())) {
			throw new BusinessException(CouponConstants.CoupError.coupon_error_450001);
		}
		if (StringUtil.isEmpty(coupOrdBackRespDTO.getStaffId())) {
			throw new BusinessException(CouponConstants.CoupError.coupon_error_450001);
		}
		if (StringUtil.isEmpty(coupOrdBackRespDTO.getapplyId())) {
			throw new BusinessException(CouponConstants.CoupError.coupon_error_450039);
		}
		/*if (CollectionUtils.isEmpty(coupOrdBackRespDTO.getCoupNumBeans())) {
			throw new BusinessException(CouponConstants.CoupError.coupon_error_450001);
		}*/
		CoupCheckBeanRespDTO bean = coupDetailSV.editBlockCoup(coupOrdBackRespDTO);
		LogUtil.info(MODULE, "CoupDetailRSVImpl.blockCoup========退货同意申请冻结订单赠送的优惠劵结束 " );
		return bean;
	}

	/**
	 * 
	 * loseBlackCoup:退货确认退款解冻订单赠送的优惠劵并失效. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupOrdBackRespDTO
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@Override
	public void loseBlackCoup(CoupOrdBackRespDTO coupOrdBackRespDTO)
			throws BusinessException {
		
		LogUtil.info(MODULE, "CoupDetailRSVImpl.loseBlackCoup========退货确认退款解冻订单赠送的优惠劵并失效开始 " );
		
		if (StringUtil.isBlank(coupOrdBackRespDTO.getOrderId())) {
			throw new BusinessException(CouponConstants.CoupError.coupon_error_450001);
		}
		if (StringUtil.isEmpty(coupOrdBackRespDTO.getStaffId())) {
			throw new BusinessException(CouponConstants.CoupError.coupon_error_450001);
		}
		if (StringUtil.isEmpty(coupOrdBackRespDTO.getapplyId())) {
			throw new BusinessException(CouponConstants.CoupError.coupon_error_450039);
		}
		if (CollectionUtils.isEmpty(coupOrdBackRespDTO.getCoupNumBeans())) {
			throw new BusinessException(CouponConstants.CoupError.coupon_error_450001);
		}
		coupDetailSV.editLoseBlackCoup(coupOrdBackRespDTO);
		LogUtil.info(MODULE, "CoupDetailRSVImpl.loseBlackCoup========退货确认退款解冻订单赠送的优惠劵并失效结束 " );
		
	}
	
	/**
	 * 优惠券冻结失败补偿接口
	 * @author huanghe5
	 * @param coupCheckBeanRespDTO
	 * @throws BusinessException
	 */
	@Override
	public void restorePayCoup(CoupCheckBeanRespDTO coupCheckBeanRespDTO)
			throws BusinessException {
		LogUtil.info(MODULE, "CoupDetailRSVImpl.restorePayCoup========优惠券冻结失败补偿开始 " );
		
		if (coupCheckBeanRespDTO==null) {
			throw new BusinessException(CouponConstants.CoupError.coupon_error_450001);
		}
		if (CollectionUtils.isEmpty(coupCheckBeanRespDTO.getCoupDetails())) {
			throw new BusinessException(CouponConstants.CoupError.coupon_error_450001);
		}
		coupDetailSV.editRestorePayCoup(coupCheckBeanRespDTO);
		LogUtil.info(MODULE, "CoupDetailRSVImpl.restorePayCoup========优惠券冻结失败补偿结束" );
	}
	
	/**
	 * 
	 * 优惠券使用明细查询(分页)
	 * @author huanghe5
	 * @param coupConsumeReqDTO
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public PageResponseDTO<CoupConsumeRespDTO> queryCoupConsumePage(
			CoupConsumeReqDTO coupConsumeReqDTO) throws BusinessException {
		LogUtil.info(MODULE, "CoupDetailRSVImpl.queryCoupConsumePage ========优惠券使用明细查询(分页)开始.：" + coupConsumeReqDTO);
		if(coupConsumeReqDTO==null){
            throw new BusinessException(CouponConstants.CoupError.coupon_error_450001);
        }
		if(StringUtil.isBlank(coupConsumeReqDTO.getStatus())){
			coupConsumeReqDTO.setStatus(CouponConstants.CoupSys.status_1);
		}
		
		PageResponseDTO<CoupConsumeRespDTO> beanPage = coupConsumeSV.queryCoupConsumePage(coupConsumeReqDTO);
		
		LogUtil.info(MODULE, "CoupDetailRSVImpl.queryCoupConsumePage ========优惠券使用明细查询(分页)结束.");
		
		return beanPage;
	}

	/**
	 * 
	 * editCoupStatus:寻找过期的优惠券,将其设置为无效. <br/> 
	 * 
	 * @author huanghe5
	 * @throws BusinessException
	 * @since JDK 1.7
	 */
	@Override
	public void editCoupStatus() throws BusinessException {
		coupDetailSV.editCoupStatus();
	}

	/**
	 * 我的优惠券 和 总页数集合
	 * @param coupMeReqDTO
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public CoupMeCountRespDTO queryCoupDetail(CoupMeReqDTO coupMeReqDTO)
			throws BusinessException {
		
		LogUtil.info(MODULE, "CoupDetailRSVImpl.queryCoupDetail ========我的优惠券 和 总页数集合开始.：" + coupMeReqDTO);
		if(coupMeReqDTO==null){
            throw new BusinessException(CouponConstants.CoupError.coupon_error_450001);
        }
		if(StringUtil.isEmpty(coupMeReqDTO.getStaffId())){
			if(coupMeReqDTO.getStaff()!=null){
				coupMeReqDTO.setStaffId(coupMeReqDTO.getStaff().getId());
			}
		}
		if(StringUtil.isEmpty(coupMeReqDTO.getStaffId())){
			throw new BusinessException(CouponConstants.CoupError.coupon_error_450012);
		}
		CoupMeCountRespDTO bean = new CoupMeCountRespDTO();
		PageResponseDTO<CoupMeRespDTO> beanPage= this.queryCoupDetailPage(coupMeReqDTO);
		
		if(beanPage!=null){
			bean.setBeanPage(beanPage);
		}
		
		//1:可使用 2:已使用 0:已过期.
		coupMeReqDTO.setOpeType(CouponConstants.CoupDetail.opeType_0);
		Long coupCount_0 = coupDetailSV.queryCoupDetailCount(coupMeReqDTO);
		coupMeReqDTO.setOpeType(CouponConstants.CoupDetail.opeType_1);
		Long coupCount_1 = coupDetailSV.queryCoupDetailCount(coupMeReqDTO);
		coupMeReqDTO.setOpeType(CouponConstants.CoupDetail.opeType_2);
		Long coupCount_2 = coupDetailSV.queryCoupDetailCount(coupMeReqDTO);
		
		coupMeReqDTO.setOpeType("");
		
		Long coupCount = coupDetailSV.queryCoupDetailCount(coupMeReqDTO);
		
		bean.setCoupCount(coupCount);
		bean.setCoupCount_0(coupCount_0);
		bean.setCoupCount_1(coupCount_1);
		bean.setCoupCount_2(coupCount_2);
		
		LogUtil.info(MODULE, "CoupDetailRSVImpl.queryCoupDetail ========我的优惠券 和 总页数集合结束.");
		
		return bean;
	}
	
	
}
