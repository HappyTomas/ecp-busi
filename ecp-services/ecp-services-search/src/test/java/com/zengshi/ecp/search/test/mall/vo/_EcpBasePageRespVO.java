package com.zengshi.ecp.search.test.mall.vo;

/** 
 * Project Name:ecpwebcore 
 * File Name:EcpBasePageRespVO.java 
 * Package Name:com.zengshi.ecp.base.vo 
 * Date:2015-8-11下午4:30:39 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.paas.utils.LogUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializeConfig;

/**
 * Title: ECP <br>
 * Project Name:ecpwebcore <br>
 * Description: <br>
 * Date:2015-8-11下午4:30:39  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public class _EcpBasePageRespVO<T> implements Serializable {
    
    private static final String MODULE  = _EcpBasePageRespVO.class.getName();
    
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1643129598829685210L;
    
    private int pageSize;
    private int pageNumber;
    private long totalRow;
    private long totalPage;
    private List<T> list;
    
    
    
    public int getPageSize() {
        return pageSize;
    }



    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }



    public int getPageNumber() {
        return pageNumber;
    }



    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }



    public long getTotalRow() {
        return totalRow;
    }



    public void setTotalRow(long totalRow) {
        this.totalRow = totalRow;
    }



    public long getTotalPage() {
        return totalPage;
    }



    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    
    public List<T> getList() {
        return list;
    }



    public void setList(List<T> list) {
        this.list = list;
    }



    /**
     * 
     * buildByPageResponseDTO:从分页的DTO结果类中生成BasePageRespVO<br/>
     *  
     * @author yugn 
     * @param dto
     * @return 
     * @throws Exception 
     * @since JDK 1.6
     */
    public static _EcpBasePageRespVO<Map> buildByPageResponseDTO(PageResponseDTO<? extends BaseResponseDTO> dto) throws Exception{
        _EcpBasePageRespVO<Map> vo = new _EcpBasePageRespVO<Map>();
        vo.setPageNumber(dto.getPageNo());
        vo.setPageSize(dto.getPageSize());
        vo.setTotalPage(dto.getPageCount());
        vo.setTotalRow(dto.getCount());
        vo.setList(new ArrayList<Map>());
        
        if(dto.getResult() == null || dto.getResult().size() ==0){
            ///如果后场返回的 result 为空，则不处理复制；
        } else {
            try{
                for(BaseResponseDTO bdto:dto.getResult()){
//                    Map map = BeanUtils.describe(bdto);
                    Map map=bean2map(bdto);
                    vo.getList().add(map);
                }
            } catch(Exception err){
                LogUtil.error(MODULE, "从 PageResponseDTO 转为：EcpBasePageRespVO 属性复制异常；",err);
                throw err;
            }
        }
        

        return vo;
    }
    
    public static Map<String, Object> bean2map(BaseResponseDTO source) {

        if (null == source) {

            return null;
        }

        Map<String, Object> target = new HashMap<String, Object>();

        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(source.getClass());

            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();

            for (PropertyDescriptor pd : pds) {
                String key = pd.getName();
                if ("class".equalsIgnoreCase(key)) {
                    continue;
                }
                Method method = pd.getReadMethod();
                Object value = method.invoke(source, new Object[] {});
                target.put(key, value);
            }
        } catch (IntrospectionException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {

            throw new RuntimeException("Bean conversion into map error", e);
        }

        return target;
    }
}



