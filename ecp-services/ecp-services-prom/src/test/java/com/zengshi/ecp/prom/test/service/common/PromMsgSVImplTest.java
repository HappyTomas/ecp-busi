package com.zengshi.ecp.prom.test.service.common;

import static org.junit.Assert.*;

import java.util.List;
import javax.annotation.Resource;
import org.junit.Test;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeMsgDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeMsgResponseDTO;
import com.zengshi.ecp.prom.dubbo.util.PromCacheUtil;
import com.zengshi.ecp.prom.service.common.msg.interfaces.IPromMsgSV;
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
public class PromMsgSVImplTest extends EcpServicesTest {

    @Resource
    private IPromMsgSV PromMsgSV;

    @Test
    public void testQueryById() throws BusinessException {

        PromTypeMsgResponseDTO dto = PromMsgSV.queryPromMsgById(new Long(1));
        assertEquals(dto.getId(), new Long(1));

    }

    @Test
    public void testQueryByCode() throws BusinessException {
        PromTypeMsgResponseDTO dto = PromMsgSV.queryPromMsgByCode("1000000001", "1", "10");
        assertEquals(dto.getPromTypeCode(), "1000000001");
    }

    @Test
    public void testClear() throws BusinessException {
               PromCacheUtil.clearPromTypeCache();
               PromCacheUtil.clearPromTypeMsgCache();
    }
    @Test
    public void testQuery() throws BusinessException {

        PromTypeMsgDTO promTypeMsgDTO = new PromTypeMsgDTO();
        List<PromTypeMsgResponseDTO> dto = PromMsgSV.queryPromMsgList(promTypeMsgDTO);
        assertEquals(dto.size(), 1);
    }
}
