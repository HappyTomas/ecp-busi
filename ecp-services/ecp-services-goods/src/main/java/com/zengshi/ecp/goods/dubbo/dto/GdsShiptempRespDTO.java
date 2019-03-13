package com.zengshi.ecp.goods.dubbo.dto;

import java.sql.Timestamp;
import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;
public class GdsShiptempRespDTO extends BaseResponseDTO{
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -4816742769339006527L;

    private Long id;

    private String shipTemplateName;

    private String shipTemplateType;

    private Long  shopId;

    private String ifFree;

    private String shipInstruction;

    private String status;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;
    
    private String pricingMode;//计价模式
    
    private String shopName;//店铺名称

    private List<GdsShiptempPriceRespDTO> gdsShipTempPriceRespDTO;//运费模板计价模式
    
    private GdsShiptempPriceRespDTO defaultPriceRespDTO;//默认运费模板（用于获取编辑数据的时候使用，新增不适用）
    
    private String catgCode;//模板分类
    
    private String catgName;//模板分类名称
    
    /**
     * 转换地区转换成json数租用于前店
     */
    private String listToJasonArray;
    /**
     * 判断是否是默认运费模板  1：是，0：否
     */
    private String ifDefaultTemplate;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShipTemplateName() {
        return shipTemplateName;
    }

    public void setShipTemplateName(String shipTemplateName) {
        this.shipTemplateName = shipTemplateName == null ? null : shipTemplateName.trim();
    }

    public String getShipTemplateType() {
        return shipTemplateType;
    }

    public void setShipTemplateType(String shipTemplateType) {
        this.shipTemplateType = shipTemplateType == null ? null : shipTemplateType.trim();
    }

    public Long  getShopId() {
        return shopId;
    }

    public void setShopId(Long  shopId) {
        this.shopId = shopId;
    }

    public String getIfFree() {
        return ifFree;
    }

    public void setIfFree(String ifFree) {
        this.ifFree = ifFree == null ? null : ifFree.trim();
    }

    public String getShipInstruction() {
        return shipInstruction;
    }

    public void setShipInstruction(String shipInstruction) {
        this.shipInstruction = shipInstruction == null ? null : shipInstruction.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Long getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(Long updateStaff) {
        this.updateStaff = updateStaff;
    }

    public List<GdsShiptempPriceRespDTO> getGdsShipTempPriceRespDTO() {
        return gdsShipTempPriceRespDTO;
    }

    public void setGdsShipTempPriceRespDTO(List<GdsShiptempPriceRespDTO> gdsShipTempPriceRespDTO) {
        this.gdsShipTempPriceRespDTO = gdsShipTempPriceRespDTO;
    }

    public String getPricingMode() {
        return pricingMode;
    }

    public void setPricingMode(String pricingMode) {
        this.pricingMode = pricingMode;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
    
    public GdsShiptempPriceRespDTO getDefaultPriceRespDTO() {
        return defaultPriceRespDTO;
    }

    public void setDefaultPriceRespDTO(GdsShiptempPriceRespDTO defaultPriceRespDTO) {
        this.defaultPriceRespDTO = defaultPriceRespDTO;
    }

    public String getListToJasonArray() {
        return listToJasonArray;
    }

    public void setListToJasonArray(String listToJasonArray) {
        this.listToJasonArray = listToJasonArray;
    }

    public String getCatgCode() {
        return catgCode;
    }

    public void setCatgCode(String catgCode) {
        this.catgCode = catgCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", shipTemplateName=").append(shipTemplateName);
        sb.append(", shipTemplateType=").append(shipTemplateType);
        sb.append(", shopId=").append(shopId);
        sb.append(", ifFree=").append(ifFree);
        sb.append(", shipInstruction=").append(shipInstruction);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append("]");
        return sb.toString();
    }

    public String getCatgName() {
        return catgName;
    }

    public void setCatgName(String catgName) {
        this.catgName = catgName;
    }

    public String getIfDefaultTemplate() {
        return ifDefaultTemplate;
    }

    public void setIfDefaultTemplate(String ifDefaultTemplate) {
        this.ifDefaultTemplate = ifDefaultTemplate;
    }
    
}
