/** 
 * Project Name:ecp-web-mall 
 * File Name:DemoAction.java 
 * Package Name:com.zengshi.ecp.app.action 
 * Date:2016-2-22下午6:51:19 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.app.action;

import java.sql.Timestamp;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Staff006Req;
import com.zengshi.ecp.app.resp.Staff006Resp;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustManageRSV;
import com.zengshi.paas.utils.StringUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.SystemException;
import com.zengshi.butterfly.core.util.DateUtils;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall Maven Webapp <br>
 * Description: 用户域：修改用户信息action<br>
 * Date:2016-3-4下午3:43:32  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */

@Service("staff006")
@Action(bizcode="staff006", userCheck=true)
@Scope("prototype")
public class Staff006Action extends AppBaseAction<Staff006Req, Staff006Resp> {

    @Resource
    private ICustManageRSV custManageRSV;
    
    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        
        /*1、获取入参*/
        String custPic = this.request.getCustPic();//头像图片id
        String gender = this.request.getGender();//性别
        String custBirthday = this.request.getCustBirthday();//生日
        String nickName = this.request.getNickname();//昵称
        CustInfoReqDTO req = new CustInfoReqDTO();
        req.setId(req.getStaff().getId());
        req.setCustPic(custPic);
        req.setGender(gender);
        if (StringUtil.isNotBlank(custBirthday)) {
            if (DateUtils.formatDate(custBirthday, "yyyy-MM-dd").getTime() > DateUtils.getDate().getTime()) {
                throw new com.zengshi.ecp.server.front.exception.BusinessException("生日不得大于当前日期");
            }
            req.setCustBirthday(Timestamp.valueOf(custBirthday + " 00:00:00"));
        }
        req.setNickname(nickName);
        
        /*2、更新用户信息*/
        custManageRSV.updateCustInfo(req);
        
        /*3、返回结果*/
        this.response.setFlag(true);
    }

}

