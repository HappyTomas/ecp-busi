package com.zengshi.ecp.coupon.service.busi.coupUse.interfaces;

import java.util.List;

import com.zengshi.ecp.coupon.dao.model.CoupFreezeCriteria;
import com.zengshi.ecp.coupon.dao.model.CoupPresent;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupCallBackReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupConsumeReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupDetailReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupFreezeReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupMeReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupOrdBackReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupOrdCheckReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupPresentReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CouponPresentReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupCodeRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupFreezeRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupMeRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupOrdBackRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupOrdCheckRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupOrdNumBackRespDTO;
import com.zengshi.ecp.general.order.dto.CoupCheckBeanRespDTO;
import com.zengshi.ecp.general.order.dto.CoupDetailRespDTO;
import com.zengshi.ecp.general.order.dto.ROrdCartCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.general.order.interfaces.IOrderChkRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

public interface ICoupDetailSV extends IGeneralSQLSV , IOrderChkRSV{
	
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
	 * 
	 * saveCoupMes:优惠券领取. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupCallBackReqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public String saveCoupGain(CoupCallBackReqDTO coupCallBackReqDTO) throws BusinessException;
	

	/**
	 * queryCoupDetailList:优惠券用户明细查询 . <br/> 
	 * 
	 * @author huanghe5
	 * @param coupMeReqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public List<CoupDetailRespDTO> queryCoupDetailList(CoupDetailReqDTO coupDetailReqDTO) throws BusinessException;
	
	
	/**
	 * 
	 * queryCoupInfoById:根据ID优惠券明细查询. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupInfoReqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
    public CoupDetailRespDTO queryCoupDetailById(CoupDetailReqDTO coupDetailBean) throws BusinessException;
	
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
	 * 
	 * countCoupOrdSku:优惠券结算. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupOrdCheckReqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public List<CoupConsumeReqDTO> saveCoupOrdSku(ROrdCartsCommRequest ordCartsCommRequest,String operType)throws BusinessException;

	/**
	 * 
	 * checkCoupCode:优惠码使用校验. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupNo
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public boolean checkCoupCode(CoupOrdCheckReqDTO coupOrdCheckReqDTO)throws BusinessException;

	/**
	 * 
	 * deleteOrdCoup:订单取消,优惠券还原. <br/> 
	 * 
	 * @author huanghe5
	 * @param orderId
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public void deleteOrdCoup(String orderId,Long staffId,Long updateStaff)throws BusinessException;
	
	
	/**
	 * 同意退款扣减订单赠送的优惠劵
	 * @param orderId
	 * @param staffId
	 * @throws BusinessException
	 */
	public void editDeductionCoup(String orderId,Long staffId)throws BusinessException;
	
	/**
	 * 
	 * queryCoupPresent:查询优惠券赠送记录. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupPresentReqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public List<CoupPresent> queryCoupPresent(CoupPresentReqDTO coupPresentReqDTO)throws BusinessException;

	/**
	 * 查询用户优惠券类型的剩余可用优惠券
	 * @param coupDetailReqDTO
	 * @return CoupOrdBackRespDTO
	 * @throws BusinessException
	 */
	public CoupOrdNumBackRespDTO queryStaffCoup(CoupOrdBackReqDTO coupOrdBackReqDTO)throws BusinessException;
	
	
	/**
	 * 
	 * blockCoup:退货同意申请冻结订单赠送的优惠劵. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupOrdBackRespDTO
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public CoupCheckBeanRespDTO editBlockCoup(CoupOrdBackRespDTO coupOrdBackRespDTO)throws BusinessException;
	
	/**
	 * 
	 * loseBlackCoup:退货确认退款解冻订单赠送的优惠劵并失效. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupOrdBackRespDTO
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public void editLoseBlackCoup(CoupOrdBackRespDTO coupOrdBackRespDTO)throws BusinessException;

	/**
	 * 优惠券冻结失败补偿接口
	 * @author huanghe5
	 * @param coupCheckBeanRespDTO
	 * @throws BusinessException
	 * @since JDK 1.7
	 */
	public void editRestorePayCoup(CoupCheckBeanRespDTO coupCheckBeanRespDTO)throws BusinessException;

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
	public void presentCoupon(CouponPresentReqDTO couponPresentReqDTO) throws BusinessException;

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
	 * 
	 * queryCoupFreeze:寻找冻结的优惠券. <br/> 
	 * 
	 * @author lisp
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public  List<CoupFreezeRespDTO> queryCoupFreeze(CoupFreezeReqDTO coupFreezeReqDTO) throws BusinessException;

	/**
	 * 初始化CoupFreeze参数
	 * @author lisp
	 */
	public void initCoupFreeze(CoupFreezeCriteria example,CoupFreezeRespDTO coupFreezeRespDTO) throws BusinessException ;
	
	/**
	 * 查询订单可退还优惠券
	 * @author lisp
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

