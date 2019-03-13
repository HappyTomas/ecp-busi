package com.zengshi.ecp.general.solr.message;


public class GdsDeltaMessage extends DeltaMessage{
    
    private String catlogIds;
    
    private String catgCodes;
    
    private String siteId;

    public String getCatlogIds() {
        return catlogIds;
    }

    public void setCatlogIds(String catlogIds) {
        this.catlogIds = catlogIds;
    }

    public String getCatgCodes() {
        return catgCodes;
    }

    public void setCatgCodes(String catgCodes) {
        this.catgCodes = catgCodes;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }
    
}

