package com.zengshi.ecp.busi.seller.goods.vo.mediacategory;

import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaCategoryRespDTO;
/**
 * 
 * 媒体分类响应VO <br>
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2015-9-14上午10:32:39  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6
 */
public class MediaCategoryRespVO extends EcpBaseResponseVO {

	/** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
	private static final long serialVersionUID = 7467000550436406024L;
	
	private GdsMediaCategoryRespDTO respDTO;
	
	private GdsMediaCategoryRespDTO parentRespDTO;
	
	public GdsMediaCategoryRespDTO getRespDTO() {
		return respDTO;
	}
	public void setRespDTO(GdsMediaCategoryRespDTO respDTO) {
		this.respDTO = respDTO;
	}
	public GdsMediaCategoryRespDTO getParentRespDTO() {
		return parentRespDTO;
	}
	public void setParentRespDTO(GdsMediaCategoryRespDTO parentRespDTO) {
		this.parentRespDTO = parentRespDTO;
	}
	
	


}
