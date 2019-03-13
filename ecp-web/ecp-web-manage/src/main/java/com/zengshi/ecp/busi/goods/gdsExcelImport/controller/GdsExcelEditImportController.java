package com.zengshi.ecp.busi.goods.gdsExcelImport.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zengshi.ecp.base.mvc.JsonResultThreadLocal;
import com.zengshi.ecp.base.mvc.annotation.NativeJson;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.goods.common.GdsBaseController;
import com.zengshi.ecp.busi.goods.gdsExcelImport.vo.FileImportReqVO;
import com.zengshi.ecp.busi.goods.gdsExcelImport.vo.FileImportRespVO;
import com.zengshi.ecp.busi.goods.gdsExcelImport.vo.GdsExcelImpLogReqInfo;
import com.zengshi.ecp.busi.goods.gdsExcelImport.vo.GdsExcelImpTempVO;
import com.zengshi.ecp.busi.goods.gdsExcelImport.vo.ListResultReqVO;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.excelImp.ExcelImportGdsModelDTO;
import com.zengshi.ecp.goods.dubbo.dto.excelImp.GdsExcelImpLogReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.excelImp.GdsExcelImpLogRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.excelImp.GdsExcelImpReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.excelImp.GdsExcelImpRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.excelImp.GdsExcelImportResultDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsExcelImpLogRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsExcelImpRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsExcelImpToolRSV;
import com.zengshi.ecp.goods.dubbo.util.ExcelImportExcelUtil;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.FileUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSON;
@Controller
@RequestMapping("/goods/excelEditImport")
public class GdsExcelEditImportController  extends GdsBaseController{


	@Resource
	IGdsExcelImpLogRSV excelImpLogRSV;
	@Resource
	IGdsExcelImpToolRSV excelImpToolRSV;
	@Resource
	IGdsExcelImpRSV excelImpRSV;

	@RequestMapping("/pageInit")
	public String pageInit(Model model) throws Exception {

		return "/goods/gdsExcelEditImport/gds-importdata";
	}

	@RequestMapping(value = "/importLogList")
	@ResponseBody
	public Model importLogList(Model model, FileImportReqVO fileImportReqVO) throws Exception {
		PageResponseDTO<GdsExcelImpLogRespDTO> pageResponseDTO = new PageResponseDTO<GdsExcelImpLogRespDTO>();
		if (fileImportReqVO.getImportId() == null) {
			pageResponseDTO.setResult(new ArrayList<GdsExcelImpLogRespDTO>());
		} else {
			GdsExcelImpLogReqDTO excelImpLogReqDTO = new GdsExcelImpLogReqDTO();
			excelImpLogReqDTO.setImportId(fileImportReqVO.getImportId());
			// excelImpLogReqDTO.setImportStatus(GdsConstants.GdsExcelImpLog.GDS_EXCEL_IMP_LOG_STATUS_UNDEAL);
			pageResponseDTO = excelImpLogRSV.queryImpLogByPage(excelImpLogReqDTO);
			if (pageResponseDTO.getResult() == null) {
				pageResponseDTO.setResult(new ArrayList<GdsExcelImpLogRespDTO>());
			} else {
				for (GdsExcelImpLogRespDTO excelImpLogRespDTO : pageResponseDTO.getResult()) {
					String importStatus = excelImpLogRespDTO.getImportStatus();
					if (GdsConstants.GdsExcelImpLog.GDS_EXCEL_IMP_LOG_STATUS_UNDEAL.equals(importStatus)) {
						excelImpLogRespDTO.setImportStatus("未导入");
					} else if (GdsConstants.GdsExcelImpLog.GDS_EXCEL_IMP_LOG_STATUS_IMPORTED.equals(importStatus)) {
						excelImpLogRespDTO.setImportStatus("已导入");
					} else if (GdsConstants.GdsExcelImpLog.GDS_EXCEL_IMP_LOG_STATUS_CANCEL.equals(importStatus)) {
						excelImpLogRespDTO.setImportStatus("已取消");
					}
				}
			}
		}
		EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(pageResponseDTO);
		return super.addPageToModel(model, respVO);
	}

	@RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
	@ResponseBody
	@NativeJson(true)
	public String uploadFile(@RequestParam(value = "excelFile", required = false) MultipartFile excel,
			Model model, HttpServletRequest request, HttpServletResponse response) {
		JsonResultThreadLocal.set(false);
		FileImportRespVO respVO = new FileImportRespVO();
		if (excel == null) {
			LogUtil.info(MODULE, "商品excel导入文件不存在");
			throw new BusinessException();
		}
		String fileId = "";
		String oriFileName = excel.getOriginalFilename();
		String[] fileNamea = oriFileName.split("\\.");
		String fileName = fileNamea[0] + "_" + UUID.randomUUID();
		String fileExtName = fileNamea[1];
		try {
			fileId = FileUtil.saveFile(excel.getBytes(), fileName, fileExtName);
			GdsExcelImpLogReqDTO excelImpLogReqDTO = new GdsExcelImpLogReqDTO();
			excelImpLogReqDTO.setImportFile(oriFileName);
			excelImpLogReqDTO.setImportUuid(fileId);
			Long importId = excelImpLogRSV.addGdsExcelImpLog(excelImpLogReqDTO);
			respVO.setImportId(importId);
			respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
			respVO.setFileId(importId+"");
			respVO.setSuccess(true);
		} catch (IOException e) {
			LogUtil.error(MODULE, "文件保存失败", e);
			respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
			respVO.setResultMsg(e.getMessage());
			respVO.setSuccess(false);
		}

		return JSON.toJSONString(respVO);

	}

