package com.zengshi.ecp.goods.dubbo.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.general.sys.mc.dto.McParamsDTO;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsEvalRSV;
import com.zengshi.ecp.goods.facade.interfaces.eventual.IGdsEvalMainTransaction;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsEvalSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.sys.dubbo.interfaces.IMcSyncSendRSV;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.LogUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * 
 * Title: 评价dubbo实现. <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: <br>
 * Date:2015-9-19上午10:54:46 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version
 * @since JDK 1.6
 */
public class GdsEvalRSVImpl extends AbstractRSVImpl implements IGdsEvalRSV {
	@Resource(name = "gdsEvalSV")
	private IGdsEvalSV gdsEvalSV;

	@Resource(name = "gdsEvalMainTransaction")
	private IGdsEvalMainTransaction gdsEvalMainTransaction;
	@Resource(name = "mcSyncSendRSV")
	private IMcSyncSendRSV mcSyncSendRSV;

	@Override
	public GdsEvalRespDTO saveGdsEval(GdsEvalReqDTO reqDTO)
			throws BusinessException {
		paramNullCheck(reqDTO, true);
		paramCheck(new Object[] { reqDTO.getContent(), reqDTO.getGdsId(),
				reqDTO.getOrderId(), reqDTO.getOrderSubId(),
				reqDTO.getShopId(), reqDTO.getStaffId() }, new String[] {
				"content", "gdsId", "orderId", "orderSubId", "shopId",
				"staffId"

		});

		// 此处调用逻辑说明：
		// 1.评价审核开关打开，则无需调用事务一致性方法。
		// 2.评价审核开关关闭，则在调用事务一致性前需要判断当前是否存在已经审核通过评价，if已经存在已经审核通过记录则无需调用
		// 最终事务一致性方法（因为，存在此种记录说明之前已经回调过）

		if (gdsEvalSV.isAuditSwitchOpen()) {
			return gdsEvalMainTransaction.addEval(reqDTO);
		} else {
			GdsEvalReqDTO haveEvalQuery = new GdsEvalReqDTO();
			haveEvalQuery.setOrderId(reqDTO.getOrderId());
			haveEvalQuery.setOrderSubId(reqDTO.getOrderSubId());
			haveEvalQuery.setStatus(GdsConstants.Commons.STATUS_VALID);
			// 查询是否已经存在已经审核评价。
			boolean haveEval = gdsEvalSV.queryHaveEval(haveEvalQuery);
			if (haveEval) {
				// 已经评价过，则简单调用SV方法添加评价。
				return gdsEvalSV.addGdsEval(reqDTO);
			} else {
				// 未评价过，则需要调用最终一事务评价添加方法异步保证订单域的订单收到已评价更新消息。
				GdsEvalRespDTO respDTO = gdsEvalMainTransaction.addEval(reqDTO);
				if (!isExistInCache(reqDTO.getOrderId())
						&& gdsEvalSV.isGdsEvalInvokeSms()) {
					try {
						pushOrderIdToCache(reqDTO.getOrderId());
						McParamsDTO paramsDTO = new McParamsDTO();
						paramsDTO.setToUserId(respDTO.getStaffId());
						paramsDTO
								.setMsgCode(IGdsEvalSV.SMS_CODE_GDS_EVAL_NOTICE);
						LogUtil.debug(MODULE,
								"无审核流程,评价成功调用短消息服务发送短信!评价ID=" + respDTO.getId()
										+ ";订单ID=" + respDTO.getOrderId()
										+ ";子订单ID=" + respDTO.getOrderSubId());
						mcSyncSendRSV.sendMsg(paramsDTO);
					} catch (Exception e) {
						LogUtil.error(MODULE,
								"添加评价调用消息服务发送短信遇到异常，评价参数[ID=" + respDTO.getId()
										+ ",订单ID=" + respDTO.getOrderId()
										+ ",子订单ID=" + respDTO.getOrderSubId()
										+ "]", e);
					}
				}
				return respDTO;

			}
		}

	}

