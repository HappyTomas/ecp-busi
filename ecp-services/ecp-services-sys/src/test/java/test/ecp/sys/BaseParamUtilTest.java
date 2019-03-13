/** 
 * Project Name:ecp-services-sys 
 * File Name:BaseParamUtilTest.java 
 * Package Name:test.ecp.sys 
 * Date:2015-8-22下午5:33:45 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package test.ecp.sys;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import com.zengshi.ecp.server.front.dto.BaseParamDTO;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.paas.utils.StringUtil;

import net.sf.json.JSONObject;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2015-8-22下午5:33:45  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public class BaseParamUtilTest  extends EcpServicesTest{
    
    private static final String paramKey = "PROM_TYPE_MSG_CODE";
    
    private static final String spLang = "zh";

    /**
     * Test method for {@link com.zengshi.ecp.sys.dubbo.util.BaseParamUtil#fetchParamList(java.lang.String)}.
     */
    @Test
    public void testFetchParamListString() {
        List<BaseParamDTO> dtos = BaseParamUtil.fetchParamList(paramKey);
        if(dtos == null || dtos.size() ==0){
            fail("查询的结果集为空");
        } else {
            for(BaseParamDTO dto :dtos){
                System.out.println("==="+JSONObject.fromObject(dto).toString());
            }
        }
        
    }

    /**
     * Test method for {@link com.zengshi.ecp.sys.dubbo.util.BaseParamUtil#fetchParamValue(java.lang.String, java.lang.String, java.lang.String)}.
     */
    @Test
    public void testFetchParamValue() {
        String value = BaseParamUtil.fetchParamValue("PUBLIC_PARAM_STATUS", "1");
        if(StringUtil.isEmpty(value)){
            fail("结果集为空");
        } else {
            System.out.println("===paramKey:"+paramKey+";code:1000; value:"+value);
        }
    }

    /**
     * Test method for {@link com.zengshi.ecp.sys.dubbo.util.BaseParamUtil#fetchParamDTO(java.lang.String, java.lang.String, java.lang.String)}.
     */
    @Test
    public void testFetchParamDTO() {
        BaseParamDTO dto = BaseParamUtil.fetchParamDTO("PUBLIC_PARAM_STATUS", "0");
        if(dto == null ){
            fail("结果集为空");
        } else {
            System.out.println("==="+JSONObject.fromObject(dto).toString());
        }
    }
    
    @Test
    public void testFetchParamListFromDynamic(){
        List<BaseParamDTO> dtos = BaseParamUtil.fetchParamList("TEST_DYNAMIC");
        if(dtos == null || dtos.size() ==0){
            fail("查询的结果集为空");
        } else {
            for(BaseParamDTO dto :dtos){
                System.out.println("==="+JSONObject.fromObject(dto).toString());
            }
        }
    }
    
    @Test
    public void testClearCache(){
        long size =BaseParamUtil.clearCache();
        System.out.println("清理缓存数量："+size);
    }

}

