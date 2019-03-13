package com.zengshi.ecp.order.service.busi.interfaces;

import com.zengshi.ecp.order.dubbo.dto.ROrdPayRelReq;
import com.zengshi.ecp.order.dubbo.dto.ROrdPayRelResp;
import com.zengshi.ecp.server.front.exception.BusinessException;

import java.util.List;

public interface IOrdPayRelSV {
    /**
     * 
     * saveOrdPayRel:(保存合并支付). <br/> 
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
     * queryOrdPayRelByOption:(根据查询条件获取数据). <br/> 
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

