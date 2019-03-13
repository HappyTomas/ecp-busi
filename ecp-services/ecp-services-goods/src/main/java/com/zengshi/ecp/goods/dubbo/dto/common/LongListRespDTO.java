package com.zengshi.ecp.goods.dubbo.dto.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class LongListRespDTO extends BaseResponseDTO{
	
	/** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
	private static final long serialVersionUID = -4925059427962832771L;
	private List<Long> lst;

	public List<Long> getLst() {
		return lst;
	}

	public void setLst(List<Long> lst) {
		this.lst = lst;
	}
	
	public void addElement(Long e){
    	if(null != e){
    		if(null == lst){
    			lst = new ArrayList<Long>();
        	}
    		if(!lst.contains(e)){
    			lst.add(e);
    		}
    	}
    }
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}

