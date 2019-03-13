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
import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.app.req.Staff103Req;
import com.zengshi.ecp.app.resp.AcctInfo;
import com.zengshi.ecp.app.resp.Staff103Resp;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IAcctManageRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
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
 * Description: 用户域：查询我的资金账户<br>
 * Date:2016-3-4下午3:43:32  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */

@Service("staff103")
@Action(bizcode="staff103", userCheck=true)
@Scope("prototype")
public class Staff103Action extends AppBaseAction<Staff103Req, Staff103Resp> {

    @Resource
    private IAcctManageRSV acctManageRSV;
    
    @Resource
    private IShopInfoRSV shopInfoRSV;
    
    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        
        /*1、获取入参*/
        AcctInfoReqDTO acctReq = new AcctInfoReqDTO();
        acctReq.setStaffId(acctReq.getStaff().getId());
        acctReq.setPageNo(0);
        
        /*2、调用业务查询接口，查询我的资金账户*/
        PageResponseDTO<AcctInfoResDTO> page = acctManageRSV.queryAcctInfoByStaff(acctReq);
        
        /*3、返回结果*/
        if (page != null && !CollectionUtils.isEmpty(page.getResult())) {
            List<AcctInfo> acctList = new ArrayList<AcctInfo>();
            for (AcctInfoResDTO res : page.getResult()) {
                AcctInfo acctInfo = new AcctInfo();
                ObjectCopyUtil.copyObjValue(res, acctInfo, null, false);
                //获取店铺 logo图片
                ShopInfoResDTO shop = shopInfoRSV.findShopInfoByShopID(res.getShopId());
                if (StringUtil.isBlank(shop.getLogoPath())) {
                    shop.setLogoPath(ImageUtil.getDefaultImageId());
                }
                acctInfo.setShopLogo(ImageUtil.getImageUrl(shop.getLogoPath()));
                acctInfo.setShopName(res.getShopName());//objectCopyUtil工具居然没有拷贝出这个字段
                acctList.add(acctInfo);
            }
            this.response.setResList(acctList);
        } else {
            this.response.setResList(new ArrayList<AcctInfo>());
        }
    }

}

