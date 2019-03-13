/** 
 * Project Name:ecp-web-mall 
 * File Name:DemoAction.java 
 * Package Name:com.zengshi.ecp.app.action 
 * Date:2016-2-22下午6:51:19 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.app.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.DemoReq;
import com.zengshi.ecp.app.resp.DemoResp;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2016-2-22下午6:51:19  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */

@Service("demo")
@Action(bizcode="demo", userCheck=false)
@Scope("prototype")
public class DemoAction extends AppBaseAction<DemoReq, DemoResp> {

    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        BaseInfo info = new BaseInfo();
        if(info.getStaff() == null){
            this.response.setMsg("staff is null");
            this.response.setName("staff");
        } else {
            this.response.setMsg("demo info; staffCode:"+info.getStaff().getStaffCode()+";id:"+info.getStaff().getId());
            this.response.setName(info.getStaff().getStaffCode());
        }
    }

}

