package com.zengshi.ecp.goods.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;

@SuppressWarnings("rawtypes")
public class GdsInfoShipGroupByCatgDTO extends BaseInfo{

    private static final long serialVersionUID = -1L;

	//顶级分类
	private String catgCode;
	
	private Long count ;

    public String getCatgCode() {
        return catgCode;
    }

    public void setCatgCode(String catgCode) {
        this.catgCode = catgCode;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
	
}
