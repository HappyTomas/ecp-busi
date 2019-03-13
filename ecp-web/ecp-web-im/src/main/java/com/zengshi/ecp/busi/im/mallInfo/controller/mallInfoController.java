package com.zengshi.ecp.busi.im.mallInfo.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.busi.im.mallInfo.vo.GdsDetailVO;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoDetailRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.order.dubbo.dto.RCustomerOrdResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdSellerMsgDTO;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsResponse;
import com.zengshi.ecp.order.dubbo.dto.RQueryOrderRequest;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdDetailsRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdMainRSV;
import com.zengshi.ecp.order.dubbo.util.LongUtils;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.prom.dubbo.dto.GdsPromListDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.prom.dubbo.dto.SeckillDiscountDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromQueryRSV;
import com.zengshi.ecp.server.front.dto.BaseStaff;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustManageRSV;
import com.zengshi.paas.utils.CipherUtil;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-im <br>
 * Description: <br>
 * Date:2016年8月4日上午11:30:47  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author zhuqr
 * @version  
 * @since JDK 1.7
 */
@Controller
@RequestMapping(value = "/mallInfo")
public class mallInfoController extends EcpBaseController {
	private static String MODULE = mallInfoController.class.getName();
	
	@Resource
	private IGdsInfoQueryRSV iGdsInfoQueryRSV;
	
    @Resource
    private IOrdMainRSV ordMainRSV;

    @Resource
    private IOrdDetailsRSV ordDetailsRSV;
    
	@Resource
	private ICustManageRSV iCustManageRSV;
	
	@Resource
	private IPromQueryRSV promQueryRSV;
	
	private static String WEB = "1";
	
	@RequestMapping(value = "/gdsDetail")
	public String gdsDetail(Model model,Long gdsId,String joinModel,String custStaffCode) throws Exception{
		GdsInfoReqDTO dto = new GdsInfoReqDTO();
		if (LongUtils.isNotEmpty(gdsId)) {
			dto.setId(gdsId);
		}
		GdsQueryOption[] gdsQueryOptions = new GdsQueryOption[] { GdsQueryOption.BASIC, GdsQueryOption.GDSTYPE,GdsQueryOption.MAINPIC};
		SkuQueryOption[] skuQueryOptions = new SkuQueryOption[] { SkuQueryOption.BASIC, SkuQueryOption.PROP,
				SkuQueryOption.SHOWSTOCK, SkuQueryOption.CAlDISCOUNT };
		dto.setGdsQueryOptions(gdsQueryOptions);
		dto.setSkuQuerys(skuQueryOptions);
		
		
		CustInfoReqDTO custStaff = new CustInfoReqDTO();
		custStaff.setStaffCode(custStaffCode);
		CustInfoResDTO custInfoRes = iCustManageRSV.findCustInfo(custStaff);
		
		BaseStaff baseStaff=new BaseStaff();
		baseStaff.setStaffLevelCode(custInfoRes.getCustLevelCode());
		baseStaff.setStaffCode(custStaffCode);
		dto.setStaff(baseStaff);
		GdsInfoDetailRespDTO resultDto = null;
		try {
			resultDto = iGdsInfoQueryRSV.queryGdsInfoDetail(dto);
		} catch (Exception e) {
			LogUtil.error(MODULE, "无法获取商品详情信息！", e);
		}
		String stockStatus = "";
		String stockStatusDesc = "";
		if (resultDto!=null) {
			if (resultDto.getSkuInfo() != null) {
				stockStatus = GdsUtils.checkStcokStatus(resultDto.getSkuInfo().getRealAmount());
				stockStatusDesc = GdsUtils.checkStcokStatusDesc(resultDto.getSkuInfo().getRealAmount());
			}
			model.addAttribute("gdsType", resultDto.getGdsTypeRespDTO());
		}
		// 获取库存阈值的配置参数
		//getStockParam(model);
		model.addAttribute("stockStatus", stockStatus);
		model.addAttribute("stockStatusDesc", stockStatusDesc);
		model.addAttribute("gdsDetailInfo", resultDto);
        model.addAttribute("joinModel", joinModel);
		return "/im/mallInfo/goods/detail";
	}
	
	
	@RequestMapping(value = "/ordDetail")
	public String ordDetail(Model model,String ordId,String joinModel) throws Exception{
		ROrderDetailsRequest rdto = new ROrderDetailsRequest();
        if (StringUtil.isBlank(ordId)) {
            throw new BusinessException("order.orderid.null.error");
        }
        rdto.setOrderId(ordId);
        rdto.setOper("01");
        ROrderDetailsResponse resp = new ROrderDetailsResponse();
        model.addAttribute("joinModel", joinModel);
/*
        if(!ordMainRSV.queryChkStatus(rdto).isStatus()){

            return "redirect:/homepage";
        }*/
       
        try {
			resp = ordDetailsRSV.queryOrderDetails(rdto);
			   // 订单id
	        model.addAttribute("orderId", ordId);
	        // 主订单相关字段
	        model.addAttribute("sOrderDetailsMain", resp.getsOrderDetailsMain());
	        // 子订单相关字段
	        model.addAttribute("sOrderDetailsSubs", resp.getsOrderDetailsSubs());
	        
	        return "/im/mallInfo/order/detail";
		} catch (Exception e) {
			model.addAttribute("failMsg", e.getMessage());
		}
      
		return "/im/mallInfo/order/detail";
	}
	
