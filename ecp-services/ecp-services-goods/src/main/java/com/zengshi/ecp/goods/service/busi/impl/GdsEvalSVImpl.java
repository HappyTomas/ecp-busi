/** 
 * Project Name:ecp-services-goods 
 * File Name:GdsEvalSVImpl.java 
 * Package Name:com.zengshi.ecp.goods.service.busi.impl 
 * Date:2015年8月24日上午10:26:46 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */
package com.zengshi.ecp.goods.service.busi.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.StringUtils;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsEvalMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsEvalShopIdxMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsEvalStaffIdxMapper;
import com.zengshi.ecp.goods.dao.model.GdsEval;
import com.zengshi.ecp.goods.dao.model.GdsEvalCriteria;
import com.zengshi.ecp.goods.dao.model.GdsEvalCriteria.Criteria;
import com.zengshi.ecp.goods.dao.model.GdsEvalShopIdx;
import com.zengshi.ecp.goods.dao.model.GdsEvalShopIdxCriteria;
import com.zengshi.ecp.goods.dao.model.GdsEvalStaffIdx;
import com.zengshi.ecp.goods.dao.model.GdsEvalStaffIdxCriteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalReplyReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalReplyRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalRespDTO;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsEvalReplySV;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsEvalSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfoidx.IGdsIDXSV;
import com.zengshi.ecp.goods.service.common.impl.AbstractSVImpl;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.ibm.icu.math.BigDecimal;
import com.ibm.icu.util.Calendar;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月24日上午10:26:46 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version
 * @since JDK 1.6
 */
public class GdsEvalSVImpl extends AbstractSVImpl implements IGdsEvalSV {
	@Resource(name = "seq_gds_eval")
	private PaasSequence seqGdsEval;
	@Resource
	private GdsEvalMapper gdsEvalMapper;
	@Resource
	private IGdsIDXSV gdsIDXSV;
	@Resource
	private IGdsEvalReplySV gdsEvalReplySV;
	@Resource
	private GdsEvalShopIdxMapper gdsEvalShopIdxMapper;
	@Resource
	private GdsEvalStaffIdxMapper gdsEvalStaffIdxMapper;

	private static final String DEFAULT_ORDER_BY = "EVALUATION_TIME DESC";

	// 审核状态列表.
	private static final List<String> AUDIT_STATUS = new ArrayList<String>();
	static {
		// 审核不通过.
		AUDIT_STATUS.add(GdsConstants.Commons.STATUS_AUDIT_NOT_THROUGH);
		// 审核通过.
		AUDIT_STATUS.add(GdsConstants.Commons.STATUS_VALID);
	}

	/**
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsEvalSV#addGdsEval(com.zengshi.ecp.goods.dao.model.GdsEval)
	 */
	@Override
	public GdsEvalRespDTO addGdsEval(GdsEvalReqDTO dto) throws BusinessException {
		GdsEval record = new GdsEval();
		ObjectCopyUtil.copyObjValue(dto, record, null, false);

		record.setId(seqGdsEval.nextValue());

		// 需要审核 ,则设置状态为待审核状态.
		if (isAuditSwitchOpen()) {
			record.setStatus(GdsConstants.Commons.STATUS_PENDING_AUDIT);
		} else {
			if (!StringUtils.hasText(record.getStatus())) {
				record.setStatus(GdsConstants.Commons.STATUS_VALID);
			}
		}
		record.setIsReply(GdsConstants.GdsEvalReply.IS_REPLY_0);
		preInsert(dto, record);

		if (null == record.getStaffId()) {
			record.setStaffId(record.getCreateStaff());
		}

		if (null == record.getEvaluationTime()) {
			record.setEvaluationTime(record.getCreateTime());
		}
		gdsEvalMapper.insert(record);
		gdsIDXSV.createGdsEvalIdx(record);
		return GdsUtils.doObjConvert(record, GdsEvalRespDTO.class, null, null);
	}

