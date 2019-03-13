package com.zengshi.ecp.general.order.dto;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class PayQuartzInfoRequest extends BaseInfo {
    //定时任务id
    private Long payQuartzInfoId;
    //订单编码
    private String orderId;
    //用户编码
    private long staffId;
    //订单金额
    private long payment;
    //来源类型    
    private String sourceType;
    //
    private List<ROrdCartItemCommRequest> ordCartItemCommList;
    //定时任务处理标准
    private String dealFlag;
    //站点Id
    private Long siteId;
    //操作工号
    private Long updateStaff;
    //创建人
    private Long createStaff;
    //订单级别促销赠送积分
    private Long sendOrderMainScore;
    //订单级别促销赠送积分倍数
    private double sendOrderMainScoreTimes;
    //任务类型
    private String taskType;

    private static final long serialVersionUID = 1L;
    
    public Long getSiteId(){
        return siteId;
    }
    
    public void setSiteId(Long siteId){
        this.siteId = siteId;
    }
    
    public String getSourceType(){
        return sourceType;
    }

    public void setSourceType(String sourceType){
        this.sourceType = sourceType;
    }
    
    public Long getPayQuartzInfoId() {
        return payQuartzInfoId;
    }

    public void setPayQuartzInfoId(Long payQuartzInfoId) {
        this.payQuartzInfoId = payQuartzInfoId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public long getPayment() {
        return payment;
    }

    public void setPayment(long payment) {
        this.payment = payment;
    }

    public void setStaffId(long staffId) {
        this.staffId = staffId;
    }

    public List<ROrdCartItemCommRequest> getOrdCartItemCommList() {
        return ordCartItemCommList;
    }

    public void setOrdCartItemCommList(List<ROrdCartItemCommRequest> ordCartItemCommList) {
        this.ordCartItemCommList = ordCartItemCommList;
    }

    public String getDealFlag() {
        return dealFlag;
    }

    public void setDealFlag(String dealFlag) {
        this.dealFlag = dealFlag;
    }

    public Long getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(Long updateStaff) {
        this.updateStaff = updateStaff;
    }

    public Long getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }

    public Long getSendOrderMainScore() {
        return sendOrderMainScore;
    }

    public void setSendOrderMainScore(Long sendOrderMainScore) {
        this.sendOrderMainScore = sendOrderMainScore;
    }

    public double getSendOrderMainScoreTimes() {
        return sendOrderMainScoreTimes;
    }

    public void setSendOrderMainScoreTimes(double sendOrderMainScoreTimes) {
        this.sendOrderMainScoreTimes = sendOrderMainScoreTimes;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType == null ? null : taskType.trim();
    }

    
    
}
