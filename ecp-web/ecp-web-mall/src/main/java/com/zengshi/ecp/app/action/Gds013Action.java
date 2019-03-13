package com.zengshi.ecp.app.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Gds013Req;
import com.zengshi.ecp.app.resp.Gds013Resp;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants.GdsEvalReply;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsEvalRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsMediaRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdEvaluationRSV;
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
@Service("gds013")
@Action(bizcode = "gds013", userCheck = true)
@Scope("prototype")
public class Gds013Action extends AppBaseAction<Gds013Req, Gds013Resp> {

	private static final String MODULE = Gds013Action.class.getName();

	@Resource(name = "gdsEvalRSV")
	private IGdsEvalRSV gdsEvalRSV;

	@Resource(name = "ordEvaluationRSV")
	private IOrdEvaluationRSV ordEvaluationRSV;

	@Resource(name = "gdsSkuInfoQueryRSV")
	private IGdsSkuInfoQueryRSV gdsSkuInfoQueryRSV;

	@Resource(name = "gdsMediaRSV")
	private IGdsMediaRSV gdsMediaRSV;

	@Override
	protected void getResponse() throws BusinessException, SystemException, Exception {
		Gds013Req gds013Req = this.request;
		Gds013Resp gds013Resp = this.response;

		LogUtil.info(MODULE, "==========" + JSONObject.toJSONString(gds013Req));
		GdsEvalReqDTO reqDTO = new GdsEvalReqDTO();
		reqDTO.setContent(FileUtil.saveFile(gds013Req.getDetail().getBytes("utf-8"), GdsEvalReply.DEFAULT_FILE_NAME,
				GdsEvalReply.FILE_TYPE_HTML));
		ObjectCopyUtil.copyObjValue(gds013Req, reqDTO, null, false);
		reqDTO.setStaffId(reqDTO.getStaff().getId());

		try {
			gdsEvalRSV.saveGdsEval(reqDTO);
			gds013Resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		} catch (Exception err) {
			LogUtil.error(MODULE, "保存评价出错！！", err);
			gds013Resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
			// respVo.setResultMsg(err.getErrorMessage());
		}

	}

}