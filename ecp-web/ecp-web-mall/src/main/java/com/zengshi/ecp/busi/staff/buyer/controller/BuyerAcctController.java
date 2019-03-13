package com.zengshi.ecp.busi.staff.buyer.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctTradeReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctTradeResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IAcctManageRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2015年10月21日上午11:41:54  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value="/buyeracct")
public class BuyerAcctController extends EcpBaseController {
	
	@Resource
	private IAcctManageRSV acctManageRSV;	//资金帐户RSV
    
	/**
	 * 
	 * index:(我的钱包首页). <br/> 
	 * 
	 * @author linby
	 * @param model
	 * @return
	 * @throws Exception 
	 * @since JDK 1.7
	 */
    @RequestMapping(value="/index")
    public String index(Model model, EcpBasePageReqVO vo) throws Exception{
    	@SuppressWarnings("rawtypes")
		EcpBasePageRespVO<Map> respVO = this.getAcctBalanceList(model, vo, null);
    	super.addPageToModel(model, respVO);
        return "/staff/buyer/acct/acct-index";
    }
    
    /**
     * 
     * tabBalance:(资金余额TAB页). <br/> 
     * 
     * @author linby
     * @param model
     * @return
     * @throws Exception 
     * @since JDK 1.7
     */
    @RequestMapping(value="/tab/balance")
    public String tabBalance(Model model, EcpBasePageReqVO vo) throws Exception{
    	@SuppressWarnings("rawtypes")
		EcpBasePageRespVO<Map> respVO = this.getAcctBalanceList(model, vo, null);
    	super.addPageToModel(model, respVO);
    	return "/staff/buyer/acct/tab/acct-balance";
    }
    
    /**
     * 
     * tabDetail:(交易明细TAB页). <br/> 
     * 
     * @author linby
     * @param model
     * @return
     * @throws Exception 
     * @since JDK 1.7
     */
    @RequestMapping(value="/tab/detail")
    public String tabDetail(Model model, EcpBasePageReqVO vo) throws Exception{
    	@SuppressWarnings("rawtypes")
    	EcpBasePageRespVO<Map> respVO = this.getTradeDetailList(model, vo, null, null);
    	super.addPageToModel(model, respVO);
    	model.addAttribute("startTime", DateUtil.getTimeThisMonthFirstSec(DateUtil.getSysDate()));
    	model.addAttribute("endTime", DateUtil.getTheDayLastSecond(DateUtil.getSysDate()));
    	return "/staff/buyer/acct/tab/acct-detail";
    }

    /**
     * 
     * tabBalanceList:(资金余额分页信息查询). <br/> 
     * 
     * @author linby
     * @param model
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.7
     */
    @RequestMapping(value="/tab/balancelist")
    public String tabBalanceList(Model model, EcpBasePageReqVO vo, @RequestParam("shopId") String shopId ) throws Exception{
    	@SuppressWarnings("rawtypes")
		EcpBasePageRespVO<Map> respVO = this.getAcctBalanceList(model, vo, shopId);
    	super.addPageToModel(model, respVO);
    	return "/staff/buyer/acct/tab/page/balance-list";
    }
    
    /**
     * 
     * tabDetailList:(交易明细分页信息查询). <br/> 
     * 
     * @author linby
     * @param model
     * @param vo
     * @param startTime
     * @return
     * @throws Exception 
     * @since JDK 1.7
     */
    @RequestMapping(value="/tab/detaillist")
    public String tabDetailList(Model model, EcpBasePageReqVO vo, @RequestParam("startTime") String startTime,
    		@RequestParam("endTime") String endTime) throws Exception{
    	@SuppressWarnings("rawtypes")
		EcpBasePageRespVO<Map> respVO = this.getTradeDetailList(model, vo, startTime, endTime);
    	super.addPageToModel(model, respVO);
    	return "/staff/buyer/acct/tab/page/detail-list";
    }
    
