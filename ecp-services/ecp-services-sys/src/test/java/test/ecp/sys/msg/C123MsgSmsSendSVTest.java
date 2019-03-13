/** 
 * Project Name:ecp-services-sys 
 * File Name:C123MsgSmsSendSVTest.java 
 * Package Name:test.ecp.sys.msg 
 * Date:2016年3月16日下午10:27:15 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package test.ecp.sys.msg;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.ecp.sys.sms.gateway.C123SmsGatewayBean;
import com.zengshi.ecp.sys.sms.gateway.SmsGatewayBean;
import com.zengshi.ecp.sys.sms.interfaces.IMsgSmsOperateSV;
import com.zengshi.ecp.sys.sms.operator.SmsOperatorSendBean;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2016年3月16日下午10:27:15  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public class C123MsgSmsSendSVTest extends EcpServicesTest {
    
    private String phoneNo="18605916162";
    private String context = "我的内容";
    
    @Resource(name="C123MsgSmsOperateSV")
    private IMsgSmsOperateSV<C123SmsGatewayBean> msgSmsOperateSV ;
    
    
    private static C123SmsGatewayBean c123Gateway = new C123SmsGatewayBean();
    // 以下资料不能正常发送短信，但可以用于验证接口使用；
    static {
        c123Gateway.setAccount("1001@500008880001");
        c123Gateway.setAuthKey("87B01469201D6557C2685137EB3B5DEC");
        c123Gateway.setCgid("80");
        c123Gateway.setCsid("101");
    }
    

    /**
     * Test method for {@link com.zengshi.ecp.sys.sms.impl.C123MsgSmsSendSVImpl#sendSms(com.zengshi.ecp.sys.sms.dto.SmsOperateSendDTO)}.
     */
    @Test
    public void testSendSms() {
        
        SmsOperatorSendBean<C123SmsGatewayBean> dto = new SmsOperatorSendBean<>(c123Gateway);
        dto.setPhoneNo(phoneNo);
        dto.setContext(context);
        dto.setSendTime(null);
        
       this.msgSmsOperateSV.sendSms(dto);
    }

}

