package com.zengshi.ecp.wxbase.message.resp;
/**
 * 文本消息
 * @author wangbh
 *
 */
public class TextMessageRespVO extends BaseMessageRespVO {
	  // 回复的消息内容  
    private String Content;  
  
    public String getContent() {  
        return Content;  
    }  
  
    public void setContent(String content) {  
        Content = content;  
    }  
}
