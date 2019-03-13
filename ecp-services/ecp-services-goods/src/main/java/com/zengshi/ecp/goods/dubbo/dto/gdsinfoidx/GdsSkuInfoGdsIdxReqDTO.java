package com.zengshi.ecp.goods.dubbo.dto.gdsinfoidx;

import java.sql.Timestamp;
import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;
@SuppressWarnings("rawtypes")
public class GdsSkuInfoGdsIdxReqDTO extends BaseInfo    {

    private static final long serialVersionUID = 1L;

    private Long skuId;

    private Long gdsId;

    private Long snapId;

    private Long  shopId;

    private String skuProps;

    private String skuName;

    private String gdsStatus;
    
    private List<String> gdsstatues;

    private String skuApprove;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;


    public Long getGdsId() {
        return gdsId;
    }

    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }

    public Long getSnapId() {
        return snapId;
    }

    public void setSnapId(Long snapId) {
        this.snapId = snapId;
    }

    public Long  getShopId() {
        return shopId;
    }

    public void setShopId(Long  shopId) {
        this.shopId = shopId;
    }

    public String getSkuProps() {
        return skuProps;
    }

    public void setSkuProps(String skuProps) {
        this.skuProps = skuProps == null ? null : skuProps.trim();
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName == null ? null : skuName.trim();
    }


    public String getSkuApprove() {
        return skuApprove;
    }

    public void setSkuApprove(String skuApprove) {
        this.skuApprove = skuApprove == null ? null : skuApprove.trim();
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Long getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
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

    public String getGdsStatus() {
        return gdsStatus;
    }

    public void setGdsStatus(String gdsStatus) {
        this.gdsStatus = gdsStatus;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(skuId);
        sb.append(", gdsId=").append(gdsId);
        sb.append(", snapId=").append(snapId);
        sb.append(", shopId=").append(shopId);
        sb.append(", skuProps=").append(skuProps);
        sb.append(", skuName=").append(skuName);
        sb.append(", gdsStatus=").append(gdsStatus);
        sb.append(", skuApprove=").append(skuApprove);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append("]");
        return sb.toString();
    }

    public List<String> getGdsstatues() {
        return gdsstatues;
    }

    public void setGdsstatues(List<String> gdsstatues) {
        this.gdsstatues = gdsstatues;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }
}
