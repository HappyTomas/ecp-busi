/** 
 * Project Name:ecp-services-demo 
 * File Name:LobSVTest.java 
 * Package Name:com.zengshi.ecp.demo.test.Lob 
 * Date:2015年8月7日上午11:53:04 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
*/
package com.zengshi.ecp.demo.test.Lob;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.demo.dao.model.LobWithBLOBs;
import com.zengshi.ecp.demo.service.common.interfaces.ILobSV;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.alibaba.dubbo.common.io.Bytes;

/** 
 * Title: ECP <br>
 * Project Name:ecp-services-demo <br>
 * Description: <br>
 * Date:2015年8月7日上午11:53:04  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author jiangysh
 * @since JDK 1.6 
 * @see       
 */
public class LobSVTest extends EcpServicesTest{

    @Resource
    private ILobSV lobSV;
    
//    @Test
    public void insert(){
        LobWithBLOBs lob=new LobWithBLOBs();
        
        lob.setId(BigDecimal.valueOf(BigDecimal.valueOf((Math.random()*100)).intValue()%100));
        lob.setName("test");
        byte[] bytes="只增测试".getBytes();
        Byte[] bts=new Byte[bytes.length];
        int i=0;
        for(byte bt : bytes){
            bts[i++]=Byte.valueOf(bt);
        }
        lob.setbLob(bts);
        lob.setcLob("adfaff测试测试2222226666");
        lobSV.insert(lob);
    }
    
    @Test
    public void query(){
        LobWithBLOBs lob=lobSV.findById(49L);
        System.out.println("clob: "+lob.getcLob());
        Byte[] bts=lob.getbLob();
        byte[] bytes=new byte[bts.length];
        int i=0;
        for(Byte bt : bts){
            bytes[i++]=bt.byteValue(); 
        }
        System.out.println("blob: "+new String(bytes));
    }
}

