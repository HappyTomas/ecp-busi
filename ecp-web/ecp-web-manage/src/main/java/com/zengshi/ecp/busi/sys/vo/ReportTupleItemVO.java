package com.zengshi.ecp.busi.sys.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class ReportTupleItemVO extends EcpBasePageReqVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9123421436723165158L;

	private Long compareItem1;
	
	private Long compareItem2;
	
	private String dataDate;

	public Long getCompareItem1() {
		return compareItem1;
	}

	public void setCompareItem1(Long compareItem1) {
		this.compareItem1 = compareItem1;
	}

	public Long getCompareItem2() {
		return compareItem2;
	}

	public void setCompareItem2(Long compareItem2) {
		this.compareItem2 = compareItem2;
	}

	public String getDataDate() {
		return dataDate;
	}

	public void setDataDate(String dataDate) {
		this.dataDate = dataDate;
	}
	
}
