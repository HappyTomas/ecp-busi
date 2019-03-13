package com.zengshi.ecp.busi.goods.common.vo;

import java.io.Serializable;

public class BaseTreeVO implements Serializable{
	
	/** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
	private static final long serialVersionUID = 7276390412958314240L;
	private String id;
    private String pId;
    private String name;
    private String font;
    private Boolean isParent;
    private Boolean click;
    private Boolean open;
    private String icon;
    private Boolean chkDisabled = false;
    
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
	public Boolean getClick() {
		return click;
	}
	public void setClick(Boolean click) {
		this.click = click;
	}
	public Boolean getOpen() {
		return open;
	}
	public void setOpen(Boolean open) {
		this.open = open;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Boolean getChkDisabled() {
		return chkDisabled;
	}
	public void setChkDisabled(Boolean chkDisabled) {
		this.chkDisabled = chkDisabled;
	}
    public String getFont() {
        return font;
    }
    public void setFont(String font) {
        this.font = font;
    }
	
	
    

}

