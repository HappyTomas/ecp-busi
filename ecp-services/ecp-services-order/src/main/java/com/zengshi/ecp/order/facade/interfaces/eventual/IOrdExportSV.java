package com.zengshi.ecp.order.facade.interfaces.eventual;

import com.zengshi.ecp.order.dubbo.dto.RExportFileResp;
import com.zengshi.ecp.order.dubbo.dto.RGoodSaleRequest;
import com.zengshi.ecp.order.dubbo.dto.RQueryOrderRequest;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Created by sky on 2016/12/7.
 */
public interface IOrdExportSV {
    /**
     * 生成销售明细下载关联Key，同时保存查询条件
     * @param rGoodSaleRequest
     * @return
     * @throws BusinessException
     */
    public RExportFileResp creatFileKey(RGoodSaleRequest rGoodSaleRequest) throws BusinessException;

    /**
     * 生成订单查询中导出单头下载关联Key，同时保存查询条件
     * @param rQueryOrderRequest
     * @return
     * @throws BusinessException
     */
    public RExportFileResp creatFileKey(RQueryOrderRequest rQueryOrderRequest) throws BusinessException;

}
