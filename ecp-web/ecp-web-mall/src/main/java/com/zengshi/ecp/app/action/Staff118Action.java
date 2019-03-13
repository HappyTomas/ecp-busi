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

import com.zengshi.ecp.app.req.Staff118Req;
import com.zengshi.ecp.app.resp.Staff118Resp;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.sys.dubbo.dto.MsgInsiteReqDTO;
import com.zengshi.ecp.sys.dubbo.interfaces.IMsgInsiteRSV;
import com.zengshi.paas.utils.StringUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall Maven Webapp <br>
 * Description: 用户域：查询我的消息数量action<br>
 * Date:2016-3-4下午3:43:32  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */

@Service("staff118")
@Action(bizcode="staff118", userCheck=true)
@Scope("prototype")
public class Staff118Action extends AppBaseAction<Staff118Req, Staff118Resp> {

	@Resource
    private IMsgInsiteRSV msgInsiteRSV;
	
    
    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        
        /*1、获取入参*/
    	String readFlag = this.request.getReadFlag();//10:已读；00：未读 ；为空则查全部
    	MsgInsiteReqDTO req = new MsgInsiteReqDTO();
    	//如果不传，则查所有
    	if (StringUtil.isNotBlank(readFlag)) {
    		req.setReadFlag(readFlag);
    	}
    	req.setStaffId(req.getStaff().getId());
        
        /*2、调用业务查询接口，查询我的消息*/
        long res = msgInsiteRSV.countMsgInsiteByStaff(req);
        
        /*3、返回结果*/
        this.response.setMsgCnt(res);
    }
}

