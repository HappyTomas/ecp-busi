package com.zengshi.ecp.aip.third.dubbo.dto.resp;


import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

/**
 * 
 * @version  
 * @since JDK 1.7
 */
public class UploadThirdRespDTO extends BaseResponseDTO {

	private static final long serialVersionUID = 1L;
	
	private String pictureId;
	
	private String pictureCategoryId;
	//返回的是绝对路径如：http://img07.taobaocdn.com/imgextra/i7/22670458/T2dD0kXb4cXXXXXXXX_!!22670458.jpg
	private String picturePath;
	
	private String title;

	public String getPictureId() {
		return pictureId;
	}

	public String getPictureCategoryId() {
		return pictureCategoryId;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public String getTitle() {
		return title;
	}

	public void setPictureId(String pictureId) {
		this.pictureId = pictureId;
	}

	public void setPictureCategoryId(String pictureCategoryId) {
		this.pictureCategoryId = pictureCategoryId;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}

