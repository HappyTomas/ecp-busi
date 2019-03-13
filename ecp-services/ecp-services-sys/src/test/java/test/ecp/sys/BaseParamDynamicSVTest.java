/** 
 * Project Name:ecp-services-sys 
 * File Name:CommDynamicSqlSVTest.java 
 * Package Name:test.ecp.sys 
 * Date:2015-8-28上午10:07:32 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package test.ecp.sys;

import static org.junit.Assert.fail;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.server.front.dto.BaseParamDTO;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.ecp.sys.service.common.interfaces.IBaseParamDynamicSV;

import net.sf.json.JSONObject;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2015-8-28上午10:07:32  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public class BaseParamDynamicSVTest extends EcpServicesTest {
    
    @Resource(name="baseParamDynamicSV")
    private IBaseParamDynamicSV baseParamDynamicSV; 

    @Test
    public void test() {
        String paramKey = "PROM_TYPE_MSG_CODE";
        List<BaseParamDTO> lst = baseParamDynamicSV.fetchDynamicParamResultList(paramKey,"");
        
        if(lst == null || lst.size() == 0){
            fail("=============未获取到数据" );
        } else {
            for (BaseParamDTO result : lst){
                System.out.println("===="+JSONObject.fromObject(result).toString());
            }
        }
    }

}

