package com.zengshi.ecp.prom.test.service.busi.service.group;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import org.junit.Test;

import com.zengshi.ecp.prom.dubbo.dto.PromChkDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromChkResponseDTO;
import com.zengshi.ecp.prom.service.busi.group.interfaces.IPromGroupChkSV;
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
public class PromGroupChkSVImplTest extends EcpServicesTest {

    @Resource
    private IPromGroupChkSV promGroupChkSV;

    @Test
    public void testQueryById() throws BusinessException {
        PromChkResponseDTO promChkResponseDTO = promGroupChkSV.queryPromGroupChkById(new Long(1));
        assertEquals(promChkResponseDTO.getId(), new Long(1));

    }
    @Test
    public void testqsave() throws BusinessException {
        PromChkDTO promChkDTO = new PromChkDTO();
        promChkDTO.setChkDesc("dese");
        promChkDTO.setCreateStaff(new Long(1));
        promChkDTO.setCreateTime(new Date());
        promChkDTO.setPromId(new Long(29));
        promChkDTO.setStatus("0");
        promGroupChkSV.savePromGroupChk(promChkDTO);
    }

}
