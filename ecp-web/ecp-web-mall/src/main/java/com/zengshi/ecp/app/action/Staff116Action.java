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

import com.zengshi.ecp.app.req.Staff116Req;
import com.zengshi.ecp.app.resp.Staff116Resp;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.ShopCollectReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopCollectRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.SystemException;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall Maven Webapp <br>
 * Description: 用户域：查询店铺信息action<br>
 * Date:2016-3-4下午3:43:32  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */

@Service("staff116")
@Action(bizcode="staff116", userCheck=false)
@Scope("prototype")
public class Staff116Action extends AppBaseAction<Staff116Req, Staff116Resp> {

	@Resource
	private IShopInfoRSV shopInfoRSV;
	
	@Resource
	private IShopCollectRSV shopCollectRSV;
	
    
    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        
        /*1、获取入参*/
    	Long shopId = this.request.getShopId();
    	if (shopId == null || shopId == 0L) {
    		throw new BusinessException("入参shopId不能为空。");
    	}
        
        /*2、调用业务查询接口，查询店铺信息*/
        ShopInfoResDTO res = shopInfoRSV.findShopInfoByShopID(shopId);
        if (res != null) {
        	/*3、返回结果*/
            ObjectCopyUtil.copyObjValue(res, this.response, null, false);
        }
        this.response.setCollectFlag("0");
        /*3、如果用户 登录了，查询下该店铺是否被用户收藏*/
        ShopCollectReqDTO collect = new ShopCollectReqDTO();
        if (collect.getStaff() != null && collect.getStaff().getId() != 0L) {
        	collect.setStaffId(collect.getStaff().getId());
        	collect.setShopId(shopId + "");
        	PageResponseDTO<ShopInfoResDTO> shopPage = shopCollectRSV.listShopCollectForFav(collect);
        	if (shopPage != null && CollectionUtils.isNotEmpty(shopPage.getResult())) {
        		this.response.setCollectFlag("1");
        	}
        }
    }
}

