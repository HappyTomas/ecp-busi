package com.zengshi.ecp.goods.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class GdsCatg2Prop implements Serializable {
    private String catgCode;

    private Long propId;

    private String ifBasic;

    private String ifHaveto;

    private String ifSearch;

    private String status;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;

    private String ifGdsInput;

    private String ifAbleEdit;

    private static final long serialVersionUID = 1L;

    public String getCatgCode() {
        return catgCode;
    }

    public void setCatgCode(String catgCode) {
        this.catgCode = catgCode == null ? null : catgCode.trim();
    }

    public Long getPropId() {
        return propId;
    }

    public void setPropId(Long propId) {
        this.propId = propId;
    }

    public String getIfBasic() {
        return ifBasic;
    }

    public void setIfBasic(String ifBasic) {
        this.ifBasic = ifBasic == null ? null : ifBasic.trim();
    }

    public String getIfHaveto() {
        return ifHaveto;
    }

    public void setIfHaveto(String ifHaveto) {
        this.ifHaveto = ifHaveto == null ? null : ifHaveto.trim();
    }

    public String getIfSearch() {
        return ifSearch;
    }

    public void setIfSearch(String ifSearch) {
        this.ifSearch = ifSearch == null ? null : ifSearch.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

    public String getIfGdsInput() {
        return ifGdsInput;
    }

    public void setIfGdsInput(String ifGdsInput) {
        this.ifGdsInput = ifGdsInput == null ? null : ifGdsInput.trim();
    }

    public String getIfAbleEdit() {
        return ifAbleEdit;
    }

    public void setIfAbleEdit(String ifAbleEdit) {
        this.ifAbleEdit = ifAbleEdit == null ? null : ifAbleEdit.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", catgCode=").append(catgCode);
        sb.append(", propId=").append(propId);
        sb.append(", ifBasic=").append(ifBasic);
        sb.append(", ifHaveto=").append(ifHaveto);
        sb.append(", ifSearch=").append(ifSearch);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", ifGdsInput=").append(ifGdsInput);
        sb.append(", ifAbleEdit=").append(ifAbleEdit);
        sb.append("]");
        return sb.toString();
    }
}
