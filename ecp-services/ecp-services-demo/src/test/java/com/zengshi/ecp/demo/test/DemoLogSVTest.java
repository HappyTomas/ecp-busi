/** 
 * Project Name:ecp-services-demo 
 * File Name:DemoLogSVTest.java 
 * Package Name:com.zengshi.ecp.demo.test 
 * Date:2015年8月10日上午10:26:24 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
*/
package com.zengshi.ecp.demo.test;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import com.db.sequence.Sequence;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.zengshi.ecp.demo.dao.model.DemoLog;
import com.zengshi.ecp.demo.dubbo.dto.DemoLogDTO;
import com.zengshi.ecp.demo.service.busi.interfaces.IDemoLogSV;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.test.EcpServicesTest;


/** 
 * Title: ECP <br>
 * Project Name:ecp-services-demo <br>
 * Description: <br>
 * Date:2015年8月10日上午10:26:24  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author jiangysh
 * @since JDK 1.6 
 * @see       
 */
public class DemoLogSVTest extends EcpServicesTest{

    @Resource
    private IDemoLogSV demoLogSV;

    @Resource(name="seq_demo_info_id")
    private Sequence sequence;
    
    @Test
    @Ignore
    public void find(){
        
        DemoLog log=demoLogSV.find(4L);
        System.out.println("name:  "+log.getLogInfo());
    }
    
    @Test
    @Ignore
    public void findAll(){
       List<DemoLog> logs= demoLogSV.findAll();
       
       Assert.assertEquals(logs.size(), 1);
    }
    
    @Test
    @Ignore
    public void save(){
        
        for(int i=300;i<400;i++){
            DemoLog log=new DemoLog();
            log.setDbCode("0"+i);
            log.setLogId(99L+i);
            log.setLogInfo("测试内容"+i);
            demoLogSV.saveLog(log);
            
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    @Test
    public void findByPage(){
        BaseInfo baseInfo=new BaseInfo();
        baseInfo.setPageNo(3);
        baseInfo.setPageSize(11);
        PageResponseDTO<DemoLogDTO> prd=demoLogSV.findByPage(baseInfo);
        System.out.println("count:"+prd.getCount());
        for(DemoLogDTO log : prd.getResult()){
            System.out.println("id:"+log.getLogId()+"   info:"+log.getLogInfo());
        }
    }

    @Test
    public void testSeq() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                ExecutorService pools = Executors.newFixedThreadPool(10);
                for (int i = 0; i < 100; i++) {
                    final int k = ++i;
                    pools.execute(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println(k + ":  " + sequence.nextValue());
                        }
                    });
                }
            }
        });

        thread.start();
        try {
            Thread.sleep(10000L);
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

