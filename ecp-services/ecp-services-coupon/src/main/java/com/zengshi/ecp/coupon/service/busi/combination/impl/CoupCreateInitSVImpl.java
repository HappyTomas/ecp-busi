package com.zengshi.ecp.coupon.service.busi.combination.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import com.zengshi.ecp.coupon.dao.mapper.busi.CoupBatchConfMapper;
import com.zengshi.ecp.coupon.dao.mapper.busi.CoupConsumeIdxMapper;
import com.zengshi.ecp.coupon.dao.mapper.busi.CoupConsumeMapper;
import com.zengshi.ecp.coupon.dao.mapper.busi.CoupDetailIdxMapper;
import com.zengshi.ecp.coupon.dao.mapper.busi.CoupDetailLogMapper;
import com.zengshi.ecp.coupon.dao.mapper.busi.CoupDetailMapper;
import com.zengshi.ecp.coupon.dao.mapper.busi.CoupInfoLogMapper;
import com.zengshi.ecp.coupon.dao.mapper.busi.CoupInfoMapper;
import com.zengshi.ecp.coupon.dao.mapper.busi.CoupPresentMapper;
import com.zengshi.ecp.coupon.dao.model.CoupBatchConf;
import com.zengshi.ecp.coupon.dao.model.CoupBatchConfCriteria;
import com.zengshi.ecp.coupon.dao.model.CoupBlackLimitCriteria;
import com.zengshi.ecp.coupon.dao.model.CoupCatgLimitCriteria;
import com.zengshi.ecp.coupon.dao.model.CoupConsume;
import com.zengshi.ecp.coupon.dao.model.CoupConsumeCriteria;
import com.zengshi.ecp.coupon.dao.model.CoupConsumeIdx;
import com.zengshi.ecp.coupon.dao.model.CoupConsumeIdxCriteria;
import com.zengshi.ecp.coupon.dao.model.CoupDetail;
import com.zengshi.ecp.coupon.dao.model.CoupDetailCriteria;
import com.zengshi.ecp.coupon.dao.model.CoupDetailIdx;
import com.zengshi.ecp.coupon.dao.model.CoupDetailIdxCriteria;
import com.zengshi.ecp.coupon.dao.model.CoupDetailLog;
import com.zengshi.ecp.coupon.dao.model.CoupDetailLogCriteria;
import com.zengshi.ecp.coupon.dao.model.CoupFullLimitCriteria;
import com.zengshi.ecp.coupon.dao.model.CoupGetLimitCriteria;
import com.zengshi.ecp.coupon.dao.model.CoupInfo;
import com.zengshi.ecp.coupon.dao.model.CoupInfoCriteria;
import com.zengshi.ecp.coupon.dao.model.CoupInfoCriteria.Criteria;
import com.zengshi.ecp.coupon.dao.model.CoupInfoLog;
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
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupCatgLimitRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupFullLimitRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupInfoRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupShopLimitRespDTO;
import com.zengshi.ecp.coupon.dubbo.impl.converter.Converter;
import com.zengshi.ecp.coupon.dubbo.util.CouponConstants;
import com.zengshi.ecp.coupon.service.busi.combination.interfaces.ICoupCreateInitSV;
import com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupCatgLimitSV;
import com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupFullLimitSV;
import com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupInfoSV;
import com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupShopLimitSV;
import com.zengshi.ecp.coupon.service.util.CoupUtil;
import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;
/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-coupon-server <br>
 * Description: <br>
 * Date:2015-11-06上午10:30:53  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huanghe5
 * @version  
 * @since JDK 1.7
 */
