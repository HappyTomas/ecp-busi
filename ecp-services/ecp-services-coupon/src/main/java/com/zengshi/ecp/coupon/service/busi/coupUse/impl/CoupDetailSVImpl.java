package com.zengshi.ecp.coupon.service.busi.coupUse.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.coupon.dao.mapper.busi.CoupConsumeMapper;
import com.zengshi.ecp.coupon.dao.mapper.busi.CoupDetailLogMapper;
import com.zengshi.ecp.coupon.dao.mapper.busi.CoupDetailMapper;
import com.zengshi.ecp.coupon.dao.mapper.busi.CoupFreezeMapper;
import com.zengshi.ecp.coupon.dao.mapper.busi.CoupInfoMapper;
import com.zengshi.ecp.coupon.dao.mapper.busi.CoupPresentMapper;
import com.zengshi.ecp.coupon.dao.mapper.busi.manual.CoupManualMapper;
import com.zengshi.ecp.coupon.dao.model.CoupConsume;
import com.zengshi.ecp.coupon.dao.model.CoupConsumeCriteria;
import com.zengshi.ecp.coupon.dao.model.CoupDetail;
import com.zengshi.ecp.coupon.dao.model.CoupDetailCriteria;
import com.zengshi.ecp.coupon.dao.model.CoupDetailLog;
import com.zengshi.ecp.coupon.dao.model.CoupDetailLogCriteria;
import com.zengshi.ecp.coupon.dao.model.CoupFreeze;
import com.zengshi.ecp.coupon.dao.model.CoupFreezeCriteria;
import com.zengshi.ecp.coupon.dao.model.CoupInfo;
import com.zengshi.ecp.coupon.dao.model.CoupPresent;
import com.zengshi.ecp.coupon.dao.model.CoupPresentCriteria;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupCallBackReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupCheckParmReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupConsumeReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupDetailReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupFreezeReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupFullLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupGetLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupGetReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupInfoReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupMeReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupOrdBackReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupOrdCheckReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupPresentReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CouponDetailPreReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CouponPresentReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupCodeRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupFreezeRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupFullLimitRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupGetLimitRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupInfoRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupLimitCheckRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupMeRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupOrdBackRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupOrdCheckRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupOrdNumBackRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupParamRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.OrdNumRespDTO;
import com.zengshi.ecp.coupon.dubbo.impl.converter.Converter;
import com.zengshi.ecp.coupon.dubbo.util.CouponConstants;
import com.zengshi.ecp.coupon.service.busi.combination.interfaces.ICoupCreateInitSV;
import com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupDetailSV;
import com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupFullLimitSV;
import com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupGetLimitSV;
import com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupInfoSV;
import com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupRuleSV;
import com.zengshi.ecp.coupon.service.common.interfaces.ICoupParamSV;
import com.zengshi.ecp.coupon.service.util.CoupUtil;
import com.zengshi.ecp.frame.context.EcpFrameContextHolder;
import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.general.order.dto.CoupCheckBeanRespDTO;
import com.zengshi.ecp.general.order.dto.CoupCheckInfoRespDTO;
import com.zengshi.ecp.general.order.dto.CoupDetailRespDTO;
import com.zengshi.ecp.general.order.dto.CoupSkuRespDTO;
import com.zengshi.ecp.general.order.dto.ROrdCartCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartsChkResponse;
import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;



public class CoupDetailSVImpl extends GeneralSQLSVImpl implements ICoupDetailSV{

	private static final String MODULE = CoupDetailSVImpl.class.getName();
	
	@Resource
    private ICoupCreateInitSV coupCreateInitSV;
	
	@Resource
    private ICoupInfoSV coupInfoSV;
	
	@Resource
    private ICoupParamSV coupParamSV;
	
	@Resource
    private CoupInfoMapper coupInfoMapper;
	
	@Resource
    private ICoupFullLimitSV coupFullLimitSV;
	
	@Resource
    private CoupDetailMapper coupDetailMapper;
	
	@Resource
    private CoupDetailLogMapper coupDetailLogMapper;
	
	@Resource
    private CoupConsumeMapper coupConsumeMapper;
	
	@Resource
    private CoupManualMapper coupManualMapper;
	
	@Resource
	private CoupPresentMapper coupPresentMapper;
	
	@Resource
    private Converter<CoupDetail, CoupMeRespDTO> coupDetailRespDTOConverter;
	
	@Resource
    private Converter<CoupMeReqDTO, CoupDetailReqDTO> coupMyDTOConverter;
	
	@Resource
    private Converter<CoupDetailReqDTO,CoupPresentReqDTO> coupDetailPresentConverter;
	
	
	@Resource
    private Converter<CoupDetail,CoupDetailRespDTO> coupDetailCheckDTOConverter;
	
	@Resource
    private Converter<CoupFreeze,CoupFreezeRespDTO> coupFreezeRespDTOConverter;
	
	@Resource
	private CoupFreezeMapper coupFreezeMapper;
	
	@Resource
    private ICoupGetLimitSV coupGetLimitSV;
	
	@Resource(name = "seq_coup_freeze_id")
	private PaasSequence seq_coup_freeze_id;
	
	
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
	@Override
	public PageResponseDTO<CoupMeRespDTO> queryCoupDetailPage(CoupMeReqDTO coupMeReqDTO) throws BusinessException {
		// 查询初始化查询条件
		CoupDetailCriteria example = new CoupDetailCriteria();
		example.setLimitClauseCount(coupMeReqDTO.getPageSize());
		example.setLimitClauseStart(coupMeReqDTO.getStartRowIndex());
		CoupDetailReqDTO coupDetailReqDTO = new CoupDetailReqDTO();
		coupDetailReqDTO.setOpeType(coupMeReqDTO.getOpeType());
		this.initCoupDetail(coupMeReqDTO,example,coupDetailReqDTO);
		// 返回查询分页结果集
		return super.queryByPagination(coupDetailReqDTO, example, true,
				new PaginationCallback<CoupDetail, CoupMeRespDTO>() {
					// 查询记录集
					@Override
					public List<CoupDetail> queryDB(BaseCriteria example) {
						
						return coupDetailMapper
								.selectByExample((CoupDetailCriteria) example);
					}

					// 查询总记录数
					@Override
					public long queryTotal(BaseCriteria example) {

						return coupDetailMapper.countByExample(
								(CoupDetailCriteria) example);
					}


					// 查询结果转换
					public CoupMeRespDTO warpReturnObject(CoupDetail t) {
						
						return coupDetailRespDTOConverter.convert(t);
					}
					
				});
	}
	
	
	/**
	 * 
	 * queryCoupInfoPage:统计用户优惠券可使用,已使用,已过期集合数量. <br/> 
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
		// 查询初始化查询条件
		CoupDetailCriteria example = new CoupDetailCriteria();
		example.setLimitClauseCount(coupMeReqDTO.getPageSize());
		example.setLimitClauseStart(coupMeReqDTO.getStartRowIndex());
		CoupDetailReqDTO coupDetail = new CoupDetailReqDTO();
		coupDetail.setStatus(CouponConstants.CoupSys.status_1);
		
		//业务组装信息
		this.initCoupDetail(coupMeReqDTO, example, coupDetail);
		
		
		return coupDetailMapper.countByExample(example).longValue();
	}
	
	private Long queryCoupDetailLog(CoupDetailLog coupDetailLog){
		CoupDetailLogCriteria example = new CoupDetailLogCriteria();
		coupCreateInitSV.initCoupDetailLog(coupDetailLog, example);
		return coupDetailLogMapper.countByExample(example).longValue();
	}
	
	
	private void initCoupDetail(CoupMeReqDTO coupMeReqDTO,
			CoupDetailCriteria example, CoupDetailReqDTO coupDetailReqDTO) {
		// 排序
		Map<String, String> mapSort = coupMeReqDTO.getMapSort();
		// String value = mapSort.get(key)
		if (StringUtil.isNotEmpty(mapSort)) {
			Iterator i = mapSort.entrySet().iterator();
			if (StringUtil.isNotEmpty(i)) {
				String str = " ";
				while (i.hasNext()) {
					Map.Entry e = (Map.Entry) i.next();
					String key = e.getKey().toString();// 排序字段标识
					String value = e.getValue().toString();// 排序类型 0:降序 1:升序
					// 0:降序 1:升序
					if (CouponConstants.CoupDetail.sort_0.equals(value)) {
						value = CouponConstants.CoupDetail.DESC;
					}
					if (CouponConstants.CoupDetail.sort_1.equals(value)) {
						value = CouponConstants.CoupDetail.ASC;
					}
					if(!" ".equals(str)){
						str =str + " , " + key +" "+ value;
					}else{
						str = str + key +" "+ value;
					}
					
				}
				example.setOrderByClause(str);
			}
		}
		if(coupMeReqDTO != null){
			coupDetailReqDTO.setStaffId(coupMeReqDTO.getStaffId());
			coupMyDTOConverter.convert(coupMeReqDTO, coupDetailReqDTO);
			coupDetailReqDTO.setPageSize(coupMeReqDTO.getPageSize());
		}
		
		// 1:可使用 2:已使用 0:已过期
		if (CouponConstants.CoupDetail.opeType_1.equals(coupMeReqDTO
				.getOpeType())) {
			coupDetailReqDTO.setIfUse(CouponConstants.CoupDetail.IF_USE_0);
			coupDetailReqDTO.setActiveTime(DateUtil.getSysDate());
			coupDetailReqDTO.setInactiveTime(DateUtil.getSysDate());
			coupDetailReqDTO.setStatus(CouponConstants.CoupSys.status_1);
		} else if (CouponConstants.CoupDetail.opeType_2.equals(coupMeReqDTO
				.getOpeType())) {
			coupDetailReqDTO.setIfUse(CouponConstants.CoupDetail.IF_USE_1);
			coupDetailReqDTO.setStatus(CouponConstants.CoupSys.status_1);
		} else if (CouponConstants.CoupDetail.opeType_0.equals(coupMeReqDTO
				.getOpeType())) {
			coupDetailReqDTO.setOpeType(CouponConstants.CoupDetail.opeType_0);// 过期
			coupDetailReqDTO.setIfUse(CouponConstants.CoupDetail.IF_USE_0);
			coupDetailReqDTO.setActiveTime(DateUtil.getSysDate());
			coupDetailReqDTO.setInactiveTime(DateUtil.getSysDate());
			coupDetailReqDTO.setStatus(CouponConstants.CoupSys.status_1);
		}else if(CouponConstants.CoupDetail.opeType_3.equals(coupMeReqDTO
				.getOpeType())){
			coupDetailReqDTO.setActiveTime(DateUtil.getSysDate());
			coupDetailReqDTO.setInactiveTime(DateUtil.getSysDate());
			coupDetailReqDTO.setIfUse(CouponConstants.CoupDetail.IF_USE_0);
			coupDetailReqDTO.setStatus(CouponConstants.CoupSys.status_2);
		}
		
		// 初始化
		coupCreateInitSV.initCoupDetail(coupDetailReqDTO, example);
		
	}

	/**
	 * 
	 * saveCoupMes:优惠券普通领取. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupCallBackReqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@Override
	public String saveCoupGain(CoupCallBackReqDTO coupCallBackReqDTO)
			throws BusinessException {
		
		if(StringUtil.isBlank(coupCallBackReqDTO.getCoupSource())){
			//用户主动领取
			coupCallBackReqDTO.setCoupSource(CouponConstants.CoupDetail.COUP_SOURCE_10);
		}
		String s="0";
		if (CoupUtil.isCoupNotLong(coupCallBackReqDTO.getCoupId())) {
			
			CoupInfoReqDTO coupInfoReqDTO = new CoupInfoReqDTO();
			coupInfoReqDTO.setId(coupCallBackReqDTO.getCoupId());
			coupInfoReqDTO.setStatus(CouponConstants.CoupSys.status_1);
			if(coupCallBackReqDTO.getCoupSum()<1){
				coupCallBackReqDTO.setCoupSum(CouponConstants.CoupSys.defaultUseNo);
			}
			List<CoupInfoRespDTO> beans = coupInfoSV.queryCoupInfoList(coupInfoReqDTO);
			
			if(StringUtil.isEmpty(beans)){
				LogUtil.error(MODULE, "优惠券领取,优惠券信息表,ID查询为空");
				return "0";
//				throw new BusinessException("亲,您的优惠券领取失败.");
			}
			CoupInfoRespDTO coupInfoBean = beans.get(0);
			/**
			 * 如果此优惠券是固定时间已经过期了就不会送
			 */
			if (CouponConstants.CoupInfo.EFF_TYPE_1.equals(coupInfoBean
					.getEffType())) {
				if (CoupUtil.timestampToDate(DateUtil.getSysDate()).getTime() < coupInfoBean
						.getActiveTime().getTime()
						|| CoupUtil.timestampToDate(DateUtil.getSysDate())
								.getTime() > coupInfoBean.getInactiveTime()
								.getTime()) {
					throw new BusinessException("coupon.error.450027");
				}
			}
			long getNum =0;
			if(Long.valueOf(CouponConstants.CoupSys.infinite).equals(coupInfoBean.getCoupNum())){
				getNum=CouponConstants.CoupSys.infinite;
			}else{
				long a = coupInfoBean.getCoupNum() - coupInfoBean.getGetNum();
				if(a>0){
					getNum=a;
				}else{
					LogUtil.error(MODULE, "优惠券库存已经满.."+a);
					throw new BusinessException("coupon.error.450023");
				}
			}
			//如果送券数量小于1则默认为1
			if(coupCallBackReqDTO.getCoupSum()<1){
				coupCallBackReqDTO.setCoupSum(1);
			}
			//领取权限校验
			int count;
			if(CouponConstants.CoupDetail.COUP_SOURCE_10.equals(coupCallBackReqDTO.getCoupSource())){
				
				count = this.checkCoup(coupCallBackReqDTO,coupInfoBean);
				if(count==0){
					LogUtil.error(MODULE, "优惠券领取,优惠券信息表,ID查询为空");
					throw new BusinessException("coupon.error.450028");
				}
			}else{
				count = coupCallBackReqDTO.getCoupSum();
			}
			
