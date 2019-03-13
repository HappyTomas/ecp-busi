/** 
 * Project Name:ecp-services-sys 
 * File Name:MsgInfoSVImplTest.java 
 * Package Name:test.ecp.sys.msg 
 * Date:2016年3月11日下午8:23:32 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */
package test.ecp.sys.msg;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.zengshi.ecp.general.sys.mc.dto.McParamsDTO;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.ecp.sys.dao.model.MsgInfo;
import com.zengshi.ecp.sys.dubbo.util.BaseMsgConstants;
import com.zengshi.ecp.sys.service.busi.interfaces.IMsgInfoSV;
import com.alibaba.fastjson.JSONObject;


/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2016年3月11日下午8:23:32 <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version
 * @since JDK 1.6
 */
public class MsgInfoSVImplTest extends EcpServicesTest {

    private String msgCode = "1000";

    private Map<String, String> params = new HashMap<String, String>();

    @Resource(name = "msgInfoSV")
    private IMsgInfoSV msgInfoSV;

    public MsgInfo testInitInsertMsgInfo() throws Exception {
        McParamsDTO dto = new McParamsDTO();
        dto.setMsgCode(this.msgCode);
        dto.setFromUserId(1L);
        dto.setToUserId(85L);
        dto.setMsgParams(params);

        return this.msgInfoSV.initInsertMsgInfo(dto);

    }
    
    @Test
    public void testDone() {
        try{
            MsgInfo info = this.testInitInsertMsgInfo();
            System.out.println("=========="+JSONObject.toJSONString(info));
            

            int done = this.msgInfoSV.updateMsgInfo2DoneById(info.getMsgInfoId());
            Assert.assertEquals(1, done);
            
            MsgInfo info2 = this.msgInfoSV.fetchMsgInfoById(info.getMsgInfoId());
            Assert.assertTrue("OK", BaseMsgConstants.SYS_MSG_SEND_INFO_STATUS_DONE.equalsIgnoreCase(info2.getMsgInfoStatus()));
            
        } catch(Exception err){
            err.printStackTrace();
        }
    }
    
    @Test
    public void testUndo() {
        try{
            MsgInfo info = this.testInitInsertMsgInfo();
            System.out.println("=========="+JSONObject.toJSONString(info));
            
            int undo = this.msgInfoSV.updateMsgInfo2UnDoById(info.getMsgInfoId());
            Assert.assertEquals(1, undo);
           
            MsgInfo info2 = this.msgInfoSV.fetchMsgInfoById(info.getMsgInfoId());
            Assert.assertTrue("OK", BaseMsgConstants.SYS_MSG_SEND_INFO_STATUS_UNDO.equalsIgnoreCase(info2.getMsgInfoStatus()));
        } catch(Exception err){
            err.printStackTrace();
        }
    }

}