public class CoupCreateInitSVImpl extends GeneralSQLSVImpl implements
		ICoupCreateInitSV {
	private static final String MODULE = CoupCreateInitSVImpl.class.getName();
	
	@Resource
    private ICoupInfoSV coupInfoSV;
	
	@Resource(name="shopInfoRSV")
    private IShopInfoRSV shopInfoRSV;
	
	@Resource
    private Converter<CoupDetailReqDTO,CoupPresentReqDTO> coupDetailPresentConverter;
	
	@Resource
    private ICoupFullLimitSV coupFullLimitSV;
	
	@Resource
    private ICoupCatgLimitSV coupCatgLimitSV;
	
	@Resource
    private ICoupShopLimitSV coupShopLimitSV;
	
	@Resource
    private IGdsCategoryRSV gdsCategoryRSV;
	
	@Resource
	private Converter<CoupInfoReqDTO, CoupInfo> coupInfoConverter;
	
	@Resource
	private Converter<CoupInfoReqDTO, CoupInfoLog> coupInfoLogConverter;
	
	@Resource
	private Converter<CoupConsumeReqDTO,CoupConsume> coupConsumeConverter;
	
	@Resource
	private Converter<CoupConsume,CoupConsumeIdx> coupConsumeIdxConverter;
	
	@Resource
	private Converter<CoupDetailReqDTO,CoupDetail> coupDetailConverter;
	@Resource
	private Converter<CoupBatchConf,CoupBatchConfRespDTO> coupBatchConfRespDTOConverter;
	@Resource
	private Converter<CoupDetail,CoupDetailReqDTO> coupDetailDTOConverter;
	
	@Resource
	private Converter<CoupDetail,CoupDetailLog> coupDetailLogConverter;
	
	@Resource
	private Converter<CoupDetail,CoupDetailIdx> coupDetailIdxConverter;
	
	@Resource
	private Converter<CoupPresentReqDTO,CoupPresent> coupPresentConverter;
	
	@Resource
	private Converter<CoupBatchConfReqDTO,CoupBatchConf> coupBatchConfConverter;
	

	@Resource(name = "seq_coup_info_id")
	private PaasSequence seq_coup_info_id;
	
	@Resource(name = "seq_coup_info_log_id")
	private PaasSequence seq_coup_info_log_id;
	
	@Resource(name = "seq_coup_detail_id")
	private PaasSequence seq_coup_detail_id;
	
	@Resource(name = "seq_coup_detail_log_id")
	private PaasSequence seq_coup_detail_log_id;
	
	@Resource(name = "seq_coup_detail_idx_id")
	private PaasSequence seq_coup_detail_idx_id;
	
	@Resource(name = "seq_coup_consume_idx_id")
	private PaasSequence seq_coup_consume_idx_id;

	@Resource(name = "seq_coup_consume_id")
	private PaasSequence seq_coup_consume_id;
	
	@Resource(name = "seq_coup_present_id")
	private PaasSequence seq_coup_present_id;
	
	@Resource(name = "seq_coup_batch_conf_id")
	private PaasSequence seq_coup_batch_conf_id;
	
	@Resource
	private CoupInfoMapper coupInfoMapper;
	
	@Resource
	private CoupPresentMapper coupPresentMapper;
	
	@Resource
	private CoupInfoLogMapper coupInfoLogMapper;
	
	@Resource
	private CoupDetailMapper coupDetailMapper;
	
	@Resource
	private CoupDetailLogMapper coupDetailLogMapper;
	
	@Resource
	private CoupDetailIdxMapper coupDetailIdxMapper;
	
	@Resource
	private CoupConsumeMapper coupConsumeMapper;
	
	@Resource
	private CoupConsumeIdxMapper coupConsumeIdxMapper;
	
	@Resource
	private CoupBatchConfMapper coupBatchConfMapper;
	
	/**
	 * 
	 * initCoupInfoParm:(组合查询优惠券信息表条件). <br/>
	 * 
	 * @author huanghe5
	 * @param example
	 * @param coupInfoReqDTO
	 * @throws BusinessException
	 * @since JDK 1.7
	 */
	@Override
	public void initCoupInfo(CoupInfoCriteria example,
			CoupInfoReqDTO coupInfoReqDTO) throws BusinessException {
		if (coupInfoReqDTO == null) {
			return;
		}
		Criteria cr = example.createCriteria();
		if (CoupUtil.isCoupNotLong(coupInfoReqDTO.getId())) {
			cr.andIdEqualTo(coupInfoReqDTO.getId());
		}
		// 站点
		if (StringUtil.isNotEmpty(coupInfoReqDTO.getSiteId())) {
			cr.andSiteIdEqualTo(coupInfoReqDTO.getSiteId());
		}
		// 优惠券类型ID
		if (CoupUtil.isCoupNotLong(coupInfoReqDTO.getCoupTypeId())) {
			cr.andCoupTypeIdEqualTo(coupInfoReqDTO.getCoupTypeId());
		}
		// 优惠券名称 (模糊查询)
		if (!StringUtil.isEmpty(coupInfoReqDTO.getCoupName())) {
			cr.andCoupNameLike(CouponConstants.CoupSys.LIKE_PERCENT
					+ coupInfoReqDTO.getCoupName()
					+ CouponConstants.CoupSys.LIKE_PERCENT);

		}
		// 优惠券面额
		if (!StringUtil.isEmpty(coupInfoReqDTO.getCoupValue())) {
			cr.andCoupValueEqualTo(coupInfoReqDTO.getCoupValue());
		}

		if (!StringUtil.isEmpty(coupInfoReqDTO.getShopId())) {
			cr.andShopIdEqualTo(coupInfoReqDTO.getShopId());
		}
		// 是否支持优惠码
		if (!StringUtil.isEmpty(coupInfoReqDTO.getIfCode())) {
			cr.andIfCodeEqualTo(coupInfoReqDTO.getIfCode());
		}
		// 是否支持退货
		if (!StringUtil.isEmpty(coupInfoReqDTO.getIfBack())) {
			cr.andIfBackEqualTo(coupInfoReqDTO.getIfBack());
		}
		// 有效期类型
		if (!StringUtil.isEmpty(coupInfoReqDTO.getEffType())) {
			cr.andEffTypeEqualTo(coupInfoReqDTO.getEffType());
		}
		// 状态
		if (!StringUtil.isEmpty(coupInfoReqDTO.getStatus())) {
			cr.andStatusEqualTo(coupInfoReqDTO.getStatus());
		}else{
            cr.andStatusNotEqualTo(CouponConstants.CoupInfo.STATUS_4);
        }
		// 创建时间
		if (!StringUtil.isEmpty(coupInfoReqDTO.getCreateTimeStart())) {
			cr.andCreateTimeLessThanOrEqualTo(new Timestamp(coupInfoReqDTO
					.getCreateTimeStart().getTime()));
		}
		if (!StringUtil.isEmpty(coupInfoReqDTO.getCreateTimeEnd())) {
			cr.andCreateTimeGreaterThanOrEqualTo(new Timestamp(coupInfoReqDTO
					.getCreateTimeEnd().getTime()));
		}
		// if(CouponConstants.CoupInfo.EFF_TYPE_1.equals(coupInfoReqDTO.getEffType())){
		if (StringUtil.isNotEmpty(coupInfoReqDTO.getActiveTime())) {
			// 生效时间
			cr.andActiveTimeGreaterThanOrEqualTo(new Timestamp(coupInfoReqDTO
					.getActiveTime().getTime()));
		}
		if (StringUtil.isNotEmpty(coupInfoReqDTO.getInactiveTime())) {
			// 失效时间
			cr.andInactiveTimeLessThanOrEqualTo(new Timestamp(coupInfoReqDTO
					.getInactiveTime().getTime()));
		}
		// }
		// 创建人
		if (!StringUtil.isEmpty(coupInfoReqDTO.getCreateStaff())) {
			cr.andCreateStaffEqualTo(coupInfoReqDTO.getCreateStaff());
		}
		// 更新时间
		if (!StringUtil.isEmpty(coupInfoReqDTO.getUpdateTimeStart())) {
			cr.andUpdateTimeGreaterThanOrEqualTo(new Timestamp(coupInfoReqDTO
					.getUpdateTimeStart().getTime()));
		}
		if (!StringUtil.isEmpty(coupInfoReqDTO.getUpdateTimeEnd())) {
			cr.andUpdateTimeLessThanOrEqualTo(new Timestamp(coupInfoReqDTO
					.getUpdateTimeEnd().getTime()));
		}
		// 更新人
		if (!StringUtil.isEmpty(coupInfoReqDTO.getUpdateStaff())) {
			cr.andUpdateStaffEqualTo(coupInfoReqDTO.getUpdateStaff());
		}
	}

	/**
	 * 
	 * saveCoupInfo:优惠券信息表添加.
	 * 
	 * @author huanghe5
	 * @param coupReqDTO
	 * @throws BusinessException
	 * @since JDK 1.7
	 */
	@Override
	public String saveCoupInfo(CoupInfoReqDTO coupInfoReqDTO)
			throws BusinessException {
		// 优惠券信息
		coupInfoReqDTO.setId(seq_coup_info_id.nextValue());
		String coupCode ="";
		if(StringUtil.isNotBlank(coupInfoReqDTO.getCoupCode())){
			coupCode = coupInfoReqDTO.getCoupCode().toUpperCase();
		}else{
			if(CouponConstants.CoupInfo.IF_CODE_1.equals(coupInfoReqDTO.getIfCode())){
				coupCode = CoupUtil.createCoupCode();
			}
		}
		coupInfoReqDTO.setCoupCode(coupCode);
		coupInfoReqDTO.setCreateStaff(coupInfoReqDTO.getStaff().getId());
		coupInfoReqDTO.setCreateTime(DateUtil.getSysDate());
		if(coupInfoReqDTO.getCoupValue()!=null&&coupInfoReqDTO.getCoupValue()>0){
			//优惠券面额数据库存储以 分 为单位
			coupInfoReqDTO.setCoupValue(coupInfoReqDTO.getCoupValue());
		}
		// 领取默认0
		if (StringUtil.isEmpty(coupInfoReqDTO.getGetNum())) {
			coupInfoReqDTO.setGetNum(0l);
		}
		if(StringUtil.isBlank(coupInfoReqDTO.getStatus())){
			coupInfoReqDTO.setStatus(CouponConstants.CoupSys.status_1);
		}
		CoupInfo coupInfoBean = coupInfoConverter.convert(coupInfoReqDTO);
		coupInfoMapper.insert(coupInfoBean);
		//添加到优惠券信息日志表
		this.saveCoupInfoLog(coupInfoReqDTO);
		
		return coupCode;
	}

	/**
	 * 
	 * saveCoupInfo:优惠券信息日志表添加.
	 * 
	 * @author huanghe5 
	 * @param coupReqDTO
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@Override
	public void saveCoupInfoLog(CoupInfoReqDTO coupInfoReqDTO)
			throws BusinessException {
		
		CoupInfoLog coupInfoBean = coupInfoLogConverter.convert(coupInfoReqDTO);
		coupInfoBean.setId(seq_coup_info_log_id.nextValue());
		coupInfoBean.setCoupId(coupInfoReqDTO.getId());
		coupInfoBean.setCreateTimeLog(DateUtil.getSysDate());
		coupInfoBean.setCreateStaffLog(coupInfoReqDTO.getStaff().getId());
		
		coupInfoLogMapper.insert(coupInfoBean);
	}
	
	/**
	 * 
	 * TODO 黑名单限制规则查询. Date:2015-10-21下午3:54:18 <br>
	 * 
	 * @author huanghe5
	 * @see com.zengshi.ecp.coupon.service.busi.combination.interfaces.ICoupCreateInitSV#initCoupBlackLimit(com.zengshi.ecp.coupon.dubbo.dto.req.CoupBlackLimitReqDTO,
	 *      com.zengshi.ecp.coupon.dao.model.CoupBlackLimitCriteria)
	 */
	@Override
	public void initCoupBlackLimit(CoupBlackLimitReqDTO coupBlackLimitReqDTO,
			CoupBlackLimitCriteria example) throws BusinessException {
		if (coupBlackLimitReqDTO == null) {
			return;
		}
		com.zengshi.ecp.coupon.dao.model.CoupBlackLimitCriteria.Criteria cr = example
				.createCriteria();
		if (CoupUtil.isCoupNotLong(coupBlackLimitReqDTO.getId())) {
			cr.andIdEqualTo(coupBlackLimitReqDTO.getId());
		}
		// 优惠券信息ID
		if (CoupUtil.isCoupNotLong(coupBlackLimitReqDTO.getCoupId())) {
			cr.andCoupIdEqualTo(coupBlackLimitReqDTO.getCoupId());
		}
		// 品类类型信息 0:单品级,1:商品级,2:分类级别
		if (StringUtil.isNotBlank(coupBlackLimitReqDTO.getCategoryType())) {
			cr.andCategoryTypeEqualTo(coupBlackLimitReqDTO.getCategoryType());
		}
		// 分类编码
		if (StringUtil.isNotBlank(coupBlackLimitReqDTO.getCatgCode())) {
			cr.andCatgCodeEqualTo(coupBlackLimitReqDTO.getCatgCode());
		}
		if (CoupUtil.isCoupNotLong(coupBlackLimitReqDTO.getGdsId())) {
			cr.andGdsIdEqualTo(coupBlackLimitReqDTO.getGdsId());
		}
		if (CoupUtil.isCoupNotLong(coupBlackLimitReqDTO.getSkuId())) {
			cr.andSkuIdEqualTo(coupBlackLimitReqDTO.getSkuId());
		}
		if (StringUtil.isNotBlank(coupBlackLimitReqDTO.getStatus())) {
			cr.andStatusEqualTo(coupBlackLimitReqDTO.getStatus());
		}
		if (CoupUtil.isCoupNotLong(coupBlackLimitReqDTO.getCreateStaff())) {
			cr.andCreateStaffEqualTo(coupBlackLimitReqDTO.getCreateStaff());
		}
	}

	/**
	 * 
	 * initCoupCatgLimit:品类限制规则. <br/>
	 * 
	 * @author huanghe5
	 * @param coupCatgLimitReqDTO
	 * @param example
	 * @since JDK 1.7
	 */
	@Override
	public void initCoupCatgLimit(CoupCatgLimitReqDTO coupCatgLimitReqDTO,
			CoupCatgLimitCriteria example) {
		if (coupCatgLimitReqDTO == null) {
			return;
		}
		com.zengshi.ecp.coupon.dao.model.CoupCatgLimitCriteria.Criteria cr = example
				.createCriteria();
		if (CoupUtil.isCoupNotLong(coupCatgLimitReqDTO.getId())) {
			cr.andIdEqualTo(coupCatgLimitReqDTO.getId());
		}
		// 优惠券信息ID
		if (CoupUtil.isCoupNotLong(coupCatgLimitReqDTO.getCoupId())) {
			cr.andCoupIdEqualTo(coupCatgLimitReqDTO.getCoupId());
		}
		// 品类类型信息 0:单品级,1:商品级,2:分类级别
		if (StringUtil.isNotBlank(coupCatgLimitReqDTO.getCategoryType())) {
			cr.andCategoryTypeEqualTo(coupCatgLimitReqDTO.getCategoryType());
		}
		// 分类编码
		if (StringUtil.isNotBlank(coupCatgLimitReqDTO.getCatgCode())) {
			cr.andCatgCodeEqualTo(coupCatgLimitReqDTO.getCatgCode());
		}
		if (CoupUtil.isCoupNotLong(coupCatgLimitReqDTO.getGdsId())) {
			cr.andGdsIdEqualTo(coupCatgLimitReqDTO.getGdsId());
		}
		if (CoupUtil.isCoupNotLong(coupCatgLimitReqDTO.getSkuId())) {
			cr.andSkuIdEqualTo(coupCatgLimitReqDTO.getSkuId());
		}
		if (StringUtil.isNotBlank(coupCatgLimitReqDTO.getStatus())) {
			cr.andStatusEqualTo(coupCatgLimitReqDTO.getStatus());
		}
		if (CoupUtil.isCoupNotLong(coupCatgLimitReqDTO.getCreateStaff())) {
			cr.andCreateStaffEqualTo(coupCatgLimitReqDTO.getCreateStaff());
		}
	}

	/**
	 * 
	 * initCoupCatgLimit:满减限制规则. <br/>
	 * 
	 * @author huanghe5
	 * @param coupCatgLimitReqDTO
	 * @param example
	 * @since JDK 1.7
	 */
	@Override
	public void initCoupFullLimit(CoupFullLimitReqDTO coupFullLimitReqDTO,
			CoupFullLimitCriteria example) {

		if (coupFullLimitReqDTO == null) {
			return;
		}
		com.zengshi.ecp.coupon.dao.model.CoupFullLimitCriteria.Criteria cr = example
				.createCriteria();
		if (CoupUtil.isCoupNotLong(coupFullLimitReqDTO.getId())) {
			cr.andIdEqualTo(coupFullLimitReqDTO.getId());
		}
		// 优惠券信息ID
		if (CoupUtil.isCoupNotLong(coupFullLimitReqDTO.getCoupId())) {
			cr.andCoupIdEqualTo(coupFullLimitReqDTO.getCoupId());
		}
		// 满减类型 1:满金额2满数量
		if (StringUtil.isNotBlank(coupFullLimitReqDTO.getType())) {
			cr.andTypeEqualTo(coupFullLimitReqDTO.getType());
		}
		// 满金额
		if (CoupUtil.isCoupNotLong(coupFullLimitReqDTO.getSumLimit())) {
			cr.andSumLimitEqualTo(coupFullLimitReqDTO.getSumLimit());
		}
		// 满数量
		if (CoupUtil.isCoupNotLong(coupFullLimitReqDTO.getAmount())) {
			cr.andAmountEqualTo(coupFullLimitReqDTO.getAmount());
		}
		if (StringUtil.isNotBlank(coupFullLimitReqDTO.getStatus())) {
			cr.andStatusEqualTo(coupFullLimitReqDTO.getStatus());
		}
		if (CoupUtil.isCoupNotLong(coupFullLimitReqDTO.getCreateStaff())) {
			cr.andCreateStaffEqualTo(coupFullLimitReqDTO.getCreateStaff());
		}

	}

	/**
	 * 
	 * initCoupCatgLimit:领取限制规则. <br/>
	 * 
	 * @author huanghe5
	 * @param coupCatgLimitReqDTO
	 * @param example
	 * @since JDK 1.7
	 */
	@Override
	public void initCoupGetLimit(CoupGetLimitReqDTO coupGetLimitReqDTO,
			CoupGetLimitCriteria example) {
		if (coupGetLimitReqDTO == null) {
			return;
		}
		com.zengshi.ecp.coupon.dao.model.CoupGetLimitCriteria.Criteria cr = example
				.createCriteria();
		if (CoupUtil.isCoupNotLong(coupGetLimitReqDTO.getId())) {
			cr.andIdEqualTo(coupGetLimitReqDTO.getId());
		}
		// 优惠券信息ID
		if (CoupUtil.isCoupNotLong(coupGetLimitReqDTO.getCoupId())) {
			cr.andCoupIdEqualTo(coupGetLimitReqDTO.getCoupId());
		}
		if (StringUtil.isNotBlank(coupGetLimitReqDTO.getStatus())) {
			cr.andStatusEqualTo(coupGetLimitReqDTO.getStatus());
		}

	}

	/**
	 * 
	 * initCoupCatgLimit:店铺限制规则. <br/>
	 * 
	 * @author huanghe5
	 * @param coupCatgLimitReqDTO
	 * @param example
	 * @since JDK 1.7
	 */
	@Override
	public void initCoupShopLimit(CoupShopLimitReqDTO coupShopLimitReqDTO,
			CoupShopLimitCriteria example) {
		if (coupShopLimitReqDTO == null) {
			return;
		}
		com.zengshi.ecp.coupon.dao.model.CoupShopLimitCriteria.Criteria cr = example
				.createCriteria();
		
		if (CoupUtil.isCoupNotLong(coupShopLimitReqDTO.getId())) {
			cr.andIdEqualTo(coupShopLimitReqDTO.getId());
		}
		// 优惠券信息ID
		if (CoupUtil.isCoupNotLong(coupShopLimitReqDTO.getCoupId())) {
			cr.andCoupIdEqualTo(coupShopLimitReqDTO.getCoupId());
		}
		// 店铺ID
		if (CoupUtil.isCoupNotLong(coupShopLimitReqDTO.getShopId())) {
			cr.andShopIdEqualTo(coupShopLimitReqDTO.getShopId());
			
		}
		// 状态
		if (StringUtil.isNotBlank(coupShopLimitReqDTO.getStatus())) {
			cr.andStatusEqualTo(coupShopLimitReqDTO.getStatus());
		}

	}

	/**
	 * 
	 * initCoupCatgLimit:其他使用限制规则. <br/>
	 * 
	 * @author huanghe5
	 * @param coupCatgLimitReqDTO
	 * @param example
	 * @since JDK 1.7
	 */
	@Override
	public void initCoupUseLimit(CoupUseLimitReqDTO coupUseLimitReqDTO,
			CoupUseLimitCriteria example) {

		if (coupUseLimitReqDTO == null) {
			return;
		}
		com.zengshi.ecp.coupon.dao.model.CoupUseLimitCriteria.Criteria cr = example
				.createCriteria();
		if (CoupUtil.isCoupNotLong(coupUseLimitReqDTO.getId())) {
			cr.andIdEqualTo(coupUseLimitReqDTO.getId());
		}
		// 优惠券信息ID
		if (CoupUtil.isCoupNotLong(coupUseLimitReqDTO.getCoupId())) {
			cr.andCoupIdEqualTo(coupUseLimitReqDTO.getCoupId());
		}
		// 使用规则ID
		if (StringUtil.isNotBlank(coupUseLimitReqDTO.getUseRuleKey())) {
			cr.andUseRuleKeyEqualTo(coupUseLimitReqDTO.getUseRuleKey());
		}
		// 状态
		if (StringUtil.isNotBlank(coupUseLimitReqDTO.getStatus())) {
			cr.andStatusEqualTo(coupUseLimitReqDTO.getStatus());
		}
	}

	/**
	 * 
	 * initCoupCatgLimit:优惠券共同使用规则查询. <br/>
	 * 
	 * @author huanghe5
	 * @param coupCatgLimitReqDTO
	 * @param example
	 * @since JDK 1.7
	 */
	@Override
	public void initCoupVarLimit(CoupVarLimitReqDTO coupVarLimitReqDTO,
			CoupVarLimitCriteria example) {
		if (coupVarLimitReqDTO == null) {
			return;
		}
		com.zengshi.ecp.coupon.dao.model.CoupVarLimitCriteria.Criteria cr = example
				.createCriteria();
		if (CoupUtil.isCoupNotLong(coupVarLimitReqDTO.getId())) {
			cr.andIdEqualTo(coupVarLimitReqDTO.getId());
		}
		// 优惠券信息ID
		if (CoupUtil.isCoupNotLong(coupVarLimitReqDTO.getCoupId())) {
			cr.andCoupIdEqualTo(coupVarLimitReqDTO.getCoupId());
		}

		// 优惠券信息ID2
		if (CoupUtil.isCoupNotLong(coupVarLimitReqDTO.getCoupId2())) {
			cr.andCoupId2EqualTo(coupVarLimitReqDTO.getCoupId2());
		}

		// 状态
		if (StringUtil.isNotBlank(coupVarLimitReqDTO.getStatus())) {
			cr.andStatusEqualTo(coupVarLimitReqDTO.getStatus());
		}

	}

	/**
	 * 
	 * deleteCoup:优惠券信息删除. <br/>
	 * 
	 * @author huanghe5
	 * @param coupReqDTO
	 * @throws BusinessException
	 * @since JDK 1.7
	 */
	@Override
	public void deleteCoup(CoupInfoReqDTO coupInfoReqDTO)
			throws BusinessException {

		CoupInfo bean = new CoupInfo();
		bean.setStatus(coupInfoReqDTO.getStatus());
		bean.setUpdateTime(DateUtil.getSysDate());
		bean.setUpdateStaff(coupInfoReqDTO.getStaff().getId());
		CoupInfoCriteria example = new CoupInfoCriteria();
		CoupInfoCriteria.Criteria cr = example.createCriteria();
		if (CoupUtil.isCoupNotLong(coupInfoReqDTO.getId())) {
			cr.andIdEqualTo(coupInfoReqDTO.getId());// 优惠券信息ID
			coupInfoMapper.updateByExampleSelective(bean, example);
		}
	}

	/**
	 * 
	 * TODO 优惠券用户明细查询初始化. Date:2015-10-31下午3:51:56 <br>
	 * 
	 * @author huanghe5
	 * @see com.zengshi.ecp.coupon.service.busi.combination.interfaces.ICoupCreateInitSV#initCoupDetail(com.zengshi.ecp.coupon.dubbo.dto.req.CoupMeReqDTO)
	 */
	@Override
	public void initCoupDetail(CoupDetailReqDTO coupDetail, CoupDetailCriteria example)
			throws BusinessException {

		if (coupDetail == null) {
			return;
		}
		com.zengshi.ecp.coupon.dao.model.CoupDetailCriteria.Criteria cr = example
				.createCriteria();
		if (StringUtil.isNotEmpty(coupDetail.getId())) {
			cr.andIdEqualTo(coupDetail.getId());
		}
		// 站点
		if (StringUtil.isNotEmpty(coupDetail.getSiteId())) {
			cr.andSiteIdEqualTo(coupDetail.getSiteId());
		}
		if (StringUtil.isNotEmpty(coupDetail.getStaffId())) {
			cr.andStaffIdEqualTo(coupDetail.getStaffId());
		}
		// 优惠券类型ID
		if (CoupUtil.isCoupNotLong(coupDetail.getCoupTypeId())) {
			cr.andCoupTypeIdEqualTo(coupDetail.getCoupTypeId());
		}
		// 优惠券信息ID
		if (CoupUtil.isCoupNotLong(coupDetail.getCoupId())) {
			cr.andCoupIdEqualTo(coupDetail.getCoupId());
		}
		// 优惠券面额
		if (!StringUtil.isEmpty(coupDetail.getCoupValue())) {
			cr.andCoupValueEqualTo(coupDetail.getCoupValue());
		}

		if (!StringUtil.isEmpty(coupDetail.getShopId())) {
			cr.andShopIdEqualTo(coupDetail.getShopId());
		}
		// 来源
		if (StringUtil.isNotBlank(coupDetail.getCoupSource())) {
			cr.andCoupSourceEqualTo(coupDetail.getCoupSource());
		}
		// 状态
		if (StringUtil.isNotBlank(coupDetail.getStatus())) {
			cr.andStatusEqualTo(coupDetail.getStatus());
		}
		// 使用时间
		if (!StringUtil.isEmpty(coupDetail.getUseTimeStart())) {
			cr.andUseTimeGreaterThanOrEqualTo(new Timestamp(coupDetail
					.getUseTimeStart().getTime()));
		}
		if (!StringUtil.isEmpty(coupDetail.getUseTimeEnd())) {
			cr.andUseTimeLessThanOrEqualTo(new Timestamp(coupDetail
					.getUseTimeEnd().getTime()));
		}
		
		// 领取时间
		if (!StringUtil.isEmpty(coupDetail.getCreateTimeStart())) {
			cr.andCreateTimeLessThanOrEqualTo(new Timestamp(coupDetail
					.getCreateTimeStart().getTime()));
		}
		if (!StringUtil.isEmpty(coupDetail.getCreateTimeEnd())) {
			cr.andCreateTimeGreaterThanOrEqualTo(new Timestamp(coupDetail
					.getCreateTimeEnd().getTime()));
			
		}
		//批次
		if(StringUtil.isNotEmpty(coupDetail.getBatchId())){
			cr.andBatchIdEqualTo(coupDetail.getBatchId());
		}
		//是否使用 0:未使用 , 1:使用
		if(StringUtil.isNotBlank(coupDetail.getIfUse())){
			cr.andIfUseEqualTo(coupDetail.getIfUse());
		}
		if(StringUtil.isNotBlank(coupDetail.getTypeLimit())){
			cr.andTypeLimitEqualTo(coupDetail.getTypeLimit());
		}
		
		if(CouponConstants.CoupDetail.opeType_0.equals(coupDetail.getOpeType())){//已经过期

			if (StringUtil.isNotEmpty(coupDetail.getInactiveTime())) {
				// 失效时间
				cr.andInactiveTimeLessThan(new Timestamp(coupDetail
						.getInactiveTime().getTime()));
			}
		}else{
			if (StringUtil.isNotEmpty(coupDetail.getActiveTime())) {
				// 生效时间
				cr.andActiveTimeLessThanOrEqualTo(new Timestamp(coupDetail
						.getActiveTime().getTime()));
			}
			if (StringUtil.isNotEmpty(coupDetail.getInactiveTime())) {
				// 失效时间
				cr.andInactiveTimeGreaterThanOrEqualTo(new Timestamp(coupDetail
						.getInactiveTime().getTime()));
			}
		}
		if (CouponConstants.CoupSys.TYPE_LIMIT_1.equals(coupDetail
				.getTypeLimit())
				&& StringUtil.isNotEmpty(coupDetail.getShopId())) {
			com.zengshi.ecp.coupon.dao.model.CoupDetailCriteria.Criteria cr2 = example
					.createCriteria();
			if (StringUtil.isNotEmpty(coupDetail.getId())) {
				cr2.andIdEqualTo(coupDetail.getId());
			}
			// 站点
			if (StringUtil.isNotEmpty(coupDetail.getSiteId())) {
				cr2.andSiteIdEqualTo(coupDetail.getSiteId());
			}
			if (StringUtil.isNotEmpty(coupDetail.getStaffId())) {
				cr2.andStaffIdEqualTo(coupDetail.getStaffId());
			}
			// 优惠券类型ID
			if (CoupUtil.isCoupNotLong(coupDetail.getCoupTypeId())) {
				cr2.andCoupTypeIdEqualTo(coupDetail.getCoupTypeId());
			}
			// 优惠券信息ID
			if (CoupUtil.isCoupNotLong(coupDetail.getCoupId())) {
				cr2.andCoupIdEqualTo(coupDetail.getCoupId());
			}
			// 优惠券面额
			if (!StringUtil.isEmpty(coupDetail.getCoupValue())) {
				cr2.andCoupValueEqualTo(coupDetail.getCoupValue());
			}

			if (!StringUtil.isEmpty(coupDetail.getShopId())) {
				cr2.andShopIdEqualTo(Long.valueOf(CouponConstants.CoupSys.shopInfinite));
			}
			// 来源
			if (StringUtil.isNotBlank(coupDetail.getCoupSource())) {
				cr2.andCoupSourceEqualTo(coupDetail.getCoupSource());
			}
			// 状态
			if (StringUtil.isNotBlank(coupDetail.getStatus())) {
				cr2.andStatusEqualTo(coupDetail.getStatus());
			}
			// 使用时间
			if (!StringUtil.isEmpty(coupDetail.getUseTimeStart())) {
				cr2.andUseTimeGreaterThanOrEqualTo(new Timestamp(coupDetail
						.getUseTimeStart().getTime()));
			}
			if (!StringUtil.isEmpty(coupDetail.getUseTimeEnd())) {
				cr2.andUseTimeLessThanOrEqualTo(new Timestamp(coupDetail
						.getUseTimeEnd().getTime()));
			}
			if(StringUtil.isNotBlank(coupDetail.getTypeLimit())){
				cr2.andTypeLimitEqualTo(coupDetail.getTypeLimit());
			}
			// 领取时间
			if (!StringUtil.isEmpty(coupDetail.getCreateTimeStart())) {
				cr2.andCreateTimeLessThanOrEqualTo(new Timestamp(coupDetail
						.getCreateTimeStart().getTime()));
			}
			if (!StringUtil.isEmpty(coupDetail.getCreateTimeEnd())) {
				cr2.andCreateTimeGreaterThanOrEqualTo(new Timestamp(coupDetail
						.getCreateTimeEnd().getTime()));
			}
			//批次
			if(StringUtil.isNotEmpty(coupDetail.getBatchId())){
				cr2.andBatchIdEqualTo(coupDetail.getBatchId());
			}
			//是否使用 0:未使用 , 1:使用
			if(StringUtil.isNotBlank(coupDetail.getIfUse())){
				cr2.andIfUseEqualTo(coupDetail.getIfUse());
			}
			
			if(CouponConstants.CoupDetail.opeType_0.equals(coupDetail.getOpeType())){//已经过期

				if (StringUtil.isNotEmpty(coupDetail.getInactiveTime())) {
					// 失效时间
					cr2.andInactiveTimeLessThan(new Timestamp(coupDetail
							.getInactiveTime().getTime()));
					
				}
			}else{
				if (StringUtil.isNotEmpty(coupDetail.getActiveTime())) {
					// 生效时间
					cr2.andActiveTimeLessThanOrEqualTo(new Timestamp(coupDetail
							.getActiveTime().getTime()));
				}
				if (StringUtil.isNotEmpty(coupDetail.getInactiveTime())) {
					// 失效时间
					cr2.andInactiveTimeGreaterThanOrEqualTo(new Timestamp(coupDetail
							.getInactiveTime().getTime()));
				}
			}
			example.or(cr2);
		}
	}
	
	/**
     * 
     * saveCoupInfo:优惠券明细表数据新增. <br/> 
     * 
     * @author huanghe5
     * @param coupDetail
     * @throws BusinessException 
     * @since JDK 1.7
     */
	@Override
	public void saveCoupDetail(CoupDetailReqDTO coupDetailReqDTO,CoupInfoRespDTO coupInfoRespDTO,String orderId)
			throws BusinessException {
		
		if(coupDetailReqDTO == null){
			return;
		}
		String coupNo = CoupUtil.createCoupNo();
		coupDetailReqDTO.setCoupNo(coupNo);
		CoupInfoReqDTO coupInfoBean = new CoupInfoReqDTO();
		coupInfoBean.setId(coupDetailReqDTO.getCoupId());
		if(StringUtil.isNotEmpty(coupInfoRespDTO)){
			if(coupInfoRespDTO != null){
				//从优惠券信息表转移到优惠券用户明细表里
				this.structureCoupDetail(coupInfoRespDTO,coupDetailReqDTO);
				// 优惠券明细信息
				coupDetailReqDTO.setId(seq_coup_detail_id.nextValue());
				if(CoupUtil.isCoupNotLong(coupDetailReqDTO.getStaff().getId())){
					coupDetailReqDTO.setCreateStaff(coupDetailReqDTO.getStaff().getId());
				}else if(CoupUtil.isCoupNotLong(coupDetailReqDTO.getStaffId())){
					coupDetailReqDTO.setCreateStaff(coupDetailReqDTO.getStaffId());
				}
				if(StringUtil.isBlank(coupDetailReqDTO.getStatus())){
					coupDetailReqDTO.setStatus(CouponConstants.CoupSys.status_1);
				}
				coupDetailReqDTO.setCreateTime(DateUtil.getSysDate());
				if(StringUtil.isEmpty(coupDetailReqDTO.getShopId())){
					coupDetailReqDTO.setShopId(coupInfoRespDTO.getShopId());
				}
				if(StringUtil.isEmpty(coupDetailReqDTO.getIfUse())){
					coupDetailReqDTO.setIfUse(CouponConstants.CoupDetail.IF_USE_1);//0:未使用 , 1:使用
				}
				if(StringUtil.isEmpty(coupDetailReqDTO.getBatchId())){
					coupDetailReqDTO.setBatchId(Long.valueOf(-1));//主动领取
				}
				
				CoupDetail coupDetail = coupDetailConverter.convert(coupDetailReqDTO);
				
				coupDetailMapper.insert(coupDetail);
				//添加索引表信息
				this.saveCoupDetailIdx(coupDetail);
				coupDetail.setStatus(CouponConstants.CoupSys.status_log_0);//新增
				//添加日志表信息
				this.saveCoupDetailLog(coupDetail);
				
				if(CouponConstants.CoupDetail.COUP_SOURCE_20.equals(coupDetailReqDTO.getCoupSource())){
					CoupPresentReqDTO coupPresentReqDTO= coupDetailPresentConverter.convert(coupDetailReqDTO);
					if(StringUtil.isNotBlank(orderId)){
						coupPresentReqDTO.setOrderId(orderId);
					}else{
						coupPresentReqDTO.setOrderId("0");
					}
					coupPresentReqDTO.setCoupDetailId(coupDetailReqDTO.getId());
					coupPresentReqDTO.setShopId(coupDetailReqDTO.getShopId());
					this.saveCoupPresent(coupPresentReqDTO);
				}
			}
		}
	}
	
	private void structureCoupDetail(CoupInfoRespDTO coupInfoRespDTO,
			CoupDetailReqDTO coupDetailReqDTO) {
		
		Timestamp sysDate= DateUtil.getSysDate();
		
		if (CouponConstants.CoupInfo.EFF_TYPE_0.equals(coupInfoRespDTO
				.getEffType())) {
			if (StringUtil.isNotEmpty(coupInfoRespDTO.getFixedTime())) {
				coupDetailReqDTO.setActiveTime(sysDate);
				coupDetailReqDTO.setInactiveTime(CoupUtil.createFullTime(
						sysDate, coupInfoRespDTO.getFixedTime()));
			}
		} else if (CouponConstants.CoupInfo.EFF_TYPE_1.equals(coupInfoRespDTO
				.getEffType())) {
			coupDetailReqDTO.setActiveTime(new Timestamp(coupInfoRespDTO
					.getActiveTime().getTime()));
			coupDetailReqDTO.setInactiveTime(new Timestamp(coupInfoRespDTO
					.getInactiveTime().getTime()));
		}
		coupDetailReqDTO.setUseRuleCode(coupInfoRespDTO.getUseRuleCode());
		coupDetailReqDTO.setGainRuleCode(coupInfoRespDTO.getGainRuleCode());

		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) JSON
				.parse(coupInfoRespDTO.getUseRuleCode());
		if (map != null && map.entrySet().iterator() != null) {
			Iterator<Entry<String, Object>> beans = map.entrySet().iterator();
			while (beans.hasNext()) {
				Entry<String, Object> ebean = beans.next();
				//如果是满减进行封装
				if (CouponConstants.CoupPara.RULE_CODE_140.equals(ebean
						.getKey().toString())) {
					String s = "";
					CoupFullLimitReqDTO coupFullLimitReqDTO = new CoupFullLimitReqDTO();
					coupFullLimitReqDTO.setCoupId(coupInfoRespDTO.getId());
					List<CoupFullLimitRespDTO> fullBean = coupFullLimitSV
							.queryCoupFullList(coupFullLimitReqDTO);
					if (CollectionUtils.isNotEmpty(fullBean)) {
						for (CoupFullLimitRespDTO coupFullLimitRespDTO2 : fullBean) {
							if (CouponConstants.CoupFullLimit.TYPE_1
									.equals(coupFullLimitRespDTO2.getType())) {
								if(StringUtil.isNotEmpty(coupInfoRespDTO.getCoupValue())&&coupInfoRespDTO.getCoupValue()>0){
									Double coupValue = coupInfoRespDTO.getCoupValue()/100.00;
									s=coupFullLimitRespDTO2.getSumLimit()/100.00+"-"+coupValue;
									coupDetailReqDTO.setCoupValueShow(s);
								}
							}
						}
					}
				}
				if(CouponConstants.CoupPara.RULE_CODE_110.equals(ebean
						.getKey().toString())&&StringUtil.isEmpty(coupDetailReqDTO.getConditionsShow())){
					if(StringUtil.isNotBlank(coupDetailReqDTO.getConditionsShow())){
						continue;
					}
					String s = CouponConstants.CoupElse.e1;
					CoupCatgLimitReqDTO coupCatgLimitReqDTO = new CoupCatgLimitReqDTO();
					coupCatgLimitReqDTO.setCoupId(coupInfoRespDTO.getId());
					List<CoupCatgLimitRespDTO> catgBeans =  coupCatgLimitSV.queryCoupCatgList(coupCatgLimitReqDTO);
					if (CollectionUtils.isNotEmpty(catgBeans)) {
						List<String> ss = new ArrayList<String>();
						for (CoupCatgLimitRespDTO coupCatgLimitRespDTO : catgBeans) {
							GdsCategoryReqDTO gdsCategoryBean= new GdsCategoryReqDTO();
							gdsCategoryBean.setCatgCode(coupCatgLimitRespDTO.getCatgCode());
							GdsCategoryRespDTO  gdsCategoryRespDTO= gdsCategoryRSV.queryGdsCategoryByPK(gdsCategoryBean);
							if(gdsCategoryRespDTO==null){
								continue;
							}
							if("其他".equals(gdsCategoryRespDTO.getCatgName())){
								continue;
							}
							if(!ss.contains(gdsCategoryRespDTO.getCatgCode())){
								if(!CouponConstants.CoupElse.e1.equals(s)){
									s=s+","+gdsCategoryRespDTO.getCatgName();
								}else{
									s= gdsCategoryRespDTO.getCatgName();
								}
							}
							ss.add(gdsCategoryRespDTO.getCatgCode());
						}
						coupDetailReqDTO.setConditionsShow(s+CouponConstants.CoupElse.e2);
					}
				}
				if(CouponConstants.CoupPara.RULE_CODE_120.equals(ebean
						.getKey().toString())&&StringUtil.isEmpty(coupDetailReqDTO.getConditionsShow())){
					String s = CouponConstants.CoupElse.e1;
					CoupShopLimitReqDTO coupShopLimitReqDTO = new CoupShopLimitReqDTO();
					coupShopLimitReqDTO.setCoupId(coupInfoRespDTO.getId());
					List<CoupShopLimitRespDTO> shopBeans = coupShopLimitSV.queryCoupShopList(coupShopLimitReqDTO,null);
					if (CollectionUtils.isNotEmpty(shopBeans)) {
						for (CoupShopLimitRespDTO coupShopLimitRespDTO : shopBeans) {
							ShopInfoResDTO shopInfoRespDTO = shopInfoRSV
									.findShopInfoByShopID(coupShopLimitRespDTO.getShopId());
							if (shopInfoRespDTO != null) {
								if(!CouponConstants.CoupElse.e1.equals(s)){
									s=s+","+shopInfoRespDTO.getShopName();
								}else{
									s= shopInfoRespDTO.getShopName();
								}
							}
						}
						if(!CouponConstants.CoupElse.e1.equals(s)){
							coupDetailReqDTO.setConditionsShow(s+CouponConstants.CoupElse.e2);
						}
					}
				}
			}
		}
		if(StringUtil.isBlank(coupDetailReqDTO.getCoupValueShow())&&coupInfoRespDTO.getCoupValue()>0){
			if(coupInfoRespDTO.getUseRuleCode().contains(CouponConstants.CoupPara.RULE_CODE_240)){
				Double coupValue = coupInfoRespDTO.getCoupValue()/1000.0;
				coupDetailReqDTO.setCoupValueShow(coupValue+"");
			}else{
				Double coupValue = coupInfoRespDTO.getCoupValue()/100.00;
				coupDetailReqDTO.setCoupValueShow(coupValue+"");
			}
		}
	}
	
	
    /**
     * 
     * saveCoupDetailIdx:优惠券明细索引表数据新增. <br/> 
     * 
     * @author huanghe5
     * @param coupDetail
     * @throws BusinessException 
     * @since JDK 1.7
     */
	@Override
	public void saveCoupDetailIdx(CoupDetail coupDetail)
			throws BusinessException {
		if(coupDetail == null){
			return;
		}
		CoupDetailIdx coupDetailidx = coupDetailIdxConverter.convert(coupDetail);
		coupDetailidx.setId(seq_coup_detail_idx_id.nextValue());
		coupDetailidx.setCoupDetailId(coupDetail.getId());
		coupDetailidx.setCreateTime(DateUtil.getSysDate());
		if(CoupUtil.isCoupNotLong(coupDetail.getStaffId())){
			coupDetailidx.setStaffId(coupDetail.getStaffId());
		}
		
		coupDetailIdxMapper.insert(coupDetailidx);
	}
	
	/**
     * 
     * saveCoupDetailLog:优惠券明细日志表数据新增. <br/> 
     * 
     * @author huanghe5
     * @param coupDetail
     * @throws BusinessException 
     * @since JDK 1.7
     */
	@Override
	public void saveCoupDetailLog(CoupDetail coupDetail)
			throws BusinessException {
		if(coupDetail == null){
			return;
		}
		CoupDetailLog coupDetailLog = coupDetailLogConverter.convert(coupDetail);
		coupDetailLog.setId(seq_coup_detail_log_id.nextValue());
		coupDetailLog.setCoupDetailId(coupDetail.getId());
		coupDetailLog.setCreateTimeLog(DateUtil.getSysDate());
		coupDetailLog.setCreateStaffLog(coupDetail.getStaffId());
		if(StringUtil.isEmpty(coupDetailLog.getStatus())){
			coupDetailLog.setStatus(CouponConstants.CoupSys.status_1);
		}
		
		
		coupDetailLogMapper.insert(coupDetailLog);
		
	}
	
	/**
	 * 
	 * updateCoupDetail:优惠券用户明细状态修改. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupMeReqDTO
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@Override
	public void updateCoupDetail(CoupDetailReqDTO coupDetailReqDTO,CoupDetail coupDetail)
			throws BusinessException {
		if(coupDetailReqDTO == null){
			return;
		}
		if(coupDetail == null){
			return;
		}
		CoupDetail coupDetailLog = coupDetailMapper.selectByPrimaryKey(coupDetailReqDTO.getId());
		//信息重置
		CoupDetailCriteria example = new CoupDetailCriteria();
		CoupDetailCriteria.Criteria cr = example.createCriteria();
		CoupDetailIdxCriteria idxExample = new CoupDetailIdxCriteria();
		CoupDetailIdxCriteria.Criteria idxCr = idxExample.createCriteria();
		if (CoupUtil.isCoupNotLong(coupDetailReqDTO.getId())) {
			cr.andIdEqualTo(coupDetailReqDTO.getId());// 优惠券信息ID
		}
		if (CoupUtil.isCoupNotLong(coupDetailReqDTO.getCoupId())) {
			cr.andCoupIdEqualTo(coupDetailReqDTO.getCoupId());
			idxCr.andCoupIdEqualTo(coupDetailReqDTO.getCoupId());
		}
		if(StringUtil.isNotEmpty(coupDetailReqDTO.getStatus())){
			cr.andStatusEqualTo(coupDetailReqDTO.getStatus());
		}
		if(StringUtil.isNotEmpty(coupDetailReqDTO.getIfUse())){
			cr.andIfUseEqualTo(coupDetailReqDTO.getIfUse());
		}
		
		if (CoupUtil.isCoupNotLong(coupDetailReqDTO.getStaffId())) {
			cr.andStaffIdEqualTo(coupDetailReqDTO.getStaffId());
		}
		if (CoupUtil.isCoupNotLong(coupDetailReqDTO.getStaff().getId())) {
			coupDetail.setUpdateStaff(coupDetailReqDTO.getStaff().getId());
		}

		coupDetailReqDTO.setUpdateTime(DateUtil.getSysDate());
		coupDetail.setUpdateTime(DateUtil.getSysDate());
		// coupDetail = coupDetailConverter.convert(coupDetailReqDTO);
		coupDetailMapper.updateByExampleSelective(coupDetail, example);
		if (StringUtil.isNotEmpty(coupDetail.getShopId())) {
			CoupDetailIdx coupDetailidx = coupDetailIdxConverter.convert(coupDetail);
			// 更新索引表
			coupDetailIdxMapper.updateByExampleSelective(coupDetailidx, idxExample);
		}
		coupDetail.setRemark("coupDetailReqDTO:信息修改");
		coupDetailLog.setStatus(CouponConstants.CoupSys.status_log_1);// 修改
		this.saveCoupDetailLog(coupDetailLog);

	}
	
	

	/**
     * 
     * initCoupSume:优惠券使用明细查询参数配置. <br/> 
     * 
     * @author huanghe5
     * @param coupConsumeReqDTO
     * @throws BusinessException 
     * @since JDK 1.7
     */
	@Override
	public void initCoupCounSume(CoupConsumeReqDTO coupConsumeReqDTO,CoupConsumeCriteria example) throws BusinessException {
		
		if (coupConsumeReqDTO == null) {
			return;
		}
		com.zengshi.ecp.coupon.dao.model.CoupConsumeCriteria.Criteria cr = example
				.createCriteria();
		if(StringUtil.isNotEmpty(coupConsumeReqDTO.getId())){
			cr.andIdEqualTo(coupConsumeReqDTO.getId());
		}
		// 站点
		if (StringUtil.isNotEmpty(coupConsumeReqDTO.getSiteId())) {
			cr.andSiteIdEqualTo(coupConsumeReqDTO.getSiteId());
		}
		
		// 优惠券信息ID
		if (CoupUtil.isCoupNotLong(coupConsumeReqDTO.getCoupId())) {
			cr.andCoupIdEqualTo(coupConsumeReqDTO.getCoupId());
		}
		if(StringUtil.isNotEmpty(coupConsumeReqDTO.getCoupDetailId())){
			cr.andCoupDetailIdEqualTo(coupConsumeReqDTO.getCoupDetailId());
		}
		if(StringUtil.isNotEmpty(coupConsumeReqDTO.getCoupNo())){
			cr.andCoupNoEqualTo(coupConsumeReqDTO.getCoupNo().trim());
		}
		if(StringUtil.isNotBlank(coupConsumeReqDTO.getOrderId())){
			cr.andOrderIdEqualTo(coupConsumeReqDTO.getOrderId().trim());
		}
		if(StringUtil.isNotBlank(coupConsumeReqDTO.getOrderSubId())){
			cr.andOrderSubIdEqualTo(coupConsumeReqDTO.getOrderSubId());
		}
		if(StringUtil.isNotBlank(coupConsumeReqDTO.getOperType())){
			cr.andOperTypeEqualTo(coupConsumeReqDTO.getOperType());
		}
		if(StringUtil.isNotEmpty(coupConsumeReqDTO.getStaffId())){
			cr.andStaffIdEqualTo(coupConsumeReqDTO.getStaffId());
		}
		if(StringUtil.isNotEmpty(coupConsumeReqDTO.getShopId())){
			cr.andShopIdEqualTo(coupConsumeReqDTO.getShopId());
		}
		// 使用时间
		if (!StringUtil.isEmpty(coupConsumeReqDTO.getUseTimeStart())) {
			cr.andUseTimeGreaterThanOrEqualTo(new Timestamp(coupConsumeReqDTO
					.getUseTimeStart().getTime()));
		}
		if (!StringUtil.isEmpty(coupConsumeReqDTO.getUseTimeEnd())) {
			cr.andUseTimeLessThanOrEqualTo(new Timestamp(coupConsumeReqDTO
					.getUseTimeEnd().getTime()));
		}
		
	}

	/**
     * saveCoupConsume:优惠券使用明细新增.<br/> 
     * 
     * @author huanghe5
     * @param coupConsume
     * @throws BusinessException 
     * @since JDK 1.7
     */
	@Override
	public void saveCoupConsume(CoupConsumeReqDTO coupConsumeReqDTO)
			throws BusinessException {
		if(coupConsumeReqDTO == null){
			return;
		}
		CoupConsume coupConsume = coupConsumeConverter.convert(coupConsumeReqDTO);
		coupConsume.setId(seq_coup_consume_id.nextValue());
		coupConsume.setCreateTime(DateUtil.getSysDate());
		coupConsume.setCreateStaff(coupConsume.getStaffId());
		coupConsume.setStatus(CouponConstants.CoupSys.status_1);
		
		coupConsumeMapper.insert(coupConsume);
		//新增索引表
		this.saveCoupConsumeIdx(coupConsume);
	}
	
	/**
     * saveCoupConsume:优惠券使用明细索引新增.<br/> 
     * 
     * @author huanghe5
     * @param coupConsume
     * @throws BusinessException 
     * @since JDK 1.7
     */
	@Override
	public void saveCoupConsumeIdx(CoupConsume coupConsume)
			throws BusinessException {
		if(coupConsume==null){
			return;
		}
		CoupConsumeIdx coupConsumeIdx = coupConsumeIdxConverter.convert(coupConsume);
		coupConsumeIdx.setId(seq_coup_consume_idx_id.nextValue());
		coupConsumeIdx.setCreateTime(DateUtil.getSysDate());
		coupConsumeIdx.setCreateStaff(coupConsume.getStaffId());
		coupConsumeIdx.setStatus(CouponConstants.CoupSys.status_1);
		
		coupConsumeIdxMapper.insert(coupConsumeIdx);
	}
	
	/**
	 * 
	 * updateCoupDetail:优惠券消费明细修改. <br/>
	 * 
	 * @author huanghe5
	 * @param coupMeReqDTO
	 * @throws BusinessException
	 * @since JDK 1.7
	 */
	@Override
	public void updateCoupCounSume(CoupConsumeReqDTO coupConsumeReqDTO,CoupConsume coupConsume)
			throws BusinessException {
		CoupConsumeCriteria example = new CoupConsumeCriteria();
		CoupConsumeCriteria.Criteria cr = example.createCriteria();
		if(StringUtil.isNotBlank(coupConsumeReqDTO.getOrderId())){
			cr.andOrderIdEqualTo(coupConsumeReqDTO.getOrderId());
		}else{
			LogUtil.error(MODULE, "coupon.error.450021");
			return;
		}
		if(StringUtil.isNotBlank(coupConsumeReqDTO.getOrderSubId())){
			cr.andOrderSubIdEqualTo(coupConsumeReqDTO.getOrderSubId());
		}
		if(coupConsumeReqDTO.getId()!=null){
			cr.andIdEqualTo(coupConsumeReqDTO.getId());
		}
		if(coupConsumeReqDTO.getCoupId()!=null){
			cr.andCoupIdEqualTo(coupConsumeReqDTO.getCoupId());
		}
		if(StringUtil.isNotBlank(coupConsumeReqDTO.getCoupNo())){
			cr.andCoupNoEqualTo(coupConsumeReqDTO.getCoupNo());
		}
		if(coupConsumeReqDTO.getStaffId()!=null){
			cr.andStaffIdEqualTo(coupConsumeReqDTO.getStaffId());
		}
		coupConsumeMapper.updateByExampleSelective(coupConsume, example);
		//更新索引表
		if(coupConsumeReqDTO.getStaffId()!=null){
			this.updateCoupCounSumeIdx(coupConsumeReqDTO, coupConsume);
		}else{
			LogUtil.error(MODULE, "coupon.error.450022");
		}
		
	}
	
	/**
   	 * 
   	 * updateCoupDetail:优惠券消费明细索引表修改. <br/> 
   	 * 
   	 * @author huanghe5
   	 * @param coupMeReqDTO
   	 * @throws BusinessException 
   	 * @since JDK 1.7
   	 */
	@Override
	public void updateCoupCounSumeIdx(CoupConsumeReqDTO coupConsumeReqDTO,
			CoupConsume coupConsume) throws BusinessException {
		
		CoupConsumeIdxCriteria example = new CoupConsumeIdxCriteria();
		CoupConsumeIdxCriteria.Criteria cr = example.createCriteria();
		
		if(coupConsumeReqDTO.getStaffId()!=null){
			cr.andStaffIdEqualTo(coupConsumeReqDTO.getStaffId());
		}else{
			LogUtil.error(MODULE, "coupon.error.450022");
			return;
		}
		if(StringUtil.isNotBlank(coupConsumeReqDTO.getOrderId())){
			cr.andOrderIdEqualTo(coupConsumeReqDTO.getOrderId());
		}
		if(StringUtil.isNotBlank(coupConsumeReqDTO.getOrderSubId())){
			cr.andOrderSubIdEqualTo(coupConsumeReqDTO.getOrderSubId());
		}
		if(coupConsumeReqDTO.getId()!=null){
			cr.andIdEqualTo(coupConsumeReqDTO.getId());
		}
		if(coupConsumeReqDTO.getCoupId()!=null){
			cr.andCoupIdEqualTo(coupConsumeReqDTO.getCoupId());
		}
		if(StringUtil.isNotBlank(coupConsumeReqDTO.getCoupNo())){
			cr.andCoupNoEqualTo(coupConsumeReqDTO.getCoupNo());
		}
		CoupConsumeIdx coupConsumeIdx = coupConsumeIdxConverter.convert(coupConsume);
		coupConsumeIdxMapper.updateByExampleSelective(coupConsumeIdx, example);
	}
	
	/**
   	 * saveCoupPresent:优惠券订单赠送记录新增. <br/> 
   	 * 
   	 * @author huanghe5
   	 * @param coupParamReqDTO
   	 * @throws BusinessException 
   	 * @since JDK 1.7
   	 */
	@Override
	public void saveCoupPresent(CoupPresentReqDTO coupPresentReqDTO)
			throws BusinessException {
		if(coupPresentReqDTO==null){
			return;
		}
		CoupPresent coupPresent = coupPresentConverter.convert(coupPresentReqDTO);
		coupPresent.setId(seq_coup_present_id.nextValue());
		coupPresent.setCreateTime(DateUtil.getSysDate());
		coupPresent.setCreateStaff(coupPresent.getStaffId());
		coupPresent.setStatus(CouponConstants.CoupSys.status_1);
		
		coupPresentMapper.insert(coupPresent);
		
		
	}
	
	/**
     * 
     * initCoupSume:优惠券订单赠送记录查询初始化. <br/> 
     * 
     * @author huanghe5
     * @param coupConsumeReqDTO
     * @throws BusinessException 
     * @since JDK 1.7
     */
	@Override
	public void initCoupPresent(CoupPresentReqDTO coupPresentReqDTO, CoupPresentCriteria example)
			throws BusinessException {
		
		if (coupPresentReqDTO == null) {
			return;
		}
		com.zengshi.ecp.coupon.dao.model.CoupPresentCriteria.Criteria cr = example.createCriteria();
		if (CoupUtil.isCoupNotLong(coupPresentReqDTO.getId())) {
			cr.andIdEqualTo(coupPresentReqDTO.getId());
		}
		// 站点
		if (StringUtil.isNotEmpty(coupPresentReqDTO.getSiteId())) {
			cr.andSiteIdEqualTo(coupPresentReqDTO.getSiteId());
		}
		// 优惠券类型ID
		if (StringUtil.isNotEmpty(coupPresentReqDTO.getCoupDetailId())) {
			cr.andCoupDetailIdEqualTo(coupPresentReqDTO.getCoupDetailId());
		}
		if(StringUtil.isNotBlank(coupPresentReqDTO.getCoupNo())){
			cr.andCoupNoEqualTo(coupPresentReqDTO.getCoupNo());
		}
		if(StringUtil.isNotBlank(coupPresentReqDTO.getOrderId())){
			cr.andOrderIdEqualTo(coupPresentReqDTO.getOrderId());
		}
		if(StringUtil.isNotBlank(coupPresentReqDTO.getOrderSubId())){
			cr.andOrderSubIdEqualTo(coupPresentReqDTO.getOrderSubId());
		}
		if (StringUtil.isNotEmpty(coupPresentReqDTO.getCoupId())){
			cr.andCoupIdEqualTo(coupPresentReqDTO.getCoupId());
		}
		if(StringUtil.isNotEmpty(coupPresentReqDTO.getShopId())){
			cr.andShopIdEqualTo(coupPresentReqDTO.getShopId());
		}
		if(StringUtil.isNotEmpty(coupPresentReqDTO.getStatus())){
			cr.andStatusEqualTo(coupPresentReqDTO.getStatus());
		}
		
		
	}
	
	/**
	 * 
	 * updateCoupDetail:优惠券订单赠送记录修改. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupMeReqDTO
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@Override
	public void updateCoupPresent(CoupPresentReqDTO coupPresentReqDTO, CoupPresent coupPresent)
			throws BusinessException {
		CoupPresentCriteria example = new CoupPresentCriteria();
		CoupPresentCriteria.Criteria cr = example.createCriteria();
		
		
		if(StringUtil.isNotBlank(coupPresentReqDTO.getOrderId())){
			cr.andOrderIdEqualTo(coupPresentReqDTO.getOrderId());
		}else{
			LogUtil.error(MODULE, "coupon.error.450022");
			return;
		}
		if(coupPresentReqDTO.getStaffId()!=null){
			cr.andStaffIdEqualTo(coupPresentReqDTO.getStaffId());
		}
		if(StringUtil.isNotBlank(coupPresentReqDTO.getOrderSubId())){
			cr.andOrderSubIdEqualTo(coupPresentReqDTO.getOrderSubId());
		}
		if(coupPresentReqDTO.getId()!=null){
			cr.andIdEqualTo(coupPresentReqDTO.getId());
		}
		if(coupPresentReqDTO.getCoupId()!=null){
			cr.andCoupIdEqualTo(coupPresentReqDTO.getCoupId());
		}
		if(StringUtil.isNotBlank(coupPresentReqDTO.getCoupNo())){
			cr.andCoupNoEqualTo(coupPresentReqDTO.getCoupNo());
		}
		if(StringUtil.isNotEmpty(coupPresentReqDTO.getCoupDetailId())){
			cr.andCoupDetailIdEqualTo(coupPresentReqDTO.getCoupDetailId());
		}
		if(StringUtil.isNotEmpty(coupPresentReqDTO.getCoupId())){
			cr.andCoupIdEqualTo(coupPresentReqDTO.getCoupId());
		}
		if(StringUtil.isNotBlank(coupPresentReqDTO.getStatus())){
			cr.andStatusEqualTo(coupPresentReqDTO.getStatus());
		}
		
		coupPresentMapper.updateByExampleSelective(coupPresent, example);
		
	}

	 /**
     * 
     * initCoupDetail:优惠券用户明细日志查询初始化. <br/> 
     * 
     * @author huanghe5
     * @param coupMeReqDTO
     * @throws BusinessException 
     * @since JDK 1.7
     */
	@Override
	public void initCoupDetailLog(CoupDetailLog coupDetailLog,
			CoupDetailLogCriteria example) throws BusinessException {
		if (coupDetailLog == null) {
			return;
		}
		com.zengshi.ecp.coupon.dao.model.CoupDetailLogCriteria.Criteria cr = example
				.createCriteria();
		
		if (StringUtil.isNotEmpty(coupDetailLog.getId())) {
			cr.andIdEqualTo(coupDetailLog.getId());
		}
		if(StringUtil.isNotEmpty(coupDetailLog.getCoupDetailId())){
			cr.andCoupDetailIdEqualTo(coupDetailLog.getCoupDetailId());
		}
		// 站点
		if (StringUtil.isNotEmpty(coupDetailLog.getSiteId())) {
			cr.andSiteIdEqualTo(coupDetailLog.getSiteId());
		}
		if (StringUtil.isNotEmpty(coupDetailLog.getStaffId())) {
			cr.andStaffIdEqualTo(coupDetailLog.getStaffId());
		}
		// 优惠券类型ID
		if (CoupUtil.isCoupNotLong(coupDetailLog.getCoupTypeId())) {
			cr.andCoupTypeIdEqualTo(coupDetailLog.getCoupTypeId());
		}
		// 优惠券信息ID
		if (CoupUtil.isCoupNotLong(coupDetailLog.getCoupId())) {
			cr.andCoupIdEqualTo(coupDetailLog.getCoupId());
		}
		// 优惠券面额
		if (!StringUtil.isEmpty(coupDetailLog.getCoupValue())) {
			cr.andCoupValueEqualTo(coupDetailLog.getCoupValue());
		}

		if (!StringUtil.isEmpty(coupDetailLog.getShopId())) {
			cr.andShopIdEqualTo(coupDetailLog.getShopId());
		}
		// 来源
		if (StringUtil.isNotBlank(coupDetailLog.getCoupSource())) {
			cr.andCoupSourceEqualTo(coupDetailLog.getCoupSource());
		}
		// 状态
		if (StringUtil.isNotBlank(coupDetailLog.getStatus())) {
			cr.andStatusEqualTo(coupDetailLog.getStatus());
		}
		//批次
		if(StringUtil.isNotEmpty(coupDetailLog.getBatchId())){
			cr.andBatchIdEqualTo(coupDetailLog.getBatchId());
		}
		//是否使用 0:未使用 , 1:使用
		if(StringUtil.isNotBlank(coupDetailLog.getIfUse())){
			cr.andIfUseEqualTo(coupDetailLog.getIfUse());
		}
		if(StringUtil.isNotBlank(coupDetailLog.getTypeLimit())){
			cr.andTypeLimitEqualTo(coupDetailLog.getTypeLimit());
		}
	}
	
	/**
	 * 
	 * saveCoupBatchConf:新增优惠券批量查询配置表. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupBatchConfReqDTO
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@Override
	public void saveCoupBatchConf(CoupBatchConfReqDTO coupBatchConfReqDTO) throws BusinessException {
		if(coupBatchConfReqDTO==null){
			return;
		}
		CoupBatchConf coupBatchConf = coupBatchConfConverter.convert(coupBatchConfReqDTO);
		coupBatchConf.setId(seq_coup_batch_conf_id.nextValue());
		coupBatchConf.setCreateTime(DateUtil.getSysDate());
		coupBatchConf.setCreateStaff(coupBatchConfReqDTO.getStaff().getId());
		coupBatchConf.setStatus(CouponConstants.CoupSys.status_1);
		
		coupBatchConfMapper.insert(coupBatchConf);
	}
	
	/**
	 * 
	 * saveCoupBatchConf:修改优惠券批量查询配置表. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupBatchConfReqDTO
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@Override
	public void updateCoupBatchConf(CoupBatchConfReqDTO coupBatchConfReqDTO, CoupBatchConf coupBatchConf)
			throws BusinessException {
		CoupBatchConfCriteria example = new CoupBatchConfCriteria();
		CoupBatchConfCriteria.Criteria cr = example.createCriteria();
		if(coupBatchConfReqDTO.getId()!=null){
			cr.andIdEqualTo(coupBatchConfReqDTO.getId());
		}
		if(coupBatchConfReqDTO.getSiteId()!=null){
			cr.andSiteIdEqualTo(coupBatchConfReqDTO.getSiteId());
		}
		if(StringUtil.isNotBlank(coupBatchConfReqDTO.getStatus())){
			cr.andStatusEqualTo(coupBatchConfReqDTO.getStatus());
		}
		
		coupBatchConfMapper.updateByExampleSelective(coupBatchConf, example);
		
	}
	
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
	@Override
	public List<CoupBatchConfRespDTO> queryCoupBatchConf(CoupBatchConfReqDTO coupBatchConfReqDTO)
			throws BusinessException {
		CoupBatchConfCriteria example = new CoupBatchConfCriteria();
		com.zengshi.ecp.coupon.dao.model.CoupBatchConfCriteria.Criteria cr = example.createCriteria();
		if (CoupUtil.isCoupNotLong(coupBatchConfReqDTO.getId())) {
			cr.andIdEqualTo(coupBatchConfReqDTO.getId());
		}
		if (StringUtil.isNotBlank(coupBatchConfReqDTO.getStatus())) {
			cr.andStatusEqualTo(coupBatchConfReqDTO.getStatus());
		}
		// 站点
		if (StringUtil.isNotEmpty(coupBatchConfReqDTO.getSiteId())) {
			cr.andSiteIdEqualTo(coupBatchConfReqDTO.getSiteId());
		}
		List<CoupBatchConf> beans = coupBatchConfMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(beans)) {
            return null;
        }
		List<CoupBatchConfRespDTO> reList = new ArrayList<CoupBatchConfRespDTO>();
		for (CoupBatchConf coupBatchConf : beans) {
			CoupBatchConfRespDTO coupBatchConfRespDTO = coupBatchConfRespDTOConverter.convert(coupBatchConf);
			reList.add(coupBatchConfRespDTO);
		}
		return reList;
	}

	

}
