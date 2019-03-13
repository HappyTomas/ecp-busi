package com.zengshi.ecp.busi.seller.goods.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.seller.goods.vo.EvalReplyVO;
import com.zengshi.ecp.busi.seller.goods.vo.GdsEvalVO;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants.Commons;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants.GdsEvalReply;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalReplyReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalReplyRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsEvalRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsEvalReplyRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.SellerResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustInfoRSV;
import com.zengshi.ecp.system.filter.SellerLocaleUtil;
import com.zengshi.ecp.system.util.GdsParamsTool;
import com.zengshi.ecp.system.util.ParamsTool;
import com.zengshi.paas.utils.FileUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: 卖家评价管理<br>
 * Date:2015年9月06日上午10:45:00 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author zhanbh
 * @version
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value = "seller/gdsevalshop")
public class GdsEvalShopController extends GdsBaseController {

	private String MODULE = GdsEvalShopController.class.getName();
	private static String URL = "/seller/goods/gdsEvalShop";

	@Resource
	private IGdsEvalRSV iGdsEvalRSV;

	@Resource
	private IGdsInfoQueryRSV iGdsInfoQueryRSV;

	@Resource
	private IGdsEvalReplyRSV iGdsEvalReplyRSV;

	@Resource
	private ICustInfoRSV iCustInfoRSV;

	/**
	 * 
	 * init:(卖家评价管理页面数据初始化). <br/>
	 * 
	 * @author zhanbh 2015.10.14
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping()
	private String gridInit(Model model, GdsEvalVO reqVo) {
		Long shopId=reqVo.getShopId();
		if (shopId == null) {
			SellerResDTO srd = SellerLocaleUtil.getSeller();
			List<ShopInfoResDTO> shopLst = srd.getShoplist();
			shopId = shopLst.get(0).getId();
			reqVo.setShopId(shopId);
		}
		model.addAttribute("shopId", shopId);
		if (StringUtil.isNotBlank(reqVo.getEvalScore())) {
			model.addAttribute("evalScore", reqVo.getEvalScore());
		}

		// 获取评价列表数据
		PageResponseDTO<GdsEvalRespDTO> pageRespVo = getEvalList(reqVo);

		model.addAttribute("evalList", pageRespVo);
		return URL + "/eval-grid";
	}// end of method gridInit

	/**
	 * gridList:(卖家评价列表获取). <br/>
	 * 
	 * @author zhanbh 2015.9.21
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/gridlist")
	private String gridList(Model model, GdsEvalVO reqVo) {
		// 获取评价列表数据
		PageResponseDTO<GdsEvalRespDTO> pageRespVo = getEvalList(reqVo);
		model.addAttribute("evalList", pageRespVo);
		return URL + "/list/list";
	}// end of method gridList

	/**
	 * 获取评价列表数据
	 */
	private PageResponseDTO<GdsEvalRespDTO> getEvalList(GdsEvalVO reqVo) {
		// 将VO 转化为 DTO 前台参数获取
		GdsEvalReqDTO reqDto = reqVo.toBaseInfo(GdsEvalReqDTO.class);
		ObjectCopyUtil.copyObjValue(reqVo, reqDto, null, false);
		// 设置其他必须参数
		reqDto.setStatus(Commons.STATUS_VALID);
		if(StringUtil.isNotBlank(reqVo.getEvalScore())){
			
			switch(reqVo.getEvalScore()){
			case "1": 
			reqDto.setScoreFrom((short)5);
			break;
			case "2":
			reqDto.setScoreFrom((short)2);	
			reqDto.setScoreTo((short)4);	
			break;
			case "3":
			reqDto.setScoreTo((short)1);	
			break;
			
			}
			
		}
		
		
		PageResponseDTO<GdsEvalRespDTO> pageRespDto = new PageResponseDTO<GdsEvalRespDTO>();
		try {
			pageRespDto = iGdsEvalRSV.queryGdsEvalRespDTOPagingForGdsDetail(reqDto);
//			pageRespDto = iGdsEvalRSV.queryPaging(reqDto);
			if (pageRespDto.getResult() == null) {
				pageRespDto.setResult(new ArrayList<GdsEvalRespDTO>());
			}
			// 转化一些参数
			GdsInfoReqDTO gdsInfo = new GdsInfoReqDTO();
			for (GdsEvalRespDTO respDto : pageRespDto.getResult()) {
				// 获取商品名称
				gdsInfo.setId(respDto.getGdsId());
				respDto.setGdsName(iGdsInfoQueryRSV.queryGdsInfo(gdsInfo)
						.getGdsName());
				// 获取评价内容
				respDto.setDetail(FileUtil.readFile2Text(respDto.getContent(),
						"UTF-8"));
				
				if(respDto.getGdsId() != null && respDto.getGdsId()!= 0L){
    				String gdsdetailUrl = GdsParamsTool.getGdsDetailSiteUrl(1l,respDto.getGdsId() ,respDto.getSkuId() ,null);
        			respDto.setGdsDetailUrl(gdsdetailUrl);
    			}
    			if(StringUtil.isNotEmpty(respDto.getOrderId())){
    				String ordDetailUrl =  ParamsTool.getOrdDetailUrl(1L, respDto.getOrderId());
    				respDto.setOrdDetailUrl(ordDetailUrl);
    			}
			}

		} catch (Exception e) {
			pageRespDto.setResult(new ArrayList<GdsEvalRespDTO>());
			e.printStackTrace();
			LogUtil.info(MODULE, "获取评价列表失败！", e);
		}
		return pageRespDto;
	}// end of mehtod getEvalList

