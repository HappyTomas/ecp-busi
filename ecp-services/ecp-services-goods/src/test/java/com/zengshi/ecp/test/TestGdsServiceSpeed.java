package com.zengshi.ecp.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.zengshi.ecp.goods.dao.model.GdsSku2Prop;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropValueRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoDetailRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2MediaRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfores.IGdsSkuInfo2MediaSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfores.IGdsSkuInfo2PropSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: ECP 测试商品服务速度<br>
 * Project Name:ecp-services-goods-server <br>
 * Description: <br>
 * Date:2016年4月23日下午2:11:25  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version  
 * @since JDK 1.6
 */
public class TestGdsServiceSpeed extends EcpServicesTest {

    @Resource
    private IGdsInfoQueryRSV iGdsInfoQueryRSV;

    @Resource
    private IGdsSkuInfo2PropSV gdsSkuInfo2PropSV;
    
    @Resource
    private IGdsSkuInfo2MediaSV gdsSkuInfo2MediaSV;

    /**
     * 测试多线程调用效率 testMultiThead:(这里用一句话描述这个方法的作用). <br/>
     * 
     * @author linwb3
     * @since JDK 1.6
     */
    @Test
    public void testMultiThead() {
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(600);
        ThreadPoolExecutor pool = new ThreadPoolExecutor(40, 40, 1, TimeUnit.HOURS, queue, new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 1; i <= 40; i++) {
            Task task = new Task();
            pool.execute(task);
        }

        pool.shutdown();//只是不能再提交新任务，等待执行的任务不受影响  
        
        try {  
            boolean loop = true;  
            do {    //等待所有任务完成  
                loop = !pool.awaitTermination(60, TimeUnit.SECONDS);  //阻塞，直到线程池里所有任务结束
            } while(loop);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
        System.out.println("共"+count.longValue()+"次");
        System.out.println("共"+total.longValue()+"ms");
        System.out.println("共"+total.longValue()/count.longValue()+"ms/次");

    }

    class Task extends Thread {
        @Override
        public void run() {
            long temp=0;
            for (int i = 0; i < 500; i++) {
                Long data4 = System.currentTimeMillis();
                testGdsDetail();
                Long time=getTime(data4);
                LogUtil.error("", "整个服务耗时" + time);
                count.addAndGet(1);
                temp=temp+time;
            }
            total.addAndGet(temp);
        };
    }

    private Long getTime(Long data) {
        return (System.currentTimeMillis() - data);
    }

    @Test
    public void testQueryGdsInfoPage() {
        GdsSku2PropReqDTO reqDTO = new GdsSku2PropReqDTO();
        reqDTO.setGdsId(37804L);
        reqDTO.setSkuId(75098L);
        reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);

        Long data2 = System.currentTimeMillis();
        GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
        gdsInfoReqDTO.setShopId(100L);
        gdsInfoReqDTO.setPageNo(1);
        gdsInfoReqDTO.setPageSize(30);
        iGdsInfoQueryRSV.queryGdsInfoListPage(gdsInfoReqDTO);
        Long time=getTime(data2);
        LogUtil.error("", "查询商品列表耗时" + time);
    }
    
    private static AtomicLong count=new AtomicLong(0);
    private static AtomicLong total=new AtomicLong(0);

    @Test
    public void testQuerySku2ProP() {
        GdsSku2PropReqDTO reqDTO = new GdsSku2PropReqDTO();
        reqDTO.setGdsId(46481L);
        reqDTO.setSkuId(86868L);
        reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
        Long data2 = System.currentTimeMillis();
        List<GdsSku2Prop> sku2props = gdsSkuInfo2PropSV.queryGdsSkuInfo2Prop(reqDTO);
        LogUtil.error("", "查询单品属性个数:" + sku2props.size());
        Long time=getTime(data2);
        LogUtil.error("", "查询单品属性耗时" + time+"ms");
    }
    
    @Test
    public void testQuerySku2Media() {
        Long data2 = System.currentTimeMillis();
        List<GdsSku2MediaRespDTO> media = gdsSkuInfo2MediaSV.querySkuMediaBySkuId(86868L, 46481L);
        LogUtil.error("", "查询单品图片个数:" + media.size());
        Long time=getTime(data2);
        LogUtil.error("", "查询单品图片耗时" + time+"ms");
    }
    

