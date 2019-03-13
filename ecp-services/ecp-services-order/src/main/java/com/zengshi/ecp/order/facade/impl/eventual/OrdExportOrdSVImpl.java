package com.zengshi.ecp.order.facade.impl.eventual;

import com.zengshi.ecp.order.dao.model.OrdFileImport;
import com.zengshi.ecp.order.dubbo.dto.RExportExcleResponse;
import com.zengshi.ecp.order.dubbo.dto.RGoodSaleRequest;
import com.zengshi.ecp.order.dubbo.dto.RGoodSaleResponse;
import com.zengshi.ecp.order.dubbo.dto.RQueryOrderRequest;
import com.zengshi.ecp.order.facade.interfaces.eventual.IOrdExportOrdSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdExportFileSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdFileImportSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdSubSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.alibaba.fastjson.JSON;
import com.distribute.tx.common.TransactionStatus;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by cbl on 2017/2/23.
 */
public class OrdExportOrdSVImpl implements IOrdExportOrdSV {

    @Resource
    private IOrdFileImportSV ordFileImportSV;

    @Autowired(required=false)
    private IOrdExportFileSV ordExportFileSV;

    private static final String MODULE = OrdExportOrdSVImpl.class.getName();

    @Override
    public void joinTransaction(JSONObject jsonObject, TransactionStatus transactionStatus, String s) {
        final RQueryOrderRequest rcdr= JSON.parseObject(jsonObject.toString(), RQueryOrderRequest.class);
        rcdr.setPageSize(Integer.MAX_VALUE);
        rcdr.setPageNo(1);
        String fileId = "";
        if("getFileId".equals(rcdr.getExportType())){ //导出单头
            RExportExcleResponse response =  ordExportFileSV.queryOrder2ExcleNew(rcdr);
            fileId = response.getFileId();
        } else  if("getBarCodeFileId".equals(rcdr.getExportType())){   //导出明细
            RExportExcleResponse response = ordExportFileSV.queryOrderBarCodeNew(rcdr);
            fileId = response.getFileId();
        } else {
            return;
        }

        LogUtil.info(MODULE,"订单导出结束写入文件");
        OrdFileImport ordFile = new OrdFileImport();
        ordFile.setId(rcdr.getExportKey());
        ordFile.setFileName(fileId);
        ordFile.setShopId(100L);
        ordFile.setStatus("1");
        ordFile.setImportTime(DateUtil.getSysDate());
        ordFileImportSV.updateByIdSelective(ordFile);
    }
}
