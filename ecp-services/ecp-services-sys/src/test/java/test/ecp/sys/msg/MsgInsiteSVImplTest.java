/** 
 * Project Name:ecp-services-sys 
 * File Name:MsgInsiteSVImplTest.java 
 * Package Name:test.ecp.sys.msg 
 * Date:2016年3月11日下午2:35:40 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package test.ecp.sys.msg;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.ecp.sys.dao.model.MsgInsite;
import com.zengshi.ecp.sys.service.busi.interfaces.IMsgInsiteSV;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2016年3月11日下午2:35:40  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public class MsgInsiteSVImplTest extends EcpServicesTest {
    
    @Resource(name="msgInsiteSV")
    private IMsgInsiteSV msgInsiteSV;
    
    private long staffId = 84;
    
    private long msgInfoId = 3;

    /**
     * Test method for {@link com.zengshi.ecp.sys.service.busi.impl.MsgInsiteSVImpl#insertMsgInsite(com.zengshi.ecp.sys.dao.model.MsgInsite)}.
     */
    @Test
    public void testInsertMsgInsite() {
        
        MsgInsite msg = new MsgInsite();
        msg.setStaffId(staffId);
        msg.setMsgInfoId(this.msgInfoId);
        msg.setMsgCode("A001");
        msg.setMsgContext("测试消息；"+MsgInsiteSVImplTest.class.getName());
        try{
            long cnt = msgInsiteSV.countMsgInsite(staffId);
            msgInsiteSV.insertMsgInsite(msg);
            long newcnt = msgInsiteSV.countMsgInsite(staffId);
            
            Assert.assertEquals(cnt+1, newcnt);
        } catch(Exception err){
            err.printStackTrace();
            Assert.assertTrue(false);
        }
        
    }

}

