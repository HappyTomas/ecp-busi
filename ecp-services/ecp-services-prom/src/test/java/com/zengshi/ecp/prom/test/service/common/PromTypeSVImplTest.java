package com.zengshi.ecp.prom.test.service.common;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeResponseDTO;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.common.interfaces.IPromTypeSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.test.EcpServicesTest;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromTypeSVImplTest extends EcpServicesTest {

    @Resource
    private IPromTypeSV promTypeSV;

    @Test
    public void testgetPromTypeTrue() throws Exception {
        PromTypeResponseDTO d = promTypeSV.queryPromTypeByCode("code");
        assertEquals("code", d.getPromTypeCode());
    }

    @Test
    public void testgetPromTypeNULL() throws Exception {
        PromTypeResponseDTO d = promTypeSV.queryPromTypeByCode("code111");
        assertEquals("code111", d.getPromTypeCode());
    }

    @Test
    public void testgetPromTypeList() throws Exception {
        PromTypeDTO promTypeDTO = new PromTypeDTO();
        promTypeDTO.setIfShow(PromConstants.PromType.IF_SHOW_1);
        promTypeDTO.setJsonBeanId("");
        promTypeDTO.setNameShort("sho");
        promTypeDTO.setPromClass(PromConstants.PromType.PROM_ClASS_10);
        promTypeDTO.setPromTypeCode("code");
        promTypeDTO.setPromTypeName("name");
        promTypeDTO.setStatus(PromConstants.PromType.STATUS_1);
        List<PromTypeResponseDTO> l = promTypeSV.queryPromTypeList(promTypeDTO);
        assertEquals(1, l.size());
    }

}
