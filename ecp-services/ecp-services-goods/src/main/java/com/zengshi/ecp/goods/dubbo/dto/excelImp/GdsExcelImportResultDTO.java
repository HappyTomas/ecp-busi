package com.zengshi.ecp.goods.dubbo.dto.excelImp;

import java.io.Serializable;

public class GdsExcelImportResultDTO  implements Serializable{

	
    /** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
	private static final long serialVersionUID = 1118088433682373269L;
	private long totalCount = 0;
    private long successCount = 0;
    private long errorCount = 0;
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public long getSuccessCount() {
		return successCount;
	}
	public void setSuccessCount(long successCount) {
		this.successCount = successCount;
	}
	public long getErrorCount() {
		return errorCount;
	}
	public void setErrorCount(long errorCount) {
		this.errorCount = errorCount;
	}
}

