package com.zengshi.ecp.coupon.dubbo.interfaces;

import java.util.List;

import com.zengshi.ecp.coupon.dubbo.dto.req.CoupBlackLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupCatgLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupFullLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupGetLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupShopLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupUseLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupVarLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupBlackLimitRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupCatgLimitRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupFullLimitRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupGetLimitRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupShopLimitRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupUseLimitRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupVarLimitRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface ICoupLimitRSV {

	/**
	 * 
	 * queryCoupInfoPage:查询黑名单表. <br/> 
	 * 
	 * @author huanghe5 
	 * @param coupBlackLimitReqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public List<CoupBlackLimitRespDTO> queryCoupBlack(CoupBlackLimitReqDTO coupBlackLimitReqDTO) throws BusinessException;

	/**
	 * 
	 * 查询品类限制表.  
	 * 
	 * @author huanghe5 
	 * @param CoupCatgLimitReqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public List<CoupCatgLimitRespDTO> queryCoupCatg(CoupCatgLimitReqDTO coupCatgLimitReqDTO) throws BusinessException;
	
	
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
	public List<CoupFullLimitRespDTO> queryCoupFull(CoupFullLimitReqDTO coupFullLimitReqDTO) throws BusinessException;
	

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
	public List<CoupGetLimitRespDTO> queryCoupGet(CoupGetLimitReqDTO coupGetLimitReqDTO) throws BusinessException;
	

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
	public List<CoupShopLimitRespDTO> queryCoupShop(CoupShopLimitReqDTO coupShopLimitReqDTO) throws BusinessException;
	


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
	public List<CoupUseLimitRespDTO> queryCoupUse(CoupUseLimitReqDTO coupUseLimitReqDTO) throws BusinessException;
	


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
	public List<CoupVarLimitRespDTO> queryCoupVAR(CoupVarLimitReqDTO coupVarLimitReqDTO) throws BusinessException;
	

}