	@Override
	public void deleteGdsEval(GdsEvalReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO, true);
		paramNullCheck(reqDTO.getId(), "reqDTO.id");
		gdsEvalSV.deleteGdsEvalByPK(reqDTO);
	}

	@Override
	public void ediaGdsEval(GdsEvalReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO, true);
		gdsEvalSV.editGdsEval(reqDTO);
	}

	@Override
	public PageResponseDTO<GdsEvalRespDTO> queryPaging(GdsEvalReqDTO reqDTO)
			throws BusinessException {
		paramNullCheck(reqDTO, false);
		return gdsEvalSV.queryGdsEvalRespDTOPaging(reqDTO);
	}

	@Override
	public GdsEvalRespDTO queryGdsEvalByPK(GdsEvalReqDTO reqDTO)
			throws BusinessException {
		paramNullCheck(reqDTO, false);
		paramNullCheck(reqDTO.getId(), "reqDTO.id");
		return gdsEvalSV.queryGdsEvalByPK(reqDTO);
	}

	@Override
	public boolean queryHaveEval(GdsEvalReqDTO reqDTO) throws BusinessException {
		return gdsEvalSV.queryHaveEval(reqDTO);
	}

	@Override
	public void batchAudit(GdsEvalReqDTO reqDTO) throws BusinessException {
		// gdsEvalSV.batchAudit(reqDTO);
		List<GdsEvalReqDTO> lst = gdsEvalSV
				.batchAuditAccordanceWithOrder(reqDTO);
		if (CollectionUtils.isNotEmpty(lst)) {
			for (GdsEvalReqDTO dto : lst) {
				if (GdsConstants.Commons.STATUS_VALID.equals(dto
						.getAuditStatus())) {
					if (gdsEvalSV.isGdsEvalInvokeSms()) {
						try {
							McParamsDTO paramsDTO = new McParamsDTO();
							paramsDTO.setToUserId(dto.getStaffId());
							paramsDTO
									.setMsgCode(IGdsEvalSV.SMS_CODE_GDS_EVAL_NOTICE);
							mcSyncSendRSV.sendMsg(paramsDTO);
						} catch (Exception e) {
							LogUtil.error(MODULE,
									"评价通过审核(orderId)，调用消息服务发送短信遇到异常", e);
						}
					}
				}
				// gdsEvalMainTransaction.executeEvalAudit(dto);
			}
		}
	}

	@Override
	public PageResponseDTO<GdsEvalRespDTO> queryGdsEvalRespDTOPagingForGdsDetail(
			GdsEvalReqDTO dto) throws BusinessException {
		return gdsEvalSV.queryGdsEvalRespDTOPagingForGdsDetail(dto);
	}

	@Override
	public PageResponseDTO<GdsEvalRespDTO> queryPagingForStaff(
			GdsEvalReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO, false);
		return gdsEvalSV.queryGdsEvalRespDTOPagingByStaff(reqDTO);
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
	private void pushOrderIdToCache(String orderId) {
		if (null == CacheUtil
				.getItem(GdsConstants.CacheKey.CACHE_PREFIX_EVAL_ORDER_ID
						+ orderId)) {
			CacheUtil.addItem(GdsConstants.CacheKey.CACHE_PREFIX_EVAL_ORDER_ID
					+ orderId, orderId, 180/* 暂存180秒 */);
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
	private boolean isExistInCache(String orderId) {
		return null != CacheUtil
				.getItem(GdsConstants.CacheKey.CACHE_PREFIX_EVAL_ORDER_ID
						+ orderId);
	}

	@Override
	public Double calCulateShopGoodEvalRate(GdsEvalReqDTO dto)
			throws BusinessException {
		// TODO Auto-generated method stub
		try {
			return gdsEvalSV.calCulateShopGoodEvalRate(dto);

		} catch (Exception e) {
			throw new BusinessException(GdsErrorConstants.GdsEval.ERROR_GOODS_EVAL_200559);
		}
	}

	@Override
	public Long statisticEvalByPass(GdsEvalReqDTO dto) throws BusinessException {
		// TODO Auto-generated method stub
		try {
			return gdsEvalSV.statisticEvalByPass(dto);

		} catch (Exception e) {
			throw new BusinessException(GdsErrorConstants.GdsEval.ERROR_GOODS_EVAL_200560);
		}
	}

    @Override
    public Long countGoodEval(GdsEvalReqDTO dto) throws BusinessException {
        return gdsEvalSV.countGoodEval(dto);
    }

    @Override
    public Long countMiddleEval(GdsEvalReqDTO dto) throws BusinessException {
        return gdsEvalSV.countMiddleEval(dto);
    }

    @Override
    public Long countBadEval(GdsEvalReqDTO dto) throws BusinessException {
        return gdsEvalSV.countBadEval(dto);
    }

}
