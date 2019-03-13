package com.zengshi.ecp.goods.service.busi.interfaces.gdslog;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.zengshi.ecp.frame.context.EcpFrameContextHolder;
import com.zengshi.ecp.goods.dubbo.dto.gdslog.GdsLogReqDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.alibaba.fastjson.JSONObject;

@Component
@Aspect
public class OperationLogAspect{
	
	private static final String SPLIT_DOT = "\\.";
       
    private final String MODULE = getClass().getName();
    
    @Resource(name="gdsLogSV")
    private IGdsLogSV gdsLogSV;
     
    @Pointcut("@annotation(com.zengshi.ecp.goods.service.busi.interfaces.gdslog.Operation)")
    public void operationMethod() {}
 
    @Around("operationMethod()")
    public Object doBasicProfiling(ProceedingJoinPoint jp)throws Throwable{
		GdsLogReqDTO logReqDTO = new GdsLogReqDTO();

		// 获取签名
		MethodSignature signature = (MethodSignature) jp.getSignature();

		Object target = jp.getTarget();
		// 获取目标对象方法.
		Method method = target.getClass().getMethod(signature.getName(),
				signature.getParameterTypes());
		// 获取日志注解对象.
		Operation annotation = method.getAnnotation(Operation.class);
		// 解析参数
		Object[] objParam = jp.getArgs();
		Object result = null;
		// 记录时间
		Date beginTime = Calendar.getInstance().getTime();
		try {
			result = jp.proceed();
			logReqDTO.setOperResult((short) 1);
		} catch (Exception e) {
			logReqDTO.setOperResult((short) 0);
			if(e instanceof BusinessException){
			   BusinessException be = (BusinessException) e;
			   LogUtil.error(MODULE, "方法执行异常 ![Err_Code:"+be.getErrorCode()+"],[Err_Msg:"+be.getErrorMessage()+"]", e);
			   logReqDTO.setErrMsg("[Err_Code:"+be.getErrorCode()+"],[Err_Msg:"+be.getErrorMessage()+"]");
			}else{
				LogUtil.error(MODULE, "方法执行异常 !", e);
				logReqDTO.setErrMsg(e.getMessage());
			}
			throw e;
		}finally{
			try{
				Date endTime = Calendar.getInstance().getTime();
				// 设置起始时间
				logReqDTO.setStartTime(new Timestamp(beginTime.getTime()));
				logReqDTO.setEndTime(new Timestamp(endTime.getTime()));
				logReqDTO.setModuleName(annotation.model());
				OperationType opeType = annotation.operType();

				if (OperationType.NULL.equals(opeType)) {
					ServiceEnum svEnum = annotation.service();
					if(!svEnum.equals(ServiceEnum.NULL_SV)){
						ICalOperationTypeSV opTypeCalSV = (ICalOperationTypeSV) EcpFrameContextHolder
								.getBean(svEnum.getBeanId());
						opeType = opTypeCalSV.getOperationType(objParam[0]);
					}
				}
				logReqDTO.setOperType(opeType.getName());

				// 抽取出方法描述：
				if (annotation.recordArgs()) {
					Map<String, Object> map = extractParam(ArrayUtils.isNotEmpty(objParam) ? objParam[0] : null, annotation);
					if(OperationType.GDS_ADD.equals(opeType) || OperationType.SKU_ADD.equals(opeType)){
						map.put("id", result);
					}
					if(null != map){
						logReqDTO.setOperParam(JSONObject.toJSONString(map));
					}
					
				}
				gdsLogSV.addGdsLog(logReqDTO);
			}catch (Exception e) {
				LogUtil.error(MODULE, "操作日志记录保存异常!logReqDTO="+ToStringBuilder.reflectionToString(logReqDTO),e);
			}
			
			
		}
		return result;
    }
     
     
    /**
     * 根据注解参数以及方法实参拼接出方法描述
     * @param objParam
     * @param arguDesc
     * @return
     */
    private Map<String,Object> extractParam(Object objParam, Operation operation ) throws Exception{
       Map<String, Object> map = null;
       if(null != objParam){
    	  String[] fields = operation.recordFields();
    	  if(ArrayUtils.isNotEmpty(fields)){
    		  map = new HashMap<String, Object>();
    		  for(String field : fields){
    			  Object obj = getParamValue(field, objParam);
    			  map.put(field, obj);
    		  }
    	  }
       }
       return map;
    }
 
    private Object getParamValue(String fieldName,Object objParam) throws Exception{
    	Object obj = objParam;
    	// 获取属性名
    	String[] pns = fieldName.split(SPLIT_DOT);
    	for(int i = 0; i < pns.length; ++ i){
    		if(null == obj){
    			break;
    		}
			PropertyDescriptor pd = new PropertyDescriptor(pns[i],obj.getClass());
			Method getMethod = pd.getReadMethod();
			obj = getMethod.invoke(obj);
    	}
    	return obj;
    }
    
  
}
