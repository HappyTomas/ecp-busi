package com.zengshi.ecp.busi.staff.vo;

import java.util.List;

import com.zengshi.ecp.base.vo.EcpBaseResponseVO;

public class FindDataAuthVO extends EcpBaseResponseVO {
    private Long id;

    private String name;

    private String authCode;

    private String funcCode;

    private String authType;

    private String enabled;
    
    private List<DataRuleVO> ruleArr;
    
    private Long roleId;
    
    private Long funcId; 

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

    public String getFuncCode() {
        return funcCode;
    }

    public void setFuncCode(String funcCode) {
        this.funcCode = funcCode == null ? null : funcCode.trim();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", authCode=").append(authCode);
        sb.append(", funcCode=").append(funcCode);
        sb.append(", authType=").append(authType);
        sb.append(", enabled=").append(enabled);
        sb.append("]");
        return sb.toString();
    }


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getFuncId() {
        return funcId;
    }

    public void setFuncId(Long funcId) {
        this.funcId = funcId;
    }

    public List<DataRuleVO> getRuleArr() {
        return ruleArr;
    }

    public void setRuleArr(List<DataRuleVO> ruleArr) {
        this.ruleArr = ruleArr;
    }



}