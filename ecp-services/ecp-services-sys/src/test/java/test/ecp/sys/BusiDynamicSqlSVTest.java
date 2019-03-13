/** 
 * Project Name:ecp-services-sys 
 * File Name:BusiDynamicSqlSVTest.java 
 * Package Name:test.ecp.sys 
 * Date:2015-8-27下午8:45:01 
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
import com.zengshi.ecp.sys.service.busi.interfaces.IBusiDynamicSqlSV;

import net.sf.json.JSONObject;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2015-8-27下午8:45:01  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public class BusiDynamicSqlSVTest extends EcpServicesTest {
    
    @Resource(name="busiDynamicSqlSV")
    private IBusiDynamicSqlSV busiDynamicSqlSV;

    @Test
    public void test() {
        String sql = "select demo_info code , value||'' value , rownum sort , 'zh' lang from t_demo_info";
        //String sql = "select demo_info, value, id from t_demo_info";
        List<BaseParamDTO> lst = this.busiDynamicSqlSV.fetchParamList(sql);
        
        if(lst == null || lst.size() == 0){
            fail("=============未获取到数据" );
        } else {
            for (BaseParamDTO result : lst){
                System.out.println("===="+JSONObject.fromObject(result).toString());
            }
        }
    }

}

