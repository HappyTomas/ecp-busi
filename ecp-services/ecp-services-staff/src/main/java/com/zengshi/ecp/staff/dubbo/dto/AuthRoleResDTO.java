package com.zengshi.ecp.staff.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class AuthRoleResDTO extends BaseResponseDTO {
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1L;

    private Long id;

    private String roleName;

    private Long roleOrder;

    private String status;

    private String sysCode;

    private String roleDesc;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;
    
    private String statusValue;
    
    private String sysCodeValue;
    
    private String privlgMenu; //菜单相关权限
    
    private String privlgMenuValue; //菜单相关权限名称

    private String menu; //相关菜单
    
    private String menuValue; //相关菜单名称
    
    private String privlgRule; //规则相关权限
    
    private String privlgRuleValue; //规则相关权限名称

    private String rule; //相关规则
    
    private String ruleValue; //相关规则名称

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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
        this.status = status;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
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

    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }

    public String getSysCodeValue() {
        return sysCodeValue;
    }

    public void setSysCodeValue(String sysCodeValue) {
        this.sysCodeValue = sysCodeValue;
    }

    public String getPrivlgMenu() {
        return privlgMenu;
    }

    public void setPrivlgMenu(String privlgMenu) {
        this.privlgMenu = privlgMenu;
    }

    public String getPrivlgMenuValue() {
        return privlgMenuValue;
    }

    public void setPrivlgMenuValue(String privlgMenuValue) {
        this.privlgMenuValue = privlgMenuValue;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getMenuValue() {
        return menuValue;
    }

    public void setMenuValue(String menuValue) {
        this.menuValue = menuValue;
    }

	public String getPrivlgRule() {
		return privlgRule;
	}

	public void setPrivlgRule(String privlgRule) {
		this.privlgRule = privlgRule;
	}

	public String getPrivlgRuleValue() {
		return privlgRuleValue;
	}

	public void setPrivlgRuleValue(String privlgRuleValue) {
		this.privlgRuleValue = privlgRuleValue;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public String getRuleValue() {
		return ruleValue;
	}

	public void setRuleValue(String ruleValue) {
		this.ruleValue = ruleValue;
	}
}

