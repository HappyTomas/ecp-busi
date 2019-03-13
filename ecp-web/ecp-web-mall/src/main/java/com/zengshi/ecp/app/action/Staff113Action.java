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

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Staff113Req;
import com.zengshi.ecp.app.resp.Staff113Resp;
import com.zengshi.ecp.app.resp.staff.CollectionShopInfo;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.order.dubbo.dto.RGoodSaleRequest;
import com.zengshi.ecp.order.dubbo.interfaces.IReportGoodPayedRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopCollectReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopCollectRSV;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.SystemException;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall Maven Webapp <br>
 * Description: 用户域：我收藏的店铺action<br>
 * Date:2016-3-4下午3:43:32  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */

@Service("staff113")
@Action(bizcode="staff113", userCheck=true)
@Scope("prototype")
public class Staff113Action extends AppBaseAction<Staff113Req, Staff113Resp> {

	@Resource
	private IShopCollectRSV shopCollectRSV;
	
	@Resource(name = "reportGoodPayedRSV")
    private IReportGoodPayedRSV reportGoodPayedRSV;
	
	@Resource(name = "gdsInfoQueryRSV")
    private IGdsInfoQueryRSV gdsInfoQueryRSV;
    
    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        
        /*1、获取入参*/
    	int pageNo = this.request.getPageNo();//页数
        int pageSize = this.request.getPageSize();//页面展现的数量
    	ShopCollectReqDTO collect = new ShopCollectReqDTO();
        collect.setStaffId(collect.getStaff().getId());
        collect.setPageNo(pageNo);
        collect.setPageSize(pageSize);
        
        /*2、调用业务查询接口，设置默认收货地址*/
        PageResponseDTO<ShopInfoResDTO> shopPage = shopCollectRSV.listShopCollectForFav(collect);
        List<CollectionShopInfo> resList = new ArrayList<CollectionShopInfo>();
        if (shopPage != null && CollectionUtils.isNotEmpty(shopPage.getResult())) {
        	for (ShopInfoResDTO shop : shopPage.getResult()) {
        		CollectionShopInfo collectonShop = new CollectionShopInfo();
        		ObjectCopyUtil.copyObjValue(shop, collectonShop, null, false);
        		//查询店铺销售的商品
        		RGoodSaleRequest rgoodSaleRequest = new RGoodSaleRequest();
                rgoodSaleRequest.setShopId(shop.getId());
                Long saleGdsCnt = this.reportGoodPayedRSV.querySumBuyNumByShopId(rgoodSaleRequest);
                if (saleGdsCnt != null) {
                	collectonShop.setSaleGdsCnt(saleGdsCnt);
                }
                //查询店铺的商品数量
                GdsInfoReqDTO gdsInfoReqDTO=new GdsInfoReqDTO();
                gdsInfoReqDTO.setShopId(shop.getId());
                List<String> list=new ArrayList<String>();
                list.add(GdsConstants.GdsInfo.GDS_STATUS_WAITSHELVES);
                list.add(GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES);
                list.add(GdsConstants.GdsInfo.GDS_STATUS_OFFSHELVES);
                gdsInfoReqDTO.setGdsStatusArr(list);
                Long gdsCnt = gdsInfoQueryRSV.countGdsInfoByShopIDAndStatusArr(gdsInfoReqDTO);
                if (gdsCnt != null) {
                	collectonShop.setGdsCnt(gdsCnt);
                }
                resList.add(collectonShop);
        	}
        }
        
        /*3、返回结果*/
        this.response.setResList(resList);
    }

}

