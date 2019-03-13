package com.zengshi.ecp.coupon.service.busi.coupUse.interfaces;

import java.util.List;

import com.zengshi.ecp.coupon.dubbo.dto.req.CoupBatchConfReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupBatchConfRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

public interface ICoupBatch extends IGeneralSQLSV{

	/**
	 * 
	 * saveCoupGain:新增优惠券批量查询配置表. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupCallBackReqDTO
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public void saveCoupBatchConf(CoupBatchConfReqDTO coupBatchConfReqDTO) throws BusinessException;
	
	/**
	 * 
	 * deleteCoupBatchConf:删除优惠券批量查询配置表. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupBatchConfReqDTO
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public void deleteCoupBatchConf(CoupBatchConfReqDTO coupBatchConfReqDTO) throws BusinessException;

	/**
	 * 
	 * editCoupBatchConf:编辑(修改)优惠券批量查询配置表. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupCallBackReqDTO
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public void editCoupBatchConf(CoupBatchConfReqDTO coupBatchConfReqDTO) throws BusinessException;
	
	/**
	 * 
	 * queryCoupBatchConf:查询配置优惠券赠送配置参数. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupBatchConfReqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public List<CoupBatchConfRespDTO> queryCoupBatchConf(CoupBatchConfReqDTO coupBatchConfReqDTO)throws BusinessException;

	/**
	 * 
	 * insertCoupon:根据配置批量赠送优惠券. <br/> 
	 * 
	 * @author huanghe5
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public CoupBatchConfRespDTO insertCoupon()throws BusinessException;
}

