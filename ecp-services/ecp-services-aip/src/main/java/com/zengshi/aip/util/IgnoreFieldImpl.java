package com.zengshi.aip.util;

import org.apache.commons.lang.StringUtils;

import net.sf.json.util.PropertyFilter;

public class IgnoreFieldImpl implements PropertyFilter {

	private String[] names;

	public IgnoreFieldImpl(String[] names) {
		this.names = names;
	}

	@Override
	public boolean apply(Object obj, String name, Object value) {
		if (names == null || names.length == 0) {
			return false;
		}
		for (String temp : names) {
			if (StringUtils.equals(temp, name)) {
				return true;
			}
		}
		return false;
	}
}
