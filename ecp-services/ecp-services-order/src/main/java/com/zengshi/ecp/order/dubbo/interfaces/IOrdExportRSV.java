package com.zengshi.ecp.order.dubbo.interfaces;

import com.zengshi.ecp.order.dubbo.dto.RExportFileReq;
import com.zengshi.ecp.order.dubbo.dto.RExportFileResp;
import com.zengshi.ecp.order.dubbo.dto.RGoodSaleRequest;
import com.zengshi.ecp.order.dubbo.dto.RQueryOrderRequest;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Created by cbl on 2016/12/7.
 * 异步下载改造相关方法可以放在该类中
 */
public interface IOrdExportRSV {
    /**
     * 生成销售明细下载关联Key，同时传入查询条件
     * @param rGoodSaleRequest
     * @return
     * @throws BusinessException
     */
    public RExportFileResp creatSaleDetailFileKey(RGoodSaleRequest rGoodSaleRequest) throws BusinessException;

    /**
     * 根据销售明细下载关联Key查询文件生成进度
     * @param rExportFileReq
     * @return
     * @throws BusinessException
     */
    public RExportFileResp querySaleDetailFileKeyProgress(RExportFileReq rExportFileReq) throws BusinessException;


    /**
     * 订单下载关联Key，同时传入查询条件
     * @param rQueryOrderRequest
     * @return
     * @throws BusinessException
     */
    public RExportFileResp creatQueryOrderFileKey(RQueryOrderRequest rQueryOrderRequest) throws BusinessException;

}
