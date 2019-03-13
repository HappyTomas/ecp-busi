package com.zengshi.ecp.busi.staff.buyer.vo;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class ScoreParamVO extends EcpBasePageReqVO{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.7 
     */ 
    private static final long serialVersionUID = 1L;

    private String scoreType;
    
    private String beginDate;
    
    private String endDate;

    public String getScoreType() {
        return scoreType;
    }

    public void setScoreType(String scoreType) {
        this.scoreType = scoreType;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}

