package com.zengshi.ecp.busi.pageConfig.pageConfig.vo;

import java.io.Serializable;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-busi-ecp-web-manage <br>
 * Description: 用于模块请求参数的公用入参对象<br>
 * Date:2016年6月1日上午11:13:29  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
public class CmsModularLoadVO implements Serializable{
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -900034747060220406L;
    //页面ID
    private Long pageId;
    //模块ID
    private Long modularId;
    //布局项ID
    private Long itemId;
    //点击编辑后返回的请求页面路径
    private String requestVmName;
    //页面类型
    private String pageTypeId;
    //平台类型
    private String platFormType;//只在wap模板化配置中使用，传入值：wap
    
    public Long getPageId() {
        return pageId;
    }
    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }
    public Long getModularId() {
        return modularId;
    }
    public void setModularId(Long modularId) {
        this.modularId = modularId;
    }
    public Long getItemId() {
        return itemId;
    }
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
    public String getRequestVmName() {
        return requestVmName;
    }
    public void setRequestVmName(String requestVmName) {
        this.requestVmName = requestVmName;
    }
    public String getPageTypeId() {
        return pageTypeId;
    }
    public void setPageTypeId(String pageTypeId) {
        this.pageTypeId = pageTypeId;
    }
	public String getPlatFormType() {
		return platFormType;
	}
	public void setPlatFormType(String platFormType) {
		this.platFormType = platFormType;
	}
    
    
}

