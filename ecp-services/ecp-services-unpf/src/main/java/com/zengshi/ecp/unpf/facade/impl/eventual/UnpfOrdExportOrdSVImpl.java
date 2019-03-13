package com.zengshi.ecp.unpf.facade.impl.eventual;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dubbo.dto.OrdFileImportDTO;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdFileImportRSV;
import com.zengshi.ecp.unpf.dubbo.dto.RExportExcleResponse;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfQueryOrderReq;
import com.zengshi.ecp.unpf.facade.interfaces.eventual.IUnpfOrdExportOrdSV;
import com.zengshi.ecp.unpf.service.busi.order.interfaces.IUnpfOrdExportFileSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.alibaba.fastjson.JSON;
import com.distribute.tx.common.TransactionStatus;

import net.sf.json.JSONObject;

/**
 * 生成导出文件
 * @author jiangmr
 */
public class UnpfOrdExportOrdSVImpl implements IUnpfOrdExportOrdSV {
	
	@Resource
	private IOrdFileImportRSV ordFileImportRSV;
	
	@Resource
	private IUnpfOrdExportFileSV unpfOrdExportFileSV;
	
    private static final String MODULE = UnpfOrdExportOrdSVImpl.class.getName();

	@Override
	public void joinTransaction(JSONObject jsonObject, TransactionStatus transactionStatus, String s) {
		 final RUnpfQueryOrderReq ruqor= JSON.parseObject(jsonObject.toString(), RUnpfQueryOrderReq.class);
	        ruqor.setPageSize(Integer.MAX_VALUE);
	        ruqor.setPageNo(1);
	        String fileId = "";
	        if("getFileId".equals(ruqor.getExportType())){ //导出单头
	            RExportExcleResponse response =  unpfOrdExportFileSV.queryOrder2Excle(ruqor);
	            fileId = response.getFileId();
	        } else  if("getBarCodeFileId".equals(ruqor.getExportType())){   //导出明细
	            RExportExcleResponse response = unpfOrdExportFileSV.queryOrderBarCode(ruqor);
	            fileId = response.getFileId();
	        } else {
	            return;
	        }

	        LogUtil.info(MODULE,"订单导出结束写入文件");
	        OrdFileImportDTO ordFile = new OrdFileImportDTO();
	        ordFile.setId(ruqor.getExportKey());
	        ordFile.setFileName(fileId);
	        ordFile.setShopId(100L);
	        ordFile.setStatus("1");
	        ordFile.setImportTime(DateUtil.getSysDate());
            ordFileImportRSV.updateById(ordFile);
		
	}

	
}
