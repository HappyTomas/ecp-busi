package com.zengshi.ecp.aip.third.dubbo.dto.resp;

import java.util.HashMap;
import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

/**
 * 
 * @version  
 * @since JDK 1.7
 */
public class CatgRespDTO extends BaseResponseDTO {

	private static final long serialVersionUID = 1L;
	
	//分类编码
	private String catgCode;
	
	//父分类编码
	private String parentCatgCode;
	
	//父分类名称
	private String parentCatgName;
	
	//分类名称
	private String name;
	
	//是否父节点
	private Boolean ifParent;
	
	//排序
	private Long sortOrder;
	
	//状态 1有效 0无效
	private String status;
	
	//是否度量衡类目
	private Boolean ifSale;
	
	//分类对应的属性
	private List<PropRespDTO> props;

	public String getCatgCode() {
		return catgCode;
	}

	public void setCatgCode(String catgCode) {
		this.catgCode = catgCode;
	}

	public String getParentCatgCode() {
		return parentCatgCode;
	}

	public void setParentCatgCode(String parentCatgCode) {
		this.parentCatgCode = parentCatgCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIfParent() {
		return ifParent;
	}

	public void setIfParent(Boolean ifParent) {
		this.ifParent = ifParent;
	}

	public Long getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Long sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getIfSale() {
		return ifSale;
	}

	public void setIfSale(Boolean ifSale) {
		this.ifSale = ifSale;
	}

	public List<PropRespDTO> getProps() {
		return props;
	}

	public void setProps(List<PropRespDTO> props) {
		this.props = props;
	}

	public String getParentCatgName() {
		return parentCatgName;
	}

	public void setParentCatgName(String parentCatgName) {
		this.parentCatgName = parentCatgName;
	}

}

