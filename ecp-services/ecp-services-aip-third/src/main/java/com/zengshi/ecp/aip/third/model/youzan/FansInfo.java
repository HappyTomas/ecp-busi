/**
  * Copyright 2017 aTool.org 
  */
package com.zengshi.ecp.aip.third.model.youzan;

import com.alibaba.fastjson.annotation.JSONField;
/**
 * Auto-generated: 2017-02-22 16:43:17
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class FansInfo {

    @JSONField(name="fans_nickname")
    private String fansNickname;
    @JSONField(name="fans_id")
    private String fansId;
    @JSONField(name="buyer_id")
    private String buyerId;
    @JSONField(name="fans_type")
    private String fansType;
    public void setFansNickname(String fansNickname) {
         this.fansNickname = fansNickname;
     }
     public String getFansNickname() {
         return fansNickname;
     }

    public void setFansId(String fansId) {
         this.fansId = fansId;
     }
     public String getFansId() {
         return fansId;
     }

    public void setBuyerId(String buyerId) {
         this.buyerId = buyerId;
     }
     public String getBuyerId() {
         return buyerId;
     }

    public void setFansType(String fansType) {
         this.fansType = fansType;
     }
     public String getFansType() {
         return fansType;
     }

}
