package com.zengshi.ecp.goods.dubbo.dto.excelImp;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class GdsExcelImpLogReqDTO extends BaseInfo{

	/** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
	private static final long serialVersionUID = -9214424075001324619L;


/**
 * 导入的商品临时数据列表
 */
	private List<ExcelImportGdsModelDTO> excelImportGdsModelDTOs;
	
	private Long importId;
		
	private String importSrc;
	
    private String importUuid;

    private Long importCount;

    private Long victoryCount;

    private Long failCount;
    
    private String importFile;

    private String importStatus;
    
    private String optType;
    
	public List<ExcelImportGdsModelDTO> getExcelImportGdsModelDTOs() {
		return excelImportGdsModelDTOs;
	}

	public void setExcelImportGdsModelDTOs(List<ExcelImportGdsModelDTO> excelImportGdsModelDTOs) {
		this.excelImportGdsModelDTOs = excelImportGdsModelDTOs;
	}


	public String getImportSrc() {
		return importSrc;
	}

	public void setImportSrc(String importSrc) {
		this.importSrc = importSrc;
	}



	public Long getImportCount() {
		return importCount;
	}

	public void setImportCount(Long importCount) {
		this.importCount = importCount;
	}

	public Long getVictoryCount() {
		return victoryCount;
	}

	public void setVictoryCount(Long victoryCount) {
		this.victoryCount = victoryCount;
	}

	public Long getFailCount() {
		return failCount;
	}

	public void setFailCount(Long failCount) {
		this.failCount = failCount;
	}

	public String getImportFile() {
		return importFile;
	}

	public void setImportFile(String importFile) {
		this.importFile = importFile;
	}

	public String getImportStatus() {
		return importStatus;
	}

	public void setImportStatus(String importStatus) {
		this.importStatus = importStatus;
	}

	public Long getImportId() {
		return importId;
	}

	public void setImportId(Long importId) {
		this.importId = importId;
	}

	public String getImportUuid() {
		return importUuid;
	}

	public void setImportUuid(String importUuid) {
		this.importUuid = importUuid;
	}

	public String getOptType() {
		return optType;
	}

	public void setOptType(String optType) {
		this.optType = optType;
	}



	
	
	
}

