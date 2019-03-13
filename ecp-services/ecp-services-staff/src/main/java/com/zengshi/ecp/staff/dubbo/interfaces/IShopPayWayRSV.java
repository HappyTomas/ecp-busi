package com.zengshi.ecp.staff.dubbo.interfaces;

import java.util.List;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.AcctInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctInfoTempReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctInfoTempResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctSumResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctTradeReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctTradeResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctTypeReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctTypeResDTO;
import com.zengshi.ecp.staff.dubbo.dto.FileImportReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ListReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.PayShopCfgReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.PayShopCfgResDTO;
import com.zengshi.ecp.staff.dubbo.dto.PayWayReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.PayWayResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopRelatedAcctsReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.TransactionContentReqDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: <br>
 * Date:2016年4月26日上午11:15:56  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author wangbh
 * @version  
 * @since JDK 1.7
 */
public interface IShopPayWayRSV {
    
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