	/**
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsEvalSV#queryGdsEvalRespDTOPaging(com.zengshi.ecp.goods.dubbo.dto.GdsEvalReqDTO)
	 */
	@Override
	public PageResponseDTO<GdsEvalRespDTO> queryGdsEvalRespDTOPaging(GdsEvalReqDTO dto) throws BusinessException {
		GdsEvalShopIdxCriteria criteria = new GdsEvalShopIdxCriteria();
		criteria.setLimitClauseStart(dto.getStartRowIndex());
		criteria.setLimitClauseCount(dto.getPageSize());
		criteria.setOrderByClause("EVAL_ID DESC");
		GdsEvalShopIdxCriteria.Criteria c = criteria.createCriteria();
		if (StringUtil.isNotBlank(dto.getGdsName())) {
			c.andGdsNameLike("%" + dto.getGdsName() + "%");
		}
		if (null != dto.getGdsId()) {
			c.andGdsIdEqualTo(dto.getGdsId());
		}
		if (null != dto.getSkuId()) {
			c.andSkuIdEqualTo(dto.getSkuId());
		}
		if (null != dto.getShopId()) {
			c.andShopIdEqualTo(dto.getShopId());
		}
		if (null != dto.getStaffId()) {
			c.andStaffIdEqualTo(dto.getStaffId());
		}
		if (dto.getBeginTime() != null && dto.getEndTime() != null) {
			c.andEvaluationTimeBetween(dto.getBeginTime(), dto.getEndTime());
		} else {
			if (dto.getBeginTime() != null) {
				c.andEvaluationTimeGreaterThanOrEqualTo(dto.getBeginTime());
			}
			if (dto.getEndTime() != null) {
				c.andEvaluationTimeLessThanOrEqualTo(dto.getEndTime());
			}
		}
		if (StringUtil.isNotBlank(dto.getStatus())) {
			c.andStatusEqualTo(dto.getStatus());
		}
		return super.queryByPagination(dto, criteria, true, new GdsEvalRespDTOPaginationCallback());
	}

	@Override
	public Long countGoodEval(GdsEvalReqDTO dto) throws BusinessException {
		GdsEvalShopIdxCriteria criteria = new GdsEvalShopIdxCriteria();
		GdsEvalShopIdxCriteria.Criteria c = criteria.createCriteria();
		if (null != dto.getShopId()) {
			c.andShopIdEqualTo(dto.getShopId());
		}
		if (StringUtil.isNotBlank(dto.getStatus())) {
			c.andStatusEqualTo(dto.getStatus());
		}
		c.andScoreEqualTo((short) 5);
		return gdsEvalShopIdxMapper.countByExample(criteria);
	}

	@Override
	public Long countMiddleEval(GdsEvalReqDTO dto) throws BusinessException {
		GdsEvalShopIdxCriteria criteria = new GdsEvalShopIdxCriteria();
		GdsEvalShopIdxCriteria.Criteria c = criteria.createCriteria();
		if (null != dto.getShopId()) {
			c.andShopIdEqualTo(dto.getShopId());
		}
		if (StringUtil.isNotBlank(dto.getStatus())) {
			c.andStatusEqualTo(dto.getStatus());
		}
		c.andScoreBetween((short) 2, (short) 4);
		return gdsEvalShopIdxMapper.countByExample(criteria);
	}

	@Override
	public Long countBadEval(GdsEvalReqDTO dto) throws BusinessException {
		GdsEvalShopIdxCriteria criteria = new GdsEvalShopIdxCriteria();
		GdsEvalShopIdxCriteria.Criteria c = criteria.createCriteria();
		if (null != dto.getShopId()) {
			c.andShopIdEqualTo(dto.getShopId());
		}
		if (StringUtil.isNotBlank(dto.getStatus())) {
			c.andStatusEqualTo(dto.getStatus());
		}
		c.andScoreEqualTo((short) 1);
		return gdsEvalShopIdxMapper.countByExample(criteria);
	}

