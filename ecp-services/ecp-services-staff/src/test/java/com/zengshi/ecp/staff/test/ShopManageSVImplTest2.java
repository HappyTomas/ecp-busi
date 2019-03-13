package com.zengshi.ecp.staff.test;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.general.order.dto.PayQuartzInfoRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.general.solr.message.DeltaMessage;
import com.zengshi.ecp.general.solr.util.DeltaUtils;
import com.zengshi.ecp.general.solr.util.DeltaUtils.EDeltaType;
import com.zengshi.ecp.general.solr.util.DeltaUtils.EOperType;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.IShopManageSV;
import com.zengshi.ecp.staff.service.util.StaffTools;
import com.zengshi.paas.utils.LogUtil;

public class ShopManageSVImplTest2 extends EcpServicesTest {
    
    @Resource
    private IShopManageSV shopManageSV;
    
    @Test
    public void test(){
    	PayQuartzInfoRequest req = new PayQuartzInfoRequest();
    	req.setPayment(2L);
    	req.setStaffId(592L);
    	List<ROrdCartItemCommRequest> ordCartItemCommList = new ArrayList<ROrdCartItemCommRequest>();
    	ROrdCartItemCommRequest a = new ROrdCartItemCommRequest();
    	a.setShopId(69L);
    	ordCartItemCommList.add(a);
    	req.setOrdCartItemCommList(ordCartItemCommList);
    	shopManageSV.updateShopVipRealForPay(req);
    	//System.out.println(code);
    }
    @Test
    public void test1() {
    	DeltaMessage msg=new DeltaMessage();
        msg.setDeltaType_(EDeltaType.DBOBJECT);
        try {
            msg.setOperType_(EOperType.UPDATE);
            msg.setOperType(EOperType.UPDATE.getType());
            List<String> ids=new ArrayList<String>();
            ids.add(171 + "");
            msg.setIds(ids);
            msg.setObjectNameEn("T_SHOP_INFO");
            DeltaUtils.sendDeltaMessage(msg);
            LogUtil.info("staff", "Send gdsInfo index create/update Message,Id is" + 171);
        } catch (Exception e) {
            LogUtil.error(StaffTools.class.getName(), "GdsInfo index create/update failed! please check you solr server Or check you MQ server!", e);
        }
    }

}

