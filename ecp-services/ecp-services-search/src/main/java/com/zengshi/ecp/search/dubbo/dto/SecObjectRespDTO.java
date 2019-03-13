package com.zengshi.ecp.search.dubbo.dto;

import java.sql.Timestamp;
import java.util.List;

import com.zengshi.ecp.general.solr.message.DeltaMessage;
import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class SecObjectRespDTO extends BaseResponseDTO {

    /** 
     * serialVersionUID:
     */ 
    private static final long serialVersionUID = 917570114433368694L;
    
    private Long id;

    private String objectNamecn;

    private String objectNameen;

    private String objectDesc;

    private String objectUniquefieldName;

    private String objectProcessorName;

    private String dubboServicename;

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
    
    private String objectParams;

    private String methodQuerybyidParamType;

    private String lanField;

    private String lanFieldFieldType;

    private String mulvalfieldProcessorName;
    
    private List<SecFieldRespDTO> secFieldRespDTOList;
    
    private DeltaMessage deltaMessage;

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
    
    public String getObjectParams() {
        return objectParams;
    }

    public void setObjectParams(String objectParams) {
        this.objectParams = objectParams;
    }

    public String getMethodQuerybyidParamType() {
        return methodQuerybyidParamType;
    }

    public void setMethodQuerybyidParamType(String methodQuerybyidParamType) {
        this.methodQuerybyidParamType = methodQuerybyidParamType == null ? null : methodQuerybyidParamType.trim();
    }

    public String getLanField() {
        return lanField;
    }

    public void setLanField(String lanField) {
        this.lanField = lanField == null ? null : lanField.trim();
    }

    public String getLanFieldFieldType() {
        return lanFieldFieldType;
    }

    public void setLanFieldFieldType(String lanFieldFieldType) {
        this.lanFieldFieldType = lanFieldFieldType == null ? null : lanFieldFieldType.trim();
    }

    public String getMulvalfieldProcessorName() {
        return mulvalfieldProcessorName;
    }

    public void setMulvalfieldProcessorName(String mulvalfieldProcessorName) {
        this.mulvalfieldProcessorName = mulvalfieldProcessorName == null ? null : mulvalfieldProcessorName.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", objectNamecn=").append(objectNamecn);
        sb.append(", objectTable=").append(objectNameen);
        sb.append(", objectDesc=").append(objectDesc);
        sb.append(", objectUniquefieldName=").append(objectUniquefieldName);
        sb.append(", objectProcessorName=").append(objectProcessorName);
        sb.append(", dubboServicename=").append(dubboServicename);
        sb.append(", objectType=").append(objectType);
        sb.append(", status=").append(status);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", methodQuerybyidParamType=").append(methodQuerybyidParamType);
        sb.append("]");
        return sb.toString();
    }

    public List<SecFieldRespDTO> getSecFieldRespDTOList() {
        return secFieldRespDTOList;
    }

    public void setSecFieldRespDTOList(List<SecFieldRespDTO> secFieldRespDTOList) {
        this.secFieldRespDTOList = secFieldRespDTOList;
    }

    public DeltaMessage getDeltaMessage() {
        return deltaMessage;
    }

    public void setDeltaMessage(DeltaMessage deltaMessage) {
        this.deltaMessage = deltaMessage;
    }
    
    

}

