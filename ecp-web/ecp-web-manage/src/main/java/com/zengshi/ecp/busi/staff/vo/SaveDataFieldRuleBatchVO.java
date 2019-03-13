package com.zengshi.ecp.busi.staff.vo;

import java.util.List;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class SaveDataFieldRuleBatchVO extends EcpBasePageReqVO {
    private Long authId;//数据规则[DataAuth] id
    
    private List<DataFieldRuleVO> ruleArr; //数据过滤规则明细集合 

    private static final long serialVersionUID = 1L;


	public Long getAuthId() {
		return authId;
	}

	public void setAuthId(Long authId) {
		this.authId = authId;
	}

	public List<DataFieldRuleVO> getRuleArr() {
		return ruleArr;
	}

	public void setRuleArr(List<DataFieldRuleVO> ruleArr) {
		this.ruleArr = ruleArr;
	}




}