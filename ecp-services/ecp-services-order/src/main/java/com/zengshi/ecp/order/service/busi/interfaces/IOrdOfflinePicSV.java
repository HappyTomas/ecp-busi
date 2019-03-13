package com.zengshi.ecp.order.service.busi.interfaces;

import java.util.List;

import com.zengshi.ecp.order.dubbo.dto.ROfflineApplyRequest;
import com.zengshi.ecp.order.dubbo.dto.SOfflineCondition;
import com.zengshi.ecp.order.dubbo.dto.SOfflinePic;

public interface IOrdOfflinePicSV {
    /** 
     * saveOfflineApply:线下支付申请保存上传凭证. <br/> 
     * @author cbl 
     * @param rOfflineApplyRequest 
     * @since JDK 1.6 
     */ 
    public void saveOfflineApply(ROfflineApplyRequest rOfflineApplyRequest,Long offlineNo);
    
    /** 
     * queryByOfflineNo:根据线下支付流水号查询凭证信息. <br/> 
     * @author cbl 
     * @param offlineNo
     * @return 
     * @since JDK 1.6 
     */ 
    public List<SOfflinePic> queryByOfflineNo(SOfflineCondition sOfflineCondition);
}

