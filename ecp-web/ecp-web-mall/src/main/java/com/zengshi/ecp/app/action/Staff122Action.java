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

import com.zengshi.ecp.app.req.Staff122Req;
import com.zengshi.ecp.app.resp.Staff122Resp;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.ScoreSignCheckRespDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreSourceReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreSourceResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IScoreInfoRSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.SystemException;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 用户域：查询签到送积分是否已经送过了<br>
 * Date:2016年9月21日下午3:39:28  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */

@Service("staff122")
@Action(bizcode="staff122", userCheck=true)
@Scope("prototype")
public class Staff122Action extends AppBaseAction<Staff122Req, Staff122Resp> {

	 @Resource
	    private IScoreInfoRSV scoreInfoRSV;
	
    
    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        
        /*1、入参*/
    	ScoreSourceReqDTO req = new ScoreSourceReqDTO();
        //查询时间为今天开始~今天结束
        req.setSelDateFrom(DateUtil.getTheDayFirstSecond(DateUtil.getSysDate()));
        req.setSelDateEnd(DateUtil.getTheDayLastSecond(DateUtil.getSysDate()));
        //用户id
        req.setStaffId(req.getStaff().getId());
        req.setSourceType("7001");
        req.setPageNo(0);
        
        /*2、调用业务方法，查询当天签到是否送了积分*/
        PageResponseDTO<ScoreSourceResDTO> scoreSourcePage = scoreInfoRSV.listScoreSource(req);
        if (scoreSourcePage != null && CollectionUtils.isNotEmpty(scoreSourcePage.getResult())) {
        	this.response.setScore(scoreSourcePage.getResult().get(0).getScore());
        } else {
        	this.response.setScore(0L);
        }
        /*3、查询已连续签到多少天*/
        ScoreSignCheckRespDTO sign = scoreInfoRSV.findScoreSignByStaffId(req.getStaff().getId());
        if (sign == null) {
        	this.response.setSignCnt(0L);
        } else {
        	//判断是不是连续  超过一天，则连续签到中断
        	if (DateUtil.getDatesDifference(DateUtil.getSysDate(), sign.getSignDateEnd()) > 1) {
        		this.response.setSignCnt(0L);
        	} else {
        		this.response.setSignCnt(sign.getSignCnt());
        	}
        }
    }
}

