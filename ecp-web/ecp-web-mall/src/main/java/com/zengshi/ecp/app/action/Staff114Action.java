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

import com.zengshi.ecp.app.req.Staff114Req;
import com.zengshi.ecp.app.resp.Staff114Resp;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.ShopCollectReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopCollectRSV;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall Maven Webapp <br>
 * Description: 用户域：收藏店铺action<br>
 * Date:2016-3-4下午3:43:32  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */

@Service("staff114")
@Action(bizcode="staff114", userCheck=true)
@Scope("prototype")
public class Staff114Action extends AppBaseAction<Staff114Req, Staff114Resp> {

	@Resource
	private IShopCollectRSV shopCollectRSV;
	
    
    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        
        /*1、获取入参*/
    	Long shopId = this.request.getShopId();
    	if (shopId == null || shopId == 0L) {
    		throw new BusinessException("入参shopId不能为空。");
    	}
        
        /*2、调用业务查询接口，收藏店铺*/
        ShopCollectReqDTO req = new ShopCollectReqDTO();
        req.setShopId(shopId + "");//店铺id
        req.setStaffId(req.getStaff().getId());//当前用户
        //调用业务逻辑，收藏店铺
        shopCollectRSV.insertShopCollect(req);
        
        /*3、返回结果*/
        this.response.setFlag(true);
    }

}

