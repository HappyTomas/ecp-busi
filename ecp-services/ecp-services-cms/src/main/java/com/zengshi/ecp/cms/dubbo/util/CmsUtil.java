package com.zengshi.ecp.cms.dubbo.util;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPreRespDTO;

/**
 * CMS工具类 Title: ECP <br>
 * Project Name:ecp-services-cms-server <br>
 * Description: <br>
 * Date:2015年10月14日上午11:36:12 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author jiangzh
 * @version
 * @since JDK 1.6
 */
public class CmsUtil {

    private static final String MODULE = CmsUtil.class.getName();

    /**
     * 使用 Map按key进行排序
     * @param <T>
     * 
     * @param map
     * @return
     */
    public static <T> Map<Integer, List<T>> sortMapByKey(
            Map<Integer, List<T>> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<Integer, List<T>> sortMap = new TreeMap<Integer, List<T>>(
                new MapKeyComparator());
        sortMap.putAll(map);
        return sortMap;
    }

}
