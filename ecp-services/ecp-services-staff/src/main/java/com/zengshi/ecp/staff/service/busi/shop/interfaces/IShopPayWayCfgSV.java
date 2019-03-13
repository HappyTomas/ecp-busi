/** 
 * Project Name:ecp-services-staff-server 
 * File Name:IShopAddrSV.java 
 * Package Name:com.zengshi.ecp.staff.service.busi.shop.interfaces 
 * Date:2015年9月16日下午6:08:27 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.staff.service.busi.shop.interfaces;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;
import com.zengshi.ecp.staff.dubbo.dto.PayShopCfgReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.PayShopCfgResDTO;

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
public interface IShopPayWayCfgSV extends IGeneralSQLSV{
    
    /**
     * 
     * queryPayShopCfgList:(查询店铺支付通道列表). <br/> 
     * 
     * @author wangbh
     * @param payShopCfgReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public PageResponseDTO<PayShopCfgResDTO> queryPayShopCfgList(PayShopCfgReqDTO payShopCfgReqDTO) throws BusinessException;
    
    
    /**
     * 
     * addPayShopCfg:(新增店铺支付通道). <br/> 
     * 
     * @author wangbh
     * @param payShopCfgReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public int addPayShopCfg(PayShopCfgReqDTO payShopCfgReqDTO) throws BusinessException;
    
    
    /**
     * 
     * updatePayShopCfg:(修改店铺支付通道). <br/> 
     * 
     * @author wangbh
     * @param payShopCfgReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public int updatePayShopCfg(PayShopCfgReqDTO payShopCfgReqDTO)throws BusinessException;
    
    
    
}

