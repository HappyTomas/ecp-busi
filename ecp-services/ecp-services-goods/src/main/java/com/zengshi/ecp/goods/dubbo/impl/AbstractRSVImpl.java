/** 
 * Project Name:ecp-services-goods 
 * File Name:AbstractRSVImpl.java 
 * Package Name:com.zengshi.ecp.goods.dubbo.impl 
 * Date:2015年8月27日下午4:09:51 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.dubbo.impl;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;

import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月27日下午4:09:51  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public abstract class AbstractRSVImpl {

    protected String MODULE = getClass().getName();
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
    protected void paramNullCheck(Object obj,boolean chkStaff,String... msg) {
        if(null == obj
                ||(obj instanceof String && StringUtil.isBlank((String)obj))){
            throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100,msg != null ? msg : new String[]{"reqDTO"});
        }
        if(chkStaff && obj instanceof BaseInfo){
            BaseInfo baseInfo = (BaseInfo)obj;
            if(null == baseInfo.getStaff()){
                throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100,new String[]{"reqDTO.staff"});
            }
        }
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
    protected void paramNullCheck(Object obj,String... msg) {
        if(null == obj
                ||(obj instanceof String && StringUtil.isBlank((String)obj))){
            throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100,msg != null ? msg : new String[]{"reqDTO"});
        }
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
    protected void paramNullCheck(Object[] obj,String... msg) {
        if(null == obj || obj.length == 0){
            throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100,msg != null ? msg : new String[]{"reqDTO"});
        }
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
    @SuppressWarnings("rawtypes")
	protected void paramNullCheck(Collection coll,String... msg) {
		if(CollectionUtils.isEmpty(coll)){
            throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100,msg != null ? msg : new String[]{"reqDTO"});
        }
    }
    
    
    /**
     * 
     * 必传参数检测，仅对普通参数进行判空处理，抛出异常信息为:必传参数{0}缺失!<br/>
     * 其中两个参数的params与msgs的数组长度要一致。
     * 
     * @author liyong7
     * @param params
     * @param msgs
     * @since JDK 1.6
     */
    protected void paramCheck(Object[] params, String[] msgs) {
        if (null != params && null != msgs && params.length == msgs.length) {
               StringBuffer errorMsg = new StringBuffer();
               for(int i = 0; i < params.length; ++ i){
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
               if(0 < errorMsg.length()){
                   errorMsg.deleteCharAt(errorMsg.length() - 1);
                   throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100,new String[]{errorMsg.toString()});
               }
        } else {
            LogUtil.error(MODULE, "参数检测方法执行异常！请保证params与msgs不为空，并且两个参数数组长度一致");
            throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200099,
                    new String[] { Thread.currentThread().getStackTrace()[1].getMethodName() });
        }
    }
    
}

