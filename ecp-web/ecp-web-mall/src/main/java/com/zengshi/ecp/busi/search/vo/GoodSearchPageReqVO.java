package com.zengshi.ecp.busi.search.vo;

import java.util.List;

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
    
    /**
     * 搜索关键字
     */
    private String keyword;
    
    /**
     * 店铺编码，用户店铺搜索
     */
    private String shopId="";
    
    /**
     * 是否分类Facet
     */
    private boolean categoryPathFacet=false;

    /**
     * 搜索分类
     */
    private String category;

    /**
     * 单个过滤和多个属性过滤都使用属性值组统一处理
     */
    private String propertyGroup;

    /**
     * 在结果中搜
     */
    private String searchmore;

    /**
     * 排序字段
     */
    private String field;

    /**
     * 排序字段排序
     */
    private String sort;
    
    /**
     * 起点价格
     */
    private String priceStart;
    
    /**
     * 终点价格
     */
    private String priceEnd;
    
    /**
     * 是否返回结果，在只做统计类时用到
     */
    private boolean retResult=true;
    /**
     * 书名称
     */
    private String bookName;
    /**
     * 书作者
     */
    private String author;
    /**
     * 书isbn
     */
    private String isbn;
    /**
     * 书装帧
     */
    private List<String> binding;
    /**
     * 书出版start时间
     */
    private String publicationDateStart;
    /**
     * 书出版end时间
     */
    private String publicationDateEnd;
    /**
     * 是否有库存
     */
    private String ifStorage;
    /**
     * 分类编码
     */
    private String catgCode;
    
    //<--------------个性化参数---------------->
    private String showType;
    /**
     * 广告ID。
     */
    private String adid;
    /**
     * 是否高级搜索true是，false否
     */
    private boolean senior;
    //<--------------个性化参数---------------->
    
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    
    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public boolean isCategoryPathFacet() {
        return categoryPathFacet;
    }

    public void setCategoryPathFacet(boolean categoryPathFacet) {
        this.categoryPathFacet = categoryPathFacet;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPropertyGroup() {
        return propertyGroup;
    }

    public void setPropertyGroup(String propertyGroup) {
        this.propertyGroup = propertyGroup;
    }

    public String getSearchmore() {
        return searchmore;
    }

    public void setSearchmore(String searchmore) {
        this.searchmore = searchmore;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    public String getPriceStart() {
        return priceStart;
    }

    public void setPriceStart(String priceStart) {
        this.priceStart = priceStart;
    }

    public String getPriceEnd() {
        return priceEnd;
    }

    public void setPriceEnd(String priceEnd) {
        this.priceEnd = priceEnd;
    }

    public boolean isRetResult() {
        return retResult;
    }

    public void setRetResult(boolean retResult) {
        this.retResult = retResult;
    }

    public String getAdid() {
        return adid;
    }

    public void setAdid(String adid) {
        this.adid = adid;
    }

	public boolean isSenior() {
		return senior;
	}

	public void setSenior(boolean senior) {
		this.senior = senior;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public List<String> getBinding() {
		return binding;
	}

	public void setBinding(List<String> binding) {
		this.binding = binding;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getIfStorage() {
		return ifStorage;
	}

	public void setIfStorage(String ifStorage) {
		this.ifStorage = ifStorage;
	}

	public String getPublicationDateStart() {
		return publicationDateStart;
	}

	public void setPublicationDateStart(String publicationDateStart) {
		this.publicationDateStart = publicationDateStart;
	}

	public String getPublicationDateEnd() {
		return publicationDateEnd;
	}

	public void setPublicationDateEnd(String publicationDateEnd) {
		this.publicationDateEnd = publicationDateEnd;
	}

	public String getCatgCode() {
		return catgCode;
	}

	public void setCatgCode(String catgCode) {
		this.catgCode = catgCode;
	}
    
    
}
