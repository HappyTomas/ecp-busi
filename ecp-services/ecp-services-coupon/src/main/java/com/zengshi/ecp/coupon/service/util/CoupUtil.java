package com.zengshi.ecp.coupon.service.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.DateUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-coupon-server <br>
 * Description: <br>
 * Date:2015-10-26下午3:52:50 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huanghe5
 * @version
 * @since JDK 1.7
 */

@Service("coupUtil")
public class CoupUtil {
	
	private static final String base = "A0BC2D3E36FG5I7J8KLMN4PSW9X87YZ";

	private static final String figure1 = "34123445465765768798790";
	
	
	/**
	 * 
	 * isNotLong:判断Long类型字段是否为空. <br/>
	 * 
	 * @author huanghe5
	 * @param n
	 * @return
	 * @since JDK 1.7
	 */
	public static boolean isCoupNotLong(Long n) {

		if (n != null) {
			if (n > -2 && n != 0) {
				return true;
			} else {
				return false;
			}
		}
		return false;

	}

	//生成随机数字和字母,  
    private String getStringRandom(int length) {
          
        String val = "";  
        Random random = new Random();  
          
        //参数length，表示生成几位随机数  
        for(int i = 0; i < length; i++) {  
              
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";  
            //输出字母还是数字  
            if( "char".equalsIgnoreCase(charOrNum) ) {  
                //输出是大写字母还是小写字母  
//                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;  
                val += (char)(random.nextInt(26) + 65);  
            } else if( "num".equalsIgnoreCase(charOrNum) ) {  
                val += String.valueOf(random.nextInt(10));  
            }  
        }  
        return val;  
    }
    
    /**
     * 
     * createCoupCode:生产优惠码. <br/> 
     * 
     * @author huanghe5
     * @return 
     * @since JDK 1.7
     */
	public static String createCoupCode(){
		CoupUtil test = new CoupUtil();  
	    
		return test.getStringRandom(10);
		
	}
    
	
	/**
	 * 
	 * createCoupNo:优惠券号码生成. <br/>
	 * 
	 * @author huanghe5
	 * @return
	 * @since JDK 1.7
	 */
	public static String createCoupNo() {
		
		return lpad(12, CacheUtil.getIncrement("6"));
		
	}
	
	private static String getRandomNo(String base, int length) {
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
	/**
	 * 按bean的属性值对list集合进行排序 
	 * @param list 
	 *            要排序的集合 
	 * @param propertyName 
	 *            集合元素的属性名 
	 * @param isAsc 
	 *         排序方向,true--降序(从高到低),false--升序
	 * @return 排序后的集合 
	 * @author huanghe5
	 */
	public static List sortList(List list, String propertyName, boolean isAsc) { 
        //借助commons-collections包的ComparatorUtils    
        //BeanComparator，ComparableComparator和ComparatorChain都是实现了Comparator这个接口    
        Comparator mycmp = ComparableComparator.getInstance();       
        mycmp = ComparatorUtils.nullLowComparator(mycmp);  //允许null 
        if(isAsc){ 
        mycmp = ComparatorUtils.reversedComparator(mycmp); //逆序       
        } 
        Comparator cmp = new BeanComparator(propertyName, mycmp);    
        Collections.sort(list, cmp);   
        return list; 
	}
	/**
     * 补齐不足长度
     * @param length 长度
     * @param number 数字
     * @return
     */
    private static String lpad(int length, long number) {
        String f = "%0" + length + "d";
        return String.format(f, number);
    }
	
	/**
	 * 
	 * createFullTime:创建某时间段的最后一秒. <br/> 
	 * 
	 * @author huanghe5
	 * @param d
	 * @param i
	 * @return 
	 * @since JDK 1.7
	 */
	public static Timestamp createFullTime(Timestamp d, int i) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		//比如浮动天数是2天  那就是 明天的 23点:59分:59秒,1天就是今天的23点:59分:59秒
		String s1 = df.format(DateUtil.getOffsetDaysDate(d, i-1));
		String s2 = s1 + " 23:59:59";
		ts = Timestamp.valueOf(s2);
		return ts;
	}

	/**
	 * 
	 * @author huanghe5
	 * @param ts
	 * @return 
	 * @since JDK 1.7
	 */
	public static Date timestampToDate(Timestamp ts) {
		Date date = new Date();
		date = ts;
		return date;
	}
	
	/**  
     * 计算两个日期之间相差的天数  
     * @author huanghe5
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws ParseException  
     */    
	public static int daysBetween(Date smdate, Date bdate) throws Exception {
		
		if(smdate.getTime()>=bdate.getTime()){
			return 0;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}
	
	
	public static void main(String[] args) {

		// System.out.println(createFullTime(DateUtil.getSysDate(),0));
		// lpad(12, CacheUtil.getIncrement("6"));
		// Test test = new Test();
		// System.out.println(lpad(10, 22));
		// String s="1";
		// CacheUtil.addItem(s, 1l);
		// for(int i=0;i<30;i++){
		// System.out.print(CacheUtil.getIncrement("5")+",");
		// }
		// System.out.println(createCoupNo());
		// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		// SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// String s1 =
		// df.format(DateUtil.getOffsetDaysDate(DateUtil.getSysDate(),
		// 40));
		// String s2 = s1 + " 23:59:59";
		//
		// try {
		// Date date = df2.parse(s2);
		// } catch (ParseException e) {
		// e.printStackTrace();
		// }
		// System.out.println(s2);
		// createCoupNo();
		//
		// Date a =DateUtil.getSysDate();
		// boolean s= a.after(a);
		// System.out.println(s);

		// for (int i=0; i<=100;i++) {
		// if(i>3){
		// break;
		// }
		// System.out.println("11111111");
		//
		// }

		// System.out.println(createFullTime(DateUtil.getSysDate(),40));

		// BigDecimal b1 = new BigDecimal(Double.toString(v1));
		// BigDecimal b2 = new BigDecimal(Double.toString(v2));
		//
		// b1.divid(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();

		// System.out.println(disCountPrice);
		// System.out.println(b);
	}
}
