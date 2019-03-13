package com.zengshi.ecp.order.service.busi.interfaces;

import com.zengshi.ecp.order.dao.model.OrdBackApply;
import com.zengshi.ecp.order.dao.model.OrdBackStaffIdx;
import com.zengshi.ecp.order.dubbo.dto.RBackApplyResp;
import com.zengshi.ecp.order.dubbo.dto.ROrderBackReq;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

public interface IOrdBackStaffIdxSV extends IGeneralSQLSV{
    public void saveOrdBackStaffIdx(OrdBackStaffIdx ordBackStaffIdx);
    
    /** 
     * queryBackGdsByStaff:退货买家查询. <br/> 
     * @author cbl 
     * @param rOrderBackReq
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public PageResponseDTO<RBackApplyResp> queryBackGdsByStaff(ROrderBackReq rOrderBackReq) throws BusinessException;
    
    /** 
     * updateBackGdsStatus:更新记录信息. <br/> 
     * @author cbl 
     * @param rBackConfirmReq
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void updateBackApplyFromInput(OrdBackApply ordBackApply) throws BusinessException;
}

