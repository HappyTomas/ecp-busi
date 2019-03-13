package com.zengshi.ecp.app.action;


import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Coup004Req;
import com.zengshi.ecp.app.resp.Coup004Resp;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupCallBackReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupInfoReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupInfoRespDTO;
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupDetailRSV;
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupRSV;
import com.zengshi.ecp.coupon.dubbo.util.CouponConstants;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustInfoRSV;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 *   
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 我的优惠券列表<br>
 * Date:2016年3月7日上午10:36:56  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huanghe5
 * @version  
 * @since JDK 1.7
 */
@Service("coup004")
@Action(bizcode = "coup004", userCheck = true)
@Scope("prototype")
public class Coup004Action extends AppBaseAction<Coup004Req, Coup004Resp> {

	//private static final String MODULE = Coup004Action.class.getName();
	
	public final static String RESULT_FLAG_SUCCESS = "1";
	
	@Resource 
	private ICoupDetailRSV coupDetailRSV;
	
	@Resource 
    private ICoupRSV coupRSV;
   
    @Resource
    private ICustInfoRSV custInfoRSV;
	
    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
    	//01 获取入参
		Long coupId = this.request.getCoupId();//用户id
		//封装入参
		CoupCallBackReqDTO  dto=new CoupCallBackReqDTO();
		dto.setCoupId(coupId);
		dto.setStaffId(dto.getStaff().getId());
		dto.setCoupSource(CouponConstants.CoupDetail.COUP_SOURCE_10);//主动领取
		dto.setCoupSum(1);//1张
		 //获取当前登录用户
		CustInfoReqDTO custReq = new CustInfoReqDTO();
		custReq.setId(custReq.getStaff().getId());
		//获取用户更多信息
		CustInfoResDTO custRes = custInfoRSV.getCustInfoById(custReq);
		//获得用户等级
		dto.setCustLevel(custRes.getCustLevelCode());
		CoupInfoReqDTO coupInfoReqDTO=new CoupInfoReqDTO();
		coupInfoReqDTO.setId(Long.valueOf(coupId));
		CoupInfoRespDTO respDTO=coupRSV.queryCoupInfo(coupInfoReqDTO);
		Long shopId=-1l;
		if(respDTO!=null){
		    shopId=respDTO.getShopId();
		}
		//根据优惠券id获得平台0 店铺级别
		dto.setShopId(shopId);
		coupDetailRSV.saveCoupGain(dto);
    	this.response.setExceStatus(RESULT_FLAG_SUCCESS);
    }

}
