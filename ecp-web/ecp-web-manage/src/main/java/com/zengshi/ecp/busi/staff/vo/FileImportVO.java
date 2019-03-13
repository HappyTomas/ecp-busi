package com.zengshi.ecp.busi.staff.vo;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class FileImportVO extends EcpBasePageReqVO {
	
	/** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.7
	 */ 
	private static final long serialVersionUID = -2055841686553510414L;
	/**
	 * 文件id   来自mongodb
	 */
	private String fileId;
	/**
	 * 文件名  不含路径
	 */
    private String fileName;
    /**
     * 文件扩展名
     */
    private String fileExtName;
    /**
     * MD5
     */
    private String fileMD5;
    /**
     * 文件大小    字节
     */
    private Long fileSize;
    
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileExtName() {
		return fileExtName;
	}
	public void setFileExtName(String fileExtName) {
		this.fileExtName = fileExtName;
	}
	public String getFileMD5() {
		return fileMD5;
	}
	public void setFileMD5(String fileMD5) {
		this.fileMD5 = fileMD5;
	}
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

}