	@RequestMapping(value = "/ordAll")
	public String ordAll(Model model,String staffCode,String joinModel,Integer pageNumber) throws Exception{
		  //后场服务所需要的DTO；
       // LogUtil.debug(MODULE, vo.toString());
        //后场服务所需要的DTO；
        RQueryOrderRequest rdor = new RQueryOrderRequest();
        CustInfoReqDTO custInfoReq=new CustInfoReqDTO();
        custInfoReq.setStaffCode(staffCode);
        CustInfoResDTO custInfoResp=iCustManageRSV.findCustInfo(custInfoReq);
        if(custInfoResp!=null){
        	 List<String> statusList=new ArrayList<String>();
        	 statusList.add(OrdConstants.OrderStatus.ORDER_STATUS_SUBMIT);
        	 statusList.add(OrdConstants.OrderStatus.ORDER_STATUS_SEND_ALL);
        	 statusList.add(OrdConstants.OrderStatus.ORDER_STATUS_RECEPT);
        	 statusList.add(OrdConstants.OrderStatus.ORDER_STATUS_CLOSE);
        	 statusList.add(OrdConstants.OrderStatus.ORDER_STATUS_PAID);
        	 statusList.add(OrdConstants.OrderStatus.ORDER_STATUS_SEND_SECTION);
        	 statusList.add(OrdConstants.OrderStatus.ORDER_STATUS_BACKGDS);
        	 statusList.add(OrdConstants.OrderStatus.ORDER_STATUS_REFUND);
             rdor.setStaffId(custInfoResp.getId());
             rdor.setSiteId(1l);
             rdor.setSysType("00");
             rdor.setPageSize(10);
             rdor.setStatusList(statusList);
             rdor.setBegDate(DateUtil.getOffsetMonthsTime(DateUtil.getSysDate(), -1));
             if(pageNumber==null){
            	 pageNumber=1;
             }
             rdor.setPageNo(pageNumber);
             
             model.addAttribute("pageNumber",pageNumber);
  
             PageResponseDTO<RCustomerOrdResponse> rdors = this.ordMainRSV.queryOrderSelectiveStatus(rdor);
             if(rdors==null){
                 rdors = new PageResponseDTO<RCustomerOrdResponse>();
                 rdors.setPageSize(1);
             }else{
            	 model.addAttribute("pageCount",rdors.getPageCount());
            	 model.addAttribute("ordList",rdors.getResult());
             }
            
        }
       
        model.addAttribute("joinModel", joinModel);
  
        return "/im/mallInfo/order/allList";
	}
	
	
	
