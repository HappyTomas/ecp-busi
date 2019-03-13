package com.zengshi.ecp.wxbase.message.req;

/**
 * 音频消息
 * @author wangbh
 *
 */
public class VoiceMessageReqVO extends BaseMessageReqVO{
	 // 媒体ID  
    private String MediaId;  
    // 语音格式  
    private String Format;  
  
    public String getMediaId() {  
        return MediaId;  
    }  
  
    public void setMediaId(String mediaId) {  
        MediaId = mediaId;  
    }  
  
    public String getFormat() {  
        return Format;  
    }  
  
    public void setFormat(String format) {  
        Format = format;  
    }  

}
