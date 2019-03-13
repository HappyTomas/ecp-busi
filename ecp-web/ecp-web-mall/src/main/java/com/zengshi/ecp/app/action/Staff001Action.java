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

import com.zengshi.ecp.app.req.Staff001Req;
import com.zengshi.ecp.app.resp.Staff001Resp;
import com.zengshi.ecp.base.util.AppUserCacheUtils;
import com.zengshi.ecp.server.front.dto.BaseStaff;
import com.zengshi.ecp.staff.dubbo.dto.LoginResultResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IAuthLoginRSV;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall Maven Webapp <br>
 * Description: 用户域：用户登录action<br>
 * Date:2016-3-4下午3:43:32  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */

@Service("staff001")
@Action(bizcode="staff001", userCheck=false)
@Scope("prototype")
public class Staff001Action extends AppBaseAction<Staff001Req, Staff001Resp> {

    @Resource
    private IAuthLoginRSV authLoginRSV;
    
    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        
        /*1、获取入参*/
        String loginCode = this.request.getLoginCode();//登录编码（可以是登录名、手机号、邮箱）
        
        String password = this.request.getPassword();//密码
        
        /*2、调用业务接口，验证用户登录*/
        LoginResultResDTO loginRes = authLoginRSV.checkLogin(loginCode, password);
        
        /*3、返回结果*/
        ObjectCopyUtil.copyObjValue(loginRes, this.response, null, false);
        
        /*4、登录成功后，往缓存里放入一个登录授权key*/
        if (loginRes.getId() != null && loginRes.getId() != 0L) {
            BaseStaff user = new BaseStaff();
            user.setId(loginRes.getId());
            user.setStaffCode(loginRes.getStaffCode());
            user.setStaffLevelCode(loginRes.getCustLevelCode());
            user.setStaffClass(loginRes.getCustType());
            String tocken = AppUserCacheUtils.saveUser2Cache(user);
            this.response.setTocken(tocken);
        }
        
    }

}

