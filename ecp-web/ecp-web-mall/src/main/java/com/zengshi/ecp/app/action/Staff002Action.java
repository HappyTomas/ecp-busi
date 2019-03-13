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

import com.zengshi.ecp.app.req.Staff002Req;
import com.zengshi.ecp.app.resp.Staff002Resp;
import com.zengshi.ecp.base.util.AppUserCacheUtils;
import com.zengshi.ecp.server.front.dto.BaseStaff;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustManageRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall Maven Webapp <br>
 * Description: 用户域：用户注册action<br>
 * Date:2016-3-4下午3:43:32  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */

@Service("staff002")
@Action(bizcode="staff002", userCheck=false)
@Scope("prototype")
public class Staff002Action extends AppBaseAction<Staff002Req, Staff002Resp> {

    @Resource
    private ICustManageRSV custManageRSV;
    
    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        
        /*1、获取入参*/
        CustInfoReqDTO req = new CustInfoReqDTO();
        ObjectCopyUtil.copyObjValue(this.request, req, null, false);
        req.setCustType(StaffConstants.custInfo.CUST_TYPE_P);
        
        /*2、调用业务接口，保存注册用户信息*/
        long record = custManageRSV.saveCustInfo(req);
        
        /*注册成功，带出用户详细信息*/
        if (record > 0) {
            CustInfoReqDTO custReq = new CustInfoReqDTO();
            custReq.setStaffCode(this.request.getStaffCode());
            CustInfoResDTO custRes = custManageRSV.findCustInfo(custReq);
            BaseStaff baseStaff = new BaseStaff();
            baseStaff.setId(custRes.getId());
            baseStaff.setStaffCode(custRes.getStaffCode());
            baseStaff.setStaffLevelCode(custRes.getCustLevelCode());
            baseStaff.setStaffClass(custRes.getCustType());
            String tocken = AppUserCacheUtils.saveUser2Cache(baseStaff);
            
            /*3、返回结果,，往缓存里放入一个登录授权key*/
            this.response.setTocken(tocken);
            
        }
        
    }

}

