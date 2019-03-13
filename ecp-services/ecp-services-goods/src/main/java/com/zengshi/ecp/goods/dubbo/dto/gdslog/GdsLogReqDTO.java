package com.zengshi.ecp.goods.dubbo.dto.gdslog;

import java.sql.Timestamp;
import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class GdsLogReqDTO extends BaseInfo {
	
	
    /** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
	private static final long serialVersionUID = 1730215025377634612L;

	private Long logId;

    private String moduleName;

    private String operType;

    private Short operResult;

    private String operParam;

    private String errMsg;

    private Timestamp startTime;

    private Timestamp endTime;

    private String status;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;

    //排序字段规则，字段名和字段排序使用半角逗号间隔
    private List<String> sortRule;

    private Timestamp begUpdateTime;

    private Timestamp endUpdateTime;
    
    private List<String>  operTypes;

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName == null ? null : moduleName.trim();
    }

    public String getOperType() {
        return operType;
    }

    public void setOperType(String operType) {
        this.operType = operType == null ? null : operType.trim();
    }

    public Short getOperResult() {
        return operResult;
    }

    public void setOperResult(Short operResult) {
        this.operResult = operResult;
    }

    public String getOperParam() {
        return operParam;
    }

    public void setOperParam(String operParam) {
        this.operParam = operParam == null ? null : operParam.trim();
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg == null ? null : errMsg.trim();
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
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

    public List<String> getSortRule() {
        return sortRule;
    }

    public void setSortRule(List<String> sortRule) {
        this.sortRule = sortRule;
    }

    public Timestamp getBegUpdateTime() {
        return begUpdateTime;
    }

    public void setBegUpdateTime(Timestamp begUpdateTime) {
        this.begUpdateTime = begUpdateTime;
    }

    public Timestamp getEndUpdateTime() {
        return endUpdateTime;
    }

    public void setEndUpdateTime(Timestamp endUpdateTime) {
        this.endUpdateTime = endUpdateTime;
    }

    public List<String> getOperTypes() {
        return operTypes;
    }

    public void setOperTypes(List<String> operTypes) {
        this.operTypes = operTypes;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("GdsLogReqDTO [logId=");
        builder.append(logId);
        builder.append(", moduleName=");
        builder.append(moduleName);
        builder.append(", operType=");
        builder.append(operType);
        builder.append(", operResult=");
        builder.append(operResult);
        builder.append(", operParam=");
        builder.append(operParam);
        builder.append(", errMsg=");
        builder.append(errMsg);
        builder.append(", startTime=");
        builder.append(startTime);
        builder.append(", endTime=");
        builder.append(endTime);
        builder.append(", status=");
        builder.append(status);
        builder.append(", createTime=");
        builder.append(createTime);
        builder.append(", createStaff=");
        builder.append(createStaff);
        builder.append(", updateTime=");
        builder.append(updateTime);
        builder.append(", updateStaff=");
        builder.append(updateStaff);
        builder.append(", sortRule=");
        builder.append(sortRule);
        builder.append(", begUpdateTime=");
        builder.append(begUpdateTime);
        builder.append(", endUpdateTime=");
        builder.append(endUpdateTime);
        builder.append(", operTypes=");
        builder.append(operTypes);
        builder.append("]");
        return builder.toString();
    }

    
}
