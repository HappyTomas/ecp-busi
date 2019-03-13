package com.zengshi.ecp.search.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class IndexReusltRespDTO extends BaseResponseDTO {

    private static final String EXECUTING = "索引重建中...！";

    private static final String FINISHED = "索引重建完成！";

    private static final String DUPLICATED = "已经有一个重建索引实例存在！";
    
    private static final String NOTEXIST="集合不存在！";

    public void setExecuting() {
        this.message = EXECUTING;
    }

    public void setFinished() {
        this.message = FINISHED;
    }

    public void setDuplicated() {
        this.message = DUPLICATED;
    }
    
    public void setNotExist() {
        this.message = NOTEXIST;
    }

    public IndexReusltRespDTO() {
        this.setExecuting();
    }

    /**
     * serialVersionUID:
     */
    private static final long serialVersionUID = 1378536313538302595L;

    /**
     * 分页全量查询总记录数
     */
    private long dataListCount = 0l;

    /**
     * 索引录入失败记录数
     */
    private long failCount = 0l;

    /**
     * 索引录入失败记录详细信息（不保留全部，字段空间受限）
     */
    /*private List<String> failRecordList =  new ArrayList<String>();*/
    
    /**
     * id列表（不保留全部，字段空间受限）
     */
    private List<String> failIdList = new ArrayList<String>();
    
    /**
     * 开始时间
     */
    private Timestamp start;
    
    /**
     * 结束时间
     */
    private Timestamp end;

    /**
     * 耗时，秒
     */
    private long timeCost = 0l;
    
    /**
     * tps
     */
    private Short tps;
    
    /**
     * 索引重建结果
     */
    private boolean success=true;
    
    /**
     * 索引重建结果信息
     */
    private String message;
    
    /**
     * 参数信息
     */
    private String args;

    public void increaseDataListCountBy(long step) {
        this.dataListCount += step;
    }

    public void increaseFailCountBy(long step) {
        this.failCount += step;
    }

    /*public void addFailRecord(String failRecord) {
        this.failRecordList.add(failRecord);
    }*/
    
    public void addFailId(String failId) {
        this.failIdList.add(failId);
    }

    public long getDataListCount() {
        return dataListCount;
    }

    public void setDataListCount(long dataListCount) {
        this.dataListCount = dataListCount;
    }

    public long getSuccessCount() {
        return this.dataListCount - this.failCount;
    }

    public long getFailCount() {
        return failCount;
    }

    public void setFailCount(long failCount) {
        this.failCount = failCount;
    }

    /*public List<String> getFailRecordList() {
        return failRecordList;
    }

    public void setFailRecordList(List<String> failRecordList) {
        this.failRecordList = failRecordList;
    }*/
    
    public List<String> getFailIdList() {
        return failIdList;
    }

    public void setFailIdList(List<String> failIdList) {
        this.failIdList = failIdList;
    }

    public long getTimeCost() {
        return timeCost;
    }

    public void setTimeCost(long timeCost) {
        this.timeCost = timeCost;
    }
    
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }

    public Short getTps() {
        return tps;
    }

    public void setTps(Short tps) {
        this.tps = tps;
    }
    
    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    @Override
    public String toString() {
        return "IndexReusltRespDTO{" +
                "args='" + args + '\'' +
                ", dataListCount=" + dataListCount +
                ", failCount=" + failCount +
                ", failIdList=" + failIdList +
                ", start=" + start +
                ", end=" + end +
                ", timeCost=" + timeCost +
                ", tps=" + tps +
                ", success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}
