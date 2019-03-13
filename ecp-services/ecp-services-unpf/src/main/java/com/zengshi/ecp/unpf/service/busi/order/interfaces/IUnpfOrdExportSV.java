package com.zengshi.ecp.unpf.service.busi.order.interfaces;

import com.zengshi.ecp.unpf.dubbo.dto.RUnpfExportFileResp;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfQueryOrderReq;

public interface IUnpfOrdExportSV {

	RUnpfExportFileResp creatFileKey(RUnpfQueryOrderReq rUnpfQueryOrderReq);

}
