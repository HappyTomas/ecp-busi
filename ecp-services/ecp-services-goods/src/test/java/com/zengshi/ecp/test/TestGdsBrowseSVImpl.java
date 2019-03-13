package com.zengshi.ecp.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zengshi.ecp.goods.dubbo.dto.GdsBrowseHisReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsBrowseHisRespDTO;
import com.zengshi.ecp.goods.dubbo.util.GdsMessageUtil;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsBrowseHisSV;
import com.zengshi.ecp.server.front.dto.BaseStaff;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.test.EcpServicesTest;

@RunWith(SpringJUnit4ClassRunner.class)
public class TestGdsBrowseSVImpl extends EcpServicesTest {
    @Resource
    IGdsBrowseHisSV browseHisSV;

    @Test
    public void testAddGdsBrowseMessage() throws Exception {

        GdsBrowseHisReqDTO gdsBrowseHisReqDTO = new GdsBrowseHisReqDTO();

        gdsBrowseHisReqDTO.setBrowseCount(11l);
        gdsBrowseHisReqDTO.setBrowsePrice(11l);
        gdsBrowseHisReqDTO.setGdsId(12l);
        gdsBrowseHisReqDTO.setShopId(11l);
        gdsBrowseHisReqDTO.setSkuId(232L);
        BaseStaff staff = new BaseStaff();
        staff.setId(112L);
        gdsBrowseHisReqDTO.setStaff(staff);

        GdsMessageUtil.sendGdsBrowseMessage(gdsBrowseHisReqDTO);
    }

    @Test
    public void testQueryGdsBrowseHisMessage() throws Exception {
        GdsBrowseHisReqDTO gdsBrowseHisReqDTO = new GdsBrowseHisReqDTO();
        gdsBrowseHisReqDTO.setStaffId(104l);
        PageResponseDTO<GdsBrowseHisRespDTO>  browseHis =   browseHisSV.queryGdsBrowseHisByPage(gdsBrowseHisReqDTO);
        System.out.println("====================" + browseHis.getCount());
    }
}
