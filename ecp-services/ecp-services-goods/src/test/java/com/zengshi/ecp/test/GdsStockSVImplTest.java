package com.zengshi.ecp.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsStockSV;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.paas.utils.DateUtil;

public class GdsStockSVImplTest  extends EcpServicesTest{

	
    @Resource
    private IGdsStockSV gdsStockSV;
    
    //id, rep_code,stock_id, order_id,sub_order,sku_id,count, status,create_time,create_staff, update_staff,update_time,shop_id )    
    //100056, 2=3011, 3=3066, 4=RW16071500003325, 5=SRW16071500003319, 6=3061, 7=3, 8=1, 9=2016-07-15 11:15:33.324, 10=3014, 11=3014, 12=2016-07-15 11:15:33.324, 13=3011, 14=3066, 15=RW16071500003325, 16=SRW16071500003319}
    @Test
    public void testPreOccupy(){
    	
    	ROrdCartItemCommRequest req=new ROrdCartItemCommRequest();
    	req.setId(100056L);
    	req.setRepCode(3011L);
    	req.setGdsId(3058L);
    	
    	req.setStockId(3066L);
    	req.setOrderId("RW16071500003325");
    	req.setOrderSubId("SRW16071500003319");
    	req.setSkuId(3061L);
    	req.setOrderAmount(3L);
    	req.setCreateTime(DateUtil.getTimestamp("2016-07-15 11:15:33","yyyy-MM-dd HH:mm:ss"));
    	req.setUpdateTime(DateUtil.getTimestamp("2016-07-15 11:15:33","yyyy-MM-dd HH:mm:ss"));
    	req.setUpdateStaff(3014L);
    	req.setCreateStaff(3014L);
    	req.setStaffId(3014L);
    	req.setShopId(3011L);
    	
    	gdsStockSV.addStockPreOccupy(req);
    }
	
	
}
