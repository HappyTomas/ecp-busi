package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Gds012Req;
import com.zengshi.ecp.app.resp.Gds012Resp;
import com.zengshi.ecp.app.resp.gds.GdsEvalBaseInfo;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.busi.staff.buyer.vo.EvalutionVO;
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
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.FileUtil;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.SystemException;

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
@Service("gds012")
@Action(bizcode = "gds012", userCheck = true)
@Scope("prototype")
public class Gds012Action extends AppBaseAction<Gds012Req, Gds012Resp> {

	private static final String MODULE = Gds012Action.class.getName();

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

		Gds012Req gds012Req = this.request;
		Gds012Resp gds012Resp = this.response;
		// 未评价
		if ("0".equals(gds012Req.getStatus())) {

			// 将页面参数对象VO转化为DTO
			ROrdEvaluationRequest reqDto = new ROrdEvaluationRequest();
			reqDto.setPageNo(gds012Req.getPageNumber());
			reqDto.setPageSize(gds012Req.getPageSize());

			reqDto.setStaffId(reqDto.getStaff().getId());
			EcpBasePageRespVO<Map> pageRespVO = null;

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
				String msg = "获取待评价列表失败！";
				gds012Resp.setMsg(msg);
			}

			List<EvalutionVO> newDto = this.unevalDetail(pageRespDto.getResult());

			PageResponseDTO<EvalutionVO> p = new PageResponseDTO<EvalutionVO>();
			p.setResult(newDto);
			p.setCount(pageRespDto.getCount());
			p.setPageCount(pageRespDto.getPageCount());
			p.setPageNo(pageRespDto.getPageNo());
			p.setPageSize(pageRespDto.getPageSize());
			List<GdsEvalBaseInfo> gdsEvalBaseInfos = new ArrayList<GdsEvalBaseInfo>();
			for (EvalutionVO evalutionVO : newDto) {
				GdsEvalBaseInfo evalBaseInfo = new GdsEvalBaseInfo();
				ObjectCopyUtil.copyObjValue(evalutionVO, evalBaseInfo, null, false);
				gdsEvalBaseInfos.add(evalBaseInfo);
			}
			gds012Resp.setGdsEvalRespList(gdsEvalBaseInfos);
			gds012Resp.setCount(pageRespDto.getCount());
			gds012Resp.setPageCount(pageRespDto.getPageCount());
		} else {

			// 将页面参数对象VO转化为DTO
			GdsEvalReqDTO reqDto = new GdsEvalReqDTO();

			// 模拟买家ID
			reqDto.setStaffId(reqDto.getStaff().getId());
			reqDto.setPageNo(gds012Req.getPageNumber());
			reqDto.setPageSize(gds012Req.getPageSize());
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
					evalutionVO.setSkuId(gdsEvalRespDTO.getSkuId());
					evalutionVO.setGdsId(gdsEvalRespDTO.getGdsId());
					evalutionVO.setShopId(gdsEvalRespDTO.getShopId());
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
						String msg = "获取图片失败！";
						gds012Resp.setMsg(msg);
					}
					newDto.add(evalutionVO);

				}
				PageResponseDTO<EvalutionVO> p = new PageResponseDTO<EvalutionVO>();
				p.setResult(newDto);
				p.setCount(pageRespDto.getCount());
				p.setPageCount(pageRespDto.getPageCount());
				p.setPageNo(pageRespDto.getPageNo());
				p.setPageSize(pageRespDto.getPageSize());
				List<GdsEvalBaseInfo> gdsEvalBaseInfos = new ArrayList<GdsEvalBaseInfo>();
				for (EvalutionVO evalutionVO : newDto) {
					GdsEvalBaseInfo evalBaseInfo = new GdsEvalBaseInfo();
					ObjectCopyUtil.copyObjValue(evalutionVO, evalBaseInfo, null, false);
					gdsEvalBaseInfos.add(evalBaseInfo);
				}
				gds012Resp.setGdsEvalRespList(gdsEvalBaseInfos);
				gds012Resp.setCount(pageRespDto.getCount());
				gds012Resp.setPageCount(pageRespDto.getPageCount());

			} catch (Exception e) {
				LogUtil.error(MODULE, "获取已评价列表失败！", e);
				String msg = "获取已评价列表失败！";
				gds012Resp.setMsg(msg);
			}

		}

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

	private String getImageUrl(String vfsId, String param) {
		StringBuilder sb = new StringBuilder();
		sb.append(vfsId);
		if (!StringUtil.isBlank(param)) {
			sb.append("_");
			sb.append(param);
		}
		return ImageUtil.getImageUrl(sb.toString());
	}
}