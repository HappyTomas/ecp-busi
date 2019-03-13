/** 
 * Project Name:ecp-web-mall 
 * File Name:DemoAction.java 
 * Package Name:com.zengshi.ecp.app.action 
 * Date:2016-2-22下午6:51:19 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.app.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.app.req.Staff107Req;
import com.zengshi.ecp.app.resp.Staff107Resp;
import com.zengshi.ecp.server.front.util.BaseAreaAdminUtil;
import com.zengshi.ecp.staff.dubbo.dto.CustAddrReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustAddrResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustAddrRSV;
import com.zengshi.paas.utils.StringUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall Maven Webapp <br>
 * Description: 用户域：根据用户查询收货地址列表action<br>
 * Date:2016-3-4下午3:43:32  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */

@Service("staff107")
@Action(bizcode="staff107", userCheck=true)
@Scope("prototype")
public class Staff107Action extends AppBaseAction<Staff107Req, Staff107Resp> {

    @Resource
    private ICustAddrRSV custAddrRsv;
    
    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        
        /*1、获取入参*/
        CustAddrReqDTO addrReq = new CustAddrReqDTO();
        addrReq.setStaffId(addrReq.getStaff().getId());
        
        /*2、调用业务查询接口，查询用户收货地址列表*/
        List<CustAddrResDTO> result = custAddrRsv.listCustAddr(addrReq);
        if (!CollectionUtils.isEmpty(result)) {
            for (CustAddrResDTO res : result) {
                String province = "";
                String city = "";
                String county = "";
                if (StringUtil.isNotBlank(res.getProvince())) {
                    province = BaseAreaAdminUtil.fetchAreaName(res.getProvince());//省份
                }
                if (StringUtil.isNotBlank(res.getCityCode())) {
                    city = BaseAreaAdminUtil.fetchAreaName(res.getCityCode());//地市
                }
                if (StringUtil.isNotBlank(res.getCountyCode())) {
                    county = BaseAreaAdminUtil.fetchAreaName(res.getCountyCode());//区县
                }
                res.setPccName(province + city + county);
            }
        }
        
        /*3、返回结果*/
        this.response.setResList(result);
    }

}

