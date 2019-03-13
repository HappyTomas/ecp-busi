package com.zengshi.ecp.search.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class SecObject implements Serializable {
    private Long id;

    private String objectNamecn;

    private String objectNameen;

    private String objectDesc;

    private String objectUniquefieldName;

    private String objectProcessorName;

    private String dubboServicename;

    private String objectParams;

    private String methodQuerybyidParamType;

    private String objectType;

    private String status;

    private Long createStaff;

    private Timestamp createTime;

    private Long updateStaff;

    private Timestamp updateTime;

    private String objectHandler;

    private String objectInspector;

    private String pagesize;

    private String insidepager;

    private String insidepagerUniquefieldName;

    private String objectIfMultilan;

    private String lans;

    private String lanField;

    private String mulvalfieldProcessorName;

    private String lanFieldFieldType;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObjectNamecn() {
        return objectNamecn;
    }

    public void setObjectNamecn(String objectNamecn) {
        this.objectNamecn = objectNamecn == null ? null : objectNamecn.trim();
    }

    public String getObjectNameen() {
        return objectNameen;
    }

    public void setObjectNameen(String objectNameen) {
        this.objectNameen = objectNameen == null ? null : objectNameen.trim();
    }

    public String getObjectDesc() {
        return objectDesc;
    }

    public void setObjectDesc(String objectDesc) {
        this.objectDesc = objectDesc == null ? null : objectDesc.trim();
    }

    public String getObjectUniquefieldName() {
        return objectUniquefieldName;
    }

    public void setObjectUniquefieldName(String objectUniquefieldName) {
        this.objectUniquefieldName = objectUniquefieldName == null ? null : objectUniquefieldName.trim();
    }

    public String getObjectProcessorName() {
        return objectProcessorName;
    }

    public void setObjectProcessorName(String objectProcessorName) {
        this.objectProcessorName = objectProcessorName == null ? null : objectProcessorName.trim();
    }

    public String getDubboServicename() {
        return dubboServicename;
    }

    public void setDubboServicename(String dubboServicename) {
        this.dubboServicename = dubboServicename == null ? null : dubboServicename.trim();
    }

    public String getObjectParams() {
        return objectParams;
    }

    public void setObjectParams(String objectParams) {
        this.objectParams = objectParams == null ? null : objectParams.trim();
    }

    public String getMethodQuerybyidParamType() {
        return methodQuerybyidParamType;
    }

    public void setMethodQuerybyidParamType(String methodQuerybyidParamType) {
        this.methodQuerybyidParamType = methodQuerybyidParamType == null ? null : methodQuerybyidParamType.trim();
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType == null ? null : objectType.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

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

    public String getObjectHandler() {
        return objectHandler;
    }

    public void setObjectHandler(String objectHandler) {
        this.objectHandler = objectHandler == null ? null : objectHandler.trim();
    }

    public String getObjectInspector() {
        return objectInspector;
    }

    public void setObjectInspector(String objectInspector) {
        this.objectInspector = objectInspector == null ? null : objectInspector.trim();
    }

    public String getPagesize() {
        return pagesize;
    }

    public void setPagesize(String pagesize) {
        this.pagesize = pagesize == null ? null : pagesize.trim();
    }

    public String getInsidepager() {
        return insidepager;
    }

    public void setInsidepager(String insidepager) {
        this.insidepager = insidepager == null ? null : insidepager.trim();
    }

    public String getInsidepagerUniquefieldName() {
        return insidepagerUniquefieldName;
    }

    public void setInsidepagerUniquefieldName(String insidepagerUniquefieldName) {
        this.insidepagerUniquefieldName = insidepagerUniquefieldName == null ? null : insidepagerUniquefieldName.trim();
    }

    public String getObjectIfMultilan() {
        return objectIfMultilan;
    }

    public void setObjectIfMultilan(String objectIfMultilan) {
        this.objectIfMultilan = objectIfMultilan == null ? null : objectIfMultilan.trim();
    }

    public String getLans() {
        return lans;
    }

    public void setLans(String lans) {
        this.lans = lans == null ? null : lans.trim();
    }

    public String getLanField() {
        return lanField;
    }

    public void setLanField(String lanField) {
        this.lanField = lanField == null ? null : lanField.trim();
    }

    public String getMulvalfieldProcessorName() {
        return mulvalfieldProcessorName;
    }

    public void setMulvalfieldProcessorName(String mulvalfieldProcessorName) {
        this.mulvalfieldProcessorName = mulvalfieldProcessorName == null ? null : mulvalfieldProcessorName.trim();
    }

    public String getLanFieldFieldType() {
        return lanFieldFieldType;
    }

    public void setLanFieldFieldType(String lanFieldFieldType) {
        this.lanFieldFieldType = lanFieldFieldType == null ? null : lanFieldFieldType.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", objectNamecn=").append(objectNamecn);
        sb.append(", objectNameen=").append(objectNameen);
        sb.append(", objectDesc=").append(objectDesc);
        sb.append(", objectUniquefieldName=").append(objectUniquefieldName);
        sb.append(", objectProcessorName=").append(objectProcessorName);
        sb.append(", dubboServicename=").append(dubboServicename);
        sb.append(", objectParams=").append(objectParams);
        sb.append(", methodQuerybyidParamType=").append(methodQuerybyidParamType);
        sb.append(", objectType=").append(objectType);
        sb.append(", status=").append(status);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", objectHandler=").append(objectHandler);
        sb.append(", objectInspector=").append(objectInspector);
        sb.append(", pagesize=").append(pagesize);
        sb.append(", insidepager=").append(insidepager);
        sb.append(", insidepagerUniquefieldName=").append(insidepagerUniquefieldName);
        sb.append(", objectIfMultilan=").append(objectIfMultilan);
        sb.append(", lans=").append(lans);
        sb.append(", lanField=").append(lanField);
        sb.append(", mulvalfieldProcessorName=").append(mulvalfieldProcessorName);
        sb.append(", lanFieldFieldType=").append(lanFieldFieldType);
        sb.append("]");
        return sb.toString();
    }
}