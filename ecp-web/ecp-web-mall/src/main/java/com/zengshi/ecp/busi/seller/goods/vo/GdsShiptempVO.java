package com.zengshi.ecp.busi.seller.goods.vo;

import java.util.List;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.zengshi.ecp.goods.dubbo.dto.GdsShiptempPriceReqDTO;

public class GdsShiptempVO extends EcpBasePageReqVO{

    /** 
     * serialVersionUID:TODO(用来接收参数添加保存运费模板). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 5531471078431050807L;
    
    private Long shipTemplateId;//运费模板ID
    
    private String shipTemplateName;

    private String shipTemplateType;

    private Long  shopId;

    private String ifFree;

    private String shipInstruction;

    private String status;
    
    private List<GdsShiptempPriceReqDTO> gdsShiptempPriceReqDTOList;
    
    private String pricingMode;//计价方式
    
    private String seniorFreeParam;//高级运费的配置参数
    
    private String defaultFreeParam;//默认运费的配置参数
    
    private String catgCode;//分类编码

    public Long getShipTemplateId() {
        return shipTemplateId;
    }

    public void setShipTemplateId(Long shipTemplateId) {
        this.shipTemplateId = shipTemplateId;
    }

    public String getShipTemplateName() {
        return shipTemplateName;
    }

    public void setShipTemplateName(String shipTemplateName) {
        this.shipTemplateName = shipTemplateName;
    }

    public String getShipTemplateType() {
        return shipTemplateType;
    }

    public void setShipTemplateType(String shipTemplateType) {
        this.shipTemplateType = shipTemplateType;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getIfFree() {
        return ifFree;
    }

    public void setIfFree(String ifFree) {
        this.ifFree = ifFree;
    }

    public String getShipInstruction() {
        return shipInstruction;
    }

    public void setShipInstruction(String shipInstruction) {
        this.shipInstruction = shipInstruction;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<GdsShiptempPriceReqDTO> getGdsShiptempPriceReqDTOList() {
        return gdsShiptempPriceReqDTOList;
    }

    public void setGdsShiptempPriceReqDTOList(List<GdsShiptempPriceReqDTO> gdsShiptempPriceReqDTOList) {
        this.gdsShiptempPriceReqDTOList = gdsShiptempPriceReqDTOList;
    }

    public String getPricingMode() {
        return pricingMode;
    }

    public void setPricingMode(String pricingMode) {
        this.pricingMode = pricingMode;
    }

    public String getSeniorFreeParam() {
        return seniorFreeParam;
    }

    public void setSeniorFreeParam(String seniorFreeParam) {
        this.seniorFreeParam = seniorFreeParam;
    }

    public String getDefaultFreeParam() {
        return defaultFreeParam;
    }

    public void setDefaultFreeParam(String defaultFreeParam) {
        this.defaultFreeParam = defaultFreeParam;
    }

    public String getCatgCode() {
        return catgCode;
    }

    public void setCatgCode(String catgCode) {
        this.catgCode = catgCode;
    }
    
}

