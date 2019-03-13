package com.zengshi.ecp.unpf.service.busi.order.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dubbo.dto.OrdFileImportDTO;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdFileImportRSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dubbo.dto.RUnpfExportFileResp;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfQueryOrderReq;
import com.zengshi.ecp.unpf.service.busi.order.interfaces.IUnpfOrdExportSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.alibaba.fastjson.JSON;
import com.distribute.tx.common.TransactionCallback;
import com.distribute.tx.common.TransactionContext;
import com.distribute.tx.common.TransactionStatus;
import com.distribute.tx.eventual.TransactionManager;

public class UnpfOrdExportSVImpl implements IUnpfOrdExportSV {

	private static final String MODULE = UnpfOrdExportSVImpl.class.getName();
	 
	@Resource
	private IOrdFileImportRSV ordFileImportRSV;
	
    @Resource(name = "traMgnExportUnpfOrd")
	private TransactionManager traMgnExportUnpfOrd;
	
	@Override
	public RUnpfExportFileResp creatFileKey(RUnpfQueryOrderReq rUnpfQueryOrderReq) {
		OrdFileImportDTO resp = null;
        try {
        	OrdFileImportDTO ordFileImport = new OrdFileImportDTO();
            ordFileImport.setFromId("5");  //下载文件KEY记录
            ordFileImport.setShopId(0L);  //下载文件生成的进度
            ordFileImport.setStatus("0"); //文件是否已生成0表示未完成 1表示已完成
            ordFileImport.setImportTime(DateUtil.getSysDate());
            resp = ordFileImportRSV.saveOrdFileImport(ordFileImport);
            rUnpfQueryOrderReq.setExportKey(resp.getId());
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            throw e;
        }
        //启动生产文件事务
        final TransactionContext txMsg = new TransactionContext();
        txMsg.setContent( JSON.toJSONString(rUnpfQueryOrderReq));
        txMsg.setName("business-topic-exportUnpfOrd");
        final RUnpfExportFileResp rUnpfExportFileResp = new RUnpfExportFileResp();
        rUnpfExportFileResp.setKey(resp.getId());

        traMgnExportUnpfOrd.startTransaction(txMsg, new TransactionCallback(){
            @Override
            public Object doInTransaction(TransactionStatus status) {
                return rUnpfExportFileResp;
            }});
        return rUnpfExportFileResp;
	}

}
