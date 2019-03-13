/** 
 * Project Name:ecp-services-goods 
 * File Name:GdsEvaluationSVImpl.java 
 * Package Name:com.zengshi.ecp.goods.service.busi.impl 
 * Date:2015年8月24日上午11:01:07 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */
package com.zengshi.ecp.goods.service.busi.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.StringUtils;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsEvalReplyMapper;
import com.zengshi.ecp.goods.dao.model.GdsEvalReply;
import com.zengshi.ecp.goods.dao.model.GdsEvalReplyCriteria;
import com.zengshi.ecp.goods.dao.model.GdsEvalReplyCriteria.Criteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalReplyReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalReplyRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalRespDTO;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsEvalReplySV;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsEvalSV;
import com.zengshi.ecp.goods.service.common.impl.AbstractSVImpl;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * 评价回复服务接口。 <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月24日上午11:01:07 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version
 * @since JDK 1.6
 */
public class GdsEvalReplySVImpl extends AbstractSVImpl implements
		IGdsEvalReplySV {

	@Resource(name = "seq_gds_eval_reply")
	private PaasSequence seqGdsEvalReply;
	@Resource
	private GdsEvalReplyMapper gdsEvalReplyMapper;
	@Resource
	private IGdsEvalSV gdsEvalSV;

	private static final String DEFAULT_ORDER_BY = "REPLAY_TIME";

	@Override
	public GdsEvalReplyRespDTO addEvalReply(GdsEvalReplyReqDTO reqDTO)
			throws BusinessException {
		if (null == reqDTO.getEvalId()) {
			LogUtil.error(MODULE, "dto传参evalId为空!");
			throw new BusinessException(
					GdsErrorConstants.GdsEval.ERROR_GOODS_EVAL_200551);
		}
		GdsEvalRespDTO eval = gdsEvalSV.queryGdsEvalByPK(reqDTO.getEvalId());
		if (null == eval
				|| GdsConstants.Commons.STATUS_INVALID.equals(eval.getStatus())) {
			throw new BusinessException(
					GdsErrorConstants.GdsEval.ERROR_GOODS_EVAL_200551);
		}

		GdsEvalReply record = new GdsEvalReply();

		ObjectCopyUtil.copyObjValue(reqDTO, record, "replyId,", false);
		record.setId(seqGdsEvalReply.nextValue());
		if (null == reqDTO.getReplyId()) {
			record.setReplyId(GdsConstants.GdsEvalReply.REPLY_ID_NULL);
		} else {
			record.setReplyId(reqDTO.getReplyId());
		}
		record.setEvalId(reqDTO.getEvalId());

		// 需要审核 ,则设置状态为待审核状态.

		if (!StringUtils.hasText(record.getStatus())) {
			record.setStatus(GdsConstants.Commons.STATUS_VALID);
		}

		if (GdsConstants.GdsEvalReply.IS_REPLY_0.equals(eval.getIsReply())) {
			GdsEvalReqDTO gdsEvalReqDTO = new GdsEvalReqDTO();
			gdsEvalReqDTO.setId(eval.getId());
			gdsEvalReqDTO.setIsReply(GdsConstants.GdsEvalReply.IS_REPLY_1);
			gdsEvalSV.updateIsReplyByPK(gdsEvalReqDTO);
		}

		preInsert(reqDTO, record);

		if (null == reqDTO.getReplayTime()) {
			record.setReplayTime(record.getCreateTime());
		} else {
			record.setReplayTime(reqDTO.getReplayTime());
		}

		gdsEvalReplyMapper.insert(record);

		return GdsUtils.doObjConvert(record, GdsEvalReplyRespDTO.class, null,
				null);

	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsEvaluationReplySV#executeCountReplyByEvalPK(java.lang.Long,
	 *      java.lang.String[])
	 */
	@Override
	public long executeCountReplyByEvalPK(Long evalId, String... status)
			throws BusinessException {
		GdsEvalReplyCriteria criteria = new GdsEvalReplyCriteria();
		Criteria c = criteria.createCriteria();
		initStatusCriteria(c, status);
		// c.andReplyIdEqualTo(GdsConstants.GdsEvalReply.REPLY_ID_NULL);
		c.andEvalIdEqualTo(evalId);
		return gdsEvalReplyMapper.countByExample(criteria);
	}

	@Override
	public boolean queryExistReply(Long evalId, String... status)
			throws BusinessException {
		GdsEvalReplyCriteria criteria = new GdsEvalReplyCriteria();
		Criteria c = criteria.createCriteria();
		initStatusCriteria(c, status);
		c.andReplyIdEqualTo(GdsConstants.GdsEvalReply.REPLY_ID_NULL);
		c.andEvalIdEqualTo(evalId);
		return gdsEvalReplyMapper.countByExample(criteria) > 0;
	}

	@Override
	public boolean queryExistSubReply(Long id, String... status)
			throws BusinessException {
		GdsEvalReplyCriteria criteria = new GdsEvalReplyCriteria();
		Criteria c = criteria.createCriteria();
		c.andReplyIdEqualTo(id);
		initStatusCriteria(c, status);
		return gdsEvalReplyMapper.countByExample(criteria) > 0;
	}

	@Override
	public int deleteReplyByEvalId(GdsEvalReplyReqDTO reqDTO)
			throws BusinessException {
		paramCheck(new Object[] { reqDTO.getEvalId() },
				new String[] { "reqDTO.evalId" });
		GdsEvalReply record = new GdsEvalReply();
		preUpdate(reqDTO, record);
		record.setStatus(GdsConstants.Commons.STATUS_INVALID);

		GdsEvalReplyCriteria criteria = new GdsEvalReplyCriteria();
		Criteria c = criteria.createCriteria();
		c.andEvalIdEqualTo(reqDTO.getEvalId());
		c.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);

		GdsEvalRespDTO eval = gdsEvalSV.queryGdsEvalByPK(reqDTO.getEvalId());
		if (GdsConstants.GdsEvalReply.IS_REPLY_1.equals(eval.getIsReply())) {
			GdsEvalReqDTO evalReqDTO = new GdsEvalReqDTO();
			evalReqDTO.setId(reqDTO.getEvalId());
			evalReqDTO.setStaff(reqDTO.getStaff());
			evalReqDTO.setIsReply(GdsConstants.GdsEvalReply.IS_REPLY_0);
			gdsEvalSV.updateIsReplyByPK(evalReqDTO);
		}

		return gdsEvalReplyMapper.updateByExampleSelective(record, criteria);
	}

	@Override
	public void deleteEvalReplyByPK(GdsEvalReplyReqDTO reqDTO)
			throws BusinessException {
		paramCheck(new Object[] { reqDTO }, new String[] { "reqDTO" });
		paramCheck(new Object[] { reqDTO.getId(), reqDTO.getStaff() },
				new String[] { "reqDTO.id", "reqDTO.staff" });
		GdsEvalReply reply = gdsEvalReplyMapper.selectByPrimaryKey(reqDTO
				.getId());
		if (null == reply
				|| GdsConstants.Commons.STATUS_INVALID
						.equals(reply.getStatus())) {
			throw new BusinessException(
					GdsErrorConstants.GdsEval.ERROR_GOODS_EVAL_200552);
		} else {
			preUpdate(reqDTO, reply);
			reply.setStatus(GdsConstants.Commons.STATUS_INVALID);
			gdsEvalReplyMapper.updateByPrimaryKeySelective(reply);
			// 递归删除.
			if (reqDTO.isRecurisve()) {
				List<GdsEvalReplyRespDTO> lst = querySubReplyByReplyId(
						reqDTO.getId(), GdsConstants.Commons.STATUS_VALID);
				if (CollectionUtils.isNotEmpty(lst)) {
					for (GdsEvalReplyRespDTO respDTO : lst) {
						reqDTO.setId(respDTO.getId());
						deleteEvalReplyByPK(reqDTO);
					}
				}
			}

		}

	}

	@Override
	public GdsEvalReplyRespDTO queryReplyByPK(GdsEvalReplyReqDTO reqDTO)
			throws BusinessException {
		paramCheck(new Object[] { reqDTO }, new String[] { "reqDTO" });
		paramCheck(new Object[] { reqDTO.getId() },
				new String[] { "reqDTO.id" });
		GdsEvalReply record = gdsEvalReplyMapper.selectByPrimaryKey(reqDTO
				.getId());
		if (null == record) {
			return null;
		}
		return GdsUtils.doObjConvert(record, GdsEvalReplyRespDTO.class, null,
				null);
	}

	@Override
	public PageResponseDTO<GdsEvalReplyRespDTO> queryGdsEvalReplyRespDTOPaging(
			GdsEvalReplyReqDTO dto) throws BusinessException {
		GdsEvalReplyCriteria criteria = new GdsEvalReplyCriteria();
		criteria.setLimitClauseStart(dto.getStartRowIndex());
		criteria.setLimitClauseCount(dto.getPageSize());
		criteria.setOrderByClause(DEFAULT_ORDER_BY);
		Criteria c = criteria.createCriteria();
		initCriteria(c, dto);
		PageResponseDTO<GdsEvalReplyRespDTO> page = super.queryByPagination(
				dto, criteria, true,
				new GdsEvalReplyRespDTOPaginationCallback());
		// 是否递归获取子回复。
		if (null != dto.isRecurisve() && true == dto.isRecurisve()
				&& page.getCount() > 0) {
			for (GdsEvalReplyRespDTO respDTO : page.getResult()) {
				if (queryExistSubReply(respDTO.getId(),
						GdsConstants.Commons.STATUS_VALID)) {
					querySubReplyRecurisve(respDTO);
				}
			}
		}
		return page;
	}

	@Override
	public List<GdsEvalReplyRespDTO> querySubReplyByReplyId(Long replyId,
			String status) throws BusinessException {
		GdsEvalReplyReqDTO dto = new GdsEvalReplyReqDTO();
		dto.setPageSize(Integer.MAX_VALUE);
		dto.setStatus(status);
		dto.setReplyId(replyId);
		return this.queryGdsEvalReplyRespDTOPaging(dto).getResult();
	}

	@Override
	public void deleteSubReplyByReplyId(GdsEvalReplyReqDTO reqDTO)
			throws BusinessException {
		paramCheck(new Object[] { reqDTO }, new String[] { "reqDTO" });
		paramCheck(new Object[] { reqDTO.getId() },
				new String[] { "reqDTO.id" });
		GdsEvalReplyCriteria criteria = new GdsEvalReplyCriteria();
		Criteria c = criteria.createCriteria();
		c.andReplyIdEqualTo(reqDTO.getId());
		c.andStatusNotEqualTo(GdsConstants.Commons.STATUS_INVALID);

		GdsEvalReply record = new GdsEvalReply();
		record.setStatus(GdsConstants.Commons.STATUS_INVALID);

		preUpdate(reqDTO, record);

		gdsEvalReplyMapper.updateByExampleSelective(record, criteria);

		if (reqDTO.isRecurisve()) {
			List<GdsEvalReplyRespDTO> lst = querySubReplyByReplyId(
					reqDTO.getId(), GdsConstants.Commons.STATUS_VALID);
			if (CollectionUtils.isNotEmpty(lst)) {
				for (GdsEvalReplyRespDTO respDTO : lst) {
					GdsEvalReplyReqDTO condition = GdsUtils.doObjConvert(
							reqDTO, GdsEvalReplyReqDTO.class, null, null);
					condition.setId(respDTO.getId());
					condition.setRecurisve(reqDTO.isRecurisve());
					deleteSubReplyByReplyId(condition);
				}
			}

		}

	}

	private void querySubReplyRecurisve(GdsEvalReplyRespDTO dto) {
		if (queryExistSubReply(dto.getId(), dto.getStatus())) {
			List<GdsEvalReplyRespDTO> subReplys = querySubReplyByReplyId(
					dto.getId(), dto.getStatus());
			dto.setSubReplys(subReplys);
			for (GdsEvalReplyRespDTO subResply : subReplys) {
				querySubReplyRecurisve(subResply);
			}
		}
	}

	/*
	 * 评价回复分页查询回调类。 <br> Project Name:ecp-services-goods <br> Description: <br>
	 * Date:2015年8月17日下午3:36:33 <br> Copyright (c) 2015 ZengShi All Rights Reserved
	 * <br>
	 * 
	 * @author liyong7
	 * 
	 * @version GdsTypeSVImpl
	 * 
	 * @since JDK 1.6
	 */
	protected class GdsEvalReplyRespDTOPaginationCallback extends
			PaginationCallback<GdsEvalReply, GdsEvalReplyRespDTO> {
		@Override
		public List<GdsEvalReply> queryDB(BaseCriteria criteria) {
			return gdsEvalReplyMapper
					.selectByExample((GdsEvalReplyCriteria) criteria);
		}

		@Override
		public long queryTotal(BaseCriteria criteria) {
			return gdsEvalReplyMapper
					.countByExample((GdsEvalReplyCriteria) criteria);
		}

		@Override
		public GdsEvalReplyRespDTO warpReturnObject(GdsEvalReply t) {
			GdsEvalReplyRespDTO dto = new GdsEvalReplyRespDTO();
			ObjectCopyUtil.copyObjValue(t, dto, null, true);
			return dto;
		}

	}

	/*
	 * 初始化查询条件. <br/>
	 * 
	 * @author liyong7
	 * 
	 * @param criteria
	 * 
	 * @param dto
	 * 
	 * @since JDK 1.6
	 */
	private Criteria initCriteria(Criteria c, GdsEvalReplyReqDTO dto) {
		// TODO 针对评价关键字进行模糊查询:因涉及分库分表，商品名称like，且评价内容存放于mongodb的原因，暂不支持评价关键字搜索。
		// 目前仅针对评价起始时间进行时间区间搜索。
		if (null != dto.getReplyId()) {
			c.andReplyIdEqualTo(dto.getReplyId());
		}
		if (StringUtil.isNotBlank(dto.getStatus())) {
			c.andStatusEqualTo(dto.getStatus());
		}
		if (null != dto.getEvalId()) {
			c.andEvalIdEqualTo(dto.getEvalId());
		}
		if (null != dto.getShopId()) {
			c.andShopIdEqualTo(dto.getShopId());
		}
		if (null != dto.getGdsId()) {
			c.andGdsIdEqualTo(dto.getGdsId());
		}
		return c;
	}

	/*
	 * 审核开关是否开启.
	 */
	private boolean isAuditSwitchOpen() {
		return SysCfgUtil.checkSysCfgValue(
				GdsConstants.SysCfgConstants.SWITCH_GDS_EVAL_AUDIT,
				GdsConstants.Commons.STATUS_VALID);
	}

}
