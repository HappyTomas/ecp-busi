package com.zengshi.ecp.app.resp;

import com.zengshi.butterfly.app.model.IBody;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 获取APP栏目服务-出参<br>
 * Date:2016-2-22下午6:53:17  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author jiangzh
 * @version  
 * @since JDK 1.6 
 */
public class Cms002Resp extends IBody {
    
    private Long id;

    private String channelName;

    private Long channelParent;

    private String channelLabel;

    private String channelUrl;

    private Long siteId;

    private Integer sortNo;
    
    private String channelIcon;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Long getChannelParent() {
        return channelParent;
    }

    public void setChannelParent(Long channelParent) {
        this.channelParent = channelParent;
    }

    public String getChannelLabel() {
        return channelLabel;
    }

    public void setChannelLabel(String channelLabel) {
        this.channelLabel = channelLabel;
    }

    public String getChannelUrl() {
        return channelUrl;
    }

    public void setChannelUrl(String channelUrl) {
        this.channelUrl = channelUrl;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getChannelIcon() {
        return channelIcon;
    }

    public void setChannelIcon(String channelIcon) {
        this.channelIcon = channelIcon;
    }
    
}

