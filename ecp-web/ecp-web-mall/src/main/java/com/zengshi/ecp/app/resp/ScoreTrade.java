/** 
 * Project Name:ecp-web-mall 
 * File Name:DemoResp.java 
 * Package Name:com.zengshi.ecp.app.resp 
 * Date:2016-2-22下午6:53:17 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.app.resp;

import java.sql.Timestamp;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall Maven Webapp <br>
 * Description: 积分收支明细javabean，用于Staff102Resp的返回对象List的元素<br>
 * Date:2016-3-4下午4:49:08  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */
public class ScoreTrade {
    
    private String scoreType;//积分类型
    
    private Timestamp createTime;//创建时间
    
    private String orderId;//订单id
    
    private Long score;//积分

    public String getScoreType() {
        return scoreType;
    }

    public void setScoreType(String scoreType) {
        this.scoreType = scoreType;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

   
}

