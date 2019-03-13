package com.zengshi.ecp.prom.test.service.busi.service.auth;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.junit.Test;

import com.zengshi.ecp.prom.dubbo.dto.PromType4ShopDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromType4ShopResponseDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeMsgDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeMsgResponseDTO;
import com.zengshi.ecp.prom.dubbo.dto.QueryPromType4ShopDTO;
import com.zengshi.ecp.prom.service.busi.auth.interfaces.IPromType4ShopSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.test.EcpServicesTest;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-6 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromType4ShopSVImplTest extends EcpServicesTest {

    @Resource
    private IPromType4ShopSV promType4ShopSV;

   /* @Test
    public void testQueryById() throws BusinessException {
        PromType4ShopResponseDTO dto = promType4ShopSV.queryPromType4ShopById(new Long(1));
        assertEquals(new Long(1), dto.getId());

    }

    @Test
    public void testQueryList() throws BusinessException {
        QueryPromType4ShopDTO queryPromType4ShopDTO = new QueryPromType4ShopDTO();
        List<PromType4ShopResponseDTO> list = promType4ShopSV
                .queryPromType4ShopList(queryPromType4ShopDTO);
    }

    @Test
    public void testInsert() throws BusinessException {

        List<PromType4ShopDTO> promType4ShopDTOList = new ArrayList<PromType4ShopDTO>();
        PromType4ShopDTO promType4ShopDTO = new PromType4ShopDTO();
        promType4ShopDTO.setCreateStaff(new Long(1));
        promType4ShopDTO.setCreateTime(new Date());
        promType4ShopDTO.setEndTime(new Date());
        promType4ShopDTO.setPromTypeCode("code");
        promType4ShopDTO.setShopId(new Long(1));
        promType4ShopDTO.setStartTime(new Date());
        promType4ShopDTO.setStatus("1");
        promType4ShopDTOList.add(promType4ShopDTO);

        PromType4ShopDTO promType4ShopDTO1 = new PromType4ShopDTO();
        promType4ShopDTO1.setCreateStaff(new Long(1));
        promType4ShopDTO1.setCreateTime(new Date());
        promType4ShopDTO1.setEndTime(new Date());
        promType4ShopDTO1.setPromTypeCode("code");
        promType4ShopDTO1.setShopId(new Long(2));
        promType4ShopDTO1.setStartTime(new Date());
        promType4ShopDTO1.setStatus("1");
        promType4ShopDTOList.add(promType4ShopDTO1);
        promType4ShopSV.savePromType4Shop(promType4ShopDTOList);
    }*/
    
    
 /*   @Test
    public void testQueryPage11() throws BusinessException {
        QueryPromType4ShopDTO queryPromType4ShopDTO=new QueryPromType4ShopDTO();
        PageResponseDTO<PromType4ShopResponseDTO>  p=promType4ShopSV.queryPromType4ShopForPage(queryPromType4ShopDTO);
        System.out.println(11);

    }*/
    
    @Test
    public void testQueryPage1133() throws BusinessException {
        QueryPromType4ShopDTO queryPromType4ShopDTO=new QueryPromType4ShopDTO();
        PageResponseDTO<PromType4ShopResponseDTO>  p=promType4ShopSV.queryPromType4ShopForPage(queryPromType4ShopDTO);
        System.out.println(11);

    }
}
