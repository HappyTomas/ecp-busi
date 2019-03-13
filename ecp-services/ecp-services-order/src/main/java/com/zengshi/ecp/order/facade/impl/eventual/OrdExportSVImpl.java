package com.zengshi.ecp.order.facade.impl.eventual;

import com.zengshi.ecp.order.dao.model.OrdFileImport;
import com.zengshi.ecp.order.dubbo.dto.RExportFileResp;
import com.zengshi.ecp.order.dubbo.dto.RGoodSaleRequest;
import com.zengshi.ecp.order.dubbo.dto.RQueryOrderRequest;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.facade.interfaces.eventual.IOrdExportSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdFileImportSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.alibaba.fastjson.JSON;
import com.distribute.tx.common.TransactionCallback;
import com.distribute.tx.common.TransactionContext;
import com.distribute.tx.common.TransactionStatus;
import com.distribute.tx.eventual.TransactionManager;

import javax.annotation.Resource;

/**
 * Created by sky on 2016/12/7.
 */
public class OrdExportSVImpl implements IOrdExportSV {

    @Resource(name="traMgnExport")
    private TransactionManager transactionManager;

    @Resource(name = "traMgnExportOrd")
    private TransactionManager traMgnExportOrd;

    @Resource
    private IOrdFileImportSV ordFileImportSV;


    private static final String MODULE = OrdExportSVImpl.class.getName();

    @Override
    public RExportFileResp creatFileKey(final RGoodSaleRequest rGoodSaleRequest) throws BusinessException {
        OrdFileImport resp = null;
        try {
            OrdFileImport ordFileImport = new OrdFileImport();
            ordFileImport.setFromId("4");  //下载文件KEY记录
    //                    ordFileImport.setFileName(""); //下载文件的mongodb存放的ID
            ordFileImport.setShopId(0L);  //下载文件生成的进度
            ordFileImport.setStatus("0"); //文件是否已生成0表示未完成 1表示已完成
//            ordFileImport.setRemark(""); //空表示正常，非空表示有异常退出
            ordFileImport.setImportTime(DateUtil.getSysDate());
            resp = ordFileImportSV.saveOrdFileImport(ordFileImport);
            rGoodSaleRequest.setKey(resp.getId());
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310021);
        }
        final TransactionContext txMsg = new TransactionContext();
        txMsg.setContent( JSON.toJSONString(rGoodSaleRequest));
        txMsg.setName("business-topic-export");
        final RExportFileResp rExportFileResp = new RExportFileResp();
        rExportFileResp.setKey(resp.getId());

        transactionManager.startTransaction(txMsg, new TransactionCallback(){
            @Override
            public Object doInTransaction(TransactionStatus status) {
//                try {
//                    OrdFileImport ordFileImport = new OrdFileImport();
//                    ordFileImport.setFromId("4");  //下载文件KEY记录
////                    ordFileImport.setFileName(""); //下载文件的mongodb存放的ID
//                    ordFileImport.setShopId(0L);  //下载文件生成的进度
//                    ordFileImport.setStatus("0"); //文件是否已生成0表示未完成 1表示已完成
//                    OrdFileImport resp = ordFileImportSV.saveOrdFileImport(ordFileImport);
//                    rQueryOrderRequest.setKey(resp.getId());
//                    txMsg.setContent( JSON.toJSONString(rQueryOrderRequest));
//                    rExportFileResp.setKey(resp.getId());
//                } catch (BusinessException be) {
//                    LogUtil.error(MODULE, "===业务异常===",be);
//                    status.setRollbackOnly();
//                    throw be;
//                } catch (Exception e) {
//                    status.setRollbackOnly();
//                    LogUtil.error(MODULE, "===系统异常===",e);
//                    throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310021);
//                }
                return rExportFileResp;
            }});
        return rExportFileResp;
    }

    @Override
    public RExportFileResp creatFileKey(RQueryOrderRequest rQueryOrderRequest) throws BusinessException {
        OrdFileImport resp = null;
        try {
            OrdFileImport ordFileImport = new OrdFileImport();
            ordFileImport.setFromId("4");  //下载文件KEY记录
            //                    ordFileImport.setFileName(""); //下载文件的mongodb存放的ID
            ordFileImport.setShopId(0L);  //下载文件生成的进度
            ordFileImport.setStatus("0"); //文件是否已生成0表示未完成 1表示已完成
//            ordFileImport.setRemark(""); //空表示正常，非空表示有异常退出
            ordFileImport.setImportTime(DateUtil.getSysDate());
            resp = ordFileImportSV.saveOrdFileImport(ordFileImport);
            rQueryOrderRequest.setExportKey(resp.getId());
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310021);
        }
        final TransactionContext txMsg = new TransactionContext();
        txMsg.setContent( JSON.toJSONString(rQueryOrderRequest));
        txMsg.setName("business-topic-exportOrd");
        final RExportFileResp rExportFileResp = new RExportFileResp();
        rExportFileResp.setKey(resp.getId());

        traMgnExportOrd.startTransaction(txMsg, new TransactionCallback(){
            @Override
            public Object doInTransaction(TransactionStatus status) {
                return rExportFileResp;
            }});
        return rExportFileResp;
    }
}
