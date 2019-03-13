package com.zengshi.ecp.app.resp.gds;

import com.zengshi.butterfly.app.model.IBody;

public class CategoryTree extends IBody{

	/** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
	private static final long serialVersionUID = -7036371984613571896L;
	
	/**
	 * 是否目录.
	 */
	private Boolean isRoot;
	/**
	 *节点ID 
	 * */
	private String id;
	/**
	 * 父节点ID
	 * */
	private String pid;
	/** 
	 * 节点名称
	 * */
	private String name;
	
	public Boolean getIsRoot() {
		return isRoot;
	}
	public void setIsRoot(Boolean isRoot) {
		this.isRoot = isRoot;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	
}