	/**
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsEvalSV#queryGdsEvalRespDTOPaging(com.zengshi.ecp.goods.dubbo.dto.GdsEvalReqDTO)
	 */
	@Override
	public PageResponseDTO<GdsEvalRespDTO> queryGdsEvalRespDTOPagingForGdsDetail(GdsEvalReqDTO dto)
			throws BusinessException {
		GdsEvalShopIdxCriteria criteria = new GdsEvalShopIdxCriteria();
		criteria.setLimitClauseStart(dto.getStartRowIndex());
		criteria.setLimitClauseCount(dto.getPageSize());
		criteria.setOrderByClause(DEFAULT_ORDER_BY);
		GdsEvalShopIdxCriteria.Criteria c = criteria.createCriteria();
		if (StringUtil.isNotBlank(dto.getGdsName())) {
			c.andGdsNameLike("%" + dto.getGdsName() + "%");
		}
		if (null != dto.getGdsId()) {
			c.andGdsIdEqualTo(dto.getGdsId());
		}
		if (null != dto.getSkuId()) {
			c.andSkuIdEqualTo(dto.getSkuId());
		}
		if (null != dto.getShopId()) {
			c.andShopIdEqualTo(dto.getShopId());
		}
		if (null != dto.getStaffId()) {
			c.andStaffIdEqualTo(dto.getStaffId());
		}
		if (dto.getBeginTime() != null && dto.getEndTime() != null) {
			Calendar cd = Calendar.getInstance();   
            cd.setTime(dto.getEndTime());   
			if (dto.getEndTime().getHours() == 0 && dto.getEndTime().getMinutes() == 0
					&& dto.getEndTime().getSeconds() == 0) {
				cd.add(Calendar.DATE, 1);
			}
			c.andEvaluationTimeBetween(dto.getBeginTime(), new Timestamp(cd.getTimeInMillis()));
		} else {
			if (dto.getBeginTime() != null) {
				c.andEvaluationTimeGreaterThanOrEqualTo(dto.getBeginTime());
			}
			if (dto.getEndTime() != null) {
				Calendar cd = Calendar.getInstance();   
	            cd.setTime(dto.getEndTime());   
				if (dto.getEndTime().getHours() == 0 && dto.getEndTime().getMinutes() == 0
						&& dto.getEndTime().getSeconds() == 0) {
					cd.add(Calendar.DATE, 1);
				}
				c.andEvaluationTimeLessThanOrEqualTo(new Timestamp(cd.getTimeInMillis()));
			}
		}

		if (null != dto.getScoreFrom()) {
			c.andScoreGreaterThanOrEqualTo(dto.getScoreFrom());
		}

		if (null != dto.getScoreTo()) {
			c.andScoreLessThanOrEqualTo(dto.getScoreTo());
		}

		if (null != dto.getScore()) {
			c.andScoreEqualTo(dto.getScore());
		}

		if (StringUtil.isNotBlank(dto.getStatus())) {
			c.andStatusEqualTo(dto.getStatus());
		}
		if (StringUtil.isNotBlank(dto.getOrderId())) {
			c.andOrderIdEqualTo(dto.getOrderId());
		}
		if (StringUtil.isNotBlank(dto.getOrderSubId())) {
			c.andOrderSubIdEqualTo(dto.getOrderSubId());
		}
		return super.queryByPagination(dto, criteria, true, new PaginationCallback<GdsEvalShopIdx, GdsEvalRespDTO>() {

			@Override
			public List<GdsEvalShopIdx> queryDB(BaseCriteria criteria) {
				return gdsEvalShopIdxMapper.selectByExample((GdsEvalShopIdxCriteria) criteria);
			}

			@Override
			public long queryTotal(BaseCriteria criteria) {
				return gdsEvalShopIdxMapper.countByExample((GdsEvalShopIdxCriteria) criteria);
			}

			@Override
			public GdsEvalRespDTO warpReturnObject(GdsEvalShopIdx t) {
				GdsEvalRespDTO dto = new GdsEvalRespDTO();
				ObjectCopyUtil.copyObjValue(t, dto, null, true);
				dto.setId(t.getEvalId());
				List<GdsEvalReplyRespDTO> evalReplyRespDTOs = new ArrayList<GdsEvalReplyRespDTO>();
				GdsEvalReplyReqDTO evalReplyReqDTO = new GdsEvalReplyReqDTO();
				evalReplyReqDTO.setEvalId(t.getEvalId());
				evalReplyReqDTO.setPageNo(0);
				evalReplyReqDTO.setPageSize(Integer.MAX_VALUE);
				evalReplyReqDTO.setReplyType(GdsConstants.GdsEvalReply.REPLY_TYPE_SELLER);
				PageResponseDTO<GdsEvalReplyRespDTO> pageResponseDTO = gdsEvalReplySV
						.queryGdsEvalReplyRespDTOPaging(evalReplyReqDTO);
				evalReplyRespDTOs = pageResponseDTO.getResult();
				dto.setEvalReplyRespDTOs(evalReplyRespDTOs);
				stuffFields(dto);
				return dto;
			}

		});
	}