	@RequestMapping(value = "/importTempData")
	@ResponseBody
	public GdsExcelImpTempVO importTempData(Model model, GdsExcelImpLogReqInfo vo) {
		GdsExcelImpTempVO gdsExcelImpTempVO = new GdsExcelImpTempVO();
		double excueTime;
		if (StringUtil.isBlank(vo.getImportFileUuid())) {
			gdsExcelImpTempVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
			return null;
		}
		byte[] bs = FileUtil.readFile(vo.getImportFileUuid());
		ByteArrayInputStream stream = new ByteArrayInputStream(bs);
		String fileType = vo.getImportFile().substring(vo.getImportFile().lastIndexOf(".") + 1);
		try {
			List<ExcelImportGdsModelDTO> excelImportGdsModelDTOs = ExcelImportExcelUtil.importGdsExcelFile(stream,
					fileType,"1");
			if (excelImportGdsModelDTOs != null && excelImportGdsModelDTOs.size() > 0) {
				for (ExcelImportGdsModelDTO dto : excelImportGdsModelDTOs) {

					dto.setImportId(vo.getImportId());

				}

			}

			long start = System.currentTimeMillis();
			GdsExcelImpLogReqDTO excelImpLogReqDTO = new GdsExcelImpLogReqDTO();
			ObjectCopyUtil.copyObjValue(vo, excelImpLogReqDTO, null, false);
			excelImpLogReqDTO.setExcelImportGdsModelDTOs(excelImportGdsModelDTOs);
			excelImpLogReqDTO.setOptType(GdsConstants.GdsExcelImpLog.GDS_EXCEL_IMP_EDIT);
			GdsExcelImportResultDTO gdsExcelImportResultDTO = excelImpToolRSV.importTempGdsExcelData(excelImpLogReqDTO);
			long end = System.currentTimeMillis();
			excueTime = (end - start) / 1000.00;
			LogUtil.debug(MODULE, "批量导入商品临时数据执行时间：" + (excueTime) + "'s");

			gdsExcelImpTempVO.setErrorCount(gdsExcelImportResultDTO.getErrorCount());
			gdsExcelImpTempVO.setSuccessCount(gdsExcelImportResultDTO.getSuccessCount());
			gdsExcelImpTempVO.setTotalCount(gdsExcelImportResultDTO.getTotalCount());
			gdsExcelImpTempVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
			gdsExcelImpTempVO.setResultMsg("批量导入商品临时数据成功");
		} catch (BusinessException e) {
			gdsExcelImpTempVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
			gdsExcelImpTempVO.setResultMsg(e.getMessage());
			LogUtil.error(MODULE, e.getErrorMessage(), e);
		} catch (Exception e) {
			gdsExcelImpTempVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
			gdsExcelImpTempVO.setResultMsg(e.getMessage());
			LogUtil.error(MODULE, e.getMessage(), e);
		}
		return gdsExcelImpTempVO;
	}

	@RequestMapping(value = "/listResult")
	@ResponseBody
	public Model listResult(Model model, ListResultReqVO reqVO) throws Exception {

		GdsExcelImpReqDTO excelImpReqDTO = reqVO.toBaseInfo(GdsExcelImpReqDTO.class);
		excelImpReqDTO.setImportId(reqVO.getImportId());
		excelImpReqDTO.setStatus(reqVO.getStatus());
		try {
			PageResponseDTO<GdsExcelImpRespDTO> pageResponseDTO = excelImpRSV
					.queryGdsExcelImpByImportId(excelImpReqDTO);
			if (pageResponseDTO.getResult() == null) {
				pageResponseDTO.setResult(new ArrayList<GdsExcelImpRespDTO>());
			}
			EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(pageResponseDTO);
			return super.addPageToModel(model, respVO);
		} catch (Exception e) {

			e.printStackTrace();
			return model;
		}

	}

	@RequestMapping(value = "/watchResultList")
	public String watchResultList(Model model, ListResultReqVO reqVO) throws Exception {
		return "/goods/gdsExcelEditImport/list/list-result";
	}

	@RequestMapping(value = "/delImportLog")
	@ResponseBody
	public GdsExcelImpTempVO delImportLog(Model model, GdsExcelImpLogReqInfo vo) throws Exception {
		GdsExcelImpLogReqDTO excelImpLogReqDTO = new GdsExcelImpLogReqDTO();
		excelImpLogReqDTO.setImportId(vo.getImportId());
		GdsExcelImpTempVO excelImpTempVO = new GdsExcelImpTempVO();

		try {
			excelImpLogRSV.delImportLogById(excelImpLogReqDTO);
			excelImpTempVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		} catch (Exception e) {
			excelImpTempVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);

		}
		return excelImpTempVO;
	}


}

