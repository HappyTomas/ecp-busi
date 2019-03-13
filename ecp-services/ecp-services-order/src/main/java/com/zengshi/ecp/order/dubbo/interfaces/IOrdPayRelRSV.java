package com.zengshi.ecp.order.dubbo.interfaces;

import com.zengshi.ecp.order.dubbo.dto.ROrdPayRelReq;
import com.zengshi.ecp.order.dubbo.dto.ROrdPayRelResp;
import com.zengshi.ecp.server.front.exception.BusinessException;

import java.util.List;

public interface IOrdPayRelRSV {
    /**
     * 
     * saveOrdPayRel:(保存合并支付的订单). <br/> 
     * 
     * @author gxq 
     * @param rOrdPayRelReq
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    public ROrdPayRelResp saveOrdPayRel(ROrdPayRelReq rOrdPayRelReq) throws BusinessException;
    
    /**
     * 
     * queryOrdPayRelByOption:(根据条件查询返回不分页的结果集). <br/> 
     * 
     * @author gxq 
     * @param rOrdPayRelReq
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    public List<ROrdPayRelResp> queryOrdPayRelByOption(ROrdPayRelReq rOrdPayRelReq) throws BusinessException;
    /**
     * 
     * updateOrdPayRel:(更新). <br/> 
     * 
     * @author gxq 
     * @param rOrdPayRelReq
     * @throws BusinessException
     * @since JDK 1.6
     */
    public void updateOrdPayRel(ROrdPayRelReq rOrdPayRelReq) throws BusinessException;
}

