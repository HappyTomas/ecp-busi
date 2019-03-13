/** 
 * Project Name:ecp-services-sys 
 * File Name:BaseSysCfgRSVTest.java 
 * Package Name:test.ecp.sys 
 * Date:2015-8-18����7:11:39 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package test.ecp.sys;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.server.front.dto.BaseSysCfgReqDTO;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.param.IBaseSysCfgRSV;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.alibaba.fastjson.JSON;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2015-8-18����7:11:39  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public class BaseSysCfgRSVTest extends EcpServicesTest {
    
    @Resource
    private IBaseSysCfgRSV baseSysCfgRSV;

    @Test
    public void saveTest(){
        String paraCode = "SYS_TEST3";
        BaseSysCfgReqDTO dto = new BaseSysCfgReqDTO();
        dto.setParaCode(paraCode);
        dto.setParaValue("300");
        dto.setParaDesc("测试说明3");
        this.baseSysCfgRSV.saveCfg(dto);
        
        
        BaseSysCfgRespDTO resp  = SysCfgUtil.fetchSysCfg(paraCode);
        System.out.println(JSON.toJSONString(resp));
    }
    
    @Test
    public void fetchTest(){
        String paraCode = "SYS_TEST";
        BaseSysCfgRespDTO resp  = SysCfgUtil.fetchSysCfg(paraCode);
        System.out.println(JSON.toJSONString(resp));
    }
    

}

