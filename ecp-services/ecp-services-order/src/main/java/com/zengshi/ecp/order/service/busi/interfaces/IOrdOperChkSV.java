/**
 * 
 */
package com.zengshi.ecp.order.service.busi.interfaces;

import java.util.List;

import com.zengshi.ecp.general.order.dto.ROrdCartsChkResponse;
import com.zengshi.ecp.order.dubbo.dto.RBackConfirmReq;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsRequest;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;



/**
 * @author linwei
 *
 */
public interface IOrdOperChkSV{
    /**
     * 
     * queryOrderOper:(判断订单是否可以执行操作). <br/> 
     * @author linwei 
     * @param info
     * @return 
     * @since JDK 1.6
     */
    public ROrdCartsChkResponse queryOrdOperChk(ROrderDetailsRequest info);
    
    /** 
     * queryBackOperChk:判断退货退款相关操作是否可以执行. <br/> 
     * @author cbl 
     * @param info
     * @return 
     * @since JDK 1.6 
     */ 
    public ROrdCartsChkResponse queryBackOperChk(ROrderDetailsRequest info);
    
    /** 
     * queryChkStatus:判断订单是否可以执行操作. <br/> 
     * @author sky 
     * @param rOrderDetailsRequest
     * @return 
     * @since JDK 1.6 
     */ 
    public ROrdCartsChkResponse queryChkStatus(ROrderDetailsRequest rOrderDetailsRequest);
    
    
    /** 
     * queryShopChkStatus:判断订单店铺相关信息. <br/> 
     * @author cbl 
     * @param rOrderDetailsRequest
     * @param shopInfoResDTOs
     * @return 
     * @since JDK 1.6 
     */ 
    public ROrdCartsChkResponse queryShopChkStatus(ROrderDetailsRequest rOrderDetailsRequest,List<ShopInfoResDTO> shopInfoResDTOs);
}
