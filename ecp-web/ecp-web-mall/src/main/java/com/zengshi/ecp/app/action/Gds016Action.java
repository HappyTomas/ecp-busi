package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Gds016Req;
import com.zengshi.ecp.app.resp.Gds016Resp;
import com.zengshi.ecp.app.resp.gds.GdsEvalReplyRespBaseInfo;
import com.zengshi.ecp.app.resp.gds.GdsPropBaseInfo;
import com.zengshi.ecp.app.resp.gds.GdsPropValueBaseInfo;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants.GdsEvalReply;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PropRelationRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalReplyReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalReplyRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsEvalRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsEvalReplyRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsMediaRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdEvaluationRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.FileUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.SystemException;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2016年3月12日下午5:16:50 <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author zjh
 * @version
 * @since JDK 1.6
 */
@Service("gds016")
@Action(bizcode = "gds016", userCheck = true)
@Scope("prototype")
public class Gds016Action extends AppBaseAction<Gds016Req, Gds016Resp> {

	private static final String MODULE = Gds016Action.class.getName();
	@Resource
	private IGdsEvalReplyRSV gdsEvalReplyRSV;

	@Resource
	private IGdsEvalRSV gdsEvalRSV;

	@Override
	protected void getResponse() throws BusinessException, SystemException,
			Exception {
		Gds016Req gds016Req = this.request;
		Gds016Resp gds016Resp = this.response;

		GdsEvalReplyReqDTO evalReplyReqDTO = new GdsEvalReplyReqDTO();
		GdsEvalReqDTO evalReqDTO = new GdsEvalReqDTO();
		evalReqDTO.setId(gds016Req.getEvalId());
		GdsEvalRespDTO gdsEvalRespDTO = gdsEvalRSV.queryGdsEvalByPK(evalReqDTO);
//		ObjectCopyUtil.copyObjValue(gdsEvalRespDTO, evalReplyReqDTO, null,
//				false);
		evalReplyReqDTO.setEvalId(gds016Req.getEvalId());
		evalReplyReqDTO.setReplyId(gds016Req.getReplyId());
		evalReplyReqDTO.setContent(FileUtil.saveFile(gds016Req.getContent()
				.getBytes("utf-8"), GdsEvalReply.DEFAULT_FILE_NAME,
				GdsEvalReply.FILE_TYPE_HTML));
		evalReplyReqDTO.setReplyType(GdsConstants.GdsEvalReply.REPLY_TYPE_BUYER);
		evalReplyReqDTO.setOrderId(gdsEvalRespDTO.getOrderId());
		evalReplyReqDTO.setOrderSubId(gdsEvalRespDTO.getOrderSubId());
		evalReplyReqDTO.setGdsId(gdsEvalRespDTO.getGdsId());
		evalReplyReqDTO.setShopId(gdsEvalRespDTO.getShopId());
		evalReplyReqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
		evalReplyReqDTO.setStaffId(evalReplyReqDTO.getStaff().getId());
		gdsEvalReplyRSV.addEvalReply(evalReplyReqDTO);
	}

}