package com.zengshi.ecp.staff.test;


import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.ecp.staff.dubbo.dto.CustWechatRelReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustWechatRelRespDTO;
import com.zengshi.ecp.staff.service.busi.wechat.interfaces.ICustWechatRelSV;

public class WechatRelSVImplTest extends EcpServicesTest {

    @Resource
    private ICustWechatRelSV relSV;
    
    
    @Test
    public void testSave() {
      
    	CustWechatRelReqDTO req = new CustWechatRelReqDTO();
    	req.setWechatId("dadf4sdf123214");
    	req.setStaffId(569L);
    	req.setStaffCode("hxl_score");
    	relSV.saveCustWechatRel(req);
    }
    
    @Test
    public void findById(){
    	CustWechatRelRespDTO res = new CustWechatRelRespDTO();
    	res = relSV.findCustWechatRelByWechatId("dadf4sdf123214");
    	System.out.println(res);
    }
    
    @Test
    public void upate(){
    	CustWechatRelReqDTO req = new CustWechatRelReqDTO();
    	req.setWechatId("dadf4sdf123214");
        relSV.updateCustWechatRel(req);
        
    }
    
    @Test
    public void list(){
       CustWechatRelReqDTO req = new CustWechatRelReqDTO();
       req.setStaffCode("h");
       req.setWechatId("dadf4sdf123214");
       req.setStaffId(569L);
       PageResponseDTO<CustWechatRelRespDTO> page = relSV.findCustWechatRel(req);
       System.out.println(page.getPageCount());
    }
    

}

