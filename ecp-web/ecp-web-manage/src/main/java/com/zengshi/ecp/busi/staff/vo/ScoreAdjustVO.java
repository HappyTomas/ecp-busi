package com.zengshi.ecp.busi.staff.vo;

import java.io.Serializable;


import org.hibernate.validator.constraints.Length;

public class ScoreAdjustVO implements Serializable{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.7 
     */ 
    private static final long serialVersionUID = 1L;

    private String adjustType;
    
    private Long scoreAcctId;
    
    private Long staffId;
    
    private String scoreAdjustType;
    
    private Long score;
    
    private String remark;

    public String getAdjustType() {
        return adjustType;
    }

    public void setAdjustType(String adjustType) {
        this.adjustType = adjustType;
    }

    public Long getScoreAcctId() {
        return scoreAcctId;
    }

    public void setScoreAcctId(Long scoreAcctId) {
        this.scoreAcctId = scoreAcctId;
    }

    public String getScoreAdjustType() {
        return scoreAdjustType;
    }

    public void setScoreAdjustType(String scoreAdjustType) {
        this.scoreAdjustType = scoreAdjustType;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    
}

