package com.zengshi.ecp.sys.dubbo.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.sys.dubbo.dto.ReportItemReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.ReportItemRespDTO;
import com.zengshi.ecp.sys.dubbo.interfaces.IReportItemRSV;
import com.zengshi.ecp.sys.dubbo.util.ReportItemConstants;
import com.zengshi.ecp.sys.service.common.interfaces.IReportItemSV;
import com.zengshi.paas.utils.StringUtil;

public class ReportItemRSVImpl implements IReportItemRSV{

	@Resource
	private IReportItemSV reportItem;
	@Override
	public void addReportItem(ReportItemReqDTO reportItemReqDTO) throws BusinessException {
		// TODO Auto-generated method stub
		if(StringUtil.isBlank(reportItemReqDTO.getItemCode())){
			
			throw new BusinessException(ReportItemConstants.SYS_REPORT_ITEM_PARAM_NULL_ITEM_CODE);
		}
		if(reportItemReqDTO.getShopId() == null){
			
			throw new BusinessException(ReportItemConstants.SYS_REPORT_ITEM_PARAM_NULL_SHOP_ID);
		}
		
		try{
			reportItem.addReportItem(reportItemReqDTO);
			
		}catch(BusinessException e){
			throw e;
			
		}catch (Exception e1) {
			// TODO: handle exception
			throw new BusinessException("sys.report.item.fail.add");

		}
	}

	@Override
	public List<ReportItemRespDTO> queryReportItemByCondition(ReportItemReqDTO reportItemReqDTO) throws BusinessException {
		// TODO Auto-generated method stub
		
	if(StringUtil.isBlank(reportItemReqDTO.getItemCode())){
			
			throw new BusinessException(ReportItemConstants.SYS_REPORT_ITEM_PARAM_NULL_ITEM_CODE);
		}
		try{
		return	reportItem.queryReportItemByCondition(reportItemReqDTO);
			
		}catch(BusinessException e){
			throw e;
			
		}catch (Exception e1) {
			// TODO: handle exception
			throw new BusinessException(ReportItemConstants.SYS_REPORT_ITEM_FAIL_QUERY);
		}
	}

	@Override
	public void delReportItem(ReportItemReqDTO reportItemReqDTO) throws BusinessException {
		// TODO Auto-generated method stub
		try{
				reportItem.delReportItem(reportItemReqDTO);
				
			}catch(BusinessException e){
				throw e;
				
			}catch (Exception e1) {
				// TODO: handle exception
				throw new BusinessException(ReportItemConstants.SYS_REPORT_ITEM_FAIL_DEL);
			}
	}

}
