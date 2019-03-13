/** 
 * Project Name:ecp-services-sys 
 * File Name:BaseParamRSVTest.java 
 * Package Name:test.ecp.sys 
 * Date:2015-8-22下午5:10:35 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package test.ecp.sys;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.zengshi.ecp.server.front.dto.BaseParamCfgReqDTO;
import com.zengshi.ecp.server.front.dto.BaseParamDTO;
import com.zengshi.ecp.server.front.param.IBaseParamCfgRSV;
import com.zengshi.ecp.server.test.EcpServicesTest;

import net.sf.json.JSONObject;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2015-8-22下午5:10:35  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public class BaseParamRSVTest extends EcpServicesTest{
    
    @Resource
    private IBaseParamCfgRSV baseParamCfgRSV;

    /**
     * Test method for {@link com.zengshi.ecp.sys.dubbo.impl.BaseParamCfgRSVImpl#fetchParamsByKey(com.zengshi.ecp.sys.dubbo.dto.BaseParamCfgReqDTO)}.
     */
    @Test
    public void testFetchParamsByKey() {
        
        BaseParamCfgReqDTO reqDTO = new BaseParamCfgReqDTO();
        reqDTO.setParamKey("SYS_STAFF_CLASS");
        reqDTO.setSpLang("zh");
        List<BaseParamDTO> dtos = baseParamCfgRSV.fetchParamsByKey(reqDTO);
        if(dtos == null || dtos.size() == 0){
            System.out.println("没有获取到参数信息列表");
            Assert.fail();
        } else {
            for(BaseParamDTO dto : dtos){
                System.out.println("=="+JSONObject.fromObject(dto).toString());
            }
        }
    }

    /**
     * Test method for {@link com.zengshi.ecp.sys.dubbo.impl.BaseParamCfgRSVImpl#fetchParamByKeyAndCode(com.zengshi.ecp.sys.dubbo.dto.BaseParamCfgReqDTO)}.
     */
    @Test
    public void testFetchParamByKeyAndCode() {
        BaseParamCfgReqDTO reqDTO = new BaseParamCfgReqDTO();
        reqDTO.setParamKey("SYS_STAFF_CLASS");
        reqDTO.setSpLang("zh");
        reqDTO.setSpCode("10");
        BaseParamDTO dto = baseParamCfgRSV.fetchParamByKeyAndCode(reqDTO);
        if(dto == null ){
            System.out.println("没有获取到参数信息");
            Assert.fail();
        } else {
                System.out.println("=="+JSONObject.fromObject(dto).toString());
        }
    }

}

