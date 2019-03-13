package com.zengshi.ecp.goods.dubbo.dto.stock;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description:库存仓库返回Resp DTO <br>
 * Date:2015年10月9日下午2:16:26  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version  
 * @since JDK 1.6
 */
public class GdsStockRepRespDTO extends BaseResponseDTO {

    /**
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     * 
     * @since JDK 1.6
     */
    private static final long serialVersionUID = -2551922765833422712L;

    /**
     * 仓库id
     */
    private Long id;

    /**
     * 仓库名称
     */
    private String repName;

    /**
     * 企业编码
     */
    private Long companyId;

    /**
     * 店铺编码
     */
    private Long shopId;

    /**
     * 仓库类型 00 买家仓库   01卖家总仓  02卖家分仓
     */
    private String repType;

    /**
     * 库存类型
     */
    private String stockType;

    /**
     * 仓库下库存列表
     */
    private List<GdsStockInfoRespDTO> stockInfoDTOs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRepName() {
        return repName;
    }

    public void setRepName(String repName) {
        this.repName = repName;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getRepType() {
        return repType;
    }

    public void setRepType(String repType) {
        this.repType = repType;
    }

    public String getStockType() {
        return stockType;
    }

    public void setStockType(String stockType) {
        this.stockType = stockType;
    }

    public List<GdsStockInfoRespDTO> getStockInfoDTOs() {
        return stockInfoDTOs;
    }

    public void setStockInfoDTOs(List<GdsStockInfoRespDTO> stockInfoDTOs) {
        this.stockInfoDTOs = stockInfoDTOs;
    }
    
    public Long calcAvalidStocks(){
    	Long stocks = 0L;
    	if(CollectionUtils.isNotEmpty(stockInfoDTOs)){
    		for(GdsStockInfoRespDTO dto :stockInfoDTOs){
    			if(null != dto.getAvailCount()){
    				stocks += dto.getAvailCount();
    			}
    		}
    	}
    	return stocks;
    }


}
