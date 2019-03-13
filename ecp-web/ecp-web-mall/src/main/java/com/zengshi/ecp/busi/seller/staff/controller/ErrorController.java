package com.zengshi.ecp.busi.seller.staff.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.system.util.ConstantTool;


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
@RequestMapping(value="/error")
public class ErrorController extends EcpBaseController{


	@RequestMapping(value="/error-v2")
    public String errorv2(Model model)throws BusinessException{
    	return "/seller/main/error/erro-500-v2";
    }
}