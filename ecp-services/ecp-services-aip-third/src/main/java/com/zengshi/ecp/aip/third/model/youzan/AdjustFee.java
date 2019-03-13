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
public class AdjustFee {

    private String change;
    @JSONField(name="pay_change")
    private String payChange;
    @JSONField(name="post_change")
    private String postChange;
    public void setChange(String change) {
         this.change = change;
     }
     public String getChange() {
         return change;
     }

    public void setPayChange(String payChange) {
         this.payChange = payChange;
     }
     public String getPayChange() {
         return payChange;
     }

    public void setPostChange(String postChange) {
         this.postChange = postChange;
     }
     public String getPostChange() {
         return postChange;
     }

}
