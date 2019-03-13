package com.zengshi.ecp.order.service.busi.interfaces;

import com.zengshi.ecp.order.dao.model.OrdBackApply;
import com.zengshi.ecp.order.dao.model.OrdMain;
import com.zengshi.ecp.order.dubbo.dto.RBackApplyOrdReq;
import com.zengshi.ecp.order.dubbo.dto.RBackApplyReq;
import com.zengshi.ecp.order.dubbo.dto.ROrdReturnRefundReq;
import com.zengshi.ecp.order.dubbo.dto.ROrdReturnRefundResp;
import com.zengshi.ecp.order.dubbo.dto.ROrderBackReq;

public interface IOrderBackSV {
	public ROrdReturnRefundResp calCulateBackInfo(RBackApplyOrdReq resp);
	
	public long calCulateScaleApply(RBackApplyReq rBackApplyReq, OrdBackApply ordBackApply,OrdMain ordMain);
	 
	public Long calCulateMoneyApply(RBackApplyOrdReq resp,double scale,boolean isLastFlag);
	
	public Long calCulateMoneyApply(RBackApplyReq rBackApplyReq, OrdBackApply ordBackApply, OrdMain ordMain,double scale,boolean isLastFlag);

	 public ROrdReturnRefundResp calCulateBackInfo (ROrderBackReq req);
	 
    /**
     *  计算比例
     * @param resp
     * @return
     */
    public long calCulateScaleApply(RBackApplyOrdReq req);
    
    public boolean modifyBackMoney(ROrdReturnRefundReq req);
}
