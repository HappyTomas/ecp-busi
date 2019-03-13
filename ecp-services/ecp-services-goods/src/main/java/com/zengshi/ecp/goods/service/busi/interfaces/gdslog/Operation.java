package com.zengshi.ecp.goods.service.busi.interfaces.gdslog;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: 日志记录注解<font color='red'>目前该注解仅支持有一个入参的方法</font><br>
 * Date:2016-3-26下午4:29:11  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Operation {
	
	/**
	 * 
	 * model:模块名称. <br/> 
	 * 
	 * @author liyong7
	 * @return 
	 * @since JDK 1.6
	 */
	public String model() default "";
	/**
	 * 
	 * operType:操作类型. <br/> 
	 * 
	 * @author liyong7
	 * @return 
	 * @since JDK 1.6
	 */
	public OperationType operType() default OperationType.NULL;
    /**
     * 
     * calOperTypeBeanId:日志类型计算服务枚举(需要事先注册到上下文文件中),默认为空. <br/> 
     * 如果未设置operType，该值一定要设置，否则会抛出空指针异常。
     * @author liyong7
     * @return 
     * @since JDK 1.6
     */
    public ServiceEnum service() default ServiceEnum.NULL_SV;
    /**
     * 
     * recordArgs:是否记录入参. <br/> 
     * 
     * @author liyong7
     * @return 
     * @since JDK 1.6
     */
    public boolean recordArgs() default true;
    /**
     * 
     * recordFields:日志记录字段. <br/> 
     * 
     * @author liyong7
     * @return 
     * @since JDK 1.6
     */
    public String[] recordFields() default {};
	
}
