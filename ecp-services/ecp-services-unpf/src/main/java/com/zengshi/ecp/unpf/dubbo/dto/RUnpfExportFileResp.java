package com.zengshi.ecp.unpf.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

/**
 * @author jiangmr
 */
public class RUnpfExportFileResp extends BaseResponseDTO {
 
	private static final long serialVersionUID = 1L;
	/**
     * 下载关联key
     */
    private Long key;
    /**
     * 下载文件ID
     */
    private String fileId;
    /**
     * 进度条百分比
     */
    private Long progress;
    /**
     * 文件生成标识
     */
    private String completeFlag;

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public Long getProgress() {
        return progress;
    }

    public void setProgress(Long progress) {
        this.progress = progress;
    }

    public String getCompleteFlag() {
        return completeFlag;
    }

    public void setCompleteFlag(String completeFlag) {
        this.completeFlag = completeFlag;
    }
}