    /**
     * 
     * getAcctBalanceList:(获取并重装用户资金帐户详情结果集). <br/> 
     * 
     * @author linby
     * @param model
     * @param vo
     * @param shopId
     * @return
     * @throws Exception 
     * @since JDK 1.7
     */
    @SuppressWarnings("rawtypes")
	private EcpBasePageRespVO<Map> getAcctBalanceList(Model model, EcpBasePageReqVO vo, String shopId) throws Exception{
    	AcctInfoReqDTO dto = vo.toBaseInfo(AcctInfoReqDTO.class);
    	if(StringUtil.isNotBlank(shopId)){
    		dto.setShopId(Long.valueOf(shopId));
    	}
    	Map<String, String> shopMap = new HashMap<String, String>();
    	PageResponseDTO<AcctInfoResDTO> res = acctManageRSV.queryAcctInfoByStaff(dto);
    	if(CollectionUtils.isNotEmpty(res.getResult())){
    		List<AcctInfoResDTO> list = new ArrayList<AcctInfoResDTO>();
    		for (AcctInfoResDTO ie : res.getResult()) {
				ie.setStatus(StaffConstants.Acct.ACCT_STATUS_ENABLE.equals(ie.getStatus())?"生效":"失效");//TODO 文字
    			list.add(ie);
    			if(!shopMap.containsKey(ie.getShopId().toString()))
    			{
    			    shopMap.put(ie.getShopId().toString(), ie.getShopName());
    			}
			}
    		res.setResult(list);
    	}
    	model.addAttribute("shopMap", shopMap);
		EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(res);
    	return respVO;
    }
    /**
     * 
     * getTradeDetailList:(获取并重装资金帐户交易信息结果集). <br/> 
     * 
     * @author linby
     * @param model
     * @param vo
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception 
     * @since JDK 1.7
     */
    @SuppressWarnings("rawtypes")
	private EcpBasePageRespVO<Map> getTradeDetailList(Model model, EcpBasePageReqVO vo, String startTime, String endTime) throws Exception{
    	AcctTradeReqDTO dto = vo.toBaseInfo(AcctTradeReqDTO.class);
    	if(StringUtil.isBlank(startTime)){
    		dto.setStartTime(DateUtil.getTimeThisMonthFirstSec(DateUtil.getSysDate()));//查询周期,本月1号到当前时刻
    	}else{
    		dto.setStartTime(DateUtil.getTimestamp(startTime));
    	}
    	if(StringUtil.isBlank(endTime)){
    		dto.setEndTime(DateUtil.getTheDayLastSecond(DateUtil.getSysDate()));//默认取当天最后一秒
    	}else{
    		dto.setEndTime(DateUtil.getTimestamp(endTime+ " 23:59:59","yyyy-MM-dd HH:mm:ss"));//页面得到日期，加上时刻以获取当天所有记录
    	}
    	dto.setStaffId(dto.getStaff().getId());
    	PageResponseDTO<AcctTradeResDTO> res = acctManageRSV.listAcctTrade(dto);
    	if(CollectionUtils.isNotEmpty(res.getResult())){
    		List<AcctTradeResDTO> list = new ArrayList<AcctTradeResDTO>();
    		for (AcctTradeResDTO ie : res.getResult()) {
    			String tradeType = ie.getTradeType();
    			if(StaffConstants.Trade.TRADE_TYPE_PAY.equals(tradeType)){
    				tradeType = "购买商品付款";
    			}else if(StaffConstants.Trade.TRADE_TYPE_RECHARGE.equals(tradeType)){
    				tradeType = "充值";
    			}else if(StaffConstants.Trade.TRADE_TYPE_CANCEL.equals(tradeType)){
    				tradeType = "取消订单";
    			}else if ("04".equals(tradeType)){
    				tradeType = "订单退款";
    			}else if ("05".equals(tradeType)){
    				tradeType = "订单退货";
    			}else{
    				tradeType = "";
    			}
    			ie.setTradeType(tradeType);
				list.add(ie);
			}
    		res.setResult(list);
    	}
		EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(res);
    	return respVO;
    }
    
}

