/** 
 * Project Name:ecp-services-goods 
 * File Name:GdsTypeSVImplTest.java 
 * Package Name:com.zengshi.ecp.goods.service.common.impl 
 * Date:2015年8月11日下午5:05:07 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.test;

import static org.junit.Assert.fail;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.zengshi.ecp.goods.dao.model.GdsType;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsTypeReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsTypeRespDTO;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsTypeSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.paas.utils.CacheUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月11日下午5:05:07  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public class GdsTypeSVImplTest extends EcpServicesTest {
    
    @Resource
    private IGdsTypeSV gdsTypeSV;

    /**
     * Test method for {@link com.zengshi.ecp.goods.service.common.impl.GdsTypeSVImpl#saveGdsType(com.zengshi.ecp.goods.dao.model.GdsType)}.
     */
    @Test
    public void testSaveGdsType() {
        GdsType gt = new GdsType();
        Timestamp now = new Timestamp(Calendar.getInstance().getTimeInMillis());
        gt.setCreateTime(now);
        gt.setUpdateTime(now);
        gt.setStatus(GdsConstants.Commons.STATUS_VALID);
        gt.setTypeDesc("hello,商品分类");
        gt.setTypeName("电脑");
        gt.setSortNo(2);
        gdsTypeSV.saveGdsType(gt);
        Assert.assertNotNull(gt.getId());
        
    }

    /**
     * Test method for {@link com.zengshi.ecp.goods.service.common.impl.GdsTypeSVImpl#queryGdsTypeById(java.lang.Long)}.
     */
    @Test
    public void testQueryGdsTypeById() {
        Long id = 1004L;
        GdsType type =  gdsTypeSV.queryGdsTypeByPK(id);
        Assert.assertNotNull(type);
        id = -1L;
        type = gdsTypeSV.queryGdsTypeByPK(id);
        Assert.assertNull(type);

    }

    /**
     * Test method for {@link com.zengshi.ecp.goods.service.common.impl.GdsTypeSVImpl#queryGdsTypeList()}.
     */
   @Test
    public void testQueryGdsTypeList() {
        //CacheUtil.delItem(IGdsTypeSV.KEY_GDSTYPELIST);
        List<GdsTypeRespDTO> gts = gdsTypeSV.queryGdsTypeListFromCache();
        Assert.assertTrue(!gts.isEmpty());
        for(GdsTypeRespDTO type : gts){
            System.out.println(type);
        }
    }

    /**
     * Test method for {@link com.zengshi.ecp.goods.service.common.impl.GdsTypeSVImpl#queryGdsTypePage(com.zengshi.ecp.goods.dubbo.dto.GdsTypeReqDTO)}.
     */
    @Test
    public void testQueryGdsTypePage() {
        GdsTypeReqDTO dto = new GdsTypeReqDTO();
        dto.setPageNo(1);
        dto.setPageSize(10);
        dto.setStatus("1");
        PageResponseDTO<GdsTypeRespDTO> gts = gdsTypeSV.queryGdsTypeRespDTOPaging(dto);
        for(GdsTypeRespDTO gt : gts.getResult()){
            System.out.println(gt);
        }
    }

    /**
     * Test method for {@link com.zengshi.ecp.goods.service.common.impl.GdsTypeSVImpl#editGdsType(com.zengshi.ecp.goods.dao.model.GdsType)}.
     */
    @Test
    public void testEditGdsType() {
        fail("Not yet implemented"); // TODO
    }
    

}

