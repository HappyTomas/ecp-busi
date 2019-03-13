package com.zengshi.ecp.app.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Coup002Req;
import com.zengshi.ecp.app.resp.Coup004Resp;
import com.zengshi.ecp.busi.coup.CoupConst;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupMeReqDTO;
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupDetailRSV;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 *   
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 我的优惠券-删除<br>
 * Date:2016年3月7日上午10:36:56  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huanghe5
 * @version  
 * @since JDK 1.7
 */
@Service("coup003")
@Action(bizcode = "coup003", userCheck = true)
@Scope("prototype")
public class Coup003Action extends AppBaseAction<Coup002Req, Coup004Resp> {

//	private static final String MODULE = Coup003Action.class.getName();
	@Resource
    private ICoupDetailRSV coupDetailRSV;
	
    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
    	//01 获取入参
    	Long id = this.request.getId();//用户id
    	Long staffId = this.request.getStaffId();//用户id
        CoupMeReqDTO coupMeReqDTO = new CoupMeReqDTO();
        coupMeReqDTO.setId(id);
        coupMeReqDTO.setStaffId(staffId);
        //02 调用接口查询我的优惠券
        try {
			coupDetailRSV.deleteCoupDetail(coupMeReqDTO);
		} catch (Exception e) {
			this.response.setExceStatus(CoupConst.CoupKey.exceStatus_0);
		}
        //03.返回结果
        this.response.setExceStatus(CoupConst.CoupKey.exceStatus_1);
        
    }

}
