package com.zengshi.ecp.prom.dubbo.util;


import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.beanutils.PropertyUtils;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2016-3-3 <br>
 * Copyright (c) 2016 AI <br>
 * 
 * @author huangjx
 */
public class PromUtil {

    @SuppressWarnings("rawtypes")
    public static <TARGET> Map<String, Object> ConvertObjToMap(TARGET target, Class cls) {
        Map<String, Object> map = new HashMap<String, Object>();
        String sname = "";
        Field[] fields = cls.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            try {
                sname = fields[i].getName();
                if (sname.equals("serialVersionUID")) {
                    continue;
                }
                // 检查vo 是否有该属性的 PropertyDescriptor ;如果没有，或者读取方法为空，那么则继续；
                PropertyDescriptor descriptor = PropertyUtils.getPropertyDescriptor(target, sname);
                if (descriptor == null || descriptor.getReadMethod() == null) {
                    continue;
                }
                // 获取属性值
                Object obj = MethodUtils.invokeMethod(target, "get" + sname.substring(0, 1).toUpperCase() + sname.substring(1), null);
                map.put(sname, obj);
            } catch (Exception e) {

            }
        }

        return map;
    }
}
