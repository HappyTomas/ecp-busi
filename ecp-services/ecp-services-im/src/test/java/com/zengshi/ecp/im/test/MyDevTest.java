package com.zengshi.ecp.im.test;

import org.junit.Test;

import java.util.*;

/**
 * Created by zhangys on 16/11/26.
 */
public class MyDevTest {

    @Test
    public void testMisc() {
        Integer a1 = 10;
        Integer a2 = 10;
        if (a1 == a2) {
            System.out.println("a1 is equal a2");
        } else {
            System.out.println("a1 is not equal a2");
        }

        Long l1 = 10l;
        Long l2 = 9l;
        if (l1 > l2) {
            System.out.println("l1 is greate l2");
        } else {
            System.out.println("l1 is not greate l2");
        }

        double d1 = l1;
        System.out.println(d1);

        Set<String> set = new HashSet<>();
        set.add("11");
        set.add("22");
        set.add("33");
        System.out.println(set);
        System.out.println("set size:"+set.size());
        String[] strArr = new String[set.size()];
        set.toArray(strArr);
//        System.out.println("strArr:"+String.join(",",strArr));

        PriorityQueue<String> strPq = new PriorityQueue<>();
        strPq.add("11");
        strPq.add("22");
        strPq.add("33");
        strPq.add("44");
        System.out.println("init:"+strPq);
        List<String> strList = new ArrayList<>();
        strList.add("11");
        strList.add("33");
//        strPq.removeAll(strPq);
        strPq.removeAll(strList);
        System.out.println("after remove all :"+strPq);

        Calendar c1 = Calendar.getInstance();

        System.out.println(c1);
        Double d2 = new Double("02");
        System.out.println(d2);
    }

   /* @Test
    public void testJdkUse() {
        PriorityQueue<Integer> IntPQ = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        for (int i = 6; i > 0; i--) {
            IntPQ.add(i);
        }

        //        for (int i = 1; i < 7; i++) {
        //            IntPQ.add(i);
        //        }
        IntPQ.remove(3);
        while (!IntPQ.isEmpty()) {
            System.out.println(IntPQ.poll().toString());
        }
    }*/

}