	@Override
	public PageResponseDTO<GdsEvalRespDTO> queryGdsEvalRespDTOPagingByStaff(GdsEvalReqDTO dto)
			throws BusinessException {
		GdsEvalStaffIdxCriteria criteria = new GdsEvalStaffIdxCriteria();
		criteria.setLimitClauseStart(dto.getStartRowIndex());
		criteria.setLimitClauseCount(dto.getPageSize());
		criteria.setOrderByClause(DEFAULT_ORDER_BY);
		GdsEvalStaffIdxCriteria.Criteria c = criteria.createCriteria();
		if (StringUtil.isNotBlank(dto.getGdsName())) {
			c.andGdsNameLike("%" + dto.getGdsName() + "%");
		}
		if (StringUtil.isNotBlank(dto.getStatus())) {
			c.andStatusEqualTo(dto.getStatus());
		}
		if (null != dto.getStaffId()) {
			c.andStaffIdEqualTo(dto.getStaffId());
		}
		if (null != dto.getGdsId()) {
			c.andGdsIdEqualTo(dto.getGdsId());
		}
		if (null != dto.getSkuId()) {
			c.andSkuIdEqualTo(dto.getSkuId());
		}
		if (null != dto.getShopId()) {
			c.andShopIdEqualTo(dto.getShopId());
		}

		if (null != dto.getScoreFrom()) {
			c.andScoreGreaterThan(dto.getScoreFrom());
		}

		if (null != dto.getScoreTo()) {
			c.andScoreGreaterThan(dto.getScoreTo());
		}

		if (null != dto.getScore()) {
			c.andScoreEqualTo(dto.getScore());
		}

		if (dto.getBeginTime() != null && dto.getEndTime() != null) {
			c.andEvaluationTimeBetween(dto.getBeginTime(), dto.getEndTime());
		} else {
			if (dto.getBeginTime() != null) {
				c.andEvaluationTimeGreaterThanOrEqualTo(dto.getBeginTime());
			}
			if (dto.getEndTime() != null) {
				c.andEvaluationTimeLessThanOrEqualTo(dto.getEndTime());
			}
		}
		if (StringUtil.isNotBlank(dto.getOrderId())) {
			c.andOrderIdEqualTo(dto.getOrderId());
		}
		if (StringUtil.isNotBlank(dto.getOrderSubId())) {
			c.andOrderSubIdEqualTo(dto.getOrderSubId());
		}
		return super.queryByPagination(dto, criteria, true, new GdsEvalRespDTOPaginationByStaffCallback());
	}

	/**
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsEvalSV#deleteGdsEvalByPK(java.lang.Long,
	 *      java.lang.Long)
	 */
	@Override
	public int deleteGdsEvalByPK(GdsEvalReqDTO dto) throws BusinessException {

		GdsEval record = gdsEvalMapper.selectByPrimaryKey(dto.getId());
		if (record == null || GdsConstants.Commons.STATUS_INVALID.equals(record.getStatus())) {
			throw new BusinessException(GdsErrorConstants.GdsEval.ERROR_GOODS_EVAL_200551);
		}

		GdsEval eval = new GdsEval();

		preUpdate(dto, eval);

		eval.setStatus(GdsConstants.Commons.STATUS_INVALID);

		GdsEvalCriteria criteria = new GdsEvalCriteria();
		Criteria c = criteria.createCriteria();
		c.andIdEqualTo(dto.getId());
		c.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);

