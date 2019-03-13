package com.zengshi.ecp.app.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Ord020Req;
import com.zengshi.ecp.app.resp.Ord020Resp;
import com.zengshi.ecp.order.dubbo.dto.ROrdExpressDetailsResp;
import com.zengshi.ecp.order.dubbo.interfaces.IOrderExpressRSV;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 查询购物车商品数量<br>
 * Copyright (c) 2017 ZengShi All Rights Reserved <br>
 * 
 * @author linw
 * @version
 * @since JDK 1.6
 */
@Service("ord020")
@Action(bizcode = "ord020", userCheck = true)
@Scope("prototype")
public class Ord020Action  extends AppBaseAction<Ord020Req, Ord020Resp>{

	@Resource
	private IOrderExpressRSV orderExpressRSV;
	
	@Override
	protected void getResponse() throws BusinessException, SystemException, Exception {
		// TODO Auto-generated method stub
		List<ROrdExpressDetailsResp> list = orderExpressRSV.queryOrderExpressDetailList(this.request.getOrderId());
		this.response.setExpressDetailsResp(list);
	}

}
