/**
 * �� �� ��:    ParseBeanUtilsBean.java
 * ��    Ȩ:     Asisinfo Co., Ltd. Copyright 2009-2037,  All rights reserved
 * ��    ��:     <����>
 * �� �� ��:    Ҷ�»�
 * �޸�ʱ��:   2013-8-20
 * ���ٵ���:  <���ٵ���>
 * �޸ĵ���:  <�޸ĵ���>
 * �޸�����:  <�޸�����>
 */
package com.zengshi.aip.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.lang.StringUtils;

/**
 * 
 * @since 1.0
 */
public class ParseBeanUtils extends BeanUtilsBean {
	@Override
	public Map<String, Object> describe(Object bean)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		if (bean == null) {
			return (new java.util.HashMap<String, Object>());
		}
		Map<String, Object> description = new HashMap<String, Object>();
		if (bean instanceof DynaBean) {
			DynaProperty[] descriptors = ((DynaBean) bean).getDynaClass()
					.getDynaProperties();
			for (int i = 0; i < descriptors.length; i++) {
				String name = descriptors[i].getName();
				description.put(name, getProperty(bean, name));
			}
		} else {
			PropertyDescriptor[] descriptors = getPropertyUtils()
					.getPropertyDescriptors(bean);
			for (int i = 0; i < descriptors.length; i++) {
				String name = descriptors[i].getName();
				if (!"class".equals(name) && getPropertyUtils().getReadMethod(descriptors[i]) != null) {
					String value = (String) getPropertyUtils().getNestedProperty(bean, name);
					if(value != null && StringUtils.isNotEmpty(value))
						description.put(name, value);
				}
			}
		}
		return description;

	}
}
