package com.zengshi.ecp.busi.goods.gdsExcelImport.vo;

import com.zengshi.ecp.base.vo.EcpBaseResponseVO;

public class FileImportRespVO extends EcpBaseResponseVO {
	
	/** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.7
	 */ 
	private static final long serialVersionUID = -2055841686553510414L;
	/**
	 * 文件id   来自mongodb
	 */

	private Long importId;
	
	private String fileId;
	
	private Boolean success;
	
	public Long getImportId() {
		return importId;
	}
	public void setImportId(Long importId) {
		this.importId = importId;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
    
	

}