	/**
	 * replyList:(卖家评价回复列表获取). <br/>
	 * 
	 * @author zhanbh 2015.9.25
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/replylist")
	private String replyList(Model model, EvalReplyVO reqVo) {
		EcpBasePageRespVO<Map> pageReqVo = null;
		// 将VO 转化为 DTO 前台参数获取
		GdsEvalReplyReqDTO reqDto = reqVo.toBaseInfo(GdsEvalReplyReqDTO.class);
		ObjectCopyUtil.copyObjValue(reqVo, reqDto, null, false);

		// 设置其他必须参数
		reqDto.setPageSize(Integer.MAX_VALUE);
		reqDto.setStatus(Commons.STATUS_VALID);

		try {
			PageResponseDTO<GdsEvalReplyRespDTO> pageRespDto = iGdsEvalReplyRSV
					.queryGdsEvalReplyRespDTOPaging(reqDto);

			// 转化参数
			CustInfoReqDTO cusReqDto = new CustInfoReqDTO();
			CustInfoResDTO cusResDto = null;
			for (GdsEvalReplyRespDTO respDto : pageRespDto.getResult()) {
				respDto.setDetail(FileUtil.readFile2Text(respDto.getContent(),
						"UTF-8"));
				// 获取用户名称
				cusReqDto.setId(respDto.getStaffId());
				cusResDto = iCustInfoRSV.getCustInfoById(cusReqDto);
				respDto.setStaffName(cusResDto.getCustName());
			}// end of for
				// pageReqVo =
				// EcpBasePageRespVO.buildByPageResponseDTO(pageRespDto);
			model.addAttribute("Seller", GdsEvalReply.REPLY_TYPE_SELLER);
			model.addAttribute("repliesInfo", pageRespDto.getResult());
			model.addAttribute("staff", reqDto.getStaff().getStaffCode());
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.info(MODULE, "获取回复列表失败！", e);
			String msg = "获取回复列表失败！";
			model.addAttribute("msg", msg);
		}
		return URL + "/list/replies";
	}// end of method gridList

	/**
	 * saveReply:(保存回复). <br/>
	 * 
	 * @author zhanbh 2015.9.28
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/savereply")
	@ResponseBody
	private Model saveReply(Model model, EvalReplyVO reqVo) {
		GdsEvalReqDTO eReqDto = new GdsEvalReqDTO();
		GdsEvalRespDTO eRespDto = null;
		EcpBaseResponseVO bRespVo = new EcpBaseResponseVO();
		// 转化参数
		GdsEvalReplyReqDTO reqDto = reqVo.toBaseInfo(GdsEvalReplyReqDTO.class);
		ObjectCopyUtil.copyObjValue(reqVo, reqDto, null, false);
		// 获取评价信息
		eReqDto.setId(reqDto.getEvalId());
		try {
			eRespDto = iGdsEvalRSV.queryGdsEvalByPK(eReqDto);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.info(MODULE, "获取评价信息失败！", e);
			String msg = "获取评价失败，请联系管理员！";
			model.addAttribute("msg", msg);
			bRespVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
			model.addAttribute("bRespVo", bRespVo);
			return model;
		}
		// 设置必须参数
		if (eRespDto != null) {
			reqDto.setGdsId(eRespDto.getGdsId());
			reqDto.setOrderId(eRespDto.getOrderId());
			reqDto.setOrderSubId(eRespDto.getOrderSubId());
			reqDto.setShopId(eRespDto.getShopId());
			reqDto.setStaffId(35l);
			reqDto.setStatus(Commons.STATUS_VALID);
			reqDto.setReplyType(GdsEvalReply.REPLY_TYPE_SELLER);
			// 保存文本
			try {
				reqDto.setContent(FileUtil.saveFile(
						reqVo.getDetail().getBytes("utf-8"),
						GdsEvalReply.DEFAULT_FILE_NAME,
						GdsEvalReply.FILE_TYPE_HTML));
			} catch (Exception e) {
				String msg = "保存回复文本失败，请联系管理员！";
				model.addAttribute("msg", msg);
				bRespVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
				model.addAttribute("bRespVo", bRespVo);
				return model;
			}
		}// end of if
		try {
			iGdsEvalReplyRSV.addEvalReply(reqDto);
			bRespVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
			model.addAttribute("bRespVo", bRespVo);
		} catch (Exception e) {
			String msg = "保存回复失败，请联系管理员！";
			model.addAttribute("msg", msg);
			bRespVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
			model.addAttribute("bRespVo", bRespVo);
			return model;
		}

		return model;
	}// end of method saveReply

}// end of class GdsEvalShopController
