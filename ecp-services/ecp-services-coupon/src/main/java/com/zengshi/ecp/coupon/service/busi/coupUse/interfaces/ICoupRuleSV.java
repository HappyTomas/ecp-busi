package com.zengshi.ecp.coupon.service.busi.coupUse.interfaces;

import com.zengshi.ecp.coupon.dubbo.dto.req.CoupCheckParmReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupLimitCheckRespDTO;
import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-coupon-server <br>
 * Description: <br>
 * Date:2015-10-16上午11:18:45  <br>
 * 
 * @author huanghe5
 * @version  
 * @since JDK 1.7
 */
public interface ICoupRuleSV extends IGeneralSQLSV{
	
	public void save(CoupReqDTO coupReqDTO) throws BusinessException;
	
	public void update(CoupReqDTO coupReqDTO) throws BusinessException;
	
	public void deleteStatus(CoupReqDTO coupReqDTO) throws BusinessException;
	
	/**
	 * 
	 * checkCoupLimit:下单时校验优惠券限制条件. <br/> 
	 * 
	 * @author huanghe5
	 * @param ordCartItemCommRequest
	 * @param coupId
	 * @return 1:通过 0:不通过  2:多个单品组合
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public CoupLimitCheckRespDTO checkCoupLimit(ROrdCartItemCommRequest  ordCartItemCommRequest ,CoupCheckParmReqDTO coupCheckParmReqDTO,CoupLimitCheckRespDTO coupLimitCheckRespDTO)throws BusinessException;
	
	
}

