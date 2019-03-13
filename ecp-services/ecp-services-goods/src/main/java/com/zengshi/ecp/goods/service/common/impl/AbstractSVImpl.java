/** 
 * Project Name:ecp-services-prom 
 * File Name:AbstractSVImpl.java 
 * Package Name:com.zengshi.ecp.prom.service.common.impl 
 * Date:2015年8月11日上午10:41:52 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */
package com.zengshi.ecp.goods.service.common.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.dto.BaseStaff;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.FileUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: 服务抽象实现类. <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月11日上午10:41:52 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version
 * @since JDK 1.6
 */
public abstract class AbstractSVImpl extends GeneralSQLSVImpl implements IGeneralSQLSV {

    protected String MODULE = getClass().getName();

    public static Timestamp now() {
        return DateUtil.getSysDate();
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

    /**
     * 
     * 执行对像序列到目标对象序列的转换.
     * 
     * @author liyong7
     * @param sources
     * @param clazz
     * @return
     * @since JDK 1.6
     */
    protected <SOURCE, TARGET> List<TARGET> doConvert(List<SOURCE> sources, Class<TARGET> clazz) {
        if (!CollectionUtils.isEmpty(sources)) {
            List<TARGET> result = new ArrayList<>();
            try {
                for (SOURCE source : sources) {
                    TARGET t = clazz.newInstance();
                    ObjectCopyUtil.copyObjValue(source, t, null, false);
                    result.add(t);
                }
                return result;
            } catch (Exception e) {
                LogUtil.error(MODULE, "对象转换异常!", e);
                throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200099,
                        new String[] { Thread.currentThread().getStackTrace()[1].getMethodName() });
            }

        }
        return null;
    }

