package com.zengshi.ecp.busi.shop.vo;

import java.util.ArrayList;
import java.util.List;

import com.zengshi.ecp.base.util.ApplicationContextUtil;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatalog2SiteRSV;
import com.zengshi.ecp.search.dubbo.search.ExtraFieldQueryField;
import com.zengshi.ecp.search.dubbo.search.SearchFacade;
import com.zengshi.ecp.search.dubbo.search.SearchParam;
import com.zengshi.ecp.search.dubbo.search.SortField;
import com.zengshi.ecp.search.dubbo.search.result.SearchResult;
import com.zengshi.ecp.search.dubbo.search.translator.GdsTranslator;
import com.zengshi.ecp.search.dubbo.search.util.ESort;
import com.zengshi.ecp.server.front.dto.BaseResponseDTO;
import com.zengshi.ecp.server.front.util.SiteLocaleUtil;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;

public class ShopSearchResult extends BaseResponseDTO {
	private final static String SUFFIX_IMAGE_SIZE = "_70x70!";
	/**
	 * 
	 */
	private static final long serialVersionUID = -469652509134303954L;

	private static final String MODULE = ShopSearchResult.class.getName();

	
	private String id;

	private String shopType;

	private String shopName;

	private String shopDesc;

	private String logoPath;

	private Double gdsEvalRate;

	private Long saleCount;
	
	private Long gdsCount;
	
	private String logoUrl;
	
	private List<GoodSearchResult> gdsList;
	
	public void render() {
        this.logoUrl = ImageUtil.getImageUrl(this.logoPath + SUFFIX_IMAGE_SIZE);
        try {
            SearchParam searchParam = new SearchParam();
            searchParam.setIfRetDocList(true);
            searchParam.setCurrentSiteId(SiteLocaleUtil.getSite());
            searchParam.setPageNo(1);
            searchParam.setPageSize(10);
            List<SortField> sortFieldList=new ArrayList<SortField>();
            ESort eSort = ESort.getAndValidSort("desc");
            if (null != eSort) {
            sortFieldList.add(new SortField("sales", eSort));
            }
            searchParam.setSortFieldList(sortFieldList);
            List<ExtraFieldQueryField> extraANDFieldQueryList = new ArrayList<ExtraFieldQueryField>(); 
            ExtraFieldQueryField extraFieldQueryField = new ExtraFieldQueryField();
            extraFieldQueryField.setName("shopId");
            extraFieldQueryField.setValue(id);
            extraANDFieldQueryList.add(extraFieldQueryField);
            searchParam.setExtraANDFieldQueryList(extraANDFieldQueryList);
            SearchResult<GoodSearchResult> searchResult = SearchFacade.search(
                    GoodSearchResult.class, searchParam, new GdsTranslator(ApplicationContextUtil.getBean("gdsCatalog2SiteRSV",
                            IGdsCatalog2SiteRSV.class) ));
            this.gdsCount = searchResult.getNumFound();
            if(searchResult.isSuccess()&& searchResult.getResultList()!= null){
                for(GoodSearchResult goodSearchResult : searchResult.getResultList()){
                    goodSearchResult.render();
                }
            }
            this.gdsList = searchResult.getResultList();
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.error(MODULE, "获取店铺：" + id + "商品总数失败！");
        }
    }
	
	public Long getGdsCount() {
		return gdsCount;
	}

	public void setGdsCount(Long gdsCount) {
		this.gdsCount = gdsCount;
	}
	

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	
	
	public String getId() {
        return id;
    }

    public String getShopType() {
        return shopType;
    }

    public String getShopName() {
        return shopName;
    }

    public String getShopDesc() {
        return shopDesc;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public Double getGdsEvalRate() {
        return gdsEvalRate;
    }

    public Long getSaleCount() {
        return saleCount;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setShopType(String shopType) {
        this.shopType = shopType;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void setShopDesc(String shopDesc) {
        this.shopDesc = shopDesc;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public void setGdsEvalRate(Double gdsEvalRate) {
        this.gdsEvalRate = gdsEvalRate;
    }

    public void setSaleCount(Long saleCount) {
        this.saleCount = saleCount;
    }


    public List<GoodSearchResult> getGdsList() {
        return gdsList;
    }

    public void setGdsList(List<GoodSearchResult> gdsList) {
        this.gdsList = gdsList;
    }

}
