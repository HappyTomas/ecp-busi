package com.zengshi.ecp.staff.service.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.frame.context.EcpFrameContextHolder;
import com.zengshi.ecp.general.solr.message.DeltaMessage;
import com.zengshi.ecp.general.solr.util.DeltaUtils;
import com.zengshi.ecp.general.solr.util.DeltaUtils.EDeltaType;
import com.zengshi.ecp.general.solr.util.DeltaUtils.EOperType;
import com.zengshi.ecp.search.dubbo.interfaces.IDeltaTransactionMessageRSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseAreaAdminUtil;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.log.interfaces.ILogInfoSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;


/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: <br>
 * Date:2015年8月6日下午5:07:18 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author PJieWin
 * @version
 * @since JDK 1.6
 */
@Service("staffTools")
public class StaffTools {

    private static ILogInfoSV logInfoSV;

    @Resource(name = "logInfoSV")
    public void setLogInfoSV(ILogInfoSV logInfoSV) {
        StaffTools.logInfoSV = logInfoSV;
    }

    /**
     * 
     * paramNullCheck:简易参数空检测. <br/> 
     * 
     * @author liyong7
     * @param obj 需要NULL检查的对象.
     * @param chkStaff 是否需要检查用户.
     * @param msg 错误信息占位符.如果遇到异常抛出消息格式为:必传参数{0}缺失!
     * @since JDK 1.6
     */
    public void paramNullCheck(Object obj,String... msg) {
        if(null == obj
                ||(obj instanceof String && StringUtil.isBlank((String)obj))){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR,msg != null ? msg : new String[]{"reqDTO"});
        }
    }
    
    /**
     * 
     * 必传参数检测，仅对普通参数进行判空处理，抛出异常信息为:必传参数{0}缺失!<br/>
     * 其中两个参数的params与msgs的数组长度要一致。
     * 
     * <pre>
     * paramCheck(new Object[] { arg1, arg2 }, new String[] { &quot;arg1&quot;, &quot;arg2&quot; });
     * </pre>
     * 
     * @author liyong7
     * @param params
     * @param msgs
     * @since JDK 1.6
     */
    public void paramCheck(Object[] params, String[] msgs) {
        if (null != params && null != msgs && params.length == msgs.length) {
            StringBuffer errorMsg = new StringBuffer();
            for (int i = 0; i < params.length; ++i) {
                Object obj = params[i];
                if(obj instanceof String){
                    if(StringUtil.isBlank((String)obj)){
                        errorMsg.append(msgs[i]);
                        errorMsg.append(",");
                    }
                }else if(obj instanceof Object[]){
                    if(obj == null || ((Object[])obj).length == 0){
                        errorMsg.append(msgs[i]);
                        errorMsg.append(",");
                    }
                }else if(obj instanceof Collection<?>){
                    if(obj == null || CollectionUtils.isEmpty((Collection<?>)obj)){
                        errorMsg.append(msgs[i]);
                        errorMsg.append(",");
                    }
                }else{
                    if(null == obj){
                        errorMsg.append(msgs[i]);
                        errorMsg.append(",");
                    }
                }
            }
            if (0 < errorMsg.length()) {
                errorMsg.deleteCharAt(errorMsg.length() - 1);
                throw new BusinessException(StaffConstants.STAFF_NULL_ERROR,
                        new String[] { errorMsg.toString() });
            }
        } else {
            throw new BusinessException(StaffConstants.STAFF_EXECUTE_ERROR, new String[] { Thread
                    .currentThread().getStackTrace()[1].getMethodName() });
        }
    }

    /**
     * 
     * coverBean2Bean:(将from对象的属性值赋值给to对象. ). <br/>
     * TODO(两个对象属性相同，但对象不同：如DAO层与dubbo层对象).<br/>
     * 
     * @author PJieWin
     * @param from
     * @param to
     * @return
     * @since JDK 1.6
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Object coverBean2Bean(Object from, Object to) {
        Class fClass = from.getClass();
        Class tClass = to.getClass();
        // 拿to所有属性（如果有继承，父类属性拿不到）
        Field[] cFields = tClass.getDeclaredFields();
        try {
            for (Field field : cFields) {
                String cKey = field.getName();
                // 确定第一个字母大写
                cKey = cKey.substring(0, 1).toUpperCase() + cKey.substring(1);

                Method fMethod;
                Object fValue;
                try {
                    fMethod = fClass.getMethod("get" + cKey);// public方法
                    fValue = fMethod.invoke(from);// 取getfKey的值
                } catch (Exception e) {
                    // 如果from没有此属性的get方法，跳过
                    continue;
                }

                try {
                    // 用fMethod.getReturnType()，而不用fValue.getClass()
                    // 为了保证get方法时，参数类型是基本类型而传入对象时会找不到方法
                    Method cMethod = tClass.getMethod("set" + cKey, fMethod.getReturnType());
                    cMethod.invoke(to, fValue);
                } catch (Exception e) {
                    // 如果to没有此属性set方法，跳过
                    continue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return to;
    }

    /**
     * 
     * convertBean2Bean:(将from对象的属性值赋值给to对象. <br/>
     * TODO(两个对象属性相同，但对象不同：如DAO层与dubbo层对象).<br/>
     * 
     * @author PJieWin
     * @param from
     * @param to
     * @return
     * @since JDK 1.6
     * 
     */
    public static Object convertBean2Bean(Object from, Object to) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(to.getClass());
            PropertyDescriptor[] ps = beanInfo.getPropertyDescriptors();

            for (PropertyDescriptor p : ps) {
                Method getMethod = p.getReadMethod();
                Method setMethod = p.getWriteMethod();
                if (getMethod != null && setMethod != null) {
                    try {
                        Object result = getMethod.invoke(from);
                        setMethod.invoke(to, result);
                    } catch (Exception e) {
                        // 如果from没有此属性的get方法，跳过
                        continue;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return to;
    }

    /**
     * 
     * 时间转换：添加天数
     * 
     * @author wangbh
     * @param date
     * @param to
     * @return
     * @since JDK 1.7
     * 
     */
    public static Timestamp getDateTime(Timestamp date, int day) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(Calendar.DATE, day);
        Timestamp time1 = new Timestamp(rightNow.getTime().getTime());
        return time1;
    }

    /**
     * 
     * check:(验证是否符合正则表达式). <br/>
     * 
     * @author PJieWin
     * @param str
     * @param regex
     * @return
     * @since JDK 1.6
     */
    public static boolean checkRegx(String str, String regex) {
        boolean flag = false;
        try {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(str);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public static String createArea(String country, String province, String city, String county) {
        String area = "";
        if (!StringUtil.isBlank(country)) {
            country = BaseAreaAdminUtil.fetchAreaName(country);
            area = country + "-";
        }
        if (!StringUtil.isBlank(province)) {
            province = BaseAreaAdminUtil.fetchAreaName(province);
            area = area + province + "-";
        }
        if (!StringUtil.isBlank(city)) {
            city = BaseAreaAdminUtil.fetchAreaName(city);
            area = area + city + "-";
        }
        if (!StringUtil.isBlank(county)) {
            county = BaseAreaAdminUtil.fetchAreaName(county);
            area = area + county + "-";
        }
        if (!StringUtil.isBlank(area)) {
            area = area.substring(0, area.length() - 1);
        }
        return area;
    }

    public static void logInfo(String methodName, Object arg) throws BusinessException {
        ILogInfoSV c1 = null;
        try {
            c1 = EcpFrameContextHolder.getBean("logInfoSV",ILogInfoSV.class);
            if(null!=c1){
                Method m1 = c1.getClass().getMethod(methodName, new Class[]{arg.getClass()});
                m1.invoke(c1, arg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void solrUpdate(String tableName,Long id) {
    	IDeltaTransactionMessageRSV deltaTransactionMessageRSV = EcpFrameContextHolder.getBean("deltaTransactionMessageRSV",IDeltaTransactionMessageRSV.class);
    	DeltaMessage msg=new DeltaMessage();
        msg.setDeltaType_(EDeltaType.DBOBJECT);
        try {
            msg.setOperType_(EOperType.UPDATE);
            msg.setOperType(EOperType.UPDATE.getType());
            List<String> ids=new ArrayList<String>();
            ids.add(id + "");
            msg.setIds(ids);
            msg.setObjectNameEn(tableName);
            deltaTransactionMessageRSV.sendDeltaMessage(msg);
            LogUtil.info("staff", "Send gdsInfo index create/update Message,Id is" + id);
        } catch (Exception e) {
            LogUtil.error(StaffTools.class.getName(), "GdsInfo index create/update failed! please check you solr server Or check you MQ server!", e);
        }

    }
    
    public static void solrDelete(String tableName,Long id) {
    	IDeltaTransactionMessageRSV deltaTransactionMessageRSV = EcpFrameContextHolder.getBean("deltaTransactionMessageRSV",IDeltaTransactionMessageRSV.class);
    	DeltaMessage msg=new DeltaMessage();
        msg.setDeltaType_(EDeltaType.DBOBJECT);
        try {
            msg.setOperType_(EOperType.DELETE);
            msg.setOperType(EOperType.DELETE.getType());
            List<String> ids=new ArrayList<String>();
            ids.add(id + "");
            msg.setIds(ids);
            msg.setObjectNameEn(tableName);
            deltaTransactionMessageRSV.sendDeltaMessage(msg);
            LogUtil.info("staff", "Send shopInfo index delete Message,Id is" + id);
        } catch (Exception e) {
            LogUtil.error(StaffTools.class.getName(), "shopInfo index delete failed! please check you solr server Or check you MQ server!", e);
        }

    }
}
