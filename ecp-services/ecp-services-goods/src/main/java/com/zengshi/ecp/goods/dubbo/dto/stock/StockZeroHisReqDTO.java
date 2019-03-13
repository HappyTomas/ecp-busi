package com.zengshi.ecp.goods.dubbo.dto.stock;

import java.sql.Timestamp;
import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class StockZeroHisReqDTO extends BaseInfo {
   
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 5231555366245037890L;

    /**
     * 产品ID
     */
    private Long gdsId;

    /**
     * 单品ID
     */
    private Long skuId;
    
    /**
     * 创建人
     */
    private Long createStaff;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 更新人
     */
    private Long updateStaff;

    /**
     * 更新时间
     */
    private Timestamp updateTime;
    
    
    private List<Long> gdsIdLst;


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

    public Long getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(Long updateStaff) {
        this.updateStaff = updateStaff;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Long getGdsId() {
        return gdsId;
    }

    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }
    

    public List<Long> getGdsIdLst() {
        return gdsIdLst;
    }

    public void setGdsIdLst(List<Long> gdsIdLst) {
        this.gdsIdLst = gdsIdLst;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("StockZeroHisReqDTO [gdsId=");
        builder.append(gdsId);
        builder.append(", skuId=");
        builder.append(skuId);
        builder.append(", createStaff=");
        builder.append(createStaff);
        builder.append(", createTime=");
        builder.append(createTime);
        builder.append(", updateStaff=");
        builder.append(updateStaff);
        builder.append(", updateTime=");
        builder.append(updateTime);
        builder.append(", gdsIdLst=");
        builder.append(gdsIdLst);
        builder.append("]");
        return builder.toString();
    }

    
    
    
}
