package com.zengshi.ecp.busi.staff.buyer.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.staff.buyer.vo.EvalVO;
import com.zengshi.ecp.busi.staff.buyer.vo.EvalutionVO;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants.GdsEvalReply;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsEvalRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsMediaRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.order.dubbo.dto.ROrdEvaluationRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdEvaluationResponse;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdEvaluationRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.paas.utils.FileUtil;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSONObject;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 买家评价 已评价 待评价<br>
 * Date:2015年9月25日上午10:45:00 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author tongkai
 * @version
 * @since JDK 1.6
 */

@Controller
@RequestMapping(value = "comment")
public class CommentController extends EcpBaseController {

	private String MODULE = CommentController.class.getName();
	private static String URL = "/staff/buyer";

	@Resource(name = "gdsEvalRSV")
	private IGdsEvalRSV gdsEvalRSV;

	@Resource(name = "ordEvaluationRSV")
	private IOrdEvaluationRSV ordEvaluationRSV;

	@Resource(name = "gdsSkuInfoQueryRSV")
	private IGdsSkuInfoQueryRSV gdsSkuInfoQueryRSV;

	@Resource(name = "gdsMediaRSV")
	private IGdsMediaRSV gdsMediaRSV;

	/**
	 * 
	 * init:(买家评价 初始化). <br/>
	 * 
	 * @author tongkai 2015.9.25
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/{status}")
	public String init(Model model, EvalVO reqVo, @PathVariable(value = "status") String status) {
		// 将页面参数对象VO转化为DTO
		ROrdEvaluationRequest reqDto = reqVo.toBaseInfo(ROrdEvaluationRequest.class);
		ObjectCopyUtil.copyObjValue(reqVo, reqDto, null, false);

		reqDto.setStaffId(reqDto.getStaff().getId());
		EcpBasePageRespVO<Map> pageRespVO = null;
		if (reqDto.getPageNo() == 0 || reqDto.getPageSize() == 0) {
			reqDto.setPageNo(1);
			reqDto.setPageSize(5);
		}
		PageResponseDTO<ROrdEvaluationResponse> pageRespDto = null;
		try {
			// 查询数据
			pageRespDto = ordEvaluationRSV.queryEvaluationWait(reqDto);
			// pageRespDto=initpage();
			if (pageRespDto.getResult() == null) {
				pageRespDto.setResult(new ArrayList<ROrdEvaluationResponse>());
			}

		} catch (Exception e) {

			LogUtil.info(MODULE, "获取待评价列表失败！", e);
		}

		List<EvalutionVO> newDto = this.unevalDetail(pageRespDto.getResult());

		PageResponseDTO<EvalutionVO> p = new PageResponseDTO<EvalutionVO>();
		p.setResult(newDto);
		p.setCount(pageRespDto.getCount());
		p.setPageCount(pageRespDto.getPageCount());
		p.setPageNo(pageRespDto.getPageNo());
		p.setPageSize(pageRespDto.getPageSize());

		// 将DTO转化为页面参数对象VO
		try {
			pageRespVO = EcpBasePageRespVO.buildByPageResponseDTO(p);
			model.addAttribute("status", status);
			super.addPageToModel(model, pageRespVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LogUtil.error(MODULE, "获取待评价列表失败！", e);
		}

		return URL + "/comment/menber-comment";
	}// end of method init

	/**
	 * 
	 * evaledList:(买家评价 已评价 列表获取). <br/>
	 * 
	 * @author tongkai 2015.9.22
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "evaledlist")
	public String evaledList(Model model, EvalVO reqVo) {
		// 将页面参数对象VO转化为DTO
		GdsEvalReqDTO reqDto = reqVo.toBaseInfo(GdsEvalReqDTO.class);
		ObjectCopyUtil.copyObjValue(reqVo, reqDto, null, false);

		// 模拟买家ID
		reqDto.setStaffId(reqDto.getStaff().getId());

		EcpBasePageRespVO<Map> pageRespVO = null;
		if (reqDto.getPageNo() == 0 || reqDto.getPageSize() == 0) {
			reqDto.setPageNo(1);
			reqDto.setPageSize(5);
		}
		PageResponseDTO<GdsEvalRespDTO> pageRespDto = null;
		try {
			// 查询数据
			pageRespDto = gdsEvalRSV.queryPagingForStaff(reqDto);
			// pageRespDto =pageInit();
			// 如果返回空集 创建一个
			if (pageRespDto.getResult() == null) {
				pageRespDto.setResult(new ArrayList<GdsEvalRespDTO>());
			}

			List<EvalutionVO> newDto = new ArrayList<EvalutionVO>();
			SkuQueryOption[] skuQuery = new SkuQueryOption[2];
			skuQuery[0] = SkuQueryOption.BASIC;
			skuQuery[1] = SkuQueryOption.MAINPIC;

			// 遍历
			for (GdsEvalRespDTO gdsEvalRespDTO : pageRespDto.getResult()) {
				GdsSkuInfoReqDTO gdsSkuInfoReqDTO = new GdsSkuInfoReqDTO();
				GdsSkuInfoRespDTO gdsSkuInfoRespDTO = new GdsSkuInfoRespDTO();
				EvalutionVO evalutionVO = new EvalutionVO();
				// 查询单品信息
				gdsSkuInfoReqDTO.setId(gdsEvalRespDTO.getSkuId());
				gdsSkuInfoReqDTO.setGdsId(gdsEvalRespDTO.getGdsId());
				gdsSkuInfoReqDTO.setSkuQuery(skuQuery);
				gdsSkuInfoReqDTO.setStatus("1");
				gdsSkuInfoRespDTO = gdsSkuInfoQueryRSV.querySkuInfoByOptions(gdsSkuInfoReqDTO);

				evalutionVO.setSkuUrl(gdsSkuInfoRespDTO.getUrl());
				evalutionVO.setDetail(FileUtil.readFile2Text(gdsEvalRespDTO.getContent(), "UTF-8"));
				evalutionVO.setGdsName(gdsSkuInfoRespDTO.getGdsName());
				evalutionVO.setSkuProps(gdsSkuInfoRespDTO.getSkuProps());
				evalutionVO.setBuyTime(gdsEvalRespDTO.getEvaluationTime());
				evalutionVO.setIntScore(gdsEvalRespDTO.getIntScore());
				evalutionVO.setOrderId(gdsEvalRespDTO.getOrderId());
				evalutionVO.setOrderSubId(gdsEvalRespDTO.getOrderSubId());
				// 设置图片
				GdsMediaRespDTO mediaDTO = gdsSkuInfoRespDTO.getMainPic();
				try {
					if (mediaDTO != null && StringUtil.isNotBlank(mediaDTO.getMediaUuid())) {// 设置URL
						// 图片URL设置
						mediaDTO.setURL(getImageUrl(mediaDTO.getMediaUuid(), "200x200!"));
					} else {
						mediaDTO = new GdsMediaRespDTO();
						mediaDTO.setURL(getImageUrl("null", "200x200!"));
					}
					evalutionVO.setUrl(mediaDTO.getURL());
				} catch (Exception e) {

					LogUtil.error(MODULE, "获取图片失败！", e);
				}
				newDto.add(evalutionVO);

			}
			PageResponseDTO<EvalutionVO> p = new PageResponseDTO<EvalutionVO>();
			p.setResult(newDto);
			p.setCount(pageRespDto.getCount());
			p.setPageCount(pageRespDto.getPageCount());
			p.setPageNo(pageRespDto.getPageNo());
			p.setPageSize(pageRespDto.getPageSize());
			// 将DTO转化为页面参数对象VO
			pageRespVO = EcpBasePageRespVO.buildByPageResponseDTO(p);

			super.addPageToModel(model, pageRespVO);

		} catch (Exception e) {
			LogUtil.error(MODULE, "获取已评价列表失败！", e);
			String msg = "出错啦！";
			model.addAttribute("msg", msg);
		}

		return URL + "/comment/div/evaled";
	}

	/**
	 * 
	 * evaledList:(买家评价 待评价 列表获取). <br/>
	 * 
	 * @author tongkai 2015.9.22
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "unevallist")
	public String unevalList(Model model, EvalVO reqVo) {
		// 将页面参数对象VO转化为DTO
		ROrdEvaluationRequest reqDto = reqVo.toBaseInfo(ROrdEvaluationRequest.class);
		ObjectCopyUtil.copyObjValue(reqVo, reqDto, null, false);
		// 模拟买家ID
		reqDto.setStaffId(reqDto.getStaff().getId());
		EcpBasePageRespVO<Map> pageRespVO = null;
		if (reqDto.getPageNo() == 0 || reqDto.getPageSize() == 0) {
			reqDto.setPageNo(1);
			reqDto.setPageSize(5);
		}
		PageResponseDTO<ROrdEvaluationResponse> pageRespDto = null;
		try {
			// 查询数据
			pageRespDto = ordEvaluationRSV.queryEvaluationWait(reqDto);
			// pageRespDto=initpage();
			if (pageRespDto.getResult() == null) {
				pageRespDto.setResult(new ArrayList<ROrdEvaluationResponse>());
			}

		} catch (Exception e) {

			LogUtil.error(MODULE, "获取待评价列表失败！", e);
		}

		List<EvalutionVO> newDto = this.unevalDetail(pageRespDto.getResult());

		PageResponseDTO<EvalutionVO> p = new PageResponseDTO<EvalutionVO>();
		p.setResult(newDto);
		p.setCount(pageRespDto.getCount());
		p.setPageCount(pageRespDto.getPageCount());
		p.setPageNo(pageRespDto.getPageNo());
		p.setPageSize(pageRespDto.getPageSize());

		// 将DTO转化为页面参数对象VO
		try {
			pageRespVO = EcpBasePageRespVO.buildByPageResponseDTO(p);
			model.addAttribute("pageInfo", pageRespVO);
			super.addPageToModel(model, pageRespVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LogUtil.error(MODULE, "获取待评价列表失败！", e);
		}

		return URL + "/comment/div/uneval";
	}

	// 保存评价
	@RequestMapping(value = "/save")
	@ResponseBody
	public EcpBaseResponseVO save(EvalVO VO) throws Exception {
		EcpBaseResponseVO respVo = new EcpBaseResponseVO();
		LogUtil.info(MODULE, "==========" + JSONObject.toJSONString(VO));
		GdsEvalReqDTO reqDTO = VO.toBaseInfo(GdsEvalReqDTO.class);
		VO.setContent(FileUtil.saveFile(VO.getDetail().getBytes("utf-8"), GdsEvalReply.DEFAULT_FILE_NAME, GdsEvalReply.FILE_TYPE_HTML));
		ObjectCopyUtil.copyObjValue(VO, reqDTO, null, true);

		reqDTO.setStaffId(reqDTO.getStaff().getId());

		try {
			gdsEvalRSV.saveGdsEval(reqDTO);
			respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		} catch (Exception err) {
			LogUtil.error(MODULE, "保存评价出错！！", err);
			respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
			// respVo.setResultMsg(err.getErrorMessage());
		}

		return respVo;
	}

	private String getImageUrl(String vfsId, String param) {
		StringBuilder sb = new StringBuilder();
		sb.append(vfsId);
		if (!StringUtil.isBlank(param)) {
			sb.append("_");
			sb.append(param);
		}
		return ImageUtil.getImageUrl(sb.toString());
	}

	// 查询为评价订单单品详情
	private List<EvalutionVO> unevalDetail(List<ROrdEvaluationResponse> pageRespDto) {
		List<EvalutionVO> newDto = new ArrayList<EvalutionVO>();
		SkuQueryOption[] skuQuery = new SkuQueryOption[2];
		skuQuery[0] = SkuQueryOption.BASIC;
		skuQuery[1] = SkuQueryOption.MAINPIC;

		for (ROrdEvaluationResponse ordEvaluationResponse : pageRespDto) {
			GdsEvalReqDTO gdsEvalReqDTO = new GdsEvalReqDTO();
			gdsEvalReqDTO.setOrderId(ordEvaluationResponse.getOrderId());
			gdsEvalReqDTO.setOrderSubId(ordEvaluationResponse.getSubId());
			// 查询订单是否已评价
			if (!gdsEvalRSV.queryHaveEval(gdsEvalReqDTO)) {

				GdsSkuInfoReqDTO gdsSkuInfoReqDTO = new GdsSkuInfoReqDTO();
				GdsSkuInfoRespDTO gdsSkuInfoRespDTO = new GdsSkuInfoRespDTO();
				EvalutionVO evalutionVO = new EvalutionVO();
				gdsSkuInfoReqDTO.setId(ordEvaluationResponse.getSkuId());
				gdsSkuInfoReqDTO.setGdsId(ordEvaluationResponse.getGdsId());
				gdsSkuInfoReqDTO.setSkuQuery(skuQuery);
				gdsSkuInfoReqDTO.setStatus("1");
				// 查询单品信息
				gdsSkuInfoRespDTO = gdsSkuInfoQueryRSV.querySkuInfoByOptions(gdsSkuInfoReqDTO);
				evalutionVO.setSkuUrl(gdsSkuInfoRespDTO.getUrl());
				evalutionVO.setGdsName(gdsSkuInfoRespDTO.getGdsName());
				evalutionVO.setSkuProps(gdsSkuInfoRespDTO.getSkuProps());
				evalutionVO.setOrderId(ordEvaluationResponse.getOrderId());
				evalutionVO.setBuyTime(ordEvaluationResponse.getOrderTime());
				evalutionVO.setOrderSubId(ordEvaluationResponse.getSubId());
				evalutionVO.setGdsId(gdsSkuInfoRespDTO.getGdsId());
				evalutionVO.setShopId(gdsSkuInfoRespDTO.getShopId());
				evalutionVO.setSkuId(ordEvaluationResponse.getSkuId());
				// 设置图片
				GdsMediaRespDTO mediaDTO = gdsSkuInfoRespDTO.getMainPic();
				try {
					if (mediaDTO != null && StringUtil.isNotBlank(mediaDTO.getMediaUuid())) {// 设置URL
						// 图片URL设置
						mediaDTO.setURL(getImageUrl(mediaDTO.getMediaUuid(), "200x200!"));
					} else {
						mediaDTO = new GdsMediaRespDTO();
						mediaDTO.setURL(getImageUrl("null", "200x200!"));
					}
					evalutionVO.setUrl(mediaDTO.getURL());
				} catch (Exception e) {
					LogUtil.error(MODULE, "获取图片失败！", e);
				}
				newDto.add(evalutionVO);
			}
		}
		return newDto;
	}

}
