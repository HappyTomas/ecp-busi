/** 
 * Project Name:ecp-services-demo 
 * File Name:DemoLogDTO.java 
 * Package Name:com.zengshi.ecp.demo.dubbo.dto 
 * Date:2015年8月14日上午10:28:08 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
*/
package com.zengshi.ecp.demo.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

/** 
 * Title: ECP <br>
 * Project Name:ecp-services-demo <br>
 * Description: <br>
 * Date:2015年8月14日上午10:28:08  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author jiangysh
 * @since JDK 1.6 
 * @see       
 */
public class DemoLogDTO extends BaseResponseDTO {
    
    private Long logId;

    private String logInfo;

    private String dbCode;

    private static final long serialVersionUID = 1L;

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getLogInfo() {
        return logInfo;
    }

    public void setLogInfo(String logInfo) {
        this.logInfo = logInfo == null ? null : logInfo.trim();
    }

    public String getDbCode() {
        return dbCode;
    }

    public void setDbCode(String dbCode) {
        this.dbCode = dbCode == null ? null : dbCode.trim();
    }

}

