package com.zengshi.ecp.cms.dubbo.util;

import java.util.Comparator;

//比较器类
public class MapKeyComparator implements Comparator<Integer> {
  public int compare(Integer str1, Integer str2) {
      return str1.compareTo(str2);
  }
}
