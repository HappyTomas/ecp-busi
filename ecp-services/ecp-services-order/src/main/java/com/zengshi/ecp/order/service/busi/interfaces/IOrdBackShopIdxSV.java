package com.zengshi.ecp.order.service.busi.interfaces;

import com.zengshi.ecp.order.dao.model.OrdBackApply;
import com.zengshi.ecp.order.dao.model.OrdBackShopIdx;
import com.zengshi.ecp.order.dubbo.dto.RBackApplyResp;
import com.zengshi.ecp.order.dubbo.dto.ROrderBackReq;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

public interface IOrdBackShopIdxSV extends IGeneralSQLSV {
    public void saveOrdBackShopIdx(OrdBackShopIdx ordBackShopIdx) throws BusinessException;
    
    /** 
     * queryBackGdsByShop:卖家退货查询. <br/> 
     * @author cbl 
     * @param rOrderBackReq
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public PageResponseDTO<RBackApplyResp> queryBackGdsByShop(ROrderBackReq rOrderBackReq) throws BusinessException;
    
    /** 
     * updateBackGdsStatus:更新记录信息. <br/> 
     * @author cbl 
     * @param rBackConfirmReq
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void updateBackApplyFromInput(OrdBackApply ordBackApply) throws BusinessException;
}

