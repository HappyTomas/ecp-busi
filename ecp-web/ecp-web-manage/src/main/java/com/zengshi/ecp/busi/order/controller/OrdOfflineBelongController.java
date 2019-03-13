package com.zengshi.ecp.busi.order.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.order.dubbo.dto.RGoodSaleRequest;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdOfflineBelongRSV;
import com.zengshi.paas.utils.LogUtil;


@Controller
@RequestMapping(value = "/ordOfflineBelong")
public class OrdOfflineBelongController extends EcpBaseController{
	
	  private static final String MODULE = OrdOfflineBelongController.class.getName();
	  
	  @Resource(name="ordOfflineBelongRSV")
	  private IOrdOfflineBelongRSV ordOfflineBelong;
	  
	  @RequestMapping(value = "/deal")
	  public String dealOrdOffline() {
		  try{
			  ordOfflineBelong.dealOrdOffline();
		  }catch (Exception e) {
			  LogUtil.info(MODULE, e.getMessage()); 
			  return "失败";
		  }
        return "成功";
	  }
	  
	  @RequestMapping(value = "/updatePT")
	  public String updatePayType() {
		  try{
			  LogUtil.info(MODULE,"同步支付类型开始updatePTStart...");
			  RGoodSaleRequest rGoodSaleRequest = new RGoodSaleRequest();
			  rGoodSaleRequest.setPageNo(1);
			  rGoodSaleRequest.setPageSize(100);
			  Long num = ordOfflineBelong.updateOrdSubShopIdx(rGoodSaleRequest);
		      if(num>1){
		    	  while(true){
		    		  rGoodSaleRequest.setPageNo(1);
		    		  num = ordOfflineBelong.updateOrdSubShopIdx(rGoodSaleRequest);
		    		  LogUtil.info(MODULE,"剩下页数："+num);
		    		  if(num==1){
		    			  break;
		    		  }
		    	  }
		      }
		      LogUtil.info(MODULE,"同步支付类型结束updatePTEnd...");
		  }catch (Exception e) {
			  LogUtil.info(MODULE, e.getMessage()); 
			  return "失败";
		  }
        return "成功";
	  }
	  
	  @RequestMapping(value = "/updateAllPT")
	  public String updateAllPayType() {
		  try{
			  LogUtil.info(MODULE,"同步支付类型开始updateAllPTStart...");
			  RGoodSaleRequest rGoodSaleRequest = new RGoodSaleRequest();
			  rGoodSaleRequest.setPageNo(1);
			  rGoodSaleRequest.setPageSize(100);
			  Long num = ordOfflineBelong.updateAllOrdSubShopIdx(rGoodSaleRequest);
		      if(num>1){
		    	  while(true){
			    		  rGoodSaleRequest.setPageNo(1);
			    		  num = ordOfflineBelong.updateAllOrdSubShopIdx(rGoodSaleRequest);
			    		  LogUtil.info(MODULE,"剩下页数："+num);
			    		  if(num==1){
			    			  break;
			    		  }
			    	  }
		      }
		      LogUtil.info(MODULE,"同步支付类型结束updateAllPTEnd...");
		  }catch (Exception e) {
			  LogUtil.info(MODULE, e.getMessage()); 
			  return "失败";
		  }
        return "成功";
	  }
}