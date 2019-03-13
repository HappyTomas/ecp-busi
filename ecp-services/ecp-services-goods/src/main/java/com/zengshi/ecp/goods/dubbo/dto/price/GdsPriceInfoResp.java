package com.zengshi.ecp.goods.dubbo.dto.price;

import org.antlr.v4.parse.ATNBuilder.subrule_return;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description:商品价格抽象父类 <br>
 * Date:2015年9月16日下午8:19:32 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public class GdsPriceInfoResp extends BaseResponseDTO implements Comparable {

    /**
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     * 
     * @since JDK 1.6
     */
    private static final long serialVersionUID = 1L;

    /**
     * 价格id
     */
    protected Long id;

    /**
     * 真实价格
     */
    protected Long price;

    /**
     * 起购量
     */
    protected Long startAmount;

    /**
     * 价格类型编码
     */
    protected String priceTypeCode;

    /**
     * 价格类型id
     */
    protected String priceTypeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getPriceTypeCode() {
        return priceTypeCode;
    }

    public void setPriceTypeCode(String priceTypeCode) {
        this.priceTypeCode = priceTypeCode;
    }

    public Long getStartAmount() {
        return startAmount;
    }

    public void setStartAmount(Long startAmount) {
        this.startAmount = startAmount;
    }

    public String getPriceTypeId() {
        return priceTypeId;
    }

    public void setPriceTypeId(String priceTypeId) {
        this.priceTypeId = priceTypeId;
    }

    @Override
    public int compareTo(Object o) {
        GdsPriceLadderRespDTO object = (GdsPriceLadderRespDTO) o;
        Long sAmount = object.getStartAmount();
        return this.startAmount.compareTo(sAmount);
    }

    @Override
    public boolean equals(Object o) {
        GdsPriceLadderRespDTO object = (GdsPriceLadderRespDTO) o;
        if(object!=null && object.getStartAmount()!=null){
            Long sAmount = object.getStartAmount();
            if(this.startAmount.equals(sAmount)){
                return true;
            }
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
