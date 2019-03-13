package com.zengshi.ecp.goods.dubbo.dto.gdsinfo;

import java.util.List;

import com.zengshi.ecp.goods.dubbo.dto.GdsScoreExtReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2CatgReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2MediaReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2PriceReqDTO;
import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * 商品录入 入参DTO
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月25日下午5:16:38  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version  
 * @since JDK 1.6
 */
public class GdsInfoAddReqDTO extends BaseInfo {
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * 
	 * @since JDK 1.6
	 */
	private static final long serialVersionUID = -4362687367247252960L;

	/**
	 * 商品属性关系
	 */
	private List<GdsGds2PropReqDTO> gds2PropReqDTOs;
	
	/**
	 * 商品单品已选规格属性
	 */
	private List<GdsGds2PropReqDTO> skuProps;
	
	/**
	 * 商品分类关系
	 */
	private List<GdsGds2CatgReqDTO> gds2CatgReqDTOs;
	
	/**
	 * 商品价格
	 */
    private List<GdsSku2PriceReqDTO> sku2PriceReqDTOs;
	
	/**
	 * 单品列表
	 */
	private List<GdsSkuInfoReqDTO> skuInfoReqDTOs;
	
	/**
	 * 商品图片关系
	 */
	private List<GdsGds2MediaReqDTO> gds2MediaReqDTOs;
	
	/**
	 * 商品积分关系
	 */
	private List<GdsScoreExtReqDTO> gdsScoreExtReqDTOs;
	
	/**
	 * 商品基本信息
	 */
	private GdsInfoReqDTO gdsInfoReqDTO;
	
	/**
	 * 公司编码
	 */
	private Long companyId;
	/**
	 * 是否本地编辑
	 */
    private String ifLocalEdit;

	/**
	 * 是否维护商品编码映射表（默认为true）
	 */
	private boolean ifMaintainGdsInterfaceGds=true;

	public List<GdsGds2PropReqDTO> getGds2PropReqDTOs() {
		return gds2PropReqDTOs;
	}

	public void setGds2PropReqDTOs(List<GdsGds2PropReqDTO> gds2PropReqDTOs) {
		this.gds2PropReqDTOs = gds2PropReqDTOs;
	}

	public List<GdsGds2CatgReqDTO> getGds2CatgReqDTOs() {
		return gds2CatgReqDTOs;
	}

	public void setGds2CatgReqDTOs(List<GdsGds2CatgReqDTO> gds2CatgReqDTOs) {
		this.gds2CatgReqDTOs = gds2CatgReqDTOs;
	}


	public List<GdsSku2PriceReqDTO> getSku2PriceReqDTOs() {
		return sku2PriceReqDTOs;
	}

	public void setSku2PriceReqDTOs(List<GdsSku2PriceReqDTO> sku2PriceReqDTOs) {
		this.sku2PriceReqDTOs = sku2PriceReqDTOs;
	}

	public List<GdsSkuInfoReqDTO> getSkuInfoReqDTOs() {
		return skuInfoReqDTOs;
	}

	public void setSkuInfoReqDTOs(List<GdsSkuInfoReqDTO> skuInfoReqDTOs) {
		this.skuInfoReqDTOs = skuInfoReqDTOs;
	}

	public List<GdsGds2MediaReqDTO> getGds2MediaReqDTOs() {
		return gds2MediaReqDTOs;
	}

	public void setGds2MediaReqDTOs(List<GdsGds2MediaReqDTO> gds2MediaReqDTOs) {
		this.gds2MediaReqDTOs = gds2MediaReqDTOs;
	}

	public GdsInfoReqDTO getGdsInfoReqDTO() {
		return gdsInfoReqDTO;
	}

	public void setGdsInfoReqDTO(GdsInfoReqDTO gdsInfoReqDTO) {
		this.gdsInfoReqDTO = gdsInfoReqDTO;
	}

	public List<GdsScoreExtReqDTO> getGdsScoreExtReqDTOs() {
		return gdsScoreExtReqDTOs;
	}

	public void setGdsScoreExtReqDTOs(List<GdsScoreExtReqDTO> gdsScoreExtReqDTOs) {
		this.gdsScoreExtReqDTOs = gdsScoreExtReqDTOs;
	}

	public List<GdsGds2PropReqDTO> getSkuProps() {
		return skuProps;
	}

	public void setSkuProps(List<GdsGds2PropReqDTO> skuProps) {
		this.skuProps = skuProps;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

    public String getIfLocalEdit() {
        return ifLocalEdit;
    }

    public void setIfLocalEdit(String ifLocalEdit) {
        this.ifLocalEdit = ifLocalEdit;
    }

	public boolean isIfMaintainGdsInterfaceGds() {
		return ifMaintainGdsInterfaceGds;
	}

	public void setIfMaintainGdsInterfaceGds(boolean ifMaintainGdsInterfaceGds) {
		this.ifMaintainGdsInterfaceGds = ifMaintainGdsInterfaceGds;
	}
}
