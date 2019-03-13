package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Gds013Req;
import com.zengshi.ecp.app.req.Gds014Req;
import com.zengshi.ecp.app.req.Gds015Req;
import com.zengshi.ecp.app.resp.Gds013Resp;
import com.zengshi.ecp.app.resp.Gds014Resp;
import com.zengshi.ecp.app.resp.Gds015Resp;
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
import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsEvalRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsEvalReplyRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsMediaRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdEvaluationRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustInfoRSV;
import com.zengshi.ecp.system.util.ParamsTool;
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
@Service("gds015")
@Action(bizcode = "gds015", userCheck = false)
@Scope("prototype")
public class Gds015Action extends AppBaseAction<Gds015Req, Gds015Resp> {

	private static final String MODULE = Gds015Action.class.getName();
	@Resource
	private IGdsEvalReplyRSV gdsEvalReplyRSV;
	@Resource
	private ICustInfoRSV iCustInfoRSV;

	@Override
	protected void getResponse() throws BusinessException, SystemException,
			Exception {
		Gds015Req gds015Req = this.request;
		Gds015Resp gds015Resp = this.response;

		GdsEvalReplyReqDTO evalReplyReqDTO = new GdsEvalReplyReqDTO();
		evalReplyReqDTO.setEvalId(gds015Req.getEvalId());
		evalReplyReqDTO.setPageNo(gds015Req.getPageNumber());
		evalReplyReqDTO.setPageSize(gds015Req.getPageSize());
		PageResponseDTO<GdsEvalReplyRespDTO> pageResponseDTO = gdsEvalReplyRSV
				.queryGdsEvalReplyRespDTOPaging(evalReplyReqDTO);
		List<GdsEvalReplyRespBaseInfo> evalRespBaseInfos = new ArrayList<GdsEvalReplyRespBaseInfo>();
		if (pageResponseDTO.getResult() != null) {
			for (GdsEvalReplyRespDTO evalReplyRespDTO : pageResponseDTO
					.getResult()) {
				GdsEvalReplyRespBaseInfo evalReplyRespBaseInfo = new GdsEvalReplyRespBaseInfo();
				ObjectCopyUtil.copyObjValue(evalReplyRespDTO,
						evalReplyRespBaseInfo, null, false);
				if(GdsConstants.GdsEvalReply.REPLY_TYPE_BUYER.equals(evalReplyRespDTO.getReplyType())){
				CustInfoReqDTO custInfoReqDTO = new CustInfoReqDTO();
				custInfoReqDTO.setId(evalReplyRespDTO.getStaffId());
				CustInfoResDTO custInfoResDTO = iCustInfoRSV
						.getCustInfoById(custInfoReqDTO);
				evalReplyRespBaseInfo.setStaffName(custInfoResDTO
						.getStaffCode());
				evalReplyRespBaseInfo.setCustPic(ParamsTool.getImageUrl(custInfoResDTO.getCustPic(),"200x200!"));
				}else{
					evalReplyRespBaseInfo.setStaffName("掌柜评论");
					CustInfoReqDTO custInfoReqDTO = new CustInfoReqDTO();
					custInfoReqDTO.setId(evalReplyRespDTO.getStaffId());
					CustInfoResDTO custInfoResDTO = iCustInfoRSV
							.getCustInfoById(custInfoReqDTO);
					evalReplyRespBaseInfo.setCustPic(ParamsTool.getImageUrl(custInfoResDTO.getCustPic(),"200x200!"));
				}

				evalReplyRespBaseInfo.setDetail(FileUtil.readFile2Text(
						evalReplyRespDTO.getContent(), "UTF-8"));

				evalRespBaseInfos.add(evalReplyRespBaseInfo);

			}

		}

		gds015Resp.setEvalReplyInfoList(evalRespBaseInfos);

	}

}