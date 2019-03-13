package com.zengshi.ecp.busi.staff.vo;

import java.util.List;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class SaveDataRuleBatchVO extends EcpBasePageReqVO {
    private Long authId;//数据规则[DataAuth] id
    
    private List<SaveDataRuleVO> ruleArr; //数据规则明细集合 

    private static final long serialVersionUID = 1L;


	public List<SaveDataRuleVO> getRuleArr() {
		return ruleArr;
	}

	public void setRuleArr(List<SaveDataRuleVO> ruleArr) {
		this.ruleArr = ruleArr;
	}

	public Long getAuthId() {
		return authId;
	}

	public void setAuthId(Long authId) {
		this.authId = authId;
	}




}