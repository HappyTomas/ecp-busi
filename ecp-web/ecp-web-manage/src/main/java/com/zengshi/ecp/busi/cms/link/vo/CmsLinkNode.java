package com.zengshi.ecp.busi.cms.link.vo;

import java.io.Serializable;

public class CmsLinkNode implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
    
    private String pId;
    
    private String name;
    
    private Boolean isParent;

    private boolean newCreate;
    
    private String siteId;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(Boolean isParent) {
        this.isParent = isParent;
    }

    public boolean isNewCreate() {
        return newCreate;
    }

    public void setNewCreate(boolean newCreate) {
        this.newCreate = newCreate;
    }

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

    
}

