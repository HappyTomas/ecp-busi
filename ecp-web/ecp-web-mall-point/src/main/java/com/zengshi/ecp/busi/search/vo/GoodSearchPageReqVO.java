package com.zengshi.ecp.busi.search.vo;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2015年9月21日上午9:23:24  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public class GoodSearchPageReqVO extends EcpBasePageReqVO {

    /**
     * serialVersionUID:
     */
    private static final long serialVersionUID = 4802843376087735942L;
    
    private String keyword;

    private String category;

    private String gdsTypeId;//商品类型
    
    private String field;

    private String sort;
    
    private String rangeType;//范围查询类型
    /**
     * 广告ID。
     */
    private String adid;
    
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getRangeType() {
        return rangeType;
    }

    public void setRangeType(String rangeType) {
        this.rangeType = rangeType;
    }

	public String getGdsTypeId() {
		return gdsTypeId;
	}

	public void setGdsTypeId(String gdsTypeId) {
		this.gdsTypeId = gdsTypeId;
	}

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getAdid() {
        return adid;
    }

    public void setAdid(String adid) {
        this.adid = adid;
    }

    
}
