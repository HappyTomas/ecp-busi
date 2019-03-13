package com.zengshi.ecp.busi.goods.controller;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.goods.common.GdsBaseController;
import com.zengshi.ecp.busi.goods.util.GdsParamsTool;
import com.zengshi.ecp.busi.goods.vo.EvalReplyVO;
import com.zengshi.ecp.busi.goods.vo.GdsEvalVO;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants.Commons;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants.GdsEvalReply;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalReplyReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalReplyRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsEvalRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsEvalReplyRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustInfoRSV;
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
@RequestMapping(value = "gdsevalshop")
public class GdsEvalShopController extends GdsBaseController {

	private String MODULE = GdsEvalShopController.class.getName();
	private static String URL = "/goods/gdsEvalShop";

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
	 * init:(卖家评价管理页面初始化). <br/>
	 * 
	 * @author zhanbh 2015.9.21
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping()
	private String init() {
		return URL + "/eval-shop-grid";
	}// end of method init
 
	/**
	 * 
	 * init:(卖家评价管理页面数据初始化). <br/>
	 * 
	 * @author zhanbh 2015.10.14
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "gridinit")
	private String gridInit (Model model, GdsEvalVO reqVo){
		//分页参数初始化
    	reqVo.setPageSize(5);
    	reqVo.setPageNumber(1);
    	if(reqVo.getEndTime()!= null){
    	    reqVo.getEndTime().setHours(23);
            reqVo.getEndTime().setMinutes(59);
            reqVo.getEndTime().setSeconds(59);
    	}
    	//获取评价列表数据
    	EcpBasePageRespVO<Map> pageRespVo =  null;
        try {
            pageRespVo = getEvalList(reqVo);
        } catch (Exception e) {
            LogUtil.error(MODULE, "获取评价列表异常！", e);
            //加入错误信息
            model.addAttribute("msg", "获取评价列表异常！");
            pageRespVo = new EcpBasePageRespVO<Map>();
            pageRespVo.setPageNumber(0);
            pageRespVo.setPageSize(reqVo.getPageSize());
            pageRespVo.setTotalPage(0);
            pageRespVo.setTotalRow(0);
        }
        if(null != pageRespVo){
            pageRespVo = GdsParamsTool.batchGdsAndOrdDetailUrl(pageRespVo);
        }
    	super.addPageToModel(model, pageRespVo);
    	return URL + "/div/evalList";
	}//end of method gridInit
	/**
	 * gridList:(卖家评价列表获取). <br/>
	 * 
	 * @author zhanbh 2015.9.21
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "gridlist")
	private String gridList(Model model, GdsEvalVO reqVo) {
		//获取评价列表数据
	    if(reqVo.getEndTime()!= null){
            reqVo.getEndTime().setHours(23);
            reqVo.getEndTime().setMinutes(59);
            reqVo.getEndTime().setSeconds(59);
        }
		EcpBasePageRespVO<Map> pageRespVo =  null;
		try {
		    pageRespVo = getEvalList(reqVo);
        } catch (Exception e) {
            LogUtil.error(MODULE, "获取评价列表异常！", e);
            //加入错误信息
            model.addAttribute("msg", "获取评价列表异常！");
            pageRespVo = new EcpBasePageRespVO<Map>();
            pageRespVo.setPageNumber(0);
            pageRespVo.setPageSize(reqVo.getPageSize());
            pageRespVo.setTotalPage(0);
            pageRespVo.setTotalRow(0);
        }
		if(null != pageRespVo){
            pageRespVo = GdsParamsTool.batchGdsAndOrdDetailUrl(pageRespVo);
        }
        super.addPageToModel(model, pageRespVo);
		return URL + "/div/evalList";
	}// end of method gridList

	
	/**
	 * 获取评价列表数据
	 * @throws Exception 
	 */
	private EcpBasePageRespVO<Map> getEvalList (GdsEvalVO reqVo) throws Exception{
		// 将VO 转化为 DTO 前台参数获取
				GdsEvalReqDTO reqDto = reqVo.toBaseInfo(GdsEvalReqDTO.class);
				ObjectCopyUtil.copyObjValue(reqVo, reqDto, null, false);
		// 设置其他必须参数
		reqDto.setStatus(Commons.STATUS_VALID);

		EcpBasePageRespVO<Map> pageRespVo = null;
		PageResponseDTO<GdsEvalRespDTO> pageRespDto = null;
		pageRespDto = iGdsEvalRSV.queryPaging(reqDto);
		if(null == pageRespDto){
		    pageRespDto = new PageResponseDTO<GdsEvalRespDTO>();
		}
		if (null == pageRespDto.getResult()) {
			pageRespDto.setResult(new ArrayList<GdsEvalRespDTO>());
		}
		// 转化一些参数
		GdsInfoReqDTO gdsInfo = new GdsInfoReqDTO();
		for (GdsEvalRespDTO respDto : pageRespDto.getResult()) {
			// 获取商品信息
		    if(null != respDto && StringUtil.isNotEmpty(respDto.getGdsId())){
		        gdsInfo.setId(respDto.getGdsId());
		        GdsInfoRespDTO gdsDetail = null;
		        try {
		            gdsDetail = iGdsInfoQueryRSV.queryGdsInfo(gdsInfo); 
		        }catch (Exception e) {
                    LogUtil.error(MODULE, "读取商品信息异常，商品id为："+respDto.getGdsId(),e);
                    respDto.setGdsName("【读取产品信息异常，如果为个别现象，请检查该产品数据正确性】");
		        } 
	            if(null != gdsDetail){
	                respDto.setGdsName(gdsDetail.getGdsName());
	                respDto.setIsbn(gdsDetail.getIsbn());
	            }
		    }
			// 获取评价内容
		    if(null != respDto && StringUtil.isNotBlank(respDto.getContent())){
		        try {
	                respDto.setDetail(FileUtil.readFile2Text(respDto.getContent(),"UTF-8"));
	            } catch (Exception e) {
	                LogUtil.error(MODULE, "读取mogoDB异常，id为："+respDto.getContent(),e);
	                   respDto.setDetail("【读取评价内容异常，如普遍果为现象，请检查对应服务器】");
	            } 
		    }
		}

		pageRespVo = EcpBasePageRespVO.buildByPageResponseDTO(pageRespDto);
		
		return pageRespVo;
	}//end of mehtod getEvalList
	
	
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
//			pageReqVo = EcpBasePageRespVO.buildByPageResponseDTO(pageRespDto);
			model.addAttribute("Seller",GdsEvalReply.REPLY_TYPE_SELLER);
			model.addAttribute("repliesInfo", pageRespDto.getResult());
			model.addAttribute("staff", reqDto.getStaff().getStaffCode());
		} catch (Exception e) {
			LogUtil.error(MODULE, "获取回复列表失败！", e);
			String msg = "获取回复列表失败！";
			model.addAttribute("msg", msg);
		}
		return URL + "/div/replies";
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
			LogUtil.error(MODULE, "获取评价信息失败！", e);
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
