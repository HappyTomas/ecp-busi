/** 
 * Project Name:ecp-services-demo-server 
 * File Name:FileUtilTest.java 
 * Package Name:com.zengshi.ecp.demo.test 
 * Date:2015年11月8日上午10:49:27 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
*/
package com.zengshi.ecp.demo.test;

import org.junit.Test;

import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.paas.utils.FileUtil;

/** 
 * Title: ECP <br>
 * Project Name:ecp-services-demo-server <br>
 * Description: <br>
 * Date:2015年11月8日上午10:49:27  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author jiangysh
 * @since JDK 1.6 
 * @see       
 */
public class FileUtilTest extends EcpServicesTest{

    @Test
    public void upload(){
        String fileId=FileUtil.saveFile("E://reportDemo.jasper", "pdf");
        
        System.out.println("=========================== "+fileId);
    }
}

