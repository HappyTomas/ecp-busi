package com.zengshi.ecp.goods.dubbo.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class GdsType2PropReqDTO extends BaseInfo{

    /** 
     * serialVersionUID:
     */ 
    private static final long serialVersionUID = 1L;
    
    private Long typeId;

    private Long propId;
    
    private String ifBasic;

    private String ifHaveto;

    private String ifSearch;

    private String ifGdsInput;

    private String status;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;

    private String ifAbleEidt;
    
    private List<Long> propIds;
    
    private String propName;
    
    /**
     * 商品录入，编辑查询需要传
     */
    private boolean ifGdsInputQuery;
    
    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
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

    public String getIfGdsInput() {
        return ifGdsInput;
    }

    public void setIfGdsInput(String ifGdsInput) {
        this.ifGdsInput = ifGdsInput == null ? null : ifGdsInput.trim();
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

    public String getIfAbleEidt() {
        return ifAbleEidt;
    }

    public void setIfAbleEidt(String ifAbleEidt) {
        this.ifAbleEidt = ifAbleEidt == null ? null : ifAbleEidt.trim();
    }
    
    public List<Long> getPropIds() {
        return propIds;
    }

    public void setPropIds(List<Long> propIds) {
        this.propIds = propIds;
    }
    
    public void addPropId(Long propId){
        if(null ==  propId) 
            return ;
        if(null == propIds){
            propIds = new ArrayList<>();
        }
        propIds.add(propId);
        
    }
    
    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }
    
    public boolean isIfGdsInputQuery() {
        return ifGdsInputQuery;
    }

    public void setIfGdsInputQuery(boolean ifGdsInputQuery) {
        this.ifGdsInputQuery = ifGdsInputQuery;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", ifBasic=").append(ifBasic);
        sb.append(", ifHaveto=").append(ifHaveto);
        sb.append(", ifSearch=").append(ifSearch);
        sb.append(", ifGdsInput=").append(ifGdsInput);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", ifAbleEidt=").append(ifAbleEidt);
        sb.append("]");
        return sb.toString();
    }

}

