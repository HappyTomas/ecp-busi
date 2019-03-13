package com.zengshi.ecp.aip.third.dubbo.dto.resp;

import java.util.HashMap;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

/**
 * 
 * @version
 * @since JDK 1.7
 */
public class PropValueRespDTO extends BaseResponseDTO {

	private static final long serialVersionUID = 1L;

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
	
	//属性值id
	private Long vid;
	
	//属性值名称
	private String name;
	
	//属性别名
	private String nameAlias;

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

	public Long getVid() {
		return vid;
	}

	public void setVid(Long vid) {
		this.vid = vid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameAlias() {
		return nameAlias;
	}

	public void setNameAlias(String nameAlias) {
		this.nameAlias = nameAlias;
	}
 

}
