package com.zengshi.ecp.wxbase.message.req;

/**
 * 文本消息
 * @author wangbh
 *
 */
public class TextMessageReqVO extends BaseMessageReqVO{

	 // 消息内容  
    private String Content;  
  
    public String getContent() {  
        return Content;  
    }  
  
    public void setContent(String content) {  
        Content = content;  
    }  
}
