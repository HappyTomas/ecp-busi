package com.zengshi.ecp.busi.shop.vo;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class ShopSearchPageReqVO extends EcpBasePageReqVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4464461557700603604L;

	/**
     * 排序字段
     */
    private String field;

    /**
     * 排序字段排序
     */
    private String sort;
    
    
    private String keyword;
	
    //好评率
    private String  evalRate;
    //店铺id
    private String id;
    //scroll滚动分页
    private int page;

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getEvalRate() {
		return evalRate;
	}


	public void setEvalRate(String evalRate) {
		this.evalRate = evalRate;
	}


    public int getPage() {
        return page;
    }


    public void setPage(int page) {
        this.page = page;
    }
    public String getField() {
        return field;
    }


    public String getSort() {
        return sort;
    }


    public String getKeyword() {
        return keyword;
    }


    public void setField(String field) {
        this.field = field;
    }


    public void setSort(String sort) {
        this.sort = sort;
    }


    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

}