	/**
	 * 
	 * querySaleList:(获取促销列表). <br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author gxq
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/querysalelist")
	@ResponseBody
	public Map<String, Object> querySaleList(GdsDetailVO gdsDetailVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		//List<PromListRespDTO> list = new ArrayList<PromListRespDTO>();
		GdsPromListDTO list = new GdsPromListDTO();
		try {
			PromRuleCheckDTO promRuleCheckDTO = new PromRuleCheckDTO();
			CustInfoReqDTO custInfoReqDTO = new CustInfoReqDTO();
			promRuleCheckDTO.setSiteId(promRuleCheckDTO.getCurrentSiteId());
			promRuleCheckDTO.setStaffId(custInfoReqDTO.getStaff().getId() + "");
			promRuleCheckDTO.setGdsId(gdsDetailVO.getGdsId());
			promRuleCheckDTO.setChannelValue(WEB);
			promRuleCheckDTO.setShopId(gdsDetailVO.getShopId());
			promRuleCheckDTO.setCalDate(new Date(System.currentTimeMillis()));
			promRuleCheckDTO.setSkuId(gdsDetailVO.getSkuId());
			promRuleCheckDTO.setBasePrice(gdsDetailVO.getRealPrice());
			promRuleCheckDTO.setBuyPrice(gdsDetailVO.getDiscountPrice());
			promRuleCheckDTO.setGdsName(gdsDetailVO.getGdsName());
			promRuleCheckDTO.setShopName(promRuleCheckDTO.getShopName());
			list = promQueryRSV.listPromNew(promRuleCheckDTO);
			if(null != list){
			    SeckillDiscountDTO  seckill = list.getSeckill();
			    if(null != seckill){
			        if(null != seckill.getSystemTime()){
			            map.put("sysdate",seckill.getSystemTime()); 
			        }else{
			            map.put("sysdate",DateUtil.getSysDate()); 
			        }
			        
			    }
			}
		} catch (BusinessException e) {
			map.put("saleList", new ArrayList<PromInfoDTO>());
			LogUtil.error(MODULE, "获取促销列表失败", e);
		}
		map.put("saleList", list);
		return map;
	}
	/**
	 * 
	 * encryptCode:(订单加密串). <br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author zhuqr
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/encryptCode")
	@ResponseBody
	public  Map<String, Object> encryptCode(String jsonStr) {
		 Map<String, Object> map= new HashMap<String, Object>();
		 map.put("jsonStr", CipherUtil.encrypt(jsonStr,  CipherUtil.DES_CBC_ALGORITHM));
		return map;
	}
	
	
	/**
	 * 
	 * @param model
	 * @param orderId
	 * @param sellerMsg
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getSellerMsg")
	@ResponseBody
	public Map<String,String> getSellerMsg(Model model,String orderId)throws Exception{
		Map<String,String> map = new HashMap<>();
 
		try {
			ROrderDetailsRequest rdto = new ROrderDetailsRequest();
			rdto.setOrderId(orderId);
	        rdto.setOper("01");
	        ROrderDetailsResponse resp = new ROrderDetailsResponse();
		    resp = ordDetailsRSV.queryOrderDetails(rdto);
		    String sellerMsg="";
		    if(!"暂无内容".equals(resp.getsOrderDetailsMain().getSellerMsg())){
		    	sellerMsg=resp.getsOrderDetailsMain().getSellerMsg();
		    }
		    map.put("sellerMsg",sellerMsg);
			map.put("success", "ok");
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error(MODULE, "保存卖家备注异常", e);
			map.put("success", "fail");
		}
		return map;
	}
	/**
	 * 
	 * @param model
	 * @param orderId
	 * @param sellerMsg
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value = "/updateSellerMsg")
    @ResponseBody
    public Map<String,String> updateSellerMsg(Model model,String orderId,String sellerMsg)throws Exception{
    	Map<String,String> map = new HashMap<>();
    	ROrdSellerMsgDTO record = new ROrdSellerMsgDTO();
    	record.setOrderId(orderId);
    	record.setSellerMsg(sellerMsg);
    	try {
    		ordMainRSV.updateSellerMsg(record);
    		map.put("success", "ok");
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error(MODULE, "保存卖家备注异常", e);
			map.put("success", "fail");
		}
    	return map;
    }
}
