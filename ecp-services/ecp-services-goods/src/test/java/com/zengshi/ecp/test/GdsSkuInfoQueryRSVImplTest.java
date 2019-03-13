/** 
 * Project Name:ecp-services-goods-server 
 * File Name:GdsSkuInfoQueryRSVImplTest.java 
 * Package Name:com.zengshi.ecp.test 
 * Date:2015-10-23下午3:34:08 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */
package com.zengshi.ecp.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zengshi.ecp.general.order.dto.ROrdCartCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfoidx.GdsSku2PropPropIdxReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.price.CartItemPriceInfo;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.goods.service.busi.interfaces.price.IGdsPriceSV;
import com.zengshi.ecp.server.front.dto.BaseStaff;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.paas.utils.LogUtil;
import com.alibaba.fastjson.JSON;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: <br>
 * Date:2015-10-23下午3:34:08 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version
 * @since JDK 1.6
 */
public class GdsSkuInfoQueryRSVImplTest extends EcpServicesTest {

    @Resource
    private IGdsSkuInfoQueryRSV gdsSkuInfoQueryRSV;

    @Autowired(required = false)
    private IGdsPriceSV gdsPriceSV;

    /**
     * Test method for
     * {@link com.zengshi.ecp.goods.dubbo.impl.GdsSkuInfoQueryRSVImpl#queryGdsSkuInfoPaging(com.zengshi.ecp.goods.dubbo.dto.gdsinfoidx.GdsSku2PropPropIdxReqDTO)}
     * .
     */
    @Test
    public void testQueryGdsSkuInfoPaging() {
        GdsSku2PropPropIdxReqDTO reqDTO = new GdsSku2PropPropIdxReqDTO();
        reqDTO.setBeginTime(new Timestamp(System.currentTimeMillis()));
        reqDTO.setEndTime(new Timestamp(System.currentTimeMillis()));
        reqDTO.setPropId(1005L);
        reqDTO.setPageSize(5);
        PageResponseDTO<GdsSkuInfoRespDTO> page = gdsSkuInfoQueryRSV.queryGdsSkuInfoPaging(reqDTO);
        System.out.println(page.getResult());
    }

    @Test
    public void testPrice() {

        GdsSkuInfoReqDTO gdsSkuInfoReqDTO = new GdsSkuInfoReqDTO();
        gdsSkuInfoReqDTO.setId(Long.valueOf(4707L));
        SkuQueryOption[] skuQuery = { SkuQueryOption.BASIC, SkuQueryOption.CAlDISCOUNT };
        gdsSkuInfoReqDTO.setSkuQuery(skuQuery);

        GdsSkuInfoRespDTO gdsSkuInforespDTO = gdsSkuInfoQueryRSV.querySkuInfoByOptions(gdsSkuInfoReqDTO);

        ROrdCartsCommRequest reqDto = new ROrdCartsCommRequest();
        reqDto.setStaff(getBaseStaff());
        reqDto.setStaffId(getBaseStaff().getId());
        List<ROrdCartItemCommRequest> arr = new ArrayList<ROrdCartItemCommRequest>();
        for (long i = 0; i < 1; i++) {
            ROrdCartItemCommRequest rOrdCartItemChkRequest = new ROrdCartItemCommRequest();
            rOrdCartItemChkRequest.setId(i);
            rOrdCartItemChkRequest.setGdsId(4575L);
            rOrdCartItemChkRequest.setSkuId(4707L);
            rOrdCartItemChkRequest.setShopId(1101L);
            rOrdCartItemChkRequest.setOrderAmount(1l);
            rOrdCartItemChkRequest.setStaff(getBaseStaff());
            rOrdCartItemChkRequest.setStaffId(getBaseStaff().getId());
            arr.add(rOrdCartItemChkRequest);
        }

        List<ROrdCartCommRequest> ordCartsCommList = new ArrayList<ROrdCartCommRequest>();
        ROrdCartCommRequest request = new ROrdCartCommRequest();
        request.setOrdCartItemCommList(arr);
        request.setStaff(getBaseStaff());
        request.setStaffId(getBaseStaff().getId());
        ordCartsCommList.add(request);
        reqDto.setOrdCartsCommList(ordCartsCommList);

        Map<Long, CartItemPriceInfo> result = gdsPriceSV.caculatePrice(reqDto);
        LogUtil.info("", JSON.toJSONString(gdsSkuInforespDTO));
        LogUtil.info("", JSON.toJSONString(result));
        System.out.println();
    }

    private BaseStaff getBaseStaff() {
        BaseStaff staff = new BaseStaff();
        staff.setId(3032L);
        staff.setStaffCode("cp101");
        return staff;
    }

}
