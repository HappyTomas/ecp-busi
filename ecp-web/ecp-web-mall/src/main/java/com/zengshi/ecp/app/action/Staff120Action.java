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

import com.zengshi.ecp.app.req.Staff120Req;
import com.zengshi.ecp.app.resp.Staff120Resp;
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
 * Description: 用户域：标记我的消息为已读action<br>
 * Date:2016-3-4下午3:43:32  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */

@Service("staff120")
@Action(bizcode="staff120", userCheck=true)
@Scope("prototype")
public class Staff120Action extends AppBaseAction<Staff120Req, Staff120Resp> {

	@Resource
    private IMsgInsiteRSV msgInsiteRSV;
	
    
    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        
        /*1、获取入参*/
    	String msgInfoIds = this.request.getMsgInfoIds();
    	MsgInsiteReqDTO req = new MsgInsiteReqDTO();
    	//校验id不能为空
    	if (StringUtil.isBlank(msgInfoIds)) {
    		throw new BusinessException("消息的id为空，无须标记！");
    	}
    	/*2、调用业务方法，标记消息*/
    	String[] msgIds = msgInfoIds.split(",");
    	req.setStaffId(req.getStaff().getId());
    	for (String id : msgIds) {
    		req.setMsgInfoId(Long.parseLong(id));
    		req.setReadFlag("10");//标记为已读
    		msgInsiteRSV.updateMsgInsite(req);
    	}
        /*3、返回结果*/
        this.response.setFlag(true);
    }
}

