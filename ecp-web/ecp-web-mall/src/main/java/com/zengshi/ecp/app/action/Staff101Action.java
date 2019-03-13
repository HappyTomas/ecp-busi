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

import com.zengshi.ecp.app.req.Staff101Req;
import com.zengshi.ecp.app.resp.Staff101Resp;
import com.zengshi.ecp.server.front.dto.BaseStaff;
import com.zengshi.ecp.staff.dubbo.dto.ScoreInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IScoreInfoRSV;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall Maven Webapp <br>
 * Description: 用户域：查询我的积分的action<br>
 * Date:2016-3-4下午3:43:32  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */

@Service("staff101")
@Action(bizcode="staff101", userCheck=true)
@Scope("prototype")
public class Staff101Action extends AppBaseAction<Staff101Req, Staff101Resp> {

    @Resource
    private IScoreInfoRSV scoreInfoRSV;
    
    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        
        /*1、获取入参*/
        BaseStaff baseStaff = new BaseStaff();

        /*2、调用业务查询接口，查询用户积分账户*/
        ScoreInfoResDTO scoreInfo = scoreInfoRSV.findScoreInfoByStaffId(baseStaff.getId());
        
        /*3、返回结果*/
        this.response.setScore(scoreInfo.getScoreBalance());//用户的可用积分
        
    }

}

