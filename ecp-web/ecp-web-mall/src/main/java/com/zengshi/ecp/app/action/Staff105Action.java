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

import com.zengshi.ecp.app.req.Staff105Req;
import com.zengshi.ecp.app.resp.Staff105Resp;
import com.zengshi.ecp.staff.dubbo.dto.CustAddrReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustAddrResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustAddrRSV;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall Maven Webapp <br>
 * Description: 用户域：收货地址新增action<br>
 * Date:2016-3-4下午3:43:32  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */

@Service("staff105")
@Action(bizcode="staff105", userCheck=true)
@Scope("prototype")
public class Staff105Action extends AppBaseAction<Staff105Req, Staff105Resp> {

    @Resource
    private ICustAddrRSV custAddrRsv;
    
    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        
        /*1、获取入参*/
        CustAddrReqDTO addrReq = new CustAddrReqDTO();
        addrReq.setStaffId(addrReq.getStaff().getId());
        addrReq.setContactName(this.request.getContactName());//联系 人名称
        addrReq.setContactPhone(this.request.getContactPhone());//联系人手机
        addrReq.setChnlAddress(this.request.getChnlAddress());//联系地址
        addrReq.setCountyCode(this.request.getCountyCode());
        addrReq.setCountryCode(this.request.getCountryCode());
        addrReq.setProvince(this.request.getProvince());
        addrReq.setCityCode(this.request.getCityCode());
        addrReq.setPostCode(this.request.getPostCode());//邮编
        addrReq.setUsingFlag(this.request.getUsingFlag());//是否默认地址
        
        /*2、调用业务查询接口，保存用户收货地址*/
        CustAddrResDTO addrRes = custAddrRsv.saveCustAddr(addrReq);
        
        /*3、返回结果*/
        if (addrRes != null && addrRes.getId() != null && addrRes.getId() != 0L) {
            this.response.setId(addrRes.getId());
            this.response.setFlag(true);
        } else {
            this.response.setId(0L);
            this.response.setFlag(false);
        }
    }

}

