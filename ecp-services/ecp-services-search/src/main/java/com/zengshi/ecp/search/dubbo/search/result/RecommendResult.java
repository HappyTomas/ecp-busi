package com.zengshi.ecp.search.dubbo.search.result;

import java.io.Serializable;
import java.util.List;

/**
 * Created by HDF on 2016/9/22.
 */
public class RecommendResult<T> implements Serializable {

    /**
     * 分页大小
     */
    private int pageSize;

    /**
     * 当前页码
     */
    private long pageNo;

    /**
     * 结果列表
     */
    private List<T> resultList;

    /**
     * 查询耗时
     */
    private int qTime;

    /**
     * 请求Url地址
     */
    private String requestUrl;

    /**
     * 状态，默认请求失败
     */
    private int status = -1;

    /**
     * 错误或提示信息
     */
    private String message;

    /**
     * 记录结果数或搜索建议结果数
     */
    private long numFound = 0;

    /**
     * 总页数
     */
    private long totallyPage;

    public boolean isSuccess() {
        return status == 0;
    }

    public void setSuccess() {
        this.status = 0;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getNumFound() {
        return numFound;
    }

    public void setNumFound(long numFound) {
        this.numFound = numFound;
    }

    public long getPageNo() {
        return pageNo;
    }

    public void setPageNo(long pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getqTime() {
        return qTime;
    }

    public void setqTime(int qTime) {
        this.qTime = qTime;
    }

    public List<T> getResultList() {
        return resultList;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getTotallyPage() {
        return totallyPage;
    }

    public void setTotallyPage(long totallyPage) {
        this.totallyPage = totallyPage;
    }
}
