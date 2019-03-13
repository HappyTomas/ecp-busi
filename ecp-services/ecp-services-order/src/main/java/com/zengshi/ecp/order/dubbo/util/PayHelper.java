package com.zengshi.ecp.order.dubbo.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.NullArgumentException;
import org.apache.commons.lang.StringUtils;

import com.zengshi.ecp.order.dubbo.dto.pay.PayParamVO;
import com.zengshi.ecp.order.dubbo.util.MsgConstants.PayInputMsgCode;
import com.zengshi.ecp.order.dubbo.util.OrdConstants.Order;
import com.zengshi.ecp.order.dubbo.util.OrdConstants.PayAudit;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.FileUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-order-server <br>
 * Description: 支付帮助类<br>
 * Date:2015年10月20日上午10:32:26  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author weijq
 * @version  
 * @since JDK 1.6
 */
public class PayHelper {
	public static final String module = PayHelper.class.getName();
	

	/**
	 * 仅用于发起支付前验证订单状态
	 * @param params
	 * @throws Exception
	 */
	public static void validatePayParams(PayParamVO params)throws Exception{
	    if(params==null){
	        throw new BusinessException(PayInputMsgCode.PAY_INPUT_300000);
	    }
		long payment = params.getPayment();
		if(0 == payment){
			throw new BusinessException(PayInputMsgCode.PAY_INPUT_300005);
		}
		if(Order.ORDER_PAY_FLAG_1.equals(params.getPayFlag())){
			throw new BusinessException(PayInputMsgCode.PAY_INPUT_300006);
		}
	}
	public static String md5(String source)
	{

		LogUtil.info(module,"<======inputStr："+source);
		
		StringBuffer sb = new StringBuffer(32);

		try
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] array = md.digest(source.getBytes("utf-8"));

