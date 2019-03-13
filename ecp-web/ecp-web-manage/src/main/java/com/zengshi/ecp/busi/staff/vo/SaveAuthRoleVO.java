package com.zengshi.ecp.busi.staff.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class SaveAuthRoleVO extends EcpBasePageReqVO implements Serializable {
    private Long id;
    @NotBlank(message="{staff.roleName.null.error}")
    @Length(max=64, message="{staff.roleName.length.error}")
    private String roleName;
    
    private Long roleOrder;

    private String status;

    @NotBlank(message="{staff.sysCode.null.error}")
    private String sysCode;

    @NotBlank(message="{staff.roleDesc.null.error}")
    @Length(max=240, message="{staff.roleDesc.length.error}")
    private String roleDesc;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;
    
    private String privlgMenu; //菜单相关权限

    private String privlgRule; //规则相关权限

    private static final long serialVersionUID = 1L;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public Long getRoleOrder() {
        return roleOrder;
    }

    public void setRoleOrder(Long roleOrder) {
        this.roleOrder = roleOrder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode == null ? null : sysCode.trim();
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc == null ? null : roleDesc.trim();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", roleName=").append(roleName);
        sb.append(", roleOrder=").append(roleOrder);
        sb.append(", status=").append(status);
        sb.append(", sysCode=").append(sysCode);
        sb.append(", roleDesc=").append(roleDesc);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append("]");
        return sb.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrivlgMenu() {
        return privlgMenu;
    }

    public void setPrivlgMenu(String privlgMenu) {
        this.privlgMenu = privlgMenu;
    }

	public String getPrivlgRule() {
		return privlgRule;
	}

	public void setPrivlgRule(String privlgRule) {
		this.privlgRule = privlgRule;
	}
}