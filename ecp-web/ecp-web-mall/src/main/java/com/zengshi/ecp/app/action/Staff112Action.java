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

import com.zengshi.ecp.app.req.Staff112Req;
import com.zengshi.ecp.app.resp.Staff112Resp;
import com.zengshi.ecp.staff.dubbo.dto.CustAddrReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustAddrRSV;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall Maven Webapp <br>
 * Description: 用户域：设置默认收货地址action<br>
 * Date:2016-3-4下午3:43:32  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */

@Service("staff112")
@Action(bizcode="staff112", userCheck=true)
@Scope("prototype")
public class Staff112Action extends AppBaseAction<Staff112Req, Staff112Resp> {

    @Resource
    private ICustAddrRSV custAddrRsv;
    
    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        
        /*1、获取入参*/
        CustAddrReqDTO addrReq = new CustAddrReqDTO();
        addrReq.setId(this.request.getId());
        addrReq.setStaffId(addrReq.getStaff().getId());
        
        /*2、调用业务查询接口，设置默认收货地址*/
        custAddrRsv.installDefauleAddr(addrReq);
        
        /*3、返回结果*/
        this.response.setFlag(true);
    }

}

