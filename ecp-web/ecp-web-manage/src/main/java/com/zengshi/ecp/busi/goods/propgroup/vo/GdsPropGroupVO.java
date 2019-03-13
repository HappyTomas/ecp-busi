package com.zengshi.ecp.busi.goods.propgroup.vo;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class GdsPropGroupVO extends EcpBasePageReqVO{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 4508413027728106235L;
    
    private Long id;//属性组编码
    
    @NotNull(message="{goods.GdsPropGroupVO.groupName.null.error}")
    @Length(min=0,max=32, message="{goods.GdsPropGroupVO.groupName.length.error}")
    private String groupName;
    
    @NotNull(message="{goods.GdsPropGroupVO.groupDesc.null.error}")
    @Length(min=0,max=64, message="{goods.GdsPropGroupVO.groupDesc.length.error}")
    private String groupDesc;

    private String status;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;
    
    //属性组列表id参数
    private String propIdParam;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getPropIdParam() {
        return propIdParam;
    }

    public void setPropIdParam(String propIdParam) {
        this.propIdParam = propIdParam;
    }
    
    
}

