/** 
 * Project Name:ecp-services-sys 
 * File Name:McSyncSendRSVImplTest.java 
 * Package Name:test.ecp.sys.msg 
 * Date:2016年3月15日下午3:12:29 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package test.ecp.sys.msg;

import java.util.HashMap;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zengshi.ecp.general.sys.mc.dto.McParamsDTO;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.ecp.sys.dubbo.dto.McParamsWithOtherTypesDTO;
import com.zengshi.ecp.sys.dubbo.interfaces.IMcSyncSendRSV;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2016年3月15日下午3:12:29  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public class McSyncSendRSVImplTest extends EcpServicesTest {
    
    @Autowired
    private IMcSyncSendRSV mcSyncSendRSV;
    
    private static final Long toUserId = 1036L;

    @Ignore
    public void test() {
        McParamsDTO dto = new McParamsDTO();
        dto.setFromUserId(100L);
        dto.setToUserId(toUserId);
        String msgCode= "";
        
        /*String msgCode="gds.eval.notice";
        dto.setMsgCode(msgCode);
        dto.setMsgParams(MapMsgParams.get(msgCode));
        mcSyncSendRSV.sendMsg(dto);*/
        
        
        msgCode="ord.send.notice";
        dto.setMsgCode(msgCode);
        dto.setMsgParams(MapMsgParams.get(msgCode));
        mcSyncSendRSV.sendMsg(dto);
        
        /* msgCode="ord.point.notice";
        dto.setMsgCode(msgCode);
        dto.setMsgParams(MapMsgParams.get(msgCode));
        mcSyncSendRSV.sendMsg(dto);
        
        
        msgCode="card.apply";
        dto.setMsgCode(msgCode);
        dto.setMsgParams(MapMsgParams.get(msgCode));
        mcSyncSendRSV.sendMsg(dto);*/
        
    }
    
    private static Map<String, Map<String,String>> MapMsgParams = new HashMap<>();
    ///发货订单
    static {
        Map<String ,String > map = new HashMap<>();
        map.put("orderId", "O2009093089497485898403");
        map.put("expressName", "中通物流公司");
        map.put("expressNo", "Addafoierjluojhdfrlkjk0980");
        
        MapMsgParams.put("ord.send.notice", map);
    }
    
    ///积分商城订单；
    static {
        Map<String ,String > map = new HashMap<>();
        map.put("orderId", "O2009093089497485898403");
        map.put("usedPoint", "880");
        map.put("remPoint", "983430489");
        
        MapMsgParams.put("ord.point.notice", map);
    }
    
    /**
     * 
     * testSendWithPhone: 测试发送手机号码 <br/> 
     * 
     * @author yugn  
     * @since JDK 1.6
     */
    @Test
    public void testSendWithPhone(){
        McParamsWithOtherTypesDTO dto = new McParamsWithOtherTypesDTO();
        dto.setFromUserId(100L);
        //dto.setToUserId(toUserId);
        dto.phoneNo("18605916162");
        String msgCode= "";
        
        /*String msgCode="gds.eval.notice";
        dto.setMsgCode(msgCode);
        dto.setMsgParams(MapMsgParams.get(msgCode));
        mcSyncSendRSV.sendMsg(dto);*/
        
        msgCode="ord.send.notice";
        dto.setMsgCode(msgCode);
        dto.setMsgParams(MapMsgParams.get(msgCode));
        mcSyncSendRSV.sendMsgBySpecial(dto);
    }
    
    /*@Test
    public void testUser(){
        
        mcSyncSendRSV.testFetchUserInfo(toUserId);
    }*/

}

