package com.zengshi.ecp.coupon.service.busi.combination.interfaces;

import java.util.List;

import com.zengshi.ecp.coupon.dao.model.CoupBatchConf;
import com.zengshi.ecp.coupon.dao.model.CoupBlackLimitCriteria;
import com.zengshi.ecp.coupon.dao.model.CoupCatgLimitCriteria;
import com.zengshi.ecp.coupon.dao.model.CoupConsume;
import com.zengshi.ecp.coupon.dao.model.CoupConsumeCriteria;
import com.zengshi.ecp.coupon.dao.model.CoupDetail;
import com.zengshi.ecp.coupon.dao.model.CoupDetailCriteria;
import com.zengshi.ecp.coupon.dao.model.CoupDetailLog;
import com.zengshi.ecp.coupon.dao.model.CoupDetailLogCriteria;
import com.zengshi.ecp.coupon.dao.model.CoupFullLimitCriteria;
import com.zengshi.ecp.coupon.dao.model.CoupGetLimitCriteria;
import com.zengshi.ecp.coupon.dao.model.CoupInfoCriteria;
import com.zengshi.ecp.coupon.dao.model.CoupPresent;
import com.zengshi.ecp.coupon.dao.model.CoupPresentCriteria;
import com.zengshi.ecp.coupon.dao.model.CoupShopLimitCriteria;
import com.zengshi.ecp.coupon.dao.model.CoupUseLimitCriteria;
import com.zengshi.ecp.coupon.dao.model.CoupVarLimitCriteria;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupBatchConfReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupBlackLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupCatgLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupConsumeReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupDetailReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupFullLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupGetLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupInfoReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupPresentReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupShopLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupUseLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupVarLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupBatchConfRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupInfoRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-coupon-server <br>
 * Description: <br>
 * Date:2015-11-06上午10:30:50  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huanghe5
 * @version  
 * @since JDK 1.7
 */
public interface ICoupCreateInitSV extends IGeneralSQLSV{
	
	/**
	 * 
	 * initCoupInfoParm:(组合查询优惠券信息表条件). <br/> 
	 * @author huanghe5 
	 * @param example
	 * @param coupInfoReqDTO
	 * @throws BusinessException
	 * @since JDK 1.7
	 */
	void initCoupInfo(CoupInfoCriteria example,CoupInfoReqDTO coupInfoReqDTO) throws BusinessException;
	
	/**
	 * 
	 * saveCoupInfo:优惠券信息表添加.
	 * 
	 * @author huanghe5 
	 * @param coupReqDTO
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	String saveCoupInfo(CoupInfoReqDTO coupInfoReqDTO) throws BusinessException; 
	
	/**
	 * 
	 * saveCoupInfo:优惠券信息日志表添加.
	 * 
	 * @author huanghe5 
	 * @param coupReqDTO
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	void saveCoupInfoLog(CoupInfoReqDTO coupInfoReqDTO) throws BusinessException; 
	
	/**
	 * 
	 * initCoupBlackLimit:黑名单限制规则. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupBlackLimitReqDTO
	 * @param example
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	void initCoupBlackLimit(CoupBlackLimitReqDTO coupBlackLimitReqDTO,CoupBlackLimitCriteria example) throws BusinessException; 
	
	/**
	 * 
	 * initCoupCatgLimit:品类限制规则. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupCatgLimitReqDTO
	 * @param example 
	 * @since JDK 1.7
	 */
	void initCoupCatgLimit(CoupCatgLimitReqDTO coupCatgLimitReqDTO,CoupCatgLimitCriteria example);
	
	/**
	 * 
	 * initCoupCatgLimit:满减限制规则. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupCatgLimitReqDTO
	 * @param example 
	 * @since JDK 1.7
	 */
	void initCoupFullLimit(CoupFullLimitReqDTO coupFullLimitReqDTO,CoupFullLimitCriteria example);
	
	/**
	 * 
	 * initCoupCatgLimit:领取限制规则. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupCatgLimitReqDTO
	 * @param example 
	 * @since JDK 1.7
	 */
	void initCoupGetLimit(CoupGetLimitReqDTO coupGetLimitReqDTO,CoupGetLimitCriteria example);
	
	
	/**
	 * 
	 * initCoupCatgLimit:店铺限制规则. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupCatgLimitReqDTO
	 * @param example 
	 * @since JDK 1.7
	 */
	void initCoupShopLimit(CoupShopLimitReqDTO coupShopLimitReqDTO,CoupShopLimitCriteria example);
	
	/**
	 * 
	 * initCoupCatgLimit:其他使用限制规则. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupCatgLimitReqDTO
	 * @param example 
	 * @since JDK 1.7
	 */
	void initCoupUseLimit(CoupUseLimitReqDTO coupUseLimitReqDTO,CoupUseLimitCriteria example);
	
	/**
	 * 
	 * initCoupCatgLimit:优惠券共同使用规则查询. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupCatgLimitReqDTO
	 * @param example 
	 * @since JDK 1.7
	 */
	void initCoupVarLimit(CoupVarLimitReqDTO coupVarLimitReqDTO,CoupVarLimitCriteria example);
	
    /**
     * 
     * deleteCoup:优惠券信息删除. <br/> 
     * @author huanghe5
     * @param coupReqDTO
     * @throws BusinessException 
     * @since JDK 1.7
     */
    void deleteCoup(CoupInfoReqDTO coupInfoReqDTO) throws BusinessException;
    
