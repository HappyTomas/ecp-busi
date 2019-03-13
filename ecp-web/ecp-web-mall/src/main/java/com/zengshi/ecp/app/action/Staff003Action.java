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

import com.zengshi.ecp.app.req.Staff003Req;
import com.zengshi.ecp.app.resp.Staff003Resp;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustInfoRSV;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall Maven Webapp <br>
 * Description: 用户域：获取用户信息action<br>
 * Date:2016-3-4下午3:43:32  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */

@Service("staff003")
@Action(bizcode="staff003", userCheck=true)
@Scope("prototype")
public class Staff003Action extends AppBaseAction<Staff003Req, Staff003Resp> {

    @Resource
    private ICustInfoRSV custInfoRSV;
    
    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        
        /*1、获取入参*/
        CustInfoReqDTO req = new CustInfoReqDTO();
        req.setId(req.getStaff().getId());
        
        /*2、调用业务接口，获取用户信息*/
        CustInfoResDTO custInfo = custInfoRSV.getCustInfoById(req);
        
        /*3、返回结果*/
        ObjectCopyUtil.copyObjValue(custInfo, this.response, null, false);
        
        //头像处理成url
        if (StringUtil.isBlank(custInfo.getCustPic())) {
            custInfo.setCustPic(ImageUtil.getDefaultImageId());
        }
        this.response.setCustPic(ImageUtil.getImageUrl(custInfo.getCustPic()));
        
    }

}

