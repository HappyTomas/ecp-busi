/** 
 * Project Name:ecp-web-mall 
 * File Name:DemoAction.java 
 * Package Name:com.zengshi.ecp.app.action 
 * Date:2016-2-22下午6:51:19 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Staff111Req;
import com.zengshi.ecp.app.resp.Staff111Resp;
import com.zengshi.ecp.server.front.dto.BaseAreaAdminRespDTO;
import com.zengshi.ecp.server.front.util.BaseAreaAdminUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall Maven Webapp <br>
 * Description: 用户域：获取省市县下拉列表数据action<br>
 * Date:2016-3-30下午3:26:26  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */

@Service("staff111")
@Action(bizcode="staff111", userCheck=false)
@Scope("prototype")
public class Staff111Action extends AppBaseAction<Staff111Req, Staff111Resp> {

    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        /*1、获取入参*/
        String areaCode = this.request.getAreaCode();
        
        List<BaseAreaAdminRespDTO> listArea = new ArrayList<BaseAreaAdminRespDTO>();
        //对台湾、香港、澳门，做特殊处理，直接返回当前级别，没有下级
        if ("710000".equals(areaCode) || "820000".equals(areaCode) || "810000".equals(areaCode)) {
        	
        } else {
        	/*2、调用服务，获取业务数据*/
        	listArea = BaseAreaAdminUtil.fetchChildAreaInfos(areaCode);
        }
        
        /*3、返回结果集*/
        this.response.setResList(listArea);
    }
}

