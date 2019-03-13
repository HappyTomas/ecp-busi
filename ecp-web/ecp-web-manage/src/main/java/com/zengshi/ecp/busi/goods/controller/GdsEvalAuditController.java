package com.zengshi.ecp.busi.goods.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.goods.util.GdsParamsTool;
import com.zengshi.ecp.busi.goods.vo.GdsEvalAuditVO;
import com.zengshi.ecp.busi.goods.vo.GdsShopVO;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsEvalRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.FileUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: 评价审核<br>
 * Date:2015年10月26日下午17:21:20 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author tongkai
 * @version
 * @since JDK 1.6
 */
@Controller
@RequestMapping("/gdsevalaudit")
public class GdsEvalAuditController extends EcpBaseController {
	private static String MODULE = GdsEvalAuditController.class.getName();
	private static String URL = "/goods/gdsEvalAudit";

	@Resource(name = "gdsEvalRSV")
	private IGdsEvalRSV gdsEvalRSV;

	/**
	 * 
	 * init:(初始化跳转到猜你喜欢列表页面). <br/>
	 * 
	 * @author tongkai
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping()
	public String init(Model model, GdsShopVO gsShopVO) {
		model.addAttribute("shopId", gsShopVO.getShopId());
		return URL + "/audit-grid";
	}

	// 显示评价审核列表
	@RequestMapping(value = "/auditlist")
	@ResponseBody
	public Model catalogList(Model model, GdsEvalAuditVO vo) throws Exception {
		// / 后场服务所需要的DTO；
		GdsEvalReqDTO dto = vo.toBaseInfo(GdsEvalReqDTO.class);
		// 组织参数
		ObjectCopyUtil.copyObjValue(vo, dto, null, false);
		if (StringUtil.isNotBlank(vo.getAuditStatus())) {
			dto.setStatus(vo.getAuditStatus());
		} else {
			dto.setStatus(GdsConstants.Commons.STATUS_PENDING_AUDIT);
		}
		if(vo.getEndTime()!= null){
		    vo.getEndTime().setHours(23);
		    vo.getEndTime().setMinutes(59);
		    vo.getEndTime().setSeconds(59);
        }
		PageResponseDTO<GdsEvalRespDTO> t = gdsEvalRSV.queryPaging(dto);
		if (t.getResult() == null) {
			t.setResult(new ArrayList<GdsEvalRespDTO>());
		}
		// 获取评价内容
		for (GdsEvalRespDTO respDto : t.getResult()) {
			respDto.setDetail(FileUtil.readFile2Text(respDto.getContent(), "UTF-8"));
		}
		// 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；

		EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);

		return super.addPageToModel(model, GdsParamsTool.batchGdsAndOrdDetailUrl(respVO));
	}

	// 批量审核通过
	@RequestMapping(value = "/gdsbatchpass")
	@ResponseBody
	public EcpBaseResponseVO gdsBatchPass(GdsEvalAuditVO gdsVo) {
		EcpBaseResponseVO vo = new EcpBaseResponseVO();
		GdsEvalReqDTO dto = new GdsEvalReqDTO();
		String[] idList = gdsVo.getOperateId().split(",");
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < idList.length; i++) {
			if (StringUtil.isNotBlank(idList[i])) {
				ids.add(Long.parseLong(idList[i]));
			}
		}
		dto.setIds(ids);
		dto.setAuditStatus(GdsConstants.Commons.STATUS_VALID);
		try {
			gdsEvalRSV.batchAudit(dto);
			vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		} catch (Exception e) {

			BusinessException be = (BusinessException) e;
			if (e instanceof BusinessException) {
				vo.setResultMsg(be.getErrorMessage());
			} else {
				vo.setResultMsg(e.getMessage());
			}
			LogUtil.error(MODULE, "批量审核通过出错！", be);
			vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
		}
		return vo;
	}

	// 批量审核不通过
	@RequestMapping(value = "/gdsbatchnopass")
	@ResponseBody
	public EcpBaseResponseVO gdsBatchNoPass(GdsEvalAuditVO gdsVo) {
		EcpBaseResponseVO vo = new EcpBaseResponseVO();
		GdsEvalReqDTO dto = new GdsEvalReqDTO();
		String[] idList = gdsVo.getOperateId().split(",");
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < idList.length; i++) {
			if (StringUtil.isNotBlank(idList[i])) {
				ids.add(Long.parseLong(idList[i]));
			}
		}
		dto.setIds(ids);
		dto.setAuditStatus(GdsConstants.Commons.STATUS_AUDIT_NOT_THROUGH);
		try {
			gdsEvalRSV.batchAudit(dto);
			vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		} catch (Exception e) {

			BusinessException be = (BusinessException) e;
			if (e instanceof BusinessException) {
				vo.setResultMsg(be.getErrorMessage());
			} else {
				vo.setResultMsg(e.getMessage());
			}
			LogUtil.error(MODULE, "批量审核通过出错！", be);
			vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
		}
		return vo;
	}

}
