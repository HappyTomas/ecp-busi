/** 
 * Project Name:ecp-web-mall 
 * File Name:DemoAction.java 
 * Package Name:com.zengshi.ecp.app.action 
 * Date:2016-2-22下午6:51:19 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.app.action;



import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Staff121Req;
import com.zengshi.ecp.app.resp.Staff121Resp;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreResultResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreSignCheckRespDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IScoreCaclRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IScoreInfoRSV;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 用户域：签到送积分<br>
 * Date:2016年9月21日下午3:39:28  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */

@Service("staff121")
@Action(bizcode="staff121", userCheck=true)
@Scope("prototype")
public class Staff121Action extends AppBaseAction<Staff121Req, Staff121Resp> {

	@Resource
	private IScoreCaclRSV scoreCaclRSV;
	
	@Resource
	private IScoreInfoRSV scoreInfoRSV;
	
    
    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        
        /*1、入参*/
    	CustInfoReqDTO req = new CustInfoReqDTO();
        req.setId(req.getStaff().getId());
        req.setStaffCode(req.getStaff().getStaffCode());
        
        /*2、调用业务方法，赠送积分，签到送积分行为类型：07*/
        ScoreResultResDTO result = scoreCaclRSV.updateScore("07", req, null);
        if (result != null && result.getScore() != null && result.getScore() != 0L) {
        	this.response.setScore(result.getScore());
        } else {
        	this.response.setScore(0L);
        }
        /*3、查询已连续签到多少天*/
        ScoreSignCheckRespDTO sign = scoreInfoRSV.findScoreSignByStaffId(req.getStaff().getId());
        if (sign == null) {
        	this.response.setSignCnt(0L);
        } else {
        	this.response.setSignCnt(sign.getSignCnt());
        }
    }
}

