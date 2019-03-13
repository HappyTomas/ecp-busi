package com.zengshi.ecp.wxbase.message.resp;
/**
 * 音乐消息
 * @author wangbh
 *
 */
public class MusicMessageRespVO extends BaseMessageRespVO{
	 // 音乐  
    private MusicRespVO Music;  
  
    public MusicRespVO getMusic() {  
        return Music;  
    }  
  
    public void setMusic(MusicRespVO music) {  
        Music = music;  
    }  
}
