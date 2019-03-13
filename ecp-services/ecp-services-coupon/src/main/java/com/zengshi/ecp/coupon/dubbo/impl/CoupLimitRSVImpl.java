package com.zengshi.ecp.coupon.dubbo.impl;

import java.util.List;

import javax.annotation.Resource;

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
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupLimitRSV;
import com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupBlackLimitSV;
import com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupCatgLimitSV;
import com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupFullLimitSV;
import com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupGetLimitSV;
import com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupShopLimitSV;
import com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupUseLimitSV;
import com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupVARLimitSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

public class CoupLimitRSVImpl implements ICoupLimitRSV{

    @Resource
    private ICoupBlackLimitSV coupBlackLimitSV;
    
    @Resource
    private ICoupCatgLimitSV coupCatgLimitSV;
    
    @Resource
    private ICoupFullLimitSV coupFullLimitSV;
    
    @Resource
    private ICoupShopLimitSV coupShopLimitSV;
    
    @Resource
    private ICoupUseLimitSV coupUseLimitSV;
    
    @Resource
    private ICoupVARLimitSV coupVarLimitSV;
    
    @Resource
    private ICoupGetLimitSV coupGetLimitSV;
    
	/**
	 * 
	 * TODO 黑名单规则信息查询. 
	 * Date:2015-10-23下午7:14:25  <br>
	 * @author huanghe5
	 * @see com.zengshi.ecp.coupon.dubbo.interfaces.ICoupLimitRSV#queryCoupBlackPage(com.zengshi.ecp.coupon.dubbo.dto.req.CoupBlackLimitReqDTO)
	 */
	@Override
	public List<CoupBlackLimitRespDTO> queryCoupBlack(
			CoupBlackLimitReqDTO coupBlackLimitReqDTO) throws BusinessException {
		if(coupBlackLimitReqDTO == null || coupBlackLimitReqDTO.getCoupId()<1){
			return null;
		}
		return coupBlackLimitSV.queryCoupBlackList(coupBlackLimitReqDTO);
	}

	/**
	 * 
	 * TODO 品类信息查询. 
	 * Date:2015-10-23下午7:14:39  <br>
	 * @author huanghe5
	 * @see com.zengshi.ecp.coupon.dubbo.interfaces.ICoupLimitRSV#queryCoupCatgPage(com.zengshi.ecp.coupon.dubbo.dto.req.CoupCatgLimitReqDTO)
	 */
	@Override
	public List<CoupCatgLimitRespDTO> queryCoupCatg(
			CoupCatgLimitReqDTO coupCatgLimitReqDTO) throws BusinessException {
		if(coupCatgLimitReqDTO == null || coupCatgLimitReqDTO.getCoupId()<1){
			return null;
		}
		return coupCatgLimitSV.queryCoupCatgList(coupCatgLimitReqDTO);
	}

	/**
	 * 
	 * TODO 查询满减规则信息. 
	 * Date:2015-10-23下午7:16:14  <br>
	 * @author huanghe5
	 * @see com.zengshi.ecp.coupon.dubbo.interfaces.ICoupLimitRSV#queryCoupFullPage(com.zengshi.ecp.coupon.dubbo.dto.req.CoupFullLimitReqDTO)
	 */
	@Override
	public List<CoupFullLimitRespDTO> queryCoupFull(
			CoupFullLimitReqDTO coupFullLimitReqDTO) throws BusinessException {
		if(coupFullLimitReqDTO == null || coupFullLimitReqDTO.getCoupId()<1){
			return null;
		}
		
		return coupFullLimitSV.queryCoupFullList(coupFullLimitReqDTO);
	}

	/**
	 * 
	 * TODO 领取信息查询. 
	 * Date:2015-10-23下午7:18:46  <br>
	 * @author huanghe5
	 * @see com.zengshi.ecp.coupon.dubbo.interfaces.ICoupLimitRSV#queryCoupGetPage(com.zengshi.ecp.coupon.dubbo.dto.req.CoupGetLimitReqDTO)
	 */
	@Override
	public List<CoupGetLimitRespDTO> queryCoupGet(
			CoupGetLimitReqDTO coupGetLimitReqDTO) throws BusinessException {
		if(coupGetLimitReqDTO == null || coupGetLimitReqDTO.getCoupId()<1){
			return null;
		}
		
		return coupGetLimitSV.queryCoupGetList(coupGetLimitReqDTO);
	}

	/**
	 * 
	 * TODO 查询店铺查询信息. 
	 * Date:2015-10-23下午7:19:52  <br>
	 * @author huanghe5
	 * @see com.zengshi.ecp.coupon.dubbo.interfaces.ICoupLimitRSV#queryCoupShopPage(com.zengshi.ecp.coupon.dubbo.dto.req.CoupShopLimitReqDTO)
	 */
	@Override
	public List<CoupShopLimitRespDTO> queryCoupShop(
			CoupShopLimitReqDTO coupShopLimitReqDTO) throws BusinessException {
		if(coupShopLimitReqDTO == null || coupShopLimitReqDTO.getCoupId()<1){
			return null;
		}
		
		return coupShopLimitSV.queryCoupShopList(coupShopLimitReqDTO,null);
	}
	
	/**
	 * 
	 * TODO 其他使用规则信息. 
	 * Date:2015-10-23下午7:20:23  <br>
	 * @author huanghe5
	 * @see com.zengshi.ecp.coupon.dubbo.interfaces.ICoupLimitRSV#queryCoupUsePage(com.zengshi.ecp.coupon.dubbo.dto.req.CoupUseLimitReqDTO)
	 */
	@Override
	public List<CoupUseLimitRespDTO> queryCoupUse(
			CoupUseLimitReqDTO coupUseLimitReqDTO) throws BusinessException {
		if(coupUseLimitReqDTO == null || coupUseLimitReqDTO.getCoupId()<1){
			return null;
		}
		
		return coupUseLimitSV.queryCoupUseList(coupUseLimitReqDTO);
	}

	/**
	 * 
	 * TODO 查询与其他优惠券使用规则. 
	 * Date:2015-10-23下午7:21:03  <br>
	 * @author huanghe5
	 * @see com.zengshi.ecp.coupon.dubbo.interfaces.ICoupLimitRSV#queryCoupVARPage(com.zengshi.ecp.coupon.dubbo.dto.req.CoupVarLimitReqDTO)
	 */
	@Override
	public List<CoupVarLimitRespDTO> queryCoupVAR(
			CoupVarLimitReqDTO coupVarLimitReqDTO) throws BusinessException {
		if(coupVarLimitReqDTO == null || coupVarLimitReqDTO.getCoupId()<1){
			return null;
		}
		
		return coupVarLimitSV.queryCoupVARList(coupVarLimitReqDTO);
	}

}

