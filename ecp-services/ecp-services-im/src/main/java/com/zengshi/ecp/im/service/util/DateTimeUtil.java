package com.zengshi.ecp.im.service.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zhangys on 16/10/11.
 */
public class DateTimeUtil {

    private static final Logger logger = LoggerFactory.getLogger(DateTimeUtil.class);

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    public static String getCurrYMD() {
        return sdf.format(new Date());
    }

    public static boolean time1AfterTime2(long t1,long t2,long diff) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTimeInMillis(t1);
        c2.setTimeInMillis(t2+diff);
        return c1.after(c2);
    }

    public static boolean time1AfterTime2(long t1,long t2) {
        return time1AfterTime2(t1,t2,0);
    }

    public static int getSecondsBySub(long ms1,long ms2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTimeInMillis(ms1);
        c2.setTimeInMillis(ms2);
        int h1 = c1.get(Calendar.HOUR_OF_DAY);
        int m1 = c1.get(Calendar.MINUTE);
        int s1 = c1.get(Calendar.SECOND);
        int hms1 = h1*60*60+m1*60+s1;

        int h2 = c2.get(Calendar.HOUR_OF_DAY);
        int m2 = c2.get(Calendar.MINUTE);
        int s2 = c2.get(Calendar.SECOND);
        int hms2 = h2*60*60+m2*60+s2;
        int res = Math.abs(hms1-hms2);
        return res;
    }

    public static int getMinutesBySub(long ms1,long ms2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTimeInMillis(ms1);
        c2.setTimeInMillis(ms2);
        int d1 = c1.get(Calendar.DAY_OF_MONTH);
        int h1 = c1.get(Calendar.HOUR_OF_DAY);
        int m1 = c1.get(Calendar.MINUTE);
        int dhm1 = d1*24*60+h1*60+m1;

        int d2 = c2.get(Calendar.DAY_OF_MONTH);
        int h2 = c2.get(Calendar.HOUR_OF_DAY);
        int m2 = c2.get(Calendar.MINUTE);
        int dhm2 = d2*24*60+h2*60+m2;
        int res = Math.abs(dhm1-dhm2);
        return res;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(getCurrYMD());
        long ct1 = System.currentTimeMillis();
        long ct2 = ct1 + 3000;
        int r1 = getSecondsBySub(ct1,ct2);
        Thread.sleep(3*1000);
        long ct3 = System.currentTimeMillis();
        System.out.printf("r1=%d,ct1=%d,ct2=%d,ct3=%d",r1,ct1,ct2,ct3);
    }

}
