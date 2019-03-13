package com.zengshi.ecp.goods.service.busi.impl.excelImp;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsExcelImpLogMapper;
import com.zengshi.ecp.goods.dao.model.GdsExcelImp;
import com.zengshi.ecp.goods.dao.model.GdsExcelImpLog;
import com.zengshi.ecp.goods.dao.model.GdsExcelImpLogCriteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.excelImp.ExcelImportGdsModelDTO;
import com.zengshi.ecp.goods.dubbo.dto.excelImp.GdsExcelImpLogReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.excelImp.GdsExcelImpLogRespDTO;
import com.zengshi.ecp.goods.service.busi.interfaces.excelImp.IGdsExcelImpLogSV;
import com.zengshi.ecp.goods.service.common.impl.AbstractSVImpl;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

public class GdsExcelImpLogSVImpl extends AbstractSVImpl implements IGdsExcelImpLogSV {
	// 正则表达式
	// 数字
	private static final String digitalReg = "[0-9]+";
	@Resource(name = "seq_gds_excel_imp_log")
	private PaasSequence seqGdsExcelImpLog;

	@Resource
	private GdsExcelImpLogMapper gdsExcelImpLogMapper;

	private static final String VALIDATE_ERROR_CATG_CODE = "分类编码不合法！";

	private void validateGdsExcelImpInfo(ExcelImportGdsModelDTO excelImportGdsModelDTO) {
		GdsExcelImp gdsExcelImp = new GdsExcelImp();

		if (excelImportGdsModelDTO.getCatgCode() == null || excelImportGdsModelDTO.getCatgCode().matches(digitalReg)) {
			ObjectCopyUtil.copyObjValue(excelImportGdsModelDTO, gdsExcelImp, null, false);
			gdsExcelImp.setGdsPropStr(excelImportGdsModelDTO.getPropInfos().toString());
			gdsExcelImp.setFailReason(VALIDATE_ERROR_CATG_CODE);
		}
		//
		// if(excelImportGdsModelDTO.getGdsDetailDesc() ) {
		//
		// }

	}

	/**
	 * 
	 * TODO 新建商品excel导入日志（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.excelImp.IGdsExcelImpLogSV#addGdsExcelImpLog(com.zengshi.ecp.goods.dubbo.dto.excelImp.GdsExcelImpLogReqDTO)
	 */
	@Override
	public Long addGdsExcelImpLog(GdsExcelImpLogReqDTO excelImpLogReqDTO) throws Exception {
		GdsExcelImpLog excelImpLog = new GdsExcelImpLog();
		ObjectCopyUtil.copyObjValue(excelImpLogReqDTO, excelImpLog, null, false);
		Long importId = seqGdsExcelImpLog.nextValue();
		excelImpLog.setImportId(importId);
		excelImpLog.setCreateStaff(excelImpLogReqDTO.getStaff().getId());
		excelImpLog.setCreateTime(new Timestamp(System.currentTimeMillis()));
		excelImpLog.setUpdateStaff(excelImpLogReqDTO.getStaff().getId());
		excelImpLog.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		excelImpLog.setImportStatus(GdsConstants.GdsExcelImpLog.GDS_EXCEL_IMP_LOG_STATUS_UNDEAL);
		gdsExcelImpLogMapper.insertSelective(excelImpLog);
		return importId;
	}

	/**
	 * 
	 * TODO 获取未处理的excel导入记录（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.excelImp.IGdsExcelImpLogSV#queryGdsExcelImpLogRespByImportInfo(com.zengshi.ecp.goods.dubbo.dto.excelImp.GdsExcelImpLogReqDTO)
	 */
	@Override
	public GdsExcelImpLogRespDTO queryGdsExcelImpLogRespByImportInfo(GdsExcelImpLogReqDTO dto) throws Exception {
		GdsExcelImpLogRespDTO excelImpLogRespDTO = new GdsExcelImpLogRespDTO();
		GdsExcelImpLogCriteria example = new GdsExcelImpLogCriteria();
		GdsExcelImpLogCriteria.Criteria criteria = example.createCriteria();
		criteria.andImportIdEqualTo(dto.getImportId());
		criteria.andImportStatusEqualTo(GdsConstants.GdsExcelImpLog.GDS_EXCEL_IMP_LOG_STATUS_UNDEAL);

		List<GdsExcelImpLog> excelImpLogs = gdsExcelImpLogMapper.selectByExample(example);
		if (excelImpLogs != null && excelImpLogs.size() > 0) {

			GdsExcelImpLog excelImpLog = excelImpLogs.get(0);
			ObjectCopyUtil.copyObjValue(excelImpLog, excelImpLogRespDTO, null, false);
			return excelImpLogRespDTO;
		} else {
			return null;
		}
	}

