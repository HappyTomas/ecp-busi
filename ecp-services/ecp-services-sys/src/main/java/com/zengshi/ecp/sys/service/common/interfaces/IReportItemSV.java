package com.zengshi.ecp.sys.service.common.interfaces;

import java.util.List;

import com.zengshi.ecp.sys.dubbo.dto.ReportItemReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.ReportItemRespDTO;

public interface IReportItemSV {

	public void addReportItem(ReportItemReqDTO reportItemReqDTO) throws Exception;

	public List<ReportItemRespDTO> queryReportItemByCondition(ReportItemReqDTO reportItemReqDTO) throws Exception;

	public void delReportItem(ReportItemReqDTO reportItemReqDTO)throws Exception;
}
