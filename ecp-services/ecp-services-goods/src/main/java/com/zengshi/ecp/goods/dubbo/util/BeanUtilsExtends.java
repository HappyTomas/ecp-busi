package com.zengshi.ecp.goods.dubbo.util;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import com.zengshi.paas.utils.LogUtil;

/**
* 重写BeanUtils.copyProperties
*
* @author monkey
*/
public class BeanUtilsExtends extends BeanUtils {
   static {
       ConvertUtils.register(new DateConvert(), java.util.Date.class);
       ConvertUtils.register(new DateConvert(), java.sql.Date.class);
   }

   public static void copyProperties(Object dest, Object orig) {
       try {
           BeanUtils.copyProperties(dest, orig);
       } catch (IllegalAccessException ex) {
       		LogUtil.error(BeanUtilsExtends.class.getName(),ex.getMessage(), ex);
       } catch (InvocationTargetException ex) {
       		LogUtil.error(BeanUtilsExtends.class.getName(),ex.getMessage(), ex);
       }
   }
}


