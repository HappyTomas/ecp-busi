package com.zengshi.ecp.order.dubbo.impl;

import com.zengshi.ecp.order.dubbo.dto.RExportFileReq;
import com.zengshi.ecp.order.dubbo.dto.RExportFileResp;
import com.zengshi.ecp.order.dubbo.dto.RGoodSaleRequest;
import com.zengshi.ecp.order.dubbo.dto.RQueryOrderRequest;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdExportRSV;
import com.zengshi.ecp.order.facade.interfaces.eventual.IOrdExportSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdFileImportSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

import javax.annotation.Resource;

/**
 * Created by cbl on 2016/12/7.
 * 异步下载改造相关方法可以放在该类中
 */
public class OrdExportRSVImpl implements IOrdExportRSV {

    @Resource
    private IOrdExportSV ordExportSV;

    @Resource
    private IOrdFileImportSV ordFileImportSV;

    @Override
    public RExportFileResp creatSaleDetailFileKey(RGoodSaleRequest rGoodSaleRequest) throws BusinessException {
        return ordExportSV.creatFileKey(rGoodSaleRequest);
    }

    @Override
    public RExportFileResp querySaleDetailFileKeyProgress(RExportFileReq rExportFileReq) throws BusinessException {
        return ordFileImportSV.queryById(rExportFileReq);
    }

    @Override
    public RExportFileResp creatQueryOrderFileKey(RQueryOrderRequest rQueryOrderRequest) throws BusinessException {
        return ordExportSV.creatFileKey(rQueryOrderRequest);
    }
}
