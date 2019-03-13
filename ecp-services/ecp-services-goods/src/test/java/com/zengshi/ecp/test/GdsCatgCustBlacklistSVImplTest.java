package com.zengshi.ecp.test;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.zengshi.ecp.goods.dubbo.dto.GdsCatgCustBlacklistReqDTO;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsCatgCustBlacklistSV;
import com.zengshi.ecp.server.test.EcpServicesTest;

public class GdsCatgCustBlacklistSVImplTest extends EcpServicesTest {

    @Resource
    private IGdsCatgCustBlacklistSV blacklistSV;

//    @Test
    public void testAddGdsCatgCustBlacklist() {
    	GdsCatgCustBlacklistReqDTO reqDTO=new GdsCatgCustBlacklistReqDTO();
    	reqDTO.setGdsId(1121074L);
    	reqDTO.setShopId(100L);
    	this.blacklistSV.addGdsCatgCustBlacklist(reqDTO);
    	System.out.println(reqDTO.getId());
    	Assert.assertFalse(reqDTO.getId()==null);
    }
//    @Test
    public void testDeleteGdsCatgCustBlacklistByKey() {
    	GdsCatgCustBlacklistReqDTO reqDTO=new GdsCatgCustBlacklistReqDTO();
    	reqDTO.setId(4002L);
    	int c=this.blacklistSV.deleteGdsCatgCustBlacklistByPrimaryKey(reqDTO);
    	System.out.println("c:"+c);
    	Assert.assertTrue(c!=0);
    }
//    @Test
    public void testDeleteGdsCatgCustBlacklistByGdsId() {
    	GdsCatgCustBlacklistReqDTO reqDTO=new GdsCatgCustBlacklistReqDTO();
    	reqDTO.setGdsId(1121074L);
    	reqDTO.setShopId(100L);
    	int c=this.blacklistSV.deleteGdsCatgCustBlacklistByGdsId(reqDTO);
    	System.out.println("c:"+c);
    	Assert.assertTrue(c!=0);
    }
    @Test
    public void testDeleteBatchGdsCatgCustBlacklistByGdsId() {
    	GdsCatgCustBlacklistReqDTO reqDTO=new GdsCatgCustBlacklistReqDTO();
    	List<Long> idList=new ArrayList<>();
    	idList.add(1121074L);
    	reqDTO.setGdsIdList(idList);
    	reqDTO.setShopId(100L);
    	int c=this.blacklistSV.deleteBatchGdsCatgCustBlacklistByGdsId(reqDTO);
    	System.out.println("c:"+c);
    	Assert.assertTrue(c!=0);
    }
}
