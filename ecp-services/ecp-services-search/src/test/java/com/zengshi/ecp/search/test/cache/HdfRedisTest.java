package com.zengshi.ecp.search.test.cache;

import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.paas.utils.CacheUtil;
import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by HDF on 2016/10/22.
 */
public class HdfRedisTest extends EcpServicesTest {

    private final static String CACHE_KEY_SECONDCATGMAP="secondCatgMapTest";
    private final static String CACHE_KEY_SECONDCOLUMNMAP="secondColumnMapTest";

    @Resource
    private IGdsCategoryRSV gdsCategoryRSV;

    private final int iUser=100;

    /**
     * 每个二级分类列表单独缓存
     */
    @Test
    public void cacheSecondIndependent(){



    }

    /**
     * 所有二级分类统一缓存为一个Redis HashMap
     */
    @Test
    public void cacheSecondUnity(){

        //100个顶级分类
        List<String> categoryList = new ArrayList<String>();
        for(int i=1;i<=100;i++){
            categoryList.add(i+"");
        }

        //==================================================缓存设置==================================================
        CacheUtil.delItem(CACHE_KEY_SECONDCATGMAP);
        // 缓存失效或第一次初始化缓存，重新查表
        if(CollectionUtils.isNotEmpty(categoryList)) {

            // 重设分布式缓存
            HashMap<String, String> map = new HashMap<String, String>(
                    categoryList.size());

            for (String s : categoryList) {
                try {
                    List<GdsCategoryRespDTO> categoryRespDTOList=new ArrayList<GdsCategoryRespDTO>();
                    //20个二级分类
                    for(int i=1;i<=20;i++){
                        GdsCategoryRespDTO gdsCategoryRespDTO=new GdsCategoryRespDTO();
                        gdsCategoryRespDTO.setCatgCode(i+"");
                        gdsCategoryRespDTO.setCatgName("虚拟分类"+i);
                        gdsCategoryRespDTO.setCatgLevel((short)2);
                        categoryRespDTOList.add(gdsCategoryRespDTO);
                    }
                    map.put(s, JSON.toJSONString(categoryRespDTOList));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // 重设分布式缓存
            CacheUtil.setHashMap(CACHE_KEY_SECONDCATGMAP, map);
        }

        //====================================================取缓存====================================================
        Long start=System.currentTimeMillis();

        //模拟并发取Redis缓存
        ExecutorService exec= Executors.newFixedThreadPool(10);
        for(int i=1;i<=iUser;i++){
            Runnable r=new Runnable() {
                @Override
                public void run() {
                    Map<String, String> map = CacheUtil
                            .getHashMap(CACHE_KEY_SECONDCATGMAP);
                    System.out.println("缓存获取完毕！！！");
                }
            };
            exec.execute(r);
        }

        try {
            exec.shutdown();
            exec.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Long end=System.currentTimeMillis();

        Long cost=(end-start)/iUser;
        System.out.println("============平均取缓存耗时：=============="+cost);
        System.out.println("-------------------");

    }

}
