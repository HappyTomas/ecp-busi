package com.zengshi.ecp.staff.dubbo.dto;

import java.math.BigDecimal;
import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class DataAuthReqDTO extends BaseInfo {
    
    //规则相关信息
    private List<DataRuleReqDTO> ruleArr; //规则下的规则明细配置
    
    private List<DataFieldRuleReqDTO> fieldRuleArr; //规则下的过滤规则明细
    
    private Long roleId;//角色关联
    
    //数据规则
    private Long id;

    private String name;

    private String authCode;

    private Long funcId;

    private String authType;

    private String enabled;

    private String sysCode;
    
    private BigDecimal authSrc;
    
    private String authDesc;

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

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode == null ? null : authCode.trim();
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
        this.authType = authType == null ? null : authType.trim();
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled == null ? null : enabled.trim();
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode == null ? null : sysCode.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", authCode=").append(authCode);
        sb.append(", funcId=").append(funcId);
        sb.append(", authType=").append(authType);
        sb.append(", enabled=").append(enabled);
        sb.append(", sysCode=").append(sysCode);
        sb.append("]");
        return sb.toString();
    }

	public List<DataRuleReqDTO> getRuleArr() {
		return ruleArr;
	}

	public void setRuleArr(List<DataRuleReqDTO> ruleArr) {
		this.ruleArr = ruleArr;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public BigDecimal getAuthSrc() {
		return authSrc;
	}

	public void setAuthSrc(BigDecimal authSrc) {
		this.authSrc = authSrc;
	}

	public String getAuthDesc() {
		return authDesc;
	}

	public void setAuthDesc(String authDesc) {
		this.authDesc = authDesc;
	}

	public List<DataFieldRuleReqDTO> getFieldRuleArr() {
		return fieldRuleArr;
	}

	public void setFieldRuleArr(List<DataFieldRuleReqDTO> fieldRuleArr) {
		this.fieldRuleArr = fieldRuleArr;
	}
}