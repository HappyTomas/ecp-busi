package com.zengshi.ecp.search.index.ext;

import java.util.List;
import java.util.Map;

import com.zengshi.ecp.search.dubbo.dto.SecConfigRespDTO;
import org.apache.commons.lang.StringUtils;

import com.zengshi.ecp.search.dubbo.dto.SecFieldRespDTO;
import com.zengshi.ecp.search.dubbo.dto.SecObjectRespDTO;
import com.zengshi.ecp.search.index.IndexException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-search <br>
 * Description: 服务调用异常统一抛出：BusinessException，扩展本身处理异常统一抛出IndexException<br>
 * Date:2015年8月12日上午11:24:17 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version @param <T>
 * @since JDK 1.6
 */
public abstract class FieldProcessor<T> {

    public abstract T process(SecConfigRespDTO secConfigRespDTO,SecObjectRespDTO secObjectRespDTO, SecFieldRespDTO curSecFieldRespDTO,
                              List<SecFieldRespDTO> secFieldRespDTOList, Map<String, Object> fieldValueMap) throws Exception;
    
    protected void nullCheck(Object value,String fieldNameCn) throws Exception{
        if(StringUtils.isBlank(String.valueOf(value))){
            throw new IndexException("索引数据源中【"+fieldNameCn+"】参数缺失！");
        }
    }
    
    protected Long parseLong(Object value,String fieldNameCn) throws Exception{
        Long longValue;
        try{
            longValue=(Long)value;
        }catch(Exception e){
            throw new IndexException("索引数据源中【"+fieldNameCn+"】不是Long类型！");
        }
        return longValue;
    }
    
    protected Integer parseInteger(Object value,String fieldNameCn) throws Exception{
        Integer integerValue;
        try{
            integerValue=(Integer)value;
        }catch(Exception e){
            throw new IndexException("索引数据源中【"+fieldNameCn+"】不是Integer类型！");
        }
        return integerValue;
    }
    
    protected void nullResultCheck(Object value,String resultObjNameCn) throws Exception{
        if(value==null){
            throw new IndexException("根据索引数据源参数查询【"+resultObjNameCn+"】结果为空！");
        }
    }

}
