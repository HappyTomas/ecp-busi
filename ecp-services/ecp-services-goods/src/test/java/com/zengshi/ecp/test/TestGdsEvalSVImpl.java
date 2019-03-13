/** 
 * Project Name:ecp-services-goods 
 * File Name:TestGdsEvalSVImpl.java 
 * Package Name:com.zengshi.ecp.test 
 * Date:2015年8月25日下午4:49:50 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.test;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalRespDTO;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsEvalSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.paas.utils.DateUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月25日下午4:49:50  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public class TestGdsEvalSVImpl extends EcpServicesTest{
    @Resource
    private IGdsEvalSV gdsEvalSV;

    /**
     * Test method for {@link com.zengshi.ecp.goods.service.busi.impl.GdsEvalSVImpl#addGdsEval(com.zengshi.ecp.goods.dubbo.dto.GdsEvalReqDTO)}.
     */
    @Test
    public final void testAddGdsEval() {
        GdsEvalReqDTO dto = new GdsEvalReqDTO();
        dto.setDeliveryspeedScore((short)1);
        dto.setEvaluationTime(DateUtil.getSysDate());
        dto.setCorrespondencyScore((short)1);
        dto.setGdsId(1000L);
        dto.setSkuId(10002L);
        dto.setIsAnonymous(GdsConstants.GdsEval.IS_ANONYMOUS_0);
        dto.setIsReply(GdsConstants.GdsEvalReply.IS_REPLY_0);
        dto.setShopId(9999L);
        dto.setStaffId(4L);
        dto.setOrderId("TEST");
        dto.setContent("测试评价会被保存到mongodb");
        gdsEvalSV.addGdsEval(dto);
    }

    /**
     * Test method for {@link com.zengshi.ecp.goods.service.busi.impl.GdsEvalSVImpl#queryGdsEvalRespDTOPaging(com.zengshi.ecp.goods.dubbo.dto.GdsEvalReqDTO)}.
     */
    @Test
    public final void testQueryGdsEvalRespDTOPaging() {
        GdsEvalReqDTO dto = new GdsEvalReqDTO();
        dto.setPageSize(9);
        dto.setStatus(GdsConstants.Commons.STATUS_VALID);
        PageResponseDTO<GdsEvalRespDTO> page = gdsEvalSV.queryGdsEvalRespDTOPaging(dto);
        Assert.assertTrue(page.getCount() > 0);
        System.out.println(page.getCount());
        if(null != page.getResult()){
            for(GdsEvalRespDTO eval : page.getResult()){
                System.out.println(eval.getContent());
            }
        }
    }

    /**
     * Test method for {@link com.zengshi.ecp.goods.service.busi.impl.GdsEvalSVImpl#deleteGdsEvalByPK(java.lang.Long, boolean, java.lang.Long)}.
     */
   /* //@Test
    public final void testDeleteGdsEvalByPK() {
       // int i = gdsEvalSV.deleteGdsEvalByPK(19L, true, null);
      //  Assert.assertTrue(i == 1);
    }*/
    
    @Test
    public void testCount(){
        GdsEvalReqDTO dto=new GdsEvalReqDTO();
        dto.setShopId(100l);
        dto.setStatus(GdsConstants.Commons.STATUS_VALID);
        System.out.println(this.gdsEvalSV.countGoodEval(dto));
        System.out.println(this.gdsEvalSV.countMiddleEval(dto));
        System.out.println(this.gdsEvalSV.countBadEval(dto));
    }


}

