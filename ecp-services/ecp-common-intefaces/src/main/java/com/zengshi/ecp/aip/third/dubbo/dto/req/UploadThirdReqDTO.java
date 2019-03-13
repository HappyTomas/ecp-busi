package com.zengshi.ecp.aip.third.dubbo.dto.req;

/**
 * 
 * @version  
 * @since JDK 1.7
 */
public class UploadThirdReqDTO extends BaseShopAuthReqDTO{

	private static final long serialVersionUID = 1L;
	 
	//图片分类id
	private String pictureCategoryId="0";
	
	//图片url  imgUrl为空时 使用fileId 
	private String imgUrl;
	
	//图片id
	private String fileId;
	
	//Bule.jpg
	private String imageInputTitle;
	
	//图片标题,如果为空,传的图片标题就取去掉后缀名的image_input_title
	private String title;
	
	public String getPictureCategoryId() {
		return pictureCategoryId;
	}

	public String getImageInputTitle() {
		return imageInputTitle;
	}

	public String getTitle() {
		return title;
	}

	public void setPictureCategoryId(String pictureCategoryId) {
		this.pictureCategoryId = pictureCategoryId;
	}

	public void setImageInputTitle(String imageInputTitle) {
		this.imageInputTitle = imageInputTitle;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
 
}

