package com.zengshi.ecp.im.test;

/**
 * Created by zhangys on 16/10/5.
 */

import com.zengshi.ecp.im.dao.model.ImCustInfo;
import com.zengshi.ecp.im.dao.model.ImStaffHotline;
import com.zengshi.ecp.im.dubbo.dto.ImCustReqDTO;
import com.zengshi.ecp.im.dubbo.dto.ImStaffHotlineReqDTO;
import com.zengshi.ecp.im.dubbo.dto.ImStaffHotlineResDTO;
import com.zengshi.ecp.im.dubbo.util.ImConstants;
import com.zengshi.ecp.im.service.common.interfaces.ICustServiceMgrSV;
import com.zengshi.ecp.im.service.common.interfaces.IStaffHotlineSV;
import com.zengshi.ecp.im.service.util.DateTimeUtil;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.MongoUtil;
import com.alibaba.fastjson.JSON;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;


import javax.annotation.Resource;
import java.util.*;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath*:spring/ecp-application-context-im.xml","classpath*:paasContext.xml"})
public class CustServiceMgrTest  extends EcpServicesTest {

    private static final String IM_STAFF_QUEUE_KEY = "ECP.IM.STAFF.QUEUE";
    private static final String IM_CUST_QUEUE_KEY = "ECP.IM.CUST.QUEUE";

    private static final String IM_CUST_WAITING_KEY = "ECP.IM.CUST_WAITING";
    private static final String IM_STAFF_IDLE_KEY = "ECP.IM.STAFF.IDLE";
    private static final String IM_STAFF_BUSY_KEY = "ECP.IM.STAFF_BUSY";
    private static final String IM_STAFF_OFFLINE_KEY = "ECP.IM.STAFF.OFFLINE";
    private static final String IM_STAFF_CUST_SET_KEY = "ECP.IM.STAFF.CUST.SET";
    private static final String IM_CUST_STAFF_MAP_KEY = "ECP.IM.CUST.STAFF.MAP";
    private static final String IM_STAFF_MAX_SERVICE_COUNT = "ECP.IM.STAFF.MAX.SERVICE.COUNT";
    private static final String IM_STAFF_CURR_LINE_COUNT = "ECP.IM.STAFF.CURR_LINE.COUNT";


    @Resource
    private ICustServiceMgrSV custServiceMgrSV;

    @Resource
    private IStaffHotlineSV staffHotlineSV;

    @Test
    public void testCacheUtil() {
//        Set<String> shopIds = CacheUtil.hkeys("ECP.IM.STAFF.QUEUE");
//        System.out.println(shopIds);
        for (int i = 1; i < 10; i++) {
            CacheUtil.saddStringItem("ECP.IM.MYDEV.TEST1", i + "");
        }
        Set<String> test1Set = CacheUtil.getSet("ECP.IM.MYDEV.TEST1");
        System.out.println(test1Set);
        System.out.println(CacheUtil.hgetItem("ECP.IM.MYDEV.TEST2","HELLO"));
        System.out.println(CacheUtil.hgetItem("ECP.IM.STAFF.MAX.SERVICE.COUNT3051","csa_dong2"));
    }

    @Test
    public void testMongoUtil() {
        String collName = "pq_svc_info";
        Map<String,Object> mapDoc = new HashMap<>();
        mapDoc.put("csaCode","test6");
        mapDoc.put("serviceCount",8);
        mapDoc.put("shopId",66);
        mapDoc.put("svcDate", DateTimeUtil.getCurrYMD());
        MongoUtil.insert(collName,mapDoc);
        DBObject dbRec = MongoUtil.findOne(collName, new BasicDBObject("hotlineId", 1),
                                   null, null);
        System.out.println(dbRec);
    }

    @Test
    public void testGetStaff() {
        long shopId = 6005;
        long businessType = 0;
        ImCustReqDTO custReqDTO = new ImCustReqDTO();
        custReqDTO.setShopId(shopId);
        custReqDTO.setOfStaffCode("1");
        custReqDTO.setCustLevel("2");
        custReqDTO.setBusinessType(businessType);
        ImStaffHotlineReqDTO staffReqDTO = new ImStaffHotlineReqDTO();
        staffReqDTO.setCsaCode("csa_whw");
        ImStaffHotlineResDTO resDTO = custServiceMgrSV.staffLogin(staffReqDTO);
    }

