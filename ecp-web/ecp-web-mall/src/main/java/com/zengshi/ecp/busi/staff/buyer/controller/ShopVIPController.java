package com.zengshi.ecp.busi.staff.buyer.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.staff.dubbo.dto.ShopVipRealSIDXReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopManageRSV;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 我的店铺会员<br>
 * Date:2016年5月20日下午2:58:38  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.7
 */
@Controller
@RequestMapping(value="/buyer/shopvip")
public class ShopVIPController extends EcpBaseController {
	
	@Resource
	private IShopManageRSV shopManageRSV;  //店铺管理
	
	/**
	 * 
	 * index:(跳转到首页). <br/> 
	 * 
	 * @author linby
	 * @param model
	 * @param vo
	 * @return
	 * @throws Exception 
	 * @since JDK 1.7
	 */
	@RequestMapping(value="/index")
    public String index(Model model, EcpBasePageReqVO vo) throws Exception{
		EcpBasePageRespVO<Map> respVO = this.getShopVIPRealList(model, vo, null);
    	super.addPageToModel(model, respVO);
		return "/staff/buyer/shopvip/index";
	}
	
	/**
	 * 
	 * shopVIPRealList:(分页控件). <br/> 
	 * 
	 * @author linby
	 * @param model
	 * @param vo
	 * @param shopName
	 * @return
	 * @throws Exception 
	 * @since JDK 1.7
	 */
	@RequestMapping("/viplist")
	public String shopVIPRealList(Model model, EcpBasePageReqVO vo, String shopName) throws Exception{
		EcpBasePageRespVO<Map> respVO = this.getShopVIPRealList(model, vo, shopName);
    	super.addPageToModel(model, respVO);
		return "/staff/buyer/shopvip/page/list";
	}
	
	
	/**
	 * 
	 * getShopVIPRealList:(这里用一句话描述这个方法的作用). <br/> 
	 * 
	 * @author linby
	 * @param model
	 * @param vo
	 * @param shopName
	 * @return
	 * @throws Exception 
	 * @since JDK 1.7
	 */
	private EcpBasePageRespVO<Map> getShopVIPRealList(Model model, EcpBasePageReqVO vo, String shopName) throws Exception{
		ShopVipRealSIDXReqDTO dto = vo.toBaseInfo(ShopVipRealSIDXReqDTO.class);
		if(StringUtil.isNotBlank(shopName)){
			dto.setShopName(shopName);
		}
		dto.setStaffId(dto.getStaff().getId());//当前会员
		
		EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(shopManageRSV.listShopVipReal(dto));
		return respVO;
	}
	

}
