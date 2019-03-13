/**
  * Copyright 2017 aTool.org 
  */
package com.zengshi.ecp.aip.third.model.youzan;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;
/**
 * Auto-generated: 2017-02-22 16:43:17
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class Response {

    @JSONField(name="total_results")
    private String totalResults;
    private List<Trades> trades;
    public void setTotalResults(String totalResults) {
         this.totalResults = totalResults;
     }
     public String getTotalResults() {
         return totalResults;
     }

    public void setTrades(List<Trades> trades) {
         this.trades = trades;
     }
     public List<Trades> getTrades() {
         return trades;
     }

}
