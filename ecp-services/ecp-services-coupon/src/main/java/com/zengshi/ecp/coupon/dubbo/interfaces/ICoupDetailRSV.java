package com.zengshi.ecp.coupon.dubbo.interfaces;

import java.util.List;

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
import com.zengshi.ecp.general.order.dto.CoupCheckBeanRespDTO;
import com.zengshi.ecp.general.order.dto.ROrdCartCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartsChkResponse;
import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-coupon-server <br>
 * Description: <br>
 * Date:2015-11-7下午12:26:37  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huanghe5
 * @version  
 * @since JDK 1.7
 */
public interface ICoupDetailRSV {
 

    /**
	 * 
	 * queryCoupInfoPage:我的优惠券. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupMeReqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public PageResponseDTO<CoupMeRespDTO> queryCoupDetailPage(CoupMeReqDTO coupMeReqDTO) throws BusinessException;
	
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
	public Long queryCoupDetailCount(CoupMeReqDTO coupMeReqDTO) throws BusinessException;
	
	/**
	 * 我的优惠券 和 总页数集合
	 * @param coupMeReqDTO
	 * @return
	 * @throws BusinessException
	 */
	public CoupMeCountRespDTO queryCoupDetail(CoupMeReqDTO coupMeReqDTO) throws BusinessException;
	
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
	public Long queryCoupValidCount(CoupMeReqDTO coupMeReqDTO) throws BusinessException;

	/**
	 * 
	 * deleteCoupDetail:删除优惠券. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupMeReqDTO
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public void deleteCoupDetail(CoupMeReqDTO coupMeReqDTO) throws BusinessException;
	
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
	public CoupOrdCheckRespDTO queryOrdCheckCoup(ROrdCartsCommRequest ordCartsCommRequest)throws BusinessException;


	/**
	 * checkOrderCoup:校验用户选择优惠券可否使用. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupOrdCheckReqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public ROrdCartsChkResponse checkOrder(ROrdCartsCommRequest ordCartsCommRequest) throws BusinessException;
	
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
	public List<CoupConsumeReqDTO> countCoupOrdSku(ROrdCartsCommRequest ordCartsCommRequest)throws BusinessException;
	
	
	/**
	 * 
	 * deleteOrdCoup:订单取消,优惠券还原. <br/> 
	 * 
	 * @author huanghe5
	 * @param orderId
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public void deleteOrdCoup(ROrdCartsCommRequest ordCartsCommRequest)throws BusinessException;

	/**
	 * presentCoupon:赠送优惠券. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupCallBackReqDTO
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public void presentCoupon(CouponPresentReqDTO couponPresentReqDTO)throws BusinessException;

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
	public void saveCoupGain(CoupCallBackReqDTO coupCallBackReqDTO) throws BusinessException;

	/**
	 * 查询用户优惠券类型的剩余可用优惠券
	 * 
	 * @author huanghe5
	 * @param coupDetailReqDTO
	 * @return
	 * @throws BusinessException
	 */
	public CoupOrdNumBackRespDTO queryStaffCoup(CoupOrdBackReqDTO coupOrdBackReqDTO) throws BusinessException;
	
	/**
	 * 同意退款扣减订单赠送的优惠劵
	 * 
	 * @author huanghe5
	 * @param orderId
	 * @param staffId
	 * @throws BusinessException
	 */
	public void deductionCoup(String orderId,Long staffId)throws BusinessException;

	/**
	 * 
	 * blockCoup:退货同意申请冻结订单赠送的优惠劵. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupOrdBackRespDTO
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public CoupCheckBeanRespDTO blockCoup(CoupOrdBackRespDTO coupOrdBackRespDTO)throws BusinessException;
	
	/**
	 * 优惠券冻结失败补偿接口
	 * @author huanghe5
	 * @param coupCheckBeanRespDTO
	 * @throws BusinessException
	 */
	public void restorePayCoup(CoupCheckBeanRespDTO coupCheckBeanRespDTO)throws BusinessException;
	
	/**
	 * 
	 * loseBlackCoup:退货确认退款解冻订单赠送的优惠劵并失效. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupOrdBackRespDTO
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public void loseBlackCoup(CoupOrdBackRespDTO coupOrdBackRespDTO)throws BusinessException;
	
	/**
	 * 优惠券使用明细查询(分页)
	 * @author huanghe5
	 * @param coupConsumeReqDTO
	 * @return
	 * @throws BusinessException
	 */
	public PageResponseDTO<CoupConsumeRespDTO> queryCoupConsumePage(CoupConsumeReqDTO coupConsumeReqDTO) throws BusinessException;

	/**
	 * 
	 * editCoupStatus:寻找过期的优惠券,将其设置为无效. <br/> 
	 * 
	 * @author huanghe5
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public void editCoupStatus()throws BusinessException;
	
	/**
	 * 查询订单可退还优惠券
	 * @param coupDetailReqDTO
	 * @return
	 * @throws BusinessException
	 */
	public CoupOrdNumBackRespDTO queryOrderCoup(CoupOrdBackReqDTO coupOrdBackReqDTO) throws BusinessException;
	
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
	public CoupCodeRespDTO queryOrdCheckCoupByCode(ROrdCartCommRequest ordCartCommRequest) throws BusinessException;
	
}
