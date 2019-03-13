/** 
 * Project Name:ecp-services-staff-server 
 * File Name:IShopAddrSV.java 
 * Package Name:com.zengshi.ecp.staff.service.busi.shop.interfaces 
 * Date:2015年9月16日下午6:08:27 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.staff.service.common.pay.interfaces;

import java.util.List;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;
import com.zengshi.ecp.staff.dubbo.dto.PayShopCfgReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.PayShopCfgResDTO;
import com.zengshi.ecp.staff.dubbo.dto.PayWayReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.PayWayResDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: <br>
 * Date:2016年4月26日上午9:52:41  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 支付通道管理
 * @author wangbh
 * @version  
 * @since JDK 1.7
 */
public interface IShopPayWaySV extends IGeneralSQLSV{
   
    
    /**
     * 
     * getShopPayWay:(获取店铺支付通道类型列表). <br/> 
     * 
     * @author wangbh
     * @param payWayReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public List<PayWayResDTO> getShopPayWay(PayWayReqDTO payWayReqDTO) throws BusinessException;
    
    /**
     * 
     * getPayWayName:(获取支付类型). <br/> 
     * 
     * @author wangbh
     * @param payWayReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public PayWayResDTO getPayWayName(PayWayReqDTO payWayReqDTO) throws BusinessException;
}

