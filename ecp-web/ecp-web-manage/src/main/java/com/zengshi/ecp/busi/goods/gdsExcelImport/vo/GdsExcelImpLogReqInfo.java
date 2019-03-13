package com.zengshi.ecp.busi.goods.gdsExcelImport.vo;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class GdsExcelImpLogReqInfo extends  BaseInfo{

	/** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
	private static final long serialVersionUID = 7576934288371544036L;

/**
 * 导入的商品临时数据列表
 */
	
	private Long importId;
	
	
	private String importSrc;
	
    private String importFileUuid;
    
    private String importFile;

    private String importStatus;

	public Long getImportId() {
		return importId;
	}

	public void setImportId(Long importId) {
		this.importId = importId;
	}



	public String getImportSrc() {
		return importSrc;
	}

	public void setImportSrc(String importSrc) {
		this.importSrc = importSrc;
	}

	public String getImportFileUuid() {
		return importFileUuid;
	}

	public void setImportFileUuid(String importFileUuid) {
		this.importFileUuid = importFileUuid;
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
}