			CoupDetailReqDTO bean = new CoupDetailReqDTO();
			bean.setCoupId(coupInfoBean.getId());
			bean.setSiteId(coupInfoBean.getSiteId());
			bean.setCoupTypeId(coupInfoBean.getCoupTypeId());
			bean.setCoupValue(coupInfoBean.getCoupValue());
			bean.setIfUse(CouponConstants.CoupDetail.IF_USE_0);
			bean.setCoupName(coupInfoBean.getCoupName());
			bean.setTypeLimit(coupInfoBean.getTypeLimit());
			bean.setShopId(coupInfoBean.getShopId());
			if(StringUtil.isNotBlank(coupCallBackReqDTO.getCoupSource())){
				bean.setCoupSource(coupCallBackReqDTO.getCoupSource());
			}
			bean.setStaffId(coupCallBackReqDTO.getStaffId());
			int coupSum = coupCallBackReqDTO.getCoupSum();
			if (count > 0&& getNum!=0) {
				if(coupSum >1&&count>1){//一种优惠券用户领取多张
					if(Long.valueOf(CouponConstants.CoupSys.infinite).equals(getNum)){//如果库存是无限制
						//用户得到的优惠券数量>用户可领取此类优惠券的数量
						if (coupSum > count) {
							for (int i = 0; i <= count; i++) {
								int upBean = updateCoupGetNum(coupCallBackReqDTO);
								if(upBean<1){
									break;
								}
								coupCreateInitSV.saveCoupDetail(bean,coupInfoBean,coupCallBackReqDTO.getOrderId());
							}
							s = "1";
						} else {
							for (int i = 0; i <= coupSum; i++) {
								int upBean = updateCoupGetNum(coupCallBackReqDTO);
								if(upBean<1){
									break;
								}
								coupCreateInitSV.saveCoupDetail(bean,coupInfoBean,coupCallBackReqDTO.getOrderId());
							}
							s = "1";
						}
					}else if(getNum>0){
						/**
						 * coupSum:系统要求赠送的优惠券张数
						 * count:此用户还可以领取此类优惠券的张数
						 * getNum:此类优惠券库存还剩的张数
						 */
						long sum ;
						if(coupSum >= count){
							if(count>=getNum){
								sum=getNum;
							}else{
								sum=coupSum;
							}
						}else{
							if(getNum>=coupSum){
								sum=coupSum;
							}else{
								sum=getNum;
							}
						}
						//优惠券新增
						for (int i = 0; i < sum; i++) {
							int upBean = updateCoupGetNum(coupCallBackReqDTO);
							if(upBean<1){
								break;
							}
							coupCreateInitSV.saveCoupDetail(bean,coupInfoBean,coupCallBackReqDTO.getOrderId());
						}
						s = "1";
					}
				}else{
					int upBean = this.updateCoupGetNum(coupCallBackReqDTO);
					if(upBean>0){//upBean>0表示此coupId优惠券库存新增成功
						coupCreateInitSV.saveCoupDetail(bean,coupInfoBean,coupCallBackReqDTO.getOrderId());
						s = "1";
					}else{
						s = "0";
					}
				}
			}else{
				s = "0";
			}
		}
		return s;
	}
	
	/**
	 * 
	 * checkCoup:用户主动领取规则校验			
	 * 
	 * @author huanghe5
	 * @param coupCallBackReqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	private int checkCoup(CoupCallBackReqDTO coupCallBackReqDTO,CoupInfoRespDTO coupInfoBean) {
		CoupDetailLog coupDetailLog = new CoupDetailLog();
		coupDetailLog.setStaffId(coupCallBackReqDTO.getStaffId());
		coupDetailLog.setCoupId(coupCallBackReqDTO.getCoupId());
		coupDetailLog.setStatus(CouponConstants.CoupSys.status_log_0);//新增
		Long detailSize = this.queryCoupDetailLog(coupDetailLog);
		//查询用户已经领取次优惠券多少张了
		//1 校验用户还有是否有领取资格,.
		// 1.1获取优惠券领取规则信息
		if (StringUtil.isNotBlank(coupInfoBean.getGainRuleCode())) {
			// 如果优惠券具有领取规则
			CoupGetLimitReqDTO coupGetLimitReqDTO = new CoupGetLimitReqDTO();
			coupGetLimitReqDTO.setCoupId(coupCallBackReqDTO.getCoupId());
			List<CoupGetLimitRespDTO> coupGetBeans = coupGetLimitSV.queryCoupGetList(coupGetLimitReqDTO);
			// 获取领取规则信息
			if (!CollectionUtils.isEmpty(coupGetBeans)) {
				CoupGetLimitRespDTO getBean = coupGetBeans.get(0);
				// 解析领取规则
				JSONArray ja = JSON.parseArray(getBean.getGainRuleValue());
				if (!CollectionUtils.isEmpty(ja)) {
					for (int i = 0; i < ja.size(); i++) {
						CoupGetReqDTO getdto = JSON.parseObject(ja.getString(i), CoupGetReqDTO.class);
						if (getdto != null) {
							if (getdto.getCustLevel() != null) {
								if (getdto.getCustLevel().equals(coupCallBackReqDTO.getCustLevel())) {
									// 校验用户是否有领取规则返回 优惠券限制领取数量-用户已领取此优惠券的数量
									int count=1;
									if(!StringUtil.isBlank(getdto.getGainNum())){
										count = Integer.valueOf(getdto.getGainNum()) - detailSize.intValue();
										if(count <1){
											throw new BusinessException("coupon.error.450029");
										}
									}else{
										count = count - detailSize.intValue();
										if(count <1){
											throw new BusinessException("coupon.error.450029");
										}
									}
									if(getdto.getStartTime()!=null){
										if(CoupUtil.timestampToDate(DateUtil.getSysDate()).getTime() < getdto
												.getStartTime().getTime()){
											throw new BusinessException("coupon.error.450031");
										}
										if (CoupUtil.timestampToDate(DateUtil.getSysDate()).getTime() >= getdto
												.getStartTime().getTime()
												&& CoupUtil.timestampToDate(DateUtil.getSysDate()).getTime() <= getdto
														.getEndTime().getTime()) {
											if (count > 0) {
												
												return count;
											}
										}else{
											throw new BusinessException("coupon.error.450032");
										}
									}
									// 如果没有限制限制,则对比
									if (count > 0) {
										return count;
									} 
								}
							}
						} else {
							return 1;// 无此优惠券领取限制信息
						}
					}
					throw new BusinessException("coupon.error.450030");
				} else {// 如果没有领取规则
					if(detailSize>0){
						LogUtil.error(MODULE, "此类优惠券每个用户只能领取一张");
						throw new BusinessException("coupon.error.450029");
					}
					return 1;// 无限制,走默认机制
				}
			} else {// 如果没有领取规则
				if(detailSize>0){
					LogUtil.error(MODULE, "此类优惠券每个用户只能领取一张");
					throw new BusinessException("coupon.error.450029");
				}
				return 1;// 无限制,走默认机制
			}
		}else{
			if(detailSize>0){
				LogUtil.error(MODULE, "此类优惠券每个用户只能领取一张");
				throw new BusinessException("coupon.error.450029");
			}
			return 1;
		}
		
	}
	
	private int updateCoupGetNum(CoupCallBackReqDTO coupCallBackReqDTO){
		CoupInfo coupBean = new CoupInfo();
		coupBean.setId(coupCallBackReqDTO.getCoupId());
		coupBean.setUpdateStaff(coupCallBackReqDTO.getStaffId());
		coupBean.setUpdateTime(DateUtil.getSysDate());
		//优惠库存增加量 ,CreateStaff作为代替字段
//		coupBean.setCreateStaff(Long.valueOf(coupCallBackReqDTO.getCoupSum()));
		int i = coupManualMapper.updateCoupNum(coupBean);
	/*	else{
			throw new BusinessException(CouponConstants.CoupError.coupon_error_450013);
		}*/
		return i;
	}


	/**
	 * queryCoupDetailList:优惠券用户明细查询 . <br/> 
	 * 
	 * @author huanghe5
	 * @param coupMeReqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@Override
	public List<CoupDetailRespDTO> queryCoupDetailList(CoupDetailReqDTO coupDetailReqDTO)
			throws BusinessException {
		// 查询初始化查询条件
		CoupDetailCriteria example = new CoupDetailCriteria();
		
		if(StringUtil.isBlank(coupDetailReqDTO.getStatus())){
			coupDetailReqDTO.setStatus(CouponConstants.CoupSys.status_1);
		}
		
		coupCreateInitSV.initCoupDetail(coupDetailReqDTO, example);
		if(StringUtil.isNotBlank(coupDetailReqDTO.getSortValue())&&StringUtil.isNotBlank(coupDetailReqDTO.getKey())){
			String value = coupDetailReqDTO.getSortValue();
			String key = coupDetailReqDTO.getKey();
			String str = " ";
			// 0:降序 1:升序
			if (CouponConstants.CoupDetail.sort_0.equals(value)) {
				value = CouponConstants.CoupDetail.DESC;
			}
			if (CouponConstants.CoupDetail.sort_1.equals(value)) {
				value = CouponConstants.CoupDetail.ASC;
			}
			if(!" ".equals(str)){
				str =str + " , " + key +" "+ value;
			}else{
				str = str + key +" "+ value;
			}
			
			example.setOrderByClause(str);
		}
		List<CoupDetail>  detailBeans= coupDetailMapper.selectByExample((CoupDetailCriteria) example);
		if(CollectionUtils.isEmpty(detailBeans)){
			return null;
		}
		List<CoupDetailRespDTO> coupDetailBeans = new ArrayList<CoupDetailRespDTO>();
		for (CoupDetail coupDetail : detailBeans) {
			CoupDetailRespDTO bean= coupDetailCheckDTOConverter.convert(coupDetail);
			coupDetailBeans.add(bean);
		}
		return coupDetailBeans;
		
	}

	/**
	 * 
	 * deleteCoupDetail:删除优惠券. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupMeReqDTO
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@Override
	public void deleteCoupDetail(CoupMeReqDTO coupMeReqDTO)
			throws BusinessException {
		CoupDetailReqDTO coupDetailReqDTO = new CoupDetailReqDTO();
		coupDetailReqDTO.setId(coupMeReqDTO.getId());
		if(!StringUtil.isEmpty(coupMeReqDTO.getStaffId())){
			coupDetailReqDTO.setStaffId(coupMeReqDTO.getStaffId());
		}
//		coupDetailReqDTO.setSiteId(coupMeReqDTO.getSiteId());
		CoupDetail coupDetail = new CoupDetail();
		coupDetail.setUpdateStaff(coupMeReqDTO.getStaffId());
		coupDetail.setStatus(CouponConstants.CoupSys.status_0);
		coupCreateInitSV.updateCoupDetail(coupDetailReqDTO,coupDetail);
	}
	
	private List<CoupDetailRespDTO> queryCoupDetailList(Long staffId,Long shopId,Long coupId,String coupNo,String typeLimit){
		
		CoupDetailReqDTO coupDetailReqDTO = new CoupDetailReqDTO();
		coupDetailReqDTO.setStaffId(staffId);
		if(StringUtil.isNotEmpty(shopId)){
			coupDetailReqDTO.setShopId(shopId);
		}
		if(StringUtil.isNotEmpty(coupId)){
			coupDetailReqDTO.setCoupId(coupId);
		}
		if(StringUtil.isNotBlank(coupNo)){
			coupDetailReqDTO.setCoupNo(coupNo);
		}
		if(StringUtil.isNotBlank(typeLimit)){
			coupDetailReqDTO.setTypeLimit(typeLimit);
		}
		coupDetailReqDTO.setIfUse(CouponConstants.CoupDetail.IF_USE_0);
		Timestamp sysDate= DateUtil.getSysDate();
		coupDetailReqDTO.setInactiveTime(sysDate);
		coupDetailReqDTO.setActiveTime(sysDate);
		coupDetailReqDTO.setStatus(CouponConstants.CoupSys.status_1);
		//查询用户所具有的有效优惠券信息
		List<CoupDetailRespDTO> coupDetails = this.queryCoupDetailList(coupDetailReqDTO);
		if(CollectionUtils.isEmpty(coupDetails)){
			LogUtil.error(MODULE, "下单时,查询可使用的优惠券,该用户无优惠券");
			return null;
		}
		return coupDetails;
	}
	
	private List<CoupInfoRespDTO> queryCoupInfoList(Long staffId,Long shopId,String typeLimit){
		
		//查询用户所具有的有效优惠券信息
		List<CoupDetailRespDTO> CoupDetails = this.queryCoupDetailList(staffId,shopId,null,null,typeLimit);
		
		//用户所具有优惠券小类ID集合(去重)
		List<Long> coupIds =  new ArrayList<Long>();
		if(CollectionUtils.isEmpty(CoupDetails)){
			
			LogUtil.error(MODULE, "下单时,查询可使用的优惠券(平台级),List<CoupDetailRespDTO> CoupDetails该用户无");
			return null;
		}
		for (CoupDetailRespDTO coupDetail : CoupDetails) {
			if (!coupIds.contains(coupDetail.getCoupId())) {
				coupIds.add(coupDetail.getCoupId());
			}
		}
		if(CollectionUtils.isEmpty(coupIds)){
			LogUtil.error(MODULE, "下单时,查询可使用的优惠券,该用户无优惠券,coupIds为空");
			return null;
		}
		//用户所具有优惠券小类集合
		List<CoupInfoRespDTO> coupInfoBeans = new ArrayList<CoupInfoRespDTO>();
		for (Long long1 : coupIds) {
			CoupInfoReqDTO coupInfoReqDTO = new CoupInfoReqDTO();
			coupInfoReqDTO.setId(long1);
			CoupInfoRespDTO coupInfoRespDTO= coupInfoSV.queryCoupInfoById(coupInfoReqDTO);
			if(coupInfoRespDTO!=null){
				coupInfoBeans.add(coupInfoRespDTO);
			}
		}
		if(CollectionUtils.isEmpty(coupInfoBeans)){
			LogUtil.error(MODULE, "下单时,查询可使用的优惠券,该用户无优惠券,"+coupInfoBeans+"coupInfoBeans为空");
			return null;
		}
		
		return coupInfoBeans;
	}
	
	// 解析每个子订单单品信息
	private String groupCoupSkuMap(CoupCheckParmReqDTO coupCheckParmReqDTO,
			ROrdCartItemCommRequest rOrdCartItemCommRequest,
			List<CoupSkuRespDTO> coupSkuBeans1,
			List<CoupSkuRespDTO> coupSkuBeans2,
			CoupInfoRespDTO coupInfoRespDTO, String skuGroup,
			CoupCheckBeanRespDTO coupCheckBeanRespDTO,
			List<CoupCheckBeanRespDTO> coupCheckBeanList,
			Map<Long, CoupCheckInfoRespDTO> coupIdSkuIdMap,List<Long> deWeightList,Map<Long, CoupCheckInfoRespDTO> coupFullSkuIdMap ) {
		
		CoupSkuRespDTO coupSkuRespDTO1 = new CoupSkuRespDTO();// 带满减规则的单品
		CoupSkuRespDTO coupSkuRespDTO2 = new CoupSkuRespDTO();// 全部通过限制规则的单品
		coupCheckBeanRespDTO.setCoupValue(coupInfoRespDTO.getCoupValue());
		
		String judge = "";
		// 根据查询到参数添加限制规则数据
		if (StringUtil.isNotEmpty(coupInfoRespDTO.getUseRuleCode())) {
			
			// 进行优惠券各种规则校验
			CoupLimitCheckRespDTO coupLimitCheckBean = this.checkLimit(coupCheckParmReqDTO,
					coupInfoRespDTO, rOrdCartItemCommRequest);
			if (coupLimitCheckBean == null) {
				return null;
			}
			/**
			 * 过滤,如果是judge_2则是带满减价格的优惠券则进行归类满减信息 否则归类到全部通过的信息
			 */
			if (CouponConstants.CoupSys.judge_1.equals(coupLimitCheckBean
					.getJudge())) {// 其他规则通过带有满减规则的优惠券信息以及单品信息
				if (CouponConstants.CoupSys.judge_2.equals(coupLimitCheckBean
						.getSkuGroup())) {
					if (StringUtil.isNotEmpty(coupLimitCheckBean.getVarLimit())) {
						coupCheckBeanRespDTO.setVarLimit(coupLimitCheckBean
								.getVarLimit());
					}

					if (StringUtil
							.isNotBlank(coupLimitCheckBean.getNoExpress())) {
						coupCheckBeanRespDTO.setNoExpress(coupLimitCheckBean
								.getNoExpress());
					}

					if (StringUtil.isNotEmpty(coupLimitCheckBean
							.getCoupVarLong())) {
						coupCheckBeanRespDTO.setCoupVarBeans(coupLimitCheckBean
								.getCoupVarLong());
					}
					// 存放单品信息
					coupSkuRespDTO1.setDiscountMoney(rOrdCartItemCommRequest
							.getDiscountMoney());
					coupSkuRespDTO1
							.setGdsId(rOrdCartItemCommRequest.getGdsId());
					coupSkuRespDTO1
							.setSkuId(rOrdCartItemCommRequest.getSkuId());
					coupSkuRespDTO1.setItemId(rOrdCartItemCommRequest.getId());
					coupSkuRespDTO1.setShopId(rOrdCartItemCommRequest
							.getShopId());
					coupSkuBeans1.add(coupSkuRespDTO1);// 带满减规则的coupID能使用单品的信息集合
					//添加满减MAP
					coupCheckBeanRespDTO.setCoupId(coupInfoRespDTO.getId());
					coupCheckBeanRespDTO.setCoupName(coupInfoRespDTO.getCoupName());
					if(coupLimitCheckBean.getOrdUseNum()<1){
						coupCheckBeanRespDTO.setOrdUseNum(CouponConstants.CoupSys.defaultUseNo);
					}else{
						coupCheckBeanRespDTO.setOrdUseNum(coupLimitCheckBean.getOrdUseNum());
					}
					CoupCheckInfoRespDTO coupCheckFullRespDTO= new CoupCheckInfoRespDTO();
					coupCheckFullRespDTO.setCoupCheckBeanRespDTO(coupCheckBeanRespDTO);
					coupCheckFullRespDTO.setCoupSkuRespDTO(coupSkuBeans1);
					coupFullSkuIdMap.put(coupInfoRespDTO.getId(), coupCheckFullRespDTO);// 带满减的优惠券以及
					skuGroup = coupLimitCheckBean.getSkuGroup();
					judge = coupLimitCheckBean.getJudge();
				} else {
					// 全部通过优惠券信息以及单品信息
						// 存放优惠券信息
					if (!deWeightList.contains(coupCheckBeanRespDTO.getCoupId())){
						deWeightList.add(coupInfoRespDTO.getId());
						coupCheckBeanRespDTO.setCoupId(coupInfoRespDTO.getId());
						if(coupLimitCheckBean.getOrdUseNum()<1){
							coupCheckBeanRespDTO.setOrdUseNum(CouponConstants.CoupSys.defaultUseNo);
						}else{
							coupCheckBeanRespDTO.setOrdUseNum(coupLimitCheckBean.getOrdUseNum());
						}
						
						coupCheckBeanRespDTO.setCoupName(coupInfoRespDTO
								.getCoupName());
						if (StringUtil.isNotEmpty(coupLimitCheckBean.getVarLimit())) {
							coupCheckBeanRespDTO.setVarLimit(coupLimitCheckBean
									.getVarLimit());
						}

						if (StringUtil
								.isNotBlank(coupLimitCheckBean.getNoExpress())) {
							coupCheckBeanRespDTO.setNoExpress(coupLimitCheckBean
									.getNoExpress());
						}
						
						if (StringUtil.isNotEmpty(coupLimitCheckBean
								.getCoupVarLong())) {
							coupCheckBeanRespDTO.setCoupVarBeans(coupLimitCheckBean
									.getCoupVarLong());
						}
						coupCheckBeanList.add(coupCheckBeanRespDTO);
					}
					
					// 存放单品信息
					coupSkuRespDTO2.setDiscountMoney(rOrdCartItemCommRequest
							.getDiscountMoney());
					coupSkuRespDTO2.setOrderAmount(rOrdCartItemCommRequest.getOrderAmount());
					coupSkuRespDTO2.setItemId(rOrdCartItemCommRequest.getId());
					coupSkuRespDTO2.setShopId(rOrdCartItemCommRequest
							.getShopId());
					coupSkuRespDTO2
							.setGdsId(rOrdCartItemCommRequest.getGdsId());
					coupSkuRespDTO2
							.setSkuId(rOrdCartItemCommRequest.getSkuId());
					coupSkuBeans2.add(coupSkuRespDTO2);// coupID能使用单品的信息集合
					CoupCheckInfoRespDTO coupCheckRespDTO = new CoupCheckInfoRespDTO();
					coupCheckRespDTO.setCoupCheckBeanRespDTO(coupCheckBeanRespDTO);
					coupCheckRespDTO.setCoupSkuRespDTO(coupSkuBeans2);
					coupIdSkuIdMap.put(coupCheckBeanRespDTO.getCoupId(),
							coupCheckRespDTO);
					judge = coupLimitCheckBean.getJudge();
				}
			} else {
				return null;
			}
		} else {// 如果没有使用规则就直接存放到可使用到优惠券里
			if (!deWeightList.contains(coupCheckBeanRespDTO.getCoupId())){
				deWeightList.add(coupInfoRespDTO.getId());
				
				coupCheckBeanRespDTO.setCoupId(coupInfoRespDTO.getId());
				coupCheckBeanRespDTO.setCoupName(coupInfoRespDTO.getCoupName());
				coupCheckBeanRespDTO.setCoupValue(coupInfoRespDTO.getCoupValue());
				coupCheckBeanRespDTO.setOrdUseNum(CouponConstants.CoupSys.defaultUseNo);
				// coupCheckBean.add(coupCheckBeanRespDTO);
				coupCheckBeanList.add(coupCheckBeanRespDTO);// 存放List
			}
			
			coupSkuRespDTO2.setDiscountMoney(rOrdCartItemCommRequest
					.getDiscountMoney());
			coupSkuRespDTO2.setItemId(rOrdCartItemCommRequest.getId());
			coupSkuRespDTO2.setShopId(rOrdCartItemCommRequest
					.getShopId());
			coupSkuRespDTO2
					.setGdsId(rOrdCartItemCommRequest.getGdsId());
			coupSkuRespDTO2
					.setSkuId(rOrdCartItemCommRequest.getSkuId());
			coupSkuBeans2.add(coupSkuRespDTO2);// coupID能使用单品的信息集合

			// 保存MAP
			CoupCheckInfoRespDTO coupCheckFullRespDTO = new CoupCheckInfoRespDTO();
			coupCheckFullRespDTO.setCoupCheckBeanRespDTO(coupCheckBeanRespDTO);
			coupCheckFullRespDTO.setCoupSkuRespDTO(coupSkuBeans2);
			coupIdSkuIdMap.put(coupCheckBeanRespDTO.getCoupId(),
					coupCheckFullRespDTO);
			judge = CouponConstants.CoupSys.judge_1;
		}
		
		if (CouponConstants.CoupSys.judge_1.equals(judge)
				&& CouponConstants.CoupSys.judge_2.equals(skuGroup)) {
			return skuGroup;
		} else if (CouponConstants.CoupSys.judge_1.equals(judge)) {
			return judge;
		} else {
			return null;
		}

	}
	
	
	/**
	 * 
	 * checkOrdCodeSkuNum:下单时,满数量限制数量校验. <br/> 
	 * 
	 * @author lisp
	 * @param 
	 * @return 
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	private void checkOrdCodeSkuNum(CoupInfoRespDTO coupInfoRespDTO,List<CoupSkuRespDTO> coupSkuBeans2,CoupCodeRespDTO coupCodeRespDTO) throws BusinessException {
			@SuppressWarnings("unchecked")
			Map<String, Object> map = (Map<String, Object>) JSON
					.parse(coupInfoRespDTO.getUseRuleCode());
			
			if (map != null && map.entrySet().iterator() != null) {
				Iterator<Entry<String, Object>> beans = map.entrySet()
						.iterator();
				while (beans.hasNext()) {
					Entry<String, Object> ebean = beans.next();
					if(CouponConstants.CoupPara.RULE_CODE_140.equals(ebean.getKey().toString())){
						CoupFullLimitReqDTO coupFullLimitReqDTO = new CoupFullLimitReqDTO();
						coupFullLimitReqDTO.setCoupId(coupInfoRespDTO.getId());
						
						//根据coupId查询限制规则信息
						List<CoupFullLimitRespDTO> bean = coupFullLimitSV.queryCoupFullList(coupFullLimitReqDTO);
						Long i =0l;
						for (CoupSkuRespDTO coupSkuRespDTO : coupSkuBeans2) {
							i+=coupSkuRespDTO.getOrderAmount();
						}
						if(!CollectionUtils.isEmpty(bean)){
							if(i<bean.get(0).getAmount()){
							 coupCodeRespDTO.setIfCanUse("0");
							 coupCodeRespDTO.setResultMsg("满足优惠码使用条件的单品数量不足"+bean.get(0).getAmount());
							 return;
							}
						}
						
					}else{
						continue;
					}
				}
			}
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
	@Override
	public CoupCodeRespDTO queryOrdCheckCoupByCode(ROrdCartCommRequest ordCartCommRequest) throws BusinessException {
		/**
		 * key:coupId value:skuID的集合 此List表示此优惠券能在哪些单品上使用 PS:满减规则可能是多个单品单价总和计算的
		 */
		Map<Long, CoupCheckInfoRespDTO> coupIdSkuIdMap = new HashMap<Long, CoupCheckInfoRespDTO>();
		CoupCodeRespDTO coupCodeRespDTO = new CoupCodeRespDTO();
			// 可使用的优惠券信息(店铺级)
			List<CoupCheckBeanRespDTO> coupCheckShopList = new ArrayList<CoupCheckBeanRespDTO>();
			// 带满减规则的优惠券ID集合(店铺)
			Map<Long, CoupCheckInfoRespDTO> coupFullShopSkuIdMap = new HashMap<Long, CoupCheckInfoRespDTO>();
			// 用于coupID去重计算coupId数量
			List<Long> deWeightList = new ArrayList<Long>();
			CoupInfoReqDTO coupInfoReqDTO = new CoupInfoReqDTO();
			String coupCode = ordCartCommRequest.getCoupCode().toUpperCase();//变为大写
			coupInfoReqDTO.setCoupCode(coupCode);
			CoupInfoRespDTO coupInfoRespDTO  =coupInfoSV.queryCoupInfoByCoupCode(coupInfoReqDTO);
			if(StringUtil.isEmpty(coupInfoRespDTO)){
				LogUtil.error(MODULE, "下单时,获取优惠码无效或已过期 CoupInfoRespDTO coupInfoRespDTO == null");
				coupCodeRespDTO.setResultMsg("您输入的优惠码无效或已过期，请检查确认！");
				return coupCodeRespDTO;
			}
			ObjectCopyUtil.copyObjValue(coupInfoRespDTO, coupCodeRespDTO, null, false);
			coupCodeRespDTO.setCoupCode(coupCode);
			if(coupInfoRespDTO.getShopId().equals(ordCartCommRequest.getShopId())){
				CoupCheckParmReqDTO coupCheckParmReqDTO = new CoupCheckParmReqDTO();
				coupCheckParmReqDTO.setCoupId(coupInfoRespDTO.getId());
				coupCheckParmReqDTO.setRuleCode(coupInfoRespDTO.getUseRuleCode());
				coupCheckParmReqDTO.setCustLevelValue(ordCartCommRequest.getCustLevelValue());
				coupCheckParmReqDTO.setSource(ordCartCommRequest.getSource());
				String skuGroup= new String();
				//带有满减价格规则的且通过其他限制校验的优惠券ID关联的单品
				List<CoupSkuRespDTO> coupSkuBeans1 = new ArrayList<CoupSkuRespDTO>();
				//通过所有限制校验的优惠券ID关联的单品
				List<CoupSkuRespDTO> coupSkuBeans2 = new ArrayList<CoupSkuRespDTO>();
				//存放可能可用优惠券信息
				CoupCheckBeanRespDTO coupCheckBeanRespDTO= new CoupCheckBeanRespDTO();
				List<ROrdCartItemCommRequest> ordCartItemCommList = ordCartCommRequest.getOrdCartItemCommList();
				if(CollectionUtils.isEmpty(ordCartItemCommList)){
					LogUtil.error(MODULE, "下单时,查询优惠码(店铺级)是否可用,ordCartItemCommList为空");
					coupCodeRespDTO.setResultMsg("ordCartItemCommList为空！");
					return coupCodeRespDTO;
				}
				coupCheckBeanRespDTO.setCoupId(coupInfoRespDTO.getId());
				coupCheckBeanRespDTO.setCoupName(coupInfoRespDTO.getCoupName());
				coupCheckBeanRespDTO.setCoupValue(coupInfoRespDTO.getCoupValue());
				String oldSkuGroup = new String();
				for (ROrdCartItemCommRequest rOrdCartItemCommRequest : ordCartItemCommList) {
					rOrdCartItemCommRequest.setShopId(ordCartCommRequest.getShopId());
					oldSkuGroup = this.groupCoupSkuMap(coupCheckParmReqDTO,
							rOrdCartItemCommRequest, coupSkuBeans1,
							coupSkuBeans2, coupInfoRespDTO, skuGroup,
							coupCheckBeanRespDTO,
							coupCheckShopList, coupIdSkuIdMap,deWeightList,coupFullShopSkuIdMap);
					if(StringUtil.isNotBlank(oldSkuGroup)){
						if(StringUtil.isNotBlank(skuGroup)){
							if(oldSkuGroup.compareTo(skuGroup)>0){
								skuGroup = oldSkuGroup;
							}
						}else{
							skuGroup = oldSkuGroup;
						}
					}
					
				}
				if(StringUtil.isBlank(skuGroup)||"0".equals(skuGroup)){
					LogUtil.error(MODULE, "优惠券Id:"+coupInfoRespDTO.getId()+"不符合规则没有通过");
					coupCodeRespDTO.setResultMsg("不符合优惠码使用规则！");
				}else if("1".equalsIgnoreCase(skuGroup)){
					coupCodeRespDTO.setIfCanUse("1");
					this.checkOrdCodeSkuNum(coupInfoRespDTO, coupSkuBeans2, coupCodeRespDTO);
				}
				//判断是否是满减规则,如果是把信息放到满减规则map里
				if (CouponConstants.CoupSys.judge_2.equals(skuGroup)) {
					CoupCheckInfoRespDTO coupCheckFullRespDTO= new CoupCheckInfoRespDTO();
					if(coupCheckBeanRespDTO.getOrdUseNum()<1){
						coupCheckBeanRespDTO.setOrdUseNum(CouponConstants.CoupSys.defaultUseNo);
					}
					coupCheckFullRespDTO.setCoupCheckBeanRespDTO(coupCheckBeanRespDTO);
					coupCheckFullRespDTO.setCoupSkuRespDTO(coupSkuBeans1);
					coupFullShopSkuIdMap.put(coupInfoRespDTO.getId(), coupCheckFullRespDTO);// 带满减的优惠券以及id
				}
				int size = coupCheckShopList.size();
				//对多个单品组合规则进行过滤处理,整合 例如满减价格 多个单品组合
				this.checkGroupSku(coupFullShopSkuIdMap,coupCheckShopList,deWeightList,coupIdSkuIdMap);
				if("2".equalsIgnoreCase(skuGroup)){
					if(size<coupCheckShopList.size()){
						coupCodeRespDTO.setIfCanUse("1");
					}else{
						coupCodeRespDTO.setResultMsg("购买金额未达到优惠码的使用金额门槛");
					}
				}
				if("1".equals(coupCodeRespDTO.getIfCanUse())){
					//判断购买金额是否大于优惠码面额
					for (CoupCheckBeanRespDTO coupCheckBeanDTO : coupCheckShopList) {
						
						CoupCheckInfoRespDTO coupCheckInfoBean = coupIdSkuIdMap.get(coupCheckBeanDTO.getCoupId());
						List<CoupSkuRespDTO> coupSkuRespDTO = coupCheckInfoBean.getCoupSkuRespDTO();
						//计算单品总面额
						long sumSkuValue = 0;
						for (CoupSkuRespDTO coupSkuRespDTO2 : coupSkuRespDTO) {
							sumSkuValue+=coupSkuRespDTO2.getDiscountMoney();
						}
						/**
						 * 不能出现0元订单,必须大于0元
						 */
						//如果有满减限制规则,则走满减限制规则
						//单品总面额必须大于优惠券可以使用单品的总金额
						if(sumSkuValue<coupCheckBeanDTO.getCoupValue().longValue()){
							coupCodeRespDTO.setIfCanUse("0");
							coupCodeRespDTO.setResultMsg("满足优惠码使用条件的单品总价低于优惠码的面额。");
						}
					}
				}
			}else if(coupInfoRespDTO.getShopId().equals(CouponConstants.CoupSys.shopInfinite)){
				/**
				 * 平台级优惠券校验
				 */
				
				// 可使用的优惠券信息(平台)
				List<CoupCheckBeanRespDTO> coupCheckBean = new ArrayList<CoupCheckBeanRespDTO>();
				// 带满减规则的优惠券ID集合
				Map<Long, CoupCheckInfoRespDTO> coupFullSkuIdMap = new HashMap<Long, CoupCheckInfoRespDTO>();
				//存放可能可用优惠券信息
				CoupCheckBeanRespDTO coupCheckBeanRespDTO= new CoupCheckBeanRespDTO();
				//带有满减价格规则的且通过其他限制校验的优惠券ID关联的单品
				List<CoupSkuRespDTO> coupSkuBeans1 = new ArrayList<CoupSkuRespDTO>();
				//通过所有限制校验的优惠券ID关联的单品
				List<CoupSkuRespDTO> coupSkuBeans2 = new ArrayList<CoupSkuRespDTO>();
				String skuGroup= new String();
				List<ROrdCartItemCommRequest> ordCartItemCommList = ordCartCommRequest.getOrdCartItemCommList();
				if(CollectionUtils.isEmpty(ordCartItemCommList)){
					LogUtil.error(MODULE, "下单时,查询优惠码是否可用,ordCartItemCommList为空");
					coupCodeRespDTO.setIfCanUse("0");
					coupCodeRespDTO.setResultMsg("ordCartItemCommList为空！");
					return coupCodeRespDTO;
				}
				CoupCheckParmReqDTO coupCheckParmReqDTO = new CoupCheckParmReqDTO();
				coupCheckParmReqDTO.setCoupId(coupInfoRespDTO.getId());
				coupCheckParmReqDTO.setRuleCode(coupInfoRespDTO.getUseRuleCode());
				coupCheckParmReqDTO.setCustLevelValue(ordCartCommRequest.getCustLevelValue());
				coupCheckParmReqDTO.setSource(ordCartCommRequest.getSource());
				String oldSkuGroup = new String();
				//解析每个子订单单品信息
				for (ROrdCartItemCommRequest rOrdCartItemCommRequest : ordCartItemCommList) {
					oldSkuGroup = this.groupCoupSkuMap(coupCheckParmReqDTO,
							rOrdCartItemCommRequest, coupSkuBeans1,
							coupSkuBeans2, coupInfoRespDTO, skuGroup,
							coupCheckBeanRespDTO, coupCheckBean,
							coupIdSkuIdMap,deWeightList,coupFullSkuIdMap);
					if(StringUtil.isNotBlank(oldSkuGroup)){
						skuGroup = oldSkuGroup;
					}
				}
				if(StringUtil.isBlank(skuGroup)){
					LogUtil.error(MODULE, "优惠券Id:"+coupInfoRespDTO.getId()+"不符合规则没有通过");
					coupCodeRespDTO.setResultMsg("不符合优惠码使用规则！");
				}else if("1".equalsIgnoreCase(skuGroup)){
					coupCodeRespDTO.setIfCanUse("1");
					this.checkOrdCodeSkuNum(coupInfoRespDTO, coupSkuBeans2, coupCodeRespDTO);
				}
				//存放优惠券信息
				coupCheckBeanRespDTO.setCoupId(coupInfoRespDTO.getId());
				coupCheckBeanRespDTO.setCoupName(coupInfoRespDTO.getCoupName());
				if(StringUtil.isNotEmpty(coupCheckBeanRespDTO.getVarLimit())){
					coupCheckBeanRespDTO.setVarLimit(coupCheckBeanRespDTO.getVarLimit());
				}
				if(StringUtil.isNotBlank(coupCheckBeanRespDTO.getNoExpress())){
					coupCheckBeanRespDTO.setNoExpress(coupCheckBeanRespDTO.getNoExpress());
				}
				//判断是否是满减规则,如果是把信息放到满减规则map里
				if (CouponConstants.CoupSys.judge_2.equals(skuGroup)) {
					CoupCheckInfoRespDTO coupCheckFullRespDTO= new CoupCheckInfoRespDTO();
					coupCheckFullRespDTO.setCoupCheckBeanRespDTO(coupCheckBeanRespDTO);
					coupCheckFullRespDTO.setCoupSkuRespDTO(coupSkuBeans1);
					coupFullSkuIdMap.put(coupInfoRespDTO.getId(), coupCheckFullRespDTO);// 带满减的优惠券以及
				} else if (CouponConstants.CoupSys.judge_1.equals(skuGroup)) {// 把信息存放全部通过的list以及map里

					deWeightList.add(coupCheckBeanRespDTO.getCoupId());// 专门为了去重的LIST
					coupCheckBean.add(coupCheckBeanRespDTO);// 存放List
					// 保存MAP
					CoupCheckInfoRespDTO coupCheckFullRespDTO = new CoupCheckInfoRespDTO();
					coupCheckFullRespDTO
							.setCoupCheckBeanRespDTO(coupCheckBeanRespDTO);
					coupCheckFullRespDTO.setCoupSkuRespDTO(coupSkuBeans2);
					coupIdSkuIdMap.put(coupCheckBeanRespDTO.getCoupId(),
							coupCheckFullRespDTO);
				}
				int size = coupCheckShopList.size();
				//对多个单品组合规则进行过滤处理,整合 例如满减价格 多个单品组合
				this.checkGroupSku(coupFullShopSkuIdMap,coupCheckShopList,deWeightList,coupIdSkuIdMap);
				if("2".equalsIgnoreCase(skuGroup)){
					if(size<coupCheckShopList.size()){
						coupCodeRespDTO.setIfCanUse("1");
					}else{
						coupCodeRespDTO.setResultMsg("购买金额未达到优惠码的使用金额门槛");
					}
				}
				if("1".equals(coupCodeRespDTO.getIfCanUse())){
					//判断购买金额是否大于优惠码面额
					for (CoupCheckBeanRespDTO coupCheckBeanDTO : coupCheckShopList) {
						CoupCheckInfoRespDTO coupCheckInfoBean = coupIdSkuIdMap.get(coupCheckBeanDTO.getCoupId());
						List<CoupSkuRespDTO> coupSkuRespDTO = coupCheckInfoBean.getCoupSkuRespDTO();
						//计算单品总面额
						long sumSkuValue = 0;
						for (CoupSkuRespDTO coupSkuRespDTO2 : coupSkuRespDTO) {
							sumSkuValue+=coupSkuRespDTO2.getDiscountMoney();
						}
						/**
						 * 不能出现0元订单,必须大于0元
						 */
						//如果有满减限制规则,则走满减限制规则
						//单品总面额必须大于优惠券可以使用单品的总金额
						if(sumSkuValue<coupInfoRespDTO.getCoupValue().longValue()){
							coupCodeRespDTO.setIfCanUse("0");
							coupCodeRespDTO.setResultMsg("可使用优惠券的单品总价低于优惠码的面额。");
						}
					}
				}
			}else{
				coupCodeRespDTO.setResultMsg("优惠码"+coupCode+"只能在"+coupInfoRespDTO.getShopName()+"店铺使用");
			}
			coupCodeRespDTO.setCoupIdskuIdMap(coupIdSkuIdMap);
		return coupCodeRespDTO;
	}
	
	
	/**
	 * 
	 * checkOrdCoupSkuNum:下单时,满数量限制数量校验. <br/> 
	 * 
	 * @author lisp
	 * @param 
	 * @return 
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	private void checkOrdCoupSkuNum(CoupInfoRespDTO coupInfoRespDTO,List<CoupSkuRespDTO> coupSkuBeans2,Map<Long, CoupCheckInfoRespDTO> coupIdSkuIdMap) throws BusinessException {
			@SuppressWarnings("unchecked")
			Map<String, Object> map = (Map<String, Object>) JSON
					.parse(coupInfoRespDTO.getUseRuleCode());
			
			if (map != null && map.entrySet().iterator() != null) {
				Iterator<Entry<String, Object>> beans = map.entrySet()
						.iterator();
				while (beans.hasNext()) {
					Entry<String, Object> ebean = beans.next();
					if(CouponConstants.CoupPara.RULE_CODE_140.equals(ebean.getKey().toString())){
						CoupFullLimitReqDTO coupFullLimitReqDTO = new CoupFullLimitReqDTO();
						coupFullLimitReqDTO.setCoupId(coupInfoRespDTO.getId());
						
						//根据coupId查询限制规则信息
						List<CoupFullLimitRespDTO> bean = coupFullLimitSV.queryCoupFullList(coupFullLimitReqDTO);
						if(!CollectionUtils.isEmpty(bean)){
							if(CouponConstants.CoupFullLimit.TYPE_2.equals(bean.get(0).getType())){
								Long i =0l;
								for (CoupSkuRespDTO coupSkuRespDTO : coupSkuBeans2) {
									i+=coupSkuRespDTO.getOrderAmount();
								}
								if(!CollectionUtils.isEmpty(bean)){
									if(i<bean.get(0).getAmount()){
										coupIdSkuIdMap.remove(coupInfoRespDTO.getId());
										return;
									}
								}
							}
						}
						
					}else{
						continue;
					}
				}
			}
	}
	
	
	/**
	 * 
	 * queryCoupInfo:下单时,查询可使用的优惠券. <br/> 
	 * 
	 * @author huanghe5
	 * @param ordCartsCommRequest
	 * @return CoupCheckRespDTO
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@Override
	public CoupOrdCheckRespDTO queryOrdCheckCoup(ROrdCartsCommRequest ordCartsCommRequest) throws BusinessException {
		//购物车订单级(店铺)实例
		List<ROrdCartCommRequest> ordCartsCommList = ordCartsCommRequest.getOrdCartsCommList();
		if(CollectionUtils.isEmpty(ordCartsCommList)){
			LogUtil.error(MODULE, "ordCartsCommRequest.getOrdCartsCommList() 入参为空..");
			return null;
		}
		/**
		 * key: Long cartId; 
		 * Value: List<CoupCheckBeanRespDTO> 这个是记录店铺能使用的优惠券信息
		 */
		Map<Long,List<CoupCheckBeanRespDTO>> coupOrdSkuMap = new HashMap<Long,List<CoupCheckBeanRespDTO>>();
		
		/**
		 * key:coupId value:skuID的集合 此List表示此优惠券能在哪些单品上使用 PS:满减规则可能是多个单品单价总和计算的
		 */
		Map<Long, CoupCheckInfoRespDTO> coupIdSkuIdMap = new HashMap<Long, CoupCheckInfoRespDTO>();

		for (ROrdCartCommRequest rOrdCartCommRequest : ordCartsCommList) {
			// 可使用的优惠券信息(店铺级)
			List<CoupCheckBeanRespDTO> coupCheckShopList = new ArrayList<CoupCheckBeanRespDTO>();
			// 带满减规则的优惠券ID集合(店铺)
			Map<Long, CoupCheckInfoRespDTO> coupFullShopSkuIdMap = new HashMap<Long, CoupCheckInfoRespDTO>();
			// 用于coupID去重计算coupId数量
			List<Long> deWeightList = new ArrayList<Long>();
			// 获取优惠券信息(店铺级)
			List<CoupInfoRespDTO> coupInfoBeans = this.queryCoupInfoList(ordCartsCommRequest.getStaffId(),rOrdCartCommRequest.getShopId(),CouponConstants.CoupSys.TYPE_LIMIT_1);
			if(CollectionUtils.isEmpty(coupInfoBeans)){
				LogUtil.error(MODULE, "下单时,获取优惠券信息为空 List<CoupInfoRespDTO> coupInfoBeans == null");
				continue;
			}
			for (CoupInfoRespDTO coupInfoRespDTO : coupInfoBeans) {
				CoupCheckParmReqDTO coupCheckParmReqDTO = new CoupCheckParmReqDTO();
				coupCheckParmReqDTO.setCoupId(coupInfoRespDTO.getId());
				coupCheckParmReqDTO.setRuleCode(coupInfoRespDTO.getUseRuleCode());
				coupCheckParmReqDTO.setCustLevelValue(rOrdCartCommRequest.getCustLevelValue());
				coupCheckParmReqDTO.setSource(ordCartsCommRequest.getSource());
				String skuGroup= new String();
				//带有满减价格规则的且通过其他限制校验的优惠券ID关联的单品
				List<CoupSkuRespDTO> coupSkuBeans1 = new ArrayList<CoupSkuRespDTO>();
				//通过所有限制校验的优惠券ID关联的单品
				List<CoupSkuRespDTO> coupSkuBeans2 = new ArrayList<CoupSkuRespDTO>();
				//存放可能可用优惠券信息
				CoupCheckBeanRespDTO coupCheckBeanRespDTO= new CoupCheckBeanRespDTO();
				List<ROrdCartItemCommRequest> ordCartItemCommList = rOrdCartCommRequest.getOrdCartItemCommList();
				if(CollectionUtils.isEmpty(ordCartItemCommList)){
					LogUtil.error(MODULE, "下单时,查询可使用的优惠券(店铺级),该用户无优惠券,ordCartItemCommList为空");
					return null;
				}
				coupCheckBeanRespDTO.setCoupId(coupInfoRespDTO.getId());
				coupCheckBeanRespDTO.setCoupName(coupInfoRespDTO.getCoupName());
				coupCheckBeanRespDTO.setCoupValue(coupInfoRespDTO.getCoupValue());
				//解析每个子订单单品信息
				for (ROrdCartItemCommRequest rOrdCartItemCommRequest : ordCartItemCommList) {
					rOrdCartItemCommRequest.setShopId(rOrdCartCommRequest.getShopId());
					skuGroup = this.groupCoupSkuMap(coupCheckParmReqDTO,
							rOrdCartItemCommRequest, coupSkuBeans1,
							coupSkuBeans2, coupInfoRespDTO, skuGroup,
							coupCheckBeanRespDTO,
							coupCheckShopList, coupIdSkuIdMap,deWeightList,coupFullShopSkuIdMap);
				}
				if(StringUtil.isBlank(skuGroup)){
					LogUtil.error(MODULE, "优惠券Id:"+coupInfoRespDTO.getId()+"不符合规则没有通过");
					continue;
				}
				
				//判断是否是满减规则,如果是把信息放到满减规则map里
				if (CouponConstants.CoupSys.judge_2.equals(skuGroup)) {
					CoupCheckInfoRespDTO coupCheckFullRespDTO= new CoupCheckInfoRespDTO();
					if(coupCheckBeanRespDTO.getOrdUseNum()<1){
						coupCheckBeanRespDTO.setOrdUseNum(CouponConstants.CoupSys.defaultUseNo);
					}
					coupCheckFullRespDTO.setCoupCheckBeanRespDTO(coupCheckBeanRespDTO);
					coupCheckFullRespDTO.setCoupSkuRespDTO(coupSkuBeans1);
					coupFullShopSkuIdMap.put(coupInfoRespDTO.getId(), coupCheckFullRespDTO);// 带满减的优惠券以及
				}
				
				this.checkOrdCoupSkuNum(coupInfoRespDTO, coupSkuBeans2, coupIdSkuIdMap);
			}
			//对多个单品组合规则进行过滤处理,整合 例如满减价格 多个单品组合
			this.checkGroupSku(coupFullShopSkuIdMap,coupCheckShopList,deWeightList,coupIdSkuIdMap);
			//获取用户优惠券具体明细信息
			for (CoupCheckBeanRespDTO coupCheckBeanDTO : coupCheckShopList) {
				
				CoupCheckInfoRespDTO coupCheckInfoBean = coupIdSkuIdMap.get(coupCheckBeanDTO.getCoupId());
				if(StringUtil.isEmpty(coupCheckInfoBean)){
					continue;
				}
				List<CoupSkuRespDTO> coupSkuRespDTO = coupCheckInfoBean.getCoupSkuRespDTO();
				//计算单品总面额
				long sumSkuValue = 0;
				for (CoupSkuRespDTO coupSkuRespDTO2 : coupSkuRespDTO) {
					sumSkuValue+=coupSkuRespDTO2.getDiscountMoney();
				}
				//查询用户所具有的有效优惠券信息
				List<CoupDetailRespDTO> detaiBeans = this.queryCoupDetailList(ordCartsCommRequest.getStaffId(),rOrdCartCommRequest.getShopId(),coupCheckBeanDTO.getCoupId(),null,CouponConstants.CoupSys.TYPE_LIMIT_1);
				if(CollectionUtils.isEmpty(detaiBeans)){
					continue;
				}
				int ordUseNum =0;
				/**
				 * 如果用户优惠券数量小于订单可使用数量则展现用户优惠券数量
				 */
				if(detaiBeans.size()<=coupCheckBeanDTO.getOrdUseNum()){
					ordUseNum = detaiBeans.size();
				}else{
					ordUseNum = coupCheckBeanDTO.getOrdUseNum();
				}
				
				if (ordUseNum>0) {
					List<CoupDetailRespDTO> coupDetails = new ArrayList<CoupDetailRespDTO>();
					int j=0;
					for (int i=0;i<ordUseNum;i++) {
						j+=1;
						CoupDetailRespDTO detailBean= detaiBeans.get(i);
						if(StringUtil.isNotBlank(detailBean.getUseRuleCode())){
							if(detailBean.getUseRuleCode().contains(CouponConstants.CoupPara.RULE_CODE_240)){
								detailBean.setDiscountCoup(CouponConstants.CoupSys.discountCoup_1);
							}
						}
						/**
						 * 不能出现0元订单,必须大于0元
						 * RULE_CODE_240:表示该券是折扣券，不需要过滤
						 */
						if(StringUtil.isNotBlank(detailBean.getUseRuleCode())&&detailBean.getUseRuleCode().contains(CouponConstants.CoupPara.RULE_CODE_240)){
							detailBean.setNoExpress(coupCheckBeanDTO.getNoExpress());
							coupDetails.add(detailBean);
						}else{
							if(rOrdCartCommRequest.getPayMoney().longValue()>=(detailBean.getCoupValue().longValue()*j)){
								//如果有满减限制规则,则走满减限制规则
								//单品总面额必须大于优惠券可以使用单品的总金额
								if(sumSkuValue>=detailBean.getCoupValue().longValue()*j){
									detailBean.setNoExpress(coupCheckBeanDTO.getNoExpress());
									coupDetails.add(detailBean);
								}
							}
						}
					}
					
					coupCheckBeanDTO.setCoupDetails(coupDetails);
					coupCheckBeanDTO.setCoupSize(coupDetails.size());
					
					// 存放信息
					coupCheckInfoBean.setCoupCheckBeanRespDTO(coupCheckBeanDTO);
				}
			}
			
			if (!CollectionUtils.isEmpty(coupCheckShopList)) {
				coupOrdSkuMap.put(rOrdCartCommRequest.getId(),
						coupCheckShopList);
			}
		}
		
		//整合返回参数
		CoupOrdCheckRespDTO bean = new CoupOrdCheckRespDTO();

		bean.setCoupIdskuIdMap(coupIdSkuIdMap);// Map
		bean.setCoupOrdSkuMap(coupOrdSkuMap);// 订单MAP
		
		
		/**
		 * 平台级优惠券校验
		 */
		
		// 可使用的优惠券信息(平台)
		List<CoupCheckBeanRespDTO> coupCheckBean = new ArrayList<CoupCheckBeanRespDTO>();
		// 用于coupID去重计算coupId数量
		List<Long> deWeightList = new ArrayList<Long>();
		//获取优惠券信息
		List<CoupInfoRespDTO> coupInfoBeans = this.queryCoupInfoList(ordCartsCommRequest.getStaffId(),Long.valueOf(CouponConstants.CoupSys.shopInfinite),CouponConstants.CoupSys.TYPE_LIMIT_0);
		if(CollectionUtils.isEmpty(coupInfoBeans)){
			LogUtil.error(MODULE, "下单时,获取优惠券信息为空 List<CoupInfoRespDTO> coupInfoBeans == null");
			
			return bean;
		}
		// 带满减规则的优惠券ID集合
		Map<Long, CoupCheckInfoRespDTO> coupFullSkuIdMap = new HashMap<Long, CoupCheckInfoRespDTO>();
		
		for (CoupInfoRespDTO coupInfoRespDTO : coupInfoBeans) {
			//存放可能可用优惠券信息
			CoupCheckBeanRespDTO coupCheckBeanRespDTO= new CoupCheckBeanRespDTO();
			//带有满减价格规则的且通过其他限制校验的优惠券ID关联的单品
			List<CoupSkuRespDTO> coupSkuBeans1 = new ArrayList<CoupSkuRespDTO>();
			//通过所有限制校验的优惠券ID关联的单品
			List<CoupSkuRespDTO> coupSkuBeans2 = new ArrayList<CoupSkuRespDTO>();
			String skuGroup= new String();
			for (ROrdCartCommRequest rOrdCartCommRequest : ordCartsCommList) {
				List<ROrdCartItemCommRequest> ordCartItemCommList = rOrdCartCommRequest.getOrdCartItemCommList();
				if(CollectionUtils.isEmpty(ordCartItemCommList)){
					LogUtil.error(MODULE, "下单时,查询可使用的优惠券,该用户无优惠券,ordCartItemCommList为空");
					return null;
				}
				CoupCheckParmReqDTO coupCheckParmReqDTO = new CoupCheckParmReqDTO();
				coupCheckParmReqDTO.setCoupId(coupInfoRespDTO.getId());
				coupCheckParmReqDTO.setRuleCode(coupInfoRespDTO.getUseRuleCode());
				coupCheckParmReqDTO.setCustLevelValue(rOrdCartCommRequest.getCustLevelValue());
				coupCheckParmReqDTO.setSource(ordCartsCommRequest.getSource());
				//解析每个子订单单品信息
				for (ROrdCartItemCommRequest rOrdCartItemCommRequest : ordCartItemCommList) {
					
					skuGroup = this.groupCoupSkuMap(coupCheckParmReqDTO,
							rOrdCartItemCommRequest, coupSkuBeans1,
							coupSkuBeans2, coupInfoRespDTO, skuGroup,
							coupCheckBeanRespDTO, coupCheckBean,
							coupIdSkuIdMap,deWeightList,coupFullSkuIdMap);
					if(StringUtil.isBlank(skuGroup)){
						LogUtil.error(MODULE, "优惠券Id:"+coupInfoRespDTO.getId()+",rOrdCartItemCommRequest.getId()."+rOrdCartItemCommRequest.getId()+"不符合规则没有通过");
						continue;
					}
				}
				if(StringUtil.isBlank(skuGroup)){
					LogUtil.error(MODULE, "优惠券Id:"+coupInfoRespDTO.getId()+"不符合规则没有通过");
					continue;
				}
				
			}
			//存放优惠券信息
			coupCheckBeanRespDTO.setCoupId(coupInfoRespDTO.getId());
			coupCheckBeanRespDTO.setCoupName(coupInfoRespDTO.getCoupName());
			if(StringUtil.isNotEmpty(coupCheckBeanRespDTO.getVarLimit())){
				coupCheckBeanRespDTO.setVarLimit(coupCheckBeanRespDTO.getVarLimit());
			}
			if(StringUtil.isNotBlank(coupCheckBeanRespDTO.getNoExpress())){
				coupCheckBeanRespDTO.setNoExpress(coupCheckBeanRespDTO.getNoExpress());
			}
			//判断是否是满减规则,如果是把信息放到满减规则map里
			if (CouponConstants.CoupSys.judge_2.equals(skuGroup)) {
				CoupCheckInfoRespDTO coupCheckFullRespDTO= new CoupCheckInfoRespDTO();
				coupCheckFullRespDTO.setCoupCheckBeanRespDTO(coupCheckBeanRespDTO);
				coupCheckFullRespDTO.setCoupSkuRespDTO(coupSkuBeans1);
				coupFullSkuIdMap.put(coupInfoRespDTO.getId(), coupCheckFullRespDTO);// 带满减的优惠券以及
			} else if (CouponConstants.CoupSys.judge_1.equals(skuGroup)) {// 把信息存放全部通过的list以及map里

				deWeightList.add(coupCheckBeanRespDTO.getCoupId());// 专门为了去重的LIST
				coupCheckBean.add(coupCheckBeanRespDTO);// 存放List
				// 保存MAP
				CoupCheckInfoRespDTO coupCheckFullRespDTO = new CoupCheckInfoRespDTO();
				coupCheckFullRespDTO
						.setCoupCheckBeanRespDTO(coupCheckBeanRespDTO);
				coupCheckFullRespDTO.setCoupSkuRespDTO(coupSkuBeans2);
				coupIdSkuIdMap.put(coupCheckBeanRespDTO.getCoupId(),
						coupCheckFullRespDTO);
			}
			this.checkOrdCoupSkuNum(coupInfoRespDTO, coupSkuBeans2, coupIdSkuIdMap);
		}
		//对多个单品组合规则进行过滤处理,整合
		this.checkGroupSku(coupFullSkuIdMap,coupCheckBean,deWeightList,coupIdSkuIdMap);
		//获取用户优惠券具体明细信息
		for (CoupCheckBeanRespDTO coupCheckBeanDTO : coupCheckBean) {
			
		    CoupCheckInfoRespDTO coupCheckInfoBean = coupIdSkuIdMap.get(coupCheckBeanDTO.getCoupId());
            List<CoupSkuRespDTO> coupSkuRespDTO = coupCheckInfoBean.getCoupSkuRespDTO();
            //计算单品总面额
            long sumSkuValue = 0;
            for (CoupSkuRespDTO coupSkuRespDTO2 : coupSkuRespDTO) {
                sumSkuValue+=coupSkuRespDTO2.getDiscountMoney();
            }
		    
			//查询用户所具有的有效优惠券信息
			List<CoupDetailRespDTO> detaiBeans = this.queryCoupDetailList(ordCartsCommRequest.getStaffId(),null,coupCheckBeanDTO.getCoupId(),null,CouponConstants.CoupSys.TYPE_LIMIT_0);
			
			if(CollectionUtils.isEmpty(detaiBeans)){
                continue;
            }
            int ordUseNum =0;//中介字段
            /**
             * 如果用户优惠券数量小于订单可使用数量则展现用户优惠券数量
             */
            if(detaiBeans.size()<=coupCheckBeanDTO.getOrdUseNum()){
                ordUseNum = detaiBeans.size();
            }else{
                ordUseNum = coupCheckBeanDTO.getOrdUseNum();
            }
			
            if (ordUseNum>0) {
                List<CoupDetailRespDTO> coupDetails = new ArrayList<CoupDetailRespDTO>();
                int j=0;
                for (int i = 0; i < ordUseNum; i++) {
                    j += 1;
                    CoupDetailRespDTO detailBean = detaiBeans.get(i);
                    // 单品总面额必须大于优惠券可以使用单品的总金额
                    if (sumSkuValue >= detailBean.getCoupValue().longValue() * j) {
                    	detailBean.setNoExpress(coupCheckBeanDTO.getNoExpress());
                        coupDetails.add(detailBean);
                    }
                }
                
                coupCheckBeanDTO.setCoupDetails(coupDetails);
                coupCheckBeanDTO.setCoupSize(coupDetails.size());
                
                // 存放信息
                coupCheckInfoBean.setCoupCheckBeanRespDTO(coupCheckBeanDTO);
                
                coupIdSkuIdMap.get(coupCheckBeanDTO.getCoupId())
                .getCoupCheckBeanRespDTO().setCoupDetails(coupDetails);
            }
			
			/*if (!CollectionUtils.isEmpty(detaiBeans)&&coupCheckBeanDTO.getOrdUseNum()>0) {
			    
				List<CoupDetailRespDTO> coupDetails = new ArrayList<CoupDetailRespDTO>();
				for (int i=0;i<=coupCheckBeanDTO.getOrdUseNum();i++) {
					coupDetails.add(detaiBeans.get(i));
				}
				coupCheckBeanDTO.setCoupDetails(coupDetails);
				coupCheckBeanDTO.setCoupSize(coupDetails.size());
				// 存放MAP
				coupIdSkuIdMap.get(coupCheckBeanDTO.getCoupId())
						.getCoupCheckBeanRespDTO().setCoupDetails(coupDetails);
				
			}*/
		}
		//整合返回参数
		bean.setCoupPlatfBean(coupCheckBean);//平台级
		bean.setCoupIdskuIdMap(coupIdSkuIdMap);//Map
		bean.setCoupOrdSkuMap(coupOrdSkuMap);//订单MAP
		return bean;
	}
	
	/**
	 * 
	 * checkLimit:校验各个规则的优惠券. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupLimitCheckBean
	 * @param coupInfoRespDTO
	 * @param rOrdCartItemCommRequest
	 * @return 
	 * @since JDK 1.7
	 */
	private CoupLimitCheckRespDTO checkLimit(CoupCheckParmReqDTO coupCheckParmReqDTO,CoupInfoRespDTO coupInfoRespDTO,ROrdCartItemCommRequest rOrdCartItemCommRequest){
		CoupLimitCheckRespDTO coupLimitCheckBean = new CoupLimitCheckRespDTO();
		CoupLimitCheckRespDTO coupLimitCheckBean1 = new CoupLimitCheckRespDTO();
		coupLimitCheckBean.setTypeLimit(coupInfoRespDTO.getTypeLimit());
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) JSON
				.parse(coupInfoRespDTO.getUseRuleCode());
		
		if (map != null && map.entrySet().iterator() != null) {
			Iterator<Entry<String, Object>> beans = map.entrySet()
					.iterator();
			while (beans.hasNext()) {
				
				Entry<String, Object> ebean = beans.next();
				if(CouponConstants.CoupPara.RULE_CODE_150.equals(ebean.getKey().toString())){
					coupLimitCheckBean.setVarLimit(ebean.getValue().toString());
				}else{
					if(CouponConstants.CoupPara.RULE_CODE_VALUE_0.equals(ebean.getValue().toString())){
						continue;
					}
					//免邮
					if(CouponConstants.CoupPara.RULE_CODE_180.equals(ebean.getKey().toString())){
						coupLimitCheckBean.setNoExpress(CouponConstants.CoupSys.noExpress_1);
					}
				}
				// 获取spring id
				CoupParamRespDTO coupParamRespDTO = coupParamSV
						.queryCoupParamByCode(ebean.getKey().toString());
				if (coupParamRespDTO != null&&StringUtil.isNotBlank(coupParamRespDTO.getServiceId())) {
					// 通过spring id找到该类的方法
					ICoupRuleSV coupRuleSV = EcpFrameContextHolder.getBean(
							coupParamRespDTO.getServiceId(),
							ICoupRuleSV.class);
					// 使用方法
					if (coupRuleSV != null) {
						coupLimitCheckBean = coupRuleSV.checkCoupLimit(rOrdCartItemCommRequest, coupCheckParmReqDTO,coupLimitCheckBean);
						//优惠券规则校验 0:不通过 1:通过   2:多个单品组合
						if (CouponConstants.CoupSys.judge_0
								.equals(coupLimitCheckBean.getJudge())) {
							return null;
						}
					}
				}else{
					coupLimitCheckBean.setJudge(CouponConstants.CoupSys.judge_1);
				}
			}
			if (CouponConstants.CoupSys.judge_1
					.equals(coupLimitCheckBean.getJudge())) {
				coupLimitCheckBean1 = coupLimitCheckBean;
				return coupLimitCheckBean1;
			}else{
				return null;
			}
			
		}else{
			coupLimitCheckBean1.setJudge(CouponConstants.CoupSys.judge_1);
			return coupLimitCheckBean1;
		}
		
	}
	//多个单品价格过滤校验
	private void checkGroupSku(
			Map<Long, CoupCheckInfoRespDTO> coupFullSkuIdMap,
			List<CoupCheckBeanRespDTO> coupCheckShopList,
			List<Long> deWeightList,
			Map<Long, CoupCheckInfoRespDTO> coupIdSkuIdMap) {
		if (coupFullSkuIdMap != null && coupFullSkuIdMap.entrySet().iterator() != null) {
			Iterator<Entry<Long,CoupCheckInfoRespDTO>> beans = coupFullSkuIdMap.entrySet()
					.iterator();
			
			while (beans.hasNext()) {
				Entry<Long,CoupCheckInfoRespDTO> ebean = beans.next();
				Long coupId = ebean.getKey();
				
				CoupInfo coupInfo = coupInfoMapper.selectByPrimaryKey(coupId);
				
				boolean bol = coupFullLimitSV.checkCoupFullLimit(ebean.getValue().getCoupSkuRespDTO(), coupId,coupInfo.getCoupValue());
				if(bol){
					CoupCheckBeanRespDTO coupCheckBeanResp = ebean.getValue().getCoupCheckBeanRespDTO();
					
					//记录优惠券能在哪些单品上使用
					if(!deWeightList.contains(coupId)){
						deWeightList.add(coupId);
						coupCheckShopList.add(coupCheckBeanResp);
						// 保存MAP
						CoupCheckInfoRespDTO coupCheckInfoRespDTO= new CoupCheckInfoRespDTO();
						coupCheckInfoRespDTO.setCoupCheckBeanRespDTO(coupCheckBeanResp);
						coupCheckInfoRespDTO.setCoupSkuRespDTO(ebean.getValue().getCoupSkuRespDTO());
						coupIdSkuIdMap.put(coupId, coupCheckInfoRespDTO);
					}
				}
			}
		}
	}

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
	@Override
	public boolean checkCoupCode(CoupOrdCheckReqDTO coupOrdCheckReqDTO) throws BusinessException {
		if(coupOrdCheckReqDTO==null||StringUtil.isEmpty(coupOrdCheckReqDTO.getCoupCode())){
			LogUtil.error(MODULE, "优惠码传参有误:"+ coupOrdCheckReqDTO) ;
			return false;
		}
		CoupInfoReqDTO coupInfoReqDTO = new CoupInfoReqDTO();
		String coupCode = coupOrdCheckReqDTO.getCoupCode().toUpperCase();//变为大写
		coupInfoReqDTO.setCoupCode(coupCode);
		coupInfoReqDTO.setIfCode(CouponConstants.CoupInfo.IF_CODE_1);
		coupInfoReqDTO.setActiveTime(DateUtil.getSysDate());
		coupInfoReqDTO.setInactiveTime(DateUtil.getSysDate());
		List<CoupInfoRespDTO> coupBeans = coupInfoSV.queryCoupInfoList(coupInfoReqDTO);
		if(CollectionUtils.isEmpty(coupBeans)){
			coupInfoReqDTO = new CoupInfoReqDTO();
			coupInfoReqDTO.setCoupCode(coupCode);
			coupInfoReqDTO.setIfCode(CouponConstants.CoupInfo.IF_CODE_1);
			List<CoupInfoRespDTO> coupCodeCheck = coupInfoSV.queryCoupInfoList(coupInfoReqDTO);
			if(!CollectionUtils.isEmpty(coupCodeCheck)){
				LogUtil.error(MODULE, "优惠码:"+ coupCode+"已经过期") ;
				String[] code = new String[1];
				code[0] = coupInfoReqDTO.getCoupName();
				throw new BusinessException("coupon.error.450019",code);
			}
			LogUtil.error(MODULE, "优惠码:"+ coupCode+"无此记录") ;
			String[] code = new String[1];
			code[0] = coupInfoReqDTO.getCoupName();
			throw new BusinessException("coupon.error.450018",code);
		}
		for (CoupInfoRespDTO coupInfoRespDTO : coupBeans) {
			coupInfoRespDTO.setCheckLimit(CouponConstants.CoupSys.checkLimit_0);// 下单校验
			// 获取商品信息
			List<ROrdCartCommRequest> ordCartsCommList = coupOrdCheckReqDTO
					.getOrdCartsCommList();
			if (CollectionUtils.isEmpty(ordCartsCommList)) {
				return false;
			}
			Map<Long,CoupCheckInfoRespDTO> coupIdskuIdMap = coupOrdCheckReqDTO.getCoupIdskuIdMap();
			// 对各个单品以及优惠券进行校验
			for (ROrdCartCommRequest rOrdCartCommRequest : ordCartsCommList) {
				CoupCheckParmReqDTO coupCheckParmReqDTO = new CoupCheckParmReqDTO();
				coupCheckParmReqDTO.setCoupId(coupInfoRespDTO.getId());
				coupCheckParmReqDTO.setRuleCode(coupInfoRespDTO.getUseRuleCode());
				coupCheckParmReqDTO.setCustLevelValue(rOrdCartCommRequest.getCustLevelValue());
				coupCheckParmReqDTO.setSource(rOrdCartCommRequest.getSource());
				List<ROrdCartItemCommRequest> ordCartItemCommList = rOrdCartCommRequest
						.getOrdCartItemCommList();
				for (ROrdCartItemCommRequest rOrdCartItemCommRequest : ordCartItemCommList) {
					try {
						// 3.1 把单品通过优惠券各个规则的校验
						CoupLimitCheckRespDTO coupLimitCheckBean = this
								.checkLimit(coupCheckParmReqDTO,coupInfoRespDTO,
										rOrdCartItemCommRequest);
						//3.11 校验用户选择的同一类优惠券数量不能超过限制规则的数量
						if(coupLimitCheckBean == null){
							return false;
						}
					
						//3.2对满减规则进行校验
						if (CouponConstants.CoupSys.judge_2.equals(coupLimitCheckBean.getSkuGroup())) {
							CoupCheckInfoRespDTO coupCheckInfoRespDTO= coupIdskuIdMap.get(coupInfoRespDTO.getId());
							List<CoupSkuRespDTO> coupSkuRespDTO = coupCheckInfoRespDTO.getCoupSkuRespDTO();
							boolean bol = coupFullLimitSV.checkCoupFullLimit(coupSkuRespDTO, coupInfoRespDTO.getId(),null);
							if(!bol){
								LogUtil.error(MODULE, "进行满减价格校验时,不通过; 优惠券ID:"+coupInfoRespDTO.getId()+"商品信息:"+coupSkuRespDTO);
								String[] code = new String[1];
								code[0] = coupInfoRespDTO.getCoupName();
								throw new BusinessException("coupon.error.450016",code);
							}
						}
						return true;
					} catch (Exception e) {
						LogUtil.error(MODULE, "优惠券检验报错", e);
						String[] code = new String[1];
						code[0] = coupInfoRespDTO.getCoupName();
						throw new BusinessException("coupon.error.450017",code);
					}
				}
			}
		}
		
		return true;
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
	public List<CoupConsumeReqDTO> saveCoupOrdSku(ROrdCartsCommRequest ordCartsCommRequest,String operType) throws BusinessException {
		//所有优惠券-单品信息集合
		Map<Long,CoupCheckInfoRespDTO> coupIdskuIdMap = ordCartsCommRequest.getCoupIdskuIdMap();
		if(StringUtil.isEmpty(coupIdskuIdMap)){
			LogUtil.error(MODULE, "获取Map<Long,CoupCheckInfoRespDTO> coupIdskuIdMap信息为空");
			throw new BusinessException("coupon.error.450035");
		}
		// 1.获取用户所选择的优惠券信息(集合)
		List<CoupConsumeReqDTO> coupConsumeReqDTOs = new ArrayList<CoupConsumeReqDTO>();

		List<ROrdCartCommRequest> ordCartsCommList = ordCartsCommRequest.getOrdCartsCommList();
		if(CollectionUtils.isEmpty(ordCartsCommList)){
			
			LogUtil.error(MODULE, "获取List<ROrdCartCommRequest> ordCartsCommList信息为空");
			throw new BusinessException("参数异常请重新提交..");
		}
		
		for (ROrdCartCommRequest rOrdCartCommRequest : ordCartsCommList) {
			if(StringUtil.isNotBlank(rOrdCartCommRequest.getCoupCode())){
				//优惠码使用明细新增
				CoupInfoReqDTO coupInfoReqDTO = new CoupInfoReqDTO();
				coupInfoReqDTO.setCoupCode(rOrdCartCommRequest.getCoupCode().toUpperCase());
				CoupInfoRespDTO coupInfoRespDTO =coupInfoSV.queryCoupInfoByCoupCode(coupInfoReqDTO);
				CoupConsumeReqDTO coupConsumeReqDTO = new CoupConsumeReqDTO();
				coupConsumeReqDTO.setCoupNo(rOrdCartCommRequest.getCoupCode());
				coupConsumeReqDTO.setOrderId(rOrdCartCommRequest.getOrderId());
				coupConsumeReqDTO.setCoupMoney(coupInfoRespDTO.getCoupValue());//设置平摊金额
				coupConsumeReqDTO.setSiteId(rOrdCartCommRequest.getCurrentSiteId());
				coupConsumeReqDTO.setStaffId(ordCartsCommRequest.getStaffId());
				coupConsumeReqDTO.setCoupId(coupInfoRespDTO.getId());
				coupConsumeReqDTO.setOperType(operType);
				coupConsumeReqDTO.setShopId(rOrdCartCommRequest.getShopId());
				coupConsumeReqDTO.setUseTime(DateUtil.getSysDate());
				coupConsumeReqDTOs.add(coupConsumeReqDTO);
				coupCreateInitSV.saveCoupConsume(coupConsumeReqDTO);
			}
			List<CoupCheckBeanRespDTO> coupCheckBeanShop = rOrdCartCommRequest.getCoupCheckBean();
			if(CollectionUtils.isEmpty(coupCheckBeanShop)){
				LogUtil.error(MODULE, "获取List<CoupCheckBeanRespDTO> coupCheckBeanShop信息为空");
				continue;
			}
			List<ROrdCartItemCommRequest> ordCartItemCommList = rOrdCartCommRequest.getOrdCartItemCommList();
			for (CoupCheckBeanRespDTO coupCheckBeanRespDTO : coupCheckBeanShop) {
				List<CoupDetailRespDTO> coupDetails = coupCheckBeanRespDTO.getCoupDetails();
				CoupDetailReqDTO coupDetailReqDTO = new CoupDetailReqDTO();
				coupDetailReqDTO.setStaffId(ordCartsCommRequest.getStaffId());
				coupDetailReqDTO.setCoupId(coupCheckBeanRespDTO.getCoupId());
				coupDetailReqDTO.setIfUse(CouponConstants.CoupDetail.IF_USE_0);
				coupDetailReqDTO.setSortValue(CouponConstants.CoupDetail.sort_1);//升序
				coupDetailReqDTO.setKey("INACTIVE_TIME");//失效时间
				List<CoupDetailRespDTO> coupDetailBeans = this.queryCoupDetailList(coupDetailReqDTO);
				if(CollectionUtils.isEmpty(coupDetailBeans)){
					continue;
				}
				if(CollectionUtils.isEmpty(coupDetails)){
					continue;
				}
				if(coupDetailBeans.size()<coupDetails.size()){
					LogUtil.error(MODULE, "获取List<CoupCheckBeanRespDTO> coupCheckBeanShop信息为空");
					throw new BusinessException("coupon.error.450036");
				}
				for(int i=0;i<coupDetails.size();i++){
					CoupDetailRespDTO coupDetailRespDTO = coupDetailBeans.get(i);

					//把用户优惠券改为已使用
					Timestamp useTime = DateUtil.getSysDate();
					CoupDetailReqDTO coupDetailBean = new CoupDetailReqDTO();
					//查询条件
					coupDetailBean.setId(coupDetailRespDTO.getId());
					coupDetailBean.setCoupId(coupDetailRespDTO.getCoupId());
					coupDetailBean.setCoupNo(coupDetailRespDTO.getCoupNo());
					coupDetailBean.setStatus(CouponConstants.CoupSys.status_1);
					coupDetailBean.setIfUse(CouponConstants.CoupDetail.IF_USE_0);
					coupDetailBean.setStaffId(coupDetailRespDTO.getStaffId());
					//修改参数
					CoupDetail coupDetail = new CoupDetail();
					coupDetail.setUseTime(useTime);
					coupDetail.setIfUse(CouponConstants.CoupDetail.IF_USE_1);
					coupDetail.setUpdateStaff(rOrdCartCommRequest.getStaffId());
					coupCreateInitSV.updateCoupDetail(coupDetailBean,coupDetail);
					//添加优惠券消费记录
					CoupCheckInfoRespDTO coupCheckInfoRespDTO = coupIdskuIdMap.get(coupCheckBeanRespDTO.getCoupId());
					//设置平摊金额
					coupCheckInfoRespDTO = this.getCoupCheckInfo(coupCheckInfoRespDTO);
					List<CoupSkuRespDTO> coupSkuRespDTO = coupCheckInfoRespDTO
							.getCoupSkuRespDTO();
					
					
					for (CoupSkuRespDTO coupSkuRespDTO2 : coupSkuRespDTO) {
						CoupConsumeReqDTO coupConsumeReqDTO = new CoupConsumeReqDTO();
						coupConsumeReqDTO.setCoupNo(coupDetailRespDTO
								.getCoupNo());
						coupConsumeReqDTO.setOrderId(rOrdCartCommRequest
								.getOrderId());
						ROrdCartItemCommRequest ordCartItemCommRequest = this
								.getSubOrder(coupSkuRespDTO2.getItemId(),
										rOrdCartCommRequest.getOrderId(),
										ordCartItemCommList);
						if (ordCartItemCommRequest == null) {
							LogUtil.error(MODULE,
									"在通过itemId获取子订单相关信息时候,获取ROrdCartItemCommRequest ordCartItemCommRequest信息为空");
							throw new BusinessException("coupon.error.450035");
						}
						coupConsumeReqDTO.setCoupMoney(coupSkuRespDTO2.getCoupMoney());//设置平摊金额
						coupConsumeReqDTO.setSiteId(ordCartItemCommRequest
								.getCurrentSiteId());
						coupConsumeReqDTO.setOrderSubId(ordCartItemCommRequest
								.getOrderSubId());
						coupConsumeReqDTO.setStaffId(ordCartsCommRequest
								.getStaffId());
						coupConsumeReqDTO.setCoupDetailId(coupDetailRespDTO
								.getId());
						coupConsumeReqDTO.setCoupId(coupDetailRespDTO
								.getCoupId());
						coupConsumeReqDTO.setOperType(operType);
						coupConsumeReqDTO.setShopId(rOrdCartCommRequest
								.getShopId());
						coupConsumeReqDTO.setUseTime(useTime);
						coupConsumeReqDTOs.add(coupConsumeReqDTO);
						coupCreateInitSV.saveCoupConsume(coupConsumeReqDTO);
					}
				}
				
				/*List<CoupDetailRespDTO> CoupDetails = coupCheckBeanRespDTO.getCoupDetails();
				
				for (CoupDetailRespDTO coupDetailRespDTO : CoupDetails) {
				}*/
				
			}
			
		}
		
		return coupConsumeReqDTOs;
	}
	
	/**
	 * 计算优惠券平摊金额
	 * @param coupCheckInfoRespDTO
	 * @return
	 */
	private CoupCheckInfoRespDTO getCoupCheckInfo(CoupCheckInfoRespDTO coupCheckInfoRespDTO){
		
		CoupCheckBeanRespDTO coupCheckBeanRespDTO = coupCheckInfoRespDTO.getCoupCheckBeanRespDTO();
		List<CoupSkuRespDTO> coupSkuRespDTO = coupCheckInfoRespDTO.getCoupSkuRespDTO();
		long coupValue = coupCheckBeanRespDTO.getCoupValue();
		//排序list,按降序
		coupSkuRespDTO = CoupUtil.sortList(coupSkuRespDTO,"discountMoney",true);
		//计算可被优惠券,优惠扣减的所有单品总金额和
		if(!CollectionUtils.isEmpty(coupSkuRespDTO)){
			if(coupSkuRespDTO.size()==1){
				coupSkuRespDTO.get(0).setCoupMoney(coupCheckBeanRespDTO.getCoupValue());
			}else{
				long sumSkuValue=0;
				for (CoupSkuRespDTO coupSkuRespDTO2 : coupSkuRespDTO) {
					sumSkuValue+=coupSkuRespDTO2.getDiscountMoney().longValue();
				}
				long sumCoupMoney = 0;
				//计算平摊金额
				for(int i=1;i<coupSkuRespDTO.size();i++){
					CoupSkuRespDTO skuBean=coupSkuRespDTO.get(i);
					//获取平摊金额
					long coupMoney = this.getCoupMoney(sumSkuValue,
							skuBean.getDiscountMoney(),
							coupValue);
					skuBean.setCoupMoney(coupMoney);
					sumCoupMoney +=coupMoney;
				}
				coupSkuRespDTO.get(0).setCoupMoney(coupValue-sumCoupMoney);
			}
		}
		return coupCheckInfoRespDTO;
	}
	
	/**
	 * 获取平摊金额
	 * @param sumSkuValue:总金额,discountMoney:单品(单价*数量)总金额,coupValue:优惠券面额
	 * @param discountMoney
	 * @param coupValue
	 * @return
	 */
	private long getCoupMoney(long sumSkuValue,long discountMoney,long coupValue){
		//获取比例
		double disCountPrice = (new BigDecimal(discountMoney)).divide(new BigDecimal(sumSkuValue),10,BigDecimal.ROUND_HALF_UP).doubleValue();
		//获取平摊金额 四舍五入
		long b =(new BigDecimal(coupValue)).setScale(2).multiply(new BigDecimal(disCountPrice)).setScale(10,BigDecimal.ROUND_HALF_UP).longValue();
		
		return b;
	}
	
	/**
	 * 
	 * getSubOrder:获取子订单信息. <br/> 
	 * 
	 * @author huanghe5
	 * @param itemId
	 * @param orderId
	 * @param ordCartItemCommList
	 * @return 
	 * @since JDK 1.7
	 */
	private ROrdCartItemCommRequest getSubOrder(Long itemId,String orderId,List<ROrdCartItemCommRequest> ordCartItemCommList){
		if(CollectionUtils.isEmpty(ordCartItemCommList)){
			throw new BusinessException("在通过itemId获取子订单相关信息时候,获取List<ROrdCartItemCommRequest> ordCartItemCommList信息为空");
		}
		for (ROrdCartItemCommRequest rOrdCartItemCommRequest : ordCartItemCommList) {
			if (orderId.equals(rOrdCartItemCommRequest.getOrderId())) {
				if(itemId!=null){
					//只是为了立即购买做的补丁，具体的修改方式待定
					if (itemId.equals(rOrdCartItemCommRequest.getId())) {
						return rOrdCartItemCommRequest;
					}
				}else{
					if("02".equals(rOrdCartItemCommRequest.getCartType())){
						return rOrdCartItemCommRequest;
					}
				}

			}
		}
		return null;
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
	public void deleteOrdCoup(String orderId,Long staffId,Long updateStaff) throws BusinessException {
	
		// 查询初始化查询条件
		CoupConsumeCriteria example = new CoupConsumeCriteria();
		CoupConsumeReqDTO coupConsumeReqDTO = new CoupConsumeReqDTO();
		coupConsumeReqDTO.setStatus(CouponConstants.CoupSys.status_1);
		coupConsumeReqDTO.setOrderId(orderId);
		coupConsumeReqDTO.setStaffId(staffId);
		coupCreateInitSV.initCoupCounSume(coupConsumeReqDTO, example);
		List<CoupConsume> coupConsumeBeans  = coupConsumeMapper.selectByExample((CoupConsumeCriteria) example);
		if(CollectionUtils.isEmpty(coupConsumeBeans)){
			return;
		}
		for (CoupConsume coupConsume : coupConsumeBeans) {
			if(CoupUtil.isCoupNotLong(coupConsume.getCoupDetailId())){
				CoupDetailReqDTO coupDetailBean = new CoupDetailReqDTO();
				coupDetailBean.setId(coupConsume.getCoupDetailId());
				coupDetailBean.setCoupId(coupConsume.getCoupId());
				coupDetailBean.setCoupNo(coupConsume.getCoupNo());
				coupDetailBean.setStaffId(staffId);
				coupDetailBean.setIfUse(CouponConstants.CoupDetail.IF_USE_1);
				coupDetailBean.setStatus(CouponConstants.CoupSys.status_1);
				
				//修改参数
				CoupDetail coupDetail = new CoupDetail();
				coupDetail.setIfUse(CouponConstants.CoupDetail.IF_USE_0);
				coupDetail.setUseTime(null);
				coupCreateInitSV.updateCoupDetail(coupDetailBean,coupDetail);
			}
		}
		// 查询初始化查询条件
		CoupConsume coupConsume = new CoupConsume();
		coupConsume.setStatus(CouponConstants.CoupSys.status_0);//消费的优惠券设置为无效
		coupCreateInitSV.updateCoupCounSume(coupConsumeReqDTO, coupConsume);
		
	}

	/**
	 * 
	 * TODO 优惠券校验. 
	 * Date:2015-12-21下午4:38:59  <br>
	 * @author huanghe5
	 * @see com.zengshi.ecp.general.order.interfaces.IOrderChkRSV#checkOrder(com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest)
	 */
	@Override
	public ROrdCartsChkResponse checkOrder(
			ROrdCartsCommRequest ordCartsCommRequest) throws BusinessException {
		
		ROrdCartsChkResponse ordCartsChkResponse = new ROrdCartsChkResponse();
		Map<Long, CoupCheckInfoRespDTO> coupIdskuIdMap = ordCartsCommRequest
				.getCoupIdskuIdMap();
		if(CollectionUtils.isEmpty(coupIdskuIdMap)){
			ordCartsChkResponse.setStatus(true);//订单域初始化
			return ordCartsChkResponse;
		}
		// 1.获取用户所选择的优惠券信息(集合)
		List<CoupCheckBeanRespDTO> coupCheckBean = new ArrayList<CoupCheckBeanRespDTO>();
		if (!CollectionUtils.isEmpty(ordCartsCommRequest.getCoupPlatfBean())) {
			coupCheckBean = ordCartsCommRequest.getCoupPlatfBean();
		}
		// 集合ListBean
		List<ROrdCartCommRequest> ordCartsList = ordCartsCommRequest
				.getOrdCartsCommList();
		List<CoupInfoRespDTO> coupInfoList = new ArrayList<CoupInfoRespDTO>();
		//解析List
		for (ROrdCartCommRequest rOrdCartCommRequest : ordCartsList) {
			if(StringUtil.isNotBlank(rOrdCartCommRequest.getCoupCode())){
				CoupInfoRespDTO coupInfoRespDTO= new CoupInfoRespDTO();
				CoupInfoReqDTO coupInfoReqDTO = new CoupInfoReqDTO();
				coupInfoReqDTO.setCoupCode(rOrdCartCommRequest.getCoupCode().toUpperCase());
				CoupInfoRespDTO coupInfo = coupInfoSV.queryCoupInfoByCoupCode(coupInfoReqDTO);
			    ObjectCopyUtil.copyObjValue(coupInfo, coupInfoRespDTO, null, false);
			    if(StringUtil.isEmpty(coupInfoRespDTO)){
			    	LogUtil.error(MODULE,	"优惠码不存在或者是已经过期了");
					String[] code = new String[1];
					code[0] = rOrdCartCommRequest.getCoupCode();
					throw new BusinessException("coupon.error.450041",code);
			    }
			    coupInfoList.add(coupInfoRespDTO);
			    CoupCheckParmReqDTO coupCheckParmReqDTO = new CoupCheckParmReqDTO();
				coupCheckParmReqDTO.setCoupId(coupInfoRespDTO.getId());
				coupCheckParmReqDTO.setRuleCode(coupInfoRespDTO.getUseRuleCode());
				coupCheckParmReqDTO.setCustLevelValue(rOrdCartCommRequest.getCustLevelValue());
				coupCheckParmReqDTO.setSource(rOrdCartCommRequest.getSource());
			    for (ROrdCartItemCommRequest rOrdCartItemCommRequest : rOrdCartCommRequest.getOrdCartItemCommList()) {
			    	try {
						// 3.1 把单品通过优惠券各个规则的校验
						CoupLimitCheckRespDTO coupLimitCheckBean = this
								.checkLimit(coupCheckParmReqDTO,coupInfoRespDTO,
										rOrdCartItemCommRequest);
						if (coupLimitCheckBean == null) {
							LogUtil.error(MODULE,
									"CoupLimitCheckRespDTO coupLimitCheckBean 为null");
							continue;
						}
						// 3.2对满减规则进行校验
						if (CouponConstants.CoupSys.judge_2
								.equals(coupLimitCheckBean.getSkuGroup())) {
							CoupCheckInfoRespDTO coupCheckInfoRespDTO = coupIdskuIdMap
									.get(coupInfoRespDTO.getId());
							
							List<CoupSkuRespDTO> coupSkuRespDTO = coupCheckInfoRespDTO.getCoupSkuRespDTO();
							boolean bol = coupFullLimitSV
									.checkCoupFullLimit(coupSkuRespDTO,
											coupInfoRespDTO
													.getId(),coupInfoRespDTO.getCoupValue());
							if (!bol) {
								LogUtil.error(
										MODULE,
										"优惠码进行满减价格校验时,不通过; 优惠券ID:"
												+ coupInfoRespDTO
														.getId()
												+ "商品信息:" + coupSkuRespDTO);
								String[] code = new String[1];
								code[0] = rOrdCartCommRequest.getCoupCode();
								throw new BusinessException(
										"coupon.error.450040", code);
							}
						}
					} catch (Exception e) {
						LogUtil.error(MODULE, "优惠码检验报错", e);
						String[] code = new String[1];
						code[0] = rOrdCartCommRequest.getCoupCode();
						throw new BusinessException("coupon.error.450040",
								code);
					}
				}
			}
			List<CoupCheckBeanRespDTO> coupCheckshopBean = rOrdCartCommRequest
					.getCoupCheckBean();
			if(CollectionUtils.isEmpty(coupCheckshopBean)||coupCheckshopBean.size()<1){
				ordCartsChkResponse.setStatus(true);//订单域初始化使用,强制设置为true,不然订单页面初始化会
				return ordCartsChkResponse;
			}
			coupCheckBean.addAll(coupCheckshopBean);
		}
		if (CollectionUtils.isEmpty(coupCheckBean)&&CollectionUtils.isEmpty(coupInfoList)) {
			LogUtil.error(MODULE,
					"List<CoupCheckBeanRespDTO> coupCheckBean 为null");
			ordCartsChkResponse.setStatus(true);//订单域初始化
			return ordCartsChkResponse; 
		}

		for (CoupCheckBeanRespDTO coupCheckBeanRespDTO : coupCheckBean) {
			List<CoupDetailRespDTO> coupDetails = coupCheckBeanRespDTO
					.getCoupDetails();
			CoupInfoRespDTO coupInfoRespDTO = new CoupInfoRespDTO();
			coupInfoRespDTO.setId(coupCheckBeanRespDTO.getCoupId());
			coupInfoRespDTO.setCheckLimit(CouponConstants.CoupSys.checkLimit_0);// 下单校验
			// 获取商品信息
			List<ROrdCartCommRequest> ordCartsCommList = ordCartsCommRequest
					.getOrdCartsCommList();
			if (CollectionUtils.isEmpty(ordCartsCommList)) {
				LogUtil.error(MODULE,
						"List<ROrdCartCommRequest> ordCartsCommList 为null");
				ordCartsChkResponse.setStatus(true);//订单域初始化
				return ordCartsChkResponse;
			}
			// 2校验用户所选择的优惠券是否过期
			for (CoupDetailRespDTO coupDetail : coupDetails) {

				// 查询用户所具有的有效优惠券信息
				List<CoupDetailRespDTO> CoupDetailbeans = this
						.queryCoupDetailList(ordCartsCommRequest.getStaffId(),
								null, coupCheckBeanRespDTO.getCoupId(),
								coupDetail.getCoupNo(), null);

				if (CollectionUtils.isEmpty(CoupDetailbeans)) {
					String[] code = new String[1];
					code[0] = coupDetail.getCoupName();
					LogUtil.error(MODULE,
							"优惠券编码CoupNo:" + coupDetail.getCoupNo() + "已经过期");
					
					throw new BusinessException("coupon.error.450014", code);
				}
				coupInfoRespDTO.setUseRuleCode(CoupDetailbeans.get(0)
						.getUseRuleCode());
				// 3.对各个单品以及优惠券进行校验
				for (ROrdCartCommRequest rOrdCartCommRequest : ordCartsCommList) {
					CoupCheckParmReqDTO coupCheckParmReqDTO = new CoupCheckParmReqDTO();
					coupCheckParmReqDTO.setCoupId(coupInfoRespDTO.getId());
					coupCheckParmReqDTO.setRuleCode(coupInfoRespDTO.getUseRuleCode());
					coupCheckParmReqDTO.setCustLevelValue(rOrdCartCommRequest.getCustLevelValue());
					coupCheckParmReqDTO.setSource(ordCartsCommRequest.getSource());
					List<ROrdCartItemCommRequest> ordCartItemCommList = rOrdCartCommRequest
							.getOrdCartItemCommList();
					for (ROrdCartItemCommRequest rOrdCartItemCommRequest : ordCartItemCommList) {
						try {
							// 3.1 把单品通过优惠券各个规则的校验
							CoupLimitCheckRespDTO coupLimitCheckBean = this
									.checkLimit(coupCheckParmReqDTO,coupInfoRespDTO,
											rOrdCartItemCommRequest);
							// 3.11 校验用户选择的同一类优惠券数量不能超过限制规则的数量
							if (coupLimitCheckBean == null) {
								LogUtil.error(MODULE,
										"CoupLimitCheckRespDTO coupLimitCheckBean 为null");
								continue;
							}
							if (coupLimitCheckBean.getOrdUseNum() > 1) {
								if (coupDetails.size() > coupLimitCheckBean
										.getOrdUseNum()) {
									LogUtil.error(
											MODULE,
											"用户使用优惠券数量大于单个订单对优惠券限制使用数量; 优惠券ID:"
													+ coupCheckBeanRespDTO
															.getCoupId()
													+ "优惠券编码:"
													+ coupDetail.getCoupNo()
													+ "商品信息:"
													+ coupLimitCheckBean);
									String[] code = new String[1];
									code[0] = coupDetail.getCoupName();
									throw new BusinessException(
											"coupon.error.450015", code);
								}
							}
							// 3.2对满减规则进行校验
							if (CouponConstants.CoupSys.judge_2
									.equals(coupLimitCheckBean.getSkuGroup())) {
								CoupCheckInfoRespDTO coupCheckInfoRespDTO = coupIdskuIdMap
										.get(coupCheckBeanRespDTO.getCoupId());
								
								List<CoupSkuRespDTO> coupSkuRespDTO = coupCheckInfoRespDTO
										.getCoupSkuRespDTO();
								boolean bol = coupFullLimitSV
										.checkCoupFullLimit(coupSkuRespDTO,
												coupCheckBeanRespDTO
														.getCoupId(),coupDetail.getCoupValue());
								if (!bol) {
									LogUtil.error(
											MODULE,
											"进行满减价格校验时,不通过; 优惠券ID:"
													+ coupCheckBeanRespDTO
															.getCoupId()
													+ "商品信息:" + coupSkuRespDTO);
									String[] code = new String[1];
									code[0] = coupDetail.getCoupName();
									throw new BusinessException(
											"coupon.error.450016", code);
								}
							}
						} catch (Exception e) {
							LogUtil.error(MODULE, "优惠券检验报错", e);
							String[] code = new String[1];
							code[0] = coupDetail.getCoupName();
							throw new BusinessException("coupon.error.450017",
									code);
						}
					}
				}
			}
		}
		ordCartsChkResponse.setStatus(true);
		return ordCartsChkResponse;
	}

	
	/**
	 * 同意退款扣减订单赠送的优惠劵(失效)
	 * @author huanghe5
	 * @param orderId
	 * @param staffId
	 * @throws BusinessException
	 */
	@Override
	public void editDeductionCoup(String orderId, Long staffId) throws BusinessException {
		// 查询初始化查询条件
		CoupPresentReqDTO coupPresentReqDTO = new CoupPresentReqDTO();
		coupPresentReqDTO.setOrderId(orderId);
		coupPresentReqDTO.setStaffId(staffId);
		List<CoupPresent>  coupPresentBeans= this.queryCoupPresent(coupPresentReqDTO);
		if(CollectionUtils.isEmpty(coupPresentBeans)){
			LogUtil.error(MODULE, "查询此用户赠送优惠券记录为空");
			return;
		}
		//将用户优惠券设置为无效
		for (CoupPresent coupPresent : coupPresentBeans) {
			//用于作为update的查询条件
			CoupDetailReqDTO coupDetailReqDTO = new CoupDetailReqDTO();
			coupDetailReqDTO.setId(coupPresent.getCoupDetailId());
			coupDetailReqDTO.setStaffId(staffId);
			coupDetailReqDTO.setIfUse(CouponConstants.CoupDetail.IF_USE_0);
			coupDetailReqDTO.setStatus(CouponConstants.CoupSys.status_2);
			//用于作为update的修改内容
			CoupDetail coupDetail = new CoupDetail();
			coupDetail.setStatus(CouponConstants.CoupSys.status_0);
			coupCreateInitSV.updateCoupDetail(coupDetailReqDTO, coupDetail);
		}
		//清除用户赠送记录
		CoupPresent coupPresent = new CoupPresent();
		coupPresent.setStatus(CouponConstants.CoupSys.status_0);
		coupCreateInitSV.updateCoupPresent(coupPresentReqDTO, coupPresent);
	}

	/**
	 * 订单赠送优惠券记录查询
	 * @author huanghe5
	 */
	@Override
	public List<CoupPresent> queryCoupPresent(CoupPresentReqDTO coupPresentReqDTO) throws BusinessException {
		CoupPresentCriteria example = new CoupPresentCriteria();
		coupPresentReqDTO.setStatus(CouponConstants.CoupSys.status_1);
		coupCreateInitSV.initCoupPresent(coupPresentReqDTO, example);
		
		return coupPresentMapper.selectByExample((CoupPresentCriteria) example);
	}

	/**
	 * 查询用户优惠券类型的剩余可用优惠券
	 * @author lisp
	 */
	@Override
	public CoupOrdNumBackRespDTO queryOrderCoup(CoupOrdBackReqDTO coupOrdBackReqDTO) throws BusinessException {
		
		//查询要解冻的优惠券
		CoupFreezeReqDTO coupFreezeReqDTO = new CoupFreezeReqDTO();
		coupFreezeReqDTO.setOrderId(coupOrdBackReqDTO.getOrderId());
		int freezrCount = 0;
		int count = 0;
		CoupFreezeCriteria exampleFreeze = new CoupFreezeCriteria();
		com.zengshi.ecp.coupon.dao.model.CoupFreezeCriteria.Criteria cr = exampleFreeze.createCriteria();
		if(StringUtil.isNotEmpty(coupOrdBackReqDTO.getOrderId())){
			cr.andOrderIdEqualTo(coupFreezeReqDTO.getOrderId());
		}
		cr.andStatusEqualTo(CouponConstants.CoupSys.status_1);
		String s = coupFreezeMapper.countByExample(exampleFreeze).toString();
		freezrCount = Integer.parseInt(s);
		//List<CoupFreezeRespDTO> coupFreezeRespDTOList = this.queryCoupFreeze(coupFreezeReqDTO);
		
		// 查询初始化查询条件
		CoupPresentReqDTO coupPresentReqDTO = new CoupPresentReqDTO();
		coupPresentReqDTO.setOrderId(coupOrdBackReqDTO.getOrderId());
		coupPresentReqDTO.setStaffId(coupOrdBackReqDTO.getStaffId());
		List<CoupPresent>  coupPresentBeans= this.queryCoupPresent(coupPresentReqDTO);
		count = coupPresentBeans.size();
		if (CollectionUtils.isEmpty(coupPresentBeans)) {
			LogUtil.error(MODULE, "查询此用户赠送优惠券记录为空");
			return null;
		}
		CoupOrdNumBackRespDTO bean = new CoupOrdNumBackRespDTO();
		//for(int i=0;i<count-freezrCount;i++){
		//CoupPresent coupPresent
		List<OrdNumRespDTO> coupNumBeans = new ArrayList<OrdNumRespDTO>();
		List<Long> deWeightList = new ArrayList<Long>();
		for(int i=0;i<count-freezrCount;i++){
		CoupPresent coupPresent = coupPresentBeans.get(i);
		//for (CoupPresent coupPresent : coupPresentBeans) {
			if(!deWeightList.contains(coupPresent.getCoupId())){
				deWeightList.add(coupPresent.getCoupId());
				CoupPresentCriteria example = new CoupPresentCriteria();
				//获取此订单的此类优惠券赠送的数量
				CoupPresentReqDTO coupPresentReq = new CoupPresentReqDTO();
				coupPresentReq.setOrderId(coupOrdBackReqDTO.getOrderId());
				coupPresentReq.setStaffId(coupOrdBackReqDTO.getStaffId());
				coupPresentReq.setCoupId(coupPresent.getCoupId());
				coupPresentReq.setStatus(CouponConstants.CoupSys.status_1);
				coupCreateInitSV.initCoupPresent(coupPresentReq, example);
				Long a = coupPresentMapper.countByExample(example).longValue();
				//获取用户还有剩余此类优惠券的数量
				CoupDetailCriteria detailExample = new CoupDetailCriteria();
				CoupDetailReqDTO coupDetailReqDTO = new CoupDetailReqDTO();
				coupDetailReqDTO.setStatus(CouponConstants.CoupSys.status_1);
				coupDetailReqDTO.setCoupId(coupPresent.getCoupId());
				coupDetailReqDTO.setStaffId(coupOrdBackReqDTO.getStaffId());
				coupDetailReqDTO.setIfUse(CouponConstants.CoupDetail.IF_USE_0);
				coupDetailReqDTO.setInactiveTime(DateUtil.getSysDate());
				coupDetailReqDTO.setActiveTime(DateUtil.getSysDate());
				coupCreateInitSV.initCoupDetail(coupDetailReqDTO, detailExample);
				Long detailSize =  coupDetailMapper.countByExample(detailExample).longValue();
				
				OrdNumRespDTO ordNumRespDTO = new OrdNumRespDTO();
				if(detailSize==null){
					ordNumRespDTO.setCoupBackNum(0l);
				}else{
					ordNumRespDTO.setCoupBackNum(detailSize);
				}
				if(a==null){
					ordNumRespDTO.setCoupPresentNum(0l);
				}else{
					ordNumRespDTO.setCoupPresentNum(a);
				}
				ordNumRespDTO.setCoupId(coupPresent.getCoupId());
				
				CoupInfoReqDTO coupInfoReqDTO = new CoupInfoReqDTO();
				coupInfoReqDTO.setId(coupPresent.getCoupId());
				CoupInfoRespDTO coupInfo= coupInfoSV.queryCoupInfoById(coupInfoReqDTO);
				ordNumRespDTO.setCoupName(coupInfo.getCoupName());
				coupNumBeans.add(ordNumRespDTO);
				
			}
		}
		if(CollectionUtils.isEmpty(coupNumBeans)){
			LogUtil.error(MODULE, "查询此优惠券无任优惠券信息......");
			return null;
		}
		bean.setCoupNumBeans(coupNumBeans);
		bean.setOrderId(coupOrdBackReqDTO.getOrderId());
		bean.setStaffId(coupOrdBackReqDTO.getStaffId());
		
		return bean;
	}

	
	/**
	 * 查询用户优惠券类型的剩余可用优惠券
	 * @author huanghe5
	 */
	@Override
	public CoupOrdNumBackRespDTO queryStaffCoup(CoupOrdBackReqDTO coupOrdBackReqDTO) throws BusinessException {
		
		// 查询初始化查询条件
		CoupPresentReqDTO coupPresentReqDTO = new CoupPresentReqDTO();
		coupPresentReqDTO.setOrderId(coupOrdBackReqDTO.getOrderId());
		coupPresentReqDTO.setStaffId(coupOrdBackReqDTO.getStaffId());
		List<CoupPresent>  coupPresentBeans= this.queryCoupPresent(coupPresentReqDTO);
		if (CollectionUtils.isEmpty(coupPresentBeans)) {
			LogUtil.error(MODULE, "查询此用户赠送优惠券记录为空");
			return null;
		}
		CoupOrdNumBackRespDTO bean = new CoupOrdNumBackRespDTO();
		
		List<OrdNumRespDTO> coupNumBeans = new ArrayList<OrdNumRespDTO>();
		List<Long> deWeightList = new ArrayList<Long>();
		
		
		
		int freezrCount = 0;
		int count = 0;
		CoupFreezeCriteria exampleFreeze = new CoupFreezeCriteria();
		com.zengshi.ecp.coupon.dao.model.CoupFreezeCriteria.Criteria cr = exampleFreeze.createCriteria();
		if(StringUtil.isNotEmpty(coupOrdBackReqDTO.getOrderId())){
			cr.andOrderIdEqualTo(coupOrdBackReqDTO.getOrderId());
		}
		cr.andStatusEqualTo(CouponConstants.CoupSys.status_1);
		String s = coupFreezeMapper.countByExample(exampleFreeze).toString();
		freezrCount = Integer.parseInt(s);
		count = coupPresentBeans.size();
		CoupPresent coupPresent;
		for(int i =0;i<count-freezrCount;i++){
		//for (CoupPresent coupPresent : coupPresentBeans) {
			 coupPresent = coupPresentBeans.get(i);
			if(!deWeightList.contains(coupPresent.getCoupId())){
				deWeightList.add(coupPresent.getCoupId());
				CoupPresentCriteria example = new CoupPresentCriteria();
				//获取此订单的此类优惠券赠送的数量
				CoupPresentReqDTO coupPresentReq = new CoupPresentReqDTO();
				coupPresentReq.setOrderId(coupOrdBackReqDTO.getOrderId());
				coupPresentReq.setStaffId(coupOrdBackReqDTO.getStaffId());
				coupPresentReq.setCoupId(coupPresent.getCoupId());
				coupPresentReq.setStatus(CouponConstants.CoupSys.status_1);
				coupCreateInitSV.initCoupPresent(coupPresentReq, example);
				Long a = coupPresentMapper.countByExample(example).longValue();
				//获取用户还有剩余此类优惠券的数量
				CoupDetailCriteria detailExample = new CoupDetailCriteria();
				CoupDetailReqDTO coupDetailReqDTO = new CoupDetailReqDTO();
				coupDetailReqDTO.setStatus(CouponConstants.CoupSys.status_1);
				coupDetailReqDTO.setCoupId(coupPresent.getCoupId());
				coupDetailReqDTO.setStaffId(coupOrdBackReqDTO.getStaffId());
				coupDetailReqDTO.setIfUse(CouponConstants.CoupDetail.IF_USE_0);
				coupDetailReqDTO.setInactiveTime(DateUtil.getSysDate());
				coupDetailReqDTO.setActiveTime(DateUtil.getSysDate());
				coupCreateInitSV.initCoupDetail(coupDetailReqDTO, detailExample);
				Long detailSize =  coupDetailMapper.countByExample(detailExample).longValue();
				
				OrdNumRespDTO ordNumRespDTO = new OrdNumRespDTO();
				if(detailSize==null){
					ordNumRespDTO.setCoupBackNum(0l);
				}else{
					ordNumRespDTO.setCoupBackNum(detailSize);
				}
				if(a==null){
					ordNumRespDTO.setCoupPresentNum(0l);
				}else{
					ordNumRespDTO.setCoupPresentNum(a);
				}
				ordNumRespDTO.setCoupId(coupPresent.getCoupId());
				
				CoupInfoReqDTO coupInfoReqDTO = new CoupInfoReqDTO();
				coupInfoReqDTO.setId(coupPresent.getCoupId());
				CoupInfoRespDTO coupInfo= coupInfoSV.queryCoupInfoById(coupInfoReqDTO);
				ordNumRespDTO.setCoupName(coupInfo.getCoupName());
				coupNumBeans.add(ordNumRespDTO);
				
			}
		}
		if(CollectionUtils.isEmpty(coupNumBeans)){
			LogUtil.error(MODULE, "查询此优惠券无任优惠券信息......");
			return null;
		}
		bean.setCoupNumBeans(coupNumBeans);
		bean.setOrderId(coupOrdBackReqDTO.getOrderId());
		bean.setStaffId(coupOrdBackReqDTO.getStaffId());
		
		return bean;
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
	public CoupCheckBeanRespDTO editBlockCoup(CoupOrdBackRespDTO coupOrdBackRespDTO) throws BusinessException {
		
		CoupPresentReqDTO coupPresentReqDTO = new CoupPresentReqDTO();
		coupPresentReqDTO.setOrderId(coupOrdBackRespDTO.getOrderId());
		coupPresentReqDTO.setStaffId(coupOrdBackRespDTO.getStaffId());
		List<CoupPresent>  coupPresentBeans= this.queryCoupPresent(coupPresentReqDTO);
		if(CollectionUtils.isEmpty(coupPresentBeans)){
			LogUtil.error(MODULE, "查询此用户赠送优惠券记录为空");
			return null;
		}
		List<CoupDetailRespDTO> coupDetails = new ArrayList<CoupDetailRespDTO>();
		for (CoupPresent coupPresent : coupPresentBeans) {
			LogUtil.info(MODULE, "需要冻结的优惠券信息.：" + coupPresent+"需要冻结的数量: "+coupPresentBeans.size());
			if(!CouponConstants.CoupSys.status_1.equalsIgnoreCase(coupPresent.getStatus())){
				LogUtil.error(MODULE, "此优惠券已失效，无需在失效");
				continue;
			}
			//把要冻结的优惠券保存到优惠券冻结表
			CoupFreeze coupFreeze = new CoupFreeze();
			ObjectCopyUtil.copyObjValue(coupPresent, coupFreeze, null, false);
			//设置Id，当前最大的Id加1
			coupFreeze.setId(seq_coup_freeze_id.nextValue());
			//设置退货申请的Id
			coupFreeze.setApplyId(coupOrdBackRespDTO.getapplyId());
			coupFreeze.setFreezeType("1");
			//将要冻结的优惠券插入冻结信息保存表
			coupFreezeMapper.insert(coupFreeze);
		
			//封装参数，确订需要变更的记录是哪一条
			CoupDetailReqDTO coupDetailBean = new CoupDetailReqDTO();
			coupDetailBean.setId(coupPresent.getCoupDetailId());
			coupDetailBean.setStaffId(coupOrdBackRespDTO.getStaffId());
			coupDetailBean.setIfUse(CouponConstants.CoupDetail.IF_USE_0);
			coupDetailBean.setStatus(CouponConstants.CoupSys.status_1);
			//封装参数，要边更的属性和对应的值
			CoupDetail coupDetail = new CoupDetail();
			coupDetail.setStatus(CouponConstants.CoupSys.status_2);//冻结
			coupCreateInitSV.updateCoupDetail(coupDetailBean, coupDetail);
			LogUtil.info(MODULE, "需要冻结的优惠券信息.：" + coupDetailBean+"此优惠券冻结结束");
			
			//封装参数，得到对应的优惠券的详细信息
			CoupDetailReqDTO coupDetailReqDTO = new CoupDetailReqDTO();
			coupDetailReqDTO = new CoupDetailReqDTO();
			coupDetailReqDTO.setId(coupPresent.getCoupDetailId());
			/*coupDetailReqDTO.setSiteId(coupOrdBackRespDTO.getSiteId());
			coupDetailReqDTO.setStaffId(coupOrdBackRespDTO.getStaffId());
			coupDetailReqDTO.setIfUse(CouponConstants.CoupDetail.IF_USE_0);
			coupDetailReqDTO.setStatus(CouponConstants.CoupSys.status_2);*/
			CoupDetailRespDTO coupDetailRespDTO = new CoupDetailRespDTO();
			coupDetailRespDTO = this.queryCoupDetailById(coupDetailReqDTO);
			//将得到的优惠的详细信息保存到List中，下面将List返回给调用者
			coupDetails.add(coupDetailRespDTO);
		}
		
		CoupCheckBeanRespDTO coupCheckBeanRespDTO = new CoupCheckBeanRespDTO();
		//将优惠券的详细信息返回给调用者
		coupCheckBeanRespDTO.setCoupDetails(coupDetails);
		LogUtil.info(MODULE, "优惠券冻结结束返回值为.：" + coupCheckBeanRespDTO);
		return coupCheckBeanRespDTO;
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
	public void editLoseBlackCoup(CoupOrdBackRespDTO coupOrdBackRespDTO)
			throws BusinessException {
		//查询要解冻的优惠券
		CoupFreezeReqDTO coupFreezeReqDTO = new CoupFreezeReqDTO();
		coupFreezeReqDTO.setApplyId(coupOrdBackRespDTO.getapplyId());
		 List<CoupFreezeRespDTO> coupFreezeRespDTOList = this.queryCoupFreeze(coupFreezeReqDTO);
		//List<OrdBackNumRespDTO> coupNumBeans = coupOrdBackRespDTO.getCoupNumBeans();
		for (CoupFreezeRespDTO coupFreezeRespDTO : coupFreezeRespDTOList) {
				CoupDetailReqDTO coupDetailBean = new CoupDetailReqDTO();
				coupDetailBean.setId(coupFreezeRespDTO.getCoupDetailId());
				coupDetailBean.setIfUse(CouponConstants.CoupDetail.IF_USE_0);//未使用
				coupDetailBean.setStatus(CouponConstants.CoupSys.status_2);//冻结
				coupDetailBean.setStaffId(coupOrdBackRespDTO.getStaffId());
				CoupDetail coupDetail = new CoupDetail();
				coupDetail.setStatus(CouponConstants.CoupSys.status_0);//失效
				coupCreateInitSV.updateCoupDetail(coupDetailBean, coupDetail);
				
				//将冻结表的优惠券更新成无效的记录
				
				//封装参数，确定变更的记录
				CoupFreezeCriteria example = new CoupFreezeCriteria();
				this.initCoupFreeze(example, coupFreezeRespDTO);
				//封装变更的属性对应的值
				CoupFreeze coupFreeze =  new  CoupFreeze();
				if (CoupUtil.isCoupNotLong(coupFreezeReqDTO.getStaff().getId())) {
					coupFreeze.setUpdateStaff(coupFreezeReqDTO.getStaff().getId());
				}
				coupFreeze.setStatus(CouponConstants.CoupSys.status_0);
				coupFreeze.setUpdateTime(DateUtil.getSysDate());
				coupFreezeMapper.updateByExampleSelective(coupFreeze, example);
			//ordBackNumRespDTO.getCoupNum():失效的优惠券数量
			
//			CoupDetailReqDTO coupDetailBean = new CoupDetailReqDTO();
//			coupDetailBean.setCoupId(ordBackNumRespDTO.getCoupId());
//			coupDetailBean.setIfUse(CouponConstants.CoupDetail.IF_USE_0);
//			coupDetailBean.setStatus(CouponConstants.CoupSys.status_2);//冻结
//			coupDetailBean.setStaffId(coupOrdBackRespDTO.getStaffId());
//			CoupDetail coupDetail = new CoupDetail();
//			coupDetail.setStatus(CouponConstants.CoupSys.status_0);//失效
//			coupCreateInitSV.updateCoupDetail(coupDetailBean, coupDetail);
			
			
		}
	}

	
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
	public void initCoupFreeze(CoupFreezeCriteria example,
			CoupFreezeRespDTO coupFreezeRespDTO) throws BusinessException {
		
		if (coupFreezeRespDTO == null) {
			return;
		}
		CoupFreezeCriteria.Criteria cr = example.createCriteria();
		if (CoupUtil.isCoupNotLong(coupFreezeRespDTO.getId())) {
			cr.andIdEqualTo(coupFreezeRespDTO.getId());// 冻结表ID
		}
		if(StringUtil.isNotEmpty(coupFreezeRespDTO.getApplyId())){// 退货申请ID
			cr.andApplyIdEqualTo(coupFreezeRespDTO.getApplyId());
		}
		if(StringUtil.isNotEmpty(coupFreezeRespDTO.getOrderId())){// 订单ID
			cr.andOrderIdEqualTo(coupFreezeRespDTO.getOrderId());
		}
			cr.andStatusEqualTo(CouponConstants.CoupSys.status_1);//状态有效
	}
	/**
	 * 优惠券冻结失败补偿接口
	 * @author huanghe5
	 * @param coupCheckBeanRespDTO
	 * @throws BusinessException
	 * @since JDK 1.7
	 */
	@Override
	public void editRestorePayCoup(CoupCheckBeanRespDTO coupCheckBeanRespDTO)
			throws BusinessException {
			//查询要解冻的优惠券
			CoupFreezeReqDTO coupFreezeReqDTO = new CoupFreezeReqDTO();
			coupFreezeReqDTO.setApplyId(coupCheckBeanRespDTO.getApplyId());
			List<CoupFreezeRespDTO> coupFreezeRespDTOList = this.queryCoupFreeze(coupFreezeReqDTO);
			for (CoupFreezeRespDTO coupFreezeRespDTO : coupFreezeRespDTOList) {
				CoupDetailReqDTO coupDetailBean = new CoupDetailReqDTO();
				coupDetailBean.setId(coupFreezeRespDTO.getCoupDetailId());
				coupDetailBean.setIfUse(CouponConstants.CoupDetail.IF_USE_0);
				coupDetailBean.setStatus(CouponConstants.CoupSys.status_2);//冻结
				//coupDetailBean.setStaffId(coupCheckBeanRespDTO.getStaffId());
				CoupDetail coupDetail = new CoupDetail();
				coupDetail.setStatus(CouponConstants.CoupSys.status_1);//生效
				coupCreateInitSV.updateCoupDetail(coupDetailBean, coupDetail);
				
				//将冻结表的优惠券更新成无效的记录
				
				//封装参数，确定变更的记录
				CoupFreezeCriteria example = new CoupFreezeCriteria();
				this.initCoupFreeze(example, coupFreezeRespDTO);
				//封装变更的属性对应的值
				CoupFreeze coupFreeze =  new  CoupFreeze();
				if (CoupUtil.isCoupNotLong(coupFreezeReqDTO.getStaff().getId())) {
					coupFreeze.setUpdateStaff(coupFreezeReqDTO.getStaff().getId());
				}
				coupFreeze.setStatus(CouponConstants.CoupSys.status_0);
				coupFreeze.setUpdateTime(DateUtil.getSysDate());
				coupFreezeMapper.updateByExampleSelective(coupFreeze, example);
		}
		
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
		List<ROrdCartCommRequest> ordCartsCommList = ordCartsCommRequest.getOrdCartsCommList();
		for (ROrdCartCommRequest rOrdCartCommRequest : ordCartsCommList) {
			this.deleteOrdCoup(rOrdCartCommRequest.getOrderId(), rOrdCartCommRequest.getStaffId(),ordCartsCommRequest.getStaff().getId());
		}
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
		// 被动领取
		List<CouponDetailPreReqDTO> couponDetailPreBeans = couponPresentReqDTO
				.getCouponDetailPreBeans();
		for (CouponDetailPreReqDTO couponDetailPreReqDTO : couponDetailPreBeans) {
			CoupCallBackReqDTO coupCallBackReqDTO = new CoupCallBackReqDTO();
			if(StringUtil.isNotBlank(couponPresentReqDTO.getOrderId())){
				coupCallBackReqDTO.setOrderId(couponPresentReqDTO.getOrderId());
			}else{
				coupCallBackReqDTO.setOrderId("0");
			}
			coupCallBackReqDTO.setCoupId(couponDetailPreReqDTO.getCoupId());
			coupCallBackReqDTO.setStaffId(couponPresentReqDTO.getStaffId());
			coupCallBackReqDTO
					.setCoupSource(CouponConstants.CoupDetail.COUP_SOURCE_20);
			coupCallBackReqDTO.setCoupSum(couponDetailPreReqDTO.getCoupSum());
			if (StringUtil.isNotBlank(coupCallBackReqDTO.getCustLevel())) {
				coupCallBackReqDTO.setCustLevel(coupCallBackReqDTO
						.getCustLevel());
			}
			this.saveCoupGain(coupCallBackReqDTO);
		}
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
		
		CoupDetailCriteria example = new CoupDetailCriteria();
		CoupDetailReqDTO coupDetail = new CoupDetailReqDTO();
		coupDetail.setStatus(CouponConstants.CoupSys.status_1);
		coupDetail.setIfUse(CouponConstants.CoupDetail.IF_USE_0);//未使用
		coupDetail.setOpeType(CouponConstants.CoupDetail.opeType_0);//过期 
		coupDetail.setInactiveTime(DateUtil.getSysDate());
		coupCreateInitSV.initCoupDetail(coupDetail, example);
		List<CoupDetail> bean = coupDetailMapper.selectByExample(example);
		if(CollectionUtils.isEmpty(bean)){
			return ;
		}
		for (CoupDetail coupDetail2 : bean) {
			CoupDetailReqDTO coupDetailReqDTO = new CoupDetailReqDTO();
			coupDetailReqDTO.setId(coupDetail2.getId());
			coupDetailReqDTO.setIfUse(CouponConstants.CoupDetail.IF_USE_0);//未使用
			coupDetailReqDTO.setStatus(CouponConstants.CoupSys.status_1);
			CoupDetail coupDetailBean = new CoupDetail();
			coupDetailBean.setStatus(CouponConstants.CoupSys.status_0);
			coupCreateInitSV.updateCoupDetail(coupDetailReqDTO, coupDetailBean);
		}
	}

	/**
	 * queryCoupInfoById:根据ID优惠券明细查询. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupInfoReqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@Override
	public CoupDetailRespDTO queryCoupDetailById(CoupDetailReqDTO coupDetailBean)
			throws BusinessException {
		
    	return coupDetailCheckDTOConverter.convert(coupDetailMapper.selectByPrimaryKey(coupDetailBean.getId()));

	}
	
	/**
	 * queryCoupFreeze:根据ApplyID,冻结优惠券明细查询. <br/> 
	 * 
	 * @author lisp
	 * @param coupFreezeReqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@Override
	public List<CoupFreezeRespDTO> queryCoupFreeze(CoupFreezeReqDTO coupFreezeReqDTO)
			throws BusinessException {
		// 查询初始化查询条件
		CoupFreezeCriteria example = new CoupFreezeCriteria();
		com.zengshi.ecp.coupon.dao.model.CoupFreezeCriteria.Criteria cr = example
				.createCriteria();
		if(StringUtil.isNotEmpty(coupFreezeReqDTO.getApplyId())){
			cr.andApplyIdEqualTo(coupFreezeReqDTO.getApplyId());
		}
		List<CoupFreeze> coupFreezeList = coupFreezeMapper.selectByExample(example);
		List<CoupFreezeRespDTO> coupFreezeRespDTOList = new ArrayList<CoupFreezeRespDTO>();
		for (CoupFreeze coupFreeze : coupFreezeList) {
			CoupFreezeRespDTO coupFreezeRespDTO =new  CoupFreezeRespDTO();
			coupFreezeRespDTOConverter.convert(coupFreeze, coupFreezeRespDTO);
			coupFreezeRespDTOList.add(coupFreezeRespDTO);
		}
		return coupFreezeRespDTOList;

	}

}

