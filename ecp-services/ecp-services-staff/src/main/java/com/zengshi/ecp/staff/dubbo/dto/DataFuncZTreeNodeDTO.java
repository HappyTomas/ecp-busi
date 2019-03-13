package com.zengshi.ecp.staff.dubbo.dto;

import java.util.Map;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class DataFuncZTreeNodeDTO extends BaseResponseDTO {
    //数据功能
	private Long id;

    private String name;

    private String code;

    private Long parentId;
 
    private String sysCode;

    private String funcDesc;

    private Short sortOrder;

    private String status;
    
    //树节点属性：是否父节点
    private Boolean isParent; 
    
    //数据规则
    private String authCode;

    private Long funcId;

    private String authType;

    private String enabled;
    
    //树节点样式
    private Map<String, String> font;
    //树节点id(字符型)
    private String zId;
    //树节点类型
    private String zType;
    //树节点父id(字符型)
    private String zPId;
    //树节点状态 
    private String zStatus;
    //树节点 展开 / 折叠 状态
    private Boolean open;
    
    //数据规则 - AuthPrivilege
    private Long privilegeId;

    private String roleAdmin;

    private String privilegeType;

    private String privilegeSysCode;

    private String privilegeStatus;
    

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode == null ? null : sysCode.trim();
    }

    public String getFuncDesc() {
        return funcDesc;
    }

    public void setFuncDesc(String funcDesc) {
        this.funcDesc = funcDesc == null ? null : funcDesc.trim();
    }

    public Short getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Short sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", code=").append(code);
        sb.append(", parentId=").append(parentId);
        sb.append(", sysCode=").append(sysCode);
        sb.append(", funcDesc=").append(funcDesc);
        sb.append(", sortOrder=").append(sortOrder);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }

	public Boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public Long getFuncId() {
		return funcId;
	}

	public void setFuncId(Long funcId) {
		this.funcId = funcId;
	}

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public Map<String, String> getFont() {
		return font;
	}

	public void setFont(Map<String, String> font) {
		this.font = font;
	}

	public Long getPrivilegeId() {
		return privilegeId;
	}

	public void setPrivilegeId(Long privilegeId) {
		this.privilegeId = privilegeId;
	}

	public String getRoleAdmin() {
		return roleAdmin;
	}

	public void setRoleAdmin(String roleAdmin) {
		this.roleAdmin = roleAdmin;
	}

	public String getPrivilegeType() {
		return privilegeType;
	}

	public void setPrivilegeType(String privilegeType) {
		this.privilegeType = privilegeType;
	}

	public String getPrivilegeSysCode() {
		return privilegeSysCode;
	}

	public void setPrivilegeSysCode(String privilegeSysCode) {
		this.privilegeSysCode = privilegeSysCode;
	}

	public String getPrivilegeStatus() {
		return privilegeStatus;
	}

	public void setPrivilegeStatus(String privilegeStatus) {
		this.privilegeStatus = privilegeStatus;
	}

	public String getzId() {
		return zId;
	}

	public void setzId(String zId) {
		this.zId = zId;
	}

	public String getzType() {
		return zType;
	}

	public void setzType(String zType) {
		this.zType = zType;
	}

	public String getzPId() {
		return zPId;
	}

	public void setzPId(String zPId) {
		this.zPId = zPId;
	}

	public String getzStatus() {
		return zStatus;
	}

	public void setzStatus(String zStatus) {
		this.zStatus = zStatus;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}
}