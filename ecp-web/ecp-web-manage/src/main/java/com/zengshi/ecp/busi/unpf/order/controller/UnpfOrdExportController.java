package com.zengshi.ecp.busi.unpf.order.controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.busi.order.file.vo.RExportFileReqVO;
import com.zengshi.ecp.busi.order.file.vo.RExportFileRespVO;
import com.zengshi.ecp.busi.unpf.order.vo.UnpfOrdMainReqVO;
import com.zengshi.ecp.order.dubbo.dto.RExportFileReq;
import com.zengshi.ecp.order.dubbo.dto.RExportFileResp;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdFileImportRSV;
import com.zengshi.ecp.unpf.dubbo.dto.RUnpfExportFileResp;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfQueryOrderReq;
import com.zengshi.ecp.unpf.dubbo.interfaces.order.IUnpfOrdExportRSV;
import com.zengshi.paas.utils.FileUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;

/**
 * Created by jiangmr
 */
@Controller
@RequestMapping(value = "/unpfOrdExport")
public class UnpfOrdExportController {

	private static final String MODULE = UnpfOrdExportController.class.getName();

	public final static String RESULT_FLAG_SUCCESS = "ok";

	public final static String RESULT_FLAG_FAILURE = "fail";

	public final static String RESULT_FLAG_EXCEPTION = "expt";

	@Resource
	private IUnpfOrdExportRSV unpfOrdExportRSV;

	@Resource
	private IOrdFileImportRSV ordFileImportRSV;

	@RequestMapping(value = "/exportPageUnpfOrd")
	public String exportPageUnpfOrd(@RequestParam("exportInfo") String exportInfo, Model model) {
		model.addAttribute("exportInfo", exportInfo);
		return "/unpf/order/export/export-page-unpford";
	}

	/**
	 * 导出单头/明细
	 * 
	 * @param unpfOrdMainReqVO
	 * @return
	 */
	@RequestMapping(value = "/exportKeyOrd")
	@ResponseBody
	public RExportFileRespVO exportKeyDt(UnpfOrdMainReqVO unpfOrdMainReqVO) {
		RExportFileRespVO resp = new RExportFileRespVO();
		RUnpfQueryOrderReq rUnpfQueryOrderReq = new RUnpfQueryOrderReq();
		rUnpfQueryOrderReq = unpfOrdMainReqVO.toBaseInfo(RUnpfQueryOrderReq.class);
		ObjectCopyUtil.copyObjValue(unpfOrdMainReqVO, rUnpfQueryOrderReq, "", false);
		rUnpfQueryOrderReq.setEndDate(new Timestamp(DateUtils.addDays(rUnpfQueryOrderReq.getEndDate(), 1).getTime()));
		try {
			RUnpfExportFileResp ruef = unpfOrdExportRSV.creatQueryOrderFileKey(rUnpfQueryOrderReq);
			resp.setKey(ruef.getKey());
			resp.setResultFlag(resp.RESULT_FLAG_SUCCESS);
		} catch (Exception e) {
			LogUtil.error(MODULE, "============订单模板导出异常==========");
			resp.setResultFlag(resp.RESULT_FLAG_EXCEPTION);
		}
		return resp;
	}

	/**
	 * 导出
	 * 
	 * @param fileId
	 * @param response
	 */
	@RequestMapping(value = "/export/{fileId}")
	public void exportExcel(@PathVariable("fileId") String fileId, HttpServletResponse response) {
		ServletOutputStream outputStream = null;
		try {
			byte[] bytes = FileUtil.readFile(fileId);
			String fileType = FileUtil.getFileType(fileId);
			String getFileName = FileUtil.getFileName(fileId);
			String fileName = getFileName + ".xls";
			if ("xlsx".equals(fileType)) {
				fileName = getFileName + ".xlsx";
			}
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			// 设置响应头和下载保存的文件名 用关键字命名
			response.setHeader("content-disposition", "attachment;filename=" + fileName);
			outputStream = response.getOutputStream();
			outputStream.write(bytes);
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (outputStream != null) {
					outputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取导出进度
	 * 
	 * @param vo
	 * @return
	 */
	@RequestMapping(value = "/exportProgress")
	@ResponseBody
	public RExportFileRespVO exportProgress(RExportFileReqVO vo) {
		RExportFileRespVO resp = new RExportFileRespVO();
		try {

			RExportFileReq rExportFileReq = new RExportFileReq();
			rExportFileReq.setKey(Long.parseLong(vo.getKey()));
			RExportFileResp rfr = ordFileImportRSV.queryById(rExportFileReq);
			resp.setKey(rfr.getKey());
			resp.setFileId(rfr.getFileId());
			resp.setCompleteFlag(rfr.getCompleteFlag());
			resp.setProgress(rfr.getProgress());
			resp.setResultFlag(resp.RESULT_FLAG_SUCCESS);
		} catch (Exception e) {
			LogUtil.error(MODULE, "============订单模板导出异常==========");
			resp.setResultFlag(resp.RESULT_FLAG_EXCEPTION);
		}
		return resp;
	}
}
