package com.zengshi.ecp.busi.seller.staff.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.mvc.annotation.NativeJson;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.goods.gdsdetail.controller.GdsDetailController;
import com.zengshi.ecp.busi.seller.staff.vo.PayWayVO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.PayShopCfgReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.PayShopCfgResDTO;
import com.zengshi.ecp.staff.dubbo.dto.PayWayReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.PayWayResDTO;
import com.zengshi.ecp.staff.dubbo.dto.SellerResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopPayWayRSV;
import com.zengshi.ecp.system.filter.SellerLocaleUtil;
import com.zengshi.ecp.system.util.ConstantTool;
import com.zengshi.paas.utils.FileUtil;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSONObject;


/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2016年4月26日上午11:36:47  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * @author wangbh
 * @version  
 * @since JDK 1.7
 */
@Controller
@RequestMapping(value="/seller")
public class BreadcrumbController extends EcpBaseController{

	@RequestMapping(value="/breakmenu")
	@ResponseBody
    public Map<String,String> getBreakMenu(@RequestParam(value="menuUrl",required=false) String menuUrl)throws BusinessException{
    	return ConstantTool.getBreadCrumb(menuUrl);
    }

}