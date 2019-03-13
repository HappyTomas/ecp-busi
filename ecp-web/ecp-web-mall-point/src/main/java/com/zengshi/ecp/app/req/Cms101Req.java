package com.zengshi.ecp.app.req;

import com.zengshi.butterfly.app.model.IBody;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 获取积分APP首页数据服务-入参<br>
 * Date:2016-5-03下午6:52:57  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author zhanbh
 * @version  
 * @since JDK 1.6 
 */
public class Cms101Req extends IBody {
    
    private Long siteId ;
    
    private Long adPlaceId;
    
    private Integer channelSize;
    
    private Integer guessPageNo;//猜你喜欢分页
    
    private Integer guessPageSize;
    
    private Integer guessGdsNum;


    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public Long getAdPlaceId() {
        return adPlaceId;
    }

    public void setAdPlaceId(Long adPlaceId) {
        this.adPlaceId = adPlaceId;
    }

    public Integer getChannelSize() {
        return channelSize;
    }

    public void setChannelSize(Integer channelSize) {
        this.channelSize = channelSize;
    }

	public Integer getGuessPageNo() {
		return guessPageNo;
	}

	public void setGuessPageNo(Integer guessPageNo) {
		this.guessPageNo = guessPageNo;
	}

	public Integer getGuessPageSize() {
		return guessPageSize;
	}

	public void setGuessPageSize(Integer guessPageSize) {
		this.guessPageSize = guessPageSize;
	}

	public Integer getGuessGdsNum() {
		return guessGdsNum;
	}

	public void setGuessGdsNum(Integer guessGdsNum) {
		this.guessGdsNum = guessGdsNum;
	}

    
}

