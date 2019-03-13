package com.zengshi.ecp.order.service.busi.interfaces.report;

import com.zengshi.ecp.order.dao.model.GoodStaffPayedReport;
import com.zengshi.ecp.order.dubbo.dto.ROrdStaffTradeInfoReq;
import com.zengshi.ecp.order.dubbo.dto.ROrdStaffTradeInfoResp;
import com.zengshi.ecp.order.dubbo.dto.RQueryGoodPayedRequest;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * Created by wang on 15/12/31.
 * @author wangxq
 */
public interface IGoodStaffPayedSV extends IGeneralSQLSV {

    /**
     * 根据staffId gdsId skuId 查询购买过的数量(假的，被人改了代码，变成查询下过订单的数量)
     * @param rQueryGoodPayedRequest
     * @return
     */
    public Long querySumBuyNumByGoodStaff(RQueryGoodPayedRequest rQueryGoodPayedRequest) throws BusinessException;

    /**
     * 保存
     * @param goodStaffPayedReport
     * @throws BusinessException
     */
    public void saveGoodStaffPayed(GoodStaffPayedReport goodStaffPayedReport) throws BusinessException;
    
    /** 
     * queryStaffTradeTimes:查找出在店铺发生交易次数一定数量的用户. <br/> 
     * @author cbl 
     * @param rQueryGoodPayedRequest
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public ROrdStaffTradeInfoResp queryStaffTradeTimes(ROrdStaffTradeInfoReq rOrdStaffTradeInfoReq) throws BusinessException;
    
    /** 
     * queryStaffTradeMoney:查找店铺交易金额大于某个金额范围的用户. <br/> 
     * @author cbl 
     * @param rQueryGoodPayedRequest
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public PageResponseDTO<ROrdStaffTradeInfoResp> queryStaffTradeMoney(ROrdStaffTradeInfoReq rOrdStaffTradeInfoReq) throws BusinessException;
    /**
     * 根据staffId gdsId skuId 查询购买过的数量
     * @param rQueryGoodPayedRequest
     * @return
     */
    public Long queryStaffBuyNumByGoodStaff(RQueryGoodPayedRequest rQueryGoodPayedRequest)
            throws BusinessException;
}