			for (int i = 0; i < array.length; i++)
			{
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).toUpperCase().substring(1, 3));
			}
		} catch (Exception e)
		{

			return null;
		}

		LogUtil.info(module,"<=====md5 utf-8加密为："+sb.toString().toLowerCase());
		return sb.toString().toLowerCase();
	}
	
	public static String md5(String source,String encoding)
	{

		LogUtil.info(module,"<======inputStr："+source);
		
		StringBuffer sb = new StringBuffer(32);

		try
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] array = md.digest(source.getBytes(encoding));

			for (int i = 0; i < array.length; i++)
			{
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).toUpperCase().substring(1, 3));
			}
		} catch (Exception e)
		{

			return null;
		}

		LogUtil.error(module,"<=====md5 "+encoding+"加密为："+sb.toString().toLowerCase());
		return sb.toString().toLowerCase();
	}
	
	public static String to12(String str){
		if(StringUtil.isBlank(str)){
			throw new BusinessException(PayInputMsgCode.PAY_INPUT_300000);
		}
		String temp = "";
		if (str.length() < 12){
			for (int i = 0; i < 12 - str.length(); i++){
				temp += "0";
			}
			return temp + str;
		} else{
			return str;
		}
	}
	public static String to12(long value){
		return to12(value+"");
	}
	
	
	public static String arrayToStr(String array[]){
		StringBuffer sb = new StringBuffer(128);
		if(array!=null && array.length>0){
			for(int i=0;i<array.length;++i){
				sb.append(array[i]).append(",");
			}
		}
		if(sb.length()>0){
			return "["+sb.substring(0, sb.length()-1)+"]";
		}
		return "[]";
	}
	

    
    /**
     * 下载并返回本地文件路径
     * @param fileId
     * @return local file path
     */
    public static String downloadFileAndGetLocalPath(String fileId){
    	if(StringUtil.isBlank(fileId)){
    		throw new BusinessException(PayInputMsgCode.PAY_INPUT_300011);
    	}
    	String localFileName= buildLocalPath(fileId);
    	FileUtil.readFile(fileId, localFileName);//下载
    	return localFileName;
    }
    /**
     * 构造本地路径
     * @param fileId
     * @return
     */
    public static String buildLocalPath(String fileId){
    	String localPath = null;
    	String tmpdir = System.getProperty("java.io.tmpdir") ;
    	if(tmpdir == null || tmpdir.equals("")){
    		//throw new BusinessException(Special.PARAM_IS_NULL, "JAVA临时目录为空");
    		tmpdir = "."+File.separator;
    	}
    	
    	localPath = tmpdir+File.separator+fileId;
//    	localPath = tmpdir+fileId;
    	LogUtil.info(module,"ret="+localPath);
    	return localPath;//取java临时目录
    }
    
    /**
     * 将值编码为UTF-8
     * @param value
     * @return
     * @throws Exception
     */
    public static String encodeByUtf8(String value)throws Exception{
    	return URLEncoder.encode(value, "utf-8");
    }
    
    /**
     * 将值解码为UTF-8
     * @param value
     * @return
     * @throws Exception
     */
    public static String decodeByUtf8(String value)throws Exception{
    	return URLDecoder.decode(value, "utf-8");
    }
    
    
    
    
    public static  String nvl2String(String str){
    	if(str == null){
    		return "";
    	}
    	return str;
    }
    
    public static String toJsonStr(Map<String,String> map){
    	String result  = null;
    	if( map!= null){
    	   result = JSONObject.fromObject(map).toString();
    	}
    	return result ;
    }
	
    /**
     * 
     * yuan2Fen:元转分. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param amount
     * @return 
     * @since JDK 1.6
     */
	public static Long yuan2Fen(String amount){
		BigDecimal b = new BigDecimal(amount);
		long l = b.multiply(new BigDecimal(100)).longValue();
		return l;
		
	}
	
	/**
     * 元转分
     * @param amount
     * @return
     */
    public static double fen2Yuan(String amount){
        BigDecimal b = new BigDecimal(amount);
        double d= b.divide(new BigDecimal(100)).doubleValue();
        return d;
        
    }

	public static String getExceptionStackTrace(Throwable e){
	    StringBuffer sb = new StringBuffer();
	    sb.append("***********Exception Start*****************************\n");
	    String expMessage = null;
	    if(e!=null){
	        ByteArrayOutputStream buf = new ByteArrayOutputStream();
	        e.printStackTrace(new PrintWriter(buf, true));
	        expMessage = buf.toString();
	        sb.append(expMessage);
	    }
	    sb.append("***********Exception End*******************************\n");
	    return sb.toString();
	}
	/**
	 * 
	 * parseQsdateAsTimestamp:根据传入的map获取时间列表，用于对账. <br/> 
	 * TODO(这里描述这个方法适用条件 – 可选).<br/> 
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	 * 
	 * @author weijq
	 * @param extendParams
	 * @return 
	 * @since JDK 1.6
	 */
	public static List<Timestamp> parseQsdateAsTimestamp(Map<String,String> extendParams){
        String KEY_NAME = PayAudit.QsDates;
        return parseAsTimestamp(extendParams, KEY_NAME, "yyyy-MM-dd");
    }
	
	public static List<Timestamp> parseAsTimestamp(Map<String,String> extendParams,String KEY_NAME,String srcDateFomat){
        List<Timestamp> result = new ArrayList<Timestamp>();
        List<Date> dateList = parseAsDate(extendParams, KEY_NAME, srcDateFomat);
        for(Date date:dateList){
            result.add(new Timestamp(date.getTime()));
        }
        return result;
    }
	
	public static List<Date> parseAsDate(Map<String,String> extendParams,String KEY_NAME,String srcDateFomat){
        if(srcDateFomat==null|| srcDateFomat.equals("")){
            throw new NullArgumentException("srcDateFomat");
        }
        if(KEY_NAME==null || KEY_NAME.equals("")){
            throw new NullArgumentException("KEY_NAME");
        }
        SimpleDateFormat formater = null;
        try{
             formater = new SimpleDateFormat(srcDateFomat);
        }catch(Exception e){
            throw new IllegalArgumentException("srcDateFomat:"+srcDateFomat);
        }
        List<Date> qsDateList =  new ArrayList<Date>();
        String qsDates = null;
        if(extendParams!=null && StringUtils.isNotEmpty(qsDates = extendParams.get(KEY_NAME))){
            String[] qsDateArr = qsDates.split(",");
            for(String qsDate:qsDateArr){
                if(StringUtils.isNotBlank(qsDate)){
                    try {
                        Date date = formater.parse(qsDate.trim());
                        if(date!=null){
                            qsDateList.add(date);
                        }
                    } catch (Exception e) {
                        LogUtil.error(module,"日期解析异常:qsDates = "+qsDates,e);
                    }
                }
            }
        }
        return qsDateList;
    }
	
	/**
	 * 
	 * getCurDate_1AsTimestamp_yyyyMMdd:获取前一天的时间,精确到天. <br/> 
	 * TODO(这里描述这个方法适用条件 – 可选).<br/> 
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	 * 
	 * @author weijq
	 * @return 
	 * @since JDK 1.6
	 */
	public static Timestamp getCurDate_1AsTimestamp_yyyyMMdd(){
	    Timestamp time = DateUtil.getTimestamp(DateUtil.getDateString("yyyy-MM-dd"), "yyyy-MM-dd");
	    Calendar calendar=Calendar.getInstance();   
        calendar.setTime(time); 
        calendar.add(Calendar.DATE, -1);
        time = new Timestamp(calendar.getTimeInMillis());
        return time;
	    
	}
}
