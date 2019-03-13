package com.zengshi.ecp.busi.helpCenter.vo;

import java.io.Serializable;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

/**文章请求服务VO
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2015年11月20日下午5:49:47 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author zhanbh
 * @version
 * @since JDK 1.6
 */
public class CmsArticleVO extends EcpBasePageReqVO implements Serializable {
/*--------------------------以下为model后添加的字段 start--------------------------*/
    
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 6878576424995351318L;
    /**
     * 静态文件URl
     */
    private String staticUrl;
    /**
     * 附件URl
     */
    private String vfsUrl;
    
    private Long id;

    private String istop;

    private String status;

    private String staticId;

    private Long siteId;

    private Long channelId;

    private String homepageIsShow;

    public String getStaticUrl() {
        return staticUrl;
    }

    public void setStaticUrl(String staticUrl) {
        this.staticUrl = staticUrl;
    }

    public String getVfsUrl() {
        return vfsUrl;
    }

    public void setVfsUrl(String vfsUrl) {
        this.vfsUrl = vfsUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIstop() {
        return istop;
    }

    public void setIstop(String istop) {
        this.istop = istop;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStaticId() {
        return staticId;
    }

    public void setStaticId(String staticId) {
        this.staticId = staticId;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public String getHomepageIsShow() {
        return homepageIsShow;
    }

    public void setHomepageIsShow(String homepageIsShow) {
        this.homepageIsShow = homepageIsShow;
    }

}
