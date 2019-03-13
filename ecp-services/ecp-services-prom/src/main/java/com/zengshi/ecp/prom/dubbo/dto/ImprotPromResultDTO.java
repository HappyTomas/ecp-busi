package com.zengshi.ecp.prom.dubbo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-prom-server <br>
 * Description: <br>
 * Date:2015-12-31下午4:11:19  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author PJieWin
 * @version  
 * @since JDK 1.6
 * 
 * 促销单品批量导入结果类
 */
public class ImprotPromResultDTO implements Serializable{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -8161438658452261137L;
    private long totalCount = 0;
    private long successCount = 0;
    private long errorCount = 0;
    private long repeatCount = 0;
    
    private List<ImportPromErrorInfoDTO> errorList = null;
    
    public ImprotPromResultDTO()
    {
        errorList = new ArrayList<ImportPromErrorInfoDTO>();
    }
    
    public long getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
    public long getSuccessCount() {
        return successCount;
    }
    public void setSuccessCount(long successCount) {
        this.successCount = successCount;
    }
    public long getErrorCount() {
        return errorCount;
    }
    public void setErrorCount(long errorCount) {
        this.errorCount = errorCount;
    }
    public List<ImportPromErrorInfoDTO> getErrorList() {
        return errorList;
    }
    public void setErrorList(List<ImportPromErrorInfoDTO> errorList) {
        this.errorList = errorList;
    }
    
    public long getRepeatCount() {
        return repeatCount;
    }

    public void setRepeatCount(long repeatCount) {
        this.repeatCount = repeatCount;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ImprotPromResultDTO [totalCount=");
        builder.append(totalCount);
        builder.append(", successCount=");
        builder.append(successCount);
        builder.append(", errorCount=");
        builder.append(errorCount);
        builder.append(", repeatCount=");
        builder.append(repeatCount);
        builder.append(", errorList=");
        builder.append(errorList);
        builder.append("]");
        return builder.toString();
    }

}

