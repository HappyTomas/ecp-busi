package com.zengshi.ecp.search.test.score;

import com.zengshi.ecp.search.dubbo.search.score.valuesource.DateValueSourceParserParam;
import com.zengshi.ecp.search.dubbo.search.score.valuesource.ScoreUtils;

import java.util.Date;

/**
 * Created by HDF on 2016/12/15.
 */
public class ScoreTest {

    public static void main(String args[]){
        //System.out.println(3 / 7 * 7 - 1);
        //System.out.println(15 / 7 * 7 - 1);

        DateValueSourceParserParam dateValueSourceParserParam=new DateValueSourceParserParam();
        String s=com.alibaba.fastjson.JSON.toJSONString(dateValueSourceParserParam);
        System.out.println(s);

        long timeMills=System.currentTimeMillis();

        //N天前测试
        for(int i=0;i<=90;i++){
            Date date=new Date(timeMills);
            date.setDate(date.getDate()-i);
            //System.out.println(date);
            System.out.println(i+"天前："+ScoreUtils.getNewsScoreFactor(timeMills,date.getTime(),null));
        }

        System.out.println("===========================================================================================");

        //N小时前测试
        for(int i=0;i<=28;i++){
            Date date=new Date(timeMills);
            date.setHours(date.getHours()-i);
            //System.out.println(date);
            System.out.println(i+"小时前："+ScoreUtils.getNewsScoreFactor(timeMills,date.getTime(),null));
        }
    }

}
