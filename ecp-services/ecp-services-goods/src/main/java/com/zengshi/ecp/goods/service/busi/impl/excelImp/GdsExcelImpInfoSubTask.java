package com.zengshi.ecp.goods.service.busi.impl.excelImp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.RecursiveTask;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.zengshi.ecp.frame.context.EcpFrameContextHolder;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.excelImp.ExcelImportGdsModelDTO;
import com.zengshi.ecp.goods.dubbo.dto.excelImp.GdsExcelImpPropValueInfo;
import com.zengshi.ecp.goods.dubbo.dto.excelImp.GdsExcelImpReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.excelImp.GdsExcelImportResultDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoAddReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsExcelImpToolRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoManageRSV;
import com.zengshi.ecp.goods.service.busi.interfaces.excelImp.IGdsExcelImpSV;
import com.zengshi.ecp.goods.service.busi.interfaces.excelImp.IGdsExcelImpToolSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsInfoManageSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

public class GdsExcelImpInfoSubTask extends RecursiveTask<GdsExcelImportResultDTO> {

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * 
	 * @since JDK 1.6
	 */
	private static final long serialVersionUID = -3475573318876183203L;
	private static final String MODULE = GdsExcelImpInfoSubTask.class.getName();

	public GdsExcelImpInfoSubTask(int startIndex, int endIndex, CountDownLatch countDownLatch,
			List<ExcelImportGdsModelDTO> models, String optType) {
		super();
		this.startIndex = startIndex;
		this.endIndex = endIndex;
		this.countDownLatch = countDownLatch;
		this.models = models;
		this.optType = optType;
	}

	private int startIndex;
	private int endIndex;
	private String optType;
	private CountDownLatch countDownLatch;
	private List<ExcelImportGdsModelDTO> models;
	private IGdsExcelImpSV gdsExcelImpSV =EcpFrameContextHolder.getBean("gdsExcelImpSV",
			IGdsExcelImpSV.class);

	private IGdsExcelImpToolSV gdsExcelImpToolSV = EcpFrameContextHolder.getBean("gdsExcelImpToolSV",
			IGdsExcelImpToolSV.class);

	private IGdsInfoManageRSV gdsInfoManageRSV = EcpFrameContextHolder.getBean("gdsInfoManageRSV", IGdsInfoManageRSV.class);

	@Override
	protected GdsExcelImportResultDTO compute() {
		GdsExcelImportResultDTO excelImportResultDTO = new GdsExcelImportResultDTO();
		Long errorCount = 0L;
		Long successCount = 0L;
		Long totalCount = 0L;
		excelImportResultDTO.setErrorCount(errorCount);
		excelImportResultDTO.setSuccessCount(successCount);
		excelImportResultDTO.setTotalCount(totalCount);
		// 处理子任务应该处理的数据偏移
		for (int i = startIndex; i < endIndex; i++) {
			ExcelImportGdsModelDTO excelImportGdsModelDTO = models.get(i);
			GdsExcelImpReqDTO excelImpReqDTO=null;
			try {
				// 插入到中间表
				excelImpReqDTO = gdsExcelImpToolSV.validateGdsExcelImpData(excelImportGdsModelDTO,
						optType);
				if (StringUtil.isBlank(excelImpReqDTO.getFailReason())) {
					// 保存到商品
					try {
						if (GdsConstants.GdsExcelImpLog.GDS_EXCEL_IMP_NEW.equals(optType)) {
							GdsInfoAddReqDTO gdsAddReq = gdsExcelImpSV.transformAddGdsExcelImp(excelImpReqDTO);
							gdsInfoManageRSV.addGdsInfo(gdsAddReq);
						} else if (GdsConstants.GdsExcelImpLog.GDS_EXCEL_IMP_EDIT.equals(optType)) {
							GdsInfoAddReqDTO gdsAddReq = gdsExcelImpSV.transformEditGdsExcelImp(excelImpReqDTO);
							gdsInfoManageRSV.editGdsInfoAndReference(gdsAddReq);
						}
					} catch (Exception e) {
						excelImpReqDTO.setFailReason("保存到商品表时失败！" + e.getMessage());
					}
				}
				// String propStr = excelImpReqDTO.getGdsPropStr();
				// List<GdsExcelImpPropValueInfo> excelImpPropValueInfos =
				// JSONArray.toList(JSONArray.fromObject(propStr),
				// GdsExcelImpPropValueInfo.class);
				// Map<Long, String> propMap = new HashMap<Long,String>();
				// for(GdsExcelImpPropValueInfo
				// excelImpPropValueInfo:excelImpPropValueInfos){
				// propMap.put(excelImpPropValueInfo.getPropId(),
				// excelImpPropValueInfo.getPropValue());
				//
				//
				// }
				try {
					excelImpReqDTO.setGdsPropStr(excelImportGdsModelDTO.getPropInfos().toString());
					gdsExcelImpSV.addGdsExcelImp(excelImpReqDTO);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					LogUtil.debug(MODULE, "导入信息：" + excelImportGdsModelDTO.toString() + "失败！");
				}
				if (StringUtil.isNotBlank(excelImpReqDTO.getFailReason())) {
					excelImportResultDTO.setErrorCount(excelImportResultDTO.getErrorCount() + 1);

				} else {
					excelImportResultDTO.setSuccessCount(excelImportResultDTO.getSuccessCount() + 1);

				}
				excelImportResultDTO.setTotalCount(excelImportResultDTO.getTotalCount() + 1);
			} catch (Exception e) {
				e.printStackTrace();
				excelImportResultDTO.setErrorCount(excelImportResultDTO.getErrorCount() + 1);
				excelImportResultDTO.setTotalCount(excelImportResultDTO.getTotalCount() + 1);
				if(null!=excelImpReqDTO)excelImpReqDTO.setFailReason("导入失败！" + e.getMessage());
				LogUtil.debug(MODULE, "导入信息：" + excelImportGdsModelDTO.toString() + "失败！");
			}
		}
		// 计算器做减法
		countDownLatch.countDown();
		return excelImportResultDTO;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public CountDownLatch getCountDownLatch() {
		return countDownLatch;
	}

	public void setCountDownLatch(CountDownLatch countDownLatch) {
		this.countDownLatch = countDownLatch;
	}

	public List<ExcelImportGdsModelDTO> getModels() {
		return models;
	}

	public void setModels(List<ExcelImportGdsModelDTO> models) {
		this.models = models;
	}

	public String getOptType() {
		return optType;
	}

	public void setOptType(String optType) {
		this.optType = optType;
	}

}
