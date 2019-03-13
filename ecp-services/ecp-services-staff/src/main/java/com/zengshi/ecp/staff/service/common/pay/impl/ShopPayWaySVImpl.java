/** 
 * Project Name:ecp-services-staff-server 
 * File Name:ShopAddrSVImpl.java 
 * Package Name:com.zengshi.ecp.staff.service.busi.shop.impl 
 * Date:2015年9月16日下午7:59:56 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.staff.service.common.pay.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.staff.dao.mapper.busi.StaffPayShopCfgMapper;
import com.zengshi.ecp.staff.dao.mapper.common.ShopPayWayMapper;
import com.zengshi.ecp.staff.dao.model.PayWay;
import com.zengshi.ecp.staff.dao.model.PayWayCriteria;
import com.zengshi.ecp.staff.dao.model.PayWayCriteria.Criteria;
import com.zengshi.ecp.staff.dubbo.dto.PayWayReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.PayWayResDTO;
import com.zengshi.ecp.staff.service.common.pay.interfaces.IShopPayWaySV;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: <br>
 * Date:2015年9月16日下午7:59:56  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author PJieWin
 * @version  
 * @since JDK 1.6 
 */
public class ShopPayWaySVImpl extends GeneralSQLSVImpl  implements IShopPayWaySV {

    private static final String MODULE = ShopPayWaySVImpl.class.getName();
    
    @Resource
    private ShopPayWayMapper shopPayWayMapper;
    
    @Override
    public List<PayWayResDTO> getShopPayWay(PayWayReqDTO payWayReqDTO) throws BusinessException {
        PayWayCriteria c = new PayWayCriteria();
        Criteria sql = c.createCriteria();
        if(StringUtil.isNotBlank(payWayReqDTO.getId())){
            sql.andIdEqualTo(payWayReqDTO.getId());
        }
        List<PayWayResDTO> listres = new ArrayList<PayWayResDTO>();
        List<PayWay>  list = shopPayWayMapper.selectByExample(c);
        for (PayWay payWay : list) {
            PayWayResDTO payWayResDTO = new PayWayResDTO();
            ObjectCopyUtil.copyObjValue(payWay, payWayResDTO, null, false);
            listres.add(payWayResDTO);
        }
        return listres;
    }

    @Override
    public PayWayResDTO getPayWayName(PayWayReqDTO payWayReqDTO) throws BusinessException {
        PayWayResDTO payWayResDTO = new PayWayResDTO();
        PayWay payWay =  shopPayWayMapper.selectByPrimaryKey(payWayReqDTO.getId());
        ObjectCopyUtil.copyObjValue(payWay, payWayResDTO, null, false);
        return payWayResDTO;
    }


}

