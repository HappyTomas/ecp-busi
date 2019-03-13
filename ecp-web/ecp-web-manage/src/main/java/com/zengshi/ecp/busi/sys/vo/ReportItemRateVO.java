package com.zengshi.ecp.busi.sys.vo;

import java.io.Serializable;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

/**
 * 率小数返回vo
 * @author zjh
 *
 */
public class ReportItemRateVO extends EcpBasePageReqVO implements Serializable{

private	String rate;

public String getRate() {
	return rate;
}

public void setRate(String rate) {
	this.rate = rate;
}
	
}