    /**
     * 
     * initCoupDetail:优惠券用户明细查询初始化. <br/> 
     * 
     * @author huanghe5
     * @param coupMeReqDTO
     * @throws BusinessException 
     * @since JDK 1.7
     */
    void initCoupDetail(CoupDetailReqDTO coupDetail,CoupDetailCriteria example)throws BusinessException;
    
    /**
     * 
     * initCoupDetail:优惠券用户明细日志查询初始化. <br/> 
     * 
     * @author huanghe5
     * @param coupMeReqDTO
     * @throws BusinessException 
     * @since JDK 1.7
     */
    void initCoupDetailLog(CoupDetailLog coupDetailLog,CoupDetailLogCriteria example)throws BusinessException;
    
    /**
     * 
     * saveCoupDetail:优惠券明细表数据新增. <br/> 
     * 
     * @author huanghe5
     * @param coupDetail
     * @throws BusinessException 
     * @since JDK 1.7
     */
    void saveCoupDetail(CoupDetailReqDTO coupDetailReqDTO,CoupInfoRespDTO coupInfoRespDTO,String orderId) throws BusinessException;
    
    /**
     * 
     * saveCoupDetailIdx:优惠券明细索引表数据新增. <br/> 
     * 
     * @author huanghe5
     * @param coupDetail
     * @throws BusinessException 
     * @since JDK 1.7
     */
    void saveCoupDetailIdx(CoupDetail coupDetail) throws BusinessException; 
    
    /**
	 * 
	 * updateCoupDetail:优惠券用户明细状态修改. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupMeReqDTO
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public void updateCoupDetail(CoupDetailReqDTO coupDetailReqDTO,CoupDetail coupDetail) throws BusinessException;
	
	/**
     * 
     * saveCoupDetail:优惠券明细日志表数据新增. <br/> 
     * 
     * @author huanghe5
     * @param coupDetail
     * @throws BusinessException 
     * @since JDK 1.7
     */
    void saveCoupDetailLog(CoupDetail coupDetail) throws BusinessException;
    
    /**
     * 
     * initCoupSume:优惠券使用明细查询参数配置. <br/> 
     * 
     * @author huanghe5
     * @param coupConsumeReqDTO
     * @throws BusinessException 
     * @since JDK 1.7
     */
    void initCoupCounSume(CoupConsumeReqDTO coupConsumeReqDTO,CoupConsumeCriteria example)throws BusinessException;
    
    
    /**
	 * 
	 * updateCoupDetail:优惠券消费明细修改. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupMeReqDTO
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public void updateCoupCounSume(CoupConsumeReqDTO coupConsumeReqDTO,CoupConsume coupConsume) throws BusinessException;
    
    /**
     * saveCoupConsume:优惠券使用明细新增.<br/> 
     * 
     * @author huanghe5
     * @param coupConsume
     * @throws BusinessException 
     * @since JDK 1.7
     */
    void saveCoupConsume(CoupConsumeReqDTO coupConsumeReqDTO) throws BusinessException;
    
    /**
     * saveCoupConsume:优惠券使用明细索引新增.<br/> 
     * 
     * @author huanghe5
     * @param coupConsume
     * @throws BusinessException 
     * @since JDK 1.7
     */
    void saveCoupConsumeIdx(CoupConsume coupConsume) throws BusinessException;
    
    /**
   	 * 
   	 * updateCoupDetail:优惠券消费明细索引表修改. <br/> 
   	 * 
   	 * @author huanghe5
   	 * @param coupMeReqDTO
   	 * @throws BusinessException 
   	 * @since JDK 1.7
   	 */
   	public void updateCoupCounSumeIdx(CoupConsumeReqDTO coupConsumeReqDTO,CoupConsume coupConsume) throws BusinessException;
    
   	/**
   	 * 
   	 * saveCoupPresent:优惠券订单赠送记录新增. <br/> 
   	 * 
   	 * @author huanghe5
   	 * @param coupParamReqDTO
   	 * @throws BusinessException 
   	 * @since JDK 1.7
   	 */
   	public void saveCoupPresent(CoupPresentReqDTO coupPresentReqDTO)throws BusinessException;
   	
   	/**
     * 
     * initCoupSume:优惠券订单赠送记录查询初始化. <br/> 
     * 
     * @author huanghe5
     * @param coupConsumeReqDTO
     * @throws BusinessException 
     * @since JDK 1.7
     */
    void initCoupPresent(CoupPresentReqDTO coupPresentReqDTO,CoupPresentCriteria example)throws BusinessException;
    
    /**
	 * 
	 * updateCoupDetail:优惠券订单赠送记录修改. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupMeReqDTO
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public void updateCoupPresent(CoupPresentReqDTO coupPresentReqDTO,CoupPresent coupPresent) throws BusinessException;
	
	/**
	 * 
	 * saveCoupBatchConf:新增优惠券批量查询配置表. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupBatchConfReqDTO
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public void saveCoupBatchConf(CoupBatchConfReqDTO coupBatchConfReqDTO)throws BusinessException;

	/**
	 * 
	 * saveCoupBatchConf:修改优惠券批量查询配置表. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupBatchConfReqDTO
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public void updateCoupBatchConf(CoupBatchConfReqDTO coupBatchConfReqDTO,CoupBatchConf coupBatchConf) throws BusinessException;
	
	/**
	 * 
	 * queryCoupBatchConf:查询优惠券批量配置表. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupBatchConfReqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public List<CoupBatchConfRespDTO> queryCoupBatchConf (CoupBatchConfReqDTO coupBatchConfReqDTO)throws BusinessException;
}