    @Test
    public void testInitMapQueue() {

    }

    @Test
    public void testGetStaffHotline() {
        ImCustReqDTO custReqDTO = new ImCustReqDTO();
        Long shopId = 100l;
        String[] csaCodes = new String[]{"csa_admin"};//,"csa_hxl"
        Long businessType = 0l;
        for (String csaCode : csaCodes) {
            ImStaffHotlineReqDTO dto = new ImStaffHotlineReqDTO();
            dto.setCsaCode(csaCode);
            dto.setShopId(shopId);
            custServiceMgrSV.staffLogin(dto);
        }
        custReqDTO.setShopId(shopId);
        custReqDTO.setOfStaffCode(csaCodes[0]);
        custReqDTO.setCustLevel("2");
        custReqDTO.setBusinessType(businessType);
    }

    @Test
    public void testSysCfg() {
        BaseSysCfgRespDTO res = SysCfgUtil.refreshSysCfgByCode("IM_STAFF_MAX_SERV_NUM");
        System.out.println("====================");
        System.out.println(JSON.toJSONString(res));
        res = SysCfgUtil.fetchSysCfg("IM_STAFF_MAX_SERV_NUM");
        System.out.println("====================");
        System.out.println(JSON.toJSONString(res));
    }

    @Test
    public void testData() {
        ImStaffHotlineReqDTO reqDTO = new ImStaffHotlineReqDTO();
        reqDTO.setStatus(ImConstants.STATE_1);
        List<ImStaffHotlineResDTO> list = staffHotlineSV.getHotlineByStaffList(reqDTO);
        for (ImStaffHotlineResDTO o : list) {
            System.out.println(o);
        }
    }

    private static class GetStaffThread implements Runnable {
        private ICustServiceMgrSV custSvcMgrSV;
        private ImCustReqDTO custReqDTO;

        public GetStaffThread(ICustServiceMgrSV custSvcMgrSV,ImCustReqDTO custReqDTO) {
            this.custSvcMgrSV = custSvcMgrSV;
            this.custReqDTO = custReqDTO;
        }
        @Override
        public void run() {
            System.out.println(custSvcMgrSV.getStaffHotline(custReqDTO));
        }
    }

    @Test
    public void testConCase() throws Exception{
        ImStaffHotlineReqDTO dto = null;
        ImStaffHotlineResDTO resDTO = null;
        long shopId = 100;
        String[] csaCodes = new String[]{"csa_pxh","csa_dss"};
        for (String csaCode : csaCodes) {
            dto = new ImStaffHotlineReqDTO();
            dto.setCsaCode(csaCode);
            dto.setShopId(shopId);
            custServiceMgrSV.staffLogin(dto);
        }
        dto.setLineStatus(ImConstants.HANG);
        dto.setCsaCode("csa_dss");
        custServiceMgrSV.alterStaffLineStatus(dto);

        dto.setLineStatus(ImConstants.HANG);
        dto.setCsaCode("csa_pxh");
        custServiceMgrSV.alterStaffLineStatus(dto);

        ImCustReqDTO custReqDTO = new ImCustReqDTO();
        custReqDTO.setOfStaffCode("madd0107");
        custReqDTO.setCustLevel(ImConstants.GOLD_LEVEL_CUST);
        custReqDTO.setShopId(shopId);
        custReqDTO.setBusinessType(ImConstants.BUSINESS_TYPE_0);
        List<GetStaffThread> theadTests = new ArrayList<>();
        GetStaffThread thread = new GetStaffThread(custServiceMgrSV,custReqDTO);
        theadTests.add(thread);

        custReqDTO.setOfStaffCode("madd0108");
        custReqDTO.setCustLevel(ImConstants.GOLD_LEVEL_CUST);
        custReqDTO.setShopId(shopId);
        custReqDTO.setBusinessType(ImConstants.BUSINESS_TYPE_0);
        thread = new GetStaffThread(custServiceMgrSV,custReqDTO);
        theadTests.add(thread);

        custReqDTO.setOfStaffCode("madd0109");
        custReqDTO.setCustLevel(ImConstants.GOLD_LEVEL_CUST);
        custReqDTO.setShopId(shopId);
        custReqDTO.setBusinessType(ImConstants.BUSINESS_TYPE_0);
        thread = new GetStaffThread(custServiceMgrSV,custReqDTO);
        theadTests.add(thread);

        custReqDTO.setOfStaffCode("madd0110");
        custReqDTO.setCustLevel(ImConstants.GOLD_LEVEL_CUST);
        custReqDTO.setShopId(shopId);
        custReqDTO.setBusinessType(ImConstants.BUSINESS_TYPE_0);
        thread = new GetStaffThread(custServiceMgrSV,custReqDTO);
        theadTests.add(thread);

        custReqDTO.setOfStaffCode("madd0111");
        custReqDTO.setCustLevel(ImConstants.GOLD_LEVEL_CUST);
        custReqDTO.setShopId(shopId);
        custReqDTO.setBusinessType(ImConstants.BUSINESS_TYPE_0);
        thread = new GetStaffThread(custServiceMgrSV,custReqDTO);
        theadTests.add(thread);

        for (GetStaffThread td : theadTests) {
            Thread.sleep(1000*3);
            new Thread(td).start();
        }
        dto.setLineStatus(ImConstants.HANG);
        dto.setCsaCode("csa_dss");
        custServiceMgrSV.alterStaffLineStatus(dto);
        Thread.currentThread().join();
        for (String csaCode : csaCodes) {
            dto = new ImStaffHotlineReqDTO();
            dto.setCsaCode(csaCode);
            dto.setShopId(shopId);
            custServiceMgrSV.staffLogout(dto);
        }
    }

