package test.ecp.sys.msg;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.ecp.sys.dubbo.dto.BaseSmsCfgReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.SmsActiveDTO;
import com.zengshi.ecp.sys.dubbo.interfaces.ISmsActiveRSV;

public class SmsActiveRSVTest extends EcpServicesTest {
    
    private String url = "http://smsapi.c123.cn/OpenPlatform/OpenApi";
    private String gateway = "c123";
    
    @Autowired
    private ISmsActiveRSV smsActiveRSV;

    @Test
    public void testSendSms() {
        
        SmsActiveDTO activeDto = this.buildTestDto();
        smsActiveRSV.sendSmsVerifyMsg(activeDto);
        activeDto.setSendMsg("要做一次接口测试，谢谢！");
        smsActiveRSV.sendSmsVerifyMsg(activeDto);
    }
    
    private SmsActiveDTO buildDto(){
        SmsActiveDTO activeDto = new SmsActiveDTO();
        activeDto.setAccount("1001@501223300001");
        activeDto.setAuthKey("86A881E5C3CD7815DCBD6AA4974E9B2C");
        activeDto.setGateway(gateway);
        activeDto.setUrl(url);
        Map<String, String> map = new HashMap<>();
        map.put("cgid", "5580");
        map.put("csid", "0");
        activeDto.setOthParams(map);
        activeDto.setRecPhoneno("18605916162");
        return activeDto;
    }
    
    //1  c123  http://smsapi.c123.cn/OpenPlatform/OpenApi  1001@500008880001  87B01469201D6557C2685137EB3B5DEC  {"cgid":"80","csid":"101"}
    private SmsActiveDTO buildTestDto(){
        SmsActiveDTO activeDto = new SmsActiveDTO();
        activeDto.setAccount("1001@501280150001");
        activeDto.setAuthKey("63F3111951DD866B6D4B641A84564923");
        activeDto.setGateway(gateway);
        activeDto.setUrl(url);
        Map<String, String> map = new HashMap<>();
        map.put("cgid", "52");
        map.put("csid", "101");
        activeDto.setOthParams(map);
        activeDto.setRecPhoneno("13805064605");
        return activeDto;
    }
    
    @Test
    public void testFetchSmsCfg(){
        BaseInfo info = new BaseInfo();
        BaseSmsCfgReqDTO dto = this.smsActiveRSV.fetchSmsCfg(info);
    }
    
    @Test
    public void testSaveSmsCfg(){
        SmsActiveDTO activeDto = this.buildTestDto();
        //activeDto.setGateway("ddd");
        smsActiveRSV.saveSmsCfg(activeDto);
        
    }

}

