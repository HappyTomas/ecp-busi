package com.zengshi.ecp.wxbase.message.req;

/**
 * 图片消息
 * @author wangbh
 *
 */
public class ImageMessageReqVO extends BaseMessageReqVO{
	 // 图片链接  
    private String PicUrl;  
  
    public String getPicUrl() {  
        return PicUrl;  
    }  
  
    public void setPicUrl(String picUrl) {  
        PicUrl = picUrl;  
    }  
}
