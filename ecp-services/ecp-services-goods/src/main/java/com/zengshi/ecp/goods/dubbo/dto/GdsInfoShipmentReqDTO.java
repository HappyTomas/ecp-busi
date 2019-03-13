package com.zengshi.ecp.goods.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;

@SuppressWarnings("rawtypes")
public class GdsInfoShipmentReqDTO extends BaseInfo {

    /**
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     * 
     * @since JDK 1.6
     */
    private static final long serialVersionUID = -1L;

    private Long gdsId;

	private Long skuId;

    private Long count;
    
    private String catgCode;
    //子订单总金额
    private Long amount;
    //商品类型
    private Long typeId;
    //所属顶级分类编码
    private String parentCode;
    
    public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public Long getSkuId() {
 		return skuId;
 	}

 	public void setSkuId(Long skuId) {
 		this.skuId = skuId;
 	}

    public Long getGdsId() {
        return gdsId;
    }

    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getCatgCode() {
        return catgCode;
    }

    public void setCatgCode(String catgCode) {
        this.catgCode = catgCode;
    }

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

}
