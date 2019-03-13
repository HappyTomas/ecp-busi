package com.zengshi.ecp.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.goods.dubbo.dto.GdsCollectReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCollectRespDTO;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsCollectSV;
import com.zengshi.ecp.server.front.dto.BaseStaff;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.alibaba.fastjson.JSON;

public class GdsCollectSVImplTest   extends EcpServicesTest{
	
	@Resource
	private IGdsCollectSV  gdsCollectSV;
	
	@Test
	public void init(){
	    
	    long shopId[]={1L,2L,3L};
	    
	    long staffId[]={1L,2L,3L};
	    
	    for(long i=1;i<=30;i++){
	        GdsCollectReqDTO  reqDTO=new GdsCollectReqDTO();
	        reqDTO.setGdsId(i);
	        reqDTO.setSkuId(i);
	        
	        if(i>20){
	            reqDTO.setShopId(shopId[0]);
	        }else if(i>10){
	            reqDTO.setShopId(shopId[1]);
            }else{
                reqDTO.setShopId(shopId[2]);
            }
	        
	        reqDTO.setStaffId(staffId[2]);
	        reqDTO.setGdsName("测试收藏的商品");
	        gdsCollectSV.addGdsCollect(reqDTO);
	    }
	    
	}
	
	@Test
	public void  testQueryGdsCollect(){
	    
	    //店铺维度，商品收藏列表数据group查询测试
		GdsCollectReqDTO  reqDTO=new GdsCollectReqDTO();
		reqDTO.setPageNo(1);
		reqDTO.setPageSize(100);
		
		reqDTO.setShopId(1l);
		reqDTO.setIncludeStaffCount(true);
		
		PageResponseDTO<GdsCollectRespDTO> pages=gdsCollectSV.queryGdsCollectRespDTOPagingByShop(reqDTO);
		System.out.println("------------------------------"+pages.getResult().size());
		System.out.println("------------------------------"+JSON.toJSON(pages));

	}
	
	
	private BaseStaff getBaseStaff() {
		BaseStaff staff = new BaseStaff();
		staff.setId(123456789L);
		staff.setStaffCode("admin");
		return staff;
	}

}

