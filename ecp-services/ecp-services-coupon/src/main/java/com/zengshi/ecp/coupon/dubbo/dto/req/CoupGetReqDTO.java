package com.zengshi.ecp.coupon.dubbo.dto.req;

import java.util.Date;

import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-10-23 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class CoupGetReqDTO extends BaseInfo {

    private static final long serialVersionUID = 1L;
    
    //客户等级  01 普通会员 02 白金 03..
    private String custLevel;
    
    //领取数量
    private String gainNum;
    
    //领取开始时间
    private Date   startTime;
    
    //领取截止时间
    private Date   endTime;

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    public String getGainNum() {
        return gainNum;
    }

    public void setGainNum(String gainNum) {
        this.gainNum = gainNum;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    
 
}
