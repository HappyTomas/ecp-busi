package com.zengshi.ecp.sys.dubbo.interfaces;

import java.util.List;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.sys.dubbo.dto.ReportItemReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.ReportItemRespDTO;

public interface IReportItemRSV {
	public void addReportItem(ReportItemReqDTO reportItemReqDTO) throws BusinessException;

	public List<ReportItemRespDTO> queryReportItemByCondition(ReportItemReqDTO reportItemReqDTO) throws BusinessException;

	public void delReportItem(ReportItemReqDTO reportItemReqDTO) throws BusinessException;
}
