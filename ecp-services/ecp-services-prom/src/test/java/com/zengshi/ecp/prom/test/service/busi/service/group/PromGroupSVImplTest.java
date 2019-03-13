package com.zengshi.ecp.prom.test.service.busi.service.group;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.junit.Test;

import com.zengshi.ecp.prom.dubbo.dto.PromGroupDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromGroupResponseDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoResponseDTO;
import com.zengshi.ecp.prom.service.busi.group.interfaces.IPromGroupSV;
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
public class PromGroupSVImplTest extends EcpServicesTest {

    @Resource
    private IPromGroupSV PromGroupSV;

  /*  @Test
    public void testQueryById() throws BusinessException {

        PromGroupResponseDTO promGroupResponseDTO = PromGroupSV.queryPromGroupById(new Long(1));
        assertEquals(promGroupResponseDTO.getId(), new Long(1));

    }

    @Test
    public void testQueryByPromGroup() throws BusinessException {
        PromGroupDTO promGroupDTO = new PromGroupDTO();
        promGroupDTO.setId(new Long(1));
        promGroupDTO.setPromTheme("e");
        promGroupDTO.setStatus("1");
        promGroupDTO.setShowStartTime(new Date());
        promGroupDTO.setShowEndTime(new Date());
        PageResponseDTO<PromGroupResponseDTO> promGroupResponseDTOList = PromGroupSV
                .queryPromGroupForPage(promGroupDTO,"1");
        System.out.println(promGroupResponseDTOList.getCount());
        System.out.println(promGroupResponseDTOList.getResult().get(0));

    }*/

    @Test
    public void testPromGroup4Shop() throws BusinessException {
        PromInfoDTO promInfoDTO = new PromInfoDTO();
        // promInfoDTO.setId(new Long(1));
        // promInfoDTO.setPromTheme("e");
        // promInfoDTO.setStatus("1");

        PageResponseDTO<PromInfoResponseDTO> promInfoResponseDTOList = PromGroupSV.queryPromGroup4Shop(new Long(2),
                promInfoDTO);
        // System.out.println(promInfoResponseDTOList.size());
        System.out.println(promInfoResponseDTOList.getCount());

    }
    @Test
    public void testPromGroup4ShopNoResult() throws BusinessException {
        PromInfoDTO promInfoDTO = new PromInfoDTO();
        // promInfoDTO.setId(new Long(1));
        // promInfoDTO.setPromTheme("e");
        // promInfoDTO.setStatus("1");

        PageResponseDTO<PromInfoResponseDTO> promInfoResponseDTOList = PromGroupSV.queryPromGroup4Shop(new Long(-100),
                promInfoDTO);
        // System.out.println(promInfoResponseDTOList.size());
        System.out.println(promInfoResponseDTOList.getCount());

    }

/*    @Test
    public void testSavePromGroup() throws BusinessException {
        PromGroupDTO promGroupDTO = new PromGroupDTO();
        promGroupDTO.setPromTheme("e");
        promGroupDTO.setStatus("1");
        promGroupDTO.setShowStartTime(new Date());
        promGroupDTO.setShowEndTime(new Date());
        promGroupDTO.setCreateStaff(new Long(1));
        promGroupDTO.setCreateTime(new Date());
        promGroupDTO.setPromUrl("www.baidu.com");
        promGroupDTO.setPromContent("co teste");
        PromGroupSV.savePromGroup(promGroupDTO);

    }*/
}
