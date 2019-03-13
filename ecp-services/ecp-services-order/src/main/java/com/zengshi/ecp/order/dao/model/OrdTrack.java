package com.zengshi.ecp.order.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class OrdTrack implements Serializable {
    /**
     * 流水号
     */
    private Long id;

    /**
     * 订单号
     */
    private String orderId;

    /**
     * 子订单
     */
    private String orderSubId;

    /**
     * 操作节点
     */
    private String node;

    /**
     * 操作节点说明
     */
    private String nodeDesc;

    /**
     * 
     */
    private String remark;

    /**
     * 修改时间
     */
    private Timestamp updateTime;

    /**
     * 修改人
     */
    private Long updateStaff;

    /**
     * 创建人
     */
    private Long createStaff;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 
     */
    private Long companyId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getOrderSubId() {
        return orderSubId;
    }

    public void setOrderSubId(String orderSubId) {
        this.orderSubId = orderSubId == null ? null : orderSubId.trim();
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node == null ? null : node.trim();
    }

    public String getNodeDesc() {
        return nodeDesc;
    }

    public void setNodeDesc(String nodeDesc) {
        this.nodeDesc = nodeDesc == null ? null : nodeDesc.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderId=").append(orderId);
        sb.append(", orderSubId=").append(orderSubId);
        sb.append(", node=").append(node);
        sb.append(", nodeDesc=").append(nodeDesc);
        sb.append(", remark=").append(remark);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", createTime=").append(createTime);
        sb.append(", companyId=").append(companyId);
        sb.append("]");
        return sb.toString();
    }
}