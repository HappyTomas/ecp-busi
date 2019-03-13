package com.zengshi.ecp.goods.dubbo.dto.category;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;
/**
 * 
 * Title: 商品分类比较响应DTO. <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: <br>
 * Date:2015-9-22上午10:19:09  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6
 */
public class GdsCategoryCompareRespDTO extends BaseResponseDTO {

	private static final long serialVersionUID = -7907793718020374875L;
	/**
	 * 分类相等.
	 */
	public static final Integer RESULT_EQUAL = 0;
	/**
	 * 源分类为目标分类子分类.
	 */
	public static final Integer RESULT_GREAT_THAN = 1;
	/**
	 * 源分类为目标分类父分类.
	 */
	public static final Integer RESULT_LESS_THAN = -1;
	/**
	 * 两个分类无关联关系. 
	 */
	public static final Integer RESULT_NO_RELATIONSHIP = -99;
	/**
	 * 两个分类无关联关系. 
	 */
	public static final Integer RESULT_ERROR = -100;
	
	// 比对结果值 0代表相等 1代表source大于target -1代表target大于source -99代表两个分类毫无关系.
	private Integer result;
	// 消息描述.
	private String msg;
	
	public Integer getResult() {
		return result;
	}
	public void setResult(Integer result) {
		this.result = result;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	

}