    @Test
    public void testGdsDetail() {
        GdsInfoReqDTO dto = new GdsInfoReqDTO();
        //46481L-86868L 单品图片
        //46481L-86869L
        dto.setId(46481L);
        dto.setSkuId(86869L);
        GdsQueryOption[] gdsQueryOptions = new GdsQueryOption[] { GdsQueryOption.BASIC };
        SkuQueryOption[] skuQueryOptions = new SkuQueryOption[] { SkuQueryOption.BASIC, SkuQueryOption.PROP, SkuQueryOption.MEDIA, SkuQueryOption.SHOWSTOCK, SkuQueryOption.CAlDISCOUNT };
        dto.setGdsQueryOptions(gdsQueryOptions);
        dto.setSkuQuerys(skuQueryOptions);
        GdsInfoDetailRespDTO resultDto = null;
        try {
            
            resultDto = iGdsInfoQueryRSV.queryGdsInfoDetail(dto);
            resultDto = new GdsInfoDetailRespDTO();
            GdsSkuInfoRespDTO gdsSkuInfoRespDTO = new GdsSkuInfoRespDTO();
            gdsSkuInfoRespDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_DELETE);
            resultDto.setSkuInfo(gdsSkuInfoRespDTO);

        } catch (BusinessException e) {
            if (resultDto == null || GdsErrorConstants.GdsInfo.ERROR_GOODS_GDSINFO_210005.equals(e.getErrorCode())) {
                resultDto = new GdsInfoDetailRespDTO();
                GdsSkuInfoRespDTO gdsSkuInfoRespDTO = new GdsSkuInfoRespDTO();
                gdsSkuInfoRespDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_DELETE);
                resultDto.setSkuInfo(gdsSkuInfoRespDTO);
            }
            LogUtil.error("", "无法获取商品详情信息！", e);
        }
        String shopName = "";
        String stockStatus = "";
        String stockStatusDesc = "";
        if (StringUtil.isNotEmpty(resultDto)) {
            if (resultDto.getSkuInfo() != null) {
                stockStatus = GdsUtils.checkStcokStatus(resultDto.getSkuInfo().getRealAmount());
                stockStatusDesc = GdsUtils.checkStcokStatusDesc(resultDto.getSkuInfo().getRealAmount());
                Map<String, GdsPropRespDTO> map = resultDto.getSkuInfo().getAllPropMaps();
                String[] strs = new String[] { "1020", "1021", "1022", "1023", "1024", "1025" };
                int detailCount = 0;
                if (map != null) {
                    for (int i = 0; i < strs.length; i++) {
                        GdsPropRespDTO gdsPropRespDTO = map.get(strs[i]);
                        if (gdsPropRespDTO != null && gdsPropRespDTO.getValues() != null) {
                            for (GdsPropValueRespDTO gdsPropValueRespDTO : gdsPropRespDTO.getValues()) {
                                if (StringUtil.isNotBlank(gdsPropValueRespDTO.getPropValue())) {
                                    gdsPropValueRespDTO.setPropValue(getHtmlUrl(gdsPropValueRespDTO.getPropValue()));
                                    detailCount++;
                                }
                            }
                        }
                    }
                }
                // 根据录入的判断是否有目录等这些属性，如果这些都没有，则按照录入来说就会提供产品详情的录入，此时只展示产品详情
                if (detailCount == 0) {
                    // 针对人卫，只显示产品详情
                    if (StringUtil.isNotBlank(resultDto.getGdsDesc())) {
                        resultDto.setGdsDesc(getHtmlUrl(resultDto.getGdsDesc()));
                    }
                }
            }
        }
    }

    private String getHtmlUrl(String vfsId) {
        if (StringUtil.isBlank(vfsId)) {
            return "";
        }
        return ImageUtil.getStaticDocUrl(vfsId, "html");
    }
    
    private static String path = "C:\\Users\\忘记的那个我~\\Desktop\\日志.txt";

    private static String path1 = "C:\\Users\\忘记的那个我~\\Desktop\\日志1.txt";
    
    
    public static void dealLog() {
        File file = new File(path);
        try {
            List<String> temp = new ArrayList<String>();
            List<String> content = FileUtils.readLines(file);
            for (String string : content) {
                if (!string.contains("interceptorDubboReq") && !string.contains("Exception") && !string.contains("at")) {
                    temp.add(string);
                }
            }
            FileUtils.writeLines(new File(path1), temp, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        dealLog();
    }

}
