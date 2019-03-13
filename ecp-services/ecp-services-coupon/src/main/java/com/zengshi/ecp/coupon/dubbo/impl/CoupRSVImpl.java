package com.zengshi.ecp.coupon.dubbo.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.coupon.dubbo.dto.req.CoupInfoReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupInfoRespDTO;
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupRSV;
import com.zengshi.ecp.coupon.dubbo.util.CouponConstants;
import com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupDetailSV;
import com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupInfoSV;
import com.zengshi.ecp.coupon.service.util.CoupUtil;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-10-8 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class CoupRSVImpl implements ICoupRSV {

    private static final String MODULE = CoupRSVImpl.class.getName();
    
    @Resource
    private ICoupInfoSV coupInfoSV;
    
    @Resource
    private IGdsSkuInfoQueryRSV gdsSkuInfoQueryRSV;
    
	@Resource
    private ICoupDetailSV coupDetailSV;
    
    /**
     * 
     * TODO 优惠券规则新增. 
     * Date:2015-10-23下午4:36:46  <br>
     * @author huanghe5
     * @return 0:优惠券  否则是 优惠码 
     * @see com.zengshi.ecp.coupon.dubbo.interfaces.ICoupRSV#saveCoup(com.zengshi.ecp.coupon.dubbo.dto.req.CoupReqDTO)
     */
    public String saveCoup(CoupReqDTO coupReqDTO) throws BusinessException{
    	LogUtil.info(MODULE, "========开始获优惠券规则新增.：" + coupReqDTO);
    	if(coupReqDTO == null){
    		throw new BusinessException(
					CouponConstants.CoupError.coupon_error_450005);
    	}
		// 新增
		coupReqDTO.setEdit(CouponConstants.CoupSys.edit_ADD);
		String coupNo = coupInfoSV.saveCoup(coupReqDTO);

		return coupNo;

    }
    
    
    /**
	 * 
	 * TODO 系统产生优惠码. 
	 * Date:2016-10-6  <br>
	 * @author lisp
	 * @return 
	 * @see com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupInfoSV#saveCoup(com.zengshi.ecp.coupon.dubbo.dto.req.CoupReqDTO)
	 */
	@Override
	public String coupCode( ) throws BusinessException {
		//优惠码
        String coupCode=CoupUtil.createCoupCode();
		return coupCode;
	}
	
    /**
     * 优惠券规则编辑
     * @param coupReqDTO
     * @throws BusinessException
     * @return 0:优惠券  否则是 优惠码 
     * @author huanghe5
     */
	@Override
	public String saveEditCoup(CoupReqDTO coupReqDTO) throws BusinessException {
		LogUtil.info(MODULE, "========开始获优惠券规则编辑.：" + coupReqDTO);
		if(coupReqDTO == null){
    		throw new BusinessException(
					CouponConstants.CoupError.coupon_error_450005);
    	}
		// 编辑
		coupReqDTO.setEdit(CouponConstants.CoupSys.edit_EDIT);
		if(StringUtil.isBlank(coupReqDTO.getCoupInfoReqDTO().getStatus())){
            coupReqDTO.getCoupInfoReqDTO().setStatus(CouponConstants.CoupInfo.STATUS_2);
        }
		String coupNo = coupInfoSV.saveCoup(coupReqDTO);
		LogUtil.info(MODULE, "========获优惠券规则编辑结束.：" + coupReqDTO);
		return coupNo;

	}
	
   /**
    * 
    * TODO 优惠券信息批量删除. 
    * Date:2015-10-23下午6:56:33  <br>
    * @author huanghe5
    * @see com.zengshi.ecp.coupon.dubbo.interfaces.ICoupRSV#deleteBatchCoup(com.zengshi.ecp.coupon.dubbo.dto.req.CoupReqDTO)
    */
    public void deleteBatchCoup(CoupReqDTO coupReqDTO) throws BusinessException {
		LogUtil.info(MODULE, "========开始获优惠券规则删除.：" + coupReqDTO);
		if (coupReqDTO == null || coupReqDTO.getCoupInfoReqDTOs() == null) {
			throw new BusinessException(CouponConstants.CoupError.coupon_error_450004);
		}
		coupInfoSV.deleteBatchCoup(coupReqDTO);

		LogUtil.info(MODULE, "========结束获优惠券规则删除.：" + coupReqDTO);
	}
    
    /**
     * 
     * TODO 优惠券信息失效. 
     * Date:2015-10-26下午8:16:18  <br>
     * @author huanghe5
     * @see com.zengshi.ecp.coupon.dubbo.interfaces.ICoupRSV#validCoup(com.zengshi.ecp.coupon.dubbo.dto.req.CoupReqDTO)
     */
    public void invalidCoup(CoupReqDTO coupReqDTO)
            throws BusinessException{
    	LogUtil.info(MODULE, "========优惠券失效方法开始, 方法名:CoupRSVImpl.invalidCoup 参数信息:" + coupReqDTO);
		if (coupReqDTO == null||coupReqDTO.getCoupInfoReqDTOs()==null) {
			throw new BusinessException(
					CouponConstants.CoupError.coupon_error_450004);
		}
		
		coupReqDTO.setEdit(CouponConstants.CoupInfo.STATUS_0);// 失效
		coupInfoSV.ifInvalidCoup(coupReqDTO);
    	LogUtil.info(MODULE, "========优惠券失效方法结束, 方法名:CoupRSVImpl.invalidCoup 参数信息:" + coupReqDTO);
    	
    }
    
    /**
     * 
     * TODO 优惠券信息生效. 
     * Date:2015-10-26下午8:16:18  <br>
     * @author huanghe5
     * @see com.zengshi.ecp.coupon.dubbo.interfaces.ICoupRSV#validCoup(com.zengshi.ecp.coupon.dubbo.dto.req.CoupReqDTO)
     */
    public void validCoup(CoupReqDTO coupReqDTO)
            throws BusinessException{
    	LogUtil.info(MODULE, "========优惠券生效方法开始, 方法名:CoupRSVImpl.validCoup 参数信息:" + coupReqDTO);
    	if (coupReqDTO == null) {
			throw new BusinessException(
					CouponConstants.CoupError.coupon_error_450004);
		}
    	coupReqDTO.setEdit(CouponConstants.CoupInfo.STATUS_1);// 生效
		coupInfoSV.ifInvalidCoup(coupReqDTO);
    	LogUtil.info(MODULE, "========优惠券生效方法结束, 方法名:CoupRSVImpl.validCoup 参数信息:" + coupReqDTO);

    }
    
   /**
    * 
    * TODO 优惠券信息查询 分页. 
    * Date:2015-10-23下午6:57:39  <br>
    * @author huanghe5
    * @see com.zengshi.ecp.coupon.dubbo.interfaces.ICoupRSV#queryCoupInfoPage(com.zengshi.ecp.coupon.dubbo.dto.req.CoupInfoReqDTO)
    */
    public PageResponseDTO<CoupInfoRespDTO> queryCoupInfoPage(CoupInfoReqDTO coupInfoReqDTO) throws BusinessException{
    	LogUtil.info(MODULE, "========优惠券规则查询开始.：" + coupInfoReqDTO);
    	if(coupInfoReqDTO==null){
            //优惠券类型为空
            throw new BusinessException(CouponConstants.CoupError.coupon_error_450001);
        }
    	PageResponseDTO<CoupInfoRespDTO> beans = coupInfoSV.queryCoupInfoPage(coupInfoReqDTO);
    	LogUtil.info(MODULE, "========优惠券规则查询结束.：" + coupInfoReqDTO);
    	
    	return beans;
    }
    
    
    
    /**
     * 
     * queryCoupInfo:查询有效的优惠券信息Bean. <br/> 
     * @author huangjx
     * @param coupInfoReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public CoupInfoRespDTO queryCoupInfo(CoupInfoReqDTO coupInfoReqDTO) throws BusinessException{
        
        if(coupInfoReqDTO==null){
            throw new BusinessException(CouponConstants.CoupError.coupon_error_450001);
        }
        //为空 返回空
        if(StringUtil.isEmpty(coupInfoReqDTO.getId())){
            return null;
        }
        return coupInfoSV.queryCoupInfoById(coupInfoReqDTO);
        
    }
    
    /**
	 * 
	 * queryCoupInfoPage:优惠券小类查询. <br/> 
	 * 
	 * @author huanghe5 
	 * @param coupInfoReqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@Override
	public List<CoupInfoRespDTO> queryCoupInfoList(List<Long> coupInfoBeans) throws BusinessException {
		
		if(CollectionUtils.isEmpty(coupInfoBeans)){
            throw new BusinessException(CouponConstants.CoupError.coupon_error_450001);
        }
		return coupInfoSV.queryCoupInfoList(coupInfoBeans);
	}

	/**
     * 
     * queryCoupById:查询有效的优惠券信息Bean. <br/> 
     * 
     * @author huanghe5
     * @param coupInfoReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    @Override
    public CoupInfoRespDTO queryCoupById(CoupInfoReqDTO coupInfoReqDTO) throws BusinessException {

        if(coupInfoReqDTO==null){
            throw new BusinessException(CouponConstants.CoupError.coupon_error_450001);
        }
        //为空 返回空
        if(StringUtil.isEmpty(coupInfoReqDTO.getId())){
            return null;
        }
        return coupInfoSV.queryCoupById(coupInfoReqDTO);
    }


    /**
     * 
     * queryCoupInfoByCoupCode:根据优惠码查询优惠券. <br/> 
     * 
     * @author lisp 
     * @param CoupInfoReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
	public CoupInfoRespDTO queryCoupInfoByCoupCode(CoupInfoReqDTO coupInfoReqDTO)
			throws BusinessException{
		 if(coupInfoReqDTO==null){
	            throw new BusinessException(CouponConstants.CoupError.coupon_error_450001);
	        }
		if(StringUtil.isBlank(coupInfoReqDTO.getCoupCode())){
			return null;
			}
		CoupInfoRespDTO coupInfoRespDTO  = coupInfoSV.queryCoupInfoByCoupCode(coupInfoReqDTO);
		return coupInfoRespDTO;
	}
}