    @Test
    public void testStaffLogin() {
        ImStaffHotlineReqDTO dto = null;
        ImStaffHotlineResDTO resDTO = null;
        long shopId = 8866;
        String csaCode = "csa_pxh";
        dto = new ImStaffHotlineReqDTO();
        dto.setCsaCode(csaCode);
        dto.setShopId(shopId);
        custServiceMgrSV.staffLogin(dto);
        custServiceMgrSV.staffLogin(dto);
        custServiceMgrSV.staffLogin(dto);
        Set<String> set = CacheUtil.zgetItems(IM_STAFF_IDLE_KEY+shopId);
        Integer cnt = (Integer) CacheUtil.hgetItem(IM_STAFF_MAX_SERVICE_COUNT+shopId,csaCode);
        System.out.println(cnt);
    }

    @Test
    public void testGetStaff3() {
        ImStaffHotlineResDTO resDTO = null;
        long shopId = 2006;
        ImCustReqDTO custReqDTO = new ImCustReqDTO();
        custReqDTO.setOfStaffCode("madd0107");
        custReqDTO.setCustLevel(ImConstants.GOLD_LEVEL_CUST);
        custReqDTO.setShopId(shopId);
        custReqDTO.setBusinessType(ImConstants.BUSINESS_TYPE_0);
        resDTO = custServiceMgrSV.getStaffHotline(custReqDTO);
        System.out.println("取到的客服信息:"+resDTO);
    }

