package com.zengshi.ecp.staff.dao.model;

import java.io.Serializable;

public class ScoreCategoryRatioKey implements Serializable {
    private String actionType;

    private String catgCode;

    private static final long serialVersionUID = 1L;

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType == null ? null : actionType.trim();
    }

    public String getCatgCode() {
        return catgCode;
    }

    public void setCatgCode(String catgCode) {
        this.catgCode = catgCode == null ? null : catgCode.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", actionType=").append(actionType);
        sb.append(", catgCode=").append(catgCode);
        sb.append("]");
        return sb.toString();
    }
}