package com.zengshi.ecp.prom.dubbo.impl.converter;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.BeanUtils;

import com.zengshi.paas.utils.ObjectCopyUtil;

public final class ConversionHelper {
    static final Map<String, PropertyDescriptor[]> propertyDescriptorCache = new ConcurrentHashMap();
/*
    public static void copyProperties(Object source, Object target) throws ConversionException {
        validateParameterNotNull(source, "source must not be null");
        validateParameterNotNull(target, "target must not be null");

        Class targetClass = target.getClass();

        PropertyDescriptor[] targetPds = (PropertyDescriptor[]) propertyDescriptorCache
                .get(targetClass.getName());
        if (targetPds == null) {
            targetPds = BeanUtils.getPropertyDescriptors(targetClass);
            propertyDescriptorCache.put(targetClass.getName(), targetPds);
        }
        for (int i = 0; i < targetPds.length; i++) {
            PropertyDescriptor targetPd = targetPds[i];
            if (targetPd.getWriteMethod() == null)
                continue;
            PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(source.getClass(),
                    targetPd.getName());
            if ((sourcePd == null) || (sourcePd.getReadMethod() == null))
                continue;
            try {
                Method readMethod = sourcePd.getReadMethod();
                if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                    readMethod.setAccessible(true);
                }

                Object value = readMethod.invoke(source, new Object[0]);
                Method writeMethod = targetPd.getWriteMethod();
                if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                    writeMethod.setAccessible(true);
                }
                // add by huangjx
                if (value instanceof Date) {
                    value = new java.sql.Timestamp(((Date) value).getTime());
                }
                Object[] args = { value };
                try {
                    writeMethod.invoke(target, args);
                } catch (IllegalArgumentException localIllegalArgumentException1) {
                    localIllegalArgumentException1.printStackTrace();
                }

            } catch (IllegalArgumentException e) {
                throw new ConversionException(e.getMessage(), e);
            } catch (IllegalAccessException e) {
                throw new ConversionException(e.getMessage(), e);
            } catch (InvocationTargetException e) {
                throw new ConversionException(e.getMessage(), e);
            }
        }
    }*/

    public static void copyProperties(Object source, Object target, String not_copy,
            boolean isNullCopy) throws ConversionException {
        try {
            ObjectCopyUtil.copyObjValue(source, target, not_copy, isNullCopy);
        } catch (Exception e) {
            throw new ConversionException(e.getMessage(), e);
        }
    }

    /*
     * public static <SRC, DEST, COLL extends Collection<DEST>> COLL convertAll(Collection<SRC> src,
     * Converter<SRC, DEST> converter, COLL target) { for (Object srcElem : src) { Object destElem =
     * converter.convert(srcElem); target.add(destElem); } return target; }
     */

    public static void validateParameterNotNull(Object parameter, String nullMessage)
            throws ConversionException {
        // Preconditions.checkArgument(parameter != null, nullMessage);
        if (parameter == null) {
            throw new ConversionException(nullMessage);
        }
    }
}
