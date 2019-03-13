package com.zengshi.ecp.cms.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

//req和resp继承的类不同。
public class CmsListRespDTO extends BaseResponseDTO {

	/*--------------------------以下为model后添加的字段 start--------------------------*/
	/**
	 * 状态翻译
	 */
	private String statusZH;
	/**
	 * 排行榜分类翻译
	 */
	private String listClassZH;
	/**
	 * 商品名称
	 */
	private String gdsName;

	/*--------------------------以下为model后添加的字段 end------------------------*/
	private Long id;

	private String listClass;

	private Long gdsId;

	private String status;

	private Integer sortNo;

	private Long createStaff;

	private Timestamp createTime;

	private Long updateStaff;

	private Timestamp updateTime;

	private static final long serialVersionUID = 1L;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getListClass() {
		return listClass;
	}

	public void setListClass(String listClass) {
		this.listClass = listClass == null ? null : listClass.trim();
	}

	public Long getGdsId() {
		return gdsId;
	}

	public void setGdsId(Long gdsId) {
		this.gdsId = gdsId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public Long getCreateStaff() {
		return createStaff;
	}

	public void setCreateStaff(Long createStaff) {
		this.createStaff = createStaff;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateStaff() {
		return updateStaff;
	}

	public void setUpdateStaff(Long updateStaff) {
		this.updateStaff = updateStaff;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getStatusZH() {
		return statusZH;
	}

	public void setStatusZH(String statusZH) {
		this.statusZH = statusZH;
	}

	public String getListClassZH() {
		return listClassZH;
	}

	public void setListClassZH(String listClassZH) {
		this.listClassZH = listClassZH;
	}

	public String getGdsName() {
		return gdsName;
	}

	public void setGdsName(String gdsName) {
		this.gdsName = gdsName;
	}
}