	@Override
	public void updateGdsExcelImpLogWhenTempDone(GdsExcelImpLogReqDTO dto) throws Exception {

		GdsExcelImpLog excelImpLog = new GdsExcelImpLog();
		ObjectCopyUtil.copyObjValue(dto, excelImpLog, null, false);
		excelImpLog.setImportStatus(GdsConstants.GdsExcelImpLog.GDS_EXCEL_IMP_LOG_STATUS_IMPORTED);
		GdsExcelImpLogCriteria example = new GdsExcelImpLogCriteria();
		GdsExcelImpLogCriteria.Criteria criteria = example.createCriteria();
		criteria.andImportIdEqualTo(dto.getImportId());
		criteria.andImportStatusEqualTo(GdsConstants.GdsExcelImpLog.GDS_EXCEL_IMP_LOG_STATUS_UNDEAL);
		gdsExcelImpLogMapper.updateByExampleSelective(excelImpLog, example);

	}

	@Override
	public PageResponseDTO<GdsExcelImpLogRespDTO> queryImpLogByPage(GdsExcelImpLogReqDTO dto) throws Exception {
		GdsExcelImpLogCriteria example = new GdsExcelImpLogCriteria();
		GdsExcelImpLogCriteria.Criteria criteria = example.createCriteria();
		criteria.andImportIdEqualTo(dto.getImportId());
		if(StringUtil.isNotBlank(dto.getImportStatus())){
			criteria.andImportStatusEqualTo(dto.getImportStatus());
			
		}
		criteria.andImportStatusNotEqualTo(GdsConstants.GdsExcelImpLog.GDS_EXCEL_IMP_LOG_STATUS_CANCEL);
//		criteria.andImportStatusEqualTo(dto.getImportStatus());
		return super.queryByPagination(dto, example, true,
				new PaginationCallback<GdsExcelImpLog, GdsExcelImpLogRespDTO>() {

					@Override
					public List<GdsExcelImpLog> queryDB(BaseCriteria arg0) {
						return gdsExcelImpLogMapper.selectByExample((GdsExcelImpLogCriteria) arg0);
					}

					@Override
					public long queryTotal(BaseCriteria arg0) {
						return gdsExcelImpLogMapper.countByExample((GdsExcelImpLogCriteria) arg0);
					}

					@Override
					public GdsExcelImpLogRespDTO warpReturnObject(GdsExcelImpLog arg0) {
						GdsExcelImpLogRespDTO excelImpLogRespDTO = new GdsExcelImpLogRespDTO();
						ObjectCopyUtil.copyObjValue(arg0, excelImpLogRespDTO, null, false);
						return excelImpLogRespDTO;
					}
				});

	}

	@Override
	public void delImportLogById(GdsExcelImpLogReqDTO excelImpLogReqDTO) throws Exception {
		GdsExcelImpLogCriteria example = new GdsExcelImpLogCriteria();
		GdsExcelImpLogCriteria.Criteria criteria = example.createCriteria();
		criteria.andImportIdEqualTo(excelImpLogReqDTO.getImportId());
		criteria.andImportStatusEqualTo(GdsConstants.GdsExcelImpLog.GDS_EXCEL_IMP_LOG_STATUS_UNDEAL);
		GdsExcelImpLog excelImpLog = new GdsExcelImpLog();
		excelImpLog.setImportStatus(GdsConstants.GdsExcelImpLog.GDS_EXCEL_IMP_LOG_STATUS_CANCEL);
		gdsExcelImpLogMapper.updateByExampleSelective(excelImpLog, example);

	}

}