		// 删除相关索引
		gdsIDXSV.deleteGdsEvalIdx(record);
		// 标记删除评价回复表与评价主键相关联的回复。
		if (dto.isDelReply()) {
			GdsEvalReplyReqDTO replyReqDTO = new GdsEvalReplyReqDTO();
			replyReqDTO.setEvalId(dto.getId());
			gdsEvalReplySV.deleteReplyByEvalId(replyReqDTO);
		}
		int i = gdsEvalMapper.updateByExampleSelective(eval, criteria);
		if (i <= 0) {
			throw new BusinessException(GdsErrorConstants.GdsEval.ERROR_GOODS_EVAL_200553);
		}
		return i;
	}

	@Override
	public void batchAudit(GdsEvalReqDTO reqDTO) throws BusinessException {

		paramCheck(new Object[] { reqDTO }, new String[] { "reqDTO" });
		paramCheck(new Object[] { reqDTO.getAuditStatus(), reqDTO.getIds() },
				new String[] { "reqDTO.auditStatus", "reqDTO.ids" });
		if (!AUDIT_STATUS.contains(reqDTO.getAuditStatus())) {
			throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200098,
					new String[] { "reqDTO.auditStatus" });
		}
		GdsEvalCriteria criteria = new GdsEvalCriteria();
		Criteria c = criteria.createCriteria();
		// 设置条件为待审核状态目录.
		c.andStatusEqualTo(GdsConstants.Commons.STATUS_PENDING_AUDIT);
		// 设置待审核评价ID.
		if (reqDTO.getIds().size() == 1) {
			c.andIdEqualTo(reqDTO.getIds().get(0));
		} else {
			c.andIdIn(reqDTO.getIds());
		}
		GdsEval record = new GdsEval();
		preUpdate(reqDTO, record);
		record.setStatus(reqDTO.getAuditStatus());
		gdsEvalMapper.updateByExampleSelective(record, criteria);

		for (Long id : reqDTO.getIds()) {
			GdsEval eval = gdsEvalMapper.selectByPrimaryKey(id);
			gdsIDXSV.updateGdsEvalIdx(eval);
		}

	}

	@Override
	public List<GdsEvalReqDTO> batchAuditAccordanceWithOrder(GdsEvalReqDTO reqDTO) throws BusinessException {
		paramCheck(new Object[] { reqDTO }, new String[] { "reqDTO" });
		paramCheck(new Object[] { reqDTO.getAuditStatus(), reqDTO.getIds() },
				new String[] { "reqDTO.auditStatus", "reqDTO.ids" });
		if (!AUDIT_STATUS.contains(reqDTO.getAuditStatus())) {
			throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200098,
					new String[] { "reqDTO.auditStatus" });
		}

		List<GdsEval> evalLst = null;
		List<GdsEvalReqDTO> beConsistanceReq = new ArrayList<GdsEvalReqDTO>();
		// 推入缓存订单ID记录。
		List<String> orderMemo = new ArrayList<String>();

		try {
			GdsEvalCriteria gec = new GdsEvalCriteria();
			Criteria evalCriteria = gec.createCriteria();
			evalCriteria.andStatusEqualTo(GdsConstants.Commons.STATUS_PENDING_AUDIT);
			evalCriteria.andIdIn(reqDTO.getIds());
			// 获取批量审批通过评价信息。
			evalLst = gdsEvalMapper.selectByExample(gec);
			// 如果是批量审核通过，需要查询出哪些评价是需要与订单域进行同步并且发短信的评价。
			if (GdsUtils.isEqualsValid(reqDTO.getAuditStatus())) {

				// 根据该过滤结果调用短信发送服务。
				if (CollectionUtils.isNotEmpty(evalLst)) {
					for (GdsEval eval : evalLst) {
						if (isExistInCache(eval)) {
							continue;
						}
						// 将审核已通过评价订单ID暂存至缓存.
						pushOrderIdToCache(eval);
						orderMemo.add(eval.getOrderId());
						if (!isEvaluated(eval)) {
							GdsEvalReqDTO dto = new GdsEvalReqDTO();
							dto.setId(eval.getId());
							dto.setOrderId(eval.getOrderId());
							dto.setOrderSubId(eval.getOrderSubId());
							dto.setStaffId(eval.getStaffId());
							dto.setAuditStatus(reqDTO.getAuditStatus());
							beConsistanceReq.add(dto);
						}
					}
				}
			} else {
				if (CollectionUtils.isNotEmpty(evalLst)) {
					for (GdsEval eval : evalLst) {
						if (!isEvaluated(eval)) {
							GdsEvalReqDTO dto = new GdsEvalReqDTO();
							dto.setId(eval.getId());
							dto.setOrderId(eval.getOrderId());
							dto.setOrderSubId(eval.getOrderSubId());
							dto.setStaffId(eval.getStaffId());
							dto.setAuditStatus(reqDTO.getAuditStatus());
							beConsistanceReq.add(dto);
						}

					}
				}

			}
			GdsEvalCriteria criteria = new GdsEvalCriteria();
			Criteria c = criteria.createCriteria();
			// 设置条件为待审核状态.
			c.andStatusEqualTo(GdsConstants.Commons.STATUS_PENDING_AUDIT);
			// 设置待审核评价ID.
			if (reqDTO.getIds().size() == 1) {
				c.andIdEqualTo(reqDTO.getIds().get(0));
			} else {
				c.andIdIn(reqDTO.getIds());
			}
			GdsEval record = new GdsEval();
			preUpdate(reqDTO, record);
			record.setStatus(reqDTO.getAuditStatus());
			gdsEvalMapper.updateByExampleSelective(record, criteria);
			for (Long id : reqDTO.getIds()) {
				GdsEval eval = gdsEvalMapper.selectByPrimaryKey(id);
				gdsIDXSV.updateGdsEvalIdx(eval);
			}
		} catch (Exception e) {
			LogUtil.error(MODULE, "评价批量审核失败!", e);
			if (CollectionUtils.isNotEmpty(beConsistanceReq)) {
				for (GdsEvalReqDTO evalReqDTO : beConsistanceReq) {
					removeOrderIdFromCache(evalReqDTO.getOrderId());
				}
			}
			throw new BusinessException(GdsErrorConstants.GdsEval.ERROR_GOODS_EVAL_200558);
		}
		return beConsistanceReq;
	}

	@Override
	public boolean isGdsEvalInvokeSms() throws BusinessException {
		return SysCfgUtil.checkSysCfgValue(GdsConstants.SysCfgConstants.SWITCH_GDS_EVAL_INVOKE_SMS,
				GdsConstants.Commons.STATUS_VALID);
	}

	@Override
	public int editGdsEval(GdsEvalReqDTO reqDTO) throws BusinessException {
		GdsEval eval = gdsEvalMapper.selectByPrimaryKey(reqDTO.getId());
		if (null == eval || GdsConstants.Commons.STATUS_INVALID.equals(eval.getStatus())) {
			throw new BusinessException(GdsErrorConstants.GdsEval.ERROR_GOODS_EVAL_200551);
		}
		GdsEval record = new GdsEval();
		ObjectCopyUtil.copyObjValue(reqDTO, record, null, false);
		preUpdate(reqDTO, record);
		int i = gdsEvalMapper.updateByPrimaryKeySelective(record);
		if (i <= 0) {
			throw new BusinessException(GdsErrorConstants.GdsEval.ERROR_GOODS_EVAL_200550);
		}
		return i;
	}

	@Override
	public GdsEvalRespDTO queryGdsEvalByPK(Long id) throws BusinessException {
		GdsEval eval = gdsEvalMapper.selectByPrimaryKey(id);
		if (null != eval) {
			// eval.setContent(fetchTextContent(eval.getContent(),GdsErrorConstants.GdsEval.ERROR_GOODS_EVAL_200556));
			GdsEvalRespDTO dto = new GdsEvalRespDTO();
			ObjectCopyUtil.copyObjValue(eval, dto, null, false);
			stuffFields(dto);
			return dto;
		} else {
			return null;
		}
	}

	@Override
	public GdsEvalRespDTO queryGdsEvalByPK(GdsEvalReqDTO reqDTO) throws BusinessException {
		GdsEval eval = gdsEvalMapper.selectByPrimaryKey(reqDTO.getId());
		if (null != eval) {
			GdsEvalRespDTO dto = new GdsEvalRespDTO();
			ObjectCopyUtil.copyObjValue(eval, dto, null, false);
			stuffFields(dto);
			return dto;
		} else {
			return null;
		}
	}

	/*
	 * (店铺维度)商品评价分页查询回调类。 <br> Project Name:ecp-services-goods <br> Description:
	 * <br> Date:2015年8月17日下午3:36:33 <br> Copyright (c) 2015 Zengshi All Rights Reserved <br>
	 * 
	 * @author liyong7
	 * 
	 * @version GdsTypeSVImpl
	 * 
	 * @since JDK 1.6
	 */
	protected class GdsEvalRespDTOPaginationCallback extends PaginationCallback<GdsEvalShopIdx, GdsEvalRespDTO> {

		@Override
		public List<GdsEvalShopIdx> queryDB(BaseCriteria criteria) {
			return gdsEvalShopIdxMapper.selectByExample((GdsEvalShopIdxCriteria) criteria);
		}

		@Override
		public long queryTotal(BaseCriteria criteria) {
			return gdsEvalShopIdxMapper.countByExample((GdsEvalShopIdxCriteria) criteria);
		}

		@Override
		public GdsEvalRespDTO warpReturnObject(GdsEvalShopIdx t) {
			GdsEvalRespDTO dto = new GdsEvalRespDTO();
			ObjectCopyUtil.copyObjValue(t, dto, null, true);
			dto.setId(t.getEvalId());
			stuffFields(dto);
			return dto;
		}

		@Override
		public List<Comparator<GdsEvalShopIdx>> defineComparators() {
			List<Comparator<GdsEvalShopIdx>> lst = new ArrayList<Comparator<GdsEvalShopIdx>>();
			lst.add(new Comparator<GdsEvalShopIdx>() {

				@Override
				public int compare(GdsEvalShopIdx o1, GdsEvalShopIdx o2) {
					if (o1.getEvalId() > o2.getEvalId()) {
						return -1;
					} else if (o1.getEvalId().equals(o2.getEvalId())) {
						return 0;
					} else {
						return 1;
					}
				}

			});
			return lst;
		}

	}

	/**
	 * 商品评价分页查询回调类(用户维度查询)。 <br>
	 * Project Name:ecp-services-goods <br>
	 * Description: <br>
	 * Date:2015年8月17日下午3:36:33 <br>
	 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
	 * 
	 * @author liyong7
	 * @version GdsTypeSVImpl
	 * @since JDK 1.6
	 */
	protected class GdsEvalRespDTOPaginationByStaffCallback
			extends PaginationCallback<GdsEvalStaffIdx, GdsEvalRespDTO> {

		@Override
		public List<GdsEvalStaffIdx> queryDB(BaseCriteria criteria) {
			return gdsEvalStaffIdxMapper.selectByExample((GdsEvalStaffIdxCriteria) criteria);
		}

		@Override
		public long queryTotal(BaseCriteria criteria) {
			return gdsEvalStaffIdxMapper.countByExample((GdsEvalStaffIdxCriteria) criteria);
		}

		@Override
		public GdsEvalRespDTO warpReturnObject(GdsEvalStaffIdx t) {
			GdsEvalRespDTO dto = new GdsEvalRespDTO();
			ObjectCopyUtil.copyObjValue(t, dto, null, true);
			dto.setId(t.getEvalId());
			stuffFields(dto);
			return dto;
		}

	}

	@Override
	public boolean isAuditSwitchOpen() {
		return SysCfgUtil.checkSysCfgValue(GdsConstants.SysCfgConstants.SWITCH_GDS_EVAL_AUDIT,
				GdsConstants.Commons.STATUS_VALID);
	}

	@Override
	public void updateIsReplyByPK(GdsEvalReqDTO reqDTO) throws BusinessException {
		paramCheck(new Object[] { reqDTO }, new String[] { "reqDTO" });
		paramCheck(new Object[] { reqDTO.getId(), reqDTO.getIsReply(), reqDTO.getStaff() },
				new String[] { "reqDTO.id", "reqDTO.isReply", "reqDTO.staff" });
		GdsEval record = new GdsEval();
		record.setIsReply(reqDTO.getIsReply());
		preUpdate(reqDTO, record);

		GdsEvalCriteria criteria = new GdsEvalCriteria();
		Criteria c = criteria.createCriteria();
		c.andIdEqualTo(reqDTO.getId());

		gdsEvalMapper.updateByExampleSelective(record, criteria);
	}

	@Override
	public boolean queryHaveEval(GdsEvalReqDTO reqDTO) throws BusinessException {
		paramCheck(new Object[] { reqDTO }, new String[] { "reqDTO" });
		paramCheck(
				new Object[] { reqDTO.getOrderId() /*
													 * , reqDTO.getOrderSubId()
													 */
				}, new String[] { "reqDTO.orderId"/*
													 * , "reqDTO.orderSubId"
													 */
				});
		GdsEvalCriteria criteria = new GdsEvalCriteria();
		Criteria c = criteria.createCriteria();
		c.andOrderIdEqualTo(reqDTO.getOrderId());
		if (StringUtil.isNotEmpty(reqDTO.getOrderSubId())) {
			c.andOrderSubIdEqualTo(reqDTO.getOrderSubId());
		}
		if (null != reqDTO.getId()) {
			c.andIdNotEqualTo(reqDTO.getId());
		}
		if (StringUtil.isNotBlank(reqDTO.getStatus())) {
			c.andStatusEqualTo(reqDTO.getStatus());
		}
		return gdsEvalMapper.countByExample(criteria) > 0;
	}

	/*
	 * 填充字段.填充回复数，单品名称,设置标签名称。
	 */
	private void stuffFields(GdsEvalRespDTO dto) {
		// 设置回复总数.
		dto.setReplyCount(gdsEvalReplySV.executeCountReplyByEvalPK(dto.getId(), GdsConstants.Commons.STATUS_VALID));
	}

	/**
	 * isEvaluated:是否已评价过. <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author liyong7
	 * @param reqDTO
	 * @since JDK 1.6
	 */
	private boolean isEvaluated(GdsEval eval) {
		GdsEvalReqDTO haveEvalQuery = new GdsEvalReqDTO();
		haveEvalQuery.setOrderId(eval.getOrderId());
		haveEvalQuery.setId(eval.getId());
		haveEvalQuery.setStatus(GdsConstants.Commons.STATUS_VALID);
		// 查询是否已经存在已经审核评价。
		return this.queryHaveEval(haveEvalQuery);
	}

	/*
	 * 
	 * pushOrderTOCache:将评价的订单ID暂存至缓存,以保证不会重复发短信. <br/> TODO(这里描述这个方法适用条件 –
	 * 可选).<br/> TODO(这里描述这个方法的执行流程 – 可选).<br/> TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author liyong7
	 * 
	 * @param eval
	 * 
	 * @since JDK 1.6
	 */
	private void pushOrderIdToCache(GdsEval eval) {
		if (null == CacheUtil.getItem(GdsConstants.CacheKey.CACHE_PREFIX_EVAL_ORDER_ID + eval.getOrderId())) {
			CacheUtil.addItem(GdsConstants.CacheKey.CACHE_PREFIX_EVAL_ORDER_ID + eval.getOrderId(), eval.getOrderId(),
					500/* 暂存500秒 */);
		}
	}

	/*
	 * 
	 * isExistInCache:判断当前评价的订单ID是否存在于缓存中. <br/>
	 * 
	 * @author liyong7
	 * 
	 * @param eval
	 * 
	 * @return
	 * 
	 * @since JDK 1.6
	 */
	private boolean isExistInCache(GdsEval eval) {
		return null != CacheUtil.getItem(GdsConstants.CacheKey.CACHE_PREFIX_EVAL_ORDER_ID + eval.getOrderId());
	}

	private void removeOrderIdFromCache(String orderId) {
		CacheUtil.delItem(GdsConstants.CacheKey.CACHE_PREFIX_EVAL_ORDER_ID + orderId);
	}

	@Override
	public Double calCulateShopGoodEvalRate(GdsEvalReqDTO dto) throws Exception {
		// TODO Auto-generated method stub
		GdsEvalShopIdxCriteria example = new GdsEvalShopIdxCriteria();
		GdsEvalShopIdxCriteria.Criteria criteria = example.createCriteria();
		criteria.andShopIdEqualTo(dto.getShopId());
		criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
		short s = 1;
		criteria.andScoreGreaterThan(s);

		Long goodCount = gdsEvalShopIdxMapper.countByExample(example);
		GdsEvalShopIdxCriteria exampleAll = new GdsEvalShopIdxCriteria();
		GdsEvalShopIdxCriteria.Criteria criteriaAll = exampleAll.createCriteria();
		criteriaAll.andShopIdEqualTo(dto.getShopId());
		criteriaAll.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);

		Long count = gdsEvalShopIdxMapper.countByExample(exampleAll);
		if (count > 0) {
			double goodCount1 = goodCount;
			double count1 = count;
			double rate = goodCount1 / count1;
			rate = rate * 100;
			BigDecimal b = new BigDecimal(rate);
			double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			return f1;
		} else {

			return 100.0;
		}
	}

	public Long statisticEvalByPass(GdsEvalReqDTO dto) throws Exception {
		Long count = 0l;
		if (isAuditSwitchOpen()) {
			GdsEvalShopIdxCriteria evalShopIdxCriteria = new GdsEvalShopIdxCriteria();
			GdsEvalShopIdxCriteria.Criteria criteria = evalShopIdxCriteria.createCriteria();
			criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_PENDING_AUDIT);
			if (dto.getShopId() != null) {
				criteria.andShopIdEqualTo(dto.getShopId());
			}
			count = gdsEvalShopIdxMapper.countByExample(evalShopIdxCriteria);

		} else {
			count = 0l;
		}

		return count;
	}

}