    @Test
    public void testCustServiceMgr() {
        ImStaffHotlineReqDTO dto = null;
        ImStaffHotlineResDTO resDTO = null;
        long shopId = 0L;
        String[] csaCodes = new String[]{"csa_dong1","csa_dong2"};
        for (String csaCode : csaCodes) {
            dto = new ImStaffHotlineReqDTO();
            dto.setCsaCode(csaCode);
            resDTO = custServiceMgrSV.staffLogin(dto);
            shopId = resDTO.getShopId();
        }
        dto.setShopId(shopId);
        dto.setLineStatus(ImConstants.HANG);
        dto.setCsaCode(csaCodes[1]);
        custServiceMgrSV.alterStaffLineStatus(dto);

        ImCustReqDTO custReqDTO = new ImCustReqDTO();
        String custCode = "madd0107";
        custReqDTO.setOfStaffCode(custCode);
        custReqDTO.setCustLevel(ImConstants.GOLD_LEVEL_CUST);
        custReqDTO.setShopId(shopId);
        custReqDTO.setBusinessType(ImConstants.BUSINESS_TYPE_0);
        resDTO = custServiceMgrSV.getStaffHotline(custReqDTO);
        System.out.println("取到的客服信息:"+resDTO);
        assertTrue("取客服返回客服出错:", csaCodes[0].equals(resDTO.getCsaCode()));
        assertTrue("取客服返回买家出错:", custCode.equals(resDTO.getOfStaffCode()));
        assertTrue("取客服出错:", 0 ==resDTO.getWaitCount());

//        dto.setLineStatus(ImConstants.HANG);
//        dto.setCsaCode("csa_pxh");
//        custServiceMgrSV.alterStaffLineStatus(dto);

        custCode = "madd0108";
        custReqDTO.setOfStaffCode(custCode);
        custReqDTO.setCustLevel(ImConstants.NORMAL_LEVEL_CUST);
        custReqDTO.setShopId(shopId);
        custReqDTO.setBusinessType(ImConstants.BUSINESS_TYPE_0);
        resDTO = custServiceMgrSV.getStaffHotline(custReqDTO);
        System.out.println("取到的客服信息:"+resDTO);
        assertTrue("取客服返回客服出错:", csaCodes[0].equals(resDTO.getCsaCode()));
        assertTrue("取客服返回买家出错:", custCode.equals(resDTO.getOfStaffCode()));
        assertTrue("取客服返回等待人数出错:", 0 == resDTO.getWaitCount());


//        dto.setLineStatus(ImConstants.ONLINE);
//        dto.setCsaCode("csa_pxh");
//        custServiceMgrSV.alterStaffLineStatus(dto);

        custCode = "madd0109";
        custReqDTO.setOfStaffCode(custCode);
        custReqDTO.setCustLevel(ImConstants.NORMAL_LEVEL_CUST);
        custReqDTO.setShopId(shopId);
        custReqDTO.setBusinessType(ImConstants.BUSINESS_TYPE_0);
        resDTO = custServiceMgrSV.getStaffHotline(custReqDTO);
        System.out.println("取到的客服信息:"+resDTO);
        assertTrue("取客服返回等待人数出错:", 1 == resDTO.getWaitCount());

        resDTO = custServiceMgrSV.getWaitCount(dto);
        System.out.println("买家会员等待人数:"+resDTO);
        assertTrue("取客服返回等待人数出错:", 1 == resDTO.getWaitCount());

        custCode = "madd0110";
        custReqDTO.setOfStaffCode(custCode);
        custReqDTO.setCustLevel(ImConstants.GOLD_LEVEL_CUST);
        custReqDTO.setShopId(shopId);
        custReqDTO.setBusinessType(ImConstants.BUSINESS_TYPE_0);
        resDTO = custServiceMgrSV.getStaffHotline(custReqDTO);
        System.out.println("取到的客服信息:"+resDTO);
        assertTrue("取客服返回等待人数出错:", 2 == resDTO.getWaitCount());
        resDTO = custServiceMgrSV.getWaitCount(dto);
        System.out.println("买家会员等待人数:" + resDTO);
        assertTrue("取客服返回等待人数出错:", 2 == resDTO.getWaitCount());

        dto.setShopId(shopId);
        dto.setLineStatus(ImConstants.HANG);
        dto.setCsaCode(csaCodes[0]);
        custServiceMgrSV.alterStaffLineStatus(dto);

        custCode = "madd0111";
        custReqDTO.setOfStaffCode(custCode);
        custReqDTO.setCustLevel(ImConstants.PLATINUM_LEVEL_CUST);
        custReqDTO.setShopId(shopId);
        custReqDTO.setBusinessType(ImConstants.BUSINESS_TYPE_0);
        resDTO = custServiceMgrSV.getStaffHotline(custReqDTO);
        System.out.println("取到的客服信息:"+resDTO);
        assertTrue("取客服返回等待人数出错:", -99 == resDTO.getWaitCount());

        resDTO = custServiceMgrSV.getWaitCount(dto);
        System.out.println("买家会员等待人数:"+resDTO);
        assertTrue("取客服返回等待人数出错:", 0 == resDTO.getWaitCount());

        custCode = "madd0109";
        custReqDTO.setOfStaffCode(custCode);
        custReqDTO.setCustLevel(ImConstants.NORMAL_LEVEL_CUST);
        custReqDTO.setShopId(shopId);
        custReqDTO.setBusinessType(ImConstants.BUSINESS_TYPE_0);
        resDTO = custServiceMgrSV.getStaffHotline(custReqDTO);
        System.out.println("取到的客服信息:"+resDTO);
        assertTrue("取客服返回等待人数出错:", 1 == resDTO.getWaitCount());

        resDTO = custServiceMgrSV.getWaitCount(dto);
        System.out.println("买家会员等待人数:"+resDTO);
        assertTrue("取客服返回等待人数出错:", 3 == resDTO.getWaitCount());

        custCode = "madd0110";
        custReqDTO.setOfStaffCode(custCode);
        custReqDTO.setCustLevel(ImConstants.GOLD_LEVEL_CUST);
        custReqDTO.setShopId(shopId);
        custReqDTO.setBusinessType(ImConstants.BUSINESS_TYPE_0);
        resDTO = custServiceMgrSV.getStaffHotline(custReqDTO);
        System.out.println("取到的客服信息:"+resDTO);
        assertTrue("取客服返回等待人数出错:", 2 == resDTO.getWaitCount());

        resDTO = custServiceMgrSV.getWaitCount(dto);
        System.out.println("买家会员等待人数:"+resDTO);
        assertTrue("取客服返回等待人数出错:", 3 == resDTO.getWaitCount());

        custCode = "madd0107";
        dto.setOfStaffCode(custCode);
        dto.setReqTime(System.currentTimeMillis());
        dto.setCsaCode(null);
        resDTO = custServiceMgrSV.finishChat(dto);
        System.out.println("调用finishChat结束会话返回:"+resDTO);
        dto.setCsaCode(csaCodes[0]);
        resDTO = custServiceMgrSV.staffFinishChat(dto);
        System.out.println("调用staffFinishChat结束会话返回:"+resDTO);

        custReqDTO.setOfStaffCode("madd0110");
        custReqDTO.setCustLevel(ImConstants.GOLD_LEVEL_CUST);
        custReqDTO.setShopId(shopId);
        custReqDTO.setBusinessType(ImConstants.BUSINESS_TYPE_0);
        resDTO = custServiceMgrSV.getStaffHotline(custReqDTO);
        System.out.println("取到的客服信息:"+resDTO);
        assertTrue("取客服返回等待人数出错:", 2 == resDTO.getWaitCount());

        resDTO = custServiceMgrSV.getWaitCount(dto);
        System.out.println("买家会员等待人数:"+resDTO);
        assertTrue("取客服返回等待人数出错:", 3 == resDTO.getWaitCount());

        custCode = "madd0111";
        custReqDTO.setOfStaffCode(custCode);
        custReqDTO.setCustLevel(ImConstants.PLATINUM_LEVEL_CUST);
        custReqDTO.setShopId(shopId);
        custReqDTO.setBusinessType(ImConstants.BUSINESS_TYPE_0);
        resDTO = custServiceMgrSV.getStaffHotline(custReqDTO);
        System.out.println("取到的客服信息:"+resDTO);
        assertTrue("取客服返回客服出错:", csaCodes[0].equals(resDTO.getCsaCode()));
        assertTrue("取客服返回买家出错:", custCode.equals(resDTO.getOfStaffCode()));
        assertTrue("取客服返回等待人数出错:", 0 == resDTO.getWaitCount());

        resDTO = custServiceMgrSV.getWaitCount(dto);
        System.out.println("买家会员等待人数:"+resDTO);
        assertTrue("取客服返回等待人数出错:", 2 == resDTO.getWaitCount());

        for (String csaCode : csaCodes) {
            dto = new ImStaffHotlineReqDTO();
            dto.setCsaCode(csaCode);
            custServiceMgrSV.staffLogout(dto);
        }

    }

    @Test
    public void testLogout() {
        ImStaffHotlineReqDTO dto = null;
        String[] csaCodes = new String[]{"csa_dong1","csa_dong2"};
        for (String csaCode : csaCodes) {
            dto = new ImStaffHotlineReqDTO();
            dto.setCsaCode(csaCode);
            custServiceMgrSV.staffLogout(dto);
        }
    }
}
