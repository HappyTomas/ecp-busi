package com.zengshi.ecp.busi.goods.vo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: 标签管理用的<br>
 * Date:2015年9月17日下午9:55:39 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author zhanbh
 * @version
 * @since JDK 1.6
 */
public class GdsLabelVO extends EcpBasePageReqVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1355468802119635703L;

	private Long id;// 标签id

	@NotNull(message = "{goods.gdsLabelVO.labelType.null.error}")
	private String labelType;// 标签类型

	@NotNull(message = "{goods.gdsLabelVO.labelTitle.null.error}")
	@Length(min = 0, max = 64, message = "{goods.MediaVO.labelTitle.length.error}")
	private String labelTitle; // 标签标题

	@Length(min = 0, max = 64, message = "{goods.MediaVO.labelDesc.length.error}")
	private String labelDesc; // 标签描述

	private String status; // 标签状态

	private Integer sortNo;// 排序值

	private String operateId;// 批量操作的id串 逗号隔开

	private String flag; // 批量操作的标志 0 批量禁用 1 批量启用

	public String getOperateId() {
		return operateId;
	}

	public void setOperateId(String operateId) {
		this.operateId = operateId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabelType() {
		return labelType;
	}

	public void setLabelType(String labelType) {
		this.labelType = labelType;
	}

	public String getLabelTitle() {
		return labelTitle;
	}

	public void setLabelTitle(String labelTitle) {
		this.labelTitle = labelTitle;
	}

	public String getLabelDesc() {
		return labelDesc;
	}

	public void setLabelDesc(String labelDesc) {
		this.labelDesc = labelDesc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
