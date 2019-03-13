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

import com.zengshi.ecp.app.req.Staff004Req;
import com.zengshi.ecp.app.resp.Staff004Resp;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IAuthStaffRSV;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall Maven Webapp <br>
 * Description: 用户域：用户修改密码action<br>
 * Date:2016-3-4下午3:43:32  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */

@Service("staff004")
@Action(bizcode="staff004", userCheck=true)
@Scope("prototype")
public class Staff004Action extends AppBaseAction<Staff004Req, Staff004Resp> {

    @Resource
    private IAuthStaffRSV authStaffRSV;
    
    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        
        /*1、获取入参*/
        AuthStaffReqDTO req = new AuthStaffReqDTO();
        req.setId(req.getStaff().getId());//用户id 
        req.setStaffPasswd(this.request.getNewPwd());//新密码
        req.setStaffPwdOld(this.request.getOldPwd());//旧密码
        
        /*2、调用业务接口，修改用户密码*/
        authStaffRSV.updatePwdById(req);
        
        /*3、返回结果*/
        this.response.setFlag(true);
        
    }

}

