/** 
 * Project Name:ecp-services-goods-server 
 * File Name:ImageSampleChapter.java 
 * Package Name:com.zengshi.ecp.goods.dubbo.dto.category.dataimport 
 * Date:2015-10-30下午4:49:19 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.dubbo.dto.category.dataimport;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods-server <br>
 * Description:图片样章信息类，请不要用于DTO传输。 <br>
 * Date:2015-10-30下午4:49:19  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public class ImageSampleChapterRespDTO extends BaseResponseDTO {
	
	/** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
	private static final long serialVersionUID = -6335245501505676453L;
	
	private String fileName;

	private String isbn;
	 
	private String sortNo;
	
	private String fileSuffix;
	
	private String mediaUuid;

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getSortNo() {
		return sortNo;
	}

	public void setSortNo(String sortNo) {
		this.sortNo = sortNo;
	}

	public String getFileSuffix() {
		return fileSuffix;
	}

	public void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
	}

	public String getMediaUuid() {
		return mediaUuid;
	}

	public void setMediaUuid(String mediaUuid) {
		this.mediaUuid = mediaUuid;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	

}

