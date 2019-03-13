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

import com.zengshi.ecp.app.req.Staff005Req;
import com.zengshi.ecp.app.resp.Staff005Resp;
import com.zengshi.ecp.base.util.AppUserCacheUtils;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall Maven Webapp <br>
 * Description: 用户域：退出登录action<br>
 * Date:2016-3-4下午3:43:32  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */

@Service("staff005")
@Action(bizcode="staff005", userCheck=true)
@Scope("prototype")
public class Staff005Action extends AppBaseAction<Staff005Req, Staff005Resp> {

    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        
        /*1、获取入参*/
        String tocken = this.request.getTocken();//登录编码（可以是登录名、手机号、邮箱）
        
        /*2、清除用户登录授权key*/
        AppUserCacheUtils.removeUserFromCache(tocken);
        
        /*3、返回结果*/
        this.response.setFlag(true);
    }

}

