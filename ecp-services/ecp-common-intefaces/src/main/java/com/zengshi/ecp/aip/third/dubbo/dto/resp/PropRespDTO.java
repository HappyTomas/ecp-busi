package com.zengshi.ecp.aip.third.dubbo.dto.resp;

import java.util.HashMap;
import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

/**
 * 
 * @version
 * @since JDK 1.7
 */
public class PropRespDTO extends BaseResponseDTO {

	private static final long serialVersionUID = 1L;

	// 分类编码
	private String catgCode;
	// 属性id
	private Long pid;

	// 属性名称
	private String pname;

	// 排序
	private Long sortOrder;

	// 状态 1有效 0无效
	private String status;

	// 属性值feature
	private HashMap features;
	
	//属性值列表
	private List<PropValueRespDTO> propValues;
	
	//是否颜色属性
	private Boolean ifColorProp;
	
	//是否输入属性
	private Boolean ifInputProp;
	
	private Boolean ifItemProp;
	
	private Boolean ifEnumProp;
	
	//是否关键属性
	private Boolean ifKeyProp;
	
	//是否销售属性
	private Boolean ifSaleProp;
	
	//是否多选
	private Boolean mult;
	
	//是否必需
	private Boolean must;
	
	//必需
	private Boolean required;
	//属性类型
	private String propType;

	public Boolean getIfColorProp() {
		return ifColorProp;
	}

	public Boolean getIfInputProp() {
		return ifInputProp;
	}

	public Boolean getIfItemProp() {
		return ifItemProp;
	}

	public Boolean getIfEnumProp() {
		return ifEnumProp;
	}

	public Boolean getIfKeyProp() {
		return ifKeyProp;
	}

	public Boolean getIfSaleProp() {
		return ifSaleProp;
	}

	public Boolean getMult() {
		return mult;
	}

	public Boolean getMust() {
		return must;
	}

	public Boolean getRequired() {
		return required;
	}

	public String getPropType() {
		return propType;
	}

	public void setIfColorProp(Boolean ifColorProp) {
		this.ifColorProp = ifColorProp;
	}

	public void setIfInputProp(Boolean ifInputProp) {
		this.ifInputProp = ifInputProp;
	}

	public void setIfItemProp(Boolean ifItemProp) {
		this.ifItemProp = ifItemProp;
	}

	public void setIfEnumProp(Boolean ifEnumProp) {
		this.ifEnumProp = ifEnumProp;
	}

	public void setIfKeyProp(Boolean ifKeyProp) {
		this.ifKeyProp = ifKeyProp;
	}

	public void setIfSaleProp(Boolean ifSaleProp) {
		this.ifSaleProp = ifSaleProp;
	}

	public void setMult(Boolean mult) {
		this.mult = mult;
	}

	public void setMust(Boolean must) {
		this.must = must;
	}

	public void setRequired(Boolean required) {
		this.required = required;
	}

	public void setPropType(String propType) {
		this.propType = propType;
	}

	public String getCatgCode() {
		return catgCode;
	}

	public void setCatgCode(String catgCode) {
		this.catgCode = catgCode;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
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

	public HashMap getFeatures() {
		return features;
	}

	public void setFeatures(HashMap features) {
		this.features = features;
	}

	public List<PropValueRespDTO> getPropValues() {
		return propValues;
	}

	public void setPropValues(List<PropValueRespDTO> propValues) {
		this.propValues = propValues;
	}

}