    /**
     * 
     * preInsert:对象持久化前预设值.从DTO中提取出用户信息,对创建人与更新人以及创建时间与更新时间进行设值. <br/>
     * 
     * @author liyong7
     * @param dto
     *            dto请求对象.
     * @param record
     *            数据记录.
     * @throws BusinessException
     * @since JDK 1.6
     */
    protected <DTO extends BaseInfo, RECORD> void preInsert(DTO dto, RECORD record)
            throws BusinessException {
        try {
            Timestamp now = now();
            Method method = null;
            Object obj = null;
            try{
            method = dto.getClass().getMethod("getCreateStaff");
            obj = method.invoke(dto);
            }catch (Exception e) {
				// TODO: handle exception
			}
            
            if(null == obj){
            	method = dto.getClass().getMethod("getStaff");
                obj = method.invoke(dto);
                if (obj != null) {
                    BaseStaff staff = (BaseStaff) obj;
                    method = record.getClass().getMethod("setCreateStaff", Long.class);
                    method.invoke(record, staff.getId());
                    method = record.getClass().getMethod("setUpdateStaff", Long.class);
                    method.invoke(record, staff.getId());
                }
            }else{
                method = record.getClass().getMethod("setCreateStaff", Long.class);
                method.invoke(record, obj);
                method = record.getClass().getMethod("setUpdateStaff", Long.class);
                method.invoke(record, obj);
            }
            
            method = record.getClass().getMethod("setCreateTime", Timestamp.class);
            method.invoke(record, now);
            method = record.getClass().getMethod("setUpdateTime", Timestamp.class);
            method.invoke(record, now);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException e) {
            LogUtil.error(MODULE, "设置状态条件异常!", e);
            throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200099,
                    new String[] { Thread.currentThread().getStackTrace()[1].getMethodName() });
        }
    }

    /**
     * 
     * preInsert:从DTO中提取出用户信息,设置更新时间与更新人信息.
     * 
     * @author liyong7
     * @param dto
     * @param record
     * @throws BusinessException
     * @since JDK 1.6
     */
    protected <DTO extends BaseInfo, RECORD> void preUpdate(DTO dto, RECORD record)
            throws BusinessException {
        try {
            Timestamp now = now();
            Method method = null;
            Object obj = null;
            try{
               method = dto.getClass().getMethod("getUpdateStaff");
               obj = method.invoke(dto);
            }catch (Exception e) {
            	
			}
                        
            if(null == obj){
            	method = dto.getClass().getMethod("getStaff");
                obj = method.invoke(dto);
                if (obj != null) {
                    BaseStaff staff = (BaseStaff) obj;
                    method = record.getClass().getMethod("setUpdateStaff", Long.class);
                    method.invoke(record, staff.getId());
                }
            }else{
                method = record.getClass().getMethod("setUpdateStaff", Long.class);
                method.invoke(record, obj);
            }
            method = record.getClass().getMethod("setUpdateTime", Timestamp.class);
            method.invoke(record, now);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException e) {
            LogUtil.error(MODULE, "设置状态条件异常!", e);
            throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200099,
                    new String[] { Thread.currentThread().getStackTrace()[1].getMethodName() });
        }
    }

    /**
     * 
     * 初始化状态查询条件。 <br/>
     * 
     * @author liyong7
     * @param c
     * @param status
     * @since JDK 1.6
     */
    protected <T> void initStatusCriteria(T t, String... status) throws BusinessException {
        if (null != status && status.length > 0) {
            try {
                Method method = null;
                if (1 == status.length) {
                    method = t.getClass().getMethod("andStatusEqualTo", String.class);
                    method.invoke(t, status[0]);
                } else {
                    method = t.getClass().getMethod("andStatusIn", List.class);
                    method.invoke(t, Arrays.asList(status));
                }
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException
                    | IllegalArgumentException | InvocationTargetException e) {
                LogUtil.error(MODULE, "设置状态条件异常!", e);
                throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200099,
                        new String[] { Thread.currentThread().getStackTrace()[1].getMethodName() });
            }
        }
    }

    /**
     * 
     * 根据文件数据库返回的ID获取内容.
     * 
     * @author liyong7
     * @param fileId
     *            文件数据库提供的记录ID.
     * @param errorCode
     *            错误异常码,必传.
     * @return
     * @since JDK 1.6
     */
    protected String fetchTextContent(String fileId, String errorCode) {
        if (StringUtils.hasText(fileId)) {
            try {
                return FileUtil.readFile2Text(fileId, "UTF-8");
            } catch (Exception e) {
                LogUtil.error(MODULE, "从文件数据库获取内容失败!", e);
                throw new BusinessException(errorCode);
            }
        }
        return null;
    }

    /**
     * 
     * 保存信息至MongoDB.<br/>
     * 
     * @author liyong7
     * @param content
     * @param fileName
     * @param fileType
     * @param errorCode
     * @return
     * @since JDK 1.6
     */
    protected String saveTextContentToDB(String content, String fileName, String fileType,
            String errorCode) {
        if (StringUtils.hasText(content)) {
            try {
                return FileUtil.saveFile(content.getBytes("UTF-8"), fileName, fileType);
            } catch (Exception e) {
                LogUtil.error(MODULE, "保存评价内容至文件服务器遇到异常!", e);
                throw new BusinessException(errorCode);
            }
        }
        return null;
    }

    /**
     * 
     * 更新指定文本内容.<br/>
     * 
     * @author liyong7
     * @param content
     * @param fileName
     * @param fileType
     * @param errorCode
     * @return
     * @since JDK 1.6
     */
    protected String updateTextContentToDB(String fileId, String content, String fileName,
            String fileType, String errorCode) {
        if (StringUtils.hasText(fileId) && StringUtils.hasText(content)) {
            try {
                return FileUtil.updateFile(content.getBytes("UTF-8"), fileId, fileName, fileType);
            } catch (Exception e) {
                LogUtil.error(MODULE, "保存评价内容至文件服务器遇到异常!", e);
                throw new BusinessException(errorCode);
            }
        }
        return null;
    }

}
